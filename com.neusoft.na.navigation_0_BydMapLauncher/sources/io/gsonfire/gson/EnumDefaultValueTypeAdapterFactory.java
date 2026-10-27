package io.gsonfire.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.Enum;

/* JADX INFO: loaded from: classes.dex */
public final class EnumDefaultValueTypeAdapterFactory<T extends Enum> implements TypeAdapterFactory {
    private final Class<T> clazz;
    private final T defaultValue;

    public EnumDefaultValueTypeAdapterFactory(Class<T> cls, T t) {
        this.clazz = cls;
        this.defaultValue = t;
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        if (!this.clazz.isAssignableFrom(typeToken.getRawType())) {
            return null;
        }
        final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, typeToken);
        return new NullableTypeAdapter(new TypeAdapter<T>() { // from class: io.gsonfire.gson.EnumDefaultValueTypeAdapterFactory.1
            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, T t) throws IOException {
                delegateAdapter.write(jsonWriter, t);
            }

            @Override // com.google.gson.TypeAdapter
            public T read(JsonReader jsonReader) throws IOException {
                T t = (T) delegateAdapter.read(jsonReader);
                return t == null ? (T) EnumDefaultValueTypeAdapterFactory.this.defaultValue : t;
            }
        });
    }
}
