/**
 * @author Felix Mann
 * @version 1.0
 * @since 2020-Juli-24
 */

public class Berry {
    private boolean isEaten;
    private int yPos, xPos;

    public Berry(){
        isEaten = false;

        setRandomPos();
    }

    public int getYPos(){
        return yPos;
    }

    public int getXPos(){
        return xPos;
    }

    public boolean isEaten(){
        return isEaten;
    }

    public void setRandomPos(){
        xPos = (int)(Math.random() * ((19) + 1));
        yPos = (int)(Math.random() * ((19) + 1));
    }

    public void setEaten(boolean eaten){
        isEaten = eaten;
    }
}
