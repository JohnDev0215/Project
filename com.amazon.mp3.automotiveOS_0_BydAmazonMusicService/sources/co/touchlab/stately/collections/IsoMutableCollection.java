package co.touchlab.stately.collections;

import co.touchlab.stately.isolate.IsolateState;
import co.touchlab.stately.isolate.StateHolder;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableCollection;

/* JADX INFO: compiled from: IsoMutableCollection.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010)\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u00022\b\u0012\u0004\u0012\u00028\u00000\u0003B\u001d\b\u0000\u0012\u0012\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u001d¢\u0006\u0004\b\u001f\u0010 J\u0013\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0096\u0002J\b\u0010\t\u001a\u00020\bH\u0016J\u0018\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0016\u0010\u000f\u001a\u00020\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0016J\b\u0010\u0010\u001a\u00020\u0006H\u0016J\u0017\u0010\u0011\u001a\u00020\u00062\u0006\u0010\n\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0011\u0010\fJ\u0016\u0010\u0012\u001a\u00020\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0016J\b\u0010\u0014\u001a\u00020\u0013H\u0016J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u0015H\u0096\u0002J\u0017\u0010\u0017\u001a\u00020\u00062\u0006\u0010\n\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0017\u0010\fJ\u0016\u0010\u0018\u001a\u00020\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0016J\u0016\u0010\u0019\u001a\u00020\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0016R\u0014\u0010\u001c\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006!"}, d2 = {"Lco/touchlab/stately/collections/IsoMutableCollection;", "T", "Lco/touchlab/stately/isolate/IsolateState;", "", "", "other", "", "equals", "", "hashCode", "element", "contains", "(Ljava/lang/Object;)Z", "", ContextMappingConstants.ELEMENTS, "containsAll", "isEmpty", "add", "addAll", "", "clear", "", "iterator", "remove", "removeAll", "retainAll", "getSize", "()I", "size", "Lco/touchlab/stately/isolate/StateHolder;", "stateHolder", "<init>", "(Lco/touchlab/stately/isolate/StateHolder;)V", "stately-iso-collections"}, k = 1, mv = {1, 4, 0})
public class IsoMutableCollection<T> extends IsolateState<Collection<T>> implements Collection<T>, KMutableCollection {
    @Override // java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) CollectionToArray.toArray(this, tArr);
    }

    @Override // java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IsoMutableCollection(StateHolder<? extends Collection<T>> stateHolder) {
        super(stateHolder);
        Intrinsics.checkNotNullParameter(stateHolder, "stateHolder");
    }

    @Override // java.util.Collection
    public boolean equals(final Object other) {
        return ((Boolean) access(new Function1<Collection<T>, Boolean>() { // from class: co.touchlab.stately.collections.IsoMutableCollection.equals.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
                return Boolean.valueOf(invoke((Collection) obj));
            }

            public final boolean invoke(Collection<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Intrinsics.areEqual(it, other);
            }
        })).booleanValue();
    }

    @Override // java.util.Collection
    public int hashCode() {
        return ((Number) access(new Function1<Collection<T>, Integer>() { // from class: co.touchlab.stately.collections.IsoMutableCollection.hashCode.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(Object obj) {
                return Integer.valueOf(invoke((Collection) obj));
            }

            public final int invoke(Collection<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.hashCode();
            }
        })).intValue();
    }

    public int getSize() {
        return ((Number) access(new Function1<Collection<T>, Integer>() { // from class: co.touchlab.stately.collections.IsoMutableCollection.size.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(Object obj) {
                return Integer.valueOf(invoke((Collection) obj));
            }

            public final int invoke(Collection<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.size();
            }
        })).intValue();
    }

    @Override // java.util.Collection
    public boolean contains(final Object element) {
        return ((Boolean) access(new Function1<Collection<T>, Boolean>() { // from class: co.touchlab.stately.collections.IsoMutableCollection.contains.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
                return Boolean.valueOf(invoke((Collection) obj));
            }

            public final boolean invoke(Collection<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.contains(element);
            }
        })).booleanValue();
    }

    @Override // java.util.Collection
    public boolean containsAll(final Collection<? extends Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return ((Boolean) access(new Function1<Collection<T>, Boolean>() { // from class: co.touchlab.stately.collections.IsoMutableCollection.containsAll.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
                return Boolean.valueOf(invoke((Collection) obj));
            }

            public final boolean invoke(Collection<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.containsAll(elements);
            }
        })).booleanValue();
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return ((Boolean) access(new Function1<Collection<T>, Boolean>() { // from class: co.touchlab.stately.collections.IsoMutableCollection.isEmpty.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
                return Boolean.valueOf(invoke((Collection) obj));
            }

            public final boolean invoke(Collection<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.isEmpty();
            }
        })).booleanValue();
    }

    @Override // java.util.Collection
    public boolean add(final T element) {
        return ((Boolean) access(new Function1<Collection<T>, Boolean>() { // from class: co.touchlab.stately.collections.IsoMutableCollection.add.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
                return Boolean.valueOf(invoke((Collection) obj));
            }

            /* JADX WARN: Multi-variable type inference failed */
            public final boolean invoke(Collection<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.add(element);
            }
        })).booleanValue();
    }

    @Override // java.util.Collection
    public boolean addAll(final Collection<? extends T> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return ((Boolean) access(new Function1<Collection<T>, Boolean>() { // from class: co.touchlab.stately.collections.IsoMutableCollection.addAll.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
                return Boolean.valueOf(invoke((Collection) obj));
            }

            public final boolean invoke(Collection<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.addAll(elements);
            }
        })).booleanValue();
    }

    @Override // java.util.Collection
    public void clear() {
        access(new Function1<Collection<T>, Unit>() { // from class: co.touchlab.stately.collections.IsoMutableCollection.clear.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Collection) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Collection<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.clear();
            }
        });
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        return (Iterator) access(new Function1<Collection<T>, IsoMutableIterator<T>>() { // from class: co.touchlab.stately.collections.IsoMutableCollection.iterator.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final IsoMutableIterator<T> invoke(Collection<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return new IsoMutableIterator<>(IsoMutableCollection.this.fork(it.iterator()));
            }
        });
    }

    @Override // java.util.Collection
    public boolean remove(final Object element) {
        return ((Boolean) access(new Function1<Collection<T>, Boolean>() { // from class: co.touchlab.stately.collections.IsoMutableCollection.remove.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
                return Boolean.valueOf(invoke((Collection) obj));
            }

            public final boolean invoke(Collection<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.remove(element);
            }
        })).booleanValue();
    }

    @Override // java.util.Collection
    public boolean removeAll(final Collection<? extends Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return ((Boolean) access(new Function1<Collection<T>, Boolean>() { // from class: co.touchlab.stately.collections.IsoMutableCollection.removeAll.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
                return Boolean.valueOf(invoke((Collection) obj));
            }

            public final boolean invoke(Collection<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.removeAll(elements);
            }
        })).booleanValue();
    }

    @Override // java.util.Collection
    public boolean retainAll(final Collection<? extends Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return ((Boolean) access(new Function1<Collection<T>, Boolean>() { // from class: co.touchlab.stately.collections.IsoMutableCollection.retainAll.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
                return Boolean.valueOf(invoke((Collection) obj));
            }

            public final boolean invoke(Collection<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.retainAll(elements);
            }
        })).booleanValue();
    }
}
