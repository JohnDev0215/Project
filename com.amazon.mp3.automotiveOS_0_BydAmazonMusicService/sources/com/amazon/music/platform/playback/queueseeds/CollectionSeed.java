package com.amazon.music.platform.playback.queueseeds;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.platform.data.entity.EntityItem;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: CollectionSeed.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u00012\u00020\u0002B!\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0004\b\n\u0010\u000bR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00038&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\f"}, d2 = {"Lcom/amazon/music/platform/playback/queueseeds/CollectionSeed;", "", "Lcom/amazon/music/platform/playback/queueseeds/QueueSeed;", "Lcom/amazon/music/platform/playback/queueseeds/EntitySingleSeed;", "getStartSeed", "()Lcom/amazon/music/platform/playback/queueseeds/EntitySingleSeed;", "startSeed", "", "Lcom/amazon/music/platform/data/entity/EntityItem;", ContextMappingConstants.ITEMS, "<init>", "(Lcom/amazon/music/platform/playback/queueseeds/EntitySingleSeed;Ljava/util/List;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public abstract class CollectionSeed implements QueueSeed {
    public abstract EntitySingleSeed getStartSeed();

    public CollectionSeed(EntitySingleSeed entitySingleSeed, List<? extends EntityItem> list) {
    }
}
