package com.pauldesautels.todolist.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

public class ToDoData { // Singleton
    private static ToDoData instance = new ToDoData();
    private static String fileName = "ToDoListItems.txt";

    private ObservableList<ToDoItem> toDoItems;
    private DateTimeFormatter formatter;

    public static ToDoData getInstance()    {
        return instance;
    }

    private ToDoData()  {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public ObservableList<ToDoItem> getToDoItems() {
        return toDoItems;
    }

    public void addToDoItem(ToDoItem item)  {
        toDoItems.add(item);
    }

    public void loadToDoItems() throws IOException  {

        toDoItems = FXCollections.observableArrayList();
        Path path = Paths.get(fileName);
        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try {
            while ((input = br.readLine()) != null)   {
                String[] itemPieces = input.split("\t");

                String title = itemPieces[0];
                String description = itemPieces[1];
                String dateString = itemPieces[2];

                LocalDate date = LocalDate.parse(dateString, formatter);
                ToDoItem toDoItem = new ToDoItem(title, description, date);
                toDoItems.add(toDoItem);
            }

        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    public void storeToDoItems() throws IOException {

        Path path = Paths.get(fileName);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try {
            Iterator<ToDoItem> iter = toDoItems.iterator();
            while (iter.hasNext())  {
                ToDoItem item = iter.next();
                bw.write(String.format("%s\t%s\t%s\t",
                        item.getTitle(),
                        item.getDescription(),
                        item.getDeadline().format(formatter)));
                bw.newLine();
            }

        } finally {
            if(bw != null)  {
                bw.close();
            }
        }
    }
    public void deleteToDoItem(ToDoItem item)   {
        toDoItems.remove(item);
    }
}
