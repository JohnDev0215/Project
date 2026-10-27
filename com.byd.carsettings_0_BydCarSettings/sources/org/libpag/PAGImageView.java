package org.libpag;

import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.libpag.CacheManager;
import org.libpag.l;

/* JADX INFO: loaded from: classes3.dex */
public class PAGImageView extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Object f4028a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static long f4029b = 1073741824;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static ThreadPoolExecutor f4030c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static ThreadPoolExecutor f4031d;
    private final ValueAnimator.AnimatorUpdateListener A;
    private ArrayList<PAGImageViewListener> B;
    private final AnimatorListenerAdapter C;
    private volatile int D;
    private volatile int E;
    private volatile int F;
    private volatile int G;
    private float H;
    private boolean I;
    private volatile boolean J;
    private Runnable K;
    private Runnable L;
    private WeakReference<Future> M;
    private boolean N;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ValueAnimator f4032e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f4033f;
    private volatile boolean g;
    private volatile Boolean h;
    private volatile boolean i;
    private final Object j;
    private int k;
    private float l;
    private PAGComposition m;
    private String n;
    private int o;
    private Matrix p;
    private ArrayList<WeakReference<Future>> q;
    private float r;
    public volatile CacheManager.a s;
    public volatile a t;
    public volatile l.b u;
    public volatile CacheManager v;
    private volatile Bitmap w;
    private ConcurrentHashMap<Integer, Bitmap> x;
    private Matrix y;
    private volatile long z;

    public interface PAGImageViewListener {
        void onAnimationCancel(PAGImageView pAGImageView);

        void onAnimationEnd(PAGImageView pAGImageView);

        void onAnimationRepeat(PAGImageView pAGImageView);

        void onAnimationStart(PAGImageView pAGImageView);

        void onAnimationUpdate(PAGImageView pAGImageView);
    }

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f4034a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f4035b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f4036c = false;
    }

    public PAGImageView(Context context) {
        super(context);
        this.f4033f = false;
        this.g = false;
        this.h = null;
        this.i = true;
        this.j = new Object();
        this.l = 30.0f;
        this.o = 2;
        this.q = new ArrayList<>();
        this.r = 1.0f;
        this.u = new l.b();
        this.x = new ConcurrentHashMap<>();
        this.A = new f(this);
        this.B = new ArrayList<>();
        this.C = new g(this);
        this.H = 1.0f;
        this.I = false;
        this.J = false;
        this.K = new h(this);
        this.L = new i(this);
        this.N = true;
        g();
    }

    public static long MaxDiskCache() {
        return f4029b;
    }

    public static void SetMaxDiskCache(long j) {
        f4029b = j;
    }

    private void g() {
        c();
        this.v = CacheManager.a(getContext());
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.f4032e = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setRepeatCount(0);
        this.f4032e.setInterpolator(new LinearInterpolator());
        this.f4032e.addUpdateListener(this.A);
        this.H = a(getContext());
    }

    private void h() {
        if (this.u.a()) {
            n();
        } else if (this.u.a(getContext(), this.m, this.n, this.D, this.E, this.l)) {
            this.f4032e.setDuration(this.u.f4077e / 1000);
            l.a(1, this);
        }
    }

    private boolean i() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    private void j() {
        ArrayList arrayList;
        if (this.B.isEmpty() || !this.f4032e.isRunning()) {
            return;
        }
        synchronized (this) {
            arrayList = new ArrayList(this.B);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((PAGImageViewListener) it.next()).onAnimationUpdate(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        ArrayList arrayList;
        this.g = false;
        synchronized (this) {
            arrayList = new ArrayList(this.B);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((PAGImageViewListener) it.next()).onAnimationEnd(this);
        }
    }

    private void l() {
        if (this.h == null) {
            this.h = Boolean.valueOf(this.f4032e.isRunning());
        }
        if (this.f4032e.isRunning()) {
            d();
        }
    }

    private void m() {
        if (this.u != null) {
            this.u.c();
        }
        p();
        this.t = null;
        h();
    }

    private void n() {
        int i = this.o;
        if (i == 0) {
            return;
        }
        this.p = l.a(i, this.u.f4073a, this.u.f4074b, this.D, this.E);
    }

    private void o() {
        WeakReference<Future> weakReference = this.M;
        if (weakReference != null && weakReference.get() != null) {
            this.M.get().cancel(false);
        }
        for (WeakReference<Future> weakReference2 : this.q) {
            if (weakReference2.get() != null) {
                weakReference2.get().cancel(false);
            }
        }
        this.q.clear();
    }

    private void p() {
        if (this.s != null) {
            this.v.b(this.t.f4035b);
            this.s.g();
            this.s.e();
            if (this.t.f4036c) {
                try {
                    new File(this.v.a(this.t.f4035b)).delete();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            this.s.h();
            this.s = null;
        }
    }

    private void q() {
        if (this.s == null || this.t == null || TextUtils.isEmpty(this.t.f4034a) || this.u.f4078f == null) {
            return;
        }
        this.s.g();
        if (this.x.size() == this.u.f4076d || this.s.b()) {
            this.u.b();
            this.s.f();
            if (this.x.size() == this.u.f4076d) {
                this.s.e();
            }
        }
        this.s.h();
    }

    private void r() {
        if (this.D == 0 || this.E == 0 || !this.g || this.f4032e.isRunning() || !(this.h == null || this.h.booleanValue())) {
            this.h = null;
        } else {
            this.h = null;
            e();
        }
    }

    private void s() {
        if (this.f4032e.getDuration() <= 0) {
            return;
        }
        if (i()) {
            this.f4032e.start();
        } else {
            removeCallbacks(this.L);
            post(this.K);
        }
    }

    public void addListener(PAGImageViewListener pAGImageViewListener) {
        synchronized (this) {
            this.B.add(pAGImageViewListener);
        }
    }

    public boolean cacheAllFramesInMemory() {
        return this.f4033f;
    }

    public int currentFrame() {
        return this.k;
    }

    public Bitmap currentImage() {
        return this.w;
    }

    public void finalize() throws Throwable {
        super.finalize();
    }

    public boolean flush() {
        if (!this.u.a()) {
            return false;
        }
        synchronized (this.j) {
            if (this.i) {
                this.i = false;
                this.f4032e.setCurrentPlayTime((long) (((double) (this.u.f4077e * 0.001f)) * l.b(this.k, this.u.f4076d)));
            } else {
                int iA = l.a(this.f4032e.getAnimatedFraction(), this.u.f4076d);
                if (iA == this.k && !this.J) {
                    return false;
                }
                this.J = false;
                this.k = iA;
            }
            if (!a(this.k)) {
                return false;
            }
            postInvalidate();
            j();
            return true;
        }
    }

    public PAGComposition getComposition() {
        return this.m;
    }

    public String getPath() {
        return this.n;
    }

    public boolean isPlaying() {
        ValueAnimator valueAnimator = this.f4032e;
        if (valueAnimator != null) {
            return valueAnimator.isRunning();
        }
        return false;
    }

    public Matrix matrix() {
        return this.p;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        this.I = true;
        super.onAttachedToWindow();
        this.J = true;
        this.f4032e.addUpdateListener(this.A);
        this.f4032e.addListener(this.C);
        synchronized (f4028a) {
            l.b();
        }
        if (this.v != null && this.s == null) {
            l.a(1, this);
        }
        r();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        this.I = false;
        super.onDetachedFromWindow();
        l();
        synchronized (f4028a) {
            l.a();
        }
        this.f4032e.removeUpdateListener(this.A);
        this.f4032e.removeListener(this.C);
        o();
        p();
        this.w = null;
        if (this.u != null) {
            this.u.c();
        }
        this.x.clear();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (this.w == null || this.w.isRecycled()) {
            return;
        }
        super.onDraw(canvas);
        canvas.save();
        Matrix matrix = this.y;
        if (matrix != null) {
            canvas.concat(matrix);
        }
        Matrix matrix2 = this.p;
        if (matrix2 != null) {
            canvas.concat(matrix2);
        }
        try {
            canvas.drawBitmap(this.w, 0.0f, 0.0f, (Paint) null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        canvas.restore();
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.F = i;
        this.G = i2;
        float f2 = this.r;
        this.D = (int) (i * f2);
        this.E = (int) (f2 * i2);
        h();
        r();
    }

    @Override // android.view.View
    public void onVisibilityAggregated(boolean z) {
        super.onVisibilityAggregated(z);
        if (this.N == z) {
            return;
        }
        this.N = z;
        Log.i("PAGImageView", "onVisibilityAggregated isVisible=" + z);
        if (z) {
            r();
        } else {
            l();
        }
    }

    public void pause() {
        this.g = false;
        this.h = null;
        d();
    }

    public void play() {
        this.g = true;
        this.h = null;
        if (this.f4032e.getAnimatedFraction() == 1.0d) {
            setCurrentFrame(0);
        }
        e();
    }

    public void removeListener(PAGImageViewListener pAGImageViewListener) {
        synchronized (this) {
            this.B.remove(pAGImageViewListener);
        }
    }

    public float renderScale() {
        return this.r;
    }

    public int scaleMode() {
        return this.o;
    }

    public void setCacheAllFramesInMemory(boolean z) {
        this.f4033f = z;
    }

    public void setComposition(PAGComposition pAGComposition) {
        setComposition(pAGComposition, 30.0f);
    }

    public void setCurrentFrame(int i) {
        if (!this.u.a() || i < 0 || i >= this.u.f4076d || this.s == null) {
            return;
        }
        this.k = i;
        this.z = (long) (Math.max(0.0f, Math.min((float) (((double) (this.u.f4077e * 0.001f)) * l.b(this.k, this.u.f4076d)), 1.0f)) * this.f4032e.getDuration());
        synchronized (this.j) {
            this.f4032e.setCurrentPlayTime(this.z);
            this.i = true;
        }
        l.a(this);
    }

    public void setMatrix(Matrix matrix) {
        this.p = matrix;
        this.o = 0;
        if (f()) {
            postInvalidate();
        }
    }

    public boolean setPath(String str, float f2) {
        if (str == null) {
            return false;
        }
        if (str.equals(this.n) && this.l == f2) {
            return true;
        }
        this.n = str;
        this.l = f2;
        this.i = true;
        this.f4032e.setCurrentPlayTime(0L);
        o();
        this.p = null;
        m();
        this.w = null;
        return true;
    }

    public void setRenderScale(float f2) {
        if (this.r == f2) {
            return;
        }
        if (f2 < 0.0f || f2 > 1.0f) {
            f2 = 1.0f;
        }
        this.r = f2;
        this.D = (int) (this.F * f2);
        this.E = (int) (this.G * f2);
        n();
        if (f2 < 1.0f) {
            Matrix matrix = new Matrix();
            this.y = matrix;
            float f3 = 1.0f / f2;
            matrix.setScale(f3, f3);
        }
    }

    public void setRepeatCount(int i) {
        if (i < 0) {
            i = 0;
        }
        this.f4032e.setRepeatCount(i - 1);
    }

    public void setScaleMode(int i) {
        if (i == this.o) {
            return;
        }
        this.o = i;
        if (!f()) {
            this.p = null;
        } else {
            n();
            postInvalidate();
        }
    }

    private static void c() {
        if (f4031d == null || f4030c == null) {
            synchronized (PAGImageView.class) {
                if (f4031d == null) {
                    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 2L, TimeUnit.SECONDS, new LinkedBlockingQueue());
                    f4031d = threadPoolExecutor;
                    threadPoolExecutor.allowCoreThreadTimeOut(true);
                }
                if (f4030c == null) {
                    ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(1, 1, 2L, TimeUnit.SECONDS, new LinkedBlockingQueue());
                    f4030c = threadPoolExecutor2;
                    threadPoolExecutor2.allowCoreThreadTimeOut(true);
                }
            }
        }
    }

    private void d() {
        if (i()) {
            this.z = this.f4032e.getCurrentPlayTime();
            this.f4032e.cancel();
        } else {
            removeCallbacks(this.K);
            post(this.L);
        }
    }

    private void e() {
        if (!this.I) {
            Log.e("PAGImageView", "doPlay: View is not attached to window");
            return;
        }
        if (this.H == 0.0f) {
            k();
            Log.e("PAGImageView", "doPlay: The scale of animator duration is turned off");
        } else {
            h();
            Log.i("PAGImageView", "doPlay");
            this.f4032e.setCurrentPlayTime(this.z);
            s();
        }
    }

    private boolean f() {
        return this.D > 0 && this.E > 0;
    }

    public boolean b() {
        if (this.I) {
            return flush();
        }
        return true;
    }

    public void setComposition(PAGComposition pAGComposition, float f2) {
        if (pAGComposition == this.m) {
            return;
        }
        this.m = pAGComposition;
        this.i = true;
        this.f4032e.setCurrentPlayTime(0L);
        o();
        this.p = null;
        m();
        this.w = null;
    }

    private float a(Context context) {
        if (Build.VERSION.SDK_INT >= 17) {
            return Settings.Global.getFloat(context.getContentResolver(), "animator_duration_scale", 1.0f);
        }
        return Settings.System.getFloat(context.getContentResolver(), "animator_duration_scale", 1.0f);
    }

    public boolean b(l.a aVar) {
        if (aVar != null) {
            try {
                if (this.s != null) {
                    return this.s.a(aVar.f4071c, aVar.f4072d);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public a a() {
        String str;
        if (!this.u.a()) {
            return null;
        }
        if (this.m == null && this.n == null) {
            return null;
        }
        if (this.n != null && this.t != null) {
            return this.t;
        }
        String str2 = "_" + ((this.u.f4073a << 16) | this.u.f4074b) + "_" + this.u.f4075c;
        boolean z = false;
        if (this.n != null) {
            str = this.n + str2;
        } else {
            PAGComposition pAGComposition = this.m;
            if ((pAGComposition instanceof PAGFile) && CacheManager.ContentVersion(pAGComposition) == 0) {
                str = ((PAGFile) this.m).path() + str2;
            } else {
                str = this.m.toString() + "_" + CacheManager.ContentVersion(this.m) + str2;
                z = true;
            }
        }
        if (this.t != null && str.equals(this.t.f4034a)) {
            return this.t;
        }
        a aVar = new a();
        aVar.f4034a = str;
        aVar.f4035b = l.a(str);
        aVar.f4036c = z;
        return aVar;
    }

    public boolean setPath(String str) {
        return setPath(str, 30.0f);
    }

    private boolean a(int i) {
        if (!this.u.a()) {
            return false;
        }
        a aVarA = a();
        if (this.t != null && this.t.f4034a != null && !this.t.f4034a.equals(aVarA.f4034a)) {
            p();
            this.s = this.v.a(aVarA.f4035b, this.u.f4073a, this.u.f4074b, this.u.f4076d);
            this.x.clear();
        }
        this.t = aVarA;
        q();
        if (this.t != null && this.t.f4035b != null) {
            if (this.x.get(Integer.valueOf(i)) != null) {
                return true;
            }
            if (this.s != null) {
                try {
                    WeakReference<Future> weakReference = this.M;
                    if (weakReference != null && weakReference.get() != null && !this.M.get().isDone()) {
                        return false;
                    }
                    if (this.s.a(i)) {
                        if (this.w == null || this.f4033f) {
                            this.w = l.a(this.u.f4073a, this.u.f4074b);
                        }
                        this.M = new WeakReference<>(f4031d.submit(new j(this, i)));
                        return false;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        if (this.w == null || this.f4033f) {
            this.w = l.a(this.u.f4073a, this.u.f4074b);
        }
        if (this.u.f4078f == null || !this.u.f4078f.copyFrameTo(this.w, i)) {
            return false;
        }
        if (this.f4033f) {
            this.x.put(Integer.valueOf(i), this.w);
        }
        if (this.s != null && this.s.b(i, this.w)) {
            this.q.add(new WeakReference<>(f4030c.submit(new k(this))));
        }
        return true;
    }

    public PAGImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4033f = false;
        this.g = false;
        this.h = null;
        this.i = true;
        this.j = new Object();
        this.l = 30.0f;
        this.o = 2;
        this.q = new ArrayList<>();
        this.r = 1.0f;
        this.u = new l.b();
        this.x = new ConcurrentHashMap<>();
        this.A = new f(this);
        this.B = new ArrayList<>();
        this.C = new g(this);
        this.H = 1.0f;
        this.I = false;
        this.J = false;
        this.K = new h(this);
        this.L = new i(this);
        this.N = true;
        g();
    }

    public PAGImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f4033f = false;
        this.g = false;
        this.h = null;
        this.i = true;
        this.j = new Object();
        this.l = 30.0f;
        this.o = 2;
        this.q = new ArrayList<>();
        this.r = 1.0f;
        this.u = new l.b();
        this.x = new ConcurrentHashMap<>();
        this.A = new f(this);
        this.B = new ArrayList<>();
        this.C = new g(this);
        this.H = 1.0f;
        this.I = false;
        this.J = false;
        this.K = new h(this);
        this.L = new i(this);
        this.N = true;
        g();
    }

    public static void a(l.a aVar) {
        if (aVar.f4069a == null || TextUtils.isEmpty(aVar.f4070b) || !aVar.f4069a.b(aVar)) {
            return;
        }
        PAGImageView pAGImageView = aVar.f4069a;
        if (pAGImageView.f4033f) {
            pAGImageView.x.put(Integer.valueOf(aVar.f4071c), aVar.f4072d);
        }
        aVar.f4069a.postInvalidate();
        aVar.f4069a.j();
    }
}
