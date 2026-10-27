package com.amazon.avod.media.framework.platform;

import java.io.File;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface FileSystem {
    File getFilesDir();

    File getGlobalSharedFileDir();

    File getLicenseStoreRootDir();

    long getRemainingDiskSpaceInBytes();

    long getRemainingDiskSpaceInBytes(File file);

    boolean requestStorage(@Nonnegative long j, @Nonnull File file);
}
