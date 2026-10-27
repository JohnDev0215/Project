package com.apollographql.apollo3.api.json;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: renamed from: com.apollographql.apollo3.api.json.-JsonWriters, reason: invalid class name */
/* JADX INFO: compiled from: JsonWriters.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001¨\u0006\u0005"}, d2 = {"Lcom/apollographql/apollo3/api/json/JsonWriter;", "", "value", "", "writeAny", "apollo-api"}, k = 2, mv = {1, 5, 1})
@JvmName(name = "-JsonWriters")
public final class JsonWriters {
    public static final void writeAny(JsonWriter jsonWriter, Object obj) throws IOException {
        Intrinsics.checkNotNullParameter(jsonWriter, "<this>");
        if (obj == null) {
            jsonWriter.nullValue();
            return;
        }
        if (!(obj instanceof Map)) {
            if (!(obj instanceof List)) {
                if (obj instanceof Boolean) {
                    jsonWriter.value(((Boolean) obj).booleanValue());
                    return;
                }
                if (obj instanceof Integer) {
                    jsonWriter.value(((Number) obj).intValue());
                    return;
                }
                if (obj instanceof Long) {
                    jsonWriter.value(((Number) obj).longValue());
                    return;
                }
                if (obj instanceof Double) {
                    jsonWriter.value(((Number) obj).doubleValue());
                    return;
                }
                if (obj instanceof JsonNumber) {
                    jsonWriter.value((JsonNumber) obj);
                    return;
                }
                if (obj instanceof String) {
                    jsonWriter.value((String) obj);
                    return;
                }
                throw new IllegalStateException(("Cannot write " + obj + " to Json").toString());
            }
            jsonWriter.beginArray();
            Iterator it = ((Iterable) obj).iterator();
            while (it.hasNext()) {
                writeAny(jsonWriter, it.next());
            }
            jsonWriter.endArray();
            return;
        }
        jsonWriter.beginObject();
        for (Map.Entry entry : ((Map) obj).entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            jsonWriter.name(String.valueOf(key));
            writeAny(jsonWriter, value);
        }
        jsonWriter.endObject();
    }
}
