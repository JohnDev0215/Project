package io.gsonfire.gson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.gsonfire.util.Mapper;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class WrapTypeAdapterFactory<T> implements TypeAdapterFactory {
    private final Map<Class<T>, Mapper<T, String>> wrappedClasses;

    public WrapTypeAdapterFactory(Map<Class<T>, Mapper<T, String>> map) {
        this.wrappedClasses = map;
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, typeToken);
        Mapper<T, String> mostSpecificMapper = getMostSpecificMapper(typeToken.getRawType());
        return mostSpecificMapper == null ? delegateAdapter : new NullableTypeAdapter(new WrapperTypeAdapter(mostSpecificMapper, gson, delegateAdapter));
    }

    private Mapper<T, String> getMostSpecificMapper(Class cls) {
        while (cls != null) {
            Mapper<T, String> mapper = this.wrappedClasses.get(cls);
            if (mapper != null) {
                return mapper;
            }
            cls = cls.getSuperclass();
        }
        return null;
    }

    private class WrapperTypeAdapter<T> extends TypeAdapter<T> {
        private final Gson gson;
        private final Mapper<T, String> mapper;
        private final TypeAdapter<T> originalTypeAdapter;

        public WrapperTypeAdapter(Mapper<T, String> mapper, Gson gson, TypeAdapter<T> typeAdapter) {
            this.mapper = mapper;
            this.gson = gson;
            this.originalTypeAdapter = typeAdapter;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, T t) throws IOException {
            if (t == null) {
                this.originalTypeAdapter.write(jsonWriter, t);
                return;
            }
            String map = this.mapper.map(t);
            JsonElement jsonTree = this.originalTypeAdapter.toJsonTree(t);
            JsonObject jsonObject = new JsonObject();
            jsonObject.add(map, jsonTree);
            this.gson.toJson(jsonObject, jsonWriter);
        }

        @Override // com.google.gson.TypeAdapter
        public T read(JsonReader jsonReader) throws IOException {
            jsonReader.beginObject();
            jsonReader.nextName();
            T t = this.originalTypeAdapter.read(jsonReader);
            jsonReader.endObject();
            return t;
        }
    }
}
