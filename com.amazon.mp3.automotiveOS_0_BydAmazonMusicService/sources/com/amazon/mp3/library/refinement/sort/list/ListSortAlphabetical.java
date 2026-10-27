package com.amazon.mp3.library.refinement.sort.list;

import com.amazon.mp3.library.refinement.Refinable;
import com.amazon.mp3.library.refinement.datasource.ListSource;
import com.amazon.mp3.library.refinement.sort.SortOrder;
import com.amazon.mp3.library.refinement.sort.list.ListSort;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;

/* JADX INFO: compiled from: ListSortAlphabetical.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B'\b\u0007\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\u0002\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR,\u0010\f\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\r\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\r0\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001f\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/library/refinement/sort/list/ListSortAlphabetical;", "R", "Lcom/amazon/mp3/library/refinement/Refinable;", "Lcom/amazon/mp3/library/refinement/sort/list/ListSort;", "sortOrder", "Lcom/amazon/mp3/library/refinement/sort/SortOrder;", "sortString", "Lkotlin/Function1;", "", "(Lcom/amazon/mp3/library/refinement/sort/SortOrder;Lkotlin/jvm/functions/Function1;)V", "getSortOrder", "()Lcom/amazon/mp3/library/refinement/sort/SortOrder;", "sortPredicate", "Lkotlin/sequences/Sequence;", "getSortPredicate", "()Lkotlin/jvm/functions/Function1;", "getSortString", "MusicExperienceCore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ListSortAlphabetical<R extends Refinable> implements ListSort<R> {
    private final SortOrder sortOrder;
    private final Function1<Sequence<? extends R>, Sequence<R>> sortPredicate;
    private final Function1<R, String> sortString;

    /* JADX WARN: Multi-variable type inference failed */
    @JvmOverloads
    public ListSortAlphabetical(SortOrder sortOrder, Function1<? super R, String> sortString) {
        Intrinsics.checkNotNullParameter(sortOrder, "sortOrder");
        Intrinsics.checkNotNullParameter(sortString, "sortString");
        this.sortOrder = sortOrder;
        this.sortString = sortString;
        this.sortPredicate = (Function1<Sequence<? extends R>, Sequence<R>>) new Function1<Sequence<? extends R>, Sequence<? extends R>>(this) { // from class: com.amazon.mp3.library.refinement.sort.list.ListSortAlphabetical$sortPredicate$1
            final /* synthetic */ ListSortAlphabetical<R> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public final Sequence<R> invoke(Sequence<? extends R> sequence) {
                Intrinsics.checkNotNullParameter(sequence, "sequence");
                if (this.this$0.getSortOrder() == SortOrder.ASCENDING) {
                    final ListSortAlphabetical<R> listSortAlphabetical = this.this$0;
                    return SequencesKt___SequencesKt.sortedWith(sequence, new Comparator() { // from class: com.amazon.mp3.library.refinement.sort.list.ListSortAlphabetical$sortPredicate$1$invoke$$inlined$sortedBy$1
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            return ComparisonsKt__ComparisonsKt.compareValues((String) listSortAlphabetical.getSortString().invoke((Refinable) t), (String) listSortAlphabetical.getSortString().invoke((Refinable) t2));
                        }
                    });
                }
                final ListSortAlphabetical<R> listSortAlphabetical2 = this.this$0;
                return SequencesKt___SequencesKt.sortedWith(sequence, new Comparator() { // from class: com.amazon.mp3.library.refinement.sort.list.ListSortAlphabetical$sortPredicate$1$invoke$$inlined$sortedByDescending$1
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt__ComparisonsKt.compareValues((String) listSortAlphabetical2.getSortString().invoke((Refinable) t2), (String) listSortAlphabetical2.getSortString().invoke((Refinable) t));
                    }
                });
            }
        };
    }

    @Override // com.amazon.mp3.library.refinement.RefinementType
    public ListSource<R> apply(ListSource<R> listSource) {
        return ListSort.DefaultImpls.apply(this, listSource);
    }

    public /* synthetic */ ListSortAlphabetical(SortOrder sortOrder, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? SortOrder.ASCENDING : sortOrder, function1);
    }

    public SortOrder getSortOrder() {
        return this.sortOrder;
    }

    public final Function1<R, String> getSortString() {
        return this.sortString;
    }

    @Override // com.amazon.mp3.library.refinement.sort.list.ListSort
    public Function1<Sequence<? extends R>, Sequence<R>> getSortPredicate() {
        return this.sortPredicate;
    }
}
