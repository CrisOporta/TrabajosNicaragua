package com.example.trabajosnicaragua;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trabajosnicaragua.ui.jobs.SharedJobsViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.widget.Toolbar;

import com.example.trabajosnicaragua.databinding.ActivityMainBinding;

import data.SharedViewModel;
import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private SharedViewModel sharedViewModel;

    private SharedJobsViewModel sharedJobsViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retrieve the variable
        Intent intent = getIntent();
        String user_rol = intent.getStringExtra("user_rol");
        int user_id = intent.getIntExtra("user_id", -1);

        // User test
        //Toasty.info(this, user_id + " " + user_rol, Toast.LENGTH_SHORT, true).show();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar topAppBar = findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_jobs)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);

        sharedJobsViewModel = new ViewModelProvider(this).get(SharedJobsViewModel.class);
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        sharedViewModel.setUserId(user_id);
        sharedViewModel.setUserRol(user_rol);
        sharedJobsViewModel.setUserId(user_id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_topbar, menu);
        int exitApp = R.id.exit_app;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        String resourceName = getResources().getResourceName(id);
        String entryName = resourceName.substring(resourceName.lastIndexOf("/") + 1);

        switch (entryName) {
            case "settings_app":

                return true;
            case "logout_app":
                //this method will remove session and open login screen
                SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
                sessionManagement.removeSession();
                Toasty.info(this, "Cerrando sesión", Toast.LENGTH_SHORT, true).show();
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            case "exit_app":
                    finishAffinity();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}