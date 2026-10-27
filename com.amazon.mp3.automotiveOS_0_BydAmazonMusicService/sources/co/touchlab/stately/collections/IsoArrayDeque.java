package co.touchlab.stately.collections;

import co.touchlab.stately.isolate.IsoStateKt;
import co.touchlab.stately.isolate.StateHolder;
import java.util.Collection;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IsoArrayDeque.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0017\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B\u001d\b\u0000\u0012\u0012\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u0006¢\u0006\u0004\b\t\u0010\nB\u001f\b\u0016\u0012\u0014\b\u0002\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u000b¢\u0006\u0004\b\t\u0010\rJ\r\u0010\u0003\u001a\u00028\u0000¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0005\u0010\u0004¨\u0006\u000e"}, d2 = {"Lco/touchlab/stately/collections/IsoArrayDeque;", "E", "Lco/touchlab/stately/collections/IsoMutableList;", "first", "()Ljava/lang/Object;", "removeFirstOrNull", "Lco/touchlab/stately/isolate/StateHolder;", "Lkotlin/collections/ArrayDeque;", "stateHolder", "<init>", "(Lco/touchlab/stately/isolate/StateHolder;)V", "Lkotlin/Function0;", "producer", "(Lkotlin/jvm/functions/Function0;)V", "stately-iso-collections"}, k = 1, mv = {1, 4, 0})
@ExperimentalStdlibApi
public class IsoArrayDeque<E> extends IsoMutableList<E> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IsoArrayDeque(StateHolder<ArrayDeque<E>> stateHolder) {
        super(stateHolder);
        Intrinsics.checkNotNullParameter(stateHolder, "stateHolder");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public IsoArrayDeque(Function0<ArrayDeque<E>> producer) {
        this(IsoStateKt.createState$default(producer, null, 2, null));
        Intrinsics.checkNotNullParameter(producer, "producer");
    }

    public /* synthetic */ IsoArrayDeque(Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new Function0<ArrayDeque<E>>() { // from class: co.touchlab.stately.collections.IsoArrayDeque.1
            @Override // kotlin.jvm.functions.Function0
            public final ArrayDeque<E> invoke() {
                return new ArrayDeque<>();
            }
        } : function0);
    }

    public final E first() {
        return (E) access(new Function1<Collection<E>, E>() { // from class: co.touchlab.stately.collections.IsoArrayDeque$first$$inlined$asAccess$1
            @Override // kotlin.jvm.functions.Function1
            public final E invoke(Collection<E> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return (E) ((ArrayDeque) it).first();
            }
        });
    }

    public final E removeFirstOrNull() {
        return (E) access(new Function1<Collection<E>, E>() { // from class: co.touchlab.stately.collections.IsoArrayDeque$removeFirstOrNull$$inlined$asAccess$1
            @Override // kotlin.jvm.functions.Function1
            public final E invoke(Collection<E> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return (E) ((ArrayDeque) it).removeFirstOrNull();
            }
        });
    }
}
