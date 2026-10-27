package p;

/* JADX INFO: loaded from: classes.dex */
public final class yz implements e20 {
    public final y10 c;

    public yz(y10 y10Var) {
        this.c = y10Var;
    }

    @Override // p.e20
    public final y10 j() {
        return this.c;
    }

    public final String toString() {
        return "CoroutineScope(coroutineContext=" + this.c + ')';
    }
}
