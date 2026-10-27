package kotlinx.serialization;

import java.util.List;
import kotlin.Metadata;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"kotlinx/serialization/SerializersKt__SerializersKt"}, d2 = {}, k = 4, mv = {1, 7, 1})
public final class SerializersKt {
    public static final KSerializer<? extends Object> parametrizedSerializerOrNull(KClass<Object> kClass, List<? extends KType> list, List<? extends KSerializer<Object>> list2) {
        return SerializersKt__SerializersKt.parametrizedSerializerOrNull(kClass, list, list2);
    }

    public static final KSerializer<Object> serializer(KType kType) {
        return SerializersKt__SerializersKt.serializer(kType);
    }

    public static final KSerializer<Object> serializer(SerializersModule serializersModule, KType kType) {
        return SerializersKt__SerializersKt.serializer(serializersModule, kType);
    }

    public static final <T> KSerializer<T> serializerOrNull(KClass<T> kClass) {
        return SerializersKt__SerializersKt.serializerOrNull(kClass);
    }

    public static final KSerializer<Object> serializerOrNull(SerializersModule serializersModule, KType kType) {
        return SerializersKt__SerializersKt.serializerOrNull(serializersModule, kType);
    }

    public static final List<KSerializer<Object>> serializersForParameters(SerializersModule serializersModule, List<? extends KType> list, boolean z) {
        return SerializersKt__SerializersKt.serializersForParameters(serializersModule, list, z);
    }
}
