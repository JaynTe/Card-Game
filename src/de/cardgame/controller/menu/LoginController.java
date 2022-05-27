package de.cardgame.controller.menu;

import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONObject;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import de.cardgame.Main;
import de.cardgame.utils.WebRequest;
import de.cardgame.utils.netty.Events;
import de.cardgame.utils.FXMLUtil.Alert;
import de.cardgame.utils.FXMLUtil.AlertType;
import de.cardgame.utils.FXMLUtil.MenuType;
import de.cardgame.utils.Logger;
import de.cardgame.utils.Logger.LogType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class LoginController implements Initializable {
	
	@FXML
	AnchorPane bodyPane;
	
	@FXML
	AnchorPane loginPane;
	
	@FXML
	JFXTextField emailBox;
	
	@FXML
	JFXPasswordField passwordBox;
	
	@FXML
	JFXCheckBox remeberBox;
	
	@FXML
	JFXButton loginBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
	public void login(ActionEvent event) {
		emailBox.setDisable(true);
		passwordBox.setDisable(true);
		
		if(emailBox.getLength() == 0 || passwordBox.getLength() == 0) {
			Logger.log("Emailbox and/or Passwordbox is empty.", LogType.ERROR); //Call a erroralet
			Main.getFxmlUtil().showAlert(bodyPane, "lel", AlertType.SUCCESS, Alert.OKAY);
			emailBox.setDisable(false);
			passwordBox.setDisable(false);
			return;
		}
		
		JSONObject jb = WebRequest.checkLoginCredentials(emailBox.getText(), passwordBox.getText());
		
		if(jb.getBoolean("error")) {
			Logger.log(jb.getString("errormsg"), LogType.ERROR); //Call a erroralet
			emailBox.setDisable(false);
			passwordBox.setDisable(false);
			return;
		}
		
		Main.getUser().setEmail(emailBox.getText());
		Main.getUser().setUserid(jb.getInt("userid"));
		Main.getUser().setSecretkey(jb.getString("secretkey"));
		
		Main.getNc().getRequests().add(new JSONObject().put("event", Events.CLIENT_VERIFYEVENT.getName())
				.put("userid", Main.getUser().getUserid())
				.put("secretkey", Main.getUser().getSecretkey())); //Super shitty lel
		
		Main.getFxmlUtil().changeBody(bodyPane, MenuType.MAIN);
		
		emailBox.setDisable(false);
		passwordBox.setDisable(false);
	}

}
