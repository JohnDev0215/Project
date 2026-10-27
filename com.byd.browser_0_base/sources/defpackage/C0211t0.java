package defpackage;

import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.base.b;
import org.chromium.components.signin.a;
import org.chromium.components.signin.base.CoreAccountId;
import org.chromium.components.signin.base.CoreAccountInfo;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes3.dex
 */
/* JADX INFO: renamed from: t0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AccountManagerFacadeImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class C0211t0 extends i9<List<String>> {
    public final /* synthetic */ List h;
    public final /* synthetic */ a i;

    public C0211t0(a aVar, ArrayList arrayList) {
        this.i = aVar;
        this.h = arrayList;
    }

    @Override // defpackage.i9
    public final List<String> b() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        ArrayList arrayList = new ArrayList();
        Iterator it = this.h.iterator();
        if (!it.hasNext()) {
            oj2.b0(SystemClock.elapsedRealtime() - jElapsedRealtime, "Signin.AndroidGetAccountIdsTime");
            return arrayList;
        }
        this.i.c();
        return null;
    }

    @Override // defpackage.i9
    public final void l(List<String> list) {
        List<String> list2 = list;
        a aVar = this.i;
        if (list2 == null) {
            aVar.getClass();
            Object obj = ThreadUtils.a;
            aVar.e.g(new se3(20, aVar));
            return;
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            List list3 = this.h;
            if (i >= list3.size()) {
                break;
            }
            String str = (String) list3.get(i);
            String str2 = list2.get(i);
            arrayList.add(new CoreAccountInfo(new CoreAccountId(str2), str, str2));
            i++;
        }
        if (aVar.f.d()) {
            aVar.f = s62.c(arrayList);
        } else {
            aVar.f.b(arrayList);
        }
        Iterator<z1> it = aVar.b.iterator();
        while (true) {
            b.a aVar2 = (b.a) it;
            if (!aVar2.hasNext()) {
                return;
            } else {
                ((z1) aVar2.next()).q();
            }
        }
    }
}
