package io.gsonfire.gson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.gsonfire.ClassConfig;
import io.gsonfire.PostProcessor;
import io.gsonfire.PreProcessor;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class FireTypeAdapter<T> extends TypeAdapter<T> {
    private final ClassConfig<? super T> classConfig;
    private final Class<T> clazz;
    private final Gson gson;
    private final HooksInvoker hooksInvoker = new HooksInvoker();
    private final TypeAdapter<T> originalTypeAdapter;

    public FireTypeAdapter(Class<T> cls, ClassConfig<? super T> classConfig, TypeAdapter<T> typeAdapter, Gson gson) {
        this.classConfig = classConfig;
        this.gson = gson;
        this.originalTypeAdapter = typeAdapter;
        this.clazz = cls;
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, T t) throws IOException {
        if (this.classConfig.isHooksEnabled()) {
            this.hooksInvoker.preSerialize(t);
        }
        JsonElement jsonTree = this.originalTypeAdapter.toJsonTree(t);
        runPostSerialize(jsonTree, t);
        this.gson.toJson(jsonTree, jsonWriter);
    }

    @Override // com.google.gson.TypeAdapter
    public T read(JsonReader jsonReader) throws IOException {
        JsonElement jsonElement = new JsonParser().parse(jsonReader);
        runPreDeserialize(jsonElement);
        T tDeserialize = deserialize(jsonElement);
        if (this.classConfig.isHooksEnabled()) {
            this.hooksInvoker.postDeserialize(tDeserialize, jsonElement, this.gson);
        }
        runPostDeserialize(tDeserialize, jsonElement);
        return tDeserialize;
    }

    private void runPostSerialize(JsonElement jsonElement, T t) {
        Iterator<PostProcessor<? super T>> it = this.classConfig.getPostProcessors().iterator();
        while (it.hasNext()) {
            it.next().postSerialize(jsonElement, t, this.gson);
        }
    }

    private void runPostDeserialize(T t, JsonElement jsonElement) {
        Iterator<PostProcessor<? super T>> it = this.classConfig.getPostProcessors().iterator();
        while (it.hasNext()) {
            it.next().postDeserialize(t, jsonElement, this.gson);
        }
    }

    private void runPreDeserialize(JsonElement jsonElement) {
        Iterator<PreProcessor<? super T>> it = this.classConfig.getPreProcessors().iterator();
        while (it.hasNext()) {
            it.next().preDeserialize(this.clazz, jsonElement, this.gson);
        }
    }

    private T deserialize(JsonElement jsonElement) {
        return this.originalTypeAdapter.fromJsonTree(jsonElement);
    }
}
