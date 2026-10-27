package kotlinx.metadata.internal.metadata.deserialization;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.metadata.internal.metadata.ProtoBuf$VersionRequirement;
import kotlinx.metadata.internal.metadata.ProtoBuf$VersionRequirementTable;

/* JADX INFO: compiled from: VersionRequirement.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0015\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0086\u0002R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lkotlinx/metadata/internal/metadata/deserialization/VersionRequirementTable;", "", "infos", "", "Lkotlinx/metadata/internal/metadata/ProtoBuf$VersionRequirement;", "(Ljava/util/List;)V", "get", "id", "", "Companion", "metadata"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class VersionRequirementTable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final VersionRequirementTable EMPTY = new VersionRequirementTable(CollectionsKt__CollectionsKt.emptyList());
    private final List<ProtoBuf$VersionRequirement> infos;

    public /* synthetic */ VersionRequirementTable(List list, DefaultConstructorMarker defaultConstructorMarker) {
        this(list);
    }

    private VersionRequirementTable(List<ProtoBuf$VersionRequirement> list) {
        this.infos = list;
    }

    public final ProtoBuf$VersionRequirement get(int id) {
        return (ProtoBuf$VersionRequirement) CollectionsKt___CollectionsKt.getOrNull(this.infos, id);
    }

    /* JADX INFO: compiled from: VersionRequirement.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lkotlinx/metadata/internal/metadata/deserialization/VersionRequirementTable$Companion;", "", "()V", "EMPTY", "Lkotlinx/metadata/internal/metadata/deserialization/VersionRequirementTable;", "getEMPTY", "()Lorg/jetbrains/kotlin/metadata/deserialization/VersionRequirementTable;", "create", "table", "Lkotlinx/metadata/internal/metadata/ProtoBuf$VersionRequirementTable;", "metadata"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final VersionRequirementTable getEMPTY() {
            return VersionRequirementTable.EMPTY;
        }

        public final VersionRequirementTable create(ProtoBuf$VersionRequirementTable table) {
            Intrinsics.checkNotNullParameter(table, "table");
            if (table.getRequirementCount() == 0) {
                return getEMPTY();
            }
            List<ProtoBuf$VersionRequirement> requirementList = table.getRequirementList();
            Intrinsics.checkNotNullExpressionValue(requirementList, "table.requirementList");
            return new VersionRequirementTable(requirementList, null);
        }
    }
}
