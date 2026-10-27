package b.b.g.r;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import java.lang.ref.SoftReference;
import java.util.ArrayList;

/* JADX INFO: compiled from: FasterAnimationsContainer.java */
/* JADX INFO: loaded from: classes2.dex */
public class o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ArrayList<b> f1820b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1821c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1822d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1823e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public SoftReference<ImageView> f1824f;
    public Handler g;
    public e h;
    public d i;
    public Context j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public transient Object f1819a = new Object();
    public boolean k = false;
    public int l = 0;

    /* JADX INFO: compiled from: FasterAnimationsContainer.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1825a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1826b;

        public b(int i, int i2) {
            this.f1825a = i;
            this.f1826b = i2;
        }

        public int a() {
            return this.f1826b;
        }

        public int b() {
            return this.f1825a;
        }
    }

    /* JADX INFO: compiled from: FasterAnimationsContainer.java */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!o.this.f1822d || o.this.f1824f.get() == null) {
                o.this.f1823e = false;
                if (o.this.h != null) {
                    o.this.h.a();
                    return;
                }
                return;
            }
            o.this.f1823e = true;
            if (!((ImageView) o.this.f1824f.get()).isShown()) {
                ((ImageView) o.this.f1824f.get()).setImageDrawable(null);
                if (o.this.k) {
                    return;
                }
                o.this.g.postDelayed(this, 1000L);
                return;
            }
            ((ImageView) o.this.f1824f.get()).setImageDrawable(ContextCompat.getDrawable(o.this.j, o.this.k().b()));
            if (o.this.k) {
                return;
            }
            o.this.g.postDelayed(this, r0.a());
        }
    }

    /* JADX INFO: compiled from: FasterAnimationsContainer.java */
    public interface d {
    }

    /* JADX INFO: compiled from: FasterAnimationsContainer.java */
    public interface e {
        void a();
    }

    public o(ImageView imageView, Context context) {
        this.j = context;
        l(imageView, context);
    }

    public void i(int i, int i2) {
        for (int i3 : j(i)) {
            this.f1820b.add(new b(i3, i2));
        }
    }

    public final int[] j(int i) {
        TypedArray typedArrayObtainTypedArray = this.j.getResources().obtainTypedArray(i);
        int length = typedArrayObtainTypedArray.length();
        int[] iArr = new int[typedArrayObtainTypedArray.length()];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = typedArrayObtainTypedArray.getResourceId(i2, 0);
        }
        typedArrayObtainTypedArray.recycle();
        return iArr;
    }

    public final b k() {
        if (this.k) {
            return this.f1820b.get(this.l);
        }
        int i = this.f1821c + 1;
        this.f1821c = i;
        if (i >= this.f1820b.size()) {
            this.f1821c = 0;
        }
        return this.f1820b.get(this.f1821c);
    }

    public final void l(ImageView imageView, Context context) {
        this.f1820b = new ArrayList<>();
        this.f1824f = new SoftReference<>(imageView);
        this.g = new Handler();
        this.f1822d = false;
        this.f1823e = false;
        this.f1821c = -1;
    }

    public void m() {
        this.f1820b.clear();
    }

    public synchronized void n() {
        synchronized (this.f1819a) {
            this.k = false;
            this.f1822d = true;
            if (this.f1823e) {
                return;
            }
            this.g.post(new c());
        }
    }

    public synchronized void o() {
        synchronized (this.f1819a) {
            this.k = true;
            this.f1822d = false;
            this.g.removeCallbacksAndMessages(null);
        }
    }

    public void setOnAnimationFrameChangedListener(d dVar) {
        this.i = dVar;
    }

    public void setOnAnimationStoppedListener(e eVar) {
        this.h = eVar;
    }
}
