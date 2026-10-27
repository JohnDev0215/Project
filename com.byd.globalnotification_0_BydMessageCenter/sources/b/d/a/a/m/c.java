package b.d.a.a.m;

import a.h.k.x.d;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;

/* JADX INFO: loaded from: classes.dex */
public class c implements a.h.k.x.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ CoordinatorLayout f1993a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ AppBarLayout f1994b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ View f1995c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1996d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ AppBarLayout.BaseBehavior f1997e;

    public c(AppBarLayout.BaseBehavior baseBehavior, CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i) {
        this.f1997e = baseBehavior;
        this.f1993a = coordinatorLayout;
        this.f1994b = appBarLayout;
        this.f1995c = view;
        this.f1996d = i;
    }

    @Override // a.h.k.x.d
    public boolean a(View view, d.a aVar) {
        this.f1997e.L(this.f1993a, this.f1994b, this.f1995c, this.f1996d, new int[]{0, 0});
        return true;
    }
}
