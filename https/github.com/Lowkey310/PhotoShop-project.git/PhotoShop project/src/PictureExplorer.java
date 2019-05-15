
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.IOException;

import javax.swing.border.*;
/**
 * Displays a picture and lets you explore the picture by displaying the row, column, red,
 * green, and blue values of the pixel at the cursor when you click a mouse button or
 * press and hold a mouse button while moving the cursor.  It also lets you zoom in or
 * out.  You can also type in a row and column value to see the color at that location.
 * 
 * Originally created for the Jython Environment for Students (JES). 
 * Modified to work with DrJava by Barbara Ericson
 * Also modified to show row and columns by Barbara Ericson
 * 
 * @author Keith McDermottt, gte047w@cc.gatech.edu
 * @author Barb Ericson ericson@cc.gatech.edu
 */
public class PictureExplorer implements MouseMotionListener, ActionListener, MouseListener
	  {
	  private int rowIndex = 0; 
	  private int colIndex = 0;
	  private JFrame pictureFrame;
	  private JScrollPane scrollPane;
	  private JLabel colLabel;
	  private JButton colPrevButton;
	  private JButton rowPrevButton;
	  private JButton colNextButton;
	  private JButton rowNextButton;
	  private JLabel rowLabel;
	  private JTextField colValue;
	  private JTextField rowValue;
	  private JLabel rValue;
	  private JLabel gValue;
	  private JLabel bValue;
	  private JLabel colorLabel;
	  private JPanel colorPanel;
	  private JMenuBar menuBar;
	  private JMenu zoomMenu;
	  private JMenuItem twentyFive;
	  private JMenuItem fifty;
	  private JMenuItem seventyFive;
	  private JMenuItem hundred;
	  private JMenuItem hundredFifty;
	  private JMenuItem twoHundred;
	  private JMenuItem fiveHundred;
	  
	  private JMenu changePictureMenu;
	  private JMenuItem changeColor;
	  	private JMenuItem grayscale;
	  	private JMenuItem zeroBlue;
	  	private JMenuItem keepOnlyBlue;
	  	private JMenuItem zeroRed;
	  	private JMenuItem keepOnlyRed;
	  	private JMenuItem zeroGreen;
	  	private JMenuItem keepOnlyGreen;
	  private JMenuItem negate;
	  private JMenuItem fixUnderwater;
	  private JMenuItem mirrorImage;
	  	private JMenuItem mirrorVertical;
	  	private JMenuItem mirrorHorizontal;
	  	private JMenuItem mirrorDiagonal;
	  private JMenuItem createCollage;
	  private JMenuItem detectEdge;
	  private JMenuItem revertToOriginal;
	  private JMenuItem textEditor;
	  private JMenuItem drawingPad;
	  
	  private DigitalPicture picture;
	  private ImageIcon scrollImageIcon;
	  private ImageDisplay imageDisplay;
	  private double zoomFactor;
	  private int numberBase=0;
  /**
   * Public constructor 
   * @param picture the picture to explore
   */
	  public PictureExplorer(DigitalPicture picture)
		  {
    // set the fields
		  this.picture=picture;
		  zoomFactor=1;
    // create the window and set things up
		  createWindow();
		  }
  
  /**
   * Changes the number system to start at one
   */
	  public void changeToBaseOne()
		  {
		  numberBase=1;
		  } 
  /**
   * Set the title of the frame
   *@param title the title to use in the JFrame
   */
	  public void setTitle(String title)
		  {
		  pictureFrame.setTitle(title);
		  }
  /**
   * Method to create and initialize the picture frame
   */
	  private void createAndInitPictureFrame()
		{
	    pictureFrame = new JFrame(); // create the JFrame
	    pictureFrame.setResizable(true);  // allow the user to resize it
	    pictureFrame.getContentPane().setLayout(new BorderLayout()); // use border layout
	    pictureFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // when close stop
	    pictureFrame.setTitle(picture.getTitle());
	    PictureExplorerFocusTraversalPolicy newPolicy = new PictureExplorerFocusTraversalPolicy();
	    pictureFrame.setFocusTraversalPolicy(newPolicy);  
		}
  /**
   * Method to create the menu bar, menus, and menu items
   */
	  private void setUpMenuBar()
		{
    //create menu
	    menuBar = new JMenuBar();
		    zoomMenu = new JMenu("Zoom");
			    twentyFive = new JMenuItem("25%");
			    fifty = new JMenuItem("50%");
			    seventyFive = new JMenuItem("75%");
			    hundred = new JMenuItem("100%");
			    hundred.setEnabled(false);
			    hundredFifty = new JMenuItem("150%");
			    twoHundred = new JMenuItem("200%");
			    fiveHundred = new JMenuItem("500%");
		    
		    changePictureMenu = new JMenu("Change Picture");
		    	changeColor = new JMenu("Change Color");
				    grayscale = new JMenuItem("Grayscale");
				    zeroBlue = new JMenuItem("Zero Blue");
				    keepOnlyBlue = new JMenuItem("Keep Only Blue");
				    zeroRed = new JMenuItem("Zero Red");
				    keepOnlyRed = new JMenuItem("Keep Only Red");
				    zeroGreen = new JMenuItem("Zero Green");
				    keepOnlyGreen = new JMenuItem("Keep Only Green");
			    negate = new JMenuItem("Negate");
			    fixUnderwater = new JMenuItem("Fix Underwater");
			    mirrorImage = new JMenu("Mirror Image");
				    mirrorVertical = new JMenuItem("Mirror Vertically");
				    mirrorHorizontal = new JMenuItem("Mirror Horizontally");
				    mirrorDiagonal = new JMenuItem("Mirror Diagonally");
			    createCollage = new JMenuItem("Create Collage");
			    detectEdge = new JMenuItem("Detect Edge");
			    textEditor = new JMenuItem("Add Text");
			    drawingPad = new JMenuItem("Drawing Pad");
			    revertToOriginal = new JMenuItem("Return To Original Picture");
    
    // add the action listeners
	    twentyFive.addActionListener(this);
	    fifty.addActionListener(this);
	    seventyFive.addActionListener(this);
	    hundred.addActionListener(this);
	    hundredFifty.addActionListener(this);
	    twoHundred.addActionListener(this);
	    fiveHundred.addActionListener(this);
	    
	    changeColor.addActionListener(this);
	    grayscale.addActionListener(this);
	    zeroBlue.addActionListener(this);
	    keepOnlyBlue.addActionListener(this);
	    zeroRed.addActionListener(this);
	    zeroGreen.addActionListener(this);
	    keepOnlyRed.addActionListener(this);
	    keepOnlyGreen.addActionListener(this);
	    negate.addActionListener(this);
	    fixUnderwater.addActionListener(this);
	    mirrorImage.addActionListener(this);
	    mirrorVertical.addActionListener(this);
	    mirrorHorizontal.addActionListener(this);
	    mirrorDiagonal.addActionListener(this);
	    createCollage.addActionListener(this);
	    detectEdge.addActionListener(this);
	    textEditor.addActionListener(this);
	    drawingPad.addActionListener(this);
	    revertToOriginal.addActionListener(this);
    
    // add the menu items to the menus
	    zoomMenu.add(twentyFive);
	    zoomMenu.add(fifty);
	    zoomMenu.add(seventyFive);
	    zoomMenu.add(hundred);
	    zoomMenu.add(hundredFifty);
	    zoomMenu.add(twoHundred);
	    zoomMenu.add(fiveHundred);
	    
	    mirrorImage.add(mirrorDiagonal);
	    mirrorImage.add(mirrorHorizontal);
	    mirrorImage.add(mirrorVertical);
	    
	    changeColor.add(zeroRed);
	    changeColor.add(zeroGreen);
	    changeColor.add(zeroBlue);
	    changeColor.add(keepOnlyRed);
	    changeColor.add(keepOnlyGreen);
	    changeColor.add(keepOnlyBlue);
	    changeColor.add(grayscale);
	    
	    changePictureMenu.add(changeColor);
	    changePictureMenu.add(mirrorImage);
	    changePictureMenu.addSeparator();
	    changePictureMenu.add(negate);
	    changePictureMenu.add(fixUnderwater);
	    changePictureMenu.add(detectEdge);
	    changePictureMenu.add(createCollage);
	    changePictureMenu.addSeparator();
	    changePictureMenu.add(textEditor);
	    changePictureMenu.add(drawingPad);
	    changePictureMenu.addSeparator();
	    changePictureMenu.add(revertToOriginal);
	    
	    menuBar.add(zoomMenu);
	    menuBar.add(changePictureMenu);
    
    // set the menu bar to this menu
	    pictureFrame.setJMenuBar(menuBar);
		}
  /**
   * Create and initialize the scrolling image
   */
	  private void createAndInitScrollingImage()
		{
	    scrollPane = new JScrollPane();
	    
	    BufferedImage bimg = picture.getBufferedImage();
	    imageDisplay = new ImageDisplay(bimg);
	    imageDisplay.addMouseMotionListener(this);
	    imageDisplay.addMouseListener(this);
	    imageDisplay.setToolTipText("Click a mouse button on a pixel to see the pixel information");
	    scrollPane.setViewportView(imageDisplay);
	    pictureFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		}
  /**
   * Creates the JFrame and sets everything up
   */
	  private void createWindow()
		  {
    // create the picture frame and initialize it
		  createAndInitPictureFrame();
    
    // set up the menu bar
		  setUpMenuBar();
    
    //create the information panel
		  createInfoPanel();
    
    //creates the scrollpane for the picture
		  createAndInitScrollingImage();
    
    // show the picture in the frame at the size it needs to be
		  pictureFrame.pack();
		  pictureFrame.setVisible(true);
		  }
  /**
   * Method to set up the next and previous buttons for the
   * pixel location information
   */
	  private void setUpNextAndPreviousButtons()
		  {
    // create the image icons for the buttons
		  Icon prevIcon = new ImageIcon(DigitalPicture.class.getResource("leftArrow.gif"), 
                                  "previous index");
		  Icon nextIcon = new ImageIcon(DigitalPicture.class.getResource("rightArrow.gif"), 
                                  "next index");
    // create the arrow buttons
	    colPrevButton = new JButton(prevIcon);
	    colNextButton = new JButton(nextIcon);
	    rowPrevButton = new JButton(prevIcon);
	    rowNextButton = new JButton(nextIcon);
    
    // set the tool tip text
	    colNextButton.setToolTipText("Click to go to the next column value");
	    colPrevButton.setToolTipText("Click to go to the previous column value");
	    rowNextButton.setToolTipText("Click to go to the next row value");
	    rowPrevButton.setToolTipText("Click to go to the previous row value");
    
    // set the sizes of the buttons
	    int prevWidth = prevIcon.getIconWidth() + 2;
	    int nextWidth = nextIcon.getIconWidth() + 2;
	    int prevHeight = prevIcon.getIconHeight() + 2;
	    int nextHeight = nextIcon.getIconHeight() + 2;
	    Dimension prevDimension = new Dimension(prevWidth,prevHeight);
	    Dimension nextDimension = new Dimension(nextWidth, nextHeight);
	    colPrevButton.setPreferredSize(prevDimension);
	    rowPrevButton.setPreferredSize(prevDimension);
	    colNextButton.setPreferredSize(nextDimension);
	    rowNextButton.setPreferredSize(nextDimension);
    
    // handle previous column button press
	    colPrevButton.addActionListener(new ActionListener() 
	    	{
	    	public void actionPerformed(ActionEvent evt)  
	    		{
		        colIndex--;
		        if (colIndex < 0)
		          colIndex = 0;
		        displayPixelInformation(colIndex,rowIndex);
	    		}
	    	});
    
    // handle previous row button press
	    rowPrevButton.addActionListener(new ActionListener() 
	    	{
	    	public void actionPerformed(ActionEvent evt) 
	    		{
		        rowIndex--;
		        if (rowIndex < 0)
		          rowIndex = 0;
		        displayPixelInformation(colIndex,rowIndex);
	    		}
	    	});
    
    // handle next column button press
	    colNextButton.addActionListener(new ActionListener() 
	    	{
	    	public void actionPerformed(ActionEvent evt) 
	    		{
		        colIndex++;
		        if (colIndex >= picture.getWidth())
		          colIndex = picture.getWidth() - 1;
		        displayPixelInformation(colIndex,rowIndex);
	    		}
	    	});
    // handle next row button press
	    rowNextButton.addActionListener(new ActionListener() 
	    	{
	    	public void actionPerformed(ActionEvent evt) 
	    		{
		        rowIndex++;
		        if (rowIndex >= picture.getHeight())
		          rowIndex = picture.getHeight() - 1;
		        displayPixelInformation(colIndex,rowIndex);
	    		}
	    	});
		  } 
  /**
   * Create the pixel location panel
   * @param labelFont the font for the labels
   * @return the location panel
   */

	  public JPanel createLocationPanel(Font labelFont) 
		  {
    // create a location panel
		    JPanel locationPanel = new JPanel();
		    locationPanel.setLayout(new FlowLayout());
		    Box hBox = Box.createHorizontalBox();
    
    // create the labels
		    rowLabel = new JLabel("Row:");
		    colLabel = new JLabel("Column:");
    
    // create the text fields
		    colValue = new JTextField(Integer.toString(colIndex + numberBase),6);
		    colValue.addActionListener(new ActionListener() 
		    	{
		    	public void actionPerformed(ActionEvent e) 
		    		{
		    		displayPixelInformation(colValue.getText(),rowValue.getText());
		    		}
		    	});
		    rowValue = new JTextField(Integer.toString(rowIndex + numberBase),6);
    
		    rowValue.addActionListener(new ActionListener() 
		    	{
		    	public void actionPerformed(ActionEvent e) 
		    		{
		    		displayPixelInformation(colValue.getText(),rowValue.getText());
		    		}
		    	});
    // set up the next and previous buttons
		    setUpNextAndPreviousButtons();
    
    // set up the font for the labels
		    colLabel.setFont(labelFont);
		    rowLabel.setFont(labelFont);
		    colValue.setFont(labelFont);
		    rowValue.setFont(labelFont);
    
    // add the items to the vertical box and the box to the panel
		    hBox.add(Box.createHorizontalGlue());
		    hBox.add(rowLabel);
		    hBox.add(rowPrevButton);
		    hBox.add(rowValue);
		    hBox.add(rowNextButton);
		    hBox.add(Box.createHorizontalStrut(10));
		    hBox.add(colLabel);
		    hBox.add(colPrevButton);
		    hBox.add(colValue);
		    hBox.add(colNextButton);
		    locationPanel.add(hBox);
		    hBox.add(Box.createHorizontalGlue());
		    
		    return locationPanel;
		  }
  
  /**
   * Create the color information panel
   * @param labelFont the font to use for labels
   * @return the color information panel
   */
	  private JPanel createColorInfoPanel(Font labelFont)
		  {
    // create a color info panel
		    JPanel colorInfoPanel = new JPanel();
		    colorInfoPanel.setLayout(new FlowLayout());
    
    // get the pixel at the x and y
		    Pixel pixel = new Pixel(picture,colIndex,rowIndex);
    
    // create the labels
		    rValue = new JLabel("R: " + pixel.getRed());
		    gValue = new JLabel("G: " + pixel.getGreen());
		    bValue = new JLabel("B: " + pixel.getBlue());
		    
    // create the sample color panel and label
		    colorLabel = new JLabel("Color at location: ");
		    colorPanel = new JPanel();
		    colorPanel.setBorder(new LineBorder(Color.black,1));
    
    // set the color sample to the pixel color
		    colorPanel.setBackground(pixel.getColor());
    
    // set the font
		    rValue.setFont(labelFont);
		    gValue.setFont(labelFont);
		    bValue.setFont(labelFont);
		    colorLabel.setFont(labelFont);
		    colorPanel.setPreferredSize(new Dimension(25,25));
    
    // add items to the color information panel
		    colorInfoPanel.add(rValue);
		    colorInfoPanel.add(gValue);
		    colorInfoPanel.add(bValue);
		    colorInfoPanel.add(colorLabel);
		    colorInfoPanel.add(colorPanel);
		    
		    return colorInfoPanel; 
		  }
  
  /**
   * Creates the North JPanel with all the pixel location
   * and color information
   */
	  private void createInfoPanel()
		  {
    // create the info panel and set the layout
		    JPanel infoPanel = new JPanel();
		    infoPanel.setLayout(new BorderLayout());
    
    // create the font
		    Font largerFont = new Font(infoPanel.getFont().getName(),
		                               infoPanel.getFont().getStyle(),14);
    
    // create the pixel location panel
		    JPanel locationPanel = createLocationPanel(largerFont);
    
    // create the color information panel
		    JPanel colorInfoPanel = createColorInfoPanel(largerFont);
    
    // add the panels to the info panel
		    infoPanel.add(BorderLayout.NORTH,locationPanel);
		    infoPanel.add(BorderLayout.SOUTH,colorInfoPanel); 
    
    // add the info panel
		    pictureFrame.getContentPane().add(BorderLayout.NORTH,infoPanel);
		  } 
  /**
   * Method to check that the current position is in the viewing area and if
   * not scroll to center the current position if possible
   */
	  public void checkScroll()
		  {
    // get the x and y position in pixels
		    int xPos = (int) (colIndex * zoomFactor); 
		    int yPos = (int) (rowIndex * zoomFactor); 
    
    // only do this if the image is larger than normal
		    if (zoomFactor > 1) 
		    	{
      
      // get the rectangle that defines the current view
			      JViewport viewport = scrollPane.getViewport();
			      Rectangle rect = viewport.getViewRect();
			      int rectMinX = (int) rect.getX();
			      int rectWidth = (int) rect.getWidth();
			      int rectMaxX = rectMinX + rectWidth - 1;
			      int rectMinY = (int) rect.getY();
			      int rectHeight = (int) rect.getHeight();
			      int rectMaxY = rectMinY + rectHeight - 1;
      
      // get the maximum possible x and y index
			      int macolIndexX = (int) (picture.getWidth() * zoomFactor) - rectWidth - 1;
			      int macolIndexY = (int) (picture.getHeight() * zoomFactor) - rectHeight - 1;
      
      // calculate how to position the current position in the middle of the viewing
      // area
			      int viewX = xPos - (int) (rectWidth / 2);
			      int viewY = yPos - (int) (rectHeight / 2);
      
      // reposition the viewX and viewY if outside allowed values
			      if (viewX < 0)
			        viewX = 0;
			      else if (viewX > macolIndexX)
			        viewX = macolIndexX;
			      if (viewY < 0)
			        viewY = 0;
			      else if (viewY > macolIndexY)
			        viewY = macolIndexY;
      
      // move the viewport upper left point
			      viewport.scrollRectToVisible(new Rectangle(viewX,viewY,rectWidth,rectHeight));
		    	}
		  } 
  /**
   * Zooms in the on picture by scaling the image.  
   * It is extremely memory intensive.
   * @param factor the amount to zoom by
   */
	  public void zoom(double factor)
		  {
    // save the current zoom factor
		  zoomFactor = factor;
    
    // calculate the new width and height and get an image that size
		    int width = (int) (picture.getWidth()*zoomFactor);
		    int height = (int) (picture.getHeight()*zoomFactor);
		    BufferedImage bimg = picture.getBufferedImage();
    
    // set the scroll image icon to the new image
		    imageDisplay.setImage(bimg.getScaledInstance(width, height, Image.SCALE_DEFAULT));
		    imageDisplay.setCurrentX((int) (colIndex * zoomFactor));
		    imageDisplay.setCurrentY((int) (rowIndex * zoomFactor));
		    imageDisplay.revalidate();
		    checkScroll();  // check if need to reposition scroll
		  }
  /**
   * Repaints the image on the scrollpane.  
   */
  public void repaint()
	  {
	  pictureFrame.repaint();
	  } 
  //****************************************//
  //               Event Listeners          //
  //****************************************//
  
  /**
   * Called when the mouse is dragged (button held down and moved)
   * @param e the mouse event
   */
  public void mouseDragged(MouseEvent e)
	  {
	  displayPixelInformation(e);
	  }
  /**
   * Method to check if the given x and y are in the picture
   * @param column the horizontal value
   * @param row the vertical value
   * @return true if the row and column are in the picture 
   * and false otherwise
   */
  private boolean isLocationInPicture(int column, int row)
	  {
	  boolean result = false; // the default is false
	  if (column >= 0 && column < picture.getWidth() &&
        row >= 0 && row < picture.getHeight())
      result = true;
	  return result;
	  }
  
  /**
   * Method to display the pixel information from the passed x and y but
   * also converts x and y from strings
   * @param xString the x value as a string from the user
   * @param yString the y value as a string from the user
   */
  public void displayPixelInformation(String xString, String yString)
	  {
	    int x = -1;
	    int y = -1;
	    try {
	      x = Integer.parseInt(xString);
	      x = x - numberBase;
	      y = Integer.parseInt(yString);
	      y = y - numberBase;
	    } 
	    catch (Exception ex) 
	    	{
	    	}
    
	    if (x >= 0 && y >= 0) 
	    	{
	    	displayPixelInformation(x,y);
	    	}
	  }
  
  /**
   * Method to display pixel information for the passed x and y
   * @param pictureX the x value in the picture
   * @param pictureY the y value in the picture
   */
  private void displayPixelInformation(int pictureX, int pictureY)
	  {
    // check that this x and y are in range
	  if (isLocationInPicture(pictureX, pictureY))
		  {
      // save the current x and y index
	      colIndex = pictureX;
	      rowIndex = pictureY;
      
      // get the pixel at the x and y
	      Pixel pixel = new Pixel(picture,colIndex,rowIndex);
      
      // set the values based on the pixel
	      colValue.setText(Integer.toString(colIndex  + numberBase));
	      rowValue.setText(Integer.toString(rowIndex + numberBase));
	      rValue.setText("R: " + pixel.getRed());
	      gValue.setText("G: " + pixel.getGreen());
	      bValue.setText("B: " + pixel.getBlue());
	      colorPanel.setBackground(new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue())); 
		  } 
	  else
		  {
		  clearInformation();
		  }
    // notify the image display of the current x and y
	    imageDisplay.setCurrentX((int) (colIndex * zoomFactor));
	    imageDisplay.setCurrentY((int) (rowIndex * zoomFactor));
	  }
  /**
   * Method to display pixel information based on a mouse event
   * @param e a mouse event
   */
  private void displayPixelInformation(MouseEvent e)
	  {
    
    // get the cursor x and y
	    int cursorX = e.getX();
	    int cursorY = e.getY();
    
    // get the x and y in the original (not scaled image)
	    int pictureX = (int) (cursorX / zoomFactor + numberBase);
	    int pictureY = (int) (cursorY / zoomFactor + numberBase);
    
    // display the information for this x and y
	    displayPixelInformation(pictureX,pictureY); 
	  }
  /**
   * Method to clear the labels and current color and reset the 
   * current index to -1
   */
  private void clearInformation()
	  {
	    colValue.setText("N/A");
	    rowValue.setText("N/A");
	    rValue.setText("R: N/A");
	    gValue.setText("G: N/A");
	    bValue.setText("B: N/A");
	    colorPanel.setBackground(Color.black);
	    colIndex = -1;
	    rowIndex = -1;
	  }
  
  /**
   * Method called when the mouse is moved with no buttons down
   * @param e the mouse event
   */
  public void mouseMoved(MouseEvent e)
	  {}
  /**
   * Method called when the mouse is clicked
   * @param e the mouse event
   */
  public void mouseClicked(MouseEvent e)
	  {
	  displayPixelInformation(e);
	  }
  /**
   * Method called when the mouse button is pushed down
   * @param e the mouse event
   */ 
  public void mousePressed(MouseEvent e)
	  {
	  displayPixelInformation(e);
	  }
  /**
   * Method called when the mouse button is released
   * @param e the mouse event
   */
  public void mouseReleased(MouseEvent e)
	  {
	  }
  /**
   * Method called when the component is entered (mouse moves over it)
   * @param e the mouse event
   */
  public void mouseEntered(MouseEvent e)
	  {
	  } 
  /**
   * Method called when the mouse moves over the component
   * @param e the mouse event
   */
  public void mouseExited(MouseEvent e)
	  {
	  }
  /**
   * Method to enable all menu commands
   */
  private void enableZoomItems()
	  {
	    twentyFive.setEnabled(true);
	    fifty.setEnabled(true);
	    seventyFive.setEnabled(true);
	    hundred.setEnabled(true);
	    hundredFifty.setEnabled(true);
	    twoHundred.setEnabled(true);
	    fiveHundred.setEnabled(true);
	    
	    keepOnlyBlue.setEnabled(true);
	  }
  /**
   * Controls the zoom menu bar
   *
   * @param a the ActionEvent 
   */
  public void actionPerformed(ActionEvent a)
	  { 
		  //Zoom Features
	  if(a.getActionCommand().equals("Update"))
		  {
		  this.repaint();
		  }
	  if(a.getActionCommand().equals("25%"))
		  {
	      this.zoom(.25);
	      enableZoomItems();
	      twentyFive.setEnabled(false);
		  }
	  if(a.getActionCommand().equals("50%"))
		  {
	      this.zoom(.50);
	      enableZoomItems();
	      fifty.setEnabled(false);
		  }
	  if(a.getActionCommand().equals("75%"))
		  {
	      this.zoom(.75);
	      enableZoomItems();
	      seventyFive.setEnabled(false);
		  }
	  if(a.getActionCommand().equals("100%"))
		  {
	      this.zoom(1.0);
	      enableZoomItems();
	      hundred.setEnabled(false);
		  }
	  if(a.getActionCommand().equals("150%"))
		  {
	      this.zoom(1.5);
	      enableZoomItems();
	      hundredFifty.setEnabled(false);
		  } 
	  if(a.getActionCommand().equals("200%"))
		  {
	      this.zoom(2.0);
	      enableZoomItems();
	      twoHundred.setEnabled(false);
		  }
	  if(a.getActionCommand().equals("500%"))
		  {
	      this.zoom(5.0);
	      enableZoomItems();
	      fiveHundred.setEnabled(false);
		  }
	  
	  		//Extra Features
	  if (a.getActionCommand().equals("Zero Red"))
		  {
			  ((SimplePicture)picture).zeroRed();
		  }
	  if (a.getActionCommand().equals("Zero Green"))
		  {
			  ((SimplePicture)picture).zeroGreen();
		  }
	  if (a.getActionCommand().equals("Zero Blue"))
		  {
			  ((SimplePicture)picture).zeroBlue();
		  }
	  if (a.getActionCommand().equals("Keep Only Red"))
		  {
			  ((SimplePicture)picture).keepOnlyRed();
		  }
	  if (a.getActionCommand().equals("Keep Only Green"))
		  {
			  ((SimplePicture)picture).keepOnlyGreen();
		  }
	  if (a.getActionCommand().equals("Keep Only Blue"))
		  {
			  ((SimplePicture)picture).keepOnlyBlue();
		  }
	  if (a.getActionCommand().equals("Mirror Diagonally"))
		  {
			  String[] choices = {"Top Right onto the Bottom Left", "Bottom Left onto the Top Right", "Bottom Right onto the Top Left", "Top Left onto Bottom Right"};
			  JFrame frame = new JFrame();
			  String choice = (String) JOptionPane.showInputDialog(frame, "Which side would you like to mirror?", "Choosing Side To Mirror", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
			  if (choice.equals("Top Right onto the Bottom Left"))
				  {
					  ((SimplePicture)picture).mirrorDiagonal();
				  }
			  else if (choice.equals("Bottom Left onto the Top Right"))
				  {
					  ((SimplePicture)picture).mirrorDiagonalBottomLeftToTopRight();
				  }
			  else if (choice.equals("Bottom Right onto the Top Left"))
				  {
					  ((SimplePicture)picture).mirrorDiagonalBottomRightToTopLeft();
				  }
			  else
				  {
					  ((SimplePicture)picture).mirrorDiagonalTopLeftToBottomRight();
				  }
		  }
	  if (a.getActionCommand().equals("Mirror Horizontally"))
		  {
			  String[] choices = {"Top onto Bottom", "Bottom onto Top"};
			  JFrame frame = new JFrame();
			  String choice = (String) JOptionPane.showInputDialog(frame, "Which side would you like to mirror?", "Choosing Side To Mirror", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
			  if (choice.equals("Top onto Bottom"))
				  {
					  ((SimplePicture)picture).mirrorHorizontal();
				  }
			  else
				  {
					  ((SimplePicture)picture).mirrorHorizontalBottomToTop();
				  }
		  }
	  if (a.getActionCommand().equals("Mirror Vertically"))
		  {
			  String[] choices = {"Left onto Right", "Right onto Left"};
			  JFrame frame = new JFrame();
			  String choice = (String) JOptionPane.showInputDialog(frame, "Which side would you like to mirror?", "Choosing Side To Mirror", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
			  if (choice.equals("Left onto Right"))
				  {
					  ((SimplePicture)picture).mirrorVertical();
				  }
			  else
				  {
					  ((SimplePicture)picture).mirrorVerticalRightToLeft();
				  }
		  }
	  if (a.getActionCommand().equals("Grayscale"))
		  {
			  ((SimplePicture)picture).grayscale();
		  }
	  if (a.getActionCommand().equals("Negate"))
		  {
			  ((SimplePicture)picture).negate();
		  }
	  if (a.getActionCommand().equals("Fix Underwater"))
		  {
			  ((SimplePicture)picture).fixUnderwater();
		  }
	  if (a.getActionCommand().equals("Detect Edge"))
		  {
			  ((SimplePicture)picture).edgeDetection(15);
		  }
	  if (a.getActionCommand().equals("Create Collage"))
		  {
			  ((SimplePicture)picture).createCollage();
		  }
	  if (a.getActionCommand().equals("Add Text"))
		  {
			  String message = JOptionPane.showInputDialog("What Would You Like To Say?", "Input Text Here");
			  ((SimplePicture)picture).addMessage(message, 100, 100);
		  }
	  if (a.getActionCommand().equals("Drawing Pad"))
		  {
			  try
				{
					BlakeMethods.runner();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
		  }
	  }
  
  
  
  /**
   * Class for establishing the focus for the textfields
   */
private class PictureExplorerFocusTraversalPolicy
    extends FocusTraversalPolicy 
    {
    /**
     * Method to get the next component for focus
     */
    public Component getComponentAfter(Container focusCycleRoot,
                                       Component aComponent) 
    	{
    	if (aComponent.equals(colValue))
    		return rowValue;
    	else 
    		return colValue;
    	}
    /**
     * Method to get the previous component for focus
     */
    public Component getComponentBefore(Container focusCycleRoot,
                                        Component aComponent) 
    	{
    	if (aComponent.equals(colValue))
    		return rowValue;
    	else 
    		return colValue;
    	}
    
    public Component getDefaultComponent(Container focusCycleRoot) 
    	{
    	return colValue;
    	}
    
    public Component getLastComponent(Container focusCycleRoot) 
    	{
    	return rowValue;
    	}
    
    public Component getFirstComponent(Container focusCycleRoot) 
    	{
    	return colValue;
    	}
    }
  
  /**
   * Test Main.  It will explore the beach 
   */
	public static void main( String args[])
		{
	    Picture pix = new Picture("beach.jpg");
	    pix.explore();
		} 
	  }
