//**Final by Alex King (Pokedex)
// * @author Charlie Gorrill
// * version Fall 2019
//* CSci 1130
//*


//potential ideas:
//create own layouts at class level, so you can change their value all in one place and
//have less magic numbers throughout the code  ( eg: BorderLayout northLayout...northLayout= new BorderLayout etc)

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

// General comments:
// the only magic numbers I decided to keep are ones that seemed more practical to leave than make into a constant, (like 'rigidAreas' in the WestPanel)


public class Final extends JFrame implements ActionListener, ItemListener{

    //pokedex Colors
    final Color WHITE_COLOR = new Color(246,247,235);
    final Color RED1_COLOR = new Color(233,79,55);
    final Color RED2_COLOR = new Color(220,10,45);
    final Color RED3_COLOR = new Color(130,6,26);
    final Color RED4_COLOR = new Color(54,14,21);

    final Color RED5_COLOR = new Color(245,175,164);
    final Color RED6_COLOR = new Color(170,58,41);
    final Color RED7_COLOR = new Color(237,111,91);

    // THE COLOR FACTORY:
    Color northPanelColor = RED2_COLOR;
    Color southPanelColor = RED2_COLOR;
    Color eastPanelColor = RED3_COLOR;
    Color westPanelColor = RED1_COLOR;
    Color centerPanelColor = RED3_COLOR;

    // The 9 decorative images
        int topImg1 = 0;
        int titleImg = 1;
        int topImg2 = 2;
        int centerImg1 = 3;
        int centerImg2 = 4;
        int imageAtStart = 5;
        int bottomRightImg = 6;
        int bottomLeftImg = 7;

    // 5 quadrants : NORTH, EAST, SOUTH, WEST, CENTER

    JPanel northTitlePanel, westClassPanel, southSearchPanel;
    JScrollPane centerTextScrollPane, eastImgScrollPane;

    JPanel imgPanel, centerPanel;
    JTextArea outputTextArea;
    String outputInfo ="";

    //search and images related declarations

    int anyVal = 0;
    int scrollPaneStartAtTop = 0;
    int noMatchImage = 37;
    int errorImage = 38;
    String pokemonNameInput;
    boolean inputTestResults;
    int searchResult;
    int noMatchFound = -1;
    int eastScrollPaneSize = 500;



    // POKEMON ARRAYS and CORRESPONDING STRINGS/ IMAGES

    int [] mewtwo = {1,5,2,4,1};
    int [] pikachu = {2,4,1,1,2};
    int [] snorlax = {2,9,2,4,1};
    int [] gengar = {1,5,2,1,1};
    int [] ditto = {3,9,1,1,1};
    int [] venusaur = {1,3,2,3,1};
    int [] charizard = {1,1,2,3,1};
    int [] blastoise = {1,2,2,3,1};
    int [] nidoqueen = {1,3,2,3,1};
    int [] nidoking = {1,3,2,3,1};
    int [] alakazam = {1,5,2,3,1};
    int [] machamp = {1,8,2,4,1};
    int [] victreebel = {1,3,2,1,1};
    int [] hypno = {1,5,2,3,1};
    int [] gyarados = {1,2,4,4,1};
    int [] articuno = {1,13,2,3,1};
    int [] zapdos = {1,4,2,3,1};
    int [] moltres = {1,1,2,3,1};
    int [] dragonite = {1,7,2,4,1};
    int [] mew = {1,5,1,1,1};
    int [] lugia = {1,5,3,4,1};
    int [] hooh = {1,1,3,4,1};
    int [] vileplume = {2,3,2,1,1};
    int [] magmar = {2,1,2,2,1};
    int [] chansey = {3,9,2,2,2};
    int [] magneton = {2,4,2,3,1};
    int [] electabuzz = {2,4,2,2,2};
    int [] koffing = {4,15,1,1,1};
    int [] scyther = {3,3,2,3,1};
    int [] muk = {3,15,2,2,1};
    int [] hitmonchan = {3,8,2,3,1};
    int [] cloyster = {3,2,2,4,1};
    int [] jigglypuff = {5,9,1,1,2};
    int [] psyduck = {5,2,1,1,2};
    int [] magikarp = {5,2,2,1,2};
    int [] meowth = {5,9,1,1,2};
    int [] mrmime = {4,5,2,3,1};

    // this "int[][] bigMainArray" is the heart of my project and was by far the toughest section to code.
    //  It's an Array within an Array, where the first array is representing all the individual pokemon and the
    //  second Array is representing the statistics of each of those pokemon which is needed to relate to the user searches.
    // all these arrays meet the requirements for Arrays

