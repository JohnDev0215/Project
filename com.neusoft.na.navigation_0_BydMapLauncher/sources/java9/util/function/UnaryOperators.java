package java9.util.function;

/* JADX INFO: loaded from: classes3.dex */
public final class UnaryOperators {
    static /* synthetic */ Object lambda$identity$0(Object obj) {
        return obj;
    }

    public static <T> UnaryOperator<T> identity() {
        return UnaryOperators$$Lambda$1.instance;
    }

    private UnaryOperators() {
    }
}
