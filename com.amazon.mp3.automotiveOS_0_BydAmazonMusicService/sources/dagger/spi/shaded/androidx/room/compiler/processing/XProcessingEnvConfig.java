package dagger.spi.shaded.androidx.room.compiler.processing;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: XProcessingEnvConfig.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\b\u0086\b\u0018\u0000 \u00122\u00020\u0001:\u0002\u0013\u0012B\u001d\b\u0002\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0005\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u0002HÆ\u0001J\t\u0010\u0007\u001a\u00020\u0006HÖ\u0001J\t\u0010\t\u001a\u00020\bHÖ\u0001J\u0013\u0010\u000b\u001a\u00020\u00022\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\f\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u0014"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnvConfig;", "", "", "excludeMethodsWithInvalidJvmSourceNames", "disableAnnotatedElementValidation", "copy", "", "toString", "", "hashCode", "other", "equals", "Z", "getExcludeMethodsWithInvalidJvmSourceNames", "()Z", "getDisableAnnotatedElementValidation", "<init>", "(ZZ)V", "Companion", "Builder", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final /* data */ class XProcessingEnvConfig {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final XProcessingEnvConfig DEFAULT = new Builder(0 == true ? 1 : 0, 1, 0 == true ? 1 : 0).getInstance();
    private final boolean disableAnnotatedElementValidation;
    private final boolean excludeMethodsWithInvalidJvmSourceNames;

    public static /* synthetic */ XProcessingEnvConfig copy$default(XProcessingEnvConfig xProcessingEnvConfig, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = xProcessingEnvConfig.excludeMethodsWithInvalidJvmSourceNames;
        }
        if ((i & 2) != 0) {
            z2 = xProcessingEnvConfig.disableAnnotatedElementValidation;
        }
        return xProcessingEnvConfig.copy(z, z2);
    }

    public final XProcessingEnvConfig copy(boolean excludeMethodsWithInvalidJvmSourceNames, boolean disableAnnotatedElementValidation) {
        return new XProcessingEnvConfig(excludeMethodsWithInvalidJvmSourceNames, disableAnnotatedElementValidation);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof XProcessingEnvConfig)) {
            return false;
        }
        XProcessingEnvConfig xProcessingEnvConfig = (XProcessingEnvConfig) other;
        return this.excludeMethodsWithInvalidJvmSourceNames == xProcessingEnvConfig.excludeMethodsWithInvalidJvmSourceNames && this.disableAnnotatedElementValidation == xProcessingEnvConfig.disableAnnotatedElementValidation;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v2 */
    public int hashCode() {
        boolean z = this.excludeMethodsWithInvalidJvmSourceNames;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        boolean z2 = this.disableAnnotatedElementValidation;
        return i + (z2 ? 1 : z2);
    }

    public String toString() {
        return "XProcessingEnvConfig(excludeMethodsWithInvalidJvmSourceNames=" + this.excludeMethodsWithInvalidJvmSourceNames + ", disableAnnotatedElementValidation=" + this.disableAnnotatedElementValidation + ')';
    }

    private XProcessingEnvConfig(boolean z, boolean z2) {
        this.excludeMethodsWithInvalidJvmSourceNames = z;
        this.disableAnnotatedElementValidation = z2;
    }

    /* synthetic */ XProcessingEnvConfig(boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2);
    }

    public final boolean getExcludeMethodsWithInvalidJvmSourceNames() {
        return this.excludeMethodsWithInvalidJvmSourceNames;
    }

    /* JADX INFO: compiled from: XProcessingEnvConfig.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005¢\u0006\u0004\b\n\u0010\u000bJ\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002J\u0006\u0010\u0006\u001a\u00020\u0005R\u0016\u0010\u0007\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnvConfig$Builder;", "", "", "value", "disableAnnotatedElementValidation", "Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnvConfig;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "instance", "Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnvConfig;", "baseline", "<init>", "(Landroidx/room/compiler/processing/XProcessingEnvConfig;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
    public static final class Builder {
        private XProcessingEnvConfig instance;

        public Builder() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public Builder(XProcessingEnvConfig baseline) {
            Intrinsics.checkNotNullParameter(baseline, "baseline");
            this.instance = baseline;
        }

        public /* synthetic */ Builder(XProcessingEnvConfig xProcessingEnvConfig, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 1) != 0) {
                boolean z = false;
                xProcessingEnvConfig = new XProcessingEnvConfig(z, z, 3, null);
            }
            this(xProcessingEnvConfig);
        }

        public final Builder disableAnnotatedElementValidation(boolean value) {
            this.instance = XProcessingEnvConfig.copy$default(this.instance, false, value, 1, null);
            return this;
        }

        /* JADX INFO: renamed from: build, reason: from getter */
        public final XProcessingEnvConfig getInstance() {
            return this.instance;
        }
    }

    /* JADX INFO: compiled from: XProcessingEnvConfig.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnvConfig$Companion;", "", "()V", "DEFAULT", "Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnvConfig;", "getDEFAULT", "()Landroidx/room/compiler/processing/XProcessingEnvConfig;", "room-compiler-processing"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final XProcessingEnvConfig getDEFAULT() {
            return XProcessingEnvConfig.DEFAULT;
        }
    }
}
