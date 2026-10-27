package io.gsonfire.postprocessors;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.gsonfire.PostProcessor;
import io.gsonfire.annotations.MergeMap;
import io.gsonfire.util.reflection.FieldInspector;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class MergeMapPostProcessor implements PostProcessor {
    private final FieldInspector fieldInspector;

    @Override // io.gsonfire.PostProcessor
    public void postDeserialize(Object obj, JsonElement jsonElement, Gson gson) {
    }

    public MergeMapPostProcessor(FieldInspector fieldInspector) {
        this.fieldInspector = fieldInspector;
    }

    @Override // io.gsonfire.PostProcessor
    public void postSerialize(JsonElement jsonElement, Object obj, Gson gson) {
        if (obj == null) {
            return;
        }
        Iterator<Field> it = this.fieldInspector.getAnnotatedMembers(obj.getClass(), MergeMap.class).iterator();
        while (it.hasNext()) {
            try {
                Map map = (Map) it.next().get(obj);
                JsonObject asJsonObject = jsonElement.getAsJsonObject();
                for (Map.Entry<String, JsonElement> entry : gson.toJsonTree(map).getAsJsonObject().entrySet()) {
                    asJsonObject.add(entry.getKey(), entry.getValue());
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
