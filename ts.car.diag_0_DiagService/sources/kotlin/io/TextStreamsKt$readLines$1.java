package kotlin.io;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.b.l;
import kotlin.jvm.internal.Lambda;
import kotlin.q;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
final class TextStreamsKt$readLines$1 extends Lambda implements l<String, q> {
    final /* synthetic */ ArrayList $result;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TextStreamsKt$readLines$1(ArrayList arrayList) {
        super(1);
        this.$result = arrayList;
    }

    @Override // kotlin.jvm.b.l
    public /* bridge */ /* synthetic */ q invoke(String str) {
        invoke2(str);
        return q.f1526a;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull String str) {
        kotlin.jvm.internal.q.c(str, "it");
        this.$result.add(str);
    }
}
