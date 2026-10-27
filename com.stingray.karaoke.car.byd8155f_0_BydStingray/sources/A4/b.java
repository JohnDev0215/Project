package A4;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f412a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f413b;
    public byte[] c;
    public int d;

    public b(int i7) {
        this.f412a = 1;
        a(i7);
    }

    public void a(int i7) {
        int i10;
        this.f413b = i7;
        int i11 = 0;
        if (i7 == 0) {
            this.c = new byte[]{0};
            this.d = 1;
            return;
        }
        this.d = 0;
        int[] iArr = new int[4];
        while (true) {
            int i12 = this.d;
            if (i12 >= 4 || i7 <= 0) {
                break;
            }
            iArr[i12] = i7 & 127;
            this.d = i12 + 1;
            i7 >>= 7;
        }
        int i13 = 1;
        while (true) {
            i10 = this.d;
            if (i13 >= i10) {
                break;
            }
            iArr[i13] = iArr[i13] | 128;
            i13++;
        }
        this.c = new byte[i10];
        while (true) {
            int i14 = this.d;
            if (i11 >= i14) {
                return;
            }
            this.c[i11] = (byte) iArr[(i14 - i11) - 1];
            i11++;
        }
    }

    public String toString() {
        switch (this.f412a) {
            case 1:
                StringBuilder sb = new StringBuilder();
                byte[] bArr = this.c;
                StringBuilder sb2 = new StringBuilder();
                for (byte b10 : bArr) {
                    sb2.append("" + "0123456789ABCDEF".charAt((b10 & 240) >> 4) + "0123456789ABCDEF".charAt(b10 & 15));
                    sb2.append(" ");
                }
                sb.append(sb2.toString());
                sb.append(" (");
                return M5.b.h(sb, this.f413b, ")");
            default:
                return super.toString();
        }
    }

    public b(ByteArrayInputStream byteArrayInputStream) throws IOException {
        int i7;
        this.f412a = 1;
        int[] iArr = new int[4];
        this.d = 0;
        this.f413b = 0;
        int i10 = byteArrayInputStream.read();
        while (true) {
            int i11 = this.d;
            if (i11 >= 4) {
                break;
            }
            this.d = i11 + 1;
            if ((i10 & 128) > 0) {
                iArr[i11] = i10 & 127;
                i10 = byteArrayInputStream.read();
            } else {
                iArr[i11] = i10 & 127;
                break;
            }
        }
        int i12 = 1;
        int i13 = 0;
        while (true) {
            i7 = this.d;
            if (i12 >= i7) {
                break;
            }
            i13 += 7;
            i12++;
        }
        this.c = new byte[i7];
        for (int i14 = 0; i14 < this.d; i14++) {
            byte[] bArr = this.c;
            int i15 = iArr[i14];
            bArr[i14] = (byte) i15;
            this.f413b += i15 << i13;
            i13 -= 7;
        }
    }

    public b(int i7, int i10, byte[] bArr) {
        this.f412a = 0;
        this.c = bArr;
        this.f413b = i7;
        this.d = i10;
    }
}
