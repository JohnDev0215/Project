package com.byd.cleardata.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.IBinder;
import android.os.storage.StorageEventListener;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import com.byd.cleardata.FileApplication;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class FileScanService extends Service {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private StorageManager f2729b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private com.byd.cleardata.service.a f2730c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final StorageEventListener f2731d = new b();
    private final BroadcastReceiver e = new c();

    class a implements MediaScannerConnection.OnScanCompletedListener {
        a() {
        }

        @Override // android.media.MediaScannerConnection.OnScanCompletedListener
        public void onScanCompleted(String str, Uri uri) {
            com.byd.cleardata.i.c.b("onScanCompleted path = " + str + " ,uri = " + uri);
        }
    }

    class b extends StorageEventListener {
        b() {
        }

        public void onStorageStateChanged(String str, String str2, String str3) {
            com.byd.cleardata.i.c.b("path:" + str + " , oldState:" + str2 + " , newState:" + str3);
        }

        public void onVolumeStateChanged(VolumeInfo volumeInfo, int i, int i2) {
            com.byd.cleardata.i.c.b("volume oldState:" + i + " , newState:" + i2);
            if (i2 == 2) {
                FileScanService.this.c(volumeInfo.path);
            }
        }
    }

    class c extends BroadcastReceiver {
        c() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String str;
            if (intent != null) {
                String action = intent.getAction();
                action.hashCode();
                if (action.equals("android.intent.action.MEDIA_SCANNER_FINISHED")) {
                    str = "media scanner finished";
                } else if (!action.equals("android.intent.action.MEDIA_SCANNER_STARTED")) {
                    return;
                } else {
                    str = "media scanner started";
                }
                com.byd.cleardata.i.c.b(str);
            }
        }
    }

    public static void b() {
        e(com.byd.cleardata.a.f().l());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        if (str != null) {
            d(new String[]{str});
        }
    }

    private void d(String[] strArr) {
        com.byd.cleardata.i.c.b("scanFile");
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        for (int i = 0; i < strArr.length; i++) {
            String str = strArr[i];
            if (str == null) {
                strArr[i] = "";
            } else if (str.startsWith("/mnt/media_rw/")) {
                strArr[i] = str.replace("/mnt/media_rw/", "/storage/");
            }
        }
        com.byd.cleardata.i.c.b("scanFiles " + Arrays.toString(strArr));
        MediaScannerConnection.scanFile(this, strArr, null, new a());
    }

    public static void e(String... strArr) {
        com.byd.cleardata.i.c.b("scanFiles");
        Intent intent = new Intent(FileApplication.a(), (Class<?>) FileScanService.class);
        intent.putExtra("path", strArr);
        FileApplication.a().startService(intent);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        StorageManager storageManager = (StorageManager) getSystemService("storage");
        this.f2729b = storageManager;
        storageManager.registerListener(this.f2731d);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addDataScheme("file");
        intentFilter.addAction("android.intent.action.MEDIA_SCANNER_STARTED");
        intentFilter.addAction("android.intent.action.MEDIA_SCANNER_FINISHED");
        registerReceiver(this.e, intentFilter);
        this.f2730c = new com.byd.cleardata.service.a();
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addDataScheme("file");
        intentFilter2.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter2.addAction("android.intent.action.MEDIA_EJECT");
        registerReceiver(this.f2730c, intentFilter2);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        com.byd.cleardata.i.c.b("onDestroy");
        unregisterReceiver(this.e);
        this.f2729b.unregisterListener(this.f2731d);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            String[] stringArrayExtra = intent.getStringArrayExtra("path");
            if (stringArrayExtra == null) {
                d(com.byd.cleardata.a.f().l());
            } else {
                d(stringArrayExtra);
            }
        }
        return super.onStartCommand(intent, i, i2);
    }
}
