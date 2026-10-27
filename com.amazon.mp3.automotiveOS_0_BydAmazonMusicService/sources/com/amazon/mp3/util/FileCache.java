package com.amazon.mp3.util;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.util.StorageInfo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class FileCache {
    private static final String TAG = "FileCache";
    private String mCacheBase;
    private String mCacheTag;
    private int mMaxCount;
    private long mMaxSizeInBytes;

    public interface CacheWriter {
        boolean writeCacheFile(OutputStream out);
    }

    public FileCache(String tag, int maxCount, long maxSizeInBytes) {
        this.mCacheTag = tag;
        this.mMaxCount = maxCount == Integer.MIN_VALUE ? Integer.MAX_VALUE : maxCount;
        this.mMaxSizeInBytes = maxSizeInBytes == -2147483648L ? Long.MAX_VALUE : maxSizeInBytes;
        this.mCacheBase = String.format(Locale.US, "/%s/%s/.%s", AmazonApplication.getExternalStorageAppDirectory(), AmazonApplication.getExternalStorageTempDirectory(), this.mCacheTag);
    }

    public boolean has(String key) {
        String filePathForKey = getFilePathForKey(key);
        if (filePathForKey != null) {
            return new File(filePathForKey).exists();
        }
        return false;
    }

    public File get(String key) {
        String filePathForKey = getFilePathForKey(key);
        if (filePathForKey == null) {
            return null;
        }
        File file = new File(filePathForKey);
        if (file.exists()) {
            return file;
        }
        return null;
    }

    public boolean put(String key, CacheWriter writer) {
        String strIntern = key.intern();
        synchronized (strIntern) {
            try {
                try {
                    String filePathForKey = getFilePathForKey(strIntern);
                    if (filePathForKey != null && validateCacheDir()) {
                        FileOutputStream fileOutputStream = new FileOutputStream(filePathForKey);
                        boolean zWriteCacheFile = writer.writeCacheFile(fileOutputStream);
                        fileOutputStream.close();
                        cleanup(true);
                        return zWriteCacheFile;
                    }
                } catch (IOException unused) {
                    Log.error(TAG, "Error writing out file %s for key %s", strIntern, getFilePathForKey(strIntern));
                }
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void remove(String key) {
        String strIntern = key.intern();
        synchronized (strIntern) {
            String filePathForKey = getFilePathForKey(strIntern);
            if (filePathForKey != null) {
                File file = new File(filePathForKey);
                if (file.exists() && !file.delete()) {
                    Log.warning(TAG, "Unable to delete cache file");
                }
            }
        }
    }

    public int count() {
        return getCacheFiles().length;
    }

    public long size() {
        long length = 0;
        for (File file : getCacheFiles()) {
            length += file.length();
        }
        return length;
    }

    private String getFilePathForKey(String key) {
        try {
            return getCacheDir() + "/" + key;
        } catch (StorageInfo.DeviceNotAvailableException unused) {
            return null;
        }
    }

    public void cleanup(boolean bBackground) {
        if (bBackground) {
            new Thread(new Runnable() { // from class: com.amazon.mp3.util.FileCache.1
                @Override // java.lang.Runnable
                public void run() {
                    FileCache.this.cleanupOnCurrentThread();
                }
            }, TAG + " cleanup").start();
            return;
        }
        cleanupOnCurrentThread();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void cleanupOnCurrentThread() {
        if (this.mMaxCount < count() || this.mMaxSizeInBytes < size()) {
            File[] cacheFiles = getCacheFiles();
            Arrays.sort(cacheFiles, new Comparator<File>() { // from class: com.amazon.mp3.util.FileCache.2
                @Override // java.util.Comparator
                public int compare(File f1, File f2) {
                    return Long.valueOf(f1.lastModified()).compareTo(Long.valueOf(f2.lastModified()));
                }
            });
            for (File file : cacheFiles) {
                if (file.delete() && this.mMaxCount >= count() && this.mMaxSizeInBytes >= size()) {
                    return;
                }
            }
        }
    }

    private File[] getCacheFiles() {
        try {
            File[] fileArrListFiles = new File(getCacheDir()).listFiles();
            return fileArrListFiles == null ? new File[0] : fileArrListFiles;
        } catch (StorageInfo.DeviceNotAvailableException unused) {
            return new File[0];
        }
    }

    private boolean validateCacheDir() {
        try {
            File file = new File(getCacheDir());
            if (file.exists()) {
                return true;
            }
            return file.mkdirs();
        } catch (StorageInfo.DeviceNotAvailableException unused) {
            return false;
        }
    }

    private String getCacheDir() throws StorageInfo.DeviceNotAvailableException {
        return StorageInfo.getInstance().getExternalStorageDirectory().getAbsolutePath() + this.mCacheBase;
    }
}
