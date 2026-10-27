package androidx.paging;

import androidx.annotation.IntRange;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ItemSnapshotList.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\f\u0018\u0000*\u0004\b\u0000\u0010\u00012\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0002B'\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\u0010\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0011\u001a\u00020\u0004H\u0096\u0002¢\u0006\u0002\u0010\u0012R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0014\u0010\u000e\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\f¨\u0006\u0013"}, d2 = {"Landroidx/paging/ItemSnapshotList;", "T", "Lkotlin/collections/AbstractList;", "placeholdersBefore", "", "placeholdersAfter", ContextMappingConstants.ITEMS, "", "(IILjava/util/List;)V", "getItems", "()Ljava/util/List;", "getPlaceholdersAfter", "()I", "getPlaceholdersBefore", "size", "getSize", "get", "index", "(I)Ljava/lang/Object;", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ItemSnapshotList<T> extends AbstractList<T> {
    private final List<T> items;
    private final int placeholdersAfter;
    private final int placeholdersBefore;

    public final int getPlaceholdersBefore() {
        return this.placeholdersBefore;
    }

    public final int getPlaceholdersAfter() {
        return this.placeholdersAfter;
    }

    public final List<T> getItems() {
        return this.items;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ItemSnapshotList(@IntRange(from = 0) int i, @IntRange(from = 0) int i2, List<? extends T> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        this.placeholdersBefore = i;
        this.placeholdersAfter = i2;
        this.items = items;
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    /* JADX INFO: renamed from: getSize */
    public int get_size() {
        return this.placeholdersBefore + this.items.size() + this.placeholdersAfter;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public T get(int index) {
        if (index >= 0 && index < this.placeholdersBefore) {
            return null;
        }
        int i = this.placeholdersBefore;
        if (index < this.items.size() + i && i <= index) {
            return this.items.get(index - this.placeholdersBefore);
        }
        if (index < size() && this.placeholdersBefore + this.items.size() <= index) {
            return null;
        }
        throw new IndexOutOfBoundsException("Illegal attempt to access index " + index + " in ItemSnapshotList of size " + size());
    }
}
