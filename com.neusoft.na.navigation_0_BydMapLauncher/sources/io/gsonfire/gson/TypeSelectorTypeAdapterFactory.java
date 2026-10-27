package io.gsonfire.gson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.gsonfire.ClassConfig;
import io.gsonfire.TypeSelector;
import java.io.IOException;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class TypeSelectorTypeAdapterFactory<T> implements TypeAdapterFactory {
    private final Set<TypeToken> alreadyResolvedTypeTokensRegistry;
    private final ClassConfig<T> classConfig;

    public TypeSelectorTypeAdapterFactory(ClassConfig<T> classConfig, Set<TypeToken> set) {
        this.classConfig = classConfig;
        this.alreadyResolvedTypeTokensRegistry = set;
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        if (!this.alreadyResolvedTypeTokensRegistry.contains(typeToken) && this.classConfig.getConfiguredClass().isAssignableFrom(typeToken.getRawType())) {
            return new NullableTypeAdapter(new TypeSelectorTypeAdapter(typeToken.getRawType(), this.classConfig.getTypeSelector(), gson));
        }
        return null;
    }

    private class TypeSelectorTypeAdapter<T> extends TypeAdapter<T> {
        private final Gson gson;
        private final Class superClass;
        private final TypeSelector typeSelector;

        private TypeSelectorTypeAdapter(Class cls, TypeSelector typeSelector, Gson gson) {
            this.superClass = cls;
            this.typeSelector = typeSelector;
            this.gson = gson;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, T t) throws IOException {
            this.gson.toJson(this.gson.getDelegateAdapter(TypeSelectorTypeAdapterFactory.this, TypeToken.get((Class) t.getClass())).toJsonTree(t), jsonWriter);
        }

        @Override // com.google.gson.TypeAdapter
        public T read(JsonReader jsonReader) throws IOException {
            TypeAdapter<T> delegateAdapter;
            JsonElement jsonElement = new JsonParser().parse(jsonReader);
            Class<? extends T> classForElement = this.typeSelector.getClassForElement(jsonElement);
            if (classForElement == null) {
                classForElement = this.superClass;
            }
            TypeToken<T> typeToken = TypeToken.get((Class) classForElement);
            TypeSelectorTypeAdapterFactory.this.alreadyResolvedTypeTokensRegistry.add(typeToken);
            try {
                if (classForElement != this.superClass) {
                    delegateAdapter = this.gson.getAdapter(typeToken);
                } else {
                    delegateAdapter = this.gson.getDelegateAdapter(TypeSelectorTypeAdapterFactory.this, typeToken);
                }
                TypeSelectorTypeAdapterFactory.this.alreadyResolvedTypeTokensRegistry.remove(typeToken);
                return delegateAdapter.fromJsonTree(jsonElement);
            } catch (Throwable th) {
                TypeSelectorTypeAdapterFactory.this.alreadyResolvedTypeTokensRegistry.remove(typeToken);
                throw th;
            }
        }
    }
}
