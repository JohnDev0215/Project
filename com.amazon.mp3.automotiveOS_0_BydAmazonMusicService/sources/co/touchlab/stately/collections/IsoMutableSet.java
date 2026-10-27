package co.touchlab.stately.collections;

import co.touchlab.stately.isolate.IsoStateKt;
import co.touchlab.stately.isolate.StateHolder;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableSet;

/* JADX INFO: compiled from: IsoMutableSet.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001d\b\u0016\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u0005¢\u0006\u0002\u0010\u0006B\u001b\b\u0000\u0012\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\b¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lco/touchlab/stately/collections/IsoMutableSet;", "T", "Lco/touchlab/stately/collections/IsoMutableCollection;", "", "producer", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)V", "stateHolder", "Lco/touchlab/stately/isolate/StateHolder;", "(Lco/touchlab/stately/isolate/StateHolder;)V", "stately-iso-collections"}, k = 1, mv = {1, 4, 0})
public class IsoMutableSet<T> extends IsoMutableCollection<T> implements Set<T>, KMutableSet {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IsoMutableSet(StateHolder<? extends Set<T>> stateHolder) {
        super(stateHolder);
        Intrinsics.checkNotNullParameter(stateHolder, "stateHolder");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public IsoMutableSet(Function0<? extends Set<T>> producer) {
        this(IsoStateKt.createState$default(producer, null, 2, null));
        Intrinsics.checkNotNullParameter(producer, "producer");
    }

    public /* synthetic */ IsoMutableSet(Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new Function0<Set<T>>() { // from class: co.touchlab.stately.collections.IsoMutableSet.1
            @Override // kotlin.jvm.functions.Function0
            public final Set<T> invoke() {
                return new LinkedHashSet();
            }
        } : function0);
    }
}
