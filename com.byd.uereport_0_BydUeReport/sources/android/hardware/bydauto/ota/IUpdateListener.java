package android.hardware.bydauto.ota;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public interface IUpdateListener {
    void onDownloadBackupFail(int i8);

    void onDownloadBackupProgress(int i8);

    void onDownloadBackupSuccess();

    void onNotify(Bundle bundle);

    void onTBOXConnectState(int i8);

    void onTboxExportLogProgress(int i8);

    void onTboxExportLogResult(int i8);

    void onTboxUpdateProgress(int i8);

    void onTboxUpdateResult(int i8);

    void onUpdateFail(int i8);

    void onUpdateProgress(int i8);

    void onUpdateSuccess();
}
