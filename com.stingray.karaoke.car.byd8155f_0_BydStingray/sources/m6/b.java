package m6;

import A7.m;
import T.C0761q;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.stingray.karaoke.car.utils.LayoutMode;
import com.stingray.karaoke.car.utils.WindowSizeClass;
import e4.h;
import e4.k;
import g4.x;
import h4.InterfaceC1359a;
import h6.n;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import k6.AbstractC1692D;
import k6.u;
import n4.o;
import n7.AbstractC1996k;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import q3.InterfaceC2177d;
import r4.C2290c;
import r9.AbstractC2311a;
import r9.AbstractC2312b;
import r9.C2322l;
import r9.z;
import s2.t;
import s9.f;

/* JADX INFO: loaded from: classes.dex */
public class b implements o, InterfaceC2177d, k, t {
    public static C2322l G0(byte[] bArr) {
        C2322l c2322l = C2322l.o;
        int length = bArr.length;
        AbstractC2312b.e(bArr.length, 0, length);
        return new C2322l(AbstractC1996k.H(0, length, bArr));
    }

    public static FontFamily L(C1.k[] kVarArr, ContentResolver contentResolver) {
        ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor;
        FontFamily.Builder builder = null;
        for (C1.k kVar : kVarArr) {
            try {
                parcelFileDescriptorOpenFileDescriptor = contentResolver.openFileDescriptor(kVar.f945a, "r", null);
            } catch (IOException e10) {
                n.u("TypefaceCompatApi29Impl", "Font load failed", e10);
            }
            if (parcelFileDescriptorOpenFileDescriptor == null) {
                if (parcelFileDescriptorOpenFileDescriptor != null) {
                }
            } else {
                try {
                    Font fontBuild = new Font.Builder(parcelFileDescriptorOpenFileDescriptor).setWeight(kVar.c).setSlant(kVar.d ? 1 : 0).setTtcIndex(kVar.f946b).build();
                    if (builder == null) {
                        builder = new FontFamily.Builder(fontBuild);
                    } else {
                        builder.addFont(fontBuild);
                    }
                } catch (Throwable th) {
                    try {
                        parcelFileDescriptorOpenFileDescriptor.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
            parcelFileDescriptorOpenFileDescriptor.close();
        }
        if (builder == null) {
            return null;
        }
        return builder.build();
    }

    public static int Q(FontStyle fontStyle, FontStyle fontStyle2) {
        return (Math.abs(fontStyle.getWeight() - fontStyle2.getWeight()) / 100) + (fontStyle.getSlant() == fontStyle2.getSlant() ? 0 : 2);
    }

    public static final String a(byte[] bArr, byte[][] bArr2, int i7) {
        int i10;
        boolean z6;
        int i11;
        int i12;
        int i13 = -1;
        byte[] bArr3 = PublicSuffixDatabase.f20596e;
        int length = bArr.length;
        int i14 = 0;
        while (i14 < length) {
            int i15 = (i14 + length) / 2;
            while (i15 > i13 && bArr[i15] != 10) {
                i15 += i13;
            }
            int i16 = i15 + 1;
            int i17 = 1;
            while (true) {
                i10 = i16 + i17;
                if (bArr[i10] == 10) {
                    break;
                }
                i17++;
            }
            int i18 = i10 - i16;
            int i19 = i7;
            boolean z10 = false;
            int i20 = 0;
            int i21 = 0;
            while (true) {
                if (z10) {
                    i11 = 46;
                    z6 = false;
                } else {
                    byte b10 = bArr2[i19][i20];
                    byte[] bArr4 = d9.b.f13645a;
                    int i22 = b10 & 255;
                    z6 = z10;
                    i11 = i22;
                }
                byte b11 = bArr[i16 + i21];
                byte[] bArr5 = d9.b.f13645a;
                i12 = i11 - (b11 & 255);
                if (i12 != 0) {
                    break;
                }
                i21++;
                i20++;
                if (i21 == i18) {
                    break;
                }
                if (bArr2[i19].length != i20) {
                    z10 = z6;
                } else {
                    if (i19 == bArr2.length - 1) {
                        break;
                    }
                    i19++;
                    z10 = true;
                    i20 = -1;
                }
            }
            if (i12 >= 0) {
                if (i12 <= 0) {
                    int i23 = i18 - i21;
                    int length2 = bArr2[i19].length - i20;
                    int length3 = bArr2.length;
                    for (int i24 = i19 + 1; i24 < length3; i24++) {
                        length2 += bArr2[i24].length;
                    }
                    if (length2 >= i23) {
                        if (length2 <= i23) {
                            Charset charset = StandardCharsets.UTF_8;
                            m.e(charset, "UTF_8");
                            return new String(bArr, i16, i18, charset);
                        }
                    }
                    length = i15;
                }
                i14 = i10 + 1;
            } else {
                length = i15;
            }
            i13 = -1;
        }
        return null;
    }

    public static final boolean h(b bVar, C0761q c0761q) {
        bVar.getClass();
        float f10 = ((u) c0761q.k(AbstractC1692D.d)).f18299b;
        return ((f10 > 480.0f ? 1 : (f10 == 480.0f ? 0 : -1)) <= 0 ? WindowSizeClass.COMPACT : (f10 > 900.0f ? 1 : (f10 == 900.0f ? 0 : -1)) <= 0 ? WindowSizeClass.MEDIUM : (f10 > 1600.0f ? 1 : (f10 == 1600.0f ? 0 : -1)) <= 0 ? WindowSizeClass.EXPANDED : WindowSizeClass.ULTRA) == WindowSizeClass.COMPACT;
    }

    public static final boolean i(b bVar, C0761q c0761q) {
        bVar.getClass();
        float f10 = ((u) c0761q.k(AbstractC1692D.d)).f18299b;
        return ((f10 > 480.0f ? 1 : (f10 == 480.0f ? 0 : -1)) <= 0 ? WindowSizeClass.COMPACT : (f10 > 900.0f ? 1 : (f10 == 900.0f ? 0 : -1)) <= 0 ? WindowSizeClass.MEDIUM : (f10 > 1600.0f ? 1 : (f10 == 1600.0f ? 0 : -1)) <= 0 ? WindowSizeClass.EXPANDED : WindowSizeClass.ULTRA) == WindowSizeClass.MEDIUM;
    }

    public static final boolean j(b bVar, C0761q c0761q) {
        bVar.getClass();
        float f10 = ((u) c0761q.k(AbstractC1692D.d)).f18298a;
        return ((f10 > 600.0f ? 1 : (f10 == 600.0f ? 0 : -1)) <= 0 ? WindowSizeClass.COMPACT : (f10 > 840.0f ? 1 : (f10 == 840.0f ? 0 : -1)) <= 0 ? WindowSizeClass.MEDIUM : (f10 > 1500.0f ? 1 : (f10 == 1500.0f ? 0 : -1)) <= 0 ? WindowSizeClass.EXPANDED : WindowSizeClass.ULTRA) == WindowSizeClass.COMPACT;
    }

    public static final boolean k(b bVar, C0761q c0761q) {
        bVar.getClass();
        float f10 = ((u) c0761q.k(AbstractC1692D.d)).f18298a;
        return ((f10 > 600.0f ? 1 : (f10 == 600.0f ? 0 : -1)) <= 0 ? WindowSizeClass.COMPACT : (f10 > 840.0f ? 1 : (f10 == 840.0f ? 0 : -1)) <= 0 ? WindowSizeClass.MEDIUM : (f10 > 1500.0f ? 1 : (f10 == 1500.0f ? 0 : -1)) <= 0 ? WindowSizeClass.EXPANDED : WindowSizeClass.ULTRA) == WindowSizeClass.MEDIUM;
    }

    public static final boolean l(z zVar) {
        z zVar2 = f.f22389e;
        return !Q8.o.s0(zVar.b(), ".class", true);
    }

    public static C2322l n(String str) {
        int i7;
        char cCharAt;
        m.f(str, "<this>");
        byte[] bArr = AbstractC2311a.f21913a;
        int length = str.length();
        while (length > 0 && ((cCharAt = str.charAt(length - 1)) == '=' || cCharAt == '\n' || cCharAt == '\r' || cCharAt == ' ' || cCharAt == '\t')) {
            length--;
        }
        int i10 = (int) ((((long) length) * 6) / 8);
        byte[] bArrCopyOf = new byte[i10];
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        while (true) {
            if (i11 < length) {
                char cCharAt2 = str.charAt(i11);
                if ('A' <= cCharAt2 && cCharAt2 < '[') {
                    i7 = cCharAt2 - 'A';
                } else if ('a' <= cCharAt2 && cCharAt2 < '{') {
                    i7 = cCharAt2 - 'G';
                } else if ('0' <= cCharAt2 && cCharAt2 < ':') {
                    i7 = cCharAt2 + 4;
                } else if (cCharAt2 != '+' && cCharAt2 != '-') {
                    if (cCharAt2 != '/' && cCharAt2 != '_') {
                        if (cCharAt2 != '\n' && cCharAt2 != '\r' && cCharAt2 != ' ' && cCharAt2 != '\t') {
                            break;
                        }
                        i11++;
                    } else {
                        i7 = 63;
                    }
                } else {
                    i7 = 62;
                }
                int i15 = i7 | (i13 << 6);
                i12++;
                if (i12 % 4 == 0) {
                    bArrCopyOf[i14] = (byte) (i15 >> 16);
                    int i16 = i14 + 2;
                    bArrCopyOf[i14 + 1] = (byte) (i15 >> 8);
                    i14 += 3;
                    bArrCopyOf[i16] = (byte) i15;
                }
                i13 = i15;
                i11++;
            } else {
                int i17 = i12 % 4;
                if (i17 != 1) {
                    if (i17 == 2) {
                        bArrCopyOf[i14] = (byte) ((i13 << 12) >> 16);
                        i14 = 1 + i14;
                    } else if (i17 == 3) {
                        int i18 = i13 << 6;
                        int i19 = 1 + i14;
                        bArrCopyOf[i14] = (byte) (i18 >> 16);
                        i14 += 2;
                        bArrCopyOf[i19] = (byte) (i18 >> 8);
                    }
                    if (i14 != i10) {
                        bArrCopyOf = Arrays.copyOf(bArrCopyOf, i14);
                        m.e(bArrCopyOf, "copyOf(...)");
                    }
                }
            }
        }
        bArrCopyOf = null;
        if (bArrCopyOf != null) {
            return new C2322l(bArrCopyOf);
        }
        return null;
    }

    public static C2322l o(String str) {
        if (str.length() % 2 != 0) {
            throw new IllegalArgumentException("Unexpected hex string: ".concat(str).toString());
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i7 = 0; i7 < length; i7++) {
            int i10 = i7 * 2;
            bArr[i7] = (byte) (s9.b.a(str.charAt(i10 + 1)) + (s9.b.a(str.charAt(i10)) << 4));
        }
        return new C2322l(bArr);
    }

    public static C2322l p(String str) {
        m.f(str, "<this>");
        byte[] bytes = str.getBytes(Q8.a.f7211a);
        m.e(bytes, "getBytes(...)");
        C2322l c2322l = new C2322l(bytes);
        c2322l.f21934n = str;
        return c2322l;
    }

    public static Font r(FontFamily fontFamily, int i7) {
        FontStyle fontStyle = new FontStyle((i7 & 1) != 0 ? 700 : 400, (i7 & 2) != 0 ? 1 : 0);
        Font font = fontFamily.getFont(0);
        int iQ = Q(fontStyle, font.getStyle());
        for (int i10 = 1; i10 < fontFamily.getSize(); i10++) {
            Font font2 = fontFamily.getFont(i10);
            int iQ2 = Q(fontStyle, font2.getStyle());
            if (iQ2 < iQ) {
                font = font2;
                iQ = iQ2;
            }
        }
        return font;
    }

    public static float t0(C0761q c0761q) {
        c0761q.Q(-1525447886);
        float f10 = 10;
        c0761q.p(false);
        return f10;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0058  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float A(T.C0761q r6) {
        /*
            r5 = this;
            r0 = -1793148270(0xffffffff951eba92, float:-3.2055024E-26)
            r6.Q(r0)
            r0 = 200(0xc8, float:2.8E-43)
            float r0 = (float) r0
            r1 = 250(0xfa, float:3.5E-43)
            float r1 = (float) r1
            T.y r2 = k6.AbstractC1692D.f18238a
            java.lang.Object r2 = r6.k(r2)
            com.stingray.karaoke.car.utils.LayoutMode r2 = (com.stingray.karaoke.car.utils.LayoutMode) r2
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            r4 = 0
            if (r2 != r3) goto L1a
            goto L59
        L1a:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r2 != r3) goto L1f
            goto L59
        L1f:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r2 != r3) goto L24
            goto L58
        L24:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            if (r2 == r3) goto L58
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r2 == r3) goto L58
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r2 != r3) goto L31
            goto L58
        L31:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r2 == r3) goto L44
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r2 == r3) goto L44
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r2 != r3) goto L3e
            goto L44
        L3e:
            A9.k r5 = new A9.k
            r5.<init>()
            throw r5
        L44:
            r2 = -609592150(0xffffffffdbaa5caa, float:-9.590526E16)
            boolean r2 = M5.b.r(r6, r2, r5, r6, r4)
            if (r2 == 0) goto L4e
            goto L59
        L4e:
            r2 = -609591093(0xffffffffdbaa60cb, float:-9.591434E16)
            boolean r5 = A7.k.u(r6, r2, r5, r6, r4)
            if (r5 == 0) goto L58
            goto L59
        L58:
            r0 = r1
        L59:
            r6.p(r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.A(T.q):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float A0(T.C0761q r8) {
        /*
            r7 = this;
            r0 = 921581184(0x36ee3680, float:7.0992974E-6)
            r8.Q(r0)
            r0 = 100
            float r0 = (float) r0
            r1 = 130(0x82, float:1.82E-43)
            float r1 = (float) r1
            r2 = 150(0x96, float:2.1E-43)
            float r2 = (float) r2
            r3 = 120(0x78, float:1.68E-43)
            float r3 = (float) r3
            T.y r4 = k6.AbstractC1692D.f18238a
            java.lang.Object r4 = r8.k(r4)
            com.stingray.karaoke.car.utils.LayoutMode r4 = (com.stingray.karaoke.car.utils.LayoutMode) r4
            com.stingray.karaoke.car.utils.LayoutMode r5 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            r6 = 0
            if (r4 != r5) goto L20
            goto L62
        L20:
            com.stingray.karaoke.car.utils.LayoutMode r5 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r4 != r5) goto L25
            goto L53
        L25:
            com.stingray.karaoke.car.utils.LayoutMode r5 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r4 != r5) goto L2a
            goto L5f
        L2a:
            com.stingray.karaoke.car.utils.LayoutMode r5 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            if (r4 == r5) goto L61
            com.stingray.karaoke.car.utils.LayoutMode r5 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r4 == r5) goto L61
            com.stingray.karaoke.car.utils.LayoutMode r5 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r4 != r5) goto L37
            goto L61
        L37:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r4 == r3) goto L4a
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r4 == r3) goto L4a
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r4 != r3) goto L44
            goto L4a
        L44:
            A9.k r7 = new A9.k
            r7.<init>()
            throw r7
        L4a:
            r3 = -609592150(0xffffffffdbaa5caa, float:-9.590526E16)
            boolean r3 = M5.b.r(r8, r3, r7, r8, r6)
            if (r3 == 0) goto L55
        L53:
            r0 = r1
            goto L62
        L55:
            r1 = -609591093(0xffffffffdbaa60cb, float:-9.591434E16)
            boolean r7 = A7.k.u(r8, r1, r7, r8, r6)
            if (r7 == 0) goto L5f
            goto L62
        L5f:
            r0 = r2
            goto L62
        L61:
            r0 = r3
        L62:
            r8.p(r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.A0(T.q):float");
    }

    public float B(C0761q c0761q) {
        c0761q.Q(-101981632);
        float f10 = 4;
        float f11 = 6;
        float f12 = 8;
        switch (a.f19251a[((LayoutMode) c0761q.k(AbstractC1692D.f18238a)).ordinal()]) {
            case 1:
            case 2:
            case 3:
                c0761q.Q(-2036471007);
                c0761q.p(false);
                break;
            case 4:
                c0761q.Q(-2036469598);
                c0761q.p(false);
                f10 = f11;
                break;
            case 5:
            case 6:
                c0761q.Q(-2036466911);
                c0761q.p(false);
                f10 = f12;
                break;
            case 7:
                c0761q.Q(-2036465437);
                f10 = i(this, c0761q) ? f11 : f12;
                c0761q.p(false);
                break;
            case 8:
                c0761q.Q(-2036462878);
                f10 = k(this, c0761q) ? f11 : f12;
                c0761q.p(false);
                break;
            case 9:
                c0761q.Q(-2036460330);
                c0761q.Q(-2036460225);
                boolean z6 = k(this, c0761q) || i(this, c0761q);
                c0761q.p(false);
                f10 = z6 ? f11 : f12;
                c0761q.p(false);
                break;
            default:
                throw M5.b.d(-2036475262, c0761q, false);
        }
        c0761q.p(false);
        return f10;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float B0(T.C0761q r7) {
        /*
            r6 = this;
            r0 = 1516872820(0x5a69a474, float:1.6441122E16)
            r7.Q(r0)
            r0 = 5
            float r0 = (float) r0
            r1 = 6
            float r1 = (float) r1
            r2 = 8
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            r5 = 0
            if (r3 != r4) goto L1b
            goto L4d
        L1b:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 != r4) goto L20
            goto L59
        L20:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L59
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L29
            goto L59
        L29:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 == r4) goto L44
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            if (r3 == r4) goto L44
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L44
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 == r4) goto L44
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L3e
            goto L44
        L3e:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L44:
            r3 = 398348334(0x17be502e, float:1.2298702E-24)
            boolean r3 = A7.k.t(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L4f
        L4d:
            r0 = r1
            goto L5a
        L4f:
            r1 = 398349423(0x17be546f, float:1.2299776E-24)
            boolean r6 = A7.k.r(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L59
            goto L5a
        L59:
            r0 = r2
        L5a:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.B0(T.q):float");
    }

    public float C(C0761q c0761q) {
        c0761q.Q(559458560);
        float f10 = 8;
        float f11 = 12;
        float f12 = 16;
        switch (a.f19251a[((LayoutMode) c0761q.k(AbstractC1692D.f18238a)).ordinal()]) {
            case 1:
            case 2:
            case 3:
                c0761q.Q(-2036471007);
                c0761q.p(false);
                break;
            case 4:
                c0761q.Q(-2036469598);
                c0761q.p(false);
                f10 = f11;
                break;
            case 5:
            case 6:
                c0761q.Q(-2036466911);
                c0761q.p(false);
                f10 = f12;
                break;
            case 7:
                c0761q.Q(-2036465437);
                f10 = i(this, c0761q) ? f11 : f12;
                c0761q.p(false);
                break;
            case 8:
                c0761q.Q(-2036462878);
                f10 = k(this, c0761q) ? f11 : f12;
                c0761q.p(false);
                break;
            case 9:
                c0761q.Q(-2036460330);
                c0761q.Q(-2036460225);
                boolean z6 = k(this, c0761q) || i(this, c0761q);
                c0761q.p(false);
                f10 = z6 ? f11 : f12;
                c0761q.p(false);
                break;
            default:
                throw M5.b.d(-2036475262, c0761q, false);
        }
        c0761q.p(false);
        return f10;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float C0(int r7, T.C0761q r8) {
        /*
            r6 = this;
            r7 = 1845804234(0x6e04bcca, float:1.0270063E28)
            r8.Q(r7)
            r7 = 16
            float r7 = (float) r7
            r0 = 20
            float r0 = (float) r0
            r1 = 38
            float r1 = (float) r1
            r2 = 44
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r8.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            r5 = 0
            if (r3 != r4) goto L20
            goto L62
        L20:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 != r4) goto L25
            goto L53
        L25:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 != r4) goto L2a
            goto L5f
        L2a:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            if (r3 == r4) goto L61
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L61
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L37
            goto L61
        L37:
            com.stingray.karaoke.car.utils.LayoutMode r2 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r2) goto L4a
            com.stingray.karaoke.car.utils.LayoutMode r2 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 == r2) goto L4a
            com.stingray.karaoke.car.utils.LayoutMode r2 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r2) goto L44
            goto L4a
        L44:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L4a:
            r2 = -609592150(0xffffffffdbaa5caa, float:-9.590526E16)
            boolean r2 = M5.b.r(r8, r2, r6, r8, r5)
            if (r2 == 0) goto L55
        L53:
            r7 = r0
            goto L62
        L55:
            r0 = -609591093(0xffffffffdbaa60cb, float:-9.591434E16)
            boolean r6 = A7.k.u(r8, r0, r6, r8, r5)
            if (r6 == 0) goto L5f
            goto L62
        L5f:
            r7 = r1
            goto L62
        L61:
            r7 = r2
        L62:
            r8.p(r5)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.C0(int, T.q):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float D(T.C0761q r7) {
        /*
            r6 = this;
            r0 = 1177707360(0x46326360, float:11416.844)
            r7.Q(r0)
            r0 = 40
            float r0 = (float) r0
            r1 = 56
            float r1 = (float) r1
            r2 = 72
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            r5 = 0
            if (r3 != r4) goto L1d
            goto L4f
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 != r4) goto L22
            goto L5b
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L5b
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L2b
            goto L5b
        L2b:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L40
            goto L46
        L40:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L46:
            r3 = 398348334(0x17be502e, float:1.2298702E-24)
            boolean r3 = A7.k.t(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L51
        L4f:
            r0 = r1
            goto L5c
        L51:
            r1 = 398349423(0x17be546f, float:1.2299776E-24)
            boolean r6 = A7.k.r(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L5b
            goto L5c
        L5b:
            r0 = r2
        L5c:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.D(T.q):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float D0(int r6, T.C0761q r7) {
        /*
            r5 = this;
            r6 = 1575027072(0x5de10180, float:2.0266726E18)
            r7.Q(r6)
            r6 = 30
            float r6 = (float) r6
            r0 = 34
            float r0 = (float) r0
            r1 = 38
            float r1 = (float) r1
            T.y r2 = k6.AbstractC1692D.f18238a
            java.lang.Object r2 = r7.k(r2)
            com.stingray.karaoke.car.utils.LayoutMode r2 = (com.stingray.karaoke.car.utils.LayoutMode) r2
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            r4 = 0
            if (r2 != r3) goto L1d
            goto L5d
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r2 != r3) goto L22
            goto L50
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r2 != r3) goto L27
            goto L5c
        L27:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            if (r2 == r3) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r2 == r3) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r2 != r3) goto L34
            goto L5c
        L34:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r2 == r3) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r2 == r3) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r2 != r3) goto L41
            goto L47
        L41:
            A9.k r5 = new A9.k
            r5.<init>()
            throw r5
        L47:
            r2 = -609592150(0xffffffffdbaa5caa, float:-9.590526E16)
            boolean r2 = M5.b.r(r7, r2, r5, r7, r4)
            if (r2 == 0) goto L52
        L50:
            r6 = r0
            goto L5d
        L52:
            r0 = -609591093(0xffffffffdbaa60cb, float:-9.591434E16)
            boolean r5 = A7.k.u(r7, r0, r5, r7, r4)
            if (r5 == 0) goto L5c
            goto L5d
        L5c:
            r6 = r1
        L5d:
            r7.p(r4)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.D0(int, T.q):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float E(T.C0761q r7) {
        /*
            r6 = this;
            r0 = 1199005674(0x47775fea, float:63327.914)
            r7.Q(r0)
            r0 = 4
            float r0 = (float) r0
            r1 = 6
            float r1 = (float) r1
            r2 = 8
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            r5 = 0
            if (r3 != r4) goto L1b
            goto L4d
        L1b:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 != r4) goto L20
            goto L59
        L20:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L59
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L29
            goto L59
        L29:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 == r4) goto L44
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            if (r3 == r4) goto L44
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L44
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 == r4) goto L44
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L3e
            goto L44
        L3e:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L44:
            r3 = 398348334(0x17be502e, float:1.2298702E-24)
            boolean r3 = A7.k.t(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L4f
        L4d:
            r0 = r1
            goto L5a
        L4f:
            r1 = 398349423(0x17be546f, float:1.2299776E-24)
            boolean r6 = A7.k.r(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L59
            goto L5a
        L59:
            r0 = r2
        L5a:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.E(T.q):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float E0(T.C0761q r7) {
        /*
            r6 = this;
            r0 = -1732312160(0xffffffff98bf03a0, float:-4.9376003E-24)
            r7.Q(r0)
            r0 = 16
            float r0 = (float) r0
            r1 = 20
            float r1 = (float) r1
            r2 = 24
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            r5 = 0
            if (r3 != r4) goto L1d
            goto L5d
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 != r4) goto L22
            goto L50
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 != r4) goto L27
            goto L5c
        L27:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L34
            goto L5c
        L34:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L41
            goto L47
        L41:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L47:
            r3 = -609592150(0xffffffffdbaa5caa, float:-9.590526E16)
            boolean r3 = M5.b.r(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L52
        L50:
            r0 = r1
            goto L5d
        L52:
            r1 = -609591093(0xffffffffdbaa60cb, float:-9.591434E16)
            boolean r6 = A7.k.u(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L5c
            goto L5d
        L5c:
            r0 = r2
        L5d:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.E0(T.q):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float F(T.C0761q r7) {
        /*
            r6 = this;
            r0 = 132293538(0x7e2a3a2, float:3.4100875E-34)
            r7.Q(r0)
            r0 = 10
            float r0 = (float) r0
            r1 = 14
            float r1 = (float) r1
            r2 = 16
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            r5 = 0
            if (r3 != r4) goto L1d
            goto L5d
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 != r4) goto L22
            goto L50
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 != r4) goto L27
            goto L5c
        L27:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L34
            goto L5c
        L34:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L41
            goto L47
        L41:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L47:
            r3 = -609592150(0xffffffffdbaa5caa, float:-9.590526E16)
            boolean r3 = M5.b.r(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L52
        L50:
            r0 = r1
            goto L5d
        L52:
            r1 = -609591093(0xffffffffdbaa60cb, float:-9.591434E16)
            boolean r6 = A7.k.u(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L5c
            goto L5d
        L5c:
            r0 = r2
        L5d:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.F(T.q):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float F0(T.C0761q r7) {
        /*
            r6 = this;
            r0 = -519132256(0xffffffffe10eaba0, float:-1.6448778E20)
            r7.Q(r0)
            r0 = 12
            float r0 = (float) r0
            r1 = 24
            float r1 = (float) r1
            r2 = 40
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            r5 = 0
            if (r3 != r4) goto L1d
            goto L4f
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 != r4) goto L22
            goto L5b
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L5b
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L2b
            goto L5b
        L2b:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L40
            goto L46
        L40:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L46:
            r3 = 398348334(0x17be502e, float:1.2298702E-24)
            boolean r3 = A7.k.t(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L51
        L4f:
            r0 = r1
            goto L5c
        L51:
            r1 = 398349423(0x17be546f, float:1.2299776E-24)
            boolean r6 = A7.k.r(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L5b
            goto L5c
        L5b:
            r0 = r2
        L5c:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.F0(T.q):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float G(T.C0761q r7) {
        /*
            r6 = this;
            r0 = 1030972544(0x3d736480, float:0.059422016)
            r7.Q(r0)
            r0 = 18
            float r0 = (float) r0
            r1 = 22
            float r1 = (float) r1
            r2 = 24
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            r5 = 0
            if (r3 != r4) goto L1d
            goto L5d
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 != r4) goto L22
            goto L50
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 != r4) goto L27
            goto L5c
        L27:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L34
            goto L5c
        L34:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L41
            goto L47
        L41:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L47:
            r3 = -609592150(0xffffffffdbaa5caa, float:-9.590526E16)
            boolean r3 = M5.b.r(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L52
        L50:
            r0 = r1
            goto L5d
        L52:
            r1 = -609591093(0xffffffffdbaa60cb, float:-9.591434E16)
            boolean r6 = A7.k.u(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L5c
            goto L5d
        L5c:
            r0 = r2
        L5d:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.G(T.q):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float H(int r6, T.C0761q r7) {
        /*
            r5 = this;
            r6 = 2131931654(0x7f12b206, float:1.9499164E38)
            r7.Q(r6)
            r6 = 8
            float r6 = (float) r6
            r0 = 10
            float r0 = (float) r0
            r1 = 12
            float r1 = (float) r1
            T.y r2 = k6.AbstractC1692D.f18238a
            java.lang.Object r2 = r7.k(r2)
            com.stingray.karaoke.car.utils.LayoutMode r2 = (com.stingray.karaoke.car.utils.LayoutMode) r2
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            r4 = 0
            if (r2 != r3) goto L1d
            goto L4f
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r2 != r3) goto L22
            goto L5b
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r2 == r3) goto L5b
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r2 != r3) goto L2b
            goto L5b
        L2b:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r2 == r3) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            if (r2 == r3) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r2 == r3) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r2 == r3) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r2 != r3) goto L40
            goto L46
        L40:
            A9.k r5 = new A9.k
            r5.<init>()
            throw r5
        L46:
            r2 = 398348334(0x17be502e, float:1.2298702E-24)
            boolean r2 = A7.k.t(r7, r2, r5, r7, r4)
            if (r2 == 0) goto L51
        L4f:
            r6 = r0
            goto L5c
        L51:
            r0 = 398349423(0x17be546f, float:1.2299776E-24)
            boolean r5 = A7.k.r(r7, r0, r5, r7, r4)
            if (r5 == 0) goto L5b
            goto L5c
        L5b:
            r6 = r1
        L5c:
            r7.p(r4)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.H(int, T.q):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float I(T.C0761q r7) {
        /*
            r6 = this;
            r0 = 563773152(0x219a7ee0, float:1.0469029E-18)
            r7.Q(r0)
            r0 = 24
            float r0 = (float) r0
            r1 = 36
            float r1 = (float) r1
            r2 = 44
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            r5 = 0
            if (r3 != r4) goto L1d
            goto L4f
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 != r4) goto L22
            goto L5b
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L5b
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L2b
            goto L5b
        L2b:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L40
            goto L46
        L40:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L46:
            r3 = 398348334(0x17be502e, float:1.2298702E-24)
            boolean r3 = A7.k.t(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L51
        L4f:
            r0 = r1
            goto L5c
        L51:
            r1 = 398349423(0x17be546f, float:1.2299776E-24)
            boolean r6 = A7.k.r(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L5b
            goto L5c
        L5b:
            r0 = r2
        L5c:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.I(T.q):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float J(T.C0761q r7) {
        /*
            r6 = this;
            r0 = 1167801088(0x459b3b00, float:4967.375)
            r7.Q(r0)
            r0 = 10
            float r0 = (float) r0
            r1 = 15
            float r1 = (float) r1
            r2 = 20
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            r5 = 0
            if (r3 != r4) goto L1d
            goto L5d
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 != r4) goto L22
            goto L50
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 != r4) goto L27
            goto L5c
        L27:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L34
            goto L5c
        L34:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L41
            goto L47
        L41:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L47:
            r3 = -609592150(0xffffffffdbaa5caa, float:-9.590526E16)
            boolean r3 = M5.b.r(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L52
        L50:
            r0 = r1
            goto L5d
        L52:
            r1 = -609591093(0xffffffffdbaa60cb, float:-9.591434E16)
            boolean r6 = A7.k.u(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L5c
            goto L5d
        L5c:
            r0 = r2
        L5d:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.J(T.q):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float K(T.C0761q r7) {
        /*
            r6 = this;
            r0 = -1008060224(0xffffffffc3ea38c0, float:-468.44336)
            r7.Q(r0)
            r0 = 18
            float r0 = (float) r0
            r1 = 20
            float r1 = (float) r1
            r2 = 25
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            r5 = 0
            if (r3 != r4) goto L1d
            goto L4f
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 != r4) goto L22
            goto L5b
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L5b
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L2b
            goto L5b
        L2b:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L40
            goto L46
        L40:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L46:
            r3 = 398348334(0x17be502e, float:1.2298702E-24)
            boolean r3 = A7.k.t(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L51
        L4f:
            r0 = r1
            goto L5c
        L51:
            r1 = 398349423(0x17be546f, float:1.2299776E-24)
            boolean r6 = A7.k.r(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L5b
            goto L5c
        L5b:
            r0 = r2
        L5c:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.K(T.q):float");
    }

    public float M(C0761q c0761q) {
        c0761q.Q(1863222528);
        float f10 = 12;
        float f11 = 16;
        float f12 = 18;
        switch (a.f19251a[((LayoutMode) c0761q.k(AbstractC1692D.f18238a)).ordinal()]) {
            case 1:
            case 2:
            case 3:
                c0761q.Q(-2036471007);
                c0761q.p(false);
                break;
            case 4:
                c0761q.Q(-2036469598);
                c0761q.p(false);
                f10 = f11;
                break;
            case 5:
            case 6:
                c0761q.Q(-2036466911);
                c0761q.p(false);
                f10 = f12;
                break;
            case 7:
                c0761q.Q(-2036465437);
                f10 = i(this, c0761q) ? f11 : f12;
                c0761q.p(false);
                break;
            case 8:
                c0761q.Q(-2036462878);
                f10 = k(this, c0761q) ? f11 : f12;
                c0761q.p(false);
                break;
            case 9:
                c0761q.Q(-2036460330);
                c0761q.Q(-2036460225);
                boolean z6 = k(this, c0761q) || i(this, c0761q);
                c0761q.p(false);
                f10 = z6 ? f11 : f12;
                c0761q.p(false);
                break;
            default:
                throw M5.b.d(-2036475262, c0761q, false);
        }
        c0761q.p(false);
        return f10;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int N(T.C0761q r4) {
        /*
            r3 = this;
            r0 = -1115356415(0xffffffffbd850301, float:-0.064947136)
            r4.Q(r0)
            T.y r0 = k6.AbstractC1692D.f18238a
            java.lang.Object r0 = r4.k(r0)
            com.stingray.karaoke.car.utils.LayoutMode r0 = (com.stingray.karaoke.car.utils.LayoutMode) r0
            com.stingray.karaoke.car.utils.LayoutMode r1 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            r2 = 0
            if (r0 != r1) goto L14
            goto L52
        L14:
            com.stingray.karaoke.car.utils.LayoutMode r1 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r0 != r1) goto L19
            goto L47
        L19:
            com.stingray.karaoke.car.utils.LayoutMode r1 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r0 != r1) goto L1e
            goto L54
        L1e:
            com.stingray.karaoke.car.utils.LayoutMode r1 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            if (r0 == r1) goto L56
            com.stingray.karaoke.car.utils.LayoutMode r1 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r0 == r1) goto L56
            com.stingray.karaoke.car.utils.LayoutMode r1 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r0 != r1) goto L2b
            goto L56
        L2b:
            com.stingray.karaoke.car.utils.LayoutMode r1 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r0 == r1) goto L3e
            com.stingray.karaoke.car.utils.LayoutMode r1 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r0 == r1) goto L3e
            com.stingray.karaoke.car.utils.LayoutMode r1 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r0 != r1) goto L38
            goto L3e
        L38:
            A9.k r3 = new A9.k
            r3.<init>()
            throw r3
        L3e:
            r0 = -609592150(0xffffffffdbaa5caa, float:-9.590526E16)
            boolean r0 = M5.b.r(r4, r0, r3, r4, r2)
            if (r0 == 0) goto L49
        L47:
            r3 = 4
            goto L57
        L49:
            r0 = -609591093(0xffffffffdbaa60cb, float:-9.591434E16)
            boolean r3 = A7.k.u(r4, r0, r3, r4, r2)
            if (r3 == 0) goto L54
        L52:
            r3 = 2
            goto L57
        L54:
            r3 = 5
            goto L57
        L56:
            r3 = 6
        L57:
            r4.p(r2)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.N(T.q):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float O(int r6, T.C0761q r7) {
        /*
            r5 = this;
            r6 = 1803049120(0x6b7858a0, float:3.0023212E26)
            r7.Q(r6)
            r6 = 48
            float r6 = (float) r6
            r0 = 70
            float r0 = (float) r0
            r1 = 100
            float r1 = (float) r1
            T.y r2 = k6.AbstractC1692D.f18238a
            java.lang.Object r2 = r7.k(r2)
            com.stingray.karaoke.car.utils.LayoutMode r2 = (com.stingray.karaoke.car.utils.LayoutMode) r2
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            r4 = 0
            if (r2 != r3) goto L1d
            goto L5d
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r2 != r3) goto L22
            goto L50
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r2 != r3) goto L27
            goto L5c
        L27:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            if (r2 == r3) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r2 == r3) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r2 != r3) goto L34
            goto L5c
        L34:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r2 == r3) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r2 == r3) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r2 != r3) goto L41
            goto L47
        L41:
            A9.k r5 = new A9.k
            r5.<init>()
            throw r5
        L47:
            r2 = -609592150(0xffffffffdbaa5caa, float:-9.590526E16)
            boolean r2 = M5.b.r(r7, r2, r5, r7, r4)
            if (r2 == 0) goto L52
        L50:
            r6 = r0
            goto L5d
        L52:
            r0 = -609591093(0xffffffffdbaa60cb, float:-9.591434E16)
            boolean r5 = A7.k.u(r7, r0, r5, r7, r4)
            if (r5 == 0) goto L5c
            goto L5d
        L5c:
            r6 = r1
        L5d:
            r7.p(r4)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.O(int, T.q):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float P(T.C0761q r7) {
        /*
            r6 = this;
            r0 = 1612598116(0x601e4b64, float:4.562528E19)
            r7.Q(r0)
            r0 = 30
            float r0 = (float) r0
            r1 = 35
            float r1 = (float) r1
            r2 = 40
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            r5 = 0
            if (r3 != r4) goto L1d
            goto L5d
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 != r4) goto L22
            goto L50
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 != r4) goto L27
            goto L5c
        L27:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L34
            goto L5c
        L34:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L41
            goto L47
        L41:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L47:
            r3 = -609592150(0xffffffffdbaa5caa, float:-9.590526E16)
            boolean r3 = M5.b.r(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L52
        L50:
            r0 = r1
            goto L5d
        L52:
            r1 = -609591093(0xffffffffdbaa60cb, float:-9.591434E16)
            boolean r6 = A7.k.u(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L5c
            goto L5d
        L5c:
            r0 = r2
        L5d:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.P(T.q):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float R(T.C0761q r7) {
        /*
            r6 = this;
            r0 = -1680125564(0xffffffff9bdb5184, float:-3.628319E-22)
            r7.Q(r0)
            r0 = 80
            float r0 = (float) r0
            r1 = 90
            float r1 = (float) r1
            r2 = 110(0x6e, float:1.54E-43)
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            r5 = 0
            if (r3 != r4) goto L1d
            goto L5d
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 != r4) goto L22
            goto L50
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 != r4) goto L27
            goto L5c
        L27:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            if (r3 == r4) goto L50
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L50
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L34
            goto L50
        L34:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L41
            goto L47
        L41:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L47:
            r3 = -609592150(0xffffffffdbaa5caa, float:-9.590526E16)
            boolean r3 = M5.b.r(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L52
        L50:
            r0 = r1
            goto L5d
        L52:
            r1 = -609591093(0xffffffffdbaa60cb, float:-9.591434E16)
            boolean r6 = A7.k.u(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L5c
            goto L5d
        L5c:
            r0 = r2
        L5d:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.R(T.q):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float S(T.C0761q r7) {
        /*
            r6 = this;
            r0 = -1072167924(0xffffffffc018040c, float:-2.375247)
            r7.Q(r0)
            r0 = 12
            float r0 = (float) r0
            r1 = 16
            float r1 = (float) r1
            r2 = 20
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            r5 = 0
            if (r3 != r4) goto L1d
            goto L5d
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 != r4) goto L22
            goto L50
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 != r4) goto L27
            goto L5c
        L27:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L34
            goto L5c
        L34:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L41
            goto L47
        L41:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L47:
            r3 = -609592150(0xffffffffdbaa5caa, float:-9.590526E16)
            boolean r3 = M5.b.r(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L52
        L50:
            r0 = r1
            goto L5d
        L52:
            r1 = -609591093(0xffffffffdbaa60cb, float:-9.591434E16)
            boolean r6 = A7.k.u(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L5c
            goto L5d
        L5c:
            r0 = r2
        L5d:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.S(T.q):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float T(T.C0761q r7) {
        /*
            r6 = this;
            r0 = 1540955880(0x5bd91ee8, float:1.222281E17)
            r7.Q(r0)
            r0 = 36
            float r0 = (float) r0
            r1 = 40
            float r1 = (float) r1
            r2 = 44
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            r5 = 0
            if (r3 != r4) goto L1d
            goto L5d
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 != r4) goto L22
            goto L50
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 != r4) goto L27
            goto L5c
        L27:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L34
            goto L5c
        L34:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L41
            goto L47
        L41:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L47:
            r3 = -609592150(0xffffffffdbaa5caa, float:-9.590526E16)
            boolean r3 = M5.b.r(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L52
        L50:
            r0 = r1
            goto L5d
        L52:
            r1 = -609591093(0xffffffffdbaa60cb, float:-9.591434E16)
            boolean r6 = A7.k.u(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L5c
            goto L5d
        L5c:
            r0 = r2
        L5d:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.T(T.q):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float U(T.C0761q r7) {
        /*
            r6 = this;
            r0 = 43229216(0x293a020, float:2.1691616E-37)
            r7.Q(r0)
            r0 = 70
            float r0 = (float) r0
            r1 = 85
            float r1 = (float) r1
            r2 = 100
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            r5 = 0
            if (r3 != r4) goto L1d
            goto L4f
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 != r4) goto L22
            goto L5b
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L5b
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L2b
            goto L5b
        L2b:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L40
            goto L46
        L40:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L46:
            r3 = 398348334(0x17be502e, float:1.2298702E-24)
            boolean r3 = A7.k.t(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L51
        L4f:
            r0 = r1
            goto L5c
        L51:
            r1 = 398349423(0x17be546f, float:1.2299776E-24)
            boolean r6 = A7.k.r(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L5b
            goto L5c
        L5b:
            r0 = r2
        L5c:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.U(T.q):float");
    }

    public float V(C0761q c0761q) {
        c0761q.Q(588427936);
        float f10 = 30;
        float f11 = 45;
        switch (a.f19251a[((LayoutMode) c0761q.k(AbstractC1692D.f18238a)).ordinal()]) {
            case 1:
            case 2:
            case 3:
                c0761q.Q(-2036471007);
                c0761q.p(false);
                c0761q.p(false);
                return f10;
            case 4:
                c0761q.Q(-2036469598);
                c0761q.p(false);
                f10 = f11;
                c0761q.p(false);
                return f10;
            case 5:
            case 6:
                c0761q.Q(-2036466911);
                c0761q.p(false);
                f10 = f11;
                c0761q.p(false);
                return f10;
            case 7:
                c0761q.Q(-2036465437);
                i(this, c0761q);
                c0761q.p(false);
                f10 = f11;
                c0761q.p(false);
                return f10;
            case 8:
                c0761q.Q(-2036462878);
                k(this, c0761q);
                c0761q.p(false);
                f10 = f11;
                c0761q.p(false);
                return f10;
            case 9:
                c0761q.Q(-2036460330);
                c0761q.Q(-2036460225);
                if (!k(this, c0761q)) {
                    i(this, c0761q);
                }
                c0761q.p(false);
                c0761q.p(false);
                f10 = f11;
                c0761q.p(false);
                return f10;
            default:
                throw M5.b.d(-2036475262, c0761q, false);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float W(T.C0761q r7) {
        /*
            r6 = this;
            r0 = -1949169710(0xffffffff8bd207d2, float:-8.0900824E-32)
            r7.Q(r0)
            r0 = 15
            float r0 = (float) r0
            r1 = 20
            float r1 = (float) r1
            r2 = 23
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            r5 = 0
            if (r3 != r4) goto L1d
            goto L5d
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 != r4) goto L22
            goto L50
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 != r4) goto L27
            goto L5c
        L27:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L34
            goto L5c
        L34:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L41
            goto L47
        L41:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L47:
            r3 = -609592150(0xffffffffdbaa5caa, float:-9.590526E16)
            boolean r3 = M5.b.r(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L52
        L50:
            r0 = r1
            goto L5d
        L52:
            r1 = -609591093(0xffffffffdbaa60cb, float:-9.591434E16)
            boolean r6 = A7.k.u(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L5c
            goto L5d
        L5c:
            r0 = r2
        L5d:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.W(T.q):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float X(T.C0761q r7) {
        /*
            r6 = this;
            r0 = 1797877600(0x6b296f60, float:2.0483442E26)
            r7.Q(r0)
            r0 = 10
            float r0 = (float) r0
            r1 = 12
            float r1 = (float) r1
            r2 = 15
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            r5 = 0
            if (r3 != r4) goto L1d
            goto L5d
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 != r4) goto L22
            goto L50
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 != r4) goto L27
            goto L5c
        L27:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L34
            goto L5c
        L34:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L41
            goto L47
        L41:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L47:
            r3 = -609592150(0xffffffffdbaa5caa, float:-9.590526E16)
            boolean r3 = M5.b.r(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L52
        L50:
            r0 = r1
            goto L5d
        L52:
            r1 = -609591093(0xffffffffdbaa60cb, float:-9.591434E16)
            boolean r6 = A7.k.u(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L5c
            goto L5d
        L5c:
            r0 = r2
        L5d:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.X(T.q):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float Y(T.C0761q r7) {
        /*
            r6 = this;
            r0 = -2071091764(0xffffffff848da5cc, float:-3.33012E-36)
            r7.Q(r0)
            r0 = 65
            float r0 = (float) r0
            r1 = 75
            float r1 = (float) r1
            r2 = 100
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            r5 = 0
            if (r3 != r4) goto L1d
            goto L4f
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 != r4) goto L22
            goto L5b
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L5b
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L2b
            goto L5b
        L2b:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L40
            goto L46
        L40:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L46:
            r3 = 398348334(0x17be502e, float:1.2298702E-24)
            boolean r3 = A7.k.t(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L51
        L4f:
            r0 = r1
            goto L5c
        L51:
            r1 = 398349423(0x17be546f, float:1.2299776E-24)
            boolean r6 = A7.k.r(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L5b
            goto L5c
        L5b:
            r0 = r2
        L5c:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.Y(T.q):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float Z(T.C0761q r7) {
        /*
            r6 = this;
            r0 = 893663328(0x35443860, float:7.3097726E-7)
            r7.Q(r0)
            r0 = 70
            float r0 = (float) r0
            r1 = 80
            float r1 = (float) r1
            r2 = 100
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            r5 = 0
            if (r3 != r4) goto L1d
            goto L4f
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 != r4) goto L22
            goto L5b
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L5b
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L2b
            goto L5b
        L2b:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L40
            goto L46
        L40:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L46:
            r3 = 398348334(0x17be502e, float:1.2298702E-24)
            boolean r3 = A7.k.t(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L51
        L4f:
            r0 = r1
            goto L5c
        L51:
            r1 = 398349423(0x17be546f, float:1.2299776E-24)
            boolean r6 = A7.k.r(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L5b
            goto L5c
        L5b:
            r0 = r2
        L5c:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.Z(T.q):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float a0(T.C0761q r7) {
        /*
            r6 = this;
            r0 = 1532221954(0x5b53da02, float:5.9630922E16)
            r7.Q(r0)
            r0 = 55
            float r0 = (float) r0
            r1 = 75
            float r1 = (float) r1
            r2 = 105(0x69, float:1.47E-43)
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            r5 = 0
            if (r3 != r4) goto L1d
            goto L5d
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 != r4) goto L22
            goto L50
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 != r4) goto L27
            goto L5c
        L27:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L34
            goto L5c
        L34:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L41
            goto L47
        L41:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L47:
            r3 = -609592150(0xffffffffdbaa5caa, float:-9.590526E16)
            boolean r3 = M5.b.r(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L52
        L50:
            r0 = r1
            goto L5d
        L52:
            r1 = -609591093(0xffffffffdbaa60cb, float:-9.591434E16)
            boolean r6 = A7.k.u(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L5c
            goto L5d
        L5c:
            r0 = r2
        L5d:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.a0(T.q):float");
    }

    @Override // s2.t
    public MediaCodecInfo b(int i7) {
        return MediaCodecList.getCodecInfoAt(i7);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float b0(T.C0761q r7) {
        /*
            r6 = this;
            r0 = 1456991072(0x56d7eb60, float:1.1870296E14)
            r7.Q(r0)
            r0 = 16
            float r0 = (float) r0
            r1 = 24
            float r1 = (float) r1
            r2 = 32
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            r5 = 0
            if (r3 != r4) goto L1d
            goto L5d
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 != r4) goto L22
            goto L50
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 != r4) goto L27
            goto L5c
        L27:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L34
            goto L5c
        L34:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L41
            goto L47
        L41:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L47:
            r3 = -609592150(0xffffffffdbaa5caa, float:-9.590526E16)
            boolean r3 = M5.b.r(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L52
        L50:
            r0 = r1
            goto L5d
        L52:
            r1 = -609591093(0xffffffffdbaa60cb, float:-9.591434E16)
            boolean r6 = A7.k.u(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L5c
            goto L5d
        L5c:
            r0 = r2
        L5d:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.b0(T.q):float");
    }

    @Override // s2.t
    public boolean c(String str, MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float c0(T.C0761q r6) {
        /*
            r5 = this;
            r0 = 1272545120(0x4bd97f60, float:2.850784E7)
            r6.Q(r0)
            r0 = 3
            float r0 = (float) r0
            r1 = 4
            float r1 = (float) r1
            T.y r2 = k6.AbstractC1692D.f18238a
            java.lang.Object r2 = r6.k(r2)
            com.stingray.karaoke.car.utils.LayoutMode r2 = (com.stingray.karaoke.car.utils.LayoutMode) r2
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            r4 = 0
            if (r2 != r3) goto L18
            goto L4a
        L18:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r2 != r3) goto L1d
            goto L4a
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r2 == r3) goto L4a
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r2 != r3) goto L26
            goto L4a
        L26:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r2 == r3) goto L41
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            if (r2 == r3) goto L41
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r2 == r3) goto L41
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r2 == r3) goto L41
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r2 != r3) goto L3b
            goto L41
        L3b:
            A9.k r5 = new A9.k
            r5.<init>()
            throw r5
        L41:
            r2 = 398348334(0x17be502e, float:1.2298702E-24)
            boolean r2 = A7.k.t(r6, r2, r5, r6, r4)
            if (r2 == 0) goto L4c
        L4a:
            r0 = r1
            goto L55
        L4c:
            r2 = 398349423(0x17be546f, float:1.2299776E-24)
            boolean r5 = A7.k.r(r6, r2, r5, r6, r4)
            if (r5 == 0) goto L4a
        L55:
            r6.p(r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.c0(T.q):float");
    }

    @Override // s2.t
    public int d() {
        return MediaCodecList.getCodecCount();
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float d0(T.C0761q r7) {
        /*
            r6 = this;
            r0 = -2058239136(0xffffffff8551c360, float:-9.8630175E-36)
            r7.Q(r0)
            r0 = 14
            float r0 = (float) r0
            r1 = 18
            float r1 = (float) r1
            r2 = 24
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            r5 = 0
            if (r3 != r4) goto L1d
            goto L4f
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 != r4) goto L22
            goto L5b
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L5b
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L2b
            goto L5b
        L2b:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L40
            goto L46
        L40:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L46:
            r3 = 398348334(0x17be502e, float:1.2298702E-24)
            boolean r3 = A7.k.t(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L51
        L4f:
            r0 = r1
            goto L5c
        L51:
            r1 = 398349423(0x17be546f, float:1.2299776E-24)
            boolean r6 = A7.k.r(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L5b
            goto L5c
        L5b:
            r0 = r2
        L5c:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.d0(T.q):float");
    }

    @Override // s2.t
    public boolean e(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return "secure-playback".equals(str) && "video/avc".equals(str2);
    }

    public float e0(C0761q c0761q) {
        c0761q.Q(-1593412908);
        float f10 = 16;
        float f11 = 18;
        float f12 = 20;
        switch (a.f19251a[((LayoutMode) c0761q.k(AbstractC1692D.f18238a)).ordinal()]) {
            case 1:
            case 2:
            case 3:
                c0761q.Q(-2036471007);
                c0761q.p(false);
                break;
            case 4:
                c0761q.Q(-2036469598);
                c0761q.p(false);
                f10 = f11;
                break;
            case 5:
            case 6:
                c0761q.Q(-2036466911);
                c0761q.p(false);
                f10 = f12;
                break;
            case 7:
                c0761q.Q(-2036465437);
                f10 = i(this, c0761q) ? f11 : f12;
                c0761q.p(false);
                break;
            case 8:
                c0761q.Q(-2036462878);
                f10 = k(this, c0761q) ? f11 : f12;
                c0761q.p(false);
                break;
            case 9:
                c0761q.Q(-2036460330);
                c0761q.Q(-2036460225);
                boolean z6 = k(this, c0761q) || i(this, c0761q);
                c0761q.p(false);
                f10 = z6 ? f11 : f12;
                c0761q.p(false);
                break;
            default:
                throw M5.b.d(-2036475262, c0761q, false);
        }
        c0761q.p(false);
        return f10;
    }

    @Override // q3.InterfaceC2177d
    public void f(int i7, Serializable serializable) {
    }

    public float f0(C0761q c0761q) {
        c0761q.Q(1125507584);
        float f10 = 10;
        float f11 = 11;
        float f12 = 12;
        switch (a.f19251a[((LayoutMode) c0761q.k(AbstractC1692D.f18238a)).ordinal()]) {
            case 1:
            case 2:
            case 3:
                c0761q.Q(-2036471007);
                c0761q.p(false);
                break;
            case 4:
                c0761q.Q(-2036469598);
                c0761q.p(false);
                f10 = f11;
                break;
            case 5:
            case 6:
                c0761q.Q(-2036466911);
                c0761q.p(false);
                f10 = f12;
                break;
            case 7:
                c0761q.Q(-2036465437);
                f10 = i(this, c0761q) ? f11 : f12;
                c0761q.p(false);
                break;
            case 8:
                c0761q.Q(-2036462878);
                f10 = k(this, c0761q) ? f11 : f12;
                c0761q.p(false);
                break;
            case 9:
                c0761q.Q(-2036460330);
                c0761q.Q(-2036460225);
                boolean z6 = k(this, c0761q) || i(this, c0761q);
                c0761q.p(false);
                f10 = z6 ? f11 : f12;
                c0761q.p(false);
                break;
            default:
                throw M5.b.d(-2036475262, c0761q, false);
        }
        c0761q.p(false);
        return f10;
    }

    @Override // s2.t
    public boolean g() {
        return false;
    }

    public float g0(C0761q c0761q) {
        c0761q.Q(-1448674452);
        float f10 = 4;
        float f11 = 5;
        float f12 = 6;
        switch (a.f19251a[((LayoutMode) c0761q.k(AbstractC1692D.f18238a)).ordinal()]) {
            case 1:
            case 2:
            case 3:
                c0761q.Q(-2036471007);
                c0761q.p(false);
                break;
            case 4:
                c0761q.Q(-2036469598);
                c0761q.p(false);
                f10 = f11;
                break;
            case 5:
            case 6:
                c0761q.Q(-2036466911);
                c0761q.p(false);
                f10 = f12;
                break;
            case 7:
                c0761q.Q(-2036465437);
                f10 = i(this, c0761q) ? f11 : f12;
                c0761q.p(false);
                break;
            case 8:
                c0761q.Q(-2036462878);
                f10 = k(this, c0761q) ? f11 : f12;
                c0761q.p(false);
                break;
            case 9:
                c0761q.Q(-2036460330);
                c0761q.Q(-2036460225);
                boolean z6 = k(this, c0761q) || i(this, c0761q);
                c0761q.p(false);
                f10 = z6 ? f11 : f12;
                c0761q.p(false);
                break;
            default:
                throw M5.b.d(-2036475262, c0761q, false);
        }
        c0761q.p(false);
        return f10;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float h0(T.C0761q r6) {
        /*
            r5 = this;
            r0 = -558150656(0xffffffffdebb4c00, float:-6.748081E18)
            r6.Q(r0)
            r0 = 200(0xc8, float:2.8E-43)
            float r0 = (float) r0
            r1 = 300(0x12c, float:4.2E-43)
            float r1 = (float) r1
            T.y r2 = k6.AbstractC1692D.f18238a
            java.lang.Object r2 = r6.k(r2)
            com.stingray.karaoke.car.utils.LayoutMode r2 = (com.stingray.karaoke.car.utils.LayoutMode) r2
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            r4 = 0
            if (r2 != r3) goto L1a
            goto L4c
        L1a:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r2 != r3) goto L1f
            goto L4c
        L1f:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r2 == r3) goto L4c
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r2 != r3) goto L28
            goto L4c
        L28:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r2 == r3) goto L43
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            if (r2 == r3) goto L43
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r2 == r3) goto L43
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r2 == r3) goto L43
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r2 != r3) goto L3d
            goto L43
        L3d:
            A9.k r5 = new A9.k
            r5.<init>()
            throw r5
        L43:
            r2 = 398348334(0x17be502e, float:1.2298702E-24)
            boolean r2 = A7.k.t(r6, r2, r5, r6, r4)
            if (r2 == 0) goto L4e
        L4c:
            r0 = r1
            goto L57
        L4e:
            r2 = 398349423(0x17be546f, float:1.2299776E-24)
            boolean r5 = A7.k.r(r6, r2, r5, r6, r4)
            if (r5 == 0) goto L4c
        L57:
            r6.p(r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.h0(T.q):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float i0(T.C0761q r5) {
        /*
            r4 = this;
            r0 = 299564094(0x11dafc3e, float:3.4549792E-28)
            r5.Q(r0)
            T.y r0 = k6.AbstractC1692D.f18238a
            java.lang.Object r0 = r5.k(r0)
            com.stingray.karaoke.car.utils.LayoutMode r0 = (com.stingray.karaoke.car.utils.LayoutMode) r0
            com.stingray.karaoke.car.utils.LayoutMode r1 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            r2 = 1053609165(0x3ecccccd, float:0.4)
            r3 = 0
            if (r0 != r1) goto L17
            goto L56
        L17:
            com.stingray.karaoke.car.utils.LayoutMode r1 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r0 != r1) goto L1c
            goto L54
        L1c:
            com.stingray.karaoke.car.utils.LayoutMode r1 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r0 == r1) goto L54
            com.stingray.karaoke.car.utils.LayoutMode r1 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r0 != r1) goto L25
            goto L54
        L25:
            com.stingray.karaoke.car.utils.LayoutMode r1 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r0 == r1) goto L40
            com.stingray.karaoke.car.utils.LayoutMode r1 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            if (r0 == r1) goto L40
            com.stingray.karaoke.car.utils.LayoutMode r1 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r0 == r1) goto L40
            com.stingray.karaoke.car.utils.LayoutMode r1 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r0 == r1) goto L40
            com.stingray.karaoke.car.utils.LayoutMode r1 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r0 != r1) goto L3a
            goto L40
        L3a:
            A9.k r4 = new A9.k
            r4.<init>()
            throw r4
        L40:
            r0 = 398348334(0x17be502e, float:1.2298702E-24)
            boolean r0 = A7.k.t(r5, r0, r4, r5, r3)
            if (r0 == 0) goto L4a
            goto L56
        L4a:
            r0 = 398349423(0x17be546f, float:1.2299776E-24)
            boolean r4 = A7.k.r(r5, r0, r4, r5, r3)
            if (r4 == 0) goto L54
            goto L56
        L54:
            r2 = 1056964608(0x3f000000, float:0.5)
        L56:
            r5.p(r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.i0(T.q):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float j0(T.C0761q r7) {
        /*
            r6 = this;
            r0 = -683326942(0xffffffffd7454222, float:-2.1688783E14)
            r7.Q(r0)
            r0 = 480(0x1e0, float:6.73E-43)
            float r0 = (float) r0
            r1 = 560(0x230, float:7.85E-43)
            float r1 = (float) r1
            r2 = 640(0x280, float:8.97E-43)
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            r5 = 0
            if (r3 != r4) goto L1d
            goto L5d
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 != r4) goto L22
            goto L50
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 != r4) goto L27
            goto L5c
        L27:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L34
            goto L5c
        L34:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L41
            goto L47
        L41:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L47:
            r3 = -609592150(0xffffffffdbaa5caa, float:-9.590526E16)
            boolean r3 = M5.b.r(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L52
        L50:
            r0 = r1
            goto L5d
        L52:
            r1 = -609591093(0xffffffffdbaa60cb, float:-9.591434E16)
            boolean r6 = A7.k.u(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L5c
            goto L5d
        L5c:
            r0 = r2
        L5d:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.j0(T.q):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float k0(T.C0761q r7) {
        /*
            r6 = this;
            r0 = 1600190302(0x5f60f75e, float:1.6210529E19)
            r7.Q(r0)
            r0 = 80
            float r0 = (float) r0
            r1 = 90
            float r1 = (float) r1
            r2 = 100
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            r5 = 0
            if (r3 != r4) goto L1d
            goto L5d
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 != r4) goto L22
            goto L50
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 != r4) goto L27
            goto L5c
        L27:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L34
            goto L5c
        L34:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L41
            goto L47
        L41:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L47:
            r3 = -609592150(0xffffffffdbaa5caa, float:-9.590526E16)
            boolean r3 = M5.b.r(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L52
        L50:
            r0 = r1
            goto L5d
        L52:
            r1 = -609591093(0xffffffffdbaa60cb, float:-9.591434E16)
            boolean r6 = A7.k.u(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L5c
            goto L5d
        L5c:
            r0 = r2
        L5d:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.k0(T.q):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0058  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float l0(T.C0761q r7) {
        /*
            r6 = this;
            r0 = -768441640(0xffffffffd23282d8, float:-1.9167484E11)
            r7.Q(r0)
            r0 = -2
            float r0 = (float) r0
            r1 = -4
            float r1 = (float) r1
            r2 = -6
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            r5 = 0
            if (r3 != r4) goto L1a
            goto L4c
        L1a:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 != r4) goto L1f
            goto L58
        L1f:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L58
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L28
            goto L58
        L28:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 == r4) goto L43
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            if (r3 == r4) goto L43
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L43
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 == r4) goto L43
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L3d
            goto L43
        L3d:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L43:
            r3 = 398348334(0x17be502e, float:1.2298702E-24)
            boolean r3 = A7.k.t(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L4e
        L4c:
            r0 = r1
            goto L59
        L4e:
            r1 = 398349423(0x17be546f, float:1.2299776E-24)
            boolean r6 = A7.k.r(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L58
            goto L59
        L58:
            r0 = r2
        L59:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.l0(T.q):float");
    }

    public Typeface m(Context context, List list, int i7) {
        ContentResolver contentResolver = context.getContentResolver();
        try {
            FontFamily fontFamilyL = L((C1.k[]) list.get(0), contentResolver);
            if (fontFamilyL == null) {
                return null;
            }
            Typeface.CustomFallbackBuilder customFallbackBuilder = new Typeface.CustomFallbackBuilder(fontFamilyL);
            for (int i10 = 1; i10 < list.size(); i10++) {
                FontFamily fontFamilyL2 = L((C1.k[]) list.get(i10), contentResolver);
                if (fontFamilyL2 != null) {
                    customFallbackBuilder.addCustomFallback(fontFamilyL2);
                }
            }
            return customFallbackBuilder.setStyle(r(fontFamilyL, i7).getStyle()).build();
        } catch (Exception e10) {
            n.u("TypefaceCompatApi29Impl", "Font load failed", e10);
            return null;
        }
    }

    public float m0(C0761q c0761q) {
        c0761q.Q(-505363936);
        float f10 = 50;
        float f11 = 60;
        float f12 = 70;
        switch (a.f19251a[((LayoutMode) c0761q.k(AbstractC1692D.f18238a)).ordinal()]) {
            case 1:
            case 2:
            case 3:
                c0761q.Q(-2036471007);
                c0761q.p(false);
                break;
            case 4:
                c0761q.Q(-2036469598);
                c0761q.p(false);
                f10 = f11;
                break;
            case 5:
            case 6:
                c0761q.Q(-2036466911);
                c0761q.p(false);
                f10 = f12;
                break;
            case 7:
                c0761q.Q(-2036465437);
                f10 = i(this, c0761q) ? f11 : f12;
                c0761q.p(false);
                break;
            case 8:
                c0761q.Q(-2036462878);
                f10 = k(this, c0761q) ? f11 : f12;
                c0761q.p(false);
                break;
            case 9:
                c0761q.Q(-2036460330);
                c0761q.Q(-2036460225);
                boolean z6 = k(this, c0761q) || i(this, c0761q);
                c0761q.p(false);
                f10 = z6 ? f11 : f12;
                c0761q.p(false);
                break;
            default:
                throw M5.b.d(-2036475262, c0761q, false);
        }
        c0761q.p(false);
        return f10;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float n0(T.C0761q r7) {
        /*
            r6 = this;
            r0 = 1746988490(0x6820edca, float:3.0398603E24)
            r7.Q(r0)
            r0 = -22
            float r0 = (float) r0
            r1 = -28
            float r1 = (float) r1
            r2 = -30
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            r5 = 0
            if (r3 != r4) goto L1d
            goto L5d
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 != r4) goto L22
            goto L50
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 != r4) goto L27
            goto L5c
        L27:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L34
            goto L5c
        L34:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L41
            goto L47
        L41:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L47:
            r3 = -609592150(0xffffffffdbaa5caa, float:-9.590526E16)
            boolean r3 = M5.b.r(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L52
        L50:
            r0 = r1
            goto L5d
        L52:
            r1 = -609591093(0xffffffffdbaa60cb, float:-9.591434E16)
            boolean r6 = A7.k.u(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L5c
            goto L5d
        L5c:
            r0 = r2
        L5d:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.n0(T.q):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x005e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float o0(T.C0761q r8) {
        /*
            r7 = this;
            r0 = -118883066(0xfffffffff8e9fd06, float:-3.7966784E34)
            r8.Q(r0)
            r0 = 24
            float r0 = (float) r0
            r1 = 28
            float r1 = (float) r1
            r2 = 34
            float r2 = (float) r2
            r3 = 40
            float r3 = (float) r3
            T.y r4 = k6.AbstractC1692D.f18238a
            java.lang.Object r4 = r8.k(r4)
            com.stingray.karaoke.car.utils.LayoutMode r4 = (com.stingray.karaoke.car.utils.LayoutMode) r4
            com.stingray.karaoke.car.utils.LayoutMode r5 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            r6 = 0
            if (r4 != r5) goto L20
            goto L52
        L20:
            com.stingray.karaoke.car.utils.LayoutMode r5 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r4 != r5) goto L25
            goto L5e
        L25:
            com.stingray.karaoke.car.utils.LayoutMode r5 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r4 == r5) goto L60
            com.stingray.karaoke.car.utils.LayoutMode r5 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r4 != r5) goto L2e
            goto L60
        L2e:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r4 == r3) goto L49
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            if (r4 == r3) goto L49
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r4 == r3) goto L49
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r4 == r3) goto L49
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r4 != r3) goto L43
            goto L49
        L43:
            A9.k r7 = new A9.k
            r7.<init>()
            throw r7
        L49:
            r3 = 398348334(0x17be502e, float:1.2298702E-24)
            boolean r3 = A7.k.t(r8, r3, r7, r8, r6)
            if (r3 == 0) goto L54
        L52:
            r0 = r1
            goto L61
        L54:
            r1 = 398349423(0x17be546f, float:1.2299776E-24)
            boolean r7 = A7.k.r(r8, r1, r7, r8, r6)
            if (r7 == 0) goto L5e
            goto L61
        L5e:
            r0 = r2
            goto L61
        L60:
            r0 = r3
        L61:
            r8.p(r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.o0(T.q):float");
    }

    @Override // n4.o
    public void p0() {
    }

    @Override // e4.InterfaceC1226b
    public boolean q(Object obj, File file, h hVar) throws Throwable {
        try {
            A4.c.d(((C2290c) ((x) obj).get()).l.f21814a.f21829a.d.asReadOnlyBuffer(), file);
            return true;
        } catch (IOException e10) {
            if (Log.isLoggable("GifEncoder", 5)) {
                n.u("GifEncoder", "Failed to encode GIF drawable data", e10);
            }
            return false;
        }
    }

    public float q0(C0761q c0761q) {
        c0761q.Q(1212659152);
        float f10 = 900;
        LayoutMode layoutMode = (LayoutMode) c0761q.k(AbstractC1692D.f18238a);
        if (layoutMode != LayoutMode.NARROW_COMPACT && layoutMode != LayoutMode.WIDE_COMPACT && layoutMode != LayoutMode.WIDE_NORMAL && layoutMode != LayoutMode.WIDE_ULTRA && layoutMode != LayoutMode.NARROW_ULTRA && layoutMode != LayoutMode.SQUARE_ULTRA) {
            if (layoutMode != LayoutMode.SQUARE_COMPACT && layoutMode != LayoutMode.NARROW_NORMAL && layoutMode != LayoutMode.SQUARE_NORMAL) {
                throw new A9.k();
            }
            if (!M5.b.r(c0761q, -609592150, this, c0761q, false)) {
                c0761q.Q(-609591093);
                j(this, c0761q);
                c0761q.p(false);
            }
        }
        c0761q.p(false);
        return f10;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float r0(T.C0761q r6) {
        /*
            r5 = this;
            r0 = 2017803974(0x78453ec6, float:1.6002432E34)
            r6.Q(r0)
            r0 = 25
            float r0 = (float) r0
            r1 = 44
            float r1 = (float) r1
            T.y r2 = k6.AbstractC1692D.f18238a
            java.lang.Object r2 = r6.k(r2)
            com.stingray.karaoke.car.utils.LayoutMode r2 = (com.stingray.karaoke.car.utils.LayoutMode) r2
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            r4 = 0
            if (r2 != r3) goto L1a
            goto L4c
        L1a:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r2 != r3) goto L1f
            goto L4c
        L1f:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r2 == r3) goto L4c
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r2 != r3) goto L28
            goto L4c
        L28:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r2 == r3) goto L43
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            if (r2 == r3) goto L43
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r2 == r3) goto L43
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r2 == r3) goto L43
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r2 != r3) goto L3d
            goto L43
        L3d:
            A9.k r5 = new A9.k
            r5.<init>()
            throw r5
        L43:
            r2 = 398348334(0x17be502e, float:1.2298702E-24)
            boolean r2 = A7.k.t(r6, r2, r5, r6, r4)
            if (r2 == 0) goto L4e
        L4c:
            r0 = r1
            goto L57
        L4e:
            r2 = 398349423(0x17be546f, float:1.2299776E-24)
            boolean r5 = A7.k.r(r6, r2, r5, r6, r4)
            if (r5 == 0) goto L4c
        L57:
            r6.p(r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.r0(T.q):float");
    }

    @Override // q3.InterfaceC2177d
    public void s() {
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float s0(T.C0761q r7) {
        /*
            r6 = this;
            r0 = -1270024640(0xffffffffb44cf640, float:-1.9088566E-7)
            r7.Q(r0)
            r0 = 20
            float r0 = (float) r0
            r1 = 25
            float r1 = (float) r1
            r2 = 39
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            r5 = 0
            if (r3 != r4) goto L1d
            goto L5d
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 != r4) goto L22
            goto L50
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 != r4) goto L27
            goto L5c
        L27:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L5c
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L34
            goto L5c
        L34:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 == r4) goto L47
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L41
            goto L47
        L41:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L47:
            r3 = -609592150(0xffffffffdbaa5caa, float:-9.590526E16)
            boolean r3 = M5.b.r(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L52
        L50:
            r0 = r1
            goto L5d
        L52:
            r1 = -609591093(0xffffffffdbaa60cb, float:-9.591434E16)
            boolean r6 = A7.k.u(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L5c
            goto L5d
        L5c:
            r0 = r2
        L5d:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.s0(T.q):float");
    }

    @Override // e4.k
    public int t(h hVar) {
        return 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0058  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float u(T.C0761q r7) {
        /*
            r6 = this;
            r0 = 402682240(0x18007180, float:1.6600915E-24)
            r7.Q(r0)
            r0 = 2
            float r0 = (float) r0
            r1 = 3
            float r1 = (float) r1
            r2 = 4
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            r5 = 0
            if (r3 != r4) goto L1a
            goto L4c
        L1a:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 != r4) goto L1f
            goto L58
        L1f:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L58
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L28
            goto L58
        L28:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 == r4) goto L43
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            if (r3 == r4) goto L43
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L43
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 == r4) goto L43
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L3d
            goto L43
        L3d:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L43:
            r3 = 398348334(0x17be502e, float:1.2298702E-24)
            boolean r3 = A7.k.t(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L4e
        L4c:
            r0 = r1
            goto L59
        L4e:
            r1 = 398349423(0x17be546f, float:1.2299776E-24)
            boolean r6 = A7.k.r(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L58
            goto L59
        L58:
            r0 = r2
        L59:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.u(T.q):float");
    }

    public float u0(C0761q c0761q) {
        c0761q.Q(901708320);
        float f10 = 92;
        LayoutMode layoutMode = (LayoutMode) c0761q.k(AbstractC1692D.f18238a);
        if (layoutMode != LayoutMode.WIDE_ULTRA && layoutMode != LayoutMode.NARROW_NORMAL && layoutMode != LayoutMode.NARROW_ULTRA && layoutMode != LayoutMode.SQUARE_ULTRA) {
            if (layoutMode != LayoutMode.WIDE_COMPACT && layoutMode != LayoutMode.NARROW_COMPACT && layoutMode != LayoutMode.SQUARE_COMPACT && layoutMode != LayoutMode.WIDE_NORMAL && layoutMode != LayoutMode.SQUARE_NORMAL) {
                throw new A9.k();
            }
            if (!A7.k.t(c0761q, 398348334, this, c0761q, false)) {
                A7.k.r(c0761q, 398349423, this, c0761q, false);
            }
        }
        c0761q.p(false);
        return f10;
    }

    @Override // n4.o
    public void v(Bitmap bitmap, InterfaceC1359a interfaceC1359a) {
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float v0(T.C0761q r6) {
        /*
            r5 = this;
            r0 = 1349404800(0x506e4880, float:1.5990915E10)
            r6.Q(r0)
            r0 = 20
            float r0 = (float) r0
            r1 = 28
            float r1 = (float) r1
            T.y r2 = k6.AbstractC1692D.f18238a
            java.lang.Object r2 = r6.k(r2)
            com.stingray.karaoke.car.utils.LayoutMode r2 = (com.stingray.karaoke.car.utils.LayoutMode) r2
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            r4 = 0
            if (r2 != r3) goto L1a
            goto L4c
        L1a:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r2 != r3) goto L1f
            goto L4c
        L1f:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r2 == r3) goto L4c
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r2 != r3) goto L28
            goto L4c
        L28:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r2 == r3) goto L43
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            if (r2 == r3) goto L43
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r2 == r3) goto L43
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r2 == r3) goto L43
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r2 != r3) goto L3d
            goto L43
        L3d:
            A9.k r5 = new A9.k
            r5.<init>()
            throw r5
        L43:
            r2 = 398348334(0x17be502e, float:1.2298702E-24)
            boolean r2 = A7.k.t(r6, r2, r5, r6, r4)
            if (r2 == 0) goto L4e
        L4c:
            r0 = r1
            goto L57
        L4e:
            r2 = 398349423(0x17be546f, float:1.2299776E-24)
            boolean r5 = A7.k.r(r6, r2, r5, r6, r4)
            if (r5 == 0) goto L4c
        L57:
            r6.p(r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.v0(T.q):float");
    }

    public int w(int i7, C0761q c0761q) {
        c0761q.Q(-1017803117);
        int i10 = 24;
        switch (a.f19251a[((LayoutMode) c0761q.k(AbstractC1692D.f18238a)).ordinal()]) {
            case 1:
            case 2:
            case 3:
                c0761q.Q(-2036471007);
                c0761q.p(false);
                i10 = 20;
                break;
            case 4:
                c0761q.Q(-2036469598);
                c0761q.p(false);
                break;
            case 5:
            case 6:
                c0761q.Q(-2036466911);
                c0761q.p(false);
                break;
            case 7:
                c0761q.Q(-2036465437);
                i(this, c0761q);
                c0761q.p(false);
                break;
            case 8:
                c0761q.Q(-2036462878);
                k(this, c0761q);
                c0761q.p(false);
                break;
            case 9:
                c0761q.Q(-2036460330);
                c0761q.Q(-2036460225);
                if (!k(this, c0761q)) {
                    i(this, c0761q);
                }
                c0761q.p(false);
                c0761q.p(false);
                break;
            default:
                throw M5.b.d(-2036475262, c0761q, false);
        }
        c0761q.p(false);
        return i10;
    }

    public float w0(C0761q c0761q) {
        c0761q.Q(-232700032);
        float f10 = 10;
        LayoutMode layoutMode = (LayoutMode) c0761q.k(AbstractC1692D.f18238a);
        if (layoutMode != LayoutMode.NARROW_COMPACT && layoutMode != LayoutMode.WIDE_COMPACT && layoutMode != LayoutMode.WIDE_NORMAL && layoutMode != LayoutMode.WIDE_ULTRA && layoutMode != LayoutMode.NARROW_ULTRA && layoutMode != LayoutMode.SQUARE_ULTRA) {
            if (layoutMode != LayoutMode.SQUARE_COMPACT && layoutMode != LayoutMode.NARROW_NORMAL && layoutMode != LayoutMode.SQUARE_NORMAL) {
                throw new A9.k();
            }
            if (!M5.b.r(c0761q, -609592150, this, c0761q, false)) {
                c0761q.Q(-609591093);
                j(this, c0761q);
                c0761q.p(false);
            }
        }
        c0761q.p(false);
        return f10;
    }

    public float x(C0761q c0761q) {
        c0761q.Q(1538123968);
        float f10 = 16;
        float f11 = 24;
        float f12 = 32;
        switch (a.f19251a[((LayoutMode) c0761q.k(AbstractC1692D.f18238a)).ordinal()]) {
            case 1:
            case 2:
            case 3:
                c0761q.Q(-2036471007);
                c0761q.p(false);
                break;
            case 4:
                c0761q.Q(-2036469598);
                c0761q.p(false);
                f10 = f11;
                break;
            case 5:
            case 6:
                c0761q.Q(-2036466911);
                c0761q.p(false);
                f10 = f12;
                break;
            case 7:
                c0761q.Q(-2036465437);
                f10 = i(this, c0761q) ? f11 : f12;
                c0761q.p(false);
                break;
            case 8:
                c0761q.Q(-2036462878);
                f10 = k(this, c0761q) ? f11 : f12;
                c0761q.p(false);
                break;
            case 9:
                c0761q.Q(-2036460330);
                c0761q.Q(-2036460225);
                boolean z6 = k(this, c0761q) || i(this, c0761q);
                c0761q.p(false);
                f10 = z6 ? f11 : f12;
                c0761q.p(false);
                break;
            default:
                throw M5.b.d(-2036475262, c0761q, false);
        }
        c0761q.p(false);
        return f10;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0058  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float x0(T.C0761q r6) {
        /*
            r5 = this;
            r0 = -1644363300(0xffffffff9dfd01dc, float:-6.6970465E-21)
            r6.Q(r0)
            r0 = 20
            float r0 = (float) r0
            r1 = 32
            float r1 = (float) r1
            T.y r2 = k6.AbstractC1692D.f18238a
            java.lang.Object r2 = r6.k(r2)
            com.stingray.karaoke.car.utils.LayoutMode r2 = (com.stingray.karaoke.car.utils.LayoutMode) r2
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            r4 = 0
            if (r2 != r3) goto L1a
            goto L59
        L1a:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r2 != r3) goto L1f
            goto L59
        L1f:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r2 != r3) goto L24
            goto L58
        L24:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            if (r2 == r3) goto L58
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r2 == r3) goto L58
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r2 != r3) goto L31
            goto L58
        L31:
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r2 == r3) goto L44
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r2 == r3) goto L44
            com.stingray.karaoke.car.utils.LayoutMode r3 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r2 != r3) goto L3e
            goto L44
        L3e:
            A9.k r5 = new A9.k
            r5.<init>()
            throw r5
        L44:
            r2 = -609592150(0xffffffffdbaa5caa, float:-9.590526E16)
            boolean r2 = M5.b.r(r6, r2, r5, r6, r4)
            if (r2 == 0) goto L4e
            goto L59
        L4e:
            r2 = -609591093(0xffffffffdbaa60cb, float:-9.591434E16)
            boolean r5 = A7.k.u(r6, r2, r5, r6, r4)
            if (r5 == 0) goto L58
            goto L59
        L58:
            r0 = r1
        L59:
            r6.p(r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.x0(T.q):float");
    }

    public float y(C0761q c0761q) {
        c0761q.Q(721776542);
        float f10 = 24;
        float f11 = 28;
        float f12 = 32;
        switch (a.f19251a[((LayoutMode) c0761q.k(AbstractC1692D.f18238a)).ordinal()]) {
            case 1:
            case 2:
            case 3:
                c0761q.Q(-2036471007);
                c0761q.p(false);
                break;
            case 4:
                c0761q.Q(-2036469598);
                c0761q.p(false);
                f10 = f11;
                break;
            case 5:
            case 6:
                c0761q.Q(-2036466911);
                c0761q.p(false);
                f10 = f12;
                break;
            case 7:
                c0761q.Q(-2036465437);
                f10 = i(this, c0761q) ? f11 : f12;
                c0761q.p(false);
                break;
            case 8:
                c0761q.Q(-2036462878);
                f10 = k(this, c0761q) ? f11 : f12;
                c0761q.p(false);
                break;
            case 9:
                c0761q.Q(-2036460330);
                c0761q.Q(-2036460225);
                boolean z6 = k(this, c0761q) || i(this, c0761q);
                c0761q.p(false);
                f10 = z6 ? f11 : f12;
                c0761q.p(false);
                break;
            default:
                throw M5.b.d(-2036475262, c0761q, false);
        }
        c0761q.p(false);
        return f10;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float y0(T.C0761q r7) {
        /*
            r6 = this;
            r0 = 1901309880(0x7153afb8, float:1.0482203E30)
            r7.Q(r0)
            r0 = 10
            float r0 = (float) r0
            r1 = 14
            float r1 = (float) r1
            r2 = 16
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            r5 = 0
            if (r3 != r4) goto L1d
            goto L4f
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 != r4) goto L22
            goto L5b
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L5b
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L2b
            goto L5b
        L2b:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L40
            goto L46
        L40:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L46:
            r3 = 398348334(0x17be502e, float:1.2298702E-24)
            boolean r3 = A7.k.t(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L51
        L4f:
            r0 = r1
            goto L5c
        L51:
            r1 = 398349423(0x17be546f, float:1.2299776E-24)
            boolean r6 = A7.k.r(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L5b
            goto L5c
        L5b:
            r0 = r2
        L5c:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.y0(T.q):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float z(T.C0761q r7) {
        /*
            r6 = this;
            r0 = 384002112(0x16e36840, float:3.673962E-25)
            r7.Q(r0)
            r0 = 40
            float r0 = (float) r0
            r1 = 45
            float r1 = (float) r1
            r2 = 50
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            r5 = 0
            if (r3 != r4) goto L1d
            goto L4f
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 != r4) goto L22
            goto L5b
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L5b
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L2b
            goto L5b
        L2b:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L40
            goto L46
        L40:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L46:
            r3 = 398348334(0x17be502e, float:1.2298702E-24)
            boolean r3 = A7.k.t(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L51
        L4f:
            r0 = r1
            goto L5c
        L51:
            r1 = 398349423(0x17be546f, float:1.2299776E-24)
            boolean r6 = A7.k.r(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L5b
            goto L5c
        L5b:
            r0 = r2
        L5c:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.z(T.q):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float z0(T.C0761q r7) {
        /*
            r6 = this;
            r0 = 1534817964(0x5b7b76ac, float:7.07807E16)
            r7.Q(r0)
            r0 = 200(0xc8, float:2.8E-43)
            float r0 = (float) r0
            r1 = 300(0x12c, float:4.2E-43)
            float r1 = (float) r1
            r2 = 350(0x15e, float:4.9E-43)
            float r2 = (float) r2
            T.y r3 = k6.AbstractC1692D.f18238a
            java.lang.Object r3 = r7.k(r3)
            com.stingray.karaoke.car.utils.LayoutMode r3 = (com.stingray.karaoke.car.utils.LayoutMode) r3
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_ULTRA
            r5 = 0
            if (r3 != r4) goto L1d
            goto L4f
        L1d:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_NORMAL
            if (r3 != r4) goto L22
            goto L5b
        L22:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_ULTRA
            if (r3 == r4) goto L5b
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_ULTRA
            if (r3 != r4) goto L2b
            goto L5b
        L2b:
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.NARROW_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_COMPACT
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.WIDE_NORMAL
            if (r3 == r4) goto L46
            com.stingray.karaoke.car.utils.LayoutMode r4 = com.stingray.karaoke.car.utils.LayoutMode.SQUARE_NORMAL
            if (r3 != r4) goto L40
            goto L46
        L40:
            A9.k r6 = new A9.k
            r6.<init>()
            throw r6
        L46:
            r3 = 398348334(0x17be502e, float:1.2298702E-24)
            boolean r3 = A7.k.t(r7, r3, r6, r7, r5)
            if (r3 == 0) goto L51
        L4f:
            r0 = r1
            goto L5c
        L51:
            r1 = 398349423(0x17be546f, float:1.2299776E-24)
            boolean r6 = A7.k.r(r7, r1, r6, r7, r5)
            if (r6 == 0) goto L5b
            goto L5c
        L5b:
            r0 = r2
        L5c:
            r7.p(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m6.b.z0(T.q):float");
    }
}
