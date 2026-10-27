package android.hardware.bydauto.ota;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public interface IUpdateListener {
    void onDownloadBackupFail(int i2);

    void onDownloadBackupProgress(int i2);

    void onDownloadBackupSuccess();

    void onNotify(Bundle bundle);

    void onTBOXConnectState(int i2);

    void onTboxExportLogProgress(int i2);

    void onTboxExportLogResult(int i2);

    void onTboxUpdateProgress(int i2);

    void onTboxUpdateResult(int i2);

    void onUpdateFail(int i2);

    void onUpdateProgress(int i2);

    void onUpdateSuccess();
}
