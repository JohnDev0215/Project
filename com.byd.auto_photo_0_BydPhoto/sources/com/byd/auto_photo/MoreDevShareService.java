package com.byd.auto_photo;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.cross.BYDCrossEventValue;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Process;
import android.os.SystemClock;
import androidx.annotation.Nullable;
import b.a.d;
import com.byd.util.w;
import com.byd.util.x;

/* JADX INFO: loaded from: classes.dex */
public class MoreDevShareService extends Service {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a f1121a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f1122b;

    private static class a extends d<MoreDevShareService> {
        public a(MoreDevShareService moreDevShareService) {
            super(moreDevShareService);
        }

        @Override // b.a.d
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void handleMessage(MoreDevShareService moreDevShareService, Message message) {
            if (message.what != 1) {
                return;
            }
            moreDevShareService.c();
        }
    }

    public static void b(Context context) {
        w.c("MoreDevShareService checkServiceRunning");
        try {
            context.startService(new Intent(context, (Class<?>) MoreDevShareService.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        com.byd.share.a.s(this).u();
        com.byd.share.a.s(this).m();
    }

    public static void d(Context context, boolean z) {
        Intent intent = new Intent(context, (Class<?>) MoreDevShareService.class);
        intent.putExtra("opt", z ? 5 : 6);
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                context.startForegroundService(intent);
            } else {
                context.startService(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void e(Bundle bundle) {
        if (!this.f1122b) {
            w.c("not support event center mode");
            return;
        }
        w.c("PhotoApp optEventCenterMsg : " + bundle);
        int i = bundle.getInt("eventType");
        BYDCrossEventValue bYDCrossEventValue = new BYDCrossEventValue();
        bYDCrossEventValue.intValue = bundle.getInt("intValue");
        bYDCrossEventValue.floatValue = bundle.getFloat("floatValue");
        bYDCrossEventValue.intArrayValue = bundle.getIntArray("intArrayValue");
        bYDCrossEventValue.floatArrayValue = bundle.getFloatArray("floatArrayValue");
        bYDCrossEventValue.bufferDataValue = bundle.getByteArray("bufferDataValue");
        com.byd.share.a.s(this).H(i, bYDCrossEventValue);
    }

    public static void f(Context context) {
        int integer = context.getResources().getInteger(R.integer.support_share_more_screen);
        int integer2 = context.getResources().getInteger(R.integer.support_share_more_screen_send);
        if (integer == 0 && integer2 == 0) {
            return;
        }
        w.c("==startPhotoServer==");
        b(context);
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.f1122b = getResources().getBoolean(R.bool.share_more_screen_by_event_center);
        x.g(this);
        w.c("MoreDevShareService onCreate : " + Process.myPid());
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
            int iElapsedRealtime = (int) SystemClock.elapsedRealtime();
            if (iElapsedRealtime < 0) {
                iElapsedRealtime = R.id.notification_share;
            }
            try {
                startForeground(iElapsedRealtime, x.c(this, MoreDevShareService.class, 100, notificationManager, R.string.app_name));
            } catch (Exception e) {
                w.c("startForeground exception : " + e.getMessage());
                e.printStackTrace();
            }
        }
        if (com.byd.share.a.t()) {
            com.byd.share.a.s(this).P();
        }
        a aVar = new a(this);
        this.f1121a = aVar;
        aVar.removeMessages(1);
        this.f1121a.sendEmptyMessageDelayed(1, 500L);
    }

    @Override // android.app.Service
    public void onDestroy() {
        w.c("MoreDevShareService onDestroy");
        super.onDestroy();
        this.f1121a.removeCallbacksAndMessages(null);
        if (Build.VERSION.SDK_INT >= 26) {
            try {
                stopForeground(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (com.byd.share.a.t()) {
            com.byd.share.a.s(this).P();
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null && intent.hasExtra("opt")) {
            int intExtra = intent.getIntExtra("opt", 0);
            w.c("MoreDevShareService onStartCommand:" + intExtra);
            if (intExtra == 5) {
                this.f1121a.removeMessages(1);
                this.f1121a.sendEmptyMessage(1);
            } else if (intExtra == 6) {
                com.byd.share.a.s(this).m();
            } else if (intExtra == 7) {
                e(intent.getExtras());
            }
        }
        return 1;
    }
}
