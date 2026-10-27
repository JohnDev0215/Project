package j7;

import android.os.Looper;
import j7.f;
import j7.g;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: compiled from: EventBusBuilder.java */
/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ExecutorService f5636a = Executors.newCachedThreadPool();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f5641f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f5643h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f5644i;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public List<l7.b> f5646k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public f f5647l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public g f5648m;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f5637b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f5638c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f5639d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f5640e = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f5642g = true;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ExecutorService f5645j = f5636a;

    public static Object a() {
        try {
            return Looper.getMainLooper();
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public f b() {
        f fVar = this.f5647l;
        return fVar != null ? fVar : f.a.a();
    }

    public g c() {
        Object objA;
        g gVar = this.f5648m;
        if (gVar != null) {
            return gVar;
        }
        if (!k7.a.c() || (objA = a()) == null) {
            return null;
        }
        return new g.a((Looper) objA);
    }
}
