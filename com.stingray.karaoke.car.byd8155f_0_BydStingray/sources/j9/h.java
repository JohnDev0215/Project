package j9;

import Q5.C0606p0;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class h extends f9.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f17521e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f17522f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f17523g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ h(int i7, Object obj, Object obj2, String str) {
        super(str, true);
        this.f17521e = i7;
        this.f17522f = obj;
        this.f17523g = obj2;
    }

    @Override // f9.a
    public final long a() {
        long jA;
        u[] uVarArr;
        int i7 = 0;
        switch (this.f17521e) {
            case 0:
                m mVar = (m) this.f17522f;
                mVar.l.a(mVar, (y) ((A7.z) this.f17523g).l);
                return -1L;
            case 1:
                try {
                    ((m) this.f17522f).l.b((u) this.f17523g);
                    break;
                } catch (IOException e10) {
                    l9.o oVar = l9.o.f18874a;
                    l9.o oVar2 = l9.o.f18874a;
                    String str = "Http2Connection.Listener failure for " + ((m) this.f17522f).f17549n;
                    oVar2.getClass();
                    l9.o.i(str, 4, e10);
                    try {
                        ((u) this.f17523g).c(2, e10);
                        break;
                    } catch (IOException unused) {
                    }
                }
                return -1L;
            default:
                C0606p0 c0606p0 = (C0606p0) this.f17522f;
                y yVar = (y) this.f17523g;
                c0606p0.getClass();
                A7.z zVar = new A7.z();
                m mVar2 = (m) c0606p0.f7010n;
                synchronized (mVar2.f17545H) {
                    synchronized (mVar2) {
                        try {
                            y yVar2 = mVar2.B;
                            y yVar3 = new y();
                            yVar3.b(yVar2);
                            yVar3.b(yVar);
                            zVar.l = yVar3;
                            jA = ((long) yVar3.a()) - ((long) yVar2.a());
                            uVarArr = (jA == 0 || mVar2.f17548m.isEmpty()) ? null : (u[]) mVar2.f17548m.values().toArray(new u[0]);
                            y yVar4 = (y) zVar.l;
                            A7.m.f(yVar4, "<set-?>");
                            mVar2.B = yVar4;
                            mVar2.f17555u.c(new h(i7, mVar2, zVar, mVar2.f17549n + " onSettings"), 0L);
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    try {
                        mVar2.f17545H.b((y) zVar.l);
                    } catch (IOException e11) {
                        mVar2.b(2, 2, e11);
                    }
                    break;
                }
                if (uVarArr != null) {
                    int length = uVarArr.length;
                    while (i7 < length) {
                        u uVar = uVarArr[i7];
                        synchronized (uVar) {
                            uVar.f17583f += jA;
                            if (jA > 0) {
                                uVar.notifyAll();
                            }
                        }
                        i7++;
                    }
                }
                return -1L;
        }
    }
}
