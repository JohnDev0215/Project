package p;

/* JADX INFO: loaded from: classes.dex */
public final class vx1 implements zc1 {
    public final x00 b;
    public final io.reactivex.rxjava3.disposables.a c;
    public final io.reactivex.rxjava3.subjects.b d;

    public vx1(x00 x00Var) {
        v03.j(x00Var, "player");
        this.b = x00Var;
        this.c = new io.reactivex.rxjava3.disposables.a();
        this.d = io.reactivex.rxjava3.subjects.b.a();
    }

    @Override // p.zc1
    public final void a() {
        this.c.d();
    }
}
