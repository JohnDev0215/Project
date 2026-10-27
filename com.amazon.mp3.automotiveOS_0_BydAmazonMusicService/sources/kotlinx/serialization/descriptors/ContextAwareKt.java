package kotlinx.serialization.descriptors;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.internal.SerialDescriptorForNullable;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: ContextAware.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0007\u001a\u0018\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0000\"$\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0004*\u00020\u00018FX\u0087\u0004¢\u0006\f\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"Lkotlinx/serialization/modules/SerializersModule;", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "descriptor", "getContextualDescriptor", "Lkotlin/reflect/KClass;", "context", "withContext", "getCapturedKClass", "(Lkotlinx/serialization/descriptors/SerialDescriptor;)Lkotlin/reflect/KClass;", "getCapturedKClass$annotations", "(Lkotlinx/serialization/descriptors/SerialDescriptor;)V", "capturedKClass", "kotlinx-serialization-core"}, k = 2, mv = {1, 7, 1})
public final class ContextAwareKt {
    public static final KClass<?> getCapturedKClass(SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        if (serialDescriptor instanceof ContextDescriptor) {
            return ((ContextDescriptor) serialDescriptor).kClass;
        }
        if (serialDescriptor instanceof SerialDescriptorForNullable) {
            return getCapturedKClass(((SerialDescriptorForNullable) serialDescriptor).getOriginal());
        }
        return null;
    }

    public static final SerialDescriptor getContextualDescriptor(SerializersModule serializersModule, SerialDescriptor descriptor) {
        KSerializer contextual$default;
        Intrinsics.checkNotNullParameter(serializersModule, "<this>");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        KClass<?> capturedKClass = getCapturedKClass(descriptor);
        if (capturedKClass == null || (contextual$default = SerializersModule.getContextual$default(serializersModule, capturedKClass, null, 2, null)) == null) {
            return null;
        }
        return contextual$default.getDescriptor();
    }

    public static final SerialDescriptor withContext(SerialDescriptor serialDescriptor, KClass<?> context) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        return new ContextDescriptor(serialDescriptor, context);
    }
}
