package f2;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class g implements d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f14575b;
    public float c;
    public float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b f14576e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b f14577f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public b f14578g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public b f14579h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f14580i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public f f14581j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ByteBuffer f14582k;
    public ShortBuffer l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ByteBuffer f14583m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f14584n;
    public long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f14585p;

    @Override // f2.d
    public final ByteBuffer a() {
        f fVar = this.f14581j;
        if (fVar != null) {
            int i7 = fVar.f14565m;
            int i10 = fVar.f14557b;
            int i11 = i7 * i10 * 2;
            if (i11 > 0) {
                if (this.f14582k.capacity() < i11) {
                    ByteBuffer byteBufferOrder = ByteBuffer.allocateDirect(i11).order(ByteOrder.nativeOrder());
                    this.f14582k = byteBufferOrder;
                    this.l = byteBufferOrder.asShortBuffer();
                } else {
                    this.f14582k.clear();
                    this.l.clear();
                }
                ShortBuffer shortBuffer = this.l;
                int iMin = Math.min(shortBuffer.remaining() / i10, fVar.f14565m);
                int i12 = iMin * i10;
                shortBuffer.put(fVar.l, 0, i12);
                int i13 = fVar.f14565m - iMin;
                fVar.f14565m = i13;
                short[] sArr = fVar.l;
                System.arraycopy(sArr, i12, sArr, 0, i13 * i10);
                this.o += (long) i11;
                this.f14582k.limit(i11);
                this.f14583m = this.f14582k;
            }
        }
        ByteBuffer byteBuffer = this.f14583m;
        this.f14583m = d.f14550a;
        return byteBuffer;
    }

    @Override // f2.d
    public final boolean b() {
        return this.f14577f.f14548a != -1 && (Math.abs(this.c - 1.0f) >= 1.0E-4f || Math.abs(this.d - 1.0f) >= 1.0E-4f || this.f14577f.f14548a != this.f14576e.f14548a);
    }

    @Override // f2.d
    public final b c(b bVar) throws c {
        if (bVar.c != 2) {
            throw new c(bVar);
        }
        int i7 = this.f14575b;
        if (i7 == -1) {
            i7 = bVar.f14548a;
        }
        this.f14576e = bVar;
        b bVar2 = new b(i7, bVar.f14549b, 2);
        this.f14577f = bVar2;
        this.f14580i = true;
        return bVar2;
    }

    @Override // f2.d
    public final void d() {
        this.c = 1.0f;
        this.d = 1.0f;
        b bVar = b.f14547e;
        this.f14576e = bVar;
        this.f14577f = bVar;
        this.f14578g = bVar;
        this.f14579h = bVar;
        ByteBuffer byteBuffer = d.f14550a;
        this.f14582k = byteBuffer;
        this.l = byteBuffer.asShortBuffer();
        this.f14583m = byteBuffer;
        this.f14575b = -1;
        this.f14580i = false;
        this.f14581j = null;
        this.f14584n = 0L;
        this.o = 0L;
        this.f14585p = false;
    }

    @Override // f2.d
    public final void e() {
        f fVar = this.f14581j;
        if (fVar != null) {
            int i7 = fVar.f14564k;
            float f10 = fVar.c;
            float f11 = fVar.d;
            double d = f10 / f11;
            double d10 = fVar.f14558e * f11;
            int i10 = fVar.f14569r;
            int i11 = fVar.f14565m + ((int) ((((((((double) (i7 - i10)) / d) + ((double) i10)) + fVar.f14574w) + ((double) fVar.o)) / d10) + 0.5d));
            fVar.f14574w = 0.0d;
            short[] sArr = fVar.f14563j;
            int i12 = fVar.f14561h * 2;
            fVar.f14563j = fVar.c(sArr, i7, i12 + i7);
            int i13 = 0;
            while (true) {
                int i14 = fVar.f14557b;
                if (i13 >= i12 * i14) {
                    break;
                }
                fVar.f14563j[(i14 * i7) + i13] = 0;
                i13++;
            }
            fVar.f14564k = i12 + fVar.f14564k;
            fVar.f();
            if (fVar.f14565m > i11) {
                fVar.f14565m = i11;
            }
            fVar.f14564k = 0;
            fVar.f14569r = 0;
            fVar.o = 0;
        }
        this.f14585p = true;
    }

    @Override // f2.d
    public final boolean f() {
        f fVar;
        return this.f14585p && ((fVar = this.f14581j) == null || (fVar.f14565m * fVar.f14557b) * 2 == 0);
    }

    @Override // f2.d
    public final void flush() {
        if (b()) {
            b bVar = this.f14576e;
            this.f14578g = bVar;
            b bVar2 = this.f14577f;
            this.f14579h = bVar2;
            if (this.f14580i) {
                int i7 = bVar.f14548a;
                this.f14581j = new f(this.c, this.d, i7, bVar.f14549b, bVar2.f14548a);
            } else {
                f fVar = this.f14581j;
                if (fVar != null) {
                    fVar.f14564k = 0;
                    fVar.f14565m = 0;
                    fVar.o = 0;
                    fVar.f14567p = 0;
                    fVar.f14568q = 0;
                    fVar.f14569r = 0;
                    fVar.f14570s = 0;
                    fVar.f14571t = 0;
                    fVar.f14572u = 0;
                    fVar.f14573v = 0;
                    fVar.f14574w = 0.0d;
                }
            }
        }
        this.f14583m = d.f14550a;
        this.f14584n = 0L;
        this.o = 0L;
        this.f14585p = false;
    }

    @Override // f2.d
    public final void g(ByteBuffer byteBuffer) {
        if (byteBuffer.hasRemaining()) {
            f fVar = this.f14581j;
            fVar.getClass();
            ShortBuffer shortBufferAsShortBuffer = byteBuffer.asShortBuffer();
            int iRemaining = byteBuffer.remaining();
            this.f14584n += (long) iRemaining;
            int iRemaining2 = shortBufferAsShortBuffer.remaining();
            int i7 = fVar.f14557b;
            int i10 = iRemaining2 / i7;
            short[] sArrC = fVar.c(fVar.f14563j, fVar.f14564k, i10);
            fVar.f14563j = sArrC;
            shortBufferAsShortBuffer.get(sArrC, fVar.f14564k * i7, ((i10 * i7) * 2) / 2);
            fVar.f14564k += i10;
            fVar.f();
            byteBuffer.position(byteBuffer.position() + iRemaining);
        }
    }
}
