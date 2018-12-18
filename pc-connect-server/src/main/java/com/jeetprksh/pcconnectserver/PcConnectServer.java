package com.jeetprksh.pcconnectserver;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/*
 * @author Jeet Prakash
 * */
@SpringBootApplication
public class PcConnectServer {

	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(PcConnectServer.class, args);
	}

	public static void start(String[] args) {
		context = SpringApplication.run(PcConnectServer.class, args);
	}

	public static void restart() {
		ApplicationArguments args = context.getBean(ApplicationArguments.class);

		Thread thread = new Thread(() -> {
			context.close();
			context = SpringApplication.run(PcConnectServer.class, args.getSourceArgs());
		});

		thread.setDaemon(false);
		thread.start();
	}

}
