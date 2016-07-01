package com.amin.demo.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = Constants.TAG + "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Handle possible data accompanying notification message.
        // [START handle_data_extras]
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                String value = getIntent().getExtras().getString(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }

        if(TextUtils.isEmpty(FirebaseInstanceId.getInstance().getToken())){
            FirebaseMessaging.getInstance().subscribeToTopic("news");
        }else{
            Log.d(TAG, "InstanceID token: " + FirebaseInstanceId.getInstance().getToken());

            TextView fcmTokenView = (TextView) findViewById(R.id.fcmToken_View);
            if (fcmTokenView != null) {
                fcmTokenView.setText(FirebaseInstanceId.getInstance().getToken());
            }
        }
    }
}
