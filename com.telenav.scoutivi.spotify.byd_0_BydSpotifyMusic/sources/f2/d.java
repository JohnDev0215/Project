package f2;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import k2.i;

/* JADX INFO: compiled from: ModelToResourceClassCache.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AtomicReference<i> f6343a = new AtomicReference<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final n.a<i, List<Class<?>>> f6344b = new n.a<>();

    public List<Class<?>> a(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        List<Class<?>> list;
        i andSet = this.f6343a.getAndSet(null);
        if (andSet == null) {
            andSet = new i(cls, cls2, cls3);
        } else {
            andSet.a(cls, cls2, cls3);
        }
        synchronized (this.f6344b) {
            list = this.f6344b.get(andSet);
        }
        this.f6343a.set(andSet);
        return list;
    }

    public void b(Class<?> cls, Class<?> cls2, Class<?> cls3, List<Class<?>> list) {
        synchronized (this.f6344b) {
            this.f6344b.put(new i(cls, cls2, cls3), list);
        }
    }
}
