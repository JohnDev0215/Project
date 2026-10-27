package com.amazon.mp3.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteFullException;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.activity.SQLErrorDialog;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.intents.PendingIntentUtil;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.service.ComponentCheckService;
import com.amazon.music.externalstorage.StorageLocationFileManager;
import java.io.File;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes4.dex */
public abstract class StorageInfo {
    private static final String LOGTAG = "StorageInfo";
    private static final String TAG = "StorageInfo";

    public abstract File getInternalStorageDirectory() throws DeviceNotAvailableException;

    protected abstract String getInternalStorageState() throws DeviceNotAvailableException;

    public static class DeviceNotAvailableException extends Exception {
        DeviceNotAvailableException() {
        }

        DeviceNotAvailableException(Exception cause) {
            super(cause);
        }
    }

    @VisibleForTesting
    protected StorageInfo() {
    }

    public final boolean isInternalStorageAvailable() {
        try {
            if (AmazonApplication.isExiting()) {
                return false;
            }
            return "mounted".equals(getInstance().getInternalStorageState());
        } catch (DeviceNotAvailableException unused) {
            return false;
        }
    }

    public final boolean isExternalStorageAvailable() {
        try {
            if (AmazonApplication.isExiting()) {
                return false;
            }
            return "mounted".equals(getInstance().getExternalStorageState());
        } catch (DeviceNotAvailableException | NullPointerException unused) {
            return false;
        }
    }

    private String getExternalStorageState() throws DeviceNotAvailableException, NullPointerException {
        return Environment.getExternalStorageState();
    }

    public File getExternalStorageDirectory() throws DeviceNotAvailableException {
        return Environment.getExternalStorageDirectory();
    }

    public static StorageInfo getInstance() {
        if (HtcWithInternalStorageInfo.isCurrentDeviceSupported()) {
            return new HtcWithInternalStorageInfo();
        }
        return new DefaultStorageInfo();
    }

    public static boolean isDataPartitionBelowAmount(int mb) {
        return getFreeSpace() < ((long) mb) * 1048576;
    }

    public static boolean isDataPartitionFull() {
        return isDataPartitionBelowAmount(5);
    }

    public static boolean isDataPartitionLow() {
        return isDataPartitionBelowAmount(70);
    }

    public static void showNotificationIfDiskFull(Context context) {
        if (isDataPartitionFull()) {
            showDiskFullNotification(context);
        }
    }

    private static Notification createDiskLowNotification(Context context) {
        Intent libraryHomeStartIntent = LibraryActivityFactory.getLibraryHomeStartIntent(context.getApplicationContext());
        libraryHomeStartIntent.addFlags(603979776);
        PendingIntent activity = PendingIntentUtil.INSTANCE.getActivity(context, 0, libraryHomeStartIntent, 134217728, true);
        String string = context.getString(R.string.dmusic_lowspace_notification_title);
        return new Notification.Builder(context).setContentIntent(activity).setTicker(string).setContentTitle(string).setContentText(context.getString(ResourceUtil.getLowspaceNotificationDescStringId())).setSmallIcon(ResourceUtil.getWarningNotificationIconId()).setAutoCancel(true).setWhen(System.currentTimeMillis()).getNotification();
    }

    public static void showDiskLowNotification(Context context) {
        BaseActivity.setLowMemory(StorageInfo.class, true);
        ((NotificationManager) context.getSystemService("notification")).notify(1005, createDiskLowNotification(context));
        context.stopService(new Intent(context, (Class<?>) ComponentCheckService.class));
    }

    public static void showDiskFullNotification(Context context) {
        BaseActivity.setFullMemory(StorageInfo.class, false);
        ((NotificationManager) context.getSystemService("notification")).notify(1005, createDiskLowNotification(context));
        context.stopService(new Intent(context, (Class<?>) ComponentCheckService.class));
    }

    public static void hideDiskLowNotification(Context context) {
        BaseActivity.setLowMemory(StorageInfo.class, false);
        try {
            ((NotificationManager) context.getSystemService("notification")).cancel(1005);
        } catch (Exception e) {
            Log.warning(LOGTAG, "Could not cancel notification: ", e);
        }
    }

    public static void hideDiskFullNotification(Context context) {
        BaseActivity.setFullMemory(StorageInfo.class, false);
        try {
            ((NotificationManager) context.getSystemService("notification")).cancel(1005);
        } catch (Exception e) {
            Log.warning(LOGTAG, "Could not cancel notification: ", e);
        }
    }

