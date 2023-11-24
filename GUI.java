import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

public class GUI implements ActionListener {

    JFrame window;
    // word counter
    JLabel wordCountLabel;

    // Text Area
    JTextArea textArea;
    JScrollPane scrollPane;

    // Top Menu Bar
    JMenuBar menuBar;
    JMenu menuFile, menuEdit, menuFormat, menuColor, menuAbout;
    // File Menu
    JMenuItem iNew, iOpen, iSave, iSaveAs, iExit;
    // Edit Menu
    JMenuItem iUndo, iRedo;
    // Format Menu
    JMenuItem iFontArial, iFontCSMS, iFontTNR, iFontImpact, iFontLCW, iFontSize8, iFontSize10, iFontSize12, iFontSize16,
            iFontSize18, iFontSize20, iFontSize24, iFontSize28, iFontSize30;
    JMenu menuFont, menuFontSize;

    // Color Menu
    JMenuItem iColor1, iColor2, iColor3, iColor4, iColor5;

    Function_File file = new Function_File(this);
    Function_Format format = new Function_Format(this);
    Function_Color color = new Function_Color(this);
    Function_Edit edit = new Function_Edit(this);

    KeyHandler kHandler = new KeyHandler(this);

    UndoManager um = new UndoManager();

    // Logo
    // ImageIcon logo = new ImageIcon("..//res//noteIcon.png");
    ImageIcon logo = new ImageIcon(GUI.class.getResource("noteIcon.png"));

    public static void main(String[] args) {
        new GUI();
    }

