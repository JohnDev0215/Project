package com.android.packageinstaller.wear;

import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageInstaller;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.android.packageinstaller.wear.PackageInstallerImpl;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes2.dex */
public class InstallTask {
    private static final int DEFAULT_BUFFER_SIZE = 8192;
    private static final String TAG = "InstallTask";
    private PackageInstallerImpl.InstallListener mCallback;
    private IntentSender mCommitCallback;
    private final Context mContext;
    private String mPackageName;
    private ParcelFileDescriptor mParcelFileDescriptor;
    private PackageInstaller.Session mSession;
    private Exception mException = null;
    private int mErrorCode = 0;
    private String mErrorDesc = null;

    public InstallTask(Context context, String packageName, ParcelFileDescriptor parcelFileDescriptor, PackageInstallerImpl.InstallListener callback, PackageInstaller.Session session, IntentSender commitCallback) {
        this.mContext = context;
        this.mPackageName = packageName;
        this.mParcelFileDescriptor = parcelFileDescriptor;
        this.mCallback = callback;
        this.mSession = session;
        this.mCommitCallback = commitCallback;
    }

    public boolean isError() {
        return (this.mErrorCode == 0 && TextUtils.isEmpty(this.mErrorDesc)) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002f A[PHI: r3
  0x002f: PHI (r3v6 'e' java.lang.Exception) = (r3v1 'e' java.lang.Exception), (r3v10 'e' java.lang.Exception) binds: [B:22:0x004d, B:11:0x002d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute() throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.packageinstaller.wear.InstallTask.execute():void");
    }

    private boolean writeToOutputStreamFromAsset(OutputStream outputStream) {
        if (outputStream == null) {
            this.mErrorCode = InstallerConstants.ERROR_INSTALL_COPY_STREAM_EXCEPTION;
            this.mErrorDesc = "Got a null OutputStream.";
            return false;
        }
        ParcelFileDescriptor parcelFileDescriptor = this.mParcelFileDescriptor;
        if (parcelFileDescriptor == null || parcelFileDescriptor.getFileDescriptor() == null) {
            this.mErrorCode = InstallerConstants.ERROR_COULD_NOT_GET_FD;
            this.mErrorDesc = "Could not get FD";
            return false;
        }
        InputStream inputStream = null;
        try {
            try {
                byte[] inputBuf = new byte[8192];
                inputStream = new ParcelFileDescriptor.AutoCloseInputStream(this.mParcelFileDescriptor);
                while (true) {
                    int bytesRead = inputStream.read(inputBuf);
                    if (bytesRead <= -1) {
                        outputStream.flush();
                        safeClose(inputStream);
                        return true;
                    }
                    if (bytesRead > 0) {
                        outputStream.write(inputBuf, 0, bytesRead);
                    }
                }
            } catch (IOException e) {
                this.mErrorCode = InstallerConstants.ERROR_INSTALL_APK_COPY_FAILURE;
                this.mErrorDesc = "Reading from Asset FD or writing to temp file failed: " + e;
                safeClose(inputStream);
                return false;
            }
        } catch (Throwable th) {
            safeClose(inputStream);
            throw th;
        }
    }

    public static void safeClose(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
            }
        }
    }
}
