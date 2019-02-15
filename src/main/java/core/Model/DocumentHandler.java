package core.Model;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;

import javax.swing.*;
import java.io.*;

public class DocumentHandler extends DefaultListModel<Snapshot> {

    // TODO:Re-write this entire shit but with DefaultListModel<Image> because I can't single out pages

    // close all documents
    public void clean() {
        try {

            for (int i = 0; i < this.getSize(); i++) {
                get(i).getDoc().close();
                this.removeElementAt(i);
            }

        } catch (IOException io) {
            System.err.println("Could not close document : " + io.getMessage());
        }
    }

    public PDDocument merge() throws IOException {

        PDDocument temp = new PDDocument();
        PDFMergerUtility merger = new PDFMergerUtility();

        for (int i = 0; i < this.size(); i++) {
            merger.appendDocument(temp,this.getElementAt(i).getDoc());
        }

        return temp;
    }

    public void load(String path) throws IOException {

        try {
            this.addElement(new Snapshot(PDDocument.load(new File(path))));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
