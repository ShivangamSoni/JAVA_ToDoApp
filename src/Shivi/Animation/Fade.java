/**
 * @Project ToDoApp
 * @Class Fade
 * *
 * @author Shivangam_Soni
 * @since 21 Jan 2020 : 12:14 PM
 */
package Shivi.Animation;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Fade {
    private FadeTransition fadeTransition;

    public Fade(Node node) {
        fadeTransition = new FadeTransition(Duration.millis(1500), node);
        fadeTransition.setFromValue(0f);
        fadeTransition.setToValue(1f);
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(false);
        fadeTransition.play();
    }
}
