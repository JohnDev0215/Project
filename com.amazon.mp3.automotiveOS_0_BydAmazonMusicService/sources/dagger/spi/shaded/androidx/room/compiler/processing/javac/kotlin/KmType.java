package dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.metadata.Flag;

/* JADX INFO: compiled from: KotlinClassMetadataUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u000e\b\u0080\b\u0018\u00002\u00020\u0001B3\u0012\n\u0010\u000b\u001a\u00060\u0006j\u0002`\n\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00000\u000f\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0000\u0012\u0006\u0010\u0018\u001a\u00020\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u0006\u0010\u0003\u001a\u00020\u0002J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0007\u001a\u00020\u0006HÖ\u0001J\u0013\u0010\t\u001a\u00020\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u001b\u0010\u000b\u001a\u00060\u0006j\u0002`\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00000\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00008\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0018\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0018\u0010\u001a¨\u0006\u001d"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "", "", "isNullable", "", "toString", "", "hashCode", "other", "equals", "Lkotlinx/metadata/Flags;", "flags", "I", "getFlags", "()I", "", "typeArguments", "Ljava/util/List;", "getTypeArguments", "()Ljava/util/List;", "extendsBound", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "getExtendsBound", "()Landroidx/room/compiler/processing/javac/kotlin/KmType;", "isExtensionType", "Z", "()Z", "<init>", "(ILjava/util/List;Landroidx/room/compiler/processing/javac/kotlin/KmType;Z)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final /* data */ class KmType {
    private final KmType extendsBound;
    private final int flags;
    private final boolean isExtensionType;
    private final List<KmType> typeArguments;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KmType)) {
            return false;
        }
        KmType kmType = (KmType) other;
        return this.flags == kmType.flags && Intrinsics.areEqual(this.typeArguments, kmType.typeArguments) && Intrinsics.areEqual(this.extendsBound, kmType.extendsBound) && this.isExtensionType == kmType.isExtensionType;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v6, types: [int] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9 */
    public int hashCode() {
        int iHashCode = ((Integer.hashCode(this.flags) * 31) + this.typeArguments.hashCode()) * 31;
        KmType kmType = this.extendsBound;
        int iHashCode2 = (iHashCode + (kmType == null ? 0 : kmType.hashCode())) * 31;
        boolean z = this.isExtensionType;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode2 + r1;
    }

    public String toString() {
        return "KmType(flags=" + this.flags + ", typeArguments=" + this.typeArguments + ", extendsBound=" + this.extendsBound + ", isExtensionType=" + this.isExtensionType + ')';
    }

    public KmType(int i, List<KmType> typeArguments, KmType kmType, boolean z) {
        Intrinsics.checkNotNullParameter(typeArguments, "typeArguments");
        this.flags = i;
        this.typeArguments = typeArguments;
        this.extendsBound = kmType;
        this.isExtensionType = z;
    }

    public final int getFlags() {
        return this.flags;
    }

    public final List<KmType> getTypeArguments() {
        return this.typeArguments;
    }

    public final KmType getExtendsBound() {
        return this.extendsBound;
    }

    public final boolean isNullable() {
        return Flag.Type.IS_NULLABLE.invoke(this.flags);
    }
}
