import java.awt.event.MouseEvent;
import java.util.*;

public class InputManagerBG extends InputManager{
    protected DiceValues diceValues;
    protected Map<Integer, Integer> availableMove;

    public InputManagerBG(GameBG game) {
        super(game);
        //diceValues = new DiceValues( data.getDice() );
    }

    public InputManagerBG(InputManagerBG input) {
        super((GameBG) input.data);
        diceValues = input.diceValues;
        availableMove = input.availableMove;
    }

    @Override
    public void readInput(GameBG data, MouseEvent e, BoardView view) throws InterruptedException {
        //int flag = 1;
        if( data.isStartGame() && isInButton(e, view)) {
            data.player = data.pickFirstPlayer();
            data.setStartGame(false);

        } else if( !data.isDiceRolled() && isInButton(e, view) ) {
            data.setUp(this);
            data.setDiceRolled(true);

            if(this.data.getHoldList().get(0) == (this.data.player - 1) * 25 ) {
                System.out.println("You need to get your pieces back in the game.");
                data.setFrom( (data.player - 1) * 25 );
                generateMoveList(data.getFrom());

                System.out.print("Options: ");
                java.util.List list = new ArrayList<>(this.availableMove.keySet());
                Collections.sort(list);
                System.out.println(list);

                for(int i = 1; i < list.size(); i++) {
                    data.getBoard().getSlot((Integer) list.get(i)).setMovable(true);
                }
            }

            ArrayList<Integer> holdList = data.getHoldList();
            if(holdList.get(0) > ( 2 -  data.player ) * 18
                    && holdList.get(holdList.size() - 1) < ( 2 - data.player) * 18 + 7) {

                System.out.println("BEAR OFF TRUE");

                data.setState(2);
            }

            boolean noAvailableMove = true;
            for(Integer slotNum:this.data.board.generateHoldList( this.data.player ) ) {
                generateMoveList(slotNum);
                if(availableMove.size() != 1) {
                    noAvailableMove = false;
                    break;
                }
            }
            if(noAvailableMove && data.getState() != 2) {
                System.out.println("No available moves! Good luck next time!");
                data.changePlayer();
                data.setDiceRolled(false);
            }

        } else if( data.isDiceRolled() && isInBoard(e.getY(), view) ) {
            int from, to;
            if (data.getFrom() == -2) {

                from = convertSlot(e.getX(), e.getY(), view);
                System.out.println("Get From " + from +" "+data.board.getSlot(from).getPlayer());

                System.out.println("State: "+data.getState());
                if(data.getState() == 2) {
                    boolean bearOff = false;
                    System.out.println("Clearing");
                    for(int i = 0; i<diceValues.getAvailableDice().size(); i++) {
                        if( (data.player == 1 && diceValues.getAvailableDice().get(i).getNum() + data.getFrom() >= 25)
                                || (data.player == 2 && - diceValues.getAvailableDice().get(i).getNum() + data.getFrom() <= 0)) {
                            diceValues.getAvailableDice().remove(i);
                            bearOff = true;
                            break;
                        }
                    }
                    if(bearOff) {
                        data.getBoard().getSlot(data.getFrom()).remove();
                        data.setFrom(-2);
                    }
                } else if ( readSlot(from) ) {

                    data.board.getSlot(from).setClicked(true);
                    data.setFrom(from);

                    System.out.print("Options: ");
                    java.util.List list = new ArrayList<>(this.availableMove.keySet());
                    Collections.sort(list);
                    System.out.println(list);

                    for(int i = 1; i < list.size(); i++) {
                        data.getBoard().getSlot((Integer) list.get(i)).setMovable(true);
                    }

                }
            } else if (data.getTo() == -2) {

                to = convertSlot(e.getX(), e.getY(), view);
                System.out.println("Get To " + to);

                if( to == data.getFrom() ) {
                    data.setFrom(-2);
                    resetBoard();
                } else if ( readMove(to) ) {

                    data.setTo(to);

                    System.out.println("MAKING MOVE");



                    //if(data.getState() == 2) {
//                        for(int i = 0; i<diceValues.getAvailableDice().size(); i++) {
//                            boolean bearOff = false;
//                            if( (data.player == 1 && diceValues.getAvailableDice().get(i).getNum() + data.getFrom() >= 25)
//                                    || (data.player == 2 && - diceValues.getAvailableDice().get(i).getNum() + data.getFrom() <= 0)) {
//                                diceValues.getAvailableDice().remove(i);
//                                bearOff = true;
//                                break;
//                            }
//                            if(!bearOff) {
//                                data.getBoard().makeMove(data.getFrom(), data.getTo(), data.player == data.board.getSlot(to).getPlayer());
//                            } else {
//                                data.
//                            }
//                        }
                    //} else
                    if(data.getFrom() > -2) {

                        data.getBoard().makeMove(data.getFrom(), data.getTo(), data.player == data.board.getSlot(to).getPlayer());
                        System.out.println("REMOVING");
                        System.out.println(diceValues.getValues().size());
                        diceValues.removeMove(availableMove.get(data.getTo()));
                        System.out.println(diceValues.getValues().size());
                    }

                    resetBoard();

                    if(diceValues.getValues().size() == 0) {
                        data.setDiceRolled(false);
                        data.changePlayer();
                    }

                    ArrayList<Integer> holdList = data.getHoldList();
                    if(holdList.get(0) > ( 2 -  data.player ) * 18
                            && holdList.get(holdList.size() - 1) < ( 2 - data.player) * 18 + 7) {

                        System.out.println("BEAR OFF TRUE");

                        data.setState(2);
                    }
                }
            }
        }
        System.out.println("YOU CLICKED!");
        //data.notifyObserver();

        //return flag;
    }

