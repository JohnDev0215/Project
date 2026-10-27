package com.amazon.net.okhttp3.app;

import com.amazon.net.okhttp3.ExtraEventListener;
import com.amazon.net.okhttp3.PriorityPolicy;
import com.amazon.net.okhttp3.RetryPolicy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;

/* JADX INFO: compiled from: UnifiedImplementation.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001B\u0087\u0001\u0012&\u0010\b\u001a\"\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u00060\u0002j\u0002`\u0007\u0012\u001e\b\u0002\u0010\u000f\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\u0004`\u000e\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0004\u0012\u001e\b\u0002\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\u0005`\u000e\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u001b\u0010\u001cR7\u0010\b\u001a\"\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u00060\u0002j\u0002`\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR-\u0010\u000f\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\u0004`\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u000f\u0010\u0011R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R-\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\u0005`\u000e8\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0010\u001a\u0004\b\u0016\u0010\u0011R\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001d"}, d2 = {"Lcom/amazon/net/okhttp3/app/UnifiedImplementation;", "", "Lkotlin/Function3;", "Lcom/amazon/net/okhttp3/ExtraEventListener;", "Lcom/amazon/net/okhttp3/PriorityPolicy;", "Lcom/amazon/net/okhttp3/RetryPolicy;", "Lokhttp3/OkHttpClient$Builder;", "Lcom/amazon/net/okhttp3/app/BuilderFactory;", "builderFactory", "Lkotlin/jvm/functions/Function3;", "getBuilderFactory", "()Lkotlin/jvm/functions/Function3;", "Lkotlin/Function1;", "", "Lcom/amazon/net/okhttp3/app/IsSupported;", "isPriorityPolicySupported", "Lkotlin/jvm/functions/Function1;", "()Lkotlin/jvm/functions/Function1;", "priorityPolicyDefault", "Lcom/amazon/net/okhttp3/PriorityPolicy;", "getPriorityPolicyDefault", "()Lcom/amazon/net/okhttp3/PriorityPolicy;", "isRetryPolicySupported", "retryPolicyDefault", "Lcom/amazon/net/okhttp3/RetryPolicy;", "getRetryPolicyDefault", "()Lcom/amazon/net/okhttp3/RetryPolicy;", "<init>", "(Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function1;Lcom/amazon/net/okhttp3/PriorityPolicy;Lkotlin/jvm/functions/Function1;Lcom/amazon/net/okhttp3/RetryPolicy;)V", "UnifiedOkHttpClient"}, k = 1, mv = {1, 6, 0})
public final class UnifiedImplementation {
    private final Function3<ExtraEventListener, PriorityPolicy, RetryPolicy, OkHttpClient.Builder> builderFactory;
    private final Function1<PriorityPolicy, Boolean> isPriorityPolicySupported;
    private final Function1<RetryPolicy, Boolean> isRetryPolicySupported;
    private final PriorityPolicy priorityPolicyDefault;
    private final RetryPolicy retryPolicyDefault;

    /* JADX WARN: Multi-variable type inference failed */
    public UnifiedImplementation(Function3<? super ExtraEventListener, ? super PriorityPolicy, ? super RetryPolicy, OkHttpClient.Builder> builderFactory, Function1<? super PriorityPolicy, Boolean> isPriorityPolicySupported, PriorityPolicy priorityPolicy, Function1<? super RetryPolicy, Boolean> isRetryPolicySupported, RetryPolicy retryPolicy) {
        Intrinsics.checkNotNullParameter(builderFactory, "builderFactory");
        Intrinsics.checkNotNullParameter(isPriorityPolicySupported, "isPriorityPolicySupported");
        Intrinsics.checkNotNullParameter(isRetryPolicySupported, "isRetryPolicySupported");
        this.builderFactory = builderFactory;
        this.isPriorityPolicySupported = isPriorityPolicySupported;
        this.priorityPolicyDefault = priorityPolicy;
        this.isRetryPolicySupported = isRetryPolicySupported;
        this.retryPolicyDefault = retryPolicy;
    }

    public final Function3<ExtraEventListener, PriorityPolicy, RetryPolicy, OkHttpClient.Builder> getBuilderFactory() {
        return this.builderFactory;
    }

    public /* synthetic */ UnifiedImplementation(Function3 function3, Function1 function1, PriorityPolicy priorityPolicy, Function1 function12, RetryPolicy retryPolicy, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(function3, (i & 2) != 0 ? new Function1<PriorityPolicy, Boolean>() { // from class: com.amazon.net.okhttp3.app.UnifiedImplementation.1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(PriorityPolicy it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Boolean.FALSE;
            }
        } : function1, (i & 4) != 0 ? null : priorityPolicy, (i & 8) != 0 ? new Function1<RetryPolicy, Boolean>() { // from class: com.amazon.net.okhttp3.app.UnifiedImplementation.2
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(RetryPolicy it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Boolean.FALSE;
            }
        } : function12, (i & 16) != 0 ? null : retryPolicy);
    }

    public final PriorityPolicy getPriorityPolicyDefault() {
        return this.priorityPolicyDefault;
    }

    public final RetryPolicy getRetryPolicyDefault() {
        return this.retryPolicyDefault;
    }
}
