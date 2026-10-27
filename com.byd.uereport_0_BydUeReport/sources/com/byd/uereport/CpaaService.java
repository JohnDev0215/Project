package com.byd.uereport;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemProperties;
import y4.c;

/* JADX INFO: loaded from: classes2.dex */
public class CpaaService extends Service {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f3034d = "CpaaService";

    public class a extends c.a {
        public a() {
        }

        @Override // y4.c
        public void n0(boolean z7) throws RemoteException {
            g5.a.i("CpaaService, ACTION_OPEN_CARPLAY");
            Intent intent = new Intent("com.byd.card.open.carplay");
            intent.putExtra("playStatus", -1);
            intent.setPackage(CpaaService.this.getPackageName());
            CpaaService.this.sendBroadcast(intent);
        }
    }

    public final void a() {
        g5.a.i("CpaaService, send cpaa service start broadcast .");
        sendBroadcast(new Intent("com.byd.system.CPAASERVICE.ACTION_START"));
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new a();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        SystemProperties.set("sys.uereport_cpaa_start", "1");
        a();
    }

    @Override // android.app.Service
    public void onDestroy() {
        SystemProperties.set("sys.uereport_cpaa_start", "0");
        super.onDestroy();
    }
}
