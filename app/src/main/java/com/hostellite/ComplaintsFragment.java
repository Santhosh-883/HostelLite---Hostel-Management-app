package com.hostellite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;

public class ComplaintsFragment extends Fragment {
    private List<String> complaintHistory = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complaints, container, false);
        Button btnRaise = view.findViewById(R.id.buttonRaiseComplaint);
        ListView historyList = view.findViewById(R.id.listComplaintHistory);
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, complaintHistory);
        historyList.setAdapter(adapter);
        btnRaise.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new RaiseComplaintFragment())
                .addToBackStack(null)
                .commit();
        });
        return view;
    }

    public void addComplaint(String complaint) {
        complaintHistory.add(0, complaint);
        if (adapter != null) adapter.notifyDataSetChanged();
    }
}