    int[][] bigMainArray = {mewtwo, pikachu, snorlax, gengar, ditto, venusaur,charizard,blastoise,nidoqueen,nidoking,alakazam,machamp,victreebel,hypno,gyarados,articuno,zapdos,moltres,dragonite,mew,lugia,hooh,vileplume,magmar,chansey,magneton,electabuzz,koffing,scyther,muk,hitmonchan,cloyster,jigglypuff,psyduck,magikarp,meowth,mrmime};

    // I wanted to setup the project so it would be easy to add new pokemon to the database, without having to enter the new
    // pokemons information 100 different times in different places throughout the code. In order to add a new pokemon,
    // all you have to is add it's name in the bigMainArray and the 4 String Arrays below and because they're so close
    // together, it takes two seconds to add more. The only time consuming part is collecting the actual pokemons info from the internet.
    String[] numericalPokemon = {"Mewtwo", "Pikachu","Snorlax","Gengar","Ditto", "Venusaur", "Charizard","Blastoise","Nidoqueen","Nidoking","Alakazam","Machamp","Victreebel","Hypno","Gyarados","Articuno","Zapdos","Moltres","Dragonite","Mew","Lugia","HoOh","Vileplume","Magmar","Chansey","Magneton","Electabuzz","Koffing","Scyther","Muk","Hitmonchan","Cloyster","Jigglypuff","Psyduck","Magikarp","Meowth","MrMime"};
    String[] pokemonNameSearchArray = {"Mewtwo", "Pikachu","Snorlax","Gengar","Ditto", "Venusaur", "Charizard","Blastoise","Nidoqueen","Nidoking","Alakazam","Machamp","Victreebel","Hypno","Gyarados","Articuno","Zapdos","Moltres","Dragonite","Mew","Lugia","HoOh","Vileplume","Magmar","Chansey","Magneton","Electabuzz","Koffing","Scyther","Muk","Hitmonchan","Cloyster","Jigglypuff","Psyduck","Magikarp","Meowth","MrMime"};
    String[] pokeImagesArray = {"mewtwo.png","pikachu.png","snorlax.png","gengar.png","ditto.png","venusaur.png","charizard.png","blastoise.png","nidoqueen.png","nidoking.png","alakazam.png","machamp.png","victreebel.png","hypno.png","gyarados.png","articuno.png","zapdos.png","moltres.png","dragonite.png","mew.png","lugia.png","hooh.png","vileplume.png","magmar.png","chansey.png","magneton.png","electabuzz.png","koffing.png","scyther.png","muk.png","hitmonchan.png","cloyster.png","jigglypuff.png","psyduck.png","magikarp.png","meowth.png","mrmime.png","error1.png", "error2.png"};

