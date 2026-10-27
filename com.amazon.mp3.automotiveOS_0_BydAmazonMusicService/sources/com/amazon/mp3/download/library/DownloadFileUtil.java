package com.amazon.mp3.download.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import com.amazon.mp3.library.item.DeluxeContentUtil;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.FileUtil;
import com.amazon.mp3.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public class DownloadFileUtil extends FileUtil {
    private static final String LOGTAG = "DownloadFileUtil";

    public static FileOutputStream openOutputStream(String filename, int contentLength) {
        if (!hasEnoughFreeSpace(filename, contentLength)) {
            return null;
        }
        FileUtil.createDirectory(filename);
        try {
            return new FileOutputStream(filename, false);
        } catch (FileNotFoundException unused) {
            Log.debug(LOGTAG, "could not open filename: %s", filename);
            return null;
        }
    }

    public static boolean hasEnoughFreeSpace(String target, long contentLength) {
        if (target == null || target.length() == 0) {
            return false;
        }
        File file = new File(target);
        while (true) {
            if (file.exists() && !file.isFile()) {
                break;
            }
            file = new File(file.getParent());
        }
        return !file.equals(Environment.getRootDirectory()) && contentLength * 2 <= FileUtil.getFreeSpace(file.getAbsolutePath());
    }

    public static void updateTracksDownloadStateViaMatchHashes(Context context, HashSet<String> matchHashes, int newState) {
        updateTracksDownloadStateViaMatchHashes(context, matchHashes, newState, -1);
    }

    public static void updateTracksDownloadStateViaMatchHashes(Context context, HashSet<String> matchHashes, int newState, int excludeState) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("download_state", Integer.valueOf(newState));
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(context);
        DbUtil.beginTransaction(writableDatabase);
        try {
            Iterator<String> it = matchHashes.iterator();
            while (it.hasNext()) {
                writableDatabase.update("Track", contentValues, "match_hash=? AND source=0 AND download_state != ?", new String[]{it.next(), "" + excludeState});
            }
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
            context.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
            context.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI_LOCAL, null);
        }
    }

    public static boolean isContentDownloadable(String extension) {
        return !DeluxeContentUtil.isDeluxeContent(extension);
    }
}
