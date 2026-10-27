package org.xwalk.core;

import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import org.chromium.content.common.ContentSwitches;

/* JADX INFO: loaded from: classes4.dex */
class XWalkLibraryLoader {
    private static final String DEFAULT_DOWNLOAD_FILE_NAME = "xwalk_download.tmp";
    private static final String DOWNLOAD_WITHOUT_NOTIFICATION = "android.permission.DOWNLOAD_WITHOUT_NOTIFICATION";
    private static final String TAG = "XWalkLib";
    private static AsyncTask<Void, Integer, Integer> sActiveTask;

    public interface ActivateListener {
        void onActivateCompleted();

        void onActivateFailed();

        void onActivateStarted();
    }

    public interface DecompressListener {
        void onDecompressCancelled();

        void onDecompressCompleted();

        void onDecompressStarted();
    }

    public interface DownloadListener {
        void onDownloadCancelled();

        void onDownloadCompleted(Uri uri);

        void onDownloadFailed(int i, int i2);

        void onDownloadStarted();

        void onDownloadUpdated(int i);
    }

    XWalkLibraryLoader() {
    }

    public static boolean isInitializing() {
        AsyncTask<Void, Integer, Integer> asyncTask = sActiveTask;
        return asyncTask != null && ((asyncTask instanceof DecompressTask) || (asyncTask instanceof ActivateTask));
    }

    public static boolean isDownloading() {
        AsyncTask<Void, Integer, Integer> asyncTask = sActiveTask;
        return asyncTask != null && ((asyncTask instanceof DownloadManagerTask) || (asyncTask instanceof HttpDownloadTask));
    }

    public static boolean isSharedLibrary() {
        return XWalkCoreWrapper.getInstance().isSharedMode();
    }

    public static boolean isLibraryReady() {
        return XWalkCoreWrapper.getInstance() != null;
    }

    public static int getLibraryStatus() {
        return XWalkCoreWrapper.getCoreStatus();
    }

    public static void prepareToInit(Context context) {
        XWalkEnvironment.init(context);
        XWalkCoreWrapper.handlePreInit(context.getClass().getName());
    }

    public static void finishInit(Context context) {
        XWalkCoreWrapper.handlePostInit(context.getClass().getName());
    }

    public static void startDecompress(DecompressListener decompressListener) {
        new DecompressTask(decompressListener).execute(new Void[0]);
    }

    public static boolean cancelDecompress() {
        AsyncTask<Void, Integer, Integer> asyncTask = sActiveTask;
        return asyncTask != null && (asyncTask instanceof DecompressTask) && asyncTask.cancel(true);
    }

    public static void startActivate(ActivateListener activateListener) {
        new ActivateTask(activateListener).execute(new Void[0]);
    }

    public static void startDownloadManager(DownloadListener downloadListener, Context context, String str) {
        new DownloadManagerTask(downloadListener, context, str).execute(new Void[0]);
    }

    public static boolean cancelDownloadManager() {
        AsyncTask<Void, Integer, Integer> asyncTask = sActiveTask;
        return asyncTask != null && (asyncTask instanceof DownloadManagerTask) && asyncTask.cancel(true);
    }

    public static void startHttpDownload(DownloadListener downloadListener, Context context, String str) {
        new HttpDownloadTask(downloadListener, context, str).execute(new Void[0]);
    }

    public static boolean cancelHttpDownload() {
        AsyncTask<Void, Integer, Integer> asyncTask = sActiveTask;
        return asyncTask != null && (asyncTask instanceof HttpDownloadTask) && asyncTask.cancel(true);
    }

    private static class DecompressTask extends AsyncTask<Void, Integer, Integer> {
        boolean mIsCompressed;
        boolean mIsDecompressed;
        DecompressListener mListener;

