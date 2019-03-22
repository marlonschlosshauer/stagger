package View;

import Model.Snapshot;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PageList extends JLabel implements ListCellRenderer {

    static int width = 200;
    static int height = PageList.width;

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Snapshot entry = (Snapshot) value;

        // Update selection
        entry.setSelected(isSelected);

        // Scale image
        this.setIcon(new ImageIcon(entry.getThumbnail().getScaledInstance(PageList.width, PageList.height, Image.SCALE_SMOOTH)));

        // Add border if selected
        if (isSelected) {
            this.setBorder(BorderFactory.createLineBorder(Color.black));
        } else {
            this.setBorder(BorderFactory.createEmptyBorder());
        }
        return this;
    }
}
