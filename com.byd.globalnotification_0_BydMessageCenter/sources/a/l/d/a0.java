package a.l.d;

import a.l.d.g0;
import a.l.d.r;
import androidx.fragment.app.Fragment;

/* JADX INFO: loaded from: classes.dex */
public final class a0 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ g0.a f931a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Fragment f932b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ a.h.g.a f933c;

    public a0(g0.a aVar, Fragment fragment, a.h.g.a aVar2) {
        this.f931a = aVar;
        this.f932b = fragment;
        this.f933c = aVar2;
    }

    @Override // java.lang.Runnable
    public void run() {
        ((r.b) this.f931a).a(this.f932b, this.f933c);
    }
}
