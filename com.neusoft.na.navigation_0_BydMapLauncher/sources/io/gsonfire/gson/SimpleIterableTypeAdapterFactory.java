package io.gsonfire.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import io.gsonfire.util.SimpleIterable;
import java.lang.reflect.ParameterizedType;

/* JADX INFO: loaded from: classes.dex */
public final class SimpleIterableTypeAdapterFactory implements TypeAdapterFactory {
    @Override // com.google.gson.TypeAdapterFactory
    public TypeAdapter create(Gson gson, TypeToken typeToken) {
        if (typeToken.getRawType() != SimpleIterable.class) {
            return null;
        }
        if (typeToken.getType() instanceof ParameterizedType) {
            return new SimpleIterableTypeAdapter(gson, ((ParameterizedType) typeToken.getType()).getActualTypeArguments()[0]);
        }
        return new SimpleIterableTypeAdapter(gson, Object.class);
    }
}
