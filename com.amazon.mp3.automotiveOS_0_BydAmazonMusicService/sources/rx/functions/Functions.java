package rx.functions;

/* JADX INFO: loaded from: classes6.dex */
public final class Functions {
    private Functions() {
        throw new IllegalStateException("No instances!");
    }

    public static <T0, T1, R> FuncN<R> fromFunc(final Func2<? super T0, ? super T1, ? extends R> func2) {
        return new FuncN<R>() { // from class: rx.functions.Functions.3
            @Override // rx.functions.FuncN
            public R call(Object... objArr) {
                if (objArr.length != 2) {
                    throw new IllegalArgumentException("Func2 expecting 2 arguments.");
                }
                return (R) func2.call(objArr[0], objArr[1]);
            }
        };
    }

    public static <T0, T1, T2, R> FuncN<R> fromFunc(final Func3<? super T0, ? super T1, ? super T2, ? extends R> func3) {
        return new FuncN<R>() { // from class: rx.functions.Functions.4
            @Override // rx.functions.FuncN
            public R call(Object... objArr) {
                if (objArr.length != 3) {
                    throw new IllegalArgumentException("Func3 expecting 3 arguments.");
                }
                return (R) func3.call(objArr[0], objArr[1], objArr[2]);
            }
        };
    }
}
