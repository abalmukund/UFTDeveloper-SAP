package uftdeveloper.sap.cucumbersteps;

import net.thucydides.core.annotations.Step;
import uftdeveloper.sap.common.BaseClassSap;
import uftdeveloper.sap.pageobjects.SapLoginPage;

public class SapCucumberSteps {

	@Step
	// Open SAP System
	public void launchSapSystemUser(String testCaseID) {
		BaseClassSap.launchSapSSO(testCaseID);
		SapLoginPage.enterUserCredentials();
	}

	@Step
	public void setTransaction(String tCode) {
		BaseClassSap.transactionCode(tCode);
	}

	@Step
	public void closeSapSystem() {
		BaseClassSap.closeAllSession();
	}
}