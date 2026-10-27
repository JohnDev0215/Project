package u3;

import android.util.Pair;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;

/* JADX INFO: compiled from: RpcGc.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ReferenceQueue<Object> f8161a = new ReferenceQueue<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ArrayList<a<Object>> f8162b = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ArrayList<Pair<Integer, ArrayList<Long>>> f8163c = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b f8164d;

    /* JADX INFO: compiled from: RpcGc.java */
    public static class a<T> extends PhantomReference<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f8165a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f8166b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Class<?> f8167c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public T f8168d;
    }

    public c(b bVar) {
        this.f8164d = bVar;
    }

    public Object a(int i8, Long l8, Class<?> cls) {
        synchronized (this.f8162b) {
            for (a<Object> aVar : this.f8162b) {
                if (aVar.f8165a == i8 && aVar.f8166b == l8.longValue() && aVar.f8167c == cls) {
                    return aVar.f8168d;
                }
            }
            return null;
        }
    }

    public void b(Object obj, int i8, Long l8, Class<?> cls) {
    }
}
