package com.google.android.play.core.assetpacks;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import p.j03;
import p.l13;
import p.nh1;
import p.oh1;
import p.w03;
import p.y3;

/* JADX INFO: loaded from: classes.dex */
public class AssetPackExtractionService extends Service {
    public j03 c;

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.c;
    }

    @Override // android.app.Service
    public final void onCreate() {
        w03 w03Var;
        super.onCreate();
        Context applicationContext = getApplicationContext();
        synchronized (l13.class) {
            try {
                if (l13.a == null) {
                    oh1 oh1Var = new oh1((nh1) null);
                    Context applicationContext2 = applicationContext.getApplicationContext();
                    if (applicationContext2 != null) {
                        applicationContext = applicationContext2;
                    }
                    y3 y3Var = new y3(applicationContext);
                    oh1Var.e = y3Var;
                    l13.a = new w03(y3Var);
                }
                w03Var = l13.a;
            } catch (Throwable th) {
                throw th;
            }
        }
        this.c = (j03) w03Var.c.a();
    }
}
