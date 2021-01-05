package uftdeveloper.sap.common;

import java.awt.image.RenderedImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.RegExpProperty;
import com.hp.lft.sdk.sap.gui.Button;
import com.hp.lft.sdk.sap.gui.ButtonDescription;
import com.hp.lft.sdk.sap.gui.CheckBox;
import com.hp.lft.sdk.sap.gui.CheckBoxDescription;
import com.hp.lft.sdk.sap.gui.ComboBox;
import com.hp.lft.sdk.sap.gui.ComponentType;
import com.hp.lft.sdk.sap.gui.EditField;
import com.hp.lft.sdk.sap.gui.EditFieldDescription;
import com.hp.lft.sdk.sap.gui.Editor;
import com.hp.lft.sdk.sap.gui.EditorDescription;
import com.hp.lft.sdk.sap.gui.Element;
import com.hp.lft.sdk.sap.gui.ElementDescription;
import com.hp.lft.sdk.sap.gui.Grid;
import com.hp.lft.sdk.sap.gui.GridDescription;
import com.hp.lft.sdk.sap.gui.GuiSession;
import com.hp.lft.sdk.sap.gui.GuiSessionDescription;
import com.hp.lft.sdk.sap.gui.Label;
import com.hp.lft.sdk.sap.gui.LabelDescription;
import com.hp.lft.sdk.sap.gui.Menubar;
import com.hp.lft.sdk.sap.gui.MenubarDescription;
import com.hp.lft.sdk.sap.gui.OKCode;
import com.hp.lft.sdk.sap.gui.OKCodeDescription;
import com.hp.lft.sdk.sap.gui.RadioButton;
import com.hp.lft.sdk.sap.gui.RadioButtonDescription;
import com.hp.lft.sdk.sap.gui.StatusBar;
import com.hp.lft.sdk.sap.gui.StatusBarDescription;
import com.hp.lft.sdk.sap.gui.TabControl;
import com.hp.lft.sdk.sap.gui.TabControlDescription;
import com.hp.lft.sdk.sap.gui.Table;
import com.hp.lft.sdk.sap.gui.TableDescription;
import com.hp.lft.sdk.sap.gui.ToolBar;
import com.hp.lft.sdk.sap.gui.ToolBarDescription;
import com.hp.lft.sdk.sap.gui.TreeView;
import com.hp.lft.sdk.sap.gui.TreeViewDescription;
import com.hp.lft.sdk.sap.gui.Window;
import com.hp.lft.sdk.sap.gui.WindowDescription;

/**
 * <h1>SAP Object Repository Base Class</h1> The BaseClassSapObjectRepo.java
 * Class implements the common methods to define SAP objects and SAP Window,
 * which are basic to create SAP Object Repository. We are not using the LeanFT
 * Application Model to maintain the SAP Object Repository, instead of that we
 * are creating object repository using java class
 *
 * @author Bal Mukund Agrawal
 * @version 1.0
 * @since 2020-03-01
 */
