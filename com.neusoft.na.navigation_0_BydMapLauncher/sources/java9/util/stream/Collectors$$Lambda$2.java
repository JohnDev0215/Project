package java9.util.stream;

import java.util.concurrent.ConcurrentHashMap;
import java9.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$2 implements Supplier {
    private static final Collectors$$Lambda$2 instance = new Collectors$$Lambda$2();

    private Collectors$$Lambda$2() {
    }

    @Override // java9.util.function.Supplier
    public Object get() {
        return new ConcurrentHashMap();
    }
}
