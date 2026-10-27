package com.neusoft.na.common;

import android.content.Context;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.util.Log;
import cerence.ark.assistant.core.dcc.DccConstant;
import com.bydux.api.common.tools.util.ListUtils;
import com.neusoft.na.common.bean.SpaceInfo;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: classes2.dex */
public class Tool {
    private static final String TAG = "XJTool";
    public static Process p;

    public static List<String> getUSBPaths(Context context) {
        Log.i(TAG, "getUSBPaths: ");
        ArrayList arrayList = new ArrayList();
        StorageManager storageManager = (StorageManager) context.getSystemService("storage");
        try {
            for (String str : (String[]) StorageManager.class.getMethod("getVolumePaths", new Class[0]).invoke(storageManager, new Object[0])) {
                if (((String) StorageManager.class.getMethod("getVolumeState", String.class).invoke(storageManager, str)).equals("mounted") && !str.contains("emulated") && !str.contains("sdcard")) {
                    arrayList.add(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static boolean fileIsExists(String str) {
        Log.i(TAG, "fileIsExists: " + str);
        try {
            return new File(str).exists();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getFileDir(String str) {
        return new File(str).getParent();
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x0077, code lost:
    
        if (r3 == null) goto L58;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0079 A[PHI: r3 r5
  0x0079: PHI (r3v10 java.io.FileInputStream) = (r3v6 java.io.FileInputStream), (r3v8 java.io.FileInputStream), (r3v11 java.io.FileInputStream) binds: [B:48:0x0077, B:56:0x0088, B:30:0x0055] A[DONT_GENERATE, DONT_INLINE]
  0x0079: PHI (r5v11 'e' ??) = (r5v7 'e' ??), (r5v9 'e' ??), (r5v19 'e' ??) binds: [B:48:0x0077, B:56:0x0088, B:30:0x0055] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0098  */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v18, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r5v19 */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r5v20 */
    /* JADX WARN: Type inference failed for: r5v21, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r5v22, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v28 */
    /* JADX WARN: Type inference failed for: r5v29 */
    /* JADX WARN: Type inference failed for: r5v30 */
    /* JADX WARN: Type inference failed for: r5v31 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9, types: [java.io.InputStreamReader] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Boolean FileHasStr(java.lang.String r5, java.lang.String r6) throws java.lang.Throwable {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r5)
            boolean r5 = r0.isDirectory()
            java.lang.String r1 = "The File doesn't not exist."
            java.lang.String r2 = "TestFile"
            if (r5 == 0) goto L14
            android.util.Log.i(r2, r1)
            goto L8b
        L14:
            r5 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L6b java.io.FileNotFoundException -> L7f
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L6b java.io.FileNotFoundException -> L7f
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L63 java.io.FileNotFoundException -> L80
            r0.<init>(r3)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L63 java.io.FileNotFoundException -> L80
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L45
            r4.<init>(r0)     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L45
        L24:
            java.lang.String r5 = r4.readLine()     // Catch: java.io.IOException -> L3f java.lang.Throwable -> L59
            if (r5 == 0) goto L4d
            boolean r5 = r5.equals(r6)     // Catch: java.io.IOException -> L3f java.lang.Throwable -> L59
            if (r5 == 0) goto L24
            r5 = 1
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch: java.io.IOException -> L3f java.lang.Throwable -> L59
            r4.close()     // Catch: java.io.IOException -> L51 java.io.FileNotFoundException -> L53 java.lang.Throwable -> L7d
            r0.close()
            r3.close()
            return r5
        L3f:
            r5 = move-exception
            goto L48
        L41:
            r6 = move-exception
            r4 = r5
            r5 = r6
            goto L5a
        L45:
            r6 = move-exception
            r4 = r5
            r5 = r6
        L48:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L59
            if (r4 == 0) goto L55
        L4d:
            r4.close()     // Catch: java.io.IOException -> L51 java.io.FileNotFoundException -> L53 java.lang.Throwable -> L7d
            goto L55
        L51:
            r5 = move-exception
            goto L6f
        L53:
            r5 = r0
            goto L80
        L55:
            r0.close()
            goto L79
        L59:
            r5 = move-exception
        L5a:
            if (r4 == 0) goto L5f
            r4.close()     // Catch: java.io.IOException -> L51 java.io.FileNotFoundException -> L53 java.lang.Throwable -> L7d
        L5f:
            throw r5     // Catch: java.io.IOException -> L51 java.io.FileNotFoundException -> L53 java.lang.Throwable -> L7d
        L60:
            r6 = move-exception
            r0 = r5
            goto L69
        L63:
            r6 = move-exception
            r0 = r5
            goto L6e
        L66:
            r6 = move-exception
            r0 = r5
            r3 = r0
        L69:
            r5 = r6
            goto L91
        L6b:
            r6 = move-exception
            r0 = r5
            r3 = r0
        L6e:
            r5 = r6
        L6f:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L7d
            if (r0 == 0) goto L77
            r0.close()
        L77:
            if (r3 == 0) goto L8b
        L79:
            r3.close()
            goto L8b
        L7d:
            r5 = move-exception
            goto L91
        L7f:
            r3 = r5
        L80:
            android.util.Log.i(r2, r1)     // Catch: java.lang.Throwable -> L60
            if (r5 == 0) goto L88
            r5.close()
        L88:
            if (r3 == 0) goto L8b
            goto L79
        L8b:
            r5 = 0
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            return r5
        L91:
            if (r0 == 0) goto L96
            r0.close()
        L96:
            if (r3 == 0) goto L9b
            r3.close()
        L9b:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.common.Tool.FileHasStr(java.lang.String, java.lang.String):java.lang.Boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00c3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean writeToTxt(java.lang.String r10, java.lang.String r11, java.lang.String r12) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "content size == "
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "writeToTxt: path:"
            r1.<init>(r2)
            java.lang.StringBuilder r1 = r1.append(r10)
            java.lang.String r2 = "::name::"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r11)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "XJTool"
            android.util.Log.i(r2, r1)
            r1 = 0
            r3 = 0
            createDirectory(r10)     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Lab
            int r4 = r12.length()     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Lab
            long r4 = (long) r4     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Lab
            com.neusoft.na.common.bean.SpaceInfo r6 = getStorageSpace(r10)     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Lab
            long r6 = r6.getFree()     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Lab
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Lab
            r8.<init>(r0)     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Lab
            java.lang.StringBuilder r0 = r8.append(r4)     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Lab
            java.lang.String r8 = ", free == "
            java.lang.StringBuilder r0 = r0.append(r8)     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Lab
            java.lang.StringBuilder r0 = r0.append(r6)     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Lab
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Lab
            android.util.Log.i(r2, r0)     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Lab
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 < 0) goto L56
            java.lang.String r10 = "not enough disk space!"
            android.util.Log.i(r2, r10)     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Lab
            return r1
        L56:
            java.io.File r0 = new java.io.File     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Lab
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Lab
            r4.<init>()     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Lab
            java.lang.StringBuilder r10 = r4.append(r10)     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Lab
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Lab
            java.lang.String r10 = r10.toString()     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Lab
            r0.<init>(r10)     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Lab
            boolean r10 = r0.createNewFile()     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Lab
            if (r10 == 0) goto L77
            java.lang.String r10 = "File did not exist,and created successfully."
            android.util.Log.i(r2, r10)     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Lab
        L77:
            java.io.FileOutputStream r10 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Lab
            r10.<init>(r0)     // Catch: java.lang.Throwable -> La8 java.lang.Exception -> Lab
            java.io.BufferedWriter r11 = new java.io.BufferedWriter     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La3
            java.io.OutputStreamWriter r2 = new java.io.OutputStreamWriter     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La3
            r2.<init>(r10)     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La3
            r11.<init>(r2)     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La3
            r11.write(r12)     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> L99
            grantFileAuthority(r0)     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> L99
            r11.close()
            r10.close()
            r10 = 1
            return r10
        L94:
            r12 = move-exception
            r3 = r11
            r11 = r10
            r10 = r12
            goto Lbc
        L99:
            r12 = move-exception
            r3 = r11
            r11 = r10
            r10 = r12
            goto Lad
        L9e:
            r11 = move-exception
            r9 = r11
            r11 = r10
            r10 = r9
            goto Lbc
        La3:
            r11 = move-exception
            r9 = r11
            r11 = r10
            r10 = r9
            goto Lad
        La8:
            r10 = move-exception
            r11 = r3
            goto Lbc
        Lab:
            r10 = move-exception
            r11 = r3
        Lad:
            r10.printStackTrace()     // Catch: java.lang.Throwable -> Lbb
            if (r3 == 0) goto Lb5
            r3.close()
        Lb5:
            if (r11 == 0) goto Lba
            r11.close()
        Lba:
            return r1
        Lbb:
            r10 = move-exception
        Lbc:
            if (r3 == 0) goto Lc1
            r3.close()
        Lc1:
            if (r11 == 0) goto Lc6
            r11.close()
        Lc6:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.common.Tool.writeToTxt(java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    public static void createDirectory(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        grantDirAuthority(file);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0067 A[PHI: r0 r5 r6
  0x0067: PHI (r0v5 api.structs.com.neusoft.common.ReadFileStatus) = (r0v3 api.structs.com.neusoft.common.ReadFileStatus), (r0v9 api.structs.com.neusoft.common.ReadFileStatus) binds: [B:54:0x00a1, B:24:0x0064] A[DONT_GENERATE, DONT_INLINE]
  0x0067: PHI (r5v2 java.lang.String) = (r5v0 java.lang.String), (r5v4 java.lang.String) binds: [B:54:0x00a1, B:24:0x0064] A[DONT_GENERATE, DONT_INLINE]
  0x0067: PHI (r6v9 java.io.FileInputStream) = (r6v7 java.io.FileInputStream), (r6v10 java.io.FileInputStream) binds: [B:54:0x00a1, B:24:0x0064] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006f A[Catch: IOException -> 0x0073, FileNotFoundException -> 0x0075, all -> 0x0093, TRY_ENTER, TryCatch #8 {all -> 0x0093, blocks: (B:13:0x004c, B:43:0x0083, B:29:0x006f, B:30:0x0072), top: B:67:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00c7  */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v12 */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v23, types: [api.structs.com.neusoft.common.ReadFileStatus] */
    /* JADX WARN: Type inference failed for: r11v24, types: [api.structs.com.neusoft.common.ReadFileStatus] */
    /* JADX WARN: Type inference failed for: r11v25 */
    /* JADX WARN: Type inference failed for: r11v26 */
    /* JADX WARN: Type inference failed for: r11v27 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v4, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r11v7, types: [api.structs.com.neusoft.common.ReadFileStatus] */
    /* JADX WARN: Type inference failed for: r11v9, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static api.requests.com.neusoft.common.commonsysapiinverse.ReqOnReadFile ReadTxtFile(int r10, java.lang.String r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 203
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.common.Tool.ReadTxtFile(int, java.lang.String):api.requests.com.neusoft.common.commonsysapiinverse.ReqOnReadFile");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r1v2, types: [boolean] */
    public static void copyFile(String str, String str2) throws Throwable {
        ?? r0 = 0;
        fileOutputStream = null;
        FileOutputStream fileOutputStream = null;
        r0 = 0;
        r0 = 0;
        try {
            try {
                ?? Exists = new File(str).exists();
                if (Exists != 0) {
                    try {
                        FileInputStream fileInputStream = new FileInputStream(str);
                        try {
                            try {
                                FileOutputStream fileOutputStream2 = new FileOutputStream(str2);
                                try {
                                    byte[] bArr = new byte[1444];
                                    int i = 0;
                                    while (true) {
                                        int i2 = fileInputStream.read(bArr);
                                        if (i2 == -1) {
                                            break;
                                        }
                                        i += i2;
                                        System.out.println(i);
                                        fileOutputStream2.write(bArr, 0, i2);
                                    }
                                    fileOutputStream2.close();
                                } catch (IOException e) {
                                    e = e;
                                    fileOutputStream = fileOutputStream2;
                                    e.printStackTrace();
                                    if (fileOutputStream != null) {
                                        fileOutputStream.close();
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    fileOutputStream = fileOutputStream2;
                                    if (fileOutputStream != null) {
                                        fileOutputStream.close();
                                    }
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        } catch (IOException e2) {
                            e = e2;
                        }
                        r0 = fileInputStream;
                    } catch (Exception e3) {
                        e = e3;
                        r0 = Exists;
                        System.out.println("复制单个文件操作出错");
                        e.printStackTrace();
                        if (r0 == 0) {
                            return;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        r0 = Exists;
                        if (r0 != 0) {
                            r0.close();
                        }
                        throw th;
                    }
                }
                if (r0 == 0) {
                    return;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (Exception e4) {
            e = e4;
        }
        r0.close();
    }

    public static void copyFolder(String str, String str2) throws Throwable {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        File file;
        FileOutputStream fileOutputStream;
        IOException e;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                try {
                    new File(str2).mkdirs();
                    String[] list = new File(str).list();
                    fileInputStream2 = null;
                    for (int i = 0; i < list.length; i++) {
                        try {
                            if (str.endsWith(File.separator)) {
                                file = new File(str + list[i]);
                            } else {
                                file = new File(str + File.separator + list[i]);
                            }
                            if (file.isFile()) {
                                FileInputStream fileInputStream3 = new FileInputStream(file);
                                try {
                                    fileOutputStream = new FileOutputStream(str2 + "/" + file.getName().toString());
                                } catch (IOException e2) {
                                    fileOutputStream = fileOutputStream2;
                                    e = e2;
                                } catch (Exception e3) {
                                    e = e3;
                                } catch (Throwable th) {
                                    th = th;
                                }
                                try {
                                    try {
                                        byte[] bArr = new byte[5120];
                                        while (true) {
                                            int i2 = fileInputStream3.read(bArr);
                                            if (i2 == -1) {
                                                break;
                                            } else {
                                                fileOutputStream.write(bArr, 0, i2);
                                            }
                                        }
                                        fileOutputStream.flush();
                                    } catch (Exception e4) {
                                        e = e4;
                                        fileOutputStream2 = fileOutputStream;
                                        fileInputStream2 = fileInputStream3;
                                        System.out.println("复制整个文件夹内容操作出错");
                                        e.printStackTrace();
                                        if (fileOutputStream2 != null) {
                                            try {
                                                fileOutputStream2.close();
                                            } catch (IOException e5) {
                                                e5.printStackTrace();
                                            }
                                        }
                                        if (fileInputStream2 != null) {
                                            fileInputStream2.close();
                                        }
                                        return;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        fileOutputStream2 = fileOutputStream;
                                        fileInputStream = fileInputStream3;
                                        if (fileOutputStream2 != null) {
                                            try {
                                                fileOutputStream2.close();
                                            } catch (IOException e6) {
                                                e6.printStackTrace();
                                            }
                                        }
                                        if (fileInputStream != null) {
                                            try {
                                                fileInputStream.close();
                                                throw th;
                                            } catch (IOException e7) {
                                                e7.printStackTrace();
                                                throw th;
                                            }
                                        }
                                        throw th;
                                    }
                                } catch (IOException e8) {
                                    e = e8;
                                    e.printStackTrace();
                                }
                                fileOutputStream2 = fileOutputStream;
                                fileInputStream2 = fileInputStream3;
                            }
                            if (file.isDirectory()) {
                                copyFolder(str + "/" + list[i], str2 + "/" + list[i]);
                            }
                        } catch (Exception e9) {
                            e = e9;
                        }
                    }
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (IOException e10) {
                            e10.printStackTrace();
                        }
                    }
                } catch (Exception e11) {
                    e = e11;
                    fileInputStream2 = null;
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream = null;
                }
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (IOException e12) {
            e12.printStackTrace();
        }
    }

    public static boolean delete(String str) {
        File file = new File(str);
        if (file.exists()) {
            return file.isFile() ? deleteFile(str) : deleteDirectory(str);
        }
        return false;
    }

    public static boolean deleteFile(String str) {
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + str + "成功！");
                return true;
            }
            System.out.println("删除单个文件" + str + "失败！");
            return false;
        }
        System.out.println("删除单个文件失败：" + str + "不存在！");
        return false;
    }

    public static boolean deleteDirectory(String str) {
        if (!str.endsWith(File.separator)) {
            str = str + File.separator;
        }
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            System.out.println("删除目录失败：" + str + "不存在！");
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
        if (!zDeleteDirectory) {
            System.out.println("删除目录失败！");
            return false;
        }
        if (!file.delete()) {
            return false;
        }
        System.out.println("删除目录" + str + "成功！");
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0064, code lost:
    
        if (r8.startsWith("[") == false) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x006c, code lost:
    
        if (r8.endsWith("]") == false) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006e, code lost:
    
        r7 = r8.substring(1, r8.length() - 1).trim();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x007f, code lost:
    
        if (r6.equals(r7) != false) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0081, code lost:
    
        r0.put(r6, r12);
        r12 = (java.util.List) r0.get(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x008a, code lost:
    
        if (r12 != null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x008c, code lost:
    
        r12 = new java.util.ArrayList();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0091, code lost:
    
        r6 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0093, code lost:
    
        r12.add(r8);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c2  */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v20 */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Map<java.lang.String, java.lang.Object> readIni(java.io.InputStream r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 298
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.common.Tool.readIni(java.io.InputStream):java.util.Map");
    }

    private static String removeIniComments(String str) {
        if (str.contains(";")) {
            str = str.substring(0, str.indexOf(";"));
        }
        if (str.contains(DccConstant.DCC_SEPARATOR)) {
            str = str.substring(0, str.indexOf(DccConstant.DCC_SEPARATOR));
        }
        return str.trim();
    }

    public static void renameFile(String str, String str2) {
        if (!str.equals(str2)) {
            File file = new File(str);
            File file2 = new File(str2);
            if (file.exists()) {
                if (file2.exists()) {
                    System.out.println(str2 + "已经存在！");
                    return;
                } else {
                    if (file.renameTo(file2)) {
                        return;
                    }
                    Log.i(TAG, "Rename file did not success.");
                    return;
                }
            }
            return;
        }
        System.out.println("新文件名和旧文件名相同...");
    }

    public static void grantAuthority(File file) {
        try {
            Process processExec = Runtime.getRuntime().exec("chmod  777 " + file);
            p = processExec;
            if (processExec.waitFor() == 0) {
                Log.i(TAG, "chmod:" + file + "succeed");
            } else {
                Log.i(TAG, "chmod:" + file + "failed");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void grantFileAuthority(File file) {
        try {
            Process processExec = Runtime.getRuntime().exec("chmod  777 " + file);
            p = processExec;
            if (processExec.waitFor() == 0) {
                Log.i(TAG, "chmod:" + file + "succeed");
            } else {
                Log.i(TAG, "chmod:" + file + "failed");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void grantDirAuthority(File file) {
        Log.i(TAG, "grantDirAuthority:" + file);
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x015e: MOVE (r0 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:105:0x015e */
    public static void getSelfMaps(String str) throws Throwable {
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        IOException e;
        UnsupportedEncodingException e2;
        FileNotFoundException e3;
        BufferedReader bufferedReader2;
        Log.i(str, "start");
        BufferedReader bufferedReader3 = null;
        try {
            try {
                fileInputStream = new FileInputStream(new File("/proc/self/maps"));
                try {
                    inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                    try {
                        bufferedReader = new BufferedReader(inputStreamReader);
                        while (true) {
                            try {
                                String line = bufferedReader.readLine();
                                if (line == null) {
                                    break;
                                }
                                if (line.contains("libc++_shared.so") || line.contains("libcurl.so") || line.contains("libna_dbu_jni.so") || line.contains("libna_dbu_lib.a") || line.contains("libna_dbu_sh.so") || line.contains("libna_dr_lib.so") || line.contains("libna_dummy_deploy_sh.so") || line.contains("libna_dummy_sh.so") || line.contains("libna_dummy_trace_sh.so") || line.contains("libna_logging.so") || line.contains("libna_logging_lib.a") || line.contains("libna_onecore.so") || line.contains("libna_onecore_adaptors_json.so") || line.contains("libna_onecore_client_sh.so") || line.contains("libna_pos_jni.so") || line.contains("libna_pos_lib.so") || line.contains("libna_sens_jni.so") || line.contains("libna_sens_lib.so") || line.contains("libNdsLib.so") || line.contains("libokapi_bm25_sqlite_extension.so")) {
                                    Log.i(str, "getSelfMaps::" + line);
                                }
                            } catch (FileNotFoundException e4) {
                                e3 = e4;
                                e3.printStackTrace();
                                if (bufferedReader != null) {
                                    bufferedReader.close();
                                }
                                if (inputStreamReader != null) {
                                    inputStreamReader.close();
                                }
                                if (fileInputStream != null) {
                                }
                                Log.i(str, "end");
                            } catch (UnsupportedEncodingException e5) {
                                e2 = e5;
                                e2.printStackTrace();
                                if (bufferedReader != null) {
                                    bufferedReader.close();
                                }
                                if (inputStreamReader != null) {
                                    inputStreamReader.close();
                                }
                                if (fileInputStream != null) {
                                }
                                Log.i(str, "end");
                            } catch (IOException e6) {
                                e = e6;
                                e.printStackTrace();
                                if (bufferedReader != null) {
                                    bufferedReader.close();
                                }
                                if (inputStreamReader != null) {
                                    inputStreamReader.close();
                                }
                                if (fileInputStream != null) {
                                }
                                Log.i(str, "end");
                            }
                        }
                        bufferedReader.close();
                        inputStreamReader.close();
                    } catch (FileNotFoundException e7) {
                        bufferedReader = null;
                        e3 = e7;
                    } catch (UnsupportedEncodingException e8) {
                        bufferedReader = null;
                        e2 = e8;
                    } catch (IOException e9) {
                        bufferedReader = null;
                        e = e9;
                    } catch (Throwable th) {
                        th = th;
                        if (bufferedReader3 != null) {
                            bufferedReader3.close();
                        }
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e10) {
                    bufferedReader = null;
                    e3 = e10;
                    inputStreamReader = null;
                } catch (UnsupportedEncodingException e11) {
                    bufferedReader = null;
                    e2 = e11;
                    inputStreamReader = null;
                } catch (IOException e12) {
                    bufferedReader = null;
                    e = e12;
                    inputStreamReader = null;
                } catch (Throwable th2) {
                    th = th2;
                    inputStreamReader = null;
                }
            } catch (FileNotFoundException e13) {
                inputStreamReader = null;
                bufferedReader = null;
                e3 = e13;
                fileInputStream = null;
            } catch (UnsupportedEncodingException e14) {
                inputStreamReader = null;
                bufferedReader = null;
                e2 = e14;
                fileInputStream = null;
            } catch (IOException e15) {
                inputStreamReader = null;
                bufferedReader = null;
                e = e15;
                fileInputStream = null;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
                inputStreamReader = null;
            }
            fileInputStream.close();
            Log.i(str, "end");
        } catch (Throwable th4) {
            th = th4;
            bufferedReader3 = bufferedReader2;
        }
    }

    public static SpaceInfo getStorageSpace(String str) {
        Log.i(TAG, "getStorageSpace:" + str);
        SpaceInfo spaceInfo = new SpaceInfo();
        File file = new File(str);
        if (!file.exists()) {
            return spaceInfo;
        }
        StatFs statFs = new StatFs(str);
        long blockSizeLong = statFs.getBlockSizeLong();
        long blockCountLong = statFs.getBlockCountLong();
        long availableBlocksLong = statFs.getAvailableBlocksLong();
        Log.i(TAG, "getStorageSpace: " + blockSizeLong + ListUtils.DEFAULT_JOIN_SEPARATOR + blockCountLong + ListUtils.DEFAULT_JOIN_SEPARATOR + availableBlocksLong);
        spaceInfo.setPartitionName(str);
        spaceInfo.setCapacity(blockCountLong * blockSizeLong);
        spaceInfo.setFree(availableBlocksLong * blockSizeLong);
        spaceInfo.setAvailable(getDirSize(file));
        return spaceInfo;
    }

    private static long getDirSize(File file) {
        long dirSize = 0;
        if (file == null) {
            return 0L;
        }
        if (file.isFile()) {
            return file.length();
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                dirSize += getDirSize(file2);
            }
        }
        return dirSize;
    }

    public static void upZipFile(File file, String str) throws Throwable {
        FileOutputStream fileOutputStream;
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        ZipFile zipFile = null;
        FileOutputStream fileOutputStream2 = null;
        zipFile = null;
        zipFile = null;
        try {
            try {
                ZipFile zipFile2 = new ZipFile(file);
                try {
                    Enumeration<? extends ZipEntry> enumerationEntries = zipFile2.entries();
                    while (enumerationEntries.hasMoreElements()) {
                        ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                        InputStream inputStream = zipFile2.getInputStream(zipEntryNextElement);
                        File file3 = new File(str, URLEncoder.encode(zipEntryNextElement.getName(), "UTF-8"));
                        if (!file3.exists()) {
                            File parentFile = file3.getParentFile();
                            if (!parentFile.exists()) {
                                parentFile.mkdirs();
                            }
                        }
                        try {
                            fileOutputStream = new FileOutputStream(file3);
                        } catch (IOException e) {
                            e = e;
                            fileOutputStream = null;
                        } catch (Throwable th) {
                            th = th;
                        }
                        try {
                            try {
                                byte[] bArr = new byte[1048576];
                                for (int i = inputStream.read(bArr); i != -1; i = inputStream.read(bArr)) {
                                    fileOutputStream.write(bArr, 0, i);
                                }
                                fileOutputStream.close();
                            } catch (IOException e2) {
                                e = e2;
                                e.printStackTrace();
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                                if (inputStream != null) {
                                }
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            fileOutputStream2 = fileOutputStream;
                            if (fileOutputStream2 != null) {
                                fileOutputStream2.close();
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            throw th;
                        }
                    }
                    zipFile2.close();
                } catch (IOException e3) {
                    e = e3;
                    zipFile = zipFile2;
                    e.printStackTrace();
                    if (zipFile != null) {
                        zipFile.close();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    zipFile = zipFile2;
                    if (zipFile != null) {
                        zipFile.close();
                    }
                    throw th;
                }
            } catch (IOException e4) {
                e = e4;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    public static void hookWebView() {
        try {
            Class<?> cls = Class.forName("android.webkit.WebViewFactory");
            Field declaredField = cls.getDeclaredField("sProviderInstance");
            declaredField.setAccessible(true);
            Object objInvoke = declaredField.get(null);
            if (objInvoke != null) {
                Log.i(TAG, "sProviderInstance isn't null");
                return;
            }
            Method declaredMethod = cls.getDeclaredMethod("getProviderClass", new Class[0]);
            declaredMethod.setAccessible(true);
            Class cls2 = (Class) declaredMethod.invoke(cls, new Object[0]);
            Class<?> cls3 = Class.forName("android.webkit.WebViewDelegate");
            Constructor<?> declaredConstructor = cls3.getDeclaredConstructor(new Class[0]);
            declaredConstructor.setAccessible(true);
            Field declaredField2 = cls.getDeclaredField("CHROMIUM_WEBVIEW_FACTORY_METHOD");
            declaredField2.setAccessible(true);
            String str = (String) declaredField2.get(null);
            if (str == null) {
                str = "create";
            }
            Method method = cls2.getMethod(str, cls3);
            if (method != null) {
                objInvoke = method.invoke(null, declaredConstructor.newInstance(new Object[0]));
            }
            if (objInvoke != null) {
                declaredField.set("sProviderInstance", objInvoke);
                Log.i(TAG, "Hook success!");
            } else {
                Log.i(TAG, "Hook failed!");
            }
        } catch (Throwable th) {
            Log.w(TAG, th);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x0130 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0145 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0125 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x013a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0133 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:148:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getPidCPURateDesc(java.lang.String r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 388
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.common.Tool.getPidCPURateDesc(java.lang.String):java.lang.String");
    }

    public static String getAssetPath(Context context, String str) {
        String str2;
        Log.i(TAG, "getAssetPath:name" + str);
        try {
            str2 = new String(InputStreamToByte(context.getClass().getResourceAsStream("/assets/" + str)));
        } catch (IOException e) {
            e.printStackTrace();
            str2 = "";
        }
        Log.i(TAG, "getAssetPath:path:".concat(str2));
        return str2;
    }

    private static byte[] InputStreamToByte(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int i = inputStream.read();
            if (i != -1) {
                byteArrayOutputStream.write(i);
            } else {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            }
        }
    }
}
