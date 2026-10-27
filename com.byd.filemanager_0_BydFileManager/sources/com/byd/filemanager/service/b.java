package com.byd.filemanager.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.storage.StorageEventListener;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import com.byd.filemanager.FileApplication;
import com.byd.filemanager.e;
import com.byd.filemanager.f;
import com.byd.filemanager.i.a.c;

/* JADX INFO: compiled from: FileScanService.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile b f3647a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private StorageManager f3649c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private DiskBroadcastReceiver f3650d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private volatile boolean f3651e;
    private c f;
    private volatile byte g;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3648b = "FileScanService";
    private final StorageEventListener h = new a();
    private final BroadcastReceiver i = new C0086b();

    /* JADX INFO: compiled from: FileScanService.java */
    class a extends StorageEventListener {
        a() {
        }

        public void onStorageStateChanged(String str, String str2, String str3) {
            com.byd.filemanager.k.b.b("path:" + str + " , oldState:" + str2 + " , newState:" + str3);
        }

        public void onVolumeStateChanged(VolumeInfo volumeInfo, int i, int i2) {
            int iD;
            com.byd.filemanager.k.b.c("FileScanService", "volume oldState:" + i + " , newState:" + i2);
            if (i2 != 2 || (iD = e.f().d(volumeInfo.getDeviceType())) <= 0) {
                return;
            }
            b.this.o(iD, 1, false);
            b.this.k(volumeInfo.path);
        }
    }

    /* JADX INFO: renamed from: com.byd.filemanager.service.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FileScanService.java */
    class C0086b extends BroadcastReceiver {
        C0086b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                action.hashCode();
                if (action.equals("android.intent.action.MEDIA_SCANNER_FINISHED")) {
                    com.byd.filemanager.k.b.c("FileScanService", "media scanner finished");
                } else if (action.equals("android.intent.action.MEDIA_SCANNER_STARTED")) {
                    com.byd.filemanager.k.b.c("FileScanService", "media scanner started");
                }
            }
        }
    }

    public static b c() {
        if (f3647a == null) {
            synchronized (b.class) {
                if (f3647a == null) {
                    f3647a = new b();
                }
            }
        }
        return f3647a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void f(String str, Uri uri) {
        int i = e.f().i(str);
        if (i < 0 || i > 2) {
            return;
        }
        boolean zD = d(i);
        com.byd.filemanager.k.b.n("FileScanService", "MediaScanner path=" + str + ",index=" + i + ",scanning=" + zD);
        if (zD) {
            n(i, 4);
            int iK = f.h().k();
            com.byd.filemanager.k.b.n("FileScanService", "MediaScanner complete path=" + str + ",index=" + i + ",uiPos=" + iK);
            c cVar = this.f;
            if (cVar == null || iK != i) {
                return;
            }
            cVar.h(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k(String str) {
        if (str != null) {
            l(new String[]{str});
        }
    }

    private void l(String[] strArr) {
        if (strArr != null && strArr.length > 0) {
            for (int i = 0; i < strArr.length; i++) {
                String str = strArr[i];
                if (str == null) {
                    strArr[i] = "";
                } else if (str.startsWith("/mnt/media_rw/")) {
                    strArr[i] = str.replace("/mnt/media_rw/", "/storage/");
                }
            }
            MediaScannerConnection.scanFile(FileApplication.a(), strArr, null, new MediaScannerConnection.OnScanCompletedListener() { // from class: com.byd.filemanager.service.a
                @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                public final void onScanCompleted(String str2, Uri uri) {
                    this.f3646a.f(str2, uri);
                }
            });
        }
        com.byd.filemanager.k.b.n("FileScanService", "MediaScanner scanFile request end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o(int i, int i2, boolean z) {
        if (i < 0 || i > 2) {
            return;
        }
        boolean z2 = i2 == 1 || i2 == 2;
        if (z2 == d(i)) {
            return;
        }
        if (z && z2 && i > 0) {
            m(e.f().l(i));
        }
        int i3 = 1 << i;
        synchronized (this) {
            if (i2 == 0 || i2 == 4) {
                this.g = (byte) ((~i3) & this.g);
            } else {
                this.g = (byte) (i3 | this.g);
            }
        }
        com.byd.filemanager.k.b.c("FileScanService", "setScan scanning=" + ((int) this.g));
    }

    public boolean d(int i) {
        return ((this.g >>> i) & 1) == 1;
    }

    public void g() {
        com.byd.filemanager.k.b.c("FileScanService", "onDestroy is end " + this.f3651e);
        if (this.f3651e) {
            FileApplication fileApplicationA = FileApplication.a();
            fileApplicationA.unregisterReceiver(this.i);
            fileApplicationA.unregisterReceiver(this.f3650d);
            this.f3649c.unregisterListener(this.h);
            this.g = (byte) 0;
            this.f = null;
            this.f3651e = false;
        }
    }

    public void h(c cVar) {
        this.f = cVar;
        com.byd.filemanager.k.b.n("FileScanService", "registerIView is end");
    }

    public void i() {
        n(0, 2);
        FileApplication fileApplicationA = FileApplication.a();
        if (this.f3651e) {
            return;
        }
        StorageManager storageManager = (StorageManager) fileApplicationA.getSystemService("storage");
        this.f3649c = storageManager;
        storageManager.registerListener(this.h);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addDataScheme("file");
        intentFilter.addAction("android.intent.action.MEDIA_SCANNER_STARTED");
        intentFilter.addAction("android.intent.action.MEDIA_SCANNER_FINISHED");
        fileApplicationA.registerReceiver(this.i, intentFilter);
        this.f3650d = new DiskBroadcastReceiver();
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addDataScheme("file");
        intentFilter2.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter2.addAction("android.intent.action.MEDIA_EJECT");
        fileApplicationA.registerReceiver(this.f3650d, intentFilter2);
        this.f3651e = true;
        j();
    }

    public void j() {
        m(e.f().g());
    }

    public void m(String... strArr) {
        if (strArr == null) {
            l(e.f().m());
        } else {
            l(strArr);
        }
    }

    public void n(int i, int i2) {
        o(i, i2, true);
    }
}
