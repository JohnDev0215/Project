package p4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: SetBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public final class e<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<T> f8847a;

    private e(int i7) {
        this.f8847a = new ArrayList(i7);
    }

    public static <T> e<T> c(int i7) {
        return new e<>(i7);
    }

    public e<T> a(T t6) {
        this.f8847a.add((T) d.c(t6, "Set contributions cannot be null"));
        return this;
    }

    public Set<T> b() {
        return this.f8847a.isEmpty() ? Collections.emptySet() : this.f8847a.size() == 1 ? Collections.singleton(this.f8847a.get(0)) : Collections.unmodifiableSet(new HashSet(this.f8847a));
    }
}
