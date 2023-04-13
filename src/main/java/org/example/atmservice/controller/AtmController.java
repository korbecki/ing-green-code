package org.example.atmservice.controller;

import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import org.example.BasicController;
import org.example.atmservice.comparator.TaskComparator;
import org.example.atmservice.dto.ATM;
import org.example.atmservice.dto.Task;
import org.example.onlinegame.dto.Clan;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class AtmController extends BasicController<List<Task>> {
    public AtmController() {
        super(new TypeToken<List<Task>>() {
        }.getType());
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        List<Task> request = removeDuplicates(fromJson(exchange));
        List<ATM> sortedList = new ArrayList<>(request)
                .stream()
                .sorted(new TaskComparator())
                .map(item -> new ATM(item.region(), item.atmId()))
                .collect(Collectors.toList());

        buildResponse(sortedList, exchange);
    }

    private List<Task> removeDuplicates(List<Task> taskList) {
        List<Task> resultList = new ArrayList<>();

        for (Task task : taskList) {
            List<Task> tmpList = resultList.stream()
                    .filter(it -> it.atmId() == task.atmId() && it.region() == task.region())
                    .collect(Collectors.toList());
            if (tmpList.isEmpty()) {
                resultList.add(task);
            } else {
                tmpList.add(task);
                resultList.removeAll(tmpList);
                resultList.add(tmpList.stream().min(Comparator.comparing(Task::requestType)).orElse(task));
            }
        }
        return resultList;
    }
}
