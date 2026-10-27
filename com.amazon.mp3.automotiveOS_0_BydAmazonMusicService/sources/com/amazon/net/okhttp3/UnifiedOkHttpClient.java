package com.amazon.net.okhttp3;

import com.amazon.net.okhttp3.app.UnifiedImplementation;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;

/* JADX INFO: compiled from: UnifiedOkHttpClient.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u001a\u0006\u0010\u0001\u001a\u00020\u0000\u001a\u0006\u0010\u0003\u001a\u00020\u0002\u001a\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0000\u001a\b\u0010\b\u001a\u00020\u0004H\u0002\"\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b\"\u0018\u0010\f\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lokhttp3/OkHttpClient$Builder;", "newBuilder", "Lokhttp3/OkHttpClient;", "newClient", "Lcom/amazon/net/okhttp3/app/UnifiedImplementation;", "injectedImplementation", "", "injectImplementationInternal", "setDefaultImplementation", "", "LOCK_OBJECT", "Ljava/lang/Object;", "unifiedImplementation", "Lcom/amazon/net/okhttp3/app/UnifiedImplementation;", "UnifiedOkHttpClient"}, k = 2, mv = {1, 6, 0})
@JvmName(name = "UnifiedOkHttpClient")
public final class UnifiedOkHttpClient {
    private static final Object LOCK_OBJECT = new Object();
    private static volatile UnifiedImplementation unifiedImplementation;

    public static final OkHttpClient.Builder newBuilder() {
        UnifiedImplementation defaultImplementation = unifiedImplementation;
        if (defaultImplementation == null) {
            defaultImplementation = setDefaultImplementation();
        }
        return defaultImplementation.getBuilderFactory().invoke(ExtraEventListener.EMPTY_LISTENER, defaultImplementation.getPriorityPolicyDefault(), defaultImplementation.getRetryPolicyDefault());
    }

    public static final OkHttpClient newClient() {
        return newBuilder().build();
    }

    public static final /* synthetic */ void injectImplementationInternal(UnifiedImplementation injectedImplementation) {
        Intrinsics.checkNotNullParameter(injectedImplementation, "injectedImplementation");
        synchronized (LOCK_OBJECT) {
            if (!(unifiedImplementation == null)) {
                throw new IllegalStateException("Changes to UnifiedOkHttpClient implementation are not allowed".toString());
            }
            unifiedImplementation = injectedImplementation;
            Unit unit = Unit.INSTANCE;
        }
    }

    private static final UnifiedImplementation setDefaultImplementation() {
        UnifiedImplementation unifiedImplementation2;
        synchronized (LOCK_OBJECT) {
            if (unifiedImplementation == null) {
                unifiedImplementation = new UnifiedImplementation(new Function3<ExtraEventListener, PriorityPolicy, RetryPolicy, OkHttpClient.Builder>() { // from class: com.amazon.net.okhttp3.UnifiedOkHttpClient$setDefaultImplementation$1$1
                    @Override // kotlin.jvm.functions.Function3
                    public final OkHttpClient.Builder invoke(ExtraEventListener extraEventListener, PriorityPolicy priorityPolicy, RetryPolicy retryPolicy) {
                        Intrinsics.checkNotNullParameter(extraEventListener, "<anonymous parameter 0>");
                        return new OkHttpClient.Builder();
                    }
                }, null, null, null, null, 30, null);
            }
            unifiedImplementation2 = unifiedImplementation;
            if (unifiedImplementation2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.net.okhttp3.app.UnifiedImplementation");
            }
        }
        return unifiedImplementation2;
    }
}
