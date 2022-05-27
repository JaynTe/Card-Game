package de.cardgame.controller.menu;

import java.net.URL;
import java.util.ResourceBundle;

import de.cardgame.Main;
import de.cardgame.utils.FXMLUtil.MenuType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class CollectionController implements Initializable {
	
	@FXML
	AnchorPane bodyPane;
	
	@FXML
	AnchorPane inventoryPane;
	
	@FXML 
	AnchorPane showcasePane;
	
	@FXML
	AnchorPane deckPane;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void click(MouseEvent event) {
		if(event.getTarget() == inventoryPane) {
			Main.getFxmlUtil().changeBody(bodyPane, MenuType.INVENTORY);
		}
	}
	
	public void hover(MouseEvent event) {
		if(event.getTarget() == inventoryPane) {
			inventoryPane.setTranslateY(-5);
		}else if(event.getTarget() == showcasePane) {
			showcasePane.setTranslateY(-5);
		}else if(event.getTarget() == deckPane) {
			deckPane.setTranslateY(-5);
		}
	}
	
	public void leave(MouseEvent event) {
		inventoryPane.setTranslateY(0);
		showcasePane.setTranslateY(0);
		deckPane.setTranslateY(0);
	}


}
