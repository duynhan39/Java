import java.awt.event.MouseEvent;
import java.util.*;

/**
 * Created by duynhan on 4/4/18.
 */
public class InputManagerBGBearOff extends InputManagerBG {
    private boolean bearOff;
    public InputManagerBGBearOff(GameBG game) {
        super(game);
        bearOff = false;
    }

    public InputManagerBGBearOff(InputManagerBG input) {
        super((GameBG) input.data);
        diceValues = input.diceValues;
        availableMove = input.availableMove;
    }


//    @Override
//    public int readInput(GameBG data, MouseEvent e, BoardView view) throws InterruptedException {
//        int from;
//        System.out.println("Bearing off");
//
//        System.out.println("Avai Dice Num "+availableMove.size());
//
//        if( !data.isDiceRolled() && isInButton(e, view) ){
//            data.setUp(this);
//            data.setDiceRolled(true);
//
//        } else if(data.getFrom() == -2) {
//            int minValue = Collections.min(diceValues.getValues());
//
//            System.out.println("FURTHEST IS " + furtherestIndex());
//            System.out.println("MINVALUE IS " + minValue);
//            if ((data.player == 1 && 25 - minValue <= furtherestIndex()) || (data.player == 2 && minValue >= furtherestIndex())) {
//
//                System.out.println("IT BEARS");
//
//                for (int index = 0; index < diceValues.getAvailableDice().size(); index++) {
//                    System.out.println("Bear off stone from " + furtherestIndex());
//                    data.board.getSlot(furtherestIndex()).remove();
//                }
//
//                diceValues.getAvailableDice().removeAll(diceValues.getAvailableDice());
//                data.setFrom(-2);
//
//            } else {
//                from = convertSlot(e.getX(), e.getY(), view);
//                if (readSlot(from) && bearOff) {
//
//                    System.out.println("Bear off stone from " + from);
//                    data.board.getSlot(from).remove();
//                    data.setFrom(-2);
//
//                    resetBoard();
//
//                    if(diceValues.getValues().size() == 0) {
//                        data.setDiceRolled(false);
//                        data.changePlayer();
//                        for(int i = 0; i<diceValues.getAvailableDice().size(); i++) {
//                            if( (data.player == 1 && diceValues.getAvailableDice().get(i).getNum() + from >= 25)
//                                    || (data.player == 2 && - diceValues.getAvailableDice().get(i).getNum() + from <= 0)) {
//                                diceValues.getAvailableDice().remove(i);
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
////            if(diceValues.getValues().size() == 0) {
////                data.setDiceRolled(false);
////                data.changePlayer();
////            }
//        } else if (data.getTo() == -2) {
//            int to;
//            to = convertSlot(e.getX(), e.getY(), view);
//            System.out.println("Get To " + to);
//
//            if (to == data.getFrom()) {
//                data.setFrom(-2);
//                resetBoard();
//            } else if (readMove(to)) {
//
//                data.setTo(to);
//
//                if (data.getFrom() >= 0)
//                    data.getBoard().makeMove(data.getFrom(), data.getTo(), data.player == data.board.getSlot(to).getPlayer());
//
//                if (data.getFrom() > -2) {
//                    System.out.println(diceValues.getValues().size());
//                    diceValues.removeMove(availableMove.get(data.getTo()));
//                    System.out.println(diceValues.getValues().size());
//                }
//
//                resetBoard();
//
//                if (diceValues.getValues().size() == 0) {
//                    data.setDiceRolled(false);
//                    data.changePlayer();
//                }
//            }
//        }
//
//        if(diceValues.getValues().size() == 0) {
//            data.setDiceRolled(false);
//            data.changePlayer();
//        }
//
//        return 1;
//    }
//
//    @Override
//    public boolean readSlot(int from) {
//        boolean bad = false;
//
//        if (from < 1 || from > 24) {
//            bad = true;
//        }
//
//        if (data.board.getSlot(from).getPlayer() == 0) {
//            bad = true;
//        }
//
//        if (data.board.getSlot(from).getPlayer() != data.player) {
//            bad = true;
//        }
//
//        if(!bad)
//            generateMoveList(from);
//
//        return !bad;
//    }
//
//    @Override
//    public void generateMoveList(Integer from) {
//        availableMove = new HashMap<>();
//        availableMove.put(0, 0);
//
//        for(Integer eachValue : diceValues.getValues()) {
//            int target = from - (data.player * 2 - 3) * eachValue;
//            if ( target == ( 2 - data.player ) * 25 ) {
//                availableMove.put(-1, eachValue);
//                bearOff = true;
//                break;
//            }
//        }
//        if(!bearOff) {
//            for(Integer eachValue : diceValues.getValues()) {
//                int target = from - (data.player * 2 - 3) * eachValue;
//                if (target > 0 && target < 25)
//                    availableMove.put(target, eachValue);
//            }
//        }
//    }
//
//    protected int furtherestIndex() {
//        if( data.player == 1 ) {
//            return data.getHoldList().get(0);
//        }
//        return data.getHoldList().get( data.getHoldList().size() - 1 );
//    }
}
