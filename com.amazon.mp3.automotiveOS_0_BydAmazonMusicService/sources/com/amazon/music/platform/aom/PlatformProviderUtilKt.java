package com.amazon.music.platform.aom;

import com.google.firebase.perf.plugin.util.AsmString;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: PlatformProviderUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0017\u0010\u0001\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00028\u0000¢\u0006\u0004\b\u0001\u0010\u0002\"\u0011\u0010\u0006\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, d2 = {"T", "freeze", AsmString.METHOD_DESC_FIREBASE_PERF_URL_CONNECTION_INSTRUMENT_WITH_OBJECT, "Lkotlin/coroutines/CoroutineContext;", "getIoDispatcher", "()Lkotlin/coroutines/CoroutineContext;", "ioDispatcher", "MusicPlatformAOM_release"}, k = 2, mv = {1, 7, 1})
public final class PlatformProviderUtilKt {
    public static final <T> T freeze(T t) {
        return t;
    }

    public static final CoroutineContext getIoDispatcher() {
        return Dispatchers.getIO();
    }
}
