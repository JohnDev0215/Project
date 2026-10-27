package C1;

import io.sentry.C1489n1;
import java.util.ArrayList;
import u.C2458F;

/* JADX INFO: loaded from: classes.dex */
public final class g implements E1.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f938a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f939b;

    public /* synthetic */ g(Object obj, int i7) {
        this.f938a = i7;
        this.f939b = obj;
    }

    @Override // E1.a
    public final void accept(Object obj) {
        switch (this.f938a) {
            case 0:
                h hVar = (h) obj;
                if (hVar == null) {
                    hVar = new h(-3);
                }
                ((C1489n1) this.f939b).B(hVar);
                return;
            default:
                h hVar2 = (h) obj;
                synchronized (i.c) {
                    try {
                        C2458F c2458f = i.d;
                        ArrayList arrayList = (ArrayList) c2458f.get((String) this.f939b);
                        if (arrayList == null) {
                            return;
                        }
                        c2458f.remove((String) this.f939b);
                        for (int i7 = 0; i7 < arrayList.size(); i7++) {
                            ((E1.a) arrayList.get(i7)).accept(hVar2);
                        }
                        return;
                    } finally {
                    }
                }
        }
    }
}
