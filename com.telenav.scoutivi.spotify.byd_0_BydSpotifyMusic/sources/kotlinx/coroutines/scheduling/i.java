package kotlinx.coroutines.scheduling;

/* JADX INFO: compiled from: Tasks.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class i implements Runnable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f7778e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public j f7779g;

    public i(long j7, j jVar) {
        this.f7778e = j7;
        this.f7779g = jVar;
    }

    public i() {
        this(0L, h.f7777g);
    }
}
