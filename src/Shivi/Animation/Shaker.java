/**
 * @Project ToDoApp
 * @Class Shaker
 * *
 * @author Shivangam_Soni
 * @since 20 Jan 2020 : 6:54 PM
 */
package Shivi.Animation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shaker {
    private TranslateTransition translateTransition;

    public Shaker(Node node) {
        translateTransition =
                new TranslateTransition(Duration.millis(50), node);
        translateTransition.setFromX(0f);
        translateTransition.setByX(10f);;
        translateTransition.setCycleCount(2);
        translateTransition.setAutoReverse(true);
        translateTransition.playFromStart();
}

    public Shaker(Node node1, Node node2) {
        translateTransition =
                new TranslateTransition(Duration.millis(50), node1);
        translateTransition.setFromX(0f);
        translateTransition.setByX(10f);;
        translateTransition.setCycleCount(2);
        translateTransition.setAutoReverse(true);
        translateTransition.playFromStart();
        translateTransition =
                new TranslateTransition(Duration.millis(50), node2);
        translateTransition.setFromX(0f);
        translateTransition.setByX(10f);;
        translateTransition.setCycleCount(2);
        translateTransition.setAutoReverse(true);
        translateTransition.playFromStart();
    }

    public void shake(Node node){
        translateTransition =
                new TranslateTransition(Duration.millis(50), node);
        translateTransition.setFromX(0f);
        translateTransition.setByX(10f);;
        translateTransition.setCycleCount(2);
        translateTransition.setAutoReverse(true);
        translateTransition.playFromStart();
    }
}
