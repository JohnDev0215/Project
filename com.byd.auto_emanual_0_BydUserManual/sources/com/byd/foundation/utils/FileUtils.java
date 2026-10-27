package com.byd.foundation.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import org.apache.shiro.config.Ini;

/* JADX INFO: loaded from: classes.dex */
public class FileUtils {
    private static final String TAG = "FileUtils";
    private static int sBufferSize = 524288;

    public interface OnProgressUpdateListener {
        void onProgressUpdate(double d);
    }

    public static String readFile2String(Context context, String str) {
        return readFile2String(context, getFileByPath(str), null);
    }

    public static String readFile2String(Context context, File file, String str) {
        byte[] file2BytesByStream = readFile2BytesByStream(context, file);
        if (file2BytesByStream == null) {
            return null;
        }
        if (StringUtils.isSpace(str)) {
            return new String(file2BytesByStream);
        }
        try {
            return new String(file2BytesByStream, str);
        } catch (UnsupportedEncodingException e) {
            LogUtils.e(TAG, e.getMessage(), e);
            return "";
        }
    }

    public static byte[] readFile2BytesByStream(Context context, File file) {
        return readFile2BytesByStream(context, file, (OnProgressUpdateListener) null);
    }

    public static byte[] readFile2BytesByStream(Context context, String str) {
        return readFile2BytesByStream(context, getFileByPath(str), (OnProgressUpdateListener) null);
    }

    public static File getFileByPath(String str) {
        if (StringUtils.isSpace(str)) {
            return null;
        }
        return new File(str);
    }

    public static byte[] readFile2BytesByStream(Context context, String str, OnProgressUpdateListener onProgressUpdateListener) {
        return readFile2BytesByStream(context, getFileByPath(str), onProgressUpdateListener);
    }

