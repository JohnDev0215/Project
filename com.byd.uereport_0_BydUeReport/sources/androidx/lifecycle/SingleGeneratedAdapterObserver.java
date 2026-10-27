package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import x0.c;
import x0.d;
import x0.f;

/* JADX INFO: loaded from: classes.dex */
public class SingleGeneratedAdapterObserver implements d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c f1462d;

    public SingleGeneratedAdapterObserver(c cVar) {
        this.f1462d = cVar;
    }

    @Override // x0.d
    public void onStateChanged(f fVar, Lifecycle.Event event) {
        this.f1462d.a(fVar, event, false, null);
        this.f1462d.a(fVar, event, true, null);
    }
}
