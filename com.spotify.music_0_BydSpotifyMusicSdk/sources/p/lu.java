package p;

import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public abstract class lu extends v03 {
    public static int W(Iterable iterable) {
        v03.j(iterable, "<this>");
        if (iterable instanceof Collection) {
            return ((Collection) iterable).size();
        }
        return 10;
    }
}
