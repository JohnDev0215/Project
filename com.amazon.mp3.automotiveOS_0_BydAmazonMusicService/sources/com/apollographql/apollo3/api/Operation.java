package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.Operation.Data;
import com.apollographql.apollo3.api.json.JsonWriter;
import java.io.IOException;
import kotlin.Metadata;

/* JADX INFO: compiled from: Operation.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003:\u0001\u0010J\b\u0010\u0005\u001a\u00020\u0004H&J\b\u0010\u0006\u001a\u00020\u0004H&J\b\u0010\u0007\u001a\u00020\u0004H'J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\bH&J\u0018\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH&¨\u0006\u0011"}, d2 = {"Lcom/apollographql/apollo3/api/Operation;", "Lcom/apollographql/apollo3/api/Operation$Data;", "D", "Lcom/apollographql/apollo3/api/Executable;", "", "document", "name", "id", "Lcom/apollographql/apollo3/api/Adapter;", "adapter", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "", "serializeVariables", "Data", "apollo-api"}, k = 1, mv = {1, 5, 1})
public interface Operation<D extends Data> extends Executable<D> {

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bf\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/apollographql/apollo3/api/Operation$Data;", "", "apollo-api"}, k = 1, mv = {1, 5, 1})
    public interface Data {
    }

    Adapter<D> adapter();

    String document();

    String id();

    String name();

    @Override // com.apollographql.apollo3.api.Executable
    void serializeVariables(JsonWriter writer, CustomScalarAdapters customScalarAdapters) throws IOException;
}
