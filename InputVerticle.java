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

      // vertx.setPeriodic(1000,this::updateTemperature);

//    pgPool = PgPool.pool(vertx, new PgConnectOptions()
//      .setHost("127.0.0.1")
//      .setUser("postgres")
//      .setDatabase("postgres")
//      .setPassword("vertx"), new PoolOptions());


      //ROUTING TO THE METHODS AND HANDLER FUNCTION
      Router router = Router.router(vertx);
      router.get("/calculate").handler(this::handleCalculateRequest);
      System.out.println("chalo chaliye");


//    router.get("/add").handler(this::addData);
//    router.get("/subtract").handler(this::subtractData);
//    router.get("/multiply").handler(this::multiplyData);
//    router.get("/divide").handler(this::divideData);
////
      // vertx.eventBus().consumer("Data added".this::datadded);



      vertx.createHttpServer().requestHandler(router)
        .listen(8080)
        .onSuccess(ok -> {
//logger.info("http server running :http://0.0.0.0:{}"+httpPort);
          startPromise.complete();
        })
        .onFailure(startPromise::fail);
//      req.response()
//        .putHeader("content-type", "text/plain")
//        .end("Hello from Vert.x!");
//    }).listen(8888, http -> {
//      if (http.succeeded()) {
      //startPromise.complete();
//        System.out.println("HTTP server started on port 8888");
//      } else {
//        startPromise.fail(http.cause());
//      }
//    });


    }

//  private void getalldata(RoutingContext routingContext)
//  {
//    logger.info("Requesting all daat from {}",routingContext.request.remoteAddress());
//  }

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

//    private void addData (RoutingContext context){
//      //logger.info("Processing HTTP request from " + context.request().absoluteURI());
//      String x_value = context.request().getParam("x");
//      String y_value = context.request().getParam("y");
//
//      int x = Integer.parseInt(x_value);
//      int y = Integer.parseInt(y_value);
//
//      int result = x + y;
////    pgPool.query("INSERT INTO computation_history (x, y, operation, result) VALUES ($1, $2, $3, $4)")
////      .execute(Tuple.of(x, y, "add", result))
////      .onSuccess(rows -> {
////        // Send response with computation result
////
////      })
////      .onFailure(err -> {
////        // Handle database insertion error
////        logger.error("Failed to insert computation result into the database: " + err.getMessage());
////        context.response().setStatusCode(500).end("Internal Server Error");
////      });
////  }
//      context.response()
//        .putHeader("Content-Type", "text/plain")
//        .end("Result: " + result);
//
//
//    }
//    private void subtractData (RoutingContext context){
//      //logger.info("Processing HTTP request from " + context.request().absoluteURI());
//      String x_value = context.request().getParam("x");
//      String y_value = context.request().getParam("y");
//
//      int x = Integer.parseInt(x_value);
//      int y = Integer.parseInt(y_value);
//
//      int result = x - y;
//
//
//      context.response()
//        .putHeader("Content-type", "text/plain")
//        .end("Result: " + result);
//    }
//    private void divideData (RoutingContext context){
//      //logger.info("Processing HTTP request from " + context.request().absoluteURI());
//      String x_value = context.request().getParam("x");
//      String y_value = context.request().getParam("y");
//
//      int x = Integer.parseInt(x_value);
//      int y = Integer.parseInt(y_value);
//
//      int result = x / y;
//
//
//      context.response()
//        .putHeader("Content-type", "text/plain")
//        .end("Result: " + result);
//    }
//    private void multiplyData (RoutingContext context){
//      //logger.info("Processing HTTP request from " + context.request().absoluteURI());
//      String x_value = context.request().getParam("x");
//      String y_value = context.request().getParam("y");
//
//      int x = Integer.parseInt(x_value);
//      int y = Integer.parseInt(y_value);
//
//      int result = x * y;
//
//
//      context.response()
//        .putHeader("Content-type", "text/plain")
//        .end("Result: " + result);
//    }

      // void putHeader(String s, String contentType) {

//  private JsonObject createPayload() {
//    return new JsonObject()
//      .put("uuid",uuid)
//      .put("temperatur",temperature)
//      .put("timestamp",System.currentTimeMillis());
//  }

//  private void updateTemperature(Long id) {
//    temperature = temperature + (random.nextGaussian()/2.0d);
//    logger.info("Temperature updated: {}" + temperature);
//
//    vertx.eventBus()
//      .publish("temperature.updates", createPayload());
//
//
//  }
    }
  }


