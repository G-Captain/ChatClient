package chat.model;

import javafx.collections.ObservableList;

public class Contacts {
	private static ObservableList<String> names;

	public static ObservableList<String> getNames() {
		return Contacts.names;
	}

	public static void setNames(ObservableList<String> names) {
		Contacts.names = names;
	}

	public static boolean exists(String name){
		for (String string : names) {
			if(string.equals(name)){
				return true;
			};
		}
		return false;
	}

}
