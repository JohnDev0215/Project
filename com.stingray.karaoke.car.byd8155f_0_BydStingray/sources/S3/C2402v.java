package s3;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: renamed from: s3.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2402v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f22351a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public RecyclerView f22352b;
    public AbstractC2367F c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f22353e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public View f22354f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C2375N f22355g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f22356h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final LinearInterpolator f22357i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final DecelerateInterpolator f22358j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final DisplayMetrics f22359k;
    public boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f22360m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f22361n;
    public int o;

    public C2402v(AbstractC2403w abstractC2403w, Context context) {
        C2375N c2375n = new C2375N();
        c2375n.d = -1;
        c2375n.f22200f = false;
        c2375n.f22201g = 0;
        c2375n.f22197a = 0;
        c2375n.f22198b = 0;
        c2375n.c = Integer.MIN_VALUE;
        c2375n.f22199e = null;
        this.f22355g = c2375n;
        this.f22357i = new LinearInterpolator();
        this.f22358j = new DecelerateInterpolator();
        this.l = false;
        this.f22361n = 0;
        this.o = 0;
        this.f22359k = context.getResources().getDisplayMetrics();
    }

    public final PointF a(int i7) {
        Object obj = this.c;
        if (obj instanceof InterfaceC2376O) {
            return ((InterfaceC2376O) obj).a(i7);
        }
        h6.n.t("RecyclerView", "You should override computeScrollVectorForPosition when the LayoutManager does not implement " + InterfaceC2376O.class.getCanonicalName());
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x010e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(int r9, int r10) {
        /*
            Method dump skipped, instruction units count: 299
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: s3.C2402v.b(int, int):void");
    }

    public final void c() {
        if (this.f22353e) {
            this.f22353e = false;
            this.o = 0;
            this.f22361n = 0;
            this.f22352b.f12140o0.f22202a = -1;
            this.f22354f = null;
            this.f22351a = -1;
            this.d = false;
            AbstractC2367F abstractC2367F = this.c;
            if (abstractC2367F.f22181e == this) {
                abstractC2367F.f22181e = null;
            }
            this.c = null;
            this.f22352b = null;
        }
    }
}
