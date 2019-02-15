package core.Model;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class DocumentHandler extends DefaultListModel<Snapshot> {


    private ArrayList<PDDocument> docs;

    public boolean add(PDDocument doc) throws IOException {

        for (PDPage page :
                doc.getPages()) {
            this.add(page);
        }

        return true;
    }

    // Wrapper for addElement of DefaultListModel
    public boolean add(PDPage page) throws IOException {
        this.addElement(new Snapshot(page));
        System.out.println("Pages currently : " + this.getSize());
        return true;
    }

    public PDDocument merge() throws IOException {

        PDDocument doc = new PDDocument();
        System.out.println("Pages currently : " + this.getSize());


        for (int i = 0; i < this.size(); i++) {
            PDPage temp = this.getElementAt(i).getPage();
            System.out.println(temp.getContents().toString());

            if(temp == null) {
                System.out.println("what the fuck are you doing java");
            }
            //doc.addPage(temp);
            doc.importPage(temp);

            System.out.println("Merged a page.");
        }

        System.out.println("Pages now loaded : " + doc.getNumberOfPages());

        System.out.println("Finished merging.");

        return doc;
    }

    public void load(String path) throws IOException {

        PDDocument temp;

        try {
            temp = PDDocument.load(new File(path));
            for (PDPage page : temp.getPages()) {
                this.add(page);
                System.out.println("Added a page.");
            }
            System.out.println("Finished adding pages.");

            temp.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
