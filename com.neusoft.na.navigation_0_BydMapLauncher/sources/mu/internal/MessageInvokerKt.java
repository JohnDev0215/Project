package mu.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MessageInvoker.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002H\u0080\b¨\u0006\u0004"}, d2 = {"toStringSafe", "", "Lkotlin/Function0;", "", "kotlin-logging"}, k = 2, mv = {1, 1, 13})
public final class MessageInvokerKt {
    public static final String toStringSafe(Function0<? extends Object> receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        try {
            return String.valueOf(receiver$0.invoke());
        } catch (Exception e) {
            return "Log message invocation failed: " + e;
        }
    }
}
