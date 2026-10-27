package android.hardware.bydauto.ota;

import android.os.Bundle;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface IUpdateListener {
    void onDownloadBackupFail(int i);

    void onDownloadBackupProgress(int i);

    void onDownloadBackupSuccess();

    void onNotify(Bundle bundle);

    void onTBOXConnectState(int i);

    void onTboxExportLogProgress(int i);

    void onTboxExportLogResult(int i);

    void onTboxUpdateProgress(int i);

    void onTboxUpdateResult(int i);

    void onUpdateFail(int i);

    void onUpdateProgress(int i);

    void onUpdateSuccess();
}
