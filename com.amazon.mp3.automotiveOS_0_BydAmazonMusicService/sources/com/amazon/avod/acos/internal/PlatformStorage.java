package com.amazon.avod.acos.internal;

import com.amazon.avod.acos.StorageHelper;
import java.io.File;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface PlatformStorage {
    @Nonnull
    File getGeneralFileDir();

    @Nonnull
    File getGeneralFileRootDir();

    @Nonnull
    File getGlobalFileDir();

    File getInternalDownloadDir();

    File getInternalPlaybackDownloadDir();

    boolean requestStorage(@Nonnegative long j, @Nonnull StorageHelper.StorageLocation storageLocation);

    void runIfClearedDataOnAppStartup(@Nonnull Runnable runnable);
}
