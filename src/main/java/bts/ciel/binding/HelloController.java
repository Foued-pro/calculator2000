package bts.ciel.binding;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public TextField hauteur;
    public TextField largeur;
    public TextField perimetre;
    public TextField surface;
    public Slider slider_Hauteur;
    public Slider slider_Largeur;
    public Rectangle rectangle;
    final  Double SEUIL_S = 5000.0;
    final  Double SEUIL = 100.0;
    public Rectangle rectangle1;
    public RadioButton cercleB;
    public RadioButton rectangleB;
    public ToggleGroup boutton;
    RectangleProperty monRectangle = new RectangleProperty();


    StringConverter sc = new DoubleStringConverter(){
        @Override
        public String toString(Double value) {
            DecimalFormat df = new DecimalFormat("#.## m");
            return df.format(value);
        }
        @Override
        public Double fromString(String value) {
            value = value.replace(" m","");
            value = value.replace(",",".");
            return Double.valueOf(value);
        }
    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        monRectangle.perimetreProperty().bind((monRectangle.largeurProperty()).add(monRectangle.hauteurProperty()).multiply(2));
        monRectangle.surfaceProperty().bind((monRectangle.largeurProperty().multiply(monRectangle.hauteurProperty())));

        perimetre.textProperty().bind(monRectangle.perimetreProperty().asString("%.2f m"));
        surface.textProperty().bind(monRectangle.surfaceProperty().asString("%.2f m²"));

        Bindings.bindBidirectional(hauteur.textProperty(), monRectangle.hauteurProperty(), sc);
        Bindings.bindBidirectional(largeur.textProperty(), monRectangle.largeurProperty(), sc);


        //liaison slider et le txt
        Bindings.bindBidirectional(hauteur.textProperty(), slider_Hauteur.valueProperty(), sc);
        Bindings.bindBidirectional(largeur.textProperty(), slider_Largeur.valueProperty(), sc);


        // slider enable or not
        slider_Largeur.visibleProperty().bind(Bindings.when(monRectangle.largeurProperty().greaterThan(SEUIL)).then(false).otherwise(true)) ;
        slider_Hauteur.visibleProperty().bind(Bindings.when(monRectangle.hauteurProperty().greaterThan(SEUIL)).then(false).otherwise(true)) ;


        //rectangle
        rectangle.widthProperty().bind(slider_Largeur.valueProperty());
        rectangle.heightProperty().bind(slider_Hauteur.valueProperty());

        //rectangle1
        rectangle1.widthProperty().bind(slider_Largeur.valueProperty());
        rectangle1.heightProperty().bind(slider_Hauteur.valueProperty());

        //couleur
        surface.backgroundProperty().bind(Bindings.when(monRectangle.surfaceProperty().greaterThan(SEUIL_S))
                .then(new Background(new BackgroundFill(Color.RED,null,null)))
                        .otherwise(new Background(new BackgroundFill(Color.AQUA,null,null))));
        perimetre.backgroundProperty().bind(Bindings.when(monRectangle.perimetreProperty().greaterThan(SEUIL_S)).
                then(new Background(new BackgroundFill(Color.RED,null,null))).otherwise(new Background(new BackgroundFill(Color.AQUA,null,null))));

        rectangle1.fillProperty().bind(Bindings.when(monRectangle.surfaceProperty().greaterThan(SEUIL_S))
                .then(Color.RED).
                otherwise(Color.AQUA));
    }


}