package com.android.providers.downloads;

import android.app.job.JobParameters;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.INetworkPolicyListener;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkPolicyManager;
import android.net.TrafficStats;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.SystemClock;
import android.os.storage.StorageManager;
import android.provider.Downloads;
import android.system.ErrnoException;
import android.system.Os;
import android.util.Log;
import android.util.MathUtils;
import android.util.Pair;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import libcore.io.IoUtils;

/* JADX INFO: loaded from: classes.dex */
public class DownloadThread extends Thread {
    private final Context mContext;
    private final long mId;
    private boolean mIgnoreBlocked;
    private final DownloadInfo mInfo;
    private final DownloadInfoDelta mInfoDelta;
    private final DownloadJobService mJobService;
    private Network mNetwork;
    private final NetworkPolicyManager mNetworkPolicy;
    private final DownloadNotifier mNotifier;
    private final JobParameters mParams;
    private volatile boolean mPolicyDirty;
    private volatile boolean mShutdownRequested;
    private long mSpeed;
    private long mSpeedSampleBytes;
    private long mSpeedSampleStart;
    private final StorageManager mStorage;
    private final SystemFacade mSystemFacade;
    private boolean mMadeProgress = false;
    private long mLastUpdateBytes = 0;
    private long mLastUpdateTime = 0;
    private int mNetworkType = -1;
    private INetworkPolicyListener mPolicyListener = new NetworkPolicyManager.Listener() { // from class: com.android.providers.downloads.DownloadThread.1
        public void onUidRulesChanged(int i, int i2) {
            if (i == DownloadThread.this.mInfo.mUid) {
                DownloadThread.this.mPolicyDirty = true;
            }
        }

        public void onMeteredIfacesChanged(String[] strArr) {
            DownloadThread.this.mPolicyDirty = true;
        }

        public void onRestrictBackgroundChanged(boolean z) {
            DownloadThread.this.mPolicyDirty = true;
        }

        public void onUidPoliciesChanged(int i, int i2) {
            if (i == DownloadThread.this.mInfo.mUid) {
                DownloadThread.this.mPolicyDirty = true;
            }
        }
    };

    public static boolean isStatusRetryable(int i) {
        return i == 492 || i == 495 || i == 500 || i == 503;
    }

    private class DownloadInfoDelta {
        public long mCurrentBytes;
        public String mETag;
        public String mErrorMsg;
        public String mFileName;
        public String mMimeType;
        public int mNumFailed;
        public int mRetryAfter;
        public int mStatus;
        public long mTotalBytes;
        public String mUri;

        public DownloadInfoDelta(DownloadInfo downloadInfo) {
            this.mUri = downloadInfo.mUri;
            this.mFileName = downloadInfo.mFileName;
            this.mMimeType = downloadInfo.mMimeType;
            this.mStatus = downloadInfo.mStatus;
            this.mNumFailed = downloadInfo.mNumFailed;
            this.mRetryAfter = downloadInfo.mRetryAfter;
            this.mTotalBytes = downloadInfo.mTotalBytes;
            this.mCurrentBytes = downloadInfo.mCurrentBytes;
            this.mETag = downloadInfo.mETag;
        }

        private ContentValues buildContentValues() {
            ContentValues contentValues = new ContentValues();
            contentValues.put("uri", this.mUri);
            contentValues.put("_data", this.mFileName);
            contentValues.put("mimetype", this.mMimeType);
            contentValues.put("status", Integer.valueOf(this.mStatus));
            contentValues.put("numfailed", Integer.valueOf(this.mNumFailed));
            contentValues.put("method", Integer.valueOf(this.mRetryAfter));
            contentValues.put("total_bytes", Long.valueOf(this.mTotalBytes));
            contentValues.put("current_bytes", Long.valueOf(this.mCurrentBytes));
            contentValues.put("etag", this.mETag);
            contentValues.put("lastmod", Long.valueOf(DownloadThread.this.mSystemFacade.currentTimeMillis()));
            contentValues.put("errorMsg", this.mErrorMsg);
            return contentValues;
        }