    // this is the more specific information about each pokemon that prints out in the textbox if said Pokemon is a match for the users search.
    // As much as it bothered me how much space this area took up, there was no way around it and looping the repeated words like 'info:' and 'weight:'  would only save a little space horizontally.
    private String[] detailedPokestats = {"Mewtwo Info:\nType: Psychic\nHeight: 6 ft 7 inches\nWeight: 269 lbs\nWeaknesses: Bug/Ghost\n",
            "Pikachu Info:\nType: Electric\nHeight: 1 ft 5 inches\nWeight: 13 lbs\nWeaknesses: Electric/Ground\n",
            "Snorlax Info:\nType: Normal\nHeight: 6 ft 11 inches\nWeight: 1014 lbs\nWeaknesses: Fighting\n",
            "Gengar Info:\nType: Psychic\nHeight: 4 ft 11 inches\nWeight: 89 lbs\nWeaknesses: Ghost/Psychic\n",
            "Ditto Info:\nType: Normal\nHeight: 1 ft 1 inches\nWeight: 9 lbs\nWeaknesses: Fighting\n",
            "Venusaur Info:\nType: Grass\nHeight: 6 ft 1 inches\nWeight: 220 lbs\nWeaknesses: Fire/Psychic\n",
            "Charizard Info:\nType: Fire\nHeight: 5 ft 2 inches\nWeight: 200 lbs\nWeaknesses: Water\n",

            "Blastoise Info:\nType: Water\nHeight: 5 ft 1 inches\nWeight: 188 lbs\nWeaknesses: Electric\n",
            "Nidoqueen Info:\nType: Grass\nHeight: 4 ft 3 inches\nWeight: 132 lbs\nWeaknesses: Ground, Ice\n",
            "Nidoking Info:\nType: Electric\nHeight: 4 ft 7 inches\nWeight: 136 lbs\nWeaknesses: Ice/Ground\n",
            "Alakazam Info:\nType: Psychic\nHeight: 4 ft 11 inches\nWeight: 105 lbs\nWeaknesses: Bug/Ghost\n",
            "Machamp Info:\nType: Fighting\nHeight: 5 ft 3 inches\nWeight: 286 lbs\nWeaknesses: Psychic\n",

            "Victreebel Info:\nType: Grass\nHeight: 5 ft 7 inches\nWeight: 34 lbs\nWeaknesses: Fire\n",
            "Hypno Info:\nType: Psychic\nHeight: 5 ft 3 inches\nWeight: 167 lbs\nWeaknesses: Psychic\n",
            "Gyarados Info:\nType: Water\nHeight: 21 ft 4 inches\nWeight: 518 lbs\nWeaknesses: Electric\n",
            "Articuno Info:\nType: Ice\nHeight: 4 ft 11 inches\nWeight: 122 lbs\nWeaknesses: Rock\n",

            "Zapdos Info:\nType: Electric\nHeight: 5 ft 3 inches\nWeight: 116 lbs\nWeaknesses: Ice\n",
            "Moltres Info:\nType: Fire\nHeight: 6 ft 7 inches\nWeight: 132 lbs\nWeaknesses: Water\n",
            "Dragonite Info:\nType: Dragon\nHeight: 7 ft 3 inches\nWeight: 463 lbs\nWeaknesses: Ice/Rock\n",
            "Mew Info:\nType: Psychic\nHeight: 1 ft 4 inches\nWeight: 9 lbs\nWeaknesses: Bug/Ghost\n",
            "Lugia Info:\nType: Psychic\nHeight: 17 ft 1 inches\nWeight: 476 lbs\nWeaknesses: Dark, Electric\n",
            "Ho Oh Info:\nType: Fire\nHeight: 12 ft 6 inches\nWeight: 438 lbs\nWeaknesses: Rock/ Water\n",

            "Vileplume Info:\nType: Grass\nHeight: 3 ft 1 inches\nWeight: 41 lbs\nWeaknesses: Fire/Ice\n",
            "Magmar Info:\nType: Fire\nHeight: 4 ft 3 inches\nWeight: 98 lbs\nWeaknesses: Rock/Water\n",
            "Chansey Info:\nType: Normal\nHeight: 3 ft 7 inches\nWeight: 73 lbs\nWeaknesses: Fighting\n",
            "Magneton Info:\nType: Electric\nHeight: 3 ft 3 inches\nWeight: 132 lbs\nWeaknesses: Fighting\n",
            "Electabuzz Info:\nType: Electric\nHeight: 3 ft 7 inches\nWeight: 66 lbs\nWeaknesses: Ground\n",

            "Koffing Info:\nType: Poison\nHeight: 2 ft 1 inches\nWeight: 2 lbs\nWeaknesses: Psychic\n",
            "Scyther Info:\nType: Grass\nHeight: 4 ft 11 inches\nWeight: 123 lbs\nWeaknesses: Fire\n",
            "Muk Info:\nType: Poison\nHeight: 3 ft 11 inches\nWeight: 66 lbs\nWeaknesses: Ground/Psychic\n",
            "Hitmonchan Info:\nType: Fighting\nHeight: 4 ft 7 inches\nWeight: 110 lbs\nWeaknesses: Psychic\n",
            "Cloyster Info:\nType: Water\nHeight: 4 ft 11 inches\nWeight: 292 lbs\nWeaknesses: Electric\n",

            "Jigglypuff Info:\nType: Normal\nHeight: 1 ft 8 inches\nWeight: 13 lbs\nWeaknesses: Steel/Poison\n",
            "Psyduck Info:\nType: Psychic\nHeight: 2 ft 7 inches\nWeight: 43 lbs\nWeaknesses: Electric\n",
            "Magikarp In fo:\nType: Water\nHeight: 2 ft 11 inches\nWeight: 22 lbs\nWeaknesses: Electric\n",
            "Meowth Info:\nType: Normal\nHeight: 1 ft 4 inches\nWeight: 9 lbs\nWeaknesses: Fighting\n",
            "Mr. Mime Info:\nType: Psychic\nHeight: 4 ft 3 inches\nWeight: 120 lbs\nWeaknesses: Steel\n"
    };

    // image array variables
    Image[] pics;
    JLabel[] imgLabelArrayZ;
    ImageIcon[] imgIconArrayZ;
    int NUM_PICS = pokeImagesArray.length;

    // these are the variables which get assigned as the 'ITS A MATCH' criteria when a search is executed
    int newRank, newType, newHeight, newWeight, newEvolution;

    // useful constants for reading the dimensions of the BorderLayout
    int windowWidth;
    int windowHeight;

