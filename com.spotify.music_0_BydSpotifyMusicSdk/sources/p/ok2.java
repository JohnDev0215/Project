package p;

/* JADX INFO: loaded from: classes.dex */
public final class ok2 extends Thread {
    public final /* synthetic */ py0 c;

    public ok2(py0 py0Var) {
        this.c = py0Var;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        this.c.invoke();
    }
}
