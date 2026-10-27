package com.amazon.mp3.navigation;

import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.coroutines.LookupError;
import com.amazon.music.browse.BrowseException;
import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineExceptionHandler;

/* JADX INFO: compiled from: CoroutineExceptionHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\u00020\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t¸\u0006\u0000"}, d2 = {"kotlinx/coroutines/CoroutineExceptionHandlerKt$CoroutineExceptionHandler$1", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "handleException", "", "context", "Lkotlin/coroutines/CoroutineContext;", "exception", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StationDestinationHandler$special$$inlined$CoroutineExceptionHandler$1 extends AbstractCoroutineContextElement implements CoroutineExceptionHandler {
    final /* synthetic */ Function1 $onError$inlined;
    final /* synthetic */ StationDestinationHandler this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StationDestinationHandler$special$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.Companion companion, StationDestinationHandler stationDestinationHandler, Function1 function1) {
        super(companion);
        this.this$0 = stationDestinationHandler;
        this.$onError$inlined = function1;
    }

    @Override // kotlinx.coroutines.CoroutineExceptionHandler
    public void handleException(CoroutineContext context, Throwable exception) {
        Log.error(StationDestinationHandler.TAG, "Received an Error when looking up Station key: " + this.this$0.getStationKey() + "  Error: " + exception);
        if ((exception instanceof BrowseException) && ((BrowseException) exception).getReason() == BrowseException.Reason.NETWORK_ERROR) {
            this.$onError$inlined.invoke(LookupError.NetworkError.INSTANCE);
        } else {
            this.$onError$inlined.invoke(LookupError.UnknownError.INSTANCE);
        }
    }
}
