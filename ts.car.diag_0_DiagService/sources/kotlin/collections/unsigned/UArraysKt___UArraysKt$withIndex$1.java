package kotlin.collections.unsigned;

import kotlin.Metadata;
import kotlin.collections.L;
import kotlin.jvm.b.a;
import kotlin.jvm.internal.Lambda;
import kotlin.k;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lkotlin/collections/UIntIterator;", "invoke"}, k = 3, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
final class UArraysKt___UArraysKt$withIndex$1 extends Lambda implements a<L> {
    final /* synthetic */ int[] $this_withIndex;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    UArraysKt___UArraysKt$withIndex$1(int[] iArr) {
        super(0);
        this.$this_withIndex = iArr;
    }

    @Override // kotlin.jvm.b.a
    @NotNull
    public final L invoke() {
        return k.a(this.$this_withIndex);
    }
}
