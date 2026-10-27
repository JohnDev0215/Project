package p;

import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class d80 {
    public final String a;
    public final p01 b;

    public d80(Set set, p01 p01Var) {
        this.a = a(set);
        this.b = p01Var;
    }

    public static String a(Set set) {
        StringBuilder sb = new StringBuilder();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            ei eiVar = (ei) it.next();
            sb.append(eiVar.a);
            sb.append('/');
            sb.append(eiVar.b);
            if (it.hasNext()) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }
}
