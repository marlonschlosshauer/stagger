package View;

import Model.Snapshot;

import javax.swing.*;
import java.awt.*;

public class PageList extends JLabel implements ListCellRenderer {

    static int cellWidth = 200;

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean disabled, boolean cellHasFocus) {
        Snapshot entry = (Snapshot) value;

        // Update selection
        entry.setDisabled(disabled);

        // Update height while keeping ratio
        double height = (double) entry.getThumbnail().getWidth(null) / (double) entry.getThumbnail().getHeight(null);
        height = PageList.cellWidth / height;

        // Scale image
        this.setIcon(new ImageIcon(entry.getThumbnail().getScaledInstance(PageList.cellWidth, (int) height, Image.SCALE_SMOOTH)));

        // Add border if selected
        if (!disabled) {
            this.setBorder(BorderFactory.createLineBorder(Color.black));
        } else {
            this.setBorder(BorderFactory.createEmptyBorder());
        }
        return this;
    }
}
