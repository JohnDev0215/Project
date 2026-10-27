package b.a.c.u;

import android.util.Pair;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;

/* JADX INFO: compiled from: RpcGc.java */
/* JADX INFO: loaded from: classes2.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ReferenceQueue<Object> f294a = new ReferenceQueue<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ArrayList<a<Object>> f295b = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ArrayList<Pair<Integer, ArrayList<Long>>> f296c = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f297d;

    /* JADX INFO: compiled from: RpcGc.java */
    public static class a<T> extends PhantomReference<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f298a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f299b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Class<?> f300c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public T f301d;
    }

    public e(c cVar) {
        this.f297d = cVar;
    }

    public Object a(int i, Long l, Class<?> cls) {
        synchronized (this.f295b) {
            for (a<Object> aVar : this.f295b) {
                if (aVar.f298a == i && aVar.f299b == l.longValue() && aVar.f300c == cls) {
                    return aVar.f301d;
                }
            }
            return null;
        }
    }

    public void b(Object obj, int i, Long l, Class<?> cls) {
    }
}
