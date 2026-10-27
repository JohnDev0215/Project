package com.amazon.avod.media.framework.storage;

import android.content.Context;
import android.os.storage.StorageManager;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class InMemoryMountedFolder {
    private static final Method MOUNT_TMPFS_METHOD = getMountTmpfsMethod();
    private static final Method UNMOUNT_TMPFS_METHOD = getUnmountTmpfsMethod();
    private String mPath;
    private final StorageManager mStorageManager;
    private final Object mSyncObject = new Object();
    private boolean mPathIsMounted = false;

    public InMemoryMountedFolder(@Nonnull Context context) {
        Preconditions.checkNotNull(context);
        DLog.logf("Retrieving the storage manager from storage service");
        StorageManager storageManager = (StorageManager) context.getSystemService("storage");
        this.mStorageManager = storageManager;
        if (storageManager == null) {
            DLog.warnf("Failed to retrieve an instance of the storage manager. Tmpfs optimization is not supported or disabled");
        } else {
            DLog.logf("Successfully retrieved an instance of the storage manager");
        }
    }

    public void mount(@Nonnull String str, int i) {
        Method method;
        synchronized (this.mSyncObject) {
            if (this.mStorageManager != null && (method = MOUNT_TMPFS_METHOD) != null && !this.mPathIsMounted) {
                this.mPath = (String) Preconditions.checkNotNull(str, "path");
                Preconditions.checkArgument(i > 0, "size_mb");
                try {
                    ensurePathExists(str);
                    method.invoke(this.mStorageManager, str, Integer.valueOf(i));
                } catch (Exception e) {
                    DLog.exceptionf(e);
                }
                this.mPathIsMounted = true;
            }
        }
    }

    public void unmount() {
        Method method;
        synchronized (this.mSyncObject) {
            if (this.mStorageManager != null && (method = UNMOUNT_TMPFS_METHOD) != null && this.mPathIsMounted) {
                Preconditions.checkState(this.mPath != null, "path");
                try {
                    method.invoke(this.mStorageManager, this.mPath);
                } catch (Exception e) {
                    DLog.exceptionf(e);
                }
                this.mPathIsMounted = false;
            }
        }
    }

    private static void ensurePathExists(String str) throws FileNotFoundException {
        File file = new File(str);
        if (file.exists() || file.mkdirs()) {
            return;
        }
        String str2 = String.format(Locale.US, "Could not create all subdirectories for %s", str);
        DLog.errorf(str2);
        throw new FileNotFoundException(str2);
    }

    private static Method getMountTmpfsMethod() {
        Method method;
        try {
            method = StorageManager.class.getMethod("mountTmpfs", String.class, Integer.TYPE);
        } catch (Exception unused) {
            method = null;
        }
        if (method != null) {
            DLog.logf("Function mountTmpfs available for InMemoryMappedFolder");
        } else {
            DLog.logf("Function mountTmpfs not available for InMemoryMappedFolder");
        }
        return method;
    }

    private static Method getUnmountTmpfsMethod() {
        Method method;
        try {
            method = StorageManager.class.getMethod("unMountTmpfs", String.class);
        } catch (Exception unused) {
            method = null;
        }
        if (method != null) {
            DLog.logf("Function unMountTmpfs available for InMemoryMappedFolder");
        } else {
            DLog.logf("Function unMountTmpfs not available for InMemoryMappedFolder");
        }
        return method;
    }
}
