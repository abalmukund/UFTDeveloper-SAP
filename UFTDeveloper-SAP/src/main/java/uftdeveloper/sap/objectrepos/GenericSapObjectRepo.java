package uftdeveloper.sap.objectrepos;

import com.hp.lft.sdk.sap.gui.Button;
import com.hp.lft.sdk.sap.gui.GuiSession;
import com.hp.lft.sdk.sap.gui.OKCode;
import com.hp.lft.sdk.sap.gui.StatusBar;
import com.hp.lft.sdk.sap.gui.Window;

import uftdeveloper.sap.common.BaseClassSap;
import uftdeveloper.sap.common.BaseClassSapObjectRepo;

/**
 * The GenericSapObjectRepo.java Class is a common Object Repository used across
 * the framework
 *
 * @author Bal Mukund Agrawal
 * @version 1.0
 * @since 2020-03-17
 */

public final class GenericSapObjectRepo extends BaseClassSapObjectRepo {
	public static final GuiSession session = BaseClassSap.getSapSystem();
	public static final Window mainWindow = sapMainWindow(session);
	public static final Window modalWindow = sapModelWindow(session, "wnd[1]");
	public static final Button saveButton = button(mainWindow, "btn[11]");
	public static final StatusBar statusBar = statusBar(mainWindow, 0);
	public static final Button enterButton = button(mainWindow, "btn[0]");
	public static final OKCode okCommand = okCode(mainWindow);
	public static final Button nextPageButton = button(mainWindow, "btn[82]");
	public static final Button previousPageButton = button(mainWindow, "btn[81]");
}