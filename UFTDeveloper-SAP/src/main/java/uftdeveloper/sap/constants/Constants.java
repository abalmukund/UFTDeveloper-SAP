package uftdeveloper.sap.constants;

public class Constants {
	public static final String LOCAL_FILE_PATH = "C:\\Jenkins\\workspace\\SAPQAAS\\";
	public static final String TESTDATA_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\";
	public static final String GETFROMSTATIC = "getfromstatic";
	public static final String BLANKVALUE = "blankvalue";
	public static final String DONTENTER = "dontenter";
	public static final String TESTDATA_FILE = "src//test//resources//testData//testData.xlsx";
	public static final String DATEFORMAT = "dd.MM.yyyy";

	private Constants() {
		throw new IllegalStateException("Initialize Constants Class");
	}
}
