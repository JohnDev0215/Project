package p;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class m02 {
    public static final m02 c = zb0.u(new w01((String) null, 0, (ArrayList) null, 0, 31));
    public final w01 a;
    public final Map b;

    public m02(w01 w01Var, LinkedHashMap linkedHashMap) {
        this.a = w01Var;
        this.b = linkedHashMap;
    }

    public final Set a() {
        Collection collectionValues = this.b.values();
        ArrayList arrayList = new ArrayList(lu.W(collectionValues));
        Iterator it = collectionValues.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf((int) ((pc) it.next()).f));
        }
        return ou.q0(arrayList);
    }
}
