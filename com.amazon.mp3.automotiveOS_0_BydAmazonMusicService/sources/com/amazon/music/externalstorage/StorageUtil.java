package com.amazon.music.externalstorage;

import android.content.Context;
import com.amazon.music.externalstorage.StorageLocationFileManager;
import java.io.File;
import java.io.FileNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class StorageUtil {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) StorageUtil.class);

    public static boolean canDeviceWriteToSdCard(Context context, String str) {
        PublicSupportedSecondaryStorageDirectory publicSupportedSecondaryStorageDirectory = new PublicSupportedSecondaryStorageDirectory(context, str);
        return canWriteToStorage(publicSupportedSecondaryStorageDirectory, StorageLocationFileManager.Visibility.PUBLIC) && canWriteToStorage(publicSupportedSecondaryStorageDirectory, StorageLocationFileManager.Visibility.PRIVATE);
    }

    private static boolean canWriteToStorage(StorageDirectory storageDirectory, StorageLocationFileManager.Visibility visibility) {
        boolean zMkdirs = false;
        if (storageDirectory == null) {
            LOG.warn("canWriteToStorage called with a null StorageDirectory. Visibility: " + visibility);
            return false;
        }
        if (visibility == null) {
            LOG.warn("canWriteToStorage called with a null Visibility.");
            return false;
        }
        String absolutePath = "";
        try {
            File directory = storageDirectory.getDirectory(visibility);
            StringBuilder sb = new StringBuilder();
            String str = File.separator;
            sb.append(str);
            sb.append("testDirectory");
            sb.append(str);
            sb.append("testFile.txt");
            String string = sb.toString();
            absolutePath = directory.getAbsolutePath();
            File parentFile = new File(absolutePath + string).getParentFile();
            if (parentFile != null) {
                if (parentFile.exists()) {
                    deleteFolder(parentFile);
                }
                zMkdirs = parentFile.mkdirs();
            }
            if (zMkdirs && parentFile != null && parentFile.exists()) {
                deleteFolder(parentFile);
            }
        } catch (FileNotFoundException e) {
            LOG.trace("canWriteToStorage error: visibility: " + visibility + " error: " + e.getMessage(), (Throwable) e);
        }
        LOG.debug("canWriteToStorage: " + zMkdirs + " visibility: " + visibility + " AbsolutePath: " + absolutePath);
        return zMkdirs;
    }

    public static void deleteFolder(File file) {
        try {
            boolean zDelete = file.delete();
            LOG.debug("delete: " + file + " : " + zDelete);
        } catch (Exception e) {
            LOG.error("Unable to delete a temp folder: " + file, (Throwable) e);
        }
    }
}
