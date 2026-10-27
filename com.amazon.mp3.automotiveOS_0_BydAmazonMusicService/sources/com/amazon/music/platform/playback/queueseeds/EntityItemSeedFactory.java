package com.amazon.music.platform.playback.queueseeds;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.entity.EntityItemSingle;
import com.amazon.music.platform.data.entity.SingleType;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EntityItemSeedFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/platform/playback/queueseeds/EntityItemSeedFactory;", "", "()V", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class EntityItemSeedFactory {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: EntityItemSeedFactory.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\tJ2\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\u0006\u0010\b\u001a\u00020\t¨\u0006\r"}, d2 = {"Lcom/amazon/music/platform/playback/queueseeds/EntityItemSeedFactory$Companion;", "", "()V", "from", "Lcom/amazon/music/platform/playback/queueseeds/QueueSeed;", "entityItemIdentifier", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "item", ContextMappingConstants.PLAY_MODE, "Lcom/amazon/music/platform/playback/queueseeds/PlayMode;", ContextMappingConstants.ITEMS, "", "Lcom/amazon/music/platform/data/entity/EntityItemSingle;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final QueueSeed from(EntityItemIdentifier entityItemIdentifier, EntityItemIdentifier item, PlayMode playMode) {
            Intrinsics.checkNotNullParameter(entityItemIdentifier, "entityItemIdentifier");
            Intrinsics.checkNotNullParameter(playMode, "playMode");
            return from(entityItemIdentifier, item, null, playMode);
        }

        public final QueueSeed from(EntityItemIdentifier entityItemIdentifier, EntityItemIdentifier item, List<? extends EntityItemSingle> items, PlayMode playMode) {
            Intrinsics.checkNotNullParameter(entityItemIdentifier, "entityItemIdentifier");
            Intrinsics.checkNotNullParameter(playMode, "playMode");
            if (entityItemIdentifier.getType() instanceof SingleType) {
                return new EntitySingleSeed(entityItemIdentifier, playMode);
            }
            EntitySingleSeed entitySingleSeed = null;
            if (item != null) {
                QueueSeed queueSeedFrom = from(item, null, playMode);
                if (queueSeedFrom instanceof EntitySingleSeed) {
                    entitySingleSeed = (EntitySingleSeed) queueSeedFrom;
                }
            }
            return new EntityCollectionSeed(entityItemIdentifier, entitySingleSeed, items, playMode);
        }
    }
}
