package com.android.inputmethod.dictionarypack;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.FileNotFoundException;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadManagerWrapper {
    private static final String TAG = "DownloadManagerWrapper";
    private final DownloadManager mDownloadManager;

    public DownloadManagerWrapper(final Context context) {
        this((DownloadManager) context.getSystemService("download"));
    }

    private DownloadManagerWrapper(final DownloadManager downloadManager) {
        this.mDownloadManager = downloadManager;
    }

    public void remove(final long... ids) {
        try {
            DownloadManager downloadManager = this.mDownloadManager;
            if (downloadManager != null) {
                downloadManager.remove(ids);
            }
        } catch (SQLiteException e) {
            Log.e(TAG, "Can't remove files with ID " + ids + " from download manager", e);
        } catch (IllegalArgumentException unused) {
        }
    }

    public ParcelFileDescriptor openDownloadedFile(final long fileId) throws FileNotFoundException {
        try {
            DownloadManager downloadManager = this.mDownloadManager;
            if (downloadManager != null) {
                return downloadManager.openDownloadedFile(fileId);
            }
        } catch (SQLiteException e) {
            Log.e(TAG, "Can't open downloaded file with ID " + fileId, e);
        } catch (IllegalArgumentException unused) {
        }
        throw new FileNotFoundException();
    }

    @Nullable
    public Cursor query(final DownloadManager.Query query) {
        try {
            DownloadManager downloadManager = this.mDownloadManager;
            if (downloadManager != null) {
                return downloadManager.query(query);
            }
            return null;
        } catch (SQLiteException e) {
            Log.e(TAG, "Can't query the download manager", e);
            return null;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public long enqueue(final DownloadManager.Request request) {
        try {
            DownloadManager downloadManager = this.mDownloadManager;
            if (downloadManager != null) {
                return downloadManager.enqueue(request);
            }
            return 0L;
        } catch (SQLiteException e) {
            Log.e(TAG, "Can't enqueue a request with the download manager", e);
            return 0L;
        } catch (IllegalArgumentException unused) {
            return 0L;
        }
    }
}