    BoxLayout southPanelBoxLayout, imgPanelBoxLayout, centerPanelBoxLayout;

    //components being added to NORTH, SOUTH and WEST PANELS
    JButton searchButton2, searchButton1;
    LabelAdder decorLabels;
    JComboBox rankComboBox, typeComboBox, heightComboBox, weightComboBox, evolutionComboBox;
    JTextField searchByNameTextField;

    // String Arrays that represent the options in the ComboBoxes of the 'SEARCH BY CLASS' SEARCH
    String[] rankComboBoxArray = {"Any","A Tier","B Tier", "C Tier", "D Tier", "F Tier"};
    String[] typeComboBoxArray = {"Any","Fire","Water", "Grass", "Electric", "Psychic", "Rock","Dragon","Fighting", "Normal", "Flying", "Ghost", "Bug", "Ice", "Ground", "Poison"};
    String[] heightComboBoxArray = {"Any","0-2 ft","2-10 ft", "10-20 ft", "20-30 ft"};
    String[] weightComboBoxArray = {"Any","0-50 lbs","50-100 lbs", "100-250 lbs", "250-1000lbs"};
    String[] evolutionComboBoxArray = {"Either","Fully Evolved","Not Fully Evolved"};

    String matchesTotal, matches, pokeInfo; // constants for filtering out what to print once the search has been finished

    ImageAdder imgPack;  // class for decorative images (the images that won't change depending on what the user does)
    SideDecor sideBoxes; // class for the side different color boxes bordering the left and right edges of the window

    public static void main(String[]args){
            Final frame = new Final();
            frame.setSize(new Dimension(1000,700));
            frame.setLayout(new BorderLayout());

            frame.setupGUI();
            frame.setupImageArrays();
            frame.setupComponents();
            frame.addComponentsToPanels();

            frame.pack();
            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            frame.setVisible(true);

    }// end of MAIN


            // my Paint method is mainly for decorative touches, like the little boxes along the far left/ right.
            // The magic numbers are inputs for the class SideDecor, which explains what they are for in it's method so I
            // figured it would be redundant to create constants for them
    public void paint(Graphics g){

            int boxesYLoc = (getHeight()/2);
            int boxesXLoc = 0;

            int darkBarYLoc = 72;
            int darkBarXLoc = 0;
            int darkBarHeight = 4;
            int frameW=getWidth();
            int frameH=getHeight();


            super.paint(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            //dark red bar hugging the bottom of the NORTH region BorderLayout
            g.setColor(RED4_COLOR);
            g.fillRect(darkBarXLoc, darkBarYLoc, frameW, darkBarHeight);

            // repeating boxes on left and right edges of window
            // this satisfies the 'methods with parameters' and 'multi-class requirements'. I wanted to make the
            // decorBoxes method very flexible, which is why there are so many parameters. I knew I would be adjusting
            // variables often and it's easy to do right from the method call instead of in the class tab itself
            g.setColor(RED6_COLOR);
            sideBoxes = new SideDecor(boxesXLoc, boxesYLoc,4,18,30,7,g);
            g.setColor(RED7_COLOR);
            sideBoxes = new SideDecor(boxesXLoc,boxesYLoc,8,15,30,7,g);

            g.setColor(RED4_COLOR);
            sideBoxes.rightSideDecor(frameW,boxesYLoc,4,20,30,7,g);
            g.setColor(RED6_COLOR);
            sideBoxes.rightSideDecor(frameW,boxesYLoc,8,15,30,7,g);

    }//end of paint method

    // method for GUI that gets outsourced quickly to make it easier to follow the code from top to bottom
    public void setupGUI() {
        Container window = getContentPane();
        setupBorderLayoutRegions();
    }

    public void setupImageArrays(){
            //instantiating ImageIcons WITHIN array
                    imgIconArrayZ = new ImageIcon[pokeImagesArray.length];
                    for (int i=0; i<pokeImagesArray.length; i++){
                        imgIconArrayZ[i]=new ImageIcon();
                    }
            //instantiating JLabels WITHIN array
                    imgLabelArrayZ = new JLabel[pokeImagesArray.length];
                    for (int i=0; i<pokeImagesArray.length; i++){
                        imgLabelArrayZ[i]=new JLabel();
                        imgLabelArrayZ[i].setVerticalAlignment(SwingConstants.CENTER);
                    }
                    pics = new Image[NUM_PICS];
                    for(int i=0; i<NUM_PICS; i++){
                        loadImageZ(pokeImagesArray[i], i);
                    }
            // now need a loop for:   icon.setImage(currentImage);
                    for (int i=0; i<pokeImagesArray.length; i++){
                        imgIconArrayZ[i].setImage(pics[i]);
                    }
            // now need loop for:  imageLabel.setIcon(icon)
                    for (int i=0; i<pokeImagesArray.length; i++){
                        imgLabelArrayZ[i].setIcon(imgIconArrayZ[i]);
                    }

                    imgPack = new ImageAdder();

    }

    // method setting up all the components before they can be added to Panels/ ScrollPanes and well as
    //adding all the listeners to the comboboxes and search buttons, satisfying their requirements.
    public void setupComponents(){


        decorLabels = new LabelAdder();
        searchButton2 = new JButton("Search");
        searchButton1 = new JButton("Search");

        rankComboBox = new JComboBox(rankComboBoxArray);
        typeComboBox = new JComboBox(typeComboBoxArray);
        heightComboBox = new JComboBox(heightComboBoxArray);
        weightComboBox = new JComboBox(weightComboBoxArray);
        evolutionComboBox = new JComboBox(evolutionComboBoxArray);

        searchByNameTextField = new JTextField();
        searchByNameTextField.setBackground(RED5_COLOR);

        //REGISTERING LISTENERS
        searchButton2.addActionListener(this);
        searchButton1.addActionListener(this);
        rankComboBox.addItemListener(this);
        typeComboBox.addItemListener(this);
        heightComboBox.addItemListener(this);
        weightComboBox.addItemListener(this);
        evolutionComboBox.addItemListener(this);

    }

    // this method is where I add everything to the actual JPanels/ ScrollPanes and I split them into 5 separate methods
    // for readability
    public void addComponentsToPanels(){

        northComponents();

        southComponents();

        eastComponents();

        centerComponents();

        westComponents();

    }


//  this method satisfies the Listeners and ActionPerformed requirements. This is where the searches are initiated.
    // the first step is to distinguish between what search the user wants with , and then branch off dep
    @Override
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();

            if (source == searchButton1)  {

                executeClassSearch();
                repaint();
                revalidate();
        }

            if (source == searchButton2) {

                userInputTest();
                if (inputTestResults) {
                    validInput(); }
                else {
                    invalidInput(); }
            repaint();
            revalidate();
        }
    }// end of actionPerformed




