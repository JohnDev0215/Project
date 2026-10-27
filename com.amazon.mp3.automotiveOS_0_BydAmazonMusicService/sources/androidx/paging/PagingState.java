package androidx.paging;

import androidx.annotation.IntRange;
import androidx.paging.PagingSource;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PagingState.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002B;\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0001\u0010\u000b\u001a\u00020\b¢\u0006\u0002\u0010\fJY\u0010\u0014\u001a\u0002H\u0015\"\u0004\b\u0002\u0010\u00152\u0006\u0010\u0007\u001a\u00020\b26\u0010\u0016\u001a2\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u0002H\u00150\u0017H\u0080\bø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u0015\u0010\u001e\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\u001fJ\u001c\u0010 \u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\r\u0010$\u001a\u0004\u0018\u00018\u0001¢\u0006\u0002\u0010%J\b\u0010&\u001a\u00020\bH\u0016J\u0006\u0010'\u001a\u00020\"J\r\u0010(\u001a\u0004\u0018\u00018\u0001¢\u0006\u0002\u0010%J\b\u0010)\u001a\u00020*H\u0016R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u000b\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u0004\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006+"}, d2 = {"Landroidx/paging/PagingState;", "Key", "", "Value", "pages", "", "Landroidx/paging/PagingSource$LoadResult$Page;", "anchorPosition", "", "config", "Landroidx/paging/PagingConfig;", "leadingPlaceholderCount", "(Ljava/util/List;Ljava/lang/Integer;Landroidx/paging/PagingConfig;I)V", "getAnchorPosition", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getConfig", "()Landroidx/paging/PagingConfig;", "getPages", "()Ljava/util/List;", "anchorPositionToPagedIndices", "T", "block", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "pageIndex", "index", "anchorPositionToPagedIndices$paging_common", "(ILkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "closestItemToPosition", "(I)Ljava/lang/Object;", "closestPageToPosition", "equals", "", "other", "firstItemOrNull", "()Ljava/lang/Object;", "hashCode", "isEmpty", "lastItemOrNull", "toString", "", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class PagingState<Key, Value> {
    private final Integer anchorPosition;
    private final PagingConfig config;
    private final int leadingPlaceholderCount;
    private final List<PagingSource.LoadResult.Page<Key, Value>> pages;

    public PagingState(List<PagingSource.LoadResult.Page<Key, Value>> pages, Integer num, PagingConfig config, @IntRange(from = 0) int i) {
        Intrinsics.checkNotNullParameter(pages, "pages");
        Intrinsics.checkNotNullParameter(config, "config");
        this.pages = pages;
        this.anchorPosition = num;
        this.config = config;
        this.leadingPlaceholderCount = i;
    }

    public final List<PagingSource.LoadResult.Page<Key, Value>> getPages() {
        return this.pages;
    }

    public final Integer getAnchorPosition() {
        return this.anchorPosition;
    }

    public final PagingConfig getConfig() {
        return this.config;
    }

    public boolean equals(Object other) {
        if (other instanceof PagingState) {
            PagingState pagingState = (PagingState) other;
            if (Intrinsics.areEqual(this.pages, pagingState.pages) && Intrinsics.areEqual(this.anchorPosition, pagingState.anchorPosition) && Intrinsics.areEqual(this.config, pagingState.config) && this.leadingPlaceholderCount == pagingState.leadingPlaceholderCount) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = this.pages.hashCode();
        Integer num = this.anchorPosition;
        return iHashCode + (num != null ? num.hashCode() : 0) + this.config.hashCode() + Integer.hashCode(this.leadingPlaceholderCount);
    }

    public final Value closestItemToPosition(int anchorPosition) {
        boolean z;
        List<PagingSource.LoadResult.Page<Key, Value>> list = this.pages;
        int i = 0;
        if ((list instanceof Collection) && list.isEmpty()) {
            z = true;
        } else {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (!((PagingSource.LoadResult.Page) it.next()).getData().isEmpty()) {
                    z = false;
                    break;
                }
            }
            z = true;
        }
        if (z) {
            return null;
        }
        int size = anchorPosition - this.leadingPlaceholderCount;
        while (i < CollectionsKt__CollectionsKt.getLastIndex(getPages()) && size > CollectionsKt__CollectionsKt.getLastIndex(getPages().get(i).getData())) {
            size -= getPages().get(i).getData().size();
            i++;
        }
        Iterator<T> it2 = getPages().iterator();
        while (it2.hasNext()) {
            PagingSource.LoadResult.Page page = (PagingSource.LoadResult.Page) it2.next();
            if (!page.getData().isEmpty()) {
                List<PagingSource.LoadResult.Page<Key, Value>> pages = getPages();
                ListIterator<PagingSource.LoadResult.Page<Key, Value>> listIterator = pages.listIterator(pages.size());
                while (listIterator.hasPrevious()) {
                    PagingSource.LoadResult.Page<Key, Value> pagePrevious = listIterator.previous();
                    if (!pagePrevious.getData().isEmpty()) {
                        if (size < 0) {
                            return (Value) CollectionsKt___CollectionsKt.first((List) page.getData());
                        }
                        if (i == CollectionsKt__CollectionsKt.getLastIndex(getPages()) && size > CollectionsKt__CollectionsKt.getLastIndex(((PagingSource.LoadResult.Page) CollectionsKt___CollectionsKt.last((List) getPages())).getData())) {
                            return (Value) CollectionsKt___CollectionsKt.last((List) pagePrevious.getData());
                        }
                        return getPages().get(i).getData().get(size);
                    }
                }
                throw new NoSuchElementException("List contains no element matching the predicate.");
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    public final PagingSource.LoadResult.Page<Key, Value> closestPageToPosition(int anchorPosition) {
        List<PagingSource.LoadResult.Page<Key, Value>> list = this.pages;
        int i = 0;
        boolean z = true;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (!((PagingSource.LoadResult.Page) it.next()).getData().isEmpty()) {
                    z = false;
                    break;
                }
            }
        }
        if (z) {
            return null;
        }
        int size = anchorPosition - this.leadingPlaceholderCount;
        while (i < CollectionsKt__CollectionsKt.getLastIndex(getPages()) && size > CollectionsKt__CollectionsKt.getLastIndex(getPages().get(i).getData())) {
            size -= getPages().get(i).getData().size();
            i++;
        }
        if (size < 0) {
            return (PagingSource.LoadResult.Page) CollectionsKt___CollectionsKt.first((List) getPages());
        }
        return getPages().get(i);
    }

    public final boolean isEmpty() {
        List<PagingSource.LoadResult.Page<Key, Value>> list = this.pages;
        if ((list instanceof Collection) && list.isEmpty()) {
            return true;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (!((PagingSource.LoadResult.Page) it.next()).getData().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public final Value firstItemOrNull() {
        Object next;
        List<Value> data;
        Iterator<T> it = this.pages.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (!((PagingSource.LoadResult.Page) next).getData().isEmpty()) {
                break;
            }
        }
        PagingSource.LoadResult.Page page = (PagingSource.LoadResult.Page) next;
        if (page == null || (data = page.getData()) == null) {
            return null;
        }
        return (Value) CollectionsKt___CollectionsKt.firstOrNull((List) data);
    }

    public final Value lastItemOrNull() {
        PagingSource.LoadResult.Page<Key, Value> pagePrevious;
        List<Value> data;
        List<PagingSource.LoadResult.Page<Key, Value>> list = this.pages;
        ListIterator<PagingSource.LoadResult.Page<Key, Value>> listIterator = list.listIterator(list.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                pagePrevious = null;
                break;
            }
            pagePrevious = listIterator.previous();
            if (!pagePrevious.getData().isEmpty()) {
                break;
            }
        }
        PagingSource.LoadResult.Page<Key, Value> page = pagePrevious;
        if (page == null || (data = page.getData()) == null) {
            return null;
        }
        return (Value) CollectionsKt___CollectionsKt.lastOrNull((List) data);
    }

    public String toString() {
        return "PagingState(pages=" + this.pages + ", anchorPosition=" + this.anchorPosition + ", config=" + this.config + ", leadingPlaceholderCount=" + this.leadingPlaceholderCount + ')';
    }

    public final <T> T anchorPositionToPagedIndices$paging_common(int anchorPosition, Function2<? super Integer, ? super Integer, ? extends T> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        int size = anchorPosition - this.leadingPlaceholderCount;
        int i = 0;
        while (i < CollectionsKt__CollectionsKt.getLastIndex(getPages()) && size > CollectionsKt__CollectionsKt.getLastIndex(getPages().get(i).getData())) {
            size -= getPages().get(i).getData().size();
            i++;
        }
        return block.mo4invoke(Integer.valueOf(i), Integer.valueOf(size));
    }
}
