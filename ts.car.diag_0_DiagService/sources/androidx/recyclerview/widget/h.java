package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
class h {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f1215b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1216c;
    int d;
    int e;
    boolean h;
    boolean i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    boolean f1214a = true;
    int f = 0;
    int g = 0;

    h() {
    }

    boolean a(RecyclerView.x xVar) {
        int i = this.f1216c;
        return i >= 0 && i < xVar.b();
    }

    View b(RecyclerView.t tVar) {
        View viewO = tVar.o(this.f1216c);
        this.f1216c += this.d;
        return viewO;
    }

    public String toString() {
        return "LayoutState{mAvailable=" + this.f1215b + ", mCurrentPosition=" + this.f1216c + ", mItemDirection=" + this.d + ", mLayoutDirection=" + this.e + ", mStartLine=" + this.f + ", mEndLine=" + this.g + '}';
    }
}
