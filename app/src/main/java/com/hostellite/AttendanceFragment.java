package com.hostellite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class AttendanceFragment extends Fragment {
    private List<StudentAttendance> attendanceHistory = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private boolean isMarked = false;
    private boolean morningMarked = false;
    private boolean eveningMarked = false;
    private Button markAttendance;
    private TextView warningText;
    private ImageView ledIndicator;
    private TextView statusText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attendance, container, false);
        markAttendance = view.findViewById(R.id.buttonMarkAttendance);
        ListView historyList = view.findViewById(R.id.listAttendanceHistory);
        ledIndicator = view.findViewById(R.id.ledIndicator);
        statusText = view.findViewById(R.id.textAttendanceStatus);
        warningText = view.findViewById(R.id.textAttendanceWarning);
        adapter = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, new ArrayList<>());
for (StudentAttendance sa : attendanceHistory) {
    adapter.add(sa.studentName + " | " + sa.date + " | " + (sa.isPresent ? "Present" : "Absent"));
}
        historyList.setAdapter(adapter);

        updateStatus();
        updateButtonState();

        markAttendance.setOnClickListener(v -> {
            String session = getCurrentSession();
            if (session == null) return;
            String time = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
            StudentAttendance entry = new StudentAttendance("Student Name", time + " [" + session + "]", true);
            attendanceHistory.add(0, entry);
            adapter.insert(entry.studentName + " | " + entry.date + " | Present", 0);
            adapter.notifyDataSetChanged();
            if (session.equals("Morning")) morningMarked = true;
            if (session.equals("Evening")) eveningMarked = true;
            isMarked = morningMarked && eveningMarked;
            updateStatus();
            updateButtonState();
        });
        return view;
    }

    private void updateStatus() {
        if (morningMarked && eveningMarked) {
            ledIndicator.setImageResource(R.drawable.led_green);
            statusText.setText("Marked");
        } else {
            ledIndicator.setImageResource(R.drawable.led_red);
            if (!morningMarked && !eveningMarked) {
                statusText.setText("Not Marked");
            } else if (!morningMarked) {
                statusText.setText("Evening Marked");
            } else {
                statusText.setText("Morning Marked");
            }
        }
    }

    private void updateButtonState() {
        String session = getCurrentSession();
        if (session == null) {
            markAttendance.setEnabled(false);
            warningText.setVisibility(View.VISIBLE);
            int hour = new Date().getHours();
            if (hour < 6 || hour >= 21) {
                warningText.setText("Attendance not marked during morning and evening");
            } else if (hour < 9) {
                warningText.setText("Attendance not marked during morning");
            } else {
                warningText.setText("Attendance not marked during evening");
            }
        } else {
            boolean alreadyMarked = (session.equals("Morning") && morningMarked) || (session.equals("Evening") && eveningMarked);
            markAttendance.setEnabled(!alreadyMarked);
            warningText.setVisibility(alreadyMarked ? View.VISIBLE : View.GONE);
            if (alreadyMarked) {
                warningText.setText("Attendance already marked for " + session.toLowerCase());
            }
        }
    }

    private String getCurrentSession() {
        Date now = new Date();
        int hour = now.getHours();
        if (hour >= 6 && hour < 9) {
            return "Morning";
        } else if (hour >= 19 && hour < 21) {
            return "Evening";
        } else {
            return null;
        }
    }
}
