package Model;

import org.apache.pdfbox.pdmodel.PDDocument;

import javax.swing.*;
import java.io.*;
import java.util.LinkedList;

public class DocumentHandler {

    // TODO:Re-write this entire shit but with DefaultListModel<Image> because I can't single out pages
    LinkedList<PDDocument> docs;
    DefaultListModel<Snapshot> model;

    public DocumentHandler() {
        model = new DefaultListModel<>();
        docs = new LinkedList<>();
    }

    // close all documents
    public void clean() {

        try {
            for (int o = 0; o < docs.size(); o++) {

                // Fetch doc
                PDDocument doc = docs.get(o);
                doc.close();

                // Remove pages from model
                for (int i = 0; i < model.getSize(); i++) {
                    if (model.getElementAt(i).getIndex() == o) {
                        model.remove(i);
                    }
                }

                // Finally close document
                docs.remove(doc);
            }
        } catch (IOException io) {
            System.err.println("Couldn't close PDDocument :" + io.getMessage());
        }
    }

    public PDDocument merge() throws IOException {

        PDDocument mergedDoc = new PDDocument();


        for (int i = 0; i < model.getSize(); i++) {
            Snapshot tempSnap = model.getElementAt(i);

            if (tempSnap.isSelected()) {
                mergedDoc.importPage(tempSnap.getDoc().getPage(tempSnap.getIndex()));
            }
        }

        return mergedDoc;
    }

    public void load(String path) throws IOException {

        // Load PDDocument into memory
        PDDocument temp = PDDocument.load(new File(path));
        docs.add(temp);

        // Make Image of every PDPage
        for (int i = 0; i < temp.getNumberOfPages(); i++) {

            // Add new Snapshot
            model.addElement(new Snapshot(temp, i));
        }
    }

    public DefaultListModel<Snapshot> getModel() {
        return model;
    }
}
