package com.amazon.music.skyfire.ui.skyfire;

import androidx.core.app.NotificationCompat;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.find.utils.PageUriUtils;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;

/* JADX INFO: compiled from: Contexts.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u001e\bÆ\u0002\u0018\u00002\u00020\u0001:\u001c\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001eB\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u001f"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts;", "", "()V", "AddTrackToLibrary", "DisplayErrorScreen", "DownloadTrack", "LibraryArtistDetail", "LiveEventDetail", "MerchDetail", "Notification", "OpenAlbumDetail", "OpenArtistDetail", "OpenArtistReleaseDetail", "OpenArtistSpotlight", "OpenCalendar", "OpenDetail", "OpenExternalLink", "OpenLiveStream", "OpenMusicExperience", "OpenPlaylistDetail", "OpenPodcastDetail", "OpenPopularContent", "OpenSeeMore", "OpenShareLink", "OpenTarget", "SetLikeState", "SetNowPlayingIntroMetadata", "SetPlaybackViewLoadingImage", "SetWatchedLiveStreamFlexEventData", "ShowUpsell", "Upsell", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class Contexts {
    public static final Contexts INSTANCE = new Contexts();

    private Contexts() {
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts$OpenArtistReleaseDetail;", "", "()V", "artistAsin", "", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class OpenArtistReleaseDetail {
        public static final OpenArtistReleaseDetail INSTANCE = new OpenArtistReleaseDetail();
        public static final String artistAsin = "artistAsin";

        private OpenArtistReleaseDetail() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts$AddTrackToLibrary;", "", "()V", "asin", "", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class AddTrackToLibrary {
        public static final AddTrackToLibrary INSTANCE = new AddTrackToLibrary();
        public static final String asin = "ASIN";

        private AddTrackToLibrary() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts$SetLikeState;", "", "()V", "asin", "", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class SetLikeState {
        public static final SetLikeState INSTANCE = new SetLikeState();
        public static final String asin = "ASIN";

        private SetLikeState() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts$DownloadTrack;", "", "()V", "asin", "", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class DownloadTrack {
        public static final DownloadTrack INSTANCE = new DownloadTrack();
        public static final String asin = "ASIN";

        private DownloadTrack() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts$Notification;", "", "()V", "message", "", "style", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Notification {
        public static final Notification INSTANCE = new Notification();
        public static final String message = "MESSAGE";
        public static final String style = "STYLE";

        private Notification() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts$OpenAlbumDetail;", "", "()V", "asin", "", ParserUtil.TRACK_ASIN_QUERY_PARAM_NAME, "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class OpenAlbumDetail {
        public static final OpenAlbumDetail INSTANCE = new OpenAlbumDetail();
        public static final String asin = "ASIN";
        public static final String trackAsin = "TRACK_ASIN";

        private OpenAlbumDetail() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts$OpenArtistDetail;", "", "()V", "asin", "", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class OpenArtistDetail {
        public static final OpenArtistDetail INSTANCE = new OpenArtistDetail();
        public static final String asin = "ASIN";

        private OpenArtistDetail() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts$OpenArtistSpotlight;", "", "()V", "asin", "", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class OpenArtistSpotlight {
        public static final OpenArtistSpotlight INSTANCE = new OpenArtistSpotlight();
        public static final String asin = "ASIN";

        private OpenArtistSpotlight() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts$OpenMusicExperience;", "", "()V", "asin", "", "experienceId", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class OpenMusicExperience {
        public static final OpenMusicExperience INSTANCE = new OpenMusicExperience();
        public static final String asin = "ASIN";
        public static final String experienceId = "EXPERIENCE_ID";

        private OpenMusicExperience() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts$OpenDetail;", "", "()V", "asin", "", "category", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class OpenDetail {
        public static final OpenDetail INSTANCE = new OpenDetail();
        public static final String asin = "ASIN";
        public static final String category = "CATEGORY";

        private OpenDetail() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts$LibraryArtistDetail;", "", "()V", "artistId", "", "asin", PageUriUtils.FILTER_QUERY_PARAM, "followerCount", "name", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class LibraryArtistDetail {
        public static final LibraryArtistDetail INSTANCE = new LibraryArtistDetail();
        public static final String artistId = "ARTIST_ID";
        public static final String asin = "ASIN";
        public static final String filter = "FILTER";
        public static final String followerCount = "FOLLOWER_COUNT";
        public static final String name = "NAME";

        private LibraryArtistDetail() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts$OpenPlaylistDetail;", "", "()V", "asin", "", ParserUtil.TRACK_ASIN_QUERY_PARAM_NAME, "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class OpenPlaylistDetail {
        public static final OpenPlaylistDetail INSTANCE = new OpenPlaylistDetail();
        public static final String asin = "ASIN";
        public static final String trackAsin = "TRACK_ASIN";

        private OpenPlaylistDetail() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts$OpenPopularContent;", "", "()V", "node", "", "widgetId", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class OpenPopularContent {
        public static final OpenPopularContent INSTANCE = new OpenPopularContent();
        public static final String node = "NODE";
        public static final String widgetId = "WIDGET_ID";

        private OpenPopularContent() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts$OpenSeeMore;", "", "()V", "uri", "", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class OpenSeeMore {
        public static final OpenSeeMore INSTANCE = new OpenSeeMore();
        public static final String uri = "URI";

        private OpenSeeMore() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts$OpenShareLink;", "", "()V", "backgroundImage", "", "contentType", ContextMappingConstants.ENTITY_ID, ContextMappingConstants.ENTITY_ID_TYPE, PageUriUtils.ENTITY_TYPE_QUERY_PARAM, ContextMappingConstants.IMAGE, "imageAspectRatio", "message", "refMarker", "subject", MediaTrack.ROLE_SUBTITLE, "title", ImagesContract.URL, "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class OpenShareLink {
        public static final OpenShareLink INSTANCE = new OpenShareLink();
        public static final String backgroundImage = "BACKGROUND_IMAGE";
        public static final String contentType = "CONTENT_TYPE";
        public static final String entityId = "ENTITY_ID";
        public static final String entityIdType = "ENTITY_ID_TYPE";
        public static final String entityType = "ENTITY_TYPE";
        public static final String image = "IMAGE";
        public static final String imageAspectRatio = "IMAGE_ASPECT_RATIO";
        public static final String message = "MESSAGE";
        public static final String refMarker = "REF";
        public static final String subject = "SUBJECT";
        public static final String subtitle = "SUBTITLE";
        public static final String title = "TITLE";
        public static final String url = "URL";

        private OpenShareLink() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts$OpenExternalLink;", "", "()V", ImagesContract.URL, "", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class OpenExternalLink {
        public static final OpenExternalLink INSTANCE = new OpenExternalLink();
        public static final String url = "URL";

        private OpenExternalLink() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts$OpenLiveStream;", "", "()V", "liveStreamId", "", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class OpenLiveStream {
        public static final OpenLiveStream INSTANCE = new OpenLiveStream();
        public static final String liveStreamId = "LIVE_STREAM_ID";

        private OpenLiveStream() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts$OpenPodcastDetail;", "", "()V", ImagesContract.URL, "", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class OpenPodcastDetail {
        public static final OpenPodcastDetail INSTANCE = new OpenPodcastDetail();
        public static final String url = "URL";

        private OpenPodcastDetail() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts$Upsell;", "", "()V", "add", "", NotificationCompat.CATEGORY_NAVIGATION, "playback", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Upsell {
        public static final Upsell INSTANCE = new Upsell();
        public static final String add = "ADD";
        public static final String navigation = "NAVIGATION";
        public static final String playback = "PLAYBACK";

        private Upsell() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts$SetWatchedLiveStreamFlexEventData;", "", "()V", "artistAsin", "", "liveStreamId", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class SetWatchedLiveStreamFlexEventData {
        public static final SetWatchedLiveStreamFlexEventData INSTANCE = new SetWatchedLiveStreamFlexEventData();
        public static final String artistAsin = "ARTIST_ASIN";
        public static final String liveStreamId = "LIVE_STREAM_ID";

        private SetWatchedLiveStreamFlexEventData() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts$DisplayErrorScreen;", "", "()V", "buttonText", "", ContextMappingConstants.PRIMARY_TEXT, ContextMappingConstants.SECONDARY_TEXT, "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class DisplayErrorScreen {
        public static final DisplayErrorScreen INSTANCE = new DisplayErrorScreen();
        public static final String buttonText = "BUTTON_TEXT";
        public static final String primaryText = "PRIMARY_TEXT";
        public static final String secondaryText = "SECONDARY_TEXT";

        private DisplayErrorScreen() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts$SetNowPlayingIntroMetadata;", "", "()V", "artworkUrl", "", "asin", "primarySubtitle", "secondarySubtitle", "title", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class SetNowPlayingIntroMetadata {
        public static final SetNowPlayingIntroMetadata INSTANCE = new SetNowPlayingIntroMetadata();
        public static final String artworkUrl = "ARTWORK_URL";
        public static final String asin = "ASIN";
        public static final String primarySubtitle = "PRIMARY_SUBTITLE";
        public static final String secondarySubtitle = "SECONDARY_SUBTITLE";
        public static final String title = "TITLE";

        private SetNowPlayingIntroMetadata() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts$SetPlaybackViewLoadingImage;", "", "()V", "imageUrl", "", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class SetPlaybackViewLoadingImage {
        public static final SetPlaybackViewLoadingImage INSTANCE = new SetPlaybackViewLoadingImage();
        public static final String imageUrl = "IMAGE_URL";

        private SetPlaybackViewLoadingImage() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts$ShowUpsell;", "", "()V", "asin", "", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class ShowUpsell {
        public static final ShowUpsell INSTANCE = new ShowUpsell();
        public static final String asin = "ASIN";

        private ShowUpsell() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts$LiveEventDetail;", "", "()V", ContentUtils.KEY_EVENT_ID, "", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class LiveEventDetail {
        public static final LiveEventDetail INSTANCE = new LiveEventDetail();
        public static final String eventId = "EVENT_ID";

        private LiveEventDetail() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts$OpenCalendar;", "", "()V", "endDate", "", "note", "startDate", "title", ImagesContract.URL, "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class OpenCalendar {
        public static final OpenCalendar INSTANCE = new OpenCalendar();
        public static final String endDate = "END_DATE";
        public static final String note = "NOTE";
        public static final String startDate = "START_DATE";
        public static final String title = "TITLE";
        public static final String url = "URL";

        private OpenCalendar() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts$MerchDetail;", "", "()V", "artistAsin", "", "merchAsin", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class MerchDetail {
        public static final MerchDetail INSTANCE = new MerchDetail();
        public static final String artistAsin = "ARTIST_ASIN";
        public static final String merchAsin = "ASIN";

        private MerchDetail() {
        }
    }

    /* JADX INFO: compiled from: Contexts.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Contexts$OpenTarget;", "", "()V", "target", "", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class OpenTarget {
        public static final OpenTarget INSTANCE = new OpenTarget();
        public static final String target = "TARGET";

        private OpenTarget() {
        }
    }
}
