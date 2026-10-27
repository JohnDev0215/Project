package com.jakewharton.rxbinding3.widget;

import android.widget.AutoCompleteTextView;
import io.reactivex.Observable;
import kotlin.Metadata;

/* JADX INFO: compiled from: AutoCompleteTextViewItemClickEventObservable.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"com/jakewharton/rxbinding3/widget/RxAutoCompleteTextView__AutoCompleteTextViewItemClickEventObservableKt"}, k = 4, mv = {1, 1, 15})
public final class RxAutoCompleteTextView {
    public static final Observable<AdapterViewItemClickEvent> itemClickEvents(AutoCompleteTextView autoCompleteTextView) {
        return RxAutoCompleteTextView__AutoCompleteTextViewItemClickEventObservableKt.itemClickEvents(autoCompleteTextView);
    }
}
