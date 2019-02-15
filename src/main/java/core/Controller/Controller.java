package core.Controller;

import core.View.MainWindow;
import core.Model.DocumentHandler;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        main = new MainWindow(handler);

        // Add action listener
        // TODO: Add Dialog Window to determine path
        main.setActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    handler.load(System.getProperty("user.home") + "/temp/" + "kap.pdf");
                } catch (IOException ex) {
                    ex.getMessage();
                }

            }
        }, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    PDDocument temp = handler.merge();
                    System.out.println("Pages now loaded : " + temp.getNumberOfPages());
                    temp.save(System.getProperty("user.home") + "/Downloads/" + "testing.pdf");
                    //temp.close();

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        this.getMain().setVisible(true);
    }

    public MainWindow getMain() {
        return main;
    }
}
