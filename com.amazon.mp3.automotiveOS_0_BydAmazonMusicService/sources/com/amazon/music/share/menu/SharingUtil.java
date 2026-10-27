package com.amazon.music.share.menu;

import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.platform.data.entity.CollectionType;
import com.amazon.music.platform.data.entity.EntityItemType;
import com.amazon.music.platform.data.entity.SingleType;
import kotlin.Metadata;

/* JADX INFO: compiled from: SharingUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/share/menu/SharingUtil;", "", "()V", "toPlatformEntityItemType", "Lcom/amazon/music/platform/data/entity/EntityItemType;", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SharingUtil {
    public static final SharingUtil INSTANCE = new SharingUtil();

    /* JADX INFO: compiled from: SharingUtil.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EntityType.values().length];
            iArr[EntityType.ALBUM.ordinal()] = 1;
            iArr[EntityType.ARTIST.ordinal()] = 2;
            iArr[EntityType.PLAYLIST.ordinal()] = 3;
            iArr[EntityType.USER_PLAYLIST.ordinal()] = 4;
            iArr[EntityType.TRACK.ordinal()] = 5;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private SharingUtil() {
    }

    public final EntityItemType toPlatformEntityItemType(EntityType entityType) {
        int i = entityType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[entityType.ordinal()];
        if (i == 1) {
            return CollectionType.Album.INSTANCE;
        }
        if (i == 2) {
            return CollectionType.Artist.INSTANCE;
        }
        if (i == 3 || i == 4) {
            return CollectionType.Playlist.INSTANCE;
        }
        if (i != 5) {
            return null;
        }
        return SingleType.Track.INSTANCE;
    }
}
