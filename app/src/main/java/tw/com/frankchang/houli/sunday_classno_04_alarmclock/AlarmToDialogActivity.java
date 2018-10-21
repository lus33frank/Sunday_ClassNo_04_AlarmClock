package tw.com.frankchang.houli.sunday_classno_04_alarmclock;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AlarmToDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmtodialog);

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setIcon(R.drawable.clock)
                .setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_message)
                .setPositiveButton(getString(R.string.dialog_btn), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlarmToDialogActivity.this.finish();
                    }
                });
        builder.create();
        builder.show();
    }
}
