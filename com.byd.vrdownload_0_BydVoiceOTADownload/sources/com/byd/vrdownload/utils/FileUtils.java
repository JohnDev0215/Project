package com.byd.vrdownload.utils;

import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import com.byd.vrdownload.App;
import com.byd.vrdownload.model.LanguageUpdate;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public class FileUtils {
    public static String DOWNLOAD_PATH = getDownloadPathValue();
    public static String PROFILE_PATH = getProfilePathValue();
    public static String PROFILE_TEMP_PATH = getProfileTempPathValue();

    public static String getProfilePath() {
        LogUtils.e("FileUtils", "getProfilePath:" + PROFILE_PATH);
        if (isSdcardException(PROFILE_PATH)) {
            PROFILE_PATH = getProfilePathValue();
        }
        return PROFILE_PATH;
    }

    public static String getProfileTempPath(String str) {
        LogUtils.e("FileUtils", "getProfileTempPath:" + PROFILE_TEMP_PATH);
        if (isSdcardException(PROFILE_TEMP_PATH)) {
            PROFILE_TEMP_PATH = getProfileTempPathValue();
        }
        return !TextUtils.isEmpty(str) ? PROFILE_TEMP_PATH + str : "error";
    }

    public static String getFilePath(LanguageUpdate languageUpdate) {
        LogUtils.e("FileUtils", "getFilePath:" + DOWNLOAD_PATH);
        if (isSdcardException(DOWNLOAD_PATH)) {
            DOWNLOAD_PATH = getDownloadPathValue();
        }
        if (languageUpdate.getLatestPatchPkgKey() != null) {
            return DOWNLOAD_PATH + "/data_" + languageUpdate.getLanguage() + languageUpdate.getLatestPkgVersion() + ".d";
        }
        return DOWNLOAD_PATH + "/data_" + languageUpdate.getLanguage() + languageUpdate.getLatestPkgVersion() + ".7z";
    }

    public static String getFilePath(String str, String str2) {
        LogUtils.e("FileUtils", "getFilePath22:" + DOWNLOAD_PATH);
        if (isSdcardException(DOWNLOAD_PATH)) {
            DOWNLOAD_PATH = getDownloadPathValue();
        }
        return DOWNLOAD_PATH + "/data_" + str + str2 + ".7z";
    }

    public static String getFileName(LanguageUpdate languageUpdate) {
        if (languageUpdate.getLatestPatchPkgKey() != null) {
            return "/data_" + languageUpdate.getLanguage() + languageUpdate.getLatestPkgVersion() + ".d";
        }
        return "/data_" + languageUpdate.getLanguage() + languageUpdate.getLatestPkgVersion() + ".7z";
    }

    public static String getFilePathTemp(LanguageUpdate languageUpdate) {
        LogUtils.e("FileUtils", "getFilePathTemp:" + DOWNLOAD_PATH);
        if (isSdcardException(DOWNLOAD_PATH)) {
            DOWNLOAD_PATH = getDownloadPathValue();
        }
        if (languageUpdate.getLatestPatchPkgKey() != null) {
            return DOWNLOAD_PATH + "/data_" + languageUpdate.getLanguage() + languageUpdate.getLatestPkgVersion() + ".d.temp";
        }
        return DOWNLOAD_PATH + "/data_" + languageUpdate.getLanguage() + languageUpdate.getLatestPkgVersion() + ".7z.temp";
    }

    public static String getFilePathOld(LanguageUpdate languageUpdate) {
        LogUtils.e("FileUtils", "getFilePathOld:" + DOWNLOAD_PATH);
        if (isSdcardException(DOWNLOAD_PATH)) {
            DOWNLOAD_PATH = getDownloadPathValue();
        }
        return DOWNLOAD_PATH + File.separator + languageUpdate.getDownloadingPkgKey();
    }

    public static File createDir(File file, String str) {
        File file2 = new File(file, str);
        if (createDir(file2)) {
            return file2;
        }
        return null;
    }

    public static boolean createDir(File file) {
        if (file.exists()) {
            return file.isDirectory();
        }
        return file.mkdir();
    }

    public static File getFileByPath(String str) {
        if (StringUtils.isSpace(str)) {
            return null;
        }
        return new File(str);
    }

    public static boolean isFileExists(String str) {
        File fileByPath = getFileByPath(str);
        if (fileByPath == null) {
            return false;
        }
        if (fileByPath.exists()) {
            return true;
        }
        return isFileExistsApi29(str);
    }

    private static boolean isFileExistsApi29(String str) {
        if (Build.VERSION.SDK_INT >= 29) {
            try {
                AssetFileDescriptor assetFileDescriptorOpenAssetFileDescriptor = App.CONTEXT.getContentResolver().openAssetFileDescriptor(Uri.parse(str), "r");
                if (assetFileDescriptorOpenAssetFileDescriptor == null) {
                    return false;
                }
                try {
                    assetFileDescriptorOpenAssetFileDescriptor.close();
                    return true;
                } catch (IOException unused) {
                    return true;
                }
            } catch (FileNotFoundException unused2) {
            }
        }
        return false;
    }

    public static boolean delete(String str) {
        return delete(getFileByPath(str));
    }

    public static boolean delete(File file) {
        if (file == null) {
            return false;
        }
        if (file.isDirectory()) {
            return deleteDir(file);
        }
        return deleteFile(file);
    }

    private static boolean deleteDir(File file) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            return true;
        }
        if (!file.isDirectory()) {
            return false;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
            for (File file2 : fileArrListFiles) {
                if (file2.isFile()) {
                    if (!file2.delete()) {
                        return false;
                    }
                } else if (file2.isDirectory() && !deleteDir(file2)) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    private static boolean deleteFile(File file) {
        return file != null && (!file.exists() || (file.isFile() && file.delete()));
    }

    public static boolean checkMd5(File file, String str) {
        return str.equals(getFileMD5(file));
    }

    private static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        byte[] bArr = new byte[1024];
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int i = fileInputStream.read(bArr, 0, 1024);
                if (i != -1) {
                    messageDigest.update(bArr, 0, i);
                } else {
                    fileInputStream.close();
                    return bytesToHexString(messageDigest.digest());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String bytesToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & FileDownloadStatus.error);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static boolean rename(String str, String str2) {
        return rename(getFileByPath(str), str2);
    }

    public static boolean rename(File file, String str) {
        if (file == null || !file.exists() || StringUtils.isSpace(str)) {
            return false;
        }
        if (str.equals(file.getName())) {
            return true;
        }
        File file2 = new File(file.getParent() + File.separator + str);
        return !file2.exists() && file.renameTo(file2);
    }

    private static String getDownloadPathValue() {
        String str = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + "VRDownload";
        LogUtils.e("FileUtils", "getDownloadPathValue:" + str);
        return str;
    }

    public static String getProfilePathValue() {
        String str = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + "VRProfile" + File.separator + "profile" + File.separator;
        LogUtils.e("FileUtils", "getProfilePathValue:" + str);
        return str;
    }

    public static String getProfileTempPathValue() {
        String str = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + "VRProfile" + File.separator + "temp" + File.separator;
        LogUtils.e("FileUtils", "getProfileTempPathValue:" + str);
        return str;
    }

    public static boolean isSdcardException(String str) {
        return str != null && str.contains("dev/null");
    }
}
