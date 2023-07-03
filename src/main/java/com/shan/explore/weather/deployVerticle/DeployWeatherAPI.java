package com.shan.explore.weather.deployVerticle;

import com.shan.explore.weather.WeatherAPI;

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
