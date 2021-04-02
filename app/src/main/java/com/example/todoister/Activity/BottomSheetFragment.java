package com.example.todoister.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.todoister.Model.Priority;
import com.example.todoister.Model.Task;
import com.example.todoister.Model.TaskViewModel;
import com.example.todoister.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Calendar;
import java.util.Date;

public class BottomSheetFragment extends BottomSheetDialogFragment {
    private EditText enterTodo;
    private ImageButton calenderButton;
    private ImageButton priorityButton;
    private RadioGroup priorityRadioGroup;
    private RadioButton selectedRadioGroup;
    private int selectedButtonId;
    private ImageButton saveButton;
    private CalendarView calenderView;
    private Group calenderGroup;
    private Date dueDate;
    Calendar calendar = Calendar.getInstance();

    public BottomSheetFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.bottom_sheet, container, false);

        calenderGroup = view.findViewById(R.id.calendar_group);
        calenderView = view.findViewById(R.id.calendar_view);
        calenderButton = view.findViewById(R.id.today_calendar_button);
        enterTodo = view.findViewById(R.id.enter_todo_et);
        saveButton = view.findViewById(R.id.save_todo_button);
        priorityButton = view.findViewById(R.id.priority_todo_button);
        priorityRadioGroup = view.findViewById(R.id.radioGroup_priority);

        Chip todayChip = view.findViewById(R.id.today_chip);
        Chip tomorrowChip = view.findViewById(R.id.tomorrow_chip);
        Chip nextWeeekChip = view.findViewById(R.id.next_week_chip);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        calenderButton.setOnClickListener(v -> {
            calenderGroup.setVisibility(calenderGroup.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
        });

        calenderView.setOnDateChangeListener((calenderView, year, month, dayOfMonth) -> {
            calendar.clear();
            calendar.set(year,month,dayOfMonth);
            dueDate = calendar.getTime();
                }
        );

        saveButton.setOnClickListener(view1 -> {
            String task = enterTodo.getText().toString().trim();
            if (!TextUtils.isEmpty(task) && dueDate != null) {
                Task myTask = new Task(task, Priority.HIGH, dueDate,
                        Calendar.getInstance().getTime(), false);
                TaskViewModel.insert(myTask);
            }
            dismiss();
        });

    }
}