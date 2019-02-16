package core.Model;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.*;
import java.io.IOException;

public class Snapshot {

    private PDDocument parentDoc; // TODO: Could only be index
    private Image thumbnail;
    private int index;
    private boolean selected = true;

    public Snapshot(PDDocument parentDoc, int index) throws IOException {

        this.parentDoc = parentDoc;
        this.index = index;

        // Create thumbnails
        PDFRenderer renderer = new PDFRenderer(parentDoc);
        thumbnail = renderer.renderImage(index);
    }

    public Image getThumbnail() {
        return this.thumbnail;
    }

    public PDDocument getDoc() {
        return this.parentDoc;
    }

    public int getIndex() {
        return index;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean isSelected) {
        this.selected = isSelected;
    }
}
