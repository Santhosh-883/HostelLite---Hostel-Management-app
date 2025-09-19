package com.hostellite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BookingFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking, container, false);
        Button btnEquipments = view.findViewById(R.id.buttonAvailableEquipments);
        Button btnStudents = view.findViewById(R.id.buttonStudentsInRoom);
        btnEquipments.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new EquipmentsFragment())
                .addToBackStack(null)
                .commit();
        });
        btnStudents.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new StudentsInRoomFragment())
                .addToBackStack(null)
                .commit();
        });
        return view;
    }
}
