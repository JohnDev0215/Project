package n6;

import java.util.Iterator;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class q implements InterfaceC2772a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ u f20124m;

    public /* synthetic */ q(u uVar, int i7) {
        this.l = i7;
        this.f20124m = uVar;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.l) {
            case 0:
                z zVar = this.f20124m.f20127a;
                w wVar = (w) zVar.f20151a.getValue();
                Object obj = null;
                if (wVar == null) {
                    return null;
                }
                long jY = C7.a.Y(((wVar.f20140e * wVar.f20141f) / 60.0f) * (zVar.c != null ? r7.longValue() / 1000.0f : -1.0f));
                Iterator it = wVar.c.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Object next = it.next();
                        AbstractC1961B abstractC1961B = (AbstractC1961B) next;
                        if (abstractC1961B.c() <= jY && abstractC1961B.a() >= jY) {
                            obj = next;
                        }
                    }
                }
                return (AbstractC1961B) obj;
            default:
                return Float.valueOf(this.f20124m.a());
        }
    }
}
