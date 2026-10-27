package com.jakewharton.rxbinding3.widget;

import android.widget.Adapter;
import android.widget.AdapterView;
import androidx.exifinterface.media.ExifInterface;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AdapterViewItemClickEventObservable.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\b\u0012\u0004\u0012\u0002H\u00030\u0005H\u0007¨\u0006\u0006"}, d2 = {"itemClickEvents", "Lio/reactivex/Observable;", "Lcom/jakewharton/rxbinding3/widget/AdapterViewItemClickEvent;", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/widget/Adapter;", "Landroid/widget/AdapterView;", "rxbinding_release"}, k = 5, mv = {1, 1, 15}, xs = "com/jakewharton/rxbinding3/widget/RxAdapterView")
final /* synthetic */ class RxAdapterView__AdapterViewItemClickEventObservableKt {
    public static final <T extends Adapter> Observable<AdapterViewItemClickEvent> itemClickEvents(AdapterView<T> itemClickEvents) {
        Intrinsics.checkParameterIsNotNull(itemClickEvents, "$this$itemClickEvents");
        return new AdapterViewItemClickEventObservable(itemClickEvents);
    }
}
