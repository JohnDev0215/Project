package defpackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;

/* JADX INFO: compiled from: BaseGifDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public final class nd extends Drawable implements Runnable, Animatable, Handler.Callback {
    public static final byte[] Z = "NETSCAPE2.0".getBytes();
    public static Paint a0;
    public static Paint b0;
    public static a c0;
    public static Handler d0;
    public int A;
    public int B;
    public boolean E;
    public int F;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public byte[] f48J;
    public boolean K;
    public int[] L;
    public int M;
    public long N;
    public boolean O;
    public int P;
    public int Q;
    public boolean R;
    public boolean U;
    public boolean V;
    public final od e;
    public final byte[] f;
    public int g;
    public final int h;
    public final int i;
    public int j;
    public int k;
    public final Bitmap l;
    public final int[] m;
    public boolean n;
    public float o;
    public volatile boolean p;
    public volatile boolean q;
    public int s;
    public boolean t;
    public int[] u;
    public int[] v;
    public boolean w;
    public int x;
    public int y;
    public int z;
    public volatile boolean r = true;
    public final byte[] C = new byte[256];
    public int D = 2;
    public final short[] G = new short[4096];
    public final byte[] H = new byte[4096];
    public final byte[] I = new byte[4097];
    public final boolean S = true;
    public final Handler T = new Handler(Looper.getMainLooper(), this);
    public int W = 0;
    public int X = 0;
    public boolean Y = true;

    /* JADX INFO: compiled from: BaseGifDrawable.java */
    public static class a extends HandlerThread implements Handler.Callback {
        public a() {
            super("GifDecoder");
        }

        /* JADX WARN: Removed duplicated region for block: B:28:0x0046  */
        @Override // android.os.Handler.Callback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean handleMessage(android.os.Message r4) {
            /*
                r3 = this;
                java.lang.Object r3 = r4.obj
                nd r3 = (defpackage.nd) r3
                r0 = 1
                if (r3 == 0) goto L6e
                android.graphics.Bitmap r1 = r3.l
                if (r1 == 0) goto L6e
                int r4 = r4.what
                r1 = 10
                r2 = 0
                if (r4 == r1) goto L24
                r1 = 12
                if (r4 == r1) goto L17
                return r2
            L17:
                od r4 = r3.e
                int r4 = r4.d
                r3.g = r4
                r3.K = r2
                r3.M = r2
                r3.D = r2
                return r0
            L24:
                defpackage.nd.b(r3)     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L28
                goto L2a
            L28:
                r3.V = r0
            L2a:
                boolean r4 = r3.V
                if (r4 == 0) goto L55
                int r4 = r3.M
                if (r4 != 0) goto L35
                r3.p = r0
                goto L55
            L35:
                if (r4 <= r0) goto L53
                int r4 = r3.W
                if (r4 == 0) goto L46
                int r1 = r3.X
                int r1 = r1 + r0
                r3.X = r1
                if (r1 >= r4) goto L43
                goto L46
            L43:
                r3.q = r0
                goto L55
            L46:
                od r4 = r3.e
                int r4 = r4.d
                r3.g = r4
                r3.K = r2
                r3.M = r2
                r3.D = r2
                goto L55
            L53:
                r3.q = r0
            L55:
                boolean r4 = r3.V
                if (r4 == 0) goto L61
                boolean r4 = r3.p
                if (r4 != 0) goto L61
                boolean r4 = r3.q
                if (r4 == 0) goto L24
            L61:
                android.os.Handler r4 = r3.T
                int r3 = r3.Q
                r1 = 11
                android.os.Message r3 = r4.obtainMessage(r1, r3, r2)
                r4.sendMessage(r3)
            L6e:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: nd.a.handleMessage(android.os.Message):boolean");
        }
    }

    public nd(od odVar, Bitmap.Config config) {
        if (c0 == null) {
            a aVar = new a();
            c0 = aVar;
            aVar.start();
            d0 = new Handler(c0.getLooper(), c0);
        }
        if (a0 == null) {
            a0 = new Paint(2);
            Paint paint = new Paint(2);
            b0 = paint;
            paint.setFilterBitmap(true);
        }
        this.e = odVar;
        this.f = odVar.a;
        this.g = odVar.d;
        int i = odVar.b;
        this.h = i;
        this.B = i;
        this.z = i;
        int i2 = odVar.c;
        this.i = i2;
        this.A = i2;
        this.s = odVar.i;
        this.p = odVar.f;
        if (this.p) {
            return;
        }
        try {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, config);
            this.l = bitmapCreateBitmap;
            if (bitmapCreateBitmap == null) {
                throw new OutOfMemoryError("Cannot allocate bitmap");
            }
            int i3 = i * i2;
            this.m = new int[i3];
            this.f48J = new byte[i3];
            this.j = i2;
            this.k = i2;
            Handler handler = d0;
            handler.sendMessage(handler.obtainMessage(10, this));
        } catch (OutOfMemoryError unused) {
            this.p = true;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:106:0x0230, code lost:
    
        r27.p = true;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v33 */
    /* JADX WARN: Type inference failed for: r2v59 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r4v10, types: [boolean] */
    /* JADX WARN: Type inference failed for: r4v50 */
    /* JADX WARN: Type inference failed for: r4v51 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7, types: [boolean] */
    /* JADX WARN: Type inference failed for: r4v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void b(defpackage.nd r27) {
        /*
            Method dump skipped, instruction units count: 1025
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nd.b(nd):void");
    }

    public final int c() {
        int i = this.g;
        int i2 = i + 1;
        byte[] bArr = this.f;
        int i3 = bArr[i] & 255;
        this.g = i2 + 1;
        return ((bArr[i2] & 255) << 8) | i3;
    }

    public final void d() {
        int i;
        do {
            int i2 = this.g;
            int i3 = i2 + 1;
            i = this.f[i2] & 255;
            this.g = i3 + i;
        } while (i > 0);
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        if (this.p || this.j == 0 || this.k == 0 || !this.U) {
            return;
        }
        if (this.n) {
            canvas.save();
            float f = this.o;
            canvas.scale(f, f, 0.0f, 0.0f);
            canvas.drawBitmap(this.l, 0.0f, 0.0f, b0);
            canvas.restore();
        } else {
            canvas.drawBitmap(this.l, 0.0f, 0.0f, a0);
        }
        if (!this.O) {
            if (this.q) {
                unscheduleSelf(this);
                return;
            } else {
                start();
                return;
            }
        }
        if (this.R) {
            return;
        }
        long jMax = Math.max(this.N + ((long) this.P), SystemClock.uptimeMillis() + 5);
        this.N = jMax;
        scheduleSelf(this, jMax);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.i;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.h;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return 0;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        if (message.what != 11) {
            return false;
        }
        this.P = message.arg1;
        Bitmap bitmap = this.l;
        if (bitmap != null) {
            int[] iArr = this.m;
            int i = this.h;
            bitmap.setPixels(iArr, 0, i, 0, 0, i, this.i);
            this.U = true;
            this.R = false;
            invalidateSelf();
        }
        return true;
    }

    @Override // android.graphics.drawable.Animatable
    public final boolean isRunning() {
        return this.O;
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.j = rect.width();
        int iHeight = rect.height();
        this.k = iHeight;
        int i = this.j;
        int i2 = this.h;
        boolean z = (i == i2 || iHeight == this.i) ? false : true;
        this.n = z;
        if (z) {
            this.o = Math.max(i / i2, iHeight / this.i);
        }
        if (this.p) {
            return;
        }
        Handler handler = d0;
        handler.sendMessage(handler.obtainMessage(12, this));
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.q) {
            return;
        }
        Handler handler = d0;
        handler.sendMessage(handler.obtainMessage(10, this));
    }

    @Override // android.graphics.drawable.Drawable
    public final void scheduleSelf(Runnable runnable, long j) {
        if (this.S) {
            super.scheduleSelf(runnable, j);
            this.R = true;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (!z) {
            stop();
        } else if (visible || z2) {
            start();
        }
        return visible;
    }

    @Override // android.graphics.drawable.Animatable
    public final void start() {
        if (this.O) {
            return;
        }
        this.O = true;
        if (!this.r) {
            this.q = true;
        }
        this.N = SystemClock.uptimeMillis();
        run();
    }

    @Override // android.graphics.drawable.Animatable
    public final void stop() {
        if (this.O) {
            unscheduleSelf(this);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void unscheduleSelf(Runnable runnable) {
        super.unscheduleSelf(runnable);
        this.O = false;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
    }
}
