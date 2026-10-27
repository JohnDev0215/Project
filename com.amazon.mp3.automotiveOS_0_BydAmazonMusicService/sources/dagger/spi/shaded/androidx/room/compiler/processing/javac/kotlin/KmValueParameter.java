package dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin;

import com.amazon.identity.auth.device.utils.AccountConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KotlinClassMetadataUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\b\u0080\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0012\u001a\u00020\u0004¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmValueParameter;", "", "", "toString", "", "hashCode", "other", "", "equals", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "getType", "()Landroidx/room/compiler/processing/javac/kotlin/KmType;", "flags", "I", "<init>", "(Ljava/lang/String;Landroidx/room/compiler/processing/javac/kotlin/KmType;I)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final /* data */ class KmValueParameter {
    private final int flags;
    private final String name;
    private final KmType type;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KmValueParameter)) {
            return false;
        }
        KmValueParameter kmValueParameter = (KmValueParameter) other;
        return Intrinsics.areEqual(this.name, kmValueParameter.name) && Intrinsics.areEqual(this.type, kmValueParameter.type) && this.flags == kmValueParameter.flags;
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + this.type.hashCode()) * 31) + Integer.hashCode(this.flags);
    }

    public String toString() {
        return "KmValueParameter(name=" + this.name + ", type=" + this.type + ", flags=" + this.flags + ')';
    }

    public KmValueParameter(String name, KmType type, int i) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(type, "type");
        this.name = name;
        this.type = type;
        this.flags = i;
    }

    public final String getName() {
        return this.name;
    }

    public final KmType getType() {
        return this.type;
    }
}
