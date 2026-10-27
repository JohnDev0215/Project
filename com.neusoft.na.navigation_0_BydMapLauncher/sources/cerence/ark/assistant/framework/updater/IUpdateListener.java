package cerence.ark.assistant.framework.updater;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IUpdateListener {
    void onAborted(UpdateResultCode updateResultCode, String str);

    void onCheckUpdateFinished(UpdateResultCode updateResultCode, boolean z, List<UpdateInfo> list);

    void onCheckUpdateStarted();

    void onConfirmed(UpdateResultCode updateResultCode, String str);

    void onDataDeleted(UpdateResultCode updateResultCode, String str);

    void onDownloadFinished(UpdateResultCode updateResultCode, String str);

    void onDownloadProgress(String str, long j, long j2, boolean z);

    void onDownloadStarted(String str);

    void onRecoveryFinished(UpdateResultCode updateResultCode, String str);

    void onRecoveryStarted();

    void onUpdateFinished(UpdateResultCode updateResultCode, String str);

    void onUpdateInstallable(UpdateInfo updateInfo, boolean z);
}
