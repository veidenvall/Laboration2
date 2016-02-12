//com.sun.javafx.scene.control.skin.TextInputControlSkin$15@255ceb

package recipesearch;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import se.chalmers.ait.dat215.lab2.Recipe;
import se.chalmers.ait.dat215.lab2.RecipeDatabase;
import se.chalmers.ait.dat215.lab2.SearchFilter;



public class RecipeSearchController implements Initializable {


    //------------Instance variables for search-------------------
    private String userSpecifiedCuisine = null;
    private String userSpecifiedMainIngredient = null;
    private String userSpecifiedDifficulty = null;
    private int userSpecifiedMaxCost = 0;
    private int userSpecifiedMaxTime = 0;

    private RecipeDatabase db;
    private List<Recipe> matches = new ArrayList<Recipe>();

    //-----------Controller----------------------------------------------
    @FXML private MenuBar menuBar;
    @FXML private StackPane stackPane;
    @FXML private AnchorPane anchorSearchView;
    @FXML private AnchorPane anchorListView;
    @FXML private AnchorPane anchorDetailedView;

    @FXML private Label pictureSearchMenuAdress;
    @FXML private Label pictureListViewAdress;
    @FXML private Label pictureDetailedViewAdress;

    //-----------SEARCHVIEW----------------------
    @FXML private Label titleLabel;
    @FXML private Label cuisineLabel;
    @FXML private Label mainIngredientLabel;
    @FXML private Label difficultyLabel;
    @FXML private Label maxTimeLabel;
    @FXML private Label maxCostLabel;

    @FXML private ChoiceBox cuisineChoiseBox;
    @FXML private ChoiceBox mainIngredientChoiceBox;
    @FXML private ChoiceBox difficultyChoiceBox;

    @FXML private Slider maxTimeSlider;
    @FXML private TextField maxCostTextField;

    @FXML private Button go;

    private ObservableList<String> cuisineList = FXCollections.observableArrayList("Default (alla)","Sverige", "Grekland","Indien","Asien","Afrika","Frankrike");
    private ObservableList<String> mainIngredieintList = FXCollections.observableArrayList("Default (alla)","Kött","Fisk","Kyckling","Vegetariskt");
    private ObservableList<String> difficultyList = FXCollections.observableArrayList("Default (alla)", "Lätt", "Mellan", "Svår");

    //------LIST VIEW-----------------------
    @FXML private Button backListView;
    @FXML private ImageView firstImage;
    @FXML private ImageView secondImage;
    @FXML private ImageView thirdImage;
    @FXML private ImageView fourthImage;
    @FXML private ImageView fifthImage;


    @FXML private Label firstMatchName;
    @FXML private Label secondMatchName;
    @FXML private Label thirdMatchName;
    @FXML private Label fourthMatchName;
    @FXML private Label fifthMatchName;
    @FXML private Label nbrOfPortionsFirst;
    @FXML private Label nbrOfPortionsSecond;
    @FXML private Label nbrOfPortionsThird;
    @FXML private Label nbrOfPortionsFourth;
    @FXML private Label nbrOfPortionsFifth;

    @FXML private TextArea firstMatchRecipeText;
    @FXML private TextArea secondMatchRecipeText;
    @FXML private TextArea thirdMatchRecipeText;
    @FXML private TextArea fourthMatchRecipeText;
    @FXML private TextArea fifthMatchRecipeText;

    @FXML private Label firstMatchSuccessRate;
    @FXML private Label secondMatchSuccessRate;
    @FXML private Label thirdMatchSuccessRate;
    @FXML private Label fourthMatchSuccessRate;
    @FXML private Label fifthMatchSuccessRate;


    //------DETAILED VIEW-----------------------
    @FXML private Button backDetailedView;
    @FXML private Button newSearch;

    @FXML private Label detailedNmbrServings;
    @FXML private Label detailedTime;
    @FXML private Label detailedDifficulty;
    @FXML private Label detailedCusine;
    @FXML private Label detailedCost;
    @FXML private Label detailedTitle;

    @FXML private TextArea detailedRecipeText;
    @FXML private ImageView detailedPicture;
    @FXML private TextArea detailedIngredients;
    @FXML private TextArea detailedPictureText;

    private static int tempVariable;

    //-----------------------------------------


    public RecipeSearchController(){

        db = RecipeDatabase.getSharedInstance();

    }

    //-------------View changes----------------------

