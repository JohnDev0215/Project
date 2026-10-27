package com.amazon.avod.events;

/* JADX INFO: loaded from: classes2.dex */
public enum PlayerEventType implements EventType {
    QOS,
    QOS_BATCHED,
    QOS_IMMEDIATE;

    @Override // com.amazon.avod.events.EventType
    public String getName() {
        return name();
    }
}
