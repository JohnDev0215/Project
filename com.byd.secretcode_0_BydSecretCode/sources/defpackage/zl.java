package defpackage;

/* JADX INFO: compiled from: AsyncPoster.java */
/* JADX INFO: loaded from: classes.dex */
public class zl implements Runnable, jm {
    public final im b = new im();
    public final bm c;

    public zl(bm bmVar) {
        this.c = bmVar;
    }

    @Override // defpackage.jm
    public void a(om omVar, Object obj) {
        this.b.a(hm.a(omVar, obj));
        this.c.d().execute(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        hm hmVarB = this.b.b();
        if (hmVarB == null) {
            throw new IllegalStateException("No pending post available");
        }
        this.c.g(hmVarB);
    }
}
