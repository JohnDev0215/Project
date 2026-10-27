package spi;

import android.graphics.Bitmap;
import android.util.Log;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.car.locale.ccTLD;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
import spi.bd;

/* JADX INFO: loaded from: classes.dex */
public final class bs implements bd {

    @ColorInt
    public int[] a;

    @ColorInt
    public final int[] b;
    public final bd.a c;
    public ByteBuffer d;
    public byte[] e;
    public short[] f;
    public byte[] g;
    public byte[] h;
    public byte[] i;

    @ColorInt
    public int[] j;
    public int k;
    public id l;
    public Bitmap m;
    public boolean n;
    public int o;
    public int p;
    public int q;
    public int r;

    @Nullable
    public Boolean s;

    @NonNull
    public Bitmap.Config t;

    public bs(@NonNull bd.a aVar) {
        this.b = new int[256];
        this.t = Bitmap.Config.ARGB_8888;
        this.c = aVar;
        this.l = new id();
    }

    public bs(@NonNull bd.a aVar, id idVar, ByteBuffer byteBuffer) {
        this(aVar, idVar, byteBuffer, 1);
    }

    /* JADX WARN: Type inference failed for: r4v4, types: [java.util.ArrayList, java.util.List<spi.gd>] */
    public bs(@NonNull bd.a aVar, id idVar, ByteBuffer byteBuffer, int i) {
        this(aVar);
        synchronized (this) {
            if (i <= 0) {
                throw new IllegalArgumentException("Sample size must be >=0, not: " + i);
            }
            int iHighestOneBit = Integer.highestOneBit(i);
            this.o = 0;
            this.l = idVar;
            this.k = -1;
            ByteBuffer byteBufferAsReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
            this.d = byteBufferAsReadOnlyBuffer;
            byteBufferAsReadOnlyBuffer.position(0);
            this.d.order(ByteOrder.LITTLE_ENDIAN);
            this.n = false;
            Iterator it = idVar.e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (((gd) it.next()).g == 3) {
                    this.n = true;
                    break;
                }
            }
            this.p = iHighestOneBit;
            int i2 = idVar.f;
            this.r = i2 / iHighestOneBit;
            int i3 = idVar.g;
            this.q = i3 / iHighestOneBit;
            this.i = ((ad) this.c).a(i2 * i3);
            bd.a aVar2 = this.c;
            int i4 = this.r * this.q;
            m0 m0Var = ((ad) aVar2).b;
            this.j = m0Var == null ? new int[i4] : (int[]) m0Var.d(i4, int[].class);
        }
    }

    /* JADX WARN: Type inference failed for: r5v2, types: [java.util.ArrayList, java.util.List<spi.gd>] */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.util.ArrayList, java.util.List<spi.gd>] */
    @Override // spi.bd
    @Nullable
    public final synchronized Bitmap a() {
        if (this.l.c <= 0 || this.k < 0) {
            if (Log.isLoggable(ccTLD.BAHAMAS, 3)) {
                Log.d(ccTLD.BAHAMAS, "Unable to decode frame, frameCount=" + this.l.c + ", framePointer=" + this.k);
            }
            this.o = 1;
        }
        int i = this.o;
        if (i != 1 && i != 2) {
            this.o = 0;
            if (this.e == null) {
                this.e = ((ad) this.c).a(255);
            }
            gd gdVar = (gd) this.l.e.get(this.k);
            int i2 = this.k - 1;
            gd gdVar2 = i2 >= 0 ? (gd) this.l.e.get(i2) : null;
            int[] iArr = gdVar.k;
            if (iArr == null) {
                iArr = this.l.a;
            }
            this.a = iArr;
            if (iArr == null) {
                if (Log.isLoggable(ccTLD.BAHAMAS, 3)) {
                    Log.d(ccTLD.BAHAMAS, "No valid color table found for frame #" + this.k);
                }
                this.o = 1;
                return null;
            }
            if (gdVar.f) {
                System.arraycopy(iArr, 0, this.b, 0, iArr.length);
                int[] iArr2 = this.b;
                this.a = iArr2;
                iArr2[gdVar.h] = 0;
                if (gdVar.g == 2 && this.k == 0) {
                    this.s = Boolean.TRUE;
                }
            }
            return i(gdVar, gdVar2);
        }
        if (Log.isLoggable(ccTLD.BAHAMAS, 3)) {
            Log.d(ccTLD.BAHAMAS, "Unable to decode frame, status=" + this.o);
        }
        return null;
    }

    @Override // spi.bd
    public final void b() {
        this.k = (this.k + 1) % this.l.c;
    }

    @Override // spi.bd
    public final int c() {
        return this.l.c;
    }

    @Override // spi.bd
    public final void clear() {
        m0 m0Var;
        m0 m0Var2;
        m0 m0Var3;
        this.l = null;
        byte[] bArr = this.i;
        if (bArr != null && (m0Var3 = ((ad) this.c).b) != null) {
            m0Var3.c(bArr);
        }
        int[] iArr = this.j;
        if (iArr != null && (m0Var2 = ((ad) this.c).b) != null) {
            m0Var2.c(iArr);
        }
        Bitmap bitmap = this.m;
        if (bitmap != null) {
            ((ad) this.c).a.c(bitmap);
        }
        this.m = null;
        this.d = null;
        this.s = null;
        byte[] bArr2 = this.e;
        if (bArr2 == null || (m0Var = ((ad) this.c).b) == null) {
            return;
        }
        m0Var.c(bArr2);
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.ArrayList, java.util.List<spi.gd>] */
    @Override // spi.bd
    public final int d() {
        int i;
        id idVar = this.l;
        int i2 = idVar.c;
        if (i2 <= 0 || (i = this.k) < 0) {
            return 0;
        }
        if (i < 0 || i >= i2) {
            return -1;
        }
        return ((gd) idVar.e.get(i)).i;
    }

    @Override // spi.bd
    public final int e() {
        return this.k;
    }

    @Override // spi.bd
    public final int f() {
        return (this.j.length * 4) + this.d.limit() + this.i.length;
    }

    public final Bitmap g() {
        Boolean bool = this.s;
        Bitmap.Config config = (bool == null || bool.booleanValue()) ? Bitmap.Config.ARGB_8888 : this.t;
        Bitmap bitmapE = ((ad) this.c).a.e(this.r, this.q, config);
        bitmapE.setHasAlpha(true);
        return bitmapE;
    }

    @Override // spi.bd
    @NonNull
    public final ByteBuffer getData() {
        return this.d;
    }

    public final void h(@NonNull Bitmap.Config config) {
        if (config == Bitmap.Config.ARGB_8888 || config == Bitmap.Config.RGB_565) {
            this.t = config;
            return;
        }
        throw new IllegalArgumentException("Unsupported format: " + config + ", must be one of " + Bitmap.Config.ARGB_8888 + " or " + Bitmap.Config.RGB_565);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0047  */
    /* JADX WARN: Type inference failed for: r5v27 */
    /* JADX WARN: Type inference failed for: r5v28 */
    /* JADX WARN: Type inference failed for: r5v29 */
    /* JADX WARN: Type inference failed for: r5v33, types: [short] */
    /* JADX WARN: Type inference failed for: r5v35 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.graphics.Bitmap i(spi.gd r36, spi.gd r37) {
        /*
            Method dump skipped, instruction units count: 1075
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.bs.i(spi.gd, spi.gd):android.graphics.Bitmap");
    }
}
