package rx.plugins;

/* JADX INFO: loaded from: classes4.dex */
final class RxJavaObservableExecutionHookDefault extends RxJavaObservableExecutionHook {
    private static final RxJavaObservableExecutionHookDefault INSTANCE = new RxJavaObservableExecutionHookDefault();

    private RxJavaObservableExecutionHookDefault() {
    }

    public static RxJavaObservableExecutionHook getInstance() {
        return INSTANCE;
    }
}
