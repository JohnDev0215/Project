package dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.metadata.Flag;

/* JADX INFO: compiled from: KotlinClassMetadataUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0080\b\u0018\u00002\u00020\u0001Bc\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u0011\u001a\u00020\u0005\u0012\n\u0010\u0014\u001a\u00060\u0007j\u0002`\u0013\u0012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016\u0012\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0016\u0012\u0006\u0010\u001f\u001a\u00020\u0017\u0012\b\u0010#\u001a\u0004\u0018\u00010\u0017\u0012\b\b\u0002\u0010%\u001a\u00020\u0002¢\u0006\u0004\b(\u0010)J\u0006\u0010\u0003\u001a\u00020\u0002J\u0006\u0010\u0004\u001a\u00020\u0002J\t\u0010\u0006\u001a\u00020\u0005HÖ\u0001J\t\u0010\b\u001a\u00020\u0007HÖ\u0001J\u0013\u0010\n\u001a\u00020\u00022\b\u0010\t\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u000f\u0010\f\u001a\u0004\b\u0010\u0010\u000eR\u0017\u0010\u0011\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u0012\u0010\u000eR\u0018\u0010\u0014\u001a\u00060\u0007j\u0002`\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u00168\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR \u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00168\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001d\u0010\u0019\u001a\u0004\b\u001e\u0010\u001bR\u0017\u0010\u001f\u001a\u00020\u00178\u0006¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R\u0019\u0010#\u001a\u0004\u0018\u00010\u00178\u0006¢\u0006\f\n\u0004\b#\u0010 \u001a\u0004\b$\u0010\"R\u0017\u0010%\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b%\u0010'¨\u0006*"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmFunction;", "", "", "isSuspend", "isExtension", "", "toString", "", "hashCode", "other", "equals", "jvmName", "Ljava/lang/String;", "getJvmName", "()Ljava/lang/String;", "name", "getName", "descriptor", "getDescriptor", "Lkotlinx/metadata/Flags;", "flags", "I", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "typeArguments", "Ljava/util/List;", "getTypeArguments", "()Ljava/util/List;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmValueParameter;", "parameters", "getParameters", "returnType", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "getReturnType", "()Landroidx/room/compiler/processing/javac/kotlin/KmType;", "receiverType", "getReceiverType", "isPropertyFunction", "Z", "()Z", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/util/List;Ljava/util/List;Landroidx/room/compiler/processing/javac/kotlin/KmType;Landroidx/room/compiler/processing/javac/kotlin/KmType;Z)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final /* data */ class KmFunction {
    private final String descriptor;
    private final int flags;
    private final boolean isPropertyFunction;
    private final String jvmName;
    private final String name;
    private final List<KmValueParameter> parameters;
    private final KmType receiverType;
    private final KmType returnType;
    private final List<KmType> typeArguments;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KmFunction)) {
            return false;
        }
        KmFunction kmFunction = (KmFunction) other;
        return Intrinsics.areEqual(this.jvmName, kmFunction.jvmName) && Intrinsics.areEqual(this.name, kmFunction.name) && Intrinsics.areEqual(this.descriptor, kmFunction.descriptor) && this.flags == kmFunction.flags && Intrinsics.areEqual(this.typeArguments, kmFunction.typeArguments) && Intrinsics.areEqual(getParameters(), kmFunction.getParameters()) && Intrinsics.areEqual(this.returnType, kmFunction.returnType) && Intrinsics.areEqual(this.receiverType, kmFunction.receiverType) && this.isPropertyFunction == kmFunction.isPropertyFunction;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v16, types: [int] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v19 */
    public int hashCode() {
        int iHashCode = ((((((((((((this.jvmName.hashCode() * 31) + this.name.hashCode()) * 31) + this.descriptor.hashCode()) * 31) + Integer.hashCode(this.flags)) * 31) + this.typeArguments.hashCode()) * 31) + getParameters().hashCode()) * 31) + this.returnType.hashCode()) * 31;
        KmType kmType = this.receiverType;
        int iHashCode2 = (iHashCode + (kmType == null ? 0 : kmType.hashCode())) * 31;
        boolean z = this.isPropertyFunction;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode2 + r1;
    }

    public String toString() {
        return "KmFunction(jvmName=" + this.jvmName + ", name=" + this.name + ", descriptor=" + this.descriptor + ", flags=" + this.flags + ", typeArguments=" + this.typeArguments + ", parameters=" + getParameters() + ", returnType=" + this.returnType + ", receiverType=" + this.receiverType + ", isPropertyFunction=" + this.isPropertyFunction + ')';
    }

    public KmFunction(String jvmName, String name, String descriptor, int i, List<KmType> typeArguments, List<KmValueParameter> parameters, KmType returnType, KmType kmType, boolean z) {
        Intrinsics.checkNotNullParameter(jvmName, "jvmName");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(typeArguments, "typeArguments");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        Intrinsics.checkNotNullParameter(returnType, "returnType");
        this.jvmName = jvmName;
        this.name = name;
        this.descriptor = descriptor;
        this.flags = i;
        this.typeArguments = typeArguments;
        this.parameters = parameters;
        this.returnType = returnType;
        this.receiverType = kmType;
        this.isPropertyFunction = z;
    }

    public /* synthetic */ KmFunction(String str, String str2, String str3, int i, List list, List list2, KmType kmType, KmType kmType2, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, i, list, list2, kmType, kmType2, (i2 & 256) != 0 ? false : z);
    }

    public final String getName() {
        return this.name;
    }

    public final String getDescriptor() {
        return this.descriptor;
    }

    public final List<KmType> getTypeArguments() {
        return this.typeArguments;
    }

    public List<KmValueParameter> getParameters() {
        return this.parameters;
    }

    public final KmType getReturnType() {
        return this.returnType;
    }

    public final boolean isSuspend() {
        return Flag.Function.IS_SUSPEND.invoke(this.flags);
    }

    public final boolean isExtension() {
        return this.receiverType != null;
    }
}
