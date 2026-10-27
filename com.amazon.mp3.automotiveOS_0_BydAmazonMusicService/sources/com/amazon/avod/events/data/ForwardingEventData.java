package com.amazon.avod.events.data;

import com.amazon.avod.events.EventData;
import com.amazon.avod.events.EventPriority;
import com.amazon.avod.events.EventType;
import com.amazon.avod.http.internal.TokenKey;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ForwardingEventData implements EventData {
    private final EventData mInner;

    public ForwardingEventData(@Nonnull EventData eventData) {
        this.mInner = (EventData) Preconditions.checkNotNull(eventData, "inner");
    }

    @Override // com.amazon.avod.events.EventData
    public long getId() {
        return this.mInner.getId();
    }

    @Override // com.amazon.avod.events.EventData
    public EventType getType() {
        return this.mInner.getType();
    }

    @Override // com.amazon.avod.events.EventData
    public String getName() {
        return this.mInner.getName();
    }

    @Override // com.amazon.avod.events.EventData
    public String getSessionId() {
        return this.mInner.getSessionId();
    }

    @Override // com.amazon.avod.events.EventData
    public EventPriority getPriority() {
        return this.mInner.getPriority();
    }

    @Override // com.amazon.avod.events.EventData
    public long getTimestampMillis() {
        return this.mInner.getTimestampMillis();
    }

    @Override // com.amazon.avod.events.EventData
    public String getTag() {
        return this.mInner.getTag();
    }

    @Override // com.amazon.avod.events.EventData
    public String getBody() {
        return this.mInner.getBody();
    }

    @Override // com.amazon.avod.events.EventData
    public int getRetryCount() {
        return this.mInner.getRetryCount();
    }

    @Override // com.amazon.avod.events.EventData
    public TokenKey getTokenKey() {
        return this.mInner.getTokenKey();
    }

    @Override // com.amazon.avod.events.EventData
    public boolean isProcessed() {
        return this.mInner.isProcessed();
    }

    @Override // com.amazon.avod.events.EventData
    public void setId(long j) {
        this.mInner.setId(j);
    }

    public String toString() {
        return this.mInner.toString();
    }
}
