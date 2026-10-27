package kotlinx.metadata.internal.metadata.deserialization;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.metadata.internal.metadata.ProtoBuf$Type;
import kotlinx.metadata.internal.metadata.ProtoBuf$TypeTable;

/* JADX INFO: compiled from: TypeTable.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0011\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0086\u0002R\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, d2 = {"Lkotlinx/metadata/internal/metadata/deserialization/TypeTable;", "", "", "index", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Type;", "get", "", "types", "Ljava/util/List;", "getTypes", "()Ljava/util/List;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$TypeTable;", "typeTable", "<init>", "(Lorg/jetbrains/kotlin/metadata/ProtoBuf$TypeTable;)V", "metadata"}, k = 1, mv = {1, 7, 1})
public final class TypeTable {
    private final List<ProtoBuf$Type> types;

    public TypeTable(ProtoBuf$TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        List<ProtoBuf$Type> typeList = typeTable.getTypeList();
        if (typeTable.hasFirstNullable()) {
            int firstNullable = typeTable.getFirstNullable();
            List<ProtoBuf$Type> typeList2 = typeTable.getTypeList();
            Intrinsics.checkNotNullExpressionValue(typeList2, "typeTable.typeList");
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(typeList2, 10));
            int i = 0;
            for (Object obj : typeList2) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                ProtoBuf$Type protoBuf$TypeBuild = (ProtoBuf$Type) obj;
                if (i >= firstNullable) {
                    protoBuf$TypeBuild = protoBuf$TypeBuild.toBuilder().setNullable(true).build();
                }
                arrayList.add(protoBuf$TypeBuild);
                i = i2;
            }
            typeList = arrayList;
        }
        Intrinsics.checkNotNullExpressionValue(typeList, "run {\n        val origin… else originalTypes\n    }");
        this.types = typeList;
    }

    public final ProtoBuf$Type get(int index) {
        return this.types.get(index);
    }
}
