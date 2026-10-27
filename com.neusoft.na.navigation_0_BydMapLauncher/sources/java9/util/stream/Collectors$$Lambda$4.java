package java9.util.stream;

import java.util.HashSet;
import java9.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$4 implements Supplier {
    private static final Collectors$$Lambda$4 instance = new Collectors$$Lambda$4();

    private Collectors$$Lambda$4() {
    }

    @Override // java9.util.function.Supplier
    public Object get() {
        return new HashSet();
    }
}
