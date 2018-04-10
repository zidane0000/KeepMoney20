package tw.edu.niu.keepmoney20;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView textView;
    RelativeLayout remenu_home;
    RelativeLayout remenu_account;
    RelativeLayout remenu_about;
    RelativeLayout remenu_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //強制鎖定為直屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        remenu_home = (RelativeLayout) findViewById(R.id.main);
        remenu_about= (RelativeLayout) findViewById(R.id.menu_about);
        remenu_account=(RelativeLayout)findViewById(R.id.menu_account);
        remenu_setting=(RelativeLayout)findViewById(R.id.menu_setting);

        textView = (TextView)findViewById(R.id.showDate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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

    /* 右上角的設定 顯示在res/menu/main
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent abbody_home = new Intent(MainActivity.this, tw.edu.niu.keepmoney20.body_home.class);
            abbody_home.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(abbody_home);
            Toast.makeText(this, "首頁" ,Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_account) {

            Intent abbody_account = new Intent(MainActivity.this, tw.edu.niu.keepmoney20.menu_account.class);
            abbody_account.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(abbody_account);
            Toast.makeText(this, "帳戶" ,Toast.LENGTH_LONG).show();

        }else if (id == R.id.nav_setting) {

            Intent abbody_setting = new Intent(MainActivity.this, tw.edu.niu.keepmoney20.menu_setting.class);
            abbody_setting.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(abbody_setting);
            Toast.makeText(this, "設定" ,Toast.LENGTH_LONG).show();

        }else if (id == R.id.nav_about) {

            Intent abbody_account = new Intent(MainActivity.this, tw.edu.niu.keepmoney20.menu_about.class);
            abbody_account.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(abbody_account);
            Toast.makeText(this, "關於" ,Toast.LENGTH_LONG).show();

        }else if (id == R.id.nav_share) {

        }else if (id == R.id.nav_evaluation) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    //        設定時間
     public void startDate(View view) {
        Calendar c = Calendar.getInstance();
        int mYear, mMonth, mDay;
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(
                MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                textView.setText(year + " 年 "+ month + " 月 " + day + " 日 ");
//                saveYear = year;
//                saveMonth = month;
//                saveDay = day;
            }
        }, mYear, mMonth, mDay).show();

    }
}
