package d4;

import F.l;
import android.graphics.Bitmap;
import android.util.Log;
import h4.InterfaceC1359a;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
import l8.C1789g;

/* JADX INFO: renamed from: d4.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1071d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f13361a;
    public final C1789g c;
    public ByteBuffer d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f13363e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public short[] f13364f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public byte[] f13365g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public byte[] f13366h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public byte[] f13367i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int[] f13368j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f13369k;
    public C1069b l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Bitmap f13370m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f13371n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f13372p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f13373q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f13374r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Boolean f13375s;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f13362b = new int[256];

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Bitmap.Config f13376t = Bitmap.Config.ARGB_8888;

    public C1071d(C1789g c1789g, C1069b c1069b, ByteBuffer byteBuffer, int i7) {
        this.c = c1789g;
        this.l = new C1069b();
        synchronized (this) {
            try {
                if (i7 <= 0) {
                    throw new IllegalArgumentException("Sample size must be >=0, not: " + i7);
                }
                int iHighestOneBit = Integer.highestOneBit(i7);
                this.o = 0;
                this.l = c1069b;
                this.f13369k = -1;
                ByteBuffer byteBufferAsReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
                this.d = byteBufferAsReadOnlyBuffer;
                byteBufferAsReadOnlyBuffer.position(0);
                this.d.order(ByteOrder.LITTLE_ENDIAN);
                this.f13371n = false;
                Iterator it = c1069b.f13352e.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else if (((C1068a) it.next()).f13345g == 3) {
                        this.f13371n = true;
                        break;
                    }
                }
                this.f13372p = iHighestOneBit;
                int i10 = c1069b.f13353f;
                this.f13374r = i10 / iHighestOneBit;
                int i11 = c1069b.f13354g;
                this.f13373q = i11 / iHighestOneBit;
                int i12 = i10 * i11;
                l lVar = (l) this.c.f18861m;
                this.f13367i = lVar == null ? new byte[i12] : (byte[]) lVar.d(i12, byte[].class);
                C1789g c1789g2 = this.c;
                int i13 = this.f13374r * this.f13373q;
                l lVar2 = (l) c1789g2.f18861m;
                this.f13368j = lVar2 == null ? new int[i13] : (int[]) lVar2.d(i13, int[].class);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final Bitmap a() {
        Boolean bool = this.f13375s;
        Bitmap bitmapI = ((InterfaceC1359a) this.c.l).i(this.f13374r, this.f13373q, (bool == null || bool.booleanValue()) ? Bitmap.Config.ARGB_8888 : this.f13376t);
        bitmapI.setHasAlpha(true);
        return bitmapI;
    }

    public final synchronized Bitmap b() {
        try {
            if (this.l.c <= 0 || this.f13369k < 0) {
                if (Log.isLoggable("d", 3)) {
                    Log.d("d", "Unable to decode frame, frameCount=" + this.l.c + ", framePointer=" + this.f13369k);
                }
                this.o = 1;
            }
            int i7 = this.o;
            if (i7 != 1 && i7 != 2) {
                this.o = 0;
                if (this.f13363e == null) {
                    l lVar = (l) this.c.f18861m;
                    this.f13363e = lVar == null ? new byte[255] : (byte[]) lVar.d(255, byte[].class);
                }
                C1068a c1068a = (C1068a) this.l.f13352e.get(this.f13369k);
                int i10 = this.f13369k - 1;
                C1068a c1068a2 = i10 >= 0 ? (C1068a) this.l.f13352e.get(i10) : null;
                int[] iArr = c1068a.f13349k;
                if (iArr == null) {
                    iArr = this.l.f13350a;
                }
                this.f13361a = iArr;
                if (iArr == null) {
                    if (Log.isLoggable("d", 3)) {
                        Log.d("d", "No valid color table found for frame #" + this.f13369k);
                    }
                    this.o = 1;
                    return null;
                }
                if (c1068a.f13344f) {
                    System.arraycopy(iArr, 0, this.f13362b, 0, iArr.length);
                    int[] iArr2 = this.f13362b;
                    this.f13361a = iArr2;
                    iArr2[c1068a.f13346h] = 0;
                    if (c1068a.f13345g == 2 && this.f13369k == 0) {
                        this.f13375s = Boolean.TRUE;
                    }
                }
                return d(c1068a, c1068a2);
            }
            if (Log.isLoggable("d", 3)) {
                Log.d("d", "Unable to decode frame, status=" + this.o);
            }
            return null;
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void c(Bitmap.Config config) {
        Bitmap.Config config2;
        Bitmap.Config config3 = Bitmap.Config.ARGB_8888;
        if (config == config3 || config == (config2 = Bitmap.Config.RGB_565)) {
            this.f13376t = config;
            return;
        }
        throw new IllegalArgumentException("Unsupported format: " + config + ", must be one of " + config3 + " or " + config2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0047  */
    /* JADX WARN: Type inference failed for: r5v25 */
    /* JADX WARN: Type inference failed for: r5v26 */
    /* JADX WARN: Type inference failed for: r5v27 */
    /* JADX WARN: Type inference failed for: r5v32, types: [short] */
    /* JADX WARN: Type inference failed for: r5v34 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.graphics.Bitmap d(d4.C1068a r34, d4.C1068a r35) {
        /*
            Method dump skipped, instruction units count: 1085
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: d4.C1071d.d(d4.a, d4.a):android.graphics.Bitmap");
    }
}
