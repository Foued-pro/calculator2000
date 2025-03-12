package bts.ciel.binding;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public TextField hauteur;
    public TextField largeur;
    public TextField perimetre;
    public TextField surface;
    public Slider slider_hauteur;
    public Slider slider_largeur;
    public Rectangle rectangle;
    public  double seuil_S = 5000;
    StringConverter sc = new DoubleStringConverter();
    public DoubleProperty surfaceProperty = new SimpleDoubleProperty();
    public DoubleProperty perimetreProperty = new SimpleDoubleProperty();
    public DoubleProperty largeurProperty = new SimpleDoubleProperty();
    public DoubleProperty hauteurProperty = new SimpleDoubleProperty();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        perimetreProperty.bind((largeurProperty.add(hauteurProperty)).multiply(2));
        surfaceProperty.bind((largeurProperty.multiply(hauteurProperty)));

        perimetre.textProperty().bind(perimetreProperty.asString());
        surface.textProperty().bind(surfaceProperty.asString());

        Bindings.bindBidirectional(hauteur.textProperty(), hauteurProperty, sc);
        Bindings.bindBidirectional(largeur.textProperty(), largeurProperty, sc);


        //liaison slider et le txt
        Bindings.bindBidirectional(hauteur.textProperty(), slider_hauteur.valueProperty(), sc);
        Bindings.bindBidirectional(largeur.textProperty(), slider_largeur.valueProperty(), sc);


        // slider enable or not
        slider_largeur.visibleProperty().bind(Bindings.when(largeurProperty.greaterThan(100)).then(false).otherwise(true)) ;
        slider_hauteur.visibleProperty().bind(Bindings.when(hauteurProperty.greaterThan(100)).then(false).otherwise(true)) ;


        //rectangle
        rectangle.widthProperty().bind(largeurProperty);
        rectangle.heightProperty().bind(hauteurProperty);

        //couleur
        surface.backgroundProperty().bind(Bindings.when(surfaceProperty.greaterThan(seuil_S)).then(new Background(new BackgroundFill(Color.RED,null,null))).
                otherwise(new Background(new BackgroundFill(Color.AQUA,null,null))));
        perimetre.backgroundProperty().bind(Bindings.when(perimetreProperty.greaterThan(seuil_S)).
                then(new Background(new BackgroundFill(Color.RED,null,null))).otherwise(new Background(new BackgroundFill(Color.AQUA,null,null))));

        rectangle.fillProperty().bind(Bindings.when(surfaceProperty.greaterThan(seuil_S))
                .then(Color.RED).
                otherwise(Color.AQUA));
    }
}