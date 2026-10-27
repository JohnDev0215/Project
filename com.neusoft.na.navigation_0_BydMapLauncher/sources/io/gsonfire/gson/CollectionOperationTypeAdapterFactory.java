package io.gsonfire.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public final class CollectionOperationTypeAdapterFactory implements TypeAdapterFactory {
    private Class<? extends Collection> clazz;

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        if (Collection.class.isAssignableFrom(typeToken.getRawType())) {
            return new CollectionOperationTypeAdapter(gson.getDelegateAdapter(this, typeToken));
        }
        return null;
    }
}
