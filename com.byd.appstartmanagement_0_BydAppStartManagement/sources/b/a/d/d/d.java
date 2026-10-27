package b.a.d.d;

import android.view.View;
import b.a.d.d.e;

/* JADX INFO: compiled from: XPager.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class d<P extends e> implements f<P>, View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public P f328b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile View f329c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile boolean f330d;

    @Override // b.a.d.d.f
    public void a(P p) {
        this.f328b = p;
    }

    @Override // b.a.d.d.f
    public P b() {
        return this.f328b;
    }

    public View g() {
        return this.f329c;
    }

    public void h() {
        b.a.d.b.a.a("XPager", "----onAttach---" + d.class.getName());
    }

    public void i() {
        b.a.d.b.a.a("XPager", "----onDetach---" + d.class.getName());
        this.f330d = true;
    }
}
