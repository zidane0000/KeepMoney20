package tw.edu.niu.keepmoney20;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewScreen extends AppCompatActivity {

    private static String DATABASE_TABLE = "money";
    private SQLiteDatabase db;
    private StdDBHelper dbHelper;

    private TextView txtDate, output;
    private EditText edtPrice, edtCategory;

    private void findViews(){
        txtDate = (TextView)findViewById(R.id.textView);
        output = (TextView)findViewById(R.id.textView4);
        edtPrice = (EditText)findViewById(R.id.editText);
        edtCategory = (EditText)findViewById(R.id.editText2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_screen);
        findViews();

        //程式進入就顯示今天日期
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年M月d日");
        Date curDate = new Date(System.currentTimeMillis()) ; // 獲取當前時間
        String str = formatter.format(curDate);
        txtDate.setText(str);

        //SQLite資料庫
        dbHelper = new StdDBHelper(this);
        db = dbHelper.getWritableDatabase();
        output.setText("資料庫是否開啟：" + db.isOpen() + "\n資料庫版本：" + db.getVersion());
    }

    public void setDate(View view) {
        Calendar c = Calendar.getInstance();
        int mYear, mMonth, mDay;
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(
                NewScreen.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                txtDate.setText(year+"年"+(month+1) + "月" + day + "日");
            }
        }, mYear, mMonth, mDay).show();
    }

    public void submit_click(View view) {
        long id;
        ContentValues cv = new ContentValues();
        cv.put("date", txtDate.getText().toString());
        cv.put("price", Integer.parseInt(edtPrice.getText().toString()));
        cv.put("category", edtCategory.getText().toString());
        id = db.insert(DATABASE_TABLE, null, cv);
        output.setText("新增紀錄成功!  ID:" + id);
    }
}