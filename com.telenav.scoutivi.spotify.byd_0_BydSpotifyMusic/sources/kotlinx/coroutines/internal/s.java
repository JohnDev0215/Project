package kotlinx.coroutines.internal;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import kotlinx.coroutines.v1;

/* JADX INFO: compiled from: MainDispatchers.kt */
/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final boolean f7678a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final v1 f7679b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final s f7680c;

    static {
        s sVar = new s();
        f7680c = sVar;
        f7678a = d0.e("kotlinx.coroutines.fast.service.loader", true);
        f7679b = sVar.a();
    }

    private s() {
    }

    private final v1 a() {
        Object next;
        v1 v1VarD;
        try {
            List<MainDispatcherFactory> listC = f7678a ? j.f7649a.c() : x5.l.l(x5.j.c(ServiceLoader.load(MainDispatcherFactory.class, MainDispatcherFactory.class.getClassLoader()).iterator()));
            Iterator<T> it = listC.iterator();
            if (it.hasNext()) {
                next = it.next();
                if (it.hasNext()) {
                    int loadPriority = ((MainDispatcherFactory) next).getLoadPriority();
                    do {
                        Object next2 = it.next();
                        int loadPriority2 = ((MainDispatcherFactory) next2).getLoadPriority();
                        if (loadPriority < loadPriority2) {
                            next = next2;
                            loadPriority = loadPriority2;
                        }
                    } while (it.hasNext());
                }
            } else {
                next = null;
            }
            MainDispatcherFactory mainDispatcherFactory = (MainDispatcherFactory) next;
            return (mainDispatcherFactory == null || (v1VarD = t.d(mainDispatcherFactory, listC)) == null) ? t.b(null, null, 3, null) : v1VarD;
        } catch (Throwable th) {
            return t.b(th, null, 2, null);
        }
    }
}
