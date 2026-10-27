package com.byd.auto_emanual.util;

import android.content.Context;
import android.text.TextUtils;
import com.byd.auto_emanual.Config;
import com.byd.auto_emanual.MApp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

/* JADX INFO: loaded from: classes.dex */
public class FileUtil {
    private static final String TAG = "FileUtil";

    public static File getCacheDir(Context context) {
        File externalCacheDir = context.getExternalCacheDir();
        return (externalCacheDir == null || !externalCacheDir.exists()) ? context.getCacheDir() : externalCacheDir;
    }

    public static String getCountryAndLanguagePath() {
        String str = getCacheDir(MApp.context).getAbsolutePath() + File.separator + AutoInfoUtil.getCountryCode() + "_" + Utils.getResourceLanguage().replace(",", "_");
        File file = new File(str);
        return (file.exists() || file.mkdir()) ? str : getCacheDir(MApp.context).getPath();
    }

    public static String getManualZipPathInDisk() {
        return Config.ROOT_PATH_IN_DISK + File.separator + Config.HANDBOOK_ROOT_PATH + File.separator + Config.MANUAL_IN_DISK + File.separator + Config.MANUAL_ZIP_NAME_IN_DISK;
    }

    public static String getZipInUsb() {
        return Config.HANDBOOK_ROOT_PATH + File.separator + Config.MANUAL_ZIP_NAME_IN_DISK;
    }

    public static void delete(File file) {
        if (file == null || !file.exists()) {
            MLog.d(TAG, file.getAbsolutePath() + " is null or not exists!");
            return;
        }
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles != null) {
                for (File file2 : fileArrListFiles) {
                    delete(file2);
                }
            } else {
                MLog.d(TAG, "null == innerFiles");
            }
            file.delete();
        }
    }

    public static void delete(String path) {
        if (TextUtils.isEmpty(path)) {
            return;
        }
        File file = new File(path);
        if (file.exists()) {
            MLog.d(TAG, "delete path = " + path);
        }
        delete(file);
    }

    public static boolean exists(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }
        boolean zExists = new File(filePath).exists();
        MLog.d(TAG, filePath + " exists = " + zExists);
        return zExists;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean existsAndHasChild(java.lang.String r3) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 != 0) goto L3a
            java.io.File r0 = new java.io.File
            r0.<init>(r3)
            boolean r1 = r0.exists()
            if (r1 == 0) goto L3a
            java.lang.String[] r0 = r0.list()
            if (r0 == 0) goto L1c
            int r0 = r0.length
            if (r0 <= 0) goto L1c
            r0 = 1
            goto L3b
        L1c:
            java.lang.String r0 = com.byd.auto_emanual.util.FileUtil.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "existsAndHasChild: "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.String r2 = " do not have child."
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.byd.auto_emanual.util.MLog.i(r0, r1)
        L3a:
            r0 = 0
        L3b:
            java.lang.String r1 = com.byd.auto_emanual.util.FileUtil.TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.StringBuilder r3 = r2.append(r3)
            java.lang.String r2 = " existsAndHasChild = "
            java.lang.StringBuilder r3 = r3.append(r2)
            java.lang.StringBuilder r3 = r3.append(r0)
            java.lang.String r3 = r3.toString()
            com.byd.auto_emanual.util.MLog.d(r1, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.auto_emanual.util.FileUtil.existsAndHasChild(java.lang.String):boolean");
    }

    public static void copy(String sourcePath, String destPath, String rootPath) throws Throwable {
        FileChannel fileChannel;
        delete(rootPath);
        String[] strArrSplit = destPath.split(File.separator);
        if (strArrSplit == null || strArrSplit.length <= 0) {
            MLog.i(TAG, "destPath is incorrect. " + destPath);
            return;
        }
        String str = File.separator + strArrSplit[1];
        for (int i = 2; i < strArrSplit.length - 1; i++) {
            str = str + File.separator + strArrSplit[i];
            File file = new File(str);
            if (file.exists() && file.isDirectory()) {
                MLog.i(TAG, file.getAbsolutePath() + " is exists.");
            } else {
                MLog.i(TAG, "mkdir  " + file.mkdir() + ". " + str);
            }
        }
        File file2 = new File(sourcePath);
        File file3 = new File(destPath);
        FileChannel channel = null;
        try {
            FileChannel channel2 = new FileInputStream(file2).getChannel();
            try {
                channel = new FileOutputStream(file3).getChannel();
                channel.transferFrom(channel2, 0L, channel2.size());
                String str2 = TAG;
                MLog.i(str2, "copy successful.");
                CloseUtil.close(channel2, str2);
                CloseUtil.close(channel, str2);
            } catch (Exception e) {
                e = e;
                fileChannel = channel;
                channel = channel2;
                try {
                    String str3 = TAG;
                    MLog.e(str3, "copy exception", e);
                    CloseUtil.close(channel, str3);
                    CloseUtil.close(fileChannel, str3);
                } catch (Throwable th) {
                    th = th;
                    String str4 = TAG;
                    CloseUtil.close(channel, str4);
                    CloseUtil.close(fileChannel, str4);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileChannel = channel;
                channel = channel2;
                String str42 = TAG;
                CloseUtil.close(channel, str42);
                CloseUtil.close(fileChannel, str42);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            fileChannel = null;
        } catch (Throwable th3) {
            th = th3;
            fileChannel = null;
        }
    }

    public static String readFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReaderNewBufferedReader = null;
        try {
            try {
                if (new File(fileName).exists()) {
                    bufferedReaderNewBufferedReader = Files.newBufferedReader(Paths.get(fileName, new String[0]));
                    while (true) {
                        String line = bufferedReaderNewBufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line);
                    }
                } else {
                    MLog.i(TAG, fileName + " file not exists.");
                }
            } catch (IOException e) {
                String str = TAG;
                MLog.e(str, "readFile error. ", e);
                CloseUtil.close(null, str);
            }
            return sb.toString();
        } finally {
            CloseUtil.close(null, TAG);
        }
    }

    public static String sandBoxPath(Context context) {
        return getCacheDir(context).getAbsolutePath();
    }

    public static String readAssertFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(MApp.context.getAssets().open(fileName)));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
            }
        } catch (IOException e) {
            MLog.e(TAG, "readAssertFile", e);
        }
        return sb.toString();
    }
}