    // this method was the biggest hurdle I had to get over. Once I finally figured out the 'double array', I still
    // had to figure out how to use it correctly in the context of a rather complicated search setup. The tough part
    // was getting the if statement correct because the 'any' option really expands the definition of a 'match'. Also,
    // it was hard to know where to use the [count] within the 2 double array brackets and why to set it at 0 as opposed to -1
    // I chose to do the If statement as one long condition because close matches are irrelevant so there's no point to nest the
    //if statements in other if statements or anything like that. All that matters is if the Pokemon meets the requirements of
    // that one long condition, then it's a match. It also took a while to realize I had to print off the match results DURING the
    // loop because the info I'm sending to the textboxes/ image Panel are all tied to the Count number. Once the loop ends, the
    //count number changes so the matched pokemon can no longer be recognized. This method satisfies the arrays and traversals
    //requirements along with the loop and if statement requirements.
    public void pokemonSearchByClass4(int[][] arr) {
        refreshSearchCriteria();

        int count = 0;
        int totalMatches = 0;

        while (count < arr.length) {

            if ((arr[count][0] == newRank || newRank == anyVal) &&
                    (arr[count][1] == newType || newType == anyVal) &&
                    (arr[count][2] == newHeight || newHeight == anyVal) &&
                    (arr[count][3] == newWeight || newWeight == anyVal) &&
                    (arr[count][4] == newEvolution || newEvolution == anyVal)) {

                totalMatches++;

                matches = matches+"\n-"+numericalPokemon[count];
                pokeInfo = pokeInfo+"\n"+detailedPokestats[count];
                imgPanel.add(imgLabelArrayZ[count]);

            } else {

            }

            count++;
        }

        if (totalMatches == 0) {
            // if total matches = zero, then there were no matches and you need to print out a different statement
            // write the action of such event HERE
            outputTextArea.setText("0 Matches Found.\nNo Pokemon in our Database match your search criteria.\n"+"\n"+"Quick tip:\nMake sure you aren't limiting your searches too much. For example, a search for 'Tier A' Pokemon that are 'Not Fully Evolved' will always result in 0 matches because no non-evolved Pokemon could ever be strong enough to make it into Tier A.\n");
            imgPanel.add(imgLabelArrayZ[noMatchImage]);
        }

        else {   // this else statement gets triggered every time unless there are NO matches through the whole arrays search
            //totalNumMatches = totalMatches;
            matchesTotal = matchesTotal + totalMatches;
            outputTextArea.setText(matchesTotal+"\n"+matches+"\n"+pokeInfo);
            outputTextArea.setCaretPosition(scrollPaneStartAtTop);

        }
    }

