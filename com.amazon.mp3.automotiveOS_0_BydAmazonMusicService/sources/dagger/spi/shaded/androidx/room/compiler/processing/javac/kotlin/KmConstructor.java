package dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KotlinClassMetadataUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0080\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\n\u0010\u000e\u001a\u00060\u0004j\u0002`\r\u0012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\u0004\b\u0016\u0010\u0017J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u000e\u001a\u00060\u0004j\u0002`\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0018"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmConstructor;", "", "", "toString", "", "hashCode", "other", "", "equals", "descriptor", "Ljava/lang/String;", "getDescriptor", "()Ljava/lang/String;", "Lkotlinx/metadata/Flags;", "flags", "I", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmValueParameter;", "parameters", "Ljava/util/List;", "getParameters", "()Ljava/util/List;", "<init>", "(Ljava/lang/String;ILjava/util/List;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final /* data */ class KmConstructor {
    private final String descriptor;
    private final int flags;
    private final List<KmValueParameter> parameters;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KmConstructor)) {
            return false;
        }
        KmConstructor kmConstructor = (KmConstructor) other;
        return Intrinsics.areEqual(this.descriptor, kmConstructor.descriptor) && this.flags == kmConstructor.flags && Intrinsics.areEqual(getParameters(), kmConstructor.getParameters());
    }

    public int hashCode() {
        return (((this.descriptor.hashCode() * 31) + Integer.hashCode(this.flags)) * 31) + getParameters().hashCode();
    }

    public String toString() {
        return "KmConstructor(descriptor=" + this.descriptor + ", flags=" + this.flags + ", parameters=" + getParameters() + ')';
    }

    public KmConstructor(String descriptor, int i, List<KmValueParameter> parameters) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        this.descriptor = descriptor;
        this.flags = i;
        this.parameters = parameters;
    }

    public List<KmValueParameter> getParameters() {
        return this.parameters;
    }
}
