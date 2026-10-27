package com.amazon.mp3.storage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.capability.Capabilities;
import com.amazon.mp3.service.ServiceUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.externalstorage.StorageLocation;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes4.dex */
public class ExternalStorageListener extends BroadcastReceiver {
    private static final String TAG = ExternalStorageListener.class.getSimpleName();

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, final Intent intent) {
        String action = intent.getAction();
        String str = TAG;
        Log.debug(str, "ExternalStorageListener Received Action: %s", action);
        if (action.equalsIgnoreCase("android.intent.action.MEDIA_REMOVED") || action.equalsIgnoreCase("android.intent.action.MEDIA_UNMOUNTED") || action.equalsIgnoreCase("android.intent.action.MEDIA_BAD_REMOVAL") || action.equalsIgnoreCase("android.intent.action.MEDIA_EJECT")) {
            onStorageRemoved(context);
            return;
        }
        if (action.equalsIgnoreCase("android.intent.action.MEDIA_MOUNTED")) {
            onStorageMounted(context);
            Capabilities capabilities = AmazonApplication.getCapabilities();
            if (capabilities != null) {
                capabilities.isDeviceSDCapable(context);
                return;
            }
            return;
        }
        Log.error(str, "No action can be taken!");
    }

    public void onStorageMounted(final Context context) {
        Log.info(TAG, "SD Card Media Mounted");
        handleEventWhenAppEntersForeground(context, 0, "SD Card Mounted");
    }

    public void onStorageRemoved(final Context context) {
        Log.info(TAG, "SD Card Media Removed");
        handleEventWhenAppEntersForeground(context, 1, "SD Card Ejected");
    }

    public void onAppLaunchWithAvailableSDCard(final Context context) {
        Log.info(TAG, "SD Card discovered.");
        handleEventWhenAppEntersForeground(context, 2, "SD Card discovered at app launch");
    }

    public void onAppLaunchWithNoAvailableSDCard(final Context context) {
        Log.info(TAG, "No SD Card discovered.");
        handleEventWhenAppEntersForeground(context, 3, "NO SD Card discovered at app launch");
    }

    private void handleEventWhenAppEntersForeground(final Context context, final int event, final String eventName) {
        AmazonApplication.subscribeForegroundObserver(new Action1() { // from class: com.amazon.mp3.storage.ExternalStorageListener$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ExternalStorageListener.lambda$handleEventWhenAppEntersForeground$0(context, event, (Void) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.storage.ExternalStorageListener$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ExternalStorageListener.lambda$handleEventWhenAppEntersForeground$1(eventName, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleEventWhenAppEntersForeground$0(Context context, int i, Void r3) {
        Intent intent = new Intent(context, (Class<?>) ExternalStorageScanService.class);
        intent.putExtra("externalstorage_drm_service_extra_media_state", i);
        ServiceUtil.startService(context, intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleEventWhenAppEntersForeground$1(String str, Throwable th) {
        Log.error(TAG, "Unable to launch ExternalStorageScanService to process SD card event " + str + ". Error:" + th);
    }

    public static StorageLocation getSecondaryStorageLocation() {
        if (Environment.isExternalStorageEmulated()) {
            return StorageLocation.SDCARD;
        }
        return StorageLocation.DEVICE;
    }
}
