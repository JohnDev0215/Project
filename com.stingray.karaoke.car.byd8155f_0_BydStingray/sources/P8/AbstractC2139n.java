package p8;

import java.io.Serializable;
import java.util.Collections;

/* JADX INFO: renamed from: p8.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2139n extends AbstractC2126a implements Serializable {
    public static C2138m g(AbstractC2136k abstractC2136k, AbstractC2139n abstractC2139n, int i7, C2122J c2122j, Class cls) {
        return new C2138m(abstractC2136k, Collections.emptyList(), abstractC2139n, new C2137l(i7, c2122j, true), cls);
    }

    public static C2138m h(AbstractC2136k abstractC2136k, Serializable serializable, AbstractC2139n abstractC2139n, int i7, EnumC2124L enumC2124L, Class cls) {
        return new C2138m(abstractC2136k, serializable, abstractC2139n, new C2137l(i7, enumC2124L, false), cls);
    }
}
