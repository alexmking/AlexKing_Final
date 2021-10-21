import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImageAdder extends JPanel {


    String[] decorativeImages = {"topleft1.png","toplogo.png","topright2.png", "centerblastoise2.png","ashpika1.png","mainstartpic.png","bottomright.png","bottomleft.png"};

    Image[] decImageArr;
    JLabel[] decorativeImagesArr;
    ImageIcon[] imgIcons;
    int TOTAL_PICS = decorativeImages.length;

    // I created this class to get the 'non-pokemon search' images away from the rest, to try and simplify the look.
    //this way if I need to make changes, I know any images I see in the "FINAL" file will only be images of the SearchArray.
    // this constructor is the same setup to my much larger PokeImagesArray. Once I figured out how to loop all the image creation process,
    // I wanted to try it with everything. This class satisfies the 'class extending JPanel' requirement
    public ImageAdder() {

        //instantiating ImageIcons WITHIN array
        imgIcons = new ImageIcon[decorativeImages.length];
        for (int i = 0; i< decorativeImages.length; i++){
            imgIcons[i]=new ImageIcon();
        }
        //instantiating JLabels WITHIN array
        decorativeImagesArr = new JLabel[decorativeImages.length];
        for (int i = 0; i< decorativeImages.length; i++){
            decorativeImagesArr[i]=new JLabel();
            decorativeImagesArr[i].setVerticalAlignment(SwingConstants.CENTER);
        }
        decImageArr = new Image[TOTAL_PICS];
        for(int i = 0; i< TOTAL_PICS; i++){
            loadDecorativeImages(decorativeImages[i], i);
        }
        // now need a loop for:   icon.setImage(currentImage);
        for (int i = 0; i< decorativeImages.length; i++){
            imgIcons[i].setImage(decImageArr[i]);
        }
        // now need loop for:  imageLabel.setIcon(icon)
        for (int i = 0; i< decorativeImages.length; i++){
            decorativeImagesArr[i].setIcon(imgIcons[i]);
        }



    }






    public void loadDecorativeImages(String imgName, int indexNum) {
        String pathZA = "images/"+imgName;
        File fileZA = new File(pathZA);
        try {
            decImageArr[indexNum] = ImageIO.read(fileZA);
        } catch (IOException e){
            e.printStackTrace(); }
    }// end of loadImageZ






}