    // this is just a clean up method to make reading the flow easier
    public void executeClassSearch(){
            pokemonSearchByClass4(bigMainArray);
    }

    // this is very important method which RESETS all variables right when a search is executed, thereby ensuring a
    // previous searches aren't displayed along with the new results.
    public void refreshSearchCriteria(){
            matchesTotal = "Matches Found: ";
            matches ="";
            pokeInfo = "";
            imgPanel.removeAll();
            newRank = rankComboBox.getSelectedIndex();
            newType = typeComboBox.getSelectedIndex();
            newHeight = heightComboBox.getSelectedIndex();
            newWeight = weightComboBox.getSelectedIndex();
            newEvolution = evolutionComboBox.getSelectedIndex();
    }



    // even though my itemStateChanged method is empty, I still need it to get the updated values for each of the ComboBoxes
    @Override
    public void itemStateChanged(ItemEvent e){

    }//end of itemStateChanged

    // this method checks to make sure the user input a String and not an int
    public void userInputTest(){

            imgPanel.removeAll();

            try {
                pokemonNameInput = searchByNameTextField.getText();
                int intTest = Integer.parseInt(pokemonNameInput);
                inputTestResults = false; }

            catch(Exception E) {
                inputTestResults = true; }

    }

    // if the user input is read as valid, it will be sent here where the input is entered into the pokemonSearchByName method,
    // where it either print out the specific match results or a 'no match' result
    public void validInput(){

            searchResult = pokemonSearchByName(pokemonNameInput, pokemonNameSearchArray);

            if (searchResult == noMatchFound){
                outputTextArea.setText("No Pokemon match found for '"+searchByNameTextField.getText()+"'!\nTry one on this list instead:\n" +
                        "1. Mewtwo\n2. Snorlax\n3. Pikachu\n4. Ditto\n5. Dragonite\n6. Gengar\n7. Alakazam\n" +
                        "8. Koffing\n9. Hypno\n10. Charizard\n11. Muk\n12. Hitmonchan\n13. Cloyster\n14. Machamp\n15. Zapdos");

                imgPanel.add(imgLabelArrayZ[noMatchImage]);
            }

            else {outputTextArea.setText("Match found!\nPokemon: "+pokemonNameSearchArray[searchResult]+"\n"+detailedPokestats[searchResult]);

                imgPanel.add(imgLabelArrayZ[searchResult]);
            }


    }

    // if the user input doesn't past the test, it will be sent here, skipping the whole search method and outputting an error message/img
    public void invalidInput(){

            outputTextArea.setText("Error: Whoops! Invalid input. Please enter only the name of the Pokemon and no numbers.\n"+"\n"+"Here's a list to get you started: \n1. Lugia\n2. Gengar\n3. Gyrados\n4. Blastoise\n5. Moltres\n6. Charizard\n7. Alakazam\n" +
                    "8. Venusaur\n9. Nidoking\n10. Pikachu\n11. Electabuzz\n12. Magneton\n13. Chansey\n14. Vileploom\n15. Magmar");

            imgPanel.add(imgLabelArrayZ[errorImage]);
    }


    // this method returns a value, (satisfying the 'return methods' requirement) which I use to figure out which pokemon was matched,
    // unless there was no match, in which case I know what value will be returned and have an if statement ready to
    // print out the 'no match' text/img. This searches the pokemonNameSearchArray for a String matching the input the
    // user entered. This also satisfies the 'method with parameters' requirement.
    public static int pokemonSearchByName(String pokemonNameEntered, String[] arr){

            int count = 0;
            int numberOfMatches = -1;

            while (count < arr.length && numberOfMatches == -1) {

                if (arr[count].equalsIgnoreCase(pokemonNameEntered)) {
                    numberOfMatches = count;
                }
                count++;
            }
            return numberOfMatches;
    }



    // method mainly organizing the steps of the BorderLayout setup so it's more easily read
    public void setupBorderLayoutRegions(){
            instantiateBorderPanels();
            setLayoutsOfBorderPanels();
            addPanelsToProperRegion();

    }

