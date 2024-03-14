package com.calculator.My_Project;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
//import io.vertx.core.http.HttpRequest;
public class InputVerticle extends AbstractVerticle {

  public static final Logger logger = LogManager.getLogger(InputVerticle.class);



    //private PgPool pgPool;

    @Override
    public void start(Promise<Void> startPromise) {
      System.out.println("chalo chaliye feom i/p verticle");

      logger.info("inside start method");

    
      //ROUTING TO THE METHODS AND HANDLER FUNCTION
      Router router = Router.router(vertx);
      router.get("/calculate").handler(this::handleCalculateRequest);

      vertx.createHttpServer().requestHandler(router)
        .listen(8080)
        .onSuccess(ok -> {
          startPromise.complete();
        })
        .onFailure(startPromise::fail);
//    
    }

    private void handleCalculateRequest(RoutingContext routingContext) {
      String xValue = routingContext.request().getParam("x");
      String yValue = routingContext.request().getParam("y");
      logger.info("routing context: "+xValue);
      // Send the input parameters to the calculation verticle
      EventBus eventBus = vertx.eventBus();
      System.out.println("chalo chaliye");

      eventBus.request("calculate", xValue + "," + yValue, reply -> {
        if (reply.succeeded()) {
          routingContext.response()
            .putHeader("Content-type", "text/plain")
            .end("Result: " + reply.result().body());
        } else {
          routingContext.response().setStatusCode(500).end("jaggu bandar");
        }
      });


    }
  }


