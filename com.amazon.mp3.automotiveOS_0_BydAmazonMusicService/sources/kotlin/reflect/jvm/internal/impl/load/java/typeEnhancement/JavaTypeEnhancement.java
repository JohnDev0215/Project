package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverSettings;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawTypeImpl;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.RawType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;

/* JADX INFO: compiled from: typeEnhancement.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class JavaTypeEnhancement {
    private final JavaResolverSettings javaResolverSettings;

    /* JADX INFO: compiled from: typeEnhancement.kt */
    private static final class Result {
        private final int subtreeSize;
        private final KotlinType type;

        public Result(KotlinType kotlinType, int i) {
            this.type = kotlinType;
            this.subtreeSize = i;
        }

        public final int getSubtreeSize() {
            return this.subtreeSize;
        }

        public final KotlinType getType() {
            return this.type;
        }
    }

    public JavaTypeEnhancement(JavaResolverSettings javaResolverSettings) {
        Intrinsics.checkNotNullParameter(javaResolverSettings, "javaResolverSettings");
        this.javaResolverSettings = javaResolverSettings;
    }

    /* JADX INFO: compiled from: typeEnhancement.kt */
    private static final class SimpleResult {
        private final boolean forWarnings;
        private final int subtreeSize;
        private final SimpleType type;

        public SimpleResult(SimpleType simpleType, int i, boolean z) {
            this.type = simpleType;
            this.subtreeSize = i;
            this.forWarnings = z;
        }

        public final boolean getForWarnings() {
            return this.forWarnings;
        }

        public final int getSubtreeSize() {
            return this.subtreeSize;
        }

        public final SimpleType getType() {
            return this.type;
        }
    }

    public final KotlinType enhance(KotlinType kotlinType, Function1<? super Integer, JavaTypeQualifiers> qualifiers, boolean z) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        Intrinsics.checkNotNullParameter(qualifiers, "qualifiers");
        return enhancePossiblyFlexible(kotlinType.unwrap(), qualifiers, 0, z).getType();
    }

    private final Result enhancePossiblyFlexible(UnwrappedType unwrappedType, Function1<? super Integer, JavaTypeQualifiers> function1, int i, boolean z) {
        KotlinType kotlinTypeFlexibleType;
        KotlinType kotlinTypeWrapEnhancement = null;
        if (KotlinTypeKt.isError(unwrappedType)) {
            return new Result(null, 1);
        }
        if (unwrappedType instanceof FlexibleType) {
            boolean z2 = unwrappedType instanceof RawType;
            FlexibleType flexibleType = (FlexibleType) unwrappedType;
            SimpleResult simpleResultEnhanceInflexible = enhanceInflexible(flexibleType.getLowerBound(), function1, i, TypeComponentPosition.FLEXIBLE_LOWER, z2, z);
            SimpleResult simpleResultEnhanceInflexible2 = enhanceInflexible(flexibleType.getUpperBound(), function1, i, TypeComponentPosition.FLEXIBLE_UPPER, z2, z);
            simpleResultEnhanceInflexible.getSubtreeSize();
            simpleResultEnhanceInflexible2.getSubtreeSize();
            if (simpleResultEnhanceInflexible.getType() != null || simpleResultEnhanceInflexible2.getType() != null) {
                if (simpleResultEnhanceInflexible.getForWarnings() || simpleResultEnhanceInflexible2.getForWarnings()) {
                    SimpleType type = simpleResultEnhanceInflexible2.getType();
                    if (type != null) {
                        SimpleType type2 = simpleResultEnhanceInflexible.getType();
                        if (type2 == null) {
                            type2 = type;
                        }
                        kotlinTypeFlexibleType = KotlinTypeFactory.flexibleType(type2, type);
                    } else {
                        kotlinTypeFlexibleType = simpleResultEnhanceInflexible.getType();
                        Intrinsics.checkNotNull(kotlinTypeFlexibleType);
                    }
                    kotlinTypeWrapEnhancement = TypeWithEnhancementKt.wrapEnhancement(unwrappedType, kotlinTypeFlexibleType);
                } else if (z2) {
                    SimpleType type3 = simpleResultEnhanceInflexible.getType();
                    if (type3 == null) {
                        type3 = flexibleType.getLowerBound();
                    }
                    SimpleType type4 = simpleResultEnhanceInflexible2.getType();
                    if (type4 == null) {
                        type4 = flexibleType.getUpperBound();
                    }
                    kotlinTypeWrapEnhancement = new RawTypeImpl(type3, type4);
                } else {
                    SimpleType type5 = simpleResultEnhanceInflexible.getType();
                    if (type5 == null) {
                        type5 = flexibleType.getLowerBound();
                    }
                    SimpleType type6 = simpleResultEnhanceInflexible2.getType();
                    if (type6 == null) {
                        type6 = flexibleType.getUpperBound();
                    }
                    kotlinTypeWrapEnhancement = KotlinTypeFactory.flexibleType(type5, type6);
                }
            }
            return new Result(kotlinTypeWrapEnhancement, simpleResultEnhanceInflexible.getSubtreeSize());
        }
        if (!(unwrappedType instanceof SimpleType)) {
            throw new NoWhenBranchMatchedException();
        }
        SimpleResult simpleResultEnhanceInflexible$default = enhanceInflexible$default(this, (SimpleType) unwrappedType, function1, i, TypeComponentPosition.INFLEXIBLE, false, z, 8, null);
        return new Result(simpleResultEnhanceInflexible$default.getForWarnings() ? TypeWithEnhancementKt.wrapEnhancement(unwrappedType, simpleResultEnhanceInflexible$default.getType()) : simpleResultEnhanceInflexible$default.getType(), simpleResultEnhanceInflexible$default.getSubtreeSize());
    }

    static /* synthetic */ SimpleResult enhanceInflexible$default(JavaTypeEnhancement javaTypeEnhancement, SimpleType simpleType, Function1 function1, int i, TypeComponentPosition typeComponentPosition, boolean z, boolean z2, int i2, Object obj) {
        return javaTypeEnhancement.enhanceInflexible(simpleType, function1, i, typeComponentPosition, (i2 & 8) != 0 ? false : z, (i2 & 16) != 0 ? false : z2);
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x0173  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeEnhancement.SimpleResult enhanceInflexible(kotlin.reflect.jvm.internal.impl.types.SimpleType r19, kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers> r20, int r21, kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.TypeComponentPosition r22, boolean r23, boolean r24) {
        /*
            Method dump skipped, instruction units count: 541
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeEnhancement.enhanceInflexible(kotlin.reflect.jvm.internal.impl.types.SimpleType, kotlin.jvm.functions.Function1, int, kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.TypeComponentPosition, boolean, boolean):kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeEnhancement$SimpleResult");
    }

    private final SimpleType notNullTypeParameter(SimpleType simpleType) {
        if (this.javaResolverSettings.getCorrectNullabilityForNotNullTypeParameter()) {
            return SpecialTypesKt.makeSimpleTypeDefinitelyNotNullOrNotNull(simpleType, true);
        }
        return new NotNullTypeParameter(simpleType);
    }
}
