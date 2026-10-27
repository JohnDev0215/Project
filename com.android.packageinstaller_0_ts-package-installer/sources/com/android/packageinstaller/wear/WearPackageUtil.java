package com.android.packageinstaller.wear;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.tukaani.xz.LZMAInputStream;
import org.tukaani.xz.XZInputStream;

/* JADX INFO: loaded from: classes2.dex */
public class WearPackageUtil {
    private static final String COMPRESSION_LZMA = "lzma";
    private static final String COMPRESSION_XZ = "xz";
    private static final String TAG = "WearablePkgInstaller";

    public static File getTemporaryFile(Context context, String packageName) {
        try {
            File newFileDir = new File(context.getFilesDir(), "tmp");
            newFileDir.mkdirs();
            Os.chmod(newFileDir.getAbsolutePath(), 505);
            File newFile = new File(newFileDir, packageName + ".apk");
            return newFile;
        } catch (ErrnoException e) {
            Log.e(TAG, "Failed to open.", e);
            return null;
        }
    }

    public static File getIconFile(Context context, String packageName) {
        try {
            File newFileDir = new File(context.getFilesDir(), "images/icons");
            newFileDir.mkdirs();
            Os.chmod(newFileDir.getAbsolutePath(), 505);
            return new File(newFileDir, packageName + ".icon");
        } catch (ErrnoException e) {
            Log.e(TAG, "Failed to open.", e);
            return null;
        }
    }

    public static File getFileFromFd(Context context, ParcelFileDescriptor fd, String packageName, String compressionAlg) {
        File newFile = getTemporaryFile(context, packageName);
        if (fd == null || fd.getFileDescriptor() == null) {
            return null;
        }
        InputStream fr = new ParcelFileDescriptor.AutoCloseInputStream(fd);
        try {
            if (TextUtils.equals(compressionAlg, COMPRESSION_XZ)) {
                fr = new XZInputStream(fr);
            } else if (TextUtils.equals(compressionAlg, COMPRESSION_LZMA)) {
                fr = new LZMAInputStream(fr);
            }
            byte[] data = new byte[1024];
            try {
                try {
                    FileOutputStream fo = new FileOutputStream(newFile);
                    while (true) {
                        int nRead = fr.read(data, 0, data.length);
                        if (nRead == -1) {
                            break;
                        }
                        fo.write(data, 0, nRead);
                    }
                    fo.flush();
                    fo.close();
                    Os.chmod(newFile.getAbsolutePath(), 420);
                    return newFile;
                } catch (ErrnoException e) {
                    Log.e(TAG, "Could not set permissions on file ", e);
                    try {
                        fr.close();
                    } catch (IOException e2) {
                        Log.e(TAG, "Failed to close the file from FD ", e2);
                    }
                    return null;
                } catch (IOException e3) {
                    Log.e(TAG, "Reading from Asset FD or writing to temp file failed ", e3);
                    try {
                        fr.close();
                    } catch (IOException e4) {
                        Log.e(TAG, "Failed to close the file from FD ", e4);
                    }
                    return null;
                }
            } finally {
                try {
                    fr.close();
                } catch (IOException e5) {
                    Log.e(TAG, "Failed to close the file from FD ", e5);
                }
            }
        } catch (IOException e6) {
            Log.e(TAG, "Compression was set to " + compressionAlg + ", but could not decode ", e6);
            return null;
        }
    }

    public static String getSanitizedPackageName(Uri packageUri) {
        String packageName = packageUri.getEncodedSchemeSpecificPart();
        if (packageName != null) {
            return packageName.replaceAll("^/+", "");
        }
        return packageName;
    }
}
