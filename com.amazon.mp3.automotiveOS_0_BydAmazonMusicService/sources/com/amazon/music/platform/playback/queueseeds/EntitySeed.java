package com.amazon.music.platform.playback.queueseeds;

import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import kotlin.Metadata;

/* JADX INFO: compiled from: EntityCollectionSeed.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00048&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/amazon/music/platform/playback/queueseeds/EntitySeed;", "Lcom/amazon/music/platform/data/entity/EntityItem;", "", "Lcom/amazon/music/platform/playback/queueseeds/QueueSeed;", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "getEntityItemIdentifier", "()Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "entityItemIdentifier", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public interface EntitySeed extends EntityItem, QueueSeed {
    @Override // com.amazon.music.platform.data.entity.EntityItem
    EntityItemIdentifier getEntityItemIdentifier();
}
