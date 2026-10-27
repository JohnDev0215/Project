package kotlinx.serialization.modules;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.PublishedApi;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationStrategy;

/* JADX INFO: compiled from: PolymorphicModuleBuilder.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000*\n\b\u0000\u0010\u0002 \u0000*\u00020\u00012\u00020\u0001B)\b\u0001\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006¢\u0006\u0004\b\u001b\u0010\u001cJ,\u0010\u0005\u001a\u00020\b\"\b\b\u0001\u0010\u0003*\u00028\u00002\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\tH\u0001R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048\bX\u0088\u0004¢\u0006\u0006\n\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00068\bX\u0088\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR6\u0010\u0012\u001a$\u0012 \u0012\u001e\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0004\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00060\u00110\u00108\bX\u0088\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R,\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00028\u0000\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0015\u0018\u00010\u00148\b@\bX\u0088\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R0\u0010\u001a\u001a\u001c\u0012\u0006\u0012\u0004\u0018\u00010\u0018\u0012\u000e\u0012\f\u0012\u0006\b\u0001\u0012\u00028\u0000\u0018\u00010\u0019\u0018\u00010\u00148\b@\bX\u0088\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u0017¨\u0006\u001d"}, d2 = {"Lkotlinx/serialization/modules/PolymorphicModuleBuilder;", "", "Base", "T", "Lkotlin/reflect/KClass;", "subclass", "Lkotlinx/serialization/KSerializer;", "serializer", "", "Lkotlinx/serialization/modules/SerializersModuleBuilder;", "builder", "buildTo", "baseClass", "Lkotlin/reflect/KClass;", "baseSerializer", "Lkotlinx/serialization/KSerializer;", "", "Lkotlin/Pair;", "subclasses", "Ljava/util/List;", "Lkotlin/Function1;", "Lkotlinx/serialization/SerializationStrategy;", "defaultSerializerProvider", "Lkotlin/jvm/functions/Function1;", "", "Lkotlinx/serialization/DeserializationStrategy;", "defaultDeserializerProvider", "<init>", "(Lkotlin/reflect/KClass;Lkotlinx/serialization/KSerializer;)V", "kotlinx-serialization-core"}, k = 1, mv = {1, 7, 1})
public final class PolymorphicModuleBuilder<Base> {
    private final KClass<Base> baseClass;
    private final KSerializer<Base> baseSerializer;
    private Function1<? super String, ? extends DeserializationStrategy<? extends Base>> defaultDeserializerProvider;
    private Function1<? super Base, ? extends SerializationStrategy<? super Base>> defaultSerializerProvider;
    private final List<Pair<KClass<? extends Base>, KSerializer<? extends Base>>> subclasses;

    @PublishedApi
    public PolymorphicModuleBuilder(KClass<Base> baseClass, KSerializer<Base> kSerializer) {
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        this.baseClass = baseClass;
        this.baseSerializer = kSerializer;
        this.subclasses = new ArrayList();
    }

    public final <T extends Base> void subclass(KClass<T> subclass, KSerializer<T> serializer) {
        Intrinsics.checkNotNullParameter(subclass, "subclass");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        this.subclasses.add(TuplesKt.to(subclass, serializer));
    }

    @PublishedApi
    public final void buildTo(SerializersModuleBuilder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        KSerializer<Base> kSerializer = this.baseSerializer;
        if (kSerializer != null) {
            KClass<Base> kClass = this.baseClass;
            SerializersModuleBuilder.registerPolymorphicSerializer$default(builder, kClass, kClass, kSerializer, false, 8, null);
        }
        Iterator<T> it = this.subclasses.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            KClass kClass2 = (KClass) pair.component1();
            KSerializer kSerializer2 = (KSerializer) pair.component2();
            KClass<Base> kClass3 = this.baseClass;
            Intrinsics.checkNotNull(kClass2, "null cannot be cast to non-null type kotlin.reflect.KClass<Base of kotlinx.serialization.modules.PolymorphicModuleBuilder.buildTo$lambda$1>");
            Intrinsics.checkNotNull(kSerializer2, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
            SerializersModuleBuilder.registerPolymorphicSerializer$default(builder, kClass3, kClass2, kSerializer2, false, 8, null);
        }
        Function1<? super Base, ? extends SerializationStrategy<? super Base>> function1 = this.defaultSerializerProvider;
        if (function1 != null) {
            builder.registerDefaultPolymorphicSerializer(this.baseClass, function1, false);
        }
        Function1<? super String, ? extends DeserializationStrategy<? extends Base>> function12 = this.defaultDeserializerProvider;
        if (function12 != null) {
            builder.registerDefaultPolymorphicDeserializer(this.baseClass, function12, false);
        }
    }
}