        DecompressTask(DecompressListener decompressListener) {
            this.mListener = decompressListener;
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            Log.d(XWalkLibraryLoader.TAG, "DecompressTask started");
            AsyncTask unused = XWalkLibraryLoader.sActiveTask = this;
            boolean zIsLibraryCompressed = XWalkDecompressor.isLibraryCompressed();
            this.mIsCompressed = zIsLibraryCompressed;
            if (zIsLibraryCompressed) {
                boolean z = false;
                int i = XWalkEnvironment.getSharedPreferences().getInt("version", 0);
                if (i > 0 && i == 8) {
                    z = true;
                }
                this.mIsDecompressed = z;
            }
            if (!this.mIsCompressed || this.mIsDecompressed) {
                return;
            }
            this.mListener.onDecompressStarted();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Integer doInBackground(Void... voidArr) {
            if (this.mIsCompressed && !this.mIsDecompressed) {
                if (!XWalkDecompressor.decompressLibrary()) {
                    return 1;
                }
                XWalkEnvironment.getSharedPreferences().edit().putInt("version", 8).apply();
            }
            return 0;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onCancelled(Integer num) {
            Log.d(XWalkLibraryLoader.TAG, "DecompressTask cancelled");
            AsyncTask unused = XWalkLibraryLoader.sActiveTask = null;
            this.mListener.onDecompressCancelled();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Integer num) {
            Log.d(XWalkLibraryLoader.TAG, "DecompressTask finished, " + num);
            if (num.intValue() == 0) {
                AsyncTask unused = XWalkLibraryLoader.sActiveTask = null;
                this.mListener.onDecompressCompleted();
                return;
            }
            throw new RuntimeException("Decompression Failed");
        }
    }

    private static class ActivateTask extends AsyncTask<Void, Integer, Integer> {
        ActivateListener mListener;

        ActivateTask(ActivateListener activateListener) {
            this.mListener = activateListener;
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            Log.d(XWalkLibraryLoader.TAG, "ActivateTask started");
            AsyncTask unused = XWalkLibraryLoader.sActiveTask = this;
            this.mListener.onActivateStarted();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Integer doInBackground(Void... voidArr) {
            if (XWalkCoreWrapper.getInstance() != null) {
                return -1;
            }
            return Integer.valueOf(XWalkCoreWrapper.attachXWalkCore());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Integer num) {
            if (num.intValue() == 1) {
                XWalkCoreWrapper.dockXWalkCore();
            }
            Log.d(XWalkLibraryLoader.TAG, "ActivateTask finished, " + num);
            AsyncTask unused = XWalkLibraryLoader.sActiveTask = null;
            if (num.intValue() > 1) {
                this.mListener.onActivateFailed();
            } else {
                this.mListener.onActivateCompleted();
            }
        }
    }

    private static class DownloadManagerTask extends AsyncTask<Void, Integer, Integer> {
        private static final int MAX_PAUSED_COUNT = 6000;
        private static final int QUERY_INTERVAL_MS = 100;
        private Context mContext;
        private long mDownloadId;
        private DownloadManager mDownloadManager;
        private String mDownloadUrl;
        private DownloadListener mListener;

        DownloadManagerTask(DownloadListener downloadListener, Context context, String str) {
            this.mListener = downloadListener;
            this.mContext = context;
            this.mDownloadUrl = str;
            this.mDownloadManager = (DownloadManager) context.getSystemService(ContentSwitches.SWITCH_DOWNLOAD_PROCESS);
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            Log.d(XWalkLibraryLoader.TAG, "DownloadManagerTask started, " + this.mDownloadUrl);
            AsyncTask unused = XWalkLibraryLoader.sActiveTask = this;
            try {
                String name = new File(new URL(this.mDownloadUrl).getPath()).getName();
                if (name.isEmpty()) {
                    name = XWalkLibraryLoader.DEFAULT_DOWNLOAD_FILE_NAME;
                }
                File file = new File(this.mContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), name);
                if (file.isFile()) {
                    file.delete();
                }
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(this.mDownloadUrl));
                request.setDestinationInExternalFilesDir(this.mContext, Environment.DIRECTORY_DOWNLOADS, name);
                if (isSilentDownload()) {
                    request.setNotificationVisibility(2);
                }
                this.mDownloadId = this.mDownloadManager.enqueue(request);
                this.mListener.onDownloadStarted();
            } catch (NullPointerException | MalformedURLException unused2) {
                Log.e(XWalkLibraryLoader.TAG, "Invalid download URL " + this.mDownloadUrl);
                this.mDownloadUrl = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Integer doInBackground(Void... voidArr) {
            if (this.mDownloadUrl == null) {
                return 16;
            }
            DownloadManager.Query filterById = new DownloadManager.Query().setFilterById(this.mDownloadId);
            int i = 0;
            while (!isCancelled()) {
                try {
                    Thread.sleep(100L);
                    Cursor cursorQuery = this.mDownloadManager.query(filterById);
                    if (cursorQuery != null && cursorQuery.moveToFirst()) {
                        int columnIndex = cursorQuery.getColumnIndex("total_size");
                        int columnIndex2 = cursorQuery.getColumnIndex("bytes_so_far");
                        int i2 = cursorQuery.getInt(columnIndex);
                        int i3 = cursorQuery.getInt(columnIndex2);
                        if (i2 > 0) {
                            publishProgress(Integer.valueOf(i3), Integer.valueOf(i2));
                        }
                        int i4 = cursorQuery.getInt(cursorQuery.getColumnIndex("status"));
                        if (i4 == 16 || i4 == 8) {
                            return Integer.valueOf(i4);
                        }
                        if (i4 == 4 && (i = i + 1) == 6000) {
                            return Integer.valueOf(i4);
                        }
                    }
                } catch (InterruptedException unused) {
                }
            }
            return 2;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(Integer... numArr) {
            Log.d(XWalkLibraryLoader.TAG, "DownloadManagerTask updated: " + numArr[0] + "/" + numArr[1]);
            this.mListener.onDownloadUpdated(numArr[1].intValue() > 0 ? (int) ((((double) numArr[0].intValue()) * 100.0d) / ((double) numArr[1].intValue())) : 0);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onCancelled(Integer num) {
            this.mDownloadManager.remove(this.mDownloadId);
            Log.d(XWalkLibraryLoader.TAG, "DownloadManagerTask cancelled");
            AsyncTask unused = XWalkLibraryLoader.sActiveTask = null;
            this.mListener.onDownloadCancelled();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Integer num) {
            Cursor cursorQuery;
            Cursor cursorQuery2;
            Log.d(XWalkLibraryLoader.TAG, "DownloadManagerTask finished, " + num);
            AsyncTask unused = XWalkLibraryLoader.sActiveTask = null;
            if (num.intValue() == 8) {
                Uri uriForDownloadedFile = this.mDownloadManager.getUriForDownloadedFile(this.mDownloadId);
                Log.d(XWalkLibraryLoader.TAG, "Uri for downloaded file:" + uriForDownloadedFile.toString());
                if (uriForDownloadedFile.getScheme().equals("content") && (cursorQuery2 = this.mDownloadManager.query(new DownloadManager.Query().setFilterById(this.mDownloadId))) != null && cursorQuery2.moveToFirst()) {
                    uriForDownloadedFile = Uri.parse("file://" + cursorQuery2.getString(cursorQuery2.getColumnIndex("local_filename")));
                }
                this.mListener.onDownloadCompleted(uriForDownloadedFile);
                return;
            }
            this.mListener.onDownloadFailed(num.intValue(), (num.intValue() == 16 && (cursorQuery = this.mDownloadManager.query(new DownloadManager.Query().setFilterById(this.mDownloadId))) != null && cursorQuery.moveToFirst()) ? cursorQuery.getInt(cursorQuery.getColumnIndex("reason")) : 1000);
        }

        private boolean isSilentDownload() {
            try {
                return Arrays.asList(this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 4096).requestedPermissions).contains(XWalkLibraryLoader.DOWNLOAD_WITHOUT_NOTIFICATION);
            } catch (PackageManager.NameNotFoundException | NullPointerException unused) {
                return false;
            }
        }
    }

    private static class HttpDownloadTask extends AsyncTask<Void, Integer, Integer> {
        private static final int DOWNLOAD_FAILED = -1;
        private static final int DOWNLOAD_SUCCESS = 0;
        private static final int UPDATE_INTERVAL_MS = 500;
        private static final String XWALK_DOWNLOAD_DIR = "xwalk_download";
        private Context mContext;
        private String mDownloadUrl;
        private File mDownloadedFile;
        private DownloadListener mListener;
        private long mProgressUpdateTime;

        HttpDownloadTask(DownloadListener downloadListener, Context context, String str) {
            this.mListener = downloadListener;
            this.mContext = context;
            this.mDownloadUrl = str;
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            Log.d(XWalkLibraryLoader.TAG, "HttpDownloadTask started, " + this.mDownloadUrl);
            AsyncTask unused = XWalkLibraryLoader.sActiveTask = this;
            try {
                String name = new File(new URL(this.mDownloadUrl).getPath()).getName();
                if (name.isEmpty()) {
                    name = XWalkLibraryLoader.DEFAULT_DOWNLOAD_FILE_NAME;
                }
                this.mDownloadedFile = new File(this.mContext.getDir(XWALK_DOWNLOAD_DIR, 0), name);
                this.mListener.onDownloadStarted();
            } catch (NullPointerException | MalformedURLException unused2) {
                Log.e(XWalkLibraryLoader.TAG, "Invalid download URL " + this.mDownloadUrl);
                this.mDownloadUrl = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x00b8, code lost:
        
            r7.flush();
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x00bb, code lost:
        
            r7.close();
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x00be, code lost:
        
            if (r6 == null) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x00c0, code lost:
        
            r6.close();
         */
        /* JADX WARN: Removed duplicated region for block: B:57:0x00e8  */
        /* JADX WARN: Removed duplicated region for block: B:65:0x00fa  */
        /* JADX WARN: Removed duplicated region for block: B:92:? A[SYNTHETIC] */
        @Override // android.os.AsyncTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Integer doInBackground(java.lang.Void... r19) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 254
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.xwalk.core.XWalkLibraryLoader.HttpDownloadTask.doInBackground(java.lang.Void[]):java.lang.Integer");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(Integer... numArr) {
            Log.d(XWalkLibraryLoader.TAG, "HttpDownloadTask updated: " + numArr[0] + "/" + numArr[1]);
            this.mListener.onDownloadUpdated(numArr[1].intValue() > 0 ? (int) ((((double) numArr[0].intValue()) * 100.0d) / ((double) numArr[1].intValue())) : 0);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onCancelled(Integer num) {
            Log.d(XWalkLibraryLoader.TAG, "HttpDownloadTask cancelled");
            AsyncTask unused = XWalkLibraryLoader.sActiveTask = null;
            this.mListener.onDownloadCancelled();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Integer num) {
            Log.d(XWalkLibraryLoader.TAG, "HttpDownloadTask finished, " + num);
            AsyncTask unused = XWalkLibraryLoader.sActiveTask = null;
            if (num.intValue() == 0) {
                this.mListener.onDownloadCompleted(Uri.fromFile(this.mDownloadedFile));
            } else {
                this.mListener.onDownloadFailed(-1, 0);
            }
        }
    }
}
