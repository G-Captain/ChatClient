package chat.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import chat.model.Contacts;
import chat.model.Main;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class LoginController implements Initializable, ControlledScreen{

	ScreensController myController;

	BufferedReader in;
    PrintWriter out;
    private DataInputStream  streamIn  =  null;
    private DataOutputStream streamOut = null;
    private String mainPaneAddr = "/chat/view/MainPane.fxml";


    @FXML
    private BorderPane conversationPane;

    @FXML
    private Button loginRegisterButton;

	@FXML
    private PasswordField loginPassword;

    @FXML
    private Button loginLogInButton;

    @FXML
    private Label loginLabel;

    @FXML
    private TextField loginTextField;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	loginLabel.setText("");
    	System.out.println("Init 1");

    }

    @FXML
    void goToScreen2(ActionEvent event) {
    	System.out.println("go to screen2");
    	//myController.setScreen(Main.screen2ID);
    	myController.closeWindow(Main.screen2ID);
    	myController.openWindow("GG",Main.screen2ID);
    }

    @FXML
    void goToScreen23(ActionEvent event) {
    	System.out.println("go to screen2");
    	if(!loginTextField.getText().equals("") && !loginPassword.getText().equals("")){
    		String serverAddress = "192.168.1.103";//getServerAddress();
            Socket socket;
			try {
				Main.setSocket(new Socket(serverAddress, 9003));
            /*in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);*/

				streamIn = new DataInputStream(new
                        BufferedInputStream(Main.getSocket().getInputStream()));
				streamOut = new DataOutputStream(new
                        BufferedOutputStream(Main.getSocket().getOutputStream()));


            // Process all messages from server, according to the protocol.
            while (true) {
                //String line = in.readLine();
            	String line = streamIn.readUTF();
                if (line.startsWith("CONNECTED")) {
                	System.out.println("good");
                    /*out.println("NAME");
                    out.println(loginTextField.getText().trim());
                    out.println("PASS");
                    out.println(loginPassword.getText().trim());*/
                    streamOut.writeUTF("NAME");
                    streamOut.flush();
                    streamOut.writeUTF(loginTextField.getText().trim());
                    streamOut.flush();
                    streamOut.writeUTF("PASS");
                    streamOut.flush();
                    streamOut.writeUTF(loginPassword.getText().trim());
                    streamOut.flush();
                } else if (line.startsWith("AUTHORISED")) {
                	loginLabel.setText("Hooray!!!");
            		loginLabel.setTextFill(Color.web("#008000"));
            		//getStage().close();
            		//openWindow2("Gadaj-Gadaj", mainPaneAddr);
            		myController.setScreen(Main.screen2ID);

            		//socket.close();
                } else if (line.startsWith("WRONG")) {
                	loginLabel.setText("Wrong login or password!");
            		loginLabel.setTextFill(Color.web("#FF0000"));
            		//Main.getSocket().close();
                }
            }

			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}else{
    		loginLabel.setText("Enter username and password!");
    		loginLabel.setTextFill(Color.web("#FF0000"));
    	}
    }

    @Override
    public void setScreenParent(ScreensController screenParent){
    	myController = screenParent;
    }

    public void openWindow(String contact, String addr){
    	final String appName = contact;
    	Parent root;
    	try {
            root = FXMLLoader.load(getClass().getResource(
                    addr));
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
    public void openWindow2(String title, String addr){
    	Stage stage = new Stage();
    	final String appName = title;
	    try {
	        /*BorderPane root = new BorderPane();
	        Parent parent = (Parent) FXMLLoader.load(getClass().getResource(
	                "/chat/view/MainPane.fxml"));*/

	        FXMLLoader loader = new FXMLLoader(getClass().getResource(addr));
	        BorderPane root = loader.load();
	        // Get the Controller from the FXMLLoader
	        /*LoginController loginController = loader.getController();
	        loginController.setContacts(contacts);*/

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