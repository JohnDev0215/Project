package com.google.android.play.core.assetpacks;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import p.a13;

/* JADX INFO: loaded from: classes.dex */
public class ExtractionForegroundService extends Service {
    public final a13 c = new a13(this);

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.c;
    }
}
