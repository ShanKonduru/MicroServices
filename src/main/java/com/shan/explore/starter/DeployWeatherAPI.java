package com.shan.explore.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class DeployWeatherAPI extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
      Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new WeatherAPI());
  }
}
