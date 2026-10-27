package b.d.a.a.m;

import a.h.k.x.d;
import android.view.View;
import com.google.android.material.appbar.AppBarLayout;

/* JADX INFO: loaded from: classes.dex */
public class d implements a.h.k.x.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ AppBarLayout f1998a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ boolean f1999b;

    public d(AppBarLayout.BaseBehavior baseBehavior, AppBarLayout appBarLayout, boolean z) {
        this.f1998a = appBarLayout;
        this.f1999b = z;
    }

    @Override // a.h.k.x.d
    public boolean a(View view, d.a aVar) {
        this.f1998a.setExpanded(this.f1999b);
        return true;
    }
}
