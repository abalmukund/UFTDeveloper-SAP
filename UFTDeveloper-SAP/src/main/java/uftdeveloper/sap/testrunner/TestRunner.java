package uftdeveloper.sap.testrunner;

import java.io.File;
import java.net.URI;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.lft.sdk.ModifiableSDKConfiguration;
import com.hp.lft.sdk.SDK;
import com.hp.lft.sdk.sap.gui.GuiSession;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import uftdeveloper.sap.common.BaseClassSap;
import uftdeveloper.sap.utils.TestDataReader;
import uftdeveloper.sap.utils.WindowsProcessKiller;

//
/**
 * Default Test Runner. Used by the Gradle default "test" task.
 *
 * @author Bal Mukund Agrawal
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(plugin = { "pretty", "html:target/site/serenity", "json:target/cucumber-report.json" }, features = {
        "src/test/resources/features" }, glue = { "uftdeveloper.sap.serenitydefinitions" })
public class TestRunner {
	private static GuiSession session;
	private static final Logger LOGGER = LoggerFactory.getLogger(TestRunner.class);

	/**
	 * TestRunner
	 */
	private TestRunner() {
	}

	@BeforeClass
	public static void setup() throws Exception {
		// initiate the SDK for leanFT to connect
		try {
			EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
			String leanFTengine = environmentVariables.getProperty("te.execution.leanFTengine");

			ModifiableSDKConfiguration config = new ModifiableSDKConfiguration();
			config.setServerAddress(new URI(leanFTengine));
			SDK.init(config);
			// Clean up the screenshot folder at every run.
			FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + "\\target\\screenShot\\"));
			TestDataReader.getInstance().setTestData();
		} catch (Exception e) {
			LOGGER.error(BaseClassSap.getExceptionname(), e);
		}
	}

	@AfterClass
	public static void cleanup() {
		WindowsProcessKiller.killProcessWithProcessName("saplogon.exe");
	}

	public static GuiSession getSession() {
		return session;
	}

	public static void setSession(GuiSession session) {
		TestRunner.session = session;
	}
}