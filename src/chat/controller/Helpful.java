
package chat.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;


public class Helpful {

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

	/*public boolean unloadScreen(String name) {
        if (screens.remove(name) == null) {
            System.out.println("Screen didn't exist");
            return false;
        } else {
            return true;
        }
    }*/
}

