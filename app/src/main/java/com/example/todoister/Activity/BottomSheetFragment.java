package com.example.todoister.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todoister.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class BottomSheetFragment extends BottomSheetDialogFragment {
    private EditText enterTodo;
    private ImageButton calenderButton;
    private ImageButton priorityButton;
    private RadioGroup priorityRadioGroup;
    private RadioButton selectedRadioGroup;
    private int selectedButtonId;
    private ImageButton saveButton;
    private CalenderView calenderView;
    private Group calenderGroup;

    public BottomSheetFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.bottom_sheet, container, false);

        calenderGroup = view.findViewBy(R.id.calender_group);
        calenderView = view.findViewBy(R.id.calender_view);
        calenderButton = view.findViewBy(R.id.today_calender_button);
        enterTodo = view.findViewBy(R.id.enter_todo_et);
        saveButton = view.findViewBy(R.id.save_todo_button);
        priorityButton = view.findViewBy(R.id.priority_todo_button);
        priorityRadioGroup = view.findViewBy(R.id.raidoGroup_priority);

        Chip todayChip = view.findViewBy(R.id.today_chip);
        Chip tomorrowChip = view.findViewBy(R.id.tomorrow_chip);
        Chip nextWeeekChip = view.findViewBy(R.id.next_week_chip);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        saveButton.setOnClickListener(view -> {
            String task = enterTodo.getText().toString().trim();
            if (!TextUtil.isEmpty(task)) {
                Task myTask = new Task(task, Priority.HIGH, Calender.getInstance().getTime(),
                        Calender.getInstance().getTime(), false);
                TaskViewModel.insert(mytask);
            }
        });

    }
}