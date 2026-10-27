package com.google.rpc;

import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.MessageOrBuilder;

/* JADX INFO: loaded from: classes3.dex */
public interface RetryInfoOrBuilder extends MessageOrBuilder {
    Duration getRetryDelay();

    DurationOrBuilder getRetryDelayOrBuilder();

    boolean hasRetryDelay();
}
