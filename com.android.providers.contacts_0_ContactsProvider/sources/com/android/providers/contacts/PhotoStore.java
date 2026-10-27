package com.android.providers.contacts;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class PhotoStore {
    private static final Object MKDIRS_LOCK = new Object();
    private final ContactsDatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mDb;
    private final Map<Long, Entry> mEntries;
    private final File mStorePath;
    private final String TAG = PhotoStore.class.getSimpleName();
    private long mTotalSize = 0;

    public PhotoStore(File file, ContactsDatabaseHelper contactsDatabaseHelper) {
        this.mStorePath = new File(file, "photos");
        synchronized (MKDIRS_LOCK) {
            if (!this.mStorePath.exists() && !this.mStorePath.mkdirs()) {
                throw new RuntimeException("Unable to create photo storage directory " + this.mStorePath.getPath());
            }
        }
        this.mDatabaseHelper = contactsDatabaseHelper;
        this.mEntries = new ArrayMap();
        initialize();
    }

    public void clear() {
        File[] fileArrListFiles = this.mStorePath.listFiles();
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                cleanupFile(file);
            }
        }
        if (this.mDb == null) {
            this.mDb = this.mDatabaseHelper.getWritableDatabase();
        }
        this.mDb.delete("photo_files", null, null);
        this.mEntries.clear();
        this.mTotalSize = 0L;
    }

    public long getTotalSize() {
        return this.mTotalSize;
    }

    public Entry get(long j) {
        return this.mEntries.get(Long.valueOf(j));
    }

    public final void initialize() {
        File[] fileArrListFiles = this.mStorePath.listFiles();
        if (fileArrListFiles == null) {
            return;
        }
        for (File file : fileArrListFiles) {
            try {
                Entry entry = new Entry(file);
                putEntry(entry.id, entry);
            } catch (NumberFormatException unused) {
                cleanupFile(file);
            }
        }
        this.mDb = this.mDatabaseHelper.getWritableDatabase();
    }

    public Set<Long> cleanup(Set<Long> set) {
        ArraySet arraySet = new ArraySet();
        arraySet.addAll(this.mEntries.keySet());
        arraySet.removeAll(set);
        if (!arraySet.isEmpty()) {
            Log.d(this.TAG, "cleanup removing " + arraySet.size() + " entries");
            Iterator it = arraySet.iterator();
            while (it.hasNext()) {
                remove(((Long) it.next()).longValue());
            }
        }
        ArraySet arraySet2 = new ArraySet();
        arraySet2.addAll(set);
        arraySet2.removeAll(this.mEntries.keySet());
        return arraySet2;
    }

    public long insert(PhotoProcessor photoProcessor) {
        return insert(photoProcessor, false);
    }

    public long insert(PhotoProcessor photoProcessor, boolean z) {
        File fileCreateTempFile;
        Bitmap displayPhoto = photoProcessor.getDisplayPhoto();
        int width = displayPhoto.getWidth();
        int height = displayPhoto.getHeight();
        int maxThumbnailPhotoDim = photoProcessor.getMaxThumbnailPhotoDim();
        if (z || width > maxThumbnailPhotoDim || height > maxThumbnailPhotoDim) {
            File file = null;
            try {
                byte[] displayPhotoBytes = photoProcessor.getDisplayPhotoBytes();
                fileCreateTempFile = File.createTempFile("img", null, this.mStorePath);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(fileCreateTempFile);
                    fileOutputStream.write(displayPhotoBytes);
                    fileOutputStream.close();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("height", Integer.valueOf(height));
                    contentValues.put("width", Integer.valueOf(width));
                    contentValues.put("filesize", Integer.valueOf(displayPhotoBytes.length));
                    long jInsert = this.mDb.insert("photo_files", null, contentValues);
                    if (jInsert != 0) {
                        File fileForPhotoFileId = getFileForPhotoFileId(jInsert);
                        if (fileCreateTempFile.renameTo(fileForPhotoFileId)) {
                            Entry entry = new Entry(fileForPhotoFileId);
                            putEntry(entry.id, entry);
                            return jInsert;
                        }
                    }
                } catch (IOException unused) {
                    file = fileCreateTempFile;
                    fileCreateTempFile = file;
                }
            } catch (IOException unused2) {
            }
            if (fileCreateTempFile != null) {
                cleanupFile(fileCreateTempFile);
            }
        }
        return 0L;
    }

    private void cleanupFile(File file) {
        if (file.delete()) {
            return;
        }
        Log.d("Could not clean up file %s", file.getAbsolutePath());
    }

    public void remove(long j) {
        cleanupFile(getFileForPhotoFileId(j));
        removeEntry(j);
    }

    private File getFileForPhotoFileId(long j) {
        return new File(this.mStorePath, String.valueOf(j));
    }

    private void putEntry(long j, Entry entry) {
        if (!this.mEntries.containsKey(Long.valueOf(j))) {
            this.mTotalSize += entry.size;
        } else {
            this.mTotalSize += entry.size - this.mEntries.get(Long.valueOf(j)).size;
        }
        this.mEntries.put(Long.valueOf(j), entry);
    }

    private void removeEntry(long j) {
        Entry entry = this.mEntries.get(Long.valueOf(j));
        if (entry != null) {
            this.mTotalSize -= entry.size;
            this.mEntries.remove(Long.valueOf(j));
        }
        this.mDb.delete("photo_files", "photo_files._id=?", new String[]{String.valueOf(j)});
    }

    public static class Entry {
        public final long id;
        public final String path;
        public final long size;

        public Entry(File file) {
            this.id = Long.parseLong(file.getName());
            this.size = file.length();
            this.path = file.getAbsolutePath();
        }
    }
}
