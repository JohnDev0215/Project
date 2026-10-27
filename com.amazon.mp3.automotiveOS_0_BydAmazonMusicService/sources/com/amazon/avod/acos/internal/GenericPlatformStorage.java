package com.amazon.avod.acos.internal;

import android.content.Context;
import com.amazon.avod.acos.StorageHelper;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class GenericPlatformStorage implements PlatformStorage {
    private final Context mContext;
    private final File mGeneralFileDir;
    private final File mGlobalFileDir;
    private final boolean mWasClearDataInvoked;

    @Override // com.amazon.avod.acos.internal.PlatformStorage
    public boolean requestStorage(@Nonnegative long j, @Nonnull StorageHelper.StorageLocation storageLocation) {
        return false;
    }

    public static PlatformStorage createPlatformStorage(Context context) {
        Preconditions.checkNotNull(context, "context");
        GenericPlatformStorage genericPlatformStorage = new GenericPlatformStorage(context);
        ensureFolderState(genericPlatformStorage.getGeneralFileDir());
        ensureFolderState(genericPlatformStorage.getGlobalFileDir());
        ensureFolderState(genericPlatformStorage.getInternalDownloadDir());
        ensureFolderState(genericPlatformStorage.getInternalPlaybackDownloadDir());
        return genericPlatformStorage;
    }

    private GenericPlatformStorage(Context context) {
        this.mContext = context;
        File filesDir = context.getFilesDir();
        this.mGeneralFileDir = filesDir;
        this.mGlobalFileDir = new File(filesDir.getAbsolutePath() + File.separator + "global");
        this.mWasClearDataInvoked = checkForSentinelFile();
    }

    @Override // com.amazon.avod.acos.internal.PlatformStorage
    public File getGeneralFileDir() {
        return this.mGeneralFileDir;
    }

    @Override // com.amazon.avod.acos.internal.PlatformStorage
    public File getGeneralFileRootDir() {
        return getGeneralFileDir().getParentFile();
    }

    @Override // com.amazon.avod.acos.internal.PlatformStorage
    public File getGlobalFileDir() {
        return this.mGlobalFileDir;
    }

    @Override // com.amazon.avod.acos.internal.PlatformStorage
    public File getInternalDownloadDir() {
        return new File(getGeneralFileDir(), "downloads");
    }

    @Override // com.amazon.avod.acos.internal.PlatformStorage
    public File getInternalPlaybackDownloadDir() {
        return new File(getGeneralFileDir(), "playback_downloads");
    }

    @Override // com.amazon.avod.acos.internal.PlatformStorage
    public void runIfClearedDataOnAppStartup(Runnable runnable) {
        if (this.mWasClearDataInvoked) {
            runnable.run();
        }
    }

    private boolean checkForSentinelFile() {
        File sentinelFile = getSentinelFile();
        if (!sentinelFile.exists()) {
            try {
                if (sentinelFile.createNewFile()) {
                    return true;
                }
                throw new IOException("Could not create file");
            } catch (IOException unused) {
                DLog.errorf("Could not create file %s. It is unsafe to clear shared storage without this sentinel.", sentinelFile.toString());
            }
        }
        return false;
    }

    private File getSentinelFile() {
        return new File(getGeneralFileDir().getAbsolutePath() + File.separator + "clearDataSentinel");
    }

    private static File ensureFolderState(@Nonnull File file) {
        Preconditions.checkNotNull(file, "fileDir");
        if (file.exists() || file.mkdirs()) {
            return file;
        }
        throw new RuntimeException(String.format(Locale.US, "Failed to create folder %s", file.getName()));
    }
}
