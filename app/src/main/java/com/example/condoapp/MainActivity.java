package com.example.condoapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.condoapp.activity.CreateIncedentReport;
import com.example.condoapp.controller.MainController;
import com.google.android.material.navigation.NavigationView;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.condoapp.databinding.ActivityMainBinding;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private String passedData[];
    ImageView qr;
    TextView name,email;
    private SharedViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainController mc = new MainController();
        passedData = new String[mc.getDataPassed().length];
        for (int i = 0; i < mc.getDataPassed().length; i++) {
            passedData[i] = getIntent().getExtras().getString(mc.getDataPassed()[i]);
        }
        viewModel = new ViewModelProvider (this).get(SharedViewModel.class);
        viewModel.setPassedData(passedData);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle extras = getIntent().getExtras();
        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CreateIncedentReport.class);
                startActivity(mc.putAllDataPassed(i,passedData));
                finish();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_incident, R.id.nav_dues,R.id.nav_history)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        View hView = navigationView.getHeaderView(0);
        name = (TextView) hView.findViewById(R.id.main_name);
        email = (TextView) hView.findViewById(R.id.main_email);
        name.setText(getIntent().getExtras().getString("first_name")+ " "+ getIntent().getExtras().getString("last_name"));
        email.setText(getIntent().getExtras().getString("email"));
        qr = (ImageView) findViewById(R.id.qrcode);
        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog(1,R.layout.dialog_qrcode);
            }
        });
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    Button close;
    void showCustomDialog(int geDialog,int id) {
        final Dialog dialog = new Dialog(MainActivity.this);
        //We have added a title in the custom layout. So let's disable the default title.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //The user will be able to cancel the dialog bu clicking anywhere outside the dialog.
        dialog.setCancelable(true);
        //Mention the name of the layout of your custom dialog.
        dialog.setContentView(id);
        close = (Button) dialog.findViewById(R.id.close_dialog_qr);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

//    void populateInfoTv(String name, String age, Boolean hasAcceptedTerms) {
//        infoTv.setVisibility(View.VISIBLE);
//        String acceptedText = "have";
//        if(!hasAcceptedTerms) {
//            acceptedText = "have not";
//        }
//        infoTv.setText(String.format(getString(R.string.info), name, age, acceptedText));
//    }
}