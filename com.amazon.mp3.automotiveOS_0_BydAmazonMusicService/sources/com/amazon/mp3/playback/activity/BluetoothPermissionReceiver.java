package com.amazon.mp3.playback.activity;

import android.content.Context;
import com.amazon.mp3.activity.GrantPermissionActivity;
import com.amazon.mp3.bluetooth.BluetoothUtil;
import com.amazon.mp3.util.Log;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: NowPlayingFragmentActivity.java */
/* JADX INFO: loaded from: classes3.dex */
class BluetoothPermissionReceiver extends GrantPermissionActivity.PermissionResultReceiver {
    private static final String TAG = BluetoothPermissionReceiver.class.getSimpleName();
    WeakReference<Context> contextWeakReference;

    BluetoothPermissionReceiver(WeakReference<Context> contextWeakReference) {
        this.contextWeakReference = contextWeakReference;
    }

    @Override // com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver
    protected void onGranted() {
        Log.info(TAG, "bluetooth permissions granted");
        Context context = this.contextWeakReference.get();
        if (context != null) {
            BluetoothUtil.syncBTPermissionPref(context, true);
        }
    }

    @Override // com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver
    protected void onDenied(String[] permissions) {
        Log.info(TAG, "bluetooth permissions denied");
    }
}
