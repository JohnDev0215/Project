package com.neusoft.na.ocservice.util;

import android.content.Context;
import android.os.storage.StorageManager;
import android.util.Log;
import cerence.ark.assistant.core.dcc.DccConstant;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class Tool {
    private static final String TAG = "XJTool";

    public static List<String> getUSBPaths(Context context) {
        Log.i(TAG, "getUSBPaths: ");
        ArrayList arrayList = new ArrayList();
        StorageManager storageManager = (StorageManager) context.getSystemService("storage");
        try {
            for (String str : (String[]) StorageManager.class.getMethod("getVolumePaths", new Class[0]).invoke(storageManager, new Object[0])) {
                if (((String) StorageManager.class.getMethod("getVolumeState", String.class).invoke(storageManager, str)).equals("mounted") && !str.contains("emulated")) {
                    Log.i(TAG, "getUSBPaths: " + str);
                    arrayList.add(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static boolean fileIsExists(String str) {
        Log.i(TAG, "fileIsExists: ");
        try {
            return new File(str).exists();
        } catch (Exception unused) {
            return false;
        }
    }

    public static Boolean FileHasStr(String str, String str2) throws Throwable {
        BufferedReader bufferedReader;
        String line;
        Log.i(TAG, "FileHasStr: strFilePath::" + str + "::str::" + str2);
        File file = new File(str);
        if (file.isDirectory()) {
            Log.i("TestFile", "The File doesn't not exist.");
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader2 = null;
                BufferedReader bufferedReader3 = null;
                try {
                    try {
                        bufferedReader = new BufferedReader(inputStreamReader);
                    } catch (IOException e) {
                        e = e;
                    }
                    do {
                        try {
                            line = bufferedReader.readLine();
                            if (line == null) {
                                bufferedReader.close();
                                inputStreamReader.close();
                                bufferedReader2 = line;
                            }
                        } catch (IOException e2) {
                            e = e2;
                            bufferedReader3 = bufferedReader;
                            e.printStackTrace();
                            if (bufferedReader3 != null) {
                                bufferedReader3.close();
                            }
                            inputStreamReader.close();
                            bufferedReader2 = bufferedReader3;
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader2 = bufferedReader;
                            if (bufferedReader2 != null) {
                                bufferedReader2.close();
                            }
                            inputStreamReader.close();
                            fileInputStream.close();
                            throw th;
                        }
                        fileInputStream.close();
                    } while (!line.equals(str2));
                    Log.i("TestFile", "has " + str2);
                    bufferedReader.close();
                    inputStreamReader.close();
                    fileInputStream.close();
                    return true;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (FileNotFoundException unused) {
                Log.i("TestFile", "The File doesn't not exist.");
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [java.io.BufferedWriter] */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r3v7, types: [java.io.FileOutputStream, java.io.OutputStream] */
    public static boolean writeToTxt(String str, String str2, String str3) throws Throwable {
        Throwable th;
        BufferedWriter bufferedWriter;
        IOException e;
        Log.i(TAG, "writeToTxt: path:" + str + "::name::" + ((String) str2) + "::content" + str3);
        createDirectory(str);
        ?? file = new File(str + ((String) str2));
        try {
            try {
                file.createNewFile();
                str2 = new FileOutputStream((File) file);
            } catch (IOException e2) {
                bufferedWriter = null;
                e = e2;
                str2 = 0;
            } catch (Throwable th2) {
                file = 0;
                th = th2;
                str2 = 0;
            }
            try {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(str2));
            } catch (IOException e3) {
                bufferedWriter = null;
                e = e3;
            } catch (Throwable th3) {
                file = 0;
                th = th3;
                if (file != 0) {
                    file.close();
                }
                if (str2 != 0) {
                    str2.close();
                }
                throw th;
            }
            try {
                bufferedWriter.write(str3);
                bufferedWriter.close();
                str2.close();
                return true;
            } catch (IOException e4) {
                e = e4;
                e.printStackTrace();
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (str2 == 0) {
                    return false;
                }
                str2.close();
                return false;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    public static void createDirectory(String str) {
        File file = new File(str);
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0068 A[Catch: IOException -> 0x0072, FileNotFoundException -> 0x007b, TryCatch #5 {FileNotFoundException -> 0x007b, IOException -> 0x0072, blocks: (B:5:0x0016, B:12:0x0044, B:13:0x004a, B:23:0x005e, B:24:0x0061, B:27:0x0068, B:28:0x006b, B:29:0x0071), top: B:38:0x0016 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String ReadTxtFile(java.lang.String r8) throws java.lang.Throwable {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r8)
            boolean r8 = r0.isDirectory()
            java.lang.String r1 = "The File doesn't not exist."
            java.lang.String r2 = "TestFile"
            java.lang.String r3 = ""
            if (r8 == 0) goto L16
            android.util.Log.i(r2, r1)
            goto L7e
        L16:
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch: java.io.IOException -> L72 java.io.FileNotFoundException -> L7b
            r8.<init>(r0)     // Catch: java.io.IOException -> L72 java.io.FileNotFoundException -> L7b
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch: java.io.IOException -> L72 java.io.FileNotFoundException -> L7b
            r0.<init>(r8)     // Catch: java.io.IOException -> L72 java.io.FileNotFoundException -> L7b
            r4 = 0
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L50 java.io.IOException -> L55
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L50 java.io.IOException -> L55
        L26:
            java.lang.String r4 = r5.readLine()     // Catch: java.io.IOException -> L4e java.lang.Throwable -> L65
            if (r4 == 0) goto L44
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L4e java.lang.Throwable -> L65
            r6.<init>()     // Catch: java.io.IOException -> L4e java.lang.Throwable -> L65
            java.lang.StringBuilder r6 = r6.append(r3)     // Catch: java.io.IOException -> L4e java.lang.Throwable -> L65
            java.lang.StringBuilder r4 = r6.append(r4)     // Catch: java.io.IOException -> L4e java.lang.Throwable -> L65
            java.lang.String r6 = "\n"
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch: java.io.IOException -> L4e java.lang.Throwable -> L65
            java.lang.String r3 = r4.toString()     // Catch: java.io.IOException -> L4e java.lang.Throwable -> L65
            goto L26
        L44:
            r5.close()     // Catch: java.io.IOException -> L72 java.io.FileNotFoundException -> L7b
            r0.close()     // Catch: java.io.IOException -> L72 java.io.FileNotFoundException -> L7b
        L4a:
            r8.close()     // Catch: java.io.IOException -> L72 java.io.FileNotFoundException -> L7b
            goto L7e
        L4e:
            r4 = move-exception
            goto L59
        L50:
            r5 = move-exception
            r7 = r5
            r5 = r4
            r4 = r7
            goto L66
        L55:
            r5 = move-exception
            r7 = r5
            r5 = r4
            r4 = r7
        L59:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L65
            if (r5 == 0) goto L61
            r5.close()     // Catch: java.io.IOException -> L72 java.io.FileNotFoundException -> L7b
        L61:
            r0.close()     // Catch: java.io.IOException -> L72 java.io.FileNotFoundException -> L7b
            goto L4a
        L65:
            r4 = move-exception
        L66:
            if (r5 == 0) goto L6b
            r5.close()     // Catch: java.io.IOException -> L72 java.io.FileNotFoundException -> L7b
        L6b:
            r0.close()     // Catch: java.io.IOException -> L72 java.io.FileNotFoundException -> L7b
            r8.close()     // Catch: java.io.IOException -> L72 java.io.FileNotFoundException -> L7b
            throw r4     // Catch: java.io.IOException -> L72 java.io.FileNotFoundException -> L7b
        L72:
            r8 = move-exception
            java.lang.String r8 = r8.getMessage()
            android.util.Log.i(r2, r8)
            goto L7e
        L7b:
            android.util.Log.i(r2, r1)
        L7e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.ocservice.util.Tool.ReadTxtFile(java.lang.String):java.lang.String");
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
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.ocservice.util.Tool.readIni(java.io.InputStream):java.util.Map");
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
}
