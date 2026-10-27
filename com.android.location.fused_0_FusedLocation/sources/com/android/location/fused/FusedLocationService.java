package com.android.location.fused;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class FusedLocationService extends Service {
    private FusedLocationProvider mProvider;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (this.mProvider == null) {
            FusedLocationProvider fusedLocationProvider = new FusedLocationProvider(this);
            this.mProvider = fusedLocationProvider;
            fusedLocationProvider.start();
        }
        return this.mProvider.getBinder();
    }

    @Override // android.app.Service
    public void onDestroy() {
        FusedLocationProvider fusedLocationProvider = this.mProvider;
        if (fusedLocationProvider != null) {
            fusedLocationProvider.stop();
            this.mProvider = null;
        }
    }

    @Override // android.app.Service
    protected void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        FusedLocationProvider fusedLocationProvider = this.mProvider;
        if (fusedLocationProvider != null) {
            fusedLocationProvider.dump(printWriter);
        }
    }
}
