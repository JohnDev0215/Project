package com.amazon.identity.auth.device.api;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public interface MAPFuture<T> {
    @FireOsSdk
    T get() throws ExecutionException, MAPCallbackErrorException, InterruptedException;

    @FireOsSdk
    T get(long j, TimeUnit timeUnit) throws ExecutionException, MAPCallbackErrorException, InterruptedException, TimeoutException;
}
