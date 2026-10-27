package com.byd.mvp.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BydIntentService extends Service {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile Looper f675b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile a f676c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f677d;
    public boolean e;

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

    @WorkerThread
    public abstract void a(@Nullable Intent intent);

    @Override // android.app.Service
    public IBinder onBind(@Nullable Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        HandlerThread handlerThread = new HandlerThread("BydIntentService[" + this.f677d + "]", 10);
        handlerThread.start();
        this.f675b = handlerThread.getLooper();
        this.f676c = new a(this.f675b);
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.f675b.quit();
        b.a.d.b.a.d("BydIntentService", "-------onDestroy------" + getClass().getName());
    }

    @Override // android.app.Service
    public void onStart(@Nullable Intent intent, int i) {
        Message messageObtainMessage = this.f676c.obtainMessage();
        messageObtainMessage.arg1 = i;
        messageObtainMessage.obj = intent;
        this.f676c.sendMessage(messageObtainMessage);
    }

    @Override // android.app.Service
    public int onStartCommand(@Nullable Intent intent, int i, int i2) {
        onStart(intent, i2);
        return this.e ? 3 : 2;
    }
}
