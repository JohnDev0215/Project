package B3;

import c9.C1042c;
import java.io.IOException;
import r9.C2319i;
import r9.J;
import r9.q;

/* JADX INFO: loaded from: classes.dex */
public final class b extends q {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f615m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f616n;

    public /* synthetic */ b(J j3) {
        super(j3);
    }

    @Override // r9.q, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        switch (this.f615m) {
            case 1:
                ((C1042c) this.f616n).l.close();
                super.close();
                break;
            default:
                super.close();
                break;
        }
    }

    @Override // r9.q, r9.J
    public long p(C2319i c2319i, long j3) throws Exception {
        switch (this.f615m) {
            case 0:
                try {
                    return super.p(c2319i, j3);
                } catch (Exception e10) {
                    this.f616n = e10;
                    throw e10;
                }
            default:
                return super.p(c2319i, j3);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(J j3, C1042c c1042c) {
        super(j3);
        this.f616n = c1042c;
    }
}
