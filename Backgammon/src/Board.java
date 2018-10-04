import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private ArrayList<Slot> slots;
    private ArrayList<Slot> middle;

    public Board() {
        slots = new ArrayList<>();
        for(int slotNum = 0; slotNum < 24; slotNum++) {
            slots.add(new Slot());
        }
        slots.get(0).setSize(2, 1);
        slots.get(23).setSize(2, 2);

        slots.get(5).setSize(5, 2);
        slots.get(18).setSize(5, 1);

        slots.get(7).setSize(3, 2);
        slots.get(16).setSize(3, 1);

        slots.get(11).setSize(5, 1);
        slots.get(12).setSize(5, 2);

        middle = new ArrayList<>();
        middle.add(new Slot(0, 1));
        middle.add(new Slot(0, 2));
    }

    public Slot getSlot(int index){
        return slots.get(index);
    }

    public void draw() {
        char[] border = new char[34];
        Arrays.fill(border, '-');

        char[] spaces = new char[3];
        Arrays.fill(spaces, ' ');

        System.out.print(spaces);
        System.out.println(border);

        int slotNum;
        for(slotNum = slots.size()/2 - 1; slotNum >= slots.size()/4; slotNum--) {
            drawLine(slotNum);
        }

        System.out.print(spaces);
        System.out.println(border);

        drawMiddle();

        System.out.print(spaces);
        System.out.println(border);

        for(slotNum = slots.size()/4 -1 ; slotNum >= 0; slotNum--) {
            drawLine(slotNum);
        }
        System.out.print(spaces);
        System.out.println(border);
    }

    private void drawLine(int slotNum){
        if(slotNum < 9)
            System.out.print(" ");
        System.out.print((slotNum+1) + " |-");
        slots.get(slotNum).drawSlot();

        char[] middleSpaces = new char[30 - slots.get(slotNum).getNum() - slots.get(slots.size() - slotNum - 1).getNum()];
        Arrays.fill(middleSpaces, ' ');
        System.out.print(middleSpaces);

        slots.get(slots.size() - slotNum - 1).drawSlot();
        System.out.println("-| " + (slots.size() - slotNum));
    }

    private void drawMiddle() {
        char[] spaces = new char[3];
        Arrays.fill(spaces, ' ');
        System.out.print(spaces);

        middle.get(0).drawSlot();

        char[] middleSpaces = new char[30 - middle.get(0).getNum() - middle.get(1).getNum()];
        Arrays.fill(middleSpaces, ' ');
        System.out.print(middleSpaces);

        middle.get(1).drawSlot();
        System.out.println();
    }

    public void makeMove(int from, int to, boolean same) {
        if(slots.get(to).getNum() == 1 && !same) {
            slots.get(to).remove();
            middle.get(slots.get(from).getPlayer() % 2).add();
        }

        slots.get(to).add( slots.get(from).getPlayer() );
        slots.get(from).remove();
    }
}
