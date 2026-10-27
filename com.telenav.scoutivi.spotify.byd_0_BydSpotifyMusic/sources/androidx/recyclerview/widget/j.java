package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: LinearSmoothScroller.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends RecyclerView.y {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    protected PointF f3157k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final DisplayMetrics f3158l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private float f3160n;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    protected final LinearInterpolator f3155i = new LinearInterpolator();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    protected final DecelerateInterpolator f3156j = new DecelerateInterpolator();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f3159m = false;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    protected int f3161o = 0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    protected int f3162p = 0;

    public j(Context context) {
        this.f3158l = context.getResources().getDisplayMetrics();
    }

    private float A() {
        if (!this.f3159m) {
            this.f3160n = v(this.f3158l);
            this.f3159m = true;
        }
        return this.f3160n;
    }

    private int y(int i7, int i8) {
        int i9 = i7 - i8;
        if (i7 * i9 <= 0) {
            return 0;
        }
        return i9;
    }

    protected int B() {
        PointF pointF = this.f3157k;
        if (pointF != null) {
            float f7 = pointF.y;
            if (f7 != 0.0f) {
                return f7 > 0.0f ? 1 : -1;
            }
        }
        return 0;
    }

    protected void C(RecyclerView.y.a aVar) {
        PointF pointFA = a(f());
        if (pointFA == null || (pointFA.x == 0.0f && pointFA.y == 0.0f)) {
            aVar.b(f());
            r();
            return;
        }
        i(pointFA);
        this.f3157k = pointFA;
        this.f3161o = (int) (pointFA.x * 10000.0f);
        this.f3162p = (int) (pointFA.y * 10000.0f);
        aVar.d((int) (this.f3161o * 1.2f), (int) (this.f3162p * 1.2f), (int) (x(10000) * 1.2f), this.f3155i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.y
    protected void l(int i7, int i8, RecyclerView.z zVar, RecyclerView.y.a aVar) {
        if (c() == 0) {
            r();
            return;
        }
        this.f3161o = y(this.f3161o, i7);
        int iY = y(this.f3162p, i8);
        this.f3162p = iY;
        if (this.f3161o == 0 && iY == 0) {
            C(aVar);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.y
    protected void m() {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.y
    protected void n() {
        this.f3162p = 0;
        this.f3161o = 0;
        this.f3157k = null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.y
    protected void o(View view, RecyclerView.z zVar, RecyclerView.y.a aVar) {
        int iT = t(view, z());
        int iU = u(view, B());
        int iW = w((int) Math.sqrt((iT * iT) + (iU * iU)));
        if (iW > 0) {
            aVar.d(-iT, -iU, iW, this.f3156j);
        }
    }

    public int s(int i7, int i8, int i9, int i10, int i11) {
        if (i11 == -1) {
            return i9 - i7;
        }
        if (i11 != 0) {
            if (i11 == 1) {
                return i10 - i8;
            }
            throw new IllegalArgumentException("snap preference should be one of the constants defined in SmoothScroller, starting with SNAP_");
        }
        int i12 = i9 - i7;
        if (i12 > 0) {
            return i12;
        }
        int i13 = i10 - i8;
        if (i13 < 0) {
            return i13;
        }
        return 0;
    }

    public int t(View view, int i7) {
        RecyclerView.o oVarE = e();
        if (oVarE == null || !oVarE.k()) {
            return 0;
        }
        RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
        return s(oVarE.Q(view) - ((ViewGroup.MarginLayoutParams) pVar).leftMargin, oVarE.T(view) + ((ViewGroup.MarginLayoutParams) pVar).rightMargin, oVarE.e0(), oVarE.o0() - oVarE.f0(), i7);
    }

    public int u(View view, int i7) {
        RecyclerView.o oVarE = e();
        if (oVarE == null || !oVarE.l()) {
            return 0;
        }
        RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
        return s(oVarE.U(view) - ((ViewGroup.MarginLayoutParams) pVar).topMargin, oVarE.O(view) + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin, oVarE.g0(), oVarE.W() - oVarE.d0(), i7);
    }

    protected float v(DisplayMetrics displayMetrics) {
        return 25.0f / displayMetrics.densityDpi;
    }

    protected int w(int i7) {
        return (int) Math.ceil(((double) x(i7)) / 0.3356d);
    }

    protected int x(int i7) {
        return (int) Math.ceil(Math.abs(i7) * A());
    }

    protected int z() {
        PointF pointF = this.f3157k;
        if (pointF != null) {
            float f7 = pointF.x;
            if (f7 != 0.0f) {
                return f7 > 0.0f ? 1 : -1;
            }
        }
        return 0;
    }
}
