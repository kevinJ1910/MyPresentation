package presentacion;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
        //actionMouse();
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


        public void mouseClicked(MouseEvent e) {
            imageLabel.setIcon(null);
            containerImage.remove(expectations);

            if (e.getSource() == myPhoto) {
                this.image = new ImageIcon(getClass().getResource("/recursos/yo.jpg"));
                imageLabel.setIcon(image);
            } else if (e.getSource() == myHobby && e.getClickCount() == 2) {
                this.image = new ImageIcon(getClass().getResource("/recursos/futbol.jpg"));
                imageLabel.setIcon(image);
            }
            revalidate();
            repaint();
        }

        public void keyPressed(KeyEvent e) {

            if (e.getKeyChar() == 'm') {
                imageLabel.setIcon(null);
                containerImage.remove(expectations);
                expectations.setText("Mis expectativas referentes al curso son grandes, siento que puedo adquirir un conocimiento muy relevante para mi\n carrera el cual me ayudara a ser mas eficaz y a desempeñarme mejor en la programacion.\n Mi contacto es: kevin.jordan@correounivalle.edu.co ");

                containerImage.add(expectations);
                expectations.setForeground(Color.orange);
                expectations.setBackground(null);
            }
            revalidate();
            repaint();
        }

        // Unused methods
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void keyReleased(KeyEvent e) {}
        public void keyTyped(KeyEvent e) {}
    }
}