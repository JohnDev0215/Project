package com.byd.vrassistant.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.conversation.RadioSdk;

/* JADX INFO: loaded from: classes2.dex */
public class DabStateReceiver extends BroadcastReceiver {
    public static final String ACTION_GET_DAB_STATR = "action.dab.playback_state.query";
    public static final String ACTION_RESP_DAB_STATR = "action.dab.playback_state.resp";
    private static final String TAG = "NEU_DabStateReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "onReceive: DabStateReceiver action==" + action);
        if (action.equals(ACTION_RESP_DAB_STATR)) {
            String stringExtra = intent.getStringExtra("state");
            if ("playing".equals(stringExtra)) {
                RadioSdk.getInstance(BydVoiceApplication.getContext()).Dab1State = 0;
            } else if ("paused".equals(stringExtra)) {
                RadioSdk.getInstance(BydVoiceApplication.getContext()).Dab1State = 1;
            } else if ("stop".equals(stringExtra)) {
                RadioSdk.getInstance(BydVoiceApplication.getContext()).Dab1State = 2;
            } else if ("scanning".equals(stringExtra)) {
                RadioSdk.getInstance(BydVoiceApplication.getContext()).Dab1State = 0;
            }
            RadioSdk.getInstance(BydVoiceApplication.getContext()).dabstateCountdown();
            Log.d(TAG, "onReceive: DabStateReceiver state==" + stringExtra);
        }
    }
}
