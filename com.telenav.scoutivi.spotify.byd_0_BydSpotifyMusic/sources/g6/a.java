package g6;

import kotlin.jvm.internal.g;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: Task.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private d f6564a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f6565b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f6566c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final boolean f6567d;

    public a(String name, boolean z6) {
        l.e(name, "name");
        this.f6566c = name;
        this.f6567d = z6;
        this.f6565b = -1L;
    }

    public final boolean a() {
        return this.f6567d;
    }

    public final String b() {
        return this.f6566c;
    }

    public final long c() {
        return this.f6565b;
    }

    public final d d() {
        return this.f6564a;
    }

    public final void e(d queue) {
        l.e(queue, "queue");
        d dVar = this.f6564a;
        if (dVar == queue) {
            return;
        }
        if (!(dVar == null)) {
            throw new IllegalStateException("task is in multiple queues".toString());
        }
        this.f6564a = queue;
    }

    public abstract long f();

    public final void g(long j7) {
        this.f6565b = j7;
    }

    public String toString() {
        return this.f6566c;
    }

    public /* synthetic */ a(String str, boolean z6, int i7, g gVar) {
        this(str, (i7 & 2) != 0 ? true : z6);
    }
}
