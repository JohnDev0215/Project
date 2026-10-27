package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.ViewGroup;
import defpackage.AbstractC1747x5;
import defpackage.C0474a6;
import defpackage.C0586c6;
import defpackage.C1637v5;
import defpackage.C1692w5;
import defpackage.W5;
import defpackage.X5;
import defpackage.Z7;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class GridLayoutManager extends LinearLayoutManager {
    public boolean D;
    public int E;
    public final SparseIntArray F;
    public final SparseIntArray G;
    public AbstractC1747x5 H;
    public final Rect I;

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.D = false;
        this.E = -1;
        this.F = new SparseIntArray();
        this.G = new SparseIntArray();
        this.H = new C1637v5();
        this.I = new Rect();
        int i3 = W5.y(context, attributeSet, i, i2).b;
        if (i3 == this.E) {
            return;
        }
        this.D = true;
        if (i3 < 1) {
            throw new IllegalArgumentException(Z7.a("Span count should be at least 1. Provided ", i3));
        }
        this.E = i3;
        this.H.a.clear();
        a0();
    }

    @Override // defpackage.W5
    public void K(RecyclerView recyclerView, int i, int i2) {
        this.H.a.clear();
    }

    @Override // defpackage.W5
    public void L(RecyclerView recyclerView, int i, int i2, int i3) {
        this.H.a.clear();
    }

    @Override // defpackage.W5
    public void M(RecyclerView recyclerView, int i, int i2) {
        this.H.a.clear();
    }

    @Override // defpackage.W5
    public void O(RecyclerView recyclerView, int i, int i2, Object obj) {
        this.H.a.clear();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, defpackage.W5
    public boolean c0() {
        return this.z == null && !this.D;
    }

    @Override // defpackage.W5
    public boolean d(X5 x5) {
        return x5 instanceof C1692w5;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, defpackage.W5
    public X5 l() {
        return this.p == 0 ? new C1692w5(-2, -1) : new C1692w5(-1, -2);
    }

    @Override // defpackage.W5
    public X5 m(Context context, AttributeSet attributeSet) {
        return new C1692w5(context, attributeSet);
    }

    @Override // defpackage.W5
    public X5 n(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new C1692w5((ViewGroup.MarginLayoutParams) layoutParams) : new C1692w5(layoutParams);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void n0(boolean z) {
        if (z) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        a(null);
        if (this.v) {
            this.v = false;
            a0();
        }
    }

    public final int o0(C0474a6 c0474a6, C0586c6 c0586c6, int i) {
        if (!c0586c6.g) {
            return this.H.a(i, this.E);
        }
        int iB = c0474a6.b(i);
        if (iB != -1) {
            return this.H.a(iB, this.E);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + i);
        return 0;
    }

    @Override // defpackage.W5
    public int r(C0474a6 c0474a6, C0586c6 c0586c6) {
        if (this.p == 1) {
            return this.E;
        }
        if (c0586c6.a() < 1) {
            return 0;
        }
        return o0(c0474a6, c0586c6, c0586c6.a() - 1) + 1;
    }

    @Override // defpackage.W5
    public int z(C0474a6 c0474a6, C0586c6 c0586c6) {
        if (this.p == 0) {
            return this.E;
        }
        if (c0586c6.a() < 1) {
            return 0;
        }
        return o0(c0474a6, c0586c6, c0586c6.a() - 1) + 1;
    }
}
