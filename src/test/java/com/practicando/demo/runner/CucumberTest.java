package com.practicando.demo.runner;


import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;
@Suite                           // ← indica que es una test-suite
@IncludeEngines("cucumber")      // ← ejecuta con el engine “cucumber”
@SelectClasspathResource("features")   // rutas *.feature que incluirá

@ConfigurationParameter(         // glue = clases con los steps
        key   = GLUE_PROPERTY_NAME,
        value = "com.practicando.demo")
@ConfigurationParameter(         // plugins de reporte, consola, etc.
        key   = PLUGIN_PROPERTY_NAME,
        value = "pretty, html:target/cucumber-reports/report.html, "
                + "json:target/cucumber-reports/report.json")

public class CucumberTest {
}
