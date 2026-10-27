package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: LayoutState.java */
/* JADX INFO: loaded from: classes.dex */
class f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f1274b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1275c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1276d;
    int e;
    boolean h;
    boolean i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    boolean f1273a = true;
    int f = 0;
    int g = 0;

    f() {
    }

    boolean a(RecyclerView.z zVar) {
        int i = this.f1275c;
        return i >= 0 && i < zVar.b();
    }

    View b(RecyclerView.v vVar) {
        View viewO = vVar.o(this.f1275c);
        this.f1275c += this.f1276d;
        return viewO;
    }

    public String toString() {
        return "LayoutState{mAvailable=" + this.f1274b + ", mCurrentPosition=" + this.f1275c + ", mItemDirection=" + this.f1276d + ", mLayoutDirection=" + this.e + ", mStartLine=" + this.f + ", mEndLine=" + this.g + '}';
    }
}
