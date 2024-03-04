package com.calculator.My_Project;

import io.vertx.core.Vertx;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

public class Main {
  public static void main(String[] args) {

    final Logger logger = LoggerFactory.getLogger(Main.class);
    logger.debug("inside main");
    var vertx = Vertx.vertx();
    vertx.deployVerticle(new InputVerticle());
    vertx.deployVerticle(new CalculatorVerticle());

    vertx.deployVerticle(new DatabaseVerticle());


//    vertx.deployVerticle(new DatabaseVerticle());


    //
    // vertx.deployVerticle(new MainVerticle2());


    // vertx.eventBus()
//      .<JsonObject> consumer("temperature.updates", message -> {
//        logger.info(">>>{}" + message.body().encodePrettily());
  }
}
