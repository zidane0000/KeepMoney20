package tw.edu.niu.keepmoney20;

import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
//#################網路資料庫的 MySQL#########################
//############################################################

//#################記帳主體___網路資料庫MySQL#########################
    String google_token="offline";   //用這來判斷是否google同步
    String[] id,ntd,google_id,dates,category,ans;   //接收 SQL語法取得後的資料
    String[] testANS;  //lv顯示的結果
    int i;  //listviews紀錄點到哪個
    boolean begin_lv =true;  //因為用thread while(true)  這個個來判斷已經跑一遍惹

    ListView lv;

    //日期處理
    String whichDate;   //查詢哪天
    int mYear, mMonth, mDay; //紀錄點選時間
//############################################################

    //評價的網址 放我們的app網址
    String EvaluationAddress = "https://play.google.com/store/apps?hl=zh_TW";
    TextView textView;

    ImageButton handaddto;
    ImageButton voiceaddto;
    TextView saywhat;

    RelativeLayout remenu_home;
    RelativeLayout remenu_account;
    RelativeLayout remenu_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_main);

        //強制鎖定為直屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        remenu_home = (RelativeLayout) findViewById(R.id.main);
        remenu_about= (RelativeLayout) findViewById(R.id.menu_about);
        remenu_account=(RelativeLayout)findViewById(R.id.menu_account);
        textView = (TextView)findViewById(R.id.showDate);


//#######################左拉選單##################################################
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
//##################################################################################


////################舊的listview##########################
//        // 準備資料，塞3個項目到ArrayList裡
//        for(int i = 0; i < 3; i++) {
//            tohome_listview_data.add("項目"+i);
//        }
//        /* 初始Adapter
//        *  第一個參數context
//        *  第二個參數是列的外觀，這邊用android內建的
//        *  第三個參數是要顯示的資料，即上面準備好的mData
//        */
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tohome_listview_data);
//        // 連結元件
//        tohome_listView = (ListView) findViewById(R.id.content_main_listview);
//        // 設置adapter給listview
//        tohome_listView.setAdapter(adapter);
//
////        // 塞項目到ArrayList
////        tohome_listview_data.add("項目");
////        adapter.notifyDataSetChanged();
//############################################################



        //語音處理輸入部分
        voiceaddto = (ImageButton) findViewById(R.id.VoiceEnter);
        voiceaddto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "請說出品項與金額");
                try {
                    startActivityForResult(intent, 10);
                } catch (ActivityNotFoundException a) {
                }
            }
        });
        saywhat = (TextView) findViewById(R.id.showsay);


        //手動新增頁面
        handaddto = (ImageButton) findViewById(R.id.ManuallyButton);
        handaddto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //至新增的畫面
                Intent gotoNewScreen = new Intent(MainActivity.this, tw.edu.niu.keepmoney20.NewScreen.class);
                gotoNewScreen.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(gotoNewScreen);
            }
        });


//#################網路資料庫的 MySQL#########################
        //程式進入就顯示今天日期
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年M月d日");
        Date curDate = new Date(System.currentTimeMillis()) ; // 獲取當前時間
        String str = formatter.format(curDate);
        textView.setText(str);
        //取得當天的日期
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        String tmpM,tmpD;
        if( (mMonth+1)<10 ){
            tmpM="0"+ Integer.toString(mMonth+1);
        }else{
            tmpM= Integer.toString(mMonth+1);
        }
        if( (mDay)<10 ){
            tmpD="0"+ Integer.toString(mDay);
        }else{
            tmpD= Integer.toString(mDay);
        }
        whichDate=Integer.toString(mYear) + tmpM + tmpD;


        lv = findViewById(R.id.content_main_listview);
        thread("SELECT * FROM `test`" + " WHERE `date`='"+ whichDate +"'" , "ans");