    public void changeToListView(){

        this.anchorListView.toFront();

        //----SEARCH RESULTS-----
        matches = db.search(new SearchFilter(userSpecifiedDifficulty,userSpecifiedMaxTime,userSpecifiedCuisine,userSpecifiedMaxCost,userSpecifiedMainIngredient));

        //setts the images:
        firstImage.setImage(matches.get(0).getFXImage());
        secondImage.setImage(matches.get(1).getFXImage());
        thirdImage.setImage(matches.get(2).getFXImage());
        fourthImage.setImage(matches.get(3).getFXImage());
        fifthImage.setImage(matches.get(4).getFXImage());

        //sets the name labels:
        firstMatchName.setText(matches.get(0).getName());
        secondMatchName.setText(matches.get(1).getName());
        thirdMatchName.setText(matches.get(2).getName());
        fourthMatchName.setText(matches.get(3).getName());
        fifthMatchName.setText(matches.get(4).getName());

        //sets the number of servings labels:
        nbrOfPortionsFirst.setText("Antal Portioner: " + matches.get(0).getServings());
        nbrOfPortionsSecond.setText("Antal Portioner: " + matches.get(1).getServings());
        nbrOfPortionsThird.setText("Antal Portioner: " + matches.get(2).getServings());
        nbrOfPortionsFourth.setText("Antal Portioner: " + matches.get(3).getServings());
        nbrOfPortionsFifth.setText("Antal Portioner: " + matches.get(4).getServings());

        //sets the description of the recipe:
        firstMatchRecipeText.setText(matches.get(0).getDescription());
        secondMatchRecipeText.setText(matches.get(1).getDescription());
        thirdMatchRecipeText.setText(matches.get(2).getDescription());
        fourthMatchRecipeText.setText(matches.get(3).getDescription());
        fifthMatchRecipeText.setText(matches.get(4).getDescription());

        //Sest the label for the success rate of the match:
        firstMatchSuccessRate.setText(matches.get(0).getMatch() + "%");
        secondMatchSuccessRate.setText(matches.get(1).getMatch() + "%");
        thirdMatchSuccessRate.setText(matches.get(2).getMatch() + "%");
        fourthMatchSuccessRate.setText(matches.get(3).getMatch() + "%");
        fifthMatchSuccessRate.setText(matches.get(4).getMatch() + "%");
    }

    public void changeToDetailedView(){

        //must determine which recipe was chosen from the list:

        if(this.firstImage.isPressed()){
            tempVariable = 0;
        }
        if(this.secondImage.isPressed()){
            tempVariable = 1;
        }
        if(this.thirdImage.isPressed()){
            tempVariable = 2;
        }
        if(this.fourthImage.isPressed()){
            tempVariable = 3;
        }
        if(this.fifthImage.isPressed()){
            tempVariable = 4;
        }


        this.anchorDetailedView.toFront();

        //----DSIPLAY RESULT----

        //Sets the name of the Title:
        detailedTitle.setText(matches.get(tempVariable).getName());

        //Sets the other labels:
        detailedCost.setText("Pris: " + matches.get(tempVariable).getPrice() + ":-");
        detailedNmbrServings.setText("Antal Portioner: " + matches.get(tempVariable).getServings());
        detailedTime.setText("Tid: " + matches.get(tempVariable).getTime() +" minuter");
        detailedDifficulty.setText("Svårighetsgrad: " +matches.get(tempVariable).getDifficulty());
        detailedCusine.setText("Kök: " + matches.get(tempVariable).getCuisine());

        //sets the picture, picture text, description and list of ingredients:
        detailedPicture.setImage(matches.get(tempVariable).getFXImage());
        detailedPictureText.setText("En bild över hur " +matches.get(tempVariable).getName() + " kan se ut");
        detailedRecipeText.setText(matches.get(tempVariable).getDescription());
        detailedIngredients.setText("Ingredienser: " + matches.get(tempVariable).getIngredients());
    }

    public void changeToSerachView(){
        this.anchorSearchView.toFront();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //sets the adress labels for the pictures:
        pictureSearchMenuAdress.setText("Bild hämtad från https://cdn2.cdnme.se/3956893/9-3/test2_56b48287ddf2b36892ed41f3.jpg");
        pictureListViewAdress.setText("Bild hämtad från https://cdn2.cdnme.se/3956893/9-3/test234568_56ba1caaddf2b32ef2cd5116.jpg");
        pictureDetailedViewAdress.setText("Bild hämtad från https://cdn2.cdnme.se/3956893/9-3/woodenboard1_56ba1e31ddf2b32ec8c23262.jpg");
        cuisineChoiseBox.setItems(cuisineList);
        mainIngredientChoiceBox.setItems(mainIngredieintList);
        difficultyChoiceBox.setItems(difficultyList);
        this.anchorSearchView.toFront();


        //ChangeListener for MaxTime
        maxTimeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                userSpecifiedMaxTime = newValue.intValue();
            }
        });

        //ChangeListener for Cusine
        cuisineChoiseBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    int choiseBoxIndex = newValue.intValue();
                userSpecifiedCuisine = cuisineList.get(choiseBoxIndex);
            }
        });

        //ChangeListener for mainIngredient
        mainIngredientChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int choiseBoxIndex = newValue.intValue();
                userSpecifiedMainIngredient = mainIngredieintList.get(choiseBoxIndex);
            }
        });

        //ChangeListener for difficulty
        difficultyChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int choiseBoxIndex = newValue.intValue();
                userSpecifiedDifficulty = difficultyList.get(choiseBoxIndex);
            }
        });

        //ChangeListener for textfield
        maxCostTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                userSpecifiedMaxCost = Integer.parseInt(newValue);
            }
        });

    }

    @FXML 
    protected void openAboutActionPerformed(ActionEvent event) throws IOException{
    
        ResourceBundle bundle = java.util.ResourceBundle.getBundle("recipesearch/resources/RecipeSearch");
        Parent root = FXMLLoader.load(getClass().getResource("recipe_search_about.fxml"), bundle);
        Stage aboutStage = new Stage();
        aboutStage.setScene(new Scene(root));
        aboutStage.setTitle(bundle.getString("about.title.text"));
        aboutStage.initModality(Modality.APPLICATION_MODAL);
        aboutStage.setResizable(false);
        aboutStage.showAndWait();
    }
    
    @FXML 
    protected void closeApplicationActionPerformed(ActionEvent event) throws IOException{
        
        Stage addressBookStage = (Stage) menuBar.getScene().getWindow();
        addressBookStage.hide();
    }    
}