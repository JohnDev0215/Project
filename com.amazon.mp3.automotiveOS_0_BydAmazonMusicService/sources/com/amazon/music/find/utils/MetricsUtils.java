package com.amazon.music.find.utils;

import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MetricsUtils.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/find/utils/MetricsUtils;", "", "()V", "isSearchPageType", "", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MetricsUtils {
    public static final MetricsUtils INSTANCE = new MetricsUtils();

    /* JADX INFO: compiled from: MetricsUtils.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PageType.values().length];
            iArr[PageType.BROWSE_SEARCH_HISTORY.ordinal()] = 1;
            iArr[PageType.BROWSE_SEARCH_SUGGESTIONS.ordinal()] = 2;
            iArr[PageType.SEARCH_FILTER.ordinal()] = 3;
            iArr[PageType.BROWSE_SEARCH.ordinal()] = 4;
            iArr[PageType.CAR_MODE_BROWSE_SEARCH.ordinal()] = 5;
            iArr[PageType.OFFLINE_LIBRARY_SEARCH.ordinal()] = 6;
            iArr[PageType.SEARCH_TAB_ALL.ordinal()] = 7;
            iArr[PageType.SEARCH_TAB_PRIME.ordinal()] = 8;
            iArr[PageType.SEARCH_TAB_LIBRARY.ordinal()] = 9;
            iArr[PageType.SEARCH_RESULTS.ordinal()] = 10;
            iArr[PageType.SEE_ALL_SEARCH_BROWSE_TRACKS.ordinal()] = 11;
            iArr[PageType.SEE_ALL_SEARCH_BROWSE_ALBUMS.ordinal()] = 12;
            iArr[PageType.SEE_ALL_SEARCH_BROWSE_ARTISTS.ordinal()] = 13;
            iArr[PageType.SEE_ALL_SEARCH_BROWSE_PLAYLISTS.ordinal()] = 14;
            iArr[PageType.SEE_ALL_SEARCH_STATIONS.ordinal()] = 15;
            iArr[PageType.SEE_ALL_SEARCH_VIDEOS.ordinal()] = 16;
            iArr[PageType.SEE_ALL_SEARCH_VIDEO_PLAYLISTS.ordinal()] = 17;
            iArr[PageType.SEE_ALL_SEARCH_PODCAST_SHOWS.ordinal()] = 18;
            iArr[PageType.SEE_ALL_SEARCH_PODCAST_EPISODES.ordinal()] = 19;
            iArr[PageType.SEE_ALL_SEARCH_LIBRARY_TRACKS.ordinal()] = 20;
            iArr[PageType.SEE_ALL_SEARCH_LIBRARY_ALBUMS.ordinal()] = 21;
            iArr[PageType.SEE_ALL_SEARCH_LIBRARY_ARTISTS.ordinal()] = 22;
            iArr[PageType.SEE_ALL_SEARCH_LIBRARY_PLAYLISTS.ordinal()] = 23;
            iArr[PageType.SEE_ALL_SEARCH_TRACKS.ordinal()] = 24;
            iArr[PageType.SEE_ALL_SEARCH_ALBUMS.ordinal()] = 25;
            iArr[PageType.SEE_ALL_SEARCH_ARTISTS.ordinal()] = 26;
            iArr[PageType.SEE_ALL_SEARCH_PLAYLISTS.ordinal()] = 27;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private MetricsUtils() {
    }

    @JvmStatic
    public static final boolean isSearchPageType(PageType pageType) {
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        switch (WhenMappings.$EnumSwitchMapping$0[pageType.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
                return true;
            default:
                return false;
        }
    }
}
