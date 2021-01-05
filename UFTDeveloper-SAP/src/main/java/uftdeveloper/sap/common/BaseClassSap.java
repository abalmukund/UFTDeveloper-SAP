package uftdeveloper.sap.common;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.sap.gui.GuiSession;
import com.hp.lft.sdk.sap.gui.GuiSessionDescription;
import com.hp.lft.sdk.sap.gui.GuiSessionFactory;
import com.hp.lft.sdk.sap.gui.Window;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.model.TestResult;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import uftdeveloper.sap.objectrepos.GenericSapObjectRepo;
import uftdeveloper.sap.objectrepos.LoginSapObjectRepo;
import uftdeveloper.sap.pageobjects.GenericPage;
import uftdeveloper.sap.testrunner.TestRunner;
import uftdeveloper.sap.utils.InitializeStaticVariables;
import uftdeveloper.sap.utils.TestData;
import uftdeveloper.sap.utils.TestDataReader;
import uftdeveloper.sap.utils.WindowsProcessKiller;

// BaseClass will be derived in all SAP page_object class
public class BaseClassSap extends PageObject {
	private static GuiSession session;
	private static final String EXCEPTIONNAME = "Exception :: ";
	private static TestData testData;
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseClassSap.class);

	public static GuiSession getSapSystem() {
		if (TestRunner.getSession() != null) {
			return TestRunner.getSession();
		} else {
			return session;
		}
	}

	/**
	 * The launchSapSSO() method will Launch SAP System
	 */
	public static void launchSapSSO(String testCaseID) {
		WindowsProcessKiller.killProcessWithProcessName("saplogon.exe");
		try {
			InitializeStaticVariables.resetStaticVariables();
			EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
			String sapSystem = environmentVariables.getProperty("te.execution.SAPsystem");
			LOGGER.info("\n INFO:  (Open SAP System)");
			LOGGER.info("\n ------------------------");
			LOGGER.info("\n EXECUTION PLATFORM: " + sapSystem);
			Window mainWindow = null;
			for (int i = 0; i < 10; i++) {
				session = GuiSessionFactory.launch(sapSystem);
				TestRunner.setSession(getSapActiveSession());
				mainWindow = LoginSapObjectRepo.mainWindow;
				if (mainWindow.exists(5)) {
					LOGGER.info("SAP Session is launched successfully");
					mainWindow.maximize();
					break;
				} else {
					LOGGER.warn(
					        "SAP Session is not launched properly, retrying to launch the session attempt: " + (i + 1));
					closeAllSession();
					GenericPage.hardCodeWait(1000);
				}
			}
			setTestData(TestDataReader.getInstance().getTestDataByTestCaseID(testCaseID));
		} catch (Exception e) {
			LOGGER.error(getExceptionname(), e);
		}
	}

	// Capture screenshot image of an SAP page
	public static RenderedImage takeSapScreenShot() {
		Window window = null;
		RenderedImage image = null;
		try {
			window = GenericSapObjectRepo.mainWindow;
			image = window.getSnapshot();
		} catch (GeneralLeanFtException e) {
			LOGGER.error(getExceptionname(), e);
		}
		return image;
	}

	public static void validateAssert(Boolean status) {
		if (status) {
			Assert.assertTrue(status);
			attachScreenShot(TestResult.SUCCESS.getAdjective());
		} else {
			attachScreenShot(TestResult.FAILURE.getAdjective());
			closeAllSession();
			Assert.assertTrue(status);
		}
	}

	public static void attachScreenShot(String stepResult) {
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String imageName = stepResult + "." + f.format(new Date());// current date is taken to generate a unique name
		                                                           // for every image.
		try {
			// Save the image into Screenshot Folder
			ImageIO.write(takeSapScreenShot(), "png",
			        new File(System.getProperty("user.dir") + "\\target\\screenShot\\" + imageName + ".png"));
		} catch (Exception e) {
			LOGGER.error(getExceptionname(), e);
		}
		try {
			Serenity.recordReportData().withTitle("ScreenShot").downloadable().fromFile(
			        Paths.get(System.getProperty("user.dir") + "\\target\\screenShot\\" + imageName + ".png"));
		} catch (IOException e) {
			LOGGER.error(getExceptionname(), e);
		}
	}

	/**
	 * Report object failure. Script unable to identify the object in the SAP screen
	 *
	 * @param e Exception
	 * @throws GeneralLeanFtException Exception thrown
	 */
	public static void objectFailed(GeneralLeanFtException e) {
		attachScreenShot(TestResult.FAILURE.getAdjective());
		closeAllSession();
		Assert.fail(e.getMessage());
	}

	/**
	 * Report NullPointerException
	 *
	 * @param e Exception
	 * @throws NullPointerException Exception thrown
	 */
	public static void objectFailed(NullPointerException e) {
		attachScreenShot(TestResult.FAILURE.getAdjective());
		closeAllSession();
		Assert.fail(e.getMessage());
	}

	// Enter Transaction code in the SAP screen
	public static void transactionCode(String tCode) {
		try {
			session.reset(tCode);
		} catch (GeneralLeanFtException e) {
			objectFailed(e);
		}
	}

	public static void closeAllSession() {
		GuiSession[] newsession = null;
		GuiSessionDescription sessionDesc = new GuiSessionDescription.Builder().build();
		try {
			newsession = GuiSessionFactory.getAllOpenSessions(sessionDesc);
			int sessionCnt = newsession.length;
			if (sessionCnt > 0) {
				for (int i = 0; i < sessionCnt; i++) {
					newsession[i].close();
				}
			}
		} catch (GeneralLeanFtException e) {
			LOGGER.error(getExceptionname(), e);
		}
	}

	public static GuiSession getSapActiveSession() {
		GuiSession[] newsession = null;
		GuiSession sessionReturn = null;
		GuiSessionDescription sessionDesc = new GuiSessionDescription.Builder().build();
		try {
			newsession = GuiSessionFactory.getAllOpenSessions(sessionDesc);
			int sessionCnt = newsession.length;
			if (sessionCnt > 0) {
				sessionReturn = newsession[0];
			}
		} catch (GeneralLeanFtException e) {
			objectFailed(e);
		}
		return sessionReturn;
	}

	public static String getExceptionname() {
		return EXCEPTIONNAME;
	}

	public static TestData getTestData() {
		return testData;
	}

	public static void setTestData(TestData testData) {
		BaseClassSap.testData = testData;
	}
}