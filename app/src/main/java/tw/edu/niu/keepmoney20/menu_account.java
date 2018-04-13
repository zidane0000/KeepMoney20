package tw.edu.niu.keepmoney20;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;



import java.util.ArrayList;

import im.dacer.androidcharts.PieHelper;
import im.dacer.androidcharts.PieView;

public class menu_account extends AppCompatActivity {


    private ListView toaccount_listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> toaccount_listview_data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_account);
        //強制鎖定為直屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        PieView pieView = (PieView)findViewById(R.id.pie_view);
        ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();
        pieHelperArrayList.add(new PieHelper(20, Color.BLACK));
        pieHelperArrayList.add(new PieHelper(6));
        pieHelperArrayList.add(new PieHelper(30));
        pieHelperArrayList.add(new PieHelper(12));
        pieHelperArrayList.add(new PieHelper(32));
        pieView.setDate(pieHelperArrayList);
        pieView.showPercentLabel(false); //是否顯示百分比

        // 準備資料，塞3個項目到ArrayList裡
        for(int i = 0; i < 3; i++) {
            toaccount_listview_data.add("項目"+i);
        }
        /* 初始Adapter
        *  第一個參數context
        *  第二個參數是列的外觀，這邊用android內建的
        *  第三個參數是要顯示的資料，即上面準備好的mData
        */
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, toaccount_listview_data);
        // 連結元件
        toaccount_listView = (ListView) findViewById(R.id.account_listview);
        // 設置adapter給listview
        toaccount_listView.setAdapter(adapter);

    }


}