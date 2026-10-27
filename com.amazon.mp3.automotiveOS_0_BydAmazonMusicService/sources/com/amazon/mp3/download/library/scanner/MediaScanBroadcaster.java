package com.amazon.mp3.download.library.scanner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public final class MediaScanBroadcaster {
    private Context context;

    public MediaScanBroadcaster(Context context) {
        this.context = context;
    }

    public void sendMediaScanCompletedBroadcast(String filepath) {
        getLocalBroadcastManager().sendBroadcast(new Intent("com.amazon.mp3.ACTION_MEDIA_SCANNER_SCANNED_FILE", Uri.fromFile(new File(filepath))));
    }

    public void registerMediaScanBroadcastReceiver(BroadcastReceiver mediaScanReceiver) {
        getLocalBroadcastManager().registerReceiver(mediaScanReceiver, getIntentFilter());
    }

    public void unregisterMediaScanBroadcastReceiver(BroadcastReceiver receiver) {
        getLocalBroadcastManager().unregisterReceiver(receiver);
    }

    @NonNull
    private IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter("com.amazon.mp3.ACTION_MEDIA_SCANNER_SCANNED_FILE");
        intentFilter.addDataScheme("file");
        return intentFilter;
    }

    @NonNull
    private LocalBroadcastManager getLocalBroadcastManager() {
        return LocalBroadcastManager.getInstance(this.context);
    }
}
