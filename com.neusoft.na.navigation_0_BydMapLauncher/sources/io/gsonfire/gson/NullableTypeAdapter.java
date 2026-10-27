package io.gsonfire.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class NullableTypeAdapter<T> extends TypeAdapter<T> {
    private final TypeAdapter<T> nullable;

    public NullableTypeAdapter(TypeAdapter<T> typeAdapter) {
        this.nullable = typeAdapter;
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, T t) throws IOException {
        if (t == null) {
            jsonWriter.nullValue();
        } else {
            this.nullable.write(jsonWriter, t);
        }
    }

    @Override // com.google.gson.TypeAdapter
    public T read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        return this.nullable.read(jsonReader);
    }
}
