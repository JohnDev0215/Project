package io.gsonfire;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.gsonfire.gson.EnumDefaultValueTypeAdapterFactory;
import io.gsonfire.gson.ExcludeByValueTypeAdapterFactory;
import io.gsonfire.gson.FireExclusionStrategy;
import io.gsonfire.gson.FireExclusionStrategyComposite;
import io.gsonfire.gson.FireTypeAdapterFactory;
import io.gsonfire.gson.SimpleIterableTypeAdapterFactory;
import io.gsonfire.gson.TypeSelectorTypeAdapterFactory;
import io.gsonfire.gson.WrapTypeAdapterFactory;
import io.gsonfire.postprocessors.MergeMapPostProcessor;
import io.gsonfire.postprocessors.methodinvoker.MethodInvokerPostProcessor;
import io.gsonfire.util.Mapper;
import io.gsonfire.util.reflection.CachedReflectionFactory;
import io.gsonfire.util.reflection.Factory;
import io.gsonfire.util.reflection.FieldInspector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class GsonFireBuilder {
    private DateSerializationPolicy dateSerializationPolicy;
    private final Map<Class, ClassConfig> classConfigMap = new HashMap();
    private final Map<Class, Mapper> wrappedClasses = new HashMap();
    private final List<Class> orderedClasses = new ArrayList();
    private final List<FireExclusionStrategy> serializationExclusions = new ArrayList();
    private final FieldInspector fieldInspector = new FieldInspector();
    private final Factory factory = new CachedReflectionFactory();
    private final Map<Class, Enum> enumDefaultValues = new HashMap();
    private boolean dateDeserializationStrict = true;
    private TimeZone serializeTimeZone = TimeZone.getDefault();
    private boolean enableExposeMethodResults = false;
    private boolean enableExclusionByValueStrategies = false;

    private ClassConfig getClassConfig(Class cls) {
        ClassConfig classConfig = this.classConfigMap.get(cls);
        if (classConfig != null) {
            return classConfig;
        }
        ClassConfig classConfig2 = new ClassConfig(cls);
        this.classConfigMap.put(cls, classConfig2);
        insertOrdered(this.orderedClasses, cls);
        return classConfig2;
    }

    private static void insertOrdered(List<Class> list, Class cls) {
        for (int size = list.size() - 1; size >= 0; size--) {
            if (list.get(size).isAssignableFrom(cls)) {
                list.add(size + 1, cls);
                return;
            }
        }
        list.add(0, cls);
    }

    public <T> GsonFireBuilder registerTypeSelector(Class<T> cls, TypeSelector<T> typeSelector) {
        getClassConfig(cls).setTypeSelector(typeSelector);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> GsonFireBuilder registerPostProcessor(Class<T> cls, PostProcessor<? super T> postProcessor) {
        getClassConfig(cls).getPostProcessors().add(postProcessor);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> GsonFireBuilder registerPreProcessor(Class<T> cls, PreProcessor<? super T> preProcessor) {
        getClassConfig(cls).getPreProcessors().add(preProcessor);
        return this;
    }

    public GsonFireBuilder dateSerializationPolicy(DateSerializationPolicy dateSerializationPolicy) {
        this.dateSerializationPolicy = dateSerializationPolicy;
        return this;
    }

    public <T> GsonFireBuilder wrap(Class<T> cls, final String str) {
        wrap(cls, new Mapper<T, String>() { // from class: io.gsonfire.GsonFireBuilder.1
            @Override // io.gsonfire.util.Mapper
            public String map(Object obj) {
                return str;
            }
        });
        return this;
    }

    public <T> GsonFireBuilder wrap(Class<T> cls, Mapper<T, String> mapper) {
        this.wrappedClasses.put(cls, mapper);
        return this;
    }

    public GsonFireBuilder enableExposeMethodResult() {
        this.enableExposeMethodResults = true;
        return this;
    }

    public GsonFireBuilder enableExclusionByValue() {
        this.enableExclusionByValueStrategies = true;
        return this;
    }

    public GsonFireBuilder enableHooks(Class cls) {
        getClassConfig(cls).setHooksEnabled(true);
        return this;
    }

    @Deprecated
    public GsonFireBuilder enableMergeMaps(Class cls) {
        registerPostProcessor(cls, new MergeMapPostProcessor(this.fieldInspector));
        return this;
    }

    public GsonFireBuilder serializeTimeZone(TimeZone timeZone) {
        this.serializeTimeZone = timeZone;
        return this;
    }

    public <T extends Enum> GsonFireBuilder enumDefaultValue(Class<T> cls, T t) {
        this.enumDefaultValues.put(cls, t);
        return this;
    }

    public GsonFireBuilder addSerializationExclusionStrategy(FireExclusionStrategy fireExclusionStrategy) {
        this.serializationExclusions.add(fireExclusionStrategy);
        return this;
    }

    public GsonBuilder createGsonBuilder() {
        Set setNewSetFromMap = Collections.newSetFromMap(new ConcurrentHashMap());
        GsonBuilder gsonBuilder = new GsonBuilder();
        if (this.enableExposeMethodResults) {
            registerPostProcessor(Object.class, new MethodInvokerPostProcessor(new FireExclusionStrategyComposite(this.serializationExclusions)));
        }
        if (this.enableExclusionByValueStrategies) {
            gsonBuilder.registerTypeAdapterFactory(new ExcludeByValueTypeAdapterFactory(this.fieldInspector, this.factory));
        }
        Iterator<Class> it = this.orderedClasses.iterator();
        while (it.hasNext()) {
            ClassConfig classConfig = this.classConfigMap.get(it.next());
            if (classConfig.getTypeSelector() != null) {
                gsonBuilder.registerTypeAdapterFactory(new TypeSelectorTypeAdapterFactory(classConfig, setNewSetFromMap));
            }
            gsonBuilder.registerTypeAdapterFactory(new FireTypeAdapterFactory(classConfig));
        }
        for (Map.Entry<Class, Enum> entry : this.enumDefaultValues.entrySet()) {
            gsonBuilder.registerTypeAdapterFactory(new EnumDefaultValueTypeAdapterFactory(entry.getKey(), entry.getValue()));
        }
        DateSerializationPolicy dateSerializationPolicy = this.dateSerializationPolicy;
        if (dateSerializationPolicy != null) {
            gsonBuilder.registerTypeAdapter(Date.class, dateSerializationPolicy.createTypeAdapter(this.serializeTimeZone));
        }
        gsonBuilder.registerTypeAdapterFactory(new SimpleIterableTypeAdapterFactory());
        gsonBuilder.registerTypeAdapterFactory(new WrapTypeAdapterFactory(this.wrappedClasses));
        return gsonBuilder;
    }

    public Gson createGson() {
        return createGsonBuilder().create();
    }
}
