package c.a.d;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.widget.ImageView;
import java.lang.ref.SoftReference;
import java.util.ArrayList;

/* JADX INFO: compiled from: FasterAnimationsContainer.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public transient ArrayList<b> f1627b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public transient int f1628c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public transient boolean f1629d;
    public transient boolean e;
    public transient SoftReference<ImageView> f;
    public transient Handler g;
    public transient Context h;
    public transient d i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public transient Object f1626a = new Object();
    public transient boolean j = false;
    public transient int k = 0;

    /* JADX INFO: compiled from: FasterAnimationsContainer.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public transient int f1630a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public transient int f1631b;

        public b(int i, int i2) {
            this.f1630a = i;
            this.f1631b = i2;
        }

        public final int c() {
            return this.f1631b;
        }

        public final int d() {
            return this.f1630a;
        }
    }

    /* JADX INFO: compiled from: FasterAnimationsContainer.java */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!g.this.f1629d || g.this.f.get() == null) {
                g.this.e = false;
                if (g.this.i != null) {
                    g.this.i.a();
                    return;
                }
                return;
            }
            g.this.e = true;
            if (!((ImageView) g.this.f.get()).isShown()) {
                ((ImageView) g.this.f.get()).setImageDrawable(null);
                if (g.this.j) {
                    return;
                }
                g.this.g.postDelayed(this, 1000L);
                return;
            }
            ((ImageView) g.this.f.get()).setImageDrawable(b.g.b.a.d(g.this.h, g.this.k().d()));
            if (g.this.j) {
                return;
            }
            g.this.g.postDelayed(this, r0.c());
        }
    }

    /* JADX INFO: compiled from: FasterAnimationsContainer.java */
    public interface d {
        void a();
    }

    public g(ImageView imageView, Context context) {
        this.h = context;
        l(imageView, context);
    }

    public void i(int i, int i2) {
        for (int i3 : j(i)) {
            this.f1627b.add(new b(i3, i2));
        }
    }

    public final int[] j(int i) {
        TypedArray typedArrayObtainTypedArray = this.h.getResources().obtainTypedArray(i);
        int length = typedArrayObtainTypedArray.length();
        int[] iArr = new int[typedArrayObtainTypedArray.length()];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = typedArrayObtainTypedArray.getResourceId(i2, 0);
        }
        typedArrayObtainTypedArray.recycle();
        return iArr;
    }

    public final b k() {
        if (this.j) {
            return this.f1627b.get(this.k);
        }
        int i = this.f1628c + 1;
        this.f1628c = i;
        if (i >= this.f1627b.size()) {
            this.f1628c = 0;
        }
        return this.f1627b.get(this.f1628c);
    }

    public final void l(ImageView imageView, Context context) {
        this.f1627b = new ArrayList<>();
        this.f = new SoftReference<>(imageView);
        this.g = new Handler();
        this.f1629d = false;
        this.e = false;
        this.f1628c = -1;
    }

    public void m() {
        this.f1627b.clear();
    }

    public void n() {
        synchronized (this.f1626a) {
            this.j = false;
            this.f1629d = true;
            if (this.e) {
                return;
            }
            this.g.post(new c());
        }
    }

    public void o() {
        synchronized (this.f1626a) {
            this.j = true;
            this.f1629d = false;
            this.g.removeCallbacksAndMessages(null);
        }
    }
}
