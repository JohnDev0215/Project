package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import t0.c;
import t0.d;
import t0.f;

/* JADX INFO: loaded from: classes.dex */
public class SingleGeneratedAdapterObserver implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f1372a;

    public SingleGeneratedAdapterObserver(c cVar) {
        this.f1372a = cVar;
    }

    @Override // t0.d
    public void d(f fVar, Lifecycle.Event event) {
        this.f1372a.a(fVar, event, false, null);
        this.f1372a.a(fVar, event, true, null);
    }
}
