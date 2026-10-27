package dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KotlinClassMetadataUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0082\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u000b\u001a\u00020\u0004\u0012\n\u0010\u0010\u001a\u00060\u0006j\u0002`\u000f\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u0006\u0010\u0003\u001a\u00020\u0002J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0007\u001a\u00020\u0006HÖ\u0001J\u0013\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0010\u001a\u00060\u0006j\u0002`\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001a"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmTypeParameter;", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "asKmType", "", "toString", "", "hashCode", "other", "", "equals", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "Lkotlinx/metadata/Flags;", "flags", "I", "getFlags", "()I", "extendsBound", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "getExtendsBound", "()Landroidx/room/compiler/processing/javac/kotlin/KmType;", "<init>", "(Ljava/lang/String;ILandroidx/room/compiler/processing/javac/kotlin/KmType;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
final /* data */ class KmTypeParameter {
    private final KmType extendsBound;
    private final int flags;
    private final String name;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KmTypeParameter)) {
            return false;
        }
        KmTypeParameter kmTypeParameter = (KmTypeParameter) other;
        return Intrinsics.areEqual(this.name, kmTypeParameter.name) && this.flags == kmTypeParameter.flags && Intrinsics.areEqual(this.extendsBound, kmTypeParameter.extendsBound);
    }

    public int hashCode() {
        int iHashCode = ((this.name.hashCode() * 31) + Integer.hashCode(this.flags)) * 31;
        KmType kmType = this.extendsBound;
        return iHashCode + (kmType == null ? 0 : kmType.hashCode());
    }

    public String toString() {
        return "KmTypeParameter(name=" + this.name + ", flags=" + this.flags + ", extendsBound=" + this.extendsBound + ')';
    }

    public KmTypeParameter(String name, int i, KmType kmType) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.flags = i;
        this.extendsBound = kmType;
    }

    public final int getFlags() {
        return this.flags;
    }

    public final KmType asKmType() {
        return new KmType(this.flags, CollectionsKt__CollectionsKt.emptyList(), this.extendsBound, false);
    }
}
