package com.hostellite;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RaiseComplaintFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_raise_complaint, container, false);
        EditText name = view.findViewById(R.id.editTextName);
        EditText room = view.findViewById(R.id.editTextRoom);
        EditText type = view.findViewById(R.id.editTextType);
        EditText desc = view.findViewById(R.id.editTextDescription);
        Button submit = view.findViewById(R.id.buttonSubmitComplaint);
        submit.setOnClickListener(v -> {
            String n = name.getText().toString().trim();
            String r = room.getText().toString().trim();
            String t = type.getText().toString().trim();
            String d = desc.getText().toString().trim();
            if (TextUtils.isEmpty(n) || TextUtils.isEmpty(r) || TextUtils.isEmpty(t) || TextUtils.isEmpty(d)) {
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            String entry = n + " (Room: " + r + ") - " + t + ": " + d;
            getParentFragmentManager().popBackStack();
            Fragment parent = getParentFragmentManager().findFragmentById(R.id.fragment_container);
            if (parent instanceof ComplaintsFragment) {
                ((ComplaintsFragment) parent).addComplaint(entry);
            }
            Toast.makeText(getContext(), "Complaint submitted!", Toast.LENGTH_SHORT).show();
        });
        return view;
    }
}
