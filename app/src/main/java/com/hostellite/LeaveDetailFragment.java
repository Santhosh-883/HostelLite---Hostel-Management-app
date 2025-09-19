package com.hostellite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LeaveDetailFragment extends Fragment {
    private static final String ARG_TYPE = "type";

    public static LeaveDetailFragment newInstance(String type) {
        LeaveDetailFragment fragment = new LeaveDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leave_detail, container, false);
        TextView title = view.findViewById(R.id.textLeaveDetail);
        String type = getArguments() != null ? getArguments().getString(ARG_TYPE) : "";
        if ("apply".equals(type)) {
            title.setText("Apply for Leave Details\n(Show leave form details here)");
        } else if ("waiting".equals(type)) {
            title.setText("Waiting List Details\n(Show waiting list details here)");
        } else if ("approved".equals(type)) {
            title.setText("Approved Leaves Details\n(Show approved leaves here)");
        }
        return view;
    }
}
