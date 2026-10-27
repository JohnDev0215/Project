package com.amazon.mp3.catalog.fragment.converter;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.baseviews.model.configuration.ContainerModelConfiguration;
import com.amazon.mp3.baseviews.model.configuration.PlaylistModelConfiguration;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.detailpages.album.util.AlbumDetailPageHeaderUtil;
import com.amazon.mp3.eligibility.CachedCapabilityActionProviderImpl;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.environment.MusicTerritory;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.data.impl.ContributorAccessObject;
import com.amazon.mp3.library.data.impl.ContributorManagerImpl;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.item.ItemWrapper$IdType;
import com.amazon.mp3.library.item.ItemWrapper$ItemType;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.util.AllAccessUserPlaylistLimitTracker;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.prime.PrimePlaylistTracksTable;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.freetier.featuregating.FMPMFeatureGating;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.models.ArtistDetailHeaderModel;
import com.amazon.music.views.library.models.CollectionDetailHeaderModel;
import com.amazon.music.views.library.models.DownloadStateModel;
import com.amazon.music.views.library.models.PageHeaderActionIconModel;
import com.amazon.music.views.library.models.StoryPageHeaderModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryDetailHeaderConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\b\u0010:\u001a\u0004\u0018\u000109\u0012\n\b\u0002\u0010?\u001a\u0004\u0018\u00010>¢\u0006\u0004\bM\u0010NJ\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0002H\u0002J\u0012\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0002J\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0002J\u0012\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0002J\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u0017\u001a\u00020\u0016H\u0002J\u000e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0002J&\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u0014H\u0002J&\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u0014H\u0002J\b\u0010#\u001a\u00020\"H\u0002J\u0012\u0010&\u001a\u00020\u00142\b\u0010%\u001a\u0004\u0018\u00010$H\u0002J\u0010\u0010'\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u001dH\u0002J\u0016\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u001e\u001a\u00020\u001dH\u0002J\u0010\u0010)\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u001dH\u0002J\b\u0010*\u001a\u00020\u0014H\u0002J\u0018\u0010+\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u001dH\u0002J\u000e\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0002J\b\u0010-\u001a\u00020\u0014H\u0002J\u0014\u0010/\u001a\u0004\u0018\u00010\u00102\b\u0010.\u001a\u0004\u0018\u00010\u0010H\u0002J\u0018\u00102\u001a\u0002012\u0006\u0010\n\u001a\u00020\u00022\u0006\u00100\u001a\u00020\u0014H\u0002J\u0012\u00104\u001a\u0004\u0018\u00010\u00052\u0006\u00103\u001a\u00020\u0002H\u0016J8\u00108\u001a\u0002012\b\b\u0002\u00105\u001a\u00020\u00142\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00022\u0010\b\u0002\u00107\u001a\n\u0012\u0004\u0012\u000206\u0018\u00010\u00182\b\b\u0002\u00100\u001a\u00020\u0014R\u0019\u0010:\u001a\u0004\u0018\u0001098\u0006¢\u0006\f\n\u0004\b:\u0010;\u001a\u0004\b<\u0010=R\u0019\u0010?\u001a\u0004\u0018\u00010>8\u0006¢\u0006\f\n\u0004\b?\u0010@\u001a\u0004\bA\u0010BR\u0014\u0010*\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010CR\u001e\u00107\u001a\n\u0012\u0004\u0012\u000206\u0018\u00010\u00188\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b7\u0010DR\u0016\u0010E\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bE\u0010CR\u0016\u0010F\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bF\u0010CR\u0016\u00100\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b0\u0010CR\u0016\u00105\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b5\u0010CR\u0017\u0010G\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\bG\u0010C\u001a\u0004\bG\u0010HR\u0017\u0010I\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\bI\u0010C\u001a\u0004\bI\u0010HR\u0014\u0010K\u001a\u00020\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010HR\u0014\u0010L\u001a\u00020\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bL\u0010H¨\u0006O"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/converter/LibraryDetailHeaderConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/mp3/library/item/AbstractItem;", "Lcom/amazon/mp3/library/item/Album;", "album", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convertAlbum", "Lcom/amazon/mp3/library/item/Artist;", "artist", "convertArtist", Environment.PLAYLIST_PATH, "convertPlaylist", "Lcom/amazon/music/views/library/metadata/PlaylistMetadata;", "metadata", "", "getLabelResourceKey", "", "getLabelBadgeText", "Lcom/amazon/mp3/playlist/CatalogPlaylist;", "catalogPlaylist", "", "shouldUseStoryPageHeader", "Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "artistMetadata", "", "Lcom/amazon/music/views/library/models/PageHeaderActionIconModel;", "generateArtistDetailHeaderIcons", "generateArtistDetailHeaderIconsSR", "isCatalog", "Landroid/net/Uri;", "playlistUri", "isFreeOnDemand", "generatePlaylistDetailHeaderIconsSR", "generatePlaylistDetailHeaderIcons", "Lcom/amazon/music/views/library/models/DownloadStateModel;", "getInitialGroupDownloadState", "Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;", "parentEligibilitiesProvider", "areTracksAvailable", "isUserPlaylistShareEnabled", "partiallyOwnedFreeTierPlaylistDetailHeaderIcons", "shouldEnablePlayback", "areAllTracksExplicit", "shouldShowSFAButton", "onDemandGoldenPlaylistDetailHeaderIcons", "shouldOverrideTitleCaps", "artistId", "getHeroImageUrl", "isOnDemandGoldenPlaylist", "", "updatePlaylistDependentValues", "data", "convert", "isNightwingOnly", "Lcom/amazon/mp3/library/item/MusicTrack;", "tracks", "refreshUserSubscriptionValues", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/amazon/mp3/baseviews/model/configuration/ContainerModelConfiguration;", "modelConfiguration", "Lcom/amazon/mp3/baseviews/model/configuration/ContainerModelConfiguration;", "getModelConfiguration", "()Lcom/amazon/mp3/baseviews/model/configuration/ContainerModelConfiguration;", "Z", "Ljava/util/List;", "isAllOwned", "isPartiallyOwned", "isSonicRushTierOnly", "()Z", "isSonicRushEnabledForPageHeaders", "getContainsTracks", "containsTracks", "isVideoTrackAvailable", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/baseviews/model/configuration/ContainerModelConfiguration;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LibraryDetailHeaderConverter implements SingleBaseModelConverter<AbstractItem> {
    private final boolean areAllTracksExplicit;
    private final Context context;
    private boolean isAllOwned;
    private boolean isNightwingOnly;
    private boolean isOnDemandGoldenPlaylist;
    private boolean isPartiallyOwned;
    private final boolean isSonicRushEnabledForPageHeaders;
    private final boolean isSonicRushTierOnly;
    private final ContainerModelConfiguration modelConfiguration;
    private List<? extends MusicTrack> tracks;

    public LibraryDetailHeaderConverter(Context context, ContainerModelConfiguration containerModelConfiguration) {
        this.context = context;
        this.modelConfiguration = containerModelConfiguration;
        this.areAllTracksExplicit = areAllTracksExplicit();
        this.isNightwingOnly = UserSubscriptionUtil.isNightwingOnly();
        this.isSonicRushTierOnly = !UserSubscriptionUtil.isHawkfire() && UserSubscriptionUtil.getUserSubscription().isSonicRush();
        this.isSonicRushEnabledForPageHeaders = Feature.SR_page_headers.isEnabled();
    }

    public /* synthetic */ LibraryDetailHeaderConverter(Context context, ContainerModelConfiguration containerModelConfiguration, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : containerModelConfiguration);
    }

    public final Context getContext() {
        return this.context;
    }

    public final ContainerModelConfiguration getModelConfiguration() {
        return this.modelConfiguration;
    }

    private final boolean getContainsTracks() {
        if (this.tracks == null) {
            return false;
        }
        return !r0.isEmpty();
    }

    private final boolean isVideoTrackAvailable() {
        MusicTrack musicTrack;
        List<? extends MusicTrack> list = this.tracks;
        PrimePlaylistTracksTable.AssetType assetType = null;
        if (list != null && (musicTrack = (MusicTrack) CollectionsKt___CollectionsKt.firstOrNull((List) list)) != null) {
            assetType = musicTrack.getAssetType();
        }
        return assetType == PrimePlaylistTracksTable.AssetType.VIDEO;
    }

    /* JADX INFO: renamed from: isSonicRushEnabledForPageHeaders, reason: from getter */
    public final boolean getIsSonicRushEnabledForPageHeaders() {
        return this.isSonicRushEnabledForPageHeaders;
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(AbstractItem data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (data instanceof Album) {
            return convertAlbum((Album) data);
        }
        if (data instanceof Artist) {
            return convertArtist((Artist) data);
        }
        if (data instanceof Playlist ? true : data instanceof CatalogPlaylist) {
            return convertPlaylist(data);
        }
        return null;
    }

    private final BaseViewModel convertAlbum(Album album) {
        Context context = this.context;
        CollectionDetailHeaderModel collectionDetailHeaderModel = new CollectionDetailHeaderModel("", context == null ? null : context.getString(R.string.dmusic_library_item_name_album), album.getTitle(), Integer.valueOf(R.id.AlbumName), album.getArtistName(), Integer.valueOf(R.id.ArtistName), BauhausDetailHeaderTextUtil.INSTANCE.getSecondaryText(album, this.tracks), null, null, AlbumDetailPageHeaderUtil.INSTANCE.generateDownloadedAlbumDetailHeaderIcons(album), LibraryContentMetadataConverter.convert$default(album, null, 2, null), getInitialGroupDownloadState(), null, null, false, null, null, null, 258304, null);
        collectionDetailHeaderModel.setSonicRushEnabled(this.isSonicRushEnabledForPageHeaders);
        return collectionDetailHeaderModel;
    }

    private final BaseViewModel convertArtist(Artist artist) {
        ContentMetadata contentMetadataConvert$default = LibraryContentMetadataConverter.convert$default(artist, null, 2, null);
        ArtistMetadata artistMetadata = contentMetadataConvert$default instanceof ArtistMetadata ? (ArtistMetadata) contentMetadataConvert$default : null;
        if (artistMetadata == null) {
            return null;
        }
        Context context = getContext();
        return new ArtistDetailHeaderModel(null, context != null ? context.getString(R.string.dmusic_library_item_name_artist) : null, artist.getName(), shouldOverrideTitleCaps(), null, null, getHeroImageUrl(artist.getId()), null, null, false, artistMetadata, getIsSonicRushEnabledForPageHeaders() ? generateArtistDetailHeaderIconsSR() : generateArtistDetailHeaderIcons(artistMetadata), null, 4529, null);
    }

    private final BaseViewModel convertPlaylist(AbstractItem playlist) {
        PlaylistModelConfiguration playlistModelConfiguration;
        List<PageHeaderActionIconModel> listGeneratePlaylistDetailHeaderIcons;
        List<String> contentTypes;
        List<PageHeaderActionIconModel> listGeneratePlaylistDetailHeaderIcons2;
        byte b = 0;
        byte b2 = 0;
        ContentMetadata contentMetadataConvert$default = LibraryContentMetadataConverter.convert$default(playlist, null, 2, null);
        PlaylistMetadata playlistMetadata = contentMetadataConvert$default instanceof PlaylistMetadata ? (PlaylistMetadata) contentMetadataConvert$default : null;
        int i = 1;
        this.isAllOwned = playlistMetadata != null && playlistMetadata.getIsAllOwned();
        boolean z = playlist instanceof CatalogPlaylist;
        Uri playlistUri = playlistMetadata == null ? null : playlistMetadata.getUri();
        if (playlistUri == null) {
            playlistUri = Uri.EMPTY;
        }
        CatalogPlaylist catalogPlaylist = z ? (CatalogPlaylist) playlist : null;
        boolean z2 = (catalogPlaylist != null && catalogPlaylist.isFree()) && catalogPlaylist.isFreeOnDemand() && this.isNightwingOnly;
        int labelResourceKey = getLabelResourceKey(playlistMetadata);
        Context context = this.context;
        String string = context == null ? null : context.getString(labelResourceKey);
        BauhausDetailHeaderTextUtil bauhausDetailHeaderTextUtil = BauhausDetailHeaderTextUtil.INSTANCE;
        String primaryText = bauhausDetailHeaderTextUtil.getPrimaryText(playlist);
        String curatedByProfileId = bauhausDetailHeaderTextUtil.getCuratedByProfileId(playlist);
        String secondaryText = bauhausDetailHeaderTextUtil.getSecondaryText(playlist, this.tracks);
        String descriptionText = bauhausDetailHeaderTextUtil.getDescriptionText(playlist);
        ContainerModelConfiguration containerModelConfiguration = this.modelConfiguration;
        if (containerModelConfiguration instanceof PlaylistModelConfiguration) {
            playlistModelConfiguration = (PlaylistModelConfiguration) containerModelConfiguration;
        } else {
            playlistModelConfiguration = new PlaylistModelConfiguration(b2 == true ? 1 : 0, i, b == true ? 1 : 0);
        }
        String followerCount = this.context == null ? null : bauhausDetailHeaderTextUtil.getFollowerCount(playlist, playlistModelConfiguration);
        if (shouldUseStoryPageHeader(catalogPlaylist)) {
            if (this.isSonicRushEnabledForPageHeaders) {
                Intrinsics.checkNotNullExpressionValue(playlistUri, "playlistUri");
                listGeneratePlaylistDetailHeaderIcons2 = generatePlaylistDetailHeaderIconsSR(z, playlistUri, z2);
            } else {
                Intrinsics.checkNotNullExpressionValue(playlistUri, "playlistUri");
                listGeneratePlaylistDetailHeaderIcons2 = generatePlaylistDetailHeaderIcons(z, playlistUri, z2);
            }
            return new StoryPageHeaderModel("", string, playlistMetadata != null ? playlistMetadata.getTitle() : null, primaryText, descriptionText, secondaryText, null, null, null, listGeneratePlaylistDetailHeaderIcons2, playlistMetadata, getInitialGroupDownloadState(), 64, null);
        }
        boolean zIsAllAccessUserPlaylistCompliant = AllAccessUserPlaylistLimitTracker.INSTANCE.isAllAccessUserPlaylistCompliant(playlistMetadata);
        String title = playlistMetadata == null ? null : playlistMetadata.getTitle();
        if (this.isSonicRushEnabledForPageHeaders) {
            Intrinsics.checkNotNullExpressionValue(playlistUri, "playlistUri");
            listGeneratePlaylistDetailHeaderIcons = generatePlaylistDetailHeaderIconsSR(z, playlistUri, z2);
        } else {
            Intrinsics.checkNotNullExpressionValue(playlistUri, "playlistUri");
            listGeneratePlaylistDetailHeaderIcons = generatePlaylistDetailHeaderIcons(z, playlistUri, z2);
        }
        CollectionDetailHeaderModel collectionDetailHeaderModel = new CollectionDetailHeaderModel("", string, title, null, primaryText, null, descriptionText, secondaryText, followerCount, listGeneratePlaylistDetailHeaderIcons, playlistMetadata, getInitialGroupDownloadState(), (!(playlistMetadata != null && (contentTypes = playlistMetadata.getContentTypes()) != null && contentTypes.contains("VIDEO")) || catalogPlaylist == null) ? null : catalogPlaylist.getThumbnailArtUrl(), curatedByProfileId, false, getLabelBadgeText(playlistMetadata), zIsAllAccessUserPlaylistCompliant ? FontStyleKey.LABEL : FontStyleKey.DISABLED_LABEL, Integer.valueOf(zIsAllAccessUserPlaylistCompliant ? R.drawable.label_badge_background : R.drawable.label_badge_background_disabled), 16424, null);
        collectionDetailHeaderModel.setSonicRushEnabled(this.isSonicRushEnabledForPageHeaders);
        return collectionDetailHeaderModel;
    }

    private final int getLabelResourceKey(PlaylistMetadata metadata) {
        List<String> contentTypes;
        String str = null;
        if (metadata != null && (contentTypes = metadata.getContentTypes()) != null) {
            str = (String) CollectionsKt___CollectionsKt.getOrNull(contentTypes, 0);
        }
        return Intrinsics.areEqual(str, "VIDEO") ? R.string.dmusic_prime_search_top_hit_video_playlist_subtitle : R.string.dmusic_library_item_name_playlist;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final String getLabelBadgeText(PlaylistMetadata metadata) {
        Integer trackCount;
        String luid;
        Context context;
        boolean zShouldShowOnDemandCapability = false;
        int i = 1;
        byte b = ((metadata != null && (trackCount = metadata.getTrackCount()) != null) ? trackCount.intValue() : 0) > 0;
        CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl = null;
        byte b2 = 0;
        if (!AmazonApplication.getCapabilities().isSonicRushEnabled() || !FMPMFeatureGating.IS_ALL_ACCESS_BADGING_ENABLED.isEnabled() || b != true) {
            return null;
        }
        if (metadata != null) {
            ContainerModelConfiguration modelConfiguration = getModelConfiguration();
            if (modelConfiguration == null) {
                modelConfiguration = new PlaylistModelConfiguration(cachedCapabilityActionProviderImpl, i, b2 == true ? 1 : 0);
            }
            zShouldShowOnDemandCapability = modelConfiguration.shouldShowOnDemandCapability(metadata);
        }
        if (zShouldShowOnDemandCapability) {
            Context context2 = this.context;
            if (context2 == null) {
                return null;
            }
            return context2.getString(R.string.dmusic_sonic_rush_tier_onDemand_pageheader_label);
        }
        if (metadata == null || (luid = metadata.getLuid()) == null || !GetOnDemandPlaylistsService.INSTANCE.get().isOnDemandUserPlaylist(luid) || (context = getContext()) == null) {
            return null;
        }
        return context.getString(R.string.dmusic_sonic_rush_tier_onDemand_pageheader_label);
    }

    private final boolean shouldUseStoryPageHeader(CatalogPlaylist catalogPlaylist) {
        return catalogPlaylist != null && catalogPlaylist.getHasVideoStory();
    }

    private final List<PageHeaderActionIconModel> generateArtistDetailHeaderIcons(ArtistMetadata artistMetadata) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.PLAY, false, false, false, null, null, 62, null));
        arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.SHUFFLE, false, false, false, null, null, 62, null));
        if (!AmazonApplication.getCapabilities().isEntityPlaylistSupported() && artistMetadata.getAsin() != null) {
            arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.STATION, false, false, false, null, null, 62, null));
        }
        return arrayList;
    }

    private final List<PageHeaderActionIconModel> generateArtistDetailHeaderIconsSR() {
        ArrayList arrayList = new ArrayList();
        if (this.isSonicRushTierOnly) {
            return arrayList;
        }
        arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.PLAY, false, false, false, null, null, 62, null));
        arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.SHUFFLE, false, false, false, null, null, 62, null));
        arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.STATION, false, false, false, null, null, 62, null));
        arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.OVERFLOW, false, false, false, null, null, 62, null));
        return arrayList;
    }

    private final List<PageHeaderActionIconModel> generatePlaylistDetailHeaderIconsSR(boolean isCatalog, Uri playlistUri, boolean isFreeOnDemand) {
        boolean zShouldShowOnDemandCapability;
        PageHeaderActionIconModel pageHeaderActionIconModel;
        AbstractItem libraryItem = new CirrusSourceLibraryItemFactory(this.context).getItem(playlistUri);
        int i = 1;
        if (libraryItem == null) {
            zShouldShowOnDemandCapability = false;
        } else {
            ContainerModelConfiguration modelConfiguration = getModelConfiguration();
            if (modelConfiguration == null) {
                modelConfiguration = new PlaylistModelConfiguration(null, i, 0 == true ? 1 : 0);
            }
            Intrinsics.checkNotNullExpressionValue(libraryItem, "libraryItem");
            zShouldShowOnDemandCapability = modelConfiguration.shouldShowOnDemandCapability(libraryItem);
        }
        ArrayList arrayList = new ArrayList();
        boolean z = areTracksAvailable(libraryItem) && !this.areAllTracksExplicit;
        boolean zIsEnabled = Feature.add_sharing_to_top_level_action.isEnabled();
        boolean z2 = Feature.supports_download_text_button.isEnabled() && !zIsEnabled;
        boolean zIsEnabled2 = Feature.supports_legacy_checks_for_capabilities.isEnabled();
        boolean zIsSmartPlaylist = MediaProvider.SmartPlaylists.isSmartPlaylist(playlistUri);
        boolean z3 = !isCatalog && getContainsTracks() && libraryItem.isAllOwned() && !zIsSmartPlaylist;
        PageHeaderActionIconModel pageHeaderActionIconModel2 = new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.PLAY, getContainsTracks(), z, false, null, null, 56, null);
        PageHeaderActionIconModel.ActionType actionType = PageHeaderActionIconModel.ActionType.DOWNLOAD;
        PageHeaderActionIconModel pageHeaderActionIconModel3 = new PageHeaderActionIconModel(actionType, getContainsTracks(), false, false, null, null, 60, null);
        PageHeaderActionIconModel pageHeaderActionIconModel4 = new PageHeaderActionIconModel(actionType, getContainsTracks(), false, true, null, null, 52, null);
        PageHeaderActionIconModel.ActionType actionType2 = PageHeaderActionIconModel.ActionType.SHUFFLE;
        PageHeaderActionIconModel pageHeaderActionIconModel5 = new PageHeaderActionIconModel(actionType2, getContainsTracks(), !this.areAllTracksExplicit, false, null, null, 56, null);
        boolean z4 = Feature.song_history_toolbar_disabled.isEnabled() && zIsSmartPlaylist;
        PageHeaderActionIconModel pageHeaderActionIconModel6 = new PageHeaderActionIconModel(actionType2, z4 ? z3 : getContainsTracks(), !this.areAllTracksExplicit, true, null, null, 48, null);
        PageHeaderActionIconModel pageHeaderActionIconModel7 = new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.OVERFLOW, false, false, false, null, null, 62, null);
        if (AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
            pageHeaderActionIconModel = new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.FAVORITE, true, false, false, null, null, 60, null);
        } else {
            pageHeaderActionIconModel = new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.FOLLOW, true, false, false, null, null, 60, null);
        }
        PageHeaderActionIconModel pageHeaderActionIconModel8 = new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.EDIT, true, false, false, null, null, 60, null);
        PageHeaderActionIconModel.ActionType actionType3 = PageHeaderActionIconModel.ActionType.SHARE;
        PageHeaderActionIconModel pageHeaderActionIconModel9 = new PageHeaderActionIconModel(actionType3, false, false, false, null, null, 62, null);
        if (zIsEnabled2) {
            boolean z5 = this.isOnDemandGoldenPlaylist || z3;
            if (z5) {
                arrayList.add(pageHeaderActionIconModel2);
            } else {
                arrayList.add(pageHeaderActionIconModel6);
            }
            if (z2 && z3) {
                arrayList.add(pageHeaderActionIconModel4);
            }
            if (z5) {
                arrayList.add(pageHeaderActionIconModel5);
            }
            if (isCatalog && AmazonApplication.getCapabilities().canFreeTierUserFollowPlaylists()) {
                arrayList.add(pageHeaderActionIconModel);
            }
            if (!zIsSmartPlaylist && !isCatalog) {
                arrayList.add(pageHeaderActionIconModel8);
            }
            if (zIsEnabled) {
                arrayList.add(pageHeaderActionIconModel9);
            }
            if (!z4 || (z4 && z3)) {
                arrayList.add(pageHeaderActionIconModel7);
            }
        } else {
            if (zShouldShowOnDemandCapability) {
                arrayList.add(pageHeaderActionIconModel2);
            } else {
                arrayList.add(pageHeaderActionIconModel6);
            }
            if (zShouldShowOnDemandCapability && z2 && !isVideoTrackAvailable()) {
                arrayList.add(pageHeaderActionIconModel4);
            }
            if (zShouldShowOnDemandCapability) {
                arrayList.add(pageHeaderActionIconModel5);
            }
            if (isVideoTrackAvailable()) {
                arrayList.add(new PageHeaderActionIconModel(actionType3, false, false, false, null, null, 62, null));
                return arrayList;
            }
            if (isCatalog) {
                arrayList.add(pageHeaderActionIconModel);
            } else if (!zIsSmartPlaylist) {
                arrayList.add(pageHeaderActionIconModel8);
            }
            if (zShouldShowOnDemandCapability && !z2 && !zIsSmartPlaylist) {
                arrayList.add(pageHeaderActionIconModel3);
            }
            if (zIsEnabled) {
                arrayList.add(pageHeaderActionIconModel9);
            }
            if (!z4 || (z4 && z3)) {
                arrayList.add(pageHeaderActionIconModel7);
            }
        }
        return arrayList;
    }

    private final List<PageHeaderActionIconModel> generatePlaylistDetailHeaderIcons(boolean isCatalog, Uri playlistUri, boolean isFreeOnDemand) {
        ArrayList arrayList = new ArrayList();
        boolean zShouldEnablePlayback = shouldEnablePlayback(playlistUri);
        boolean zIsRecentlyAddedPlaylist = PlaylistUtil.isRecentlyAddedPlaylist(playlistUri);
        if (PlaylistUtil.isRecentlyAddedToDevicePlaylist(playlistUri) || zIsRecentlyAddedPlaylist) {
            arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.PLAY, zShouldEnablePlayback, !this.areAllTracksExplicit, zIsRecentlyAddedPlaylist, null, null, 48, null));
            arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.SHUFFLE, zShouldEnablePlayback, !this.areAllTracksExplicit, zIsRecentlyAddedPlaylist, null, null, 48, null));
            return arrayList;
        }
        if (this.isOnDemandGoldenPlaylist) {
            return onDemandGoldenPlaylistDetailHeaderIcons();
        }
        if (this.isNightwingOnly && !this.isAllOwned) {
            if (this.isPartiallyOwned && !isCatalog) {
                return partiallyOwnedFreeTierPlaylistDetailHeaderIcons(playlistUri);
            }
            PageHeaderActionIconModel pageHeaderActionIconModel = new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.SHUFFLE, true, false, true, null, null, 52, null);
            PageHeaderActionIconModel pageHeaderActionIconModel2 = new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.STATION, true, false, true, null, null, 52, null);
            if (isFreeOnDemand) {
                arrayList.add(pageHeaderActionIconModel);
            } else {
                arrayList.add(pageHeaderActionIconModel2);
            }
            if (isCatalog && AmazonApplication.getCapabilities().canFreeTierUserFollowPlaylists()) {
                if (AmazonApplication.getCapabilities().playlistFollowButtonChangeEnabled() || AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
                    arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.FAVORITE, true, false, true, null, null, 52, null));
                } else {
                    arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.FOLLOW, true, false, true, null, null, 52, null));
                }
            }
            arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.OVERFLOW, false, false, false, null, null, 62, null));
            return arrayList;
        }
        boolean z = areTracksAvailable(new CirrusSourceLibraryItemFactory(this.context).getItem(playlistUri)) && !this.areAllTracksExplicit;
        arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.PLAY, zShouldEnablePlayback || isVideoTrackAvailable(), z, false, null, null, 56, null));
        arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.SHUFFLE, zShouldEnablePlayback || isVideoTrackAvailable(), z, false, null, null, 56, null));
        if (isVideoTrackAvailable()) {
            arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.SHARE, false, false, false, null, null, 62, null));
            return arrayList;
        }
        if (shouldShowSFAButton(isCatalog, playlistUri)) {
            arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.STATION, false, false, false, null, null, 62, null));
        }
        if (isCatalog && !this.isNightwingOnly) {
            if (AmazonApplication.getCapabilities().playlistFollowButtonChangeEnabled() || AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
                arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.FAVORITE, getContainsTracks(), false, false, null, null, 60, null));
            } else {
                arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.FOLLOW, getContainsTracks(), false, false, null, null, 60, null));
            }
        }
        if (!isUserPlaylistShareEnabled(playlistUri) && PlaylistUtil.canDownloadPlaylist(playlistUri)) {
            arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.DOWNLOAD, getContainsTracks(), false, false, null, null, 60, null));
        }
        arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.SHARE, false, false, false, null, null, 62, null));
        arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.OVERFLOW, false, false, false, null, null, 62, null));
        return arrayList;
    }

    private final DownloadStateModel getInitialGroupDownloadState() {
        return new DownloadStateModel(DownloadStateModel.DownloadState.NO_STATE, 0);
    }

    private final boolean areTracksAvailable(EntityEligibilitiesProvider parentEligibilitiesProvider) {
        if (!getContainsTracks()) {
            return false;
        }
        List<? extends MusicTrack> list = this.tracks;
        Intrinsics.checkNotNull(list);
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            ((MusicTrack) it.next()).setParentEligibilities(parentEligibilitiesProvider);
        }
        List<? extends MusicTrack> list2 = this.tracks;
        Intrinsics.checkNotNull(list2);
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            return false;
        }
        Iterator<T> it2 = list2.iterator();
        while (it2.hasNext()) {
            if (((MusicTrack) it2.next()).isAvailable()) {
                return true;
            }
        }
        return false;
    }

    private final boolean isUserPlaylistShareEnabled(Uri playlistUri) {
        return this.isNightwingOnly && AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled() && !MediaProvider.isSmartPlaylist(playlistUri);
    }

    private final List<PageHeaderActionIconModel> partiallyOwnedFreeTierPlaylistDetailHeaderIcons(Uri playlistUri) {
        boolean zShouldEnablePlayback = shouldEnablePlayback(playlistUri);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.PLAY, zShouldEnablePlayback, !this.areAllTracksExplicit, false, null, null, 56, null));
        arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.SHUFFLE, zShouldEnablePlayback, !this.areAllTracksExplicit, false, null, null, 56, null));
        arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.SHARE, false, false, false, null, null, 62, null));
        arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.OVERFLOW, false, false, false, null, null, 62, null));
        return CollectionsKt___CollectionsKt.toList(arrayList);
    }

    private final boolean shouldEnablePlayback(Uri playlistUri) {
        if (areTracksAvailable(new CirrusSourceLibraryItemFactory(this.context).getItem(playlistUri))) {
            return !this.isNightwingOnly || this.isAllOwned || this.isPartiallyOwned;
        }
        return false;
    }

    private final boolean areAllTracksExplicit() {
        if (AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled() && getContainsTracks()) {
            List<? extends MusicTrack> list = this.tracks;
            Intrinsics.checkNotNull(list);
            if ((list instanceof Collection) && list.isEmpty()) {
                return true;
            }
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (!((MusicTrack) it.next()).isExplicit()) {
                }
            }
            return true;
        }
        return false;
    }

    private final boolean shouldShowSFAButton(boolean isCatalog, Uri playlistUri) {
        if (this.isAllOwned || !StationUtils.isCustomerAllowedToPlaySFAStation() || AmazonApplication.getCapabilities().isEntityPlaylistSupported()) {
            return false;
        }
        if (!isCatalog) {
            boolean zHasAsinsInUdoPlaylist = PlaylistUtil.hasAsinsInUdoPlaylist(this.context, playlistUri);
            AbstractItem item = new CirrusSourceLibraryItemFactory(this.context).getItem(playlistUri);
            if (zHasAsinsInUdoPlaylist) {
                return (areTracksAvailable(item) || isUserPlaylistShareEnabled(playlistUri)) && this.isNightwingOnly && !this.isAllOwned;
            }
            return false;
        }
        return this.isNightwingOnly;
    }

    private final List<PageHeaderActionIconModel> onDemandGoldenPlaylistDetailHeaderIcons() {
        List<PageHeaderActionIconModel> listMutableListOf = CollectionsKt__CollectionsKt.mutableListOf(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.PLAY, false, false, false, null, null, 62, null), new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.SHUFFLE, false, false, false, null, null, 62, null));
        if (AmazonApplication.getCapabilities().canFreeTierUserFollowPlaylists()) {
            if (AmazonApplication.getCapabilities().playlistFollowButtonChangeEnabled() || AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
                listMutableListOf.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.FAVORITE, false, false, false, null, null, 62, null));
            } else {
                listMutableListOf.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.FOLLOW, false, false, false, null, null, 62, null));
            }
        }
        listMutableListOf.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.OVERFLOW, false, false, false, null, null, 62, null));
        return listMutableListOf;
    }

    private final boolean shouldOverrideTitleCaps() {
        return MusicTerritory.JAPAN.equalsIgnoreCase(AccountDetailUtil.getMusicTerritoryOfResidence());
    }

    private final String getHeroImageUrl(String artistId) {
        List<ContributorAccessObject.Contributor> contributors = ContributorManagerImpl.INSTANCE.getContributors(ItemWrapper$IdType.MERGED_ID.getUri(ItemWrapper$ItemType.ARTIST, artistId).toString());
        Intrinsics.checkNotNullExpressionValue(contributors, "service.getContributors(artistUri.toString())");
        ContributorAccessObject.Contributor contributor = contributors.isEmpty() ^ true ? contributors.get(0) : null;
        if (contributor == null) {
            return null;
        }
        return contributor.getHeroImageURL();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void refreshUserSubscriptionValues$default(LibraryDetailHeaderConverter libraryDetailHeaderConverter, boolean z, AbstractItem abstractItem, List list, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = UserSubscriptionUtil.isNightwingOnly();
        }
        if ((i & 2) != 0) {
            abstractItem = null;
        }
        if ((i & 4) != 0) {
            list = null;
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        libraryDetailHeaderConverter.refreshUserSubscriptionValues(z, abstractItem, list, z2);
    }

    public final void refreshUserSubscriptionValues(boolean isNightwingOnly, AbstractItem playlist, List<? extends MusicTrack> tracks, boolean isOnDemandGoldenPlaylist) {
        this.isNightwingOnly = isNightwingOnly;
        this.tracks = tracks;
        if (playlist == null) {
            return;
        }
        updatePlaylistDependentValues(playlist, isOnDemandGoldenPlaylist);
    }

    private final void updatePlaylistDependentValues(AbstractItem playlist, boolean isOnDemandGoldenPlaylist) {
        this.isAllOwned = playlist.isAllOwned();
        this.isPartiallyOwned = (playlist.isAllOwned() || playlist.isNotOwned()) ? false : true;
        this.isOnDemandGoldenPlaylist = isOnDemandGoldenPlaylist;
    }
}
