package chat.controller;

import java.util.HashMap;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ScreensController extends StackPane{
	private HashMap<String, Node> screens = new HashMap<>();
	private Stage stage;

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	public HashMap<String, Node> getScreens() {
		return screens;
	}

	public void setScreens(HashMap<String, Node> screens) {
		this.screens = screens;
	}



	public void addScreen(String name, Node screen) {
	       screens.put(name, screen);
	   }

	//Returns the Node with the appropriate name
    public Node getScreen(String name) {
        return screens.get(name);
    }

	public boolean loadScreen(String name, String resource) {
	     try {
	       FXMLLoader myLoader = new
	               FXMLLoader(getClass().getResource(resource));
	       Parent loadScreen = (Parent) myLoader.load();
	       ControlledScreen myScreenControler =
	              ((ControlledScreen) myLoader.getController());
	       myScreenControler.setScreenParent(this);
	       addScreen(name, loadScreen);
	       return true;
	     }catch(Exception e) {
	       System.out.println(e.getMessage());
	       return false;
	     }
	   }

	public boolean setScreen(final String name) {
        if (screens.get(name) != null) {   //screen loaded
            final DoubleProperty opacity = opacityProperty();

            if (!getChildren().isEmpty()) {    //if there is more than one screen
                Timeline fade = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                        new KeyFrame(new Duration(1000), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        getChildren().remove(0);                    //remove the displayed screen
                        getChildren().add(0, screens.get(name));     //add the screen
                        Timeline fadeIn = new Timeline(
                                new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                new KeyFrame(new Duration(800), new KeyValue(opacity, 1.0)));
                        fadeIn.play();
                    }
                }, new KeyValue(opacity, 0.0)));
                fade.play();

            } else {
                setOpacity(0.0);
                getChildren().add(screens.get(name));       //no one else been displayed, then just show
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(2500), new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
            return true;
        } else {
            System.out.println("screen hasn't been loaded!!! \n");
            return false;
        }
    }

	public boolean unloadScreen(String name) {
        if (screens.remove(name) == null) {
            System.out.println("Screen didn't exist");
            return false;
        } else {
            return true;
        }
    }

	public void openWindow(String title, String addr){
    	final String appName = title;

    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(addr));
            BorderPane root = loader.load();
            // Get the Controller from the FXMLLoader
            /*LoginController loginController = loader.getController();*/
            //ConversationController controller = loader.getController();
            //controller.setRecipient(contact);
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            stage.setTitle(appName);
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
