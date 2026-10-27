package com.android.car.media.byd.voice;

import android.annotation.SuppressLint;
import android.car.utils.Logs;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleService;
import spi.c;
import spi.p7;
import spi.q4;

/* JADX INFO: loaded from: classes.dex */
public class AmazonMusicVoiceService extends LifecycleService {
    public final p7 a = new p7();
    public final a b = new a();

    public class a extends q4 {
        public a() {
        }
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    @SuppressLint({"MissingSuperCall"})
    public final IBinder onBind(@NonNull Intent intent) {
        StringBuilder sbK = c.k("onBind  intent isNull=");
        sbK.append(intent == null);
        Logs.d("AmazonMusicVoiceService", sbK.toString());
        return this.b;
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public final void onCreate() {
        super.onCreate();
        Logs.d("AmazonMusicVoiceService", "onCreate");
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public final void onDestroy() {
        Logs.d("AmazonMusicVoiceService", "onDestroy");
        super.onDestroy();
    }

    @Override // android.app.Service
    public final void onRebind(Intent intent) {
        Logs.d("AmazonMusicVoiceService", "onRebind");
        super.onRebind(intent);
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public final void onStart(@Nullable Intent intent, int i) {
        super.onStart(intent, i);
        StringBuilder sbK = c.k("onStartintent isNull=");
        sbK.append(intent == null);
        Logs.d("AmazonMusicVoiceService", sbK.toString());
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public final int onStartCommand(@Nullable Intent intent, int i, int i2) {
        StringBuilder sbK = c.k("onStartCommandintent isNull=");
        sbK.append(intent == null);
        Logs.d("AmazonMusicVoiceService", sbK.toString());
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.app.Service
    public final boolean onUnbind(Intent intent) {
        Logs.d("AmazonMusicVoiceService", "onUnbind");
        return super.onUnbind(intent);
    }
}
