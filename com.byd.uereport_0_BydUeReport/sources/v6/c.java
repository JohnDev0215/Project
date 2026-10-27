package v6;

import com.google.zxing.WriterException;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: Encoder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f8589a = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Charset f8590b = StandardCharsets.ISO_8859_1;

    /* JADX INFO: compiled from: Encoder.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f8591a;

        static {
            int[] iArr = new int[Mode.values().length];
            f8591a = iArr;
            try {
                iArr[Mode.NUMERIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8591a[Mode.ALPHANUMERIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8591a[Mode.BYTE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8591a[Mode.KANJI.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static void a(String str, r6.a aVar, Charset charset) {
        for (byte b8 : str.getBytes(charset)) {
            aVar.c(b8, 8);
        }
    }

    public static void b(CharSequence charSequence, r6.a aVar) throws WriterException {
        int length = charSequence.length();
        int i8 = 0;
        while (i8 < length) {
            int iP = p(charSequence.charAt(i8));
            if (iP == -1) {
                throw new WriterException();
            }
            int i9 = i8 + 1;
            if (i9 < length) {
                int iP2 = p(charSequence.charAt(i9));
                if (iP2 == -1) {
                    throw new WriterException();
                }
                aVar.c((iP * 45) + iP2, 11);
                i8 += 2;
            } else {
                aVar.c(iP, 6);
                i8 = i9;
            }
        }
    }

    public static void c(String str, Mode mode, r6.a aVar, Charset charset) throws WriterException {
        int i8 = a.f8591a[mode.ordinal()];
        if (i8 == 1) {
            h(str, aVar);
            return;
        }
        if (i8 == 2) {
            b(str, aVar);
            return;
        }
        if (i8 == 3) {
            a(str, aVar, charset);
        } else {
            if (i8 == 4) {
                e(str, aVar);
                return;
            }
            throw new WriterException("Invalid mode: " + mode);
        }
    }

    public static void d(CharacterSetECI characterSetECI, r6.a aVar) {
        aVar.c(Mode.ECI.getBits(), 4);
        aVar.c(characterSetECI.getValue(), 8);
    }

    public static void e(String str, r6.a aVar) throws WriterException {
        int i8;
        byte[] bytes = str.getBytes(r6.d.f7855b);
        if (bytes.length % 2 != 0) {
            throw new WriterException("Kanji byte size not even");
        }
        int length = bytes.length - 1;
        for (int i9 = 0; i9 < length; i9 += 2) {
            int i10 = ((bytes[i9] & 255) << 8) | (bytes[i9 + 1] & 255);
            int i11 = 33088;
            if (i10 >= 33088 && i10 <= 40956) {
                i8 = i10 - i11;
            } else if (i10 < 57408 || i10 > 60351) {
                i8 = -1;
            } else {
                i11 = 49472;
                i8 = i10 - i11;
            }
            if (i8 == -1) {
                throw new WriterException("Invalid byte sequence");
            }
            aVar.c(((i8 >> 8) * 192) + (i8 & 255), 13);
        }
    }

    public static void f(int i8, u6.a aVar, Mode mode, r6.a aVar2) throws WriterException {
        int characterCountBits = mode.getCharacterCountBits(aVar);
        int i9 = 1 << characterCountBits;
        if (i8 < i9) {
            aVar2.c(i8, characterCountBits);
            return;
        }
        throw new WriterException(i8 + " is bigger than " + (i9 - 1));
    }

    public static void g(Mode mode, r6.a aVar) {
        aVar.c(mode.getBits(), 4);
    }

    public static void h(CharSequence charSequence, r6.a aVar) {
        int length = charSequence.length();
        int i8 = 0;
        while (i8 < length) {
            int iCharAt = charSequence.charAt(i8) - '0';
            int i9 = i8 + 2;
            if (i9 < length) {
                aVar.c((iCharAt * 100) + ((charSequence.charAt(i8 + 1) - '0') * 10) + (charSequence.charAt(i9) - '0'), 10);
                i8 += 3;
            } else {
                i8++;
                if (i8 < length) {
                    aVar.c((iCharAt * 10) + (charSequence.charAt(i8) - '0'), 7);
                    i8 = i9;
                } else {
                    aVar.c(iCharAt, 4);
                }
            }
        }
    }

    public static int i(Mode mode, r6.a aVar, r6.a aVar2, u6.a aVar3) {
        return aVar.g() + mode.getCharacterCountBits(aVar3) + aVar2.g();
    }

    public static int j(b bVar) {
        return d.a(bVar) + d.c(bVar) + d.d(bVar) + d.e(bVar);
    }

    public static int k(r6.a aVar, ErrorCorrectionLevel errorCorrectionLevel, u6.a aVar2, b bVar) throws WriterException {
        int i8 = Integer.MAX_VALUE;
        int i9 = -1;
        for (int i10 = 0; i10 < 8; i10++) {
            e.a(aVar, errorCorrectionLevel, aVar2, i10, bVar);
            int iJ = j(bVar);
            if (iJ < i8) {
                i9 = i10;
                i8 = iJ;
            }
        }
        return i9;
    }

    public static Mode l(String str, Charset charset) {
        if (r6.d.f7855b.equals(charset) && s(str)) {
            return Mode.KANJI;
        }
        boolean z7 = false;
        boolean z8 = false;
        for (int i8 = 0; i8 < str.length(); i8++) {
            char cCharAt = str.charAt(i8);
            if (cCharAt >= '0' && cCharAt <= '9') {
                z8 = true;
            } else {
                if (p(cCharAt) == -1) {
                    return Mode.BYTE;
                }
                z7 = true;
            }
        }
        return z7 ? Mode.ALPHANUMERIC : z8 ? Mode.NUMERIC : Mode.BYTE;
    }

    public static u6.a m(int i8, ErrorCorrectionLevel errorCorrectionLevel) throws WriterException {
        for (int i9 = 1; i9 <= 40; i9++) {
            u6.a aVarE = u6.a.e(i9);
            if (v(i8, aVarE, errorCorrectionLevel)) {
                return aVarE;
            }
        }
        throw new WriterException("Data too big");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static v6.f n(java.lang.String r6, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel r7, java.util.Map<com.google.zxing.EncodeHintType, ?> r8) throws com.google.zxing.WriterException {
        /*
            Method dump skipped, instruction units count: 335
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: v6.c.n(java.lang.String, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel, java.util.Map):v6.f");
    }

    public static byte[] o(byte[] bArr, int i8) {
        int length = bArr.length;
        int[] iArr = new int[length + i8];
        for (int i9 = 0; i9 < length; i9++) {
            iArr[i9] = bArr[i9] & 255;
        }
        new s6.c(s6.a.f7924e).b(iArr, i8);
        byte[] bArr2 = new byte[i8];
        for (int i10 = 0; i10 < i8; i10++) {
            bArr2[i10] = (byte) iArr[length + i10];
        }
        return bArr2;
    }

    public static int p(int i8) {
        int[] iArr = f8589a;
        if (i8 < iArr.length) {
            return iArr[i8];
        }
        return -1;
    }

    public static void q(int i8, int i9, int i10, int i11, int[] iArr, int[] iArr2) throws WriterException {
        if (i11 >= i10) {
            throw new WriterException("Block ID too large");
        }
        int i12 = i8 % i10;
        int i13 = i10 - i12;
        int i14 = i8 / i10;
        int i15 = i14 + 1;
        int i16 = i9 / i10;
        int i17 = i16 + 1;
        int i18 = i14 - i16;
        int i19 = i15 - i17;
        if (i18 != i19) {
            throw new WriterException("EC bytes mismatch");
        }
        if (i10 != i13 + i12) {
            throw new WriterException("RS blocks mismatch");
        }
        if (i8 != ((i16 + i18) * i13) + ((i17 + i19) * i12)) {
            throw new WriterException("Total bytes mismatch");
        }
        if (i11 < i13) {
            iArr[0] = i16;
            iArr2[0] = i18;
        } else {
            iArr[0] = i17;
            iArr2[0] = i19;
        }
    }

    public static r6.a r(r6.a aVar, int i8, int i9, int i10) throws WriterException {
        if (aVar.h() != i9) {
            throw new WriterException("Number of bits and data bytes does not match");
        }
        ArrayList arrayList = new ArrayList(i10);
        int i11 = 0;
        int iMax = 0;
        int iMax2 = 0;
        for (int i12 = 0; i12 < i10; i12++) {
            int[] iArr = new int[1];
            int[] iArr2 = new int[1];
            q(i8, i9, i10, i12, iArr, iArr2);
            int i13 = iArr[0];
            byte[] bArr = new byte[i13];
            aVar.j(i11 * 8, bArr, 0, i13);
            byte[] bArrO = o(bArr, iArr2[0]);
            arrayList.add(new v6.a(bArr, bArrO));
            iMax = Math.max(iMax, i13);
            iMax2 = Math.max(iMax2, bArrO.length);
            i11 += iArr[0];
        }
        if (i9 != i11) {
            throw new WriterException("Data bytes does not match offset");
        }
        r6.a aVar2 = new r6.a();
        for (int i14 = 0; i14 < iMax; i14++) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                byte[] bArrA = ((v6.a) it.next()).a();
                if (i14 < bArrA.length) {
                    aVar2.c(bArrA[i14], 8);
                }
            }
        }
        for (int i15 = 0; i15 < iMax2; i15++) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                byte[] bArrB = ((v6.a) it2.next()).b();
                if (i15 < bArrB.length) {
                    aVar2.c(bArrB[i15], 8);
                }
            }
        }
        if (i8 == aVar2.h()) {
            return aVar2;
        }
        throw new WriterException("Interleaving error: " + i8 + " and " + aVar2.h() + " differ.");
    }

    public static boolean s(String str) {
        byte[] bytes = str.getBytes(r6.d.f7855b);
        int length = bytes.length;
        if (length % 2 != 0) {
            return false;
        }
        for (int i8 = 0; i8 < length; i8 += 2) {
            int i9 = bytes[i8] & 255;
            if ((i9 < 129 || i9 > 159) && (i9 < 224 || i9 > 235)) {
                return false;
            }
        }
        return true;
    }

    public static u6.a t(ErrorCorrectionLevel errorCorrectionLevel, Mode mode, r6.a aVar, r6.a aVar2) throws WriterException {
        return m(i(mode, aVar, aVar2, m(i(mode, aVar, aVar2, u6.a.e(1)), errorCorrectionLevel)), errorCorrectionLevel);
    }

    public static void u(int i8, r6.a aVar) throws WriterException {
        int i9 = i8 * 8;
        if (aVar.g() > i9) {
            throw new WriterException("data bits cannot fit in the QR Code" + aVar.g() + " > " + i9);
        }
        for (int i10 = 0; i10 < 4 && aVar.g() < i9; i10++) {
            aVar.a(false);
        }
        int iG = aVar.g() & 7;
        if (iG > 0) {
            while (iG < 8) {
                aVar.a(false);
                iG++;
            }
        }
        int iH = i8 - aVar.h();
        for (int i11 = 0; i11 < iH; i11++) {
            aVar.c((i11 & 1) == 0 ? 236 : 17, 8);
        }
        if (aVar.g() != i9) {
            throw new WriterException("Bits size does not equal capacity");
        }
    }

    public static boolean v(int i8, u6.a aVar, ErrorCorrectionLevel errorCorrectionLevel) {
        return aVar.d() - aVar.c(errorCorrectionLevel).d() >= (i8 + 7) / 8;
    }
}
