package b.b.l.u;

import android.util.Pair;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;

/* JADX INFO: compiled from: RpcGc.java */
/* JADX INFO: loaded from: classes2.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ReferenceQueue<Object> f2063a = new ReferenceQueue<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ArrayList<a<Object>> f2064b = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ArrayList<Pair<Integer, ArrayList<Long>>> f2065c = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f2066d;

    /* JADX INFO: compiled from: RpcGc.java */
    public static class a<T> extends PhantomReference<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2067a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f2068b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Class<?> f2069c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public T f2070d;
    }

    public e(c cVar) {
        this.f2066d = cVar;
    }

    public Object a(int i, Long l, Class<?> cls) {
        synchronized (this.f2064b) {
            for (a<Object> aVar : this.f2064b) {
                if (aVar.f2067a == i && aVar.f2068b == l.longValue() && aVar.f2069c == cls) {
                    return aVar.f2070d;
                }
            }
            return null;
        }
    }

    public void b(Object obj, int i, Long l, Class<?> cls) {
    }
}
