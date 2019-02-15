package core.Model;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.*;
import java.io.IOException;

public class Snapshot {

    private PDPage page;
    private Image thumbnail;
    private boolean selected = true;

    public Snapshot(PDPage page) throws IOException{

        this.page = page;

        // Create thumbnail
        PDDocument temp= new PDDocument();
        temp.addPage(page);

        PDFRenderer renderer = new PDFRenderer(temp);
        this.thumbnail = renderer.renderImage(0);

        temp.close();

        if(thumbnail == null ) {
            throw new IOException("Couldn't create Thumbnail.");
        }
    }

    public Image getThumbnail() {
        return this.thumbnail;
    }

    public PDPage getPage() {
        return this.page;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
