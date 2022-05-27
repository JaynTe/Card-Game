package de.cardgame.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

	public static void log(String msg, LogType logTyp) {
		String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
		switch (logTyp) {
		case NONE:
			System.out.println("[" + date + "] " + msg);
			break;

		case WARN:
			System.out.println("[" + date + "] WARN > " + msg);
			break;

		case ERROR:
			System.out.println("[" + date + "] ERROR > " + msg);
			break;

		case FATAL:
			System.out.println("[" + date + "] FATAL > " + msg);
			break;
		case ALL:
			System.out.println("[" + date + "] ALL > " + msg);
			break;
		case DEBUG:
			System.out.println("[" + date + "] DEBUG > " + msg);
		case INFO:
			System.out.println("[" + date + "] INFO > " + msg);
			break;
		case NOTICE:
			System.out.println("[" + date + "] NOTICE > " + msg);
			break;
		default:
			System.out.println("[" + date + "] " + msg);
			break;
		}
	}

	public enum LogType {
		ALL, INFO, NOTICE, DEBUG, WARN, ERROR, FATAL, NONE;
	}

}
