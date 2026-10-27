package com.apollographql.apollo3.api;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExecutionContext.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u0000 \u00112\u00020\u0001:\u0003\u0011\u0012\u0013J5\u0010\u0002\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u00032\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002H\u00030\u0006H&¢\u0006\u0002\u0010\bJ(\u0010\t\u001a\u0004\u0018\u0001H\n\"\b\b\u0000\u0010\n*\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\n0\fH¦\u0002¢\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\u00020\u00002\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH&J\u0011\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0000H\u0096\u0002¨\u0006\u0014"}, d2 = {"Lcom/apollographql/apollo3/api/ExecutionContext;", "", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "Lcom/apollographql/apollo3/api/ExecutionContext$Element;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", "E", "key", "Lcom/apollographql/apollo3/api/ExecutionContext$Key;", "(Lcom/apollographql/apollo3/api/ExecutionContext$Key;)Lcom/apollographql/apollo3/api/ExecutionContext$Element;", "minusKey", "plus", "context", "Companion", "Element", "Key", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface ExecutionContext {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @JvmField
    public static final ExecutionContext Empty = EmptyExecutionContext.INSTANCE;

    /* JADX INFO: compiled from: ExecutionContext.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003¨\u0006\u0004"}, d2 = {"Lcom/apollographql/apollo3/api/ExecutionContext$Key;", "E", "Lcom/apollographql/apollo3/api/ExecutionContext$Element;", "", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public interface Key<E extends Element> {
    }

    <R> R fold(R initial, Function2<? super R, ? super Element, ? extends R> operation);

    <E extends Element> E get(Key<E> key);

    ExecutionContext minusKey(Key<?> key);

    ExecutionContext plus(ExecutionContext context);

    /* JADX INFO: compiled from: ExecutionContext.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public static final class DefaultImpls {
        public static ExecutionContext plus(ExecutionContext executionContext, ExecutionContext context) {
            Intrinsics.checkNotNullParameter(executionContext, "this");
            Intrinsics.checkNotNullParameter(context, "context");
            return context == EmptyExecutionContext.INSTANCE ? executionContext : (ExecutionContext) context.fold(executionContext, new Function2<ExecutionContext, Element, ExecutionContext>() { // from class: com.apollographql.apollo3.api.ExecutionContext.plus.1
                @Override // kotlin.jvm.functions.Function2
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
                public final ExecutionContext mo4invoke(ExecutionContext acc, Element element) {
                    Intrinsics.checkNotNullParameter(acc, "acc");
                    Intrinsics.checkNotNullParameter(element, "element");
                    ExecutionContext executionContextMinusKey = acc.minusKey(element.getKey());
                    return executionContextMinusKey == EmptyExecutionContext.INSTANCE ? element : new CombinedExecutionContext(executionContextMinusKey, element);
                }
            });
        }
    }

    /* JADX INFO: compiled from: ExecutionContext.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J*\u0010\u0005\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0002*\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096\u0002¢\u0006\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00038&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/apollographql/apollo3/api/ExecutionContext$Element;", "Lcom/apollographql/apollo3/api/ExecutionContext;", "E", "Lcom/apollographql/apollo3/api/ExecutionContext$Key;", "key", "get", "(Lcom/apollographql/apollo3/api/ExecutionContext$Key;)Lcom/apollographql/apollo3/api/ExecutionContext$Element;", "getKey", "()Lcom/apollographql/apollo3/api/ExecutionContext$Key;", "apollo-api"}, k = 1, mv = {1, 5, 1})
    public interface Element extends ExecutionContext {
        @Override // com.apollographql.apollo3.api.ExecutionContext
        <E extends Element> E get(Key<E> key);

        Key<?> getKey();

        /* JADX INFO: compiled from: ExecutionContext.kt */
        @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
        public static final class DefaultImpls {
            public static ExecutionContext plus(Element element, ExecutionContext context) {
                Intrinsics.checkNotNullParameter(element, "this");
                Intrinsics.checkNotNullParameter(context, "context");
                return DefaultImpls.plus(element, context);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static <E extends Element> E get(Element element, Key<E> key) {
                Intrinsics.checkNotNullParameter(element, "this");
                Intrinsics.checkNotNullParameter(key, "key");
                if (Intrinsics.areEqual(element.getKey(), key)) {
                    return element;
                }
                return null;
            }

            public static <R> R fold(Element element, R r, Function2<? super R, ? super Element, ? extends R> operation) {
                Intrinsics.checkNotNullParameter(element, "this");
                Intrinsics.checkNotNullParameter(operation, "operation");
                return operation.mo4invoke(r, element);
            }

            public static ExecutionContext minusKey(Element element, Key<?> key) {
                Intrinsics.checkNotNullParameter(element, "this");
                Intrinsics.checkNotNullParameter(key, "key");
                return Intrinsics.areEqual(element.getKey(), key) ? EmptyExecutionContext.INSTANCE : element;
            }
        }
    }

    /* JADX INFO: compiled from: ExecutionContext.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0001¨\u0006\u0005"}, d2 = {"Lcom/apollographql/apollo3/api/ExecutionContext$Companion;", "", "()V", "Empty", "Lcom/apollographql/apollo3/api/ExecutionContext;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }
    }
}
