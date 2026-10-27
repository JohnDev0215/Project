package com.amazon.music.platform.data.identifiers;

import com.amazon.music.platform.PlatformProviderUtilKt;
import com.amazon.music.platform.data.entity.EntityId;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.Serializable;

/* JADX INFO: compiled from: QueueId.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0087\b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\u0012\u0006\u0010\n\u001a\u00020\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\n\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR \u0010\u0012\u001a\u00020\u00028\u0016X\u0097D¢\u0006\u0012\n\u0004\b\u000e\u0010\u000b\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000f\u0010\r¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/platform/data/identifiers/QueueId;", "Lcom/amazon/music/platform/data/entity/EntityId;", "", "toString", "", "hashCode", "", "other", "", "equals", "identifier", "Ljava/lang/String;", "getIdentifier", "()Ljava/lang/String;", "identifierType$1", "getIdentifierType", "getIdentifierType$annotations", "()V", "identifierType", "<init>", "(Ljava/lang/String;)V", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
@Serializable
public final /* data */ class QueueId implements EntityId {
    private final String identifier;

    /* JADX INFO: renamed from: identifierType$1, reason: from kotlin metadata */
    private final String identifierType;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof QueueId) && Intrinsics.areEqual(getIdentifier(), ((QueueId) other).getIdentifier());
    }

    public int hashCode() {
        return getIdentifier().hashCode();
    }

    public String toString() {
        return "QueueId(identifier=" + getIdentifier() + ')';
    }

    public QueueId(String identifier) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        this.identifier = identifier;
        this.identifierType = "QUEUE_ID";
        PlatformProviderUtilKt.freeze(this);
    }

    @Override // com.amazon.music.platform.data.entity.EntityId
    public String getIdentifier() {
        return this.identifier;
    }

    @Override // com.amazon.music.platform.data.entity.EntityId
    public String getIdentifierType() {
        return this.identifierType;
    }
}
