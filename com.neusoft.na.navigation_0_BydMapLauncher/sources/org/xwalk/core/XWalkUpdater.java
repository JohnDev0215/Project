package org.xwalk.core;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import java.io.File;
import java.util.List;
import org.xwalk.core.XWalkLibraryLoader;

/* JADX INFO: loaded from: classes4.dex */
public class XWalkUpdater {
    private static final String ANDROID_MARKET_DETAILS = "market://details?id=";
    private static final String GOOGLE_PLAY_PACKAGE = "com.android.vending";
    private static final String TAG = "XWalkLib";
    private XWalkBackgroundUpdateListener mBackgroundUpdateListener;
    private Runnable mCancelCommand;
    private Context mContext;
    private XWalkDialogManager mDialogManager;
    private Runnable mDownloadCommand;
    private XWalkUpdateListener mUpdateListener;

    public interface XWalkBackgroundUpdateListener {
        void onXWalkUpdateCancelled();

        void onXWalkUpdateCompleted();

        void onXWalkUpdateFailed();

        void onXWalkUpdateProgress(int i);

        void onXWalkUpdateStarted();
    }

    public interface XWalkUpdateListener {
        void onXWalkUpdateCancelled();
    }

    public XWalkUpdater(XWalkUpdateListener xWalkUpdateListener, Context context) {
        this.mUpdateListener = xWalkUpdateListener;
        this.mContext = context;
        this.mDialogManager = new XWalkDialogManager(context);
    }

    public XWalkUpdater(XWalkUpdateListener xWalkUpdateListener, Context context, XWalkDialogManager xWalkDialogManager) {
        this.mUpdateListener = xWalkUpdateListener;
        this.mContext = context;
        this.mDialogManager = xWalkDialogManager;
    }

    public XWalkUpdater(XWalkBackgroundUpdateListener xWalkBackgroundUpdateListener, Context context) {
        this.mBackgroundUpdateListener = xWalkBackgroundUpdateListener;
        this.mContext = context;
    }

    public boolean updateXWalkRuntime() {
        if (XWalkLibraryLoader.isInitializing() || XWalkLibraryLoader.isDownloading()) {
            Log.d(TAG, "Other initialization or download is proceeding");
            return false;
        }
        if (XWalkLibraryLoader.isLibraryReady()) {
            Log.d(TAG, "Initialization has been completed. Do not need to update");
            return false;
        }
        int libraryStatus = XWalkLibraryLoader.getLibraryStatus();
        if (libraryStatus == 0) {
            throw new RuntimeException("Must invoke XWalkInitializer.initAsync() first");
        }
        if (this.mUpdateListener != null) {
            this.mDownloadCommand = new Runnable() { // from class: org.xwalk.core.XWalkUpdater.1
                @Override // java.lang.Runnable
                public void run() {
                    XWalkUpdater.this.downloadXWalkApk();
                }
            };
            Runnable runnable = new Runnable() { // from class: org.xwalk.core.XWalkUpdater.2
                @Override // java.lang.Runnable
                public void run() {
                    Log.d(XWalkUpdater.TAG, "XWalkUpdater cancelled");
                    XWalkUpdater.this.mUpdateListener.onXWalkUpdateCancelled();
                }
            };
            this.mCancelCommand = runnable;
            this.mDialogManager.showInitializationError(libraryStatus, runnable, this.mDownloadCommand);
            return true;
        }
        if (this.mBackgroundUpdateListener != null) {
            XWalkLibraryLoader.startHttpDownload(new BackgroundListener(), this.mContext, XWalkEnvironment.getXWalkApkUrl());
            return true;
        }
        throw new IllegalArgumentException("Update listener is null");
    }

    public boolean dismissDialog() {
        XWalkDialogManager xWalkDialogManager = this.mDialogManager;
        if (xWalkDialogManager == null || !xWalkDialogManager.isShowingDialog()) {
            return false;
        }
        this.mDialogManager.dismissDialog();
        return true;
    }

    public void setXWalkApkUrl(String str) {
        XWalkEnvironment.setXWalkApkUrl(str);
    }

