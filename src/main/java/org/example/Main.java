package org.example;

import com.sun.net.httpserver.HttpServer;
import org.example.atmservice.controller.AtmController;
import org.example.onlinegame.controller.GameController;
import org.example.transactions.controller.TransactionController;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    public static void main(String[] args) throws IOException {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
        server.createContext("/atms/calculateOrder", new AtmController());
        server.createContext("/onlinegame/calculate", new GameController());
        server.createContext("/transactions/report", new TransactionController());
        server.setExecutor(threadPoolExecutor);
        server.start();
        System.out.println("Server started");
    }
}