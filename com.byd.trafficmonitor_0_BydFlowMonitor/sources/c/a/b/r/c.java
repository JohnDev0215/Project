package c.a.b.r;

import android.util.Pair;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;

/* JADX INFO: compiled from: RpcGc.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ReferenceQueue<Object> f3526a = new ReferenceQueue<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ArrayList<a<Object>> f3527b = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ArrayList<Pair<Integer, ArrayList<Long>>> f3528c = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b f3529d;

    /* JADX INFO: compiled from: RpcGc.java */
    public static class a<T> extends PhantomReference<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f3530a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f3531b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Class<?> f3532c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public T f3533d;
    }

    public c(b bVar) {
        this.f3529d = bVar;
    }

    public Object a(int i2, Long l, Class<?> cls) {
        synchronized (this.f3527b) {
            for (a<Object> aVar : this.f3527b) {
                if (aVar.f3530a == i2 && aVar.f3531b == l.longValue() && aVar.f3532c == cls) {
                    return aVar.f3533d;
                }
            }
            return null;
        }
    }

    public void b(Object obj, int i2, Long l, Class<?> cls) {
    }
}
