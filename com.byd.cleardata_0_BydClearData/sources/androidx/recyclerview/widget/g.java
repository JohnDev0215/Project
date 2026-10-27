package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
class g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f1592b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1593c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1594d;
    int e;
    boolean h;
    boolean i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    boolean f1591a = true;
    int f = 0;
    int g = 0;

    g() {
    }

    boolean a(RecyclerView.a0 a0Var) {
        int i = this.f1593c;
        return i >= 0 && i < a0Var.b();
    }

    View b(RecyclerView.v vVar) {
        View viewO = vVar.o(this.f1593c);
        this.f1593c += this.f1594d;
        return viewO;
    }

    public String toString() {
        return "LayoutState{mAvailable=" + this.f1592b + ", mCurrentPosition=" + this.f1593c + ", mItemDirection=" + this.f1594d + ", mLayoutDirection=" + this.e + ", mStartLine=" + this.f + ", mEndLine=" + this.g + '}';
    }
}
