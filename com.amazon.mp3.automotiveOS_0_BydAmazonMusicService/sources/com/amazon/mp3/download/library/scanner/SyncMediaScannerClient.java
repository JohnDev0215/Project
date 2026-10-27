package com.amazon.mp3.download.library.scanner;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
final class SyncMediaScannerClient implements IMediaScannerClient {
    private TrackScanCompletedListener mCallback;
    private Context mContext;
    private String mFilepath;
    private String mMimeType;
    private final Object mLockObject = new Object();
    private MediaScannerConnection mConnection = null;
    private boolean mCompleted = false;

    @Override // com.amazon.mp3.download.library.scanner.IMediaScannerClient
    public boolean isCompleted() {
        return this.mCompleted;
    }

    public SyncMediaScannerClient(Context context, String filepath, String mimeType, TrackScanCompletedListener callback) {
        this.mContext = context;
        this.mFilepath = filepath;
        this.mMimeType = mimeType;
        this.mCallback = callback;
        Log.verbose("MediaScannerClient", "Creating media scanner client for path %s and type %s", filepath, mimeType);
        if (this.mFilepath == null || this.mMimeType == null) {
            throw new IllegalArgumentException("SyncMediaScannerClient - filepath or mimeType == null.");
        }
    }

    @Override // android.media.MediaScannerConnection.MediaScannerConnectionClient
    public void onMediaScannerConnected() {
        Log.verbose("MediaScannerClient", "Media scanner connected for %s and %s", this.mFilepath, this.mMimeType);
        MediaScannerConnection mediaScannerConnection = this.mConnection;
        if (mediaScannerConnection == null) {
            Log.verbose("MediaScannerClient", "Media scanner reconnected?? bailing...");
        } else {
            mediaScannerConnection.scanFile(this.mFilepath, this.mMimeType);
        }
    }

    @Override // android.media.MediaScannerConnection.OnScanCompletedListener
    public void onScanCompleted(String filepath, Uri uri) {
        Log.verbose("MediaScannerClient", "Media scan completed for %s with uri %s", filepath, uri);
        this.mCompleted = true;
        TrackScanCompletedListener trackScanCompletedListener = this.mCallback;
        if (trackScanCompletedListener != null) {
            trackScanCompletedListener.onScanCompleted(filepath, uri);
        }
        Object obj = this.mLockObject;
        if (obj != null) {
            synchronized (obj) {
                this.mLockObject.notifyAll();
            }
        }
        MediaScannerConnection mediaScannerConnection = this.mConnection;
        if (mediaScannerConnection != null && mediaScannerConnection.isConnected()) {
            this.mConnection.disconnect();
        }
        this.mCallback = null;
        this.mConnection = null;
        this.mFilepath = null;
        this.mMimeType = null;
        new MediaScanBroadcaster(this.mContext).sendMediaScanCompletedBroadcast(filepath);
    }

    @Override // com.amazon.mp3.download.library.scanner.IMediaScannerClient
    public void setConnection(MediaScannerConnection connection) {
        this.mConnection = connection;
    }

    @Override // com.amazon.mp3.download.library.scanner.IMediaScannerClient
    public Object getLockObject() {
        return this.mLockObject;
    }
}
