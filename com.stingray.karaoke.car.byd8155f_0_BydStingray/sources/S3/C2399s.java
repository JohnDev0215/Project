package s3;

import android.view.View;
import java.util.List;
import k3.C1648i0;

/* JADX INFO: renamed from: s3.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2399s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f22340a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f22341b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f22342e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f22343f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f22344g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f22345h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f22346i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f22347j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public List f22348k;
    public boolean l;

    public final void a(View view) {
        int iB;
        int size = this.f22348k.size();
        View view2 = null;
        int i7 = Integer.MAX_VALUE;
        for (int i10 = 0; i10 < size; i10++) {
            View view3 = ((U) this.f22348k.get(i10)).f22219a;
            C2368G c2368g = (C2368G) view3.getLayoutParams();
            if (view3 != view && !c2368g.f22190a.i() && (iB = (c2368g.f22190a.b() - this.d) * this.f22342e) >= 0 && iB < i7) {
                view2 = view3;
                if (iB == 0) {
                    break;
                } else {
                    i7 = iB;
                }
            }
        }
        if (view2 == null) {
            this.d = -1;
        } else {
            this.d = ((C2368G) view2.getLayoutParams()).f22190a.b();
        }
    }

    public final View b(C1648i0 c1648i0) {
        List list = this.f22348k;
        if (list == null) {
            View view = c1648i0.k(Long.MAX_VALUE, this.d).f22219a;
            this.d += this.f22342e;
            return view;
        }
        int size = list.size();
        for (int i7 = 0; i7 < size; i7++) {
            View view2 = ((U) this.f22348k.get(i7)).f22219a;
            C2368G c2368g = (C2368G) view2.getLayoutParams();
            if (!c2368g.f22190a.i() && this.d == c2368g.f22190a.b()) {
                a(view2);
                return view2;
            }
        }
        return null;
    }
}
