package com.amazon.avod.drm.playready;

import android.content.Context;
import android.content.res.AssetManager;
import com.amazon.avod.media.framework.error.LicenseError;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.util.DLog;
import com.google.common.io.ByteStreams;
import com.google.common.io.Closeables;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SoftwarePlayReadyInitialization {
    private final String mLicenseStorePath;
    private final String mSystemDirectoryPath;

    private SoftwarePlayReadyInitialization(String str, String str2) {
        this.mSystemDirectoryPath = str;
        this.mLicenseStorePath = str2;
    }

    public static SoftwarePlayReadyInitialization initialize(@Nonnull Context context, @Nonnull FileSystem fileSystem) throws PlayReadyException {
        String absolutePath = fileSystem.getLicenseStoreRootDir().getAbsolutePath();
        String drmSystemPath = getDrmSystemPath(absolutePath);
        String licensePath = getLicensePath(absolutePath);
        DLog.logf("DRM sub-system initializing with system path: %s", drmSystemPath);
        DLog.logf("Initializing license store: %s", licensePath);
        SoftwarePlayReadyInitialization softwarePlayReadyInitialization = new SoftwarePlayReadyInitialization(drmSystemPath, licensePath);
        File file = new File(drmSystemPath, "prinit.dat");
        if (!file.exists()) {
            try {
                File parentFile = file.getParentFile();
                if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
                    throw new IOException(String.format(Locale.US, "Failed to create directory %s at path %s", parentFile.getName(), parentFile.getAbsolutePath()));
                }
                AssetManager assets = context.getAssets();
                String[] list = assets.list("PlayReady");
                if (list != null) {
                    for (String str : list) {
                        copyFile(drmSystemPath, str, assets);
                    }
                }
                File file2 = new File(drmSystemPath, "keyfile.dat");
                if (file2.exists() && !file2.delete()) {
                    throw new IOException(String.format(Locale.US, "Failed to delete key file at %s", file2.getAbsolutePath()));
                }
                File file3 = new File(licensePath);
                if (file3.exists() && !file3.delete()) {
                    throw new IOException(String.format(Locale.US, "Failed to delete license file at %s", file3.getAbsolutePath()));
                }
            } catch (IOException | SecurityException e) {
                PlayReadyException playReadyException = new PlayReadyException(e.getMessage(), LicenseError.SOFTWARE_PLAYREADY_INITIALIZATION);
                DLog.exceptionf(playReadyException, "Failed to provision PlayReady DRM system.", new Object[0]);
                throw playReadyException;
            }
        }
        return softwarePlayReadyInitialization;
    }

    private static void copyFile(String str, String str2, AssetManager assetManager) throws Throwable {
        FileOutputStream fileOutputStream;
        String str3;
        InputStream inputStreamOpen = null;
        try {
            StringBuilder sb = new StringBuilder(str);
            str3 = File.separator;
            sb.append(str3);
            sb.append(str2);
            fileOutputStream = new FileOutputStream(sb.toString());
        } catch (Throwable th) {
            th = th;
            fileOutputStream = null;
        }
        try {
            inputStreamOpen = assetManager.open("PlayReady" + str3 + str2);
            ByteStreams.copy(inputStreamOpen, fileOutputStream);
            DLog.devf("Provisioning the DRM sub-system. Copied file %s", str2);
            Closeables.closeQuietly(inputStreamOpen);
            try {
                fileOutputStream.flush();
            } finally {
            }
        } catch (Throwable th2) {
            th = th2;
            Closeables.closeQuietly(inputStreamOpen);
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.flush();
                } finally {
                }
            }
            throw th;
        }
    }

    public String getSystemDirectoryPath() {
        return this.mSystemDirectoryPath;
    }

    public String getLicenseStorePath() {
        return this.mLicenseStorePath;
    }

    private static String getLicensePath(String str) {
        StringBuilder sb = new StringBuilder(str);
        String str2 = File.separator;
        sb.append(str2);
        sb.append("playready");
        sb.append(str2);
        sb.append("aivdatastore.hds");
        return sb.toString();
    }

    private static String getDrmSystemPath(String str) {
        StringBuilder sb = new StringBuilder(str);
        String str2 = File.separator;
        sb.append(str2);
        sb.append("playready");
        sb.append(str2);
        sb.append("system");
        return sb.toString();
    }
}
