package s3;

import android.view.animation.BaseInterpolator;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: renamed from: s3.N, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2375N {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f22197a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f22198b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public BaseInterpolator f22199e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f22200f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f22201g;

    public final void a(RecyclerView recyclerView) {
        int i7 = this.d;
        if (i7 >= 0) {
            this.d = -1;
            recyclerView.K(i7);
            this.f22200f = false;
            return;
        }
        if (!this.f22200f) {
            this.f22201g = 0;
            return;
        }
        BaseInterpolator baseInterpolator = this.f22199e;
        if (baseInterpolator != null && this.c < 1) {
            throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
        }
        int i10 = this.c;
        if (i10 < 1) {
            throw new IllegalStateException("Scroll duration must be a positive number");
        }
        recyclerView.f12135l0.c(this.f22197a, this.f22198b, i10, baseInterpolator);
        int i11 = this.f22201g + 1;
        this.f22201g = i11;
        if (i11 > 10) {
            h6.n.j("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
        }
        this.f22200f = false;
    }
}