    public Long getTotalSpaceFromPathMB(final File file) {
        Long lValueOf = Long.valueOf(file.getTotalSpace());
        if (lValueOf.longValue() == 0) {
            if (!file.exists()) {
                boolean zMkdirs = file.mkdirs();
                Log.warning(TAG, "Directory " + file.getPath() + " created successfully: " + zMkdirs);
            }
            lValueOf = Long.valueOf(file.getTotalSpace());
        }
        if (lValueOf.longValue() == 0) {
            lValueOf = Long.valueOf(FileUtil.getTotalSpace(file));
            Log.warning(TAG, "File.freeSpace returned 0, trying FileUtil.getFreeSpace via StatFs: " + Formatter.formatShortFileSize(AmazonApplication.getContext(), lValueOf.longValue()));
        }
        return Long.valueOf(convertBytesToMB(lValueOf));
    }

    public long getTotalInternalStorageMB() {
        return convertBytesToMB(Long.valueOf(Environment.getDataDirectory().getTotalSpace()));
    }

    public static long convertBytesToMB(final Long bytesValue) {
        return bytesValue.longValue() / 1048576;
    }

    public static long getInternalStorageMBAvailable() {
        return new File(Environment.getDataDirectory().getAbsolutePath()).getUsableSpace() / 1048576;
    }

    public static long getExternalStorageMBAvailable(final Context context) {
        return StorageLocationFileManager.create(context, Environment.DIRECTORY_MUSIC).getPreferredStorageDirectory(StorageLocationFileManager.Visibility.PUBLIC).getUsableSpace() / 1048576;
    }

    public static void logStorageDebugInfo(final Context context) {
        Log.info(TAG, "%d MB usable on /data | %d MB usable for download", Long.valueOf(getInternalStorageMBAvailable()), Long.valueOf(getExternalStorageMBAvailable(context)));
    }

    public static void updateNotification(Context context) {
        if (isDataPartitionLow()) {
            if (isDataPartitionFull()) {
                showDiskFullNotification(context);
                return;
            } else {
                hideDiskFullNotification(context);
                showDiskLowNotification(context);
                return;
            }
        }
        hideDiskFullNotification(context);
        hideDiskLowNotification(context);
    }

    private static long getFreeSpace() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
        return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
    }

    private static class DefaultStorageInfo extends StorageInfo {
        private DefaultStorageInfo() {
        }

        @Override // com.amazon.mp3.util.StorageInfo
        protected String getInternalStorageState() throws DeviceNotAvailableException {
            throw new DeviceNotAvailableException();
        }

        @Override // com.amazon.mp3.util.StorageInfo
        public File getInternalStorageDirectory() throws DeviceNotAvailableException {
            throw new DeviceNotAvailableException();
        }
    }

    private static class HtcWithInternalStorageInfo extends StorageInfo {
        private static Method sGetPhoneStorageDirectory;
        private static Method sGetPhoneStorageState;
        private static Method sGetSupportedStorages;

        private HtcWithInternalStorageInfo() {
        }

        static {
            try {
                sGetSupportedStorages = Environment.class.getMethod("getSupportedStorages", new Class[0]);
                sGetPhoneStorageState = Environment.class.getMethod("getPhoneStorageState", new Class[0]);
                sGetPhoneStorageDirectory = Environment.class.getMethod("getPhoneStorageDirectory", new Class[0]);
            } catch (NoSuchMethodException unused) {
            }
        }

        public static boolean isCurrentDeviceSupported() {
            return (sGetSupportedStorages == null || sGetPhoneStorageState == null || sGetPhoneStorageDirectory == null) ? false : true;
        }

        @Override // com.amazon.mp3.util.StorageInfo
        protected String getInternalStorageState() throws DeviceNotAvailableException {
            try {
                return (String) sGetPhoneStorageState.invoke(null, new Object[0]);
            } catch (Exception e) {
                throw new DeviceNotAvailableException(e);
            }
        }

        @Override // com.amazon.mp3.util.StorageInfo
        public File getInternalStorageDirectory() throws DeviceNotAvailableException {
            try {
                return (File) sGetPhoneStorageDirectory.invoke(null, new Object[0]);
            } catch (Exception e) {
                throw new DeviceNotAvailableException(e);
            }
        }
    }

    public static boolean canOpenDatabase(Context context) {
        try {
            SQLiteDatabase.openDatabase(context.getDatabasePath("CirrusMediaSource.db").getPath(), null, 0).close();
            return true;
        } catch (Exception e) {
            if (e instanceof SQLiteFullException) {
                Log.error(TAG, "Ran into SQLiteFullException", e);
                return false;
            }
            Log.error(TAG, "Ran into unknown exception", e);
            return true;
        }
    }

    public static void createSQLOutOfSpaceDialog(Context context) {
        context.startActivity(SQLErrorDialog.getStartIntent(context, R.string.dmusic_storage_check_title, R.string.dmusic_storage_check_description));
    }
}
