package cerence.ark.assistant.framework.impl.updater;

import android.text.TextUtils;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.common.callback.Result;
import cerence.ark.assistant.dm.NativeDialogManager;
import cerence.ark.assistant.dm.data.DmResultCode;
import cerence.ark.assistant.dm.data.NativeUpdateInfo;
import cerence.ark.assistant.framework.data.ErrorCode;
import cerence.ark.assistant.framework.impl.FrameworkUtils;
import cerence.ark.assistant.framework.updater.DownloadStatus;
import cerence.ark.assistant.library.Utils;
import com.bydux.api.common.tools.util.ListUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class UpdaterImpl implements IDataUpdater {
    private static final String TAG = "UpdaterImpl";
    private final NativeDialogManager mNativeDialogManager;

    public UpdaterImpl(NativeDialogManager nativeDialogManager) {
        this.mNativeDialogManager = nativeDialogManager;
    }

    public Result<Void, ErrorCode> initUpdater(UpdateListenerImpl updateListenerImpl) {
        DmResultCode dmResultCodeInitUpdater = this.mNativeDialogManager.initUpdater(updateListenerImpl);
        Logger.info(TAG, Utils.format("initUpdater: %s", dmResultCodeInitUpdater));
        if (FrameworkUtils.isSuccess(dmResultCodeInitUpdater)) {
            return new Result.Builder().buildSuccess();
        }
        return Utils.buildErrorResult(FrameworkUtils.getErrorCode(dmResultCodeInitUpdater));
    }

    public Result<Void, ErrorCode> destroyUpdater() {
        String str = TAG;
        Logger.info(str, "destroyUpdater: begin");
        DmResultCode dmResultCodeDestroyUpdater = this.mNativeDialogManager.destroyUpdater();
        Logger.info(str, Utils.format("destroyUpdater: %s", dmResultCodeDestroyUpdater));
        if (FrameworkUtils.isSuccess(dmResultCodeDestroyUpdater)) {
            return new Result.Builder().buildSuccess();
        }
        return Utils.buildErrorResult(FrameworkUtils.getErrorCode(dmResultCodeDestroyUpdater));
    }

    @Override // cerence.ark.assistant.framework.impl.updater.IDataUpdater
    public String getCurrentVersion(String str) {
        if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, "getCurrentVersion: attributes is null");
            return null;
        }
        String str2 = TAG;
        Logger.info(str2, Utils.format("getCurrentVersion: attributes = %s", str));
        String currentVersion = this.mNativeDialogManager.getCurrentVersion(str);
        Logger.info(str2, Utils.format("getCurrentVersion: %s", currentVersion));
        return currentVersion;
    }

    @Override // cerence.ark.assistant.framework.impl.updater.IDataUpdater
    public List<DownloadStatus> getDownloadStatus(List<String> list) {
        if (list == null || list.isEmpty()) {
            Logger.error(TAG, Utils.format("getDownloadStatus: the parameter attributesList is empty", new Object[0]));
            return new ArrayList();
        }
        Logger.info(TAG, Utils.format("getDownloadStatus: attributesList = %s", TextUtils.join(ListUtils.DEFAULT_JOIN_SEPARATOR, list)));
        NativeUpdateInfo[] downloadStatus = this.mNativeDialogManager.getDownloadStatus((String[]) list.toArray(new String[0]));
        ArrayList arrayList = new ArrayList();
        if (downloadStatus != null) {
            for (NativeUpdateInfo nativeUpdateInfo : downloadStatus) {
                if (nativeUpdateInfo == null) {
                    Logger.warn(TAG, "getDownloadStatus: nativeUpdateInfo is null");
                } else {
                    arrayList.add(DownloadStatus.builder().setAttribute(nativeUpdateInfo.getAttribute()).setOldVersion(nativeUpdateInfo.getOldVersion()).setNewVersion(nativeUpdateInfo.getNewVersion()).setDescription(nativeUpdateInfo.getDescription()).setCurrentSize(nativeUpdateInfo.getCurrentSize()).setTotalSize(nativeUpdateInfo.getTotalSize()).setDownloading(nativeUpdateInfo.getDownloading()).build());
                }
            }
        }
        Logger.info(TAG, Utils.format("getDownloadStatus: %s", TextUtils.join(ListUtils.DEFAULT_JOIN_SEPARATOR, arrayList)));
        return arrayList;
    }

    @Override // cerence.ark.assistant.framework.impl.updater.IDataUpdater
    public Result<Void, ErrorCode> deleteUpdate(String str) {
        String str2 = TAG;
        Logger.info(str2, Utils.format("deleteUpdate: attributes = %s", str));
        DmResultCode dmResultCodeDeleteUpdate = this.mNativeDialogManager.deleteUpdate(str, new String[0]);
        Logger.info(str2, Utils.format("deleteUpdate: %s", dmResultCodeDeleteUpdate));
        if (FrameworkUtils.isSuccess(dmResultCodeDeleteUpdate)) {
            return new Result.Builder().buildSuccess();
        }
        return Utils.buildErrorResult(FrameworkUtils.getErrorCode(dmResultCodeDeleteUpdate));
    }

    @Override // cerence.ark.assistant.framework.impl.updater.IDataUpdater
    public Result<Void, ErrorCode> checkUpdate(List<String> list, boolean z, boolean z2) {
        if (list == null || list.isEmpty()) {
            Logger.error(TAG, Utils.format("checkUpdate: the parameter attributesList is empty", new Object[0]));
            return Utils.buildErrorResult(ErrorCode.PARAMETER_INVALID);
        }
        String str = TAG;
        Logger.info(str, Utils.format("checkUpdate: isQueryFullUpdate = %s, acceptTrial = %s, attributesList = %s", Boolean.valueOf(z), Boolean.valueOf(z2), TextUtils.join(ListUtils.DEFAULT_JOIN_SEPARATOR, list)));
        DmResultCode dmResultCodeCheckUpdate = this.mNativeDialogManager.checkUpdate((String[]) list.toArray(new String[0]), z, z2);
        Logger.info(str, Utils.format("checkUpdate: %s", dmResultCodeCheckUpdate));
        if (FrameworkUtils.isSuccess(dmResultCodeCheckUpdate)) {
            return new Result.Builder().buildSuccess();
        }
        return Utils.buildErrorResult(FrameworkUtils.getErrorCode(dmResultCodeCheckUpdate));
    }

    @Override // cerence.ark.assistant.framework.impl.updater.IDataUpdater
    public Result<Void, ErrorCode> abortUpdate(String str) {
        String str2 = TAG;
        Logger.info(str2, Utils.format("abortUpdate: attributes = %s", str));
        DmResultCode dmResultCodeAbortUpdate = this.mNativeDialogManager.abortUpdate(str);
        Logger.info(str2, Utils.format("abortUpdate: %s", dmResultCodeAbortUpdate));
        if (FrameworkUtils.isSuccess(dmResultCodeAbortUpdate)) {
            return new Result.Builder().buildSuccess();
        }
        return Utils.buildErrorResult(FrameworkUtils.getErrorCode(dmResultCodeAbortUpdate));
    }

    @Override // cerence.ark.assistant.framework.impl.updater.IDataUpdater
    public Result<Void, ErrorCode> stopUpdate() {
        String str = TAG;
        Logger.info(str, "stopUpdate: begin");
        DmResultCode dmResultCodeStopUpdate = this.mNativeDialogManager.stopUpdate();
        Logger.info(str, Utils.format("stopUpdate: %s", dmResultCodeStopUpdate));
        if (FrameworkUtils.isSuccess(dmResultCodeStopUpdate)) {
            return new Result.Builder().buildSuccess();
        }
        return Utils.buildErrorResult(FrameworkUtils.getErrorCode(dmResultCodeStopUpdate));
    }

    @Override // cerence.ark.assistant.framework.impl.updater.IDataUpdater
    public Result<Void, ErrorCode> resumeUpdate(String str) {
        String str2 = TAG;
        Logger.info(str2, Utils.format("resumeUpdate: attributes = %s", str));
        DmResultCode dmResultCodeResumeUpdate = this.mNativeDialogManager.resumeUpdate(str);
        Logger.info(str2, Utils.format("resumeUpdate: %s", dmResultCodeResumeUpdate));
        if (FrameworkUtils.isSuccess(dmResultCodeResumeUpdate)) {
            return new Result.Builder().buildSuccess();
        }
        return Utils.buildErrorResult(FrameworkUtils.getErrorCode(dmResultCodeResumeUpdate));
    }

    @Override // cerence.ark.assistant.framework.impl.updater.IDataUpdater
    public Result<Void, ErrorCode> acceptDownload(String str) {
        String str2 = TAG;
        Logger.info(str2, Utils.format("acceptDownload: attributes = %s", str));
        DmResultCode dmResultCodeAcceptDownload = this.mNativeDialogManager.acceptDownload(str);
        Logger.info(str2, Utils.format("acceptDownload: %s", dmResultCodeAcceptDownload));
        if (FrameworkUtils.isSuccess(dmResultCodeAcceptDownload)) {
            return new Result.Builder().buildSuccess();
        }
        return Utils.buildErrorResult(FrameworkUtils.getErrorCode(dmResultCodeAcceptDownload));
    }

    @Override // cerence.ark.assistant.framework.impl.updater.IDataUpdater
    public Result<Void, ErrorCode> rejectDownload(String str) {
        String str2 = TAG;
        Logger.info(str2, Utils.format("rejectDownload: attributes = %s", str));
        DmResultCode dmResultCodeRejectDownload = this.mNativeDialogManager.rejectDownload(str);
        Logger.info(str2, Utils.format("rejectDownload: %s", dmResultCodeRejectDownload));
        if (FrameworkUtils.isSuccess(dmResultCodeRejectDownload)) {
            return new Result.Builder().buildSuccess();
        }
        return Utils.buildErrorResult(FrameworkUtils.getErrorCode(dmResultCodeRejectDownload));
    }

    @Override // cerence.ark.assistant.framework.impl.updater.IDataUpdater
    public Result<Void, ErrorCode> acceptInstall(String str) {
        String str2 = TAG;
        Logger.info(str2, Utils.format("acceptInstall: attributes = %s", str));
        DmResultCode dmResultCodeAcceptInstall = this.mNativeDialogManager.acceptInstall(str);
        Logger.info(str2, Utils.format("acceptInstall: %s", dmResultCodeAcceptInstall));
        if (FrameworkUtils.isSuccess(dmResultCodeAcceptInstall)) {
            return new Result.Builder().buildSuccess();
        }
        return Utils.buildErrorResult(FrameworkUtils.getErrorCode(dmResultCodeAcceptInstall));
    }

    @Override // cerence.ark.assistant.framework.impl.updater.IDataUpdater
    public Result<Void, ErrorCode> rejectInstall(String str) {
        String str2 = TAG;
        Logger.info(str2, Utils.format("rejectInstall: attributes = %s", str));
        DmResultCode dmResultCodeRejectInstall = this.mNativeDialogManager.rejectInstall(str);
        Logger.info(str2, Utils.format("rejectInstall: %s", dmResultCodeRejectInstall));
        if (FrameworkUtils.isSuccess(dmResultCodeRejectInstall)) {
            return new Result.Builder().buildSuccess();
        }
        return Utils.buildErrorResult(FrameworkUtils.getErrorCode(dmResultCodeRejectInstall));
    }
}
