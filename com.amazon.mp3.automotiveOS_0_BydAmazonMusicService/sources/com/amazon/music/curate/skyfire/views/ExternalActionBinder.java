package com.amazon.music.curate.skyfire.views;

import ExternalActionInterface.v1_0.ExternalAction;
import ExternalActionInterface.v1_0.PlayMode;
import ExternalActionInterface.v1_0.TriggerExternalActionMethod;
import Touch.SyncInterface.v1_0.EntityIdType;
import androidx.fragment.app.FragmentActivity;
import com.amazon.music.curate.provider.LibraryNavigationProvider;
import com.amazon.music.curate.provider.PlaybackProvider;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.curate.skyfire.bootstrap.CurateBootstrapProviders;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public final class ExternalActionBinder {
    private static final String TAG = "ExternalActionBinder";
    private static final Logger logger = LoggerFactory.getLogger(ExternalActionBinder.class.getSimpleName());
    private final FragmentActivity activity;
    private final LibraryNavigationProvider navigationProvider;
    private final PlaybackProvider playbackProvider;

    public ExternalActionBinder(final FragmentActivity activity, final CurateBootstrapProviders curateBootstrapProviders) {
        this.activity = activity;
        this.navigationProvider = curateBootstrapProviders.getLibraryNavigationProvider();
        this.playbackProvider = curateBootstrapProviders.getPlaybackProvider();
    }

    public void handleExternalAction(final TriggerExternalActionMethod method) {
        String str;
        PlaybackPageType playbackPageType;
        List<Map<String, String>> contentInfo = getContentInfo(method.context());
        String strName = method.name();
        if (this.navigationProvider == null) {
            logger.error("{} was null. Stopping external action handling", LibraryNavigationProvider.class.getSimpleName());
        }
        switch (AnonymousClass1.$SwitchMap$ExternalActionInterface$v1_0$ExternalAction[ExternalAction.valueOf(strName).ordinal()]) {
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
                this.navigationProvider.onNavigationButtonClicked(strName, contentInfo);
                break;
            case 13:
                Map<String, String> mapContext = method.context();
                if (mapContext != null && !mapContext.isEmpty()) {
                    this.navigationProvider.showCatalogPlaylist(mapContext.get(EntityIdType.ASIN.name()), contentInfo);
                    break;
                }
                break;
            case 14:
                Map<String, String> mapContext2 = method.context();
                if (mapContext2 != null && !mapContext2.isEmpty()) {
                    EntityIdType entityIdType = EntityIdType.OBJECT_ID;
                    if (mapContext2.get(entityIdType.name()) == null) {
                        str = mapContext2.get(EntityIdType.ASIN.name());
                    } else {
                        str = mapContext2.get(entityIdType.name());
                    }
                    this.navigationProvider.showLibraryPlaylist(str, contentInfo);
                    break;
                }
                break;
            case 15:
                Map<String, String> mapContext3 = method.context();
                if (mapContext3 != null && !mapContext3.isEmpty()) {
                    String str2 = mapContext3.get(EntityIdType.ASIN.name());
                    if (StringUtils.isNotBlank(str2)) {
                        this.playbackProvider.playSFA(this.activity, str2, PlaybackPageType.SFA, contentInfo);
                        logger.debug("Playing SFA {}", str2);
                    } else {
                        EntityIdType entityIdType2 = EntityIdType.STATION_KEY;
                        String str3 = mapContext3.get(entityIdType2.name());
                        if (StringUtils.isNotBlank(str3)) {
                            if (ContextMappingConstants.MY_SOUNDTRACK_STATION_KEY.equalsIgnoreCase(str3)) {
                                playbackPageType = PlaybackPageType.MY_SOUNDTRACK;
                            } else {
                                playbackPageType = PlaybackPageType.MY_LIBRARY_FRONT_PAGE_RECENTLY_PLAYED;
                            }
                            this.playbackProvider.playStation(this.activity, entityIdType2.name(), str3, playbackPageType, contentInfo);
                            logger.debug("Playing station, station key = {}", str3);
                        } else {
                            EntityIdType entityIdType3 = EntityIdType.STATION_SEED_ID;
                            String str4 = mapContext3.get(entityIdType3.name());
                            if (StringUtils.isNotBlank(str4)) {
                                this.playbackProvider.playStation(this.activity, entityIdType3.name(), str4, PlaybackPageType.MY_LIBRARY_FRONT_PAGE_RECENTLY_PLAYED, contentInfo);
                                logger.debug("Playing station, station SeedId = {}", str4);
                            } else {
                                String str5 = mapContext3.get(EntityIdType.OBJECT_ID.name());
                                if (StringUtils.isNotBlank(str5)) {
                                    this.playbackProvider.playSFA(this.activity, str5, PlaybackPageType.PLAYLIST_DETAIL, contentInfo);
                                    logger.debug("Playing SFA {}", str5);
                                }
                            }
                        }
                    }
                    break;
                }
                break;
            case 16:
                Map<String, String> mapContext4 = method.context();
                if (mapContext4 != null && !mapContext4.isEmpty()) {
                    String str6 = mapContext4.get(EntityIdType.OBJECT_ID.name());
                    String str7 = mapContext4.get(EntityIdType.ASIN.name());
                    if (StringUtils.isNotBlank(str6)) {
                        this.playbackProvider.playUserPlaylist(this.activity, str6, false, PlaybackPageType.MY_LIBRARY_FRONT_PAGE_RECENTLY_PLAYED, contentInfo);
                    } else if (StringUtils.isNotBlank(str7)) {
                        String str8 = mapContext4.get(PlayMode.PLAY_MODE);
                        if (PlayMode.ON_DEMAND.name().equalsIgnoreCase(str8) || PlayMode.ON_DEMAND_LIMITED.name().equalsIgnoreCase(str8)) {
                            this.playbackProvider.playPlaylistOnDemand(this.activity, str7, PlaybackPageType.MY_LIBRARY_FRONT_PAGE_RECENTLY_PLAYED, contentInfo);
                            logger.debug("Playing playlist on demand, playlistAsin = {}", str7);
                        } else {
                            this.playbackProvider.playCatalogPlaylist(this.activity, str7, false, PlaybackPageType.MY_LIBRARY_FRONT_PAGE_RECENTLY_PLAYED, contentInfo);
                            logger.debug("Shuffling playlist, playlistAsin = {}", str7);
                        }
                    }
                    break;
                }
                break;
            case 17:
                Map<String, String> mapContext5 = method.context();
                if (mapContext5 != null && !mapContext5.isEmpty()) {
                    String str9 = mapContext5.get(EntityIdType.ASIN.name());
                    if (StringUtils.isNotBlank(str9)) {
                        this.playbackProvider.playArtist(this.activity, str9, false, PlaybackPageType.MY_LIBRARY_FRONT_PAGE_RECENTLY_PLAYED, contentInfo);
                    }
                    break;
                }
                break;
            case 18:
                Map<String, String> mapContext6 = method.context();
                if (mapContext6 != null && !mapContext6.isEmpty()) {
                    String str10 = mapContext6.get(EntityIdType.ASIN.name());
                    if (StringUtils.isNotBlank(str10)) {
                        this.playbackProvider.playAlbum(this.activity, str10, false, PlaybackPageType.MY_LIBRARY_FRONT_PAGE_RECENTLY_PLAYED, contentInfo);
                    }
                    break;
                }
                break;
            default:
                logger.error("not supported action {}", strName);
                break;
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.curate.skyfire.views.ExternalActionBinder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$ExternalActionInterface$v1_0$ExternalAction;

        static {
            int[] iArr = new int[ExternalAction.values().length];
            $SwitchMap$ExternalActionInterface$v1_0$ExternalAction = iArr;
            try {
                iArr[ExternalAction.GO_LIBRARY_PLAYLISTS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$ExternalActionInterface$v1_0$ExternalAction[ExternalAction.GO_LIBRARY_PLAYLISTS_SEE_MORE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$ExternalActionInterface$v1_0$ExternalAction[ExternalAction.CREATE_NEW_PLAYLIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$ExternalActionInterface$v1_0$ExternalAction[ExternalAction.GO_LIBRARY_SONGS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$ExternalActionInterface$v1_0$ExternalAction[ExternalAction.GO_LIBRARY_ARTISTS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$ExternalActionInterface$v1_0$ExternalAction[ExternalAction.GO_LIBRARY_ALBUMS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$ExternalActionInterface$v1_0$ExternalAction[ExternalAction.GO_LIBRARY_PODCASTS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$ExternalActionInterface$v1_0$ExternalAction[ExternalAction.SHOW_RECENTLY_PLAYED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$ExternalActionInterface$v1_0$ExternalAction[ExternalAction.SHOW_RECENTLY_ADDED.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$ExternalActionInterface$v1_0$ExternalAction[ExternalAction.SHOW_RECENTLY_DOWNLOADED.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$ExternalActionInterface$v1_0$ExternalAction[ExternalAction.SHOW_IMPORT_PLAYLISTS.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$ExternalActionInterface$v1_0$ExternalAction[ExternalAction.GO_BROWSE_HOME.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$ExternalActionInterface$v1_0$ExternalAction[ExternalAction.SHOW_CATALOG_PLAYLIST.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$ExternalActionInterface$v1_0$ExternalAction[ExternalAction.SHOW_LIBRARY_PLAYLIST.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$ExternalActionInterface$v1_0$ExternalAction[ExternalAction.PLAY_STATION.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$ExternalActionInterface$v1_0$ExternalAction[ExternalAction.PLAY_PLAYLIST.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$ExternalActionInterface$v1_0$ExternalAction[ExternalAction.PLAY_ARTIST.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$ExternalActionInterface$v1_0$ExternalAction[ExternalAction.PLAY_ALBUM.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }

    private List<Map<String, String>> getContentInfo(Map<String, String> actionContextMap) {
        String str;
        String str2 = null;
        if (actionContextMap == null || actionContextMap.isEmpty()) {
            str = null;
        } else {
            str2 = actionContextMap.get(ContextMappingConstants.CONTENT_SRC);
            str = actionContextMap.get("contentInfoInstId");
        }
        ArrayList arrayList = new ArrayList();
        if (str2 != null && str != null) {
            HashMap map = new HashMap();
            map.put(ContextMappingConstants.CONTENT_SRC, str2);
            map.put("contentInfoInstId", str);
            arrayList.add(map);
        }
        return arrayList;
    }
}
