package io.gsonfire.gson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import io.gsonfire.annotations.PostDeserialize;
import io.gsonfire.annotations.PreSerialize;
import io.gsonfire.util.reflection.AbstractMethodInspector;
import io.gsonfire.util.reflection.MethodInvoker;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class HooksInvoker {
    private static final Set<Class> SUPPORTED_TYPES = new HashSet(Arrays.asList(JsonElement.class, Gson.class));
    private AbstractMethodInspector<MethodInvoker> inspector = new AbstractMethodInspector<MethodInvoker>() { // from class: io.gsonfire.gson.HooksInvoker.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.gsonfire.util.reflection.AnnotationInspector
        public MethodInvoker map(Method method) {
            return new MethodInvoker(method, HooksInvoker.SUPPORTED_TYPES);
        }
    };

    public void preSerialize(Object obj) {
        invokeAll(obj, PreSerialize.class, null, null);
    }

    public void postDeserialize(Object obj, JsonElement jsonElement, Gson gson) {
        invokeAll(obj, PostDeserialize.class, jsonElement, gson);
    }

    private void invokeAll(Object obj, Class<? extends Annotation> cls, JsonElement jsonElement, Gson gson) {
        if (obj != null) {
            Iterator<MethodInvoker> it = this.inspector.getAnnotatedMembers(obj.getClass(), cls).iterator();
            while (it.hasNext()) {
                try {
                    it.next().invoke(obj, new HooksInvokerValueSupplier(jsonElement, gson));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    private static class HooksInvokerValueSupplier implements MethodInvoker.ValueSupplier {
        private final Gson gson;
        private final JsonElement jsonElement;

        private HooksInvokerValueSupplier(JsonElement jsonElement, Gson gson) {
            this.jsonElement = jsonElement;
            this.gson = gson;
        }

        @Override // io.gsonfire.util.reflection.MethodInvoker.ValueSupplier
        public Object getValueForType(Class cls) {
            if (cls == JsonElement.class) {
                return this.jsonElement;
            }
            if (cls == Gson.class) {
                return this.gson;
            }
            return null;
        }
    }
}
