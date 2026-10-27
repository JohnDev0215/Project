package com.apollographql.apollo3.api.json;

import com.apollographql.apollo3.api.Upload;
import java.io.Closeable;
import java.io.IOException;
import kotlin.Metadata;

/* JADX INFO: compiled from: JsonWriter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010\u0006\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00060\u0001j\u0002`\u0002J\b\u0010\u0003\u001a\u00020\u0000H&J\b\u0010\u0004\u001a\u00020\u0000H&J\b\u0010\u0005\u001a\u00020\u0000H&J\b\u0010\u0006\u001a\u00020\u0000H&J\u0010\u0010\b\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0007H&J\u0010\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0007H&J\b\u0010\n\u001a\u00020\u0000H&J\u0010\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000bH&J\u0010\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\fH&J\u0010\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH&J\u0010\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH&J\u0010\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000fH&J\u0010\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0010H&R\u0014\u0010\u0013\u001a\u00020\u00078&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Lcom/apollographql/apollo3/api/json/JsonWriter;", "Ljava/io/Closeable;", "Lokio/Closeable;", "beginArray", "endArray", "beginObject", "endObject", "", "name", "value", "nullValue", "", "", "", "", "Lcom/apollographql/apollo3/api/json/JsonNumber;", "Lcom/apollographql/apollo3/api/Upload;", "getPath", "()Ljava/lang/String;", "path", "apollo-api"}, k = 1, mv = {1, 5, 1})
public interface JsonWriter extends Closeable {
    JsonWriter beginArray() throws IOException;

    JsonWriter beginObject() throws IOException;

    JsonWriter endArray() throws IOException;

    JsonWriter endObject() throws IOException;

    String getPath();

    JsonWriter name(String name) throws IOException;

    JsonWriter nullValue() throws IOException;

    JsonWriter value(double value) throws IOException;

    JsonWriter value(int value) throws IOException;

    JsonWriter value(long value) throws IOException;

    JsonWriter value(Upload value) throws IOException;

    JsonWriter value(JsonNumber value) throws IOException;

    JsonWriter value(String value) throws IOException;

    JsonWriter value(boolean value) throws IOException;
}
