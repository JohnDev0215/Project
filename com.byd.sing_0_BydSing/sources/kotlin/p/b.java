package kotlin.p;

import kotlin.jvm.internal.h;
import kotlin.reflect.i;

/* JADX INFO: compiled from: ObservableProperty.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class b<V> implements c<Object, V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private V f4086a;

    public b(V v) {
        this.f4086a = v;
    }

    @Override // kotlin.p.c
    public void a(Object obj, i<?> iVar, V v) {
        h.d(iVar, "property");
        V v2 = this.f4086a;
        if (d(iVar, v2, v)) {
            this.f4086a = v;
            c(iVar, v2, v);
        }
    }

    @Override // kotlin.p.c
    public V b(Object obj, i<?> iVar) {
        h.d(iVar, "property");
        return this.f4086a;
    }

    protected abstract void c(i<?> iVar, V v, V v2);

    protected boolean d(i<?> iVar, V v, V v2) {
        h.d(iVar, "property");
        return true;
    }
}
