package com.kuehnenagel.coinclient;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CoinClientApplication {

    private static Logger LOG = LoggerFactory.getLogger(CoinClientApplication.class);
    @Autowired
    private CoinConsole console;
    @Autowired
    private ApplicationContext appContext;

	public static void main(String[] args) {
        LOG.info("Starting the Application.");
		SpringApplication.run(CoinClientApplication.class, args);
	}

	protected void shutdown(){
        int exitCode = SpringApplication.exit(appContext, new ExitCodeGenerator() {
            @Override
            public int getExitCode() {
                // return the error code
                return 0;
            }
        });

        System.exit(exitCode);
    }

    @Bean
    CommandLineRunner runner(){
        return args -> {
          if (args.length > 0 && args[0] != ""){
                LOG.info("Performing the default method.");
                console.perform(args[0]);
            }
            else {
                LOG.info("You need to inform a currency when running the app.");
                LOG.info("Example: 'java -jar CoinClient.jar BRL' - For the bitcoin rate in Brazilian Reals.");
            }
        };
    }
}
