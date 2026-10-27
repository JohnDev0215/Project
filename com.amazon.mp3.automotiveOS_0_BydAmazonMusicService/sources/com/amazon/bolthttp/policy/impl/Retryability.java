package com.amazon.bolthttp.policy.impl;

import com.amazon.bolthttp.ConnectivityTimeoutException;
import com.google.common.collect.ImmutableSet;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

/* JADX INFO: loaded from: classes2.dex */
public final class Retryability {
    public static final ImmutableSet<Class<? extends Exception>> AGGRESSIVE_RETRYABLE_NETWORK_EXCEPTIONS;
    public static final ImmutableSet<Class<? extends Exception>> SAFE_RETRYABLE_NETWORK_EXCEPTIONS;

    static {
        ImmutableSet<Class<? extends Exception>> immutableSetOf = ImmutableSet.of(UnknownHostException.class, ConnectException.class, SSLException.class, SSLHandshakeException.class);
        SAFE_RETRYABLE_NETWORK_EXCEPTIONS = immutableSetOf;
        AGGRESSIVE_RETRYABLE_NETWORK_EXCEPTIONS = ImmutableSet.builder().addAll((Iterable) immutableSetOf).add(ConnectivityTimeoutException.class).add(SocketTimeoutException.class).build();
    }
}
