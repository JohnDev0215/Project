package com.jakewharton.rxbinding3.view;

import android.view.ViewGroup;
import io.reactivex.Observable;
import kotlin.Metadata;

/* JADX INFO: compiled from: ViewGroupHierarchyChangeEventObservable.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"com/jakewharton/rxbinding3/view/RxViewGroup__ViewGroupHierarchyChangeEventObservableKt"}, k = 4, mv = {1, 1, 15})
public final class RxViewGroup {
    public static final Observable<ViewGroupHierarchyChangeEvent> changeEvents(ViewGroup viewGroup) {
        return RxViewGroup__ViewGroupHierarchyChangeEventObservableKt.changeEvents(viewGroup);
    }
}
