package p;

/* JADX INFO: loaded from: classes.dex */
public final class bd2 implements oa0 {
    public final kotlinx.coroutines.flow.g c;
    public final long e;
    public final Object k;
    public final c00 l;

    public bd2(kotlinx.coroutines.flow.g gVar, long j, Object obj, zp zpVar) {
        this.c = gVar;
        this.e = j;
        this.k = obj;
        this.l = zpVar;
    }

    @Override // p.oa0
    public final void dispose() {
        kotlinx.coroutines.flow.g gVar = this.c;
        synchronized (gVar) {
            if (this.e < gVar.j()) {
                return;
            }
            Object[] objArr = gVar.f90p;
            v03.h(objArr);
            long j = this.e;
            if (objArr[((int) j) & (objArr.length - 1)] != this) {
                return;
            }
            cd2.a(objArr, j, cd2.a);
            gVar.e();
        }
    }
}
