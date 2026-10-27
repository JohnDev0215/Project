package com.amazon.music.platform.aom.hints.dae;

import com.amazon.music.platform.aom.hints.guiContext.GUIContext;
import com.amazon.music.platform.aom.hints.guiContext.GUIContextPageType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: DAEConstructorFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/platform/aom/hints/dae/DAEConstructorFactory;", "", "()V", "Companion", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class DAEConstructorFactory {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: DAEConstructorFactory.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/platform/aom/hints/dae/DAEConstructorFactory$Companion;", "", "()V", "getDAE", "Lcom/amazon/music/platform/aom/hints/dae/DAE;", "guiContext", "Lcom/amazon/music/platform/aom/hints/guiContext/GUIContext;", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: DAEConstructorFactory.kt */
        @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[GUIContextPageType.values().length];
                iArr[GUIContextPageType.BROWSE_HOME.ordinal()] = 1;
                iArr[GUIContextPageType.ARTIST_DETAIL.ordinal()] = 2;
                iArr[GUIContextPageType.PLAYLIST_DETAIL.ordinal()] = 3;
                iArr[GUIContextPageType.LIBRARY.ordinal()] = 4;
                iArr[GUIContextPageType.NOW_PLAYING.ordinal()] = 5;
                iArr[GUIContextPageType.GENRE_LANDING.ordinal()] = 6;
                iArr[GUIContextPageType.SEARCH.ordinal()] = 7;
                iArr[GUIContextPageType.PLAYLIST_LANDING.ordinal()] = 8;
                iArr[GUIContextPageType.STATION_LANDING.ordinal()] = 9;
                iArr[GUIContextPageType.PODCAST_LANDING.ordinal()] = 10;
                iArr[GUIContextPageType.NEW_RELEASE_LANDING.ordinal()] = 11;
                iArr[GUIContextPageType.MOST_POPULAR_LANDING.ordinal()] = 12;
                iArr[GUIContextPageType.ALBUM_DETAIL.ordinal()] = 13;
                iArr[GUIContextPageType.USER_PLAYLIST_DETAIL.ordinal()] = 14;
                iArr[GUIContextPageType.CLOUD_LIBRARY_PLAYLIST_LANDING.ordinal()] = 15;
                iArr[GUIContextPageType.CAR_MODE.ordinal()] = 16;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DAE getDAE(GUIContext guiContext) {
            String strName;
            GUIContextPageType pageType = guiContext != null ? guiContext.getPageType() : null;
            switch (pageType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[pageType.ordinal()]) {
                case 1:
                    strName = EventType.BROWSE_HOME.name();
                    break;
                case 2:
                    strName = EventType.ARTIST_DETAIL.name();
                    break;
                case 3:
                    strName = EventType.PLAYLIST_DETAIL.name();
                    break;
                case 4:
                    strName = EventType.LIBRARY.name();
                    break;
                case 5:
                    strName = EventType.NOW_PLAYING.name();
                    break;
                case 6:
                    strName = EventType.GENRE_LANDING.name();
                    break;
                case 7:
                    strName = EventType.SEARCH.name();
                    break;
                case 8:
                    strName = EventType.PLAYLIST_LANDING.name();
                    break;
                case 9:
                    strName = EventType.STATION_LANDING.name();
                    break;
                case 10:
                    strName = EventType.PODCAST_LANDING.name();
                    break;
                case 11:
                    strName = EventType.NEW_RELEASE_LANDING.name();
                    break;
                case 12:
                    strName = EventType.MOST_POPULAR_LANDING.name();
                    break;
                case 13:
                    strName = EventType.ALBUM_DETAIL.name();
                    break;
                case 14:
                    strName = EventType.USER_PLAYLIST_DETAIL.name();
                    break;
                case 15:
                    strName = EventType.CLOUD_LIBRARY_PLAYLIST_LANDING.name();
                    break;
                case 16:
                    strName = EventType.CAR_MODE.name();
                    break;
                default:
                    strName = EventType.IN_FOCUS.name();
                    break;
            }
            return new DAE(Domain.MUSIC.name(), Application.AMAZON_MUSIC.name(), strName);
        }
    }
}
