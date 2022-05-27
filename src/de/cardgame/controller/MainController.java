package de.cardgame.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import de.cardgame.Main;
import de.cardgame.utils.FXMLUtil;
import de.cardgame.utils.Logger;
import de.cardgame.utils.FXMLUtil.MenuType;
import de.cardgame.utils.Helper;
import de.cardgame.utils.Logger.LogType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MainController implements Initializable {

	@FXML
	AnchorPane mainPane;

	@FXML
	AnchorPane headerPane;
	
		@FXML
		ImageView premmoneyIcon;
		
		@FXML
		Label premmoney;
		
		@FXML
		ImageView moneyIcon;
		
		@FXML
	    Label money;

	@FXML
	AnchorPane bodyPane;

	@FXML
	AnchorPane footerPane;

	@FXML
	JFXButton backBtn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Main.getFxmlUtil().changeBody(bodyPane, MenuType.LOGIN); // Loading Login into Main
		
		if(Main.getCurrentType() == MenuType.LOGIN) {
			headerPane.setVisible(false);
			headerPane.setDisable(true);
			
			footerPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0)");
		}
	}

	@FXML
	public void back(ActionEvent event) {
		switch (Main.getCurrentType()) {
		case MAIN:
			Helper.shutdown();
			break;
		case LOGIN:
			Helper.shutdown();
			break;
		case COLLECTION:
			Main.getFxmlUtil().changeBody(bodyPane, FXMLUtil.MenuType.MAIN);
			break;
		case DECK:
			break;
		case INVENTORY:
			Main.getFxmlUtil().changeBody(bodyPane, FXMLUtil.MenuType.COLLECTION);
			break;
		case MARKET:
			break;
		case SHOWCASE:
			break;
		default:
			break;
		}
		
		if (Main.getCurrentType() == MenuType.MAIN || Main.getCurrentType() == MenuType.LOGIN)
			backBtn.setText("EXIT");
		else
			backBtn.setText("BACK");
	}

	@FXML
	public void onBodyMove(MouseEvent event) {
		changeEventtest();
		
		if (backBtn.getText().equals("EXIT") && (Main.getCurrentType() != MenuType.MAIN && Main.getCurrentType() != MenuType.LOGIN))
			backBtn.setText("BACK");
		else if (backBtn.getText().equals("BACK") && (Main.getCurrentType() == MenuType.MAIN || Main.getCurrentType() == MenuType.LOGIN))
			backBtn.setText("EXIT");
		
		
		if(Main.getCurrentType() != MenuType.LOGIN) {
			headerPane.setVisible(true);
			headerPane.setDisable(false);
			
			footerPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.75)");
		}else if(Main.getCurrentType() == MenuType.LOGIN) {
			headerPane.setVisible(false);
			headerPane.setDisable(true);
			
			footerPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0)");
		}
		
		
	}
	
	//TODO
	public void changeEventtest() {
		money.setText(String.valueOf(Main.getUser().getMoney()));
		premmoney.setText(String.valueOf(Main.getUser().getPremmoney()));
	}


}
