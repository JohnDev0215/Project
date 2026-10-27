package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.Executable;
import com.apollographql.apollo3.api.json.MapJsonWriter;
import java.io.IOException;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Executables.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\"\u0010\u0006\u001a\u00020\u0005\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0004\u001a\u00020\u0003¨\u0006\u0007"}, d2 = {"", "D", "Lcom/apollographql/apollo3/api/Executable;", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "Lcom/apollographql/apollo3/api/Executable$Variables;", "variables", "apollo-api"}, k = 2, mv = {1, 5, 1})
@JvmName(name = "Executables")
public final class Executables {
    public static final <D> Executable.Variables variables(Executable<D> executable, CustomScalarAdapters customScalarAdapters) throws IOException {
        Intrinsics.checkNotNullParameter(executable, "<this>");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        MapJsonWriter mapJsonWriter = new MapJsonWriter();
        mapJsonWriter.beginObject();
        executable.serializeVariables(mapJsonWriter, customScalarAdapters);
        mapJsonWriter.endObject();
        Object objRoot = mapJsonWriter.root();
        if (objRoot == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any?>");
        }
        return new Executable.Variables((Map) objRoot);
    }
}
