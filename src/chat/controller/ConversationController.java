package chat.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import chat.model.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.input.KeyEvent;

public class ConversationController implements Initializable, ControlledScreen{

	ScreensController myController;
	private DataInputStream  streamIn  =  null;
    private DataOutputStream streamOut = null;
    private String recipient;

	    @FXML
	    private BorderPane conversationPane;

	 	@FXML
	    private Label conversationLabel;

	    @FXML
	    private Button conversationButton;

	    @FXML
	    private TextArea conversationTextField;

	    @FXML
	    private TextArea conversationTextArea;

	    @FXML
	    public void onEnter(ActionEvent ae){
	    	addMyText();
	    }

	    public void setRecipient(String recipient){
	        this.recipient = recipient;
	    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        /*System.out.println(usernameLabel.getText());
        usernameTextField.setText("Wpisz swoje imiê");*/
    	System.out.println("Init 3");
    	/*try {
			streamIn = new DataInputStream(new
			        BufferedInputStream(Main.getSocket().getInputStream()));
			streamOut = new DataOutputStream(new
	                BufferedOutputStream(Main.getSocket().getOutputStream()));
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        //names.add("jasiu");
    	/*conversationButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	addMyText();
            }
        });*/
    }

    @FXML
    void sendText(ActionEvent event) {
    	try {
			streamIn = new DataInputStream(new
			        BufferedInputStream(Main.getSocket().getInputStream()));
			streamOut = new DataOutputStream(new
	                BufferedOutputStream(Main.getSocket().getOutputStream()));
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
    	}
    	String fieldText = conversationTextField.getText();
    	if(!fieldText.equals("")){
    		conversationTextArea.appendText("ME: " + fieldText + "\n");
    		try {
    			System.out.println(fieldText);
    			streamOut.writeUTF("MSG");
            	streamOut.flush();
            	streamOut.writeUTF(recipient);
            	streamOut.flush();
            	streamOut.writeUTF(fieldText);
            	streamOut.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	conversationTextField.clear();
    	}
    }

    @Override
    public void setScreenParent(ScreensController screenParent){
    	myController = screenParent;
    }

    public void addMyText(){
    	String fieldText = conversationTextField.getText();
    	if(!fieldText.equals("")){
    		conversationTextArea.appendText("ME: " + fieldText + "\n");
    		try {
    			System.out.println(fieldText);
    			streamOut.writeUTF("MSG");
            	streamOut.flush();
            	streamOut.writeUTF(recipient);
            	streamOut.flush();
            	streamOut.writeUTF(fieldText);
            	streamOut.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	conversationTextField.clear();
    	}
    }
}
