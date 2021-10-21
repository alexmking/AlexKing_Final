import javax.swing.*;

public class LabelAdder {


    JLabel searchByNameLabel, searchByClassLabel1,searchByClassLabel2,
            rankLabel, typeLabel, heightLabel, weightLabel, evolutionLabel;


    // I made this class with the same reasoning as the others: get all the background code
    // away from the more complicated Search/Array code. There was no need to having all these
    //labels cluttering up space in the main file.
    public LabelAdder() {
    setupLabels();
    }


    public void setupLabels(){
        searchByNameLabel = new JLabel(" Search By Name:");

        searchByClassLabel1 = new JLabel(" Search By");
        searchByClassLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
        searchByClassLabel2 = new JLabel(" Class:");
        searchByClassLabel2.setHorizontalAlignment(SwingConstants.LEFT);

        rankLabel = new JLabel("Rank:");
        typeLabel = new JLabel("Type:");
        heightLabel = new JLabel("Height (ft):");
        weightLabel = new JLabel("Weight (lbs):");
        evolutionLabel = new JLabel("Evolution:");


    }







}