    public boolean cancelBackgroundDownload() {
        return XWalkLibraryLoader.cancelHttpDownload();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadXWalkApk() {
        String str;
        String xWalkApkUrl = XWalkEnvironment.getXWalkApkUrl();
        if (!xWalkApkUrl.isEmpty()) {
            XWalkLibraryLoader.startDownloadManager(new ForegroundListener(), this.mContext, xWalkApkUrl);
            return;
        }
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("market://details?id=org.xwalk.core"));
        List<ResolveInfo> listQueryIntentActivities = this.mContext.getPackageManager().queryIntentActivities(intent, 131072);
        StringBuilder sb = new StringBuilder();
        Log.d(TAG, "Available Stores:");
        boolean zEquals = false;
        for (ResolveInfo resolveInfo : listQueryIntentActivities) {
            Log.d(TAG, resolveInfo.activityInfo.packageName);
            zEquals |= resolveInfo.activityInfo.packageName.equals("com.android.vending");
            String storeName = getStoreName(resolveInfo.activityInfo.packageName);
            if (storeName != null) {
                if (sb.length() > 0) {
                    sb.append("/");
                }
                sb.append(storeName);
            }
        }
        if (sb.length() == 0) {
            this.mDialogManager.showUnsupportedStore(this.mCancelCommand);
            return;
        }
        if (zEquals || !XWalkEnvironment.isIaDevice()) {
            str = XWalkEnvironment.is64bitApp() ? XWalkLibraryInterface.XWALK_CORE64_PACKAGE : XWalkLibraryInterface.XWALK_CORE_PACKAGE;
        } else {
            str = XWalkEnvironment.is64bitApp() ? XWalkLibraryInterface.XWALK_CORE64_IA_PACKAGE : XWalkLibraryInterface.XWALK_CORE_IA_PACKAGE;
        }
        Log.d(TAG, "Package name of Crosswalk to download: ".concat(str));
        intent.setData(Uri.parse(ANDROID_MARKET_DETAILS.concat(str)));
        String storeName2 = zEquals ? getStoreName("com.android.vending") : sb.toString();
        Log.d(TAG, "Supported Stores: " + storeName2);
        this.mDialogManager.showSelectStore(new Runnable() { // from class: org.xwalk.core.XWalkUpdater.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    XWalkUpdater.this.mContext.startActivity(intent);
                } catch (ActivityNotFoundException unused) {
                    XWalkUpdater.this.mDialogManager.showUnsupportedStore(XWalkUpdater.this.mCancelCommand);
                }
            }
        }, storeName2);
    }

    private class ForegroundListener implements XWalkLibraryLoader.DownloadListener {
        private ForegroundListener() {
        }

        @Override // org.xwalk.core.XWalkLibraryLoader.DownloadListener
        public void onDownloadStarted() {
            XWalkUpdater.this.mDialogManager.showDownloadProgress(new Runnable() { // from class: org.xwalk.core.XWalkUpdater.ForegroundListener.1
                @Override // java.lang.Runnable
                public void run() {
                    XWalkLibraryLoader.cancelDownloadManager();
                }
            });
        }

        @Override // org.xwalk.core.XWalkLibraryLoader.DownloadListener
        public void onDownloadUpdated(int i) {
            XWalkUpdater.this.mDialogManager.setProgress(i, 100);
        }

        @Override // org.xwalk.core.XWalkLibraryLoader.DownloadListener
        public void onDownloadCancelled() {
            XWalkUpdater.this.mUpdateListener.onXWalkUpdateCancelled();
        }

        @Override // org.xwalk.core.XWalkLibraryLoader.DownloadListener
        public void onDownloadFailed(int i, int i2) {
            XWalkUpdater.this.mDialogManager.dismissDialog();
            XWalkUpdater.this.mDialogManager.showDownloadError(XWalkUpdater.this.mCancelCommand, XWalkUpdater.this.mDownloadCommand);
        }

        @Override // org.xwalk.core.XWalkLibraryLoader.DownloadListener
        public void onDownloadCompleted(Uri uri) {
            XWalkUpdater.this.mDialogManager.dismissDialog();
            Log.d(XWalkUpdater.TAG, "Install the Crosswalk runtime: " + uri.toString());
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setFlags(268435456);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            XWalkUpdater.this.mContext.startActivity(intent);
        }
    }

    private class BackgroundListener implements XWalkLibraryLoader.DownloadListener {
        private BackgroundListener() {
        }

        @Override // org.xwalk.core.XWalkLibraryLoader.DownloadListener
        public void onDownloadStarted() {
            XWalkUpdater.this.mBackgroundUpdateListener.onXWalkUpdateStarted();
        }

        @Override // org.xwalk.core.XWalkLibraryLoader.DownloadListener
        public void onDownloadUpdated(int i) {
            XWalkUpdater.this.mBackgroundUpdateListener.onXWalkUpdateProgress(i);
        }

        @Override // org.xwalk.core.XWalkLibraryLoader.DownloadListener
        public void onDownloadCancelled() {
            XWalkUpdater.this.mBackgroundUpdateListener.onXWalkUpdateCancelled();
        }

        @Override // org.xwalk.core.XWalkLibraryLoader.DownloadListener
        public void onDownloadFailed(int i, int i2) {
            XWalkUpdater.this.mBackgroundUpdateListener.onXWalkUpdateFailed();
        }

        /* JADX WARN: Type inference failed for: r1v3, types: [org.xwalk.core.XWalkUpdater$BackgroundListener$1] */
        @Override // org.xwalk.core.XWalkLibraryLoader.DownloadListener
        public void onDownloadCompleted(Uri uri) {
            final String path = uri.getPath();
            final String extractedCoreDir = XWalkEnvironment.getExtractedCoreDir();
            Log.d(XWalkUpdater.TAG, "Download mode extract dir: " + extractedCoreDir);
            new AsyncTask<Void, Void, Boolean>() { // from class: org.xwalk.core.XWalkUpdater.BackgroundListener.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public Boolean doInBackground(Void... voidArr) {
                    if (XWalkEnvironment.isXWalkVerify() && !XWalkUpdater.this.verifyDownloadedXWalkRuntime(path)) {
                        return false;
                    }
                    if (XWalkDecompressor.isResourceCompressed(path)) {
                        if (!XWalkDecompressor.decompressResource(path, extractedCoreDir)) {
                            return false;
                        }
                    } else if (!XWalkDecompressor.extractResource(path, extractedCoreDir)) {
                        return false;
                    }
                    return true;
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public void onPostExecute(Boolean bool) {
                    new File(path).delete();
                    if (!bool.booleanValue()) {
                        XWalkUpdater.this.mBackgroundUpdateListener.onXWalkUpdateFailed();
                    } else {
                        XWalkUpdater.this.mBackgroundUpdateListener.onXWalkUpdateCompleted();
                    }
                }
            }.execute(new Void[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean verifyDownloadedXWalkRuntime(String str) {
        PackageInfo packageInfo;
        PackageInfo packageArchiveInfo = this.mContext.getPackageManager().getPackageArchiveInfo(str, 64);
        if (packageArchiveInfo == null) {
            Log.e(TAG, "The downloaded XWalkRuntimeLib.apk is invalid!");
            return false;
        }
        try {
            packageInfo = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 64);
        } catch (PackageManager.NameNotFoundException unused) {
        }
        if (packageArchiveInfo.signatures == null || packageInfo.signatures == null) {
            Log.e(TAG, "No signature in package info");
            return false;
        }
        if (packageArchiveInfo.signatures.length != packageInfo.signatures.length) {
            Log.e(TAG, "signatures length not equal");
            return false;
        }
        for (int i = 0; i < packageArchiveInfo.signatures.length; i++) {
            Log.d(TAG, "Checking signature " + i);
            if (!packageInfo.signatures[i].equals(packageArchiveInfo.signatures[i])) {
                Log.e(TAG, "signatures do not match");
                return false;
            }
        }
        Log.d(TAG, "Signature check passed");
        return true;
    }

    private String getStoreName(String str) {
        if (str.equals("com.android.vending")) {
            return this.mContext.getString(R.string.google_play_store);
        }
        return null;
    }
}
