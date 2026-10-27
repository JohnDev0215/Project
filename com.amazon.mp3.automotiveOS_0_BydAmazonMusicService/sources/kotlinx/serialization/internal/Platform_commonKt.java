package kotlinx.serialization.internal;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;

/* JADX INFO: compiled from: Platform.common.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\u001a\u0012\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\u0000\u001a!\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00000\u0005*\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u0004H\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0010\u0010\n\u001a\u00020\t*\u0006\u0012\u0002\b\u00030\bH\u0000\u001a\u0012\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\b*\u00020\u000bH\u0000\" \u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00000\u00058\u0002X\u0083\u0004¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u0012\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lkotlinx/serialization/descriptors/SerialDescriptor;", "", "", "cachedSerialNames", "", "", "compactArray", "(Ljava/util/List;)[Lkotlinx/serialization/descriptors/SerialDescriptor;", "Lkotlin/reflect/KClass;", "", "serializerNotRegistered", "Lkotlin/reflect/KType;", "", "kclass", "EMPTY_DESCRIPTOR_ARRAY", "[Lkotlinx/serialization/descriptors/SerialDescriptor;", "getEMPTY_DESCRIPTOR_ARRAY$annotations", "()V", "kotlinx-serialization-core"}, k = 2, mv = {1, 7, 1})
public final class Platform_commonKt {
    private static final SerialDescriptor[] EMPTY_DESCRIPTOR_ARRAY = new SerialDescriptor[0];

    public static final Set<String> cachedSerialNames(SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        if (serialDescriptor instanceof CachedNames) {
            return ((CachedNames) serialDescriptor).getSerialNames();
        }
        HashSet hashSet = new HashSet(serialDescriptor.getElementsCount());
        int elementsCount = serialDescriptor.getElementsCount();
        for (int i = 0; i < elementsCount; i++) {
            hashSet.add(serialDescriptor.getElementName(i));
        }
        return hashSet;
    }

    public static final SerialDescriptor[] compactArray(List<? extends SerialDescriptor> list) {
        if (list == null || list.isEmpty()) {
            list = null;
        }
        if (list != null) {
            Object[] array = list.toArray(new SerialDescriptor[0]);
            Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            SerialDescriptor[] serialDescriptorArr = (SerialDescriptor[]) array;
            if (serialDescriptorArr != null) {
                return serialDescriptorArr;
            }
        }
        return EMPTY_DESCRIPTOR_ARRAY;
    }

    public static final Void serializerNotRegistered(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        throw new SerializationException("Serializer for class '" + kClass.getSimpleName() + "' is not found.\nMark the class as @Serializable or provide the serializer explicitly.");
    }

    public static final KClass<Object> kclass(KType kType) {
        Intrinsics.checkNotNullParameter(kType, "<this>");
        KClassifier classifier = kType.getClassifier();
        if (classifier instanceof KClass) {
            return (KClass) classifier;
        }
        if (classifier instanceof KTypeParameter) {
            throw new IllegalStateException(("Captured type paramerer " + classifier + " from generic non-reified function. Such functionality cannot be supported as " + classifier + " is erased, either specify serializer explicitly or make calling function inline with reified " + classifier).toString());
        }
        throw new IllegalStateException(("Only KClass supported as classifier, got " + classifier).toString());
    }
}
