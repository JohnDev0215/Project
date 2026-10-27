package g.a.f.j;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class b implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f5730a = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte f5731b = 61;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final byte[] f5732c = new byte[128];

    public b() {
        e();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x00a3  */
    @Override // g.a.f.j.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int a(byte[] r10, int r11, int r12, java.io.OutputStream r13) throws java.io.IOException {
        /*
            r9 = this;
            int r0 = r12 % 3
            int r12 = r12 - r0
            r1 = r11
        L4:
            int r2 = r11 + r12
            r3 = 4
            r4 = 2
            if (r1 >= r2) goto L4c
            r2 = r10[r1]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r5 = r1 + 1
            r5 = r10[r5]
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r6 = r1 + 2
            r6 = r10[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            byte[] r7 = r9.f5730a
            int r8 = r2 >>> 2
            r8 = r8 & 63
            r7 = r7[r8]
            r13.write(r7)
            byte[] r7 = r9.f5730a
            int r2 = r2 << r3
            int r3 = r5 >>> 4
            r2 = r2 | r3
            r2 = r2 & 63
            r2 = r7[r2]
            r13.write(r2)
            byte[] r2 = r9.f5730a
            int r3 = r5 << 2
            int r4 = r6 >>> 6
            r3 = r3 | r4
            r3 = r3 & 63
            r2 = r2[r3]
            r13.write(r2)
            byte[] r2 = r9.f5730a
            r3 = r6 & 63
            r2 = r2[r3]
            r13.write(r2)
            int r1 = r1 + 3
            goto L4
        L4c:
            r11 = 1
            if (r0 == r11) goto L7b
            if (r0 == r4) goto L52
            goto L9e
        L52:
            r1 = r10[r2]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r2 = r2 + r11
            r10 = r10[r2]
            r10 = r10 & 255(0xff, float:3.57E-43)
            int r11 = r1 >>> 2
            r11 = r11 & 63
            int r1 = r1 << r3
            int r2 = r10 >>> 4
            r1 = r1 | r2
            r1 = r1 & 63
            int r10 = r10 << r4
            r10 = r10 & 63
            byte[] r2 = r9.f5730a
            r11 = r2[r11]
            r13.write(r11)
            byte[] r11 = r9.f5730a
            r11 = r11[r1]
            r13.write(r11)
            byte[] r11 = r9.f5730a
            r10 = r11[r10]
            goto L96
        L7b:
            r10 = r10[r2]
            r10 = r10 & 255(0xff, float:3.57E-43)
            int r11 = r10 >>> 2
            r11 = r11 & 63
            int r10 = r10 << r3
            r10 = r10 & 63
            byte[] r1 = r9.f5730a
            r11 = r1[r11]
            r13.write(r11)
            byte[] r11 = r9.f5730a
            r10 = r11[r10]
            r13.write(r10)
            byte r10 = r9.f5731b
        L96:
            r13.write(r10)
            byte r10 = r9.f5731b
            r13.write(r10)
        L9e:
            int r12 = r12 / 3
            int r12 = r12 * r3
            if (r0 != 0) goto La4
            r3 = 0
        La4:
            int r12 = r12 + r3
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: g.a.f.j.b.a(byte[], int, int, java.io.OutputStream):int");
    }

    @Override // g.a.f.j.c
    public int b(String str, OutputStream outputStream) throws IOException {
        int length = str.length();
        while (length > 0 && d(str.charAt(length - 1))) {
            length--;
        }
        int i2 = 0;
        if (length == 0) {
            return 0;
        }
        int i3 = length;
        int i4 = 0;
        while (i3 > 0 && i4 != 4) {
            if (!d(str.charAt(i3 - 1))) {
                i4++;
            }
            i3--;
        }
        int iF = f(str, 0, i3);
        while (iF < i3) {
            int i5 = iF + 1;
            byte b2 = this.f5732c[str.charAt(iF)];
            int iF2 = f(str, i5, i3);
            int i6 = iF2 + 1;
            byte b3 = this.f5732c[str.charAt(iF2)];
            int iF3 = f(str, i6, i3);
            int i7 = iF3 + 1;
            byte b4 = this.f5732c[str.charAt(iF3)];
            int iF4 = f(str, i7, i3);
            int i8 = iF4 + 1;
            byte b5 = this.f5732c[str.charAt(iF4)];
            if ((b2 | b3 | b4 | b5) < 0) {
                throw new IOException("invalid characters encountered in base64 data");
            }
            outputStream.write((b2 << 2) | (b3 >> 4));
            outputStream.write((b3 << 4) | (b4 >> 2));
            outputStream.write((b4 << 6) | b5);
            i2 += 3;
            iF = f(str, i8, i3);
        }
        int iF5 = f(str, iF, length);
        int iF6 = f(str, iF5 + 1, length);
        int iF7 = f(str, iF6 + 1, length);
        return i2 + c(outputStream, str.charAt(iF5), str.charAt(iF6), str.charAt(iF7), str.charAt(f(str, iF7 + 1, length)));
    }

    public final int c(OutputStream outputStream, char c2, char c3, char c4, char c5) throws IOException {
        char c6 = this.f5731b;
        if (c4 == c6) {
            if (c5 != c6) {
                throw new IOException("invalid characters encountered at end of base64 data");
            }
            byte[] bArr = this.f5732c;
            byte b2 = bArr[c2];
            byte b3 = bArr[c3];
            if ((b2 | b3) < 0) {
                throw new IOException("invalid characters encountered at end of base64 data");
            }
            outputStream.write((b2 << 2) | (b3 >> 4));
            return 1;
        }
        if (c5 == c6) {
            byte[] bArr2 = this.f5732c;
            byte b4 = bArr2[c2];
            byte b5 = bArr2[c3];
            byte b6 = bArr2[c4];
            if ((b4 | b5 | b6) < 0) {
                throw new IOException("invalid characters encountered at end of base64 data");
            }
            outputStream.write((b4 << 2) | (b5 >> 4));
            outputStream.write((b5 << 4) | (b6 >> 2));
            return 2;
        }
        byte[] bArr3 = this.f5732c;
        byte b7 = bArr3[c2];
        byte b8 = bArr3[c3];
        byte b9 = bArr3[c4];
        byte b10 = bArr3[c5];
        if ((b7 | b8 | b9 | b10) < 0) {
            throw new IOException("invalid characters encountered at end of base64 data");
        }
        outputStream.write((b7 << 2) | (b8 >> 4));
        outputStream.write((b8 << 4) | (b9 >> 2));
        outputStream.write((b9 << 6) | b10);
        return 3;
    }

    public final boolean d(char c2) {
        return c2 == '\n' || c2 == '\r' || c2 == '\t' || c2 == ' ';
    }

    public void e() {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            byte[] bArr = this.f5732c;
            if (i3 >= bArr.length) {
                break;
            }
            bArr[i3] = -1;
            i3++;
        }
        while (true) {
            byte[] bArr2 = this.f5730a;
            if (i2 >= bArr2.length) {
                return;
            }
            this.f5732c[bArr2[i2]] = (byte) i2;
            i2++;
        }
    }

    public final int f(String str, int i2, int i3) {
        while (i2 < i3 && d(str.charAt(i2))) {
            i2++;
        }
        return i2;
    }
}
