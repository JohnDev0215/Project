package org.chromium.base;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.StrictMode;
import android.os.SystemClock;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.metrics.RecordHistogram;

/* JADX INFO: loaded from: classes3.dex */
public abstract class PathUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int CACHE_DIRECTORY = 3;
    private static final int DATABASE_DIRECTORY = 2;
    private static final int DATA_DIRECTORY = 0;
    private static final int NUM_DIRECTORIES = 4;
    private static final int THUMBNAIL_DIRECTORY = 1;
    private static final String THUMBNAIL_DIRECTORY_NAME = "textures";
    private static Context sDataDirectoryAppContext;
    private static String sDataDirectorySuffix;
    private static AsyncTask<Void, Void, String[]> sDirPathFetchTask;
    private static final AtomicBoolean sInitializationStarted = new AtomicBoolean();

    private PathUtils() {
    }

    private static class Holder {
        private static final String[] DIRECTORY_PATHS = PathUtils.getOrComputeDirectoryPaths();

        private Holder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String[] getOrComputeDirectoryPaths() {
        try {
            if (sDirPathFetchTask.cancel(false)) {
                StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
                StrictMode.allowThreadDiskWrites();
                try {
                    return setPrivateDataDirectorySuffixInternal();
                } finally {
                    StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                }
            }
            return sDirPathFetchTask.get();
        } catch (InterruptedException | ExecutionException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String[] setPrivateDataDirectorySuffixInternal() {
        String[] strArr = new String[4];
        strArr[0] = sDataDirectoryAppContext.getDir(sDataDirectorySuffix, 0).getPath();
        strArr[1] = sDataDirectoryAppContext.getDir(THUMBNAIL_DIRECTORY_NAME, 0).getPath();
        strArr[2] = sDataDirectoryAppContext.getDatabasePath("foo").getParent();
        if (sDataDirectoryAppContext.getCacheDir() != null) {
            strArr[3] = sDataDirectoryAppContext.getCacheDir().getPath();
        }
        return strArr;
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [org.chromium.base.PathUtils$1] */
    public static void setPrivateDataDirectorySuffix(String str, Context context) {
        if (sInitializationStarted.getAndSet(true)) {
            return;
        }
        sDataDirectorySuffix = str;
        sDataDirectoryAppContext = context.getApplicationContext();
        sDirPathFetchTask = new AsyncTask<Void, Void, String[]>() { // from class: org.chromium.base.PathUtils.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public String[] doInBackground(Void... voidArr) {
                return PathUtils.setPrivateDataDirectorySuffixInternal();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private static String getDirectoryPath(int i) {
        return Holder.DIRECTORY_PATHS[i];
    }

    public static String getDataDirectory(Context context) {
        return getDirectoryPath(0);
    }

    public static String getDatabaseDirectory(Context context) {
        return getDirectoryPath(2);
    }

    public static String getCacheDirectory(Context context) {
        return getDirectoryPath(3);
    }

    public static String getThumbnailCacheDirectory(Context context) {
        return getDirectoryPath(1);
    }

    private static String getDownloadsDirectory(Context context) {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            RecordHistogram.recordTimesHistogram("Android.StrictMode.DownloadsDir", SystemClock.elapsedRealtime() - jElapsedRealtime, TimeUnit.MILLISECONDS);
            return path;
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        }
    }

    private static String getNativeLibraryDirectory(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        return ((applicationInfo.flags & 128) != 0 || (applicationInfo.flags & 1) == 0) ? applicationInfo.nativeLibraryDir : "/system/lib/";
    }

    public static String getExternalStorageDirectory() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }
}
