package com.amazon.mp3.util;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.StatFs;
import com.amazon.music.destination.parser.ParserUtil;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes4.dex */
public class IoUtil {
    private static final String LOGTAG = "IoUtil";

    public static boolean close(Closeable c) {
        if (c == null) {
            return false;
        }
        try {
            c.close();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public static boolean close(SQLiteDatabase db) {
        if (db == null) {
            return false;
        }
        try {
            db.close();
            return true;
        } catch (SQLiteException unused) {
            Log.error(LOGTAG, "failed to delete database");
            return false;
        }
    }

    public static String streamToString(InputStream stream) {
        try {
            int iAvailable = stream.available();
            if (iAvailable <= 32) {
                iAvailable = 32;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(iAvailable);
            byte[] bArr = new byte[8192];
            while (true) {
                int i = stream.read(bArr, 0, 8192);
                if (i != -1) {
                    byteArrayOutputStream.write(bArr, 0, i);
                } else {
                    try {
                        return byteArrayOutputStream.toString(ParserUtil.UTF_8);
                    } catch (UnsupportedEncodingException unused) {
                        return byteArrayOutputStream.toString();
                    }
                }
            }
        } catch (IOException e) {
            Log.warning(LOGTAG, "Error reading input stream", e);
            return "";
        }
    }

    public static void deleteFilesInDirectory(String path, boolean recurse) {
        File[] fileArrListFiles;
        if (path == null) {
            return;
        }
        File file = new File(path);
        if (file.exists() && file.canWrite() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                try {
                    if (file2.isDirectory() && recurse) {
                        deleteFilesInDirectory(file2.getAbsolutePath(), true);
                    }
                    if (!file2.delete()) {
                        Log.warning(LOGTAG, "Couldn't delete file: %s", file2.toString());
                    }
                } catch (SecurityException unused) {
                    Log.warning(LOGTAG, "Couldn't delete file: %s", file2.toString());
                }
            }
        }
    }

    public static void deleteFilesInDirectory(String path) {
        deleteFilesInDirectory(path, false);
    }

    public static long getFreeSpaceForPath(String path) {
        if (path == null) {
            return 0L;
        }
        try {
            StatFs statFs = new StatFs(path);
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (RuntimeException unused) {
            return 0L;
        }
    }

    public static long getTotalSpaceForPath(String path) {
        if (path == null) {
            return 0L;
        }
        try {
            StatFs statFs = new StatFs(path);
            return ((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount());
        } catch (RuntimeException unused) {
            return 0L;
        }
    }
}
