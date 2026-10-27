package org.chromium.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Trace;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes3.dex */
public class ResourceExtractor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String APP_VERSION_PREF = "org.chromium.base.ResourceExtractor.Version";
    private static final String ICU_DATA_FILENAME = "icudtl.dat";
    private static final String TAG = "cr.base";
    private static final String V8_NATIVES_DATA_FILENAME = "natives_blob.bin";
    private static final String V8_SNAPSHOT_DATA_FILENAME = "snapshot_blob.bin";
    private static ResourceExtractor sInstance;
    private static ResourceInterceptor sInterceptor;
    private static ResourceEntry[] sResourcesToExtract = new ResourceEntry[0];
    private final Context mContext;
    private ExtractTask mExtractTask;

    public interface ResourceInterceptor {
        InputStream openRawResource(String str);

        boolean shouldInterceptLoadRequest(String str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isAppDataFile(String str) {
        return ICU_DATA_FILENAME.equals(str) || V8_NATIVES_DATA_FILENAME.equals(str) || V8_SNAPSHOT_DATA_FILENAME.equals(str);
    }

    public static final class ResourceEntry {
        public final String extractedFileName;
        public final String pathWithinApk;
        public final int resourceId;

        public ResourceEntry(int i, String str, String str2) {
            this.resourceId = i;
            this.pathWithinApk = str;
            this.extractedFileName = str2;
        }
    }

    private class ExtractTask extends AsyncTask<Void, Void, Void> {
        private static final int BUFFER_SIZE = 16384;
        private final List<Runnable> mCompletionCallbacks;

        private ExtractTask() {
            this.mCompletionCallbacks = new ArrayList();
        }

        private void extractResourceHelper(InputStream inputStream, File file, byte[] bArr) throws Throwable {
            FileOutputStream fileOutputStream;
            FileOutputStream fileOutputStream2 = null;
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (Throwable th) {
                th = th;
            }
            try {
                Log.i(ResourceExtractor.TAG, "Extracting resource %s", file);
                while (true) {
                    int i = inputStream.read(bArr, 0, 16384);
                    if (i == -1) {
                        try {
                            break;
                        } finally {
                            if (inputStream != null) {
                                inputStream.close();
                            }
                        }
                    }
                    fileOutputStream.write(bArr, 0, i);
                }
                fileOutputStream.close();
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream2 = fileOutputStream;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } finally {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    }
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        }

        private void doInBackgroundImpl() {
            File outputDir = ResourceExtractor.this.getOutputDir();
            File appDataDir = ResourceExtractor.this.getAppDataDir();
            if (!outputDir.exists() && !outputDir.mkdirs()) {
                Log.e(ResourceExtractor.TAG, "Unable to create pak resources directory!", new Object[0]);
                return;
            }
            beginTraceSection("checkPakTimeStamp");
            long apkVersion = getApkVersion();
            SharedPreferences appSharedPreferences = ContextUtils.getAppSharedPreferences();
            boolean z = apkVersion != appSharedPreferences.getLong(ResourceExtractor.APP_VERSION_PREF, 0L);
            endTraceSection();
            if (z) {
                ResourceExtractor.this.deleteFiles();
                appSharedPreferences.edit().putLong(ResourceExtractor.APP_VERSION_PREF, apkVersion).apply();
            }
            beginTraceSection("WalkAssets");
            byte[] bArr = new byte[16384];
            try {
                for (ResourceEntry resourceEntry : ResourceExtractor.sResourcesToExtract) {
                    File file = new File(ResourceExtractor.isAppDataFile(resourceEntry.extractedFileName) ? appDataDir : outputDir, resourceEntry.extractedFileName);
                    if (file.length() == 0) {
                        beginTraceSection("ExtractResource");
                        try {
                            extractResourceHelper((ResourceExtractor.sInterceptor == null || !ResourceExtractor.sInterceptor.shouldInterceptLoadRequest(resourceEntry.extractedFileName)) ? ResourceExtractor.this.mContext.getResources().openRawResource(resourceEntry.resourceId) : ResourceExtractor.sInterceptor.openRawResource(resourceEntry.extractedFileName), file, bArr);
                            endTraceSection();
                        } finally {
                        }
                    }
                }
            } catch (IOException e) {
                Log.w(ResourceExtractor.TAG, "Exception unpacking required pak resources: %s", e.getMessage());
                ResourceExtractor.this.deleteFiles();
            } finally {
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            beginTraceSection("ResourceExtractor.ExtractTask.doInBackground");
            try {
                doInBackgroundImpl();
                endTraceSection();
                return null;
            } catch (Throwable th) {
                endTraceSection();
                throw th;
            }
        }

        private void onPostExecuteImpl() {
            for (int i = 0; i < this.mCompletionCallbacks.size(); i++) {
                this.mCompletionCallbacks.get(i).run();
            }
            this.mCompletionCallbacks.clear();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            beginTraceSection("ResourceExtractor.ExtractTask.onPostExecute");
            try {
                onPostExecuteImpl();
            } finally {
                endTraceSection();
            }
        }

        private long getApkVersion() {
            try {
                return ResourceExtractor.this.mContext.getPackageManager().getPackageInfo(ResourceExtractor.this.mContext.getPackageName(), 0).lastUpdateTime;
            } catch (PackageManager.NameNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        private void beginTraceSection(String str) {
            Trace.beginSection(str);
        }

        private void endTraceSection() {
            Trace.endSection();
        }
    }

    public static ResourceExtractor get(Context context) {
        if (sInstance == null) {
            sInstance = new ResourceExtractor(context);
        }
        return sInstance;
    }

    public static void setResourceInterceptor(ResourceInterceptor resourceInterceptor) {
        sInterceptor = resourceInterceptor;
    }

    public static void setResourcesToExtract(ResourceEntry[] resourceEntryArr) {
        sResourcesToExtract = resourceEntryArr;
    }

    private ResourceExtractor(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public void waitForCompletion() {
        if (shouldSkipPakExtraction()) {
            return;
        }
        try {
            this.mExtractTask.get();
            sInterceptor = null;
            sInstance = null;
        } catch (InterruptedException unused) {
            deleteFiles();
        } catch (CancellationException unused2) {
            deleteFiles();
        } catch (ExecutionException unused3) {
            deleteFiles();
        }
    }

    public void addCompletionCallback(Runnable runnable) {
        ThreadUtils.assertOnUiThread();
        Handler handler = new Handler(Looper.getMainLooper());
        if (shouldSkipPakExtraction()) {
            handler.post(runnable);
        } else if (this.mExtractTask.getStatus() != AsyncTask.Status.FINISHED) {
            this.mExtractTask.mCompletionCallbacks.add(runnable);
        } else {
            handler.post(runnable);
        }
    }

    public void startExtractingResources() {
        if (this.mExtractTask == null && !shouldSkipPakExtraction()) {
            ExtractTask extractTask = new ExtractTask();
            this.mExtractTask = extractTask;
            extractTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File getAppDataDir() {
        return new File(PathUtils.getDataDirectory(this.mContext));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File getOutputDir() {
        return new File(getAppDataDir(), "paks");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteFiles() {
        File[] fileArrListFiles;
        File file = new File(getAppDataDir(), ICU_DATA_FILENAME);
        if (file.exists() && !file.delete()) {
            Log.e(TAG, "Unable to remove the icudata %s", file.getName());
        }
        File file2 = new File(getAppDataDir(), V8_NATIVES_DATA_FILENAME);
        if (file2.exists() && !file2.delete()) {
            Log.e(TAG, "Unable to remove the v8 data %s", file2.getName());
        }
        File file3 = new File(getAppDataDir(), V8_SNAPSHOT_DATA_FILENAME);
        if (file3.exists() && !file3.delete()) {
            Log.e(TAG, "Unable to remove the v8 data %s", file3.getName());
        }
        File outputDir = getOutputDir();
        if (!outputDir.exists() || (fileArrListFiles = outputDir.listFiles()) == null) {
            return;
        }
        for (File file4 : fileArrListFiles) {
            if (!file4.delete()) {
                Log.e(TAG, "Unable to remove existing resource %s", file4.getName());
            }
        }
    }

    private static boolean shouldSkipPakExtraction() {
        return sResourcesToExtract.length == 0;
    }
}
