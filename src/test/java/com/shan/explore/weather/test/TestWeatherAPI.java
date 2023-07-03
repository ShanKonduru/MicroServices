package com.shan.explore.weather.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.shan.explore.weather.WeatherAPI;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;

@ExtendWith(VertxExtension.class)
public class TestWeatherAPI {

    @Test
    public void testWeatherEndpoint(Vertx vertx, VertxTestContext testContext) {
        // Deploy the WeatherAPI verticle
        vertx.deployVerticle(new WeatherAPI(), testContext.succeeding(id -> {
            // Create a web client
            WebClient client = WebClient.create(vertx);

            // Make a GET request to the /weather endpoint
            client.get(8888, "localhost", "/weather")
                    .send(testContext.succeeding(response -> {
                        // Assert the response status code is 200 OK
                        assertEquals(200, response.statusCode());

                        // Assert the response body is not null
                        assertNotNull(response.body());

                        // Parse the response body JSON
                        JsonObject responseBody = response.bodyAsJsonObject();

                        // Assert the response contains the "weather" field
                        assertNotNull(responseBody.getString("weather"));

                        testContext.completeNow();
                    }));
        }));
    }
}
