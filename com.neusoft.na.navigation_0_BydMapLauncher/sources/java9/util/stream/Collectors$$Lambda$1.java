package java9.util.stream;

import java.util.HashMap;
import java9.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$1 implements Supplier {
    private static final Collectors$$Lambda$1 instance = new Collectors$$Lambda$1();

    private Collectors$$Lambda$1() {
    }

    @Override // java9.util.function.Supplier
    public Object get() {
        return new HashMap();
    }
}
