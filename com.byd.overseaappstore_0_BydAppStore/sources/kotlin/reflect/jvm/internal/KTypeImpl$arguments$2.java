package kotlin.reflect.jvm.internal;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.jvm.internal.KTypeImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import org.jetbrains.annotations.NotNull;
import spi.e;

/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0016\u0012\u0004\u0012\u00020\u0002 \u0003*\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "Lkotlin/reflect/KTypeProjection;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 5, 1})
public final class KTypeImpl$arguments$2 extends Lambda implements Function0<List<? extends KTypeProjection>> {
    public final /* synthetic */ Function0 $computeJavaType;
    public final /* synthetic */ KTypeImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KTypeImpl$arguments$2(KTypeImpl kTypeImpl, Function0 function0) {
        super(0);
        this.this$0 = kTypeImpl;
        this.$computeJavaType = function0;
    }

    @Override // kotlin.jvm.functions.Function0
    public final List<? extends KTypeProjection> invoke() {
        KTypeProjection kTypeProjectionInvariant;
        List<TypeProjection> arguments = this.this$0.getType().getArguments();
        if (arguments.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<List<? extends Type>>() { // from class: kotlin.reflect.jvm.internal.KTypeImpl$arguments$2$parameterizedTypeArguments$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final List<? extends Type> invoke() {
                Type javaType = this.this$0.this$0.getJavaType();
                Intrinsics.checkNotNull(javaType);
                return ReflectClassUtilKt.getParameterizedTypeArguments(javaType);
            }
        });
        ArrayList arrayList = new ArrayList(CollectionsKt.b(arguments));
        final int i = 0;
        for (Object obj : arguments) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TypeProjection typeProjection = (TypeProjection) obj;
            if (typeProjection.isStarProjection()) {
                kTypeProjectionInvariant = KTypeProjection.INSTANCE.getSTAR();
            } else {
                KotlinType type = typeProjection.getType();
                Intrinsics.checkNotNullExpressionValue(type, "typeProjection.type");
                Function0<Type> function0 = null;
                byte b = 0;
                if (this.$computeJavaType != null) {
                    final byte b2 = b == true ? 1 : 0;
                    function0 = new Function0<Type>() { // from class: kotlin.reflect.jvm.internal.KTypeImpl$arguments$2$$special$$inlined$mapIndexed$lambda$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        @NotNull
                        public final Type invoke() {
                            Class genericComponentType;
                            String str;
                            Type javaType = this.this$0.getJavaType();
                            if (javaType instanceof Class) {
                                Class cls = (Class) javaType;
                                genericComponentType = cls.isArray() ? cls.getComponentType() : Object.class;
                                str = "if (javaType.isArray) ja…Type else Any::class.java";
                            } else if (javaType instanceof GenericArrayType) {
                                if (i != 0) {
                                    StringBuilder sbK = e.k("Array type has been queried for a non-0th argument: ");
                                    sbK.append(this.this$0);
                                    throw new KotlinReflectionInternalError(sbK.toString());
                                }
                                genericComponentType = ((GenericArrayType) javaType).getGenericComponentType();
                                str = "javaType.genericComponentType";
                            } else {
                                if (!(javaType instanceof ParameterizedType)) {
                                    StringBuilder sbK2 = e.k("Non-generic type has been queried for arguments: ");
                                    sbK2.append(this.this$0);
                                    throw new KotlinReflectionInternalError(sbK2.toString());
                                }
                                genericComponentType = (Type) ((List) lazy.getValue()).get(i);
                                if (genericComponentType instanceof WildcardType) {
                                    WildcardType wildcardType = (WildcardType) genericComponentType;
                                    Type[] lowerBounds = wildcardType.getLowerBounds();
                                    Intrinsics.checkNotNullExpressionValue(lowerBounds, "argument.lowerBounds");
                                    Type type2 = (Type) ArraysKt.firstOrNull(lowerBounds);
                                    if (type2 != null) {
                                        genericComponentType = type2;
                                    } else {
                                        Type[] upperBounds = wildcardType.getUpperBounds();
                                        Intrinsics.checkNotNullExpressionValue(upperBounds, "argument.upperBounds");
                                        genericComponentType = (Type) ArraysKt.first(upperBounds);
                                    }
                                }
                                str = "if (argument !is Wildcar…ument.upperBounds.first()";
                            }
                            Intrinsics.checkNotNullExpressionValue(genericComponentType, str);
                            return genericComponentType;
                        }
                    };
                }
                KTypeImpl kTypeImpl = new KTypeImpl(type, function0);
                int i3 = KTypeImpl.WhenMappings.$EnumSwitchMapping$0[typeProjection.getProjectionKind().ordinal()];
                if (i3 == 1) {
                    kTypeProjectionInvariant = KTypeProjection.INSTANCE.invariant(kTypeImpl);
                } else if (i3 == 2) {
                    kTypeProjectionInvariant = KTypeProjection.INSTANCE.contravariant(kTypeImpl);
                } else {
                    if (i3 != 3) {
                        throw new NoWhenBranchMatchedException();
                    }
                    kTypeProjectionInvariant = KTypeProjection.INSTANCE.covariant(kTypeImpl);
                }
            }
            arrayList.add(kTypeProjectionInvariant);
            i = i2;
        }
        return arrayList;
    }
}
