package com.apollographql.apollo3.api;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: CompiledGraphQL.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u0011\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0001\t¨\u0006\n"}, d2 = {"Lcom/apollographql/apollo3/api/CompiledNamedType;", "Lcom/apollographql/apollo3/api/CompiledType;", "", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "Lcom/apollographql/apollo3/api/EnumType;", "apollo-api"}, k = 1, mv = {1, 5, 1})
public abstract class CompiledNamedType extends CompiledType {
    private final String name;

    public /* synthetic */ CompiledNamedType(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    private CompiledNamedType(String str) {
        super(null);
        this.name = str;
    }
}
