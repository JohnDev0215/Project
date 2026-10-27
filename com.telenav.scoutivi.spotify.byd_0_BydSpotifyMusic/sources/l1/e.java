package l1;

import android.graphics.Bitmap;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;
import l1.a;

/* JADX INFO: compiled from: StandardGifDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public class e implements a {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private static final String f7922u = "e";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int[] f7923a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int[] f7924b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final a.InterfaceC0114a f7925c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ByteBuffer f7926d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private byte[] f7927e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private short[] f7928f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private byte[] f7929g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private byte[] f7930h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private byte[] f7931i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private int[] f7932j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f7933k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private c f7934l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private Bitmap f7935m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private boolean f7936n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private int f7937o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private int f7938p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private int f7939q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private int f7940r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private Boolean f7941s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private Bitmap.Config f7942t;

    public e(a.InterfaceC0114a interfaceC0114a, c cVar, ByteBuffer byteBuffer, int i7) {
        this(interfaceC0114a);
        r(cVar, byteBuffer, i7);
    }

    private int j(int i7, int i8, int i9) {
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        for (int i15 = i7; i15 < this.f7938p + i7; i15++) {
            byte[] bArr = this.f7931i;
            if (i15 >= bArr.length || i15 >= i8) {
                break;
            }
            int i16 = this.f7923a[bArr[i15] & 255];
            if (i16 != 0) {
                i10 += (i16 >> 24) & 255;
                i11 += (i16 >> 16) & 255;
                i12 += (i16 >> 8) & 255;
                i13 += i16 & 255;
                i14++;
            }
        }
        int i17 = i7 + i9;
        for (int i18 = i17; i18 < this.f7938p + i17; i18++) {
            byte[] bArr2 = this.f7931i;
            if (i18 >= bArr2.length || i18 >= i8) {
                break;
            }
            int i19 = this.f7923a[bArr2[i18] & 255];
            if (i19 != 0) {
                i10 += (i19 >> 24) & 255;
                i11 += (i19 >> 16) & 255;
                i12 += (i19 >> 8) & 255;
                i13 += i19 & 255;
                i14++;
            }
        }
        if (i14 == 0) {
            return 0;
        }
        return ((i10 / i14) << 24) | ((i11 / i14) << 16) | ((i12 / i14) << 8) | (i13 / i14);
    }

    private void k(b bVar) {
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int[] iArr = this.f7932j;
        int i12 = bVar.f7897d;
        int i13 = this.f7938p;
        int i14 = i12 / i13;
        int i15 = bVar.f7895b / i13;
        int i16 = bVar.f7896c / i13;
        int i17 = bVar.f7894a / i13;
        boolean z6 = this.f7933k == 0;
        int i18 = this.f7940r;
        int i19 = this.f7939q;
        byte[] bArr = this.f7931i;
        int[] iArr2 = this.f7923a;
        Boolean bool = this.f7941s;
        int i20 = 8;
        int i21 = 0;
        int i22 = 0;
        int i23 = 1;
        while (i21 < i14) {
            Boolean bool2 = bool;
            if (bVar.f7898e) {
                if (i22 >= i14) {
                    i7 = i14;
                    int i24 = i23 + 1;
                    if (i24 == 2) {
                        i23 = i24;
                        i22 = 4;
                    } else if (i24 == 3) {
                        i23 = i24;
                        i20 = 4;
                        i22 = 2;
                    } else if (i24 != 4) {
                        i23 = i24;
                    } else {
                        i23 = i24;
                        i22 = 1;
                        i20 = 2;
                    }
                } else {
                    i7 = i14;
                }
                i8 = i22 + i20;
            } else {
                i7 = i14;
                i8 = i22;
                i22 = i21;
            }
            int i25 = i22 + i15;
            boolean z7 = i13 == 1;
            if (i25 < i19) {
                int i26 = i25 * i18;
                int i27 = i26 + i17;
                int i28 = i27 + i16;
                int i29 = i26 + i18;
                if (i29 < i28) {
                    i28 = i29;
                }
                i9 = i8;
                int i30 = i21 * i13 * bVar.f7896c;
                if (z7) {
                    int i31 = i27;
                    while (i31 < i28) {
                        int i32 = i15;
                        int i33 = iArr2[bArr[i30] & 255];
                        if (i33 != 0) {
                            iArr[i31] = i33;
                        } else if (z6 && bool2 == null) {
                            bool2 = Boolean.TRUE;
                        }
                        i30 += i13;
                        i31++;
                        i15 = i32;
                    }
                } else {
                    i11 = i15;
                    int i34 = ((i28 - i27) * i13) + i30;
                    int i35 = i27;
                    while (true) {
                        i10 = i16;
                        if (i35 < i28) {
                            int iJ = j(i30, i34, bVar.f7896c);
                            if (iJ != 0) {
                                iArr[i35] = iJ;
                            } else if (z6 && bool2 == null) {
                                bool2 = Boolean.TRUE;
                            }
                            i30 += i13;
                            i35++;
                            i16 = i10;
                        }
                    }
                    bool = bool2;
                    i21++;
                    i15 = i11;
                    i16 = i10;
                    i14 = i7;
                    i22 = i9;
                }
            } else {
                i9 = i8;
            }
            i11 = i15;
            i10 = i16;
            bool = bool2;
            i21++;
            i15 = i11;
            i16 = i10;
            i14 = i7;
            i22 = i9;
        }
        Boolean bool3 = bool;
        if (this.f7941s == null) {
            this.f7941s = Boolean.valueOf(bool3 == null ? false : bool3.booleanValue());
        }
    }

    private void l(b bVar) {
        b bVar2 = bVar;
        int[] iArr = this.f7932j;
        int i7 = bVar2.f7897d;
        int i8 = bVar2.f7895b;
        int i9 = bVar2.f7896c;
        int i10 = bVar2.f7894a;
        boolean z6 = this.f7933k == 0;
        int i11 = this.f7940r;
        byte[] bArr = this.f7931i;
        int[] iArr2 = this.f7923a;
        int i12 = 0;
        byte b7 = -1;
        while (i12 < i7) {
            int i13 = (i12 + i8) * i11;
            int i14 = i13 + i10;
            int i15 = i14 + i9;
            int i16 = i13 + i11;
            if (i16 < i15) {
                i15 = i16;
            }
            int i17 = bVar2.f7896c * i12;
            int i18 = i14;
            while (i18 < i15) {
                byte b8 = bArr[i17];
                int i19 = i7;
                int i20 = b8 & 255;
                if (i20 != b7) {
                    int i21 = iArr2[i20];
                    if (i21 != 0) {
                        iArr[i18] = i21;
                    } else {
                        b7 = b8;
                    }
                }
                i17++;
                i18++;
                i7 = i19;
            }
            i12++;
            bVar2 = bVar;
        }
        Boolean bool = this.f7941s;
        this.f7941s = Boolean.valueOf((bool != null && bool.booleanValue()) || (this.f7941s == null && z6 && b7 != -1));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v15, types: [short] */
    /* JADX WARN: Type inference failed for: r7v17 */
    private void m(b bVar) {
        int i7;
        int i8;
        short s6;
        e eVar = this;
        if (bVar != null) {
            eVar.f7926d.position(bVar.f7903j);
        }
        if (bVar == null) {
            c cVar = eVar.f7934l;
            i7 = cVar.f7910f;
            i8 = cVar.f7911g;
        } else {
            i7 = bVar.f7896c;
            i8 = bVar.f7897d;
        }
        int i9 = i7 * i8;
        byte[] bArr = eVar.f7931i;
        if (bArr == null || bArr.length < i9) {
            eVar.f7931i = eVar.f7925c.e(i9);
        }
        byte[] bArr2 = eVar.f7931i;
        if (eVar.f7928f == null) {
            eVar.f7928f = new short[4096];
        }
        short[] sArr = eVar.f7928f;
        if (eVar.f7929g == null) {
            eVar.f7929g = new byte[4096];
        }
        byte[] bArr3 = eVar.f7929g;
        if (eVar.f7930h == null) {
            eVar.f7930h = new byte[4097];
        }
        byte[] bArr4 = eVar.f7930h;
        int iQ = q();
        int i10 = 1 << iQ;
        int i11 = i10 + 1;
        int i12 = i10 + 2;
        int i13 = iQ + 1;
        int i14 = (1 << i13) - 1;
        int i15 = 0;
        for (int i16 = 0; i16 < i10; i16++) {
            sArr[i16] = 0;
            bArr3[i16] = (byte) i16;
        }
        byte[] bArr5 = eVar.f7927e;
        int i17 = i13;
        int i18 = i12;
        int i19 = i14;
        int iP = 0;
        int i20 = 0;
        int i21 = 0;
        int i22 = 0;
        int i23 = 0;
        int i24 = 0;
        int i25 = 0;
        int i26 = -1;
        while (true) {
            if (i15 >= i9) {
                break;
            }
            if (iP == 0) {
                iP = p();
                if (iP <= 0) {
                    eVar.f7937o = 3;
                    break;
                }
                i20 = 0;
            }
            i22 += (bArr5[i20] & 255) << i21;
            i20++;
            iP--;
            int i27 = i21 + 8;
            int i28 = i18;
            int i29 = i17;
            int i30 = i26;
            int i31 = i13;
            int i32 = i24;
            while (true) {
                if (i27 < i29) {
                    i26 = i30;
                    i18 = i28;
                    i21 = i27;
                    eVar = this;
                    i24 = i32;
                    i13 = i31;
                    i17 = i29;
                    break;
                }
                int i33 = i12;
                int i34 = i22 & i19;
                i22 >>= i29;
                i27 -= i29;
                if (i34 == i10) {
                    i19 = i14;
                    i29 = i31;
                    i28 = i33;
                    i12 = i28;
                    i30 = -1;
                } else {
                    if (i34 == i11) {
                        i21 = i27;
                        i24 = i32;
                        i18 = i28;
                        i13 = i31;
                        i12 = i33;
                        i26 = i30;
                        i17 = i29;
                        eVar = this;
                        break;
                    }
                    if (i30 == -1) {
                        bArr2[i23] = bArr3[i34];
                        i23++;
                        i15++;
                        i30 = i34;
                        i32 = i30;
                        i12 = i33;
                        i27 = i27;
                    } else {
                        if (i34 >= i28) {
                            bArr4[i25] = (byte) i32;
                            i25++;
                            s6 = i30;
                        } else {
                            s6 = i34;
                        }
                        while (s6 >= i10) {
                            bArr4[i25] = bArr3[s6];
                            i25++;
                            s6 = sArr[s6];
                        }
                        i32 = bArr3[s6] & 255;
                        byte b7 = (byte) i32;
                        bArr2[i23] = b7;
                        while (true) {
                            i23++;
                            i15++;
                            if (i25 <= 0) {
                                break;
                            }
                            i25--;
                            bArr2[i23] = bArr4[i25];
                        }
                        byte[] bArr6 = bArr4;
                        if (i28 < 4096) {
                            sArr[i28] = (short) i30;
                            bArr3[i28] = b7;
                            i28++;
                            if ((i28 & i19) == 0 && i28 < 4096) {
                                i29++;
                                i19 += i28;
                            }
                        }
                        i30 = i34;
                        i12 = i33;
                        i27 = i27;
                        bArr4 = bArr6;
                    }
                }
            }
        }
        Arrays.fill(bArr2, i23, i9, (byte) 0);
    }

    private Bitmap o() {
        Boolean bool = this.f7941s;
        Bitmap bitmapB = this.f7925c.b(this.f7940r, this.f7939q, (bool == null || bool.booleanValue()) ? Bitmap.Config.ARGB_8888 : this.f7942t);
        bitmapB.setHasAlpha(true);
        return bitmapB;
    }

    private int p() {
        int iQ = q();
        if (iQ <= 0) {
            return iQ;
        }
        ByteBuffer byteBuffer = this.f7926d;
        byteBuffer.get(this.f7927e, 0, Math.min(iQ, byteBuffer.remaining()));
        return iQ;
    }

    private int q() {
        return this.f7926d.get() & 255;
    }

    private Bitmap s(b bVar, b bVar2) {
        int i7;
        int i8;
        Bitmap bitmap;
        int[] iArr = this.f7932j;
        int i9 = 0;
        if (bVar2 == null) {
            Bitmap bitmap2 = this.f7935m;
            if (bitmap2 != null) {
                this.f7925c.d(bitmap2);
            }
            this.f7935m = null;
            Arrays.fill(iArr, 0);
        }
        if (bVar2 != null && bVar2.f7900g == 3 && this.f7935m == null) {
            Arrays.fill(iArr, 0);
        }
        if (bVar2 != null && (i8 = bVar2.f7900g) > 0) {
            if (i8 == 2) {
                if (!bVar.f7899f) {
                    c cVar = this.f7934l;
                    int i10 = cVar.f7916l;
                    if (bVar.f7904k == null || cVar.f7914j != bVar.f7901h) {
                        i9 = i10;
                    }
                }
                int i11 = bVar2.f7897d;
                int i12 = this.f7938p;
                int i13 = i11 / i12;
                int i14 = bVar2.f7895b / i12;
                int i15 = bVar2.f7896c / i12;
                int i16 = bVar2.f7894a / i12;
                int i17 = this.f7940r;
                int i18 = (i14 * i17) + i16;
                int i19 = (i13 * i17) + i18;
                while (i18 < i19) {
                    int i20 = i18 + i15;
                    for (int i21 = i18; i21 < i20; i21++) {
                        iArr[i21] = i9;
                    }
                    i18 += this.f7940r;
                }
            } else if (i8 == 3 && (bitmap = this.f7935m) != null) {
                int i22 = this.f7940r;
                bitmap.getPixels(iArr, 0, i22, 0, 0, i22, this.f7939q);
            }
        }
        m(bVar);
        if (bVar.f7898e || this.f7938p != 1) {
            k(bVar);
        } else {
            l(bVar);
        }
        if (this.f7936n && ((i7 = bVar.f7900g) == 0 || i7 == 1)) {
            if (this.f7935m == null) {
                this.f7935m = o();
            }
            Bitmap bitmap3 = this.f7935m;
            int i23 = this.f7940r;
            bitmap3.setPixels(iArr, 0, i23, 0, 0, i23, this.f7939q);
        }
        Bitmap bitmapO = o();
        int i24 = this.f7940r;
        bitmapO.setPixels(iArr, 0, i24, 0, 0, i24, this.f7939q);
        return bitmapO;
    }

    @Override // l1.a
    public int a() {
        return this.f7933k;
    }

    @Override // l1.a
    public int b() {
        return this.f7926d.limit() + this.f7931i.length + (this.f7932j.length * 4);
    }

    @Override // l1.a
    public void c(Bitmap.Config config) {
        if (config == Bitmap.Config.ARGB_8888 || config == Bitmap.Config.RGB_565) {
            this.f7942t = config;
            return;
        }
        throw new IllegalArgumentException("Unsupported format: " + config + ", must be one of " + Bitmap.Config.ARGB_8888 + " or " + Bitmap.Config.RGB_565);
    }

    @Override // l1.a
    public void clear() {
        this.f7934l = null;
        byte[] bArr = this.f7931i;
        if (bArr != null) {
            this.f7925c.c(bArr);
        }
        int[] iArr = this.f7932j;
        if (iArr != null) {
            this.f7925c.f(iArr);
        }
        Bitmap bitmap = this.f7935m;
        if (bitmap != null) {
            this.f7925c.d(bitmap);
        }
        this.f7935m = null;
        this.f7926d = null;
        this.f7941s = null;
        byte[] bArr2 = this.f7927e;
        if (bArr2 != null) {
            this.f7925c.c(bArr2);
        }
    }

    @Override // l1.a
    public synchronized Bitmap d() {
        if (this.f7934l.f7907c <= 0 || this.f7933k < 0) {
            String str = f7922u;
            if (Log.isLoggable(str, 3)) {
                Log.d(str, "Unable to decode frame, frameCount=" + this.f7934l.f7907c + ", framePointer=" + this.f7933k);
            }
            this.f7937o = 1;
        }
        int i7 = this.f7937o;
        if (i7 != 1 && i7 != 2) {
            this.f7937o = 0;
            if (this.f7927e == null) {
                this.f7927e = this.f7925c.e(255);
            }
            b bVar = this.f7934l.f7909e.get(this.f7933k);
            int i8 = this.f7933k - 1;
            b bVar2 = i8 >= 0 ? this.f7934l.f7909e.get(i8) : null;
            int[] iArr = bVar.f7904k;
            if (iArr == null) {
                iArr = this.f7934l.f7905a;
            }
            this.f7923a = iArr;
            if (iArr == null) {
                String str2 = f7922u;
                if (Log.isLoggable(str2, 3)) {
                    Log.d(str2, "No valid color table found for frame #" + this.f7933k);
                }
                this.f7937o = 1;
                return null;
            }
            if (bVar.f7899f) {
                System.arraycopy(iArr, 0, this.f7924b, 0, iArr.length);
                int[] iArr2 = this.f7924b;
                this.f7923a = iArr2;
                iArr2[bVar.f7901h] = 0;
                if (bVar.f7900g == 2 && this.f7933k == 0) {
                    this.f7941s = Boolean.TRUE;
                }
            }
            return s(bVar, bVar2);
        }
        String str3 = f7922u;
        if (Log.isLoggable(str3, 3)) {
            Log.d(str3, "Unable to decode frame, status=" + this.f7937o);
        }
        return null;
    }

    @Override // l1.a
    public void e() {
        this.f7933k = (this.f7933k + 1) % this.f7934l.f7907c;
    }

    @Override // l1.a
    public int f() {
        return this.f7934l.f7907c;
    }

    @Override // l1.a
    public int g() {
        int i7;
        if (this.f7934l.f7907c <= 0 || (i7 = this.f7933k) < 0) {
            return 0;
        }
        return n(i7);
    }

    @Override // l1.a
    public ByteBuffer h() {
        return this.f7926d;
    }

    @Override // l1.a
    public void i() {
        this.f7933k = -1;
    }

    public int n(int i7) {
        if (i7 >= 0) {
            c cVar = this.f7934l;
            if (i7 < cVar.f7907c) {
                return cVar.f7909e.get(i7).f7902i;
            }
        }
        return -1;
    }

    public synchronized void r(c cVar, ByteBuffer byteBuffer, int i7) {
        if (i7 <= 0) {
            throw new IllegalArgumentException("Sample size must be >=0, not: " + i7);
        }
        int iHighestOneBit = Integer.highestOneBit(i7);
        this.f7937o = 0;
        this.f7934l = cVar;
        this.f7933k = -1;
        ByteBuffer byteBufferAsReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        this.f7926d = byteBufferAsReadOnlyBuffer;
        byteBufferAsReadOnlyBuffer.position(0);
        this.f7926d.order(ByteOrder.LITTLE_ENDIAN);
        this.f7936n = false;
        Iterator<b> it = cVar.f7909e.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            } else if (it.next().f7900g == 3) {
                this.f7936n = true;
                break;
            }
        }
        this.f7938p = iHighestOneBit;
        int i8 = cVar.f7910f;
        this.f7940r = i8 / iHighestOneBit;
        int i9 = cVar.f7911g;
        this.f7939q = i9 / iHighestOneBit;
        this.f7931i = this.f7925c.e(i8 * i9);
        this.f7932j = this.f7925c.a(this.f7940r * this.f7939q);
    }

    public e(a.InterfaceC0114a interfaceC0114a) {
        this.f7924b = new int[256];
        this.f7942t = Bitmap.Config.ARGB_8888;
        this.f7925c = interfaceC0114a;
        this.f7934l = new c();
    }
}
