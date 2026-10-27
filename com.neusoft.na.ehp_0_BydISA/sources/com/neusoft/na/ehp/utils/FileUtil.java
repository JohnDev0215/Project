package com.neusoft.na.ehp.utils;

import android.content.Context;
import android.os.storage.StorageManager;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import org.java_websocket.extensions.ExtensionRequestData;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class FileUtil {
    private static final String TAG = "NeuEhpFileUtil";

    public File getAssetsInstallDir(Context context) {
        return new File(getAppCacheDir(context), "install");
    }

    private File getAppCacheDir(Context context) {
        return context.getFilesDir();
    }

    public File getPrivateCacheDir(Context context) {
        return new File(getServiceCacheDir(context), "cache");
    }

    public File getPrivateTempDir(Context context) {
        return new File(getServiceCacheDir(context), "tmp");
    }

    private File getServiceCacheDir(Context context) {
        return new File(getAppCacheDir(context), "EhpService");
    }

    public String getDltLogPath(Context context) {
        InputStream inputStreamOpen;
        String string = "/sdcard/navilog";
        InputStream inputStream = null;
        try {
            try {
                try {
                    inputStreamOpen = context.getAssets().open("dlt_config.json");
                    if (inputStreamOpen != null) {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        while (true) {
                            int i = inputStreamOpen.read();
                            if (i == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(i);
                        }
                        string = new JSONObject(byteArrayOutputStream.toString()).getString("path");
                    } else {
                        Log.i(TAG, "External config doesn't exist,using default config options.");
                    }
                } catch (Throwable th) {
                    if (0 != 0) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (0 != 0) {
                    inputStream.close();
                }
            }
            if (inputStreamOpen != null) {
                inputStreamOpen.close();
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        return string;
    }

    public void copyFolder(String str, String str2) throws Exception {
        File file;
        new File(str2).mkdirs();
        String[] list = new File(str).list();
        for (int i = 0; i < list.length; i++) {
            if (str.endsWith(File.separator)) {
                file = new File(str + list[i]);
            } else {
                file = new File(str + File.separator + list[i]);
            }
            if (file.isFile()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                FileOutputStream fileOutputStream = new FileOutputStream(str2 + "/" + file.getName().toString());
                byte[] bArr = new byte[5120];
                while (true) {
                    int i2 = fileInputStream.read(bArr);
                    if (i2 == -1) {
                        break;
                    } else {
                        fileOutputStream.write(bArr, 0, i2);
                    }
                }
                fileOutputStream.flush();
                fileOutputStream.getFD().sync();
                try {
                    fileOutputStream.close();
                    fileInputStream.close();
                } catch (IOException unused) {
                }
            }
            if (file.isDirectory()) {
                copyFolder(str + "/" + list[i], str2 + "/" + list[i]);
            }
        }
    }

    public boolean deleteDirectory(String str) {
        if (!str.endsWith(File.separator)) {
            str = str + File.separator;
        }
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            return false;
        }
        File[] fileArrListFiles = file.listFiles();
        boolean zDeleteDirectory = true;
        for (int i = 0; i < fileArrListFiles.length; i++) {
            if (fileArrListFiles[i].isFile()) {
                zDeleteDirectory = deleteFile(fileArrListFiles[i].getAbsolutePath());
                if (!zDeleteDirectory) {
                    break;
                }
            } else {
                if (fileArrListFiles[i].isDirectory() && !(zDeleteDirectory = deleteDirectory(fileArrListFiles[i].getAbsolutePath()))) {
                    break;
                }
            }
        }
        return zDeleteDirectory;
    }

    private boolean deleteFile(String str) {
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + str + "成功！");
                Log.i(TAG, "删除单个文件" + str + "成功！");
                return true;
            }
            Log.i(TAG, "删除单个文件" + str + "失败！");
            return false;
        }
        Log.i(TAG, "删除单个文件失败：" + str + "不存在！");
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0116 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:105:0x00eb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0106 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0120 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:131:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00d3 A[Catch: IOException -> 0x00c0, FileNotFoundException -> 0x00c2, all -> 0x00fe, TRY_ENTER, TryCatch #20 {all -> 0x00fe, blocks: (B:21:0x009a, B:64:0x00e6, B:39:0x00bc, B:52:0x00d3, B:53:0x00d6), top: B:101:0x0069 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00f5 A[Catch: IOException -> 0x00f9, TRY_ENTER, TRY_LEAVE, TryCatch #0 {IOException -> 0x00f9, blocks: (B:71:0x00f5, B:48:0x00cc, B:85:0x0110), top: B:101:0x0069 }] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0110 A[Catch: IOException -> 0x00f9, TRY_ENTER, TRY_LEAVE, TryCatch #0 {IOException -> 0x00f9, blocks: (B:71:0x00f5, B:48:0x00cc, B:85:0x0110), top: B:101:0x0069 }] */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r8v15, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v18 */
    /* JADX WARN: Type inference failed for: r8v19 */
    /* JADX WARN: Type inference failed for: r8v21 */
    /* JADX WARN: Type inference failed for: r8v23 */
    /* JADX WARN: Type inference failed for: r8v26, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r8v27 */
    /* JADX WARN: Type inference failed for: r8v29 */
    /* JADX WARN: Type inference failed for: r8v30 */
    /* JADX WARN: Type inference failed for: r8v31, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r8v32, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v34 */
    /* JADX WARN: Type inference failed for: r8v35 */
    /* JADX WARN: Type inference failed for: r8v36 */
    /* JADX WARN: Type inference failed for: r8v37 */
    /* JADX WARN: Type inference failed for: r8v38 */
    /* JADX WARN: Type inference failed for: r8v39 */
    /* JADX WARN: Type inference failed for: r8v40 */
    /* JADX WARN: Type inference failed for: r8v41 */
    /* JADX WARN: Type inference failed for: r8v42 */
    /* JADX WARN: Type inference failed for: r8v43 */
    /* JADX WARN: Type inference failed for: r8v44 */
    /* JADX WARN: Type inference failed for: r8v45 */
    /* JADX WARN: Type inference failed for: r8v46 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:74:0x00fa -> B:100:0x0113). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean fileHasStr(java.lang.String r8, java.lang.String r9, java.lang.String r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 298
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.ehp.utils.FileUtil.fileHasStr(java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    private String encrypt2ToMD5(String str) {
        String string = ExtensionRequestData.EMPTY_VALUE;
        try {
            byte[] bArrDigest = MessageDigest.getInstance("MD5").digest(str.getBytes("utf-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDigest) {
                sb.append(Integer.toHexString((b & 255) | 256).substring(1, 3).toUpperCase());
            }
            string = sb.toString();
            Log.i(TAG, "encrypt2ToMD5 hexStr:" + string);
            return string;
        } catch (Exception e) {
            e.printStackTrace();
            return string;
        }
    }

    public static String getDbPath(Context context) {
        List<String> uSBPaths = getUSBPaths(context);
        Log.i(TAG, "getDbPath: " + uSBPaths.size());
        return uSBPaths.size() > 0 ? uSBPaths.get(0) : ExtensionRequestData.EMPTY_VALUE;
    }

    public static List<String> getUSBPaths(Context context) {
        Log.i(TAG, "getUSBPaths:" + context);
        ArrayList arrayList = new ArrayList();
        try {
            for (String str : (String[]) StorageManager.class.getMethod("getVolumePaths", new Class[0]).invoke((StorageManager) context.getSystemService("storage"), new Object[0])) {
                Log.i(TAG, "Path: " + str);
                if (!str.contains("emulated") && !str.contains("sdcard") && !str.contains("/data") && !str.contains("/dev")) {
                    Log.i(TAG, "USB Path: " + str);
                    arrayList.add(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
