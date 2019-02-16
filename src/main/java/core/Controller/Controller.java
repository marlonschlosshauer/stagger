package core.Controller;

import core.View.MainWindow;
import core.Model.DocumentHandler;
import org.apache.pdfbox.pdmodel.PDDocument;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class Controller {

    private MainWindow main;
    private DocumentHandler handler;

    public static void main(String args[]) {

        Controller con = new Controller();
        con.init();
    }

    public Controller() {
        this.handler = new DocumentHandler();
    }

    public void init() {

        main = new MainWindow(handler.getModel());

        // Add action listener
        main.setActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    File[] selected = invokeLoadFileChooser();

                    if(selected == null) {
                        return;
                    }

                    for(File file : selected) {
                        handler.load(file.getAbsolutePath());
                    }

                } catch (IOException ex) {
                    ex.getMessage();
                }

            }
        }, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    PDDocument temp = handler.merge();
                    temp.save(System.getProperty("user.home") + "/Downloads/" + "testing.pdf");

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        main.setVisible(true);
    }

    public File[] invokeLoadFileChooser() {
        JFileChooser files = new JFileChooser();
        files.setMultiSelectionEnabled(true);

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                ".PDF", "pdf", "PDF");
        files.setFileFilter(filter);
        files.showOpenDialog(null);

        return files.getSelectedFiles();
    }


    public String invokeSaveFileChooser() {
        JFileChooser files = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                ".PDF", "pdf", "PDF");
        files.setFileFilter(filter);

        int selectedFile = files.showOpenDialog(null);

        if(selectedFile == JFileChooser.CANCEL_OPTION) {
            return "";
        }

        if(selectedFile == JFileChooser.APPROVE_OPTION) {
            System.out.println("Selected file : " + files.getSelectedFile().getName());
        }

        return files.getSelectedFile().getAbsolutePath();
    }
}
