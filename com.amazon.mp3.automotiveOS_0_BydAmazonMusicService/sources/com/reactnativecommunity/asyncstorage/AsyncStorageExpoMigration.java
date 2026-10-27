package com.reactnativecommunity.asyncstorage;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.annotation.RequiresApi;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes7.dex */
public class AsyncStorageExpoMigration {
    public static void migrate(Context context) throws Throwable {
        if (isAsyncStorageDatabaseCreated(context)) {
            return;
        }
        ArrayList<File> expoDatabases = getExpoDatabases(context);
        File lastModifiedFile = getLastModifiedFile(expoDatabases);
        if (lastModifiedFile == null) {
            Log.v("AsyncStorageExpoMigration", "No scoped database found");
            return;
        }
        try {
            ReactDatabaseSupplier.getInstance(context).get();
            copyFile(new FileInputStream(lastModifiedFile), new FileOutputStream(context.getDatabasePath("RKStorage")));
            Log.v("AsyncStorageExpoMigration", "Migrated most recently modified database " + lastModifiedFile.getName() + " to RKStorage");
            try {
                for (File file : expoDatabases) {
                    if (file.delete()) {
                        Log.v("AsyncStorageExpoMigration", "Deleted scoped database " + file.getName());
                    } else {
                        Log.v("AsyncStorageExpoMigration", "Failed to delete scoped database " + file.getName());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.v("AsyncStorageExpoMigration", "Completed the scoped AsyncStorage migration");
        } catch (Exception e2) {
            Log.v("AsyncStorageExpoMigration", "Failed to migrate scoped database " + lastModifiedFile.getName());
            e2.printStackTrace();
        }
    }

    private static boolean isAsyncStorageDatabaseCreated(Context context) {
        return context.getDatabasePath("RKStorage").exists();
    }

    private static ArrayList<File> getExpoDatabases(Context context) {
        ArrayList<File> arrayList = new ArrayList<>();
        try {
            File[] fileArrListFiles = context.getDatabasePath("noop").getParentFile().listFiles();
            if (fileArrListFiles != null) {
                for (File file : fileArrListFiles) {
                    if (file.getName().startsWith("RKStorage-scoped-experience-") && !file.getName().endsWith("-journal")) {
                        arrayList.add(file);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    private static File getLastModifiedFile(ArrayList<File> arrayList) {
        File file = null;
        if (arrayList.size() == 0) {
            return null;
        }
        long j = -1;
        for (File file2 : arrayList) {
            long lastModifiedTimeInMillis = getLastModifiedTimeInMillis(file2);
            if (lastModifiedTimeInMillis > j) {
                file = file2;
                j = lastModifiedTimeInMillis;
            }
        }
        return file != null ? file : arrayList.get(0);
    }

    private static long getLastModifiedTimeInMillis(File file) {
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                return getLastModifiedTimeFromBasicFileAttrs(file);
            }
            return file.lastModified();
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }
    }

    @RequiresApi(26)
    private static long getLastModifiedTimeFromBasicFileAttrs(File file) {
        try {
            return Files.readAttributes(file.toPath(), BasicFileAttributes.class, new LinkOption[0]).creationTime().toMillis();
        } catch (Exception unused) {
            return -1L;
        }
    }

    private static void copyFile(FileInputStream fileInputStream, FileOutputStream fileOutputStream) throws Throwable {
        FileChannel fileChannel;
        FileChannel channel;
        FileChannel channel2 = null;
        try {
            channel = fileInputStream.getChannel();
        } catch (Throwable th) {
            th = th;
            fileChannel = null;
        }
        try {
            channel2 = fileOutputStream.getChannel();
            channel.transferTo(0L, channel.size(), channel2);
            try {
                channel.close();
            } finally {
                if (channel2 != null) {
                    channel2.close();
                }
            }
        } catch (Throwable th2) {
            th = th2;
            FileChannel fileChannel2 = channel2;
            channel2 = channel;
            fileChannel = fileChannel2;
            if (channel2 != null) {
                try {
                    channel2.close();
                } finally {
                    if (fileChannel != null) {
                        fileChannel.close();
                    }
                }
            }
            throw th;
        }
    }
}