    public void resetBoard() {
        data.setTo(-2);
        data.setFrom(-2);
        for(int i = 1; i <= 24; i++) {
            data.getBoard().getSlot(i).setMovable(false);
            data.getBoard().getSlot(i).setClicked(false);
        }
    }

    public boolean readSlot(int from) {
        boolean bad = false;

            if ( (from < 1 || from > 24 ) && data.getState()<2 ) {
                bad = true;
            }
            if (data.board.getSlot(from).getPlayer() == 0) {
                bad = true;
            }
            if (data.board.getSlot(from).getPlayer() != data.player) {
                bad = true;
            }
            if(!bad)
                generateMoveList(from);
            if(availableMove.size() == 1) {
                bad = true;
            }
        return !bad;
    }

    public boolean readMove(int to) {
        boolean bad = false;

        if( !availableMove.containsKey(to) )
            bad = true;

        return !bad;
    }

    public void generateMoveList(Integer from) {
        availableMove = new HashMap<>();
        availableMove.put(0, 0);

        for(Integer eachMove: diceValues.getValues()) {
            int target = from - (data.player * 2 - 3) * eachMove;
            if ( target > 0 &&
                    target < 25 &&
                    data.board.getSlot(target).isOpen(data.player) )
                availableMove.put(target, eachMove);
        }
    }

    protected int convertSlot(int x, int y, BoardView view) {
        int border = view.getWidth() / 2 - view.getAnchorD() / 9 / 2 + view.getGap();
        int orient = 1;

        if(y < view.getHeight() / 2 + view.getNameHeight())
            orient = -1;

        int dis = x - border;
        if(x > view.getWidth()/2 + view.getGap())
            dis -= 2 * view.getGap();

        return (25-orient)/2 - orient * (dis) / view.getTriWid();
    }

    protected boolean isInBoard(int y, BoardView view) {
        return !(y < view.getHeight() / 2 - view.getAnchorD() / 15 / 2 + view.getGap() + view.getNameHeight()
                || y > view.getHeight() / 2 + view.getAnchorD() / 15 / 2 - view.getGap() + view.getNameHeight()
                || (y > view.getHeight()/2 + view.getNameHeight()  - view.getGap()
                && y < view.getHeight()/2 + view.getNameHeight() + view.getGap() )
        );
    }

    protected boolean isInButton(MouseEvent e, BoardView view) {
        return (
                (e.getX() > view.getWidth() / 2 + view.getAnchorD() / 9 / 4)
                        && ( e.getX() < view.getWidth() / 2 + view.getAnchorD() / 9 / 2 - view.getGap() )
                        && ( e.getY() > view.getGap() )
                        && ( e.getY() < view.getGap() + view.getTriWid() * 3/5 )
        );
    }
}
