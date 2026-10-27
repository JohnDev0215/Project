package com.amazon.mp3.platform.providers;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: DispatcherProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0002H\u0016¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/platform/providers/DispatcherProvider;", "", "Lkotlinx/coroutines/CoroutineDispatcher;", "io", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public interface DispatcherProvider {
    CoroutineDispatcher io();

    /* JADX INFO: compiled from: DispatcherProvider.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static CoroutineDispatcher io(DispatcherProvider dispatcherProvider) {
            Intrinsics.checkNotNullParameter(dispatcherProvider, "this");
            return Dispatchers.getIO();
        }
    }
}
