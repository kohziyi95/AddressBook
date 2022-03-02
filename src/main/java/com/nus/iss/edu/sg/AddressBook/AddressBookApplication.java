package com.nus.iss.edu.sg.AddressBook;

import static com.nus.iss.edu.sg.AddressBook.util.IOUtil.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AddressBookApplication {
	private static Logger logger = Logger.getLogger(AddressBookApplication.class.getName());
	private static final String DATA_DIR = "dataDir";
	
	public static void main(String[] args) {
		
		SpringApplication app = new SpringApplication(AddressBookApplication.class);
		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);

		List<String> optsVal = appArgs.getOptionValues(DATA_DIR);

		if (optsVal != null) {
			createDir((String)optsVal.get(0));
		} else {
			logger.log(Level.WARNING, "No data directory is provided!");
			System.exit(1);
		}
		app.run(args);
	}

}
