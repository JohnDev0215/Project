package com.byd.vrassistant.utils;

import android.content.Context;
import android.os.StatFs;
import android.text.TextUtils;
import cerence.ark.assistant.core.dcc.DccConstant;
import cerence.ark.assistant.library.JsonKey;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.bean.VoiceLanuageDp;
import com.byd.vrassistant.manager.USBCopyDataManager;
import com.google.android.mms.pdu.Base64;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes2.dex */
public class DataUtils {
    public static final int BUFFER_LEN = 8192;
    private static final String TAG = "NEU_DataUtils";
    static long hasReadLen;
    static long lastReadLen;

    public interface OTADataProgress {
        void onOtaDataErr(VoiceLanuageDp voiceLanuageDp, String str);

        void onOtaDataFinished(VoiceLanuageDp voiceLanuageDp);

        void onOtaDataStarted(VoiceLanuageDp voiceLanuageDp);
    }

    public interface USBCopyDataProgress {
        boolean onFileCopyBackUp(String str, String str2);

        void onFileCopyFinished(int i, String str);

        boolean onFileCopyIncreased(String str, String str2);

        void onFileCopyStarted(String str, String str2);

        boolean onGetFileMD5Sum(String str, int i, String str2);

        void onGetMaxFileSize(int i);

        void onToast(String str, String str2, int i);
    }

    public static String getPlatformDataPackPath() {
        return Constant.DATA_PATH_8155;
    }

    public static String getPlatformExportLogPath() {
        return Constant.EXPORT_LOG_PATH_8155;
    }

    public static String getPlatformUSADataPackPath() {
        return Constant.SYSTEM_DATA_PATH_8155;
    }

