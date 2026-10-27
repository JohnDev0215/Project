package kotlinx.serialization.modules;

import kotlin.Metadata;
import kotlin.collections.MapsKt__MapsKt;

/* JADX INFO: compiled from: SerializersModule.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\" \u0010\u0001\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0001\u0010\u0002\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004*`\b\u0000\u0010\u000e\u001a\u0004\b\u0000\u0010\u0007\")\u0012\u0015\u0012\u0013\u0018\u00010\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u000e\u0012\f\u0012\u0006\b\u0001\u0012\u00028\u0000\u0018\u00010\r0\b2)\u0012\u0015\u0012\u0013\u0018\u00010\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u000e\u0012\f\u0012\u0006\b\u0001\u0012\u00028\u0000\u0018\u00010\r0\b*X\b\u0000\u0010\u0011\u001a\u0004\b\u0000\u0010\u0007\"%\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000f\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00100\b2%\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000f\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00100\b¨\u0006\u0012"}, d2 = {"Lkotlinx/serialization/modules/SerializersModule;", "EmptySerializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "getEmptySerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "getEmptySerializersModule$annotations", "()V", "Base", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "className", "Lkotlinx/serialization/DeserializationStrategy;", "PolymorphicDeserializerProvider", "value", "Lkotlinx/serialization/SerializationStrategy;", "PolymorphicSerializerProvider", "kotlinx-serialization-core"}, k = 2, mv = {1, 7, 1})
public final class SerializersModuleKt {
    private static final SerializersModule EmptySerializersModule = new SerialModuleImpl(MapsKt__MapsKt.emptyMap(), MapsKt__MapsKt.emptyMap(), MapsKt__MapsKt.emptyMap(), MapsKt__MapsKt.emptyMap(), MapsKt__MapsKt.emptyMap());

    public static final SerializersModule getEmptySerializersModule() {
        return EmptySerializersModule;
    }
}
