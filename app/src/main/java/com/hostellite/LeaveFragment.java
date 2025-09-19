package com.hostellite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LeaveFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leave, container, false);
        Button applyLeaveButton = view.findViewById(R.id.buttonApplyLeave);
        Button waitingListButton = view.findViewById(R.id.buttonWaitingList);
        Button approvedLeavesButton = view.findViewById(R.id.buttonApprovedLeaves);
        // TODO: Add navigation logic for each button
        return view;
    }
}
