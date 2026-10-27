package java9.util.stream;

import java.util.ArrayList;
import java9.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$3 implements Supplier {
    private static final Collectors$$Lambda$3 instance = new Collectors$$Lambda$3();

    private Collectors$$Lambda$3() {
    }

    @Override // java9.util.function.Supplier
    public Object get() {
        return new ArrayList();
    }
}
