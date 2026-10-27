package androidx.paging;

import androidx.paging.LoadState;
import androidx.paging.PageEvent;
import androidx.paging.ViewportHint;
import com.amazon.music.find.utils.PageUriUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* JADX INFO: compiled from: PagePresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\b\u0000\u0018\u0000 7*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u000278B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\bJ\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\bH\u0002J\u001e\u0010\u001e\u001a\u00020\u001d2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000 2\u0006\u0010!\u001a\u00020\"H\u0002J\u0010\u0010#\u001a\u00020\b2\u0006\u0010$\u001a\u00020%H\u0002J\u0015\u0010&\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u001b\u001a\u00020\b¢\u0006\u0002\u0010'J\u0015\u0010(\u001a\u00028\u00002\u0006\u0010)\u001a\u00020\bH\u0016¢\u0006\u0002\u0010'J\u0006\u0010*\u001a\u00020+J\u001e\u0010,\u001a\u00020\u001d2\f\u0010-\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\u0006\u0010!\u001a\u00020\"H\u0002J\u001c\u0010.\u001a\u00020\u001d2\f\u0010/\u001a\b\u0012\u0004\u0012\u00028\u0000002\u0006\u0010!\u001a\u00020\"J\f\u00101\u001a\b\u0012\u0004\u0012\u00028\u000002J\b\u00103\u001a\u000204H\u0016J\u0018\u00105\u001a\u00020\b*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000f06H\u0002R\u0014\u0010\u0007\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\nR\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\nR\u001e\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\nR\u0014\u0010\u0015\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\nR\u001e\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\n¨\u00069"}, d2 = {"Landroidx/paging/PagePresenter;", "T", "", "Landroidx/paging/NullPaddedList;", "insertEvent", "Landroidx/paging/PageEvent$Insert;", "(Landroidx/paging/PageEvent$Insert;)V", "originalPageOffsetFirst", "", "getOriginalPageOffsetFirst", "()I", "originalPageOffsetLast", "getOriginalPageOffsetLast", "pages", "", "Landroidx/paging/TransformablePage;", "<set-?>", "placeholdersAfter", "getPlaceholdersAfter", "placeholdersBefore", "getPlaceholdersBefore", "size", "getSize", "storageCount", "getStorageCount", "accessHintForPresenterIndex", "Landroidx/paging/ViewportHint$Access;", "index", "checkIndex", "", "dropPages", "drop", "Landroidx/paging/PageEvent$Drop;", "callback", "Landroidx/paging/PagePresenter$ProcessPageEventCallback;", "dropPagesWithOffsets", "pageOffsetsToDrop", "Lkotlin/ranges/IntRange;", "get", "(I)Ljava/lang/Object;", "getFromStorage", "localIndex", "initializeHint", "Landroidx/paging/ViewportHint$Initial;", "insertPage", "insert", "processEvent", "pageEvent", "Landroidx/paging/PageEvent;", "snapshot", "Landroidx/paging/ItemSnapshotList;", "toString", "", "fullCount", "", "Companion", "ProcessPageEventCallback", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class PagePresenter<T> implements NullPaddedList<T> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final PagePresenter<Object> INITIAL = new PagePresenter<>(PageEvent.Insert.INSTANCE.getEMPTY_REFRESH_LOCAL());
    private final List<TransformablePage<T>> pages;
    private int placeholdersAfter;
    private int placeholdersBefore;
    private int storageCount;

    /* JADX INFO: compiled from: PagePresenter.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u001a\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH&J \u0010\t\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H&¨\u0006\u0013"}, d2 = {"Landroidx/paging/PagePresenter$ProcessPageEventCallback;", "", "onChanged", "", "position", "", "count", "onInserted", "onRemoved", "onStateUpdate", PageUriUtils.SOURCE_QUERY_PARAM, "Landroidx/paging/LoadStates;", "mediator", "loadType", "Landroidx/paging/LoadType;", "fromMediator", "", "loadState", "Landroidx/paging/LoadState;", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public interface ProcessPageEventCallback {
        void onChanged(int position, int count);

        void onInserted(int position, int count);

        void onRemoved(int position, int count);

        void onStateUpdate(LoadStates source, LoadStates mediator);

        void onStateUpdate(LoadType loadType, boolean fromMediator, LoadState loadState);
    }

    /* JADX INFO: compiled from: PagePresenter.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LoadType.values().length];
            iArr[LoadType.REFRESH.ordinal()] = 1;
            iArr[LoadType.PREPEND.ordinal()] = 2;
            iArr[LoadType.APPEND.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public PagePresenter(PageEvent.Insert<T> insertEvent) {
        Intrinsics.checkNotNullParameter(insertEvent, "insertEvent");
        this.pages = CollectionsKt___CollectionsKt.toMutableList((Collection) insertEvent.getPages());
        this.storageCount = fullCount(insertEvent.getPages());
        this.placeholdersBefore = insertEvent.getPlaceholdersBefore();
        this.placeholdersAfter = insertEvent.getPlaceholdersAfter();
    }

    @Override // androidx.paging.NullPaddedList
    public int getStorageCount() {
        return this.storageCount;
    }

    private final int getOriginalPageOffsetFirst() {
        Integer numMinOrNull = ArraysKt___ArraysKt.minOrNull(((TransformablePage) CollectionsKt___CollectionsKt.first((List) this.pages)).getOriginalPageOffsets());
        Intrinsics.checkNotNull(numMinOrNull);
        return numMinOrNull.intValue();
    }

    private final int getOriginalPageOffsetLast() {
        Integer numMaxOrNull = ArraysKt___ArraysKt.maxOrNull(((TransformablePage) CollectionsKt___CollectionsKt.last((List) this.pages)).getOriginalPageOffsets());
        Intrinsics.checkNotNull(numMaxOrNull);
        return numMaxOrNull.intValue();
    }

    @Override // androidx.paging.NullPaddedList
    public int getPlaceholdersBefore() {
        return this.placeholdersBefore;
    }

    @Override // androidx.paging.NullPaddedList
    public int getPlaceholdersAfter() {
        return this.placeholdersAfter;
    }

    private final void checkIndex(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + getSize());
        }
    }

    public String toString() {
        int storageCount = getStorageCount();
        ArrayList arrayList = new ArrayList(storageCount);
        for (int i = 0; i < storageCount; i++) {
            arrayList.add(getFromStorage(i));
        }
        return "[(" + getPlaceholdersBefore() + " placeholders), " + CollectionsKt___CollectionsKt.joinToString$default(arrayList, null, null, null, 0, null, null, 63, null) + ", (" + getPlaceholdersAfter() + " placeholders)]";
    }

    public final T get(int index) {
        checkIndex(index);
        int placeholdersBefore = index - getPlaceholdersBefore();
        if (placeholdersBefore < 0 || placeholdersBefore >= getStorageCount()) {
            return null;
        }
        return getFromStorage(placeholdersBefore);
    }

    public final ItemSnapshotList<T> snapshot() {
        int placeholdersBefore = getPlaceholdersBefore();
        int placeholdersAfter = getPlaceholdersAfter();
        List<TransformablePage<T>> list = this.pages;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, ((TransformablePage) it.next()).getData());
        }
        return new ItemSnapshotList<>(placeholdersBefore, placeholdersAfter, arrayList);
    }

    @Override // androidx.paging.NullPaddedList
    public T getFromStorage(int localIndex) {
        int size = this.pages.size();
        int i = 0;
        while (i < size) {
            int size2 = this.pages.get(i).getData().size();
            if (size2 > localIndex) {
                break;
            }
            localIndex -= size2;
            i++;
        }
        return this.pages.get(i).getData().get(localIndex);
    }

    @Override // androidx.paging.NullPaddedList
    public int getSize() {
        return getPlaceholdersBefore() + getStorageCount() + getPlaceholdersAfter();
    }

    private final int fullCount(List<TransformablePage<T>> list) {
        Iterator<T> it = list.iterator();
        int size = 0;
        while (it.hasNext()) {
            size += ((TransformablePage) it.next()).getData().size();
        }
        return size;
    }

    public final void processEvent(PageEvent<T> pageEvent, ProcessPageEventCallback callback) {
        Intrinsics.checkNotNullParameter(pageEvent, "pageEvent");
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (pageEvent instanceof PageEvent.Insert) {
            insertPage((PageEvent.Insert) pageEvent, callback);
            return;
        }
        if (pageEvent instanceof PageEvent.Drop) {
            dropPages((PageEvent.Drop) pageEvent, callback);
        } else if (pageEvent instanceof PageEvent.LoadStateUpdate) {
            PageEvent.LoadStateUpdate loadStateUpdate = (PageEvent.LoadStateUpdate) pageEvent;
            callback.onStateUpdate(loadStateUpdate.getSource(), loadStateUpdate.getMediator());
        }
    }

    public final ViewportHint.Initial initializeHint() {
        int storageCount = getStorageCount() / 2;
        return new ViewportHint.Initial(storageCount, storageCount, getOriginalPageOffsetFirst(), getOriginalPageOffsetLast());
    }

    public final ViewportHint.Access accessHintForPresenterIndex(int index) {
        int i = 0;
        int placeholdersBefore = index - getPlaceholdersBefore();
        while (placeholdersBefore >= this.pages.get(i).getData().size() && i < CollectionsKt__CollectionsKt.getLastIndex(this.pages)) {
            placeholdersBefore -= this.pages.get(i).getData().size();
            i++;
        }
        return this.pages.get(i).viewportHintFor(placeholdersBefore, index - getPlaceholdersBefore(), ((getSize() - index) - getPlaceholdersAfter()) - 1, getOriginalPageOffsetFirst(), getOriginalPageOffsetLast());
    }

    private final void insertPage(PageEvent.Insert<T> insert, ProcessPageEventCallback callback) {
        int iFullCount = fullCount(insert.getPages());
        int size = getSize();
        int i = WhenMappings.$EnumSwitchMapping$0[insert.getLoadType().ordinal()];
        if (i == 1) {
            throw new IllegalArgumentException();
        }
        if (i == 2) {
            int iMin = Math.min(getPlaceholdersBefore(), iFullCount);
            int placeholdersBefore = getPlaceholdersBefore() - iMin;
            int i2 = iFullCount - iMin;
            this.pages.addAll(0, insert.getPages());
            this.storageCount = getStorageCount() + iFullCount;
            this.placeholdersBefore = insert.getPlaceholdersBefore();
            callback.onChanged(placeholdersBefore, iMin);
            callback.onInserted(0, i2);
            int size2 = (getSize() - size) - i2;
            if (size2 > 0) {
                callback.onInserted(0, size2);
            } else if (size2 < 0) {
                callback.onRemoved(0, -size2);
            }
        } else if (i == 3) {
            int iMin2 = Math.min(getPlaceholdersAfter(), iFullCount);
            int placeholdersBefore2 = getPlaceholdersBefore() + getStorageCount();
            int i3 = iFullCount - iMin2;
            List<TransformablePage<T>> list = this.pages;
            list.addAll(list.size(), insert.getPages());
            this.storageCount = getStorageCount() + iFullCount;
            this.placeholdersAfter = insert.getPlaceholdersAfter();
            callback.onChanged(placeholdersBefore2, iMin2);
            callback.onInserted(placeholdersBefore2 + iMin2, i3);
            int size3 = (getSize() - size) - i3;
            if (size3 > 0) {
                callback.onInserted(getSize() - size3, size3);
            } else if (size3 < 0) {
                callback.onRemoved(getSize(), -size3);
            }
        }
        callback.onStateUpdate(insert.getSourceLoadStates(), insert.getMediatorLoadStates());
    }

    private final int dropPagesWithOffsets(IntRange pageOffsetsToDrop) {
        boolean z;
        Iterator<TransformablePage<T>> it = this.pages.iterator();
        int size = 0;
        while (it.hasNext()) {
            TransformablePage<T> next = it.next();
            int[] originalPageOffsets = next.getOriginalPageOffsets();
            int length = originalPageOffsets.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = false;
                    break;
                }
                if (pageOffsetsToDrop.contains(originalPageOffsets[i])) {
                    z = true;
                    break;
                }
                i++;
            }
            if (z) {
                size += next.getData().size();
                it.remove();
            }
        }
        return size;
    }

    private final void dropPages(PageEvent.Drop<T> drop, ProcessPageEventCallback callback) {
        int size = getSize();
        LoadType loadType = drop.getLoadType();
        LoadType loadType2 = LoadType.PREPEND;
        if (loadType == loadType2) {
            int placeholdersBefore = getPlaceholdersBefore();
            this.storageCount = getStorageCount() - dropPagesWithOffsets(new IntRange(drop.getMinPageOffset(), drop.getMaxPageOffset()));
            this.placeholdersBefore = drop.getPlaceholdersRemaining();
            int size2 = getSize() - size;
            if (size2 > 0) {
                callback.onInserted(0, size2);
            } else if (size2 < 0) {
                callback.onRemoved(0, -size2);
            }
            int iMax = Math.max(0, placeholdersBefore + size2);
            int placeholdersRemaining = drop.getPlaceholdersRemaining() - iMax;
            if (placeholdersRemaining > 0) {
                callback.onChanged(iMax, placeholdersRemaining);
            }
            callback.onStateUpdate(loadType2, false, LoadState.NotLoading.INSTANCE.getIncomplete$paging_common());
            return;
        }
        int placeholdersAfter = getPlaceholdersAfter();
        this.storageCount = getStorageCount() - dropPagesWithOffsets(new IntRange(drop.getMinPageOffset(), drop.getMaxPageOffset()));
        this.placeholdersAfter = drop.getPlaceholdersRemaining();
        int size3 = getSize() - size;
        if (size3 > 0) {
            callback.onInserted(size, size3);
        } else if (size3 < 0) {
            callback.onRemoved(size + size3, -size3);
        }
        int placeholdersRemaining2 = drop.getPlaceholdersRemaining() - (placeholdersAfter - (size3 < 0 ? Math.min(placeholdersAfter, -size3) : 0));
        if (placeholdersRemaining2 > 0) {
            callback.onChanged(getSize() - drop.getPlaceholdersRemaining(), placeholdersRemaining2);
        }
        callback.onStateUpdate(LoadType.APPEND, false, LoadState.NotLoading.INSTANCE.getIncomplete$paging_common());
    }

    /* JADX INFO: compiled from: PagePresenter.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0004\"\b\b\u0001\u0010\u0006*\u00020\u0001H\u0000¢\u0006\u0002\b\u0007R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Landroidx/paging/PagePresenter$Companion;", "", "()V", "INITIAL", "Landroidx/paging/PagePresenter;", "initial", "T", "initial$paging_common", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final <T> PagePresenter<T> initial$paging_common() {
            return PagePresenter.INITIAL;
        }
    }
}
