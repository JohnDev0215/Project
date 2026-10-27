package io.gsonfire.gson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.gsonfire.annotations.ExcludeByValue;
import io.gsonfire.util.FieldNameResolver;
import io.gsonfire.util.reflection.Factory;
import io.gsonfire.util.reflection.FieldInspector;
import java.io.IOException;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public final class ExcludeByValueTypeAdapterFactory implements TypeAdapterFactory {
    private final Factory factory;
    private final FieldInspector fieldInspector;
    private FieldNameResolver fieldNameResolver = null;

    public ExcludeByValueTypeAdapterFactory(FieldInspector fieldInspector, Factory factory) {
        this.fieldInspector = fieldInspector;
        this.factory = factory;
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        if (this.fieldNameResolver == null) {
            this.fieldNameResolver = new FieldNameResolver(gson);
        }
        return new ExcludeByValueTypeAdapter(gson, gson.getDelegateAdapter(this, typeToken));
    }

    private class ExcludeByValueTypeAdapter extends TypeAdapter {
        private final Gson gson;
        private final TypeAdapter originalTypeAdapter;

        public ExcludeByValueTypeAdapter(Gson gson, TypeAdapter typeAdapter) {
            this.gson = gson;
            this.originalTypeAdapter = typeAdapter;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Object obj) throws IOException {
            String fieldName;
            if (obj != null) {
                JsonObject asJsonObject = null;
                for (Field field : ExcludeByValueTypeAdapterFactory.this.fieldInspector.getAnnotatedMembers(obj.getClass(), ExcludeByValue.class)) {
                    try {
                        if (((ExclusionByValueStrategy) ExcludeByValueTypeAdapterFactory.this.factory.get(((ExcludeByValue) field.getAnnotation(ExcludeByValue.class)).value())).shouldSkipField(field.get(obj)) && (fieldName = ExcludeByValueTypeAdapterFactory.this.fieldNameResolver.getFieldName(field)) != null) {
                            if (asJsonObject == null) {
                                JsonElement jsonTree = this.originalTypeAdapter.toJsonTree(obj);
                                if (jsonTree == null || jsonTree.isJsonNull() || !jsonTree.isJsonObject()) {
                                    break;
                                } else {
                                    asJsonObject = this.originalTypeAdapter.toJsonTree(obj).getAsJsonObject();
                                }
                            }
                            asJsonObject.remove(fieldName);
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (asJsonObject != null) {
                    this.gson.toJson(asJsonObject, jsonWriter);
                    return;
                } else {
                    this.originalTypeAdapter.write(jsonWriter, obj);
                    return;
                }
            }
            this.originalTypeAdapter.write(jsonWriter, obj);
        }

        @Override // com.google.gson.TypeAdapter
        public Object read(JsonReader jsonReader) throws IOException {
            return this.originalTypeAdapter.read(jsonReader);
        }
    }
}
