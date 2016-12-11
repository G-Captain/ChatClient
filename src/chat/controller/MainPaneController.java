package chat.controller;

import java.net.URL;
import java.util.ResourceBundle;

import chat.model.Contacts;
import chat.model.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainPaneController implements Initializable, ControlledScreen{

	/*private ObservableList<String> names = FXCollections.observableArrayList(
	          "Julia", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise");*/
	ScreensController myController;

    @FXML
    private BorderPane contactBorderPane;

    @FXML
    private ListView<String> contactListView;

    @FXML
    private MenuBar contactMenuBar;

    @FXML
    private MenuItem contactMenuItemLogIn;

    @FXML
    private MenuItem contactMenuItemRegister;

    @FXML
    private MenuItem contactMenuItemClose;

    @FXML
    private MenuItem contactMenuItemAddContact;

    @FXML
    private MenuItem contactMenuItemLogOut;

    private String chatAddr = "/chat/view/ConversationPane.fxml";
    private String addAddr = "/chat/view/AddContactPane.fxml";
    private ContextMenu cm;
    /*public Contacts getContacts() {
		return contacts;
	}

	public void setContacts(Contacts contacts) {
		contactListView.setItems(contacts.getNames());
	}

	public void removeContact(Contacts contacts, String unwanted) {
		//contacts.getNames().remove(unwanted);

		Iterator<String> namesIterator = contacts.getNames().iterator();
        while (namesIterator.hasNext()) {
            String name = namesIterator.next();
            if (name.equals(unwanted)) {
                namesIterator.remove();
            }
        }
	}
*/
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        /*System.out.println(usernameLabel.getText());
        usernameTextField.setText("Wpisz swoje imiê");*/
    	System.out.println("Init 2");
    	//System.out.println(getContacts());
        contactListView.setItems(Contacts.getNames());

        cm = new ContextMenu();
        MenuItem cmItem1 = new MenuItem("Remove Contact");

        cm.getItems().add(cmItem1);

       /*System.out.println("event");
        contactListView.addEventHandler(MouseEvent.MOUSE_CLICKED,
            new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent e) {
                    if (e.getButton() == MouseButton.SECONDARY){
                        System.out.println("jest!!!");
                    	if (contactListView.getSelectionModel().selectedItemProperty().getValue() != null){
        					cm.show(contactListView, e.getScreenX(), e.getScreenY());
        				}
                    }else if(e.getButton().equals(MouseButton.PRIMARY)){
                        if(e.getClickCount() == 2){
                        	openWindow(contactListView.getSelectionModel().selectedItemProperty().getValue(), chatAddr);
                        }else{
                        	cm.hide();
                        }
                    }
                }
        });*/

        //click on ContextMenuItem - "Remove Contact"
        /*cmItem1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                //getContacts().getNames().remove(contactListView.getSelectionModel().selectedItemProperty().getValue());
                //System.out.println(contactListView.getSelectionModel().selectedItemProperty().getValue());
                //removeContact(contacts, contactListView.getSelectionModel().selectedItemProperty().getValue());
            	Contacts.getNames().remove(contactListView.getSelectionModel().selectedItemProperty().getValue());

            }
        });*/

        /*contactMenuItemAddContact.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	System.out.println("wkliknalem");
            	//openWindow2("Add Contact", addAddr);
            	myController.setScreen(Main.screen4ID);
            }
        });*/

        /*contactMenuItemAddContact.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                         if(e.getButton().equals(MouseButton.PRIMARY)){
                        	System.out.println("wkliknalem");
                        	//openWindow(contactListView.getSelectionModel().selectedItemProperty().getValue(), chatAddr);
                        }
                    }
            });*/
        //names.add("jasiu");
    }

    @FXML
    void clickToAdd(ActionEvent event) {
    	System.out.println("event4");
    }

    @FXML
    void goToScreen4(ActionEvent event) {
    	System.out.println("event4");
    	myController.setScreen(Main.screen4ID);
    }

    @FXML
    void goToScreen3(MouseEvent event) {
    	System.out.println("event3");
    	myController.setScreen(Main.screen3ID);
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


   public void openWindow2(String contact, String addr){
    	final String appName = contact;

    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(addr));
            BorderPane root = loader.load();
            // Get the Controller from the FXMLLoader
            /*LoginController loginController = loader.getController();*/
            ConversationController controller = loader.getController();
            controller.setRecipient(contact);
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

