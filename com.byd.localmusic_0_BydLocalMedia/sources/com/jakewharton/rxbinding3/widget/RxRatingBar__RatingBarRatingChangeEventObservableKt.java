package com.jakewharton.rxbinding3.widget;

import android.widget.RatingBar;
import com.jakewharton.rxbinding3.InitialValueObservable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RatingBarRatingChangeEventObservable.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u0007¨\u0006\u0004"}, d2 = {"ratingChangeEvents", "Lcom/jakewharton/rxbinding3/InitialValueObservable;", "Lcom/jakewharton/rxbinding3/widget/RatingBarChangeEvent;", "Landroid/widget/RatingBar;", "rxbinding_release"}, k = 5, mv = {1, 1, 15}, xs = "com/jakewharton/rxbinding3/widget/RxRatingBar")
final /* synthetic */ class RxRatingBar__RatingBarRatingChangeEventObservableKt {
    public static final InitialValueObservable<RatingBarChangeEvent> ratingChangeEvents(RatingBar ratingChangeEvents) {
        Intrinsics.checkParameterIsNotNull(ratingChangeEvents, "$this$ratingChangeEvents");
        return new RatingBarRatingChangeEventObservable(ratingChangeEvents);
    }
}
