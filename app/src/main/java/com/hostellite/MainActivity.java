package com.hostellite;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private BottomNavigationView bottomNav;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeManager.applyTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);
        bottomNav = findViewById(R.id.bottom_navigation);

        // Set up the hamburger icon
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bottom navigation logic (5 tabs)
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();
            // Always show nav bar for bottom nav
            bottomNav.setVisibility(View.VISIBLE);
            if (id == R.id.navigation_attendance) {
                selectedFragment = new AttendanceFragment();
            } else if (id == R.id.navigation_booking) {
                selectedFragment = new BookingFragment();
            } else if (id == R.id.navigation_complaints) {
                selectedFragment = new ComplaintsFragment();
            } else if (id == R.id.navigation_leave) {
                selectedFragment = new LeaveFragment();
            } else if (id == R.id.navigation_announcements) {
                selectedFragment = new AnnouncementsFragment();
            }
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            }
            return true;
        });

        // Drawer navigation logic (all tabs)
        navView.setNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();
            // Always show nav bar except for settings (which is now SettingsFragment full page)
            if (id == R.id.nav_attendance) {
                selectedFragment = new AttendanceFragment();
                bottomNav.setVisibility(View.VISIBLE);
                bottomNav.setSelectedItemId(R.id.navigation_attendance);
            } else if (id == R.id.nav_booking) {
                selectedFragment = new BookingFragment();
                bottomNav.setVisibility(View.VISIBLE);
                bottomNav.setSelectedItemId(R.id.navigation_booking);
            } else if (id == R.id.nav_complaints) {
                selectedFragment = new ComplaintsFragment();
                bottomNav.setVisibility(View.VISIBLE);
                bottomNav.setSelectedItemId(R.id.navigation_complaints);
            } else if (id == R.id.nav_announcements) {
                selectedFragment = new AnnouncementsFragment();
                bottomNav.setVisibility(View.VISIBLE);
                bottomNav.setSelectedItemId(R.id.navigation_announcements);
            } else if (id == R.id.nav_leave) {
                selectedFragment = new LeaveFragment();
                bottomNav.setVisibility(View.VISIBLE);
                bottomNav.setSelectedItemId(R.id.navigation_leave);
            } else if (id == R.id.nav_id_card) {
                selectedFragment = new IdCardFragment();
                bottomNav.setVisibility(View.GONE);
            } else if (id == R.id.nav_settings) {
                selectedFragment = new SettingsFragment();
                bottomNav.setVisibility(View.GONE);
            }
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // Set default fragment to ID Card (Coming Soon). No tab selected by default.
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new IdCardFragment()).commit();
        bottomNav.setVisibility(View.GONE);
        // Do not select any bottom nav tab here.
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
