package w0;

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
public class g extends RecyclerView.y {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public PointF f5642k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final DisplayMetrics f5643l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f5645n;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final LinearInterpolator f5640i = new LinearInterpolator();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final DecelerateInterpolator f5641j = new DecelerateInterpolator();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f5644m = false;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f5646o = 0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f5647p = 0;

    public g(Context context) {
        this.f5643l = context.getResources().getDisplayMetrics();
    }

    public final float A() {
        if (!this.f5644m) {
            this.f5645n = v(this.f5643l);
            this.f5644m = true;
        }
        return this.f5645n;
    }

    public int B() {
        PointF pointF = this.f5642k;
        if (pointF != null) {
            float f3 = pointF.y;
            if (f3 != 0.0f) {
                return f3 > 0.0f ? 1 : -1;
            }
        }
        return 0;
    }

    public void C(RecyclerView.y.a aVar) {
        PointF pointFA = a(f());
        if (pointFA == null || (pointFA.x == 0.0f && pointFA.y == 0.0f)) {
            aVar.b(f());
            r();
            return;
        }
        i(pointFA);
        this.f5642k = pointFA;
        this.f5646o = (int) (pointFA.x * 10000.0f);
        this.f5647p = (int) (pointFA.y * 10000.0f);
        aVar.d((int) (this.f5646o * 1.2f), (int) (this.f5647p * 1.2f), (int) (x(10000) * 1.2f), this.f5640i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.y
    public void l(int i3, int i4, RecyclerView.z zVar, RecyclerView.y.a aVar) {
        if (c() == 0) {
            r();
            return;
        }
        this.f5646o = y(this.f5646o, i3);
        int iY = y(this.f5647p, i4);
        this.f5647p = iY;
        if (this.f5646o == 0 && iY == 0) {
            C(aVar);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.y
    public void m() {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.y
    public void n() {
        this.f5647p = 0;
        this.f5646o = 0;
        this.f5642k = null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.y
    public void o(View view, RecyclerView.z zVar, RecyclerView.y.a aVar) {
        int iT = t(view, z());
        int iU = u(view, B());
        int iW = w((int) Math.sqrt((iT * iT) + (iU * iU)));
        if (iW > 0) {
            aVar.d(-iT, -iU, iW, this.f5641j);
        }
    }

    public int s(int i3, int i4, int i5, int i6, int i7) {
        if (i7 == -1) {
            return i5 - i3;
        }
        if (i7 != 0) {
            if (i7 == 1) {
                return i6 - i4;
            }
            throw new IllegalArgumentException("snap preference should be one of the constants defined in SmoothScroller, starting with SNAP_");
        }
        int i8 = i5 - i3;
        if (i8 > 0) {
            return i8;
        }
        int i9 = i6 - i4;
        if (i9 < 0) {
            return i9;
        }
        return 0;
    }

    public int t(View view, int i3) {
        RecyclerView.o oVarE = e();
        if (oVarE == null || !oVarE.k()) {
            return 0;
        }
        RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
        return s(oVarE.Q(view) - ((ViewGroup.MarginLayoutParams) pVar).leftMargin, oVarE.T(view) + ((ViewGroup.MarginLayoutParams) pVar).rightMargin, oVarE.e0(), oVarE.o0() - oVarE.f0(), i3);
    }

    public int u(View view, int i3) {
        RecyclerView.o oVarE = e();
        if (oVarE == null || !oVarE.l()) {
            return 0;
        }
        RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
        return s(oVarE.U(view) - ((ViewGroup.MarginLayoutParams) pVar).topMargin, oVarE.O(view) + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin, oVarE.g0(), oVarE.W() - oVarE.d0(), i3);
    }

    public float v(DisplayMetrics displayMetrics) {
        return 25.0f / displayMetrics.densityDpi;
    }

    public int w(int i3) {
        return (int) Math.ceil(((double) x(i3)) / 0.3356d);
    }

    public int x(int i3) {
        return (int) Math.ceil(Math.abs(i3) * A());
    }

    public final int y(int i3, int i4) {
        int i5 = i3 - i4;
        if (i3 * i5 <= 0) {
            return 0;
        }
        return i5;
    }

    public int z() {
        PointF pointF = this.f5642k;
        if (pointF != null) {
            float f3 = pointF.x;
            if (f3 != 0.0f) {
                return f3 > 0.0f ? 1 : -1;
            }
        }
        return 0;
    }
}
