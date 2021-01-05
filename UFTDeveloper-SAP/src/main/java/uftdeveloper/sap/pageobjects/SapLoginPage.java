package uftdeveloper.sap.pageobjects;

import com.hp.lft.sdk.GeneralLeanFtException;

import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import uftdeveloper.sap.common.BaseClassSap;
import uftdeveloper.sap.objectrepos.LoginSapObjectRepo;

/**
 * <h1>An Example Page object Class</h1> The SapLoginPage.java Class implements
 * Page obejct class for SapLoginPage. Its a Example page object class for user
 * reference and user can create similar page object class for their project.
 *
 * @author Bal Mukund Agrawal
 * @version 1.0
 * @since 2020-03-01
 */
public class SapLoginPage extends BaseClassSap {

	public static void enterUserCredentials() {
		try {
			EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
			String userFromJenkins = environmentVariables.getProperty("sap.user");
			String passwordFromJenkins = environmentVariables.getProperty("sap.pass");
			LoginSapObjectRepo.editUserId.waitUntilExists(10);
			LoginSapObjectRepo.editUserId.highlight();
			LoginSapObjectRepo.editUserId.setText(userFromJenkins);
			LoginSapObjectRepo.editPassword.setText(passwordFromJenkins);
			LoginSapObjectRepo.enterButton.click();
			LoginSapObjectRepo.continueButton.click();
		} catch (GeneralLeanFtException e) {
			objectFailed(e);
		}
	}
}
