package org.xwalk.core;

import SevenZip.Compression.LZMA.Decoder;
import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: classes4.dex */
class XWalkDecompressor {
    private static final int LZMA_OUTSIZE = 8;
    private static final int LZMA_PROP_SIZE = 5;
    private static final String[] MANDATORY_LIBRARIES = {"libxwalkcore.so"};
    private static final String[] MANDATORY_RESOURCES = {"libxwalkcore.so", "classes.dex", "icudtl.dat", "xwalk.pak", "xwalk_100_percent.pak"};
    private static final int STREAM_BUFFER_SIZE = 4096;
    private static final String TAG = "XWalkLib";

    XWalkDecompressor() {
    }

    public static boolean isLibraryCompressed() {
        for (String str : MANDATORY_LIBRARIES) {
            try {
                try {
                    openRawResource(str).close();
                } catch (IOException unused) {
                }
            } catch (Resources.NotFoundException unused2) {
                return false;
            }
        }
        return true;
    }

    public static boolean decompressLibrary() throws Throwable {
        String privateDataDir = XWalkEnvironment.getPrivateDataDir();
        File file = new File(privateDataDir);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
        if (!file.exists() && !file.mkdirs()) {
            return false;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        for (String str : MANDATORY_LIBRARIES) {
            try {
                Log.d(TAG, "Decompressing " + str);
                extractLzmaToFile(openRawResource(str), new File(privateDataDir, str));
            } catch (Resources.NotFoundException unused) {
                Log.d(TAG, str + " not found");
                return false;
            } catch (IOException e) {
                Log.e(TAG, e.getLocalizedMessage());
                return false;
            }
        }
        Log.d(TAG, "Time to decompress : " + (SystemClock.uptimeMillis() - jUptimeMillis) + " ms");
        return true;
    }

    public static boolean isResourceCompressed(String str) throws Throwable {
        ZipFile zipFile;
        ZipFile zipFile2 = null;
        try {
            zipFile = new ZipFile(str);
        } catch (IOException unused) {
        } catch (Throwable th) {
            th = th;
        }
        try {
            for (String str2 : MANDATORY_RESOURCES) {
                if (zipFile.getEntry("assets" + File.separator + str2 + ".lzma") == null) {
                    try {
                        zipFile.close();
                    } catch (IOException | NullPointerException unused2) {
                    }
                    return false;
                }
            }
            try {
                zipFile.close();
                return true;
            } catch (IOException | NullPointerException unused3) {
                return true;
            }
        } catch (IOException unused4) {
            zipFile2 = zipFile;
            try {
                zipFile2.close();
            } catch (IOException | NullPointerException unused5) {
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            zipFile2 = zipFile;
            try {
                zipFile2.close();
            } catch (IOException | NullPointerException unused6) {
            }
            throw th;
        }
    }

    public static boolean extractResource(String str, String str2) throws Throwable {
        ZipEntry entry;
        Log.d(TAG, "Extract resource from Apk " + str);
        long jUptimeMillis = SystemClock.uptimeMillis();
        ZipFile zipFile = null;
        try {
            try {
                ZipFile zipFile2 = new ZipFile(str);
                try {
                    for (String str3 : MANDATORY_RESOURCES) {
                        if (isNativeLibrary(str3)) {
                            String deviceAbi = XWalkEnvironment.getDeviceAbi();
                            entry = zipFile2.getEntry("lib" + File.separator + deviceAbi + File.separator + str3);
                            if (entry == null && XWalkEnvironment.is64bitDevice()) {
                                if (deviceAbi.equals("arm64-v8a")) {
                                    deviceAbi = "armeabi-v7a";
                                } else if (deviceAbi.equals("x86_64")) {
                                    deviceAbi = "x86";
                                }
                                entry = zipFile2.getEntry("lib" + File.separator + deviceAbi + File.separator + str3);
                            }
                        } else {
                            entry = isAsset(str3) ? zipFile2.getEntry("assets" + File.separator + str3) : zipFile2.getEntry(str3);
                        }
                        if (entry == null) {
                            Log.e(TAG, str3 + " not found");
                            try {
                                zipFile2.close();
                            } catch (IOException | NullPointerException unused) {
                            }
                            return false;
                        }
                        Log.d(TAG, "Extracting " + str3);
                        extractStreamToFile(zipFile2.getInputStream(entry), new File(str2, str3));
                    }
                    try {
                        zipFile2.close();
                    } catch (IOException | NullPointerException unused2) {
                    }
                    Log.d(TAG, "Time to extract : " + (SystemClock.uptimeMillis() - jUptimeMillis) + " ms");
                    return true;
                } catch (IOException e) {
                    e = e;
                    zipFile = zipFile2;
                    Log.d(TAG, e.getLocalizedMessage());
                    try {
                        zipFile.close();
                    } catch (IOException | NullPointerException unused3) {
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    zipFile = zipFile2;
                    try {
                        zipFile.close();
                    } catch (IOException | NullPointerException unused4) {
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }

    public static boolean decompressResource(String str, String str2) throws Throwable {
        Log.d(TAG, "Decompress resource from Apk " + str);
        long jUptimeMillis = SystemClock.uptimeMillis();
        String[] strArr = MANDATORY_RESOURCES;
        ArrayList arrayList = new ArrayList(strArr.length);
        ExecutorService executorServiceNewFixedThreadPool = Executors.newFixedThreadPool(strArr.length);
        ZipFile zipFile = null;
        try {
            try {
                ZipFile zipFile2 = new ZipFile(str);
                try {
                    for (String str3 : strArr) {
                        try {
                            ZipEntry entry = zipFile2.getEntry("assets" + File.separator + str3 + ".lzma");
                            if (entry == null) {
                                Log.e(TAG, str3 + " not found");
                                try {
                                    zipFile2.close();
                                } catch (IOException | NullPointerException unused) {
                                }
                                return false;
                            }
                            arrayList.add(new DecompressResourceTask(zipFile2, entry, new File(str2, str3)));
                        } catch (InterruptedException | ExecutionException | RejectedExecutionException unused2) {
                            Log.d(TAG, "Failed to execute decompression");
                            try {
                                zipFile2.close();
                            } catch (IOException | NullPointerException unused3) {
                            }
                            return false;
                        } finally {
                            executorServiceNewFixedThreadPool.shutdown();
                        }
                    }
                    Iterator it = executorServiceNewFixedThreadPool.invokeAll(arrayList).iterator();
                    boolean zBooleanValue = true;
                    while (it.hasNext()) {
                        zBooleanValue &= ((Boolean) ((Future) it.next()).get()).booleanValue();
                    }
                    try {
                        zipFile2.close();
                    } catch (IOException | NullPointerException unused4) {
                    }
                    Log.d(TAG, "Time to decompress : " + (SystemClock.uptimeMillis() - jUptimeMillis) + " ms");
                    return zBooleanValue;
                } catch (IOException e) {
                    e = e;
                    zipFile = zipFile2;
                    Log.d(TAG, e.getLocalizedMessage());
                    try {
                        zipFile.close();
                    } catch (IOException | NullPointerException unused5) {
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    zipFile = zipFile2;
                    try {
                        zipFile.close();
                    } catch (IOException | NullPointerException unused6) {
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }

    private static class DecompressResourceTask implements Callable<Boolean> {
        File mDestFile;
        ZipEntry mZipEntry;
        ZipFile mZipFile;

        DecompressResourceTask(ZipFile zipFile, ZipEntry zipEntry, File file) {
            this.mZipFile = zipFile;
            this.mZipEntry = zipEntry;
            this.mDestFile = file;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public Boolean call() throws Throwable {
            try {
                Log.d(XWalkDecompressor.TAG, "Decompressing " + this.mZipEntry.getName());
                XWalkDecompressor.extractLzmaToFile(this.mZipFile.getInputStream(this.mZipEntry), this.mDestFile);
                return true;
            } catch (IOException e) {
                Log.e(XWalkDecompressor.TAG, e.getLocalizedMessage());
                return false;
            }
        }
    }

    private static boolean isNativeLibrary(String str) {
        return str.endsWith(".so");
    }

    private static boolean isAsset(String str) {
        return str.endsWith(".dat") || str.endsWith(".pak");
    }

    private static InputStream openRawResource(String str) throws Resources.NotFoundException {
        Context applicationContext = XWalkEnvironment.getApplicationContext();
        Resources resources = applicationContext.getResources();
        return resources.openRawResource(resources.getIdentifier(str.split("\\.")[0], "raw", applicationContext.getPackageName()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.OutputStream] */
    public static void extractLzmaToFile(InputStream inputStream, File file) throws Throwable {
        BufferedInputStream bufferedInputStream;
        IOException e;
        BufferedOutputStream bufferedOutputStream;
        ?? r0 = 0;
        r0 = 0;
        try {
            try {
                bufferedInputStream = new BufferedInputStream(inputStream);
                try {
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                } catch (IOException e2) {
                    e = e2;
                } catch (Throwable th) {
                    th = th;
                    try {
                        r0.flush();
                    } catch (IOException | NullPointerException unused) {
                    }
                    try {
                        r0.close();
                    } catch (IOException | NullPointerException unused2) {
                    }
                    try {
                        bufferedInputStream.close();
                        throw th;
                    } catch (IOException | NullPointerException unused3) {
                        throw th;
                    }
                }
            } catch (IOException e3) {
                e = e3;
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = null;
            }
            try {
                byte[] bArr = new byte[5];
                if (bufferedInputStream.read(bArr, 0, 5) != 5) {
                    throw new IOException("Input lzma file is too short");
                }
                Decoder decoder = new Decoder();
                if (!decoder.SetDecoderProperties(bArr)) {
                    throw new IOException("Incorrect lzma properties");
                }
                long j = 0;
                for (int i = 0; i < 8; i++) {
                    int i2 = bufferedInputStream.read();
                    if (i2 < 0) {
                        Log.w(TAG, "Can't read stream size");
                    }
                    j |= ((long) i2) << (i * 8);
                }
                if (!decoder.Code(bufferedInputStream, bufferedOutputStream, j)) {
                    throw new IOException("Error in data stream");
                }
                try {
                    bufferedOutputStream.flush();
                } catch (IOException | NullPointerException unused4) {
                }
                try {
                    bufferedOutputStream.close();
                } catch (IOException | NullPointerException unused5) {
                }
                try {
                    bufferedInputStream.close();
                } catch (IOException | NullPointerException unused6) {
                }
            } catch (IOException e4) {
                e = e4;
                if (file.isFile()) {
                    file.delete();
                }
                throw e;
            }
        } catch (Throwable th3) {
            th = th3;
            r0 = inputStream;
        }
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0035: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:25:0x0035 */
    private static void extractStreamToFile(InputStream inputStream, File file) throws Throwable {
        IOException e;
        OutputStream outputStream;
        FileOutputStream fileOutputStream;
        OutputStream outputStream2 = null;
        try {
            try {
                inputStream.available();
                fileOutputStream = new FileOutputStream(file);
            } catch (IOException e2) {
                e = e2;
            } catch (Throwable th) {
                th = th;
                try {
                    outputStream2.flush();
                } catch (IOException | NullPointerException unused) {
                }
                try {
                    outputStream2.close();
                } catch (IOException | NullPointerException unused2) {
                }
                try {
                    inputStream.close();
                    throw th;
                } catch (IOException | NullPointerException unused3) {
                    throw th;
                }
            }
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int i = inputStream.read(bArr);
                    if (i >= 0) {
                        fileOutputStream.write(bArr, 0, i);
                    } else {
                        try {
                            break;
                        } catch (IOException | NullPointerException unused4) {
                        }
                    }
                }
                fileOutputStream.flush();
                try {
                    fileOutputStream.close();
                } catch (IOException | NullPointerException unused5) {
                }
                try {
                    inputStream.close();
                } catch (IOException | NullPointerException unused6) {
                }
            } catch (IOException e3) {
                e = e3;
                if (file.isFile()) {
                    file.delete();
                }
                throw e;
            }
        } catch (Throwable th2) {
            th = th2;
            outputStream2 = outputStream;
            outputStream2.flush();
            outputStream2.close();
            inputStream.close();
            throw th;
        }
    }
}
