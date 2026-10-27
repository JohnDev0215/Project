package com.bugsnag.android.repackaged.dslplatform.json;

import androidx.annotation.Nullable;
import com.amazon.music.destination.parser.ParserUtil;
import com.bugsnag.android.repackaged.dslplatform.json.JsonReader;
import com.bugsnag.android.repackaged.dslplatform.json.JsonWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: classes5.dex */
public class DslJson<TContext> implements UnknownSerializer, TypeLookup {
    private final JsonWriter.WriteObject NULL_WRITER;
    private final JsonWriter.WriteObject OBJECT_ARRAY_WRITER;
    private final JsonWriter.WriteObject<JsonObject> OBJECT_WRITER;
    public final boolean allowArrayFormat;
    protected final List<ConverterFactory<Object>> binderFactories;
    private final ConcurrentMap<Type, Object> binders;

    @Nullable
    public final TContext context;
    private final Map<Class<? extends Annotation>, Boolean> creatorMarkers;
    private final Map<Type, Object> defaults;
    private final JsonReader.DoublePrecision doublePrecision;
    private final JsonReader.ErrorInfo errorInfo;
    private final ExternalConverterAnalyzer externalConverterAnalyzer;

    @Nullable
    protected final Fallback<TContext> fallback;
    protected final StringCache keyCache;
    protected final ThreadLocal<JsonReader> localReader;
    protected final ThreadLocal<JsonWriter> localWriter;
    private final int maxNumberDigits;
    private final int maxStringSize;
    private final ConcurrentMap<Class<?>, JsonReader.ReadJsonObject<JsonObject>> objectReaders;
    public final boolean omitDefaults;
    protected final List<ConverterFactory<JsonReader.ReadObject>> readerFactories;
    private final ConcurrentMap<Type, JsonReader.ReadObject> readers;
    private final int settingsBinders;
    private final int settingsReaders;
    private final int settingsWriters;
    private final JsonReader.UnknownNumberParsing unknownNumbers;
    protected final StringCache valuesCache;
    protected final List<ConverterFactory<JsonWriter.WriteObject>> writerFactories;
    private final ConcurrentMap<Class<?>, Class<?>> writerMap;
    private final ConcurrentMap<Type, JsonWriter.WriteObject> writers;
    private static final Charset UTF8 = Charset.forName(ParserUtil.UTF_8);
    private static final Object unknownValue = new Object();
    private static final Iterator EMPTY_ITERATOR = new Iterator() { // from class: com.bugsnag.android.repackaged.dslplatform.json.DslJson.4
        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        @Override // java.util.Iterator
        @Nullable
        public Object next() {
            return null;
        }

        @Override // java.util.Iterator
        public void remove() {
        }
    };
    private static final JsonWriter.WriteObject CHAR_ARRAY_WRITER = new JsonWriter.WriteObject() { // from class: com.bugsnag.android.repackaged.dslplatform.json.DslJson.8
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonWriter.WriteObject
        public void write(JsonWriter jsonWriter, @Nullable Object obj) {
            StringConverter.serialize(new String((char[]) obj), jsonWriter);
        }
    };
    private static final byte[] NULL = {110, 117, 108, 108};

    public interface ConverterFactory<T> {
        @Nullable
        T tryCreate(Type type, DslJson dslJson);
    }

    public interface Fallback<TContext> {
        @Nullable
        Object deserialize(@Nullable TContext tcontext, Type type, InputStream inputStream) throws IOException;

        void serialize(@Nullable Object obj, OutputStream outputStream) throws IOException;
    }

    public static class Settings<TContext> {
        private boolean allowArrayFormat;
        private TContext context;
        private Fallback<TContext> fallback;
        private int fromServiceLoader;
        private boolean javaSpecifics;
        private boolean omitDefaults;
        private StringCache valuesCache;
        private StringCache keyCache = new SimpleStringCache();
        private JsonReader.ErrorInfo errorInfo = JsonReader.ErrorInfo.WITH_STACK_TRACE;
        private JsonReader.DoublePrecision doublePrecision = JsonReader.DoublePrecision.DEFAULT;
        private JsonReader.UnknownNumberParsing unknownNumbers = JsonReader.UnknownNumberParsing.LONG_AND_BIGDECIMAL;
        private int maxNumberDigits = 512;
        private int maxStringBuffer = 134217728;
        private final List<Configuration> configurations = new ArrayList();
        private final List<ConverterFactory<JsonWriter.WriteObject>> writerFactories = new ArrayList();
        private final List<ConverterFactory<JsonReader.ReadObject>> readerFactories = new ArrayList();
        private final List<ConverterFactory<Object>> binderFactories = new ArrayList();
        private final Set<ClassLoader> classLoaders = new HashSet();
        private final Map<Class<? extends Annotation>, Boolean> creatorMarkers = new HashMap();

