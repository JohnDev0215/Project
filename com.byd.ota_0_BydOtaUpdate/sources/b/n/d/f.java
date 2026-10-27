package b.n.d;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: LayoutState.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2092b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2093c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2094d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2095e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f2098h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f2099i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f2091a = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2096f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2097g = 0;

    public boolean a(RecyclerView.z zVar) {
        int i2 = this.f2093c;
        return i2 >= 0 && i2 < zVar.b();
    }

    public View b(RecyclerView.v vVar) {
        View viewO = vVar.o(this.f2093c);
        this.f2093c += this.f2094d;
        return viewO;
    }

    public String toString() {
        return "LayoutState{mAvailable=" + this.f2092b + ", mCurrentPosition=" + this.f2093c + ", mItemDirection=" + this.f2094d + ", mLayoutDirection=" + this.f2095e + ", mStartLine=" + this.f2096f + ", mEndLine=" + this.f2097g + '}';
    }
}
