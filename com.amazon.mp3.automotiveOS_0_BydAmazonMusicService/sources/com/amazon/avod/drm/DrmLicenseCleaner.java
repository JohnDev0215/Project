package com.amazon.avod.drm;

import com.amazon.avod.acos.StorageHelper;
import com.amazon.avod.drm.playready.PlayReadyLicensingService;
import com.amazon.avod.media.framework.MediaComponent;
import com.amazon.avod.media.framework.config.LicensingConfig;
import com.amazon.avod.media.framework.platform.AndroidOwnerChecker;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DrmLicenseCleaner implements MediaComponent {
    private final BaseDrmSystem mDrmSystem;
    private final ExecutorService mExecutor;
    private final PlayReadyLicensingService mLicensingService;
    private final AndroidOwnerChecker mOwnerChecker = new AndroidOwnerChecker();
    private final StorageHelper mStorageHelper = StorageHelper.getInstance();
    private final LicensingConfig mLicensingConfig = LicensingConfig.getInstance();

    public DrmLicenseCleaner(@Nonnull BaseDrmSystem baseDrmSystem, @Nonnull PlayReadyLicensingService playReadyLicensingService, @Nonnull ExecutorService executorService) {
        this.mDrmSystem = (BaseDrmSystem) Preconditions.checkNotNull(baseDrmSystem, "drmSystem");
        this.mLicensingService = (PlayReadyLicensingService) Preconditions.checkNotNull(playReadyLicensingService, "licensingService");
        this.mExecutor = (ExecutorService) Preconditions.checkNotNull(executorService, "executor");
    }

    @Override // com.amazon.avod.media.framework.MediaComponent
    public synchronized void initialize() {
        if (this.mLicensingConfig.isResetDrmOnClearDataEnabled()) {
            this.mExecutor.submit(new Runnable() { // from class: com.amazon.avod.drm.DrmLicenseCleaner$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$initialize$1();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initialize$1() {
        this.mStorageHelper.runIfClearedDataOnAppStartup(new Runnable() { // from class: com.amazon.avod.drm.DrmLicenseCleaner$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$null$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$null$0() {
        DLog.logf("App data has been cleared: deleting all DRM licenses and resetting DRM state");
        resetDrm();
    }

    private synchronized void resetDrm() {
        this.mDrmSystem.deleteAllLicenses();
        this.mLicensingService.resetState();
    }
}
