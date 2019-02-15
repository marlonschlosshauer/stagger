package core.Model;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.*;
import java.io.IOException;

public class Snapshot {

    private PDDocument doc;
    private Image[] thumbnail;
    private boolean[] selected;

    public Snapshot(PDDocument doc) throws IOException {

        this.doc = doc;
        thumbnail = new Image[doc.getNumberOfPages()];
        selected = new boolean[doc.getNumberOfPages()];

        setSelected(0,doc.getNumberOfPages()-1, true);

        // Create thumbnails
        PDFRenderer renderer = new PDFRenderer(doc);

        for (int i = 0; i < doc.getNumberOfPages(); i++) {
            this.thumbnail[i] = renderer.renderImage(0);
        }
    }

    public Image[] getThumbnails() {
        return this.thumbnail;
    }

    public PDDocument getDoc() {
        return this.doc;
    }

    public boolean isSelected(int index) {
        return this.selected[index];
    }

    public void setSelected(int index, boolean selected) {
        this.selected[index] = selected;
    }

    public void setSelected(int start, int end, boolean selected) {
        for(int i = start ; i <= end ; i++) {
            setSelected(i,selected);
        }
    }
}
