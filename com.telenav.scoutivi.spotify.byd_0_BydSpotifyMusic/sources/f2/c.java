package f2;

import c2.g;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;
import q1.i;
import q1.t;

/* JADX INFO: compiled from: LoadPathCache.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final t<?, ?, ?> f6340c = new t<>(Object.class, Object.class, Object.class, Collections.singletonList(new i(Object.class, Object.class, Object.class, Collections.emptyList(), new g(), null)), null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final n.a<k2.i, t<?, ?, ?>> f6341a = new n.a<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final AtomicReference<k2.i> f6342b = new AtomicReference<>();

    private k2.i b(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        k2.i andSet = this.f6342b.getAndSet(null);
        if (andSet == null) {
            andSet = new k2.i();
        }
        andSet.a(cls, cls2, cls3);
        return andSet;
    }

    public <Data, TResource, Transcode> t<Data, TResource, Transcode> a(Class<Data> cls, Class<TResource> cls2, Class<Transcode> cls3) {
        t<Data, TResource, Transcode> tVar;
        k2.i iVarB = b(cls, cls2, cls3);
        synchronized (this.f6341a) {
            tVar = (t) this.f6341a.get(iVarB);
        }
        this.f6342b.set(iVarB);
        return tVar;
    }

    public boolean c(t<?, ?, ?> tVar) {
        return f6340c.equals(tVar);
    }

    public void d(Class<?> cls, Class<?> cls2, Class<?> cls3, t<?, ?, ?> tVar) {
        synchronized (this.f6341a) {
            n.a<k2.i, t<?, ?, ?>> aVar = this.f6341a;
            k2.i iVar = new k2.i(cls, cls2, cls3);
            if (tVar == null) {
                tVar = f6340c;
            }
            aVar.put(iVar, tVar);
        }
    }
}
