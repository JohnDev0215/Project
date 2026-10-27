package com.amazon.mp3.activity.util;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UniqueCodeUtil;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public class SingleInstanceMonitor {
    private static final String TAG = "SingleInstanceMonitor";
    private Activity mActivity;
    private int mInstanceId = UniqueCodeUtil.nextUniqueCode();
    private BroadcastReceiver mInstanceStartedReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.activity.util.SingleInstanceMonitor.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!SingleInstanceMonitor.this.mInstanceType.equals(intent.getStringExtra("com.amazon.mp3.activity.EXTRA_INSTANCE_TYPE")) || intent.getIntExtra("com.amazon.mp3.activity.EXTRA_INSTANCE_ID", -1) == SingleInstanceMonitor.this.mInstanceId || SingleInstanceMonitor.this.mActivity.isFinishing()) {
                return;
            }
            Log.info(SingleInstanceMonitor.TAG, "Finishing instance: %s (%d)", SingleInstanceMonitor.this.mInstanceType, Integer.valueOf(SingleInstanceMonitor.this.mInstanceId));
            SingleInstanceMonitor.this.mActivity.finish();
        }
    };
    private String mInstanceType;
    private static final IntentFilter INTENT_FILTER = new IntentFilter("com.amazon.mp3.ACTION_NEW_INSTANCE_STARTED");
    private static HashMap<String, Integer> sLastInstanceIdMap = new HashMap<>();

    public SingleInstanceMonitor(Activity activity) {
        this.mInstanceType = activity.getClass().getName();
        this.mActivity = activity;
    }

    public void onCreate(Bundle instanceState) {
        this.mActivity.registerReceiver(this.mInstanceStartedReceiver, INTENT_FILTER);
    }

    public void onDestroy() {
        this.mActivity.unregisterReceiver(this.mInstanceStartedReceiver);
    }

    public void onStart() {
        Integer num = sLastInstanceIdMap.get(this.mInstanceType);
        if (num == null) {
            sLastInstanceIdMap.put(this.mInstanceType, Integer.valueOf(this.mInstanceId));
            return;
        }
        if (this.mInstanceId != num.intValue()) {
            sLastInstanceIdMap.put(this.mInstanceType, Integer.valueOf(this.mInstanceId));
            Intent intent = new Intent("com.amazon.mp3.ACTION_NEW_INSTANCE_STARTED");
            intent.putExtra("com.amazon.mp3.activity.EXTRA_INSTANCE_TYPE", this.mInstanceType);
            intent.putExtra("com.amazon.mp3.activity.EXTRA_INSTANCE_ID", this.mInstanceId);
            this.mActivity.sendBroadcast(intent);
        }
    }
}
