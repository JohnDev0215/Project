package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.Executable;
import com.apollographql.apollo3.api.ExecutionContext;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomScalarAdapters.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u00142\u00020\u0001:\u0002\u0015\u0014B+\b\u0002\u0012\u0016\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\t\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u0012\u0010\u0013J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002J\u0006\u0010\u0006\u001a\u00020\u0005R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\bR$\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0018\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0016"}, d2 = {"Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "Lcom/apollographql/apollo3/api/ExecutionContext$Element;", "", "", "variables", "Lcom/apollographql/apollo3/api/CustomScalarAdapters$Builder;", "newBuilder", "Lcom/apollographql/apollo3/api/Executable$Variables;", "Lcom/apollographql/apollo3/api/Executable$Variables;", "", "Lcom/apollographql/apollo3/api/Adapter;", "adaptersMap", "Ljava/util/Map;", "Lcom/apollographql/apollo3/api/ExecutionContext$Key;", "getKey", "()Lcom/apollographql/apollo3/api/ExecutionContext$Key;", "key", "customScalarAdapters", "<init>", "(Ljava/util/Map;Lcom/apollographql/apollo3/api/Executable$Variables;)V", "Key", "Builder", "apollo-api"}, k = 1, mv = {1, 5, 1})
public final class CustomScalarAdapters implements ExecutionContext.Element {
    private final Map<String, Adapter<?>> adaptersMap;
    private final Executable.Variables variables;

    /* JADX INFO: renamed from: Key, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JvmField
    public static final CustomScalarAdapters Empty = new Builder().build();

    public /* synthetic */ CustomScalarAdapters(Map map, Executable.Variables variables, DefaultConstructorMarker defaultConstructorMarker) {
        this(map, variables);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private CustomScalarAdapters(Map<String, ? extends Adapter<?>> map, Executable.Variables variables) {
        this.variables = variables;
        this.adaptersMap = map;
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

    public final Set<String> variables() {
        Executable.Variables variables = this.variables;
        if (variables == null) {
            return SetsKt__SetsKt.emptySet();
        }
        Map<String, Object> valueMap = variables.getValueMap();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, Object> entry : valueMap.entrySet()) {
            if (Intrinsics.areEqual(entry.getValue(), Boolean.TRUE)) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap.keySet();
    }

    @Override // com.apollographql.apollo3.api.ExecutionContext.Element
    public ExecutionContext.Key<?> getKey() {
        return INSTANCE;
    }

    /* JADX INFO: renamed from: com.apollographql.apollo3.api.CustomScalarAdapters$Key, reason: from kotlin metadata */
    /* JADX INFO: compiled from: CustomScalarAdapters.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/apollographql/apollo3/api/CustomScalarAdapters$Key;", "Lcom/apollographql/apollo3/api/ExecutionContext$Key;", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "()V", "Empty", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion implements ExecutionContext.Key<CustomScalarAdapters> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final Builder newBuilder() {
        return new Builder().addAll(this);
    }

    /* JADX INFO: compiled from: CustomScalarAdapters.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002J\u0006\u0010\u0005\u001a\u00020\u0002J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006R$\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\t\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0007\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/apollographql/apollo3/api/CustomScalarAdapters$Builder;", "", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "addAll", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/apollographql/apollo3/api/Executable$Variables;", "variables", "", "", "Lcom/apollographql/apollo3/api/Adapter;", "adaptersMap", "Ljava/util/Map;", "Lcom/apollographql/apollo3/api/Executable$Variables;", "<init>", "()V", "apollo-api"}, k = 1, mv = {1, 5, 1})
    public static final class Builder {
        private final Map<String, Adapter<?>> adaptersMap = new LinkedHashMap();
        private Executable.Variables variables;

        public final Builder addAll(CustomScalarAdapters customScalarAdapters) {
            Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
            this.adaptersMap.putAll(customScalarAdapters.adaptersMap);
            return this;
        }

        public final CustomScalarAdapters build() {
            return new CustomScalarAdapters(this.adaptersMap, this.variables, null);
        }

        public final Builder variables(Executable.Variables variables) {
            Intrinsics.checkNotNullParameter(variables, "variables");
            this.variables = variables;
            return this;
        }
    }
}
