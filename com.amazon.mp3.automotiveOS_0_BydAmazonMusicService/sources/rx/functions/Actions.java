package rx.functions;

import rx.exceptions.OnErrorNotImplementedException;

/* JADX INFO: loaded from: classes6.dex */
public final class Actions {
    private static final EmptyAction EMPTY_ACTION = new EmptyAction();

    private Actions() {
        throw new IllegalStateException("No instances!");
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8> EmptyAction<T0, T1, T2, T3, T4, T5, T6, T7, T8> empty() {
        return EMPTY_ACTION;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final class EmptyAction<T0, T1, T2, T3, T4, T5, T6, T7, T8> implements Action0, Action1<T0>, Action2<T0, T1> {
        @Override // rx.functions.Action0
        public void call() {
        }

        @Override // rx.functions.Action1
        public void call(T0 t0) {
        }

        @Override // rx.functions.Action2
        public void call(T0 t0, T1 t1) {
        }

        EmptyAction() {
        }
    }

    enum NotImplemented implements Action1<Throwable> {
        INSTANCE;

        @Override // rx.functions.Action1
        public void call(Throwable th) {
            throw new OnErrorNotImplementedException(th);
        }
    }

    public static Action1<Throwable> errorNotImplemented() {
        return NotImplemented.INSTANCE;
    }
}
