package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.json.JsonWriter;
import java.io.IOException;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Executable.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001:\u0001\tJ\u0018\u0010\b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\n"}, d2 = {"Lcom/apollographql/apollo3/api/Executable;", "", "D", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "", "serializeVariables", "Variables", "apollo-api"}, k = 1, mv = {1, 5, 1})
public interface Executable<D> {
    void serializeVariables(JsonWriter writer, CustomScalarAdapters customScalarAdapters) throws IOException;

    /* JADX INFO: compiled from: Executable.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001b\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\u0002\u0010\u0005R\u001f\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/apollographql/apollo3/api/Executable$Variables;", "", "valueMap", "", "", "(Ljava/util/Map;)V", "getValueMap", "()Ljava/util/Map;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Variables {
        private final Map<String, Object> valueMap;

        public Variables(Map<String, ? extends Object> valueMap) {
            Intrinsics.checkNotNullParameter(valueMap, "valueMap");
            this.valueMap = valueMap;
        }

        public final Map<String, Object> getValueMap() {
            return this.valueMap;
        }
    }
}
