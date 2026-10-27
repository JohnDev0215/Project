package com.apollographql.apollo3.api.json.internal;

import com.apollographql.apollo3.api.Upload;
import com.apollographql.apollo3.api.json.JsonNumber;
import com.apollographql.apollo3.api.json.JsonWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FileUploadAwareJsonWriter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\u0010\u0006\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0014\u001a\u00020\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002J\b\u0010\u0006\u001a\u00020\u0000H\u0016J\b\u0010\u0007\u001a\u00020\u0000H\u0016J\b\u0010\b\u001a\u00020\u0000H\u0016J\b\u0010\t\u001a\u00020\u0000H\u0016J\u0010\u0010\n\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0003H\u0016J\u0010\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0003H\u0016J\u0010\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\rH\u0016J\u0010\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u000eH\u0016J\u0010\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u000fH\u0016J\u0010\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0010H\u0016J\u0010\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0004H\u0016J\b\u0010\u0011\u001a\u00020\u0000H\u0016J\b\u0010\u0013\u001a\u00020\u0012H\u0016R\u0014\u0010\u0014\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R \u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001b\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001e"}, d2 = {"Lcom/apollographql/apollo3/api/json/internal/FileUploadAwareJsonWriter;", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "", "", "Lcom/apollographql/apollo3/api/Upload;", "collectedUploads", "beginArray", "endArray", "beginObject", "endObject", "name", "value", "", "", "", "", "Lcom/apollographql/apollo3/api/json/JsonNumber;", "nullValue", "", "close", "wrappedWriter", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "", "uploads", "Ljava/util/Map;", "getPath", "()Ljava/lang/String;", "path", "<init>", "(Lcom/apollographql/apollo3/api/json/JsonWriter;)V", "apollo-api"}, k = 1, mv = {1, 5, 1})
public final class FileUploadAwareJsonWriter implements JsonWriter {
    private final Map<String, Upload> uploads;
    private final JsonWriter wrappedWriter;

    public FileUploadAwareJsonWriter(JsonWriter wrappedWriter) {
        Intrinsics.checkNotNullParameter(wrappedWriter, "wrappedWriter");
        this.wrappedWriter = wrappedWriter;
        this.uploads = new LinkedHashMap();
    }

    public final Map<String, Upload> collectedUploads() {
        return this.uploads;
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public FileUploadAwareJsonWriter beginArray() throws IOException {
        this.wrappedWriter.beginArray();
        return this;
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public FileUploadAwareJsonWriter endArray() throws IOException {
        this.wrappedWriter.endArray();
        return this;
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public FileUploadAwareJsonWriter beginObject() throws IOException {
        this.wrappedWriter.beginObject();
        return this;
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public FileUploadAwareJsonWriter endObject() throws IOException {
        this.wrappedWriter.endObject();
        return this;
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public FileUploadAwareJsonWriter name(String name) throws IOException {
        Intrinsics.checkNotNullParameter(name, "name");
        this.wrappedWriter.name(name);
        return this;
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public FileUploadAwareJsonWriter value(String value) throws IOException {
        Intrinsics.checkNotNullParameter(value, "value");
        this.wrappedWriter.value(value);
        return this;
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public FileUploadAwareJsonWriter value(boolean value) throws IOException {
        this.wrappedWriter.value(value);
        return this;
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public FileUploadAwareJsonWriter value(double value) throws IOException {
        this.wrappedWriter.value(value);
        return this;
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public FileUploadAwareJsonWriter value(int value) throws IOException {
        this.wrappedWriter.value(value);
        return this;
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public FileUploadAwareJsonWriter value(long value) throws IOException {
        this.wrappedWriter.value(value);
        return this;
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public FileUploadAwareJsonWriter value(JsonNumber value) throws IOException {
        Intrinsics.checkNotNullParameter(value, "value");
        this.wrappedWriter.value(value);
        return this;
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public FileUploadAwareJsonWriter value(Upload value) throws IOException {
        Intrinsics.checkNotNullParameter(value, "value");
        this.uploads.put(this.wrappedWriter.getPath(), value);
        this.wrappedWriter.nullValue();
        return this;
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public FileUploadAwareJsonWriter nullValue() throws IOException {
        this.wrappedWriter.nullValue();
        return this;
    }

    @Override // com.apollographql.apollo3.api.json.JsonWriter
    public String getPath() {
        return this.wrappedWriter.getPath();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.wrappedWriter.close();
    }
}
