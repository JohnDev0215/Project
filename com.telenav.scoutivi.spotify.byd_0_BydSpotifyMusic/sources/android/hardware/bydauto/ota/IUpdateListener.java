package android.hardware.bydauto.ota;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public interface IUpdateListener {
    void onDownloadBackupFail(int i7);

    void onDownloadBackupProgress(int i7);

    void onDownloadBackupSuccess();

    void onNotify(Bundle bundle);

    void onTBOXConnectState(int i7);

    void onTboxExportLogProgress(int i7);

    void onTboxExportLogResult(int i7);

    void onTboxUpdateProgress(int i7);

    void onTboxUpdateResult(int i7);

    void onUpdateFail(int i7);

    void onUpdateProgress(int i7);

    void onUpdateSuccess();
}
