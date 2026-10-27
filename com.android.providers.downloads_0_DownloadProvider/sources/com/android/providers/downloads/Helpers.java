package com.android.providers.downloads;

import android.app.AppOpsManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.FileUtils;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.os.UserHandle;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.provider.Downloads;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.webkit.MimeTypeMap;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.util.ArrayUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class Helpers {
    private static Handler sAsyncHandler;
    private static HandlerThread sAsyncHandlerThread;
    private static DownloadNotifier sNotifier;
    private static SystemFacade sSystemFacade;
    public static Random sRandom = new Random(SystemClock.uptimeMillis());
    private static final Pattern CONTENT_DISPOSITION_PATTERN = Pattern.compile("attachment;\\s*filename\\s*=\\s*\"([^\"]*)\"");
    private static final Pattern PATTERN_ANDROID_DIRS = Pattern.compile("(?i)^/storage/[^/]+(?:/[0-9]+)?/Android/(?:data|obb|media)/.+");
    private static final Pattern PATTERN_ANDROID_PRIVATE_DIRS = Pattern.compile("(?i)^/storage/[^/]+(?:/[0-9]+)?/Android/(data|obb)/.+");
    private static final Pattern PATTERN_PUBLIC_DIRS = Pattern.compile("(?i)^/storage/[^/]+(?:/[0-9]+)?/([^/]+)/.+");
    private static final Object sUniqueLock = new Object();
    private static final Pattern PATTERN_RELATIVE_PATH = Pattern.compile("(?i)^/storage/(?:emulated/[0-9]+/|[^/]+/)(Android/sandbox/([^/]+)/)?");
    private static final Pattern PATTERN_VOLUME_NAME = Pattern.compile("(?i)^/storage/([^/]+)");

    public static synchronized Handler getAsyncHandler() {
        if (sAsyncHandlerThread == null) {
            HandlerThread handlerThread = new HandlerThread("sAsyncHandlerThread", 10);
            sAsyncHandlerThread = handlerThread;
            handlerThread.start();
            sAsyncHandler = new Handler(sAsyncHandlerThread.getLooper());
        }
        return sAsyncHandler;
    }

    @VisibleForTesting
    public static synchronized void setSystemFacade(SystemFacade systemFacade) {
        sSystemFacade = systemFacade;
    }

    public static synchronized SystemFacade getSystemFacade(Context context) {
        if (sSystemFacade == null) {
            sSystemFacade = new RealSystemFacade(context);
        }
        return sSystemFacade;
    }

    public static synchronized DownloadNotifier getDownloadNotifier(Context context) {
        if (sNotifier == null) {
            sNotifier = new DownloadNotifier(context);
        }
        return sNotifier;
    }

    public static String getString(Cursor cursor, String str) {
        return cursor.getString(cursor.getColumnIndexOrThrow(str));
    }

    public static int getInt(Cursor cursor, String str) {
        return cursor.getInt(cursor.getColumnIndexOrThrow(str));
    }

    public static void scheduleJob(Context context, long j) {
        if (scheduleJob(context, DownloadInfo.queryDownloadInfo(context, j))) {
            return;
        }
        getDownloadNotifier(context).update();
    }

    public static boolean scheduleJob(Context context, DownloadInfo downloadInfo) {
        if (downloadInfo == null) {
            return false;
        }
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(JobScheduler.class);
        int i = (int) downloadInfo.mId;
        jobScheduler.cancel(i);
        if (!downloadInfo.isReadyToSchedule()) {
            return false;
        }
        JobInfo.Builder builder = new JobInfo.Builder(i, new ComponentName(context, (Class<?>) DownloadJobService.class));
        if (downloadInfo.isVisible()) {
            builder.setPriority(35);
            builder.setFlags(1);
        }
        long minimumLatency = downloadInfo.getMinimumLatency();
        if (minimumLatency > 0) {
            builder.setMinimumLatency(minimumLatency);
        }
        builder.setRequiredNetworkType(downloadInfo.getRequiredNetworkType(downloadInfo.mTotalBytes));
        if ((downloadInfo.mFlags & 1) != 0) {
            builder.setRequiresCharging(true);
        }
        if ((downloadInfo.mFlags & 2) != 0) {
            builder.setRequiresDeviceIdle(true);
        }
        if (downloadInfo.mTotalBytes > 0) {
            if (downloadInfo.mCurrentBytes > 0 && !TextUtils.isEmpty(downloadInfo.mETag)) {
                builder.setEstimatedNetworkBytes(downloadInfo.mTotalBytes - downloadInfo.mCurrentBytes, -1L);
            } else {
                builder.setEstimatedNetworkBytes(downloadInfo.mTotalBytes, -1L);
            }
        }
        String str = downloadInfo.mPackage;
        if (str == null) {
            str = context.getPackageManager().getPackagesForUid(downloadInfo.mUid)[0];
        }
        jobScheduler.scheduleAsPackage(builder.build(), str, UserHandle.myUserId(), "DownloadManager");
        return true;
    }

    private static String parseContentDisposition(String str) {
        try {
            Matcher matcher = CONTENT_DISPOSITION_PATTERN.matcher(str);
            if (matcher.find()) {
                return matcher.group(1);
            }
            return null;
        } catch (IllegalStateException unused) {
            return null;
        }
    }

    static String generateSaveFile(Context context, String str, String str2, String str3, String str4, String str5, int i) throws IOException {
        String strChooseFilename;
        File absoluteFile;
        File[] fileArr;
        String strSubstring;
        String strChooseExtensionFromFilename;
        String absolutePath;
        if (i == 4) {
            File file = new File(Uri.parse(str2).getPath());
            absoluteFile = file.getParentFile().getAbsoluteFile();
            fileArr = new File[]{absoluteFile};
            strChooseFilename = file.getName();
        } else {
            File runningDestinationDirectory = getRunningDestinationDirectory(context, i);
            File[] fileArr2 = {runningDestinationDirectory, getSuccessDestinationDirectory(context, i)};
            strChooseFilename = chooseFilename(str, str2, str3, str4);
            absoluteFile = runningDestinationDirectory;
            fileArr = fileArr2;
        }
        for (File file2 : fileArr) {
            if (!file2.isDirectory() && !file2.mkdirs()) {
                throw new IOException("Failed to create parent for " + file2);
            }
        }
        if (DownloadDrmHelper.isDrmConvertNeeded(str5)) {
            strChooseFilename = DownloadDrmHelper.modifyDrmFwLockFileExtension(strChooseFilename);
        }
        int iLastIndexOf = strChooseFilename.lastIndexOf(46);
        boolean z = iLastIndexOf < 0;
        if (i == 4) {
            if (z) {
                strChooseExtensionFromFilename = "";
            } else {
                strSubstring = strChooseFilename.substring(0, iLastIndexOf);
                strChooseExtensionFromFilename = strChooseFilename.substring(iLastIndexOf);
                strChooseFilename = strSubstring;
            }
        } else if (z) {
            strChooseExtensionFromFilename = chooseExtensionFromMimeType(str5, true);
        } else {
            strSubstring = strChooseFilename.substring(0, iLastIndexOf);
            strChooseExtensionFromFilename = chooseExtensionFromFilename(str5, i, strChooseFilename, iLastIndexOf);
            strChooseFilename = strSubstring;
        }
        synchronized (sUniqueLock) {
            File file3 = new File(absoluteFile, generateAvailableFilenameLocked(fileArr, strChooseFilename, strChooseExtensionFromFilename));
            file3.createNewFile();
            absolutePath = file3.getAbsolutePath();
        }
        return absolutePath;
    }

    private static String chooseFilename(String str, String str2, String str3, String str4) {
        String strDecode;
        int iLastIndexOf;
        String strDecode2;
        if (str2 == null || str2.endsWith("/")) {
            str2 = null;
        } else {
            if (Constants.LOGVV) {
                Log.v("DownloadManager", "getting filename from hint");
            }
            int iLastIndexOf2 = str2.lastIndexOf(47) + 1;
            if (iLastIndexOf2 > 0) {
                str2 = str2.substring(iLastIndexOf2);
            }
        }
        if (str2 == null && str3 != null && (str2 = parseContentDisposition(str3)) != null) {
            if (Constants.LOGVV) {
                Log.v("DownloadManager", "getting filename from content-disposition");
            }
            int iLastIndexOf3 = str2.lastIndexOf(47) + 1;
            if (iLastIndexOf3 > 0) {
                str2 = str2.substring(iLastIndexOf3);
            }
        }
        if (str2 == null && str4 != null && (strDecode2 = Uri.decode(str4)) != null && !strDecode2.endsWith("/") && strDecode2.indexOf(63) < 0) {
            if (Constants.LOGVV) {
                Log.v("DownloadManager", "getting filename from content-location");
            }
            int iLastIndexOf4 = strDecode2.lastIndexOf(47) + 1;
            str2 = iLastIndexOf4 > 0 ? strDecode2.substring(iLastIndexOf4) : strDecode2;
        }
        if (str2 == null && (strDecode = Uri.decode(str)) != null && !strDecode.endsWith("/") && strDecode.indexOf(63) < 0 && (iLastIndexOf = strDecode.lastIndexOf(47) + 1) > 0) {
            if (Constants.LOGVV) {
                Log.v("DownloadManager", "getting filename from uri");
            }
            str2 = strDecode.substring(iLastIndexOf);
        }
        if (str2 == null) {
            if (Constants.LOGVV) {
                Log.v("DownloadManager", "using default filename");
            }
            str2 = "downloadfile";
        }
        return FileUtils.buildValidFatFilename(str2);
    }

    private static String chooseExtensionFromMimeType(String str, boolean z) {
        String extensionFromMimeType;
        if (str != null) {
            extensionFromMimeType = MimeTypeMap.getSingleton().getExtensionFromMimeType(str);
            if (extensionFromMimeType != null) {
                if (Constants.LOGVV) {
                    Log.v("DownloadManager", "adding extension from type");
                }
                extensionFromMimeType = "." + extensionFromMimeType;
            } else if (Constants.LOGVV) {
                Log.v("DownloadManager", "couldn't find extension for " + str);
            }
        } else {
            extensionFromMimeType = null;
        }
        if (extensionFromMimeType != null) {
            return extensionFromMimeType;
        }
        if (str == null || !str.toLowerCase().startsWith("text/")) {
            if (!z) {
                return extensionFromMimeType;
            }
            if (Constants.LOGVV) {
                Log.v("DownloadManager", "adding default binary extension");
            }
            return ".bin";
        }
        if (str.equalsIgnoreCase("text/html")) {
            if (Constants.LOGVV) {
                Log.v("DownloadManager", "adding default html extension");
            }
            return ".html";
        }
        if (!z) {
            return extensionFromMimeType;
        }
        if (Constants.LOGVV) {
            Log.v("DownloadManager", "adding default text extension");
        }
        return ".txt";
    }

    private static String chooseExtensionFromFilename(String str, int i, String str2, int i2) {
        String strChooseExtensionFromMimeType;
        String mimeTypeFromExtension;
        if (str == null || ((mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str2.substring(i2 + 1))) != null && mimeTypeFromExtension.equalsIgnoreCase(str))) {
            strChooseExtensionFromMimeType = null;
        } else {
            strChooseExtensionFromMimeType = chooseExtensionFromMimeType(str, false);
            if (strChooseExtensionFromMimeType != null) {
                if (Constants.LOGVV) {
                    Log.v("DownloadManager", "substituting extension from type");
                }
            } else if (Constants.LOGVV) {
                Log.v("DownloadManager", "couldn't find extension for " + str);
            }
        }
        if (strChooseExtensionFromMimeType != null) {
            return strChooseExtensionFromMimeType;
        }
        if (Constants.LOGVV) {
            Log.v("DownloadManager", "keeping extension");
        }
        return str2.substring(i2);
    }

    private static boolean isFilenameAvailableLocked(File[] fileArr, String str) {
        if ("recovery".equalsIgnoreCase(str)) {
            return false;
        }
        for (File file : fileArr) {
            if (new File(file, str).exists()) {
                return false;
            }
        }
        return true;
    }

    private static String generateAvailableFilenameLocked(File[] fileArr, String str, String str2) throws IOException {
        String str3 = str + str2;
        if (isFilenameAvailableLocked(fileArr, str3)) {
            return str3;
        }
        int iNextInt = 1;
        for (int i = 1; i < 1000000000; i *= 10) {
            for (int i2 = 0; i2 < 9; i2++) {
                String str4 = str + "-" + iNextInt + str2;
                if (isFilenameAvailableLocked(fileArr, str4)) {
                    return str4;
                }
                iNextInt += sRandom.nextInt(i) + 1;
            }
        }
        throw new IOException("Failed to generate an available filename");
    }

    public static Uri convertToMediaStoreDownloadsUri(Uri uri) {
        return MediaStore.Downloads.getContentUri(MediaStore.getVolumeName(uri), ContentUris.parseId(uri));
    }

    public static Uri triggerMediaScan(ContentProviderClient contentProviderClient, File file) {
        return MediaStore.scanFile(ContentResolver.wrap(contentProviderClient), file);
    }

    public static final Uri getContentUriForPath(Context context, String str) {
        return MediaStore.Downloads.getContentUri(((StorageManager) context.getSystemService(StorageManager.class)).getStorageVolume(new File(str)).getMediaStoreVolumeName());
    }

    public static boolean isFileInExternalAndroidDirs(String str) {
        return PATTERN_ANDROID_DIRS.matcher(str).matches();
    }

    static boolean isFilenameValid(Context context, File file) {
        return isFilenameValid(context, file, true);
    }

    static boolean isFilenameValidInExternal(Context context, File file) {
        return isFilenameValid(context, file, false);
    }

    static boolean isFilenameValidInExternalPackage(File file, String str) {
        try {
            if (containsCanonical(Environment.buildExternalStorageAppDataDirs(str), file) || containsCanonical(Environment.buildExternalStorageAppObbDirs(str), file)) {
                return true;
            }
            return containsCanonical(Environment.buildExternalStorageAppMediaDirs(str), file);
        } catch (IOException e) {
            Log.w("DownloadManager", "Failed to resolve canonical path: " + file.getAbsolutePath(), e);
            return false;
        }
    }

    static boolean isFilenameValidInExternalObbDir(File file) {
        try {
            return containsCanonical(Environment.buildExternalStorageAndroidObbDirs(), file);
        } catch (IOException e) {
            Log.w("DownloadManager", "Failed to resolve canonical path: " + file.getAbsolutePath(), e);
            return false;
        }
    }

    static boolean isFileInPrivateExternalAndroidDirs(File file) {
        try {
            return PATTERN_ANDROID_PRIVATE_DIRS.matcher(file.getCanonicalPath()).matches();
        } catch (IOException e) {
            Log.w("DownloadManager", "Failed to resolve canonical path: " + file.getAbsolutePath(), e);
            return false;
        }
    }

    @VisibleForTesting
    static void checkDestinationFilePathRestrictions(File file, String str, Context context, AppOpsManager appOpsManager, String str2, boolean z, boolean z2) {
        boolean zIsFilenameValidInKnownPublicDir;
        if (z2) {
            zIsFilenameValidInKnownPublicDir = isFilenameValidInPublicDownloadsDir(file);
        } else {
            zIsFilenameValidInKnownPublicDir = isFilenameValidInKnownPublicDir(file.getAbsolutePath());
        }
        if (isFilenameValidInExternalPackage(file, str) || zIsFilenameValidInKnownPublicDir) {
            return;
        }
        if (isFilenameValidInExternalObbDir(file) && isCallingAppInstaller(context, appOpsManager, str)) {
            return;
        }
        if (isFileInPrivateExternalAndroidDirs(file)) {
            throw new SecurityException("Unsupported path " + file);
        }
        if (z && isFilenameValidInExternal(context, file)) {
            context.enforceCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE", "No permission to write to " + file);
            if (appOpsManager.noteProxyOp(60, str, Binder.getCallingUid(), str2, (String) null) == 0) {
                return;
            }
            throw new SecurityException("No permission to write to " + file);
        }
        throw new SecurityException("Unsupported path " + file);
    }

    private static boolean isCallingAppInstaller(Context context, AppOpsManager appOpsManager, String str) {
        return appOpsManager.noteOp(66, Binder.getCallingUid(), str, (String) null, "obb_download") == 0 || context.checkCallingOrSelfPermission("android.permission.REQUEST_INSTALL_PACKAGES") == 0;
    }

    static boolean isFilenameValidInPublicDownloadsDir(File file) {
        try {
            return containsCanonical(Environment.buildExternalStoragePublicDirs(Environment.DIRECTORY_DOWNLOADS), file);
        } catch (IOException e) {
            Log.w("DownloadManager", "Failed to resolve canonical path: " + file.getAbsolutePath(), e);
            return false;
        }
    }

    @VisibleForTesting
    public static boolean isFilenameValidInKnownPublicDir(String str) {
        if (str == null) {
            return false;
        }
        Matcher matcher = PATTERN_PUBLIC_DIRS.matcher(str);
        if (!matcher.matches()) {
            return false;
        }
        return ArrayUtils.contains(Environment.STANDARD_DIRECTORIES, matcher.group(1));
    }

    static boolean isFilenameValid(Context context, File file, boolean z) {
        if (z) {
            try {
                if (containsCanonical(context.getFilesDir(), file) || containsCanonical(context.getCacheDir(), file) || containsCanonical(Environment.getDownloadCacheDirectory(), file)) {
                    return true;
                }
            } catch (IOException e) {
                Log.w("DownloadManager", "Failed to resolve canonical path: " + file.getAbsolutePath(), e);
                return false;
            }
        }
        for (StorageVolume storageVolume : StorageManager.getVolumeList(UserHandle.myUserId(), 256)) {
            if (containsCanonical(storageVolume.getPathFile(), file)) {
                return true;
            }
        }
        return false;
    }

    private static String normalizeUuid(String str) {
        if (str != null) {
            return str.toLowerCase(Locale.ROOT);
        }
        return null;
    }

    public static String extractVolumeName(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = PATTERN_VOLUME_NAME.matcher(str);
        if (!matcher.find()) {
            return "internal";
        }
        String strGroup = matcher.group(1);
        return strGroup.equals("emulated") ? "external_primary" : normalizeUuid(strGroup);
    }

    public static String extractRelativePath(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = PATTERN_RELATIVE_PATH.matcher(str);
        if (!matcher.find()) {
            return null;
        }
        int iLastIndexOf = str.lastIndexOf(47);
        return (iLastIndexOf == -1 || iLastIndexOf < matcher.end()) ? "/" : str.substring(matcher.end(), iLastIndexOf + 1);
    }

    public static String extractDisplayName(String str) {
        if (str == null) {
            return null;
        }
        if (str.indexOf(47) == -1) {
            return str;
        }
        if (str.endsWith("/")) {
            str = str.substring(0, str.length() - 1);
        }
        return str.substring(str.lastIndexOf(47) + 1);
    }

    private static boolean containsCanonical(File file, File file2) throws IOException {
        return FileUtils.contains(file.getCanonicalFile(), file2);
    }

    private static boolean containsCanonical(File[] fileArr, File file) throws IOException {
        for (File file2 : fileArr) {
            if (containsCanonical(file2, file)) {
                return true;
            }
        }
        return false;
    }

    public static File getRunningDestinationDirectory(Context context, int i) throws IOException {
        return getDestinationDirectory(context, i, true);
    }

    public static File getSuccessDestinationDirectory(Context context, int i) throws IOException {
        return getDestinationDirectory(context, i, false);
    }

    private static File getDestinationDirectory(Context context, int i, boolean z) throws IOException {
        if (i == 0) {
            File file = new File(Environment.getExternalStorageDirectory(), Environment.DIRECTORY_DOWNLOADS);
            if (file.isDirectory() || !file.mkdirs()) {
                return file;
            }
            throw new IOException("unable to create external downloads directory");
        }
        if (i == 1 || i == 2 || i == 3) {
            if (z) {
                return context.getFilesDir();
            }
            return context.getCacheDir();
        }
        throw new IllegalStateException("unexpected destination: " + i);
    }

    @VisibleForTesting
    public static void handleRemovedUidEntries(Context context, ContentProvider contentProvider, int i) {
        String str;
        String str2;
        SparseArray sparseArray = new SparseArray();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (i == -1) {
            str = "uid IS NOT NULL";
        } else {
            str = "uid=" + i;
        }
        Cursor cursorQuery = contentProvider.query(Downloads.Impl.ALL_DOWNLOADS_CONTENT_URI, new String[]{"_id", "uid", "destination", "_data"}, str, null, null);
        while (cursorQuery.moveToNext()) {
            try {
                long j = cursorQuery.getLong(0);
                int i2 = cursorQuery.getInt(1);
                int iIndexOfKey = sparseArray.indexOfKey(i2);
                if (iIndexOfKey >= 0) {
                    str2 = (String) sparseArray.valueAt(iIndexOfKey);
                } else {
                    String packageForUid = getPackageForUid(context, i2);
                    sparseArray.put(i2, packageForUid);
                    str2 = packageForUid;
                }
                if (str2 == null) {
                    int i3 = cursorQuery.getInt(2);
                    String string = cursorQuery.getString(3);
                    if ((i3 == 0 || i3 == 4 || i3 == 6) && isFilenameValidInKnownPublicDir(string)) {
                        arrayList2.add(Long.valueOf(j));
                    } else {
                        arrayList.add(Long.valueOf(j));
                    }
                }
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    try {
                        cursorQuery.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        if (arrayList2.size() > 0) {
            Log.i("DownloadManager", "Orphaning downloads with ids " + Arrays.toString(arrayList2.toArray()) + " as owner package is removed");
            ContentValues contentValues = new ContentValues();
            contentValues.putNull("uid");
            contentProvider.update(Downloads.Impl.ALL_DOWNLOADS_CONTENT_URI, contentValues, buildQueryWithIds(arrayList2), null);
        }
        if (arrayList.size() > 0) {
            Log.i("DownloadManager", "Deleting downloads with ids " + Arrays.toString(arrayList.toArray()) + " as owner package is removed");
            contentProvider.delete(Downloads.Impl.ALL_DOWNLOADS_CONTENT_URI, buildQueryWithIds(arrayList), null);
        }
    }

    public static String buildQueryWithIds(ArrayList<Long> arrayList) {
        StringBuilder sb = new StringBuilder("_id in (");
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            sb.append(arrayList.get(i));
            sb.append(i == size + (-1) ? ")" : ",");
            i++;
        }
        return sb.toString();
    }

    public static String getPackageForUid(Context context, int i) {
        String[] packagesForUid = context.getPackageManager().getPackagesForUid(i);
        if (packagesForUid == null || packagesForUid.length == 0) {
            return null;
        }
        return packagesForUid[0];
    }
}
