package de.cardgame;

import de.cardgame.utils.FXMLUtil;
import de.cardgame.utils.Logger;
import de.cardgame.utils.UserInfo;
import de.cardgame.utils.Logger.LogType;
import de.cardgame.utils.netty.NettyClient;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

	private static final String APPLICATION_NAME = "Cardname";
	private static final double APPLICATION_VERSION = 0.1;
	
	private static final boolean devbuild = true;

	
	private static final FXMLUtil fxmlUtil = new FXMLUtil();
	private static FXMLUtil.MenuType currentType;

	
	private static final NettyClient nc = new NettyClient("localhost", 8000);

	private static final UserInfo user = new UserInfo();

	public static void main(String[] args) {
		Font.loadFont(Main.class.getResource("Comfortaa.ttf").toExternalForm(), 60);

		new Thread(() -> {
			try {
				nc.run();
			} catch (Exception e) {
				Logger.log(e.getMessage(), LogType.FATAL);
			}
		}, "NettyClient").start();

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("fxml/main.fxml"));

		primaryStage.setTitle(APPLICATION_NAME + " v" + APPLICATION_VERSION + (devbuild ? " (dev build)" : ""));

		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);

		primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitHint("");
		primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

		primaryStage.show();
	}

	public static UserInfo getUser() {
		return user;
	}
	
	public static NettyClient getNc() {
		return nc;
	}

	public static FXMLUtil getFxmlUtil() {
		return fxmlUtil;
	}

	public static String getApplicationName() {
		return APPLICATION_NAME;
	}

	public static double getApplicationVersion() {
		return APPLICATION_VERSION;
	}

	public static FXMLUtil.MenuType getCurrentType() {
		return currentType;
	}

	public static void setCurrentType(FXMLUtil.MenuType currentType) {
		Main.currentType = currentType;
	}

	public static boolean isDevbuild() {
		return devbuild;
	}

}
