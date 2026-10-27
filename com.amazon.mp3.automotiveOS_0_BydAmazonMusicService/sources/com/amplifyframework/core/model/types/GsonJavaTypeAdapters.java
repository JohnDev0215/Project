package com.amplifyframework.core.model.types;

import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes5.dex */
public final class GsonJavaTypeAdapters {
    private GsonJavaTypeAdapters() {
    }

    public static void register(@NonNull GsonBuilder gsonBuilder) {
        gsonBuilder.registerTypeAdapter(String.class, new StringDeserializer()).registerTypeAdapterFactory(new ClassTypeAdapterFactory());
    }

    public static final class StringDeserializer implements JsonDeserializer<String> {
        @Override // com.google.gson.JsonDeserializer
        public String deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            if (jsonElement.isJsonPrimitive()) {
                return jsonElement.getAsJsonPrimitive().getAsString();
            }
            if (jsonElement.isJsonObject()) {
                return jsonElement.toString();
            }
            throw new JsonParseException("Failed to parse String from " + jsonElement);
        }
    }

    public static final class ClassTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (Class.class.isAssignableFrom(typeToken.getRawType())) {
                return new ClassTypeAdapter();
            }
            return null;
        }

        static final class ClassTypeAdapter extends TypeAdapter<Class<?>> {
            ClassTypeAdapter() {
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, Class<?> cls) throws IOException {
                if (cls == null) {
                    jsonWriter.nullValue();
                } else {
                    jsonWriter.value(cls.getName());
                }
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: read */
            public Class<?> read2(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                String strNextString = jsonReader.nextString();
                try {
                    try {
                        return Class.forName(strNextString);
                    } catch (IllegalArgumentException unused) {
                        throw new IOException("Unable to deserialize class for " + strNextString);
                    }
                } catch (ClassNotFoundException unused2) {
                    return boxForPrimitiveLabel(strNextString);
                }
            }

            private static Class<?> boxForPrimitiveLabel(String str) {
                str.hashCode();
                switch (str) {
                    case "double":
                        return Double.class;
                    case "int":
                        return Integer.class;
                    case "byte":
                        return Byte.class;
                    case "char":
                        return Character.class;
                    case "long":
                        return Long.class;
                    case "void":
                        return Void.class;
                    case "boolean":
                        return Boolean.class;
                    case "float":
                        return Float.class;
                    case "short":
                        return Short.class;
                    default:
                        throw new IllegalArgumentException("No primitive with name = " + str);
                }
            }
        }
    }
}