        @Deprecated
        public Settings<TContext> fallbackTo(@Nullable Fallback<TContext> fallback) {
            this.fallback = fallback;
            return this;
        }

        public Settings<TContext> includeServiceLoader() {
            return includeServiceLoader(Thread.currentThread().getContextClassLoader());
        }

        public Settings<TContext> includeServiceLoader(ClassLoader classLoader) {
            if (classLoader == null) {
                throw new IllegalArgumentException("loader can't be null");
            }
            this.classLoaders.add(classLoader);
            for (Configuration configuration : ServiceLoader.load(Configuration.class, classLoader)) {
                boolean z = false;
                Class<?> cls = configuration.getClass();
                Iterator<Configuration> it = this.configurations.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if (it.next().getClass() == cls) {
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    this.fromServiceLoader++;
                    this.configurations.add(configuration);
                }
            }
            return this;
        }
    }

    public DslJson() {
        this(new Settings().includeServiceLoader());
    }

    public DslJson(Settings<TContext> settings) {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        this.writerFactories = copyOnWriteArrayList;
        CopyOnWriteArrayList copyOnWriteArrayList2 = new CopyOnWriteArrayList();
        this.readerFactories = copyOnWriteArrayList2;
        CopyOnWriteArrayList copyOnWriteArrayList3 = new CopyOnWriteArrayList();
        this.binderFactories = copyOnWriteArrayList3;
        this.defaults = new ConcurrentHashMap();
        this.objectReaders = new ConcurrentHashMap();
        this.readers = new ConcurrentHashMap();
        this.binders = new ConcurrentHashMap();
        this.writers = new ConcurrentHashMap();
        this.writerMap = new ConcurrentHashMap();
        this.OBJECT_WRITER = new JsonWriter.WriteObject<JsonObject>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.DslJson.5
            @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonWriter.WriteObject
            public void write(JsonWriter jsonWriter, @Nullable JsonObject jsonObject) {
                if (jsonObject == null) {
                    jsonWriter.writeNull();
                } else {
                    jsonObject.serialize(jsonWriter, DslJson.this.omitDefaults);
                }
            }
        };
        this.OBJECT_ARRAY_WRITER = new JsonWriter.WriteObject() { // from class: com.bugsnag.android.repackaged.dslplatform.json.DslJson.7
            @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonWriter.WriteObject
            public void write(JsonWriter jsonWriter, @Nullable Object obj) {
                DslJson.this.serialize(jsonWriter, (JsonObject[]) obj);
            }
        };
        this.NULL_WRITER = new JsonWriter.WriteObject() { // from class: com.bugsnag.android.repackaged.dslplatform.json.DslJson.9
            @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonWriter.WriteObject
            public void write(JsonWriter jsonWriter, @Nullable Object obj) {
                jsonWriter.writeNull();
            }
        };
        if (settings == null) {
            throw new IllegalArgumentException("settings can't be null");
        }
        this.localWriter = new ThreadLocal<JsonWriter>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.DslJson.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.lang.ThreadLocal
            public JsonWriter initialValue() {
                return new JsonWriter(4096, this);
            }
        };
        this.localReader = new ThreadLocal<JsonReader>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.DslJson.2
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.lang.ThreadLocal
            public JsonReader initialValue() {
                DslJson dslJson = this;
                return new JsonReader(new byte[4096], 4096, dslJson.context, new char[64], dslJson.keyCache, dslJson.valuesCache, dslJson, dslJson.errorInfo, this.doublePrecision, this.unknownNumbers, this.maxNumberDigits, this.maxStringSize);
            }
        };
        this.context = (TContext) ((Settings) settings).context;
        this.fallback = ((Settings) settings).fallback;
        this.omitDefaults = ((Settings) settings).omitDefaults;
        this.allowArrayFormat = ((Settings) settings).allowArrayFormat;
        this.keyCache = ((Settings) settings).keyCache;
        this.valuesCache = ((Settings) settings).valuesCache;
        this.unknownNumbers = ((Settings) settings).unknownNumbers;
        this.errorInfo = ((Settings) settings).errorInfo;
        this.doublePrecision = ((Settings) settings).doublePrecision;
        this.maxNumberDigits = ((Settings) settings).maxNumberDigits;
        this.maxStringSize = ((Settings) settings).maxStringBuffer;
        copyOnWriteArrayList.addAll(((Settings) settings).writerFactories);
        this.settingsWriters = ((Settings) settings).writerFactories.size();
        copyOnWriteArrayList2.addAll(((Settings) settings).readerFactories);
        this.settingsReaders = ((Settings) settings).readerFactories.size();
        copyOnWriteArrayList3.addAll(((Settings) settings).binderFactories);
        this.settingsBinders = ((Settings) settings).binderFactories.size();
        this.externalConverterAnalyzer = new ExternalConverterAnalyzer(((Settings) settings).classLoaders);
        this.creatorMarkers = new HashMap(((Settings) settings).creatorMarkers);
        registerReader(byte[].class, BinaryConverter.Base64Reader);
        registerWriter(byte[].class, BinaryConverter.Base64Writer);
        Class<T> cls = Boolean.TYPE;
        registerReader(cls, BoolConverter.READER);
        JsonWriter.WriteObject writeObject = BoolConverter.WRITER;
        registerWriter(cls, writeObject);
        registerDefault(cls, Boolean.FALSE);
        registerReader(boolean[].class, BoolConverter.ARRAY_READER);
        registerWriter(boolean[].class, BoolConverter.ARRAY_WRITER);
        registerReader(Boolean.class, BoolConverter.NULLABLE_READER);
        registerWriter(Boolean.class, writeObject);
        if (((Settings) settings).javaSpecifics) {
            registerJavaSpecifics(this);
        }
        JsonReader.ReadObject readObject = ObjectConverter.MapReader;
        registerReader(LinkedHashMap.class, readObject);
        registerReader(HashMap.class, readObject);
        registerReader(Map.class, readObject);
        registerWriter(Map.class, new JsonWriter.WriteObject<Map>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.DslJson.3
            @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonWriter.WriteObject
            public void write(JsonWriter jsonWriter, @Nullable Map map) {
                if (map == null) {
                    jsonWriter.writeNull();
                    return;
                }
                try {
                    DslJson.this.serializeMap(map, jsonWriter);
                } catch (IOException e) {
                    throw new SerializationException(e);
                }
            }
        });
        registerReader(URI.class, NetConverter.UriReader);
        registerWriter(URI.class, NetConverter.UriWriter);
        registerReader(InetAddress.class, NetConverter.AddressReader);
        registerWriter(InetAddress.class, NetConverter.AddressWriter);
        registerReader(Double.TYPE, NumberConverter.DOUBLE_READER);
        Class<T> cls2 = Double.TYPE;
        JsonWriter.WriteObject writeObject2 = NumberConverter.DOUBLE_WRITER;
        registerWriter(cls2, writeObject2);
        registerDefault(Double.TYPE, Double.valueOf(0.0d));
        registerReader(double[].class, NumberConverter.DOUBLE_ARRAY_READER);
        registerWriter(double[].class, NumberConverter.DOUBLE_ARRAY_WRITER);
        registerReader(Double.class, NumberConverter.NULLABLE_DOUBLE_READER);
        registerWriter(Double.class, writeObject2);
        Class<T> cls3 = Float.TYPE;
        registerReader(cls3, NumberConverter.FLOAT_READER);
        JsonWriter.WriteObject writeObject3 = NumberConverter.FLOAT_WRITER;
        registerWriter(cls3, writeObject3);
        registerDefault(cls3, Float.valueOf(0.0f));
        registerReader(float[].class, NumberConverter.FLOAT_ARRAY_READER);
        registerWriter(float[].class, NumberConverter.FLOAT_ARRAY_WRITER);
        registerReader(Float.class, NumberConverter.NULLABLE_FLOAT_READER);
        registerWriter(Float.class, writeObject3);
        Class<T> cls4 = Integer.TYPE;
        registerReader(cls4, NumberConverter.INT_READER);
        JsonWriter.WriteObject writeObject4 = NumberConverter.INT_WRITER;
        registerWriter(cls4, writeObject4);
        registerDefault(cls4, 0);
        registerReader(int[].class, NumberConverter.INT_ARRAY_READER);
        registerWriter(int[].class, NumberConverter.INT_ARRAY_WRITER);
        registerReader(Integer.class, NumberConverter.NULLABLE_INT_READER);
        registerWriter(Integer.class, writeObject4);
        registerReader(Short.TYPE, NumberConverter.SHORT_READER);
        Class<T> cls5 = Short.TYPE;
        JsonWriter.WriteObject writeObject5 = NumberConverter.SHORT_WRITER;
        registerWriter(cls5, writeObject5);
        registerDefault(Short.TYPE, (short) 0);
        registerReader(short[].class, NumberConverter.SHORT_ARRAY_READER);
        registerWriter(short[].class, NumberConverter.SHORT_ARRAY_WRITER);
        registerReader(Short.class, NumberConverter.NULLABLE_SHORT_READER);
        registerWriter(Short.class, writeObject5);
        Class<T> cls6 = Long.TYPE;
        registerReader(cls6, NumberConverter.LONG_READER);
        JsonWriter.WriteObject writeObject6 = NumberConverter.LONG_WRITER;
        registerWriter(cls6, writeObject6);
        registerDefault(cls6, 0L);
        registerReader(long[].class, NumberConverter.LONG_ARRAY_READER);
        registerWriter(long[].class, NumberConverter.LONG_ARRAY_WRITER);
        registerReader(Long.class, NumberConverter.NULLABLE_LONG_READER);
        registerWriter(Long.class, writeObject6);
        registerReader(BigDecimal.class, NumberConverter.DecimalReader);
        registerWriter(BigDecimal.class, NumberConverter.DecimalWriter);
        registerReader(String.class, StringConverter.READER);
        registerWriter(String.class, StringConverter.WRITER);
        registerReader(UUID.class, UUIDConverter.READER);
        registerWriter(UUID.class, UUIDConverter.WRITER);
        registerReader(Number.class, NumberConverter.NumberReader);
        registerWriter(CharSequence.class, StringConverter.WRITER_CHARS);
        registerReader(StringBuilder.class, StringConverter.READER_BUILDER);
        registerReader(StringBuffer.class, StringConverter.READER_BUFFER);
        Iterator it = ((Settings) settings).configurations.iterator();
        while (it.hasNext()) {
            ((Configuration) it.next()).configure(this);
        }
        if (((Settings) settings).classLoaders.isEmpty() || ((Settings) settings).fromServiceLoader != 0) {
            return;
        }
        loadDefaultConverters(this, ((Settings) settings).classLoaders, "dsl_json_Annotation_Processor_External_Serialization");
        loadDefaultConverters(this, ((Settings) settings).classLoaders, "dsl_json.json.ExternalSerialization");
        loadDefaultConverters(this, ((Settings) settings).classLoaders, "dsl_json_ExternalSerialization");
    }

    public static class SimpleStringCache implements StringCache {
        private final String[] cache;
        private final int mask;

        public SimpleStringCache() {
            this(10);
        }

        public SimpleStringCache(int i) {
            int i2 = 2;
            for (int i3 = 1; i3 < i; i3++) {
                i2 *= 2;
            }
            this.mask = i2 - 1;
            this.cache = new String[i2];
        }

        @Override // com.bugsnag.android.repackaged.dslplatform.json.StringCache
        public String get(char[] cArr, int i) {
            long j = -2128831035;
            for (int i2 = 0; i2 < i; i2++) {
                j = (j ^ ((long) ((byte) cArr[i2]))) * 16777619;
            }
            int i3 = ((int) j) & this.mask;
            String str = this.cache[i3];
            if (str == null) {
                return createAndPut(i3, cArr, i);
            }
            if (str.length() != i) {
                return createAndPut(i3, cArr, i);
            }
            for (int i4 = 0; i4 < str.length(); i4++) {
                if (str.charAt(i4) != cArr[i4]) {
                    return createAndPut(i3, cArr, i);
                }
            }
            return str;
        }

        private String createAndPut(int i, char[] cArr, int i2) {
            String str = new String(cArr, 0, i2);
            this.cache[i] = str;
            return str;
        }
    }

    private static void loadDefaultConverters(DslJson dslJson, Set<ClassLoader> set, String str) {
        Iterator<ClassLoader> it = set.iterator();
        while (it.hasNext()) {
            try {
                ((Configuration) it.next().loadClass(str).newInstance()).configure(dslJson);
            } catch (Exception | NoClassDefFoundError unused) {
            }
        }
    }

    static void registerJavaSpecifics(DslJson dslJson) {
        dslJson.registerReader(Element.class, XmlConverter.Reader);
        dslJson.registerWriter(Element.class, XmlConverter.Writer);
    }

    public <T> void registerDefault(Class<T> cls, T t) {
        this.defaults.put(cls, t);
    }

    public <T, S extends T> void registerReader(Class<T> cls, @Nullable JsonReader.ReadObject<S> readObject) {
        if (readObject == null) {
            this.readers.remove(cls);
        } else {
            this.readers.put(cls, readObject);
        }
    }

    public <T> void registerWriter(Class<T> cls, @Nullable JsonWriter.WriteObject<T> writeObject) {
        if (writeObject == null) {
            this.writerMap.remove(cls);
            this.writers.remove(cls);
        } else {
            this.writerMap.put(cls, cls);
            this.writers.put(cls, writeObject);
        }
    }

    @Nullable
    public JsonWriter.WriteObject<?> tryFindWriter(Type type) {
        JsonWriter.WriteObject<?> writeObject;
        JsonWriter.WriteObject<?> writeObject2 = this.writers.get(type);
        if (writeObject2 != null) {
            return writeObject2;
        }
        Type typeExtractActualType = extractActualType(type);
        if (typeExtractActualType != type && (writeObject = this.writers.get(typeExtractActualType)) != null) {
            this.writers.putIfAbsent(type, writeObject);
            return writeObject;
        }
        boolean z = typeExtractActualType instanceof Class;
        if (z && JsonObject.class.isAssignableFrom((Class) typeExtractActualType)) {
            this.writers.putIfAbsent(type, this.OBJECT_WRITER);
            return this.OBJECT_WRITER;
        }
        JsonWriter.WriteObject<?> writeObject3 = (JsonWriter.WriteObject) lookupFromFactories(type, typeExtractActualType, this.writerFactories, this.writers);
        if (writeObject3 != null) {
            return writeObject3;
        }
        if (!z) {
            return null;
        }
        Class<?> cls = this.writerMap.get(typeExtractActualType);
        if (cls != null) {
            return this.writers.get(cls);
        }
        Class<?> cls2 = (Class) typeExtractActualType;
        ArrayList<Class<?>> arrayList = new ArrayList();
        findAllSignatures(cls2, arrayList);
        for (Class<?> cls3 : arrayList) {
            JsonWriter.WriteObject<?> writeObject4 = this.writers.get(cls3);
            if (writeObject4 == null) {
                writeObject4 = (JsonWriter.WriteObject) lookupFromFactories(type, cls3, this.writerFactories, this.writers);
            }
            if (writeObject4 != null) {
                this.writerMap.putIfAbsent(cls2, cls3);
                return writeObject4;
            }
        }
        return null;
    }

    private static Type extractActualType(Type type) {
        if (!(type instanceof WildcardType)) {
            return type;
        }
        WildcardType wildcardType = (WildcardType) type;
        return (wildcardType.getUpperBounds().length == 1 && wildcardType.getLowerBounds().length == 0) ? wildcardType.getUpperBounds()[0] : type;
    }

    private <T> void checkExternal(Type type, ConcurrentMap<Type, T> concurrentMap) {
        Type typeExtractActualType;
        if (type instanceof Class) {
            this.externalConverterAnalyzer.tryFindConverter((Class) type, this);
            return;
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            this.externalConverterAnalyzer.tryFindConverter((Class) parameterizedType.getRawType(), this);
            for (Type type2 : parameterizedType.getActualTypeArguments()) {
                if (!concurrentMap.containsKey(type2) && (typeExtractActualType = extractActualType(type2)) != type2 && !concurrentMap.containsKey(typeExtractActualType)) {
                    checkExternal(typeExtractActualType, concurrentMap);
                }
            }
        }
    }

    @Nullable
    private <T> T lookupFromFactories(Type type, Type type2, List<ConverterFactory<T>> list, ConcurrentMap<Type, T> concurrentMap) {
        if (type2 instanceof Class) {
            this.externalConverterAnalyzer.tryFindConverter((Class) type2, this);
            T t = concurrentMap.get(type2);
            if (t != null) {
                return t;
            }
        } else if (type2 instanceof ParameterizedType) {
            checkExternal(type2, concurrentMap);
        }
        Iterator<ConverterFactory<T>> it = list.iterator();
        while (it.hasNext()) {
            T tTryCreate = it.next().tryCreate(type2, this);
            if (tTryCreate != null) {
                concurrentMap.putIfAbsent(type, tTryCreate);
                return tTryCreate;
            }
        }
        return null;
    }

    @Nullable
    public JsonReader.ReadObject<?> tryFindReader(Type type) {
        JsonReader.ReadJsonObject<JsonObject> objectReader;
        JsonReader.ReadObject<?> readObject;
        JsonReader.ReadObject<?> readObject2 = this.readers.get(type);
        if (readObject2 != null) {
            return readObject2;
        }
        Type typeExtractActualType = extractActualType(type);
        if (typeExtractActualType != type && (readObject = this.readers.get(typeExtractActualType)) != null) {
            this.readers.putIfAbsent(type, readObject);
            return readObject;
        }
        if (typeExtractActualType instanceof Class) {
            Class<?> cls = (Class) typeExtractActualType;
            if (JsonObject.class.isAssignableFrom(cls) && (objectReader = getObjectReader(cls)) != null) {
                JsonReader.ReadObject readObjectConvertToReader = convertToReader(objectReader);
                this.readers.putIfAbsent(type, readObjectConvertToReader);
                return readObjectConvertToReader;
            }
        }
        return (JsonReader.ReadObject) lookupFromFactories(type, typeExtractActualType, this.readerFactories, this.readers);
    }

    @Nullable
    public <T> JsonWriter.WriteObject<T> tryFindWriter(Class<T> cls) {
        return (JsonWriter.WriteObject<T>) tryFindWriter((Type) cls);
    }

    @Nullable
    public <T> JsonReader.ReadObject<T> tryFindReader(Class<T> cls) {
        return (JsonReader.ReadObject<T>) tryFindReader((Type) cls);
    }

    private static void findAllSignatures(Class<?> cls, ArrayList<Class<?>> arrayList) {
        if (arrayList.contains(cls)) {
            return;
        }
        arrayList.add(cls);
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass != null && superclass != Object.class) {
            findAllSignatures(superclass, arrayList);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            findAllSignatures(cls2, arrayList);
        }
    }

    @Nullable
    private JsonReader.ReadJsonObject<JsonObject> probeForObjectReader(Class<?> cls, Object obj) {
        Object objInvoke;
        try {
            objInvoke = cls.getField("JSON_READER").get(obj);
        } catch (Exception unused) {
            try {
                try {
                    objInvoke = cls.getMethod("JSON_READER", new Class[0]).invoke(obj, new Object[0]);
                } catch (Exception unused2) {
                    objInvoke = cls.getMethod("getJSON_READER", new Class[0]).invoke(obj, new Object[0]);
                }
            } catch (Exception unused3) {
                return null;
            }
        }
        if (objInvoke instanceof JsonReader.ReadJsonObject) {
            return (JsonReader.ReadJsonObject) objInvoke;
        }
        return null;
    }

    @Nullable
    protected final JsonReader.ReadJsonObject<JsonObject> getObjectReader(Class<?> cls) {
        try {
            JsonReader.ReadJsonObject<JsonObject> readJsonObjectProbeForObjectReader = this.objectReaders.get(cls);
            if (readJsonObjectProbeForObjectReader == null) {
                readJsonObjectProbeForObjectReader = probeForObjectReader(cls, null);
                if (readJsonObjectProbeForObjectReader == null) {
                    try {
                        Object obj = cls.getField("Companion").get(null);
                        readJsonObjectProbeForObjectReader = probeForObjectReader(obj.getClass(), obj);
                    } catch (Exception unused) {
                        return null;
                    }
                }
                if (readJsonObjectProbeForObjectReader != null) {
                    this.objectReaders.putIfAbsent(cls, readJsonObjectProbeForObjectReader);
                }
            }
            return readJsonObjectProbeForObjectReader;
        } catch (Exception unused2) {
            return null;
        }
    }

    public void serializeMap(Map<String, Object> map, JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeByte((byte) 123);
        int size = map.size();
        if (size > 0) {
            Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
            Map.Entry<String, Object> next = it.next();
            jsonWriter.writeString(next.getKey());
            jsonWriter.writeByte((byte) 58);
            serialize(jsonWriter, next.getValue());
            for (int i = 1; i < size; i++) {
                jsonWriter.writeByte((byte) 44);
                Map.Entry<String, Object> next2 = it.next();
                jsonWriter.writeString(next2.getKey());
                jsonWriter.writeByte((byte) 58);
                serialize(jsonWriter, next2.getValue());
            }
        }
        jsonWriter.writeByte((byte) 125);
    }

    private static Object convertResultToArray(Class<?> cls, List<?> list) {
        int i = 0;
        if (cls.isPrimitive()) {
            if (Boolean.TYPE.equals(cls)) {
                boolean[] zArr = new boolean[list.size()];
                while (i < list.size()) {
                    zArr[i] = ((Boolean) list.get(i)).booleanValue();
                    i++;
                }
                return zArr;
            }
            if (Integer.TYPE.equals(cls)) {
                int[] iArr = new int[list.size()];
                while (i < list.size()) {
                    iArr[i] = ((Integer) list.get(i)).intValue();
                    i++;
                }
                return iArr;
            }
            if (Long.TYPE.equals(cls)) {
                long[] jArr = new long[list.size()];
                while (i < list.size()) {
                    jArr[i] = ((Long) list.get(i)).longValue();
                    i++;
                }
                return jArr;
            }
            if (Short.TYPE.equals(cls)) {
                short[] sArr = new short[list.size()];
                while (i < list.size()) {
                    sArr[i] = ((Short) list.get(i)).shortValue();
                    i++;
                }
                return sArr;
            }
            if (Byte.TYPE.equals(cls)) {
                byte[] bArr = new byte[list.size()];
                while (i < list.size()) {
                    bArr[i] = ((Byte) list.get(i)).byteValue();
                    i++;
                }
                return bArr;
            }
            if (Float.TYPE.equals(cls)) {
                float[] fArr = new float[list.size()];
                while (i < list.size()) {
                    fArr[i] = ((Float) list.get(i)).floatValue();
                    i++;
                }
                return fArr;
            }
            if (Double.TYPE.equals(cls)) {
                double[] dArr = new double[list.size()];
                while (i < list.size()) {
                    dArr[i] = ((Double) list.get(i)).doubleValue();
                    i++;
                }
                return dArr;
            }
            if (Character.TYPE.equals(cls)) {
                char[] cArr = new char[list.size()];
                while (i < list.size()) {
                    cArr[i] = ((Character) list.get(i)).charValue();
                    i++;
                }
                return cArr;
            }
        }
        return list.toArray((Object[]) Array.newInstance(cls, 0));
    }

    protected IOException createErrorMessage(Class<?> cls) {
        ArrayList<Class> arrayList = new ArrayList();
        findAllSignatures(cls, arrayList);
        for (Class cls2 : arrayList) {
            if (this.readers.containsKey(cls2)) {
                if (cls2.equals(cls)) {
                    return new IOException("Reader for provided type: " + cls + " is disabled and fallback serialization is not registered (converter is registered as null).\nTry initializing system with custom fallback or don't register null for " + cls);
                }
                return new IOException("Unable to find reader for provided type: " + cls + " and fallback serialization is not registered.\nFound reader for: " + cls2 + " so try deserializing into that instead?\nAlternatively, try initializing system with custom fallback or register specified type using registerReader into " + getClass());
            }
        }
        return new IOException("Unable to find reader for provided type: " + cls + " and fallback serialization is not registered.\nTry initializing DslJson with custom fallback in case of unsupported objects or register specified type using registerReader into " + getClass());
    }

    @Nullable
    public <TResult> TResult deserialize(Class<TResult> cls, InputStream inputStream) throws IOException {
        if (cls == null) {
            throw new IllegalArgumentException("manifest can't be null");
        }
        if (inputStream == null) {
            throw new IllegalArgumentException("stream can't be null");
        }
        JsonReader<TContext> jsonReaderProcess = this.localReader.get().process(inputStream);
        try {
            return (TResult) deserialize(cls, jsonReaderProcess, inputStream);
        } finally {
            jsonReaderProcess.reset();
        }
    }

    @Nullable
    protected <TResult> TResult deserialize(Class<TResult> cls, JsonReader jsonReader, InputStream inputStream) throws IOException {
        JsonReader.ReadJsonObject<JsonObject> objectReader;
        jsonReader.getNextToken();
        JsonReader.ReadObject<T> readObjectTryFindReader = tryFindReader((Class) cls);
        if (readObjectTryFindReader != 0) {
            return (TResult) readObjectTryFindReader.read(jsonReader);
        }
        if (cls.isArray()) {
            if (jsonReader.wasNull()) {
                return null;
            }
            if (jsonReader.last() != 91) {
                throw jsonReader.newParseError("Expecting '[' for array start");
            }
            Class componentType = cls.getComponentType();
            if (jsonReader.getNextToken() == 93) {
                return (TResult) Array.newInstance((Class<?>) componentType, 0);
            }
            if (JsonObject.class.isAssignableFrom(componentType) && (objectReader = getObjectReader(componentType)) != null) {
                return (TResult) convertResultToArray(componentType, jsonReader.deserializeNullableCollection(objectReader));
            }
            Object objTryFindReader = tryFindReader(componentType);
            if (objTryFindReader != null) {
                return (TResult) convertResultToArray(componentType, jsonReader.deserializeNullableCollection((JsonReader.ReadObject) objTryFindReader));
            }
        }
        Fallback<TContext> fallback = this.fallback;
        if (fallback != null) {
            return (TResult) fallback.deserialize(this.context, cls, new RereadStream(jsonReader.buffer, inputStream));
        }
        throw createErrorMessage(cls);
    }

    static class RereadStream extends InputStream {
        private final byte[] buffer;
        private int position;
        private final InputStream stream;
        private boolean usingBuffer = true;

        RereadStream(byte[] bArr, InputStream inputStream) {
            this.buffer = bArr;
            this.stream = inputStream;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            if (this.usingBuffer) {
                int i = this.position;
                byte[] bArr = this.buffer;
                if (i < bArr.length) {
                    this.position = i + 1;
                    return bArr[i];
                }
                this.usingBuffer = false;
            }
            return this.stream.read();
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr) throws IOException {
            if (this.usingBuffer) {
                return super.read(bArr);
            }
            return this.stream.read(bArr);
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            if (this.usingBuffer) {
                return super.read(bArr, i, i2);
            }
            return this.stream.read(bArr, i, i2);
        }
    }

    private <T extends JsonObject> JsonReader.ReadObject<T> convertToReader(final JsonReader.ReadJsonObject<T> readJsonObject) {
        return (JsonReader.ReadObject<T>) new JsonReader.ReadObject<T>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.DslJson.6
            /* JADX WARN: Incorrect return type in method signature: (Lcom/bugsnag/android/repackaged/dslplatform/json/JsonReader;)TT; */
            @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonReader.ReadObject
            public JsonObject read(JsonReader jsonReader) throws IOException {
                if (jsonReader.wasNull()) {
                    return null;
                }
                if (jsonReader.last() != 123) {
                    throw jsonReader.newParseError("Expecting '{' for object start");
                }
                jsonReader.getNextToken();
                return readJsonObject.deserialize(jsonReader);
            }
        };
    }

    @Deprecated
    public <T extends JsonObject> void serialize(JsonWriter jsonWriter, @Nullable T[] tArr) {
        if (tArr == null) {
            jsonWriter.writeNull();
            return;
        }
        jsonWriter.writeByte((byte) 91);
        if (tArr.length != 0) {
            T t = tArr[0];
            if (t != null) {
                t.serialize(jsonWriter, this.omitDefaults);
            } else {
                jsonWriter.writeNull();
            }
            for (int i = 1; i < tArr.length; i++) {
                jsonWriter.writeByte((byte) 44);
                T t2 = tArr[i];
                if (t2 != null) {
                    t2.serialize(jsonWriter, this.omitDefaults);
                } else {
                    jsonWriter.writeNull();
                }
            }
        }
        jsonWriter.writeByte((byte) 93);
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x00a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean serialize(com.bugsnag.android.repackaged.dslplatform.json.JsonWriter r12, java.lang.reflect.Type r13, @androidx.annotation.Nullable java.lang.Object r14) {
        /*
            Method dump skipped, instruction units count: 343
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bugsnag.android.repackaged.dslplatform.json.DslJson.serialize(com.bugsnag.android.repackaged.dslplatform.json.JsonWriter, java.lang.reflect.Type, java.lang.Object):boolean");
    }

    public final void serialize(@Nullable Object obj, OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("stream can't be null");
        }
        if (obj == null) {
            outputStream.write(NULL);
            return;
        }
        JsonWriter jsonWriter = this.localWriter.get();
        jsonWriter.reset(outputStream);
        Class<?> cls = obj.getClass();
        if (!serialize(jsonWriter, cls, obj)) {
            Fallback<TContext> fallback = this.fallback;
            if (fallback == null) {
                throw new ConfigurationException("Unable to serialize provided object. Failed to find serializer for: " + cls);
            }
            fallback.serialize(obj, outputStream);
            return;
        }
        jsonWriter.flush();
        jsonWriter.reset(null);
    }

    public final void serialize(JsonWriter jsonWriter, @Nullable Object obj) throws IOException {
        if (jsonWriter == null) {
            throw new IllegalArgumentException("writer can't be null");
        }
        if (obj == null) {
            jsonWriter.writeNull();
            return;
        }
        Class<?> cls = obj.getClass();
        if (serialize(jsonWriter, cls, obj)) {
            return;
        }
        if (this.fallback == null) {
            throw new ConfigurationException("Unable to serialize provided object. Failed to find serializer for: " + cls);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.fallback.serialize(obj, byteArrayOutputStream);
        jsonWriter.writeAscii(byteArrayOutputStream.toByteArray());
    }
}
