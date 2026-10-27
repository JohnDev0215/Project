package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import b.m.c;
import b.m.d;
import b.m.f;
import b.m.j;

/* JADX INFO: loaded from: classes.dex */
public class CompositeGeneratedAdaptersObserver implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c[] f397a;

    public CompositeGeneratedAdaptersObserver(c[] cVarArr) {
        this.f397a = cVarArr;
    }

    @Override // b.m.d
    public void d(f fVar, Lifecycle.Event event) {
        j jVar = new j();
        for (c cVar : this.f397a) {
            cVar.a(fVar, event, false, jVar);
        }
        for (c cVar2 : this.f397a) {
            cVar2.a(fVar, event, true, jVar);
        }
    }
}
