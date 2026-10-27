package kotlinx.serialization;

import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.Triple;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.HashMapSerializer;
import kotlinx.serialization.internal.HashSetSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.LinkedHashSetSerializer;
import kotlinx.serialization.internal.PlatformKt;
import kotlinx.serialization.internal.Platform_commonKt;
import kotlinx.serialization.internal.PrimitivesKt;
import kotlinx.serialization.modules.SerializersModule;
import kotlinx.serialization.modules.SerializersModuleBuildersKt;

/* JADX INFO: compiled from: Serializers.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\u001a\u0016\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00022\u0006\u0010\u0001\u001a\u00020\u0000\u001a\u001a\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002*\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u0000\u001a\u001c\u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0002*\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u0000\u001a-\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0002*\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\t\u0010\n\u001a2\u0010\u000e\u001a\u0012\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002\u0018\u00010\f*\u00020\u00052\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00000\f2\u0006\u0010\b\u001a\u00020\u0007H\u0000\u001a$\u0010\u0006\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0002\"\b\b\u0000\u0010\u000f*\u00020\u0003*\b\u0012\u0004\u0012\u00028\u00000\u0010H\u0007\u001a@\u0010\u0013\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0003\u0018\u00010\u0002*\b\u0012\u0004\u0012\u00020\u00030\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00000\f2\u0014\u0010\u0012\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00020\fH\u0000\u001a9\u0010\u0016\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0003\u0018\u00010\u0002*\b\u0012\u0004\u0012\u00020\u00030\u00102\u0014\u0010\u0012\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00020\fH\u0002¢\u0006\u0004\b\u0014\u0010\u0015\u001aG\u0010\u0019\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0003\u0018\u00010\u0002*\b\u0012\u0004\u0012\u00020\u00030\u00102\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00000\f2\u0014\u0010\u0012\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00020\fH\u0002¢\u0006\u0004\b\u0017\u0010\u0018\u001a3\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0002\"\b\b\u0000\u0010\u000f*\u00020\u0003*\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u001a\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u001b\u0010\u001c¨\u0006\u001e"}, d2 = {"Lkotlin/reflect/KType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lkotlinx/serialization/KSerializer;", "", "serializer", "Lkotlinx/serialization/modules/SerializersModule;", "serializerOrNull", "", "failOnMissingTypeArgSerializer", "serializerByKTypeImpl$SerializersKt__SerializersKt", "(Lkotlinx/serialization/modules/SerializersModule;Lkotlin/reflect/KType;Z)Lkotlinx/serialization/KSerializer;", "serializerByKTypeImpl", "", "typeArguments", "serializersForParameters", "T", "Lkotlin/reflect/KClass;", "types", "serializers", "parametrizedSerializerOrNull", "compiledParametrizedSerializer$SerializersKt__SerializersKt", "(Lkotlin/reflect/KClass;Ljava/util/List;)Lkotlinx/serialization/KSerializer;", "compiledParametrizedSerializer", "builtinParametrizedSerializer$SerializersKt__SerializersKt", "(Lkotlin/reflect/KClass;Ljava/util/List;Ljava/util/List;)Lkotlinx/serialization/KSerializer;", "builtinParametrizedSerializer", "shouldBeNullable", "nullable$SerializersKt__SerializersKt", "(Lkotlinx/serialization/KSerializer;Z)Lkotlinx/serialization/KSerializer;", "nullable", "kotlinx-serialization-core"}, k = 5, mv = {1, 7, 1}, xs = "kotlinx/serialization/SerializersKt")
final /* synthetic */ class SerializersKt__SerializersKt {
    private static final KSerializer<? extends Object> compiledParametrizedSerializer$SerializersKt__SerializersKt(KClass<Object> kClass, List<? extends KSerializer<Object>> list) {
        Object[] array = list.toArray(new KSerializer[0]);
        Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        KSerializer[] kSerializerArr = (KSerializer[]) array;
        return PlatformKt.constructSerializerForGivenTypeArgs(kClass, (KSerializer<Object>[]) Arrays.copyOf(kSerializerArr, kSerializerArr.length));
    }

    public static final KSerializer<Object> serializer(KType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return SerializersKt.serializer(SerializersModuleBuildersKt.EmptySerializersModule(), type);
    }

    public static final KSerializer<Object> serializer(SerializersModule serializersModule, KType type) {
        Intrinsics.checkNotNullParameter(serializersModule, "<this>");
        Intrinsics.checkNotNullParameter(type, "type");
        KSerializer<Object> kSerializerSerializerByKTypeImpl$SerializersKt__SerializersKt = serializerByKTypeImpl$SerializersKt__SerializersKt(serializersModule, type, true);
        if (kSerializerSerializerByKTypeImpl$SerializersKt__SerializersKt != null) {
            return kSerializerSerializerByKTypeImpl$SerializersKt__SerializersKt;
        }
        PlatformKt.platformSpecificSerializerNotRegistered(Platform_commonKt.kclass(type));
        throw new KotlinNothingValueException();
    }

    public static final KSerializer<Object> serializerOrNull(SerializersModule serializersModule, KType type) {
        Intrinsics.checkNotNullParameter(serializersModule, "<this>");
        Intrinsics.checkNotNullParameter(type, "type");
        return serializerByKTypeImpl$SerializersKt__SerializersKt(serializersModule, type, false);
    }

    private static final KSerializer<Object> serializerByKTypeImpl$SerializersKt__SerializersKt(SerializersModule serializersModule, KType kType, boolean z) {
        KSerializer<Object> kSerializerFindCachedSerializer;
        KSerializer<? extends Object> contextual;
        KClass<Object> kclass = Platform_commonKt.kclass(kType);
        boolean zIsMarkedNullable = kType.isMarkedNullable();
        List<KTypeProjection> arguments = kType.getArguments();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arguments, 10));
        Iterator<T> it = arguments.iterator();
        while (it.hasNext()) {
            KType type = ((KTypeProjection) it.next()).getType();
            if (type == null) {
                throw new IllegalArgumentException(("Star projections in type arguments are not allowed, but had " + kType).toString());
            }
            arrayList.add(type);
        }
        if (arrayList.isEmpty()) {
            kSerializerFindCachedSerializer = SerializersCacheKt.findCachedSerializer(kclass, zIsMarkedNullable);
        } else {
            Object objFindParametrizedCachedSerializer = SerializersCacheKt.findParametrizedCachedSerializer(kclass, arrayList, zIsMarkedNullable);
            if (z) {
                if (Result.m2816isFailureimpl(objFindParametrizedCachedSerializer)) {
                    objFindParametrizedCachedSerializer = null;
                }
                kSerializerFindCachedSerializer = (KSerializer) objFindParametrizedCachedSerializer;
            } else {
                if (Result.m2813exceptionOrNullimpl(objFindParametrizedCachedSerializer) != null) {
                    return null;
                }
                kSerializerFindCachedSerializer = (KSerializer) objFindParametrizedCachedSerializer;
            }
        }
        if (kSerializerFindCachedSerializer != null) {
            return kSerializerFindCachedSerializer;
        }
        if (arrayList.isEmpty()) {
            contextual = SerializersModule.getContextual$default(serializersModule, kclass, null, 2, null);
        } else {
            List<KSerializer<Object>> listSerializersForParameters = SerializersKt.serializersForParameters(serializersModule, arrayList, z);
            if (listSerializersForParameters == null) {
                return null;
            }
            KSerializer<? extends Object> kSerializerParametrizedSerializerOrNull = SerializersKt.parametrizedSerializerOrNull(kclass, arrayList, listSerializersForParameters);
            contextual = kSerializerParametrizedSerializerOrNull == null ? serializersModule.getContextual(kclass, listSerializersForParameters) : kSerializerParametrizedSerializerOrNull;
        }
        if (contextual != null) {
            return nullable$SerializersKt__SerializersKt(contextual, zIsMarkedNullable);
        }
        return null;
    }

    public static final <T> KSerializer<T> serializerOrNull(KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        KSerializer<T> kSerializerCompiledSerializerImpl = PlatformKt.compiledSerializerImpl(kClass);
        return kSerializerCompiledSerializerImpl == null ? PrimitivesKt.builtinSerializerOrNull(kClass) : kSerializerCompiledSerializerImpl;
    }

    public static final KSerializer<? extends Object> parametrizedSerializerOrNull(KClass<Object> kClass, List<? extends KType> types, List<? extends KSerializer<Object>> serializers) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Intrinsics.checkNotNullParameter(types, "types");
        Intrinsics.checkNotNullParameter(serializers, "serializers");
        KSerializer<? extends Object> kSerializerBuiltinParametrizedSerializer$SerializersKt__SerializersKt = builtinParametrizedSerializer$SerializersKt__SerializersKt(kClass, types, serializers);
        return kSerializerBuiltinParametrizedSerializer$SerializersKt__SerializersKt == null ? compiledParametrizedSerializer$SerializersKt__SerializersKt(kClass, serializers) : kSerializerBuiltinParametrizedSerializer$SerializersKt__SerializersKt;
    }

    private static final KSerializer<? extends Object> builtinParametrizedSerializer$SerializersKt__SerializersKt(KClass<Object> kClass, List<? extends KType> list, List<? extends KSerializer<Object>> list2) {
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Collection.class)) ? true : Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(List.class)) ? true : Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(List.class)) ? true : Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(ArrayList.class))) {
            return new ArrayListSerializer(list2.get(0));
        }
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(HashSet.class))) {
            return new HashSetSerializer(list2.get(0));
        }
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Set.class)) ? true : Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Set.class)) ? true : Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(LinkedHashSet.class))) {
            return new LinkedHashSetSerializer(list2.get(0));
        }
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(HashMap.class))) {
            return new HashMapSerializer(list2.get(0), list2.get(1));
        }
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Map.class)) ? true : Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Map.class)) ? true : Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(LinkedHashMap.class))) {
            return new LinkedHashMapSerializer(list2.get(0), list2.get(1));
        }
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Map.Entry.class))) {
            return BuiltinSerializersKt.MapEntrySerializer(list2.get(0), list2.get(1));
        }
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Pair.class))) {
            return BuiltinSerializersKt.PairSerializer(list2.get(0), list2.get(1));
        }
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Triple.class))) {
            return BuiltinSerializersKt.TripleSerializer(list2.get(0), list2.get(1), list2.get(2));
        }
        if (!PlatformKt.isReferenceArray(kClass)) {
            return null;
        }
        KClassifier classifier = list.get(0).getClassifier();
        Intrinsics.checkNotNull(classifier, "null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
        return BuiltinSerializersKt.ArraySerializer((KClass) classifier, list2.get(0));
    }

    private static final <T> KSerializer<T> nullable$SerializersKt__SerializersKt(KSerializer<T> kSerializer, boolean z) {
        if (z) {
            return BuiltinSerializersKt.getNullable(kSerializer);
        }
        Intrinsics.checkNotNull(kSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.SerializersKt__SerializersKt.nullable?>");
        return kSerializer;
    }

    public static final List<KSerializer<Object>> serializersForParameters(SerializersModule serializersModule, List<? extends KType> typeArguments, boolean z) {
        ArrayList arrayList;
        Intrinsics.checkNotNullParameter(serializersModule, "<this>");
        Intrinsics.checkNotNullParameter(typeArguments, "typeArguments");
        if (z) {
            arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(typeArguments, 10));
            Iterator<T> it = typeArguments.iterator();
            while (it.hasNext()) {
                arrayList.add(SerializersKt.serializer(serializersModule, (KType) it.next()));
            }
        } else {
            arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(typeArguments, 10));
            Iterator<T> it2 = typeArguments.iterator();
            while (it2.hasNext()) {
                KSerializer<Object> kSerializerSerializerOrNull = SerializersKt.serializerOrNull(serializersModule, (KType) it2.next());
                if (kSerializerSerializerOrNull == null) {
                    return null;
                }
                arrayList.add(kSerializerSerializerOrNull);
            }
        }
        return arrayList;
    }
}
