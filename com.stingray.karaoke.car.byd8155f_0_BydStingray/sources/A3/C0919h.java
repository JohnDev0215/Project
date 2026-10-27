package a3;

import B2.N;
import K.L0;
import Y2.k;
import Y2.l;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.SparseArray;
import d5.C1078G;
import d5.I;
import d5.d0;
import g2.C1289b;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h2.t;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: a3.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0919h implements l {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final byte[] f11082s = {0, 7, 8, 15};

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final byte[] f11083t = {0, 119, -120, -1};

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final byte[] f11084u = {0, 17, 34, 51, 68, 85, 102, 119, -120, -103, -86, -69, -52, -35, -18, -1};
    public final Paint l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Paint f11085m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Canvas f11086n;
    public final C0913b o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final C0912a f11087p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C0918g f11088q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Bitmap f11089r;

    public C0919h(List list) {
        t tVar = new t((byte[]) list.get(0));
        int iB = tVar.B();
        int iB2 = tVar.B();
        Paint paint = new Paint();
        this.l = paint;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        paint.setPathEffect(null);
        Paint paint2 = new Paint();
        this.f11085m = paint2;
        paint2.setStyle(Paint.Style.FILL);
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
        paint2.setPathEffect(null);
        this.f11086n = new Canvas();
        this.o = new C0913b(719, 575, 0, 719, 0, 575);
        this.f11087p = new C0912a(0, new int[]{0, -1, -16777216, -8421505}, b(), c());
        this.f11088q = new C0918g(iB, iB2);
    }

    public static byte[] a(int i7, int i10, N n10) {
        byte[] bArr = new byte[i7];
        for (int i11 = 0; i11 < i7; i11++) {
            bArr[i11] = (byte) n10.i(i10);
        }
        return bArr;
    }

    public static int[] b() {
        int[] iArr = new int[16];
        iArr[0] = 0;
        for (int i7 = 1; i7 < 16; i7++) {
            if (i7 < 8) {
                iArr[i7] = e(255, (i7 & 1) != 0 ? 255 : 0, (i7 & 2) != 0 ? 255 : 0, (i7 & 4) != 0 ? 255 : 0);
            } else {
                iArr[i7] = e(255, (i7 & 1) != 0 ? 127 : 0, (i7 & 2) != 0 ? 127 : 0, (i7 & 4) == 0 ? 0 : 127);
            }
        }
        return iArr;
    }

    public static int[] c() {
        int[] iArr = new int[256];
        iArr[0] = 0;
        for (int i7 = 0; i7 < 256; i7++) {
            if (i7 < 8) {
                iArr[i7] = e(63, (i7 & 1) != 0 ? 255 : 0, (i7 & 2) != 0 ? 255 : 0, (i7 & 4) == 0 ? 0 : 255);
            } else {
                int i10 = i7 & 136;
                if (i10 == 0) {
                    iArr[i7] = e(255, ((i7 & 1) != 0 ? 85 : 0) + ((i7 & 16) != 0 ? 170 : 0), ((i7 & 2) != 0 ? 85 : 0) + ((i7 & 32) != 0 ? 170 : 0), ((i7 & 4) == 0 ? 0 : 85) + ((i7 & 64) == 0 ? 0 : 170));
                } else if (i10 == 8) {
                    iArr[i7] = e(127, ((i7 & 1) != 0 ? 85 : 0) + ((i7 & 16) != 0 ? 170 : 0), ((i7 & 2) != 0 ? 85 : 0) + ((i7 & 32) != 0 ? 170 : 0), ((i7 & 4) == 0 ? 0 : 85) + ((i7 & 64) == 0 ? 0 : 170));
                } else if (i10 == 128) {
                    iArr[i7] = e(255, ((i7 & 1) != 0 ? 43 : 0) + 127 + ((i7 & 16) != 0 ? 85 : 0), ((i7 & 2) != 0 ? 43 : 0) + 127 + ((i7 & 32) != 0 ? 85 : 0), ((i7 & 4) == 0 ? 0 : 43) + 127 + ((i7 & 64) == 0 ? 0 : 85));
                } else if (i10 == 136) {
                    iArr[i7] = e(255, ((i7 & 1) != 0 ? 43 : 0) + ((i7 & 16) != 0 ? 85 : 0), ((i7 & 2) != 0 ? 43 : 0) + ((i7 & 32) != 0 ? 85 : 0), ((i7 & 4) == 0 ? 0 : 43) + ((i7 & 64) == 0 ? 0 : 85));
                }
            }
        }
        return iArr;
    }

    public static int e(int i7, int i10, int i11, int i12) {
        return (i7 << 24) | (i10 << 16) | (i11 << 8) | i12;
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x01f1 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0224 A[LOOP:3: B:89:0x0172->B:118:0x0224, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:141:0x021f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0181  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void f(byte[] r23, int[] r24, int r25, int r26, int r27, android.graphics.Paint r28, android.graphics.Canvas r29) {
        /*
            Method dump skipped, instruction units count: 580
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a3.C0919h.f(byte[], int[], int, int, int, android.graphics.Paint, android.graphics.Canvas):void");
    }

    public static C0912a g(N n10, int i7) {
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15 = 8;
        int i16 = n10.i(8);
        n10.u(8);
        int i17 = 2;
        int i18 = i7 - 2;
        int i19 = 0;
        int[] iArr = {0, -1, -16777216, -8421505};
        int[] iArrB = b();
        int[] iArrC = c();
        while (i18 > 0) {
            int i20 = n10.i(i15);
            int i21 = n10.i(i15);
            int[] iArr2 = (i21 & 128) != 0 ? iArr : (i21 & 64) != 0 ? iArrB : iArrC;
            if ((i21 & 1) != 0) {
                i13 = n10.i(i15);
                i14 = n10.i(i15);
                i10 = n10.i(i15);
                i12 = n10.i(i15);
                i11 = i18 - 6;
            } else {
                int i22 = n10.i(6) << i17;
                int i23 = n10.i(4) << 4;
                i10 = n10.i(4) << 4;
                i11 = i18 - 4;
                i12 = n10.i(i17) << 6;
                i13 = i22;
                i14 = i23;
            }
            if (i13 == 0) {
                i14 = i19;
                i10 = i14;
                i12 = 255;
            }
            double d = i13;
            double d10 = i14 - 128;
            double d11 = i10 - 128;
            iArr2[i20] = e((byte) (255 - (i12 & 255)), AbstractC1343A.i((int) ((1.402d * d10) + d), 0, 255), AbstractC1343A.i((int) ((d - (0.34414d * d11)) - (d10 * 0.71414d)), 0, 255), AbstractC1343A.i((int) ((d11 * 1.772d) + d), 0, 255));
            i18 = i11;
            i19 = 0;
            i16 = i16;
            iArrC = iArrC;
            i15 = 8;
            i17 = 2;
        }
        return new C0912a(i16, iArr, iArrB, iArrC);
    }

    public static C0914c i(N n10) {
        byte[] bArr;
        int i7 = n10.i(16);
        n10.u(4);
        int i10 = n10.i(2);
        boolean zH = n10.h();
        n10.u(1);
        byte[] bArr2 = AbstractC1343A.f15198f;
        if (i10 != 1) {
            if (i10 == 0) {
                int i11 = n10.i(16);
                int i12 = n10.i(16);
                if (i11 > 0) {
                    bArr2 = new byte[i11];
                    n10.l(i11, bArr2);
                }
                if (i12 > 0) {
                    bArr = new byte[i12];
                    n10.l(i12, bArr);
                }
            }
            return new C0914c(i7, zH, bArr2, bArr);
        }
        n10.u(n10.i(8) * 16);
        bArr = bArr2;
        return new C0914c(i7, zH, bArr2, bArr);
    }

    @Override // Y2.l
    public final void d() {
        C0918g c0918g = this.f11088q;
        c0918g.c.clear();
        c0918g.d.clear();
        c0918g.f11077e.clear();
        c0918g.f11078f.clear();
        c0918g.f11079g.clear();
        c0918g.f11080h = null;
        c0918g.f11081i = null;
    }

    @Override // Y2.l
    public final void h(byte[] bArr, int i7, int i10, k kVar, h2.g gVar) {
        C0918g c0918g;
        Y2.a aVar;
        int i11;
        char c;
        int i12;
        C0913b c0913b;
        ArrayList arrayList;
        int i13;
        C0918g c0918g2;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        C0916e c0916e;
        C0916e c0916e2;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23 = 8;
        N n10 = new N(i7 + i10, bArr);
        n10.r(i7);
        while (true) {
            int iB = n10.b();
            c0918g = this.f11088q;
            if (iB >= 48 && n10.i(i23) == 15) {
                int i24 = n10.i(i23);
                int i25 = 16;
                int i26 = n10.i(16);
                int i27 = n10.i(16);
                int iF = n10.f() + i27;
                if (i27 * 8 > n10.b()) {
                    AbstractC1346c.z("DvbParser", "Data field length exceeds limit");
                    n10.u(n10.b());
                } else {
                    switch (i24) {
                        case 16:
                            if (i26 == c0918g.f11075a) {
                                L0 l02 = c0918g.f11081i;
                                n10.i(i23);
                                int i28 = n10.i(4);
                                int i29 = n10.i(2);
                                n10.u(2);
                                int i30 = i27 - 2;
                                SparseArray sparseArray = new SparseArray();
                                while (i30 > 0) {
                                    int i31 = n10.i(i23);
                                    n10.u(i23);
                                    i30 -= 6;
                                    sparseArray.put(i31, new C0915d(n10.i(16), n10.i(16)));
                                    i23 = 8;
                                }
                                L0 l03 = new L0(i28, i29, sparseArray);
                                if (i29 != 0) {
                                    c0918g.f11081i = l03;
                                    c0918g.c.clear();
                                    c0918g.d.clear();
                                    c0918g.f11077e.clear();
                                } else if (l02 != null && l02.f3180m != i28) {
                                    c0918g.f11081i = l03;
                                }
                            }
                            break;
                        case 17:
                            L0 l04 = c0918g.f11081i;
                            if (i26 == c0918g.f11075a && l04 != null) {
                                int i32 = n10.i(i23);
                                n10.u(4);
                                boolean zH = n10.h();
                                n10.u(3);
                                int i33 = n10.i(16);
                                int i34 = n10.i(16);
                                n10.i(3);
                                int i35 = n10.i(3);
                                n10.u(2);
                                int i36 = n10.i(i23);
                                int i37 = n10.i(i23);
                                int i38 = n10.i(4);
                                int i39 = n10.i(2);
                                n10.u(2);
                                int i40 = i27 - 10;
                                SparseArray sparseArray2 = new SparseArray();
                                while (i40 > 0) {
                                    int i41 = n10.i(i25);
                                    int i42 = n10.i(2);
                                    n10.i(2);
                                    int i43 = n10.i(12);
                                    n10.u(4);
                                    int i44 = n10.i(12);
                                    int i45 = i40 - 6;
                                    if (i42 == 1 || i42 == 2) {
                                        n10.i(i23);
                                        n10.i(i23);
                                        i40 -= 8;
                                    } else {
                                        i40 = i45;
                                    }
                                    sparseArray2.put(i41, new C0917f(i43, i44));
                                    i25 = 16;
                                }
                                C0916e c0916e3 = new C0916e(i32, zH, i33, i34, i35, i36, i37, i38, i39, sparseArray2);
                                SparseArray sparseArray3 = c0918g.c;
                                if (l04.f3181n == 0 && (c0916e2 = (C0916e) sparseArray3.get(i32)) != null) {
                                    int i46 = 0;
                                    while (true) {
                                        SparseArray sparseArray4 = c0916e2.f11072j;
                                        if (i46 < sparseArray4.size()) {
                                            c0916e3.f11072j.put(sparseArray4.keyAt(i46), (C0917f) sparseArray4.valueAt(i46));
                                            i46++;
                                        }
                                    }
                                }
                                sparseArray3.put(c0916e3.f11065a, c0916e3);
                            }
                            break;
                        case 18:
                            if (i26 == c0918g.f11075a) {
                                C0912a c0912aG = g(n10, i27);
                                c0918g.d.put(c0912aG.f11055a, c0912aG);
                            } else if (i26 == c0918g.f11076b) {
                                C0912a c0912aG2 = g(n10, i27);
                                c0918g.f11078f.put(c0912aG2.f11055a, c0912aG2);
                            }
                            break;
                        case 19:
                            if (i26 == c0918g.f11075a) {
                                C0914c c0914cI = i(n10);
                                c0918g.f11077e.put(c0914cI.f11061a, c0914cI);
                            } else if (i26 == c0918g.f11076b) {
                                C0914c c0914cI2 = i(n10);
                                c0918g.f11079g.put(c0914cI2.f11061a, c0914cI2);
                            }
                            break;
                        case 20:
                            if (i26 == c0918g.f11075a) {
                                n10.u(4);
                                boolean zH2 = n10.h();
                                n10.u(3);
                                int i47 = n10.i(16);
                                int i48 = n10.i(16);
                                if (zH2) {
                                    int i49 = n10.i(16);
                                    int i50 = n10.i(16);
                                    int i51 = n10.i(16);
                                    i19 = i50;
                                    i20 = n10.i(16);
                                    i22 = i51;
                                    i21 = i49;
                                } else {
                                    i19 = i47;
                                    i20 = i48;
                                    i21 = 0;
                                    i22 = 0;
                                }
                                c0918g.f11080h = new C0913b(i47, i48, i21, i19, i22, i20);
                            }
                            break;
                    }
                    n10.v(iF - n10.f());
                }
                i23 = 8;
            }
        }
        L0 l05 = c0918g.f11081i;
        if (l05 == null) {
            C1078G c1078g = I.f13384m;
            aVar = new Y2.a(d0.f13415p, -9223372036854775807L, -9223372036854775807L);
        } else {
            C0913b c0913b2 = c0918g.f11080h;
            if (c0913b2 == null) {
                c0913b2 = this.o;
            }
            Bitmap bitmap = this.f11089r;
            Canvas canvas = this.f11086n;
            if (bitmap == null || c0913b2.f11057a + 1 != bitmap.getWidth() || c0913b2.f11058b + 1 != this.f11089r.getHeight()) {
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(c0913b2.f11057a + 1, c0913b2.f11058b + 1, Bitmap.Config.ARGB_8888);
                this.f11089r = bitmapCreateBitmap;
                canvas.setBitmap(bitmapCreateBitmap);
            }
            ArrayList arrayList2 = new ArrayList();
            int i52 = 0;
            while (true) {
                SparseArray sparseArray5 = (SparseArray) l05.o;
                if (i52 < sparseArray5.size()) {
                    canvas.save();
                    C0915d c0915d = (C0915d) sparseArray5.valueAt(i52);
                    C0916e c0916e4 = (C0916e) c0918g.c.get(sparseArray5.keyAt(i52));
                    int i53 = c0915d.f11063a + c0913b2.c;
                    int i54 = c0915d.f11064b + c0913b2.f11059e;
                    int iMin = Math.min(c0916e4.c + i53, c0913b2.d);
                    int i55 = c0916e4.d;
                    int i56 = i54 + i55;
                    canvas.clipRect(i53, i54, iMin, Math.min(i56, c0913b2.f11060f));
                    SparseArray sparseArray6 = c0918g.d;
                    int i57 = c0916e4.f11068f;
                    C0912a c0912a = (C0912a) sparseArray6.get(i57);
                    if (c0912a == null && (c0912a = (C0912a) c0918g.f11078f.get(i57)) == null) {
                        c0912a = this.f11087p;
                    }
                    int i58 = 0;
                    while (true) {
                        SparseArray sparseArray7 = c0916e4.f11072j;
                        if (i58 < sparseArray7.size()) {
                            int iKeyAt = sparseArray7.keyAt(i58);
                            C0917f c0917f = (C0917f) sparseArray7.valueAt(i58);
                            L0 l06 = l05;
                            C0914c c0914c = (C0914c) c0918g.f11077e.get(iKeyAt);
                            if (c0914c == null) {
                                c0914c = (C0914c) c0918g.f11079g.get(iKeyAt);
                            }
                            if (c0914c != null) {
                                Paint paint = c0914c.f11062b ? null : this.l;
                                c0918g2 = c0918g;
                                int i59 = c0917f.f11073a + i53;
                                int i60 = c0917f.f11074b + i54;
                                i13 = i52;
                                int i61 = c0916e4.f11067e;
                                int i62 = i58;
                                int[] iArr = i61 == 3 ? c0912a.d : i61 == 2 ? c0912a.c : c0912a.f11056b;
                                i14 = i62;
                                arrayList = arrayList2;
                                c0913b = c0913b2;
                                i16 = i55;
                                i15 = i56;
                                i18 = i53;
                                i17 = i54;
                                c0916e = c0916e4;
                                Paint paint2 = paint;
                                f(c0914c.c, iArr, i61, i59, i60, paint2, canvas);
                                f(c0914c.d, iArr, i61, i59, i60 + 1, paint2, canvas);
                            } else {
                                c0913b = c0913b2;
                                arrayList = arrayList2;
                                i13 = i52;
                                c0918g2 = c0918g;
                                i14 = i58;
                                i15 = i56;
                                i16 = i55;
                                i17 = i54;
                                i18 = i53;
                                c0916e = c0916e4;
                            }
                            i58 = i14 + 1;
                            c0916e4 = c0916e;
                            i53 = i18;
                            l05 = l06;
                            c0918g = c0918g2;
                            i52 = i13;
                            c0913b2 = c0913b;
                            i55 = i16;
                            i56 = i15;
                            i54 = i17;
                            arrayList2 = arrayList;
                        } else {
                            L0 l07 = l05;
                            C0913b c0913b3 = c0913b2;
                            ArrayList arrayList3 = arrayList2;
                            int i63 = i52;
                            C0918g c0918g3 = c0918g;
                            int i64 = i56;
                            int i65 = i55;
                            int i66 = i54;
                            int i67 = i53;
                            C0916e c0916e5 = c0916e4;
                            boolean z6 = c0916e5.f11066b;
                            int i68 = c0916e5.c;
                            if (z6) {
                                int i69 = c0916e5.f11067e;
                                if (i69 == 3) {
                                    i12 = c0912a.d[c0916e5.f11069g];
                                    c = 2;
                                } else {
                                    c = 2;
                                    i12 = i69 == 2 ? c0912a.c[c0916e5.f11070h] : c0912a.f11056b[c0916e5.f11071i];
                                }
                                Paint paint3 = this.f11085m;
                                paint3.setColor(i12);
                                i11 = i66;
                                canvas.drawRect(i67, i11, i67 + i68, i64, paint3);
                            } else {
                                i11 = i66;
                                c = 2;
                            }
                            Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(this.f11089r, i67, i11, i68, i65);
                            float f10 = c0913b3.f11057a;
                            float f11 = c0913b3.f11058b;
                            arrayList3.add(new C1289b(null, null, null, bitmapCreateBitmap2, i11 / f11, 0, 0, i67 / f10, 0, Integer.MIN_VALUE, -3.4028235E38f, i68 / f10, i65 / f11, false, -16777216, Integer.MIN_VALUE, 0.0f));
                            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                            canvas.restore();
                            c0918g = c0918g3;
                            i52 = i63 + 1;
                            l05 = l07;
                            arrayList2 = arrayList3;
                            c0913b2 = c0913b3;
                        }
                    }
                } else {
                    aVar = new Y2.a(arrayList2, -9223372036854775807L, -9223372036854775807L);
                }
            }
        }
        gVar.accept(aVar);
    }

    @Override // Y2.l
    public final int s() {
        return 2;
    }
}
