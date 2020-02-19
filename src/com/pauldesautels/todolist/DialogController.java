package com.pauldesautels.todolist;

import com.pauldesautels.todolist.datamodel.ToDoData;
import com.pauldesautels.todolist.datamodel.ToDoItem;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class DialogController {

    @FXML
    private TextField titleField;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private DatePicker deadlinePicker;

    public ToDoItem processResults()    { // After "OK' button is pressed
        String title = titleField.getText().trim();
        String description = descriptionArea.getText().trim();
        LocalDate deadlineValue = deadlinePicker.getValue();

        ToDoItem newItem = new ToDoItem(title, description, deadlineValue);
        ToDoData.getInstance().addToDoItem(newItem);
        return newItem;
    }
}
