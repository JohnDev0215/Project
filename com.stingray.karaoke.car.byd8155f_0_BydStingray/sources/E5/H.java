package E5;

import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class H {
    public static final ArrayList d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f1772a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ThreadLocal f1773b = new ThreadLocal();
    public final LinkedHashMap c = new LinkedHashMap();

    static {
        ArrayList arrayList = new ArrayList(5);
        d = arrayList;
        arrayList.add(M.f1782a);
        arrayList.add(C0188m.c);
        arrayList.add(C0182g.f1807e);
        arrayList.add(C0182g.d);
        arrayList.add(I.f1774a);
        arrayList.add(C0187l.d);
    }

    public H(E4.b bVar) {
        int size = bVar.f1747a.size();
        ArrayList arrayList = d;
        ArrayList arrayList2 = new ArrayList(arrayList.size() + size);
        arrayList2.addAll(bVar.f1747a);
        arrayList2.addAll(arrayList);
        this.f1772a = Collections.unmodifiableList(arrayList2);
    }

    public final q a(Class cls) {
        return c(cls, G5.f.f2685a, null);
    }

    public final q b(Type type, Set set) {
        return c(type, set, null);
    }

    public final q c(Type type, Set set, String str) {
        q qVar;
        if (type == null) {
            throw new NullPointerException("type == null");
        }
        if (set == null) {
            throw new NullPointerException("annotations == null");
        }
        Type typeG = G5.f.g(G5.f.a(type));
        Object objAsList = set.isEmpty() ? typeG : Arrays.asList(typeG, set);
        synchronized (this.c) {
            try {
                q qVar2 = (q) this.c.get(objAsList);
                if (qVar2 != null) {
                    return qVar2;
                }
                G g7 = (G) this.f1773b.get();
                if (g7 == null) {
                    g7 = new G(this);
                    this.f1773b.set(g7);
                }
                ArrayList arrayList = g7.f1770a;
                int size = arrayList.size();
                int i7 = 0;
                while (true) {
                    ArrayDeque arrayDeque = g7.f1771b;
                    if (i7 >= size) {
                        F f10 = new F(typeG, str, objAsList);
                        arrayList.add(f10);
                        arrayDeque.add(f10);
                        qVar = null;
                        break;
                    }
                    F f11 = (F) arrayList.get(i7);
                    if (f11.c.equals(objAsList)) {
                        arrayDeque.add(f11);
                        q qVar3 = f11.d;
                        qVar = f11;
                        if (qVar3 != null) {
                            qVar = qVar3;
                        }
                    } else {
                        i7++;
                    }
                }
                try {
                    if (qVar != null) {
                        return qVar;
                    }
                    try {
                        int size2 = this.f1772a.size();
                        for (int i10 = 0; i10 < size2; i10++) {
                            q qVarA = ((p) this.f1772a.get(i10)).a(typeG, set, this);
                            if (qVarA != null) {
                                ((F) g7.f1771b.getLast()).d = qVarA;
                                g7.b(true);
                                return qVarA;
                            }
                        }
                        throw new IllegalArgumentException("No JsonAdapter for " + G5.f.j(typeG, set));
                    } catch (IllegalArgumentException e10) {
                        throw g7.a(e10);
                    }
                } finally {
                    g7.b(false);
                }
            } finally {
            }
        }
    }

    public final q d(C0180e c0180e, Type type, Set set) {
        if (set == null) {
            throw new NullPointerException("annotations == null");
        }
        Type typeG = G5.f.g(G5.f.a(type));
        List list = this.f1772a;
        int iIndexOf = list.indexOf(c0180e);
        if (iIndexOf == -1) {
            throw new IllegalArgumentException("Unable to skip past unknown factory " + c0180e);
        }
        int size = list.size();
        for (int i7 = iIndexOf + 1; i7 < size; i7++) {
            q qVarA = ((p) list.get(i7)).a(typeG, set, this);
            if (qVarA != null) {
                return qVarA;
            }
        }
        throw new IllegalArgumentException("No next JsonAdapter for " + G5.f.j(typeG, set));
    }
}
