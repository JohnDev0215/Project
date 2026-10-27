package cerence.ark.assistant.framework.impl.updater;

import android.text.TextUtils;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.dm.NativeDialogManager;
import cerence.ark.assistant.dm.data.NativeUpdateInfo;
import cerence.ark.assistant.dm.data.NativeUpdateResultCode;
import cerence.ark.assistant.framework.impl.FrameworkUtils;
import cerence.ark.assistant.framework.updater.IUpdateListener;
import cerence.ark.assistant.framework.updater.UpdateInfo;
import cerence.ark.assistant.library.Utils;
import com.bydux.api.common.tools.util.ListUtils;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class UpdateListenerImpl implements NativeDialogManager.UpdaterCallback {
    private static final String TAG = "UpdateListenerImpl";
    private final IUpdateListener mUpdateListener;

    public UpdateListenerImpl(IUpdateListener iUpdateListener) {
        this.mUpdateListener = iUpdateListener;
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.UpdaterCallback
    public void onCheckUpdateStarted() {
        String str = TAG;
        Logger.info(str, "onCheckUpdateStarted");
        IUpdateListener iUpdateListener = this.mUpdateListener;
        if (iUpdateListener != null) {
            iUpdateListener.onCheckUpdateStarted();
        } else {
            Logger.error(str, "onCheckUpdateStarted: updateListener is null");
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.UpdaterCallback
    public void onCheckUpdateFinished(boolean z, NativeUpdateInfo[] nativeUpdateInfoArr, NativeUpdateResultCode nativeUpdateResultCode) {
        String str = TAG;
        Logger.info(str, Utils.format("onCheckUpdateFinished: updateAvailable = %s, resultCode = %s", Boolean.valueOf(z), nativeUpdateResultCode));
        if (this.mUpdateListener == null) {
            Logger.error(str, "onCheckUpdateFinished: updateListener is null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (nativeUpdateInfoArr != null && nativeUpdateInfoArr.length > 0) {
            for (NativeUpdateInfo nativeUpdateInfo : nativeUpdateInfoArr) {
                if (nativeUpdateInfo == null) {
                    Logger.error(TAG, "onCheckUpdateFinished: updateInfo is null");
                } else {
                    arrayList.add(UpdateInfo.builder().setAttribute(nativeUpdateInfo.getAttribute()).setDescription(nativeUpdateInfo.getDescription()).setOldVersion(nativeUpdateInfo.getOldVersion()).setUpdateSize(nativeUpdateInfo.getTotalSize()).setNewVersion(nativeUpdateInfo.getNewVersion()).build());
                }
            }
        }
        Logger.info(TAG, Utils.format("onCheckUpdateFinished: %s", TextUtils.join(ListUtils.DEFAULT_JOIN_SEPARATOR, arrayList)));
        this.mUpdateListener.onCheckUpdateFinished(FrameworkUtils.getUpdateResultCode(nativeUpdateResultCode), z, arrayList);
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.UpdaterCallback
    public void onDownloadStarted(String str) {
        String str2 = TAG;
        Logger.info(str2, Utils.format("onDownloadStarted: attributes = %s", str));
        IUpdateListener iUpdateListener = this.mUpdateListener;
        if (iUpdateListener != null) {
            iUpdateListener.onDownloadStarted(str);
        } else {
            Logger.error(str2, "onDownloadStarted: updateListener is null");
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.UpdaterCallback
    public void onDownloadProgress(String str, long j, long j2, boolean z) {
        if (!z) {
            Logger.error(TAG, "onDownloadProgress: network has been disconnected");
        }
        IUpdateListener iUpdateListener = this.mUpdateListener;
        if (iUpdateListener != null) {
            iUpdateListener.onDownloadProgress(str, j, j2, z);
        } else {
            Logger.error(TAG, "onDownloadProgress: updateListener is null");
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.UpdaterCallback
    public void onDownloadFinished(String str, NativeUpdateResultCode nativeUpdateResultCode) {
        String str2 = TAG;
        Logger.info(str2, Utils.format("onDownloadFinished: attributes = %s, resultCode = %s", str, nativeUpdateResultCode));
        IUpdateListener iUpdateListener = this.mUpdateListener;
        if (iUpdateListener != null) {
            iUpdateListener.onDownloadFinished(FrameworkUtils.getUpdateResultCode(nativeUpdateResultCode), str);
        } else {
            Logger.error(str2, "onDownloadFinished: updateListener is null");
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.UpdaterCallback
    public void onUpdateInstallable(NativeUpdateInfo nativeUpdateInfo, boolean z) {
        String str = TAG;
        Logger.info(str, Utils.format("onUpdateInstallable: consentRequired = %s", Boolean.valueOf(z)));
        if (this.mUpdateListener != null) {
            UpdateInfo.Builder builder = UpdateInfo.builder();
            if (nativeUpdateInfo != null) {
                builder.setAttribute(nativeUpdateInfo.getAttribute()).setDescription(nativeUpdateInfo.getDescription()).setOldVersion(nativeUpdateInfo.getOldVersion()).setUpdateSize(nativeUpdateInfo.getTotalSize()).setNewVersion(nativeUpdateInfo.getNewVersion());
            } else {
                Logger.error(str, "onUpdateInstallable: updateInfo is null");
            }
            UpdateInfo updateInfoBuild = builder.build();
            Logger.info(str, Utils.format("onUpdateInstallable: %s", updateInfoBuild));
            this.mUpdateListener.onUpdateInstallable(updateInfoBuild, z);
            return;
        }
        Logger.error(str, "onUpdateInstallable: updateListener is null");
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.UpdaterCallback
    public void onUpdateFinished(String str, NativeUpdateResultCode nativeUpdateResultCode) {
        String str2 = TAG;
        Logger.info(str2, Utils.format("onUpdateFinished: attributes = %s, resultCode = %s", str, nativeUpdateResultCode));
        IUpdateListener iUpdateListener = this.mUpdateListener;
        if (iUpdateListener != null) {
            iUpdateListener.onUpdateFinished(FrameworkUtils.getUpdateResultCode(nativeUpdateResultCode), str);
        } else {
            Logger.error(str2, "onUpdateFinished: updateListener is null");
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.UpdaterCallback
    public void onUpdateAborted(String str, NativeUpdateResultCode nativeUpdateResultCode) {
        String str2 = TAG;
        Logger.info(str2, Utils.format("onUpdateAborted: attributes = %s, resultCode = %s", str, nativeUpdateResultCode));
        IUpdateListener iUpdateListener = this.mUpdateListener;
        if (iUpdateListener != null) {
            iUpdateListener.onAborted(FrameworkUtils.getUpdateResultCode(nativeUpdateResultCode), str);
        } else {
            Logger.error(str2, "onUpdateAborted: updateListener is null");
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.UpdaterCallback
    public void onRecoveryStarted() {
        String str = TAG;
        Logger.info(str, "onRecoveryStarted");
        IUpdateListener iUpdateListener = this.mUpdateListener;
        if (iUpdateListener != null) {
            iUpdateListener.onRecoveryStarted();
        } else {
            Logger.error(str, "onRecoveryStarted: updateListener is null");
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.UpdaterCallback
    public void onRecoveryFinished(String str, NativeUpdateResultCode nativeUpdateResultCode) {
        String str2 = TAG;
        Logger.info(str2, Utils.format("onRecoveryFinished: attributes = %s, resultCode = %s", str, nativeUpdateResultCode));
        IUpdateListener iUpdateListener = this.mUpdateListener;
        if (iUpdateListener != null) {
            iUpdateListener.onRecoveryFinished(FrameworkUtils.getUpdateResultCode(nativeUpdateResultCode), str);
        } else {
            Logger.error(str2, "onRecoveryFinished: updateListener is null");
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.UpdaterCallback
    public void onDataDeleted(String str, NativeUpdateResultCode nativeUpdateResultCode) {
        String str2 = TAG;
        Logger.info(str2, Utils.format("onDataDeleted: attributes = %s, resultCode = %s", str, nativeUpdateResultCode));
        IUpdateListener iUpdateListener = this.mUpdateListener;
        if (iUpdateListener != null) {
            iUpdateListener.onDataDeleted(FrameworkUtils.getUpdateResultCode(nativeUpdateResultCode), str);
        } else {
            Logger.error(str2, "onDataDeleted: updateListener is null");
        }
    }

    @Override // cerence.ark.assistant.dm.NativeDialogManager.UpdaterCallback
    public void onConfirmed(String str, NativeUpdateResultCode nativeUpdateResultCode) {
        String str2 = TAG;
        Logger.info(str2, Utils.format("onConfirmed: attributes = %s, resultCode = %s", str, nativeUpdateResultCode));
        IUpdateListener iUpdateListener = this.mUpdateListener;
        if (iUpdateListener != null) {
            iUpdateListener.onConfirmed(FrameworkUtils.getUpdateResultCode(nativeUpdateResultCode), str);
        } else {
            Logger.error(str2, "onConfirmed: updateListener is null");
        }
    }
}
