package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.internal.ResponseParser;
import com.apollographql.apollo3.api.json.JsonReader;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Operations.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a4\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0007¨\u0006\t"}, d2 = {"Lcom/apollographql/apollo3/api/Operation$Data;", "D", "Lcom/apollographql/apollo3/api/Operation;", "Lcom/apollographql/apollo3/api/json/JsonReader;", "jsonReader", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "Lcom/apollographql/apollo3/api/ApolloResponse;", "parseJsonResponse", "apollo-api"}, k = 2, mv = {1, 5, 1})
@JvmName(name = "Operations")
public final class Operations {
    @JvmOverloads
    public static final <D extends Operation.Data> ApolloResponse<D> parseJsonResponse(Operation<D> operation, JsonReader jsonReader, CustomScalarAdapters customScalarAdapters) throws IOException {
        Intrinsics.checkNotNullParameter(operation, "<this>");
        Intrinsics.checkNotNullParameter(jsonReader, "jsonReader");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        return ResponseParser.INSTANCE.parse(jsonReader, operation, customScalarAdapters.newBuilder().variables(Executables.variables(operation, customScalarAdapters)).build());
    }
}
