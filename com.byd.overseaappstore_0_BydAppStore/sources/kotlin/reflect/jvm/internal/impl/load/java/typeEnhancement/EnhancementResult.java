package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.e;

/* JADX INFO: loaded from: classes2.dex */
final class EnhancementResult<T> {

    @Nullable
    private final Annotations enhancementAnnotations;
    private final T result;

    public EnhancementResult(T t, @Nullable Annotations annotations) {
        this.result = t;
        this.enhancementAnnotations = annotations;
    }

    public final T component1() {
        return this.result;
    }

    @Nullable
    public final Annotations component2() {
        return this.enhancementAnnotations;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EnhancementResult)) {
            return false;
        }
        EnhancementResult enhancementResult = (EnhancementResult) obj;
        return Intrinsics.areEqual(this.result, enhancementResult.result) && Intrinsics.areEqual(this.enhancementAnnotations, enhancementResult.enhancementAnnotations);
    }

    public int hashCode() {
        T t = this.result;
        int iHashCode = (t == null ? 0 : t.hashCode()) * 31;
        Annotations annotations = this.enhancementAnnotations;
        return iHashCode + (annotations != null ? annotations.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        StringBuilder sbK = e.k("EnhancementResult(result=");
        sbK.append(this.result);
        sbK.append(", enhancementAnnotations=");
        sbK.append(this.enhancementAnnotations);
        sbK.append(')');
        return sbK.toString();
    }
}
