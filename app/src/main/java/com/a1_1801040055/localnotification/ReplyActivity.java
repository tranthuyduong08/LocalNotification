package com.a1_1801040055.localnotification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static com.a1_1801040055.localnotification.NotificationService.REPLY_ACTION;


public class ReplyActivity extends AppCompatActivity {

    private static final String KEY_MESSAGE_ID = "key_message_id";
    private static final String KEY_NOTIFY_ID = "key_notify_id";

    private int MessageId;
    private int NotifyId;

    private ImageButton SendButton;
    private EditText EditReply;

    public static Intent getReplyMessageIntent(Context context, int notifyId, int messageId) {
        Intent intent = new Intent(context, ReplyActivity.class);
        intent.setAction(REPLY_ACTION);
        intent.putExtra(KEY_MESSAGE_ID, messageId);
        intent.putExtra(KEY_NOTIFY_ID, notifyId);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

        Intent intent = getIntent();

        if (REPLY_ACTION.equals(intent.getAction())) {
            MessageId = intent.getIntExtra(KEY_MESSAGE_ID, 0);
            NotifyId = intent.getIntExtra(KEY_NOTIFY_ID, 0);
        }

        EditReply = (EditText) findViewById(R.id.edit_reply);
        SendButton = (ImageButton) findViewById(R.id.button_send);

        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(NotifyId, MessageId);
            }
        });
    }

    private void sendMessage(int notifyId,  int messageId) {
        // update notification
        updateNotification(notifyId);

        String message = EditReply.getText().toString().trim();
        // handle send message
        Toast.makeText(this, "Message ID: " + messageId + "\nMessage: " + message,
                Toast.LENGTH_SHORT).show();

        finish();
    }

    private void updateNotification(int notifyId) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_notif_smile)
                .setContentText("Notification");

        notificationManager.notify(notifyId, builder.build());
    }
}
