package com.byd.uereport;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.bydauto.dms.BYDAutoDmsDevice;
import android.hardware.bydauto.dms.IDMSListener;
import android.hardware.bydauto.reminder.AbsBYDAutoReminderListener;
import android.hardware.bydauto.reminder.BYDAutoReminderDevice;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import com.byd.bt.model.BTDevice;
import com.byd.uereport.bean.ReportBean;
import com.byd.uereport.dialog.WeakCloudSignalDialog;
import g5.p;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class VoiceReminderService extends Service {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f3148d = 10000;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Handler f3149e = new a(Looper.myLooper());

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public BroadcastReceiver f3150f = new d();

    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 10000) {
                return;
            }
            new WeakCloudSignalDialog(VoiceReminderService.this.getApplicationContext()).show();
        }
    }

    public class c extends IDMSListener {
        public c() {
        }

        @Override // android.hardware.bydauto.dms.IDMSListener
        public void onAttentionDetectChanged(int i8) {
            super.onAttentionDetectChanged(i8);
            VoiceReminderService.this.e(1, i8);
        }

        @Override // android.hardware.bydauto.dms.IDMSListener
        public void onDriverNotResponse() {
            super.onDriverNotResponse();
            VoiceReminderService.this.e(2, -1);
        }

        @Override // android.hardware.bydauto.dms.IDMSListener
        public void onFatigueDetectChanged(int i8) {
            super.onFatigueDetectChanged(i8);
            VoiceReminderService.this.e(0, i8);
        }
    }

    public class d extends BroadcastReceiver {
        public d() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            intent.getStringExtra("content");
            int intExtra = intent.getIntExtra("value", -1);
            int intExtra2 = intent.getIntExtra("voice_value", -1);
            g5.a.i("playVoiceReceiver voiceValue......" + intExtra2);
            int iC = p.c(intExtra2);
            if (iC != -1) {
                y4.d.n(VoiceReminderService.this).s(new ReportBean(iC, "report void id is " + iC, 1, 0L, ReportType.SCENE_MODE.ordinal(), -1));
            }
            if (intExtra == 1) {
                y4.d.n(VoiceReminderService.this).s(new ReportBean(R.raw.line_00040, "aaa", 3, 0L, 0, -1));
            } else if (intExtra == 2) {
                y4.d.n(VoiceReminderService.this).q(110);
            } else if (intExtra != 3) {
                y4.d.n(VoiceReminderService.this).q(intExtra);
            } else {
                y4.d.n(VoiceReminderService.this).s(new ReportBean(R.raw.line_00001, "ccc", -1, BTDevice.CONNECTED_TIME_STANDARD, 0, -1));
            }
        }
    }

    public final void c() {
        g5.a.i("init......");
        y4.d.n(this).I(new h5.c(new h5.a(this)));
    }

    public final void d() {
        registerReceiver(this.f3150f, new IntentFilter("com.byd.uereport.ACTION_VOICE"));
    }

    public final void e(int i8, int i9) {
        int i10;
        g5.a.i("submitHandleDMSTask type:" + i8 + ",state:" + i9);
        int i11 = -1;
        if (i8 == 0) {
            if (i9 == 1) {
                i11 = 140;
            } else if (i9 == 2) {
                i11 = 141;
            } else if (i9 == 3) {
                i11 = 142;
            } else if (i9 == 4) {
                i11 = 143;
            }
        } else if (i8 == 1) {
            if (i9 != 1) {
                i10 = i9 == 2 ? 145 : 144;
            }
            i11 = i10;
        } else if (i8 == 2) {
            i11 = 146;
        }
        y4.d.n(this).q(i11);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        r4.a.d(this);
        super.onCreate();
        c();
        d();
        BYDAutoReminderDevice.getInstance(this).registerListener((AbsBYDAutoReminderListener) new b());
        BYDAutoDmsDevice.getInstance(this).registerListener((IDMSListener) new c());
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        y4.d.n(this).l();
        unregisterReceiver(this.f3150f);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i8, int i9) {
        g5.a.i("Reminder Service Start......");
        return 1;
    }

    public class b extends AbsBYDAutoReminderListener {
        public b() {
        }

        @Override // android.hardware.bydauto.reminder.AbsBYDAutoReminderListener
        public void onIndChanged(int i8) {
            super.onIndChanged(i8);
            g5.a.i("onIndChanged 1:" + i8);
            if (i8 == 42) {
                VoiceReminderService.this.f3149e.sendEmptyMessage(10000);
            }
            y4.d.n(VoiceReminderService.this).q(i8);
        }

        @Override // android.hardware.bydauto.reminder.AbsBYDAutoReminderListener
        public void onIndChanged(int i8, int i9) {
            super.onIndChanged(i8, i9);
            g5.a.i("onIndChanged 2:" + i9);
        }
    }
}
