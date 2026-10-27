package cerence.ark.assistant.framework.impl.updater;

import android.os.Handler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.common.Scheduler;
import cerence.ark.assistant.common.callback.IResultCallback;
import cerence.ark.assistant.common.callback.Result;
import cerence.ark.assistant.dm.DmTokenProvider;
import cerence.ark.assistant.dm.NativeDialogManager;
import cerence.ark.assistant.dm.data.DmRecoveryModeParams;
import cerence.ark.assistant.dm.data.DmResultCode;
import cerence.ark.assistant.framework.data.ErrorCode;
import cerence.ark.assistant.framework.impl.FrameworkUtils;
import cerence.ark.assistant.framework.updater.IRecoveryUpdater;
import cerence.ark.assistant.library.Utils;

/* JADX INFO: loaded from: classes2.dex */
public class RecoveryUpdaterImpl implements IRecoveryUpdater {
    private static final String TAG = "RecoveryUpdaterImpl";
    private final Handler mAssistantHandler;

    public RecoveryUpdaterImpl(Handler handler) {
        this.mAssistantHandler = handler;
    }

    public Result<Void, ErrorCode> initUpdater(DmRecoveryModeParams dmRecoveryModeParams, DmTokenProvider dmTokenProvider, UpdateListenerImpl updateListenerImpl) {
        DmResultCode dmResultCodeEnterRecoveryMode = NativeDialogManager.enterRecoveryMode(dmRecoveryModeParams, dmTokenProvider, updateListenerImpl);
        Logger.info(TAG, Utils.format("enterRecoveryMode: %s", dmResultCodeEnterRecoveryMode));
        if (FrameworkUtils.isSuccess(dmResultCodeEnterRecoveryMode)) {
            return new Result.Builder().buildSuccess();
        }
        return Utils.buildErrorResult(FrameworkUtils.getErrorCode(dmResultCodeEnterRecoveryMode));
    }

    public Result<Void, ErrorCode> destroyUpdater() {
        DmResultCode dmResultCodeExitRecoveryMode = NativeDialogManager.exitRecoveryMode();
        Logger.info(TAG, Utils.format("exitRecoveryMode: %s", dmResultCodeExitRecoveryMode.name()));
        if (FrameworkUtils.isSuccess(dmResultCodeExitRecoveryMode)) {
            return new Result.Builder().buildSuccess();
        }
        return Utils.buildErrorResult(FrameworkUtils.getErrorCode(dmResultCodeExitRecoveryMode));
    }

    @Override // cerence.ark.assistant.framework.updater.IRecoveryUpdater
    public void abortUpdate(final IResultCallback<Void, ErrorCode> iResultCallback) {
        this.mAssistantHandler.post(new Runnable() { // from class: cerence.ark.assistant.framework.impl.updater.RecoveryUpdaterImpl$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                RecoveryUpdaterImpl.lambda$abortUpdate$2(iResultCallback);
            }
        });
    }

    static /* synthetic */ void lambda$abortUpdate$2(final IResultCallback iResultCallback) {
        String str = TAG;
        Logger.info(str, "abortUpdate: begin");
        final DmResultCode dmResultCodeAbortRecovery = NativeDialogManager.abortRecovery();
        Logger.info(str, Utils.format("abortUpdate: %s", dmResultCodeAbortRecovery));
        if (FrameworkUtils.isSuccess(dmResultCodeAbortRecovery)) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.framework.impl.updater.RecoveryUpdaterImpl$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifySuccess(iResultCallback);
                }
            });
        } else {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.framework.impl.updater.RecoveryUpdaterImpl$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(FrameworkUtils.getErrorCode(dmResultCodeAbortRecovery), iResultCallback);
                }
            });
        }
    }

    @Override // cerence.ark.assistant.framework.updater.IRecoveryUpdater
    public void stopUpdate(final IResultCallback<Void, ErrorCode> iResultCallback) {
        this.mAssistantHandler.post(new Runnable() { // from class: cerence.ark.assistant.framework.impl.updater.RecoveryUpdaterImpl$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                RecoveryUpdaterImpl.lambda$stopUpdate$5(iResultCallback);
            }
        });
    }

    static /* synthetic */ void lambda$stopUpdate$5(final IResultCallback iResultCallback) {
        String str = TAG;
        Logger.info(str, "stopUpdate: begin");
        final DmResultCode dmResultCodeStopRecovery = NativeDialogManager.stopRecovery();
        Logger.info(str, Utils.format("stopUpdate: %s", dmResultCodeStopRecovery));
        if (FrameworkUtils.isSuccess(dmResultCodeStopRecovery)) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.framework.impl.updater.RecoveryUpdaterImpl$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifySuccess(iResultCallback);
                }
            });
        } else {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.framework.impl.updater.RecoveryUpdaterImpl$$ExternalSyntheticLambda8
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(FrameworkUtils.getErrorCode(dmResultCodeStopRecovery), iResultCallback);
                }
            });
        }
    }

    @Override // cerence.ark.assistant.framework.updater.IRecoveryUpdater
    public void resumeUpdate(final IResultCallback<Void, ErrorCode> iResultCallback) {
        this.mAssistantHandler.post(new Runnable() { // from class: cerence.ark.assistant.framework.impl.updater.RecoveryUpdaterImpl$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                RecoveryUpdaterImpl.lambda$resumeUpdate$8(iResultCallback);
            }
        });
    }

    static /* synthetic */ void lambda$resumeUpdate$8(final IResultCallback iResultCallback) {
        String str = TAG;
        Logger.info(str, "resumeUpdate: begin");
        final DmResultCode dmResultCodeResumeRecovery = NativeDialogManager.resumeRecovery();
        Logger.info(str, Utils.format("resumeUpdate: %s", dmResultCodeResumeRecovery));
        if (FrameworkUtils.isSuccess(dmResultCodeResumeRecovery)) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.framework.impl.updater.RecoveryUpdaterImpl$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifySuccess(iResultCallback);
                }
            });
        } else {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.framework.impl.updater.RecoveryUpdaterImpl$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(FrameworkUtils.getErrorCode(dmResultCodeResumeRecovery), iResultCallback);
                }
            });
        }
    }
}
