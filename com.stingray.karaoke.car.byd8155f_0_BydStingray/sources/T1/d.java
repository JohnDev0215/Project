package T1;

import android.os.Build;
import g1.AbstractC1274a;
import java.util.ArrayList;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class d extends AbstractC1274a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ e f8489e;

    public d(e eVar) {
        this.f8489e = eVar;
    }

    @Override // g1.AbstractC1274a
    public final void V(Throwable th) {
        this.f8489e.f8490a.e(th);
    }

    @Override // g1.AbstractC1274a
    public final void W(B8.t tVar) {
        e eVar = this.f8489e;
        eVar.c = tVar;
        B8.t tVar2 = eVar.c;
        i iVar = eVar.f8490a;
        eVar.f8491b = new P(tVar2, iVar.f8500g, iVar.f8502i, Build.VERSION.SDK_INT >= 34 ? n.a() : io.sentry.config.a.b0());
        i iVar2 = eVar.f8490a;
        iVar2.getClass();
        ArrayList arrayList = new ArrayList();
        iVar2.f8496a.writeLock().lock();
        try {
            iVar2.c = 1;
            arrayList.addAll(iVar2.f8497b);
            iVar2.f8497b.clear();
            iVar2.f8496a.writeLock().unlock();
            iVar2.d.post(new C1.a(arrayList, iVar2.c, null));
        } catch (Throwable th) {
            iVar2.f8496a.writeLock().unlock();
            throw th;
        }
    }
}
