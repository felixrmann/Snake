import java.util.Vector;

/**
 * @author Felix Mann
 * @version 1.0
 * @since 2020-Juli-09
 */

public class Schalange {
    private Vector<Integer> yPos, xPos;
    private int legth;

    public Schalange(){
        yPos = new Vector<>();
        xPos = new Vector<>();

        yPos.add(10);
        yPos.add(10);
        yPos.add(10);

        xPos.add(5);
        xPos.add(4);
        xPos.add(3);

        legth = 3;
    }

    public void setYPos(int yPos) {
        Vector temp = new Vector(this.yPos);

        this.yPos.clear();
        this.yPos.add(yPos);
        for (int i = 0; i < temp.size(); i++) {
            this.yPos.add((Integer)temp.get(i));
        }
    }

    public void setXPos(int xPos) {
        Vector temp = new Vector(this.xPos);

        this.xPos.clear();
        this.xPos.add(xPos);
        for (int i = 0; i < temp.size(); i++) {
            this.xPos.add((Integer)temp.get(i));
        }
    }

    public int getYPos(int index){
        return yPos.get(index);
    }

    public int getXPos(int index){
        return xPos.get(index);
    }

    public void removeFirstIndex(){
        yPos.remove(0);
        xPos.remove(0);
    }

    public void removeLastIndex(){
        yPos.remove(yPos.size() - 1);
        xPos.remove(xPos.size() - 1);
    }

    public int getLegth() {
        return legth;
    }

    public void grow() {
        legth++;
    }
}
