package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;

public abstract class BasicController<T> implements HttpHandler {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final Type type;

    public BasicController(Type type) {
        this.type = type;
    }

    protected T fromJson(HttpExchange exchange) {
        InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        synchronized (GSON) {
            synchronized (type) {
                return GSON.fromJson(bufferedReader, type);
            }
        }
    }

    protected void buildResponse(Object response, HttpExchange exchange) throws IOException {
        synchronized (GSON) {
            String json = GSON.toJson(response);
            OutputStream outputStream = exchange.getResponseBody();
            exchange.sendResponseHeaders(200, json.getBytes().length);
            outputStream.write(json.getBytes());
            outputStream.flush();
            outputStream.close();
        }
        runGc();
    }

    private void runGc() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
    }
}
