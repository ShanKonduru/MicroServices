package com.shan.explore.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.util.Random;

public class WeatherAPI extends AbstractVerticle {

    @Override
    public void start() {
        Router router = Router.router(vertx);
        router.get("/weather").handler(this::handleWeatherRequest);

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(8080, ar -> {
                    if (ar.succeeded()) {
                        System.out.println("Server started on port 8080");
                    } else {
                        System.err.println("Failed to start server: " + ar.cause());
                    }
                });
    }

    private void handleWeatherRequest(RoutingContext routingContext) {
        String weather = generateRandomWeather();
        routingContext.response()
                .putHeader("Content-Type", "application/json")
                .end("{\"weather\": \"" + weather + "\"}");
    }

    private String generateRandomWeather() {
        String[] weatherConditions = { "sunny", "cloudy", "rainy", "stormy" };
        Random random = new Random();
        int index = random.nextInt(weatherConditions.length);
        return weatherConditions[index];
    }
}
