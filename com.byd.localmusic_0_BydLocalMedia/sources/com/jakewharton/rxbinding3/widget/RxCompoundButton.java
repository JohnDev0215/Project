package com.jakewharton.rxbinding3.widget;

import android.widget.CompoundButton;
import com.jakewharton.rxbinding3.InitialValueObservable;
import kotlin.Metadata;

/* JADX INFO: compiled from: CompoundButtonCheckedChangeObservable.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"com/jakewharton/rxbinding3/widget/RxCompoundButton__CompoundButtonCheckedChangeObservableKt"}, k = 4, mv = {1, 1, 15})
public final class RxCompoundButton {
    public static final InitialValueObservable<Boolean> checkedChanges(CompoundButton compoundButton) {
        return RxCompoundButton__CompoundButtonCheckedChangeObservableKt.checkedChanges(compoundButton);
    }
}
