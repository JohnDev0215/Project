package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.module.kotlin.KotlinModule;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: Extensions.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000²\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u0006\u0010\u0000\u001a\u00020\u0001\u001a\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0006\b\u0000\u0010\u0006\u0018\u0001H\u0086\b\u001a!\u0010\u0007\u001a\u00020\b2\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\b\f\u001a!\u0010\r\u001a\u00020\u000e2\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\b\f\u001a5\u0010\u0010\u001a\u00020\u0011\"\n\b\u0000\u0010\u0006\u0018\u0001*\u00020\u0012*\u00020\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0016H\u0086\b\u001a5\u0010\u0017\u001a\u00020\u0011\"\n\b\u0000\u0010\u0006\u0018\u0001*\u00020\u0012*\u00020\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00142\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0019H\u0086\b\u001a\"\u0010\u001a\u001a\u0002H\u0006\"\u0006\b\u0000\u0010\u0006\u0018\u0001*\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0012H\u0086\b¢\u0006\u0002\u0010\u001c\u001a\"\u0010\u001d\u001a\u0002H\u0006\"\u0006\b\u0000\u0010\u0006\u0018\u0001*\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u001fH\u0086\b¢\u0006\u0002\u0010 \u001a\"\u0010\u001d\u001a\u0002H\u0006\"\u0006\b\u0000\u0010\u0006\u0018\u0001*\u00020\u00032\u0006\u0010!\u001a\u00020\"H\u0086\b¢\u0006\u0002\u0010#\u001a\"\u0010\u001d\u001a\u0002H\u0006\"\u0006\b\u0000\u0010\u0006\u0018\u0001*\u00020\u00032\u0006\u0010!\u001a\u00020$H\u0086\b¢\u0006\u0002\u0010%\u001a\"\u0010\u001d\u001a\u0002H\u0006\"\u0006\b\u0000\u0010\u0006\u0018\u0001*\u00020\u00032\u0006\u0010!\u001a\u00020&H\u0086\b¢\u0006\u0002\u0010'\u001a\"\u0010\u001d\u001a\u0002H\u0006\"\u0006\b\u0000\u0010\u0006\u0018\u0001*\u00020\u00032\u0006\u0010!\u001a\u00020(H\u0086\b¢\u0006\u0002\u0010)\u001a\"\u0010\u001d\u001a\u0002H\u0006\"\u0006\b\u0000\u0010\u0006\u0018\u0001*\u00020\u00032\u0006\u0010!\u001a\u00020*H\u0086\b¢\u0006\u0002\u0010+\u001a\"\u0010\u001d\u001a\u0002H\u0006\"\u0006\b\u0000\u0010\u0006\u0018\u0001*\u00020\u00032\u0006\u0010,\u001a\u00020-H\u0086\b¢\u0006\u0002\u0010.\u001a\"\u0010/\u001a\u0002H\u0006\"\u0006\b\u0000\u0010\u0006\u0018\u0001*\u0002002\u0006\u0010\u001e\u001a\u00020\u001fH\u0086\b¢\u0006\u0002\u00101\u001a#\u00102\u001a\b\u0012\u0004\u0012\u0002H\u000603\"\u0006\b\u0000\u0010\u0006\u0018\u0001*\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u001fH\u0086\b\u001a#\u00104\u001a\b\u0012\u0004\u0012\u0002H\u000605\"\u0006\b\u0000\u0010\u0006\u0018\u0001*\u0002002\u0006\u0010\u001e\u001a\u00020\u001fH\u0086\b\u001a\n\u00106\u001a\u00020\u0003*\u00020\u0003\u001a$\u00107\u001a\u0004\u0018\u0001H\u0006\"\u0006\b\u0000\u0010\u0006\u0018\u0001*\u00020\u00032\u0006\u00108\u001a\u000209H\u0086\b¢\u0006\u0002\u0010:\u001a$\u00107\u001a\u0004\u0018\u0001H\u0006\"\u0006\b\u0000\u0010\u0006\u0018\u0001*\u0002002\u0006\u00108\u001a\u000209H\u0086\b¢\u0006\u0002\u0010;\u001a&\u0010<\u001a\n >*\u0004\u0018\u00010=0=*\u00020=2\b\u0010?\u001a\u0004\u0018\u00010\u00122\u0006\u0010@\u001a\u00020AH\u0000\u001a&\u0010<\u001a\n >*\u0004\u0018\u00010=0=*\u00020=2\b\u0010?\u001a\u0004\u0018\u00010\u00122\u0006\u0010B\u001a\u00020-H\u0000¨\u0006C"}, d2 = {"jacksonMapperBuilder", "Lcom/fasterxml/jackson/databind/json/JsonMapper$Builder;", "jacksonObjectMapper", "Lcom/fasterxml/jackson/databind/ObjectMapper;", "jacksonTypeRef", "Lcom/fasterxml/jackson/core/type/TypeReference;", "T", "jsonMapper", "Lcom/fasterxml/jackson/databind/json/JsonMapper;", "initializer", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "kotlinModule", "Lcom/fasterxml/jackson/module/kotlin/KotlinModule;", "Lcom/fasterxml/jackson/module/kotlin/KotlinModule$Builder;", "addDeserializer", "Lcom/fasterxml/jackson/databind/module/SimpleModule;", "", "kClass", "Lkotlin/reflect/KClass;", "deserializer", "Lcom/fasterxml/jackson/databind/JsonDeserializer;", "addSerializer", "serializer", "Lcom/fasterxml/jackson/databind/JsonSerializer;", "convertValue", "from", "(Lcom/fasterxml/jackson/databind/ObjectMapper;Ljava/lang/Object;)Ljava/lang/Object;", "readValue", "jp", "Lcom/fasterxml/jackson/core/JsonParser;", "(Lcom/fasterxml/jackson/databind/ObjectMapper;Lcom/fasterxml/jackson/core/JsonParser;)Ljava/lang/Object;", "src", "Ljava/io/File;", "(Lcom/fasterxml/jackson/databind/ObjectMapper;Ljava/io/File;)Ljava/lang/Object;", "Ljava/io/InputStream;", "(Lcom/fasterxml/jackson/databind/ObjectMapper;Ljava/io/InputStream;)Ljava/lang/Object;", "Ljava/io/Reader;", "(Lcom/fasterxml/jackson/databind/ObjectMapper;Ljava/io/Reader;)Ljava/lang/Object;", "Ljava/net/URL;", "(Lcom/fasterxml/jackson/databind/ObjectMapper;Ljava/net/URL;)Ljava/lang/Object;", "", "(Lcom/fasterxml/jackson/databind/ObjectMapper;[B)Ljava/lang/Object;", "content", "", "(Lcom/fasterxml/jackson/databind/ObjectMapper;Ljava/lang/String;)Ljava/lang/Object;", "readValueTyped", "Lcom/fasterxml/jackson/databind/ObjectReader;", "(Lcom/fasterxml/jackson/databind/ObjectReader;Lcom/fasterxml/jackson/core/JsonParser;)Ljava/lang/Object;", "readValues", "Lcom/fasterxml/jackson/databind/MappingIterator;", "readValuesTyped", "", "registerKotlinModule", "treeToValue", "n", "Lcom/fasterxml/jackson/core/TreeNode;", "(Lcom/fasterxml/jackson/databind/ObjectMapper;Lcom/fasterxml/jackson/core/TreeNode;)Ljava/lang/Object;", "(Lcom/fasterxml/jackson/databind/ObjectReader;Lcom/fasterxml/jackson/core/TreeNode;)Ljava/lang/Object;", "wrapWithPath", "Lcom/fasterxml/jackson/databind/JsonMappingException;", "kotlin.jvm.PlatformType", "refFrom", "index", "", "refFieldName", "jackson-module-kotlin"}, k = 2, mv = {1, 4, 1})
public final class ExtensionsKt {
    public static /* synthetic */ KotlinModule kotlinModule$default(Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<KotlinModule.Builder, Unit>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt.kotlinModule.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KotlinModule.Builder builder) {
                    invoke2(builder);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KotlinModule.Builder receiver) {
                    Intrinsics.checkNotNullParameter(receiver, "$receiver");
                }
            };
        }
        return kotlinModule(function1);
    }

    public static final KotlinModule kotlinModule(Function1<? super KotlinModule.Builder, Unit> initializer) {
        Intrinsics.checkNotNullParameter(initializer, "initializer");
        KotlinModule.Builder builder = new KotlinModule.Builder();
        initializer.invoke(builder);
        return builder.build();
    }

    public static /* synthetic */ JsonMapper jsonMapper$default(Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<JsonMapper.Builder, Unit>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt.jsonMapper.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(JsonMapper.Builder builder) {
                    invoke2(builder);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(JsonMapper.Builder receiver) {
                    Intrinsics.checkNotNullParameter(receiver, "$receiver");
                }
            };
        }
        return jsonMapper(function1);
    }

    public static final JsonMapper jsonMapper(Function1<? super JsonMapper.Builder, Unit> initializer) {
        Intrinsics.checkNotNullParameter(initializer, "initializer");
        JsonMapper.Builder builder = JsonMapper.builder();
        Intrinsics.checkNotNullExpressionValue(builder, "builder");
        initializer.invoke(builder);
        JsonMapper jsonMapperBuild = builder.build();
        Intrinsics.checkNotNullExpressionValue(jsonMapperBuild, "builder.build()");
        return jsonMapperBuild;
    }

    public static final ObjectMapper jacksonObjectMapper() {
        return jsonMapper(new Function1<JsonMapper.Builder, Unit>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt.jacksonObjectMapper.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(JsonMapper.Builder builder) {
                invoke2(builder);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(JsonMapper.Builder receiver) {
                Intrinsics.checkNotNullParameter(receiver, "$receiver");
                receiver.addModule(ExtensionsKt.kotlinModule$default(null, 1, null));
            }
        });
    }

    public static final JsonMapper.Builder jacksonMapperBuilder() {
        JsonMapper.Builder builderAddModule = JsonMapper.builder().addModule(kotlinModule$default(null, 1, null));
        Intrinsics.checkNotNullExpressionValue(builderAddModule, "JsonMapper.builder().addModule(kotlinModule())");
        return builderAddModule;
    }

    public static final ObjectMapper registerKotlinModule(ObjectMapper registerKotlinModule) {
        Intrinsics.checkNotNullParameter(registerKotlinModule, "$this$registerKotlinModule");
        ObjectMapper objectMapperRegisterModule = registerKotlinModule.registerModule(kotlinModule$default(null, 1, null));
        Intrinsics.checkNotNullExpressionValue(objectMapperRegisterModule, "this.registerModule(kotlinModule())");
        return objectMapperRegisterModule;
    }

    public static final /* synthetic */ <T> T convertValue(ObjectMapper convertValue, Object from) {
        Intrinsics.checkNotNullParameter(convertValue, "$this$convertValue");
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.needClassReification();
        return (T) convertValue.convertValue(from, new TypeReference<T>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt$convertValue$$inlined$jacksonTypeRef$1
        });
    }

    public static final /* synthetic */ <T> TypeReference<T> jacksonTypeRef() {
        Intrinsics.needClassReification();
        return new TypeReference<T>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt.jacksonTypeRef.1
        };
    }

    public static final /* synthetic */ <T> T readValue(ObjectMapper readValue, JsonParser jp) {
        Intrinsics.checkNotNullParameter(readValue, "$this$readValue");
        Intrinsics.checkNotNullParameter(jp, "jp");
        Intrinsics.needClassReification();
        return (T) readValue.readValue(jp, new TypeReference<T>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt$readValue$$inlined$jacksonTypeRef$1
        });
    }

    public static final /* synthetic */ <T> T readValue(ObjectMapper readValue, File src) {
        Intrinsics.checkNotNullParameter(readValue, "$this$readValue");
        Intrinsics.checkNotNullParameter(src, "src");
        Intrinsics.needClassReification();
        return (T) readValue.readValue(src, new TypeReference<T>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt$readValue$$inlined$jacksonTypeRef$2
        });
    }

    public static final /* synthetic */ <T> T readValue(ObjectMapper readValue, InputStream src) {
        Intrinsics.checkNotNullParameter(readValue, "$this$readValue");
        Intrinsics.checkNotNullParameter(src, "src");
        Intrinsics.needClassReification();
        return (T) readValue.readValue(src, new TypeReference<T>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt$readValue$$inlined$jacksonTypeRef$6
        });
    }

    public static final /* synthetic */ <T> T readValue(ObjectMapper readValue, Reader src) {
        Intrinsics.checkNotNullParameter(readValue, "$this$readValue");
        Intrinsics.checkNotNullParameter(src, "src");
        Intrinsics.needClassReification();
        return (T) readValue.readValue(src, new TypeReference<T>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt$readValue$$inlined$jacksonTypeRef$5
        });
    }

    public static final /* synthetic */ <T> T readValue(ObjectMapper readValue, String content) {
        Intrinsics.checkNotNullParameter(readValue, "$this$readValue");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.needClassReification();
        return (T) readValue.readValue(content, new TypeReference<T>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt$readValue$$inlined$jacksonTypeRef$4
        });
    }

    public static final /* synthetic */ <T> T readValue(ObjectMapper readValue, URL src) {
        Intrinsics.checkNotNullParameter(readValue, "$this$readValue");
        Intrinsics.checkNotNullParameter(src, "src");
        Intrinsics.needClassReification();
        return (T) readValue.readValue(src, new TypeReference<T>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt$readValue$$inlined$jacksonTypeRef$3
        });
    }

    public static final /* synthetic */ <T> T readValue(ObjectMapper readValue, byte[] src) {
        Intrinsics.checkNotNullParameter(readValue, "$this$readValue");
        Intrinsics.checkNotNullParameter(src, "src");
        Intrinsics.needClassReification();
        return (T) readValue.readValue(src, new TypeReference<T>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt$readValue$$inlined$jacksonTypeRef$7
        });
    }

    public static final /* synthetic */ <T> T readValueTyped(ObjectReader readValueTyped, JsonParser jp) {
        Intrinsics.checkNotNullParameter(readValueTyped, "$this$readValueTyped");
        Intrinsics.checkNotNullParameter(jp, "jp");
        Intrinsics.needClassReification();
        return (T) readValueTyped.readValue(jp, new TypeReference<T>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt$readValueTyped$$inlined$jacksonTypeRef$1
        });
    }

    public static final /* synthetic */ <T> MappingIterator<T> readValues(ObjectMapper readValues, JsonParser jp) throws IOException {
        Intrinsics.checkNotNullParameter(readValues, "$this$readValues");
        Intrinsics.checkNotNullParameter(jp, "jp");
        Intrinsics.needClassReification();
        MappingIterator<T> values = readValues.readValues(jp, (TypeReference) new TypeReference<T>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt$readValues$$inlined$jacksonTypeRef$1
        });
        Intrinsics.checkNotNullExpressionValue(values, "readValues(jp, jacksonTypeRef<T>())");
        return values;
    }

    public static final /* synthetic */ <T> Iterator<T> readValuesTyped(ObjectReader readValuesTyped, JsonParser jp) throws IOException {
        Intrinsics.checkNotNullParameter(readValuesTyped, "$this$readValuesTyped");
        Intrinsics.checkNotNullParameter(jp, "jp");
        Intrinsics.needClassReification();
        Iterator<T> values = readValuesTyped.readValues(jp, new TypeReference<T>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt$readValuesTyped$$inlined$jacksonTypeRef$1
        });
        Intrinsics.checkNotNullExpressionValue(values, "readValues(jp, jacksonTypeRef<T>())");
        return values;
    }

    public static final /* synthetic */ <T> T treeToValue(ObjectMapper treeToValue, TreeNode n) {
        Intrinsics.checkNotNullParameter(treeToValue, "$this$treeToValue");
        Intrinsics.checkNotNullParameter(n, "n");
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T) treeToValue.treeToValue(n, Object.class);
    }

    public static final /* synthetic */ <T> T treeToValue(ObjectReader treeToValue, TreeNode n) {
        Intrinsics.checkNotNullParameter(treeToValue, "$this$treeToValue");
        Intrinsics.checkNotNullParameter(n, "n");
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T) treeToValue.treeToValue(n, Object.class);
    }

    public static final JsonMappingException wrapWithPath(JsonMappingException wrapWithPath, Object obj, String refFieldName) {
        Intrinsics.checkNotNullParameter(wrapWithPath, "$this$wrapWithPath");
        Intrinsics.checkNotNullParameter(refFieldName, "refFieldName");
        return JsonMappingException.wrapWithPath(wrapWithPath, obj, refFieldName);
    }

    public static final JsonMappingException wrapWithPath(JsonMappingException wrapWithPath, Object obj, int i) {
        Intrinsics.checkNotNullParameter(wrapWithPath, "$this$wrapWithPath");
        return JsonMappingException.wrapWithPath(wrapWithPath, obj, i);
    }

    public static final /* synthetic */ <T> SimpleModule addSerializer(SimpleModule addSerializer, KClass<T> kClass, JsonSerializer<T> serializer) {
        Intrinsics.checkNotNullParameter(addSerializer, "$this$addSerializer");
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        addSerializer.addSerializer(JvmClassMappingKt.getJavaClass((KClass) kClass), serializer);
        addSerializer.addSerializer(JvmClassMappingKt.getJavaObjectType(kClass), serializer);
        return addSerializer;
    }

    public static final /* synthetic */ <T> SimpleModule addDeserializer(SimpleModule addDeserializer, KClass<T> kClass, JsonDeserializer<T> deserializer) {
        Intrinsics.checkNotNullParameter(addDeserializer, "$this$addDeserializer");
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        addDeserializer.addDeserializer(JvmClassMappingKt.getJavaClass((KClass) kClass), deserializer);
        addDeserializer.addDeserializer(JvmClassMappingKt.getJavaObjectType(kClass), deserializer);
        return addDeserializer;
    }
}
