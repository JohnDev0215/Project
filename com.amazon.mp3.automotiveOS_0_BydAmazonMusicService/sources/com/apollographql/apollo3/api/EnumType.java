package com.apollographql.apollo3.api;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CompiledGraphQL.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/apollographql/apollo3/api/EnumType;", "Lcom/apollographql/apollo3/api/CompiledNamedType;", "name", "", "(Ljava/lang/String;)V", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class EnumType extends CompiledNamedType {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EnumType(String name) {
        super(name, null);
        Intrinsics.checkNotNullParameter(name, "name");
    }
}
