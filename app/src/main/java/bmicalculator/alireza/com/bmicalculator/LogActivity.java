package bmicalculator.alireza.com.bmicalculator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;

public class LogActivity extends AppCompatActivity {
    LinkedList<Record> records = null;
    Paint paint;
    private DatePicker datePicker;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        //initilizeView();
        initilizeDate();

        datePicker.setOnTouchListener(new View.OnTouchListener() {
          @Override
          public boolean onTouch(View v, MotionEvent event) {
              if(event.getAction()>0){
                  String day = datePicker.getDayOfMonth() + "";
                  String month = datePicker.getMonth()+"";
                  String year = datePicker.getYear()+"";
                  text.setText(day+"-"+month+"-"+year);
              }
              return false;
          }
        });




    }
    public void initilizeDate(){
        datePicker = (DatePicker)findViewById(R.id.datePicker);
        text = (TextView) findViewById(R.id.textViewSelectedDate);
    }


    public void initilizeView(){
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(1);
        records = new LinkedList();
        //Date date = new Date();
        DateFormat df = new SimpleDateFormat("dd-mm-yy");
        //String mydate = df.format(date);
        //Date newDate = df.parse("12-01-2017");
        try {
            records.add(new Record(df.parse("12-01-2017"), 10));
            records.add(new Record(df.parse("12-02-2017"), 12));
            records.add(new Record(df.parse("12-03-2017"), 14));
            records.add(new Record(df.parse("12-04-2017"), 15));
            records.add(new Record(df.parse("12-05-2017"), 16));
            records.add(new Record(df.parse("12-06-2017"), 17));

        }catch(Exception e){
            e.printStackTrace();
        }
        setContentView(new Panel(this));
    }

    class Panel extends View{
        public Panel(Context context){
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            float baseX = 20;
            float baseY = 500;
            canvas.drawColor(Color.WHITE);
            paint.setColor(Color.BLACK);
            // axises
            //canvas.drawLine(baseX, baseY,baseX+200, baseY-200,paint);
            canvas.drawLine(baseX, baseY,baseX+200, baseY,paint);
            canvas.drawLine(baseX, baseY,baseX, baseY-200,paint);


            ListIterator  it =  records.listIterator();
            int lastX = 0,lastY = 0;
            while(it.hasNext()) {
                Record data = (Record) it.next();
                Date date = data.getDate();
                float weight = data.getWeight();
                Log.i("Data", new SimpleDateFormat("dd-mm-yyyy").format(date));
                int newX = 0, newY = 0;
                newY  = lastY + 20;
                newX  = (int)Math.round(weight/10);
                Log.i("Data", newX+"");

                if (lastX != 0 && lastY != 0) {
                    canvas.drawLine(lastX, lastY, baseX + newX, baseY - newY, paint);
                }
                lastX = (int)baseX + newX ; lastY = (int)baseY - newY;
            }

        }
    }


}


