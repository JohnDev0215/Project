package v8;

import T7.Q;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import n7.AbstractC2000o;

/* JADX INFO: renamed from: v8.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2561a implements O8.b {
    public static final C2561a l = new C2561a();

    @Override // O8.b
    public final Iterable b(Object obj) {
        int i7 = AbstractC2564d.f23040a;
        Collection collectionQ = ((Q) obj).q();
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(collectionQ, 10));
        Iterator it = ((ArrayList) collectionQ).iterator();
        while (it.hasNext()) {
            arrayList.add(((Q) it.next()).b1());
        }
        return arrayList;
    }
}
