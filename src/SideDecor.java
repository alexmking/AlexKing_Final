import java.awt.*;

public class SideDecor {

    int betterYStart;

    // this class was more frustrating than I thought it would be. I just wanted a simple loop to make boxes that extend from the
    //center of the windows height, and the edge of the windows width. But because of how the Rectangle is drawn (x and y loc starting at the corner)
    // they would need to be slightly different in order to appear exactly the same on the left side and right side.
    // there was no way I could do it without having two separate setups. The left one adding 1/2 of recWidth to its X loc
    // and the right one subtracting 1/2 of the recWidth from its X loc. This satisfies my methods with parameters requirement and
    // paint requirement.
    public SideDecor(int xStart, int yStart, int recWidth, int recHeight, int gapLength, int loopNum, Graphics g) {


        int count = 1;
        betterYStart = yStart+(recHeight/2);

        g.fillRect(xStart-(recWidth/2), betterYStart, recWidth, recHeight);

        for (int i=0; i<loopNum; i++){

            g.fillRect(xStart-(recWidth/2), betterYStart+(gapLength*count), recWidth, recHeight);
            g.fillRect(xStart-(recWidth/2), betterYStart-(gapLength*count), recWidth, recHeight);

            count++;

        }

}// end of CONSTRUCTOR

    public void rightSideDecor(int xStart, int yStart, int recWidth, int recHeight, int gapLength, int loopNum, Graphics g) {


        int count = 1;

        int rightStartXValue = xStart - (recWidth/2);

        g.fillRect(rightStartXValue, betterYStart, recWidth, recHeight);


        for (int i=0; i<loopNum; i++) {

            g.fillRect(rightStartXValue, betterYStart + (gapLength * count), recWidth, recHeight);
            g.fillRect(rightStartXValue, betterYStart - (gapLength * count), recWidth, recHeight);


            count++;
        }
    }



}
