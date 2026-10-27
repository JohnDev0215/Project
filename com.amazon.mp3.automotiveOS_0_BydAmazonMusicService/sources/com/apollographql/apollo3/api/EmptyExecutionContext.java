package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.ExecutionContext;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExecutionContext.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J5\u0010\u0003\u001a\u0002H\u0004\"\u0004\b\u0000\u0010\u00042\u0006\u0010\u0005\u001a\u0002H\u00042\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u0002H\u00040\u0007H\u0016¢\u0006\u0002\u0010\tJ(\u0010\n\u001a\u0004\u0018\u0001H\u000b\"\b\b\u0000\u0010\u000b*\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\rH\u0096\u0002¢\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\u00020\u00012\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\rH\u0016J\u0011\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u0001H\u0096\u0002¨\u0006\u0012"}, d2 = {"Lcom/apollographql/apollo3/api/EmptyExecutionContext;", "Lcom/apollographql/apollo3/api/ExecutionContext;", "()V", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "Lcom/apollographql/apollo3/api/ExecutionContext$Element;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", "E", "key", "Lcom/apollographql/apollo3/api/ExecutionContext$Key;", "(Lcom/apollographql/apollo3/api/ExecutionContext$Key;)Lcom/apollographql/apollo3/api/ExecutionContext$Element;", "minusKey", "plus", "context", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class EmptyExecutionContext implements ExecutionContext {
    public static final EmptyExecutionContext INSTANCE = new EmptyExecutionContext();

    @Override // com.apollographql.apollo3.api.ExecutionContext
    public <R> R fold(R initial, Function2<? super R, ? super ExecutionContext.Element, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(operation, "operation");
        return initial;
    }

    @Override // com.apollographql.apollo3.api.ExecutionContext
    public <E extends ExecutionContext.Element> E get(ExecutionContext.Key<E> key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return null;
    }

    @Override // com.apollographql.apollo3.api.ExecutionContext
    public ExecutionContext minusKey(ExecutionContext.Key<?> key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this;
    }

    @Override // com.apollographql.apollo3.api.ExecutionContext
    public ExecutionContext plus(ExecutionContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return context;
    }

    private EmptyExecutionContext() {
    }
}
