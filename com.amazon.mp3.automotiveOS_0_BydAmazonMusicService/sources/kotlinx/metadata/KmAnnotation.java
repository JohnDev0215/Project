package kotlinx.metadata;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: annotations.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\n\u0010\n\u001a\u00060\u0002j\u0002`\t\u0012\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u001b\u0010\n\u001a\u00060\u0002j\u0002`\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR#\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lkotlinx/metadata/KmAnnotation;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lkotlinx/metadata/ClassName;", "className", "Ljava/lang/String;", "getClassName", "()Ljava/lang/String;", "", "Lkotlinx/metadata/KmAnnotationArgument;", "arguments", "Ljava/util/Map;", "getArguments", "()Ljava/util/Map;", "<init>", "(Ljava/lang/String;Ljava/util/Map;)V", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1})
public final /* data */ class KmAnnotation {
    private final Map<String, KmAnnotationArgument> arguments;
    private final String className;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KmAnnotation)) {
            return false;
        }
        KmAnnotation kmAnnotation = (KmAnnotation) other;
        return Intrinsics.areEqual(this.className, kmAnnotation.className) && Intrinsics.areEqual(this.arguments, kmAnnotation.arguments);
    }

    public int hashCode() {
        return (this.className.hashCode() * 31) + this.arguments.hashCode();
    }

    public String toString() {
        return "KmAnnotation(className=" + this.className + ", arguments=" + this.arguments + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public KmAnnotation(String className, Map<String, ? extends KmAnnotationArgument> arguments) {
        Intrinsics.checkNotNullParameter(className, "className");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        this.className = className;
        this.arguments = arguments;
    }

    public final String getClassName() {
        return this.className;
    }
}