    public static byte[] readFile2BytesByStream(Context context, File file, OnProgressUpdateListener onProgressUpdateListener) {
        if (!isFileExists(file) && !isFileExistsApi29(context, file.getAbsolutePath())) {
            return null;
        }
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file), sBufferSize);
            try {
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        byte[] bArr = new byte[sBufferSize];
                        if (onProgressUpdateListener != null) {
                            double dAvailable = bufferedInputStream.available();
                            onProgressUpdateListener.onProgressUpdate(0.0d);
                            int i = 0;
                            while (true) {
                                int i2 = bufferedInputStream.read(bArr, 0, sBufferSize);
                                if (i2 == -1) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr, 0, i2);
                                i += i2;
                                onProgressUpdateListener.onProgressUpdate(((double) i) / dAvailable);
                            }
                        } else {
                            while (true) {
                                int i3 = bufferedInputStream.read(bArr, 0, sBufferSize);
                                if (i3 == -1) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr, 0, i3);
                            }
                        }
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        byteArrayOutputStream.close();
                        bufferedInputStream.close();
                        return byteArray;
                    } finally {
                    }
                } catch (Exception e) {
                    LogUtils.e(TAG, e.getMessage(), e);
                    bufferedInputStream.close();
                    return null;
                }
            } finally {
            }
        } catch (IOException e2) {
            LogUtils.e(TAG, e2.getMessage(), e2);
            return null;
        }
    }

    public static boolean writeFileFromIS(String str, InputStream inputStream) {
        return writeFileFromIS(getFileByPath(str), inputStream, false, (OnProgressUpdateListener) null);
    }

    public static boolean writeFileFromIS(String str, InputStream inputStream, boolean z) {
        return writeFileFromIS(getFileByPath(str), inputStream, z, (OnProgressUpdateListener) null);
    }

    public static boolean writeFileFromIS(File file, InputStream inputStream) {
        return writeFileFromIS(file, inputStream, false, (OnProgressUpdateListener) null);
    }

    public static boolean writeFileFromIS(File file, InputStream inputStream, boolean z) {
        return writeFileFromIS(file, inputStream, z, (OnProgressUpdateListener) null);
    }

    public static boolean writeFileFromIS(String str, InputStream inputStream, OnProgressUpdateListener onProgressUpdateListener) {
        return writeFileFromIS(getFileByPath(str), inputStream, false, onProgressUpdateListener);
    }

    public static boolean writeFileFromIS(String str, InputStream inputStream, boolean z, OnProgressUpdateListener onProgressUpdateListener) {
        return writeFileFromIS(getFileByPath(str), inputStream, z, onProgressUpdateListener);
    }

    public static boolean writeFileFromIS(File file, InputStream inputStream, OnProgressUpdateListener onProgressUpdateListener) {
        return writeFileFromIS(file, inputStream, false, onProgressUpdateListener);
    }

    public static boolean writeFileFromIS(File file, InputStream inputStream, boolean z, OnProgressUpdateListener onProgressUpdateListener) {
        if (inputStream == null || !createOrExistsFile(file)) {
            LogUtils.e(TAG, "create file <" + file + "> failed.");
            return false;
        }
        try {
            try {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file, z), sBufferSize);
                try {
                    if (onProgressUpdateListener == null) {
                        byte[] bArr = new byte[sBufferSize];
                        while (true) {
                            int i = inputStream.read(bArr);
                            if (i == -1) {
                                break;
                            }
                            bufferedOutputStream.write(bArr, 0, i);
                        }
                    } else {
                        double dAvailable = inputStream.available();
                        onProgressUpdateListener.onProgressUpdate(0.0d);
                        byte[] bArr2 = new byte[sBufferSize];
                        int i2 = 0;
                        while (true) {
                            int i3 = inputStream.read(bArr2);
                            if (i3 == -1) {
                                break;
                            }
                            bufferedOutputStream.write(bArr2, 0, i3);
                            i2 += i3;
                            onProgressUpdateListener.onProgressUpdate(((double) i2) / dAvailable);
                        }
                    }
                    bufferedOutputStream.close();
                    CloseUtils.closeIO(inputStream);
                    return true;
                } finally {
                }
            } catch (Exception e) {
                LogUtils.e(TAG, e.getMessage(), e);
                CloseUtils.closeIO(inputStream);
                return false;
            }
        } catch (Throwable th) {
            CloseUtils.closeIO(inputStream);
            throw th;
        }
    }

    public static boolean writeFileFromBytesByStream(String str, byte[] bArr) {
        return writeFileFromBytesByStream(getFileByPath(str), bArr, false, (OnProgressUpdateListener) null);
    }

    public static boolean writeFileFromBytesByStream(String str, byte[] bArr, boolean z) {
        return writeFileFromBytesByStream(getFileByPath(str), bArr, z, (OnProgressUpdateListener) null);
    }

    public static boolean writeFileFromBytesByStream(File file, byte[] bArr) {
        return writeFileFromBytesByStream(file, bArr, false, (OnProgressUpdateListener) null);
    }

    public static boolean writeFileFromBytesByStream(File file, byte[] bArr, boolean z) {
        return writeFileFromBytesByStream(file, bArr, z, (OnProgressUpdateListener) null);
    }

    public static boolean writeFileFromBytesByStream(String str, byte[] bArr, OnProgressUpdateListener onProgressUpdateListener) {
        return writeFileFromBytesByStream(getFileByPath(str), bArr, false, onProgressUpdateListener);
    }

    public static boolean writeFileFromBytesByStream(String str, byte[] bArr, boolean z, OnProgressUpdateListener onProgressUpdateListener) {
        return writeFileFromBytesByStream(getFileByPath(str), bArr, z, onProgressUpdateListener);
    }

    public static boolean writeFileFromBytesByStream(File file, byte[] bArr, OnProgressUpdateListener onProgressUpdateListener) {
        return writeFileFromBytesByStream(file, bArr, false, onProgressUpdateListener);
    }

    public static boolean writeFileFromBytesByStream(File file, byte[] bArr, boolean z, OnProgressUpdateListener onProgressUpdateListener) {
        if (bArr == null) {
            return false;
        }
        return writeFileFromIS(file, new ByteArrayInputStream(bArr), z, onProgressUpdateListener);
    }

    public static boolean writeFileFromString(String str, String str2) {
        return writeFileFromString(getFileByPath(str), str2, false);
    }

    public static boolean writeFileFromString(String str, String str2, boolean z) {
        return writeFileFromString(getFileByPath(str), str2, z);
    }

    public static boolean writeFileFromString(File file, String str) {
        return writeFileFromString(file, str, false);
    }

    public static boolean writeFileFromString(File file, String str, boolean z) {
        if (file != null && str != null) {
            if (!createOrExistsFile(file)) {
                LogUtils.e(TAG, "create file <" + file + "> failed.");
                return false;
            }
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, z));
                try {
                    bufferedWriter.write(str);
                    bufferedWriter.close();
                    return true;
                } finally {
                }
            } catch (IOException e) {
                LogUtils.e(TAG, e.getMessage(), e);
            }
        }
        return false;
    }

    public static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    public static boolean createOrExistsFile(String str) {
        return createOrExistsFile(getFileByPath(str));
    }

    public static boolean createOrExistsFile(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            LogUtils.e(TAG, e.getMessage(), e);
            return false;
        }
    }

    public static boolean isFileExists(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return true;
        }
        return isFileExists(file.getAbsolutePath());
    }

    public static boolean isFileExists(String str) {
        File fileByPath = getFileByPath(str);
        return fileByPath != null && fileByPath.exists();
    }

    public static boolean isFileExist(Context context, String str) {
        try {
            try {
                CloseUtils.closeIO(context.getResources().getAssets().open(str));
                return true;
            } catch (IOException e) {
                LogUtils.e(TAG, "isFileExist ex:" + e.getMessage());
                CloseUtils.closeIO(null);
                return false;
            }
        } catch (Throwable th) {
            CloseUtils.closeIO(null);
            throw th;
        }
    }

    public static boolean isFileExistsApi29(Context context, String str) {
        if (Build.VERSION.SDK_INT >= 29) {
            try {
                AssetFileDescriptor assetFileDescriptorOpenAssetFileDescriptor = context.getContentResolver().openAssetFileDescriptor(Uri.parse(str), "r");
                if (assetFileDescriptorOpenAssetFileDescriptor == null) {
                    if (assetFileDescriptorOpenAssetFileDescriptor != null) {
                        assetFileDescriptorOpenAssetFileDescriptor.close();
                    }
                    return false;
                }
                if (assetFileDescriptorOpenAssetFileDescriptor == null) {
                    return true;
                }
                assetFileDescriptorOpenAssetFileDescriptor.close();
                return true;
            } catch (IOException e) {
                LogUtils.e(TAG, e.getMessage(), e);
            }
        }
        return false;
    }

    public static String readFile(String str) {
        Object obj;
        ByteArrayOutputStream byteArrayOutputStream;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        FileInputStream fileInputStream = null;
        try {
            File file = new File(str);
            if (!file.exists()) {
                LogUtils.w(TAG, Ini.SECTION_PREFIX + str + "] file not found ...");
                CloseUtils.closeIO(null, null);
                return "";
            }
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = fileInputStream2.read(bArr);
                        if (-1 != i) {
                            byteArrayOutputStream.write(bArr, 0, i);
                        } else {
                            String strTrim = byteArrayOutputStream.toString("UTF-8").trim();
                            CloseUtils.closeIO(fileInputStream2, byteArrayOutputStream);
                            return strTrim;
                        }
                    }
                } catch (Throwable th) {
                    obj = th;
                    fileInputStream = fileInputStream2;
                    try {
                        LogUtils.e(TAG, Ini.SECTION_PREFIX + str + "] read failed: " + obj);
                        CloseUtils.closeIO(fileInputStream, byteArrayOutputStream);
                        return "";
                    } catch (Throwable th2) {
                        CloseUtils.closeIO(fileInputStream, byteArrayOutputStream);
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                byteArrayOutputStream = null;
                fileInputStream = fileInputStream2;
                obj = th3;
            }
        } catch (Throwable th4) {
            obj = th4;
            byteArrayOutputStream = null;
        }
    }

    public static byte[] inputStreamToByteArray(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] bArr;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                bArr = new byte[1024];
            } finally {
            }
        } catch (Throwable th) {
            try {
                LogUtils.e(TAG, " inputStreamToByteArray failed: ", th);
                CloseUtils.closeIO(inputStream);
                return new byte[0];
            } catch (Throwable th2) {
                CloseUtils.closeIO(inputStream);
                throw th2;
            }
        }
        while (true) {
            int i = inputStream.read(bArr);
            if (i != -1) {
                byteArrayOutputStream.write(bArr, 0, i);
            } else {
                byteArrayOutputStream.flush();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                CloseUtils.closeIO(inputStream);
                return byteArray;
            }
            LogUtils.e(TAG, " inputStreamToByteArray failed: ", th);
            CloseUtils.closeIO(inputStream);
            return new byte[0];
        }
    }

    public static boolean isFileExist(String str, Context context) {
        try {
            try {
                CloseUtils.closeIO(context.getResources().getAssets().open(str));
                return true;
            } catch (IOException e) {
                LogUtils.e(TAG, "isFileExist ex:" + e.getMessage());
                CloseUtils.closeIO(null);
                return false;
            }
        } catch (Throwable th) {
            CloseUtils.closeIO(null);
            throw th;
        }
    }

    public static void setBufferSize(int i) {
        sBufferSize = i;
    }
}
