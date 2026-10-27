package com.byd.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.byd.fragment.AlbumBaseFragment;

/* JADX INFO: loaded from: classes2.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile i f2957a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b f2958b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f2959c;
    private Handler d;

    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            int i = message.what;
            if (i == 1) {
                i.this.c((String) message.obj, true);
                return;
            }
            if (i == 2) {
                i.this.c((String) message.obj, false);
            } else if (i == 3) {
                i.this.h((String) message.obj, true);
            } else {
                if (i != 4) {
                    return;
                }
                i.this.h((String) message.obj, false);
            }
        }
    }

    private class b extends BroadcastReceiver {
        private b() {
        }

        /* synthetic */ b(i iVar, a aVar) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String path;
            String action = intent.getAction();
            Uri data = intent.getData();
            w.c(action + " -> uri:" + data);
            if (data == null || (path = data.getPath()) == null) {
                return;
            }
            if (!"android.intent.action.MEDIA_SCANNER_FINISHED".equals(action)) {
                j.e(i.this.f2959c).p(path, true);
            } else {
                j.e(i.this.f2959c).p(path, false);
                i.this.i();
            }
        }
    }

    private i(Context context) {
        this.f2959c = context.getApplicationContext();
        e();
    }

    public static i d(Context context) {
        if (f2957a == null) {
            synchronized (i.class) {
                if (f2957a == null) {
                    f2957a = new i(context);
                }
            }
        }
        return f2957a;
    }

    private synchronized void e() {
        HandlerThread handlerThread = new HandlerThread("PhotoService");
        handlerThread.start();
        this.d = new a(handlerThread.getLooper());
    }

    private void g() {
        if (this.f2958b == null) {
            this.f2958b = new b(this, null);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.MEDIA_SCANNER_FINISHED");
            intentFilter.addAction("android.intent.action.MEDIA_SCANNER_STARTED");
            intentFilter.addDataScheme("file");
            try {
                this.f2959c.registerReceiver(this.f2958b, intentFilter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        LocalBroadcastManager.getInstance(this.f2959c).sendBroadcast(new Intent("com.byd.external_mounted_state"));
        LocalBroadcastManager.getInstance(this.f2959c).sendBroadcast(new Intent(AlbumBaseFragment.ACTION_ALBUM_DATA_CHANGE));
    }

    private void j() {
        b bVar = this.f2958b;
        if (bVar != null) {
            this.f2959c.unregisterReceiver(bVar);
            this.f2958b = null;
        }
    }

    public void c(String str, boolean z) {
        w.c("addExternalCard :" + str + " udisk:" + z);
        j.e(this.f2959c).a(str, z);
        g();
    }

    public void f(boolean z, boolean z2, String str) {
        Handler handler = this.d;
        if (handler != null) {
            handler.obtainMessage(z ? z2 ? 1 : 2 : z2 ? 3 : 4, str).sendToTarget();
        }
    }

    public void h(String str, boolean z) {
        w.c("removeExternalCard , udisk :" + z + " | path : " + str);
        b.a.k.c.D(this.f2959c).R(z ? 3 : 4, str);
        j.e(this.f2959c).n(str);
        b.a.h.l.m(this.f2959c).t(str);
        if (j.e(this.f2959c).q() == 0) {
            j();
        }
    }
}
