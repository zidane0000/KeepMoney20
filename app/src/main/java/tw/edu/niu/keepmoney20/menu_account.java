package tw.edu.niu.keepmoney20;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class menu_account extends AppCompatActivity {

    private float[] price= {1,2,3};
    private String[] xData={"食物","交通","其他"};
    PieChart menu_account_pie;
    private ListView toaccount_listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> toaccount_listview_data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_account);
        //強制鎖定為直屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        menu_account_pie = (PieChart) findViewById(R.id.idPieChart);

        menu_account_pie.setRotationEnabled(true);//不可旋轉

        Description description = new Description();
        description.setText("");
        menu_account_pie.setDescription(description);//不顯示左下角的字

        //pieChart.setUsePercentValues(true);
        //pieChart.setHoleColor(Color.BLUE);
        menu_account_pie.setHoleRadius(0f);//中心不留空洞
        menu_account_pie.setTransparentCircleAlpha(0);//不需要有透明的第二個小圈
        //menu_account_pie.setCenterText("Super Cool Chart");
        //menu_account_pie.setCenterTextSize(5);
        //pieChart.setDrawEntryLabels(true);
        //pieChart.setEntryLabelTextSize(20);
        //More options just check out the documentation!

        addDataSet();

        //案下去會有字浮出來
        menu_account_pie.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
//                int pos1 = e.toString().indexOf("(sum): ");
//                String sales = e.toString().substring(pos1 + 2);
//
//                for(int i = 0; i < price.length; i++){
//                    if(price[i] == Float.parseFloat(sales)){
//                        pos1 = i;
//                        break;
//                    }
//                }
//                String employee = xData[pos1 + 1];
//                Toast.makeText(menu_account.this, "Employee "  + "\n" + "Sales: $"  + "K", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

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

    private void addDataSet() {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < price.length; i++){
            yEntrys.add(new PieEntry(price[i] , i));
        }

        for(int i = 1; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.GRAY);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);

        pieDataSet.setColors(colors);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        menu_account_pie.setData(pieData);
        menu_account_pie.invalidate();
    }
}