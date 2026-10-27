package cerence.ark.assistant.core;

import android.os.Handler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.common.Scheduler;
import cerence.ark.assistant.common.callback.IResultCallback;
import cerence.ark.assistant.framework.data.ErrorCode;
import cerence.ark.assistant.framework.updater.DownloadStatus;
import cerence.ark.assistant.framework.updater.IUpdater;
import cerence.ark.assistant.library.Utils;
import cerence.ark.assistant.service.RemoteResult;
import cerence.ark.assistant.service.RemoteUpdater;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class UpdaterProxy implements IUpdater {
    private static final String TAG = "UpdaterProxy";
    private final Handler mAssistantHandler;
    private final RemoteUpdater mRemoteUpdater;

    public UpdaterProxy(RemoteUpdater remoteUpdater, Handler handler) {
        this.mRemoteUpdater = remoteUpdater;
        this.mAssistantHandler = handler;
    }

    public RemoteUpdater getRemoteUpdater() {
        return this.mRemoteUpdater;
    }

    @Override // cerence.ark.assistant.framework.updater.IUpdater
    public String getCurrentVersion(String str) {
        try {
            RemoteUpdater remoteUpdater = this.mRemoteUpdater;
            if (remoteUpdater == null) {
                Logger.error(TAG, "getCurrentVersion: remoteUpdater is null");
                return null;
            }
            return remoteUpdater.getCurrentVersion(str);
        } catch (Exception e) {
            Logger.error(TAG, "getCurrentVersion: exception", e);
            return null;
        }
    }

    @Override // cerence.ark.assistant.framework.updater.IUpdater
    public void queryDownloadStatus(final List<String> list, final IResultCallback<List<DownloadStatus>, ErrorCode> iResultCallback) {
        this.mAssistantHandler.post(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m67x5794ddaf(iResultCallback, list);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$queryDownloadStatus$3$cerence-ark-assistant-core-UpdaterProxy, reason: not valid java name */
    /* synthetic */ void m67x5794ddaf(final IResultCallback iResultCallback, List list) {
        try {
            RemoteUpdater remoteUpdater = this.mRemoteUpdater;
            if (remoteUpdater == null) {
                Logger.error(TAG, "queryDownloadStatus: remoteUpdater is null");
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifyError(ErrorCode.UNKNOWN, iResultCallback);
                    }
                });
            } else {
                final List<DownloadStatus> downloadStatus = remoteUpdater.getDownloadStatus(list);
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda11
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifySuccess(downloadStatus, iResultCallback);
                    }
                });
            }
        } catch (Exception e) {
            Logger.error(TAG, "queryDownloadStatus: exception", e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda22
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }

    @Override // cerence.ark.assistant.framework.updater.IUpdater
    public void deleteUpdate(final String str, final IResultCallback<Void, ErrorCode> iResultCallback) {
        this.mAssistantHandler.post(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m66lambda$deleteUpdate$8$cerencearkassistantcoreUpdaterProxy(iResultCallback, str);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$deleteUpdate$8$cerence-ark-assistant-core-UpdaterProxy, reason: not valid java name */
    /* synthetic */ void m66lambda$deleteUpdate$8$cerencearkassistantcoreUpdaterProxy(final IResultCallback iResultCallback, String str) {
        try {
            RemoteUpdater remoteUpdater = this.mRemoteUpdater;
            if (remoteUpdater == null) {
                Logger.error(TAG, "deleteUpdate: remoteUpdater is null");
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda27
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifyError(ErrorCode.UNKNOWN, iResultCallback);
                    }
                });
                return;
            }
            final RemoteResult remoteResultDeleteUpdate = remoteUpdater.deleteUpdate(str);
            if (remoteResultDeleteUpdate.isSuccess()) {
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda28
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifySuccess(iResultCallback);
                    }
                });
            } else {
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda29
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifyError(remoteResultDeleteUpdate.getErrorCode(), iResultCallback);
                    }
                });
            }
        } catch (Exception e) {
            Logger.error(TAG, "deleteUpdate: exception", e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda30
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }

    @Override // cerence.ark.assistant.framework.updater.IUpdater
    public void checkUpdate(final List<String> list, final boolean z, final boolean z2, final IResultCallback<Void, ErrorCode> iResultCallback) {
        this.mAssistantHandler.post(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m65lambda$checkUpdate$13$cerencearkassistantcoreUpdaterProxy(iResultCallback, list, z, z2);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$checkUpdate$13$cerence-ark-assistant-core-UpdaterProxy, reason: not valid java name */
    /* synthetic */ void m65lambda$checkUpdate$13$cerencearkassistantcoreUpdaterProxy(final IResultCallback iResultCallback, List list, boolean z, boolean z2) {
        try {
            RemoteUpdater remoteUpdater = this.mRemoteUpdater;
            if (remoteUpdater == null) {
                Logger.error(TAG, "checkUpdate: remoteUpdater is null");
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda7
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifyError(ErrorCode.UNKNOWN, iResultCallback);
                    }
                });
                return;
            }
            final RemoteResult remoteResultCheckUpdate = remoteUpdater.checkUpdate(list, z, z2);
            if (remoteResultCheckUpdate.isSuccess()) {
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda8
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifySuccess(iResultCallback);
                    }
                });
            } else {
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda9
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifyError(remoteResultCheckUpdate.getErrorCode(), iResultCallback);
                    }
                });
            }
        } catch (Exception e) {
            Logger.error(TAG, "checkUpdate: exception", e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda10
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }

    @Override // cerence.ark.assistant.framework.updater.IUpdater
    public void abortUpdate(final String str, final IResultCallback<Void, ErrorCode> iResultCallback) {
        this.mAssistantHandler.post(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda26
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m62lambda$abortUpdate$18$cerencearkassistantcoreUpdaterProxy(iResultCallback, str);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$abortUpdate$18$cerence-ark-assistant-core-UpdaterProxy, reason: not valid java name */
    /* synthetic */ void m62lambda$abortUpdate$18$cerencearkassistantcoreUpdaterProxy(final IResultCallback iResultCallback, String str) {
        try {
            RemoteUpdater remoteUpdater = this.mRemoteUpdater;
            if (remoteUpdater == null) {
                Logger.error(TAG, "abortUpdate: remoteUpdater is null");
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda46
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifyError(ErrorCode.UNKNOWN, iResultCallback);
                    }
                });
                return;
            }
            final RemoteResult remoteResultAbortUpdate = remoteUpdater.abortUpdate(str);
            if (remoteResultAbortUpdate.isSuccess()) {
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda47
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifySuccess(iResultCallback);
                    }
                });
            } else {
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda48
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifyError(remoteResultAbortUpdate.getErrorCode(), iResultCallback);
                    }
                });
            }
        } catch (Exception e) {
            Logger.error(TAG, "abortUpdate: exception", e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }

    @Override // cerence.ark.assistant.framework.updater.IUpdater
    public void stopUpdate(final IResultCallback<Void, ErrorCode> iResultCallback) {
        this.mAssistantHandler.post(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m71lambda$stopUpdate$23$cerencearkassistantcoreUpdaterProxy(iResultCallback);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$stopUpdate$23$cerence-ark-assistant-core-UpdaterProxy, reason: not valid java name */
    /* synthetic */ void m71lambda$stopUpdate$23$cerencearkassistantcoreUpdaterProxy(final IResultCallback iResultCallback) {
        try {
            RemoteUpdater remoteUpdater = this.mRemoteUpdater;
            if (remoteUpdater == null) {
                Logger.error(TAG, "stopUpdate: remoteUpdater is null");
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda33
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifyError(ErrorCode.UNKNOWN, iResultCallback);
                    }
                });
                return;
            }
            final RemoteResult remoteResultStopUpdate = remoteUpdater.stopUpdate();
            if (remoteResultStopUpdate.isSuccess()) {
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda43
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifySuccess(iResultCallback);
                    }
                });
            } else {
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda44
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifyError(remoteResultStopUpdate.getErrorCode(), iResultCallback);
                    }
                });
            }
        } catch (Exception e) {
            Logger.error(TAG, "stopUpdate: exception", e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda45
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }

    @Override // cerence.ark.assistant.framework.updater.IUpdater
    public void resumeUpdate(final String str, final IResultCallback<Void, ErrorCode> iResultCallback) {
        this.mAssistantHandler.post(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda37
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m70lambda$resumeUpdate$28$cerencearkassistantcoreUpdaterProxy(iResultCallback, str);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$resumeUpdate$28$cerence-ark-assistant-core-UpdaterProxy, reason: not valid java name */
    /* synthetic */ void m70lambda$resumeUpdate$28$cerencearkassistantcoreUpdaterProxy(final IResultCallback iResultCallback, String str) {
        try {
            RemoteUpdater remoteUpdater = this.mRemoteUpdater;
            if (remoteUpdater == null) {
                Logger.error(TAG, "resumeUpdate: remoteUpdater is null");
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda16
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifyError(ErrorCode.UNKNOWN, iResultCallback);
                    }
                });
                return;
            }
            final RemoteResult remoteResultResumeUpdate = remoteUpdater.resumeUpdate(str);
            if (remoteResultResumeUpdate.isSuccess()) {
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda17
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifySuccess(iResultCallback);
                    }
                });
            } else {
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda18
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifyError(remoteResultResumeUpdate.getErrorCode(), iResultCallback);
                    }
                });
            }
        } catch (Exception e) {
            Logger.error(TAG, "resumeUpdate: exception", e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda19
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }

    @Override // cerence.ark.assistant.framework.updater.IUpdater
    public void acceptDownload(final String str, final IResultCallback<Void, ErrorCode> iResultCallback) {
        this.mAssistantHandler.post(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda15
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m63lambda$acceptDownload$33$cerencearkassistantcoreUpdaterProxy(iResultCallback, str);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$acceptDownload$33$cerence-ark-assistant-core-UpdaterProxy, reason: not valid java name */
    /* synthetic */ void m63lambda$acceptDownload$33$cerencearkassistantcoreUpdaterProxy(final IResultCallback iResultCallback, String str) {
        try {
            RemoteUpdater remoteUpdater = this.mRemoteUpdater;
            if (remoteUpdater == null) {
                Logger.error(TAG, "acceptDownload: remoteUpdater is null");
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda20
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifyError(ErrorCode.UNKNOWN, iResultCallback);
                    }
                });
                return;
            }
            final RemoteResult remoteResultAcceptDownload = remoteUpdater.acceptDownload(str);
            if (remoteResultAcceptDownload.isSuccess()) {
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda21
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifySuccess(iResultCallback);
                    }
                });
            } else {
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda23
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifyError(remoteResultAcceptDownload.getErrorCode(), iResultCallback);
                    }
                });
            }
        } catch (Exception e) {
            Logger.error(TAG, "acceptDownload: exception", e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda24
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }

    @Override // cerence.ark.assistant.framework.updater.IUpdater
    public void rejectDownload(final String str, final IResultCallback<Void, ErrorCode> iResultCallback) {
        this.mAssistantHandler.post(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda38
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m68lambda$rejectDownload$38$cerencearkassistantcoreUpdaterProxy(iResultCallback, str);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$rejectDownload$38$cerence-ark-assistant-core-UpdaterProxy, reason: not valid java name */
    /* synthetic */ void m68lambda$rejectDownload$38$cerencearkassistantcoreUpdaterProxy(final IResultCallback iResultCallback, String str) {
        try {
            RemoteUpdater remoteUpdater = this.mRemoteUpdater;
            if (remoteUpdater == null) {
                Logger.error(TAG, "rejectDownload: remoteUpdater is null");
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda32
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifyError(ErrorCode.UNKNOWN, iResultCallback);
                    }
                });
                return;
            }
            final RemoteResult remoteResultRejectDownload = remoteUpdater.rejectDownload(str);
            if (remoteResultRejectDownload.isSuccess()) {
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda34
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifySuccess(iResultCallback);
                    }
                });
            } else {
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda35
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifyError(remoteResultRejectDownload.getErrorCode(), iResultCallback);
                    }
                });
            }
        } catch (Exception e) {
            Logger.error(TAG, "rejectDownload: exception", e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda36
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }

    @Override // cerence.ark.assistant.framework.updater.IUpdater
    public void acceptInstall(final String str, final IResultCallback<Void, ErrorCode> iResultCallback) {
        this.mAssistantHandler.post(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda25
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m64lambda$acceptInstall$43$cerencearkassistantcoreUpdaterProxy(iResultCallback, str);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$acceptInstall$43$cerence-ark-assistant-core-UpdaterProxy, reason: not valid java name */
    /* synthetic */ void m64lambda$acceptInstall$43$cerencearkassistantcoreUpdaterProxy(final IResultCallback iResultCallback, String str) {
        try {
            RemoteUpdater remoteUpdater = this.mRemoteUpdater;
            if (remoteUpdater == null) {
                Logger.error(TAG, "acceptInstall: remoteUpdater is null");
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda39
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifyError(ErrorCode.UNKNOWN, iResultCallback);
                    }
                });
                return;
            }
            final RemoteResult remoteResultAcceptInstall = remoteUpdater.acceptInstall(str);
            if (remoteResultAcceptInstall.isSuccess()) {
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda40
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifySuccess(iResultCallback);
                    }
                });
            } else {
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda41
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifyError(remoteResultAcceptInstall.getErrorCode(), iResultCallback);
                    }
                });
            }
        } catch (Exception e) {
            Logger.error(TAG, "acceptInstall: exception", e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda42
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }

    @Override // cerence.ark.assistant.framework.updater.IUpdater
    public void rejectInstall(final String str, final IResultCallback<Void, ErrorCode> iResultCallback) {
        this.mAssistantHandler.post(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda31
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m69lambda$rejectInstall$48$cerencearkassistantcoreUpdaterProxy(iResultCallback, str);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$rejectInstall$48$cerence-ark-assistant-core-UpdaterProxy, reason: not valid java name */
    /* synthetic */ void m69lambda$rejectInstall$48$cerencearkassistantcoreUpdaterProxy(final IResultCallback iResultCallback, String str) {
        try {
            RemoteUpdater remoteUpdater = this.mRemoteUpdater;
            if (remoteUpdater == null) {
                Logger.error(TAG, "rejectInstall: remoteUpdater is null");
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifyError(ErrorCode.UNKNOWN, iResultCallback);
                    }
                });
                return;
            }
            final RemoteResult remoteResultRejectInstall = remoteUpdater.rejectInstall(str);
            if (remoteResultRejectInstall.isSuccess()) {
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifySuccess(iResultCallback);
                    }
                });
            } else {
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifyError(remoteResultRejectInstall.getErrorCode(), iResultCallback);
                    }
                });
            }
        } catch (Exception e) {
            Logger.error(TAG, "rejectInstall: exception", e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.UpdaterProxy$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }
}
