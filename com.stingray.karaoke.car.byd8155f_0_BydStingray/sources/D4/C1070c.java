package d4;

import android.util.Log;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

/* JADX INFO: renamed from: d4.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1070c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ByteBuffer f13360b;
    public C1069b c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f13359a = new byte[256];
    public int d = 0;

    public final boolean a() {
        return this.c.f13351b != 0;
    }

    public final C1069b b() {
        byte[] bArr;
        if (this.f13360b == null) {
            throw new IllegalStateException("You must call setData() before parseHeader()");
        }
        if (a()) {
            return this.c;
        }
        StringBuilder sb = new StringBuilder();
        for (int i7 = 0; i7 < 6; i7++) {
            sb.append((char) c());
        }
        if (sb.toString().startsWith("GIF")) {
            this.c.f13353f = this.f13360b.getShort();
            this.c.f13354g = this.f13360b.getShort();
            int iC = c();
            C1069b c1069b = this.c;
            c1069b.f13355h = (iC & 128) != 0;
            c1069b.f13356i = (int) Math.pow(2.0d, (iC & 7) + 1);
            this.c.f13357j = c();
            C1069b c1069b2 = this.c;
            c();
            c1069b2.getClass();
            if (this.c.f13355h && !a()) {
                C1069b c1069b3 = this.c;
                c1069b3.f13350a = e(c1069b3.f13356i);
                C1069b c1069b4 = this.c;
                c1069b4.f13358k = c1069b4.f13350a[c1069b4.f13357j];
            }
        } else {
            this.c.f13351b = 1;
        }
        if (!a()) {
            boolean z6 = false;
            while (!z6 && !a() && this.c.c <= Integer.MAX_VALUE) {
                int iC2 = c();
                if (iC2 == 33) {
                    int iC3 = c();
                    if (iC3 == 1) {
                        f();
                    } else if (iC3 == 249) {
                        this.c.d = new C1068a();
                        c();
                        int iC4 = c();
                        C1068a c1068a = this.c.d;
                        int i10 = (iC4 & 28) >> 2;
                        c1068a.f13345g = i10;
                        if (i10 == 0) {
                            c1068a.f13345g = 1;
                        }
                        c1068a.f13344f = (iC4 & 1) != 0;
                        short s6 = this.f13360b.getShort();
                        if (s6 < 2) {
                            s6 = 10;
                        }
                        C1068a c1068a2 = this.c.d;
                        c1068a2.f13347i = s6 * 10;
                        c1068a2.f13346h = c();
                        c();
                    } else if (iC3 == 254) {
                        f();
                    } else if (iC3 != 255) {
                        f();
                    } else {
                        d();
                        StringBuilder sb2 = new StringBuilder();
                        int i11 = 0;
                        while (true) {
                            bArr = this.f13359a;
                            if (i11 >= 11) {
                                break;
                            }
                            sb2.append((char) bArr[i11]);
                            i11++;
                        }
                        if (sb2.toString().equals("NETSCAPE2.0")) {
                            do {
                                d();
                                if (bArr[0] == 1) {
                                    byte b10 = bArr[1];
                                    byte b11 = bArr[2];
                                    this.c.getClass();
                                }
                                if (this.d > 0) {
                                }
                            } while (!a());
                        } else {
                            f();
                        }
                    }
                } else if (iC2 == 44) {
                    C1069b c1069b5 = this.c;
                    if (c1069b5.d == null) {
                        c1069b5.d = new C1068a();
                    }
                    c1069b5.d.f13341a = this.f13360b.getShort();
                    this.c.d.f13342b = this.f13360b.getShort();
                    this.c.d.c = this.f13360b.getShort();
                    this.c.d.d = this.f13360b.getShort();
                    int iC5 = c();
                    boolean z10 = (iC5 & 128) != 0;
                    int iPow = (int) Math.pow(2.0d, (iC5 & 7) + 1);
                    C1068a c1068a3 = this.c.d;
                    c1068a3.f13343e = (iC5 & 64) != 0;
                    if (z10) {
                        c1068a3.f13349k = e(iPow);
                    } else {
                        c1068a3.f13349k = null;
                    }
                    this.c.d.f13348j = this.f13360b.position();
                    c();
                    f();
                    if (!a()) {
                        C1069b c1069b6 = this.c;
                        c1069b6.c++;
                        c1069b6.f13352e.add(c1069b6.d);
                    }
                } else if (iC2 != 59) {
                    this.c.f13351b = 1;
                } else {
                    z6 = true;
                }
            }
            C1069b c1069b7 = this.c;
            if (c1069b7.c < 0) {
                c1069b7.f13351b = 1;
            }
        }
        return this.c;
    }

    public final int c() {
        try {
            return this.f13360b.get() & 255;
        } catch (Exception unused) {
            this.c.f13351b = 1;
            return 0;
        }
    }

    public final void d() {
        int iC = c();
        this.d = iC;
        if (iC <= 0) {
            return;
        }
        int i7 = 0;
        int i10 = 0;
        while (true) {
            try {
                i10 = this.d;
                if (i7 >= i10) {
                    return;
                }
                i10 -= i7;
                this.f13360b.get(this.f13359a, i7, i10);
                i7 += i10;
            } catch (Exception e10) {
                if (Log.isLoggable("GifHeaderParser", 3)) {
                    StringBuilder sbL = M5.b.l("Error Reading Block n: ", " count: ", i7, i10, " blockSize: ");
                    sbL.append(this.d);
                    Log.d("GifHeaderParser", sbL.toString(), e10);
                }
                this.c.f13351b = 1;
                return;
            }
        }
    }

    public final int[] e(int i7) {
        byte[] bArr = new byte[i7 * 3];
        int[] iArr = null;
        try {
            this.f13360b.get(bArr);
            iArr = new int[256];
            int i10 = 0;
            int i11 = 0;
            while (i10 < i7) {
                int i12 = bArr[i11] & 255;
                int i13 = i11 + 2;
                int i14 = bArr[i11 + 1] & 255;
                i11 += 3;
                int i15 = i10 + 1;
                iArr[i10] = (i14 << 8) | (i12 << 16) | (-16777216) | (bArr[i13] & 255);
                i10 = i15;
            }
        } catch (BufferUnderflowException e10) {
            if (Log.isLoggable("GifHeaderParser", 3)) {
                Log.d("GifHeaderParser", "Format Error Reading Color Table", e10);
            }
            this.c.f13351b = 1;
        }
        return iArr;
    }

    public final void f() {
        int iC;
        do {
            iC = c();
            this.f13360b.position(Math.min(this.f13360b.position() + iC, this.f13360b.limit()));
        } while (iC > 0);
    }
}
