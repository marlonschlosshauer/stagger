package core.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainWindow extends JFrame {

    private JScrollPane pages;
    private JList thumbnailList;
    private JButton loadButton;
    private JButton saveButton;
    private JPanel buttonPanel;

    public MainWindow(ListModel model) {
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
        //this.setBackground(Color.YELLOW);

        // Thumbnail
        thumbnailList = new JList(model);
        thumbnailList.setCellRenderer(new PageList());
        thumbnailList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        thumbnailList.setVisibleRowCount(PageList.width/this.getWidth());
        //thumbnailList.setBackground(Color.BLUE);

        thumbnailList.setFixedCellWidth(PageList.width);

        // Panel for Thumbnail
        pages = new JScrollPane(this.thumbnailList);
        //pages.setBackground(Color.red);

        this.add(pages);

        // Buttons
        loadButton = new JButton("Load");
        saveButton = new JButton("Merge");

        loadButton.setBounds(240, 515, 50, 25);
        saveButton.setBounds(270, 515, 50, 25);

        // Panel for Buttons
        buttonPanel = new JPanel();
        buttonPanel.setMaximumSize(new Dimension(this.getWidth(),this.getHeight()/10));
        buttonPanel.setSize(600, 100);
        //buttonPanel.setBackground(Color.YELLOW);

        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);

        // Change JList row count according to size
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                // Recalculate rows
                thumbnailList.setVisibleRowCount(PageList.width/getWidth());
            }
        });

        this.add(buttonPanel);
    }

    public void setActionListener(ActionListener load, ActionListener save) {
        this.loadButton.addActionListener(load);
        this.saveButton.addActionListener(save);
    }
}
