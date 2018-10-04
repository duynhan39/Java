import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardGUI extends JPanel implements Observer{
    private JPanel root;
    private BoardView fullBoardView;

    private JTextArea text;


    private static GameBG game;
    InputManagerBG input;

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Backgammon");
        frame.setContentPane(new BoardGUI().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public BoardGUI() throws InterruptedException {
        game = new GameBG();
        input = new InputManagerBG(game);
        game.register(this);
        fullBoardView.setGame(game);

        fullBoardView.setPreferredSize(new Dimension(1000, 750));

        game.notifyObserver();

        root.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if( !game.isOver() ) {
                    try {
                        input.readInput(game, e, fullBoardView);

//                        if (game.getState() == 2) {
//                            System.out.println("CHANGE");
//                            input = new InputManagerBGBearOff(input);
//                        } else if (game.getState() == 1) {
//                            input = new InputManagerBG(input);
//                        }

                        if (game.getHoldList().size() == 0) {
                            System.out.println("\nPLAYER " + game.player + " WINS!!!\n");
                            //break;
                            game.setOver(true);
                        }

                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    game.notifyObserver();
                }
            }
        });
    }

    @Override
    public void update() {
        fullBoardView.repaint();
    }
}
