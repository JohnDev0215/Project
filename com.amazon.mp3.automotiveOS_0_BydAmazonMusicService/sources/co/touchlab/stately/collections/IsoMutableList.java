package co.touchlab.stately.collections;

import co.touchlab.stately.isolate.StateHolder;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.jvm.internal.markers.KMutableList;

/* JADX INFO: compiled from: IsoMutableList.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010+\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u00022\b\u0012\u0004\u0012\u00028\u00000\u0003B\u001d\b\u0000\u0012\u0012\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u001b¢\u0006\u0004\b\u001d\u0010\u001eJ\u0018\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0096\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000b\u001a\u00020\u00042\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u000b\u0010\nJ\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u001e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0016J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0016J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u00132\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0017\u0010\u0015\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0015\u0010\u0007J \u0010\u0016\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\b\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u001e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0004H\u0016¨\u0006\u001f"}, d2 = {"Lco/touchlab/stately/collections/IsoMutableList;", "T", "Lco/touchlab/stately/collections/IsoMutableCollection;", "", "", "index", "get", "(I)Ljava/lang/Object;", "element", "indexOf", "(Ljava/lang/Object;)I", "lastIndexOf", "", "add", "(ILjava/lang/Object;)V", "", ContextMappingConstants.ELEMENTS, "", "addAll", "", "listIterator", "removeAt", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "fromIndex", "toIndex", "subList", "Lco/touchlab/stately/isolate/StateHolder;", "stateHolder", "<init>", "(Lco/touchlab/stately/isolate/StateHolder;)V", "stately-iso-collections"}, k = 1, mv = {1, 4, 0})
public class IsoMutableList<T> extends IsoMutableCollection<T> implements List<T>, KMutableList {
    @Override // java.util.List
    public final /* bridge */ T remove(int i) {
        return removeAt(i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IsoMutableList(StateHolder<? extends List<T>> stateHolder) {
        super(stateHolder);
        Intrinsics.checkNotNullParameter(stateHolder, "stateHolder");
    }

    @Override // java.util.List
    public void add(final int index, final T element) {
        access(new Function1<Collection<T>, Unit>() { // from class: co.touchlab.stately.collections.IsoMutableList$add$$inlined$asAccess$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(Collection<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                TypeIntrinsics.asMutableList(it).add(index, element);
                return Unit.INSTANCE;
            }
        });
    }

    @Override // java.util.List
    public boolean addAll(final int index, final Collection<? extends T> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return ((Boolean) access(new Function1<Collection<T>, Boolean>() { // from class: co.touchlab.stately.collections.IsoMutableList$addAll$$inlined$asAccess$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Collection<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Boolean.valueOf(TypeIntrinsics.asMutableList(it).addAll(index, elements));
            }
        })).booleanValue();
    }

    @Override // java.util.List
    public T get(final int index) {
        return (T) access(new Function1<Collection<T>, T>() { // from class: co.touchlab.stately.collections.IsoMutableList$get$$inlined$asAccess$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final T invoke(Collection<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return (T) TypeIntrinsics.asMutableList(it).get(index);
            }
        });
    }

    @Override // java.util.List
    public int indexOf(final Object element) {
        return ((Number) access(new Function1<Collection<T>, Integer>() { // from class: co.touchlab.stately.collections.IsoMutableList$indexOf$$inlined$asAccess$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Integer invoke(Collection<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Integer.valueOf(TypeIntrinsics.asMutableList(it).indexOf(element));
            }
        })).intValue();
    }

    @Override // java.util.List
    public int lastIndexOf(final Object element) {
        return ((Number) access(new Function1<Collection<T>, Integer>() { // from class: co.touchlab.stately.collections.IsoMutableList$lastIndexOf$$inlined$asAccess$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Integer invoke(Collection<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Integer.valueOf(TypeIntrinsics.asMutableList(it).lastIndexOf(element));
            }
        })).intValue();
    }

    @Override // java.util.List
    public ListIterator<T> listIterator() {
        return (ListIterator) access(new Function1<Collection<T>, IsoMutableListIterator<T>>() { // from class: co.touchlab.stately.collections.IsoMutableList$listIterator$$inlined$asAccess$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final IsoMutableListIterator<T> invoke(Collection<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return new IsoMutableListIterator<>(this.this$0.fork(TypeIntrinsics.asMutableList(it).listIterator()));
            }
        });
    }

    @Override // java.util.List
    public ListIterator<T> listIterator(final int index) {
        return (ListIterator) access(new Function1<Collection<T>, IsoMutableListIterator<T>>() { // from class: co.touchlab.stately.collections.IsoMutableList$listIterator$$inlined$asAccess$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final IsoMutableListIterator<T> invoke(Collection<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return new IsoMutableListIterator<>(this.this$0.fork(TypeIntrinsics.asMutableList(it).listIterator(index)));
            }
        });
    }

    public T removeAt(final int index) {
        return (T) access(new Function1<Collection<T>, T>() { // from class: co.touchlab.stately.collections.IsoMutableList$removeAt$$inlined$asAccess$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final T invoke(Collection<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return (T) TypeIntrinsics.asMutableList(it).remove(index);
            }
        });
    }

    @Override // java.util.List
    public T set(final int index, final T element) {
        return (T) access(new Function1<Collection<T>, T>() { // from class: co.touchlab.stately.collections.IsoMutableList$set$$inlined$asAccess$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final T invoke(Collection<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return (T) TypeIntrinsics.asMutableList(it).set(index, element);
            }
        });
    }

    @Override // java.util.List
    public List<T> subList(final int fromIndex, final int toIndex) {
        return (List) access(new Function1<Collection<T>, IsoMutableList<T>>() { // from class: co.touchlab.stately.collections.IsoMutableList$subList$$inlined$asAccess$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final IsoMutableList<T> invoke(Collection<T> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return new IsoMutableList<>(this.this$0.fork(TypeIntrinsics.asMutableList(it).subList(fromIndex, toIndex)));
            }
        });
    }
}
