package com.amazon.mp3.library.refinement.sort.list;

import com.amazon.mp3.library.refinement.Refinable;
import com.amazon.mp3.library.refinement.datasource.ListSource;
import com.amazon.mp3.library.refinement.sort.SortOrder;
import com.amazon.mp3.library.refinement.sort.list.ListSort;
import java.lang.Comparable;
import java.lang.Number;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;

/* JADX INFO: compiled from: ListSortNumeric.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000f\n\u0002\u0010\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\u0012\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004*\u00020\u00052\b\u0012\u0004\u0012\u0002H\u00010\u0006B%\b\u0007\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\n¢\u0006\u0002\u0010\u000bR\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR,\u0010\u0010\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00110\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\r¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/library/refinement/sort/list/ListSortNumeric;", "R", "Lcom/amazon/mp3/library/refinement/Refinable;", "N", "", "", "Lcom/amazon/mp3/library/refinement/sort/list/ListSort;", "sortOrder", "Lcom/amazon/mp3/library/refinement/sort/SortOrder;", "sortNumber", "Lkotlin/Function1;", "(Lcom/amazon/mp3/library/refinement/sort/SortOrder;Lkotlin/jvm/functions/Function1;)V", "getSortNumber", "()Lkotlin/jvm/functions/Function1;", "getSortOrder", "()Lcom/amazon/mp3/library/refinement/sort/SortOrder;", "sortPredicate", "Lkotlin/sequences/Sequence;", "getSortPredicate", "MusicExperienceCore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ListSortNumeric<R extends Refinable, N extends Number & Comparable<? super N>> implements ListSort<R> {
    private final Function1<R, N> sortNumber;
    private final SortOrder sortOrder;
    private final Function1<Sequence<? extends R>, Sequence<R>> sortPredicate;

    /* JADX WARN: Multi-variable type inference failed */
    @JvmOverloads
    public ListSortNumeric(SortOrder sortOrder, Function1<? super R, ? extends N> sortNumber) {
        Intrinsics.checkNotNullParameter(sortOrder, "sortOrder");
        Intrinsics.checkNotNullParameter(sortNumber, "sortNumber");
        this.sortOrder = sortOrder;
        this.sortNumber = sortNumber;
        this.sortPredicate = (Function1<Sequence<? extends R>, Sequence<R>>) new Function1<Sequence<? extends R>, Sequence<? extends R>>(this) { // from class: com.amazon.mp3.library.refinement.sort.list.ListSortNumeric$sortPredicate$1
            final /* synthetic */ ListSortNumeric<R, N> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public final Sequence<R> invoke(Sequence<? extends R> sequence) {
                Intrinsics.checkNotNullParameter(sequence, "sequence");
                if (this.this$0.getSortOrder() == SortOrder.ASCENDING) {
                    final ListSortNumeric<R, N> listSortNumeric = this.this$0;
                    return SequencesKt___SequencesKt.sortedWith(sequence, new Comparator() { // from class: com.amazon.mp3.library.refinement.sort.list.ListSortNumeric$sortPredicate$1$invoke$$inlined$sortedBy$1
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            return ComparisonsKt__ComparisonsKt.compareValues((Comparable) ((Number) listSortNumeric.getSortNumber().invoke((Refinable) t)), (Comparable) ((Number) listSortNumeric.getSortNumber().invoke((Refinable) t2)));
                        }
                    });
                }
                final ListSortNumeric<R, N> listSortNumeric2 = this.this$0;
                return SequencesKt___SequencesKt.sortedWith(sequence, new Comparator() { // from class: com.amazon.mp3.library.refinement.sort.list.ListSortNumeric$sortPredicate$1$invoke$$inlined$sortedByDescending$1
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt__ComparisonsKt.compareValues((Comparable) ((Number) listSortNumeric2.getSortNumber().invoke((Refinable) t2)), (Comparable) ((Number) listSortNumeric2.getSortNumber().invoke((Refinable) t)));
                    }
                });
            }
        };
    }

    @Override // com.amazon.mp3.library.refinement.RefinementType
    public ListSource<R> apply(ListSource<R> listSource) {
        return ListSort.DefaultImpls.apply(this, listSource);
    }

    public /* synthetic */ ListSortNumeric(SortOrder sortOrder, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? SortOrder.ASCENDING : sortOrder, function1);
    }

    public SortOrder getSortOrder() {
        return this.sortOrder;
    }

    public final Function1<R, N> getSortNumber() {
        return this.sortNumber;
    }

    @Override // com.amazon.mp3.library.refinement.sort.list.ListSort
    public Function1<Sequence<? extends R>, Sequence<R>> getSortPredicate() {
        return this.sortPredicate;
    }
}
