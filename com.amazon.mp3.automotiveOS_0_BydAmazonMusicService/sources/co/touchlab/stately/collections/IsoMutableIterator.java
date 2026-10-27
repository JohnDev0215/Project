package co.touchlab.stately.collections;

import co.touchlab.stately.isolate.IsolateState;
import co.touchlab.stately.isolate.StateHolder;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;

/* JADX INFO: compiled from: IsoMutableIterator.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010)\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\b\u0000\u0012\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u0007\u001a\u00020\bH\u0096\u0002J\u000e\u0010\t\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lco/touchlab/stately/collections/IsoMutableIterator;", "T", "Lco/touchlab/stately/isolate/IsolateState;", "", "stateHolder", "Lco/touchlab/stately/isolate/StateHolder;", "(Lco/touchlab/stately/isolate/StateHolder;)V", "hasNext", "", "next", "()Ljava/lang/Object;", "remove", "", "stately-iso-collections"}, k = 1, mv = {1, 4, 0})
public final class IsoMutableIterator<T> extends IsolateState<Iterator<? extends T>> implements Iterator<T>, KMutableIterator {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IsoMutableIterator(StateHolder<? extends Iterator<? extends T>> stateHolder) {
        super(stateHolder);
        Intrinsics.checkNotNullParameter(stateHolder, "stateHolder");
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return ((Boolean) access(new Function1<Iterator<? extends T>, Boolean>() { // from class: co.touchlab.stately.collections.IsoMutableIterator.hasNext.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
                return Boolean.valueOf(invoke((Iterator) obj));
            }

            public final boolean invoke(Iterator<? extends T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.hasNext();
            }
        })).booleanValue();
    }

    @Override // java.util.Iterator
    public T next() {
        return (T) access(new Function1<Iterator<? extends T>, T>() { // from class: co.touchlab.stately.collections.IsoMutableIterator.next.1
            @Override // kotlin.jvm.functions.Function1
            public final T invoke(Iterator<? extends T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.next();
            }
        });
    }

    @Override // java.util.Iterator
    public void remove() {
        access(new Function1<Iterator<? extends T>, Unit>() { // from class: co.touchlab.stately.collections.IsoMutableIterator.remove.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Iterator) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Iterator<? extends T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.remove();
            }
        });
    }
}
