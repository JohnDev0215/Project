package b.r.d;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: LayoutState.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3103b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3104c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3105d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3106e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f3109h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f3110i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f3102a = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3107f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f3108g = 0;

    public boolean a(RecyclerView.z zVar) {
        int i2 = this.f3104c;
        return i2 >= 0 && i2 < zVar.b();
    }

    public View b(RecyclerView.v vVar) {
        View viewO = vVar.o(this.f3104c);
        this.f3104c += this.f3105d;
        return viewO;
    }

    public String toString() {
        return "LayoutState{mAvailable=" + this.f3103b + ", mCurrentPosition=" + this.f3104c + ", mItemDirection=" + this.f3105d + ", mLayoutDirection=" + this.f3106e + ", mStartLine=" + this.f3107f + ", mEndLine=" + this.f3108g + '}';
    }
}
