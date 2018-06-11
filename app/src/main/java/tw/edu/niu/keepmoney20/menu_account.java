package tw.edu.niu.keepmoney20;

import android.app.Activity;
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

public class menu_account extends Activity {
    //#################網路資料庫的 MySQL#########################
    ListView lv;
    String google_token="offline";
    String[] id,ntd,google_id,dates,category,ans;
    int i;  //listviews紀錄點到哪個
    boolean begin_lv =true;

    TextView textViewYearMonth;

    TextView cost,earn;
    int thisMonthCost=0;

    //查詢哪天
    String whichDate;
    String whichYearMonth;

    //紀錄點選時間
    int mYear, mMonth;
    //############################################################

    //圓餅圖種類
    PieView pieView;
    int total_All;
    float total_food,total_income,total_entertainment,total_traffic,totoal_3c,totoal_medical,total_house,total_other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_account);

        //強制鎖定為直屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //設定標題
        setTitle("帳戶");

        earn=findViewById(R.id.account_income);
        cost=findViewById(R.id.account_expenses);
        total_All=0;
        total_food=0;total_income=0;total_entertainment=0;total_traffic=0;totoal_3c=0;totoal_medical=0;total_house=0;total_other=0;

        //圓餅圖
        pieView = (PieView)findViewById(R.id.pie_view);


//#########################網路資料庫###########################
        //取得當天的   月份!
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mMonth++;
        if(mMonth<10){ whichYearMonth=Integer.toString(mYear) + "0" + Integer.toString(mMonth); }
        else{ whichYearMonth=Integer.toString(mYear) + Integer.toString(mMonth); }

        textViewYearMonth = findViewById(R.id.accountdayshow);
        textViewYearMonth.setText(Integer.toString(mYear) + "年  " + Integer.toString(mMonth) + "月");
        lv = findViewById(R.id.account_listview);
        thread("SELECT * FROM `test`" + " WHERE date LIKE '" + whichYearMonth + "%'"  , "date");
//##############################################################

    }

    //按下按鈕 下一個月
    public void RH(View view) {
        mMonth++;
        if(mMonth>12){
            mYear++;
            mMonth=1;
        }
        textViewYearMonth.setText(Integer.toString(mYear) + "年  " + Integer.toString(mMonth) + "月");

        if(mMonth<10){ whichYearMonth=Integer.toString(mYear) + "0" + Integer.toString(mMonth); }
        else{ whichYearMonth=Integer.toString(mYear) + Integer.toString(mMonth); }
        begin_lv=true;
        thread("SELECT * FROM `test`" + " WHERE date LIKE '" + whichYearMonth + "%'"  , "date");
    }
    //按下按鈕  上一個月
    public void LF(View view) {
        mMonth--;
        if(mMonth<1){
            mYear--;
            mMonth=12;
        }
        textViewYearMonth.setText(Integer.toString(mYear) + "年  " + Integer.toString(mMonth) + "月");

        if(mMonth<10){ whichYearMonth=Integer.toString(mYear) + "0" + Integer.toString(mMonth); }
        else{ whichYearMonth=Integer.toString(mYear) + Integer.toString(mMonth); }
        begin_lv=true;
        thread("SELECT * FROM `test`" + " WHERE date LIKE '" + whichYearMonth + "%'"  , "date");
    }

