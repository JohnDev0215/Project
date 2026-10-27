package com.amazon.music.platform.playback.queueseeds;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.platform.PlatformProviderUtilKt;
import com.amazon.music.platform.data.entity.EntityItemCollection;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.entity.EntityItemSingle;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EntityCollectionSeed.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B3\u0012\u0006\u0010\r\u001a\u00020\f\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\u000e\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c¢\u0006\u0004\b!\u0010\"J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0007\u001a\u00020\u0006HÖ\u0001J\u0013\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\bHÖ\u0003R\u001a\u0010\r\u001a\u00020\f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\"\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u00168\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u001c8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 ¨\u0006#"}, d2 = {"Lcom/amazon/music/platform/playback/queueseeds/EntityCollectionSeed;", "Lcom/amazon/music/platform/playback/queueseeds/CollectionSeed;", "Lcom/amazon/music/platform/data/entity/EntityItemCollection;", "Lcom/amazon/music/platform/playback/queueseeds/EntitySeed;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "entityItemIdentifier", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "getEntityItemIdentifier", "()Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "Lcom/amazon/music/platform/playback/queueseeds/EntitySingleSeed;", "startSeed", "Lcom/amazon/music/platform/playback/queueseeds/EntitySingleSeed;", "getStartSeed", "()Lcom/amazon/music/platform/playback/queueseeds/EntitySingleSeed;", "", "Lcom/amazon/music/platform/data/entity/EntityItemSingle;", ContextMappingConstants.ITEMS, "Ljava/util/List;", "getItems", "()Ljava/util/List;", "Lcom/amazon/music/platform/playback/queueseeds/PlayMode;", ContextMappingConstants.PLAY_MODE, "Lcom/amazon/music/platform/playback/queueseeds/PlayMode;", "getPlayMode", "()Lcom/amazon/music/platform/playback/queueseeds/PlayMode;", "<init>", "(Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;Lcom/amazon/music/platform/playback/queueseeds/EntitySingleSeed;Ljava/util/List;Lcom/amazon/music/platform/playback/queueseeds/PlayMode;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class EntityCollectionSeed extends CollectionSeed implements EntityItemCollection, EntitySeed {
    private final EntityItemIdentifier entityItemIdentifier;
    private final List<EntityItemSingle> items;
    private final PlayMode playMode;
    private final EntitySingleSeed startSeed;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EntityCollectionSeed)) {
            return false;
        }
        EntityCollectionSeed entityCollectionSeed = (EntityCollectionSeed) other;
        return Intrinsics.areEqual(getEntityItemIdentifier(), entityCollectionSeed.getEntityItemIdentifier()) && Intrinsics.areEqual(getStartSeed(), entityCollectionSeed.getStartSeed()) && Intrinsics.areEqual(getItems(), entityCollectionSeed.getItems()) && getPlayMode() == entityCollectionSeed.getPlayMode();
    }

    public int hashCode() {
        return (((((getEntityItemIdentifier().hashCode() * 31) + (getStartSeed() == null ? 0 : getStartSeed().hashCode())) * 31) + (getItems() == null ? 0 : getItems().hashCode())) * 31) + (getPlayMode() != null ? getPlayMode().hashCode() : 0);
    }

    public String toString() {
        return "EntityCollectionSeed(entityItemIdentifier=" + getEntityItemIdentifier() + ", startSeed=" + getStartSeed() + ", items=" + getItems() + ", playMode=" + getPlayMode() + ')';
    }

    @Override // com.amazon.music.platform.data.entity.EntityItem
    public EntityItemIdentifier getEntityItemIdentifier() {
        return this.entityItemIdentifier;
    }

    @Override // com.amazon.music.platform.playback.queueseeds.CollectionSeed
    public EntitySingleSeed getStartSeed() {
        return this.startSeed;
    }

    @Override // com.amazon.music.platform.data.entity.EntityItemCollection
    public List<EntityItemSingle> getItems() {
        return this.items;
    }

    @Override // com.amazon.music.platform.playback.queueseeds.QueueSeed
    public PlayMode getPlayMode() {
        return this.playMode;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public EntityCollectionSeed(EntityItemIdentifier entityItemIdentifier, EntitySingleSeed entitySingleSeed, List<? extends EntityItemSingle> list, PlayMode playMode) {
        super(entitySingleSeed, list);
        Intrinsics.checkNotNullParameter(entityItemIdentifier, "entityItemIdentifier");
        this.entityItemIdentifier = entityItemIdentifier;
        this.startSeed = entitySingleSeed;
        this.items = list;
        this.playMode = playMode;
        PlatformProviderUtilKt.freeze(this);
    }
}
