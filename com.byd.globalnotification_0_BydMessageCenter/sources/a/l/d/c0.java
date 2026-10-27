package a.l.d;

import a.l.d.g0;
import a.l.d.r;
import androidx.fragment.app.Fragment;

/* JADX INFO: loaded from: classes.dex */
public final class c0 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ g0.a f941a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Fragment f942b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ a.h.g.a f943c;

    public c0(g0.a aVar, Fragment fragment, a.h.g.a aVar2) {
        this.f941a = aVar;
        this.f942b = fragment;
        this.f943c = aVar2;
    }

    @Override // java.lang.Runnable
    public void run() {
        ((r.b) this.f941a).a(this.f942b, this.f943c);
    }
}
