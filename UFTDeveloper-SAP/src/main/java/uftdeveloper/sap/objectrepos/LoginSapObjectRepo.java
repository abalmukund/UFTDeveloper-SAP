package uftdeveloper.sap.objectrepos;

import com.hp.lft.sdk.sap.gui.Button;
import com.hp.lft.sdk.sap.gui.EditField;
import com.hp.lft.sdk.sap.gui.GuiSession;
import com.hp.lft.sdk.sap.gui.Window;

import uftdeveloper.sap.common.BaseClassSap;
import uftdeveloper.sap.common.BaseClassSapObjectRepo;

/**
 * <h1>An Example Object Repository Class</h1> The LoginSapObjectRepo.java Class
 * implements Object Repository for for Login transaction code Its a Example
 * page object class for user reference and user can create similar Object
 * Repository for their project.
 *
 * @author Bal Mukund Agrawal
 * @version 1.0
 * @since 2020-03-01
 */
public final class LoginSapObjectRepo extends BaseClassSapObjectRepo {
	public static final GuiSession session = BaseClassSap.getSapSystem();
	public static final Window mainWindow = sapMainWindow(session);
	public static final EditField editUserId = textField(mainWindow, "UserName");
	public static final EditField editPassword = passwordField(mainWindow, "Password");
	public static final Button enterButton = button(mainWindow, "btn[0]");
	public static final Button continueButton = button(sapModelWindow(session, "wnd[1]"), "btn[0]");
}