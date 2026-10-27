package W8;

import V8.S;
import V8.d0;

/* JADX INFO: loaded from: classes.dex */
public final class E extends S implements d0 {
    @Override // V8.d0
    public final Object getValue() {
        Integer numValueOf;
        synchronized (this) {
            Object[] objArr = this.f9588s;
            A7.m.c(objArr);
            numValueOf = Integer.valueOf(((Number) objArr[((int) ((this.f9589t + ((long) ((int) ((q() + ((long) this.f9591v)) - this.f9589t)))) - 1)) & (objArr.length - 1)]).intValue());
        }
        return numValueOf;
    }

    public final void x(int i7) {
        synchronized (this) {
            Object[] objArr = this.f9588s;
            A7.m.c(objArr);
            j(Integer.valueOf(((Number) objArr[((int) ((this.f9589t + ((long) ((int) ((q() + ((long) this.f9591v)) - this.f9589t)))) - 1)) & (objArr.length - 1)]).intValue() + i7));
        }
    }
}
