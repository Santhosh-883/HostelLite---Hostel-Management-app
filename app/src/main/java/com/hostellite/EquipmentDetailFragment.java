package com.hostellite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EquipmentDetailFragment extends Fragment {
    private static final String ARG_TYPE = "type";

    public static EquipmentDetailFragment newInstance(String type) {
        EquipmentDetailFragment fragment = new EquipmentDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_equipment_detail, container, false);
        TextView title = view.findViewById(R.id.textTitle);
        String type = getArguments() != null ? getArguments().getString(ARG_TYPE) : "";
        if ("equipments".equals(type)) {
            title.setText("Available Equipments\n- Football\n- Basketball\n- Tennis Racket");
        } else if ("students".equals(type)) {
            title.setText("Students in the Sports Room\n- John Doe\n- Jane Smith");
        }
        return view;
    }
}
