package c.a.a.f;

import android.hardware.bydauto.ota.IUpdateListener;
import android.os.Bundle;
import c.a.a.b;
import c.a.b.d.e;

/* JADX INFO: compiled from: UpdateListener.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements IUpdateListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2197a = "a";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f2198b;

    public a(b bVar) {
        this.f2198b = bVar;
    }

    @Override // android.hardware.bydauto.ota.IUpdateListener
    public void onDownloadBackupFail(int i2) {
        e.c(f2197a, "onDownloadBackupFail->errorCode = " + i2);
    }

    @Override // android.hardware.bydauto.ota.IUpdateListener
    public void onDownloadBackupProgress(int i2) {
        e.c(f2197a, "onDownloadBackupProgress->progress = " + i2);
    }

    @Override // android.hardware.bydauto.ota.IUpdateListener
    public void onDownloadBackupSuccess() {
        e.c(f2197a, "onDownloadBackupSuccess");
    }

    @Override // android.hardware.bydauto.ota.IUpdateListener
    public void onNotify(Bundle bundle) {
        this.f2198b.onNotify(bundle);
    }

    @Override // android.hardware.bydauto.ota.IUpdateListener
    public void onTBOXConnectState(int i2) {
        e.c(f2197a, "onTBOXConnectState->state = " + i2);
    }

    @Override // android.hardware.bydauto.ota.IUpdateListener
    public void onTboxExportLogProgress(int i2) {
        e.c(f2197a, "onTboxExportLogProgress->progress = " + i2);
    }

    @Override // android.hardware.bydauto.ota.IUpdateListener
    public void onTboxExportLogResult(int i2) {
        e.c(f2197a, "onTboxExportLogResult->result = " + i2);
    }

    @Override // android.hardware.bydauto.ota.IUpdateListener
    public void onTboxUpdateProgress(int i2) {
        e.c(f2197a, "onTboxUpdateProgress->progress = " + i2);
    }

    @Override // android.hardware.bydauto.ota.IUpdateListener
    public void onTboxUpdateResult(int i2) {
        e.c(f2197a, "onTboxUpdateResult->ret = " + i2);
    }

    @Override // android.hardware.bydauto.ota.IUpdateListener
    public void onUpdateFail(int i2) {
        this.f2198b.onUpdateFail(i2);
    }

    @Override // android.hardware.bydauto.ota.IUpdateListener
    public void onUpdateProgress(int i2) {
        this.f2198b.onUpdateProgress(i2);
    }

    @Override // android.hardware.bydauto.ota.IUpdateListener
    public void onUpdateSuccess() {
        this.f2198b.onUpdateSuccess();
    }
}
