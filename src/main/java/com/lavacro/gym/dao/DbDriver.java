package com.lavacro.gym.dao;

import com.lavacro.gym.properties.Database;
import com.zaxxer.hikari.HikariDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class DbDriver {
	private static final Logger logger = LoggerFactory.getLogger(DbDriver.class);

	private final Database database;

	@Autowired
	public DbDriver(Database database) {
		this.database = database;
	}

	@Configuration
	public class DbConfig {
		private String username;
		private String password;

		@Bean
		DataSource dataSource() {
			username = database.getUsername();
			password = database.getPassword();
			if(username == null || password == null) {
				getUserAndPassword();
			}

			HikariDataSource hds = DataSourceBuilder
					.create()
					.driverClassName(database.getDriver())
					.url(database.getUrl())
					.username(username)
					.password(password)
					.type(HikariDataSource.class)
					.build();
			hds.setConnectionTestQuery("SELECT 1");
			hds.setIdleTimeout(30000);
			hds.setMinimumIdle(3);
			hds.setMaximumPoolSize(10);
			logger.info("Received database bean");
			return hds;
		}

		private void getUserAndPassword() {
			String config_file = System.getenv("SECRETS_CONFIG_FILE");
			Map<String,String> vars = new HashMap<>();
			try (
				BufferedReader br = new BufferedReader(new FileReader(config_file))
			) {
				br.lines().forEach( line -> {
					String[] params = line.split("=");
					if(params.length == 2) {
						vars.put(params[0], params[1]);
					}
				});
			} catch(IOException e) {
				logger.error("Error getting secrets: {}", e.getMessage());
			}
			if(vars.containsKey("user")) {
				username = vars.get("user");
			}
			if(vars.containsKey("password")) {
				password = vars.get("password");
			}
		}
	}
}
