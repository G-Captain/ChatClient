package chat.controller;

import java.net.URL;
import java.util.ResourceBundle;

import chat.model.Contacts;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class AddContactController implements Initializable, ControlledScreen{

	ScreensController myController;

    @FXML
    private TextField addContactTextField;

    @FXML
    private Label addContactLabel1;

    @FXML
    private Label addContactLabel2;

    @FXML
    private Button addContactButton;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        /*System.out.println(usernameLabel.getText());
        usernameTextField.setText("Wpisz swoje imiê");*/
    	//System.out.println("inicjuje2");
        //names.add("jasiu");
    	addContactLabel2.setText("");
    	/*addContactButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	if(!addContactTextField.getText().equals("")){
            		if(!Contacts.exists(addContactTextField.getText().trim())){
            			Contacts.getNames().add(addContactTextField.getText().trim());
            			addContactLabel2.setText("Contact added successfully!");
            			addContactLabel2.setTextFill(Color.web("#008000"));
            		}else{
            			addContactLabel2.setText("Contact already on the list!");
            			addContactLabel2.setTextFill(Color.web("#FF0000"));
            		}
            	}else{
            		addContactLabel2.setText("");
            	}
            }
        });*/
    }

    @FXML
    void addContact(ActionEvent event) {
    	if(!addContactTextField.getText().equals("")){
    		if(!Contacts.exists(addContactTextField.getText().trim())){
    			Contacts.getNames().add(addContactTextField.getText().trim());
    			addContactLabel2.setText("Contact added successfully!");
    			addContactLabel2.setTextFill(Color.web("#008000"));
    		}else{
    			addContactLabel2.setText("Contact already on the list!");
    			addContactLabel2.setTextFill(Color.web("#FF0000"));
    		}
    	}else{
    		addContactLabel2.setText("");
    	}
    }

    @Override
    public void setScreenParent(ScreensController screenParent){
    	myController = screenParent;
    }
}
