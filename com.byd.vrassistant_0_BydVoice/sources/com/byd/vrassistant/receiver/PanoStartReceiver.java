package com.byd.vrassistant.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.view.ViewManager;

/* JADX INFO: loaded from: classes2.dex */
public class PanoStartReceiver extends BroadcastReceiver {
    private static final String TAG = "PanoStartReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action == null || !action.equals(Constant.ACTION_PANO_START)) {
            return;
        }
        int intExtra = intent.getIntExtra(Constant.EXTRA_PANO_START, 0);
        RLog.i(TAG, "onReceive: isPanoStart==" + intExtra);
        ViewManager.getViewStateManager().setPanoStart(intExtra == 1);
    }
}
