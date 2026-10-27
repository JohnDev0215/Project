package com.android.server.telecom.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.telecom.Log;
import com.android.server.telecom.Call;
import com.android.server.telecom.CallsManagerListenerBase;
import com.android.server.telecom.R;

/* JADX INFO: loaded from: classes.dex */
public class AudioProcessingNotification extends CallsManagerListenerBase {
    private static final String NOTIFICATION_TAG = "AudioProcessingNotification";
    private Call mCallInAudioProcessing;
    private final Context mContext;
    private final NotificationManager mNotificationManager;

    public AudioProcessingNotification(Context context) {
        this.mContext = context;
        this.mNotificationManager = (NotificationManager) context.getSystemService("notification");
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCallStateChanged(Call call, int i, int i2) {
        if (i2 == 12 && i != 12) {
            showAudioProcessingNotification(call);
        } else {
            if (i != 12 || i2 == 12) {
                return;
            }
            cancelAudioProcessingNotification();
        }
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCallAdded(Call call) {
        if (call.getState() == 12) {
            showAudioProcessingNotification(call);
        }
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCallRemoved(Call call) {
        if (call == this.mCallInAudioProcessing) {
            cancelAudioProcessingNotification();
        }
    }

    private void showAudioProcessingNotification(Call call) {
        Log.i(this, "showAudioProcessingNotification", new Object[0]);
        this.mCallInAudioProcessing = call;
        Notification.Builder builder = new Notification.Builder(this.mContext, "TelecomBackgroundAudioProcessing");
        builder.setSmallIcon(R.drawable.ic_phone).setColor(this.mContext.getResources().getColor(R.color.theme_color)).setContentTitle(this.mContext.getText(R.string.notification_audioProcessing_title)).setStyle(new Notification.BigTextStyle().bigText(this.mContext.getString(R.string.notification_audioProcessing_body, call.getAudioProcessingRequestingApp()))).setOngoing(true);
        builder.build();
    }

    private void cancelAudioProcessingNotification() {
        this.mNotificationManager.cancel(NOTIFICATION_TAG, 2);
    }
}
