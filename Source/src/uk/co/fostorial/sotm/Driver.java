package uk.co.fostorial.sotm;

import java.awt.Font;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Driver {

	public static void main(String[] args)
	{
		try {
			// Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}
		
		UIManager.put( "Button.font", new Font( "Comic Book", Font.PLAIN, 14 ));
		UIManager.put( "Label.font", new Font( "Comic Book", Font.PLAIN, 14 ));
		UIManager.put( "TitledBorder.font", new Font( "Comic Book", Font.PLAIN, 14 ));
		UIManager.put( "TextField.font", new Font( "Comic Book", Font.PLAIN, 14 ));
		UIManager.put( "TextArea.font", new Font( "Comic Book", Font.PLAIN, 14 ));
		UIManager.put( "Table.font", new Font( "Comic Book", Font.PLAIN, 14 ));
		UIManager.put( "TableHeader.font", new Font( "Comic Book", Font.PLAIN, 14 ));
		UIManager.put( "MenuBar.font", new Font( "Comic Book", Font.PLAIN, 14 ));
		UIManager.put( "Menu.font", new Font( "Comic Book", Font.PLAIN, 14 ));
		UIManager.put( "MenuItem.font", new Font( "Comic Book", Font.PLAIN, 14 ));
		UIManager.put( "TabbedPane.font", new Font( "Comic Book", Font.PLAIN, 14 ));
		UIManager.put( "OptionPane.font", new Font( "Comic Book", Font.PLAIN, 14 ));
		UIManager.put( "ComboBox.font", new Font( "Comic Book", Font.PLAIN, 14 ));
		UIManager.put( "InternalFrame.titleFont", new Font( "Comic Book", Font.PLAIN, 14 ));
		
		new CreatorFrame();
	}
}
