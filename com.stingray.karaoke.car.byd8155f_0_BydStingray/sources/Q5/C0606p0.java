package Q5;

import F8.C0269d;
import Q7.InterfaceC0666t;
import T7.AbstractC0789h;
import com.stingray.karaoke.car.ui.navigation.StingrayKaraokeTabScreen;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.p0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0606p0 implements InterfaceC2772a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f7009m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f7010n;

    public /* synthetic */ C0606p0(Object obj, int i7, Object obj2) {
        this.l = i7;
        this.f7010n = obj;
        this.f7009m = obj2;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.l) {
            case 0:
                F.y yVar = (F.y) this.f7010n;
                ((InterfaceC2785n) this.f7009m).invoke(Integer.valueOf(yVar.f1974b.f1443b.i()), Integer.valueOf(yVar.f1974b.c.i()));
                return m7.q.f19267a;
            case 1:
                F8.H.f2437m.getClass();
                F8.H h10 = F8.H.f2438n;
                F8.K kC = ((AbstractC0789h) this.f7010n).C();
                List listEmptyList = Collections.emptyList();
                P5.w wVar = new P5.w(this, 1);
                E8.b bVar = E8.l.f1859e;
                A7.m.e(bVar, "NO_LOCKS");
                return C0269d.s(h10, kC, listEmptyList, new y8.j(bVar, wVar), false);
            case 2:
                O8.g gVar = new O8.g();
                Iterator it = ((T7.t) this.f7010n).q().iterator();
                while (it.hasNext()) {
                    gVar.add(((InterfaceC0666t) it.next()).f((F8.W) this.f7009m));
                }
                return gVar;
            case 3:
                ((InterfaceC2782k) this.f7009m).b(((N5.f0) this.f7010n).f4691a);
                return m7.q.f19267a;
            case 4:
                ((InterfaceC2782k) this.f7009m).b((StingrayKaraokeTabScreen) this.f7010n);
                return m7.q.f19267a;
            default:
                j9.m mVar = (j9.m) this.f7010n;
                j9.q qVar = (j9.q) this.f7009m;
                try {
                } catch (IOException e10) {
                    mVar.b(2, 2, e10);
                } catch (Throwable th) {
                    mVar.b(3, 3, null);
                    d9.b.c(qVar);
                    throw th;
                }
                if (!qVar.b(true, this)) {
                    throw new IOException("Required SETTINGS preface not received");
                }
                while (qVar.b(false, this)) {
                }
                mVar.b(1, 9, null);
                d9.b.c(qVar);
                return m7.q.f19267a;
        }
    }

    public /* synthetic */ C0606p0(m7.c cVar, Object obj, int i7) {
        this.l = i7;
        this.f7009m = cVar;
        this.f7010n = obj;
    }
}
