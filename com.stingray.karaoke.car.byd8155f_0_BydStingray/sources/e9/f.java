package e9;

import Q5.C0606p0;
import g9.m;
import g9.n;
import java.io.IOException;
import java.net.Socket;
import r9.AbstractC2312b;
import r9.C2316f;

/* JADX INFO: loaded from: classes.dex */
public final class f extends f9.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f14505e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f14506f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ f(Object obj, String str, int i7) {
        super(str, true);
        this.f14505e = i7;
        this.f14506f = obj;
    }

    @Override // f9.a
    public final long a() {
        switch (this.f14505e) {
            case 0:
                g gVar = (g) this.f14506f;
                synchronized (gVar) {
                    if (gVar.f14523w && !gVar.f14524x) {
                        try {
                            gVar.F();
                        } catch (IOException unused) {
                            gVar.f14525y = true;
                        }
                        try {
                            if (gVar.m()) {
                                gVar.z();
                                gVar.f14520t = 0;
                            }
                        } catch (IOException unused2) {
                            gVar.f14526z = true;
                            gVar.f14518r = AbstractC2312b.b(new C2316f());
                        }
                        break;
                    }
                }
                return -1L;
            case 1:
                ((C0606p0) this.f14506f).a();
                return -1L;
            case 2:
                n nVar = (n) this.f14506f;
                long jNanoTime = System.nanoTime();
                int i7 = 0;
                long j3 = Long.MIN_VALUE;
                m mVar = null;
                int i10 = 0;
                for (m mVar2 : nVar.d) {
                    A7.m.e(mVar2, "connection");
                    synchronized (mVar2) {
                        if (nVar.b(mVar2, jNanoTime) > 0) {
                            i10++;
                        } else {
                            i7++;
                            long j10 = jNanoTime - mVar2.f15087q;
                            if (j10 > j3) {
                                mVar = mVar2;
                                j3 = j10;
                            }
                        }
                    }
                }
                long j11 = nVar.f15088a;
                if (j3 < j11 && i7 <= 5) {
                    if (i7 > 0) {
                        return j11 - j3;
                    }
                    if (i10 > 0) {
                        return j11;
                    }
                    return -1L;
                }
                A7.m.c(mVar);
                synchronized (mVar) {
                    if (!mVar.f15086p.isEmpty()) {
                        return 0L;
                    }
                    if (mVar.f15087q + j3 != jNanoTime) {
                        return 0L;
                    }
                    mVar.f15082j = true;
                    nVar.d.remove(mVar);
                    Socket socket = mVar.d;
                    A7.m.c(socket);
                    d9.b.d(socket);
                    if (!nVar.d.isEmpty()) {
                        return 0L;
                    }
                    nVar.f15089b.a();
                    return 0L;
                }
            default:
                j9.m mVar3 = (j9.m) this.f14506f;
                mVar3.getClass();
                try {
                    mVar3.f17545H.m(2, 0, false);
                    return -1L;
                } catch (IOException e10) {
                    mVar3.b(2, 2, e10);
                    return -1L;
                }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ f(String str, Object obj, int i7) {
        super(str, true);
        this.f14505e = i7;
        this.f14506f = obj;
    }
}
