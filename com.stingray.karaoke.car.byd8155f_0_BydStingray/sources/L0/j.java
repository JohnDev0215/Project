package L0;

import F0.T;
import h2.AbstractC1347d;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class j implements Iterable, B7.a {
    public final LinkedHashMap l = new LinkedHashMap();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f3909m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f3910n;

    public final Object d(u uVar) {
        Object obj = this.l.get(uVar);
        if (obj != null) {
            return obj;
        }
        throw new IllegalStateException("Key not present: " + uVar + " - consider getOrElse or getOrNull");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof j)) {
            return false;
        }
        j jVar = (j) obj;
        return A7.m.a(this.l, jVar.l) && this.f3909m == jVar.f3909m && this.f3910n == jVar.f3910n;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.f3910n) + AbstractC1347d.d(this.l.hashCode() * 31, 31, this.f3909m);
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return this.l.entrySet().iterator();
    }

    public final void l(u uVar, Object obj) {
        boolean z6 = obj instanceof a;
        LinkedHashMap linkedHashMap = this.l;
        if (!z6 || !linkedHashMap.containsKey(uVar)) {
            linkedHashMap.put(uVar, obj);
            return;
        }
        Object obj2 = linkedHashMap.get(uVar);
        A7.m.d(obj2, "null cannot be cast to non-null type androidx.compose.ui.semantics.AccessibilityAction<*>");
        a aVar = (a) obj2;
        a aVar2 = (a) obj;
        String str = aVar2.f3875a;
        if (str == null) {
            str = aVar.f3875a;
        }
        m7.c cVar = aVar2.f3876b;
        if (cVar == null) {
            cVar = aVar.f3876b;
        }
        linkedHashMap.put(uVar, new a(str, cVar));
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        if (this.f3909m) {
            sb.append("mergeDescendants=true");
            str = ", ";
        } else {
            str = "";
        }
        if (this.f3910n) {
            sb.append(str);
            sb.append("isClearingSemantics=true");
            str = ", ";
        }
        for (Map.Entry entry : this.l.entrySet()) {
            u uVar = (u) entry.getKey();
            Object value = entry.getValue();
            sb.append(str);
            sb.append(uVar.f3965a);
            sb.append(" : ");
            sb.append(value);
            str = ", ";
        }
        return T.z(this) + "{ " + ((Object) sb) + " }";
    }
}