public class BaseClassSapObjectRepo {
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseClassSapObjectRepo.class);

	public static TreeView treeView(Window win, String objNamae, int objIndex) {
		TreeView treeView = null;
		try {
			treeView = win.describe(TreeView.class, new TreeViewDescription.Builder().index(objIndex).name(objNamae)
			        .type(ComponentType.CTRL_TREE).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return treeView;
	}

	public static TreeView treeView(Window win, String objNamae) {
		TreeView treeView = null;
		try {
			treeView = win.describe(TreeView.class,
			        new TreeViewDescription.Builder().name(objNamae).type(ComponentType.CTRL_TREE).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return treeView;
	}

	public static Grid gridView(Window win, String objName, int objIndex) {
		Grid grid = null;
		try {
			grid = win.describe(Grid.class, new GridDescription.Builder().name(objName).index(objIndex)
			        .type(ComponentType.CTRL_GRID_VIEW).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return grid;
	}

	public static Grid gridView(Window win, String objName) {
		Grid grid = null;
		try {
			grid = win.describe(Grid.class,
			        new GridDescription.Builder().name(objName).type(ComponentType.CTRL_GRID_VIEW).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return grid;
	}

	public static Button button(Window win, String btnName) {
		Button executeButton = null;
		try {
			executeButton = win.describe(Button.class,
			        new ButtonDescription.Builder().type(ComponentType.BUTTON).name(btnName).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return executeButton;
	}

	public static Button button(Window win, String btnName, int objIndex) {
		Button executeButton = null;
		try {
			executeButton = win.describe(Button.class,
			        new ButtonDescription.Builder().type(ComponentType.BUTTON).name(btnName).index(objIndex).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return executeButton;
	}

	public static Button buttonByID(Window win, String idname) {
		Button executeButton = null;
		try {
			executeButton = win.describe(Button.class,
			        new ButtonDescription.Builder().type(ComponentType.BUTTON).id(idname).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return executeButton;
	}

	public static Button buttonByContainer(Window win, String btnName, String containername) {
		Button executeButton = null;
		try {
			executeButton = win.describe(Button.class, new ButtonDescription.Builder().type(ComponentType.BUTTON)
			        .name(btnName).containerName(containername).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return executeButton;
	}

	public static Grid grid(Window win, String objName) {
		Grid grid = null;
		try {
			grid = win.describe(Grid.class, new GridDescription.Builder().name(objName)
			        .type(com.hp.lft.sdk.sap.gui.ComponentType.CTRL_GRID_VIEW).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return grid;

	}

	public static RadioButton radiobutton(Window win, String btnName) {
		RadioButton radioButton = null;
		try {
			radioButton = win.describe(RadioButton.class,
			        new RadioButtonDescription.Builder().type(ComponentType.RADIO_BUTTON).name(btnName).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return radioButton;
	}

	public static ComboBox comboBox(Window win, String objName) {
		ComboBox comboBox = null;
		try {
			comboBox = win.describe(ComboBox.class,
			        new RadioButtonDescription.Builder().type(ComponentType.COMBO_BOX).name(objName).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return comboBox;
	}

	public static EditField editField(Window win, String objName) {
		EditField textField = null;
		try {
			textField = win.describe(EditField.class,
			        new EditFieldDescription.Builder().name(objName).type(ComponentType.C_TEXT_FIELD).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return textField;
	}

	public static EditField editFieldNameAttachedTextRegEx(Window win, String objName, String attachedText) {
		EditField textField = null;
		try {
			textField = win.describe(EditField.class,
			        new EditFieldDescription.Builder().name(new RegExpProperty(objName))
			                .attachedText(new RegExpProperty(attachedText)).type(ComponentType.C_TEXT_FIELD).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return textField;
	}

	public static EditField editFieldAttachedTextRegEx(Window win, String attachedText) {
		EditField textField = null;
		try {
			textField = win.describe(EditField.class, new EditFieldDescription.Builder()
			        .attachedText(new RegExpProperty(attachedText)).type(ComponentType.C_TEXT_FIELD).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return textField;
	}

	public static EditField textField(Window win, String objName) {
		EditField textField = null;
		try {
			textField = win.describe(EditField.class,
			        new EditFieldDescription.Builder().name(objName).type(ComponentType.TEXT_FIELD).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return textField;
	}

	public static EditField passwordField(Window win, String objName) {
		EditField textField = null;
		try {
			textField = win.describe(EditField.class,
			        new EditFieldDescription.Builder().name(objName).type(ComponentType.PASSWORD_FIELD).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return textField;
	}

	public EditField passwordField(Window win, String objName, int objIndex) {
		EditField textField = null;
		try {
			textField = win.describe(EditField.class, new EditFieldDescription.Builder().name(objName).index(objIndex)
			        .type(ComponentType.PASSWORD_FIELD).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return textField;
	}

	public static EditField editField(Window win, String objName, int objIndex) {
		EditField textField = null;
		try {
			textField = win.describe(EditField.class, new EditFieldDescription.Builder().name(objName).index(objIndex)
			        .type(ComponentType.C_TEXT_FIELD).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return textField;
	}

	public TabControl tabControl(Window win, String objName, int objIndex) {
		TabControl tabControl = null;
		try {
			tabControl = win.describe(TabControl.class, new TabControlDescription.Builder().name(objName)
			        .index(objIndex).type(ComponentType.TAB_STRIP).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return tabControl;
	}

	public static CheckBox checkBoxControl(Window win, String objName) {
		CheckBox chkBox = null;
		try {
			chkBox = win.describe(CheckBox.class,
			        new CheckBoxDescription.Builder().name(objName).type(ComponentType.CHECK_BOX).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return chkBox;
	}

	public static CheckBox checkBoxControl(Window win, String objName, int objIndex) {
		CheckBox chkBox = null;
		try {
			chkBox = win.describe(CheckBox.class, new CheckBoxDescription.Builder().name(objName).index(objIndex)
			        .type(ComponentType.CHECK_BOX).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return chkBox;
	}

	public static TabControl tabControl(Window win, String objName) {
		TabControl tabControl = null;
		try {
			tabControl = win.describe(TabControl.class, new TabControlDescription.Builder()
			        .name(new RegExpProperty(objName)).type(ComponentType.TAB_STRIP).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return tabControl;
	}

	public static TabControl tabControlId(Window win, String id) {
		TabControl tabControl = null;
		try {
			tabControl = win.describe(TabControl.class,
			        new TabControlDescription.Builder().id(id).type(ComponentType.TAB_STRIP).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return tabControl;
	}

	public static Table tableControl(Window win, String objName) {
		Table tabelControl = null;
		try {
			tabelControl = win.describe(Table.class,
			        new TableDescription.Builder().name(objName).type(ComponentType.TABLE_CONTROL).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return tabelControl;
	}

	public Table tableControl(Window win, String objName, int objIndex) {
		Table tableControl = null;
		try {
			tableControl = win.describe(Table.class, new TableDescription.Builder().name(objName).index(objIndex)
			        .type(ComponentType.TABLE_CONTROL).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return tableControl;
	}

	public static StatusBar statusBar(Window win, int objIndex) {
		StatusBar statusBar = null;
		try {
			statusBar = win.describe(StatusBar.class,
			        new StatusBarDescription.Builder().index(objIndex).type(ComponentType.STATUSBAR).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return statusBar;
	}

	public static StatusBar statusBar(Window win, String objName) {
		StatusBar statusBar = null;
		try {
			statusBar = win.describe(StatusBar.class,
			        new StatusBarDescription.Builder().name(objName).type(ComponentType.STATUSBAR).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return statusBar;
	}

	public static Label label(Window win, String objName) {
		Label label = null;
		try {
			label = win.describe(Label.class,
			        new LabelDescription.Builder().name(objName).type(ComponentType.LABEL).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return label;
	}

	public static Label label(Window win, String objName, String objText) {
		Label label = null;
		try {
			label = win.describe(Label.class,
			        new LabelDescription.Builder().name(objName).text(objText).type(ComponentType.LABEL).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return label;
	}

	public Label label(Window win, String objName, int objIndex) {
		Label label = null;
		try {
			label = win.describe(Label.class,
			        new LabelDescription.Builder().name(objName).index(objIndex).type(ComponentType.LABEL).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return label;
	}

	public Label labelById(Window win, String idname) {
		Label label = null;
		try {
			label = win.describe(Label.class,
			        new LabelDescription.Builder().id(idname).type(ComponentType.LABEL).build());

		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return label;
	}

	public static Label labelByRelativeId(Window win, String relativeid) {
		Label label = null;
		try {
			label = win.describe(Label.class,
			        new LabelDescription.Builder().relativeId(relativeid).type(ComponentType.LABEL).build());

		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return label;
	}

	public static Label modelwindowlabel(Window win, String objText) {
		Label label = null;
		try {
			label = win.describe(Label.class,
			        new LabelDescription.Builder().text(objText).type(ComponentType.LABEL).build());

		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return label;
	}

	public static Label labelRelativeID(Window win, String relativeID, String text) {
		Label label = null;
		try {
			label = win.describe(Label.class,
			        new LabelDescription.Builder().relativeId(relativeID).text(text).type(ComponentType.LABEL).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return label;
	}

	public static Element elementUserArea(Window win, String objName) {
		Element usrAreaElement = null;
		try {
			usrAreaElement = win.describe(Element.class, new ElementDescription.Builder().name(objName)
			        .type(com.hp.lft.sdk.sap.gui.ComponentType.USER_AREA).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return usrAreaElement;
	}

	public static Menubar menuBar(Window win, String objMenu) {
		Menubar menubarCtrl = null;
		try {
			menubarCtrl = win.describe(Menubar.class, new MenubarDescription.Builder().name(objMenu)
			        .type(com.hp.lft.sdk.sap.gui.ComponentType.MENUBAR).build());

		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return menubarCtrl;
	}

	public static Window sapMainWindow(GuiSession session, String windowName) {
		Window sapMainWindow = null;
		try {
			sapMainWindow = session.describe(Window.class,
			        new WindowDescription.Builder().name(windowName).type(ComponentType.MAIN_WINDOW).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return sapMainWindow;
	}

	public static Window sapMainWindow(GuiSession session, String windowName, String transactionCode) {
		Window sapMainWindow = null;
		try {
			sapMainWindow = session.describe(Window.class, new WindowDescription.Builder().name(windowName)
			        .transaction(transactionCode).type(ComponentType.MAIN_WINDOW).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return sapMainWindow;
	}

	public static Window sapMainWindow(GuiSession session, String windowName, String transactionCode, String program) {
		Window sapMainWindow = null;
		try {
			sapMainWindow = session.describe(Window.class, new WindowDescription.Builder().name(windowName)
			        .transaction(transactionCode).program(program).type(ComponentType.MAIN_WINDOW).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return sapMainWindow;
	}

	public RenderedImage takeSapScreenShot(GuiSession session) {
		RenderedImage image = null;
		try {
			sapMainWindow(session).getSnapshot();
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return image;
	}

	public static Window sapMainWindow(GuiSession session) {
		Window sapMainWindow = null;
		try {
			sapMainWindow = session.describe(Window.class,
			        new WindowDescription.Builder().name("wnd[0]").type(ComponentType.MAIN_WINDOW).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return sapMainWindow;
	}

	public static Window sapModelWindow(GuiSession session) {
		Window sapModelWindow = null;
		try {
			sapModelWindow = session.describe(Window.class,
			        new WindowDescription.Builder().name("wnd[0]").type(ComponentType.MODAL_WINDOW).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return sapModelWindow;
	}

	public static Window sapModelWindow(GuiSession session, String windowName) {
		Window sapModelWindow = null;
		try {
			sapModelWindow = session.describe(Window.class,
			        new WindowDescription.Builder().name(windowName).type(ComponentType.MODAL_WINDOW).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return sapModelWindow;
	}

	public static Window sapModelWindow(GuiSession session, String windowName, String text) {
		Window sapModelWindow = null;
		try {
			sapModelWindow = session.describe(Window.class, new WindowDescription.Builder().name(windowName).text(text)
			        .type(ComponentType.MODAL_WINDOW).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return sapModelWindow;
	}

	public RenderedImage captureScreen(Window window) {
		RenderedImage image = null;
		try {
			image = window.getSnapshot();
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return image;
	}

	public static OKCode okCode(Window window) {
		OKCode okCode = null;
		try {
			okCode = window.describe(OKCode.class,
			        new OKCodeDescription.Builder().type(ComponentType.OK_CODE_FIELD).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return okCode;
	}

	public static Editor editorField(Window win, String objName) {
		Editor editorField = null;
		try {
			editorField = win.describe(Editor.class,
			        new EditorDescription.Builder().name(objName).type(ComponentType.CTRL_TEXT_AREA).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return editorField;
	}

	public static Menubar menuBar(Window win) {
		Menubar menubarCtrl = null;
		try {
			menubarCtrl = win.describe(Menubar.class,
			        new MenubarDescription.Builder().type(com.hp.lft.sdk.sap.gui.ComponentType.MENUBAR).build());

		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return menubarCtrl;
	}

	public static EditField editField(Window win, String objName, int objIndex, String attachedText) {
		EditField textField = null;
		try {
			textField = win.describe(EditField.class, new EditFieldDescription.Builder().name(objName).index(objIndex)
			        .attachedText(attachedText).type(ComponentType.C_TEXT_FIELD).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return textField;
	}

	public static Button button(Window win, String btnName, String tooltip) {
		Button executeButton = null;
		try {
			executeButton = win.describe(Button.class,
			        new ButtonDescription.Builder().type(ComponentType.BUTTON).name(btnName).tooltip(tooltip).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return executeButton;
	}

	public static RadioButton radiobutton(Window win, String btnName, String attachText) {
		RadioButton radioButton = null;
		try {
			radioButton = win.describe(RadioButton.class, new RadioButtonDescription.Builder()
			        .type(ComponentType.RADIO_BUTTON).name(btnName).attachedText(attachText).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return radioButton;
	}

	public static EditField editField(Window win, String objName, String attachedText, String text) {
		EditField textField = null;
		try {
			textField = win.describe(EditField.class,
			        new EditFieldDescription.Builder().name(objName).attachedText(attachedText)
			                .text(new RegExpProperty(text)).type(ComponentType.C_TEXT_FIELD).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return textField;
	}

	public static EditField textField(Window win, String objName, String attachedText) {
		EditField textField = null;
		try {
			textField = win.describe(EditField.class, new EditFieldDescription.Builder().name(objName)
			        .attachedText(attachedText).type(ComponentType.TEXT_FIELD).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return textField;
	}

	public static EditField textField(Window win, String objName, int index) {
		EditField textField = null;
		try {
			textField = win.describe(EditField.class, new EditFieldDescription.Builder().name(objName).index(index)
			        .type(ComponentType.TEXT_FIELD).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return textField;
	}

	public static EditField textField(Window win, String objName, String text, int index) {
		EditField textField = null;
		try {
			textField = win.describe(EditField.class, new EditFieldDescription.Builder().name(objName).text(text)
			        .index(index).type(ComponentType.TEXT_FIELD).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return textField;
	}

	public static GuiSession session(GuiSession session, String objName) {
		GuiSession session1 = null;
		try {
			session1 = session.describe(GuiSession.class, new GuiSessionDescription.Builder().name(objName).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return session1;
	}

	public static ToolBar toolBar(Window win, String objName) {
		ToolBar toolBar = null;
		try {
			toolBar = win.describe(ToolBar.class,
			        new ToolBarDescription.Builder().name(objName).type(ComponentType.CTRL_TOOL_BAR).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return toolBar;
	}

	public static ToolBar toolBarGrid(Window win, String objName) {
		ToolBar toolBar = null;
		try {
			toolBar = win.describe(ToolBar.class,
			        new ToolBarDescription.Builder().name(objName).type(ComponentType.GRID_TOOL_BAR).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return toolBar;
	}

	public static Table getTableFromPage(Window window) {
		Table[] tableGeneric = null;
		Table table = null;
		TableDescription tableDescription = null;
		tableDescription = new TableDescription.Builder().type(ComponentType.TABLE_CONTROL).build();
		try {
			tableGeneric = window.findChildren(Table.class, tableDescription);
			if (tableGeneric.length > 0) {
				table = tableGeneric[0];
			}
		} catch (CloneNotSupportedException e) {
			LOGGER.error("Exception :: ", e);
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return table;
	}

	public static Label getGenericLastLabelByTextAndRelativeID(Window window, String objName, String relativeID) {
		LabelDescription labelDesc = null;
		if (relativeID.isEmpty() || relativeID == null) {
			labelDesc = new LabelDescription.Builder().type(ComponentType.LABEL).text(objName).build();
		} else if (objName.isEmpty() || objName == null) {
			labelDesc = new LabelDescription.Builder().type(ComponentType.LABEL).relativeId(relativeID).build();
		} else {
			labelDesc = new LabelDescription.Builder().type(ComponentType.LABEL).text(objName).relativeId(relativeID)
			        .build();
		}
		Label[] labels;
		Label label = null;
		try {
			labels = window.findChildren(Label.class, labelDesc);
			if (labels.length > 0)
				label = labels[labels.length - 1];
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		} catch (CloneNotSupportedException e) {
			LOGGER.error(BaseClassSap.getExceptionname(), e);
		}
		return label;
	}

	public static CheckBox getCheckBoxFromPage(Window window, int number) {
		CheckBox[] checkBoxes = null;
		CheckBox checkBox = null;
		CheckBoxDescription checkBxDesc = new CheckBoxDescription.Builder().type(ComponentType.CHECK_BOX).build();
		try {
			checkBoxes = window.findChildren(CheckBox.class, checkBxDesc);
			if (number == -1) {
				checkBox = checkBoxes[checkBoxes.length - 1];
			} else if (checkBoxes.length > number) {
				checkBox = checkBoxes[number];
			}
		} catch (CloneNotSupportedException e) {
			LOGGER.error("Exception :: ", e);
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return checkBox;
	}

	public static TabControl getTabControl(Window window) {
		TabControl[] tabControls = null;
		TabControl tabControl = null;
		TabControlDescription tabControlDesc = new TabControlDescription.Builder().type(ComponentType.TAB_STRIP)
		        .build();
		try {
			tabControls = window.findChildren(TabControl.class, tabControlDesc);
			if (tabControls.length > 0) {
				tabControl = tabControls[tabControls.length - 1];
			}
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		} catch (CloneNotSupportedException e) {
			LOGGER.error(BaseClassSap.getExceptionname(), e);
		}
		return tabControl;
	}

	public static RadioButton radiobuttonWithAttachedText(Window win, String attachText) {
		RadioButton radioButton = null;
		try {
			radioButton = win.describe(RadioButton.class, new RadioButtonDescription.Builder()
			        .type(ComponentType.RADIO_BUTTON).attachedText(attachText).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return radioButton;
	}

	public static Grid gridViewWithNameTitle(Window win, String objName, String title) {
		Grid grid = null;
		try {
			grid = win.describe(Grid.class, new GridDescription.Builder().name(objName).title(title)
			        .type(ComponentType.CTRL_GRID_VIEW).build());
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		}
		return grid;
	}

	public static Label[] getLabels(Window window, String objName) {
		LabelDescription labelDesc = new LabelDescription.Builder().type(ComponentType.LABEL).text(objName).build();
		Label[] labels = null;
		try {
			labels = window.findChildren(Label.class, labelDesc);
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		} catch (CloneNotSupportedException e) {
			LOGGER.error(BaseClassSap.getExceptionname(), e);
		}
		return labels;
	}

	public static CheckBox[] getCheckBoxes(Window window) {
		CheckBoxDescription checkBxDesc = new CheckBoxDescription.Builder().type(ComponentType.CHECK_BOX).build();
		CheckBox[] checkBoxes = null;
		try {
			checkBoxes = window.findChildren(CheckBox.class, checkBxDesc);
		} catch (GeneralLeanFtException e) {
			BaseClassSap.objectFailed(e);
		} catch (CloneNotSupportedException e) {
			LOGGER.error(BaseClassSap.getExceptionname(), e);
		}
		return checkBoxes;
	}
}