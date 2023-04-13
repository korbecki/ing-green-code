package org.example.onlinegame.controller;

import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import org.example.BasicController;
import org.example.Constants;
import org.example.onlinegame.dto.Clan;
import org.example.onlinegame.dto.Players;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GameController extends BasicController<Players> {

    public GameController() {
        super(new TypeToken<Players>() {
        }.getType());
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Players players = fromJson(exchange);
        List<Clan> clanList = players.clans().stream().sorted().collect(Collectors.toList());
        List<List<Clan>> resultList = new LinkedList<>();

        while (!clanList.isEmpty()) {
            List<Clan> clans = new LinkedList<>();
            int freeSpace = players.groupCount();

            Iterator<Clan> clanIterator = clanList.iterator();
            while (clanIterator.hasNext()) {
                Clan clan = clanIterator.next();
                if (clan.getNumberOfPlayers() <= freeSpace) {
                    freeSpace -= clan.getNumberOfPlayers();
                    clans.add(clan);
                    clanIterator.remove();
                    if (Constants.INT_ZERO == freeSpace) {
                        break;
                    }
                }
            }
            resultList.add(clans);
        }
        buildResponse(resultList, exchange);
    }
}
