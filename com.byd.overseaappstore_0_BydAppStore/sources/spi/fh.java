package spi;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class fh implements w1 {
    public static final Bitmap.Config j = Bitmap.Config.ARGB_8888;
    public final hh a;
    public final Set<Bitmap.Config> b;
    public final a c;
    public long d;
    public long e;
    public int f;
    public int g;
    public int h;
    public int i;

    public static final class a {
    }

    public fh(long j2) {
        sp spVar = new sp();
        HashSet hashSet = new HashSet(Arrays.asList(Bitmap.Config.values()));
        hashSet.add(null);
        hashSet.remove(Bitmap.Config.HARDWARE);
        this(j2, spVar, Collections.unmodifiableSet(hashSet));
    }

    public fh(long j2, hh hhVar, Set<Bitmap.Config> set) {
        this.d = j2;
        this.a = hhVar;
        this.b = set;
        this.c = new a();
    }

    @Override // spi.w1
    @SuppressLint({"InlinedApi"})
    public final void a(int i) {
        if (Log.isLoggable("LruBitmapPool", 3)) {
            d.w("trimMemory, level=", i, "LruBitmapPool");
        }
        if (i >= 40 || i >= 20) {
            if (Log.isLoggable("LruBitmapPool", 3)) {
                Log.d("LruBitmapPool", "clearMemory");
            }
            i(0L);
        } else if (i >= 20 || i == 15) {
            i(this.d / 2);
        }
    }

    @Override // spi.w1
    public final void b() {
        if (Log.isLoggable("LruBitmapPool", 3)) {
            Log.d("LruBitmapPool", "clearMemory");
        }
        i(0L);
    }

    @Override // spi.w1
    public final synchronized void c(Bitmap bitmap) {
        if (bitmap == null) {
            throw new NullPointerException("Bitmap must not be null");
        }
        if (bitmap.isRecycled()) {
            throw new IllegalStateException("Cannot pool recycled bitmap");
        }
        if (bitmap.isMutable() && this.a.e(bitmap) <= this.d && this.b.contains(bitmap.getConfig())) {
            int iE = this.a.e(bitmap);
            this.a.c(bitmap);
            Objects.requireNonNull(this.c);
            this.h++;
            this.e += (long) iE;
            if (Log.isLoggable("LruBitmapPool", 2)) {
                Log.v("LruBitmapPool", "Put bitmap in pool=" + this.a.f(bitmap));
            }
            f();
            i(this.d);
            return;
        }
        if (Log.isLoggable("LruBitmapPool", 2)) {
            Log.v("LruBitmapPool", "Reject bitmap from pool, bitmap: " + this.a.f(bitmap) + ", is mutable: " + bitmap.isMutable() + ", is allowed config: " + this.b.contains(bitmap.getConfig()));
        }
        bitmap.recycle();
    }

    @Override // spi.w1
    @NonNull
    public final Bitmap d(int i, int i2, Bitmap.Config config) {
        Bitmap bitmapH = h(i, i2, config);
        if (bitmapH != null) {
            bitmapH.eraseColor(0);
            return bitmapH;
        }
        if (config == null) {
            config = j;
        }
        return Bitmap.createBitmap(i, i2, config);
    }

    @Override // spi.w1
    @NonNull
    public final Bitmap e(int i, int i2, Bitmap.Config config) {
        Bitmap bitmapH = h(i, i2, config);
        if (bitmapH != null) {
            return bitmapH;
        }
        if (config == null) {
            config = j;
        }
        return Bitmap.createBitmap(i, i2, config);
    }

    public final void f() {
        if (Log.isLoggable("LruBitmapPool", 2)) {
            g();
        }
    }

    public final void g() {
        StringBuilder sbK = e.k("Hits=");
        sbK.append(this.f);
        sbK.append(", misses=");
        sbK.append(this.g);
        sbK.append(", puts=");
        sbK.append(this.h);
        sbK.append(", evictions=");
        sbK.append(this.i);
        sbK.append(", currentSize=");
        sbK.append(this.e);
        sbK.append(", maxSize=");
        sbK.append(this.d);
        sbK.append("\nStrategy=");
        sbK.append(this.a);
        Log.v("LruBitmapPool", sbK.toString());
    }

    @Nullable
    public final synchronized Bitmap h(int i, int i2, @Nullable Bitmap.Config config) {
        Bitmap bitmapD;
        if (config == Bitmap.Config.HARDWARE) {
            throw new IllegalArgumentException("Cannot create a mutable Bitmap with config: " + config + ". Consider setting Downsampler#ALLOW_HARDWARE_CONFIG to false in your RequestOptions and/or in GlideBuilder.setDefaultRequestOptions");
        }
        bitmapD = this.a.d(i, i2, config != null ? config : j);
        if (bitmapD == null) {
            if (Log.isLoggable("LruBitmapPool", 3)) {
                Log.d("LruBitmapPool", "Missing bitmap=" + this.a.b(i, i2, config));
            }
            this.g++;
        } else {
            this.f++;
            this.e -= (long) this.a.e(bitmapD);
            Objects.requireNonNull(this.c);
            bitmapD.setHasAlpha(true);
            bitmapD.setPremultiplied(true);
        }
        if (Log.isLoggable("LruBitmapPool", 2)) {
            Log.v("LruBitmapPool", "Get bitmap=" + this.a.b(i, i2, config));
        }
        f();
        return bitmapD;
    }

    public final synchronized void i(long j2) {
        while (this.e > j2) {
            Bitmap bitmapA = this.a.a();
            if (bitmapA == null) {
                if (Log.isLoggable("LruBitmapPool", 5)) {
                    Log.w("LruBitmapPool", "Size mismatch, resetting");
                    g();
                }
                this.e = 0L;
                return;
            }
            Objects.requireNonNull(this.c);
            this.e -= (long) this.a.e(bitmapA);
            this.i++;
            if (Log.isLoggable("LruBitmapPool", 3)) {
                Log.d("LruBitmapPool", "Evicting bitmap=" + this.a.f(bitmapA));
            }
            f();
            bitmapA.recycle();
        }
    }

    public fh(long j2, Set<Bitmap.Config> set) {
        this(j2, new sp(), set);
    }
}
