package java9.util.stream;

import java9.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$56 implements Supplier {
    private final Object arg$1;

    private Collectors$$Lambda$56(Object obj) {
        this.arg$1 = obj;
    }

    public static Supplier lambdaFactory$(Object obj) {
        return new Collectors$$Lambda$56(obj);
    }

    @Override // java9.util.function.Supplier
    public Object get() {
        return Collectors.lambda$boxSupplier$46(this.arg$1);
    }
}
