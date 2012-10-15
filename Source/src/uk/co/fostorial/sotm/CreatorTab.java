package uk.co.fostorial.sotm;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CreatorTab extends JSplitPane {

	private JScrollPane imagePaneScroll;
	private JLayeredPane imagePane;
	private JPanel properties;
	private JScrollPane propertiesScroll;
	
	private int imageWidth = 1;
	private int imageHeight = 1;
	
	private CreatorFrame frame;
	
	public CreatorTab(CreatorFrame frame)
	{
		super();
		
		this.setFrame(frame);
		
		setup();
	}
	
	private void setup()
	{
		this.setAutoscrolls(true);
		
		imagePaneScroll = new JScrollPane();
		imagePaneScroll.setAutoscrolls(true);
		imagePane = new JLayeredPane();
		imagePaneScroll.setViewportView(imagePane);
		this.setLeftComponent(imagePaneScroll);
		
		propertiesScroll = new JScrollPane();
		propertiesScroll.setAutoscrolls(true);
		properties = new JPanel();
		propertiesScroll.setViewportView(properties);
		this.setRightComponent(propertiesScroll);
	}
	
	public void saveToJPG()
	{
		try {
			JFileChooser chooser = new JFileChooser();
			int outcome = chooser.showSaveDialog(this);
			
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				File f = chooser.getSelectedFile();
				if (f.getAbsolutePath().toLowerCase().endsWith(".jpg") == false 
						|| f.getAbsolutePath().toLowerCase().endsWith(".jpeg") == false)
				{
					f = new File(f.getAbsolutePath() + ".jpg");
				}
				
				int w = imageWidth;
                int h = imageHeight;
                int type = BufferedImage.TYPE_INT_RGB;
                BufferedImage image = new BufferedImage(w, h, type);
                Graphics2D g2 = image.createGraphics();
                imagePane.paint(g2);
                g2.dispose();
				
				File outputfile = f;
		    	ImageIO.write(image, "jpg", outputfile);
			}
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public void saveToPNG()
	{
		try {
			JFileChooser chooser = new JFileChooser();
			int outcome = chooser.showSaveDialog(this);
			
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				File f = chooser.getSelectedFile();
				if (f.getAbsolutePath().toLowerCase().endsWith(".png") == false)
				{
					f = new File(f.getAbsolutePath() + ".png");
				}
				
				int w = imageWidth;
                int h = imageHeight;
                int type = BufferedImage.TYPE_INT_RGB;
                BufferedImage image = new BufferedImage(w, h, type);
                Graphics2D g2 = image.createGraphics();
                imagePane.paint(g2);
                g2.dispose();
				
				File outputfile = f;
		    	ImageIO.write(image, "png", outputfile);
			}
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}

	public CreatorFrame getFrame() {
		return frame;
	}

	public void setFrame(CreatorFrame frame) {
		this.frame = frame;
	}

	public JLayeredPane getImagePane() {
		return imagePane;
	}

	public void setImagePane(JLayeredPane imagePane) {
		this.imagePane = imagePane;
	}

	public JPanel getProperties() {
		return properties;
	}

	public void setProperties(JPanel properties) {
		this.properties = properties;
	}

	public JScrollPane getImagePaneScroll() {
		return imagePaneScroll;
	}

	public void setImagePaneScroll(JScrollPane imagePaneScroll) {
		this.imagePaneScroll = imagePaneScroll;
	}

	public JScrollPane getPropertiesScroll() {
		return propertiesScroll;
	}

	public void setPropertiesScroll(JScrollPane propertiesScroll) {
		this.propertiesScroll = propertiesScroll;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	public class PropertiesDocumentListener implements DocumentListener
	{
		private JLabel label;
		
		public PropertiesDocumentListener(JLabel label)
		{
			this.label = label;
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			try
			{
				label.setText(e.getDocument().getText(0, e.getDocument().getLength()));
			}
			catch (Exception ex)
			{
				label.setText("ERROR!");
			}
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			try
			{
				label.setText(e.getDocument().getText(0, e.getDocument().getLength()));
			}
			catch (Exception ex)
			{
				label.setText("ERROR!");
			}
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			try
			{
				label.setText(e.getDocument().getText(0, e.getDocument().getLength()));
			}
			catch (Exception ex)
			{
				label.setText("ERROR!");
			}
		}
		
	}
	
	public class MultilinePropertiesDocumentListener implements DocumentListener
	{
		private JLabel label;
		
		public MultilinePropertiesDocumentListener(JLabel label)
		{
			this.label = label;
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			try
			{
				String txt = e.getDocument().getText(0, e.getDocument().getLength());
				txt.replace("\n", "<br>");
				label.setText("<html>" + txt + "</html>");
			}
			catch (Exception ex)
			{
				label.setText("ERROR!");
			}
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			try
			{
				String newline = System.getProperty("line.separator");
				String txt = e.getDocument().getText(0, e.getDocument().getLength());
				//String txt = e.getDocument().getProperty(key);
				txt.replace(newline, "|");
				label.setText("<html>" + txt + "</html>");
			}
			catch (Exception ex)
			{
				label.setText("ERROR!");
			}
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			try
			{
				String txt = e.getDocument().getText(0, e.getDocument().getLength());
				txt.replace("\n", "<br>");
				label.setText("<html>" + txt + "</html>");
				//label.setText(e.getDocument().getText(0, e.getDocument().getLength()));
			}
			catch (Exception ex)
			{
				label.setText("ERROR!");
			}
		}
		
	}
	
	public class TextareaPropertiesDocumentListener implements DocumentListener
	{
		private JTextArea label;
		
		public TextareaPropertiesDocumentListener(JTextArea label)
		{
			this.label = label;
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			try
			{
				label.setDocument(e.getDocument());
			}
			catch (Exception ex)
			{
				label.setText("ERROR!");
			}
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			try
			{
				label.setDocument(e.getDocument());
			}
			catch (Exception ex)
			{
				label.setText("ERROR!");
			}
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			try
			{
				label.setDocument(e.getDocument());
			}
			catch (Exception ex)
			{
				label.setText("ERROR!");
			}
		}
		
	}
}