    public static boolean copyData(Context context, String str) throws Throwable {
        String str2 = TAG;
        RLog.d(str2, String.format("data path = %s", str));
        String assets2String = readAssets2String(context, "data/version.json");
        String file2String = readFile2String(str + "/version.json");
        if (!TextUtils.isEmpty(assets2String) && assets2String.equals(file2String)) {
            RLog.d(str2, String.format("dmVersionFromAssets = %s, dmVersionFromApp = %s", assets2String, file2String));
            return true;
        }
        RLog.d(str2, "copy data file begin");
        RLog.d(str2, "delete old data, result = " + deleteDirOrFile(new File(str)));
        boolean zCopyFileFromAssets = copyFileFromAssets(context, JsonKey.RESULTS_DATA, str);
        RLog.d(str2, String.format("dmVersionFromAssets = %s, result = %s", assets2String, Boolean.valueOf(zCopyFileFromAssets)));
        return zCopyFileFromAssets;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(17:0|2|74|3|72|4|5|61|6|(3:7|(1:9)(1:76)|(1:43)(1:77))|10|(2:67|12)|57|16|20|(0)(0)|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0039, code lost:
    
        r7 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x003a, code lost:
    
        r7.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:43:0x006a A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0070 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x007a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:77:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:78:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String readAssets2String(android.content.Context r7, java.lang.String r8) throws java.lang.Throwable {
        /*
            r0 = 0
            android.content.Context r7 = r7.getApplicationContext()     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4e
            android.content.res.AssetManager r7 = r7.getAssets()     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4e
            java.io.InputStream r7 = r7.open(r8)     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4e
            java.io.ByteArrayOutputStream r8 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L46
            r8.<init>()     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L46
            r1 = 8192(0x2000, float:1.148E-41)
            byte[] r2 = new byte[r1]     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L6d
        L16:
            r3 = 0
            int r4 = r7.read(r2, r3, r1)     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L6d
            r5 = -1
            if (r4 == r5) goto L22
            r8.write(r2, r3, r4)     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L6d
            goto L16
        L22:
            java.lang.String r1 = new java.lang.String     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L6d
            byte[] r2 = r8.toByteArray()     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L6d
            r1.<init>(r2)     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L6d
            if (r7 == 0) goto L35
            r7.close()     // Catch: java.io.IOException -> L31
            goto L35
        L31:
            r7 = move-exception
            r7.printStackTrace()
        L35:
            r8.close()     // Catch: java.io.IOException -> L39
            goto L3d
        L39:
            r7 = move-exception
            r7.printStackTrace()
        L3d:
            r0 = r1
            goto L68
        L3f:
            r1 = move-exception
            goto L51
        L41:
            r8 = move-exception
            r6 = r0
            r0 = r8
            r8 = r6
            goto L6e
        L46:
            r1 = move-exception
            r8 = r0
            goto L51
        L49:
            r7 = move-exception
            r8 = r0
            r0 = r7
            r7 = r8
            goto L6e
        L4e:
            r1 = move-exception
            r7 = r0
            r8 = r7
        L51:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L6d
            if (r7 == 0) goto L5e
            r7.close()     // Catch: java.io.IOException -> L5a
            goto L5e
        L5a:
            r7 = move-exception
            r7.printStackTrace()
        L5e:
            if (r8 == 0) goto L68
            r8.close()     // Catch: java.io.IOException -> L64
            goto L68
        L64:
            r7 = move-exception
            r7.printStackTrace()
        L68:
            if (r0 != 0) goto L6c
            java.lang.String r0 = ""
        L6c:
            return r0
        L6d:
            r0 = move-exception
        L6e:
            if (r7 == 0) goto L78
            r7.close()     // Catch: java.io.IOException -> L74
            goto L78
        L74:
            r7 = move-exception
            r7.printStackTrace()
        L78:
            if (r8 == 0) goto L82
            r8.close()     // Catch: java.io.IOException -> L7e
            goto L82
        L7e:
            r7 = move-exception
            r7.printStackTrace()
        L82:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.utils.DataUtils.readAssets2String(android.content.Context, java.lang.String):java.lang.String");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:79|10|81|11|12|(2:64|13)|(3:14|(1:16)(1:83)|(1:49)(1:84))|17|72|18|66|22|26|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0042, code lost:
    
        r8 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0043, code lost:
    
        r8.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004a, code lost:
    
        r8 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x004b, code lost:
    
        r8.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:49:0x007b A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0081 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x008b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:84:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:85:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String readFile2String(java.lang.String r8) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r8)
            r1 = 0
            if (r0 == 0) goto L8
            return r1
        L8:
            java.io.File r0 = new java.io.File
            r0.<init>(r8)
            boolean r8 = r0.exists()
            if (r8 == 0) goto L94
            boolean r8 = r0.isDirectory()
            if (r8 == 0) goto L1b
            goto L94
        L1b:
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5f
            r8.<init>(r0)     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5f
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L57
            r0.<init>()     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L57
            r2 = 8192(0x2000, float:1.148E-41)
            byte[] r3 = new byte[r2]     // Catch: java.lang.Exception -> L50 java.lang.Throwable -> L7e
        L29:
            r4 = 0
            int r5 = r8.read(r3, r4, r2)     // Catch: java.lang.Exception -> L50 java.lang.Throwable -> L7e
            r6 = -1
            if (r5 == r6) goto L35
            r0.write(r3, r4, r5)     // Catch: java.lang.Exception -> L50 java.lang.Throwable -> L7e
            goto L29
        L35:
            java.lang.String r2 = new java.lang.String     // Catch: java.lang.Exception -> L50 java.lang.Throwable -> L7e
            byte[] r3 = r0.toByteArray()     // Catch: java.lang.Exception -> L50 java.lang.Throwable -> L7e
            r2.<init>(r3)     // Catch: java.lang.Exception -> L50 java.lang.Throwable -> L7e
            r8.close()     // Catch: java.io.IOException -> L42
            goto L46
        L42:
            r8 = move-exception
            r8.printStackTrace()
        L46:
            r0.close()     // Catch: java.io.IOException -> L4a
            goto L4e
        L4a:
            r8 = move-exception
            r8.printStackTrace()
        L4e:
            r1 = r2
            goto L79
        L50:
            r2 = move-exception
            goto L62
        L52:
            r0 = move-exception
            r7 = r1
            r1 = r0
            r0 = r7
            goto L7f
        L57:
            r2 = move-exception
            r0 = r1
            goto L62
        L5a:
            r8 = move-exception
            r0 = r1
            r1 = r8
            r8 = r0
            goto L7f
        L5f:
            r2 = move-exception
            r8 = r1
            r0 = r8
        L62:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L7e
            if (r8 == 0) goto L6f
            r8.close()     // Catch: java.io.IOException -> L6b
            goto L6f
        L6b:
            r8 = move-exception
            r8.printStackTrace()
        L6f:
            if (r0 == 0) goto L79
            r0.close()     // Catch: java.io.IOException -> L75
            goto L79
        L75:
            r8 = move-exception
            r8.printStackTrace()
        L79:
            if (r1 != 0) goto L7d
            java.lang.String r1 = ""
        L7d:
            return r1
        L7e:
            r1 = move-exception
        L7f:
            if (r8 == 0) goto L89
            r8.close()     // Catch: java.io.IOException -> L85
            goto L89
        L85:
            r8 = move-exception
            r8.printStackTrace()
        L89:
            if (r0 == 0) goto L93
            r0.close()     // Catch: java.io.IOException -> L8f
            goto L93
        L8f:
            r8 = move-exception
            r8.printStackTrace()
        L93:
            throw r1
        L94:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.utils.DataUtils.readFile2String(java.lang.String):java.lang.String");
    }

    public static boolean deleteDirOrFile(File file) {
        if (file == null) {
            return false;
        }
        if (file.isDirectory()) {
            return deleteDir(file);
        }
        return deleteFile(file);
    }

    public static void deleteDirOrFileEx(String str) throws InterruptedException, IOException {
        Runtime.getRuntime().exec("rm -rf " + str).waitFor();
    }

    public static void clearPackage(String str) {
        try {
            Runtime.getRuntime().exec("pm clear " + str).waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    public static void stringToFile(String str, String str2) {
        try {
            FileWriter fileWriter = new FileWriter(str);
            fileWriter.flush();
            fileWriter.write(str2);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteDir(File file) {
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
        if (fileArrListFiles != null && fileArrListFiles.length != 0) {
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

    public static boolean deleteFile(File file) {
        return file != null && (!file.exists() || (file.isFile() && file.delete()));
    }

    public static boolean copyFileFromAssets(Context context, String str, String str2) {
        try {
            String[] list = context.getApplicationContext().getAssets().list(str);
            if (list != null && list.length > 0) {
                boolean zCopyFileFromAssets = true;
                for (String str3 : list) {
                    zCopyFileFromAssets &= copyFileFromAssets(context, str + "/" + str3, str2 + "/" + str3);
                }
                return zCopyFileFromAssets;
            }
            return writeFileFromIS(new File(str2), context.getAssets().open(str));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void writeObjectToFile(File file, Object obj) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(obj);
            RLog.e(TAG, "writeObjectToFile Serializable ok");
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeStringlToFile(File file, String str) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            bufferedWriter.newLine();
            bufferedWriter.write("----------------------------------------------------------------");
            bufferedWriter.newLine();
            bufferedWriter.write(str);
            bufferedWriter.close();
            RLog.e(TAG, "writeString to " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean writeFileFromIS(File file, InputStream inputStream) throws Throwable {
        if (!createOrExistsFile(file) || inputStream == null) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                try {
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int i = inputStream.read(bArr, 0, 8192);
                        if (i != -1) {
                            fileOutputStream2.write(bArr, 0, i);
                        } else {
                            try {
                                break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    inputStream.close();
                    try {
                        fileOutputStream2.close();
                        return true;
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        return true;
                    }
                } catch (IOException e3) {
                    e = e3;
                    fileOutputStream = fileOutputStream2;
                    e.printStackTrace();
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    try {
                        inputStream.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                            throw th;
                        } catch (IOException e7) {
                            e7.printStackTrace();
                            throw th;
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e8) {
            e = e8;
        }
    }

    private static boolean createOrExistsFile(File file) {
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
            e.printStackTrace();
            return false;
        }
    }

    private static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    public static String byte2Hex(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (int i : bArr) {
            while (i < 0) {
                i += 256;
            }
            if (i < 16) {
                stringBuffer.append("0");
            }
            stringBuffer.append(Integer.toString(i, 16));
        }
        return stringBuffer.toString();
    }

    public static String stringFilter(String str) {
        return Pattern.compile("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]").matcher(str).replaceAll("").replaceAll(" ", "").toLowerCase();
    }

    public static long getFolderSize(File file) {
        long length;
        long j = 0;
        try {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles != null) {
                for (int i = 0; i < fileArrListFiles.length; i++) {
                    if (fileArrListFiles[i].isDirectory()) {
                        length = getFolderSize(fileArrListFiles[i]);
                    } else {
                        length = fileArrListFiles[i].length();
                    }
                    j += length;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j;
    }

    public static long getAvailableSize(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return 0L;
        }
        StatFs statFs = new StatFs(file.getPath());
        return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
    }

    public static boolean copyFile(String str, String str2) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                RLog.e(TAG, "copyFile:  oldFile not exist.");
                return false;
            }
            if (!file.isFile()) {
                RLog.e(TAG, "copyFile:  oldFile not file.");
                return false;
            }
            if (!file.canRead()) {
                RLog.e(TAG, "copyFile:  oldFile cannot read.");
                return false;
            }
            FileInputStream fileInputStream = new FileInputStream(str);
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            byte[] bArr = new byte[1024];
            while (true) {
                int i = fileInputStream.read(bArr);
                if (-1 != i) {
                    fileOutputStream.write(bArr, 0, i);
                } else {
                    fileInputStream.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean copyFileEx(String str, String str2, HashMap<String, String> map, int i) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                RLog.e(TAG, "copyFile:  oldFile not exist.");
                return false;
            }
            if (!file.isFile()) {
                RLog.e(TAG, "copyFile:  oldFile not file.");
                return false;
            }
            if (!file.canRead()) {
                RLog.e(TAG, "copyFile:  oldFile cannot read.");
                return false;
            }
            final File file2 = new File(str2);
            if (file2.exists() && i == 0 && map.containsValue(getFileMD5(file2)) && !((List) map.keySet().stream().filter(new Predicate() { // from class: com.byd.vrassistant.utils.DataUtils$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((String) obj).contains(file2.getName());
                }
            }).collect(Collectors.toList())).isEmpty()) {
                RLog.e(TAG, file2.getAbsolutePath() + " already copy");
                return true;
            }
            RLog.e(TAG, file2.getAbsolutePath() + " start copy");
            FileInputStream fileInputStream = new FileInputStream(str);
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            byte[] bArr = new byte[1024];
            while (true) {
                int i2 = fileInputStream.read(bArr);
                if (-1 != i2) {
                    fileOutputStream.write(bArr, 0, i2);
                } else {
                    fileInputStream.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int copy(String str, String str2) {
        File file = new File(str);
        if (!file.exists()) {
            RLog.i(TAG, "root is not exists,root=" + file);
            return -1;
        }
        File[] fileArrListFiles = file.listFiles();
        File file2 = new File(str2);
        if (!file2.exists()) {
            String str3 = TAG;
            RLog.i(str3, "target is not exists,target=" + file2 + ",canWrite=" + file2.canWrite());
            try {
                RLog.i(str3, "create dir ,result=" + file2.mkdirs());
            } catch (Exception e) {
                RLog.e(TAG, "mkdirs fail", e);
                return -1;
            }
        }
        int iCopy = 0;
        for (int i = 0; i < fileArrListFiles.length; i++) {
            if (fileArrListFiles[i].isDirectory()) {
                iCopy = copy(fileArrListFiles[i].getPath() + "/", str2 + fileArrListFiles[i].getName() + "/");
            } else {
                iCopy = copyFile(fileArrListFiles[i].getPath(), new StringBuilder().append(str2).append(fileArrListFiles[i].getName()).toString()) ? 0 : -1;
            }
        }
        return iCopy;
    }

    public static HashMap<String, String> listAllFileMD5Sum(String str, USBCopyDataProgress uSBCopyDataProgress, int i, String str2) {
        HashMap<String, String> map = new HashMap<>();
        File file = new File(str);
        if (!file.exists()) {
            RLog.i(TAG, "root is not exists,root=" + file);
            return map;
        }
        File[] fileArrListFiles = file.listFiles();
        for (int i2 = 0; i2 < fileArrListFiles.length; i2++) {
            if (fileArrListFiles[i2].isDirectory()) {
                map.putAll(listAllFileMD5Sum(fileArrListFiles[i2].getPath() + "/", uSBCopyDataProgress, i, str2));
            } else if (uSBCopyDataProgress != null ? uSBCopyDataProgress.onGetFileMD5Sum(fileArrListFiles[i2].getName(), i, str2) : true) {
                File file2 = new File(fileArrListFiles[i2].getPath());
                map.put(file2.getAbsolutePath(), getFileMD5(file2));
            }
        }
        return map;
    }

    public static int listAllFileSize(String str) {
        File file = new File(str);
        if (!file.exists()) {
            RLog.i(TAG, "root is not exists,root=" + file);
            return 0;
        }
        File[] fileArrListFiles = file.listFiles();
        int iListAllFileSize = 0;
        for (int i = 0; i < fileArrListFiles.length; i++) {
            iListAllFileSize = fileArrListFiles[i].isDirectory() ? iListAllFileSize + listAllFileSize(fileArrListFiles[i].getPath() + "/") : iListAllFileSize + 1;
        }
        return iListAllFileSize;
    }

    public static int copyEx(String str, String str2, HashMap<String, String> map, USBCopyDataProgress uSBCopyDataProgress, int i, String str3) {
        boolean zOnFileCopyBackUp;
        File file = new File(str);
        if (!file.exists()) {
            RLog.i(TAG, "root is not exists,root=" + file);
            return -1;
        }
        File[] fileArrListFiles = file.listFiles();
        File file2 = new File(str2);
        if (!file2.exists()) {
            String str4 = TAG;
            RLog.i(str4, "target is not exists,target=" + file2 + ",canWrite=" + file2.canWrite());
            try {
                RLog.i(str4, "create dir ,result=" + file2.mkdirs());
                if (!file2.canWrite()) {
                    RLog.e(str4, file2 + ",canWrite=" + file2.canWrite());
                    return -1;
                }
            } catch (Exception e) {
                RLog.e(TAG, "mkdirs fail", e);
                return -1;
            }
        }
        int iCopyEx = 0;
        for (int i2 = 0; i2 < fileArrListFiles.length; i2++) {
            if (fileArrListFiles[i2].isDirectory()) {
                iCopyEx = copyEx(fileArrListFiles[i2].getPath() + "/", str2 + fileArrListFiles[i2].getName() + "/", map, uSBCopyDataProgress, i, str3);
            } else {
                if (uSBCopyDataProgress != null && i == 0) {
                    zOnFileCopyBackUp = uSBCopyDataProgress.onFileCopyIncreased(fileArrListFiles[i2].getName(), str3);
                } else {
                    zOnFileCopyBackUp = (uSBCopyDataProgress == null || i != 1) ? false : uSBCopyDataProgress.onFileCopyBackUp(fileArrListFiles[i2].getName(), str3);
                }
                if (zOnFileCopyBackUp) {
                    iCopyEx = copyFileEx(fileArrListFiles[i2].getPath(), new StringBuilder().append(str2).append(fileArrListFiles[i2].getName()).toString(), map, i) ? 0 : -1;
                }
            }
        }
        return iCopyEx;
    }

    public static String getFileMD5(File file) throws Throwable {
        MessageDigest messageDigest;
        FileInputStream fileInputStream;
        int i;
        String string = "";
        if (!file.isFile()) {
            return "";
        }
        hasReadLen = 0L;
        lastReadLen = 0L;
        byte[] bArr = new byte[1024];
        FileInputStream fileInputStream2 = null;
        fileInputStream2 = null;
        fileInputStream2 = null;
        try {
            try {
                try {
                    messageDigest = MessageDigest.getInstance("MD5");
                    fileInputStream = new FileInputStream(file);
                } catch (Exception e) {
                    e = e;
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            while (true) {
                try {
                    i = fileInputStream.read(bArr, 0, 1024);
                    if (i == -1) {
                        break;
                    }
                    messageDigest.update(bArr, 0, i);
                    hasReadLen += (long) i;
                } catch (Exception e3) {
                    e = e3;
                    fileInputStream2 = fileInputStream;
                    e.printStackTrace();
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                        fileInputStream2 = fileInputStream2;
                    }
                    return string;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream2 = fileInputStream;
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
                return string;
            }
            fileInputStream.close();
            string = new BigInteger(1, messageDigest.digest()).toString(16);
            fileInputStream.close();
            fileInputStream2 = i;
            return string;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String getPlatformDataPackPathEx() {
        String platformDataPackPath = getPlatformDataPackPath();
        File file = new File(platformDataPackPath);
        if (file.exists()) {
            return getPlatformDataPackPath();
        }
        String str = TAG;
        RLog.e(str, platformDataPackPath + " is not exist");
        if (file.mkdirs()) {
            RLog.e(str, "mkdir " + platformDataPackPath + " ok");
            return platformDataPackPath;
        }
        RLog.e(str, "mkdir " + platformDataPackPath + " error");
        File dataDir = BydVoiceApplication.getContext().getDataDir();
        RLog.e(str, "datapack path use data " + dataDir.getAbsolutePath());
        return dataDir != null ? dataDir.getAbsolutePath() : platformDataPackPath;
    }

    public static String getPlatformUSADataPackPathEx() {
        String str;
        if (!new File(getPlatformUSADataPackPath()).exists()) {
            str = getPlatformDataPackPath() + "/data_eng-USA";
            if (new File(str).exists()) {
                RLog.e(TAG, "system is not exist use navi");
            } else {
                String str2 = TAG;
                RLog.e(str2, str + " is not exist");
                File dataDir = BydVoiceApplication.getContext().getDataDir();
                RLog.e(str2, "data_eng-USA use data " + dataDir.getAbsolutePath());
                if (dataDir != null) {
                    str = dataDir.getAbsolutePath() + "/data_eng-USA";
                }
            }
        } else {
            str = getPlatformDataPackPath() + "/data_eng-USA";
        }
        return (!new File(str).exists() || USBCopyDataManager.has_usa_copyed) ? getPlatformUSADataPackPath() : str;
    }

    public static String getCurrentMethodName() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        return stackTrace[1].getClassName() + DccConstant.SEPARATOR + stackTrace[1].getMethodName();
    }

    public static PublicKey loadPublicKey(String str) throws Exception {
        return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new FileInputStream(str))).getPublicKey();
    }

    public static Certificate loadCertificate(String str) throws Exception {
        return CertificateFactory.getInstance("X.509").generateCertificate(new FileInputStream(str));
    }

    public static String getHash(File file) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bArr = new byte[1024];
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        while (true) {
            int i = fileInputStream.read(bArr);
            if (i > 0) {
                messageDigest.update(bArr, 0, i);
            } else {
                fileInputStream.close();
                return bytesToHex(messageDigest.digest());
            }
        }
    }

    private static String bytesToHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            sb.append(String.format("%02x", new Integer(b & 255)));
        }
        return sb.toString();
    }

    public static Boolean checkSign(String str, String str2, PublicKey publicKey) throws Exception {
        try {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(publicKey);
            signature.update(str.getBytes(StandardCharsets.UTF_8));
            return Boolean.valueOf(signature.verify(Base64.decodeBase64(str2.getBytes())));
        } catch (Exception e) {
            throw e;
        }
    }
}
