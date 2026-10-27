package com.android.inputmethod.dictionarypack;

import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ProgressBar;

/* JADX INFO: loaded from: classes2.dex */
public class DictionaryDownloadProgressBar extends ProgressBar {
    private static final int NOT_A_DOWNLOADMANAGER_PENDING_ID = 0;
    private static final String TAG = "DictionaryDownloadProgressBar";
    private String mClientId;
    private boolean mIsCurrentlyAttachedToWindow;
    private Thread mReporterThread;
    private String mWordlistId;

    public DictionaryDownloadProgressBar(final Context context) {
        super(context);
        this.mIsCurrentlyAttachedToWindow = false;
        this.mReporterThread = null;
    }

    public DictionaryDownloadProgressBar(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        this.mIsCurrentlyAttachedToWindow = false;
        this.mReporterThread = null;
    }

    public void setIds(final String clientId, final String wordlistId) {
        this.mClientId = clientId;
        this.mWordlistId = wordlistId;
    }

    private static int getDownloadManagerPendingIdFromWordlistId(final Context context, final String clientId, final String wordlistId) {
        ContentValues contentValuesOfLatestAvailableWordlistById = MetadataDbHelper.getContentValuesOfLatestAvailableWordlistById(MetadataDbHelper.getDb(context, clientId), wordlistId);
        if (contentValuesOfLatestAvailableWordlistById == null) {
            Log.e(TAG, "Unexpected word list ID: " + wordlistId);
            return 0;
        }
        return contentValuesOfLatestAvailableWordlistById.getAsInteger(MetadataDbHelper.PENDINGID_COLUMN).intValue();
    }

    private void updateReporterThreadRunningStatusAccordingToVisibility() {
        Thread thread = this.mReporterThread;
        if (thread != null) {
            thread.interrupt();
        }
        if (this.mIsCurrentlyAttachedToWindow && getVisibility() == 0) {
            int downloadManagerPendingIdFromWordlistId = getDownloadManagerPendingIdFromWordlistId(getContext(), this.mClientId, this.mWordlistId);
            if (downloadManagerPendingIdFromWordlistId == 0) {
                this.mReporterThread = null;
                return;
            }
            UpdaterThread updaterThread = new UpdaterThread(getContext(), downloadManagerPendingIdFromWordlistId);
            updaterThread.start();
            this.mReporterThread = updaterThread;
            return;
        }
        this.mReporterThread = null;
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected void onAttachedToWindow() {
        this.mIsCurrentlyAttachedToWindow = true;
        updateReporterThreadRunningStatusAccordingToVisibility();
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mIsCurrentlyAttachedToWindow = false;
        updateReporterThreadRunningStatusAccordingToVisibility();
    }

    private class UpdaterThread extends Thread {
        private static final int REPORT_PERIOD = 150;
        final DownloadManagerWrapper mDownloadManagerWrapper;
        final int mId;

        public UpdaterThread(final Context context, final int id) {
            this.mDownloadManagerWrapper = new DownloadManagerWrapper(context);
            this.mId = id;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Cursor cursorQuery;
            try {
                UpdateHelper updateHelper = new UpdateHelper();
                DownloadManager.Query filterById = new DownloadManager.Query().setFilterById(this.mId);
                DictionaryDownloadProgressBar.this.setIndeterminate(true);
                while (!isInterrupted() && (cursorQuery = this.mDownloadManagerWrapper.query(filterById)) != null) {
                    try {
                        if (cursorQuery.moveToNext()) {
                            updateHelper.setProgressFromAnotherThread(cursorQuery.getInt(cursorQuery.getColumnIndex("bytes_so_far")));
                            cursorQuery.close();
                            Thread.sleep(150L);
                        } else {
                            updateHelper.setProgressFromAnotherThread(DictionaryDownloadProgressBar.this.getMax());
                            return;
                        }
                    } finally {
                        cursorQuery.close();
                    }
                }
            } catch (InterruptedException unused) {
            }
        }

        class UpdateHelper implements Runnable {
            private int mProgress;

            UpdateHelper() {
            }

            @Override // java.lang.Runnable
            public void run() {
                DictionaryDownloadProgressBar.this.setIndeterminate(false);
                DictionaryDownloadProgressBar.this.setProgress(this.mProgress);
            }

            public void setProgressFromAnotherThread(final int progress) {
                if (this.mProgress != progress) {
                    this.mProgress = progress;
                    Handler handler = DictionaryDownloadProgressBar.this.getHandler();
                    if (handler == null) {
                        return;
                    }
                    handler.post(this);
                }
            }
        }
    }
}
