package com.amazon.avod.content.image;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.fileio.DiskFullIOException;
import com.amazon.avod.fileio.DiskIOException;
import com.amazon.avod.media.framework.storage.DiskStorage;
import com.amazon.avod.media.framework.storage.FileLockManager;
import com.google.common.base.Preconditions;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.locks.Lock;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ImageFileManager {
    private final DiskStorage mDiskStorage;
    private final FileLockManager mLockManager;
    private final File mRootDir;

    public ImageFileManager(@Nonnull FileLockManager fileLockManager, @Nonnull DiskStorage diskStorage, @Nonnull File file) {
        this.mLockManager = (FileLockManager) Preconditions.checkNotNull(fileLockManager, "lockManager");
        this.mDiskStorage = (DiskStorage) Preconditions.checkNotNull(diskStorage, "diskStorage");
        this.mRootDir = (File) Preconditions.checkNotNull(file, "rootDir");
    }

    public File createFile(@Nonnull String str) {
        Preconditions.checkNotNull(str, "fileName");
        return new File(this.mRootDir, str);
    }

    public void saveToFile(@Nonnull InputStream inputStream, int i, @Nonnull File file) throws ContentException {
        Lock lockAcquireWriteLock = null;
        try {
            try {
                try {
                    try {
                        lockAcquireWriteLock = acquireWriteLock(file);
                        this.mDiskStorage.putInputStream(file.getAbsolutePath(), inputStream, i);
                    } catch (DiskIOException e) {
                        throw new ContentException(ContentException.ContentError.DISK_ERROR, e.getMessage(), e);
                    }
                } catch (DiskFullIOException e2) {
                    throw new ContentException(ContentException.ContentError.DISK_FULL, e2.getMessage(), e2);
                }
            } catch (IOException e3) {
                throw new ContentException(ContentException.ContentError.NETWORK_ERROR, e3.getMessage(), e3);
            }
        } finally {
            if (lockAcquireWriteLock != null) {
                lockAcquireWriteLock.unlock();
            }
        }
    }

    public void deleteFile(@Nonnull File file) throws ContentException {
        Lock lockAcquireWriteLock = null;
        try {
            try {
                lockAcquireWriteLock = acquireWriteLock(file);
                this.mDiskStorage.delete(file.getAbsolutePath());
            } catch (DiskIOException e) {
                throw new ContentException(ContentException.ContentError.DISK_ERROR, e.getMessage(), e);
            }
        } finally {
            if (lockAcquireWriteLock != null) {
                lockAcquireWriteLock.unlock();
            }
        }
    }

    @Nonnull
    private Lock acquireWriteLock(@Nonnull File file) {
        Lock writeLock = this.mLockManager.getWriteLock(file.getAbsolutePath());
        writeLock.lock();
        return writeLock;
    }
}
