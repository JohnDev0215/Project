package kotlinx.metadata.internal.metadata.jvm.deserialization;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.metadata.internal.metadata.jvm.JvmProtoBuf;

/* JADX INFO: compiled from: JvmNameResolver.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"toExpandedRecordsList", "", "Lkotlinx/metadata/internal/metadata/jvm/JvmProtoBuf$StringTableTypes$Record;", "metadata.jvm"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class JvmNameResolverKt {
    public static final List<JvmProtoBuf.StringTableTypes.Record> toExpandedRecordsList(List<JvmProtoBuf.StringTableTypes.Record> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        ArrayList arrayList = new ArrayList();
        arrayList.ensureCapacity(list.size());
        for (JvmProtoBuf.StringTableTypes.Record record : list) {
            int range = record.getRange();
            for (int i = 0; i < range; i++) {
                arrayList.add(record);
            }
        }
        arrayList.trimToSize();
        return arrayList;
    }
}
