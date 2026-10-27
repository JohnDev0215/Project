package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import b.n.d;
import b.n.e;
import b.n.g;

/* JADX INFO: loaded from: classes.dex */
public class SingleGeneratedAdapterObserver implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d f1043a;

    public SingleGeneratedAdapterObserver(d dVar) {
        this.f1043a = dVar;
    }

    @Override // b.n.e
    public void d(g gVar, Lifecycle.Event event) {
        this.f1043a.a(gVar, event, false, null);
        this.f1043a.a(gVar, event, true, null);
    }
}
