package tw.edu.niu.keepmoney20;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.lang.String;

public class NewScreen extends Activity {

    //#########################網路資料庫的 MySQL##################################
    String google_token="offline";
    String whichDate;

    int mYear, mMonth ,mDay;
//#############################################################################


//##############################離線SQLite#####################################
    private static String DATABASE_TABLE = "money";
    private SQLiteDatabase db;
    private StdDBHelper dbHelper;
//#############################################################################

    private TextView txtDate, output;

    private Button button0,button1,button2,button3,button4,button5,button6;
    private Button button7,button8,button9,buttonDelete,buttonEnter;
    private Button button_f,button_i,button_3,button_m,button_e,button_t,button_o,button_h;
    private  TextView howmuch,textViewcate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_screen);
        txtDate = (TextView)findViewById(R.id.textView);
        output = (TextView)findViewById(R.id.textViewshow);

//#############################網路SQL#########################################
        //程式進入就顯示今天日期
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年M月d日");
        Date curDate = new Date(System.currentTimeMillis()) ; // 獲取當前時間
        String str = formatter.format(curDate);
        txtDate.setText(str);
        //取得今天的日期
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        //解決201869變成20180609
        String tmpM,tmpD;
        if( (mMonth+1)<10 ){ tmpM="0"+ Integer.toString(mMonth+1); }
        else{ tmpM= Integer.toString(mMonth+1); }
        if( (mDay)<10 ){ tmpD="0"+ Integer.toString(mDay); }
        else{ tmpD= Integer.toString(mDay); }

        whichDate=Integer.toString(mYear) + tmpM + tmpD;  //程式一開始顯示今天的日期 以便刷新今天的lv
//############################################################################


//#########################SQLite資料庫#######################################
        dbHelper = new StdDBHelper(this);
        db = dbHelper.getWritableDatabase();
        output.setText("資料庫是否開啟：" + db.isOpen() + "\n資料庫版本：" + db.getVersion());
//############################################################################

        //輸入金錢
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


        //種類
        button_f = findViewById(R.id.button_food);
        button_f.setOnClickListener(btnListener);
        button_i = findViewById(R.id.button_income);
        button_i.setOnClickListener(btnListener);
        button_t = findViewById(R.id.button_traffic);
        button_t.setOnClickListener(btnListener);
        button_e = findViewById(R.id.button_entertainment);
        button_e.setOnClickListener(btnListener);
        button_h = findViewById(R.id.button_house);
        button_h.setOnClickListener(btnListener);
        button_3 = findViewById(R.id.button_3C);
        button_3.setOnClickListener(btnListener);
        button_m = findViewById(R.id.button_medical);
        button_m.setOnClickListener(btnListener);
        button_o = findViewById(R.id.button_other);
        button_o.setOnClickListener(btnListener);
        textViewcate = findViewById(R.id.textCategory);
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
//###############################網路資料庫的 MySQL#################################
                    String s1="INSERT INTO `test`(`google_id`,`date`,`ntd`,`category`,`ans`) VALUES (";
                    String s2=")";
                    String x = "'" + google_token + "'," + "'" + whichDate + "'," + "'" + howmuch.getText().toString() + "'," + "'" + textViewcate.getText().toString() + "'," + "'" + "預刪除ans欄位" + "'" ;
                    if(! (howmuch.getText().toString().equals("")|| textViewcate.getText().toString().equals("")) ){   //如果框框裡有文字
                        thread(s1+ x +s2, "insert");   //新增資料所以key值空的
                        Toast.makeText(NewScreen.this,"新增成功",Toast.LENGTH_LONG).show();
                        howmuch.setText("");
                        textViewcate.setText("");
                        Intent intent = new Intent(NewScreen.this,MainActivity.class);
                        intent.putExtra("ADD_NEW","OK");
                        startActivity(intent);    //?重開acticity1
                        NewScreen.this.finish();
                    }else{
                        Toast.makeText(NewScreen.this,"輸入不完整，請重新輸入",Toast.LENGTH_SHORT).show();
                    }
//#################################################################################
                    break;
                case R.id.button_food:
                    textViewcate.setText("食物");break;
                case R.id.button_income:
                    textViewcate.setText("收入");break;
                case R.id.button_traffic:
                    textViewcate.setText("交通");break;
                case R.id.button_entertainment:
                    textViewcate.setText("娛樂");break;
                case R.id.button_house:
                    textViewcate.setText("居家");break;
                case R.id.button_3C:
                    textViewcate.setText("3C");break;
                case R.id.button_medical:
                    textViewcate.setText("醫療");break;
                case R.id.button_other:
                    textViewcate.setText("其他");break;
                default:break;
            }
        }
    };

    public void setDate(View view) {
        new DatePickerDialog(
                NewScreen.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                txtDate.setText(year+"年"+(month+1) + "月" + day + "日");
                String tmpM,tmpD;
                if( (month+1)<10 ){ tmpM="0"+ Integer.toString(month+1); }
                else{ tmpM= Integer.toString(month+1); }
                if( (day)<10 ){ tmpD="0"+ Integer.toString(day); }
                else{ tmpD= Integer.toString(day); }
                whichDate=Integer.toString(year) + tmpM + tmpD;

                //改寫下一次點到進入開始的日期
                mYear=year; mMonth=month; mDay=day;
            }
        }, mYear, mMonth, mDay).show();
    }


//#################網路資料庫的 MySQL#########################
    private void thread(final String jsonsql, final String key){
        new Thread(){
            Json js = new Json();
            @Override
            public void run() {
                super.run();
                try{
                    if(key=="insert"){
                        String sql = js.parseJSON("http://203.145.206.45/select.php", "203.145.206.45", "bdlab", "bdlab", "keepmoney", jsonsql);
                    }
                }catch (Exception e){ e.printStackTrace(); }
            }
        }.start();
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