package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultQualifiers;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.e;

/* JADX INFO: loaded from: classes2.dex */
final class TypeAndDefaultQualifiers {

    @Nullable
    private final JavaDefaultQualifiers defaultQualifiers;
    private final boolean isFromStarProjection;

    @NotNull
    private final KotlinType type;

    @Nullable
    private final TypeParameterDescriptor typeParameterForArgument;

    public TypeAndDefaultQualifiers(@NotNull KotlinType type, @Nullable JavaDefaultQualifiers javaDefaultQualifiers, @Nullable TypeParameterDescriptor typeParameterDescriptor, boolean z) {
        Intrinsics.checkNotNullParameter(type, "type");
        this.type = type;
        this.defaultQualifiers = javaDefaultQualifiers;
        this.typeParameterForArgument = typeParameterDescriptor;
        this.isFromStarProjection = z;
    }

    @NotNull
    public final KotlinType component1() {
        return this.type;
    }

    @Nullable
    public final JavaDefaultQualifiers component2() {
        return this.defaultQualifiers;
    }

    @Nullable
    public final TypeParameterDescriptor component3() {
        return this.typeParameterForArgument;
    }

    public final boolean component4() {
        return this.isFromStarProjection;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TypeAndDefaultQualifiers)) {
            return false;
        }
        TypeAndDefaultQualifiers typeAndDefaultQualifiers = (TypeAndDefaultQualifiers) obj;
        return Intrinsics.areEqual(this.type, typeAndDefaultQualifiers.type) && Intrinsics.areEqual(this.defaultQualifiers, typeAndDefaultQualifiers.defaultQualifiers) && Intrinsics.areEqual(this.typeParameterForArgument, typeAndDefaultQualifiers.typeParameterForArgument) && this.isFromStarProjection == typeAndDefaultQualifiers.isFromStarProjection;
    }

    @NotNull
    public final KotlinType getType() {
        return this.type;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v5, types: [int] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v8 */
    public int hashCode() {
        int iHashCode = this.type.hashCode() * 31;
        JavaDefaultQualifiers javaDefaultQualifiers = this.defaultQualifiers;
        int iHashCode2 = (iHashCode + (javaDefaultQualifiers == null ? 0 : javaDefaultQualifiers.hashCode())) * 31;
        TypeParameterDescriptor typeParameterDescriptor = this.typeParameterForArgument;
        int iHashCode3 = (iHashCode2 + (typeParameterDescriptor != null ? typeParameterDescriptor.hashCode() : 0)) * 31;
        boolean z = this.isFromStarProjection;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode3 + r1;
    }

    @NotNull
    public String toString() {
        StringBuilder sbK = e.k("TypeAndDefaultQualifiers(type=");
        sbK.append(this.type);
        sbK.append(", defaultQualifiers=");
        sbK.append(this.defaultQualifiers);
        sbK.append(", typeParameterForArgument=");
        sbK.append(this.typeParameterForArgument);
        sbK.append(", isFromStarProjection=");
        sbK.append(this.isFromStarProjection);
        sbK.append(')');
        return sbK.toString();
    }
}
