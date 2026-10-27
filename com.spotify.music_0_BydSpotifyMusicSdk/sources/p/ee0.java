package p;

/* JADX INFO: loaded from: classes.dex */
public abstract class ee0 extends androidx.room.a {
    public abstract void d(th2 th2Var, Object obj);

    public final void e(Object obj) {
        th2 th2VarA = a();
        try {
            d(th2VarA, obj);
            th2VarA.v0();
        } finally {
            c(th2VarA);
        }
    }
}
