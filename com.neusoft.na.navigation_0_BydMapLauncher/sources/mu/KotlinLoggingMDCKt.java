package mu;

import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.MDC;

/* JADX INFO: compiled from: KotlinLoggingMDC.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\u001aN\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012*\u0010\u0002\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00040\u0003\"\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0086\b¢\u0006\u0002\u0010\b\u001a6\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0086\b¢\u0006\u0002\u0010\t\u001a6\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000b2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0086\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"withLoggingContext", "T", "pair", "", "Lkotlin/Pair;", "", "body", "Lkotlin/Function0;", "([Lkotlin/Pair;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "(Lkotlin/Pair;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "map", "", "(Ljava/util/Map;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "kotlin-logging"}, k = 2, mv = {1, 1, 13})
public final class KotlinLoggingMDCKt {
    public static final <T> T withLoggingContext(Pair<String, String> pair, Function0<? extends T> body) {
        Intrinsics.checkParameterIsNotNull(pair, "pair");
        Intrinsics.checkParameterIsNotNull(body, "body");
        MDC.MDCCloseable mDCCloseablePutCloseable = MDC.putCloseable(pair.getFirst(), pair.getSecond());
        try {
            MDC.MDCCloseable mDCCloseable = mDCCloseablePutCloseable;
            T tInvoke = body.invoke();
            InlineMarker.finallyStart(1);
            CloseableKt.closeFinally(mDCCloseablePutCloseable, null);
            InlineMarker.finallyEnd(1);
            return tInvoke;
        } finally {
        }
    }

    public static final <T> T withLoggingContext(Pair<String, String>[] pair, Function0<? extends T> body) {
        Intrinsics.checkParameterIsNotNull(pair, "pair");
        Intrinsics.checkParameterIsNotNull(body, "body");
        int i = 0;
        try {
            for (Pair<String, String> pair2 : pair) {
                MDC.put(pair2.getFirst(), pair2.getSecond());
            }
            return body.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            int length = pair.length;
            while (i < length) {
                MDC.remove(pair[i].getFirst());
                i++;
            }
            InlineMarker.finallyEnd(1);
        }
    }

    public static final <T> T withLoggingContext(Map<String, String> map, Function0<? extends T> body) {
        Intrinsics.checkParameterIsNotNull(map, "map");
        Intrinsics.checkParameterIsNotNull(body, "body");
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                MDC.put(entry.getKey(), entry.getValue());
            }
            return body.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                MDC.remove(it.next().getKey());
            }
            InlineMarker.finallyEnd(1);
        }
    }
}
