package se.iths.springloppis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Startpunkten för appen
public class SpringLoppisApplication { // Utgångspunkten för hela applikationen.

    public static void main(String[] args) {
        SpringApplication.run(SpringLoppisApplication.class, args);
    }
     // En klassisk mainmetod.

}

/* Här kan man lägga in saker som man vill ska hända. Om man t ex vill ha testdata som genereras när man
startar appen osv. */