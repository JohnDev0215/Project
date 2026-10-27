package dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin;

import com.amazon.identity.auth.device.utils.AccountConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KotlinClassMetadataUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0080\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u0019\u0010\u001aJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00128\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0014\u001a\u0004\b\u0018\u0010\u0016¨\u0006\u001b"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmProperty;", "", "", "toString", "", "hashCode", "other", "", "equals", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "getType", "()Landroidx/room/compiler/processing/javac/kotlin/KmType;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmFunction;", "getter", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmFunction;", "getGetter", "()Landroidx/room/compiler/processing/javac/kotlin/KmFunction;", "setter", "getSetter", "<init>", "(Ljava/lang/String;Landroidx/room/compiler/processing/javac/kotlin/KmType;Landroidx/room/compiler/processing/javac/kotlin/KmFunction;Landroidx/room/compiler/processing/javac/kotlin/KmFunction;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final /* data */ class KmProperty {
    private final KmFunction getter;
    private final String name;
    private final KmFunction setter;
    private final KmType type;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KmProperty)) {
            return false;
        }
        KmProperty kmProperty = (KmProperty) other;
        return Intrinsics.areEqual(this.name, kmProperty.name) && Intrinsics.areEqual(this.type, kmProperty.type) && Intrinsics.areEqual(this.getter, kmProperty.getter) && Intrinsics.areEqual(this.setter, kmProperty.setter);
    }

    public int hashCode() {
        int iHashCode = ((this.name.hashCode() * 31) + this.type.hashCode()) * 31;
        KmFunction kmFunction = this.getter;
        int iHashCode2 = (iHashCode + (kmFunction == null ? 0 : kmFunction.hashCode())) * 31;
        KmFunction kmFunction2 = this.setter;
        return iHashCode2 + (kmFunction2 != null ? kmFunction2.hashCode() : 0);
    }

    public String toString() {
        return "KmProperty(name=" + this.name + ", type=" + this.type + ", getter=" + this.getter + ", setter=" + this.setter + ')';
    }

    public KmProperty(String name, KmType type, KmFunction kmFunction, KmFunction kmFunction2) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(type, "type");
        this.name = name;
        this.type = type;
        this.getter = kmFunction;
        this.setter = kmFunction2;
    }

    public final String getName() {
        return this.name;
    }

    public final KmType getType() {
        return this.type;
    }

    public final KmFunction getGetter() {
        return this.getter;
    }

    public final KmFunction getSetter() {
        return this.setter;
    }
}
