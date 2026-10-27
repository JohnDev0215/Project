package com.amazon.avod.content.smoothstream.manifest.acquisition;

import com.amazon.avod.util.BetaConfig;
import com.amazon.avod.util.BetaConfigProvider;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ManifestUploaderFactory {

    @Nonnull
    private final BetaConfig mBetaConfig;

    @Nonnull
    private final ManifestCapturerConfig mConfig;

    public ManifestUploaderFactory() {
        this(ManifestCapturerConfig.INSTANCE, BetaConfigProvider.getInstance().provideBetaConfig());
    }

    ManifestUploaderFactory(@Nonnull ManifestCapturerConfig manifestCapturerConfig, @Nonnull BetaConfig betaConfig) {
        this.mConfig = (ManifestCapturerConfig) Preconditions.checkNotNull(manifestCapturerConfig, "manifestCapturerConfig");
        this.mBetaConfig = (BetaConfig) Preconditions.checkNotNull(betaConfig, "betaConfig");
    }

    @Nonnull
    public ManifestUploader createManifestUploader() {
        ArrayList arrayList = new ArrayList();
        if (this.mConfig.isCrashboardsUploadEnabled()) {
            arrayList.add(new CrashboardManifestUploader());
        }
        if (this.mConfig.isKinesisUploadEnabled()) {
            arrayList.add(new PostManifestUploader());
        }
        if (this.mConfig.isExternalStorageDownloadEnabled()) {
            arrayList.add(new ExternalStorageManifestWriter());
        } else {
            ExternalStorageManifestWriter.clearData();
        }
        return new CompositeManifestUploader(arrayList);
    }
}
