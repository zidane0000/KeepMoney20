package tw.edu.niu.keepmoney20;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.lang.String;

public class NewScreen extends AppCompatActivity{

    private static String DATABASE_TABLE = "money";
    private SQLiteDatabase db;
    private StdDBHelper dbHelper;

    private TextView txtDate, output;


    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button buttonDelete;
    private Button buttonEnter;
    private  TextView howmuch;

    private void findViews(){
        txtDate = (TextView)findViewById(R.id.textView);
        output = (TextView)findViewById(R.id.textViewshow);
        //edtPrice = (EditText)findViewById(R.id.editText);
        //edtCategory = (EditText)findViewById(R.id.editText2);
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

        button0 = (Button) findViewById(R.id.computer0);
        button1 = (Button) findViewById(R.id.computer1);
        button2 = (Button) findViewById(R.id.computer2);
        button3 = (Button) findViewById(R.id.computer3);
        button4 = (Button) findViewById(R.id.computer4);
        button5 = (Button) findViewById(R.id.computer5);
        button6 = (Button) findViewById(R.id.computer6);
        button7 = (Button) findViewById(R.id.computer7);
        button8 = (Button) findViewById(R.id.computer8);
        button9 = (Button) findViewById(R.id.computer9);
        buttonDelete = (Button) findViewById(R.id.computerDelete);
        buttonEnter = (Button) findViewById(R.id.computerEnter);
        howmuch = (TextView) findViewById(R.id.textPrice);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String howmuchstr = howmuch.getText().toString();
                if(howmuch.length()==0)
                {
                    howmuch.setText("0");
                }else{
                    String newhowmuch = howmuchstr + "0";
                    howmuch.setText(newhowmuch);
                }
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String howmuchstr = howmuch.getText().toString();
                if(howmuch.length()==0)
                {
                    howmuch.setText("1");
                }else{
                    String newhowmuch = howmuchstr + "1";
                    howmuch.setText(newhowmuch);
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String howmuchstr = howmuch.getText().toString();
                if(howmuch.length()==0)
                {
                    howmuch.setText("2");
                }else{
                    String newhowmuch = howmuchstr + "2";
                    howmuch.setText(newhowmuch);
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String howmuchstr = howmuch.getText().toString();
                if(howmuch.length()==0)
                {
                    howmuch.setText("3");
                }else{
                    String newhowmuch = howmuchstr + "3";
                    howmuch.setText(newhowmuch);
                }
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String howmuchstr = howmuch.getText().toString();
                if(howmuch.length()==0)
                {
                    howmuch.setText("4");
                }else{
                    String newhowmuch = howmuchstr + "4";
                    howmuch.setText(newhowmuch);
                }
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String howmuchstr = howmuch.getText().toString();
                if(howmuch.length()==0)
                {
                    howmuch.setText("5");
                }else{
                    String newhowmuch = howmuchstr + "5";
                    howmuch.setText(newhowmuch);
                }
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String howmuchstr = howmuch.getText().toString();
                if(howmuch.length()==0)
                {
                    howmuch.setText("6");
                }else{
                    String newhowmuch = howmuchstr + "6";
                    howmuch.setText(newhowmuch);
                }
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String howmuchstr = howmuch.getText().toString();
                if(howmuch.length()==0)
                {
                    howmuch.setText("7");
                }else{
                    String newhowmuch = howmuchstr + "7";
                    howmuch.setText(newhowmuch);
                }
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String howmuchstr = howmuch.getText().toString();
                if(howmuch.length()==0)
                {
                    howmuch.setText("8");
                }else{
                    String newhowmuch = howmuchstr + "8";
                    howmuch.setText(newhowmuch);
                }
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String howmuchstr = howmuch.getText().toString();
                if(howmuch.length()==0)
                {
                    howmuch.setText("9");
                }else{
                    String newhowmuch = howmuchstr + "9";
                    howmuch.setText(newhowmuch);
                }
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String howmuchstr = howmuch.getText().toString();
                howmuch.setText(howmuchstr.substring(0,howmuch.length()-1));
            }
        });

        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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

//    public void submit_click(View view) {
//        long id;
//        ContentValues cv = new ContentValues();
//        cv.put("date", txtDate.getText().toString());
//        cv.put("price", Integer.parseInt(edtPrice.getText().toString()));
//        cv.put("category", edtCategory.getText().toString());
//        id = db.insert(DATABASE_TABLE, null, cv);
//        output.setText("新增紀錄成功!  ID:" + id);
//    }

}