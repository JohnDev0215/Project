package kotlinx.serialization.modules;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.reflect.KClass;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationStrategy;

/* JADX INFO: compiled from: SerializersModule.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0015\u0010\u0016J<\u0010\b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006\"\b\b\u0000\u0010\u0002*\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0012\b\u0002\u0010\u0007\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005H'J9\u0010\f\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u000b\"\b\b\u0000\u0010\u0002*\u00020\u00012\u000e\u0010\t\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00032\u0006\u0010\n\u001a\u00028\u0000H'¢\u0006\u0004\b\f\u0010\rJ6\u0010\f\u001a\f\u0012\u0006\b\u0001\u0012\u00028\u0000\u0018\u00010\u0010\"\b\b\u0000\u0010\u0002*\u00020\u00012\u000e\u0010\t\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH'J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0011H'\u0082\u0001\u0001\u0017¨\u0006\u0018"}, d2 = {"Lkotlinx/serialization/modules/SerializersModule;", "", "T", "Lkotlin/reflect/KClass;", "kClass", "", "Lkotlinx/serialization/KSerializer;", "typeArgumentsSerializers", "getContextual", "baseClass", "value", "Lkotlinx/serialization/SerializationStrategy;", "getPolymorphic", "(Lkotlin/reflect/KClass;Ljava/lang/Object;)Lkotlinx/serialization/SerializationStrategy;", "", "serializedClassName", "Lkotlinx/serialization/DeserializationStrategy;", "Lkotlinx/serialization/modules/SerializersModuleCollector;", "collector", "", "dumpTo", "<init>", "()V", "Lkotlinx/serialization/modules/SerialModuleImpl;", "kotlinx-serialization-core"}, k = 1, mv = {1, 7, 1})
public abstract class SerializersModule {
    public /* synthetic */ SerializersModule(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract void dumpTo(SerializersModuleCollector collector);

    public abstract <T> KSerializer<T> getContextual(KClass<T> kClass, List<? extends KSerializer<?>> typeArgumentsSerializers);

    public abstract <T> DeserializationStrategy<? extends T> getPolymorphic(KClass<? super T> baseClass, String serializedClassName);

    public abstract <T> SerializationStrategy<T> getPolymorphic(KClass<? super T> baseClass, T value);

    private SerializersModule() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ KSerializer getContextual$default(SerializersModule serializersModule, KClass kClass, List list, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getContextual");
        }
        if ((i & 2) != 0) {
            list = CollectionsKt__CollectionsKt.emptyList();
        }
        return serializersModule.getContextual(kClass, list);
    }
}
