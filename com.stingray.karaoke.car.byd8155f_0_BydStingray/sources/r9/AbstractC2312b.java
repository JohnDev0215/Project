package r9;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;

/* JADX INFO: renamed from: r9.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2312b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C2317g f21914a = new C2317g();

    public static final boolean a(int i7, int i10, int i11, byte[] bArr, byte[] bArr2) {
        A7.m.f(bArr, "a");
        A7.m.f(bArr2, "b");
        for (int i12 = 0; i12 < i11; i12++) {
            if (bArr[i12 + i7] != bArr2[i12 + i10]) {
                return false;
            }
        }
        return true;
    }

    public static final C b(H h10) {
        A7.m.f(h10, "<this>");
        return new C(h10);
    }

    public static final D c(J j3) {
        A7.m.f(j3, "<this>");
        return new D(j3);
    }

    public static void d(long j3, C2319i c2319i, int i7, ArrayList arrayList, int i10, int i11, ArrayList arrayList2) {
        int i12;
        int i13;
        int i14;
        int i15;
        C2319i c2319i2;
        int i16 = i7;
        if (i10 >= i11) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        for (int i17 = i10; i17 < i11; i17++) {
            if (((C2322l) arrayList.get(i17)).d() < i16) {
                throw new IllegalArgumentException("Failed requirement.");
            }
        }
        C2322l c2322l = (C2322l) arrayList.get(i10);
        C2322l c2322l2 = (C2322l) arrayList.get(i11 - 1);
        int i18 = -1;
        if (i16 == c2322l.d()) {
            int iIntValue = ((Number) arrayList2.get(i10)).intValue();
            int i19 = i10 + 1;
            C2322l c2322l3 = (C2322l) arrayList.get(i19);
            i12 = i19;
            i13 = iIntValue;
            c2322l = c2322l3;
        } else {
            i12 = i10;
            i13 = -1;
        }
        if (c2322l.i(i16) == c2322l2.i(i16)) {
            int iMin = Math.min(c2322l.d(), c2322l2.d());
            int i20 = 0;
            for (int i21 = i16; i21 < iMin && c2322l.i(i21) == c2322l2.i(i21); i21++) {
                i20++;
            }
            long j10 = 4;
            long j11 = (c2319i.f21932m / j10) + j3 + ((long) 2) + ((long) i20) + 1;
            c2319i.a0(-i20);
            c2319i.a0(i13);
            int i22 = i20 + i16;
            while (i16 < i22) {
                c2319i.a0(c2322l.i(i16) & 255);
                i16++;
            }
            if (i12 + 1 == i11) {
                if (i22 != ((C2322l) arrayList.get(i12)).d()) {
                    throw new IllegalStateException("Check failed.");
                }
                c2319i.a0(((Number) arrayList2.get(i12)).intValue());
                return;
            } else {
                C2319i c2319i3 = new C2319i();
                c2319i.a0(((int) ((c2319i3.f21932m / j10) + j11)) * (-1));
                d(j11, c2319i3, i22, arrayList, i12, i11, arrayList2);
                c2319i.r(c2319i3);
                return;
            }
        }
        int i23 = 1;
        for (int i24 = i12 + 1; i24 < i11; i24++) {
            if (((C2322l) arrayList.get(i24 - 1)).i(i16) != ((C2322l) arrayList.get(i24)).i(i16)) {
                i23++;
            }
        }
        long j12 = 4;
        long j13 = (c2319i.f21932m / j12) + j3 + ((long) 2) + ((long) (i23 * 2));
        c2319i.a0(i23);
        c2319i.a0(i13);
        for (int i25 = i12; i25 < i11; i25++) {
            int i26 = ((C2322l) arrayList.get(i25)).i(i16);
            if (i25 == i12 || i26 != ((C2322l) arrayList.get(i25 - 1)).i(i16)) {
                c2319i.a0(i26 & 255);
            }
        }
        C2319i c2319i4 = new C2319i();
        int i27 = i12;
        while (i27 < i11) {
            byte bI = ((C2322l) arrayList.get(i27)).i(i16);
            int i28 = i27 + 1;
            int i29 = i28;
            while (true) {
                if (i29 >= i11) {
                    i14 = i11;
                    break;
                } else {
                    if (bI != ((C2322l) arrayList.get(i29)).i(i16)) {
                        i14 = i29;
                        break;
                    }
                    i29++;
                }
            }
            if (i28 == i14 && i16 + 1 == ((C2322l) arrayList.get(i27)).d()) {
                c2319i.a0(((Number) arrayList2.get(i27)).intValue());
                i15 = i14;
                c2319i2 = c2319i4;
            } else {
                c2319i.a0(((int) ((c2319i4.f21932m / j12) + j13)) * i18);
                i15 = i14;
                c2319i2 = c2319i4;
                d(j13, c2319i4, i16 + 1, arrayList, i27, i14, arrayList2);
            }
            c2319i4 = c2319i2;
            i27 = i15;
            i18 = -1;
        }
        c2319i.r(c2319i4);
    }

    public static final void e(long j3, long j10, long j11) {
        if ((j10 | j11) < 0 || j10 > j3 || j3 - j10 < j11) {
            throw new ArrayIndexOutOfBoundsException("size=" + j3 + " offset=" + j10 + " byteCount=" + j11);
        }
    }

    public static final boolean f(AssertionError assertionError) {
        Logger logger = x.f21949a;
        if (assertionError.getCause() == null) {
            return false;
        }
        String message = assertionError.getMessage();
        return message != null ? Q8.h.C0(message, "getsockname failed", false) : false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x00cf, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static r9.y g(r9.C2322l... r11) {
        /*
            Method dump skipped, instruction units count: 271
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: r9.AbstractC2312b.g(r9.l[]):r9.y");
    }

    public static final C2313c h(Socket socket) throws IOException {
        Logger logger = x.f21949a;
        I i7 = new I(socket);
        OutputStream outputStream = socket.getOutputStream();
        A7.m.e(outputStream, "getOutputStream(...)");
        return new C2313c(i7, 0, new C2313c(outputStream, 1, i7));
    }

    public static final C2314d i(File file) {
        Logger logger = x.f21949a;
        A7.m.f(file, "<this>");
        return new C2314d(h6.j.h(new FileInputStream(file), file), L.d);
    }

    public static final C2314d j(InputStream inputStream) {
        Logger logger = x.f21949a;
        A7.m.f(inputStream, "<this>");
        return new C2314d(inputStream, new L());
    }

    public static final C2314d k(Socket socket) throws IOException {
        Logger logger = x.f21949a;
        I i7 = new I(socket);
        InputStream inputStream = socket.getInputStream();
        A7.m.e(inputStream, "getInputStream(...)");
        return new C2314d(i7, new C2314d(inputStream, i7));
    }

    public static final String l(int i7) {
        int i10 = 0;
        if (i7 == 0) {
            return "0";
        }
        char[] cArr = s9.b.f22381a;
        char[] cArr2 = {cArr[(i7 >> 28) & 15], cArr[(i7 >> 24) & 15], cArr[(i7 >> 20) & 15], cArr[(i7 >> 16) & 15], cArr[(i7 >> 12) & 15], cArr[(i7 >> 8) & 15], cArr[(i7 >> 4) & 15], cArr[i7 & 15]};
        while (i10 < 8 && cArr2[i10] == '0') {
            i10++;
        }
        if (i10 < 0) {
            throw new IndexOutOfBoundsException(A7.k.g("startIndex: ", i10, ", endIndex: 8, size: 8"));
        }
        if (i10 <= 8) {
            return new String(cArr2, i10, 8 - i10);
        }
        throw new IllegalArgumentException(A7.k.g("startIndex: ", i10, " > endIndex: 8"));
    }
}
