package com.amazon.music.platform.playback.queueseeds;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.platform.PlatformProviderUtilKt;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EntityCollectionSeed.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\u000b\u001a\u00020\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/platform/playback/queueseeds/EntitySingleSeed;", "Lcom/amazon/music/platform/playback/queueseeds/EntitySeed;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "entityItemIdentifier", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "getEntityItemIdentifier", "()Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "Lcom/amazon/music/platform/playback/queueseeds/PlayMode;", ContextMappingConstants.PLAY_MODE, "Lcom/amazon/music/platform/playback/queueseeds/PlayMode;", "getPlayMode", "()Lcom/amazon/music/platform/playback/queueseeds/PlayMode;", "<init>", "(Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;Lcom/amazon/music/platform/playback/queueseeds/PlayMode;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class EntitySingleSeed implements EntitySeed {
    private final EntityItemIdentifier entityItemIdentifier;
    private final PlayMode playMode;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EntitySingleSeed)) {
            return false;
        }
        EntitySingleSeed entitySingleSeed = (EntitySingleSeed) other;
        return Intrinsics.areEqual(getEntityItemIdentifier(), entitySingleSeed.getEntityItemIdentifier()) && getPlayMode() == entitySingleSeed.getPlayMode();
    }

    public int hashCode() {
        return (getEntityItemIdentifier().hashCode() * 31) + (getPlayMode() == null ? 0 : getPlayMode().hashCode());
    }

    public String toString() {
        return "EntitySingleSeed(entityItemIdentifier=" + getEntityItemIdentifier() + ", playMode=" + getPlayMode() + ')';
    }

    public EntitySingleSeed(EntityItemIdentifier entityItemIdentifier, PlayMode playMode) {
        Intrinsics.checkNotNullParameter(entityItemIdentifier, "entityItemIdentifier");
        this.entityItemIdentifier = entityItemIdentifier;
        this.playMode = playMode;
        PlatformProviderUtilKt.freeze(this);
    }

    @Override // com.amazon.music.platform.playback.queueseeds.EntitySeed, com.amazon.music.platform.data.entity.EntityItem
    public EntityItemIdentifier getEntityItemIdentifier() {
        return this.entityItemIdentifier;
    }

    @Override // com.amazon.music.platform.playback.queueseeds.QueueSeed
    public PlayMode getPlayMode() {
        return this.playMode;
    }
}
