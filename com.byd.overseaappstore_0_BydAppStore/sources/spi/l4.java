package spi;

import androidx.annotation.NonNull;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class l4 extends FilterInputStream {
    public final long a;
    public int b;

    private l4(@NonNull InputStream inputStream, long j) {
        super(inputStream);
        this.a = j;
    }

    @NonNull
    public static InputStream B(@NonNull InputStream inputStream, long j) {
        return new l4(inputStream, j);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final synchronized int available() throws IOException {
        return (int) Math.max(this.a - ((long) this.b), ((FilterInputStream) this).in.available());
    }

    public final int e(int i) throws IOException {
        if (i >= 0) {
            this.b += i;
        } else if (this.a - ((long) this.b) > 0) {
            StringBuilder sbK = e.k("Failed to read all expected data, expected: ");
            sbK.append(this.a);
            sbK.append(", but read: ");
            sbK.append(this.b);
            throw new IOException(sbK.toString());
        }
        return i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final synchronized int read() throws IOException {
        int i;
        i = super.read();
        e(i >= 0 ? 1 : -1);
        return i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final synchronized int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        i3 = super.read(bArr, i, i2);
        e(i3);
        return i3;
    }
}
