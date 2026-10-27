package com.amazon.avod.media.framework.platform;

import com.amazon.avod.acos.StorageHelper;
import com.amazon.avod.util.Preconditions2;
import com.google.common.base.Preconditions;
import java.io.File;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidPlatform implements FileSystem {
    private final StorageHelper mStorageHelper = StorageHelper.getInstance();

    @Override // com.amazon.avod.media.framework.platform.FileSystem
    public File getFilesDir() {
        return this.mStorageHelper.getGeneralFileDir();
    }

    @Override // com.amazon.avod.media.framework.platform.FileSystem
    public File getGlobalSharedFileDir() {
        return this.mStorageHelper.getGlobalSharedDir();
    }

    @Override // com.amazon.avod.media.framework.platform.FileSystem
    public long getRemainingDiskSpaceInBytes() {
        return getRemainingDiskSpaceInBytes(null);
    }

    @Override // com.amazon.avod.media.framework.platform.FileSystem
    public long getRemainingDiskSpaceInBytes(File file) {
        if (file == null) {
            file = getFilesDir();
        }
        return this.mStorageHelper.getAvailableStorageInBytes(file);
    }

    @Override // com.amazon.avod.media.framework.platform.FileSystem
    public boolean requestStorage(@Nonnegative long j, @Nonnull File file) {
        Preconditions2.checkNonNegative(j, "Cannot request a negative amount of storage");
        Preconditions.checkNotNull(file, "location");
        return this.mStorageHelper.requestStorage(j, file);
    }

    @Override // com.amazon.avod.media.framework.platform.FileSystem
    public File getLicenseStoreRootDir() {
        return getFilesDir();
    }
}
