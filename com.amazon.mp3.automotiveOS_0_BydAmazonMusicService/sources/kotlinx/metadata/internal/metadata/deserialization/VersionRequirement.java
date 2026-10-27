package kotlinx.metadata.internal.metadata.deserialization;

import com.amazonaws.auth.policy.internal.JsonDocumentFields;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.scheduling.WorkQueueKt;
import kotlinx.metadata.internal.metadata.ProtoBuf$VersionRequirement;

/* JADX INFO: compiled from: VersionRequirement.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\u0018\u0000 \u00192\u00020\u0001:\u0002\u0019\u001aB1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\b\u0010\u0018\u001a\u00020\u000bH\u0016R\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001b"}, d2 = {"Lkotlinx/metadata/internal/metadata/deserialization/VersionRequirement;", "", "version", "Lkotlinx/metadata/internal/metadata/deserialization/VersionRequirement$Version;", "kind", "Lkotlinx/metadata/internal/metadata/ProtoBuf$VersionRequirement$VersionKind;", "level", "Lkotlin/DeprecationLevel;", "errorCode", "", "message", "", "(Lorg/jetbrains/kotlin/metadata/deserialization/VersionRequirement$Version;Lorg/jetbrains/kotlin/metadata/ProtoBuf$VersionRequirement$VersionKind;Lkotlin/DeprecationLevel;Ljava/lang/Integer;Ljava/lang/String;)V", "getErrorCode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getKind", "()Lorg/jetbrains/kotlin/metadata/ProtoBuf$VersionRequirement$VersionKind;", "getLevel", "()Lkotlin/DeprecationLevel;", "getMessage", "()Ljava/lang/String;", "getVersion", "()Lorg/jetbrains/kotlin/metadata/deserialization/VersionRequirement$Version;", "toString", "Companion", JsonDocumentFields.VERSION, "metadata"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class VersionRequirement {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Integer errorCode;
    private final ProtoBuf$VersionRequirement.VersionKind kind;
    private final DeprecationLevel level;
    private final String message;
    private final Version version;

    public VersionRequirement(Version version, ProtoBuf$VersionRequirement.VersionKind kind, DeprecationLevel level, Integer num, String str) {
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(level, "level");
        this.version = version;
        this.kind = kind;
        this.level = level;
        this.errorCode = num;
        this.message = str;
    }

    public final Version getVersion() {
        return this.version;
    }

    public final ProtoBuf$VersionRequirement.VersionKind getKind() {
        return this.kind;
    }

    public final DeprecationLevel getLevel() {
        return this.level;
    }

    public final Integer getErrorCode() {
        return this.errorCode;
    }

    public final String getMessage() {
        return this.message;
    }

    /* JADX INFO: compiled from: VersionRequirement.kt */
    @Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\r\b\u0086\b\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B!\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u0011\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0005¢\u0006\u0004\b\u0015\u0010\u0016J\u0006\u0010\u0003\u001a\u00020\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0016J\t\u0010\u0006\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0007\u001a\u00020\u0005HÆ\u0003J\t\u0010\b\u001a\u00020\u0005HÆ\u0003J\t\u0010\t\u001a\u00020\u0005HÖ\u0001J\u0013\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\r\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0010R\u0017\u0010\u0013\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0014\u0010\u0010¨\u0006\u0018"}, d2 = {"Lkotlinx/metadata/internal/metadata/deserialization/VersionRequirement$Version;", "", "", "asString", "toString", "", "component1", "component2", "component3", "hashCode", "other", "", "equals", "major", "I", "getMajor", "()I", "minor", "getMinor", "patch", "getPatch", "<init>", "(III)V", "Companion", "metadata"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Version {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JvmField
        public static final Version INFINITY = new Version(256, 256, 256);
        private final int major;
        private final int minor;
        private final int patch;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getMajor() {
            return this.major;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getMinor() {
            return this.minor;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getPatch() {
            return this.patch;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Version)) {
                return false;
            }
            Version version = (Version) other;
            return this.major == version.major && this.minor == version.minor && this.patch == version.patch;
        }

        public int hashCode() {
            return (((this.major * 31) + this.minor) * 31) + this.patch;
        }

        public Version(int i, int i2, int i3) {
            this.major = i;
            this.minor = i2;
            this.patch = i3;
        }

        public final int getMajor() {
            return this.major;
        }

        public final int getMinor() {
            return this.minor;
        }

        public final int getPatch() {
            return this.patch;
        }

        public final String asString() {
            StringBuilder sb;
            int i;
            if (this.patch == 0) {
                sb = new StringBuilder();
                sb.append(this.major);
                sb.append('.');
                i = this.minor;
            } else {
                sb = new StringBuilder();
                sb.append(this.major);
                sb.append('.');
                sb.append(this.minor);
                sb.append('.');
                i = this.patch;
            }
            sb.append(i);
            return sb.toString();
        }

        public String toString() {
            return asString();
        }

        /* JADX INFO: compiled from: VersionRequirement.kt */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\f\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u000fR\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lkotlinx/metadata/internal/metadata/deserialization/VersionRequirement$Version$Companion;", "", "()V", "INFINITY", "Lkotlinx/metadata/internal/metadata/deserialization/VersionRequirement$Version;", "MAJOR_BITS", "", "MAJOR_MASK", "MINOR_BITS", "MINOR_MASK", "PATCH_BITS", "PATCH_MASK", "decode", "version", "versionFull", "(Ljava/lang/Integer;Ljava/lang/Integer;)Lorg/jetbrains/kotlin/metadata/deserialization/VersionRequirement$Version;", "metadata"}, k = 1, mv = {1, 7, 1}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final Version decode(Integer num, Integer num2) {
                if (num2 != null) {
                    return new Version(num2.intValue() & 255, (num2.intValue() >> 8) & 255, (num2.intValue() >> 16) & 255);
                }
                if (num != null) {
                    return new Version(num.intValue() & 7, (num.intValue() >> 3) & 15, (num.intValue() >> 7) & WorkQueueKt.MASK);
                }
                return Version.INFINITY;
            }
        }
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("since ");
        sb.append(this.version);
        sb.append(' ');
        sb.append(this.level);
        String str2 = "";
        if (this.errorCode != null) {
            str = " error " + this.errorCode;
        } else {
            str = "";
        }
        sb.append(str);
        if (this.message != null) {
            str2 = ": " + this.message;
        }
        sb.append(str2);
        return sb.toString();
    }

    /* JADX INFO: compiled from: VersionRequirement.kt */
    @Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\n\u0010\u000bJ \u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006¨\u0006\f"}, d2 = {"Lkotlinx/metadata/internal/metadata/deserialization/VersionRequirement$Companion;", "", "", "id", "Lkotlinx/metadata/internal/metadata/deserialization/NameResolver;", "nameResolver", "Lkotlinx/metadata/internal/metadata/deserialization/VersionRequirementTable;", "table", "Lkotlinx/metadata/internal/metadata/deserialization/VersionRequirement;", "create", "<init>", "()V", "metadata"}, k = 1, mv = {1, 7, 1})
    public static final class Companion {

        /* JADX INFO: compiled from: VersionRequirement.kt */
        @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[ProtoBuf$VersionRequirement.Level.values().length];
                try {
                    iArr[ProtoBuf$VersionRequirement.Level.WARNING.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[ProtoBuf$VersionRequirement.Level.ERROR.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[ProtoBuf$VersionRequirement.Level.HIDDEN.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final VersionRequirement create(int id, NameResolver nameResolver, VersionRequirementTable table) {
            DeprecationLevel deprecationLevel;
            Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
            Intrinsics.checkNotNullParameter(table, "table");
            ProtoBuf$VersionRequirement protoBuf$VersionRequirement = table.get(id);
            if (protoBuf$VersionRequirement == null) {
                return null;
            }
            Version versionDecode = Version.INSTANCE.decode(protoBuf$VersionRequirement.hasVersion() ? Integer.valueOf(protoBuf$VersionRequirement.getVersion()) : null, protoBuf$VersionRequirement.hasVersionFull() ? Integer.valueOf(protoBuf$VersionRequirement.getVersionFull()) : null);
            ProtoBuf$VersionRequirement.Level level = protoBuf$VersionRequirement.getLevel();
            Intrinsics.checkNotNull(level);
            int i = WhenMappings.$EnumSwitchMapping$0[level.ordinal()];
            if (i == 1) {
                deprecationLevel = DeprecationLevel.WARNING;
            } else if (i == 2) {
                deprecationLevel = DeprecationLevel.ERROR;
            } else {
                if (i != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                deprecationLevel = DeprecationLevel.HIDDEN;
            }
            DeprecationLevel deprecationLevel2 = deprecationLevel;
            Integer numValueOf = protoBuf$VersionRequirement.hasErrorCode() ? Integer.valueOf(protoBuf$VersionRequirement.getErrorCode()) : null;
            String string = protoBuf$VersionRequirement.hasMessage() ? nameResolver.getString(protoBuf$VersionRequirement.getMessage()) : null;
            ProtoBuf$VersionRequirement.VersionKind versionKind = protoBuf$VersionRequirement.getVersionKind();
            Intrinsics.checkNotNullExpressionValue(versionKind, "info.versionKind");
            return new VersionRequirement(versionDecode, versionKind, deprecationLevel2, numValueOf, string);
        }
    }
}
