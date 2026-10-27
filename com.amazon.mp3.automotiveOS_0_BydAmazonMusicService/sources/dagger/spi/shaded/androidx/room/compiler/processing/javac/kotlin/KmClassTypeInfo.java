package dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KotlinClassMetadataUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0080\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\r¨\u0006\u0012"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmClassTypeInfo;", "", "", "toString", "", "hashCode", "other", "", "equals", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "kmType", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "getKmType", "()Landroidx/room/compiler/processing/javac/kotlin/KmType;", "superType", "getSuperType", "<init>", "(Landroidx/room/compiler/processing/javac/kotlin/KmType;Landroidx/room/compiler/processing/javac/kotlin/KmType;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final /* data */ class KmClassTypeInfo {
    private final KmType kmType;
    private final KmType superType;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KmClassTypeInfo)) {
            return false;
        }
        KmClassTypeInfo kmClassTypeInfo = (KmClassTypeInfo) other;
        return Intrinsics.areEqual(this.kmType, kmClassTypeInfo.kmType) && Intrinsics.areEqual(this.superType, kmClassTypeInfo.superType);
    }

    public int hashCode() {
        int iHashCode = this.kmType.hashCode() * 31;
        KmType kmType = this.superType;
        return iHashCode + (kmType == null ? 0 : kmType.hashCode());
    }

    public String toString() {
        return "KmClassTypeInfo(kmType=" + this.kmType + ", superType=" + this.superType + ')';
    }

    public KmClassTypeInfo(KmType kmType, KmType kmType2) {
        Intrinsics.checkNotNullParameter(kmType, "kmType");
        this.kmType = kmType;
        this.superType = kmType2;
    }

    public final KmType getKmType() {
        return this.kmType;
    }

    public final KmType getSuperType() {
        return this.superType;
    }
}
