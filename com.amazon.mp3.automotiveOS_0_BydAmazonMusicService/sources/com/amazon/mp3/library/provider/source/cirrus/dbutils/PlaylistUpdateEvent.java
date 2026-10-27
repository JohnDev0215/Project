package com.amazon.mp3.library.provider.source.cirrus.dbutils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistRepositoryEvents.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0080\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\u000b\u001a\u00020\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/library/provider/source/cirrus/dbutils/PlaylistUpdateEvent;", "Lcom/amazon/mp3/library/provider/source/cirrus/dbutils/RepositoryEvent;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/mp3/library/provider/source/cirrus/dbutils/PlaylistUpdateEventType;", "eventType", "Lcom/amazon/mp3/library/provider/source/cirrus/dbutils/PlaylistUpdateEventType;", "getEventType", "()Lcom/amazon/mp3/library/provider/source/cirrus/dbutils/PlaylistUpdateEventType;", "<init>", "(Lcom/amazon/mp3/library/provider/source/cirrus/dbutils/PlaylistUpdateEventType;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class PlaylistUpdateEvent extends RepositoryEvent {
    private final PlaylistUpdateEventType eventType;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof PlaylistUpdateEvent) && getEventType() == ((PlaylistUpdateEvent) other).getEventType();
    }

    public int hashCode() {
        return getEventType().hashCode();
    }

    public String toString() {
        return "PlaylistUpdateEvent(eventType=" + getEventType() + ')';
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlaylistUpdateEvent(PlaylistUpdateEventType eventType) {
        super(eventType);
        Intrinsics.checkNotNullParameter(eventType, "eventType");
        this.eventType = eventType;
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbutils.RepositoryEvent
    public PlaylistUpdateEventType getEventType() {
        return this.eventType;
    }
}
