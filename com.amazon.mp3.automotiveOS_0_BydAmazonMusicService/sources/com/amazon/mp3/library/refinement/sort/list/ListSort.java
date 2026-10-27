package com.amazon.mp3.library.refinement.sort.list;

import com.amazon.mp3.library.refinement.Refinable;
import com.amazon.mp3.library.refinement.datasource.ListSource;
import com.amazon.mp3.library.refinement.sort.Sort;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

/* JADX INFO: compiled from: ListSort.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u0003R,\u0010\t\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00060\u00058&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/library/refinement/sort/list/ListSort;", "Lcom/amazon/mp3/library/refinement/Refinable;", "R", "Lcom/amazon/mp3/library/refinement/sort/Sort;", "Lcom/amazon/mp3/library/refinement/datasource/ListSource;", "Lkotlin/Function1;", "Lkotlin/sequences/Sequence;", "getSortPredicate", "()Lkotlin/jvm/functions/Function1;", "sortPredicate", "MusicExperienceCore_release"}, k = 1, mv = {1, 6, 0})
public interface ListSort<R extends Refinable> extends Sort<R, ListSource<R>> {
    Function1<Sequence<? extends R>, Sequence<R>> getSortPredicate();

    /* JADX INFO: compiled from: ListSort.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static <R extends Refinable> ListSource<R> apply(ListSort<R> listSort, ListSource<R> dataSource) {
            Intrinsics.checkNotNullParameter(listSort, "this");
            Intrinsics.checkNotNullParameter(dataSource, "dataSource");
            dataSource.setSort(listSort.getSortPredicate());
            return dataSource;
        }
    }
}
