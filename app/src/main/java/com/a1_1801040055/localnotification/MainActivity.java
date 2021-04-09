package com.a1_1801040055.localnotification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String ARG_REPLY = "arg_reply";
    private Button btnNoti;


    public static Intent getReplyMessageIntent(Context context, String reply) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(ARG_REPLY, reply);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNoti = (Button) findViewById(R.id.button_show_notification);

        btnNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotification();
            }
        });
    }

    private void showNotification() {
        startService(new Intent(this, NotificationService.class));
    }
}