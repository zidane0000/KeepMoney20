package tw.edu.niu.keepmoney20;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import im.dacer.androidcharts.PieHelper;
import im.dacer.androidcharts.PieView;

public class menu_account extends AppCompatActivity {
//#################網路資料庫的 MySQL#########################
    ListView lv;
    String google_token="fox850907";
    String[] id,ntd,google_id,dates,category,ans;
    int i;  //listviews紀錄點到哪個
    boolean begin_lv =true;

    TextView textViewYearMonth;

    //查詢哪天
    String whichDate;
    String whichYearMonth;

    //紀錄點選時間
    int mYear, mMonth;
//############################################################

    //基本功 宣告物件
    //三個一起的
//    private ListView toaccount_listView;  //紀錄的listview
//    private ArrayAdapter<String> adapter;
//    private ArrayList<String> toaccount_listview_data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_account);

        //強制鎖定為直屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //設定標題
        setTitle("帳戶");

        //圓餅圖
        PieView pieView = (PieView)findViewById(R.id.pie_view);
        ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();
        pieHelperArrayList.add(new PieHelper(20, Color.BLACK));
        pieHelperArrayList.add(new PieHelper(6));
        pieHelperArrayList.add(new PieHelper(30));
        pieHelperArrayList.add(new PieHelper(12));
//        pieHelperArrayList.add(new PieHelper(32));
        pieView.setDate(pieHelperArrayList);
        pieView.showPercentLabel(true); //是否顯示百分比

//#########################網路資料庫###########################
        //取得當天的日期
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mMonth++;
        if(mMonth<10){
            whichYearMonth=Integer.toString(mYear) + "0" + Integer.toString(mMonth);
        }else{
            whichYearMonth=Integer.toString(mYear) + Integer.toString(mMonth);
        }

        textViewYearMonth = findViewById(R.id.accountdayshow);
        textViewYearMonth.setText(Integer.toString(mYear) + "年  " + Integer.toString(mMonth) + "月");
        lv = findViewById(R.id.account_listview);
        thread("SELECT * FROM `test`" + " WHERE date LIKE '" + whichYearMonth + "%'"  , "ans");

//##############################################################

        //舊的LV
//        // 準備資料，塞3個項目到ArrayList裡
//        for(int i = 0; i < 3; i++) {
//            toaccount_listview_data.add("項目"+i);
//        }
//        /* 初始Adapter
//        *  第一個參數context
//        *  第二個參數是列的外觀，這邊用android內建的
//        *  第三個參數是要顯示的資料，即上面準備好的mData
//        */
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, toaccount_listview_data);
//        // 連結元件
//        toaccount_listView = (ListView) findViewById(R.id.account_listview);
//        // 設置adapter給listview
//        toaccount_listView.setAdapter(adapter);

    }

    //按下按鈕 下一個月
    public void RH(View view) {
        mMonth++;
        if(mMonth>12){
            mYear++;
            mMonth=1;
        }

        textViewYearMonth.setText(Integer.toString(mYear) + "年  " + Integer.toString(mMonth) + "月");

        if(mMonth<10){
            whichYearMonth=Integer.toString(mYear) + "0" + Integer.toString(mMonth);
        }else{
            whichYearMonth=Integer.toString(mYear) + Integer.toString(mMonth);
        }
        begin_lv=true;
        thread("SELECT * FROM `test`" + " WHERE date LIKE '" + whichYearMonth + "%'"  , "ans");

    }
    //按下按鈕  上一個月
    public void LF(View view) {
        mMonth--;
        if(mMonth<1){
            mYear--;
            mMonth=12;
        }

        textViewYearMonth.setText(Integer.toString(mYear) + "年  " + Integer.toString(mMonth) + "月");

        if(mMonth<10){
            whichYearMonth=Integer.toString(mYear) + "0" + Integer.toString(mMonth);
        }else{
            whichYearMonth=Integer.toString(mYear) + Integer.toString(mMonth);
        }
        begin_lv=true;
        thread("SELECT * FROM `test`" + " WHERE date LIKE '" + whichYearMonth + "%'"  , "ans");
    }

//#########################網路資料庫###########################
    private void thread(final String jsonsql, final String key){
        new Thread(){
            Json js = new Json();
            @Override
            public void run() {
                super.run();
                while( (begin_lv==true) ) {
                    try {
                        //可以的
                        String sql = js.parseJSON("http://203.145.206.45/select.php", "203.145.206.45", "bdlab", "bdlab", "keepmoney", jsonsql);

                        //把每一欄 存近來
                        if (key != null) {
                            id = js.jsonkey(sql, "id");
                            google_id = js.jsonkey(sql, "google_id");
                            dates = js.jsonkey(sql, "date");
                            ntd = js.jsonkey(sql, "ntd");
                            category = js.jsonkey(sql, "category");
                            ans = js.jsonkey(sql, "ans");
                        }

                        handler.sendEmptyMessage(0);   //呼叫handler

                        //歸零
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
            Toast.makeText(menu_account.this, "更新List", Toast.LENGTH_LONG).show();
            if(ans!=null) {
                lv.setAdapter(null);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(menu_account.this, android.R.layout.simple_list_item_1, ans);
                lv.setAdapter(adapter);
            }else{
                String s[] ={"這個月沒有記錄哦!"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(menu_account.this, android.R.layout.simple_list_item_1, s);
                lv.setAdapter(adapter);
            }
        }
    };
//##############################################################
}