        public void writeToDatabase() {
            DownloadThread.this.mContext.getContentResolver().update(DownloadThread.this.mInfo.getAllDownloadsUri(), buildContentValues(), null, null);
        }

        public void writeToDatabaseOrThrow() throws StopRequestException {
            if (DownloadThread.this.mContext.getContentResolver().update(DownloadThread.this.mInfo.getAllDownloadsUri(), buildContentValues(), "status != '490' AND deleted == '0' AND (control IS NULL OR control != '1')", null) == 0) {
                if (DownloadThread.this.mInfo.queryDownloadControl() == 1) {
                    throw new StopRequestException(193, "Download paused!");
                }
                throw new StopRequestException(490, "Download deleted or missing!");
            }
        }
    }

    public DownloadThread(DownloadJobService downloadJobService, JobParameters jobParameters, DownloadInfo downloadInfo) {
        this.mContext = downloadJobService;
        this.mSystemFacade = Helpers.getSystemFacade(downloadJobService);
        this.mNotifier = Helpers.getDownloadNotifier(this.mContext);
        this.mNetworkPolicy = (NetworkPolicyManager) this.mContext.getSystemService(NetworkPolicyManager.class);
        this.mStorage = (StorageManager) this.mContext.getSystemService(StorageManager.class);
        this.mJobService = downloadJobService;
        this.mParams = jobParameters;
        this.mId = downloadInfo.mId;
        this.mInfo = downloadInfo;
        this.mInfoDelta = new DownloadInfoDelta(downloadInfo);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        StringBuilder sb;
        Network network;
        Process.setThreadPriority(10);
        if (this.mInfo.queryDownloadStatus() == 200) {
            logDebug("Already finished; skipping");
            return;
        }
        boolean z = true;
        try {
            try {
                this.mNetworkPolicy.registerListener(this.mPolicyListener);
                logDebug("Starting");
                this.mInfoDelta.mStatus = 192;
                this.mInfoDelta.writeToDatabase();
                this.mIgnoreBlocked = this.mInfo.isVisible();
                network = this.mSystemFacade.getNetwork(this.mParams);
                this.mNetwork = network;
            } catch (Throwable th) {
                logDebug("Finished with status " + Downloads.Impl.statusToString(this.mInfoDelta.mStatus));
                this.mNotifier.notifyDownloadSpeed(this.mId, 0L);
                finalizeDestination();
                this.mInfoDelta.writeToDatabase();
                TrafficStats.clearThreadStatsTag();
                TrafficStats.clearThreadStatsUid();
                this.mNetworkPolicy.unregisterListener(this.mPolicyListener);
                throw th;
            }
        } catch (StopRequestException e) {
            this.mInfoDelta.mStatus = e.getFinalStatus();
            this.mInfoDelta.mErrorMsg = e.getMessage();
            logWarning("Stop requested with status " + Downloads.Impl.statusToString(this.mInfoDelta.mStatus) + ": " + this.mInfoDelta.mErrorMsg);
            if (this.mInfoDelta.mStatus == 194) {
                throw new IllegalStateException("Execution should always throw final error codes");
            }
            if (isStatusRetryable(this.mInfoDelta.mStatus)) {
                if (this.mMadeProgress) {
                    this.mInfoDelta.mNumFailed = 1;
                } else {
                    this.mInfoDelta.mNumFailed++;
                }
                if (this.mInfoDelta.mNumFailed < 5) {
                    NetworkInfo networkInfo = this.mSystemFacade.getNetworkInfo(this.mNetwork, this.mInfo.mUid, this.mIgnoreBlocked);
                    if (networkInfo != null && networkInfo.getType() == this.mNetworkType && networkInfo.isConnected()) {
                        this.mInfoDelta.mStatus = 194;
                    } else {
                        this.mInfoDelta.mStatus = 195;
                    }
                    if ((this.mInfoDelta.mETag == null && this.mMadeProgress) || DownloadDrmHelper.isDrmConvertNeeded(this.mInfoDelta.mMimeType)) {
                        this.mInfoDelta.mStatus = 489;
                    }
                }
            }
            if (this.mInfoDelta.mStatus == 195 && !this.mInfo.isMeteredAllowed(this.mInfoDelta.mTotalBytes)) {
                this.mInfoDelta.mStatus = 196;
            }
            sb = new StringBuilder();
        } catch (Throwable th2) {
            this.mInfoDelta.mStatus = 491;
            this.mInfoDelta.mErrorMsg = th2.toString();
            logError("Failed: " + this.mInfoDelta.mErrorMsg, th2);
            sb = new StringBuilder();
        }
        if (network == null) {
            throw new StopRequestException(195, "No network associated with requesting UID");
        }
        NetworkInfo networkInfo2 = this.mSystemFacade.getNetworkInfo(network, this.mInfo.mUid, this.mIgnoreBlocked);
        if (networkInfo2 != null) {
            this.mNetworkType = networkInfo2.getType();
        }
        TrafficStats.setThreadStatsTag(-255);
        TrafficStats.setThreadStatsUid(this.mInfo.mUid);
        executeDownload();
        this.mInfoDelta.mStatus = 200;
        TrafficStats.incrementOperationCount(1);
        if (this.mInfoDelta.mTotalBytes == -1) {
            this.mInfoDelta.mTotalBytes = this.mInfoDelta.mCurrentBytes;
        }
        sb = new StringBuilder();
        sb.append("Finished with status ");
        sb.append(Downloads.Impl.statusToString(this.mInfoDelta.mStatus));
        logDebug(sb.toString());
        this.mNotifier.notifyDownloadSpeed(this.mId, 0L);
        finalizeDestination();
        this.mInfoDelta.writeToDatabase();
        TrafficStats.clearThreadStatsTag();
        TrafficStats.clearThreadStatsUid();
        this.mNetworkPolicy.unregisterListener(this.mPolicyListener);
        int i = this.mInfoDelta.mStatus;
        if (i != 194 && i != 195 && i != 196) {
            z = false;
        }
        this.mJobService.jobFinishedInternal(this.mParams, z);
    }