    public GUI() {
        createWindow();
        createTextArea();
        createMenuBar();
        createFileMenu();
        createEditMenu();
        createFormatMenu();
        createColorMenu();
        createAboutMenu();

        format.selectedfont = "Arial";
        format.createFont(16);
        color.changeColor("White");
        window.setVisible(true);

        wordCountLabel = new JLabel("Word Count: 0");
        window.add(wordCountLabel, BorderLayout.SOUTH); // Add to the bottom

        textArea.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                updateWordCountLabel();
            }

            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                updateWordCountLabel();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                updateWordCountLabel();
            }
        });

    }

    public int countWords() {
        String text = textArea.getText().trim(); // trim to handle leading and trailing spaces
        if (text.isEmpty()) {
            return 0;
        } else {
            String[] words = text.split("\\s+");
            return words.length;
        }
    }

    public void updateWordCountLabel() {
        int wordCount = countWords();
        wordCountLabel.setText("   Word Count: " + wordCount);
    }

    public void createWindow() {
        window = new JFrame("Notepad");
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setIconImage(logo.getImage());
    }

    public void createTextArea() {
        textArea = new JTextArea();
        textArea.setFont(format.arial);

        textArea.addKeyListener(kHandler);

        textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e) {
                um.addEdit(e.getEdit());
                updateWordCountLabel();
            }
        });

        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        window.add(scrollPane);
    }

    public void createMenuBar() {
        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

        menuFile = new JMenu("File");
        menuBar.add(menuFile);

        menuEdit = new JMenu("Edit");
        menuBar.add(menuEdit);

        menuFormat = new JMenu("Format");
        menuBar.add(menuFormat);

        menuColor = new JMenu("Color");
        menuBar.add(menuColor);

        menuAbout = new JMenu("About");
        menuBar.add(menuAbout);
    }

    public void createFileMenu() {
        iNew = new JMenuItem("New");
        iNew.addActionListener((java.awt.event.ActionListener) this);
        iNew.setActionCommand("New");
        menuFile.add(iNew);

        iOpen = new JMenuItem("Open");
        iOpen.addActionListener((java.awt.event.ActionListener) this);
        iOpen.setActionCommand("Open");
        menuFile.add(iOpen);

        iSave = new JMenuItem("Save");
        iSave.addActionListener((java.awt.event.ActionListener) this);
        iSave.setActionCommand("Save");
        menuFile.add(iSave);

        iSaveAs = new JMenuItem("Save As");
        iSaveAs.addActionListener((java.awt.event.ActionListener) this);
        iSaveAs.setActionCommand("SaveAs");
        menuFile.add(iSaveAs);

        iExit = new JMenuItem("Exit");
        iExit.addActionListener((java.awt.event.ActionListener) this);
        iExit.setActionCommand("Exit");
        menuFile.add(iExit);
    }

    public void createEditMenu() {
        iUndo = new JMenuItem("Undo");
        iUndo.addActionListener(this);
        iUndo.setActionCommand("Undo");
        menuEdit.add(iUndo);

        iRedo = new JMenuItem("Redo");
        iRedo.addActionListener(this);
        iRedo.setActionCommand("Redo");
        menuEdit.add(iRedo);
    }

    public void createFormatMenu() {

        menuFont = new JMenu("Font");
        menuFormat.add(menuFont);

        iFontArial = new JMenuItem("Arial");
        iFontArial.addActionListener(this);
        iFontArial.setActionCommand("Arial");
        menuFont.add(iFontArial);

        iFontCSMS = new JMenuItem("Comic Sans MS");
        iFontCSMS.addActionListener(this);
        iFontCSMS.setActionCommand("Comic Sans MS");
        menuFont.add(iFontCSMS);

        iFontTNR = new JMenuItem("Times New Roman");
        iFontTNR.addActionListener(this);
        iFontTNR.setActionCommand("Times New Roman");
        menuFont.add(iFontTNR);

        iFontImpact = new JMenuItem("Impact");
        iFontImpact.addActionListener(this);
        iFontImpact.setActionCommand("Impact");
        menuFont.add(iFontImpact);

        iFontLCW = new JMenuItem("Lucida Handwriting");
        iFontLCW.addActionListener(this);
        iFontLCW.setActionCommand("Lucida Handwriting");
        menuFont.add(iFontLCW);

        menuFontSize = new JMenu("Font Size");
        menuFormat.add(menuFontSize);

        iFontSize8 = new JMenuItem("8");
        iFontSize8.addActionListener(this);
        iFontSize8.setActionCommand("Size8");
        menuFontSize.add(iFontSize8);

        iFontSize10 = new JMenuItem("10");
        iFontSize10.addActionListener(this);
        iFontSize10.setActionCommand("Size10");
        menuFontSize.add(iFontSize10);

        iFontSize12 = new JMenuItem("12");
        iFontSize12.addActionListener(this);
        iFontSize12.setActionCommand("Size12");
        menuFontSize.add(iFontSize12);

        iFontSize16 = new JMenuItem("16");
        iFontSize16.addActionListener(this);
        iFontSize16.setActionCommand("Size16");
        menuFontSize.add(iFontSize16);

        iFontSize18 = new JMenuItem("18");
        iFontSize18.addActionListener(this);
        iFontSize18.setActionCommand("Size18");
        menuFontSize.add(iFontSize18);

        iFontSize20 = new JMenuItem("20");
        iFontSize20.addActionListener(this);
        iFontSize20.setActionCommand("Size20");
        menuFontSize.add(iFontSize20);

        iFontSize24 = new JMenuItem("24");
        iFontSize24.addActionListener(this);
        iFontSize24.setActionCommand("Size24");
        menuFontSize.add(iFontSize24);

        iFontSize28 = new JMenuItem("28");
        iFontSize28.addActionListener(this);
        iFontSize28.setActionCommand("Size28");
        menuFontSize.add(iFontSize28);

        iFontSize30 = new JMenuItem("30");
        iFontSize30.addActionListener(this);
        iFontSize30.setActionCommand("Size30");
        menuFontSize.add(iFontSize30);
    }

    public void createColorMenu() {
        iColor1 = new JMenuItem("White");
        iColor1.addActionListener(this);
        iColor1.setActionCommand("White");
        menuColor.add(iColor1);

        iColor2 = new JMenuItem("Black");
        iColor2.addActionListener(this);
        iColor2.setActionCommand("Black");
        menuColor.add(iColor2);

        iColor3 = new JMenuItem("Blue");
        iColor3.addActionListener(this);
        iColor3.setActionCommand("Blue");
        menuColor.add(iColor3);

        iColor4 = new JMenuItem("Red");
        iColor4.addActionListener(this);
        iColor4.setActionCommand("Red");
        menuColor.add(iColor4);

        iColor5 = new JMenuItem("Yellow");
        iColor5.addActionListener(this);
        iColor5.setActionCommand("Yellow");
        menuColor.add(iColor5);
       
    }
    public void createAboutMenu() {
        // Add a button for your name and publication date
        JMenuItem infoButton = new JMenuItem("About this notepad");
        infoButton.addActionListener(this);
        infoButton.setActionCommand("About");
        
        menuAbout.add(infoButton); // Add to the menu bar, not the color menu
    }
    private void displayAboutDialog() {
        JOptionPane.showMessageDialog(window,
        "Notepad Project version 1.0.0\nMain reference: https://www.geeksforgeeks.org/java-swing-create-a-simple-text-editor/ \n" +
                "Produced by: \n22000450 Ahmad Faqrullah \n22000454 Syazreeq Akmal \n22000766 Adam Shamel \n22001874 Muhammad Alif Najmi\n\n" +
                "Published on 5th November 2023",
        "About",
        JOptionPane.INFORMATION_MESSAGE);
}

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "New":
                file.newFile();
                updateWordCountLabel();
                break;
            case "Open":
                file.open();
                break;
            case "Save":
                file.save();
                break;
            case "SaveAs":
                file.saveAs();
                break;
            case "Exit":
                file.Exit();
                break;
            case "Undo":
                edit.undo();
                break;
            case "Redo":
                edit.redo();
                break;
            // font
            case "Arial":
                format.setFont(command);
                break;
            case "Comic Sans MS":
                format.setFont(command);
                break;
            case "Times New Roman":
                format.setFont(command);
                break;
            case "Impact":
                format.setFont(command);
                break;
            case "Lucida Handwriting":
                format.setFont(command);
                break;
            // fontSize
            case "Size8":
                format.createFont(8);
                break;
            case "Size10":
                format.createFont(10);
                break;
            case "Size12":
                format.createFont(12);
                break;
            case "Size16":
                format.createFont(16);
                break;
            case "Size18":
                format.createFont(18);
                break;
            case "Size20":
                format.createFont(20);
                break;
            case "Size24":
                format.createFont(24);
                break;
            case "Size28":
                format.createFont(28);
                break;
            case "Size30":
                format.createFont(30);
                break;
            // color
            case "White":
                color.changeColor(command);
                break;
            case "Black":
                color.changeColor(command);
                break;
            case "Blue":
                color.changeColor(command);
                break;
            case "Red":
                color.changeColor(command);
                break;
            case "Yellow":
                color.changeColor(command);
                break;
            case "About":
                displayAboutDialog();
                break;
        }
        
    }
}
