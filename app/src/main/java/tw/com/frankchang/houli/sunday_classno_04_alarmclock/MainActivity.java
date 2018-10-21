package tw.com.frankchang.houli.sunday_classno_04_alarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView tvTime01, tvTime02;

    private Calendar calendar = Calendar.getInstance();
    private PendingIntent pendings;
    private AlarmManager alarmManager;

    private final int INTENT_ID = 8888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findviewer();
    }

    private void findviewer() {
        tvTime01 = (TextView) findViewById(R.id.textView);
        tvTime02 = (TextView) findViewById(R.id.textView2);
    }

    public void setOnClick(View view) {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);

        TimePickerDialog tpDialog = new TimePickerDialog(this, timeSetListener, hour, minutes, true);
        tpDialog.show();
    }

    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            //設定指定時間
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);

            //發送指定時間給AlarmManager
            Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
            pendings = PendingIntent.getBroadcast(MainActivity.this, INTENT_ID, alarmIntent, 0);

            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendings);

            //設定指定的時間於畫面上
            tvTime01.setText(dateFormat(hourOfDay) + "：" + dateFormat(minute));
            tvTime02.setText(R.string.main_Text_01);
        }
    };

    private String dateFormat(int times){
        String dateShow = "" + times;

        if (dateShow.length() == 1){
            dateShow = "0" + dateShow;
        }

        return dateShow;
    }

    public void cancelOnClick(View view) {
        if (alarmManager == null){
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }

        alarmManager.cancel(pendings);
        tvTime01.setText("");
        tvTime02.setText(getString(R.string.xml_text_02));
    }
}
