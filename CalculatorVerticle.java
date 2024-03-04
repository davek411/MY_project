package com.calculator.My_Project;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

public class CalculatorVerticle extends AbstractVerticle {
  private static final Logger logger = LoggerFactory.getLogger(CalculatorVerticle.class);

  public void start(Promise<Void> startPromise) {
    logger.info("main verticle deployed");
    vertx.eventBus().consumer("calculate", message -> {
      logger.debug("inside consume..");
      String[] values = ((String) message.body()).split(",");
      int x = Integer.parseInt(values[0]);
      int y = Integer.parseInt(values[1]);
      System.out.println("x: "+x);
      System.out.println("y: "+y);
      int result = x + y; // Perform calculation (you can modify this to perform any operation)
      System.out.println("chalo chaliye");

      // Reply back with the result
      message.reply(result);
    });

    startPromise.complete();
  }
}
