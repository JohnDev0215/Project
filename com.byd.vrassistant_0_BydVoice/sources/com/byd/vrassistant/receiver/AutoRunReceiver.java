package com.byd.vrassistant.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.byd.vrassistant.eventbus.SystemStatusEvent;
import com.byd.vrassistant.utils.RLog;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes2.dex */
public class AutoRunReceiver extends BroadcastReceiver {
    public static String TAG = "NEU_AutoRunReceiver";
    String BYDVOICE_BROADCAST_AUTOBATCH_START = "bydvoice.broadcast.autobatch.start";
    String BYDVOICE_BROADCAST_AUTOBATCH_PCM = "bydvoice.broadcast.autobatch.pcm";
    String BYDVOICE_BROADCAST_AUTOBATCH_STOP = "bydvoice.broadcast.autobatch.stop";
    String BYDVOICE_BROADCAST_RECORD_START = "bydvoice.broadcast.record.start";
    String BYDVOICE_BROADCAST_RECORD_STOP = "bydvoice.broadcast.record.stop";
    String BYDVOICE_BROADCAST_LOG_ON = "com.byd.voice.action.LogOn";
    String BYDVOICE_BROADCAST_LOG_OFF = "com.byd.voice.action.LogOff";
    String BYDVOICE_BROADCAST_INIT_DATA = "com.byd.voice.action.init";
    String BYDVOICE_BROADCAST_INIT_LANG = "com.byd.voice.action.initlang";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String stringExtra;
        int i;
        String action = intent.getAction();
        RLog.e(TAG, "onReceive: " + action);
        stringExtra = "";
        if (action.equals(this.BYDVOICE_BROADCAST_AUTOBATCH_START)) {
            i = 4;
        } else if (action.equals(this.BYDVOICE_BROADCAST_AUTOBATCH_STOP)) {
            i = 5;
        } else if (action.equals(this.BYDVOICE_BROADCAST_RECORD_START)) {
            i = 6;
        } else if (action.equals(this.BYDVOICE_BROADCAST_RECORD_STOP)) {
            i = 7;
        } else if (action.equals(this.BYDVOICE_BROADCAST_AUTOBATCH_PCM)) {
            stringExtra = intent.getStringExtra("filename") != null ? intent.getStringExtra("filename") : "";
            i = 8;
        } else if (action.equals(this.BYDVOICE_BROADCAST_LOG_ON)) {
            i = 10;
        } else if (action.equals(this.BYDVOICE_BROADCAST_LOG_OFF)) {
            i = 11;
        } else if (action.equals(this.BYDVOICE_BROADCAST_INIT_DATA)) {
            i = 12;
        } else if (action.equals(this.BYDVOICE_BROADCAST_INIT_LANG)) {
            String stringExtra2 = intent.getStringExtra("data_path");
            stringExtra = stringExtra2 != null ? stringExtra2 : "";
            i = 13;
        } else {
            i = 0;
        }
        EventBus.getDefault().post(new SystemStatusEvent(stringExtra, i));
    }
}
