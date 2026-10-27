package org.chromium.android_webview;

import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.os.Trace;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import defpackage.AbstractC0279Pk;
import defpackage.C0683dt;
import defpackage.Is;
import defpackage.Lt;
import defpackage.Ts;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import org.chromium.base.PathUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AwDataDirLock {
    public static FileLock sExclusiveFileLock;
    public static RandomAccessFile sLockFile;

    public static String a(RandomAccessFile randomAccessFile) {
        try {
            int i = randomAccessFile.readInt();
            String str = randomAccessFile.readUTF() + " (pid " + i + ")";
            try {
                Os.kill(i, 0);
                return "Using WebView from more than one process at once with the same data directory is not supported. https://crbug.com/558377 : Lock owner " + str;
            } catch (ErrnoException e) {
                if (e.errno == OsConstants.ESRCH) {
                    return "Using WebView from more than one process at once with the same data directory is not supported. https://crbug.com/558377 : Lock owner " + str + " doesn't exist!";
                }
                if (e.errno == OsConstants.EPERM) {
                    return "Using WebView from more than one process at once with the same data directory is not supported. https://crbug.com/558377 : Lock owner " + str + " pid has been reused!";
                }
                return "Using WebView from more than one process at once with the same data directory is not supported. https://crbug.com/558377 : Lock owner " + str + " status unknown!";
            }
        } catch (IOException unused) {
            return "Using WebView from more than one process at once with the same data directory is not supported. https://crbug.com/558377 : Lock owner unknown";
        }
    }

    public static void b(Context context) {
        C0683dt c0683dtA;
        File file;
        Trace.beginSection("AwDataDirLock.lock");
        try {
            c0683dtA = C0683dt.A();
            try {
                file = new File(PathUtils.getDataDirectory(), "webview_data.lock");
            } finally {
            }
        } catch (Throwable th) {
            try {
                Trace.endSection();
            } catch (Throwable th2) {
                AbstractC0279Pk.a.a(th, th2);
            }
            throw th;
        }
        try {
            sLockFile = new RandomAccessFile(file, "rw");
            boolean z = true;
            for (int i = 1; i <= 5; i++) {
                try {
                    sExclusiveFileLock = sLockFile.getChannel().tryLock();
                } catch (IOException unused) {
                }
                if (sExclusiveFileLock != null) {
                    RandomAccessFile randomAccessFile = sLockFile;
                    try {
                        randomAccessFile.setLength(0L);
                        randomAccessFile.writeInt(Process.myPid());
                        randomAccessFile.writeUTF(Is.b());
                    } catch (IOException e) {
                        Ts.f("AwDataDirLock", "Failed to write info to lock file", e);
                    }
                    Lt.a.d("Android.WebView.Startup.DataDirLockAttempts", i, 1, 6, 7);
                    c0683dtA.close();
                    Trace.endSection();
                    return;
                }
                if (i == 5) {
                    break;
                }
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException unused2) {
                }
                Trace.endSection();
                throw th;
            }
            String strA = a(sLockFile);
            if (Build.VERSION.SDK_INT < 28 || context.getApplicationInfo().targetSdkVersion < 28) {
                z = false;
            }
            if (z) {
                throw new RuntimeException(strA);
            }
            Ts.f("AwDataDirLock", strA, new Object[0]);
            Lt.a.d("Android.WebView.Startup.DataDirLockAttempts", 0, 1, 6, 7);
            c0683dtA.close();
            Trace.endSection();
        } catch (IOException e2) {
            throw new RuntimeException("Failed to create lock file " + file, e2);
        }
    }
}
