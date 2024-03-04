package com.calculator.My_Project;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.pgclient.PgBuilder;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.SqlClient;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonArray;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.pgclient.PgPool;

import io.vertx.sqlclient.SqlClient;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.core.json.JsonObject;



  public class DatabaseVerticle extends AbstractVerticle {

    private SqlClient client;
    private static final Logger logger = LoggerFactory.getLogger(DatabaseVerticle.class);

    @Override
    public void start() {
      logger.info(" inside database");
      // Configure connection options
      PgConnectOptions connectOptions = new PgConnectOptions()
        .setPort(5432)
        .setHost("localhost")
        .setDatabase("user_expressions")
        .setUser("postgres")
        .setPassword("passmethrough100");

      // Configure pool options
      PoolOptions poolOptions = new PoolOptions()
        .setMaxSize(1);
      logger.info("before pool..");
      // Create client pool
      client = PgBuilder.client()
        .with(poolOptions)
        .connectingTo(connectOptions)
        .build();
      // Log pool creation status

      // Listen for events from other verticles
//      vertx.eventBus().consumer("computatiod, message -> {
//        String result = (String) message.body();
      System.out.println("hey: ");
      // Replace "your-table-name" with your actual table name
      String sql = "INSERT INTO user_expressions (result, expression) VALUES (11,'9+2')";
      System.out.println("hey sql: "+sql);
      logger.info(sql);

      // Execute the query with the result
      client.query(sql)
        .execute()
        .onComplete(ar -> {
          if (ar.succeeded()) {
            System.out.println("Data inserted successfully!");
          } else {
            System.err.println("Failed to insert data: " + ar.cause().getMessage());
          }
        });

      logger.info("completed query");
    }
    @Override
    public void stop() throws Exception {
      client.close();
    }
  }




