package r1;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.util.Log;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: LruBitmapPool.java */
/* JADX INFO: loaded from: classes.dex */
public class j implements d {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final Bitmap.Config f9348k = Bitmap.Config.ARGB_8888;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final k f9349a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Set<Bitmap.Config> f9350b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final long f9351c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final a f9352d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private long f9353e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private long f9354f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f9355g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f9356h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f9357i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private int f9358j;

    /* JADX INFO: compiled from: LruBitmapPool.java */
    private interface a {
        void a(Bitmap bitmap);

        void b(Bitmap bitmap);
    }

    /* JADX INFO: compiled from: LruBitmapPool.java */
    private static final class b implements a {
        b() {
        }

        @Override // r1.j.a
        public void a(Bitmap bitmap) {
        }

        @Override // r1.j.a
        public void b(Bitmap bitmap) {
        }
    }

    j(long j7, k kVar, Set<Bitmap.Config> set) {
        this.f9351c = j7;
        this.f9353e = j7;
        this.f9349a = kVar;
        this.f9350b = set;
        this.f9352d = new b();
    }

    @TargetApi(26)
    private static void f(Bitmap.Config config) {
        if (config != Bitmap.Config.HARDWARE) {
            return;
        }
        throw new IllegalArgumentException("Cannot create a mutable Bitmap with config: " + config + ". Consider setting Downsampler#ALLOW_HARDWARE_CONFIG to false in your RequestOptions and/or in GlideBuilder.setDefaultRequestOptions");
    }

    private static Bitmap g(int i7, int i8, Bitmap.Config config) {
        if (config == null) {
            config = f9348k;
        }
        return Bitmap.createBitmap(i7, i8, config);
    }

    private void h() {
        if (Log.isLoggable("LruBitmapPool", 2)) {
            i();
        }
    }

    private void i() {
        Log.v("LruBitmapPool", "Hits=" + this.f9355g + ", misses=" + this.f9356h + ", puts=" + this.f9357i + ", evictions=" + this.f9358j + ", currentSize=" + this.f9354f + ", maxSize=" + this.f9353e + "\nStrategy=" + this.f9349a);
    }

    private void j() {
        q(this.f9353e);
    }

    @TargetApi(26)
    private static Set<Bitmap.Config> k() {
        HashSet hashSet = new HashSet(Arrays.asList(Bitmap.Config.values()));
        hashSet.add(null);
        hashSet.remove(Bitmap.Config.HARDWARE);
        return Collections.unmodifiableSet(hashSet);
    }

    private static k l() {
        return new m();
    }

    private synchronized Bitmap m(int i7, int i8, Bitmap.Config config) {
        Bitmap bitmapC;
        f(config);
        bitmapC = this.f9349a.c(i7, i8, config != null ? config : f9348k);
        if (bitmapC == null) {
            if (Log.isLoggable("LruBitmapPool", 3)) {
                Log.d("LruBitmapPool", "Missing bitmap=" + this.f9349a.e(i7, i8, config));
            }
            this.f9356h++;
        } else {
            this.f9355g++;
            this.f9354f -= (long) this.f9349a.a(bitmapC);
            this.f9352d.a(bitmapC);
            p(bitmapC);
        }
        if (Log.isLoggable("LruBitmapPool", 2)) {
            Log.v("LruBitmapPool", "Get bitmap=" + this.f9349a.e(i7, i8, config));
        }
        h();
        return bitmapC;
    }

    @TargetApi(19)
    private static void o(Bitmap bitmap) {
        bitmap.setPremultiplied(true);
    }

    private static void p(Bitmap bitmap) {
        bitmap.setHasAlpha(true);
        o(bitmap);
    }

    private synchronized void q(long j7) {
        while (this.f9354f > j7) {
            Bitmap bitmapB = this.f9349a.b();
            if (bitmapB == null) {
                if (Log.isLoggable("LruBitmapPool", 5)) {
                    Log.w("LruBitmapPool", "Size mismatch, resetting");
                    i();
                }
                this.f9354f = 0L;
                return;
            }
            this.f9352d.a(bitmapB);
            this.f9354f -= (long) this.f9349a.a(bitmapB);
            this.f9358j++;
            if (Log.isLoggable("LruBitmapPool", 3)) {
                Log.d("LruBitmapPool", "Evicting bitmap=" + this.f9349a.f(bitmapB));
            }
            h();
            bitmapB.recycle();
        }
    }

    @Override // r1.d
    @SuppressLint({"InlinedApi"})
    public void a(int i7) {
        if (Log.isLoggable("LruBitmapPool", 3)) {
            Log.d("LruBitmapPool", "trimMemory, level=" + i7);
        }
        if (i7 >= 40 || i7 >= 20) {
            b();
        } else if (i7 >= 20 || i7 == 15) {
            q(n() / 2);
        }
    }

    @Override // r1.d
    public void b() {
        if (Log.isLoggable("LruBitmapPool", 3)) {
            Log.d("LruBitmapPool", "clearMemory");
        }
        q(0L);
    }

    @Override // r1.d
    public Bitmap c(int i7, int i8, Bitmap.Config config) {
        Bitmap bitmapM = m(i7, i8, config);
        if (bitmapM == null) {
            return g(i7, i8, config);
        }
        bitmapM.eraseColor(0);
        return bitmapM;
    }

    @Override // r1.d
    public synchronized void d(Bitmap bitmap) {
        try {
            if (bitmap == null) {
                throw new NullPointerException("Bitmap must not be null");
            }
            if (bitmap.isRecycled()) {
                throw new IllegalStateException("Cannot pool recycled bitmap");
            }
            if (bitmap.isMutable() && this.f9349a.a(bitmap) <= this.f9353e && this.f9350b.contains(bitmap.getConfig())) {
                int iA = this.f9349a.a(bitmap);
                this.f9349a.d(bitmap);
                this.f9352d.b(bitmap);
                this.f9357i++;
                this.f9354f += (long) iA;
                if (Log.isLoggable("LruBitmapPool", 2)) {
                    Log.v("LruBitmapPool", "Put bitmap in pool=" + this.f9349a.f(bitmap));
                }
                h();
                j();
                return;
            }
            if (Log.isLoggable("LruBitmapPool", 2)) {
                Log.v("LruBitmapPool", "Reject bitmap from pool, bitmap: " + this.f9349a.f(bitmap) + ", is mutable: " + bitmap.isMutable() + ", is allowed config: " + this.f9350b.contains(bitmap.getConfig()));
            }
            bitmap.recycle();
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // r1.d
    public Bitmap e(int i7, int i8, Bitmap.Config config) {
        Bitmap bitmapM = m(i7, i8, config);
        return bitmapM == null ? g(i7, i8, config) : bitmapM;
    }

    public long n() {
        return this.f9353e;
    }

    public j(long j7) {
        this(j7, l(), k());
    }
}
