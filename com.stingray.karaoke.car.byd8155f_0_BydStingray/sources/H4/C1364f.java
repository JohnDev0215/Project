package h4;

import A4.q;
import android.graphics.Bitmap;
import android.util.Log;
import h6.n;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: renamed from: h4.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1364f implements InterfaceC1359a {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final Bitmap.Config f15516u = Bitmap.Config.ARGB_8888;
    public final C1368j l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Set f15517m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final io.sentry.hints.i f15518n;
    public final long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f15519p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f15520q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f15521r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f15522s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f15523t;

    public C1364f(long j3) {
        C1368j c1368j = new C1368j();
        HashSet hashSet = new HashSet(Arrays.asList(Bitmap.Config.values()));
        hashSet.add(null);
        hashSet.remove(Bitmap.Config.HARDWARE);
        Set setUnmodifiableSet = Collections.unmodifiableSet(hashSet);
        this.o = j3;
        this.l = c1368j;
        this.f15517m = setUnmodifiableSet;
        this.f15518n = new io.sentry.hints.i(22);
    }

    public final void a() {
        Log.v("LruBitmapPool", "Hits=" + this.f15520q + ", misses=" + this.f15521r + ", puts=" + this.f15522s + ", evictions=" + this.f15523t + ", currentSize=" + this.f15519p + ", maxSize=" + this.o + "\nStrategy=" + this.l);
    }

    @Override // h4.InterfaceC1359a
    public final void b(int i7) {
        if (Log.isLoggable("LruBitmapPool", 3)) {
            android.hardware.bydauto.ac.tsimpl.a.o("trimMemory, level=", i7, "LruBitmapPool");
        }
        if (i7 >= 40 || i7 >= 20) {
            z();
        } else if (i7 >= 20 || i7 == 15) {
            d(this.o / 2);
        }
    }

    public final synchronized Bitmap c(int i7, int i10, Bitmap.Config config) {
        Bitmap bitmapB;
        try {
            if (config == Bitmap.Config.HARDWARE) {
                throw new IllegalArgumentException("Cannot create a mutable Bitmap with config: " + config + ". Consider setting Downsampler#ALLOW_HARDWARE_CONFIG to false in your RequestOptions and/or in GlideBuilder.setDefaultRequestOptions");
            }
            bitmapB = this.l.b(i7, i10, config != null ? config : f15516u);
            if (bitmapB == null) {
                if (Log.isLoggable("LruBitmapPool", 3)) {
                    StringBuilder sb = new StringBuilder("Missing bitmap=");
                    this.l.getClass();
                    sb.append(C1368j.c(q.d(config) * i7 * i10, config));
                    Log.d("LruBitmapPool", sb.toString());
                }
                this.f15521r++;
            } else {
                this.f15520q++;
                long j3 = this.f15519p;
                this.l.getClass();
                this.f15519p = j3 - ((long) q.c(bitmapB));
                this.f15518n.getClass();
                bitmapB.setHasAlpha(true);
                bitmapB.setPremultiplied(true);
            }
            if (Log.isLoggable("LruBitmapPool", 2)) {
                StringBuilder sb2 = new StringBuilder("Get bitmap=");
                this.l.getClass();
                sb2.append(C1368j.c(q.d(config) * i7 * i10, config));
                Log.v("LruBitmapPool", sb2.toString());
            }
            if (Log.isLoggable("LruBitmapPool", 2)) {
                a();
            }
        } catch (Throwable th) {
            throw th;
        }
        return bitmapB;
    }

    public final synchronized void d(long j3) {
        while (this.f15519p > j3) {
            try {
                C1368j c1368j = this.l;
                Bitmap bitmap = (Bitmap) c1368j.f15532b.F();
                if (bitmap != null) {
                    c1368j.a(Integer.valueOf(q.c(bitmap)), bitmap);
                }
                if (bitmap == null) {
                    if (Log.isLoggable("LruBitmapPool", 5)) {
                        n.t("LruBitmapPool", "Size mismatch, resetting");
                        a();
                    }
                    this.f15519p = 0L;
                    return;
                }
                this.f15518n.getClass();
                long j10 = this.f15519p;
                this.l.getClass();
                this.f15519p = j10 - ((long) q.c(bitmap));
                this.f15523t++;
                if (Log.isLoggable("LruBitmapPool", 3)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Evicting bitmap=");
                    this.l.getClass();
                    sb.append(C1368j.c(q.c(bitmap), bitmap.getConfig()));
                    Log.d("LruBitmapPool", sb.toString());
                }
                if (Log.isLoggable("LruBitmapPool", 2)) {
                    a();
                }
                bitmap.recycle();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // h4.InterfaceC1359a
    public final Bitmap i(int i7, int i10, Bitmap.Config config) {
        Bitmap bitmapC = c(i7, i10, config);
        if (bitmapC != null) {
            return bitmapC;
        }
        if (config == null) {
            config = f15516u;
        }
        return Bitmap.createBitmap(i7, i10, config);
    }

    @Override // h4.InterfaceC1359a
    public final Bitmap j(int i7, int i10, Bitmap.Config config) {
        Bitmap bitmapC = c(i7, i10, config);
        if (bitmapC != null) {
            bitmapC.eraseColor(0);
            return bitmapC;
        }
        if (config == null) {
            config = f15516u;
        }
        return Bitmap.createBitmap(i7, i10, config);
    }

    @Override // h4.InterfaceC1359a
    public final synchronized void k(Bitmap bitmap) {
        try {
            if (bitmap == null) {
                throw new NullPointerException("Bitmap must not be null");
            }
            if (bitmap.isRecycled()) {
                throw new IllegalStateException("Cannot pool recycled bitmap");
            }
            if (bitmap.isMutable()) {
                this.l.getClass();
                if (q.c(bitmap) <= this.o && this.f15517m.contains(bitmap.getConfig())) {
                    this.l.getClass();
                    int iC = q.c(bitmap);
                    this.l.e(bitmap);
                    this.f15518n.getClass();
                    this.f15522s++;
                    this.f15519p += (long) iC;
                    if (Log.isLoggable("LruBitmapPool", 2)) {
                        StringBuilder sb = new StringBuilder("Put bitmap in pool=");
                        this.l.getClass();
                        sb.append(C1368j.c(q.c(bitmap), bitmap.getConfig()));
                        Log.v("LruBitmapPool", sb.toString());
                    }
                    if (Log.isLoggable("LruBitmapPool", 2)) {
                        a();
                    }
                    d(this.o);
                    return;
                }
            }
            if (Log.isLoggable("LruBitmapPool", 2)) {
                StringBuilder sb2 = new StringBuilder("Reject bitmap from pool, bitmap: ");
                this.l.getClass();
                sb2.append(C1368j.c(q.c(bitmap), bitmap.getConfig()));
                sb2.append(", is mutable: ");
                sb2.append(bitmap.isMutable());
                sb2.append(", is allowed config: ");
                sb2.append(this.f15517m.contains(bitmap.getConfig()));
                Log.v("LruBitmapPool", sb2.toString());
            }
            bitmap.recycle();
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // h4.InterfaceC1359a
    public final void z() {
        if (Log.isLoggable("LruBitmapPool", 3)) {
            Log.d("LruBitmapPool", "clearMemory");
        }
        d(0L);
    }
}