    // method instantiating all the Panels before they're given a specific layout
    public void instantiateBorderPanels(){
            northTitlePanel = new JPanel();
            westClassPanel = new JPanel();
            southSearchPanel = new JPanel();
            centerPanel = new JPanel();
            eastImgScrollPane = new JScrollPane();
            windowWidth = getWidth();
    }
            // im using GridLayouts and Boxlayouts on JPanels and JScrollPanes, all over a BorderLayout, satisfying the requirements for each.
            // I thought this setup made the most sense for what I needed and it ensured the program would still be functional/ visually accurate when
            // the user adjusts the window size.
    public void setLayoutsOfBorderPanels(){

            northTitlePanel.setLayout(new GridLayout(1,7));

            southPanelBoxLayout = new BoxLayout(southSearchPanel, BoxLayout.X_AXIS);
            southSearchPanel.setLayout(southPanelBoxLayout);

            centerPanelBoxLayout = new BoxLayout(centerPanel, BoxLayout.Y_AXIS);
            centerPanel.setLayout(centerPanelBoxLayout);
            centerPanel.setPreferredSize(new Dimension(windowWidth/4,windowHeight));

            westClassPanel.setLayout(new GridLayout(19,2));
            westClassPanel.setPreferredSize(new Dimension(windowWidth/4,windowHeight));

    }
    // method adding the JPanels and ScrollPanes to their proper Regions on the BorderLayout
    public void addPanelsToProperRegion(){
            add(northTitlePanel, BorderLayout.NORTH);
            add(westClassPanel, BorderLayout.WEST);
            add(southSearchPanel, BorderLayout.SOUTH);
            add(centerPanel, BorderLayout.CENTER);
            add(eastImgScrollPane, BorderLayout.EAST);
                extrasForCenterAndEastScrollPanes();
                setColorToRegions();
    }

    // Method adding extra components to the more complicated EAST and CENTER regions of the BorderLayout
    public void extrasForCenterAndEastScrollPanes(){

            //center scroll
            outputTextArea = new JTextArea(25,5);
            outputTextArea.setLineWrap(true);
            outputTextArea.setWrapStyleWord(true);
            outputTextArea.setText(""+outputInfo);
            outputTextArea.setBackground(RED5_COLOR);
            centerTextScrollPane = new JScrollPane(outputTextArea);
            centerTextScrollPane.setBackground(RED5_COLOR);

            imgPanel = new JPanel();
            imgPanelBoxLayout = new BoxLayout(imgPanel, BoxLayout.Y_AXIS);
            imgPanel.setLayout(imgPanelBoxLayout);

            eastImgScrollPane.setPreferredSize(new Dimension(eastScrollPaneSize,eastScrollPaneSize));
            eastImgScrollPane.setViewportView(imgPanel);

    }
    // method to set the background of each Borderlayout Region
    public void setColorToRegions(){

            northTitlePanel.setBackground(northPanelColor);
            southSearchPanel.setBackground(southPanelColor);
            eastImgScrollPane.setBackground(eastPanelColor);
            westClassPanel.setBackground(westPanelColor);
            centerPanel.setBackground(centerPanelColor);
            imgPanel.setBackground(RED3_COLOR);
    }

    public void loadImageZ(String name, int index) {
        String path = "images/"+name;
        File file = new File(path);
        try {
            pics[index] = ImageIO.read(file);
        } catch (IOException e){
            e.printStackTrace(); }
    }// end of loadImageZ




    // method adding components to the North Panel (3 images on a gridlayout)
    public void northComponents(){

            northTitlePanel.add(imgPack.decorativeImagesArr[topImg1]);
            imgPack.decorativeImagesArr[topImg1].setHorizontalAlignment(SwingConstants.LEFT);
            northTitlePanel.add(imgPack.decorativeImagesArr[titleImg]);
            imgPack.decorativeImagesArr[titleImg].setHorizontalAlignment(SwingConstants.CENTER);
            northTitlePanel.add(imgPack.decorativeImagesArr[topImg2]);
            imgPack.decorativeImagesArr[topImg2].setHorizontalAlignment(SwingConstants.RIGHT);

        }

    public void southComponents(){
            southSearchPanel.add(imgPack.decorativeImagesArr[bottomLeftImg]);
            southSearchPanel.add(Box.createRigidArea(new Dimension(170,20)));
            southSearchPanel.add(decorLabels.searchByNameLabel);
            Font font1 = new Font("Courier", Font.BOLD,15);
            decorLabels.searchByNameLabel.setFont(font1);
            decorLabels.searchByNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
            southSearchPanel.add(Box.createRigidArea(new Dimension(10,20)));
            southSearchPanel.add(searchByNameTextField);
            searchByNameTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
            southSearchPanel.add(Box.createRigidArea(new Dimension(10,10)));
            searchByNameTextField.setHorizontalAlignment(SwingConstants.LEFT);
            southSearchPanel.add(searchButton2);
            southSearchPanel.add(Box.createRigidArea(new Dimension(170,20)));
            southSearchPanel.add(imgPack.decorativeImagesArr[bottomRightImg]);

    }

