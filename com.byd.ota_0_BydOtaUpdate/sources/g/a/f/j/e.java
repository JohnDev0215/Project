package g.a.f.j;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class e implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f5734a = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final byte[] f5735b = new byte[128];

    public e() {
        d();
    }

    public static boolean c(char c2) {
        return c2 == '\n' || c2 == '\r' || c2 == '\t' || c2 == ' ';
    }

    @Override // g.a.f.j.c
    public int a(byte[] bArr, int i2, int i3, OutputStream outputStream) throws IOException {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            int i5 = bArr[i4] & 255;
            outputStream.write(this.f5734a[i5 >>> 4]);
            outputStream.write(this.f5734a[i5 & 15]);
        }
        return i3 * 2;
    }

    @Override // g.a.f.j.c
    public int b(String str, OutputStream outputStream) throws IOException {
        int length = str.length();
        while (length > 0 && c(str.charAt(length - 1))) {
            length--;
        }
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            while (i2 < length && c(str.charAt(i2))) {
                i2++;
            }
            int i4 = i2 + 1;
            byte b2 = this.f5735b[str.charAt(i2)];
            while (i4 < length && c(str.charAt(i4))) {
                i4++;
            }
            int i5 = i4 + 1;
            byte b3 = this.f5735b[str.charAt(i4)];
            if ((b2 | b3) < 0) {
                throw new IOException("invalid characters encountered in Hex string");
            }
            outputStream.write((b2 << 4) | b3);
            i3++;
            i2 = i5;
        }
        return i3;
    }

    public void d() {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            byte[] bArr = this.f5735b;
            if (i3 >= bArr.length) {
                break;
            }
            bArr[i3] = -1;
            i3++;
        }
        while (true) {
            byte[] bArr2 = this.f5734a;
            if (i2 >= bArr2.length) {
                byte[] bArr3 = this.f5735b;
                bArr3[65] = bArr3[97];
                bArr3[66] = bArr3[98];
                bArr3[67] = bArr3[99];
                bArr3[68] = bArr3[100];
                bArr3[69] = bArr3[101];
                bArr3[70] = bArr3[102];
                return;
            }
            this.f5735b[bArr2[i2]] = (byte) i2;
            i2++;
        }
    }
}
