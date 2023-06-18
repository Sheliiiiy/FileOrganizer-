package org.example;

import javax.swing.*;
import java.awt.*;

public class ScrollableTextArea {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Scrollable TextArea Example");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            AutoExpandingTextArea textArea = new AutoExpandingTextArea();
            JScrollPane scrollPane = new JScrollPane(textArea);
            frame.add(scrollPane, BorderLayout.CENTER);

            frame.setSize(400, 300);
            frame.setVisible(true);
        });
    }

    static class AutoExpandingTextArea extends JTextArea {
        @Override
        public boolean getScrollableTracksViewportWidth() {
            return getUI().getPreferredSize(this).width
                    <= getParent().getSize().width;
        }

        @Override
        public void setText(String text) {
            super.setText(text);
            adjustHeight();
        }

        @Override
        public void append(String str) {
            super.append(str);
            adjustHeight();
        }

        private void adjustHeight() {
            Dimension d = getPreferredSize();
            d.height = getScrollableUnitIncrement(getVisibleRect(), SwingConstants.VERTICAL, 1);
            setPreferredSize(d);
            revalidate();
        }
    }
}
