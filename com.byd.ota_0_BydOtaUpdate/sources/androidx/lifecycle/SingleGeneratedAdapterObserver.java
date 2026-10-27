package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import b.l.c;
import b.l.d;
import b.l.f;

/* JADX INFO: loaded from: classes.dex */
public class SingleGeneratedAdapterObserver implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f546a;

    public SingleGeneratedAdapterObserver(c cVar) {
        this.f546a = cVar;
    }

    @Override // b.l.d
    public void d(f fVar, Lifecycle.Event event) {
        this.f546a.a(fVar, event, false, null);
        this.f546a.a(fVar, event, true, null);
    }
}
