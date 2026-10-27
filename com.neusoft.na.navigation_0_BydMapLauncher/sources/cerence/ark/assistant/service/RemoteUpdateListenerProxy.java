package cerence.ark.assistant.service;

import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.framework.updater.IUpdateListener;
import cerence.ark.assistant.framework.updater.UpdateInfo;
import cerence.ark.assistant.framework.updater.UpdateResultCode;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class RemoteUpdateListenerProxy implements IUpdateListener {
    private static final String TAG = "RemoteUpdateListenerProxy";
    private final RemoteUpdateListener mRemoteUpdateListener;

    public RemoteUpdateListenerProxy(RemoteUpdateListener remoteUpdateListener) {
        this.mRemoteUpdateListener = remoteUpdateListener;
    }

    @Override // cerence.ark.assistant.framework.updater.IUpdateListener
    public void onCheckUpdateStarted() {
        try {
            RemoteUpdateListener remoteUpdateListener = this.mRemoteUpdateListener;
            if (remoteUpdateListener != null) {
                remoteUpdateListener.onCheckUpdateStarted();
            } else {
                Logger.error(TAG, "onCheckUpdateStarted: remoteUpdateListener is null");
            }
        } catch (Exception e) {
            Logger.error(TAG, "onCheckUpdateStarted exception", e);
        }
    }

    @Override // cerence.ark.assistant.framework.updater.IUpdateListener
    public void onCheckUpdateFinished(UpdateResultCode updateResultCode, boolean z, List<UpdateInfo> list) {
        try {
            RemoteUpdateListener remoteUpdateListener = this.mRemoteUpdateListener;
            if (remoteUpdateListener != null) {
                remoteUpdateListener.onCheckUpdateFinished(updateResultCode, z, list);
            } else {
                Logger.error(TAG, "onCheckUpdateFinished: remoteUpdateListener is null");
            }
        } catch (Exception e) {
            Logger.error(TAG, "onCheckUpdateFinished exception", e);
        }
    }

    @Override // cerence.ark.assistant.framework.updater.IUpdateListener
    public void onDownloadStarted(String str) {
        try {
            RemoteUpdateListener remoteUpdateListener = this.mRemoteUpdateListener;
            if (remoteUpdateListener != null) {
                remoteUpdateListener.onDownloadStarted(str);
            } else {
                Logger.error(TAG, "onDownloadStarted: remoteUpdateListener is null");
            }
        } catch (Exception e) {
            Logger.error(TAG, "onDownloadStarted exception", e);
        }
    }

    @Override // cerence.ark.assistant.framework.updater.IUpdateListener
    public void onDownloadProgress(String str, long j, long j2, boolean z) {
        try {
            RemoteUpdateListener remoteUpdateListener = this.mRemoteUpdateListener;
            if (remoteUpdateListener != null) {
                remoteUpdateListener.onDownloadProgress(str, j, j2, z);
            } else {
                Logger.error(TAG, "onDownloadProgress: remoteUpdateListener is null");
            }
        } catch (Exception e) {
            Logger.error(TAG, "onDownloadProgress exception", e);
        }
    }

    @Override // cerence.ark.assistant.framework.updater.IUpdateListener
    public void onDownloadFinished(UpdateResultCode updateResultCode, String str) {
        try {
            RemoteUpdateListener remoteUpdateListener = this.mRemoteUpdateListener;
            if (remoteUpdateListener != null) {
                remoteUpdateListener.onDownloadFinished(updateResultCode, str);
            } else {
                Logger.error(TAG, "onDownloadFinished: remoteUpdateListener is null");
            }
        } catch (Exception e) {
            Logger.error(TAG, "onDownloadFinished exception", e);
        }
    }

    @Override // cerence.ark.assistant.framework.updater.IUpdateListener
    public void onUpdateInstallable(UpdateInfo updateInfo, boolean z) {
        try {
            RemoteUpdateListener remoteUpdateListener = this.mRemoteUpdateListener;
            if (remoteUpdateListener != null) {
                remoteUpdateListener.onUpdateInstallable(updateInfo, z);
            } else {
                Logger.error(TAG, "onUpdateInstallable: remoteUpdateListener is null");
            }
        } catch (Exception e) {
            Logger.error(TAG, "onUpdateInstallable exception", e);
        }
    }

    @Override // cerence.ark.assistant.framework.updater.IUpdateListener
    public void onUpdateFinished(UpdateResultCode updateResultCode, String str) {
        try {
            RemoteUpdateListener remoteUpdateListener = this.mRemoteUpdateListener;
            if (remoteUpdateListener != null) {
                remoteUpdateListener.onUpdateFinished(updateResultCode, str);
            } else {
                Logger.error(TAG, "onUpdateFinished: remoteUpdateListener is null");
            }
        } catch (Exception e) {
            Logger.error(TAG, "onUpdateFinished exception", e);
        }
    }

    @Override // cerence.ark.assistant.framework.updater.IUpdateListener
    public void onAborted(UpdateResultCode updateResultCode, String str) {
        try {
            RemoteUpdateListener remoteUpdateListener = this.mRemoteUpdateListener;
            if (remoteUpdateListener != null) {
                remoteUpdateListener.onAborted(updateResultCode, str);
            } else {
                Logger.error(TAG, "onAborted: remoteUpdateListener is null");
            }
        } catch (Exception e) {
            Logger.error(TAG, "onAborted exception", e);
        }
    }

    @Override // cerence.ark.assistant.framework.updater.IUpdateListener
    public void onDataDeleted(UpdateResultCode updateResultCode, String str) {
        try {
            RemoteUpdateListener remoteUpdateListener = this.mRemoteUpdateListener;
            if (remoteUpdateListener != null) {
                remoteUpdateListener.onDataDeleted(updateResultCode, str);
            } else {
                Logger.error(TAG, "onDataDeleted: remoteUpdateListener is null");
            }
        } catch (Exception e) {
            Logger.error(TAG, "onDataDeleted exception", e);
        }
    }

    @Override // cerence.ark.assistant.framework.updater.IUpdateListener
    public void onConfirmed(UpdateResultCode updateResultCode, String str) {
        try {
            RemoteUpdateListener remoteUpdateListener = this.mRemoteUpdateListener;
            if (remoteUpdateListener != null) {
                remoteUpdateListener.onConfirmed(updateResultCode, str);
            } else {
                Logger.error(TAG, "onConfirmed: remoteUpdateListener is null");
            }
        } catch (Exception e) {
            Logger.error(TAG, "onConfirmed exception", e);
        }
    }

    @Override // cerence.ark.assistant.framework.updater.IUpdateListener
    public void onRecoveryStarted() {
        try {
            RemoteUpdateListener remoteUpdateListener = this.mRemoteUpdateListener;
            if (remoteUpdateListener != null) {
                remoteUpdateListener.onRecoveryStarted();
            } else {
                Logger.error(TAG, "onRecoveryStarted: remoteUpdateListener is null");
            }
        } catch (Exception e) {
            Logger.error(TAG, "onRecoveryStarted exception", e);
        }
    }

    @Override // cerence.ark.assistant.framework.updater.IUpdateListener
    public void onRecoveryFinished(UpdateResultCode updateResultCode, String str) {
        try {
            RemoteUpdateListener remoteUpdateListener = this.mRemoteUpdateListener;
            if (remoteUpdateListener != null) {
                remoteUpdateListener.onRecoveryFinished(updateResultCode, str);
            } else {
                Logger.error(TAG, "onRecoveryFinished: remoteUpdateListener is null");
            }
        } catch (Exception e) {
            Logger.error(TAG, "onRecoveryFinished exception", e);
        }
    }
}
