package chat.model;

import java.net.Socket;

import chat.controller.ScreensController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	private static Socket socket = null;
	public static String screen1ID = "LoginController";
    public static String screen1File = "/chat/view/LoginPane.fxml";
    public static String screen2ID = "MainPaneController";
    public static String screen2File = "/chat/view/MainPane.fxml";
    public static String screen3ID = "ConversationController";
    public static String screen3File = "/chat/view/ConversationPane.fxml";
    public static String screen4ID = "AddContactController";
    public static String screen4File = "/chat/view/AddContactPane.fxml";
    public Main() {
        System.out.println("Constructor");

    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("Start");
        final String appName = "Gadaj-Gadaj v0.1";
        try {
            /*BorderPane root = new BorderPane();
            Parent parent = (Parent) FXMLLoader.load(getClass().getResource(
                    "/chat/view/MainPane.fxml"));*/
        	//Contacts contacts = new Contacts();
            Contacts.setNames(FXCollections.observableArrayList(
        	          "Julia", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise"));
            System.out.println(Contacts.getNames());
/*
            Helpful helpful = new Helpful();
            helpful.openWindow(appName, screen1File);
*/

            ScreensController mainContainer = new ScreensController();
            mainContainer.openWindow(appName, screen1File);

/*
            ScreensController mainContainer = new ScreensController();
            mainContainer.loadScreen(Main.screen1ID, Main.screen1File);
            mainContainer.loadScreen(Main.screen2ID, Main.screen2File);
            mainContainer.loadScreen(Main.screen3ID, Main.screen3File);
            mainContainer.loadScreen(Main.screen4ID, Main.screen4File);

            mainContainer.setScreen(Main.screen1ID);

            Group root = new Group();
            root.getChildren().addAll(mainContainer);
            Scene scene = new Scene(root);
            primaryStage.setTitle(appName);
            primaryStage.setScene(scene);
            primaryStage.show();
*/


            /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/chat/view/LoginPane.fxml"));
            BorderPane root = loader.load();
            // Get the Controller from the FXMLLoader
            LoginController loginController = loader.getController();
            loginController.setStage(primaryStage);

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle(appName);
            primaryStage.setScene(scene);
            primaryStage.show();*/
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        System.out.println("Init");
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Stop");
        System.out.println(Contacts.getNames());
    }

	public static Socket getSocket() {
		return socket;
	}

	public static void setSocket(Socket socket) {
		Main.socket = socket;
	}
}
