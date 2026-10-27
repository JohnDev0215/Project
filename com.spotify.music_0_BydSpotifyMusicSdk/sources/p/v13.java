package p;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public final class v13 extends InputStream {
    public final Enumeration c;
    public FileInputStream e;

    public v13(Enumeration enumeration) throws IOException {
        this.c = enumeration;
        b();
    }

    public final void b() throws IOException {
        FileInputStream fileInputStream = this.e;
        if (fileInputStream != null) {
            fileInputStream.close();
        }
        Enumeration enumeration = this.c;
        if (enumeration.hasMoreElements()) {
            this.e = new FileInputStream((File) enumeration.nextElement());
        } else {
            this.e = null;
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        super.close();
        FileInputStream fileInputStream = this.e;
        if (fileInputStream != null) {
            fileInputStream.close();
            this.e = null;
        }
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        while (true) {
            FileInputStream fileInputStream = this.e;
            if (fileInputStream == null) {
                return -1;
            }
            int i = fileInputStream.read();
            if (i != -1) {
                return i;
            }
            b();
        }
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.e == null) {
            return -1;
        }
        bArr.getClass();
        if (i < 0 || i2 < 0 || i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 == 0) {
            return 0;
        }
        do {
            int i3 = this.e.read(bArr, i, i2);
            if (i3 > 0) {
                return i3;
            }
            b();
        } while (this.e != null);
        return -1;
    }
}
