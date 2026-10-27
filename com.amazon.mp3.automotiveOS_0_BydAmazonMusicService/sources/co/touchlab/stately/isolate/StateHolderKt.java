package co.touchlab.stately.isolate;

import kotlin.Metadata;

/* JADX INFO: compiled from: StateHolder.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"defaultStateRunner", "Lco/touchlab/stately/isolate/StateRunner;", "getDefaultStateRunner", "()Lco/touchlab/stately/isolate/StateRunner;", "stately-isolate"}, k = 2, mv = {1, 4, 0})
public final class StateHolderKt {
    private static final StateRunner defaultStateRunner = new BackgroundStateRunner();

    public static final StateRunner getDefaultStateRunner() {
        return defaultStateRunner;
    }
}
