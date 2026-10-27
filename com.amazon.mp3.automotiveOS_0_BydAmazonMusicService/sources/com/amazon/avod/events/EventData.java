package com.amazon.avod.events;

import com.amazon.avod.http.internal.TokenKey;

/* JADX INFO: loaded from: classes2.dex */
public interface EventData {
    String getBody();

    long getId();

    String getName();

    EventPriority getPriority();

    int getRetryCount();

    String getSessionId();

    String getTag();

    long getTimestampMillis();

    TokenKey getTokenKey();

    EventType getType();

    boolean isProcessed();

    void setId(long j);
}
