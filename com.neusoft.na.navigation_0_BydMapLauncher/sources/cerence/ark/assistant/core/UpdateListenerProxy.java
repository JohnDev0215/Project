package cerence.ark.assistant.core;

import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.framework.updater.IUpdateListener;
import cerence.ark.assistant.framework.updater.UpdateInfo;
import cerence.ark.assistant.framework.updater.UpdateResultCode;
import cerence.ark.assistant.service.RemoteUpdateListener;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class UpdateListenerProxy extends RemoteUpdateListener.Stub {
    private static final String TAG = "UpdateListenerProxy";
    private final IUpdateListener mUpdateListener;

    public UpdateListenerProxy(IUpdateListener iUpdateListener) {
        this.mUpdateListener = iUpdateListener;
    }

    @Override // cerence.ark.assistant.service.RemoteUpdateListener
    public void onCheckUpdateStarted() {
        IUpdateListener iUpdateListener = this.mUpdateListener;
        if (iUpdateListener != null) {
            iUpdateListener.onCheckUpdateStarted();
        } else {
            Logger.error(TAG, "onCheckUpdateStarted: updateListener is null");
        }
    }

    @Override // cerence.ark.assistant.service.RemoteUpdateListener
    public void onCheckUpdateFinished(UpdateResultCode updateResultCode, boolean z, List<UpdateInfo> list) {
        IUpdateListener iUpdateListener = this.mUpdateListener;
        if (iUpdateListener != null) {
            iUpdateListener.onCheckUpdateFinished(updateResultCode, z, list);
        } else {
            Logger.error(TAG, "onCheckUpdateFinished: updateListener is null");
        }
    }

    @Override // cerence.ark.assistant.service.RemoteUpdateListener
    public void onDownloadStarted(String str) {
        IUpdateListener iUpdateListener = this.mUpdateListener;
        if (iUpdateListener != null) {
            iUpdateListener.onDownloadStarted(str);
        } else {
            Logger.error(TAG, "onDownloadStarted: updateListener is null");
        }
    }

    @Override // cerence.ark.assistant.service.RemoteUpdateListener
    public void onDownloadProgress(String str, long j, long j2, boolean z) {
        IUpdateListener iUpdateListener = this.mUpdateListener;
        if (iUpdateListener != null) {
            iUpdateListener.onDownloadProgress(str, j, j2, z);
        } else {
            Logger.error(TAG, "onDownloadProgress: updateListener is null");
        }
    }

    @Override // cerence.ark.assistant.service.RemoteUpdateListener
    public void onDownloadFinished(UpdateResultCode updateResultCode, String str) {
        IUpdateListener iUpdateListener = this.mUpdateListener;
        if (iUpdateListener != null) {
            iUpdateListener.onDownloadFinished(updateResultCode, str);
        } else {
            Logger.error(TAG, "onDownloadFinished: updateListener is null");
        }
    }

    @Override // cerence.ark.assistant.service.RemoteUpdateListener
    public void onUpdateInstallable(UpdateInfo updateInfo, boolean z) {
        IUpdateListener iUpdateListener = this.mUpdateListener;
        if (iUpdateListener != null) {
            iUpdateListener.onUpdateInstallable(updateInfo, z);
        } else {
            Logger.error(TAG, "onUpdateInstallable: updateListener is null");
        }
    }

    @Override // cerence.ark.assistant.service.RemoteUpdateListener
    public void onUpdateFinished(UpdateResultCode updateResultCode, String str) {
        IUpdateListener iUpdateListener = this.mUpdateListener;
        if (iUpdateListener != null) {
            iUpdateListener.onUpdateFinished(updateResultCode, str);
        } else {
            Logger.error(TAG, "onUpdateFinished: updateListener is null");
        }
    }

    @Override // cerence.ark.assistant.service.RemoteUpdateListener
    public void onAborted(UpdateResultCode updateResultCode, String str) {
        IUpdateListener iUpdateListener = this.mUpdateListener;
        if (iUpdateListener != null) {
            iUpdateListener.onAborted(updateResultCode, str);
        } else {
            Logger.error(TAG, "onAborted: updateListener is null");
        }
    }

    @Override // cerence.ark.assistant.service.RemoteUpdateListener
    public void onDataDeleted(UpdateResultCode updateResultCode, String str) {
        IUpdateListener iUpdateListener = this.mUpdateListener;
        if (iUpdateListener != null) {
            iUpdateListener.onDataDeleted(updateResultCode, str);
        } else {
            Logger.error(TAG, "onDataDeleted: updateListener is null");
        }
    }

    @Override // cerence.ark.assistant.service.RemoteUpdateListener
    public void onConfirmed(UpdateResultCode updateResultCode, String str) {
        IUpdateListener iUpdateListener = this.mUpdateListener;
        if (iUpdateListener != null) {
            iUpdateListener.onConfirmed(updateResultCode, str);
        } else {
            Logger.error(TAG, "onConfirmed: updateListener is null");
        }
    }

    @Override // cerence.ark.assistant.service.RemoteUpdateListener
    public void onRecoveryStarted() {
        IUpdateListener iUpdateListener = this.mUpdateListener;
        if (iUpdateListener != null) {
            iUpdateListener.onRecoveryStarted();
        } else {
            Logger.error(TAG, "onRecoveryStarted: updateListener is null");
        }
    }

    @Override // cerence.ark.assistant.service.RemoteUpdateListener
    public void onRecoveryFinished(UpdateResultCode updateResultCode, String str) {
        IUpdateListener iUpdateListener = this.mUpdateListener;
        if (iUpdateListener != null) {
            iUpdateListener.onRecoveryFinished(updateResultCode, str);
        } else {
            Logger.error(TAG, "onRecoveryFinished: updateListener is null");
        }
    }
}
