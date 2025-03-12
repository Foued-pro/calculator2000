package bts.ciel.binding;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class RectangleProperty {
    // Definit une variable pour stocker les proprietés
    private DoubleProperty hauteur = new SimpleDoubleProperty();
    private DoubleProperty largeur = new SimpleDoubleProperty();
    private DoubleProperty perimetre = new SimpleDoubleProperty();
    private DoubleProperty surface = new SimpleDoubleProperty();

    // Définit un getter pour la propriété hauteur elle-même

    public DoubleProperty hauteurProperty() {
        return hauteur;
    }
    // Définit un getter pour la propriété largeur elle-même

    public DoubleProperty largeurProperty() {
        return largeur;
    }

    // Définit un getter pour la propriété perimetre elle meme
    public DoubleProperty perimetreProperty() {
        perimetre.bind((largeur.add(hauteur)).multiply(2));
        return perimetre;
    }
    // Définit un getter pour la propriété surface elle-même

    public DoubleProperty surfaceProperty() {
        surface.bind(largeur.multiply(hauteur));
        return surface;
    }


}
