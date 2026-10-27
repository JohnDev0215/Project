package kotlin.collections.unsigned;

import kotlin.Metadata;
import kotlin.collections.M;
import kotlin.jvm.b.a;
import kotlin.jvm.internal.Lambda;
import kotlin.m;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lkotlin/collections/ULongIterator;", "invoke"}, k = 3, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
final class UArraysKt___UArraysKt$withIndex$2 extends Lambda implements a<M> {
    final /* synthetic */ long[] $this_withIndex;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    UArraysKt___UArraysKt$withIndex$2(long[] jArr) {
        super(0);
        this.$this_withIndex = jArr;
    }

    @Override // kotlin.jvm.b.a
    @NotNull
    public final M invoke() {
        return m.a(this.$this_withIndex);
    }
}
