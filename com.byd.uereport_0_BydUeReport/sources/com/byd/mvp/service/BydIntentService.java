package com.byd.mvp.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BydIntentService extends Service {
    private static final String TAG = "BydIntentService";
    private String mName;
    private boolean mRedelivery;
    private volatile a mServiceHandler;
    private volatile Looper mServiceLooper;

    public final class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            BydIntentService.this.onHandleIntent((Intent) message.obj);
            BydIntentService.this.stopSelf(message.arg1);
        }
    }

    public BydIntentService(String str) {
        this.mName = str;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        HandlerThread handlerThread = new HandlerThread("BydIntentService[" + this.mName + "]", 10);
        handlerThread.start();
        this.mServiceLooper = handlerThread.getLooper();
        this.mServiceHandler = new a(this.mServiceLooper);
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.mServiceLooper.quit();
        x3.a.c(TAG, "-------onDestroy------" + getClass().getName());
    }

    public abstract void onHandleIntent(Intent intent);

    @Override // android.app.Service
    public void onStart(Intent intent, int i8) {
        Message messageObtainMessage = this.mServiceHandler.obtainMessage();
        messageObtainMessage.arg1 = i8;
        messageObtainMessage.obj = intent;
        this.mServiceHandler.sendMessage(messageObtainMessage);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i8, int i9) {
        onStart(intent, i9);
        return this.mRedelivery ? 3 : 2;
    }

    public void setIntentRedelivery(boolean z7) {
        this.mRedelivery = z7;
    }
}
