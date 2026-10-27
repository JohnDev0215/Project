package com.byd.cpaa.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public abstract class BydIntentService extends Service {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile Looper f2700d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile a f2701e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f2702f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f2703g;

    public final class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            BydIntentService.this.a((Intent) message.obj);
            BydIntentService.this.stopSelf(message.arg1);
        }
    }

    public BydIntentService(String str) {
        this.f2702f = str;
    }

    public abstract void a(Intent intent);

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        r4.a.d(this);
        super.onCreate();
        HandlerThread handlerThread = new HandlerThread("BydIntentService[" + this.f2702f + "]", 10);
        handlerThread.start();
        this.f2700d = handlerThread.getLooper();
        this.f2701e = new a(this.f2700d);
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.f2700d.quit();
        x3.a.c("BydIntentService", "-------onDestroy------" + getClass().getName());
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int i8) {
        Message messageObtainMessage = this.f2701e.obtainMessage();
        messageObtainMessage.arg1 = i8;
        messageObtainMessage.obj = intent;
        this.f2701e.sendMessage(messageObtainMessage);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i8, int i9) {
        onStart(intent, i9);
        return this.f2703g ? 3 : 2;
    }
}
