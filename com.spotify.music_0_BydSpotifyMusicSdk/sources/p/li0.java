package p;

/* JADX INFO: loaded from: classes.dex */
public final class li0 extends ni0 {
    public final yp k;
    public final /* synthetic */ kotlinx.coroutines.e l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public li0(kotlinx.coroutines.e eVar, long j, zp zpVar) {
        super(j);
        this.l = eVar;
        this.k = zpVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ((zp) this.k).B(this.l);
    }

    @Override // p.ni0
    public final String toString() {
        return super.toString() + this.k;
    }
}
