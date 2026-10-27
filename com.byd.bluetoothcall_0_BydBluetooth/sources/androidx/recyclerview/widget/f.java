package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: LayoutState.java */
/* JADX INFO: loaded from: classes.dex */
class f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f1795b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1796c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1797d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f1798e;
    boolean h;
    boolean i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    boolean f1794a = true;
    int f = 0;
    int g = 0;

    f() {
    }

    boolean a(RecyclerView.z zVar) {
        int i = this.f1796c;
        return i >= 0 && i < zVar.b();
    }

    View b(RecyclerView.v vVar) {
        View viewO = vVar.o(this.f1796c);
        this.f1796c += this.f1797d;
        return viewO;
    }

    public String toString() {
        return "LayoutState{mAvailable=" + this.f1795b + ", mCurrentPosition=" + this.f1796c + ", mItemDirection=" + this.f1797d + ", mLayoutDirection=" + this.f1798e + ", mStartLine=" + this.f + ", mEndLine=" + this.g + '}';
    }
}
