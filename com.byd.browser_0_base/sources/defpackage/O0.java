package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: chromium-webapk8.dex-default-8 */
/* JADX INFO: loaded from: assets/webapk8.dex */
public abstract class O0 extends R0 {
    public static List a(N0 n0) {
        ArrayList arrayList = new ArrayList();
        Iterator it = n0.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return C0183f.a(arrayList);
    }
}
