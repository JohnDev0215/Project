package android.hardware.bydauto.ota;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public interface IUpdateListener {
    void onDownloadBackupFail(int i3);

    void onDownloadBackupProgress(int i3);

    void onDownloadBackupSuccess();

    void onNotify(Bundle bundle);

    void onTBOXConnectState(int i3);

    void onTboxExportLogProgress(int i3);

    void onTboxExportLogResult(int i3);

    void onTboxUpdateProgress(int i3);

    void onTboxUpdateResult(int i3);

    void onUpdateFail(int i3);

    void onUpdateProgress(int i3);

    void onUpdateSuccess();
}