    public void requestShutdown() {
        this.mShutdownRequested = true;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.calcSwitchOut(SwitchRegionMaker.java:217)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:68)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeEndlessLoop(LoopRegionMaker.java:282)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:65)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    private void executeDownload() throws com.android.providers.downloads.StopRequestException {
        /*
            Method dump skipped, instruction units count: 400
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.downloads.DownloadThread.executeDownload():void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0119 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0121 A[Catch: all -> 0x011d, IOException -> 0x0129, TRY_LEAVE, TryCatch #15 {IOException -> 0x0129, all -> 0x011d, blocks: (B:88:0x0119, B:92:0x0121), top: B:112:0x0119 }] */
    /* JADX WARN: Type inference failed for: r10v0, types: [com.android.providers.downloads.DownloadThread] */
    /* JADX WARN: Type inference failed for: r10v22 */
    /* JADX WARN: Type inference failed for: r2v13, types: [android.os.storage.StorageManager] */
    /* JADX WARN: Type inference failed for: r2v15, types: [android.os.storage.StorageManager] */
    /* JADX WARN: Type inference failed for: r3v12, types: [java.io.FileDescriptor] */
    /* JADX WARN: Type inference failed for: r3v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.io.FileDescriptor] */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v17, types: [java.io.OutputStream, java.lang.AutoCloseable] */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v22 */
    /* JADX WARN: Type inference failed for: r4v23 */
    /* JADX WARN: Type inference failed for: r4v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.io.OutputStream, java.lang.AutoCloseable] */
    /* JADX WARN: Type inference failed for: r4v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void transferData(java.net.HttpURLConnection r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 311
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.downloads.DownloadThread.transferData(java.net.HttpURLConnection):void");
    }

    private void transferData(InputStream inputStream, OutputStream outputStream, FileDescriptor fileDescriptor) throws StopRequestException {
        byte[] bArr = new byte[8192];
        while (true) {
            if (this.mPolicyDirty) {
                checkConnectivity();
            }
            if (this.mShutdownRequested) {
                throw new StopRequestException(495, "Local halt requested; job probably timed out");
            }
            try {
                int i = inputStream.read(bArr);
                if (i != -1) {
                    try {
                        outputStream.write(bArr, 0, i);
                        this.mMadeProgress = true;
                        this.mInfoDelta.mCurrentBytes += (long) i;
                        updateProgress(fileDescriptor);
                    } catch (IOException e) {
                        throw new StopRequestException(492, e);
                    }
                } else {
                    DownloadInfoDelta downloadInfoDelta = this.mInfoDelta;
                    long j = downloadInfoDelta.mTotalBytes;
                    if (j == -1 || downloadInfoDelta.mCurrentBytes == j) {
                        return;
                    }
                    throw new StopRequestException(495, "Content length mismatch; found " + this.mInfoDelta.mCurrentBytes + " instead of " + this.mInfoDelta.mTotalBytes);
                }
            } catch (IOException e2) {
                throw new StopRequestException(495, "Failed reading response: " + e2, e2);
            }
        }
    }

    private void finalizeDestination() {
        String str;
        if (Downloads.Impl.isStatusError(this.mInfoDelta.mStatus)) {
            try {
                ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = this.mContext.getContentResolver().openFileDescriptor(this.mInfo.getAllDownloadsUri(), "rw");
                try {
                    Os.ftruncate(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor(), 0L);
                } catch (ErrnoException unused) {
                } catch (Throwable th) {
                    IoUtils.closeQuietly(parcelFileDescriptorOpenFileDescriptor);
                    throw th;
                }
                IoUtils.closeQuietly(parcelFileDescriptorOpenFileDescriptor);
            } catch (FileNotFoundException unused2) {
            }
            if (this.mInfoDelta.mFileName != null) {
                new File(this.mInfoDelta.mFileName).delete();
                this.mInfoDelta.mFileName = null;
                return;
            }
            return;
        }
        if (!Downloads.Impl.isStatusSuccess(this.mInfoDelta.mStatus) || (str = this.mInfoDelta.mFileName) == null) {
            return;
        }
        if (Helpers.isFileInExternalAndroidDirs(str)) {
            this.mStorage.fixupAppDir(new File(this.mInfoDelta.mFileName).getParentFile());
        }
        if (this.mInfo.mDestination != 4) {
            try {
                File file = new File(this.mInfoDelta.mFileName);
                File runningDestinationDirectory = Helpers.getRunningDestinationDirectory(this.mContext, this.mInfo.mDestination);
                File successDestinationDirectory = Helpers.getSuccessDestinationDirectory(this.mContext, this.mInfo.mDestination);
                if (runningDestinationDirectory.equals(successDestinationDirectory) || !file.getParentFile().equals(runningDestinationDirectory)) {
                    return;
                }
                File file2 = new File(successDestinationDirectory, file.getName());
                if (file.renameTo(file2)) {
                    this.mInfoDelta.mFileName = file2.getAbsolutePath();
                }
            } catch (IOException unused3) {
            }
        }
    }

    private void checkConnectivity() throws StopRequestException {
        this.mPolicyDirty = false;
        NetworkInfo networkInfo = this.mSystemFacade.getNetworkInfo(this.mNetwork, this.mInfo.mUid, this.mIgnoreBlocked);
        NetworkCapabilities networkCapabilities = this.mSystemFacade.getNetworkCapabilities(this.mNetwork);
        if (networkInfo == null || !networkInfo.isConnected()) {
            throw new StopRequestException(195, "Network is disconnected");
        }
        if (!networkCapabilities.hasCapability(18) && !this.mInfo.isRoamingAllowed()) {
            throw new StopRequestException(195, "Network is roaming");
        }
        if (!networkCapabilities.hasCapability(11) && !this.mInfo.isMeteredAllowed(this.mInfoDelta.mTotalBytes)) {
            throw new StopRequestException(195, "Network is metered");
        }
    }

    private void updateProgress(FileDescriptor fileDescriptor) throws IOException, StopRequestException {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.mInfoDelta.mCurrentBytes;
        long j2 = jElapsedRealtime - this.mSpeedSampleStart;
        if (j2 > 500) {
            long j3 = ((j - this.mSpeedSampleBytes) * 1000) / j2;
            long j4 = this.mSpeed;
            if (j4 == 0) {
                this.mSpeed = j3;
            } else {
                this.mSpeed = ((j4 * 3) + j3) / 4;
            }
            if (this.mSpeedSampleStart != 0) {
                this.mNotifier.notifyDownloadSpeed(this.mId, this.mSpeed);
            }
            this.mSpeedSampleStart = jElapsedRealtime;
            this.mSpeedSampleBytes = j;
        }
        long j5 = j - this.mLastUpdateBytes;
        long j6 = jElapsedRealtime - this.mLastUpdateTime;
        if (j5 <= 65536 || j6 <= 2000) {
            return;
        }
        fileDescriptor.sync();
        this.mInfoDelta.writeToDatabaseOrThrow();
        this.mLastUpdateBytes = j;
        this.mLastUpdateTime = jElapsedRealtime;
    }

    private void parseOkHeaders(HttpURLConnection httpURLConnection) throws StopRequestException {
        if (this.mInfoDelta.mFileName == null) {
            try {
                this.mInfoDelta.mFileName = Helpers.generateSaveFile(this.mContext, this.mInfoDelta.mUri, this.mInfo.mHint, httpURLConnection.getHeaderField("Content-Disposition"), httpURLConnection.getHeaderField("Content-Location"), this.mInfoDelta.mMimeType, this.mInfo.mDestination);
            } catch (IOException e) {
                throw new StopRequestException(492, "Failed to generate filename: " + e);
            }
        }
        DownloadInfoDelta downloadInfoDelta = this.mInfoDelta;
        if (downloadInfoDelta.mMimeType == null) {
            downloadInfoDelta.mMimeType = Intent.normalizeMimeType(httpURLConnection.getContentType());
        }
        if (httpURLConnection.getHeaderField("Transfer-Encoding") == null) {
            this.mInfoDelta.mTotalBytes = getHeaderFieldLong(httpURLConnection, "Content-Length", -1L);
        } else {
            this.mInfoDelta.mTotalBytes = -1L;
        }
        this.mInfoDelta.mETag = httpURLConnection.getHeaderField("ETag");
        this.mInfoDelta.writeToDatabaseOrThrow();
        checkConnectivity();
    }

    private void parseUnavailableHeaders(HttpURLConnection httpURLConnection) {
        this.mInfoDelta.mRetryAfter = (int) (MathUtils.constrain(httpURLConnection.getHeaderFieldInt("Retry-After", -1), 30L, 86400L) * 1000);
    }

    private void addRequestHeaders(HttpURLConnection httpURLConnection, boolean z) {
        for (Pair<String, String> pair : this.mInfo.getHeaders()) {
            httpURLConnection.addRequestProperty((String) pair.first, (String) pair.second);
        }
        if (httpURLConnection.getRequestProperty("User-Agent") == null) {
            httpURLConnection.addRequestProperty("User-Agent", this.mInfo.getUserAgent());
        }
        httpURLConnection.setRequestProperty("Accept-Encoding", "identity");
        httpURLConnection.setRequestProperty("Connection", "close");
        if (z) {
            String str = this.mInfoDelta.mETag;
            if (str != null) {
                httpURLConnection.addRequestProperty("If-Match", str);
            }
            httpURLConnection.addRequestProperty("Range", "bytes=" + this.mInfoDelta.mCurrentBytes + "-");
        }
    }

    private void logDebug(String str) {
        Log.d("DownloadManager", "[" + this.mId + "] " + str);
    }

    private void logWarning(String str) {
        Log.w("DownloadManager", "[" + this.mId + "] " + str);
    }

    private void logError(String str, Throwable th) {
        Log.e("DownloadManager", "[" + this.mId + "] " + str, th);
    }

    private static long getHeaderFieldLong(URLConnection uRLConnection, String str, long j) {
        try {
            return Long.parseLong(uRLConnection.getHeaderField(str));
        } catch (NumberFormatException unused) {
            return j;
        }
    }
}
