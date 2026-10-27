package com.blankj.utilcode.util;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class FileIOUtils {
    private static int sBufferSize = 524288;

    public interface OnProgressUpdateListener {
        void onProgressUpdate(double d);
    }

    private FileIOUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
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

    public static boolean writeFileFromIS(File file, InputStream inputStream, boolean z, OnProgressUpdateListener onProgressUpdateListener) throws Throwable {
        if (inputStream == null || !createOrExistsFile(file)) {
            return false;
        }
        BufferedOutputStream bufferedOutputStream = null;
        try {
            try {
                BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file, z), sBufferSize);
                try {
                    if (onProgressUpdateListener == null) {
                        byte[] bArr = new byte[sBufferSize];
                        while (true) {
                            int i = inputStream.read(bArr);
                            if (i == -1) {
                                break;
                            }
                            bufferedOutputStream2.write(bArr, 0, i);
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
                            bufferedOutputStream2.write(bArr2, 0, i3);
                            i2 += i3;
                            onProgressUpdateListener.onProgressUpdate(((double) i2) / dAvailable);
                        }
                    }
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        bufferedOutputStream2.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    return true;
                } catch (IOException e3) {
                    e = e3;
                    bufferedOutputStream = bufferedOutputStream2;
                    e.printStackTrace();
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    bufferedOutputStream = bufferedOutputStream2;
                    try {
                        inputStream.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
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
            }
        } catch (Throwable th2) {
            th = th2;
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

    public static boolean writeFileFromBytesByChannel(String str, byte[] bArr, boolean z) {
        return writeFileFromBytesByChannel(getFileByPath(str), bArr, false, z);
    }

    public static boolean writeFileFromBytesByChannel(String str, byte[] bArr, boolean z, boolean z2) {
        return writeFileFromBytesByChannel(getFileByPath(str), bArr, z, z2);
    }

    public static boolean writeFileFromBytesByChannel(File file, byte[] bArr, boolean z) {
        return writeFileFromBytesByChannel(file, bArr, false, z);
    }

    public static boolean writeFileFromBytesByChannel(File file, byte[] bArr, boolean z, boolean z2) {
        if (bArr == null || !createOrExistsFile(file)) {
            return false;
        }
        FileChannel channel = null;
        try {
            try {
                channel = new FileOutputStream(file, z).getChannel();
                channel.position(channel.size());
                channel.write(ByteBuffer.wrap(bArr));
                if (z2) {
                    channel.force(true);
                }
                if (channel != null) {
                    try {
                        channel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return true;
            } catch (IOException e2) {
                e2.printStackTrace();
                if (channel != null) {
                    try {
                        channel.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                return false;
            }
        } catch (Throwable th) {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static boolean writeFileFromBytesByMap(String str, byte[] bArr, boolean z) {
        return writeFileFromBytesByMap(str, bArr, false, z);
    }

    public static boolean writeFileFromBytesByMap(String str, byte[] bArr, boolean z, boolean z2) {
        return writeFileFromBytesByMap(getFileByPath(str), bArr, z, z2);
    }

    public static boolean writeFileFromBytesByMap(File file, byte[] bArr, boolean z) {
        return writeFileFromBytesByMap(file, bArr, false, z);
    }

    public static boolean writeFileFromBytesByMap(File file, byte[] bArr, boolean z, boolean z2) {
        if (bArr == null || !createOrExistsFile(file)) {
            return false;
        }
        FileChannel channel = null;
        try {
            try {
                channel = new FileOutputStream(file, z).getChannel();
                MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, channel.size(), bArr.length);
                map.put(bArr);
                if (z2) {
                    map.force();
                }
                if (channel != null) {
                    try {
                        channel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return true;
            } catch (IOException e2) {
                e2.printStackTrace();
                if (channel != null) {
                    try {
                        channel.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                return false;
            }
        } catch (Throwable th) {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
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

    public static boolean writeFileFromString(File file, String str, boolean z) throws Throwable {
        if (file == null || str == null || !createOrExistsFile(file)) {
            return false;
        }
        BufferedWriter bufferedWriter = null;
        try {
            try {
                BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file, z));
                try {
                    bufferedWriter2.write(str);
                    try {
                        bufferedWriter2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return true;
                } catch (IOException e2) {
                    e = e2;
                    bufferedWriter = bufferedWriter2;
                    e.printStackTrace();
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    bufferedWriter = bufferedWriter2;
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static List<String> readFile2List(String str) {
        return readFile2List(getFileByPath(str), (String) null);
    }

    public static List<String> readFile2List(String str, String str2) {
        return readFile2List(getFileByPath(str), str2);
    }

    public static List<String> readFile2List(File file) {
        return readFile2List(file, 0, Integer.MAX_VALUE, (String) null);
    }

    public static List<String> readFile2List(File file, String str) {
        return readFile2List(file, 0, Integer.MAX_VALUE, str);
    }

    public static List<String> readFile2List(String str, int i, int i2) {
        return readFile2List(getFileByPath(str), i, i2, (String) null);
    }

    public static List<String> readFile2List(String str, int i, int i2, String str2) {
        return readFile2List(getFileByPath(str), i, i2, str2);
    }

    public static List<String> readFile2List(File file, int i, int i2) {
        return readFile2List(file, i, i2, (String) null);
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x006b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<java.lang.String> readFile2List(java.io.File r6, int r7, int r8, java.lang.String r9) throws java.lang.Throwable {
        /*
            boolean r0 = isFileExists(r6)
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            if (r7 <= r8) goto Lb
            return r1
        Lb:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            r0.<init>()     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            boolean r2 = isSpace(r9)     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            r3 = 1
            if (r2 == 0) goto L27
            java.io.BufferedReader r9 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            r4.<init>(r6)     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            r9.<init>(r2)     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            goto L37
        L27:
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            r4.<init>(r5, r9)     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            r9 = r2
        L37:
            java.lang.String r6 = r9.readLine()     // Catch: java.io.IOException -> L53 java.lang.Throwable -> L67
            if (r6 == 0) goto L4a
            if (r3 <= r8) goto L40
            goto L4a
        L40:
            if (r7 > r3) goto L47
            if (r3 > r8) goto L47
            r0.add(r6)     // Catch: java.io.IOException -> L53 java.lang.Throwable -> L67
        L47:
            int r3 = r3 + 1
            goto L37
        L4a:
            r9.close()     // Catch: java.io.IOException -> L4e
            goto L52
        L4e:
            r6 = move-exception
            r6.printStackTrace()
        L52:
            return r0
        L53:
            r6 = move-exception
            goto L59
        L55:
            r6 = move-exception
            goto L69
        L57:
            r6 = move-exception
            r9 = r1
        L59:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L67
            if (r9 == 0) goto L66
            r9.close()     // Catch: java.io.IOException -> L62
            goto L66
        L62:
            r6 = move-exception
            r6.printStackTrace()
        L66:
            return r1
        L67:
            r6 = move-exception
            r1 = r9
        L69:
            if (r1 == 0) goto L73
            r1.close()     // Catch: java.io.IOException -> L6f
            goto L73
        L6f:
            r7 = move-exception
            r7.printStackTrace()
        L73:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.FileIOUtils.readFile2List(java.io.File, int, int, java.lang.String):java.util.List");
    }

    public static String readFile2String(String str) {
        return readFile2String(getFileByPath(str), (String) null);
    }

    public static String readFile2String(String str, String str2) {
        return readFile2String(getFileByPath(str), str2);
    }

    public static String readFile2String(File file) {
        return readFile2String(file, (String) null);
    }

    public static String readFile2String(File file, String str) {
        byte[] file2BytesByStream = readFile2BytesByStream(file);
        if (file2BytesByStream == null) {
            return null;
        }
        if (isSpace(str)) {
            return new String(file2BytesByStream);
        }
        try {
            return new String(file2BytesByStream, str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static byte[] readFile2BytesByStream(String str) {
        return readFile2BytesByStream(getFileByPath(str), (OnProgressUpdateListener) null);
    }

    public static byte[] readFile2BytesByStream(File file) {
        return readFile2BytesByStream(file, (OnProgressUpdateListener) null);
    }

    public static byte[] readFile2BytesByStream(String str, OnProgressUpdateListener onProgressUpdateListener) {
        return readFile2BytesByStream(getFileByPath(str));
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x0087 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:77:? A[Catch: FileNotFoundException -> 0x0090, SYNTHETIC, TRY_LEAVE, TryCatch #0 {FileNotFoundException -> 0x0090, blocks: (B:5:0x0008, B:17:0x004e, B:21:0x0056, B:24:0x005b, B:20:0x0053, B:33:0x0069, B:38:0x0073, B:41:0x0078, B:36:0x006e, B:44:0x007d, B:49:0x0087, B:53:0x008f, B:52:0x008c, B:47:0x0082), top: B:57:0x0008, inners: #1, #3, #5, #6, #8, #10 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] readFile2BytesByStream(java.io.File r10, com.blankj.utilcode.util.FileIOUtils.OnProgressUpdateListener r11) throws java.lang.Throwable {
        /*
            boolean r0 = isFileExists(r10)
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch: java.io.FileNotFoundException -> L90
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.io.FileNotFoundException -> L90
            r2.<init>(r10)     // Catch: java.io.FileNotFoundException -> L90
            int r10 = com.blankj.utilcode.util.FileIOUtils.sBufferSize     // Catch: java.io.FileNotFoundException -> L90
            r0.<init>(r2, r10)     // Catch: java.io.FileNotFoundException -> L90
            java.io.ByteArrayOutputStream r10 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L64
            r10.<init>()     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L64
            int r2 = com.blankj.utilcode.util.FileIOUtils.sBufferSize     // Catch: java.io.IOException -> L5f java.lang.Throwable -> L7c
            byte[] r2 = new byte[r2]     // Catch: java.io.IOException -> L5f java.lang.Throwable -> L7c
            r3 = -1
            r4 = 0
            if (r11 != 0) goto L2d
        L21:
            int r11 = com.blankj.utilcode.util.FileIOUtils.sBufferSize     // Catch: java.io.IOException -> L5f java.lang.Throwable -> L7c
            int r11 = r0.read(r2, r4, r11)     // Catch: java.io.IOException -> L5f java.lang.Throwable -> L7c
            if (r11 == r3) goto L4a
            r10.write(r2, r4, r11)     // Catch: java.io.IOException -> L5f java.lang.Throwable -> L7c
            goto L21
        L2d:
            int r5 = r0.available()     // Catch: java.io.IOException -> L5f java.lang.Throwable -> L7c
            double r5 = (double) r5     // Catch: java.io.IOException -> L5f java.lang.Throwable -> L7c
            r7 = 0
            r11.onProgressUpdate(r7)     // Catch: java.io.IOException -> L5f java.lang.Throwable -> L7c
            r7 = r4
        L38:
            int r8 = com.blankj.utilcode.util.FileIOUtils.sBufferSize     // Catch: java.io.IOException -> L5f java.lang.Throwable -> L7c
            int r8 = r0.read(r2, r4, r8)     // Catch: java.io.IOException -> L5f java.lang.Throwable -> L7c
            if (r8 == r3) goto L4a
            r10.write(r2, r4, r8)     // Catch: java.io.IOException -> L5f java.lang.Throwable -> L7c
            int r7 = r7 + r8
            double r8 = (double) r7     // Catch: java.io.IOException -> L5f java.lang.Throwable -> L7c
            double r8 = r8 / r5
            r11.onProgressUpdate(r8)     // Catch: java.io.IOException -> L5f java.lang.Throwable -> L7c
            goto L38
        L4a:
            byte[] r11 = r10.toByteArray()     // Catch: java.io.IOException -> L5f java.lang.Throwable -> L7c
            r0.close()     // Catch: java.io.IOException -> L52 java.io.FileNotFoundException -> L90
            goto L56
        L52:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.io.FileNotFoundException -> L90
        L56:
            r10.close()     // Catch: java.io.IOException -> L5a java.io.FileNotFoundException -> L90
            goto L5e
        L5a:
            r10 = move-exception
            r10.printStackTrace()     // Catch: java.io.FileNotFoundException -> L90
        L5e:
            return r11
        L5f:
            r11 = move-exception
            goto L66
        L61:
            r11 = move-exception
            r10 = r1
            goto L7d
        L64:
            r11 = move-exception
            r10 = r1
        L66:
            r11.printStackTrace()     // Catch: java.lang.Throwable -> L7c
            r0.close()     // Catch: java.io.IOException -> L6d java.io.FileNotFoundException -> L90
            goto L71
        L6d:
            r11 = move-exception
            r11.printStackTrace()     // Catch: java.io.FileNotFoundException -> L90
        L71:
            if (r10 == 0) goto L7b
            r10.close()     // Catch: java.io.IOException -> L77 java.io.FileNotFoundException -> L90
            goto L7b
        L77:
            r10 = move-exception
            r10.printStackTrace()     // Catch: java.io.FileNotFoundException -> L90
        L7b:
            return r1
        L7c:
            r11 = move-exception
        L7d:
            r0.close()     // Catch: java.io.IOException -> L81 java.io.FileNotFoundException -> L90
            goto L85
        L81:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.io.FileNotFoundException -> L90
        L85:
            if (r10 == 0) goto L8f
            r10.close()     // Catch: java.io.IOException -> L8b java.io.FileNotFoundException -> L90
            goto L8f
        L8b:
            r10 = move-exception
            r10.printStackTrace()     // Catch: java.io.FileNotFoundException -> L90
        L8f:
            throw r11     // Catch: java.io.FileNotFoundException -> L90
        L90:
            r10 = move-exception
            r10.printStackTrace()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.FileIOUtils.readFile2BytesByStream(java.io.File, com.blankj.utilcode.util.FileIOUtils$OnProgressUpdateListener):byte[]");
    }

    public static byte[] readFile2BytesByChannel(String str) {
        return readFile2BytesByChannel(getFileByPath(str));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static byte[] readFile2BytesByChannel(File file) throws Throwable {
        FileChannel channel;
        FileChannel fileChannel = 0;
        try {
            if (!isFileExists(file)) {
                return null;
            }
            try {
                channel = new RandomAccessFile(file, "r").getChannel();
            } catch (IOException e) {
                e = e;
                channel = null;
            } catch (Throwable th) {
                th = th;
                if (fileChannel != 0) {
                    try {
                        fileChannel.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                throw th;
            }
            try {
                ByteBuffer byteBufferAllocate = ByteBuffer.allocate((int) channel.size());
                while (channel.read(byteBufferAllocate) > 0) {
                }
                byte[] bArrArray = byteBufferAllocate.array();
                if (channel != null) {
                    try {
                        channel.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                return bArrArray;
            } catch (IOException e4) {
                e = e4;
                e.printStackTrace();
                if (channel != null) {
                    try {
                        channel.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            fileChannel = file;
        }
    }

    public static byte[] readFile2BytesByMap(String str) {
        return readFile2BytesByMap(getFileByPath(str));
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0050 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] readFile2BytesByMap(java.io.File r9) throws java.lang.Throwable {
        /*
            boolean r0 = isFileExists(r9)
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            java.io.RandomAccessFile r0 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3c
            java.lang.String r2 = "r"
            r0.<init>(r9, r2)     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3c
            java.nio.channels.FileChannel r9 = r0.getChannel()     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3c
            long r2 = r9.size()     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4c
            int r0 = (int) r2     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4c
            java.nio.channels.FileChannel$MapMode r4 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4c
            r5 = 0
            long r7 = (long) r0     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4c
            r3 = r9
            java.nio.MappedByteBuffer r2 = r3.map(r4, r5, r7)     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4c
            java.nio.MappedByteBuffer r2 = r2.load()     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4c
            byte[] r3 = new byte[r0]     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4c
            r4 = 0
            r2.get(r3, r4, r0)     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4c
            if (r9 == 0) goto L37
            r9.close()     // Catch: java.io.IOException -> L33
            goto L37
        L33:
            r9 = move-exception
            r9.printStackTrace()
        L37:
            return r3
        L38:
            r0 = move-exception
            goto L3e
        L3a:
            r0 = move-exception
            goto L4e
        L3c:
            r0 = move-exception
            r9 = r1
        L3e:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L4c
            if (r9 == 0) goto L4b
            r9.close()     // Catch: java.io.IOException -> L47
            goto L4b
        L47:
            r9 = move-exception
            r9.printStackTrace()
        L4b:
            return r1
        L4c:
            r0 = move-exception
            r1 = r9
        L4e:
            if (r1 == 0) goto L58
            r1.close()     // Catch: java.io.IOException -> L54
            goto L58
        L54:
            r9 = move-exception
            r9.printStackTrace()
        L58:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.FileIOUtils.readFile2BytesByMap(java.io.File):byte[]");
    }

    public static void setBufferSize(int i) {
        sBufferSize = i;
    }

    private static File getFileByPath(String str) {
        if (isSpace(str)) {
            return null;
        }
        return new File(str);
    }

    private static boolean createOrExistsFile(String str) {
        return createOrExistsFile(getFileByPath(str));
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

    private static boolean isFileExists(File file) {
        return file != null && file.exists();
    }

    private static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static byte[] is2Bytes(InputStream inputStream) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        byte[] bArr;
        if (inputStream == null) {
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (IOException e) {
            e = e;
            byteArrayOutputStream = null;
        } catch (Throwable th2) {
            byteArrayOutputStream = null;
            th = th2;
        }
        try {
            try {
                bArr = new byte[sBufferSize];
            } catch (Throwable th3) {
                th = th3;
            }
            while (true) {
                int i = inputStream.read(bArr, 0, sBufferSize);
                if (i == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, i);
                th = th3;
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                        throw th;
                    } catch (IOException e3) {
                        e3.printStackTrace();
                        throw th;
                    }
                }
                throw th;
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                inputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            try {
                byteArrayOutputStream.close();
            } catch (IOException e5) {
                e5.printStackTrace();
            }
            return byteArray;
        } catch (IOException e6) {
            e = e6;
            e.printStackTrace();
            try {
                inputStream.close();
            } catch (IOException e7) {
                e7.printStackTrace();
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e8) {
                    e8.printStackTrace();
                }
            }
            return null;
        }
    }
}
