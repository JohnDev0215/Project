package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: LayoutState.java */
/* JADX INFO: loaded from: classes.dex */
class g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f1460b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1461c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1462d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f1463e;
    boolean h;
    boolean i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    boolean f1459a = true;
    int f = 0;
    int g = 0;

    g() {
    }

    boolean a(RecyclerView.z zVar) {
        int i = this.f1461c;
        return i >= 0 && i < zVar.b();
    }

    View b(RecyclerView.v vVar) {
        View viewO = vVar.o(this.f1461c);
        this.f1461c += this.f1462d;
        return viewO;
    }

    public String toString() {
        return "LayoutState{mAvailable=" + this.f1460b + ", mCurrentPosition=" + this.f1461c + ", mItemDirection=" + this.f1462d + ", mLayoutDirection=" + this.f1463e + ", mStartLine=" + this.f + ", mEndLine=" + this.g + '}';
    }
}
