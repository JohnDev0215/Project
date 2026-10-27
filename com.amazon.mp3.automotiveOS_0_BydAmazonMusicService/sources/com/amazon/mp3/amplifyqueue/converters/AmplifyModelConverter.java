package com.amazon.mp3.amplifyqueue.converters;

import androidx.annotation.WorkerThread;
import androidx.collection.LruCache;
import com.amazon.mp3.amplifyqueue.model.DeviceFamilyEnum;
import com.amazon.mp3.amplifyqueue.model.MetricsSpecEnum;
import com.amazon.mp3.amplifyqueue.model.PlayModeEnum;
import com.amazon.mp3.amplifyqueue.model.PlaybackAssetSourceTypeEnum;
import com.amazon.mp3.amplifyqueue.model.QueueEntityIdTypeEnum;
import com.amazon.mp3.amplifyqueue.model.QueueEntitySubtypeEnum;
import com.amazon.mp3.amplifyqueue.model.QueueSeedTypeEnum;
import com.amazon.mp3.amplifyqueue.model.ServiceTierEnum;
import com.amazon.mp3.amplifyqueue.model.SupportedFeatureEnum;
import com.amazon.mp3.amplifyqueue.model.UpsellTypeEnum;
import com.amplifyframework.core.model.temporal.Temporal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AmplifyModelConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001H\u0002J/\u0010\b\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u00052\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H\u0007¢\u0006\u0004\b\b\u0010\tJ/\u0010\n\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u00052\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H\u0007¢\u0006\u0004\b\n\u0010\tJ/\u0010\f\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\f\u0010\rR(\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000f0\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00038\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0016\u001a\n \u0015*\u0004\u0018\u00010\u00140\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\n \u0015*\u0004\u0018\u00010\u00140\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0017¨\u0006\u001b"}, d2 = {"Lcom/amazon/mp3/amplifyqueue/converters/AmplifyModelConverter;", "", "obj", "", "generateKey", "T", "Ljava/lang/Class;", "targetClass", "convertFromAmplify", "(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;", "convertToAmplify", "defaultValue", "getDummyEnumValue", "(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Object;", "Landroidx/collection/LruCache;", "Ljava/lang/ref/WeakReference;", "cache", "Landroidx/collection/LruCache;", "DATETIME", "Ljava/lang/String;", "Lcom/google/gson/Gson;", "kotlin.jvm.PlatformType", "gsonFromAmplify", "Lcom/google/gson/Gson;", "gsonToAmplify", "<init>", "()V", "amplifyqueue_release"}, k = 1, mv = {1, 6, 0})
public final class AmplifyModelConverter {
    public static final AmplifyModelConverter INSTANCE = new AmplifyModelConverter();
    private static LruCache<String, WeakReference<Object>> cache = new LruCache<>(1000);
    private static final String DATETIME = "dateTime";
    private static final Gson gsonFromAmplify = new GsonBuilder().registerTypeAdapter(Temporal.DateTime.class, new JsonSerializer<Temporal.DateTime>() { // from class: com.amazon.mp3.amplifyqueue.converters.AmplifyModelConverter$gsonFromAmplify$1
        @Override // com.google.gson.JsonSerializer
        public JsonElement serialize(Temporal.DateTime src, Type typeOfSrc, JsonSerializationContext context) {
            Date date;
            JsonObject jsonObject = new JsonObject();
            String str = AmplifyModelConverter.DATETIME;
            Long lValueOf = null;
            if (src != null && (date = src.toDate()) != null) {
                lValueOf = Long.valueOf(date.getTime());
            }
            jsonObject.addProperty(str, lValueOf);
            return jsonObject;
        }
    }).registerTypeAdapter(Temporal.Timestamp.class, new JsonSerializer<Temporal.Timestamp>() { // from class: com.amazon.mp3.amplifyqueue.converters.AmplifyModelConverter$gsonFromAmplify$2
        @Override // com.google.gson.JsonSerializer
        public JsonElement serialize(Temporal.Timestamp src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(AmplifyModelConverter.DATETIME, src == null ? null : Long.valueOf(src.getSecondsSinceEpoch()));
            return jsonObject;
        }
    }).registerTypeAdapter(Long.TYPE, new JsonDeserializer<Long>() { // from class: com.amazon.mp3.amplifyqueue.converters.AmplifyModelConverter$gsonFromAmplify$3
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.JsonDeserializer
        public Long deserialize(JsonElement p0, Type p1, JsonDeserializationContext p2) {
            JsonObject asJsonObject;
            JsonElement jsonElement;
            if (p0 == null || (asJsonObject = p0.getAsJsonObject()) == null || (jsonElement = asJsonObject.get(AmplifyModelConverter.DATETIME)) == null) {
                return null;
            }
            return Long.valueOf(jsonElement.getAsLong());
        }
    }).create();
    private static final Gson gsonToAmplify = new GsonBuilder().registerTypeAdapter(Temporal.DateTime.class, new JsonDeserializer<Temporal.DateTime>() { // from class: com.amazon.mp3.amplifyqueue.converters.AmplifyModelConverter$gsonToAmplify$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.JsonDeserializer
        public Temporal.DateTime deserialize(JsonElement p0, Type p1, JsonDeserializationContext p2) {
            return new Temporal.DateTime(new Date(Long.parseLong(String.valueOf(p0))), 0);
        }
    }).registerTypeAdapter(Temporal.Timestamp.class, new JsonDeserializer<Temporal.Timestamp>() { // from class: com.amazon.mp3.amplifyqueue.converters.AmplifyModelConverter$gsonToAmplify$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.JsonDeserializer
        public Temporal.Timestamp deserialize(JsonElement p0, Type p1, JsonDeserializationContext p2) {
            return new Temporal.Timestamp(new Date(Long.parseLong(String.valueOf(p0))));
        }
    }).create();

    private AmplifyModelConverter() {
    }

    @WorkerThread
    public final <T> T convertFromAmplify(Object obj, Class<T> targetClass) {
        Intrinsics.checkNotNullParameter(targetClass, "targetClass");
        if (obj == null) {
            return null;
        }
        String strGenerateKey = generateKey(obj);
        if (cache.get(strGenerateKey) != null) {
            WeakReference<Object> weakReference = cache.get(strGenerateKey);
            Intrinsics.checkNotNull(weakReference);
            if (weakReference.get() != null) {
                WeakReference<Object> weakReference2 = cache.get(strGenerateKey);
                Intrinsics.checkNotNull(weakReference2);
                return (T) weakReference2.get();
            }
        }
        Gson gson = gsonFromAmplify;
        T t = (T) gson.fromJson(gson.toJson(obj), (Class) targetClass);
        cache.put(strGenerateKey, new WeakReference<>(t));
        return t;
    }

    @WorkerThread
    public final <T> T convertToAmplify(Object obj, Class<T> targetClass) {
        Intrinsics.checkNotNullParameter(targetClass, "targetClass");
        if (obj == null) {
            return null;
        }
        String strGenerateKey = generateKey(obj);
        if (cache.get(strGenerateKey) != null) {
            WeakReference<Object> weakReference = cache.get(strGenerateKey);
            Intrinsics.checkNotNull(weakReference);
            if (weakReference.get() != null) {
                WeakReference<Object> weakReference2 = cache.get(strGenerateKey);
                Intrinsics.checkNotNull(weakReference2);
                return (T) weakReference2.get();
            }
        }
        Gson gson = gsonToAmplify;
        T t = (T) gson.fromJson(gson.toJson(obj), (Class) targetClass);
        cache.put(strGenerateKey, new WeakReference<>(t));
        return t;
    }

    private final String generateKey(Object obj) {
        return obj.getClass().getName() + '-' + obj.hashCode();
    }

    public static /* synthetic */ Object getDummyEnumValue$default(AmplifyModelConverter amplifyModelConverter, Class cls, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        return amplifyModelConverter.getDummyEnumValue(cls, str);
    }

    public final <T> T getDummyEnumValue(Class<T> targetClass, String defaultValue) {
        Enum enumValueOf;
        Intrinsics.checkNotNullParameter(targetClass, "targetClass");
        if (Intrinsics.areEqual(targetClass, SupportedFeatureEnum.class) || Intrinsics.areEqual(targetClass, PlayModeEnum.class)) {
            return null;
        }
        if (Intrinsics.areEqual(targetClass, PlaybackAssetSourceTypeEnum.class)) {
            return (T) PlaybackAssetSourceTypeEnum.URL;
        }
        if (Intrinsics.areEqual(targetClass, QueueEntityIdTypeEnum.class)) {
            return (T) QueueEntityIdTypeEnum.ASIN;
        }
        if (Intrinsics.areEqual(targetClass, QueueEntitySubtypeEnum.class)) {
            return null;
        }
        if (Intrinsics.areEqual(targetClass, QueueSeedTypeEnum.class)) {
            return (T) QueueSeedTypeEnum.QUEUE_ID_HISTORICAL_PLAYBACK;
        }
        if (Intrinsics.areEqual(targetClass, DeviceFamilyEnum.class)) {
            try {
                Intrinsics.checkNotNull(defaultValue);
                enumValueOf = DeviceFamilyEnum.valueOf(defaultValue);
            } catch (Exception unused) {
                enumValueOf = DeviceFamilyEnum.ALEXA;
            }
        } else if (Intrinsics.areEqual(targetClass, UpsellTypeEnum.class)) {
            try {
                Intrinsics.checkNotNull(defaultValue);
                enumValueOf = UpsellTypeEnum.valueOf(defaultValue);
            } catch (Exception unused2) {
                enumValueOf = UpsellTypeEnum.AMU_CATALOG_ACCESS;
            }
        } else if (Intrinsics.areEqual(targetClass, MetricsSpecEnum.class)) {
            try {
                Intrinsics.checkNotNull(defaultValue);
                enumValueOf = MetricsSpecEnum.valueOf(defaultValue);
            } catch (Exception unused3) {
                enumValueOf = MetricsSpecEnum.GENERIC;
            }
        } else {
            if (!Intrinsics.areEqual(targetClass, ServiceTierEnum.class)) {
                return null;
            }
            try {
                Intrinsics.checkNotNull(defaultValue);
                enumValueOf = ServiceTierEnum.valueOf(defaultValue);
            } catch (Exception unused4) {
                enumValueOf = ServiceTierEnum.PRIME;
            }
        }
        return (T) enumValueOf;
    }
}
