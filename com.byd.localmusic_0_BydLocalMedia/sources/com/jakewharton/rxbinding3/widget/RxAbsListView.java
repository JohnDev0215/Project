package com.jakewharton.rxbinding3.widget;

import android.widget.AbsListView;
import io.reactivex.Observable;
import kotlin.Metadata;

/* JADX INFO: compiled from: AbsListViewScrollEventObservable.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"com/jakewharton/rxbinding3/widget/RxAbsListView__AbsListViewScrollEventObservableKt"}, k = 4, mv = {1, 1, 15})
public final class RxAbsListView {
    public static final Observable<AbsListViewScrollEvent> scrollEvents(AbsListView absListView) {
        return RxAbsListView__AbsListViewScrollEventObservableKt.scrollEvents(absListView);
    }
}
