package com.amazon.mp3.brush.converters;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.eligibility.CachedCapabilityActionProviderImpl;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.mp3.util.EligibilityUtil;
import com.amazon.music.ContentAccessType;
import com.amazon.music.alps.config.URLConfig;
import com.amazon.music.find.converter.BrushV2Converter;
import com.amazon.music.find.converter.model.FindImage;
import com.amazon.music.find.converter.model.MusicSearchAlbum;
import com.amazon.music.find.converter.model.MusicSearchArtist;
import com.amazon.music.find.converter.model.MusicSearchCommunityPlaylist;
import com.amazon.music.find.converter.model.MusicSearchLiveEvent;
import com.amazon.music.find.converter.model.MusicSearchPlaylist;
import com.amazon.music.find.converter.model.MusicSearchPodcastEpisode;
import com.amazon.music.find.converter.model.MusicSearchPodcastShow;
import com.amazon.music.find.converter.model.MusicSearchQuery;
import com.amazon.music.find.converter.model.MusicSearchStation;
import com.amazon.music.find.converter.model.MusicSearchTrack;
import com.amazon.music.find.converter.model.MusicSearchUserPlaylist;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.CommunityPlaylistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.CustomerProfileMetadata;
import com.amazon.music.views.library.metadata.GenreMetadata;
import com.amazon.music.views.library.metadata.LiveEventMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.PodcastMetadata;
import com.amazon.music.views.library.metadata.SearchQueryMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.HorizontalTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.musicensembleservice.brush.Album;
import com.amazon.musicensembleservice.brush.Artist;
import com.amazon.musicensembleservice.brush.CommunityPlaylist;
import com.amazon.musicensembleservice.brush.CustomerProfile;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.FeaturedBarker;
import com.amazon.musicensembleservice.brush.Genre;
import com.amazon.musicensembleservice.brush.Image;
import com.amazon.musicensembleservice.brush.LiveEvent;
import com.amazon.musicensembleservice.brush.ParentalControls;
import com.amazon.musicensembleservice.brush.Playlist;
import com.amazon.musicensembleservice.brush.PodcastEpisode;
import com.amazon.musicensembleservice.brush.Station;
import com.amazon.musicensembleservice.brush.Track;
import com.amazon.musicensembleservice.brush.UserPlaylist;
import com.amazon.musicprofileservice.ProfileInfo;
import com.amazon.podcast.deeplinks.Deeplinks;
import com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemModel;
import com.amazon.podcast.views.horizontalRowItemsListView.PodcastEpisodeHorizontalRowItemModel;
import com.amazon.podcast.views.horizontalRowItemsListView.PodcastHorizontalRowItemModel;
import com.google.android.gms.cast.MediaTrack;
import com.visualon.OSMPUtils.voOSType;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: BrushHorizontalTileConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0016\u0018\u0000 J2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001JB\u0007¢\u0006\u0004\bH\u0010IJ\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0007H\u0002J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\tH\u0002J\u0012\u0010\f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u000bH\u0002J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\rH\u0002J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u000fH\u0002J2\u0010\u0017\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u00112\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00122\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0012H\u0002J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0018H\u0002J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u001aH\u0002J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u001cH\u0002J\u0012\u0010 \u001a\u0004\u0018\u00010\u001f2\u0006\u0010\u0004\u001a\u00020\u001eH\u0002J\u0012\u0010#\u001a\u0004\u0018\u00010\"2\u0006\u0010\u0004\u001a\u00020!H\u0002J\u0012\u0010&\u001a\u0004\u0018\u00010%2\u0006\u0010\u0004\u001a\u00020$H\u0002J\u0012\u0010(\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020'H\u0002J\u0012\u0010*\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020)H\u0002J\u0018\u0010-\u001a\u00020\u00152\u0006\u0010+\u001a\u00020\u00152\u0006\u0010,\u001a\u00020\u0015H\u0002JY\u00108\u001a\u0002072\b\u0010.\u001a\u0004\u0018\u00010\u00152\b\u0010/\u001a\u0004\u0018\u00010\u00152\b\u00101\u001a\u0004\u0018\u0001002\u0006\u00102\u001a\u00020\u00132\n\b\u0002\u00103\u001a\u0004\u0018\u0001002\n\b\u0002\u00104\u001a\u0004\u0018\u0001002\n\b\u0002\u00106\u001a\u0004\u0018\u000105H\u0002¢\u0006\u0004\b8\u00109J \u0010<\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00122\u000e\u0010;\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010:H\u0002J\u0012\u0010>\u001a\u0004\u0018\u00010\u00132\u0006\u0010=\u001a\u00020\u0011H\u0002J\u0010\u0010A\u001a\u0004\u0018\u00010@2\u0006\u0010?\u001a\u00020\u0002J\"\u0010A\u001a\u0004\u0018\u00010@2\u0006\u0010?\u001a\u00020\u00022\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012H\u0016J2\u0010A\u001a\u0004\u0018\u00010@2\u0006\u0010?\u001a\u00020\u00022\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00122\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0012H\u0016J\b\u0010C\u001a\u00020BH\u0016J\u0010\u0010E\u001a\u00020B2\u0006\u0010D\u001a\u00020BH\u0016J\u0019\u0010F\u001a\u0004\u0018\u0001002\u0006\u00102\u001a\u00020\u0013H\u0016¢\u0006\u0004\bF\u0010G¨\u0006K"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushHorizontalTileConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/musicensembleservice/brush/Entity;", "Lcom/amazon/musicensembleservice/brush/Album;", "item", "Lcom/amazon/music/views/library/models/HorizontalTileModel;", "convertAlbum", "Lcom/amazon/musicensembleservice/brush/LiveEvent;", "convertLiveEvent", "Lcom/amazon/musicensembleservice/brush/Playlist;", "convertPlaylist", "Lcom/amazon/musicensembleservice/brush/CommunityPlaylist;", "convertCommunityPlaylist", "Lcom/amazon/musicensembleservice/brush/UserPlaylist;", "convertUserPlaylist", "Lcom/amazon/musicensembleservice/brush/Station;", "convertStation", "Lcom/amazon/musicensembleservice/brush/Track;", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadataList", "", "tags", "convertTrack", "Lcom/amazon/musicensembleservice/brush/Artist;", "convertArtist", "Lcom/amazon/musicensembleservice/brush/CustomerProfile;", "convertProfile", "Lcom/amazon/musicensembleservice/brush/Genre;", "convertGenre", "Lcom/amazon/musicensembleservice/brush/PodcastEpisode;", "Lcom/amazon/podcast/views/episodeRowItemsListView/EpisodeRowItemModel;", "convertPodcastEpisode", "Lcom/amazon/music/find/converter/model/MusicSearchPodcastEpisode;", "Lcom/amazon/podcast/views/horizontalRowItemsListView/PodcastEpisodeHorizontalRowItemModel;", "convertSearchPodcastEpisode", "Lcom/amazon/music/find/converter/model/MusicSearchPodcastShow;", "Lcom/amazon/podcast/views/horizontalRowItemsListView/PodcastHorizontalRowItemModel;", "convertSearchPodcastShow", "Lcom/amazon/music/find/converter/model/MusicSearchQuery;", "convertSearchQuery", "Lcom/amazon/musicensembleservice/brush/FeaturedBarker;", "convertFeaturedBarker", "target", MediaTrack.ROLE_SUBTITLE, "normalizeSubtitle", "blockRef", "imageUrl", "", "contentType", "contentMetadata", "resourceId", "imageSize", "Landroid/graphics/drawable/Drawable;", "imageDrawable", "Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "createArtworkFrameModel", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Lcom/amazon/music/views/library/metadata/ContentMetadata;Ljava/lang/Integer;Ljava/lang/Integer;Landroid/graphics/drawable/Drawable;)Lcom/amazon/music/views/library/models/ArtworkFrameModel;", "", "contentEncodings", "removeImmersiveEncoding", "track", "getContainerMetadata", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "", "shouldDisplayPlayIcon", "showOverFlow", "shouldShowOverFlow", "getPlayIconType", "(Lcom/amazon/music/views/library/metadata/ContentMetadata;)Ljava/lang/Integer;", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class BrushHorizontalTileConverter implements SingleBaseModelConverter<Entity> {
    private static final Logger logger = LoggerFactory.getLogger(BrushHorizontalTileConverter.class.getName());

    public boolean shouldDisplayPlayIcon() {
        return false;
    }

    public boolean shouldShowOverFlow(boolean showOverFlow) {
        return true;
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public final BaseViewModel convert(Entity data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return convert(data, null);
    }

    public BaseViewModel convert(Entity data, List<? extends ContentMetadata> metadataList) {
        Intrinsics.checkNotNullParameter(data, "data");
        return convert(data, metadataList, null);
    }

    public BaseViewModel convert(Entity data, List<? extends ContentMetadata> metadataList, List<String> tags) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (data instanceof Album) {
            return convertAlbum((Album) data);
        }
        if (data instanceof Playlist) {
            return convertPlaylist((Playlist) data);
        }
        if (data instanceof UserPlaylist) {
            return convertUserPlaylist((UserPlaylist) data);
        }
        if (data instanceof CommunityPlaylist) {
            return convertCommunityPlaylist((CommunityPlaylist) data);
        }
        if (data instanceof Station) {
            return convertStation((Station) data);
        }
        if (data instanceof Track) {
            return convertTrack((Track) data, metadataList, tags);
        }
        if (data instanceof Genre) {
            return convertGenre((Genre) data);
        }
        if (data instanceof Artist) {
            return convertArtist((Artist) data);
        }
        if (data instanceof CustomerProfile) {
            return convertProfile((CustomerProfile) data);
        }
        if (data instanceof MusicSearchQuery) {
            return convertSearchQuery((MusicSearchQuery) data);
        }
        if (data instanceof FeaturedBarker) {
            return convertFeaturedBarker((FeaturedBarker) data);
        }
        if (data instanceof MusicSearchPodcastEpisode) {
            return convertSearchPodcastEpisode((MusicSearchPodcastEpisode) data);
        }
        if (data instanceof MusicSearchPodcastShow) {
            return convertSearchPodcastShow((MusicSearchPodcastShow) data);
        }
        if (data instanceof PodcastEpisode) {
            return convertPodcastEpisode((PodcastEpisode) data);
        }
        if (data instanceof LiveEvent) {
            return convertLiveEvent((LiveEvent) data);
        }
        return null;
    }

    private final HorizontalTileModel convertAlbum(Album item) {
        String asin = item.getAsin();
        if (asin == null) {
            return null;
        }
        Image image = item.getImage();
        String url = image == null ? null : image.getUrl();
        Drawable imageDrawable = (url == null && (image instanceof FindImage)) ? ((FindImage) image).getImageDrawable() : null;
        List<Artist> artists = item.getArtists();
        Artist artist = artists == null ? null : (Artist) CollectionsKt___CollectionsKt.first((List) artists);
        List<String> listRemoveImmersiveEncoding = removeImmersiveEncoding(item.getContentEncoding());
        String blockRef = item.getBlockRef();
        String title = item.getTitle();
        String asin2 = artist == null ? null : artist.getAsin();
        String name = artist == null ? null : artist.getName();
        boolean z = item instanceof MusicSearchAlbum;
        Boolean boolIsIsOwned = z ? ((MusicSearchAlbum) item).isIsOwned() : Boolean.FALSE;
        Boolean boolIsIsInLibrary = item.isIsInLibrary();
        boolean zBooleanValue = boolIsIsInLibrary == null ? false : boolIsIsInLibrary.booleanValue();
        BrushConverterUtils brushConverterUtils = BrushConverterUtils.INSTANCE;
        List<String> contentTiers = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "item.contentTiers");
        Set<ContentAccessType> setConvertTiers = brushConverterUtils.convertTiers(contentTiers);
        List<String> contentTiers2 = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers2, "item.contentTiers");
        Set<SubscriptionType> setConvertOnDemandTiers = brushConverterUtils.convertOnDemandTiers(contentTiers2);
        Uri albumUri = ContentPlaybackUtils.INSTANCE.getAlbumUri(artist == null ? null : artist.getName(), item.getTitle(), asin);
        ParentalControls parentalControls = item.getParentalControls();
        boolean zAreEqual = parentalControls == null ? false : Intrinsics.areEqual(parentalControls.isHasExplicitLanguage(), Boolean.TRUE);
        PlaymodeEligibility playmodeEligibilityConvertPlaymodeEligibility = BrushConverterUtils.convertPlaymodeEligibility(item.getPlaymodeEligibility());
        MusicSearchAlbum musicSearchAlbum = z ? (MusicSearchAlbum) item : null;
        AlbumMetadata albumMetadata = new AlbumMetadata(blockRef, albumUri, null, asin, title, url, null, asin2, name, Boolean.valueOf(zBooleanValue), boolIsIsOwned, setConvertTiers, zAreEqual, null, null, null, playmodeEligibilityConvertPlaymodeEligibility, setConvertOnDemandTiers, null, musicSearchAlbum == null ? null : musicSearchAlbum.getLanguageOfPerformances(), 319552, null);
        String blockRef2 = item.getBlockRef();
        String primaryTitle = item.getPrimaryTitle();
        String secondaryTitle = item.getSecondaryTitle();
        String tertiaryTitle = item.getTertiaryTitle();
        ArtworkFrameModel artworkFrameModelCreateArtworkFrameModel$default = createArtworkFrameModel$default(this, item.getBlockRef(), url, 0, albumMetadata, null, null, imageDrawable, 48, null);
        MusicSearchAlbum musicSearchAlbum2 = z ? (MusicSearchAlbum) item : null;
        boolean enableOrdinal = musicSearchAlbum2 == null ? false : musicSearchAlbum2.getEnableOrdinal();
        MusicSearchAlbum musicSearchAlbum3 = z ? (MusicSearchAlbum) item : null;
        return new HorizontalTileModel(blockRef2, null, primaryTitle, secondaryTitle, tertiaryTitle, artworkFrameModelCreateArtworkFrameModel$default, null, musicSearchAlbum3 == null ? false : musicSearchAlbum3.getShowOrdinal(), null, null, false, false, listRemoveImmersiveEncoding, false, true, null, 0, albumMetadata, null, false, enableOrdinal, null, false, null, null, null, false, false, false, null, 1072475970, null);
    }

    private final HorizontalTileModel convertLiveEvent(LiveEvent item) {
        String id = item.getId();
        if (id == null) {
            return null;
        }
        Uri uri = Uri.parse(URLConfig.SHARE_BASE_URL.getValue() + "live/events/" + ((Object) item.getId()));
        String url = item.getImage().getUrl();
        String blockRef = item.getBlockRef();
        Intrinsics.checkNotNullExpressionValue(uri, "uri");
        LiveEventMetadata liveEventMetadata = new LiveEventMetadata(blockRef, uri, null, id, 4, null);
        String blockRef2 = item.getBlockRef();
        String title = item.getTitle();
        MusicSearchLiveEvent musicSearchLiveEvent = (MusicSearchLiveEvent) item;
        String subtitle = musicSearchLiveEvent.getSubtitle();
        String tertiaryTitle = musicSearchLiveEvent.getTertiaryTitle();
        ArtworkFrameModel artworkFrameModelCreateArtworkFrameModel$default = createArtworkFrameModel$default(this, musicSearchLiveEvent.getBlockRef(), url, 15, liveEventMetadata, null, null, null, 112, null);
        boolean z = item instanceof MusicSearchLiveEvent;
        MusicSearchLiveEvent musicSearchLiveEvent2 = z ? musicSearchLiveEvent : null;
        boolean enableOrdinal = musicSearchLiveEvent2 == null ? false : musicSearchLiveEvent2.getEnableOrdinal();
        MusicSearchLiveEvent musicSearchLiveEvent3 = z ? musicSearchLiveEvent : null;
        return new HorizontalTileModel(blockRef2, null, title, subtitle, tertiaryTitle, artworkFrameModelCreateArtworkFrameModel$default, null, musicSearchLiveEvent3 == null ? false : musicSearchLiveEvent3.getShowOrdinal(), null, null, false, false, null, false, false, null, 15, liveEventMetadata, null, false, enableOrdinal, null, false, null, null, null, false, false, false, null, 1072496450, null);
    }

    private final HorizontalTileModel convertPlaylist(Playlist item) {
        String asin = item.getAsin();
        if (asin == null) {
            return null;
        }
        Image image = item.getImage();
        String url = image == null ? null : image.getUrl();
        Drawable imageDrawable = (url == null && (image instanceof FindImage)) ? ((FindImage) image).getImageDrawable() : null;
        String blockRef = item.getBlockRef();
        String title = item.getTitle();
        Boolean boolIsIsInLibrary = item.isIsInLibrary();
        boolean zBooleanValue = boolIsIsInLibrary == null ? false : boolIsIsInLibrary.booleanValue();
        Boolean boolIsIsOwned = item.isIsOwned();
        boolean zBooleanValue2 = boolIsIsOwned == null ? false : boolIsIsOwned.booleanValue();
        BrushConverterUtils brushConverterUtils = BrushConverterUtils.INSTANCE;
        List<String> contentTiers = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "item.contentTiers");
        Set<ContentAccessType> setConvertTiers = brushConverterUtils.convertTiers(contentTiers);
        List<String> contentTiers2 = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers2, "item.contentTiers");
        Set<SubscriptionType> setConvertOnDemandTiers = brushConverterUtils.convertOnDemandTiers(contentTiers2);
        PlaymodeEligibility playmodeEligibilityConvertPlaymodeEligibility = BrushConverterUtils.convertPlaymodeEligibility(item.getPlaymodeEligibility());
        Uri playlistUri = ContentPlaybackUtils.INSTANCE.getPlaylistUri(asin);
        List<String> contentTypes = item.getContentTypes();
        if (contentTypes == null) {
            contentTypes = CollectionsKt__CollectionsJVMKt.listOf("AUDIO");
        }
        List<String> list = contentTypes;
        ParentalControls parentalControls = item.getParentalControls();
        boolean zAreEqual = parentalControls == null ? false : Intrinsics.areEqual(parentalControls.isHasExplicitLanguage(), Boolean.TRUE);
        boolean z = item instanceof MusicSearchPlaylist;
        MusicSearchPlaylist musicSearchPlaylist = z ? (MusicSearchPlaylist) item : null;
        PlaylistMetadata playlistMetadata = new PlaylistMetadata(blockRef, playlistUri, null, asin, title, url, null, null, null, null, Boolean.valueOf(zBooleanValue), setConvertTiers, list, zAreEqual, null, null, null, null, zBooleanValue2, null, false, playmodeEligibilityConvertPlaymodeEligibility, setConvertOnDemandTiers, false, musicSearchPlaylist == null ? null : musicSearchPlaylist.getLanguageOfPerformances(), 10208196, null);
        List<String> contentTypes2 = item.getContentTypes();
        boolean zAreEqual2 = Intrinsics.areEqual(contentTypes2 == null ? null : (String) CollectionsKt___CollectionsKt.first((List) contentTypes2), "VIDEO");
        int i = zAreEqual2 ? 8 : 1;
        String blockRef2 = item.getBlockRef();
        String primaryTitle = item.getPrimaryTitle();
        String secondaryTitle = item.getSecondaryTitle();
        String tertiaryTitle = item.getTertiaryTitle();
        ArtworkFrameModel artworkFrameModelCreateArtworkFrameModel$default = createArtworkFrameModel$default(this, item.getBlockRef(), url, Integer.valueOf(i), playlistMetadata, null, null, imageDrawable, 48, null);
        MusicSearchPlaylist musicSearchPlaylist2 = z ? (MusicSearchPlaylist) item : null;
        boolean enableOrdinal = musicSearchPlaylist2 == null ? false : musicSearchPlaylist2.getEnableOrdinal();
        MusicSearchPlaylist musicSearchPlaylist3 = z ? (MusicSearchPlaylist) item : null;
        return new HorizontalTileModel(blockRef2, null, primaryTitle, secondaryTitle, tertiaryTitle, artworkFrameModelCreateArtworkFrameModel$default, null, musicSearchPlaylist3 != null ? musicSearchPlaylist3.getShowOrdinal() : false, null, null, false, false, null, false, false, null, i, playlistMetadata, null, zAreEqual2, enableOrdinal, null, false, null, null, null, false, false, false, null, 1071972162, null);
    }

    private final HorizontalTileModel convertCommunityPlaylist(CommunityPlaylist item) {
        Object next;
        Image image;
        String playlistId = item.getPlaylistId();
        if (playlistId == null) {
            return null;
        }
        List<Image> images = item.getImages();
        if (images == null) {
            image = null;
        } else {
            Iterator<T> it = images.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if ("ORIGINAL_ART".equals(((Image) next).getType())) {
                    break;
                }
            }
            image = (Image) next;
        }
        String url = image == null ? "" : image.getUrl();
        String blockRef = item.getBlockRef();
        String title = item.getTitle();
        String description = item.getDescription();
        Long tracksCount = item.getTracksCount();
        Boolean isIsOwned = item.isIsOwned();
        String curator = item.getCurator();
        Uri playlistUri = ContentPlaybackUtils.INSTANCE.getPlaylistUri(playlistId);
        BrushConverterUtils brushConverterUtils = BrushConverterUtils.INSTANCE;
        boolean z = item instanceof MusicSearchCommunityPlaylist;
        MusicSearchCommunityPlaylist musicSearchCommunityPlaylist = z ? (MusicSearchCommunityPlaylist) item : null;
        List<String> contentTiers = musicSearchCommunityPlaylist == null ? null : musicSearchCommunityPlaylist.getContentTiers();
        if (contentTiers == null) {
            contentTiers = CollectionsKt__CollectionsKt.emptyList();
        }
        Set<ContentAccessType> setConvertTiers = brushConverterUtils.convertTiers(contentTiers);
        MusicSearchCommunityPlaylist musicSearchCommunityPlaylist2 = z ? (MusicSearchCommunityPlaylist) item : null;
        List<String> contentTiers2 = musicSearchCommunityPlaylist2 == null ? null : musicSearchCommunityPlaylist2.getContentTiers();
        if (contentTiers2 == null) {
            contentTiers2 = CollectionsKt__CollectionsKt.emptyList();
        }
        Set<SubscriptionType> setConvertOnDemandTiers = brushConverterUtils.convertOnDemandTiers(contentTiers2);
        PlaymodeEligibility playmodeEligibilityConvertPlaymodeEligibility = BrushConverterUtils.convertPlaymodeEligibility(item.getPlaymodeEligibility());
        Intrinsics.checkNotNullExpressionValue(isIsOwned, "isIsOwned");
        CommunityPlaylistMetadata communityPlaylistMetadata = new CommunityPlaylistMetadata(blockRef, playlistUri, null, title, url, description, tracksCount, null, isIsOwned.booleanValue(), playlistId, playmodeEligibilityConvertPlaymodeEligibility, setConvertTiers, setConvertOnDemandTiers, false, curator, 8324, null);
        String blockRef2 = item.getBlockRef();
        String primaryTitle = item.getPrimaryTitle();
        String secondaryTitle = item.getSecondaryTitle();
        String tertiaryTitle = item.getTertiaryTitle();
        ArtworkFrameModel artworkFrameModelCreateArtworkFrameModel$default = createArtworkFrameModel$default(this, item.getBlockRef(), url, 1, communityPlaylistMetadata, null, null, null, 112, null);
        MusicSearchCommunityPlaylist musicSearchCommunityPlaylist3 = z ? (MusicSearchCommunityPlaylist) item : null;
        boolean enableOrdinal = musicSearchCommunityPlaylist3 == null ? false : musicSearchCommunityPlaylist3.getEnableOrdinal();
        MusicSearchCommunityPlaylist musicSearchCommunityPlaylist4 = z ? (MusicSearchCommunityPlaylist) item : null;
        return new HorizontalTileModel(blockRef2, null, primaryTitle, secondaryTitle, tertiaryTitle, artworkFrameModelCreateArtworkFrameModel$default, null, musicSearchCommunityPlaylist4 == null ? false : musicSearchCommunityPlaylist4.getShowOrdinal(), null, null, false, false, null, false, false, null, 1, communityPlaylistMetadata, null, false, enableOrdinal, null, false, null, null, null, false, false, false, null, 1072496450, null);
    }

    private final HorizontalTileModel convertUserPlaylist(UserPlaylist item) {
        Object next;
        Image image;
        String playlistId = item.getPlaylistId();
        if (playlistId == null) {
            return null;
        }
        List<Image> images = item.getImages();
        if (images == null) {
            image = null;
        } else {
            Iterator<T> it = images.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if ("ORIGINAL_ART".equals(((Image) next).getType())) {
                    break;
                }
            }
            image = (Image) next;
        }
        String url = image == null ? "" : image.getUrl();
        String blockRef = item.getBlockRef();
        String title = item.getTitle();
        String description = item.getDescription();
        Long tracksCount = item.getTracksCount();
        BrushConverterUtils brushConverterUtils = BrushConverterUtils.INSTANCE;
        boolean z = item instanceof MusicSearchUserPlaylist;
        MusicSearchUserPlaylist musicSearchUserPlaylist = z ? (MusicSearchUserPlaylist) item : null;
        List<String> contentTiers = musicSearchUserPlaylist == null ? null : musicSearchUserPlaylist.getContentTiers();
        if (contentTiers == null) {
            contentTiers = CollectionsKt__CollectionsKt.emptyList();
        }
        Set<SubscriptionType> setConvertContentTiers = brushConverterUtils.convertContentTiers(contentTiers);
        Boolean isIsOwned = item.isIsOwned();
        Uri userPlaylistUri = ContentPlaybackUtils.INSTANCE.getUserPlaylistUri();
        PlaymodeEligibility playmodeEligibilityConvertPlaymodeEligibility = BrushConverterUtils.convertPlaymodeEligibility(item.getPlaymodeEligibility());
        Intrinsics.checkNotNullExpressionValue(isIsOwned, "isIsOwned");
        UserPlaylistMetadata userPlaylistMetadata = new UserPlaylistMetadata(blockRef, userPlaylistUri, null, title, url, description, tracksCount, null, isIsOwned.booleanValue(), playlistId, playmodeEligibilityConvertPlaymodeEligibility, setConvertContentTiers, null, false, 12420, null);
        String blockRef2 = item.getBlockRef();
        String primaryTitle = item.getPrimaryTitle();
        String secondaryTitle = item.getSecondaryTitle();
        String tertiaryTitle = item.getTertiaryTitle();
        ArtworkFrameModel artworkFrameModelCreateArtworkFrameModel$default = createArtworkFrameModel$default(this, item.getBlockRef(), url, 1, userPlaylistMetadata, null, null, null, 112, null);
        MusicSearchUserPlaylist musicSearchUserPlaylist2 = z ? (MusicSearchUserPlaylist) item : null;
        boolean enableOrdinal = musicSearchUserPlaylist2 == null ? false : musicSearchUserPlaylist2.getEnableOrdinal();
        MusicSearchUserPlaylist musicSearchUserPlaylist3 = z ? (MusicSearchUserPlaylist) item : null;
        return new HorizontalTileModel(blockRef2, null, primaryTitle, secondaryTitle, tertiaryTitle, artworkFrameModelCreateArtworkFrameModel$default, null, musicSearchUserPlaylist3 == null ? false : musicSearchUserPlaylist3.getShowOrdinal(), null, null, false, false, null, false, false, null, 1, userPlaylistMetadata, null, false, enableOrdinal, null, false, null, null, null, false, false, false, null, 1072496450, null);
    }

    private final HorizontalTileModel convertStation(Station item) {
        String stationKey = item.getStationKey();
        if (stationKey == null) {
            return null;
        }
        Image image = item.getImage();
        String url = image == null ? null : image.getUrl();
        String blockRef = item.getBlockRef();
        String title = item.getTitle();
        BrushConverterUtils brushConverterUtils = BrushConverterUtils.INSTANCE;
        List<String> contentTiers = item.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "item.contentTiers");
        Set<ContentAccessType> setConvertTiers = brushConverterUtils.convertTiers(contentTiers);
        Map<String, ParentalControls> parentalControlsPerContentTier = item.getParentalControlsPerContentTier();
        Intrinsics.checkNotNullExpressionValue(parentalControlsPerContentTier, "item.parentalControlsPerContentTier");
        StationMetadata stationMetadata = new StationMetadata(blockRef, ContentPlaybackUtils.INSTANCE.getStationUri(stationKey), null, stationKey, title, null, null, null, url, brushConverterUtils.convertExplicitMap(parentalControlsPerContentTier), setConvertTiers, null, null, null, 14564, null);
        String blockRef2 = item.getBlockRef();
        String primaryTitle = item.getPrimaryTitle();
        String secondaryTitle = item.getSecondaryTitle();
        String tertiaryTitle = item.getTertiaryTitle();
        ArtworkFrameModel artworkFrameModelCreateArtworkFrameModel$default = createArtworkFrameModel$default(this, item.getBlockRef(), url, 3, stationMetadata, null, null, null, 112, null);
        boolean z = item instanceof MusicSearchStation;
        MusicSearchStation musicSearchStation = z ? (MusicSearchStation) item : null;
        boolean enableOrdinal = musicSearchStation == null ? false : musicSearchStation.getEnableOrdinal();
        MusicSearchStation musicSearchStation2 = z ? (MusicSearchStation) item : null;
        return new HorizontalTileModel(blockRef2, null, primaryTitle, secondaryTitle, tertiaryTitle, artworkFrameModelCreateArtworkFrameModel$default, null, musicSearchStation2 == null ? false : musicSearchStation2.getShowOrdinal(), null, null, false, false, null, false, false, null, 3, stationMetadata, null, false, enableOrdinal, null, false, null, null, null, false, false, false, null, 1072496450, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:73:0x0105  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.amazon.music.views.library.models.HorizontalTileModel convertTrack(com.amazon.musicensembleservice.brush.Track r56, java.util.List<? extends com.amazon.music.views.library.metadata.ContentMetadata> r57, java.util.List<java.lang.String> r58) {
        /*
            Method dump skipped, instruction units count: 619
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.brush.converters.BrushHorizontalTileConverter.convertTrack(com.amazon.musicensembleservice.brush.Track, java.util.List, java.util.List):com.amazon.music.views.library.models.HorizontalTileModel");
    }

    private final HorizontalTileModel convertArtist(Artist item) {
        String asin = item.getAsin();
        if (asin == null) {
            return null;
        }
        Image image = item.getImage();
        String url = image == null ? null : image.getUrl();
        Drawable imageDrawable = (url == null && (image instanceof FindImage)) ? ((FindImage) image).getImageDrawable() : null;
        boolean z = item instanceof MusicSearchArtist;
        String str = url;
        ArtistMetadata artistMetadata = new ArtistMetadata(item.getBlockRef(), ContentPlaybackUtils.INSTANCE.getArtistUri(), null, null, asin, item.getName(), null, str, item.getContributorAsin(), z ? ((MusicSearchArtist) item).getSource() : null, z ? BrushConverterUtils.INSTANCE.convertTiers(((MusicSearchArtist) item).getContentTiers()) : null, false, null, null, false, null, null, false, BrushConverterUtils.INSTANCE.isContentAvailable(item), null, 784460, null);
        String blockRef = item.getBlockRef();
        String primaryTitle = item.getPrimaryTitle();
        String secondaryTitle = item.getSecondaryTitle();
        String tertiaryTitle = item.getTertiaryTitle();
        ArtworkFrameModel artworkFrameModelCreateArtworkFrameModel$default = createArtworkFrameModel$default(this, item.getBlockRef(), str, 4, artistMetadata, null, null, imageDrawable, 48, null);
        MusicSearchArtist musicSearchArtist = z ? (MusicSearchArtist) item : null;
        boolean enableOrdinal = musicSearchArtist == null ? false : musicSearchArtist.getEnableOrdinal();
        MusicSearchArtist musicSearchArtist2 = z ? (MusicSearchArtist) item : null;
        return new HorizontalTileModel(blockRef, null, primaryTitle, secondaryTitle, tertiaryTitle, artworkFrameModelCreateArtworkFrameModel$default, null, musicSearchArtist2 == null ? false : musicSearchArtist2.getShowOrdinal(), null, null, false, false, null, false, false, null, 4, artistMetadata, null, false, enableOrdinal, null, false, null, null, null, false, false, true, null, 804060994, null);
    }

    private final HorizontalTileModel convertProfile(CustomerProfile item) {
        ProfileInfo profileDetails = item.getProfileDetails();
        if (profileDetails == null) {
            return null;
        }
        String avatarLink = profileDetails.getAvatarLink();
        String profileId = profileDetails.getProfileId();
        Intrinsics.checkNotNullExpressionValue(profileId, "profileDetails.profileId");
        String publicName = profileDetails.getPublicName();
        Intrinsics.checkNotNullExpressionValue(publicName, "profileDetails.publicName");
        String profileStatus = profileDetails.getProfileStatus();
        Intrinsics.checkNotNullExpressionValue(profileStatus, "profileDetails.profileStatus");
        CustomerProfileMetadata customerProfileMetadata = new CustomerProfileMetadata(null, null, profileId, publicName, profileStatus, 3, null);
        return new HorizontalTileModel(null, null, profileDetails.getPublicName(), null, null, createArtworkFrameModel$default(this, null, avatarLink, 4, customerProfileMetadata, null, null, null, 112, null), null, false, null, null, false, false, null, false, false, null, 4, customerProfileMetadata, null, false, false, null, false, null, null, null, false, false, true, null, 805109698, null);
    }

    private final HorizontalTileModel convertGenre(Genre item) {
        if (item.getBrowseId() == null) {
            return null;
        }
        Image image = item.getImage();
        String url = image != null ? image.getUrl() : null;
        String blockRef = item.getBlockRef();
        String browseId = item.getBrowseId();
        String title = item.getTitle();
        Uri genreUri = ContentPlaybackUtils.INSTANCE.getGenreUri();
        Intrinsics.checkNotNullExpressionValue(browseId, "browseId");
        GenreMetadata genreMetadata = new GenreMetadata(blockRef, genreUri, null, null, browseId, title, url, null, voOSType.VOOSMP_PID_ANALYTICS_EXPORT_GET_PACKET, null);
        return new HorizontalTileModel(item.getBlockRef(), null, item.getPrimaryTitle(), item.getSecondaryTitle(), item.getTertiaryTitle(), createArtworkFrameModel$default(this, item.getBlockRef(), url, 5, genreMetadata, null, null, null, 112, null), null, false, null, null, false, false, null, false, false, null, 5, genreMetadata, null, false, false, null, false, null, null, null, false, false, false, null, 1073545154, null);
    }

    private final EpisodeRowItemModel convertPodcastEpisode(PodcastEpisode item) {
        String str;
        int iLongValue;
        EpisodeRowItemModel episodeRowItemModel = null;
        if (item.getCatalogId() != null) {
            String catalogId = item.getCatalogId();
            String podcastShowCatalogId = item.getPodcastShowCatalogId();
            Image podcastShowImage = item.getPodcastShowImage();
            String url = podcastShowImage == null ? null : podcastShowImage.getUrl();
            String blockRef = item.getBlockRef();
            String title = item.getTitle();
            String subtitle = item.getSubtitle();
            Long durationSeconds = item.getDurationSeconds();
            String publishDate = item.getPublishDate();
            List<String> contentTraits = item.getContentTraits();
            List<String> availableUpsells = item.getAvailableUpsells();
            String playbackMode = item.getPlaybackMode();
            String podcastEpisodeVariantId = item.getPodcastEpisodeVariantId();
            String podcastShowVariantId = item.getPodcastShowVariantId();
            String availabilityDate = item.getAvailabilityDate();
            String blockRef2 = item.getBlockRef();
            String catalogId2 = item.getCatalogId();
            String podcastShowCatalogId2 = item.getPodcastShowCatalogId();
            Image podcastShowImage2 = item.getPodcastShowImage();
            String url2 = podcastShowImage2 != null ? podcastShowImage2.getUrl() : null;
            String title2 = item.getTitle();
            String subtitle2 = item.getSubtitle();
            List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
            Uri EMPTY = Uri.EMPTY;
            Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
            Intrinsics.checkNotNullExpressionValue(catalogId2, "catalogId");
            PodcastMetadata podcastMetadata = new PodcastMetadata(blockRef2, EMPTY, listEmptyList, catalogId2, "", null, title2, subtitle2, url2, podcastShowCatalogId2, null, 1024, null);
            if (durationSeconds == null) {
                iLongValue = 0;
                str = catalogId;
            } else {
                str = catalogId;
                iLongValue = (int) durationSeconds.longValue();
            }
            episodeRowItemModel = new EpisodeRowItemModel(blockRef, podcastMetadata, 11, str, podcastShowCatalogId, title, subtitle, publishDate, iLongValue, url, contentTraits, availableUpsells, playbackMode, podcastEpisodeVariantId, podcastShowVariantId, availabilityDate);
        }
        return episodeRowItemModel;
    }

    private final PodcastEpisodeHorizontalRowItemModel convertSearchPodcastEpisode(MusicSearchPodcastEpisode item) {
        if (item.getCatalogId() == null) {
            return null;
        }
        String catalogId = item.getCatalogId();
        String podcastShowCatalogId = item.getPodcastShowCatalogId();
        Image image = item.getImage();
        String url = image != null ? image.getUrl() : null;
        String blockRef = item.getBlockRef();
        String title = item.getTitle();
        String subtitle = item.getSubtitle();
        String publishDate = item.getPublishDate();
        String podcastEpisodeVariantId = item.getPodcastEpisodeVariantId();
        String podcastShowVariantId = item.getPodcastShowVariantId();
        List<String> contentTiers = item.getContentTiers();
        Map<String, List<String>> podcastEpisodeTierBenefits = item.getPodcastEpisodeTierBenefits();
        Map<String, List<String>> podcastShowTierBenefits = item.getPodcastShowTierBenefits();
        Map<String, Date> availabilityDisplayDate = item.getAvailabilityDisplayDate();
        String contentUrl = Deeplinks.episodeForPlaybackFromSearchResults(podcastShowCatalogId, catalogId, subtitle, title, contentTiers);
        String blockRef2 = item.getBlockRef();
        String catalogId2 = item.getCatalogId();
        String podcastShowCatalogId2 = item.getPodcastShowCatalogId();
        String title2 = item.getTitle();
        String subtitle2 = item.getSubtitle();
        List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        Uri EMPTY = Uri.EMPTY;
        Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
        Intrinsics.checkNotNullExpressionValue(catalogId2, "catalogId");
        Intrinsics.checkNotNullExpressionValue(contentUrl, "contentUrl");
        return new PodcastEpisodeHorizontalRowItemModel(blockRef, new PodcastMetadata(blockRef2, EMPTY, listEmptyList, catalogId2, contentUrl, null, title2, subtitle2, url, podcastShowCatalogId2, podcastEpisodeVariantId), 11, catalogId, podcastShowCatalogId, title, subtitle, url, publishDate, podcastEpisodeVariantId, podcastShowVariantId, contentTiers, podcastEpisodeTierBenefits, podcastShowTierBenefits, availabilityDisplayDate, item.getEnableOrdinal(), item.getShowOrdinal());
    }

    private final PodcastHorizontalRowItemModel convertSearchPodcastShow(MusicSearchPodcastShow item) {
        if (item.getCatalogId() == null) {
            return null;
        }
        String catalogId = item.getCatalogId();
        String title = item.getTitle();
        String subtitle = item.getSubtitle();
        Image image = item.getImage();
        String url = image != null ? image.getUrl() : null;
        String blockRef = item.getBlockRef();
        List<String> contentTraits = item.getContentTraits();
        List<String> availableUpsells = item.getAvailableUpsells();
        String playbackMode = item.getPlaybackMode();
        String podcastShowVariantId = item.getPodcastShowVariantId();
        List<String> contentTiers = item.getContentTiers();
        Map<String, List<String>> tierBenefits = item.getTierBenefits();
        String contentUrl = Deeplinks.podcastForPlaybackFromSearchResults(catalogId, title, contentTiers);
        String blockRef2 = item.getBlockRef();
        String catalogId2 = item.getCatalogId();
        String title2 = item.getTitle();
        String subtitle2 = item.getSubtitle();
        List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        Uri EMPTY = Uri.EMPTY;
        Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
        Intrinsics.checkNotNullExpressionValue(catalogId2, "catalogId");
        Intrinsics.checkNotNullExpressionValue(contentUrl, "contentUrl");
        return new PodcastHorizontalRowItemModel(blockRef, new PodcastMetadata(blockRef2, EMPTY, listEmptyList, catalogId2, contentUrl, null, title2, subtitle2, url, null, podcastShowVariantId, 512, null), 12, catalogId, title, subtitle, url, contentTraits, availableUpsells, playbackMode, podcastShowVariantId, contentTiers, tierBenefits, true, item.getEnableOrdinal(), item.getShowOrdinal());
    }

    private final HorizontalTileModel convertSearchQuery(MusicSearchQuery item) {
        String query = item.getQuery();
        SearchQueryMetadata searchQueryMetadata = new SearchQueryMetadata(item.getBlockRef(), ContentPlaybackUtils.INSTANCE.getDEFAULT_URI(), null, item.getQuery(), 4, null);
        return new HorizontalTileModel(item.getBlockRef(), null, query, "", "", createArtworkFrameModel$default(this, item.getBlockRef(), item.getImageUrl(), 10, searchQueryMetadata, item.getImageResource(), Integer.valueOf(item.getImageSize()), null, 64, null), null, false, null, null, false, false, null, false, false, null, 10, searchQueryMetadata, null, false, false, null, false, null, null, null, false, false, false, null, 1073545154, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0011  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.amazon.music.views.library.models.HorizontalTileModel convertFeaturedBarker(com.amazon.musicensembleservice.brush.FeaturedBarker r51) {
        /*
            Method dump skipped, instruction units count: 307
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.brush.converters.BrushHorizontalTileConverter.convertFeaturedBarker(com.amazon.musicensembleservice.brush.FeaturedBarker):com.amazon.music.views.library.models.HorizontalTileModel");
    }

    private final String normalizeSubtitle(String target, String subtitle) {
        boolean booleanQueryParameter = false;
        try {
            booleanQueryParameter = Uri.parse(target).getBooleanQueryParameter(BrushV2Converter.KEY_SUBTITLE_CONTAINS_ENTITY_TYPE, false);
        } catch (Exception unused) {
        }
        if (!booleanQueryParameter) {
            return subtitle;
        }
        List listSplit$default = StringsKt__StringsKt.split$default((CharSequence) subtitle, new String[]{"•"}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listSplit$default, 10));
        Iterator it = listSplit$default.iterator();
        while (it.hasNext()) {
            arrayList.add(StringsKt__StringsKt.trim((CharSequence) it.next()).toString());
        }
        return arrayList.size() == 2 ? (String) arrayList.get(1) : subtitle;
    }

    static /* synthetic */ ArtworkFrameModel createArtworkFrameModel$default(BrushHorizontalTileConverter brushHorizontalTileConverter, String str, String str2, Integer num, ContentMetadata contentMetadata, Integer num2, Integer num3, Drawable drawable, int i, Object obj) {
        if (obj == null) {
            return brushHorizontalTileConverter.createArtworkFrameModel(str, str2, num, contentMetadata, (i & 16) != 0 ? null : num2, (i & 32) != 0 ? null : num3, (i & 64) != 0 ? null : drawable);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createArtworkFrameModel");
    }

    private final ArtworkFrameModel createArtworkFrameModel(String blockRef, String imageUrl, Integer contentType, ContentMetadata contentMetadata, Integer resourceId, Integer imageSize, Drawable imageDrawable) {
        return new ArtworkFrameModel(blockRef, imageUrl, contentType, null, null, getPlayIconType(contentMetadata), null, resourceId, imageSize, imageDrawable, null, AmazonApplication.getCapabilities().eligibilityBasedPlaybackIcon() ? IconStyleKey.SONIC_RUSH_OVERLAY : null, 1112, null);
    }

    public Integer getPlayIconType(ContentMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl = null;
        if (BrushConverterUtils.INSTANCE.shouldHidePlayTypeIconForExperiment(contentMetadata)) {
            return null;
        }
        int i = 1;
        if (contentMetadata instanceof StationMetadata) {
            return 1;
        }
        if ((contentMetadata instanceof TrackMetadata) && Intrinsics.areEqual(((TrackMetadata) contentMetadata).getAssetType(), "AUDIO")) {
            return new EligibilityUtil(cachedCapabilityActionProviderImpl, i, 0 == true ? 1 : 0).getPlaybackIcon((EntityEligibilitiesProvider) contentMetadata);
        }
        return null;
    }

    private final List<String> removeImmersiveEncoding(List<String> contentEncodings) {
        if (AmazonApplication.getCapabilities().isSpatialAudioEnabled(AmazonApplication.getContext()) && contentEncodings != null) {
            if (contentEncodings.contains("immersive")) {
                contentEncodings.remove("immersive");
            }
        } else {
            boolean z = false;
            if ((contentEncodings != null && contentEncodings.contains("atmosAvailable")) && contentEncodings != null) {
                contentEncodings.remove("atmosAvailable");
            }
            if (contentEncodings != null && contentEncodings.contains("ra360Available")) {
                z = true;
            }
            if (z && contentEncodings != null) {
                contentEncodings.remove("ra360Available");
            }
        }
        return contentEncodings;
    }

    private final ContentMetadata getContainerMetadata(Track track) {
        if (track instanceof MusicSearchTrack) {
            return BrushConverterUtils.INSTANCE.getContainerMetadata((MusicSearchTrack) track);
        }
        return null;
    }
}
