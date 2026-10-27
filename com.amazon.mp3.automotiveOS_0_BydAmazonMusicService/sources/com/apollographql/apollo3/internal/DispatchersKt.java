package com.apollographql.apollo3.internal;

import kotlin.Metadata;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: dispatchers.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0000¨\u0006\u0003"}, d2 = {"defaultDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "requested", "apollo-runtime"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class DispatchersKt {
    public static final CoroutineDispatcher defaultDispatcher(CoroutineDispatcher coroutineDispatcher) {
        return coroutineDispatcher == null ? Dispatchers.getIO() : coroutineDispatcher;
    }
}
