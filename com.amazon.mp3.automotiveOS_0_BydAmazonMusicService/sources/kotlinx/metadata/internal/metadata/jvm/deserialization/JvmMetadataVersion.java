package kotlinx.metadata.internal.metadata.jvm.deserialization;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.metadata.internal.metadata.deserialization.BinaryVersion;

/* JADX INFO: compiled from: JvmMetadataVersion.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0017\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nB\u0015\b\u0016\u0012\n\u0010\f\u001a\u00020\u0007\"\u00020\u000b¢\u0006\u0004\b\t\u0010\rJ\b\u0010\u0003\u001a\u00020\u0002H\u0016R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0004\u0010\u0006¨\u0006\u000f"}, d2 = {"Lkotlinx/metadata/internal/metadata/jvm/deserialization/JvmMetadataVersion;", "Lkotlinx/metadata/internal/metadata/deserialization/BinaryVersion;", "", "isCompatible", "isStrictSemantics", "Z", "()Z", "", "versionArray", "<init>", "([IZ)V", "", "numbers", "([I)V", "Companion", "metadata.jvm"}, k = 1, mv = {1, 7, 1})
public final class JvmMetadataVersion extends BinaryVersion {

    @JvmField
    public static final JvmMetadataVersion INSTANCE = new JvmMetadataVersion(1, 7, 1);

    @JvmField
    public static final JvmMetadataVersion INVALID_VERSION = new JvmMetadataVersion(new int[0]);
    private final boolean isStrictSemantics;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JvmMetadataVersion(int[] versionArray, boolean z) {
        super(Arrays.copyOf(versionArray, versionArray.length));
        Intrinsics.checkNotNullParameter(versionArray, "versionArray");
        this.isStrictSemantics = z;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JvmMetadataVersion(int... numbers) {
        this(numbers, false);
        Intrinsics.checkNotNullParameter(numbers, "numbers");
    }

    public boolean isCompatible() {
        boolean zIsCompatibleTo;
        if (getMajor() == 1 && getMinor() == 0) {
            return false;
        }
        if (this.isStrictSemantics) {
            zIsCompatibleTo = isCompatibleTo(INSTANCE);
        } else {
            int major = getMajor();
            JvmMetadataVersion jvmMetadataVersion = INSTANCE;
            zIsCompatibleTo = major == jvmMetadataVersion.getMajor() && getMinor() <= jvmMetadataVersion.getMinor() + 1;
        }
        return zIsCompatibleTo;
    }
}
