package org.example;

import javax.swing.*;
import java.awt.Dimension;

public class AutoExpandingTextArea extends JTextArea {
    @Override
    public void append(String text) {
        super.append(text);
        adjustSize();
    }

    private void adjustSize() {
        Dimension preferredSize = getPreferredSize();
        int height = preferredSize.height;
        int maxHeight = getParent().getHeight();

        if (height > maxHeight) {
            setPreferredSize(new Dimension(preferredSize.width, maxHeight));
            revalidate();
        }
    }
}
