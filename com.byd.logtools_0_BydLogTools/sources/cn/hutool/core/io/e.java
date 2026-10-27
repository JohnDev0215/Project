package cn.hutool.core.io;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import okio.Segment;

/* JADX INFO: compiled from: IoUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends f {
    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }

    public static long b(InputStream inputStream, OutputStream outputStream) throws IORuntimeException {
        return c(inputStream, outputStream, Segment.SIZE);
    }

    public static long c(InputStream inputStream, OutputStream outputStream, int i) throws IORuntimeException {
        return e(inputStream, outputStream, i, null);
    }

    public static long d(InputStream inputStream, OutputStream outputStream, int i, long j, g gVar) throws IORuntimeException {
        return new cn.hutool.core.io.h.b(i, j, gVar).b(inputStream, outputStream);
    }

    public static long e(InputStream inputStream, OutputStream outputStream, int i, g gVar) throws IORuntimeException {
        return d(inputStream, outputStream, i, -1L, gVar);
    }

    public static b f(InputStream inputStream, boolean z) throws IORuntimeException {
        b bVar;
        if (inputStream instanceof FileInputStream) {
            try {
                bVar = new b(inputStream.available());
            } catch (IOException e2) {
                throw new IORuntimeException(e2);
            }
        } else {
            bVar = new b();
        }
        try {
            b(inputStream, bVar);
            return bVar;
        } finally {
            if (z) {
                a(inputStream);
            }
        }
    }

    public static byte[] g(InputStream inputStream) throws IORuntimeException {
        return h(inputStream, true);
    }

    public static byte[] h(InputStream inputStream, boolean z) throws IORuntimeException {
        try {
            if (!(inputStream instanceof FileInputStream)) {
                return f(inputStream, z).a();
            }
            try {
                int iAvailable = inputStream.available();
                byte[] bArr = new byte[iAvailable];
                int i = inputStream.read(bArr);
                if (i == iAvailable) {
                    return bArr;
                }
                throw new IOException(c.a.a.d.b.g("File length is [{}] but read [{}]!", Integer.valueOf(iAvailable), Integer.valueOf(i)));
            } catch (IOException e2) {
                throw new IORuntimeException(e2);
            }
        } finally {
            if (z) {
                a(inputStream);
            }
        }
    }

    public static BufferedInputStream i(InputStream inputStream) {
        c.a.a.c.d.j(inputStream, "InputStream must be not null!", new Object[0]);
        return inputStream instanceof BufferedInputStream ? (BufferedInputStream) inputStream : new BufferedInputStream(inputStream);
    }

    public static FileInputStream j(File file) {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e2) {
            throw new IORuntimeException(e2);
        }
    }
}
