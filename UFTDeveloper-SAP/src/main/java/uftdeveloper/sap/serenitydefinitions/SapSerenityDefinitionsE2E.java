package uftdeveloper.sap.serenitydefinitions;

import cucumber.api.java.en.Given;
import net.thucydides.core.annotations.Steps;
import uftdeveloper.sap.cucumbersteps.SapCucumberSteps;

public class SapSerenityDefinitionsE2E {

	@Steps
	SapCucumberSteps teststeps;

	@Given("Login in to SAP System '(.*)'$")
	public void logintoSapSystem(String testCaseID) {
		teststeps.launchSapSystemUser(testCaseID);
	}

	@Given("Select Transaction Code '(.*)'")
	public void givenSelectTransactionCode(String tCode) {
		teststeps.setTransaction(tCode);
	}

	@Given("Close SAP System")
	public void givenCloseSapSystem() {
		teststeps.closeSapSystem();
	}
}