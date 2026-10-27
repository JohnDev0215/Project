package com.amazon.mp3.library.refinement.datasource;

import com.amazon.mp3.library.refinement.Refinable;
import com.amazon.mp3.library.refinement.query.ListQuery;
import com.amazon.mp3.library.refinement.sort.list.ListSort;
import com.google.android.gms.actions.SearchIntents;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequencesKt;
import kotlin.sequences.SequencesKt___SequencesKt;

/* JADX INFO: compiled from: ListSource.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010#\n\u0002\u0010\u000b\n\u0002\b\n\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u0003BS\u0012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u001a\b\u0002\u0010\u0019\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00180\f0\u0017\u0012 \b\u0002\u0010\u001b\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\r\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\r0\f¢\u0006\u0004\b \u0010!J\u0016\u0010\b\u001a\u00020\u00072\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0002J\b\u0010\n\u001a\u00020\tH\u0016J\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0016J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0016J&\u0010\u000f\u001a\u00020\u00072\u001e\u0010\u000e\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\r\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\r0\fJ\u0014\u0010\u000f\u001a\u00020\u00072\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010J\u0006\u0010\u0011\u001a\u00020\u0007J\u0006\u0010\u0012\u001a\u00020\u0007R\u001d\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R&\u0010\u0019\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00180\f0\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR.\u0010\u001b\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\r\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\r0\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u001c\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010\u0014¨\u0006\""}, d2 = {"Lcom/amazon/mp3/library/refinement/datasource/ListSource;", "Lcom/amazon/mp3/library/refinement/Refinable;", "R", "Lcom/amazon/mp3/library/refinement/datasource/RefinableDataSource;", "Lcom/amazon/mp3/library/refinement/query/ListQuery;", "", "list", "", "updateResult", "", "count", SearchIntents.EXTRA_QUERY, "Lkotlin/Function1;", "Lkotlin/sequences/Sequence;", "sort", "setSort", "Lcom/amazon/mp3/library/refinement/sort/list/ListSort;", "clearSort", "clearFilters", "data", "Ljava/util/List;", "getData", "()Ljava/util/List;", "", "", "filterLogic", "Ljava/util/Set;", "sortLogic", "Lkotlin/jvm/functions/Function1;", "sequence", "Lkotlin/sequences/Sequence;", "refinedData", "<init>", "(Ljava/util/List;Ljava/util/Set;Lkotlin/jvm/functions/Function1;)V", "MusicExperienceCore_release"}, k = 1, mv = {1, 6, 0})
public final class ListSource<R extends Refinable> implements RefinableDataSource<R, ListQuery<R>> {
    private final List<R> data;
    private final Set<Function1<R, Boolean>> filterLogic;
    private List<? extends R> refinedData;
    private Sequence<? extends R> sequence;
    private Function1<? super Sequence<? extends R>, ? extends Sequence<? extends R>> sortLogic;

    /* JADX WARN: Multi-variable type inference failed */
    public ListSource(List<? extends R> data, Set<Function1<R, Boolean>> filterLogic, Function1<? super Sequence<? extends R>, ? extends Sequence<? extends R>> sortLogic) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(filterLogic, "filterLogic");
        Intrinsics.checkNotNullParameter(sortLogic, "sortLogic");
        this.data = data;
        this.filterLogic = filterLogic;
        this.sortLogic = sortLogic;
        this.sequence = SequencesKt__SequencesKt.sequenceOf(new Refinable[0]);
        this.refinedData = CollectionsKt__CollectionsKt.emptyList();
        updateResult(data);
    }

    public /* synthetic */ ListSource(List list, Set set, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? new LinkedHashSet() : set, (i & 4) != 0 ? new Function1<Sequence<? extends R>, Sequence<? extends R>>() { // from class: com.amazon.mp3.library.refinement.datasource.ListSource.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public final Sequence<R> invoke(Sequence<? extends R> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it;
            }
        } : function1);
    }

    @Override // com.amazon.mp3.library.refinement.datasource.RefinableDataSource
    public synchronized int count() {
        return this.refinedData.size();
    }

    public synchronized List<R> query(ListQuery<R> query) {
        int pageIndex;
        Intrinsics.checkNotNullParameter(query, "query");
        pageIndex = query.getPageIndex() * query.getPageSize();
        return this.refinedData.subList(pageIndex, Math.min(query.getPageSize() + pageIndex, this.refinedData.size()));
    }

    public synchronized List<R> query() {
        return this.refinedData;
    }

    public final synchronized void setSort(Function1<? super Sequence<? extends R>, ? extends Sequence<? extends R>> sort) {
        Intrinsics.checkNotNullParameter(sort, "sort");
        if (!Intrinsics.areEqual(sort, this.sortLogic)) {
            this.sortLogic = sort;
            updateResult(this.refinedData);
        }
    }

    public final synchronized void setSort(ListSort<R> sort) {
        Intrinsics.checkNotNullParameter(sort, "sort");
        if (!Intrinsics.areEqual(sort, this.sortLogic)) {
            this.sortLogic = sort.getSortPredicate();
            updateResult(this.refinedData);
        }
    }

    public final synchronized void clearSort() {
        this.sortLogic = new Function1<Sequence<? extends R>, Sequence<? extends R>>() { // from class: com.amazon.mp3.library.refinement.datasource.ListSource.clearSort.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public final Sequence<R> invoke(Sequence<? extends R> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it;
            }
        };
        updateResult(this.data);
    }

    public final void clearFilters() {
        if (!this.filterLogic.isEmpty()) {
            this.filterLogic.clear();
            updateResult(this.data);
        }
    }

    private final synchronized void updateResult(List<? extends R> list) {
        this.sequence = CollectionsKt___CollectionsKt.asSequence(list);
        Iterator<T> it = this.filterLogic.iterator();
        while (it.hasNext()) {
            this.sequence = SequencesKt___SequencesKt.filter(this.sequence, (Function1) it.next());
        }
        Sequence<? extends R> sequenceInvoke = this.sortLogic.invoke(this.sequence);
        this.sequence = sequenceInvoke;
        this.refinedData = SequencesKt___SequencesKt.toList(sequenceInvoke);
    }
}
