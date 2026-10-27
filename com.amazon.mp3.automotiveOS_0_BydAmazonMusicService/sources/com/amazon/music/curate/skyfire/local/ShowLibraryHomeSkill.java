package com.amazon.music.curate.skyfire.local;

import CoreInterface.v1_0.Method;
import TemplateContainerInterface.v1_0.ImmutableCreateAndBindManagedContainerMethod;
import Touch.GalleryTemplateInterface.v1_0.ImmutableGalleryTemplate;
import Touch.SyncInterface.v1_0.PageContext;
import Touch.SyncInterface.v1_0.WidgetContext;
import Touch.SyncInterface.v1_0.WidgetItemContext;
import Touch.WidgetsInterface.v1_0.BadgeItemElement;
import Touch.WidgetsInterface.v1_0.ImmutableAllAccessBadgeItemElement;
import Touch.WidgetsInterface.v1_0.ImmutableAudioQualityBadgeItemElement;
import Touch.WidgetsInterface.v1_0.ImmutableCircleVerticalItemElement;
import Touch.WidgetsInterface.v1_0.ImmutableIngressButtonElement;
import Touch.WidgetsInterface.v1_0.ImmutableLinkNavigatorElement;
import Touch.WidgetsInterface.v1_0.ImmutableLinkNavigatorItemElement;
import Touch.WidgetsInterface.v1_0.ImmutableMessageElement;
import Touch.WidgetsInterface.v1_0.ImmutablePillNavigatorWidgetElement;
import Touch.WidgetsInterface.v1_0.ImmutablePillNavigatorWidgetItemElement;
import Touch.WidgetsInterface.v1_0.ImmutableSquareVerticalItemElement;
import Touch.WidgetsInterface.v1_0.ImmutableTextButtonElement;
import Touch.WidgetsInterface.v1_0.ImmutableVisualShovelerElement;
import Touch.WidgetsInterface.v1_0.ImmutableVisualShovelerSeeMoreItemElement;
import Touch.WidgetsInterface.v1_0.LinkNavigatorItemElement;
import Touch.WidgetsInterface.v1_0.PillNavigatorWidgetItemElement;
import Touch.WidgetsInterface.v1_0.VerticalItemElement;
import Touch.WidgetsInterface.v1_0.WidgetElement;
import android.content.Context;
import com.amazon.music.curate.R;
import com.amazon.music.curate.featuregating.CurateFeatureGating;
import com.amazon.music.curate.model.LibraryContextMapping;
import com.amazon.music.curate.provider.InternalProvider;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public final class ShowLibraryHomeSkill {
    private static final String TAG = "ShowLibraryHomeSkill";
    private static final Logger logger = LoggerFactory.getLogger(ShowLibraryHomeSkill.class.getSimpleName());
    private final Context context;
    private boolean isEmptyState = true;
    final List<PillNavigatorWidgetItemElement> libraryPills = new ArrayList();
    final List<LinkNavigatorItemElement> recentsLinks = new ArrayList();
    final List<VerticalItemElement> playlistsShovelerItems = new ArrayList();
    final List<VerticalItemElement> madeForYouShovelerItems = new ArrayList();
    final List<VerticalItemElement> recentlyPlayedShovelerItems = new ArrayList();

    public ShowLibraryHomeSkill(final Context context) {
        this.context = context;
    }

    public List<Method> invoke() {
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add(ImmutableCreateAndBindManagedContainerMethod.builder().queue(Queues.template()).template(ImmutableGalleryTemplate.builder().widgetElements(getLibraryLandingPageWidgets()).build()).build());
            return arrayList;
        } catch (Exception e) {
            logger.error("failed to invoke local ShowLibraryHomeSkill", (Throwable) e);
            return Collections.EMPTY_LIST;
        }
    }

    private List<WidgetElement> getLibraryLandingPageWidgets() {
        Iterator<Map.Entry<String, List<LibraryContextMapping>>> it = groupByWidgetContext(InternalProvider.Singleton.INSTANCE.get().getDatabase().libraryContextMappingDao().getMappingsByPageContext(PageContext.LIBRARY_FRONT_PAGE.name())).entrySet().iterator();
        while (it.hasNext()) {
            bindWidgetItems(it.next().getValue());
        }
        if (!this.isEmptyState) {
            return bindWidgets();
        }
        return bindEmptyState();
    }

    private LinkedHashMap<String, List<LibraryContextMapping>> groupByWidgetContext(final List<LibraryContextMapping> mappings) {
        LinkedHashMap<String, List<LibraryContextMapping>> linkedHashMap = new LinkedHashMap<>();
        new ArrayList();
        for (LibraryContextMapping libraryContextMapping : mappings) {
            String widgetContext = libraryContextMapping.getWidgetContext();
            if (!linkedHashMap.containsKey(libraryContextMapping.getWidgetContext())) {
                ArrayList arrayList = new ArrayList();
                linkedHashMap.put(widgetContext, arrayList);
                arrayList.add(libraryContextMapping);
            } else {
                linkedHashMap.get(widgetContext).add(libraryContextMapping);
            }
        }
        return linkedHashMap;
    }

    private List<WidgetElement> bindEmptyState() {
        ArrayList arrayList = new ArrayList();
        if (!CollectionUtils.isEmpty(this.libraryPills)) {
            arrayList.add(ImmutablePillNavigatorWidgetElement.builder().header(this.context.getString(R.string.library_label)).items(this.libraryPills).build());
        }
        arrayList.add(ImmutableMessageElement.builder().message(this.context.getString(R.string.empty_state_message_primary_text)).subMessage(this.context.getString(R.string.empty_state_message_secondary_text)).buttons(Arrays.asList(ImmutableTextButtonElement.builder().text(this.context.getString(R.string.empty_state_explore_button)).onItemSelected(ShowBrowseHomeSkill.prepareInvoke()).build())).build());
        if (shouldShowBannerToImportPlaylists()) {
            arrayList.add(ImmutableLinkNavigatorElement.builder().addItems(ImmutableLinkNavigatorItemElement.builder().mainText(this.context.getString(R.string.empty_state_import_playlists)).onItemSelected(ShowImportPlaylistsSkill.prepareInvoke()).build()).build());
        }
        return arrayList;
    }

    private List<WidgetElement> bindWidgets() {
        String string;
        ArrayList arrayList = new ArrayList();
        if (!CollectionUtils.isEmpty(this.libraryPills)) {
            ImmutablePillNavigatorWidgetElement.Builder builder = ImmutablePillNavigatorWidgetElement.builder();
            if (!CurateFeatureGating.NATIVE_PROFILE_MY_LIBRARY_PAGE_ANDROID.isEnabled()) {
                builder.header(this.context.getString(R.string.library_label));
            }
            builder.items(this.libraryPills);
            arrayList.add(builder.build());
        }
        if (!CollectionUtils.isEmpty(this.playlistsShovelerItems)) {
            ImmutableVisualShovelerElement.Builder builderItems = ImmutableVisualShovelerElement.builder().primaryHeader(this.context.getString(R.string.playlists_header)).items(this.playlistsShovelerItems);
            if (this.playlistsShovelerItems.size() >= 20) {
                builderItems.seeMore(ImmutableVisualShovelerSeeMoreItemElement.builder().text(this.context.getString(R.string.see_more_button_text)).onItemSelected(ShowPlaylistsSkill.prepareInvoke()).build());
            }
            if (shouldShowNewPlaylistButtonLibrary()) {
                String string2 = this.context.getString(R.string.dmusic_new_playlist_library_landing);
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(ImmutableIngressButtonElement.builder().text(string2).onItemSelected(CreateNewPlaylistSkill.prepareInvoke()).icon(string2).build());
                builderItems.additionalActionButtons(arrayList2);
            }
            arrayList.add(builderItems.build());
        }
        if (!CollectionUtils.isEmpty(this.madeForYouShovelerItems)) {
            arrayList.add(ImmutableVisualShovelerElement.builder().primaryHeader(this.context.getString(R.string.made_for_you_header)).items(this.madeForYouShovelerItems).build());
        }
        if (!CollectionUtils.isEmpty(this.recentlyPlayedShovelerItems)) {
            arrayList.add(ImmutableVisualShovelerElement.builder().primaryHeader(this.context.getString(R.string.recently_played_header)).items(this.recentlyPlayedShovelerItems).build());
        }
        if (!CollectionUtils.isEmpty(this.recentsLinks)) {
            if (shouldShowBannerToImportPlaylists()) {
                string = this.context.getString(R.string.recents_import_label);
            } else {
                string = this.context.getString(R.string.recents_label);
            }
            arrayList.add(ImmutableLinkNavigatorElement.builder().header(string).items(this.recentsLinks).build());
        }
        return arrayList;
    }

    private boolean bindWidgetItems(final List<LibraryContextMapping> itemMapping) {
        for (LibraryContextMapping libraryContextMapping : itemMapping) {
            if (isLibraryContent(libraryContextMapping)) {
                this.libraryPills.add(ImmutablePillNavigatorWidgetItemElement.builder().primaryText(libraryContextMapping.getPrimaryText()).onItemSelected(getLibraryContentMethods(libraryContextMapping)).build());
            } else if (isRecents(libraryContextMapping)) {
                if (!isSongHistoryWidget(libraryContextMapping) || shouldShowSongHistoryLink()) {
                    this.recentsLinks.add(ImmutableLinkNavigatorItemElement.builder().mainText(libraryContextMapping.getPrimaryText()).onItemSelected(getRecentsMethods(libraryContextMapping)).build());
                }
            } else if (isPlaylists(libraryContextMapping)) {
                this.isEmptyState = false;
                logger.debug("Playlists data exist");
                this.playlistsShovelerItems.add(ImmutableSquareVerticalItemElement.builder().primaryText(libraryContextMapping.getPrimaryText()).secondaryText(libraryContextMapping.getSecondaryText()).bottomBadges(convertToBadges(libraryContextMapping.getContentEncoding())).onItemSelected(getPlaylistsMethods(libraryContextMapping)).image(libraryContextMapping.getImage()).build());
            } else if (isMadeForYou(libraryContextMapping)) {
                if (!ContextMappingConstants.MY_SOUNDTRACK_STATION_KEY.equalsIgnoreCase(libraryContextMapping.getEntityId())) {
                    this.isEmptyState = false;
                    logger.debug("Made For You data exist");
                }
                if (WidgetItemContext.ENTITY_ARTIST.name().equalsIgnoreCase(libraryContextMapping.getWidgetItemContext())) {
                    this.madeForYouShovelerItems.add(ImmutableCircleVerticalItemElement.builder().label(getItemLabel(libraryContextMapping.getWidgetItemContext())).primaryText(libraryContextMapping.getPrimaryText()).secondaryText(libraryContextMapping.getSecondaryText()).onItemSelected(getMadeForYouMethods(libraryContextMapping)).image(libraryContextMapping.getImage()).build());
                } else {
                    this.madeForYouShovelerItems.add(ImmutableSquareVerticalItemElement.builder().label(getItemLabel(libraryContextMapping.getWidgetItemContext())).primaryText(libraryContextMapping.getPrimaryText()).secondaryText(libraryContextMapping.getSecondaryText()).bottomBadges(convertToBadges(libraryContextMapping.getContentEncoding())).onItemSelected(getMadeForYouMethods(libraryContextMapping)).image(libraryContextMapping.getImage()).build());
                }
            } else if (isRecentlyPlayed(libraryContextMapping)) {
                this.isEmptyState = false;
                logger.debug("Recently Played data exist");
                if (WidgetItemContext.ENTITY_ARTIST.name().equalsIgnoreCase(libraryContextMapping.getWidgetItemContext())) {
                    this.recentlyPlayedShovelerItems.add(ImmutableCircleVerticalItemElement.builder().label(getItemLabel(libraryContextMapping.getWidgetItemContext())).primaryText(libraryContextMapping.getPrimaryText()).secondaryText(libraryContextMapping.getSecondaryText()).bottomBadges(convertToBadges(libraryContextMapping.getContentEncoding())).onItemSelected(getRecentlyPlayedMethods(libraryContextMapping)).image(libraryContextMapping.getImage()).build());
                } else {
                    this.recentlyPlayedShovelerItems.add(ImmutableSquareVerticalItemElement.builder().label(getItemLabel(libraryContextMapping.getWidgetItemContext())).primaryText(libraryContextMapping.getPrimaryText()).secondaryText(libraryContextMapping.getSecondaryText()).bottomBadges(convertToBadges(libraryContextMapping.getContentEncoding())).onItemSelected(getRecentlyPlayedMethods(libraryContextMapping)).image(libraryContextMapping.getImage()).build());
                }
            }
            logger.debug("mapping {}", libraryContextMapping.toString());
        }
        return this.isEmptyState;
    }

    private List<BadgeItemElement> convertToBadges(final String contentEncoding) {
        if (StringUtils.isBlank(contentEncoding)) {
            return Collections.EMPTY_LIST;
        }
        String[] strArrSplit = contentEncoding.split(",");
        ArrayList arrayList = new ArrayList();
        for (String str : strArrSplit) {
            logger.debug("content encoding {}", str);
            if (StringUtils.contains(str, ContextMappingConstants.ALL_ACCESS_BADGE_KEY)) {
                arrayList.add(ImmutableAllAccessBadgeItemElement.builder().description(this.context.getString(R.string.all_access_badge)).build());
            } else {
                arrayList.add(ImmutableAudioQualityBadgeItemElement.builder().description(str).build());
            }
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: com.amazon.music.curate.skyfire.local.ShowLibraryHomeSkill$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$Touch$SyncInterface$v1_0$WidgetItemContext;

        static {
            int[] iArr = new int[WidgetItemContext.values().length];
            $SwitchMap$Touch$SyncInterface$v1_0$WidgetItemContext = iArr;
            try {
                iArr[WidgetItemContext.ENTITY_CATALOG_PLAYLIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$Touch$SyncInterface$v1_0$WidgetItemContext[WidgetItemContext.ENTITY_LIBRARY_PLAYLIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$Touch$SyncInterface$v1_0$WidgetItemContext[WidgetItemContext.ENTITY_STATION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$Touch$SyncInterface$v1_0$WidgetItemContext[WidgetItemContext.ENTITY_ALBUM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$Touch$SyncInterface$v1_0$WidgetItemContext[WidgetItemContext.ENTITY_ARTIST.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$Touch$SyncInterface$v1_0$WidgetItemContext[WidgetItemContext.ACTION_NAVIGATION_PLAYLISTS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$Touch$SyncInterface$v1_0$WidgetItemContext[WidgetItemContext.ACTION_NAVIGATION_SONGS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$Touch$SyncInterface$v1_0$WidgetItemContext[WidgetItemContext.ACTION_NAVIGATION_ARTISTS.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$Touch$SyncInterface$v1_0$WidgetItemContext[WidgetItemContext.ACTION_NAVIGATION_ALBUMS.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$Touch$SyncInterface$v1_0$WidgetItemContext[WidgetItemContext.ACTION_NAVIGATION_PODCASTS.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$Touch$SyncInterface$v1_0$WidgetItemContext[WidgetItemContext.ACTION_NAVIGATION_RECENTS_SONGS_HISTORY.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$Touch$SyncInterface$v1_0$WidgetItemContext[WidgetItemContext.ACTION_NAVIGATION_RECENTS_ADDED_SONGS.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$Touch$SyncInterface$v1_0$WidgetItemContext[WidgetItemContext.ACTION_NAVIGATION_RECENTS_DOWNLOADED_SONGS.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$Touch$SyncInterface$v1_0$WidgetItemContext[WidgetItemContext.ACTION_NAVIGATION_IMPORT_PLAYLISTS.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
        }
    }

    private String getItemLabel(final String itemContext) {
        int i = AnonymousClass1.$SwitchMap$Touch$SyncInterface$v1_0$WidgetItemContext[WidgetItemContext.valueOf(itemContext).ordinal()];
        if (i == 1 || i == 2) {
            return this.context.getString(R.string.playlist_label).toUpperCase();
        }
        if (i == 3) {
            return this.context.getString(R.string.station_label).toUpperCase();
        }
        if (i != 4) {
            return i != 5 ? "" : this.context.getString(R.string.artist_label).toUpperCase();
        }
        return this.context.getString(R.string.album_label).toUpperCase();
    }

    private boolean isLibraryContent(final LibraryContextMapping mapping) {
        return WidgetContext.NAVIGATION_LIBRARY_CONTENT.name().equalsIgnoreCase(mapping.getWidgetContext());
    }

    private boolean isRecents(final LibraryContextMapping mapping) {
        return WidgetContext.NAVIGATION_RECENTS.name().equalsIgnoreCase(mapping.getWidgetContext());
    }

    private boolean isPlaylists(final LibraryContextMapping mapping) {
        return WidgetContext.SHOVELER_PLAYLISTS.name().equalsIgnoreCase(mapping.getWidgetContext());
    }

    private boolean isMadeForYou(final LibraryContextMapping mapping) {
        return WidgetContext.SHOVELER_MADE_FOR_YOU.name().equalsIgnoreCase(mapping.getWidgetContext());
    }

    private boolean isRecentlyPlayed(final LibraryContextMapping mapping) {
        return WidgetContext.SHOVELER_RECENTLY_PLAYED.name().equalsIgnoreCase(mapping.getWidgetContext());
    }

    private boolean isSongHistoryWidget(final LibraryContextMapping mapping) {
        return WidgetItemContext.ACTION_NAVIGATION_RECENTS_SONGS_HISTORY.name().equalsIgnoreCase(mapping.getWidgetItemContext());
    }

    private List<Method> getLibraryContentMethods(final LibraryContextMapping mapping) {
        switch (AnonymousClass1.$SwitchMap$Touch$SyncInterface$v1_0$WidgetItemContext[WidgetItemContext.valueOf(mapping.getWidgetItemContext()).ordinal()]) {
            case 6:
                return ShowPlaylistsSkill.prepareInvoke();
            case 7:
                return ShowSongsSkill.prepareInvoke();
            case 8:
                return ShowArtistsSkill.prepareInvoke();
            case 9:
                return ShowAlbumsSkill.prepareInvoke();
            case 10:
                return ShowPodcastsSkill.prepareInvoke();
            default:
                return Collections.EMPTY_LIST;
        }
    }

    private List<Method> getRecentsMethods(final LibraryContextMapping mapping) {
        switch (AnonymousClass1.$SwitchMap$Touch$SyncInterface$v1_0$WidgetItemContext[WidgetItemContext.valueOf(mapping.getWidgetItemContext()).ordinal()]) {
            case 11:
                return ShowRecentlyPlayedSkill.prepareInvoke();
            case 12:
                return ShowRecentlyAddedSkill.prepareInvoke();
            case 13:
                return ShowRecentlyDownloadedSkill.prepareInvoke();
            case 14:
                return ShowImportPlaylistsSkill.prepareInvoke();
            default:
                return Collections.EMPTY_LIST;
        }
    }

    private List<Method> getRecentlyPlayedMethods(final LibraryContextMapping mapping) {
        Map<String, String> contentInfo = getContentInfo(mapping);
        int i = AnonymousClass1.$SwitchMap$Touch$SyncInterface$v1_0$WidgetItemContext[WidgetItemContext.valueOf(mapping.getWidgetItemContext()).ordinal()];
        if (i == 1) {
            return PlayCatalogPlaylistSkill.prepareInvoke(mapping.getEntityIdType(), mapping.getEntityId(), mapping.getPlayMode(), contentInfo);
        }
        if (i == 2) {
            return PlayLibraryPlaylistSkill.prepareInvoke(mapping.getEntityIdType(), mapping.getEntityId(), mapping.getPlayMode(), contentInfo);
        }
        if (i == 3) {
            return PlayStationSkill.prepareInvoke(mapping.getEntityIdType(), mapping.getEntityId(), contentInfo);
        }
        if (i == 4) {
            return PlayAlbumSkill.prepareInvoke(mapping.getEntityIdType(), mapping.getEntityId(), mapping.getPlayMode(), contentInfo);
        }
        if (i == 5) {
            return PlayArtistSkill.prepareInvoke(mapping.getEntityIdType(), mapping.getEntityId(), mapping.getPlayMode(), contentInfo);
        }
        return Collections.EMPTY_LIST;
    }

    private List<Method> getPlaylistsMethods(final LibraryContextMapping mapping) {
        Map<String, String> contentInfo = getContentInfo(mapping);
        if (shouldShowOneClickButtonPlaylistWidget()) {
            ArrayList arrayList = new ArrayList();
            int i = AnonymousClass1.$SwitchMap$Touch$SyncInterface$v1_0$WidgetItemContext[WidgetItemContext.valueOf(mapping.getWidgetItemContext()).ordinal()];
            if (i == 1) {
                arrayList.add(ShowCatalogPlaylistSkill.prepareInvoke(mapping.getEntityIdType(), mapping.getEntityId(), contentInfo).get(0));
                arrayList.add(PlayCatalogPlaylistSkill.prepareInvoke(mapping.getEntityIdType(), mapping.getEntityId(), mapping.getPlayMode(), contentInfo).get(0));
                return arrayList;
            }
            if (i == 2) {
                arrayList.add(ShowLibraryPlaylistSkill.prepareInvoke(mapping.getEntityIdType(), mapping.getEntityId(), contentInfo).get(0));
                arrayList.add(PlayLibraryPlaylistSkill.prepareInvoke(mapping.getEntityIdType(), mapping.getEntityId(), mapping.getPlayMode(), contentInfo).get(0));
                return arrayList;
            }
            return Collections.EMPTY_LIST;
        }
        int i2 = AnonymousClass1.$SwitchMap$Touch$SyncInterface$v1_0$WidgetItemContext[WidgetItemContext.valueOf(mapping.getWidgetItemContext()).ordinal()];
        if (i2 == 1) {
            return ShowCatalogPlaylistSkill.prepareInvoke(mapping.getEntityIdType(), mapping.getEntityId(), contentInfo);
        }
        if (i2 == 2) {
            return ShowLibraryPlaylistSkill.prepareInvoke(mapping.getEntityIdType(), mapping.getEntityId(), contentInfo);
        }
        return Collections.EMPTY_LIST;
    }

    private List<Method> getMadeForYouMethods(final LibraryContextMapping mapping) {
        Map<String, String> contentInfo = getContentInfo(mapping);
        int i = AnonymousClass1.$SwitchMap$Touch$SyncInterface$v1_0$WidgetItemContext[WidgetItemContext.valueOf(mapping.getWidgetItemContext()).ordinal()];
        if (i == 1) {
            return ShowCatalogPlaylistSkill.prepareInvoke(mapping.getEntityIdType(), mapping.getEntityId(), contentInfo);
        }
        if (i == 2) {
            return ShowLibraryPlaylistSkill.prepareInvoke(mapping.getEntityIdType(), mapping.getEntityId(), contentInfo);
        }
        if (i == 3) {
            return PlayStationSkill.prepareInvoke(mapping.getEntityIdType(), mapping.getEntityId(), contentInfo);
        }
        return Collections.EMPTY_LIST;
    }

    private Map<String, String> getContentInfo(final LibraryContextMapping mapping) {
        HashMap map = new HashMap();
        if (StringUtils.isNotBlank(mapping.getContentSrc()) && StringUtils.isNotBlank(mapping.getContentInfoInstId())) {
            map.put(ContextMappingConstants.CONTENT_SRC, mapping.getContentSrc());
            map.put("contentInfoInstId", mapping.getContentInfoInstId());
        }
        return map;
    }

    private boolean shouldShowBannerToImportPlaylists() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(CurateFeatureGating.IMPORT_PLAYLIST);
    }

    private boolean shouldShowOneClickButtonPlaylistWidget() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(CurateFeatureGating.SHOW_ONE_CLICK_BUTTON_PLAYLIST);
    }

    private boolean shouldShowSongHistoryLink() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        return (featureGateProvider == null || featureGateProvider.isFeatureAvailable(CurateFeatureGating.DISABLE_SONG_HISTORY_LINK)) ? false : true;
    }

    private boolean shouldShowNewPlaylistButtonLibrary() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(CurateFeatureGating.SHOW_NEW_PLAYLIST_BUTTON_LIBRARY);
    }
}
