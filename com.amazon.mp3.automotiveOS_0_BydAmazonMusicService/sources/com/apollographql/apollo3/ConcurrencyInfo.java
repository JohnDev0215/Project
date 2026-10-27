package com.apollographql.apollo3;

import com.apollographql.apollo3.api.ExecutionContext;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ConcurrencyInfo.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lcom/apollographql/apollo3/ConcurrencyInfo;", "Lcom/apollographql/apollo3/api/ExecutionContext$Element;", "Lkotlinx/coroutines/CoroutineDispatcher;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "getDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "Lkotlinx/coroutines/CoroutineScope;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "getCoroutineScope", "()Lkotlinx/coroutines/CoroutineScope;", "Lcom/apollographql/apollo3/api/ExecutionContext$Key;", "getKey", "()Lcom/apollographql/apollo3/api/ExecutionContext$Key;", "key", "<init>", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineScope;)V", "Key", "apollo-runtime"}, k = 1, mv = {1, 5, 1})
public final class ConcurrencyInfo implements ExecutionContext.Element {

    /* JADX INFO: renamed from: Key, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final CoroutineScope coroutineScope;
    private final CoroutineDispatcher dispatcher;

    public ConcurrencyInfo(CoroutineDispatcher dispatcher, CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        this.dispatcher = dispatcher;
        this.coroutineScope = coroutineScope;
    }

    @Override // com.apollographql.apollo3.api.ExecutionContext
    public <R> R fold(R r, Function2<? super R, ? super ExecutionContext.Element, ? extends R> function2) {
        return (R) ExecutionContext.Element.DefaultImpls.fold(this, r, function2);
    }

    @Override // com.apollographql.apollo3.api.ExecutionContext.Element, com.apollographql.apollo3.api.ExecutionContext
    public <E extends ExecutionContext.Element> E get(ExecutionContext.Key<E> key) {
        return (E) ExecutionContext.Element.DefaultImpls.get(this, key);
    }

    @Override // com.apollographql.apollo3.api.ExecutionContext
    public ExecutionContext minusKey(ExecutionContext.Key<?> key) {
        return ExecutionContext.Element.DefaultImpls.minusKey(this, key);
    }

    @Override // com.apollographql.apollo3.api.ExecutionContext
    public ExecutionContext plus(ExecutionContext executionContext) {
        return ExecutionContext.Element.DefaultImpls.plus(this, executionContext);
    }

    public final CoroutineDispatcher getDispatcher() {
        return this.dispatcher;
    }

    @Override // com.apollographql.apollo3.api.ExecutionContext.Element
    public ExecutionContext.Key<?> getKey() {
        return INSTANCE;
    }

    /* JADX INFO: renamed from: com.apollographql.apollo3.ConcurrencyInfo$Key, reason: from kotlin metadata */
    /* JADX INFO: compiled from: ConcurrencyInfo.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/apollographql/apollo3/ConcurrencyInfo$Key;", "Lcom/apollographql/apollo3/api/ExecutionContext$Key;", "Lcom/apollographql/apollo3/ConcurrencyInfo;", "()V", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion implements ExecutionContext.Key<ConcurrencyInfo> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
