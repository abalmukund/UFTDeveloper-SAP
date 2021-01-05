package uftdeveloper.sap.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import uftdeveloper.sap.common.BaseClassSap;
import uftdeveloper.sap.constants.Constants;

public class TestDataReader {
	private static TestDataReader testDataReader = new TestDataReader();
	private List<TestData> testDataRead;
	private static List<String> columnNames = new ArrayList<String>();
	private JsonObject jsonObject;
	private static final Logger LOGGER = LoggerFactory.getLogger(TestDataReader.class);

	public static TestDataReader getInstance() {
		return testDataReader;
	}

	public List<TestData> getTestData() {
		Gson gson = new Gson();
		File file = new File(Constants.TESTDATA_FILE);
		TestData[] testDataArray = gson.fromJson(getExcelDataAsJsonArray(file), TestData[].class);
		return Arrays.asList(testDataArray);
	}

	private JsonArray getExcelDataAsJsonArray(File excelFile) {
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(excelFile);
		} catch (IOException | InvalidFormatException e) {
			LOGGER.error(BaseClassSap.getExceptionname(), e);
		}
		JsonArray sheetArray = new JsonArray();
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row currentRow = rowIterator.next();
			jsonObject = new JsonObject();
			if (currentRow.getRowNum() != 0) {
				addValueToJsonObject(currentRow);
				sheetArray.add(getJsonObject());
			} else {
				for (int k = 0; k < currentRow.getPhysicalNumberOfCells(); k++) {
					getColumnNames().add(currentRow.getCell(k).getStringCellValue());
				}
			}
		}
		return sheetArray;
	}

	public void setTestData() {
		this.testDataRead = getTestData();
	}

	public TestData getTestDataByTestCaseID(String testCaseID) {
		return testDataRead.stream().filter(x -> x.getTestCaseID().equalsIgnoreCase(testCaseID)).findAny().get();
	}

	private void addValueToJsonObject(Row currentRow) {
		for (int j = 0; j < getColumnNames().size(); j++) {
			if (currentRow.getCell(j) != null) {
				if (currentRow.getCell(j).getCellType() == CellType.STRING) {
					getJsonObject().addProperty(getColumnNames().get(j),
					        currentRow.getCell(j).getStringCellValue().trim());
				} else if (currentRow.getCell(j).getCellType() == CellType.NUMERIC) {
					getJsonObject().addProperty(getColumnNames().get(j), currentRow.getCell(j).getNumericCellValue());
				} else if (currentRow.getCell(j).getCellType() == CellType.BOOLEAN) {
					getJsonObject().addProperty(getColumnNames().get(j), currentRow.getCell(j).getBooleanCellValue());
				} else if (currentRow.getCell(j).getCellType() == CellType.BLANK) {
					getJsonObject().addProperty(getColumnNames().get(j), "");
				} else if (currentRow.getCell(j).getCellType() == CellType.FORMULA) {
					getJsonObject().addProperty(getColumnNames().get(j),
					        Double.toString(currentRow.getCell(j).getNumericCellValue()));
				}
			} else {
				getJsonObject().addProperty(getColumnNames().get(j), "");
			}
		}
	}

	public static List<String> getColumnNames() {
		return columnNames;
	}

	public JsonObject getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(JsonObject jsonObject) {
		this.jsonObject = jsonObject;
	}
}