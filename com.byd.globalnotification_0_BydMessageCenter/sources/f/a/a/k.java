package f.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public j f2566a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public j f2567b;

    public synchronized void a(j jVar) {
        if (this.f2567b != null) {
            this.f2567b.f2565c = jVar;
            this.f2567b = jVar;
        } else {
            if (this.f2566a != null) {
                throw new IllegalStateException("Head present, but no tail");
            }
            this.f2567b = jVar;
            this.f2566a = jVar;
        }
        notifyAll();
    }

    public synchronized j b() {
        j jVar;
        jVar = this.f2566a;
        if (this.f2566a != null) {
            j jVar2 = this.f2566a.f2565c;
            this.f2566a = jVar2;
            if (jVar2 == null) {
                this.f2567b = null;
            }
        }
        return jVar;
    }
}
