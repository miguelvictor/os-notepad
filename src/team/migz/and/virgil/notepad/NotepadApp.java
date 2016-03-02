package team.migz.and.virgil.notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class NotepadApp extends JFrame {

    private static final String WINDOW_TITLE = "Notepad";

    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 500;

    private static final String MENU_SAVE_AS = "Save As";
    private static final String MENU_NEW_FILE = "New";
    private static final String MENU_OPEN_FILE = "Open";
    private static final String MENU_SAVE_FILE = "Save";

    private JTextArea txtWorkspace;

    private File mCurrentlyOpenedFile;

    public NotepadApp() {
        setTitle(WINDOW_TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        initializeComponents();
    }

    private void initializeComponents() {
        txtWorkspace = createWorkspace();
        JScrollPane scrollableWorkspace = scrollbarAsNeeded(txtWorkspace);
        JPanel toolbar = createToolbar();

        // Add toolbar and textarea to main window
        Container contentPane = getContentPane();
        contentPane.add(toolbar, BorderLayout.NORTH);
        contentPane.add(scrollableWorkspace, BorderLayout.CENTER);
    }

    private JTextArea createWorkspace() {
        JTextArea txtWorkspace = new JTextArea();
        txtWorkspace.setWrapStyleWord(false);
        txtWorkspace.setMargin(new Insets(10, 10, 10, 10));
        return txtWorkspace;
    }

    private JScrollPane scrollbarAsNeeded(JComponent component) {
        JScrollPane scrollPane = new JScrollPane(component);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        return scrollPane;
    }

    private JPanel createToolbar() {
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addToolbarItems(toolbar);

        return toolbar;
    }

    private void addToolbarItems(JPanel toolbar) {
        JButton btnNewFile = new JButton(MENU_NEW_FILE);
        JButton btnOpenFile = new JButton(MENU_OPEN_FILE);
        JButton btnSaveFile = new JButton(MENU_SAVE_FILE);
        JButton btnSaveAsFile = new JButton(MENU_SAVE_AS);

        btnNewFile.addActionListener(e -> newFile());
        btnOpenFile.addActionListener(e -> openFile());
        btnSaveFile.addActionListener(e -> saveFile());
        btnSaveAsFile.addActionListener(e -> saveAsFile());

        toolbar.add(btnNewFile);
        toolbar.add(btnOpenFile);
        toolbar.add(btnSaveFile);
        toolbar.add(btnSaveAsFile);

        stylizeToolbarComponents(toolbar, btnNewFile, btnOpenFile, btnSaveFile, btnSaveAsFile);
    }

    private void newFile() {
        System.out.println("Click Trigger: New File");

        if (mCurrentlyOpenedFile == null) {
            if (!txtWorkspace.getText().isEmpty()) {
                int choice = JOptionPane.showConfirmDialog(this, "You have unsaved changes, are you sure you want to create a new file?", "Prompt", JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    txtWorkspace.setText("");
                }
            }
        } else {
            try {
                if (Utils.readAsString(mCurrentlyOpenedFile).equals(txtWorkspace.getText())) {
                    mCurrentlyOpenedFile = null;
                    txtWorkspace.setText("");
                } else {
                    int choice = JOptionPane.showConfirmDialog(this, "You have unsaved changes, are you sure you want to close this file and create a new file?", "Prompt", JOptionPane.YES_NO_OPTION);

                    if (choice == JOptionPane.YES_OPTION) {
                        mCurrentlyOpenedFile = null;
                        txtWorkspace.setText("");
                    }
                }
            } catch (IOException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void openFile() {
        System.out.println("Click Trigger: Open File");
    }

    private void saveFile() {
        System.out.println("Click Trigger: Save");

        String desiredFileName = JOptionPane.showInputDialog(this, "Enter filename: ", "Input", JOptionPane.INFORMATION_MESSAGE);
        System.out.println("Desired Filename: " + desiredFileName);

        // TODO save the file
        if (mCurrentlyOpenedFile == null) {

        } else {

        }
    }

    private void saveAsFile() {
        System.out.println("Click Trigger: Save As");
    }

    private void stylizeToolbarComponents(JComponent... components) {
        Color background = new Color(0x3c5eb9);
        Color foreground = Color.WHITE;

        for (JComponent component : components) {
            component.setBackground(background);

            if (component instanceof JButton) {
                component.setForeground(foreground);
                component.setFocusable(false);
                ((JButton) component).setBorderPainted(false);
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new NotepadApp().setVisible(true));
    }

}
