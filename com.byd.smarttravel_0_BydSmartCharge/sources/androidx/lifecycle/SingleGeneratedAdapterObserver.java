package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import b.m.c;
import b.m.d;
import b.m.f;

/* JADX INFO: loaded from: classes.dex */
public class SingleGeneratedAdapterObserver implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f416a;

    public SingleGeneratedAdapterObserver(c cVar) {
        this.f416a = cVar;
    }

    @Override // b.m.d
    public void d(f fVar, Lifecycle.Event event) {
        this.f416a.a(fVar, event, false, null);
        this.f416a.a(fVar, event, true, null);
    }
}
