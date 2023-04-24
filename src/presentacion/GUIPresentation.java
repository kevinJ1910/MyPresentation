package presentacion;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class GUIPresentation extends JFrame {
    private JButton myExpectations, myPhoto, myHobby;
    private Title title;
    private JPanel containerButtons, containerImage;
    private JLabel imageLabel;
    private JTextArea expectations;
    private Escucha escucha;

    public GUIPresentation() {
        iniGUI();
        this.setTitle("My Presentation");
        this.setSize(600, 400);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void iniGUI() {
        title = new Title("A Presentation About Me", Color.red);
        myPhoto = new JButton("This is me");
        myHobby = new JButton("This is my hobby");
        myExpectations = new JButton("This is my expectations");
        escucha = new Escucha();

        Arrays.asList(myExpectations, myPhoto, myHobby).forEach(containerButtons -> containerButtons.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3, true)));
        Arrays.asList(myExpectations, myPhoto, myHobby).forEach(containerButtons -> containerButtons.setBackground(Color.ORANGE));
        Arrays.asList(myExpectations, myPhoto, myHobby).forEach(containerButtons -> containerButtons.setForeground(Color.BLUE));
        Arrays.asList(myExpectations, myPhoto, myHobby).forEach(containerButtons -> containerButtons.setFont(new Font(Font.SERIF, Font.ITALIC + Font.BOLD, 15)));

        containerButtons = new JPanel();
        containerImage = new JPanel();
        imageLabel = new JLabel();
        expectations = new JTextArea();

        containerButtons.setBackground(Color.red);
        containerImage.setBackground(Color.BLUE);
        expectations.setFont(new Font(Font.SANS_SERIF, Font.BOLD + Font.ITALIC, 15));
        containerImage.setBorder(BorderFactory.createTitledBorder(null, "About Me", TitledBorder.CENTER, TitledBorder.DEFAULT_JUSTIFICATION, new Font(Font.MONOSPACED, Font.PLAIN, 20), Color.PINK));

        buttonExpectations();

        containerImage.add(imageLabel);
        containerButtons.add(myExpectations);
        containerButtons.add(myHobby);
        containerButtons.add(myPhoto);

        myPhoto.addMouseListener(escucha);
        myHobby.addMouseListener(escucha);
        myExpectations.addKeyListener(escucha);

        this.add(title, BorderLayout.NORTH);
        this.add(containerButtons, BorderLayout.SOUTH);
        this.add(containerImage, BorderLayout.CENTER);
    }

    private void buttonExpectations() {
        myExpectations.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("M"), "showText");
        myExpectations.getActionMap().put("showText", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                containerImage.removeAll();
                expectations.setText("Mis expectativas referentes al curso son grandes, siento que puedo adquirir un conocimiento muy relevante para mi\n carrera el cual me ayudara a ser mas eficaz y a desempeñarme mejor en la programacion.\n Mi contacto es: kevin.jordan@correounivalle.edu.co ");

                expectations.setBackground(null);
                expectations.setForeground(Color.orange);
                containerImage.add(expectations);
                containerImage.revalidate();
                containerImage.repaint();
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUIPresentation myGui = new GUIPresentation();
            }
        });
    }

    private class Escucha implements MouseListener, KeyListener {
        private ImageIcon image;

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource() == myPhoto && e.getClickCount() == 1) {
                imageLabel.setIcon(null);
                containerImage.remove(expectations);
                this.image = new ImageIcon(getClass().getResource("/recursos/yo.jpg"));
                imageLabel.setIcon(image);
                containerImage.add(imageLabel);
            }

            else if(e.getSource() == myHobby && e.getClickCount() == 2) {
                imageLabel.setIcon(null);
                containerImage.remove(imageLabel);
                containerImage.remove(expectations);
                this.image = new ImageIcon(getClass().getResource("/recursos/futbol.jpg"));
                imageLabel.setIcon(image);
                containerImage.add(imageLabel);
            }
            repaint();
        }

        // Unused methods
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void keyReleased(KeyEvent e) {}
        public void keyTyped(KeyEvent e) {}
        public void keyPressed(KeyEvent e) {}
    }
}