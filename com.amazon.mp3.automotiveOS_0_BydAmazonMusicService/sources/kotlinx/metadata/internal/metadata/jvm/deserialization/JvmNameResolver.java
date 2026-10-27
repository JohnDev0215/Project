package kotlinx.metadata.internal.metadata.jvm.deserialization;

import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.metadata.internal.metadata.jvm.JvmProtoBuf;

/* JADX INFO: compiled from: JvmNameResolver.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lkotlinx/metadata/internal/metadata/jvm/deserialization/JvmNameResolver;", "Lkotlinx/metadata/internal/metadata/jvm/deserialization/JvmNameResolverBase;", "types", "Lkotlinx/metadata/internal/metadata/jvm/JvmProtoBuf$StringTableTypes;", "strings", "", "", "(Lorg/jetbrains/kotlin/metadata/jvm/JvmProtoBuf$StringTableTypes;[Ljava/lang/String;)V", "getTypes", "()Lorg/jetbrains/kotlin/metadata/jvm/JvmProtoBuf$StringTableTypes;", "metadata.jvm"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class JvmNameResolver extends JvmNameResolverBase {
    private final JvmProtoBuf.StringTableTypes types;

    /* JADX WARN: Illegal instructions before constructor call */
    public JvmNameResolver(JvmProtoBuf.StringTableTypes types, String[] strings) {
        Set set;
        Intrinsics.checkNotNullParameter(types, "types");
        Intrinsics.checkNotNullParameter(strings, "strings");
        List<Integer> localNameList = types.getLocalNameList();
        if (localNameList.isEmpty()) {
            set = SetsKt__SetsKt.emptySet();
        } else {
            Intrinsics.checkNotNullExpressionValue(localNameList, "_init_$lambda-0");
            set = CollectionsKt___CollectionsKt.toSet(localNameList);
        }
        List<JvmProtoBuf.StringTableTypes.Record> recordList = types.getRecordList();
        Intrinsics.checkNotNullExpressionValue(recordList, "types.recordList");
        super(strings, set, JvmNameResolverKt.toExpandedRecordsList(recordList));
        this.types = types;
    }
}