//############################################################
    }


    @Override//左拉選單ㄉ
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override//左拉選單ㄉ
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override//左拉選單ㄉ
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

    @SuppressWarnings("StatementWithEmptyBody")//左拉選單ㄉ
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Toast.makeText(this, "首頁" ,Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_account) {

            Intent abbody_account = new Intent(MainActivity.this, tw.edu.niu.keepmoney20.menu_account.class);
            abbody_account.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(abbody_account);
            Toast.makeText(this, "帳戶" ,Toast.LENGTH_LONG).show();

        }else if (id == R.id.nav_about) {

            Intent abbody_account = new Intent(MainActivity.this, tw.edu.niu.keepmoney20.menu_about.class);
            abbody_account.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(abbody_account);
            Toast.makeText(this, "關於" ,Toast.LENGTH_LONG).show();

        }else if (id == R.id.nav_share) {

            Intent shareintent = new Intent(Intent.ACTION_SEND);
            shareintent.setType("text/plain");
            String shareBody = "Your body here";
            String shareSub = "Your Subject here";
            shareintent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
            shareintent.putExtra(Intent.EXTRA_TEXT,shareBody);
            startActivity(Intent.createChooser(shareintent, "分享至"));

        }else if (id == R.id.nav_evaluation) {
            Uri urishareaddress = Uri.parse(EvaluationAddress);
            Intent shareus=new Intent(Intent.ACTION_VIEW,urishareaddress);
            startActivity(shareus);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //設定時間
     public void startDate(View view) {
        new DatePickerDialog(
                MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                textView.setText(year+"年"+(month+1) + "月" + day + "日");
                String tmpM,tmpD;
                if( (month+1)<10 ){
                    tmpM="0"+ Integer.toString(month+1);
                }else{
                    tmpM= Integer.toString(month+1);
                }
                if( (day)<10 ){
                    tmpD="0"+ Integer.toString(day);
                }else{
                    tmpD= Integer.toString(day);
                }
                whichDate=Integer.toString(year) + tmpM + tmpD;
                begin_lv=true;
                thread("SELECT * FROM `test`" + " WHERE `date`='"+ whichDate +"'" , "ans");

                //改寫下一次點到進入開始的日期
                mYear=year; mMonth=month; mDay=day;
            }
        }, mYear, mMonth, mDay).show();

    }


    //語音講話結果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    saywhat.setText(result.get(0));
                    String analysisVoiceResult=result.get(0);


                    Toast.makeText(MainActivity.this,analysisVoiceResult,Toast.LENGTH_LONG).show();
//                    把結果存入網路SQL
                    String s1="INSERT INTO `test`(`google_id`,`date`,`ntd`,`category`,`ans`) VALUES (";
                    String s2=")";
                    String sANS = analysisVoiceResult.substring(0,2) + "，花了" + analysisVoiceResult.substring(2) ;
                    String x = "'" + google_token + "'," + "'" + whichDate + "'," + "'" + analysisVoiceResult.substring(0,2) + "'," + "'" + analysisVoiceResult.substring(2) + "'," + "'" + sANS + "'" ;

                    begin_lv=true;
                    thread(s1+x+s2,"");
                    Toast.makeText(MainActivity.this,"新增成功",Toast.LENGTH_LONG).show();

                    //存完刷新lv
                    begin_lv=true;
                    thread("SELECT * FROM `test`" + " WHERE `date`='"+ whichDate +"'" , "ans");

                }
                break;
            }
        }
    }


//#########################網路資料庫###########################
    private void thread(final String jsonsql, final String key){
        new Thread(){
            Json js = new Json();
            @Override
            public void run() {
                super.run();
                //從新增頁面接收訊息
                Intent intent2=getIntent();
                String s2=intent2.getStringExtra("ADD_NEW");

                if( (s2=="OK") || (begin_lv==true) ) {
                    try {
                        //可以的
                        String sql = js.parseJSON("http://203.145.206.45/select.php", "203.145.206.45", "bdlab", "bdlab", "keepmoney", jsonsql);

                        //把每一欄 存近來
                        if (key != null) {   //沒有輸入key 為新增時
                            id = js.jsonkey(sql, "id");
                            google_id = js.jsonkey(sql, "google_id");
                            dates = js.jsonkey(sql, "date");
                            ntd = js.jsonkey(sql, "ntd");
                            category = js.jsonkey(sql, "category");
                            ans = js.jsonkey(sql, "ans");
                        }

                        handler.sendEmptyMessage(0);   //呼叫handler

                        //歸零
                        s2="ERRO";
                        begin_lv=false;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(ans!=null){
                Toast.makeText(MainActivity.this, "k=" + Integer.toString(ans.length), Toast.LENGTH_SHORT).show();
                testANS=ans;
//                testANS[0]= "安安"+testANS[0];

                for(int k=0; k<ans.length;k++){
                    testANS[k]=dates[k]+ "， " + ans[k];
                }
            }
            Toast.makeText(MainActivity.this, "更新List中", Toast.LENGTH_SHORT).show();
            if(ans!=null) {
                lv.setAdapter(null);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, testANS);
                lv.setAdapter(adapter);
            }else{
                String s[] ={"今天沒有記錄哦!"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, s);
                lv.setAdapter(adapter);
            }
        }
    };
//##############################################################

}
