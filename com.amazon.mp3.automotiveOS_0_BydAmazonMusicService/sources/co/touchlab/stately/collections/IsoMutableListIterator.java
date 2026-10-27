package co.touchlab.stately.collections;

import co.touchlab.stately.isolate.IsolateState;
import co.touchlab.stately.isolate.StateHolder;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableListIterator;

/* JADX INFO: compiled from: IsoMutableList.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010+\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\b\u0000\u0012\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u0005¢\u0006\u0002\u0010\u0006J\u0015\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\nJ\t\u0010\u000b\u001a\u00020\fH\u0096\u0002J\b\u0010\r\u001a\u00020\fH\u0016J\u000e\u0010\u000e\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u000fJ\b\u0010\u0010\u001a\u00020\u0011H\u0016J\r\u0010\u0012\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000fJ\b\u0010\u0013\u001a\u00020\u0011H\u0016J\b\u0010\u0014\u001a\u00020\bH\u0016J\u0015\u0010\u0015\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\n¨\u0006\u0016"}, d2 = {"Lco/touchlab/stately/collections/IsoMutableListIterator;", "T", "Lco/touchlab/stately/isolate/IsolateState;", "", "stateHolder", "Lco/touchlab/stately/isolate/StateHolder;", "(Lco/touchlab/stately/isolate/StateHolder;)V", "add", "", "element", "(Ljava/lang/Object;)V", "hasNext", "", "hasPrevious", "next", "()Ljava/lang/Object;", "nextIndex", "", "previous", "previousIndex", "remove", "set", "stately-iso-collections"}, k = 1, mv = {1, 4, 0})
public final class IsoMutableListIterator<T> extends IsolateState<ListIterator<T>> implements ListIterator<T>, KMutableListIterator {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IsoMutableListIterator(StateHolder<? extends ListIterator<T>> stateHolder) {
        super(stateHolder);
        Intrinsics.checkNotNullParameter(stateHolder, "stateHolder");
    }

    @Override // java.util.ListIterator
    public boolean hasPrevious() {
        return ((Boolean) access(new Function1<ListIterator<T>, Boolean>() { // from class: co.touchlab.stately.collections.IsoMutableListIterator.hasPrevious.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
                return Boolean.valueOf(invoke((ListIterator) obj));
            }

            public final boolean invoke(ListIterator<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.hasPrevious();
            }
        })).booleanValue();
    }

    @Override // java.util.ListIterator
    public int nextIndex() {
        return ((Number) access(new Function1<ListIterator<T>, Integer>() { // from class: co.touchlab.stately.collections.IsoMutableListIterator.nextIndex.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(Object obj) {
                return Integer.valueOf(invoke((ListIterator) obj));
            }

            public final int invoke(ListIterator<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.nextIndex();
            }
        })).intValue();
    }

    @Override // java.util.ListIterator
    public T previous() {
        return (T) access(new Function1<ListIterator<T>, T>() { // from class: co.touchlab.stately.collections.IsoMutableListIterator.previous.1
            @Override // kotlin.jvm.functions.Function1
            public final T invoke(ListIterator<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.previous();
            }
        });
    }

    @Override // java.util.ListIterator
    public int previousIndex() {
        return ((Number) access(new Function1<ListIterator<T>, Integer>() { // from class: co.touchlab.stately.collections.IsoMutableListIterator.previousIndex.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(Object obj) {
                return Integer.valueOf(invoke((ListIterator) obj));
            }

            public final int invoke(ListIterator<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.previousIndex();
            }
        })).intValue();
    }

    @Override // java.util.ListIterator
    public void add(final T element) {
        access(new Function1<ListIterator<T>, Unit>() { // from class: co.touchlab.stately.collections.IsoMutableListIterator.add.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((ListIterator) obj);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            public final void invoke(ListIterator<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.add(element);
            }
        });
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public boolean hasNext() {
        return ((Boolean) access(new Function1<ListIterator<T>, Boolean>() { // from class: co.touchlab.stately.collections.IsoMutableListIterator.hasNext.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
                return Boolean.valueOf(invoke((ListIterator) obj));
            }

            public final boolean invoke(ListIterator<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.hasNext();
            }
        })).booleanValue();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public T next() {
        return (T) access(new Function1<ListIterator<T>, T>() { // from class: co.touchlab.stately.collections.IsoMutableListIterator.next.1
            @Override // kotlin.jvm.functions.Function1
            public final T invoke(ListIterator<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.next();
            }
        });
    }

    @Override // java.util.ListIterator
    public void set(final T element) {
        access(new Function1<ListIterator<T>, Unit>() { // from class: co.touchlab.stately.collections.IsoMutableListIterator.set.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((ListIterator) obj);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            public final void invoke(ListIterator<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.set(element);
            }
        });
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public void remove() {
        access(new Function1<ListIterator<T>, Unit>() { // from class: co.touchlab.stately.collections.IsoMutableListIterator.remove.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((ListIterator) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(ListIterator<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.remove();
            }
        });
    }
}
