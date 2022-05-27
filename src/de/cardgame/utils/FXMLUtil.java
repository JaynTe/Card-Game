package de.cardgame.utils;

import java.io.IOException;

import animatefx.animation.BounceInUp;
import de.cardgame.Main;
import de.cardgame.utils.Logger.LogType;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class FXMLUtil<T> {

	public void changeBody(AnchorPane bodyPane, MenuType menu) {
		
		if(menu != null)
			Main.setCurrentType(menu);

		AnchorPane newLoadedPane = null;
		try {
			switch (menu) {
			case MAIN:
				newLoadedPane = FXMLLoader.load(getClass().getResource("../fxml/menus/mainmenu.fxml"));
				Logger.log("Loading mainmenu.fxml", LogType.INFO);
				break;

			case COLLECTION:
				newLoadedPane = FXMLLoader.load(getClass().getResource("../fxml/menus/collectionmenu.fxml"));
				Logger.log("Loading collectionmenu.fxml", LogType.INFO);
				break;
			case INVENTORY:
				newLoadedPane = FXMLLoader.load(getClass().getResource("../fxml/menus/collection/inventorymenu.fxml"));
				Logger.log("Loading inventorymenu.fxml", LogType.INFO);
				break;
			case LOGIN:
				newLoadedPane = FXMLLoader.load(getClass().getResource("../fxml/menus/login.fxml"));
				Logger.log("Loading login.fxml", LogType.INFO);
				break;
			default:
				newLoadedPane = FXMLLoader.load(getClass().getResource("../fxml/menus/mainmenu.fxml"));
				Logger.log("Loading mainmenu.fxml", LogType.INFO);
				Main.setCurrentType(MenuType.MAIN);
				break;
			}
		} catch (IOException e) {
			Logger.log("Cant load the fxml file.", LogType.FATAL);
			e.printStackTrace();
		}

		if (newLoadedPane != null) {
			bodyPane.getChildren().clear();
			bodyPane.getChildren().add(newLoadedPane);
			if(menu != MenuType.LOGIN)
				new BounceInUp(newLoadedPane).play();
		}
	}

	public void showAlert(AnchorPane bodyPane, String msg, AlertType alertType) {
		
	}
	
	public void showAlert(AnchorPane bodyPane, String msg, AlertType alertType, Alert type) {
		AnchorPane alertPane = new AnchorPane();
		Label title = new Label("Alert!");
		
		title.setLayoutX(74);
		title.setLayoutY(96);
		title.setFont(new Font(32));
		
		switch(alertType) {
			case SUCCESS:
				title.setText("Success");
				title.setTextFill(new Color(0, 1, 0, 1));
				break;
			default:
				title.setText("Alert!");
				break;
		}
		
		alertPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.75);");
		
		alertPane.setLayoutX(730);
		alertPane.setLayoutY(166);
		
		alertPane.setPrefHeight(206);
		alertPane.setPrefWidth(460);
		
		alertPane.getChildren().add(title);
		
		bodyPane.getChildren().add(alertPane);
		
		
		System.out.println("test");
		
	}
	
	public enum MenuType {
		LOGIN, MAIN, COLLECTION, INVENTORY, MARKET, SHOWCASE, DECK;
	}
	
	
	public enum AlertType {
		INFO, ERROR, WARN, SUCCESS, NONE;
	}
	
	public enum Alert {
		OKAY, YESNO, 
	}

}
