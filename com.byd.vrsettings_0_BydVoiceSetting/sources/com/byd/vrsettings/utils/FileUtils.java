package com.byd.vrsettings.utils;

import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import com.byd.vrdownload.model.LanguageUpdate;
import com.byd.vrsettings.BydVoiceSettingApplication;
import com.chad.library.BuildConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import kotlin.UByte;

/* JADX INFO: loaded from: classes.dex */
public class FileUtils {
    public static final String DOWNLOAD_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + "VRDownload";

    public static String getFilePath(LanguageUpdate languageUpdate) {
        if (languageUpdate.getLatestPatchPkgKey() != null) {
            return DOWNLOAD_PATH + "/data_" + languageUpdate.getLanguage() + languageUpdate.getLatestPkgVersion() + ".d";
        }
        return DOWNLOAD_PATH + "/data_" + languageUpdate.getLanguage() + languageUpdate.getLatestPkgVersion() + ".7z";
    }

    public static String getFilePath(String str, String str2) {
        return DOWNLOAD_PATH + "/data_" + str + str2 + ".7z";
    }

    public static String getFileName(LanguageUpdate languageUpdate) {
        if (languageUpdate.getLatestPatchPkgKey() != null) {
            return "/data_" + languageUpdate.getLanguage() + languageUpdate.getLatestPkgVersion() + ".d";
        }
        return "/data_" + languageUpdate.getLanguage() + languageUpdate.getLatestPkgVersion() + ".7z";
    }

    public static String getFilePathTemp(LanguageUpdate languageUpdate) {
        if (languageUpdate.getLatestPatchPkgKey() != null) {
            return DOWNLOAD_PATH + "/data_" + languageUpdate.getLanguage() + languageUpdate.getLatestPkgVersion() + ".d.temp";
        }
        return DOWNLOAD_PATH + "/data_" + languageUpdate.getLanguage() + languageUpdate.getLatestPkgVersion() + ".7z.temp";
    }

    public static String getFilePathOld(LanguageUpdate languageUpdate) {
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
                AssetFileDescriptor assetFileDescriptorOpenAssetFileDescriptor = BydVoiceSettingApplication.getContext().getContentResolver().openAssetFileDescriptor(Uri.parse(str), "r");
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
        StringBuilder sb = new StringBuilder(BuildConfig.FLAVOR);
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
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
}
