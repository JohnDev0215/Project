package f.a.a;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final List<j> f2562d = new ArrayList();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f2563a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public q f2564b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public j f2565c;

    public j(Object obj, q qVar) {
        this.f2563a = obj;
        this.f2564b = qVar;
    }

    public static j a(q qVar, Object obj) {
        synchronized (f2562d) {
            int size = f2562d.size();
            if (size <= 0) {
                return new j(obj, qVar);
            }
            j jVarRemove = f2562d.remove(size - 1);
            jVarRemove.f2563a = obj;
            jVarRemove.f2564b = qVar;
            jVarRemove.f2565c = null;
            return jVarRemove;
        }
    }
}
