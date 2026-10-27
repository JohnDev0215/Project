package com.amazon.music.downloads.worker;

/* JADX INFO: loaded from: classes4.dex */
public class DownloadableItem {
    private long mBytesDownloaded;
    private final String mDestinationURI;
    private final String mDownloadURL;
    private String mEtag;
    private long mFileSizeInBytes;
    private boolean mIsPaused = false;
    private boolean mIsCanceled = false;
    private boolean mIsQueued = false;
    private boolean mIsProcessing = false;

    public DownloadableItem(String str, String str2) {
        this.mDownloadURL = str;
        this.mDestinationURI = str2;
    }

    public String getDownloadURL() {
        return this.mDownloadURL;
    }

    public String getDestinationURI() {
        return this.mDestinationURI;
    }

    synchronized String getEtag() {
        return this.mEtag;
    }

    synchronized void setEtag(String str) {
        this.mEtag = str;
    }

    synchronized boolean isPaused() {
        return this.mIsPaused;
    }

    synchronized void setIsPaused(boolean z) {
        this.mIsPaused = z;
    }

    synchronized boolean isCanceled() {
        return this.mIsCanceled;
    }

    synchronized void setIsCanceled(boolean z) {
        this.mIsCanceled = z;
    }

    synchronized boolean isQueued() {
        return this.mIsQueued;
    }

    synchronized void setIsQueued(boolean z) {
        this.mIsQueued = z;
    }

    synchronized boolean isProcessing() {
        return this.mIsProcessing;
    }

    synchronized void setIsProcessing(boolean z) {
        this.mIsProcessing = z;
    }

    long getFileSizeInBytes() {
        return this.mFileSizeInBytes;
    }

    void setFileSizeInBytes(long j) {
        this.mFileSizeInBytes = j;
    }

    long getBytesDownloaded() {
        return this.mBytesDownloaded;
    }

    void setBytesDownloaded(long j) {
        this.mBytesDownloaded = j;
    }
}
