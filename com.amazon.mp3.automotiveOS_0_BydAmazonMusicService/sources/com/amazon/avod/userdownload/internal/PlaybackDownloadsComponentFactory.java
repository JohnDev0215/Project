package com.amazon.avod.userdownload.internal;

import android.content.Context;
import com.amazon.avod.userdownload.internal.PlaybackDownloadLicenseHelper;
import com.amazon.avod.userdownload.rights.RightsManager;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackDownloadsComponentFactory {
    protected final Context mContext;
    protected final PlaybackDownloadLicenseHelper.Factory mLicenseHelperFactory = new PlaybackDownloadLicenseHelper.Factory(Suppliers.memoize(new Supplier() { // from class: com.amazon.avod.userdownload.internal.PlaybackDownloadsComponentFactory$$ExternalSyntheticLambda0
        @Override // com.google.common.base.Supplier
        public final Object get() {
            return this.f$0.lambda$new$0();
        }
    }));
    protected final Supplier<RightsManager> mRightsManager;
    protected final PlaybackDownloadSharedComponents mSharedComponents;

    public PlaybackDownloadsComponentFactory(@Nonnull PlaybackDownloadSharedComponents playbackDownloadSharedComponents, @Nonnull Supplier<RightsManager> supplier, @Nonnull Context context) {
        this.mSharedComponents = (PlaybackDownloadSharedComponents) Preconditions.checkNotNull(playbackDownloadSharedComponents, "sharedComponents");
        this.mRightsManager = (Supplier) Preconditions.checkNotNull(supplier, "noOpRightsManager");
        this.mContext = (Context) Preconditions.checkNotNull(context, "context");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ PlaybackDownloadSharedComponents lambda$new$0() {
        return this.mSharedComponents;
    }

    @Nonnull
    public PlaybackDownloadManager generateDownloadManager() {
        PlaybackDownloadPersistence playbackDownloadPersistenceCreatePersistence = createPersistence();
        return new PlaybackDownloadManager(this.mSharedComponents, playbackDownloadPersistenceCreatePersistence, createPlaybackExecutor(playbackDownloadPersistenceCreatePersistence), this.mRightsManager.get(), this.mLicenseHelperFactory, this.mContext);
    }

    @Nonnull
    protected PlaybackDownloadExecutor createPlaybackExecutor(@Nonnull PlaybackDownloadPersistence playbackDownloadPersistence) {
        PauseTokenVendor pauseTokenVendor = new PauseTokenVendor();
        return new PlaybackDownloadExecutor(playbackDownloadPersistence, this.mSharedComponents.getPlaybackDownloadNotifier(), new PlaybackDownloadQueueManager(playbackDownloadPersistence, pauseTokenVendor), this.mSharedComponents.getPlaybackDownloadEventReporter(), this.mSharedComponents.getLocationConfig(), pauseTokenVendor, this.mSharedComponents.getQueueConfig(), this.mSharedComponents.getStorageHelper(), this.mContext);
    }

    @Nonnull
    protected PlaybackDownloadPersistence createPersistence() {
        return new PlaybackDownloadPersistence(this.mSharedComponents.getStorageHelper(), new PlaybackDownloadDiskPersistence());
    }

    @Nonnull
    public PlaybackDownloadSharedComponents getSharedComponents() {
        return this.mSharedComponents;
    }
}
