package com.amazon.mp3.library.provider.source.cirrus.dbutils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LocalRepositoryEvents.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/library/provider/source/cirrus/dbutils/RepositoryEvent;", "", "eventType", "Lcom/amazon/mp3/library/provider/source/cirrus/dbutils/RepositoryEventType;", "(Lcom/amazon/mp3/library/provider/source/cirrus/dbutils/RepositoryEventType;)V", "getEventType", "()Lcom/amazon/mp3/library/provider/source/cirrus/dbutils/RepositoryEventType;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class RepositoryEvent {
    private final RepositoryEventType eventType;

    public RepositoryEvent(RepositoryEventType eventType) {
        Intrinsics.checkNotNullParameter(eventType, "eventType");
        this.eventType = eventType;
    }

    public RepositoryEventType getEventType() {
        return this.eventType;
    }
}
