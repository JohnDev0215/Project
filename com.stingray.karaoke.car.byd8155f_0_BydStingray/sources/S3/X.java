package s3;

import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class X {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f22236a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f22237b;
    public boolean c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f22238e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int[] f22239f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ StaggeredGridLayoutManager f22240g;

    public X(StaggeredGridLayoutManager staggeredGridLayoutManager) {
        this.f22240g = staggeredGridLayoutManager;
        a();
    }

    public final void a() {
        this.f22236a = -1;
        this.f22237b = Integer.MIN_VALUE;
        this.c = false;
        this.d = false;
        this.f22238e = false;
        int[] iArr = this.f22239f;
        if (iArr != null) {
            Arrays.fill(iArr, -1);
        }
    }
}
