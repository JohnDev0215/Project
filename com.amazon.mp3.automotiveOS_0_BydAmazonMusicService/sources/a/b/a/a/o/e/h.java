package a.b.a.a.o.e;

/* JADX INFO: loaded from: classes.dex */
public final class h implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f139a;
    public int b;

    public h() {
    }

    public h(int i, int i2) {
        this.f139a = i;
        this.b = i2;
    }

    public h(c cVar) {
        h hVar = (h) cVar;
        this.f139a = hVar.getWidth();
        this.b = hVar.getHeight();
    }

    public synchronized void a(int i, int i2) {
        this.f139a = i;
        this.b = i2;
    }

    @Override // a.b.a.a.o.e.c
    public synchronized int getHeight() {
        return this.b;
    }

    @Override // a.b.a.a.o.e.c
    public synchronized int getWidth() {
        return this.f139a;
    }

    @Override // a.b.a.a.o.e.c
    public synchronized String toString() {
        StringBuilder sb;
        sb = new StringBuilder();
        synchronized (this) {
        }
        sb.append(this.b);
        return sb.toString();
        sb.append(this.f139a);
        sb.append('x');
        synchronized (this) {
            sb.append(this.b);
        }
        return sb.toString();
    }
}
