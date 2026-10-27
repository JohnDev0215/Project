package kotlinx.serialization.json.internal;

import com.amazon.identity.auth.device.utils.AccountConstants;
import java.lang.annotation.Annotation;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.SealedClassSerializer;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.internal.AbstractPolymorphicSerializer;
import kotlinx.serialization.internal.JsonInternalDependenciesKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonClassDiscriminator;
import kotlinx.serialization.json.JsonDecoder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;

/* JADX INFO: compiled from: Polymorphic.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a*\u0010\u0007\u001a\u00020\u00062\n\u0010\u0001\u001a\u0006\u0012\u0002\b\u00030\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u001a\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0000\u001a'\u0010\u000f\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u000b*\u00020\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u001a\u0010\u0015\u001a\u00020\u00142\b\u0010\u0011\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0013\u001a\u00020\u0012H\u0001\u001a\u0014\u0010\u0005\u001a\u00020\u0004*\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0017H\u0000¨\u0006\u0019"}, d2 = {"Lkotlinx/serialization/SerializationStrategy;", "serializer", "", "actualSerializer", "", "classDiscriminator", "", "validateIfSealed", "Lkotlinx/serialization/descriptors/SerialKind;", "kind", "checkKind", "T", "Lkotlinx/serialization/json/JsonDecoder;", "Lkotlinx/serialization/DeserializationStrategy;", "deserializer", "decodeSerializableValuePolymorphic", "(Lkotlinx/serialization/json/JsonDecoder;Lkotlinx/serialization/DeserializationStrategy;)Ljava/lang/Object;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lkotlinx/serialization/json/JsonObject;", "jsonTree", "", "throwSerializerNotFound", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "Lkotlinx/serialization/json/Json;", "json", "kotlinx-serialization-json"}, k = 2, mv = {1, 7, 1})
public final class PolymorphicKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void validateIfSealed(SerializationStrategy<?> serializationStrategy, SerializationStrategy<Object> serializationStrategy2, String str) {
        if ((serializationStrategy instanceof SealedClassSerializer) && JsonInternalDependenciesKt.jsonCachedSerialNames(serializationStrategy2.getDescriptor()).contains(str)) {
            String serialName = serializationStrategy.getDescriptor().getSerialName();
            throw new IllegalStateException(("Sealed class '" + serializationStrategy2.getDescriptor().getSerialName() + "' cannot be serialized as base class '" + serialName + "' because it has property name that conflicts with JSON class discriminator '" + str + "'. You can either change class discriminator in JsonConfiguration, rename property with @SerialName annotation or fall back to array polymorphism").toString());
        }
    }

    public static final void checkKind(SerialKind kind) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        if (kind instanceof SerialKind.ENUM) {
            throw new IllegalStateException("Enums cannot be serialized polymorphically with 'type' parameter. You can use 'JsonBuilder.useArrayPolymorphism' instead".toString());
        }
        if (kind instanceof PrimitiveKind) {
            throw new IllegalStateException("Primitives cannot be serialized polymorphically with 'type' parameter. You can use 'JsonBuilder.useArrayPolymorphism' instead".toString());
        }
        if (kind instanceof PolymorphicKind) {
            throw new IllegalStateException("Actual serializer for polymorphic cannot be polymorphic itself".toString());
        }
    }

    public static final <T> T decodeSerializableValuePolymorphic(JsonDecoder jsonDecoder, DeserializationStrategy<T> deserializer) {
        JsonPrimitive jsonPrimitive;
        Intrinsics.checkNotNullParameter(jsonDecoder, "<this>");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        if (!(deserializer instanceof AbstractPolymorphicSerializer) || jsonDecoder.getJson().getConfiguration().getUseArrayPolymorphism()) {
            return deserializer.deserialize(jsonDecoder);
        }
        String strClassDiscriminator = classDiscriminator(deserializer.getDescriptor(), jsonDecoder.getJson());
        JsonElement jsonElementDecodeJsonElement = jsonDecoder.decodeJsonElement();
        SerialDescriptor descriptor = deserializer.getDescriptor();
        if (jsonElementDecodeJsonElement instanceof JsonObject) {
            JsonObject jsonObject = (JsonObject) jsonElementDecodeJsonElement;
            JsonElement jsonElement = (JsonElement) jsonObject.get((Object) strClassDiscriminator);
            String content = (jsonElement == null || (jsonPrimitive = JsonElementKt.getJsonPrimitive(jsonElement)) == null) ? null : jsonPrimitive.getContent();
            DeserializationStrategy<? extends T> deserializationStrategyFindPolymorphicSerializerOrNull = ((AbstractPolymorphicSerializer) deserializer).findPolymorphicSerializerOrNull(jsonDecoder, content);
            if (deserializationStrategyFindPolymorphicSerializerOrNull == null) {
                throwSerializerNotFound(content, jsonObject);
                throw new KotlinNothingValueException();
            }
            return (T) TreeJsonDecoderKt.readPolymorphicJson(jsonDecoder.getJson(), strClassDiscriminator, jsonObject, deserializationStrategyFindPolymorphicSerializerOrNull);
        }
        throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonObject.class) + " as the serialized body of " + descriptor.getSerialName() + ", but had " + Reflection.getOrCreateKotlinClass(jsonElementDecodeJsonElement.getClass()));
    }

    @JvmName(name = "throwSerializerNotFound")
    public static final Void throwSerializerNotFound(String str, JsonObject jsonTree) {
        String str2;
        Intrinsics.checkNotNullParameter(jsonTree, "jsonTree");
        if (str == null) {
            str2 = "missing class discriminator ('null')";
        } else {
            str2 = "class discriminator '" + str + '\'';
        }
        throw JsonExceptionsKt.JsonDecodingException(-1, "Polymorphic serializer was not found for " + str2, jsonTree.toString());
    }

    public static final String classDiscriminator(SerialDescriptor serialDescriptor, Json json) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        for (Annotation annotation : serialDescriptor.getAnnotations()) {
            if (annotation instanceof JsonClassDiscriminator) {
                return ((JsonClassDiscriminator) annotation).discriminator();
            }
        }
        return json.getConfiguration().getClassDiscriminator();
    }
}
