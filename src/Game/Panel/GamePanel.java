package Game.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Game.Tools.*;
import Game.Tools.Box;


public class GamePanel extends JPanel {
    //Field

    private JPanel panel;

    private JLabel label = new JLabel("Game Start! <3");

    private Font font = new Font("состояние",Font.PLAIN,30);

    private Game game;
    public static final int COLS = 5;

    public static final int ROWS = 7;

    public static final int TOTAL_BALLS = 15;

    public static final int IMAGE_SIZE = 100;


    //Constructor

    public GamePanel() {
        super();
        game = new Game(COLS, ROWS, TOTAL_BALLS);
        game.start();
        Ranges.setSize(new Coord(COLS, ROWS));
        setImages();
        initLabel();
        initPanel();
        initFrame();
    }


//Functions

    private void initLabel(){
        add(label);
        label.setBounds(500,10,80,20);
        setFont(font);
    }

    private void initPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coord coord : Ranges.getAllCords()) {
                    g.drawImage((Image) game.getBox(coord).image,
                            coord.x * IMAGE_SIZE, coord.y * IMAGE_SIZE, this);

                }
                g.drawImage(getImage("b2"), 0, 0, this);
                g.drawImage(getImage("b1"), IMAGE_SIZE * 2, 0, this);
                g.drawImage(getImage("b3"), IMAGE_SIZE * 4, 0, this);
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coord coord = new Coord(x,y);
                SaveCoord save = new SaveCoord();
                if (e.getButton()==MouseEvent.BUTTON1)
                    game.pressLeftButton(coord, save);
                if (e.getButton()==MouseEvent.BUTTON3)
                    game.pressRightButton(coord);
                label.setToolTipText(getMessage());
                panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x * IMAGE_SIZE, Ranges.getSize().y * IMAGE_SIZE));
        setFocusable(true);
        requestFocus();
        add(panel);
    }

    private String getMessage() {
        switch (game.getState()){
            case PLAYED: return "Think twice!";
            case WINNER: return "YOU WIN!";
            default:return "secret";
        }
    }

    private Image getImage(String name) {
        String filename = "/" + name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }

    private void setImages() {
        for (Box box : Box.values()) {
            box.image = getImage(box.name());
        }
    }

    private void initFrame() {
        JFrame startFrame = new JFrame("Lesta game");
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startFrame.setContentPane(panel);
        startFrame.setLocationRelativeTo(null);
        startFrame.pack();
        startFrame.setVisible(true);
        startFrame.setIconImage(getImage("b1"));

    }


}
