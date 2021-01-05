package uftdeveloper.sap.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uftdeveloper.sap.common.BaseClassSap;
import uftdeveloper.sap.pageobjects.GenericPage;

public class WindowsProcessKiller extends BaseClassSap {

	private static final String TASKLIST = "tasklist";
	private static final String KILL = "taskkill /IM ";
	private static final String FORCE = " /T /F";
	private static final Logger LOGGER = LoggerFactory.getLogger(WindowsProcessKiller.class);

	private boolean isProcessRunning(String serviceName) {
		try {
			Process pro = Runtime.getRuntime().exec(TASKLIST);
			BufferedReader reader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith(serviceName)) {
					return true;
				}
			}
		} catch (IOException e) {
			LOGGER.error(BaseClassSap.getExceptionname(), e);
		}
		return false;
	}

	private void killProcess(String serviceName) {
		try {
			Runtime.getRuntime().exec(KILL + serviceName + FORCE);
			LOGGER.info(serviceName + " killed successfully!");
		} catch (IOException e) {
			LOGGER.error(BaseClassSap.getExceptionname(), e);
		}
	}

	public static void killProcessWithProcessName(String processName) {
		WindowsProcessKiller pKiller = new WindowsProcessKiller();
		boolean isRunning = true;
		while (isRunning) {
			isRunning = pKiller.isProcessRunning(processName);
			LOGGER.info("Is " + processName + " running : " + isRunning);
			if (isRunning) {
				pKiller.killProcess(processName);
				GenericPage.hardCodeWait(1000);
			} else {
				LOGGER.info("Not able to find the process : " + processName);
			}
		}
	}
}