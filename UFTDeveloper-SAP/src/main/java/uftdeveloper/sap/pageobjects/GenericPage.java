package uftdeveloper.sap.pageobjects;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.ScrollOrientation;
import com.hp.lft.sdk.sap.gui.Grid;
import com.hp.lft.sdk.sap.gui.RadioButton;
import com.hp.lft.sdk.sap.gui.Table;
import com.hp.lft.sdk.sap.gui.TableCell;
import com.hp.lft.sdk.sap.gui.ToolBar;
import com.hp.lft.sdk.sap.gui.ToolBarDescription;
import com.hp.lft.sdk.sap.gui.Window;

import net.thucydides.core.model.TestResult;
import uftdeveloper.sap.common.BaseClassSap;
import uftdeveloper.sap.common.BaseClassSapObjectRepo;
import uftdeveloper.sap.constants.Constants;
import uftdeveloper.sap.objectrepos.GenericSapObjectRepo;

/**
 * The GenericPage.java Class implements Page object class for re-usability of
 * common functions across the project
 *
 * @author Bal Mukund Agrawal
 * @version 1.0
 * @since 2020-03-17
 */

public class GenericPage extends BaseClassSap {
	private static final Logger LOGGER = LoggerFactory.getLogger(GenericPage.class);

	public static void clickSaveButton() {
		try {
			GenericSapObjectRepo.saveButton.click();
		} catch (GeneralLeanFtException e) {
			objectFailed(e);
		}
	}

	public static void validateStatusBarText(String textToValidate) {
		try {
			validateAssert(GenericSapObjectRepo.statusBar.getText().contentEquals(textToValidate));
		} catch (GeneralLeanFtException e) {
			objectFailed(e);
		}
	}

	public static void createReportFolder(String folderName) {
		try {
			File file = new File("C:\\" + folderName + "");
			if (!file.exists()) {
				if (file.mkdir()) {
					LOGGER.info("Report Directory is created!");
				} else {
					LOGGER.info("Failed to create directory!");
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String getCurrentDate() {
		String strDate = null;
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATEFORMAT);
			strDate = sdf.format(cal.getTime());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return strDate;
	}

	public static String getAlphaNumericString(int n) {
		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			int index = (int) (alphaNumericString.length() * Math.random());
			sb.append(alphaNumericString.charAt(index));
		}
		return sb.toString();
	}

	/**
	 * @author Bal Mukund Agrawal Apr 8, 2020
	 * @return
	 */
	public static String fetchOrderFromStatusBar() {
		String orderNumber = null;
		try {
			hardCodeWait(1000);
			String statusText = GenericSapObjectRepo.statusBar.getText();
			LOGGER.info(statusText);
			Pattern p = Pattern.compile("\\d+");
			Matcher m = p.matcher(statusText);
			if (m.find()) {
				orderNumber = m.group(0);
			}
			BaseClassSap.attachScreenShot(TestResult.SUCCESS.getAdjective());
		} catch (GeneralLeanFtException e) {
			objectFailed(e);
		}
		return orderNumber;
	}

	/**
	 * @author Bal Mukund Agrawal Apr 14, 2020
	 * @param min
	 * @param max
	 * @return
	 */
	public static String getRandomNumberInRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		Random r = new Random();
		return String.valueOf(r.nextInt((max - min) + 1) + min);
	}

	public static void clickEnterButton() {
		try {
			GenericSapObjectRepo.enterButton.click();
		} catch (GeneralLeanFtException e) {
			objectFailed(e);
		}
	}

	/**
	 * @author Bal Mukund Agrawal
	 * @since Apr 23, 2020
	 * @returnType void
	 * @param tCode
	 */
	public static void enterOKCode(String tCode) {
		try {
			GenericSapObjectRepo.okCommand.highlight();
			GenericSapObjectRepo.okCommand.setValue(tCode);
			clickEnterButton();
		} catch (GeneralLeanFtException e) {
			objectFailed(e);
		}
	}

	/**
	 * @author Bal Mukund Agrawal
	 * @since Apr 23, 2020
	 * @returnType String
	 * @param sDateDuration
	 * @return
	 */
	public static String getAnyDate(String sDateDuration) {
		String strDate = null;
		try {
			if (sDateDuration.contains("+") || sDateDuration.contains("-")) {
				Calendar cal = Calendar.getInstance();
				String sDate = sDateDuration.split("T")[1].trim();
				cal.add(Calendar.DATE, Integer.valueOf(sDate));
				SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATEFORMAT);
				strDate = sdf.format(cal.getTime());
			} else if ("today".equalsIgnoreCase(sDateDuration)) {
				strDate = getCurrentDate();
			} else {
				strDate = sDateDuration;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return strDate;
	}

	/**
	 * @author Bal Mukund Agrawal
	 * @since Apr 23, 2020
	 * @returnType String
	 * @return
	 */
	public static String getTimestampInMS() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return String.valueOf(timestamp.getTime());
	}

	/**
	 * @author Bal Mukund Agrawal
	 * @since Apr 23, 2020
	 * @returnType String
	 * @return
	 */
	public static String fetchalphanumericnumber() {
		String materialnumber = null;
		try {
			hardCodeWait(3000);
			String statusText;
			statusText = GenericSapObjectRepo.statusBar.getText();
			LOGGER.info(statusText);
			String[] strArray = statusText.split(" ");
			materialnumber = strArray[1];
			LOGGER.info(materialnumber);
			BaseClassSap.attachScreenShot(TestResult.SUCCESS.getAdjective());
		} catch (GeneralLeanFtException e) {
			objectFailed(e);
		}
		return materialnumber;
	}

	/**
	 * @author Bal Mukund Agrawal
	 * @since May 5, 2020
	 * @returnType String
	 * @return
	 */
	public static String getCurrentMonth() {
		return String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
	}

	public static String getCurrentYear() {
		return String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
	}

	public static void hardCodeWait(long waitTime) {
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean isValidDate(String inDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATEFORMAT);
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(inDate.trim());
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}

	public static List<String> fetchOrderAndDeliveryNumberFromStatusBar() {
		List<String> list = new ArrayList<String>();
		try {
			hardCodeWait(1000);
			String statusText = GenericSapObjectRepo.statusBar.getText();
			LOGGER.info(statusText);
			Pattern p = Pattern.compile("\\d+");
			Matcher m = p.matcher(statusText);
			while (m.find()) {
				list.add(m.group(0));
			}
			validateAssert(list.size() != 2);
		} catch (GeneralLeanFtException e) {
			objectFailed(e);
		}
		return list;
	}

	public static String getfutureDatefromcurrentdate(int month) {
		String strDate = null;
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATEFORMAT);
			cal.add(Calendar.MONTH + 1, month);
			strDate = sdf.format(cal.getTime());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return strDate;
	}

