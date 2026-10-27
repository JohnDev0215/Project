package com.amazon.mp3.brush;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.baseviews.model.configuration.AlbumModelConfiguration;
import com.amazon.mp3.baseviews.model.configuration.PlaylistModelConfiguration;
import com.amazon.mp3.brush.converters.BrushConverterUtils;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.music.ContentAccessType;
import com.amazon.music.freetier.featuregating.FMPMFeatureGating;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import com.amazon.musicensembleservice.brush.Image;
import com.amazon.musicensembleservice.brush.MusicEntity;
import com.amazon.musicensembleservice.brush.Playlist;
import com.amazon.musicensembleservice.brush.UserPlaylist;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AllAccessPlaylistsUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002J\u0014\u0010\u0007\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0006H\u0002J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00022\u0006\u0010\t\u001a\u00020\bH\u0002J&\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\f2\u0006\u0010\u000b\u001a\u00020\u00042\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\fJ\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u00042\u0006\u0010\t\u001a\u00020\bJ\u0016\u0010\u0014\u001a\u00020\u00102\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\fR\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001c¨\u0006 "}, d2 = {"Lcom/amazon/mp3/brush/AllAccessPlaylistsUtils;", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "", "getPlaylistOnDemandBadging", "Lcom/amazon/music/views/library/metadata/AlbumMetadata;", "getAlbumOnDemandBadging", "Lcom/amazon/musicensembleservice/brush/MusicEntity;", Environment.PLAYLIST_PATH, "getPlaylistMetadata", "title", "", "tags", "getAllAccessPlaylistsStringsToHighlight", "getOnDemandBadging", "", "getAllAccessCTAPlayIcon", "(Lcom/amazon/music/views/library/metadata/ContentMetadata;)Ljava/lang/Boolean;", "getAllAccessLabelRecents", "isAllAccessTracksWidget", "Lcom/amazon/mp3/baseviews/model/configuration/PlaylistModelConfiguration;", "playlistConfiguration", "Lcom/amazon/mp3/baseviews/model/configuration/PlaylistModelConfiguration;", "Lcom/amazon/mp3/baseviews/model/configuration/AlbumModelConfiguration;", "albumConfiguration", "Lcom/amazon/mp3/baseviews/model/configuration/AlbumModelConfiguration;", "allAccessLabel", "Ljava/lang/String;", "onDemandLabel", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AllAccessPlaylistsUtils {
    private static final String allAccessLabel;
    private static final String onDemandLabel;
    public static final AllAccessPlaylistsUtils INSTANCE = new AllAccessPlaylistsUtils();
    private static final PlaylistModelConfiguration playlistConfiguration = new PlaylistModelConfiguration(null, 1, 0 == true ? 1 : 0);
    private static final AlbumModelConfiguration albumConfiguration = new AlbumModelConfiguration(null, null, 3, null);

    private AllAccessPlaylistsUtils() {
    }

    static {
        String string = AmazonApplication.getApplication().getApplicationContext().getString(R.string.dmusic_sonic_rush_tier_onDemand_pageheader_label);
        Intrinsics.checkNotNullExpressionValue(string, "getApplication().applica…nDemand_pageheader_label)");
        allAccessLabel = string;
        String string2 = AmazonApplication.getApplication().getApplicationContext().getString(R.string.dmusic_on_demand_badge_label);
        Intrinsics.checkNotNullExpressionValue(string2, "getApplication().applica…ic_on_demand_badge_label)");
        onDemandLabel = string2;
    }

    public final List<String> getAllAccessPlaylistsStringsToHighlight(String title, List<String> tags) {
        Intrinsics.checkNotNullParameter(title, "title");
        if (FMPMFeatureGating.IS_ALL_ACCESS_TITLE_HIGHLIGHT_ENABLED.isDisabled() || tags == null || !tags.contains("hotspot-on-demand-playlists")) {
            return null;
        }
        return AmazonApplication.getCapabilities().getAllAccessHighlightConfigurations(title);
    }

    public final String getOnDemandBadging(ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        if (metadata instanceof PlaylistMetadata ? true : metadata instanceof UserPlaylistMetadata) {
            return getPlaylistOnDemandBadging(metadata);
        }
        if (metadata instanceof AlbumMetadata) {
            return getAlbumOnDemandBadging((AlbumMetadata) metadata);
        }
        return null;
    }

    public final Boolean getAllAccessCTAPlayIcon(ContentMetadata metadata) {
        if (FMPMFeatureGating.IS_CTA_PLAY_ICON_ENABLED_FOR_ALL_ACCESS_PLAYLISTS.isDisabled() || metadata == null) {
            return Boolean.FALSE;
        }
        if (metadata instanceof UserPlaylistMetadata) {
            return Boolean.valueOf(playlistConfiguration.shouldShowOnDemandCapability((EntityEligibilitiesProvider) metadata));
        }
        if (metadata instanceof PlaylistMetadata) {
            return Boolean.valueOf(playlistConfiguration.shouldShowOnDemandCapability((EntityEligibilitiesProvider) metadata));
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.lang.String getPlaylistOnDemandBadging(com.amazon.music.views.library.metadata.ContentMetadata r6) {
        /*
            r5 = this;
            com.amazon.mp3.featuregate.Feature r0 = com.amazon.mp3.featuregate.Feature.is_all_access_badging_enabled
            r1 = 1
            boolean r0 = r0.isEnabled(r1)
            r2 = 0
            if (r0 == 0) goto L43
            if (r6 != 0) goto Ld
            goto L43
        Ld:
            boolean r0 = r6 instanceof com.amazon.music.views.library.metadata.UserPlaylistMetadata
            r3 = 0
            if (r0 == 0) goto L30
            com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService$Companion r0 = com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService.INSTANCE
            com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService r0 = r0.get()
            r4 = r6
            com.amazon.music.views.library.metadata.UserPlaylistMetadata r4 = (com.amazon.music.views.library.metadata.UserPlaylistMetadata) r4
            java.lang.String r4 = r4.getPlaylistId()
            boolean r0 = r0.isOnDemandUserPlaylist(r4)
            if (r0 == 0) goto L3d
            com.amazon.mp3.baseviews.model.configuration.PlaylistModelConfiguration r0 = com.amazon.mp3.brush.AllAccessPlaylistsUtils.playlistConfiguration
            com.amazon.music.platform.providers.EntityEligibilitiesProvider r6 = (com.amazon.music.platform.providers.EntityEligibilitiesProvider) r6
            boolean r6 = r0.shouldShowOnDemandCapability(r6)
            if (r6 == 0) goto L3d
            goto L3e
        L30:
            boolean r0 = r6 instanceof com.amazon.music.views.library.metadata.PlaylistMetadata
            if (r0 == 0) goto L3d
            com.amazon.mp3.baseviews.model.configuration.PlaylistModelConfiguration r0 = com.amazon.mp3.brush.AllAccessPlaylistsUtils.playlistConfiguration
            com.amazon.music.platform.providers.EntityEligibilitiesProvider r6 = (com.amazon.music.platform.providers.EntityEligibilitiesProvider) r6
            boolean r1 = r0.shouldShowOnDemandCapability(r6)
            goto L3e
        L3d:
            r1 = r3
        L3e:
            if (r1 == 0) goto L43
            java.lang.String r6 = com.amazon.mp3.brush.AllAccessPlaylistsUtils.allAccessLabel
            return r6
        L43:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.brush.AllAccessPlaylistsUtils.getPlaylistOnDemandBadging(com.amazon.music.views.library.metadata.ContentMetadata):java.lang.String");
    }

    private final String getAlbumOnDemandBadging(AlbumMetadata metadata) {
        if (Feature.is_album_on_demand_badging_enabled.isEnabled(true) && metadata != null && albumConfiguration.shouldShowOnDemandCapability(metadata)) {
            return onDemandLabel;
        }
        return null;
    }

    public final String getAllAccessLabelRecents(MusicEntity playlist) {
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        if (Feature.is_all_access_badging_enabled.isEnabled(true)) {
            return getPlaylistOnDemandBadging(getPlaylistMetadata(playlist));
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final ContentMetadata getPlaylistMetadata(MusicEntity playlist) {
        Object next;
        Image image;
        if (playlist instanceof UserPlaylist) {
            UserPlaylist userPlaylist = (UserPlaylist) playlist;
            List<Image> images = userPlaylist.getImages();
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
            String blockRef = userPlaylist.getBlockRef();
            String title = userPlaylist.getTitle();
            String url = image == null ? null : image.getUrl();
            ContentPlaybackUtils contentPlaybackUtils = ContentPlaybackUtils.INSTANCE;
            Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
            String playlistId = userPlaylist.getPlaylistId();
            Intrinsics.checkNotNullExpressionValue(playlistId, "playlist.playlistId");
            Uri userPlaylistLocalUriWithoutTrack = contentPlaybackUtils.getUserPlaylistLocalUriWithoutTrack(applicationContext, playlistId);
            String playlistId2 = userPlaylist.getPlaylistId();
            Object objConvertPlaymodeEligibility = userPlaylist.getPlaymodeEligibility() != null ? BrushConverterUtils.convertPlaymodeEligibility(userPlaylist.getPlaymodeEligibility()) : null;
            Long lValueOf = Long.valueOf(userPlaylist.getTracksCount().longValue());
            Intrinsics.checkNotNullExpressionValue(playlistId2, "playlistId");
            return new UserPlaylistMetadata(blockRef, userPlaylistLocalUriWithoutTrack, null, title, url, null, lValueOf, null, false, playlistId2, objConvertPlaymodeEligibility, null, null, false, 14756, null);
        }
        if (!(playlist instanceof Playlist)) {
            return null;
        }
        Playlist playlist2 = (Playlist) playlist;
        String blockRef2 = playlist2.getBlockRef();
        String asin = playlist2.getAsin();
        String title2 = playlist2.getTitle();
        Image image2 = playlist2.getImage();
        String url2 = image2 != null ? image2.getUrl() : null;
        Boolean boolIsIsInLibrary = playlist2.isIsInLibrary();
        BrushConverterUtils brushConverterUtils = BrushConverterUtils.INSTANCE;
        List<String> contentTiers = playlist2.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "playlist.contentTiers");
        Set<ContentAccessType> setConvertTiers = brushConverterUtils.convertTiers(contentTiers);
        List<String> contentTiers2 = playlist2.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers2, "playlist.contentTiers");
        Set<SubscriptionType> setConvertOnDemandTiers = brushConverterUtils.convertOnDemandTiers(contentTiers2);
        PlaymodeEligibility playmodeEligibilityConvertPlaymodeEligibility = BrushConverterUtils.convertPlaymodeEligibility(playlist2.getPlaymodeEligibility());
        Boolean boolIsIsOwned = playlist2.isIsOwned();
        boolean zBooleanValue = boolIsIsOwned == null ? false : boolIsIsOwned.booleanValue();
        ContentPlaybackUtils contentPlaybackUtils2 = ContentPlaybackUtils.INSTANCE;
        String asin2 = playlist2.getAsin();
        Intrinsics.checkNotNullExpressionValue(asin2, "playlist.asin");
        Uri playlistUri = contentPlaybackUtils2.getPlaylistUri(asin2);
        List<String> contentTypes = playlist2.getContentTypes();
        if (contentTypes == null) {
            contentTypes = CollectionsKt__CollectionsJVMKt.listOf("AUDIO");
        }
        return new PlaylistMetadata(blockRef2, playlistUri, null, asin, title2, url2, null, null, null, null, boolIsIsInLibrary, setConvertTiers, contentTypes, false, null, null, null, null, zBooleanValue, null, false, playmodeEligibilityConvertPlaymodeEligibility, setConvertOnDemandTiers, false, null, 26993604, null);
    }

    public final boolean isAllAccessTracksWidget(List<String> tags) {
        return tags != null && tags.contains("artist-all-access-tracks");
    }
}
