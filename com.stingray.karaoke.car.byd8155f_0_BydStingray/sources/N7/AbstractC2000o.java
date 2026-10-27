package n7;

import java.util.Collection;

/* JADX INFO: renamed from: n7.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2000o extends AbstractC1999n {
    public static int J(Iterable iterable, int i7) {
        A7.m.f(iterable, "<this>");
        return iterable instanceof Collection ? ((Collection) iterable).size() : i7;
    }
}
