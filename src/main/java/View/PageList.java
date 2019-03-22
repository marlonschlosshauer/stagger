package View;

import Model.Snapshot;

import javax.swing.*;
import java.awt.*;

public class PageList extends JLabel implements ListCellRenderer {

    static int width = 200;
    static int height = PageList.width;

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean disabled, boolean cellHasFocus) {
        Snapshot entry = (Snapshot) value;

        // Update selection
        entry.setDisabled(disabled);

        // Scale image
        this.setIcon(new ImageIcon(entry.getThumbnail().getScaledInstance(PageList.width, PageList.height, Image.SCALE_SMOOTH)));

        // Add border if selected
        if (!disabled) {
            this.setBorder(BorderFactory.createLineBorder(Color.black));
        } else {
            this.setBorder(BorderFactory.createEmptyBorder());
        }
        return this;
    }
}