    // Method adding the starting image to the East Region
    public void eastComponents(){
            //IMG PANEL (inside EAST scrollpane)
            imgPanel.add(imgPack.decorativeImagesArr[imageAtStart]);
    }

    // this method adds 2 pokemon images, a JTextArea and a JScrollPane to the center panel, which
    // is where the text search results get sent to. The scroll was needed because of how often the
    // search results were too big for the dimensions for the JTextArea. This satisfies the requirement
    // for the more JComponents like the JTextArea
    public void centerComponents(){
            centerPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
            centerPanel.add(Box.createRigidArea(new Dimension(1,10)));

            centerPanel.add(imgPack.decorativeImagesArr[centerImg1]);
            imgPack.decorativeImagesArr[centerImg1].setAlignmentX(Component.CENTER_ALIGNMENT);

            centerPanel.add(Box.createRigidArea(new Dimension(1,10)));
            centerPanel.add(centerTextScrollPane);
            centerTextScrollPane.setAlignmentX(CENTER_ALIGNMENT);
            centerPanel.add(Box.createRigidArea(new Dimension(1,10)));

            centerPanel.add(Box.createRigidArea(new Dimension(1,5)));
            centerPanel.add(imgPack.decorativeImagesArr[centerImg2]);
            imgPack.decorativeImagesArr[centerImg2].setAlignmentX(Component.CENTER_ALIGNMENT);
            centerPanel.add(Box.createRigidArea(new Dimension(1,5)));

    }

    // This method represents everything added to the westPanel. The rigidAreas were the
    //only way I could get the look that I wanted without sacrificing function. Here I'm
    // using 5 ComboBoxes, JLabels and a JButton, satisfying their requirements. The JComboBoxes
    // seemed to be the best and most adaptable option for the type of search I was doing.
    public void westComponents(){
            //WEST PANEL
            westClassPanel.add(Box.createRigidArea(new Dimension(20,1)));
            westClassPanel.add(Box.createRigidArea(new Dimension(20,1)));
            Font font2 = new Font("Courier", Font.BOLD,17);
            decorLabels.searchByClassLabel1.setFont(font2);
            decorLabels.searchByClassLabel1.setForeground(RED4_COLOR);
            westClassPanel.add(decorLabels.searchByClassLabel1);
            decorLabels.searchByClassLabel2.setFont(font2);
            decorLabels.searchByClassLabel2.setForeground(RED4_COLOR);
            westClassPanel.add(decorLabels.searchByClassLabel2);
            westClassPanel.add(Box.createRigidArea(new Dimension(20,2)));
            westClassPanel.add(Box.createRigidArea(new Dimension(20,2)));
            westClassPanel.add(decorLabels.rankLabel);
            decorLabels.rankLabel.setHorizontalAlignment(SwingConstants.CENTER);
            westClassPanel.add(rankComboBox);
            westClassPanel.add(Box.createRigidArea(new Dimension(20,2)));
            westClassPanel.add(Box.createRigidArea(new Dimension(20,2)));

            westClassPanel.add(decorLabels.typeLabel);
            decorLabels.typeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            westClassPanel.add(typeComboBox);
            westClassPanel.add(Box.createRigidArea(new Dimension(20,2)));
            westClassPanel.add(Box.createRigidArea(new Dimension(20,2)));

            westClassPanel.add(decorLabels.heightLabel);
            decorLabels.heightLabel.setHorizontalAlignment(SwingConstants.CENTER);
            westClassPanel.add(heightComboBox);
            westClassPanel.add(Box.createRigidArea(new Dimension(20,2)));
            westClassPanel.add(Box.createRigidArea(new Dimension(20,2)));

            westClassPanel.add(decorLabels.weightLabel);
            decorLabels.weightLabel.setHorizontalAlignment(SwingConstants.CENTER);
            westClassPanel.add(weightComboBox);
            westClassPanel.add(Box.createRigidArea(new Dimension(20,2)));
            westClassPanel.add(Box.createRigidArea(new Dimension(20,2)));

            westClassPanel.add(decorLabels.evolutionLabel);
            decorLabels.evolutionLabel.setHorizontalAlignment(SwingConstants.CENTER);
            westClassPanel.add(evolutionComboBox);
            westClassPanel.add(Box.createRigidArea(new Dimension(20,2)));
            westClassPanel.add(Box.createRigidArea(new Dimension(20,2)));

            westClassPanel.add(Box.createRigidArea(new Dimension(20,2)));
            westClassPanel.add(searchButton1);
    }


    }
    // end of FINAL class
