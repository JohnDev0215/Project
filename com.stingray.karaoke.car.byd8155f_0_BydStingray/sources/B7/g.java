package b7;

import h5.t;
import java.util.ArrayList;
import k7.C1707a;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ i f12287a;

    public /* synthetic */ g(i iVar) {
        this.f12287a = iVar;
    }

    public void a(Object[] objArr) {
        i iVar;
        int length = objArr.length;
        int i7 = 0;
        while (true) {
            iVar = this.f12287a;
            if (i7 >= length) {
                break;
            }
            Object obj = objArr[i7];
            if (obj instanceof String) {
                h hVar = iVar.f12301y;
                hVar.getClass();
                C1707a.a(new t(16, hVar, (String) obj, false));
            } else if (obj instanceof byte[]) {
                h hVar2 = iVar.f12301y;
                hVar2.getClass();
                C1707a.a(new t(17, hVar2, (byte[]) obj, false));
            }
            i7++;
        }
        iVar.f12293q = false;
        ArrayList arrayList = iVar.f12298v;
        if (arrayList.isEmpty() || iVar.f12293q) {
            return;
        }
        iVar.b1((j7.d) arrayList.remove(0));
    }
}
