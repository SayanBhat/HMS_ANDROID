package project.sayan.hms.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import project.sayan.hms.Model.UserModel;
import project.sayan.hms.R;
import project.sayan.hms.mFragments.HealthNewsFragment;
import project.sayan.hms.mFragments.MyAccountFragment;
import project.sayan.hms.mFragments.ViewPager_MainFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    NavigationView navigationView;
    private TextView welcome_email;
    private TextView welcome_name;
    private final String LOGINCRED="LoginCredentials";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

       navigationView= (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);


        getSupportFragmentManager().beginTransaction().replace(R.id.container,new ViewPager_MainFragment()).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        SharedPreferences sharedPref = getSharedPreferences(LOGINCRED, Context.MODE_PRIVATE);

        if (id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new ViewPager_MainFragment()).commit();
            toolbar.setTitle("Home");
        }
        else if (id == R.id.nav_myaccount) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new MyAccountFragment()).commit();
            toolbar.setTitle("My Account");
        }
        else if (id == R.id.nav_healthnews) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new HealthNewsFragment()).commit();
            toolbar.setTitle("Health News");
        } else if (id == R.id.nav_logout) {

            SharedPreferences.Editor ed=sharedPref.edit();
            ed.clear();
            ed.apply();
            Toast.makeText(MainActivity.this,"Signed Out Successfully",Toast.LENGTH_SHORT).show();
            navigationView.getMenu().getItem(0).setChecked(true);
            navigationView.getMenu().findItem(R.id.nav_logout).setVisible(false);
            welcome_email.setText("");
            welcome_name.setText("");
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new ViewPager_MainFragment()).commit();
            toolbar.setTitle("Home");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();
        UserModel user= new UserModel();
        if (navigationView != null) {
            welcome_email = navigationView.getHeaderView(0).findViewById(R.id.nav_header_tvEmail);
            welcome_name=navigationView.getHeaderView(0).findViewById(R.id.nav_header_tvName);
        }
        SharedPreferences sh_Pref = getSharedPreferences(LOGINCRED, MODE_PRIVATE);
        boolean check = sh_Pref.getBoolean("IS_LOGIN", false);
        if (check) {

            welcome_email.setText(sh_Pref.getString(user.NAME,""));
            welcome_name.setText(sh_Pref.getString(user.EMAIL,""));
            navigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);
        }
        else {
            welcome_email.setText("");
            welcome_name.setText("");
            navigationView.getMenu().findItem(R.id.nav_logout).setVisible(false);
        }
    }
}