	public static String getYearInYYYYFormat(int number) {
		String strYear = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, number);
		strYear = sdf.format(cal.getTime());
		return strYear;
	}

	public static int getRowNumberFromTable(Table tableName, String colName, String searchValue) {
		int rowNumber = -1;
		int rowCnt;
		try {
			rowCnt = tableName.getRows().size();
			for (int i = 0; i < rowCnt; i++) {
				if (tableName.getRows().get(i).getCell(colName).getValue().toString().trim()
				        .equalsIgnoreCase(searchValue)) {
					rowNumber = i;
					break;
				}
			}
		} catch (GeneralLeanFtException e) {
			objectFailed(e);
		}
		return rowNumber;
	}

	public static int getRowNumberFromGrid(Grid gridName, String colName, String searchValue) {
		int rowNumber = -1;
		int rowCnt;
		try {
			rowCnt = gridName.getRows().size();
			for (int i = 0; i < rowCnt; i++) {
				if (gridName.getRows().get(i).getCell(colName).getValue().toString().trim()
				        .equalsIgnoreCase(searchValue)) {
					rowNumber = i;
					break;
				}
			}
		} catch (GeneralLeanFtException e) {
			objectFailed(e);
		}
		return rowNumber;
	}

	public static int getEditableRowNumberFromTable(Table tableName, String colName) {
		int rowNumber = -1;
		int rowCnt;
		try {
			rowCnt = tableName.getRows().size();
			for (int i = 0; i < rowCnt; i++) {
				if (tableName.getRows().get(i).getCell(colName).isEditable()) {
					rowNumber = i;
					break;
				}
			}
		} catch (GeneralLeanFtException e) {
			objectFailed(e);
		}
		return rowNumber;
	}

	public static int getEditableRowNumberFromGrid(Grid gridName, String colName) {
		int rowNumber = -1;
		int rowCnt;
		try {
			rowCnt = gridName.getRows().size();
			for (int i = 0; i < rowCnt; i++) {
				if (gridName.getRows().get(i).getCell(colName).isEditable()) {
					rowNumber = i;
					break;
				}
			}
		} catch (GeneralLeanFtException e) {
			objectFailed(e);
		}
		return rowNumber;
	}

	public static int getEditableEmptyRowNumberFromTable(Table tableName, String colName) {
		int rowNumber = -1;
		int rowCnt;
		try {
			rowCnt = tableName.getRows().size();
			for (int i = 0; i < rowCnt; i++) {
				if ((tableName.getRows().get(i).getCell(colName).isEditable())
				        && (tableName.getRows().get(i).getCell(colName).getValue().toString().isEmpty())) {
					rowNumber = i;
					break;
				}
			}
		} catch (GeneralLeanFtException e) {
			objectFailed(e);
		}
		return rowNumber;
	}

	public static int getEditableColumnFromTable(Table table) {
		List<TableCell> columns = null;
		Boolean editableFlag = false;
		int colIndex = 0;
		try {
			columns = table.getRows().get(0).getCells();
		} catch (GeneralLeanFtException e) {
			objectFailed(e);
		}
		for (int i = 0; i < columns.size(); i++) {
			try {
				editableFlag = columns.get(i).isEditable();
			} catch (GeneralLeanFtException e) {
				LOGGER.error("Exception :: ", e);
				editableFlag = false;
			}
			if (editableFlag) {
				colIndex = i;
				break;
			}
		}
		return colIndex;
	}

	public static String getCurrentYearInYYFormat() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy");
		return sdf.format(date);
	}

	public static boolean checkBetweenDates(String dateToCheck, String startDate, String endDate) {
		boolean res = false;
		SimpleDateFormat fmt1 = new SimpleDateFormat(Constants.DATEFORMAT);
		try {
			Date requestDate = fmt1.parse(dateToCheck);
			Date fromDate = fmt1.parse(startDate);
			Date toDate = fmt1.parse(endDate);
			res = requestDate.compareTo(fromDate) >= 0 && requestDate.compareTo(toDate) <= 0;
		} catch (ParseException pex) {
			LOGGER.error(getExceptionname(), pex);
		}
		return res;

	}

	public static void selectRadioButtonWithAttachedText(Window window, String objName, String attachedtext) {
		try {
			RadioButton radioButton = BaseClassSapObjectRepo.radiobutton(window, objName, attachedtext);
			int iCount = 0;
			while (!radioButton.exists(1)) {
				window.scroll(ScrollOrientation.VERTICAL_SCROLL, iCount + 1);
				iCount++;
			}
			radioButton.setFocus();
			radioButton.set();
		} catch (GeneralLeanFtException e) {
			objectFailed(e);
		}
	}

	public static String getNextDatefromcurrentdate() {
		String strDate = null;
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATEFORMAT);
			cal.add(Calendar.DAY_OF_YEAR, 1);
			strDate = sdf.format(cal.getTime());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return strDate;
	}

	public static boolean validateValueInGrid(Grid objName, String colName, String value) {
		boolean bFlag = false;
		ToolBar gridToolbarToolBar;
		for (int i = 0; i < 30; i++) {
			try {
				gridToolbarToolBar = GenericSapObjectRepo.mainWindow.describe(ToolBar.class,
				        new ToolBarDescription.Builder().name("shell")
				                .type(com.hp.lft.sdk.sap.gui.ComponentType.GRID_TOOL_BAR).build());
				gridToolbarToolBar.pressButton("&REFRESH");
				hardCodeWait(5000);
				if (objName.findRowWithCellTextInColumn(value, colName).getCell(colName).getValue().toString().trim()
				        .equalsIgnoreCase(value)) {
					bFlag = true;
					break;
				}
			} catch (GeneralLeanFtException e) {
				LOGGER.error(getExceptionname(), e);
			}
			hardCodeWait(10000);
		}
		return bFlag;
	}

	public static void deleteCreatedFile(String filePath) {
		File myObj = new File(filePath);
		myObj.delete();
	}

	public static List<String> getAlphaNumericValueFromText() {
		List<String> value = new ArrayList<String>();
		try {
			hardCodeWait(1000);
			String statusText = GenericSapObjectRepo.statusBar.getText();
			LOGGER.info(statusText);
			Pattern p = Pattern.compile("[A-Z0-9]+[0-9A-Z]");
			Matcher m = p.matcher(statusText);
			while (m.find()) {
				value.add(m.group(0));
			}
			BaseClassSap.attachScreenShot(TestResult.SUCCESS.getAdjective());
		} catch (GeneralLeanFtException e) {
			objectFailed(e);
		}
		return value;
	}

	public static String createDelimittedString(String stringOne, String stringTwo) {
		String newString = null;
		if (stringOne == null) {
			newString = stringTwo;
		} else {
			newString = stringOne + ";" + stringTwo;
		}
		return newString;
	}

	public static float randFloat(float min, float max) {
		Random rand = new Random();
		BigDecimal bigDecimal = new BigDecimal(Float.toString(rand.nextFloat() * (max - min) + min));
		bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
		return bigDecimal.floatValue();
	}

	public static Boolean matchStatusBarText(String textToValidate) {
		Boolean bFlag = false;
		try {
			bFlag = GenericSapObjectRepo.statusBar.getText().contains(textToValidate);
		} catch (GeneralLeanFtException e) {
			objectFailed(e);
		}
		return bFlag;
	}

	public static String getFirstDayOfMonthYear(int month, int year) {
		Calendar cal = Calendar.getInstance();
		if (month == 0) {
			month = cal.get(Calendar.MONTH) + 1;
		}
		String returnDate = null;
		returnDate = "01." + month + "." + getYearInYYYYFormat(year);
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATEFORMAT);
		try {
			returnDate = String.valueOf(sdf.format(sdf.parse(returnDate)));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return returnDate;
	}

	public static String getLastDateOfYear() {
		return "31.12." + getYearInYYYYFormat(0);
	}

	public static String getNextMonthFromGivenDate(String givenDate) {
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		DateTimeFormatter sdf1 = DateTimeFormatter.ofPattern("MM");
		LocalDate ld = LocalDate.parse(givenDate, sdf);
		LocalDate nextMonthDate = ld.plusMonths(1);
		return nextMonthDate.format(sdf1);
	}

	public static String getNextYearFromGivenDate(String givenDate) {
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDate ld = LocalDate.parse(givenDate, sdf);
		LocalDate nextMonthDate = ld.plusMonths(1);
		return String.valueOf(nextMonthDate.getYear());
	}
}