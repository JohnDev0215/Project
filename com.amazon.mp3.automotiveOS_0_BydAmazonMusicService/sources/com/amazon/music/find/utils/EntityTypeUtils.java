package com.amazon.music.find.utils;

import com.amazon.music.platform.find.common.EntityType;
import com.amazon.music.search.SectionType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EntityTypeUtils.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00042\b\u0010\b\u001a\u0004\u0018\u00010\t¨\u0006\n"}, d2 = {"Lcom/amazon/music/find/utils/EntityTypeUtils;", "", "()V", "convertFromSectionType", "Lcom/amazon/music/platform/find/common/EntityType;", "sectionType", "Lcom/amazon/music/search/SectionType;", "convertFromString", "value", "", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EntityTypeUtils {
    public static final EntityTypeUtils INSTANCE = new EntityTypeUtils();

    /* JADX INFO: compiled from: EntityTypeUtils.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SectionType.values().length];
            iArr[SectionType.ARTISTS_SECTION.ordinal()] = 1;
            iArr[SectionType.ALBUMS_SECTION.ordinal()] = 2;
            iArr[SectionType.TRACKS_SECTION.ordinal()] = 3;
            iArr[SectionType.PLAYLISTS_SECTION.ordinal()] = 4;
            iArr[SectionType.STATIONS_SECTION.ordinal()] = 5;
            iArr[SectionType.PODCAST_SHOW_SECTION.ordinal()] = 6;
            iArr[SectionType.PODCAST_EPISODE_SECTION.ordinal()] = 7;
            iArr[SectionType.VIDEO_SECTION.ordinal()] = 8;
            iArr[SectionType.VIDEO_PLAYLIST_SECTION.ordinal()] = 9;
            iArr[SectionType.SOCCER_SECTION.ordinal()] = 10;
            iArr[SectionType.COMMUNITY_PLAYLIST_SECTION.ordinal()] = 11;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private EntityTypeUtils() {
    }

    public final EntityType convertFromSectionType(SectionType sectionType) {
        Intrinsics.checkNotNullParameter(sectionType, "sectionType");
        switch (WhenMappings.$EnumSwitchMapping$0[sectionType.ordinal()]) {
            case 1:
                return EntityType.ARTIST;
            case 2:
                return EntityType.ALBUM;
            case 3:
                return EntityType.TRACK;
            case 4:
                return EntityType.PLAYLIST;
            case 5:
                return EntityType.STATION;
            case 6:
                return EntityType.PODCAST_SHOW;
            case 7:
                return EntityType.PODCAST_EPISODE;
            case 8:
                return EntityType.VIDEO;
            case 9:
                return EntityType.VIDEO_PLAYLIST;
            case 10:
                return EntityType.SPORT;
            case 11:
                return EntityType.COMMUNITY_PLAYLIST;
            default:
                return null;
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final EntityType convertFromString(String value) {
        EntityType entityType;
        if (value == null) {
            return null;
        }
        switch (value.hashCode()) {
            case -1865828127:
                if (!value.equals("playlists")) {
                    return null;
                }
                entityType = EntityType.PLAYLIST;
                break;
                break;
            case -1736070346:
                if (!value.equals("community-playlist")) {
                    return null;
                }
                entityType = EntityType.COMMUNITY_PLAYLIST;
                break;
                break;
            case -1415163932:
                if (!value.equals("albums")) {
                    return null;
                }
                entityType = EntityType.ALBUM;
                break;
                break;
            case -816678056:
                if (!value.equals("videos")) {
                    return null;
                }
                entityType = EntityType.VIDEO;
                break;
                break;
            case -732362228:
                if (!value.equals("artists")) {
                    return null;
                }
                entityType = EntityType.ARTIST;
                break;
                break;
            case -632946216:
                if (!value.equals(DeeplinksUtils.PODCAST_EPISODES_PATH_SEGMENT)) {
                    return null;
                }
                entityType = EntityType.PODCAST_EPISODE;
                break;
                break;
            case 109620734:
                if (!value.equals("songs")) {
                    return null;
                }
                entityType = EntityType.TRACK;
                break;
                break;
            case 312270319:
                if (!value.equals(DeeplinksUtils.PODCASTS_PATH_SEGMENT)) {
                    return null;
                }
                entityType = EntityType.PODCAST_SHOW;
                break;
                break;
            case 1318331839:
                if (!value.equals("stations")) {
                    return null;
                }
                entityType = EntityType.STATION;
                break;
                break;
            case 1413190159:
                if (!value.equals("video-playlists")) {
                    return null;
                }
                entityType = EntityType.VIDEO_PLAYLIST;
                break;
                break;
            default:
                return null;
        }
        return entityType;
    }
}
