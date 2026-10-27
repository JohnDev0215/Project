package com.byd.vrassistant.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.byd.vrassistant.manager.VoiceAssistantManager;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.TagUtil;

/* JADX INFO: loaded from: classes2.dex */
public class ZoomCarReceiver extends BroadcastReceiver {
    private static final String MEETING_STATUS = "meeting_action";
    private static final String TAG = TagUtil.getTagName(ZoomCarReceiver.class);
    public static final String ZOOM_MEETING_ACTION = "byd.intent.action.ZOOM_MEETING_ACTION";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String str = TAG;
        RLog.i(str, "recevier intent.action=" + action);
        if (ZOOM_MEETING_ACTION.equals(action)) {
            int intExtra = intent.getIntExtra(MEETING_STATUS, -1);
            if (intExtra == 0) {
                RLog.i(str, "**********************非会议中**********************");
            } else {
                if (intExtra != 1) {
                    return;
                }
                RLog.i(str, "**********************会议中**********************");
                RLog.i(str, "ZoomCar Receiver SendCancelPTTEvent");
                VoiceAssistantManager.get().sendCancelPTTEvent();
            }
        }
    }
}