//#########################網路資料庫###########################
    private void thread(final String jsonsql, final String key){
        new Thread(){
            Json js = new Json();
            @Override
            public void run() {
                super.run();
                if( (begin_lv==true) ) {
                    try {
                        //連進SQL
                        String sql = js.parseJSON("http://203.145.206.45/select.php", "203.145.206.45", "bdlab", "bdlab", "keepmoney", jsonsql);

                        //把每一欄 存近來
                        id = js.jsonkey(sql, "id");
                        google_id = js.jsonkey(sql, "google_id");
                        dates = js.jsonkey(sql, "date");
                        ntd = js.jsonkey(sql, "ntd");
                        category = js.jsonkey(sql, "category");
                        ans = js.jsonkey(sql, "ans");

                        //
                        //歸零
                        begin_lv=false;

                        handler.sendEmptyMessage(0);   //呼叫handler

                    } catch (Exception e) { e.printStackTrace(); }
                }
            }
        }.start();
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(dates!=null) {
                //            圓餅圖
                for(int i=0;i < category.length; i++)
                {
                    if(category[i].equals("食物")) {
                        total_food = total_food + Integer.parseInt(ntd[i]);
                        total_All = total_All + Integer.parseInt(ntd[i]);
                    }else if(category[i].equals("收入")){
                        total_income = total_income + Integer.parseInt(ntd[i]);
                        total_All = total_All + Integer.parseInt(ntd[i]);
                    }else if(category[i].equals("娛樂")){
                        total_entertainment = total_entertainment + Integer.parseInt(ntd[i]);
                        total_All = total_All + Integer.parseInt(ntd[i]);
                    }else if(category[i].equals("交通")){
                        total_traffic = total_traffic + Integer.parseInt(ntd[i]);
                        total_All = total_All + Integer.parseInt(ntd[i]);
                    }else if(category[i].equals("3C")){
                        totoal_3c = totoal_3c + Integer.parseInt(ntd[i]);
                        total_All = total_All + Integer.parseInt(ntd[i]);
                    }else if(category[i].equals("醫療")){
                        totoal_medical = totoal_medical + Integer.parseInt(ntd[i]);
                        total_All = total_All + Integer.parseInt(ntd[i]);
                    }else if(category[i].equals("居家")){
                        total_house = total_house + Integer.parseInt(ntd[i]);
                        total_All = total_All + Integer.parseInt(ntd[i]);
                    }else if(category[i].equals("其他")){
                        total_other = total_other + Integer.parseInt(ntd[i]);
                        total_All = total_All + Integer.parseInt(ntd[i]);
                    }
                }
                ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();
//            pieHelperArrayList.add(new PieHelper((total_income/total_All*100), Color.rgb(2,2,2)));  //收入另外計算
                if(total_food != 0){
                    pieHelperArrayList.add(new PieHelper( (total_food/total_All*100) , Color.rgb(0,0,255)));
                }else if(total_entertainment != 0){
                    pieHelperArrayList.add(new PieHelper((total_entertainment/total_All*100), Color.rgb(255,20,147)));
                }else if(total_traffic != 0){
                    pieHelperArrayList.add(new PieHelper((total_traffic/total_All*100), Color.rgb(127,255,0) ));
                }else if(totoal_3c != 0){
                    pieHelperArrayList.add(new PieHelper((totoal_3c/total_All*100), Color.rgb(155,48,255)));
                }else if(totoal_medical != 0){
                    pieHelperArrayList.add(new PieHelper((totoal_medical/total_All*100), Color.rgb(255,250,250)));
                }else if(total_house != 0){
                    pieHelperArrayList.add(new PieHelper((total_house/total_All*100), Color.rgb(255,255,0)));
                }else if(total_other != 0){
                    pieHelperArrayList.add(new PieHelper((total_other/total_All*100), Color.rgb(255,165,0)));
                }
                pieView.setDate(pieHelperArrayList);
                pieView.showPercentLabel(true); //是否顯示百分比
                //下一次 歸零
                total_All=0;
                total_food=0;total_income=0;total_entertainment=0;total_traffic=0;totoal_3c=0;totoal_medical=0;total_house=0;total_other=0;

                String lvShowArray[];
                lvShowArray=dates;
                for(int k=0; k<dates.length;k++){
                    lvShowArray[k]=dates[k]+ "， " + category[k] + "，花了 " + ntd[k] + "元";

                    //這個月的支出
                    try {
                        thisMonthCost = thisMonthCost + Integer.valueOf(ntd[k]);
                    }catch (Exception e){ }
                }
                cost.setText("本月支出：" + Integer.toString(thisMonthCost));
                earn.setText("本月收入：");
                thisMonthCost=0;
                lv.setAdapter(null);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(menu_account.this, android.R.layout.simple_list_item_1, lvShowArray);
                lv.setAdapter(adapter);
                Toast.makeText(menu_account.this, "更新List中 From 網路SQL", Toast.LENGTH_SHORT).show();
            }else{
                String s[] ={"這個月沒有記錄哦!"};

                cost.setText("本月支出：" + Integer.toString(thisMonthCost));
                thisMonthCost=0;

                lv.setAdapter(null);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(menu_account.this, android.R.layout.simple_list_item_1, s);
                lv.setAdapter(adapter);
                Toast.makeText(menu_account.this, "更新List中 From 網路SQL 這個月沒有記錄哦~", Toast.LENGTH_SHORT).show();
            }
        }
    };
//##############################################################
}