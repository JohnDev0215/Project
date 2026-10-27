package S8;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public final class I extends K {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C0712i f8087n;
    public final /* synthetic */ M o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public I(M m10, long j3, C0712i c0712i) {
        super(j3);
        this.o = m10;
        this.f8087n = c0712i;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalAccessException, InvocationTargetException {
        this.f8087n.C(this.o);
    }

    @Override // S8.K
    public final String toString() {
        return super.toString() + this.f8087n;
    }
}
