package de.cardgame.controller.menu;

import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONObject;

import animatefx.animation.FadeInUp;
import animatefx.animation.FadeInUpCustom;
import animatefx.animation.FadeOutDown;
import de.cardgame.utils.WebRequest;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class InventoryController implements Initializable {
	
	int userid = 1;
	
	
	@FXML
	AnchorPane mainPane;
	
	@FXML
	Rectangle testhoverrect;

	@FXML
	Label testhoverlabel;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		JSONObject cards = WebRequest.getInventoryFromUserID(userid, null);
		
		if(cards.has("error")) {
			System.out.println(cards.getString("errormsg"));
		}else {
			System.out.println(cards.getJSONArray("cards").length());
		}
	}
	
	
	
	
	
	
	@FXML
	public void hover(MouseEvent event) {
	   if(MouseEvent.MOUSE_ENTERED == event.getEventType()) {
		   testhoverrect.setVisible(true);
		   testhoverlabel.setVisible(true);
		   new FadeInUpCustom(testhoverrect).play();
		   new FadeInUp(testhoverlabel).play();
	   }else {
		   new FadeOutDown(testhoverrect).play();
		   new FadeOutDown(testhoverlabel).play();
	   }
	 }

}
