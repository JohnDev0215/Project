package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Properties;
import p.p03;
import p.tz2;

/* JADX INFO: loaded from: classes.dex */
public final class p {
    public static final tz2 h = new tz2("SliceMetadataManager", 0);
    public final c b;
    public final String c;
    public final int d;
    public final long e;
    public final String f;
    public final byte[] a = new byte[8192];
    public int g = -1;

    public p(c cVar, String str, int i, long j, String str2) {
        this.b = cVar;
        this.c = str;
        this.d = i;
        this.e = j;
        this.f = str2;
    }

    public final int a() throws IOException {
        c cVar = this.b;
        cVar.getClass();
        File file = new File(new File(new File(new File(cVar.c(this.d, this.e, this.c), "_slices"), "_metadata"), this.f), "checkpoint.dat");
        if (!file.exists()) {
            return 0;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();
            if (Integer.parseInt(properties.getProperty("fileStatus", "-1")) == 4) {
                return -1;
            }
            if (properties.getProperty("previousChunk") != null) {
                return Integer.parseInt(properties.getProperty("previousChunk")) + 1;
            }
            throw new zzck("Slice checkpoint file corrupt.");
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public final p03 b() throws IOException {
        c cVar = this.b;
        cVar.getClass();
        File file = new File(new File(new File(new File(cVar.c(this.d, this.e, this.c), "_slices"), "_metadata"), this.f), "checkpoint.dat");
        if (!file.exists()) {
            throw new zzck("Slice checkpoint file does not exist.");
        }
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            properties.load(fileInputStream);
            fileInputStream.close();
            if (properties.getProperty("fileStatus") == null || properties.getProperty("previousChunk") == null) {
                throw new zzck("Slice checkpoint file corrupt.");
            }
            try {
                int i = Integer.parseInt(properties.getProperty("fileStatus"));
                String property = properties.getProperty("fileName");
                long j = Long.parseLong(properties.getProperty("fileOffset", "-1"));
                long j2 = Long.parseLong(properties.getProperty("remainingBytes", "-1"));
                int i2 = Integer.parseInt(properties.getProperty("previousChunk"));
                this.g = Integer.parseInt(properties.getProperty("metadataFileCounter", "0"));
                return new p03(i, property, j, j2, i2);
            } catch (NumberFormatException e) {
                throw new zzck("Slice checkpoint file corrupt.", e);
            }
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public final File c() {
        c cVar = this.b;
        cVar.getClass();
        File file = new File(new File(new File(cVar.c(this.d, this.e, this.c), "_slices"), "_metadata"), this.f);
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file, String.format("%s-NAM.dat", Integer.valueOf(this.g)));
    }

    public final void d(long j, byte[] bArr, int i, int i2) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(c(), "rw");
        try {
            randomAccessFile.seek(j);
            randomAccessFile.write(bArr, i, i2);
            randomAccessFile.close();
        } catch (Throwable th) {
            try {
                randomAccessFile.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public final void e(int i) throws IOException {
        Properties properties = new Properties();
        properties.put("fileStatus", "3");
        properties.put("fileOffset", String.valueOf(c().length()));
        properties.put("previousChunk", String.valueOf(i));
        properties.put("metadataFileCounter", String.valueOf(this.g));
        FileOutputStream fileOutputStream = new FileOutputStream(l());
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public final void f(String str, long j, long j2, int i) throws IOException {
        Properties properties = new Properties();
        properties.put("fileStatus", "1");
        properties.put("fileName", str);
        properties.put("fileOffset", String.valueOf(j));
        properties.put("remainingBytes", String.valueOf(j2));
        properties.put("previousChunk", String.valueOf(i));
        properties.put("metadataFileCounter", String.valueOf(this.g));
        FileOutputStream fileOutputStream = new FileOutputStream(l());
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public final void g(byte[] bArr, int i) throws IOException {
        Properties properties = new Properties();
        properties.put("fileStatus", "2");
        properties.put("previousChunk", String.valueOf(i));
        properties.put("metadataFileCounter", String.valueOf(this.g));
        FileOutputStream fileOutputStream = new FileOutputStream(l());
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
            c cVar = this.b;
            cVar.getClass();
            File file = new File(new File(new File(new File(cVar.c(this.d, this.e, this.c), "_slices"), "_metadata"), this.f), "checkpoint_ext.dat");
            if (file.exists()) {
                file.delete();
            }
            fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(bArr);
            } finally {
                try {
                    fileOutputStream.close();
                } catch (Throwable unused) {
                }
            }
        } finally {
        }
    }

    public final void h(int i) throws IOException {
        Properties properties = new Properties();
        properties.put("fileStatus", "4");
        properties.put("previousChunk", String.valueOf(i));
        properties.put("metadataFileCounter", String.valueOf(this.g));
        FileOutputStream fileOutputStream = new FileOutputStream(l());
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public final void i(byte[] bArr) {
        this.g++;
        c cVar = this.b;
        cVar.getClass();
        File file = new File(new File(new File(cVar.c(this.d, this.e, this.c), "_slices"), "_metadata"), this.f);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(file, String.format("%s-LFH.dat", Integer.valueOf(this.g))));
            try {
                fileOutputStream.write(bArr);
                fileOutputStream.close();
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        } catch (IOException e) {
            throw new zzck("Could not write metadata file.", e);
        }
    }

    public final void j(InputStream inputStream, byte[] bArr) throws IOException {
        byte[] bArr2 = this.a;
        this.g++;
        FileOutputStream fileOutputStream = new FileOutputStream(c());
        try {
            fileOutputStream.write(bArr);
            int i = inputStream.read(bArr2);
            while (i > 0) {
                fileOutputStream.write(bArr2, 0, i);
                i = inputStream.read(bArr2);
            }
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public final void k(byte[] bArr, int i) throws IOException {
        this.g++;
        FileOutputStream fileOutputStream = new FileOutputStream(c());
        try {
            fileOutputStream.write(bArr, 0, i);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public final File l() throws IOException {
        c cVar = this.b;
        cVar.getClass();
        File file = new File(new File(new File(new File(cVar.c(this.d, this.e, this.c), "_slices"), "_metadata"), this.f), "checkpoint.dat");
        file.getParentFile().mkdirs();
        file.createNewFile();
        return file;
    }
}
