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
        button0.setOnClickListener(btnListener);
        button1 = (Button) findViewById(R.id.computer1);
        button1.setOnClickListener(btnListener);
        button2 = (Button) findViewById(R.id.computer2);
        button2.setOnClickListener(btnListener);
        button3 = (Button) findViewById(R.id.computer3);
        button3.setOnClickListener(btnListener);
        button4 = (Button) findViewById(R.id.computer4);
        button4.setOnClickListener(btnListener);
        button5 = (Button) findViewById(R.id.computer5);
        button5.setOnClickListener(btnListener);
        button6 = (Button) findViewById(R.id.computer6);
        button6.setOnClickListener(btnListener);
        button7 = (Button) findViewById(R.id.computer7);
        button7.setOnClickListener(btnListener);
        button8 = (Button) findViewById(R.id.computer8);
        button8.setOnClickListener(btnListener);
        button9 = (Button) findViewById(R.id.computer9);
        button9.setOnClickListener(btnListener);
        buttonDelete = (Button) findViewById(R.id.computerDelete);
        buttonDelete.setOnClickListener(btnListener);
        buttonEnter = (Button) findViewById(R.id.computerEnter);
        buttonEnter.setOnClickListener(btnListener);
        howmuch = (TextView) findViewById(R.id.textPrice);

    }

    private  Button.OnClickListener btnListener = new Button.OnClickListener(){
        public void onClick(View V){

            String howmuchstr,newhowmuch;
            switch (V.getId()){
                case R.id.computer0:
                    howmuchstr = howmuch.getText().toString();
                    if(howmuch.length()==0){   howmuch.setText("0");   }
                    else{   newhowmuch = howmuchstr + "0";howmuch.setText(newhowmuch);  }
                    break;
                case R.id.computer1:
                    howmuchstr = howmuch.getText().toString();
                    if(howmuch.length()==0){   howmuch.setText("1");   }
                    else{   newhowmuch = howmuchstr + "1";howmuch.setText(newhowmuch);  }
                    break;
                case R.id.computer2:
                    howmuchstr = howmuch.getText().toString();
                    if(howmuch.length()==0){   howmuch.setText("2");   }
                    else{   newhowmuch = howmuchstr + "2";howmuch.setText(newhowmuch);  }
                    break;
                case R.id.computer3:
                    howmuchstr = howmuch.getText().toString();
                    if(howmuch.length()==0){   howmuch.setText("3");   }
                    else{   newhowmuch = howmuchstr + "3";howmuch.setText(newhowmuch);  }
                    break;
                case R.id.computer4:
                    howmuchstr = howmuch.getText().toString();
                    if(howmuch.length()==0){   howmuch.setText("4");   }
                    else{   newhowmuch = howmuchstr + "4";howmuch.setText(newhowmuch);  }
                    break;
                case R.id.computer5:
                    howmuchstr = howmuch.getText().toString();
                    if(howmuch.length()==0){   howmuch.setText("5");   }
                    else{   newhowmuch = howmuchstr + "5";howmuch.setText(newhowmuch);  }
                    break;
                case R.id.computer6:
                    howmuchstr = howmuch.getText().toString();
                    if(howmuch.length()==0){   howmuch.setText("6");   }
                    else{   newhowmuch = howmuchstr + "6";howmuch.setText(newhowmuch);  }
                    break;
                case R.id.computer7:
                    howmuchstr = howmuch.getText().toString();
                    if(howmuch.length()==0){   howmuch.setText("7");   }
                    else{   newhowmuch = howmuchstr + "7";howmuch.setText(newhowmuch);  }
                    break;
                case R.id.computer8:
                    howmuchstr = howmuch.getText().toString();
                    if(howmuch.length()==0){   howmuch.setText("8");   }
                    else{   newhowmuch = howmuchstr + "8";howmuch.setText(newhowmuch);  }
                    break;
                case R.id.computer9:
                    howmuchstr = howmuch.getText().toString();
                    if(howmuch.length()==0){   howmuch.setText("9");   }
                    else{   newhowmuch = howmuchstr + "9";howmuch.setText(newhowmuch);  }
                    break;
                case R.id.computerDelete:
                    howmuchstr = howmuch.getText().toString();
                    if(howmuchstr.length()>0)howmuch.setText(howmuchstr.substring(0,howmuch.length()-1));
                    break;
                case R.id.computerEnter:

            }
        }
    };

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