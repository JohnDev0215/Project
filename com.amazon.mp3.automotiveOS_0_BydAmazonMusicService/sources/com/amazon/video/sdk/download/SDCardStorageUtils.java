package com.amazon.video.sdk.download;

import android.content.Context;
import android.os.Environment;
import androidx.core.content.ContextCompat;
import androidx.core.os.EnvironmentCompat;
import com.amazon.avod.acos.SecondaryStorageUtils;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.util.DLog;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.io.File;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes5.dex */
public class SDCardStorageUtils implements SecondaryStorageUtils {
    private final Context mContext;
    private final String mDeviceId;
    private boolean mSdCardDetected = false;

    public SDCardStorageUtils(@Nonnull Context context, @Nonnull String str) {
        this.mContext = (Context) Preconditions.checkNotNull(context, "context");
        this.mDeviceId = (String) Preconditions.checkNotNull(str, Splash.PARAMS_DEVICE_ID);
    }

    @Override // com.amazon.avod.acos.SecondaryStorageUtils
    @Nonnull
    public Optional<File> getExternalStoragePath() {
        Optional<File> currentSDCardFile = getCurrentSDCardFile();
        if (!currentSDCardFile.isPresent()) {
            return Optional.absent();
        }
        return verifyStorageState(currentSDCardFile, true);
    }

    @Override // com.amazon.avod.acos.SecondaryStorageUtils
    @Nonnull
    public Optional<File> getExternalStoragePathIfWritable() {
        Optional<File> currentSDCardFile = getCurrentSDCardFile();
        if (!currentSDCardFile.isPresent()) {
            return Optional.absent();
        }
        return verifyStorageState(currentSDCardFile, false);
    }

    private Optional<File> verifyStorageState(Optional<File> optional, boolean z) {
        return isValidStorageState(EnvironmentCompat.getStorageState(optional.get()), z) ? optional : Optional.absent();
    }

    private boolean isValidStorageState(String str, boolean z) {
        if (z) {
            return str.equals("mounted") || str.equals("mounted_ro");
        }
        return str.equals("mounted");
    }

    @Override // com.amazon.avod.acos.SecondaryStorageUtils
    @Nonnull
    public Optional<File> getExternalPathForUserDownload() {
        Optional<File> externalStoragePathIfWritable = getExternalStoragePathIfWritable();
        if (!externalStoragePathIfWritable.isPresent()) {
            return Optional.absent();
        }
        return Optional.of(new File(new File(externalStoragePathIfWritable.get(), this.mDeviceId), ContentSessionType.DOWNLOAD.getDataPartition()));
    }

    @Override // com.amazon.avod.acos.SecondaryStorageUtils
    @Nonnull
    public Optional<File> getExternalPathForPlaybackDownload() {
        Optional<File> externalStoragePathIfWritable = getExternalStoragePathIfWritable();
        if (!externalStoragePathIfWritable.isPresent()) {
            return Optional.absent();
        }
        return Optional.of(new File(new File(externalStoragePathIfWritable.get(), this.mDeviceId), "playback_downloads"));
    }

    @Override // com.amazon.avod.acos.SecondaryStorageUtils
    public boolean isSDCardSlotPresent() {
        if (Strings.isNullOrEmpty(System.getenv("EXTERNAL_SDCARD_STORAGE"))) {
            Strings.isNullOrEmpty(System.getenv("SECONDARY_STORAGE"));
        }
        return this.mSdCardDetected || (ContextCompat.getExternalFilesDirs(this.mContext, null).length >= 2);
    }

    @Override // com.amazon.avod.acos.SecondaryStorageUtils
    public void onSDCardMounted() {
        this.mSdCardDetected = true;
    }

    @Nonnull
    private Optional<File> getCurrentSDCardFile() {
        File[] externalFilesDirs = ContextCompat.getExternalFilesDirs(this.mContext, null);
        if (externalFilesDirs == null || externalFilesDirs.length == 0) {
            return Optional.absent();
        }
        return getCurrentSDCardFileLollipopPlus(externalFilesDirs);
    }

    private Optional<File> getCurrentSDCardFileLollipopPlus(@Nonnull File[] fileArr) {
        Preconditions.checkNotNull(fileArr, "externalDirs");
        EnvironmentProxyLollipop environmentProxyLollipop = new EnvironmentProxyLollipop();
        for (File file : fileArr) {
            if (file != null) {
                try {
                    if (environmentProxyLollipop.isExternalStorageRemovable(file) && !environmentProxyLollipop.isExternalStorageEmulated(file)) {
                        return Optional.of(file);
                    }
                } catch (IllegalArgumentException e) {
                    DLog.logf("Error verifying whether SD card on is Removable or Emulated on path %s. %s", DLog.maskString(file.getAbsolutePath()), e.getMessage());
                    return Optional.absent();
                }
            }
        }
        return Optional.absent();
    }

    static class EnvironmentProxyLollipop {
        EnvironmentProxyLollipop() {
        }

        boolean isExternalStorageRemovable(@Nonnull File file) {
            Preconditions.checkNotNull(file, "file");
            return Environment.isExternalStorageRemovable(file);
        }

        boolean isExternalStorageEmulated(@Nonnull File file) {
            Preconditions.checkNotNull(file, "file");
            return Environment.isExternalStorageEmulated(file);
        }
    }
}
