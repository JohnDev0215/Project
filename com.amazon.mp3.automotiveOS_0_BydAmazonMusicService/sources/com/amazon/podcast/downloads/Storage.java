package com.amazon.podcast.downloads;

import android.content.Context;
import android.os.Environment;
import com.amazon.music.externalstorage.StorageLocationFileManager;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class Storage {
    private static Logger logger = LoggerFactory.getLogger("Storage");
    private final Context context;

    public Storage(final Context context) {
        this.context = context;
    }

    public File getRootDestination() {
        StorageLocationFileManager storageLocationFileManagerCreate = StorageLocationFileManager.create(this.context, Environment.DIRECTORY_PODCASTS);
        File preferredStorageDirectory = storageLocationFileManagerCreate.getPreferredStorageDirectory(StorageLocationFileManager.Visibility.PRIVATE);
        logger.debug("preferred storage directory {}", preferredStorageDirectory.getAbsoluteFile());
        logger.debug("is directory writable {}", Boolean.valueOf(storageLocationFileManagerCreate.isStorageLocationWritable(preferredStorageDirectory)));
        return preferredStorageDirectory;
    }

    public void deleteDirectory() {
        deleteFilesInDirectory(getRootDestination());
    }

    private void deleteFilesInDirectory(final File directory) {
        File[] fileArrListFiles;
        if (directory.exists() && directory.canWrite() && (fileArrListFiles = directory.listFiles()) != null) {
            for (File file : fileArrListFiles) {
                try {
                    if (file.isDirectory()) {
                        deleteFilesInDirectory(directory);
                    }
                    if (!file.delete()) {
                        logger.warn("Storage", "Couldn't delete file: %s", file.toString());
                    }
                } catch (Exception unused) {
                    logger.warn("Storage", "Couldn't delete file: %s", file.toString());
                }
            }
        }
    }
}
