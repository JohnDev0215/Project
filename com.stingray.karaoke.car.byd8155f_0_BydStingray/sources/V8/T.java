package V8;

import S8.C0712i;
import W8.AbstractC0842b;
import W8.AbstractC0844d;
import q7.InterfaceC2189c;

/* JADX INFO: loaded from: classes.dex */
public final class T extends AbstractC0844d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f9593a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public C0712i f9594b;

    @Override // W8.AbstractC0844d
    public final boolean a(AbstractC0842b abstractC0842b) {
        S s6 = (S) abstractC0842b;
        if (this.f9593a >= 0) {
            return false;
        }
        long j3 = s6.f9589t;
        if (j3 < s6.f9590u) {
            s6.f9590u = j3;
        }
        this.f9593a = j3;
        return true;
    }

    @Override // W8.AbstractC0844d
    public final InterfaceC2189c[] b(AbstractC0842b abstractC0842b) {
        long j3 = this.f9593a;
        this.f9593a = -1L;
        this.f9594b = null;
        return ((S) abstractC0842b).w(j3);
    }
}
