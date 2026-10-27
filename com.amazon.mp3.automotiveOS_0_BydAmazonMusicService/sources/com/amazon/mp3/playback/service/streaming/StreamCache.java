package com.amazon.mp3.playback.service.streaming;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.FileUtil;
import com.amazon.mp3.util.IoUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StorageInfo;
import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public final class StreamCache {
    private static final String LOGTAG = "StreamCache";
    private static long sCurrentCacheSizeOnDisk;
    private static StreamCache sInstance;
    private static long sMaxExtendedCacheSize;
    private static long sMaxNormalCacheSize;
    private final Set<String> mCachePaths;
    private final Context mContext;
    private final SQLiteDatabase mDb;
    private final String mDefaultCachePath;

    private StreamCache(Context context, SQLiteDatabase db) {
        HashSet hashSet = new HashSet();
        this.mCachePaths = hashSet;
        this.mContext = context;
        this.mDb = db;
        StringBuilder sb = new StringBuilder();
        sb.append(context.getCacheDir());
        String str = File.separator;
        sb.append(str);
        sb.append("streamcache");
        sb.append(str);
        String string = sb.toString();
        this.mDefaultCachePath = string;
        hashSet.add(string);
        try {
            String absolutePath = StorageInfo.getInstance().getExternalStorageDirectory().getAbsolutePath();
            hashSet.add(absolutePath + str + AmazonApplication.getExternalStorageAppDirectory() + str + AmazonApplication.getExternalStorageTempDirectory() + str + ".streamcache" + str);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(absolutePath);
            sb2.append(str);
            sb2.append("amazonmp3");
            sb2.append(str);
            sb2.append(".streamcache");
            sb2.append(str);
            hashSet.add(sb2.toString());
        } catch (StorageInfo.DeviceNotAvailableException unused) {
            Log.info(LOGTAG, "External storage cache directory not available");
        }
        clearFilesNotInDb();
        recalculateSpaceForCaching();
    }

    public void recalculateSpaceForCaching() {
        computeMaxCacheSize();
        computeMaxExtendedCacheSize();
    }

    public static synchronized StreamCache getInstance(Context c) {
        if (sInstance == null) {
            sInstance = new StreamCache(c, CacheDatabase.getWritableDatabase(c));
        }
        return sInstance;
    }

    public synchronized void clearAll() {
        Log.verbose(LOGTAG, "Clearing on-disk cache");
        CacheDatabase.reset(this.mContext);
        Iterator<String> it = this.mCachePaths.iterator();
        while (it.hasNext()) {
            IoUtil.deleteFilesInDirectory(it.next());
        }
        computeCacheSizeOnDisk();
    }

    private static synchronized void removeFromCacheSize(long size) {
        sCurrentCacheSizeOnDisk -= size;
    }

    private static int getMaxPreserveFilesCountInExtenedMode() {
        return AmazonApplication.getConfiguration(AmazonApplication.getContext()).getInteger("amp_number_of_tracks_to_prefetch", 2);
    }

    public synchronized void clearTrack(String luid) {
        if (luid == null) {
            return;
        }
        Uri contentUri = MediaProvider.Tracks.getContentUri("cirrus", luid);
        if (contentUri == null) {
            return;
        }
        Cursor cursorQuery = null;
        try {
            cursorQuery = this.mDb.query("CachedItems", new String[]{"_id", "local_path"}, "content_uri=?", new String[]{contentUri.toString()}, null, null, null);
            if (cursorQuery != null && cursorQuery.getCount() > 0) {
                int columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow("local_path");
                while (cursorQuery.moveToNext()) {
                    File file = new File(cursorQuery.getString(columnIndexOrThrow));
                    long length = file.length();
                    if (FileUtil.deleteFile(file)) {
                        removeFromCacheSize(length);
                    }
                    Log.debug(LOGTAG, "Deleted cache file: %s", file.toString());
                }
                DbUtil.closeCursor(cursorQuery);
                DbUtil.beginTransaction(this.mDb);
                try {
                    this.mDb.delete("CachedItems", "content_uri=?", new String[]{contentUri.toString()});
                    this.mDb.setTransactionSuccessful();
                } finally {
                    this.mDb.endTransaction();
                }
            }
        } finally {
            DbUtil.closeCursor(cursorQuery);
        }
    }

    public long getMaxCacheSize() {
        computeMaxExtendedCacheSize();
        return sMaxExtendedCacheSize;
    }

    public long getRemainingCacheSize() {
        computeCacheSizeOnDisk();
        return sMaxExtendedCacheSize - sCurrentCacheSizeOnDisk;
    }

    private void clearFilesNotInDb() {
        Cursor cursorQuery = this.mDb.query("CachedItems", new String[]{"local_path"}, null, null, null, null, null);
        if (cursorQuery == null) {
            Log.warning(LOGTAG, "Couldn't get cursor from select all query");
            return;
        }
        try {
            clearFilesNotInDb(cursorQuery);
        } finally {
            DbUtil.closeCursor(cursorQuery);
        }
    }

    private void clearFilesNotInDb(Cursor c) {
        HashSet<String> hashSet = new HashSet();
        Iterator<String> it = this.mCachePaths.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            File[] fileArrListFiles = new File(it.next()).listFiles();
            if (fileArrListFiles != null) {
                for (File file : fileArrListFiles) {
                    if (file != null) {
                        hashSet.add(file.getAbsolutePath());
                    }
                }
            }
        }
        if (hashSet.size() <= 0) {
            return;
        }
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                hashSet.remove(c.getString(c.getColumnIndex("local_path")));
            } while (c.moveToNext());
            if (hashSet.size() <= 0) {
                return;
            }
        }
        for (String str : hashSet) {
            File file2 = new File(str);
            if (file2.exists() && file2.delete()) {
                Log.debug(LOGTAG, "Deleted file because it was not in cache DB: %s", str);
            } else {
                String str2 = LOGTAG;
                Log.warning(str2, "Could not delete file");
                Log.debug(str2, "File: %s", str);
            }
        }
    }

    private synchronized void computeCacheSizeOnDisk() {
        sCurrentCacheSizeOnDisk = 0L;
        Iterator<String> it = this.mCachePaths.iterator();
        while (true) {
            if (it.hasNext()) {
                File[] fileArrListFiles = new File(it.next()).listFiles();
                if (fileArrListFiles != null) {
                    for (File file : fileArrListFiles) {
                        if (file != null) {
                            sCurrentCacheSizeOnDisk += file.length();
                        }
                    }
                }
            } else {
                Log.verbose(LOGTAG, "Current cache size on disk: %d", Long.valueOf(sCurrentCacheSizeOnDisk));
            }
        }
    }

    private synchronized void computeMaxCacheSize() {
        computeCacheSizeOnDisk();
        float totalSpaceForPath = IoUtil.getTotalSpaceForPath(this.mDefaultCachePath);
        long jMin = (long) Math.min(0.2f * totalSpaceForPath, (IoUtil.getFreeSpaceForPath(this.mDefaultCachePath) + sCurrentCacheSizeOnDisk) - (totalSpaceForPath * 0.1f));
        sMaxNormalCacheSize = jMin;
        if (jMin < 0) {
            sMaxNormalCacheSize = 0L;
        }
        Log.verbose(LOGTAG, "Max cache size: %d", Long.valueOf(sMaxNormalCacheSize));
    }

    private synchronized void computeMaxExtendedCacheSize() {
        computeCacheSizeOnDisk();
        long jMin = (long) Math.min(IoUtil.getTotalSpaceForPath(this.mDefaultCachePath) * 0.2f, IoUtil.getFreeSpaceForPath(this.mDefaultCachePath) + sCurrentCacheSizeOnDisk);
        sMaxExtendedCacheSize = jMin;
        if (jMin < 0) {
            sMaxExtendedCacheSize = 0L;
        }
        Log.verbose(LOGTAG, "Max extended cache size: %d", Long.valueOf(sMaxExtendedCacheSize));
    }
}
