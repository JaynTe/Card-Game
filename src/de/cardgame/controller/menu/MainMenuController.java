package de.cardgame.controller.menu;

import java.net.URL;
import java.util.ResourceBundle;

import de.cardgame.Main;
import de.cardgame.utils.FXMLUtil.MenuType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MainMenuController implements Initializable {
	
	
	@FXML 
	AnchorPane bodyPane;
	
	@FXML
	AnchorPane playPane;
	
	@FXML
	AnchorPane collectionPane;
	
	@FXML
	AnchorPane marketPane;
	
	@FXML
	Label www;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	//	www.setFont(Font.font("Comfortaa", 120));
	}
	
	public void hover(MouseEvent event) {
		if(event.getTarget() == collectionPane) {
			collectionPane.setTranslateY(-5);
		}else if(event.getTarget() == playPane) {
			playPane.setTranslateY(-5);
		}else if(event.getTarget() == marketPane) {
			marketPane.setTranslateY(-5);
		}
	}
	
	public void leave(MouseEvent event) {
		playPane.setTranslateY(0);
		collectionPane.setTranslateY(0);
		marketPane.setTranslateY(0);
	}
	
	public void click(MouseEvent event) {
		if(event.getTarget() == collectionPane) {
			Main.getFxmlUtil().changeBody(bodyPane, MenuType.COLLECTION);
		}
	}

}
