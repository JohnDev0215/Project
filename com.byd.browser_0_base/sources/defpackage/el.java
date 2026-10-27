package defpackage;

import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: compiled from: CachedZeroSuggestionsManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class el {
    public static q8 a(Set set, Function function) {
        if (set == null) {
            return null;
        }
        q8 q8Var = new q8(set.size());
        Iterator it = set.iterator();
        while (it.hasNext()) {
            q8Var.add(function.apply(it.next()));
        }
        return q8Var;
    }
}
