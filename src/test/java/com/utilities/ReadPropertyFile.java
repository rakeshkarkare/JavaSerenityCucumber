package com.utilities;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.guice.Injectors;
import net.thucydides.core.util.EnvironmentVariables;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class ReadPropertyFile {
  /**
   * Read Property file
   *
   * @return endpoint
   */
  public static String readConfigurationFile(String key) {
    EnvironmentVariables environmentVariables = Injectors.getInjector()
        .getInstance(EnvironmentVariables.class);

    return EnvironmentSpecificConfiguration.from(environmentVariables)
            .getProperty(key);
  }

  public static String form_endpoint(String endpoint){
    return ReadPropertyFile.readConfigurationFile("domain")+
            ReadPropertyFile.readConfigurationFile(endpoint)+
            ReadPropertyFile.readConfigurationFile(EndpointConstants.ACCESS_TOKEN);
  }

  public static String form_endpoint_with_invalid_token(String endpoint){
//    ZonedDateTime tomorrow = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS).plusDays(1).withMinute(9);
//    System.out.println("DATE" + (int) tomorrow.toInstant().getEpochSecond());

    return ReadPropertyFile.readConfigurationFile("domain")+
            ReadPropertyFile.readConfigurationFile(endpoint)+
            "Invalid Token";
  }

}