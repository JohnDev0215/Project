package com.amazon.mp3.navigation.deeplink;

import android.content.Context;
import android.os.Bundle;
import com.amazon.layout.music.model.AlbumHint;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.detailpages.playlist.actions.PlaylistPlaybackAction;
import com.amazon.mp3.external.api.uri.QuerySongByAsin;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.factory.LibraryItemFactory;
import com.amazon.mp3.navigation.playback.AlbumPlaybackHelper;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.music.destination.parser.Action;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.visualon.OSMPUtils.voOSType;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseViewsDeeplinkActionHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u001e\u0010\n\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002JO\u0010\u0014\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\f2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0014\u0010\u0015¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/navigation/deeplink/BaseViewsDeeplinkActionHandler;", "", "Landroid/os/Bundle;", "bundle", "", "clearDeeplinkExtras", "Landroid/content/Context;", "context", "Lcom/amazon/music/views/library/metadata/AlbumMetadata;", "metadata", "handleAlbumDeeplinkAction", "Lcom/amazon/music/views/library/metadata/PlaylistMetadata;", "Lcom/amazon/mp3/detailpages/playlist/actions/PlaylistPlaybackAction;", "playlistPlaybackAction", "", "startPosition", "", ParserUtil.TRACK_ASIN_QUERY_PARAM_NAME, "", "isCustomerSelectedEntity", "handlePlaylistDeeplinkAction", "(Lcom/amazon/music/views/library/metadata/PlaylistMetadata;Landroid/os/Bundle;Lcom/amazon/mp3/detailpages/playlist/actions/PlaylistPlaybackAction;Ljava/lang/Integer;Ljava/lang/String;ZLandroid/content/Context;)V", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BaseViewsDeeplinkActionHandler {
    public static final BaseViewsDeeplinkActionHandler INSTANCE = new BaseViewsDeeplinkActionHandler();

    private BaseViewsDeeplinkActionHandler() {
    }

    public final void handleAlbumDeeplinkAction(Context context, AlbumMetadata metadata, Bundle bundle) {
        Album album;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        Serializable serializable = bundle.getSerializable("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_ACTION");
        if (serializable == null) {
            return;
        }
        String string = bundle.getString("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_TRACK_ASIN");
        AlbumPlaybackHelper albumPlaybackHelper = new AlbumPlaybackHelper(context);
        Unit unit = null;
        if (serializable == Action.PLAY) {
            if (string != null) {
                albumPlaybackHelper.play((AlbumHint) null, metadata.getAsin(), PrimeCollectionTask.Task.PLAYBACK_DEEPLINK, string);
                unit = Unit.INSTANCE;
            }
            if (unit == null) {
                albumPlaybackHelper.play(null, metadata.getAsin(), false, ControlSource.EXTERNAL, false);
            }
        } else if (serializable == Action.SHARE) {
            LibraryItemFactory libraryItemFactory = AmazonApplication.getLibraryItemFactory();
            if (string != null) {
                MusicTrack track = libraryItemFactory.getTrack(new QuerySongByAsin(context).findCollectionUriByAsin(string));
                if (track != null) {
                    ShareDeeplinkActionUtil.INSTANCE.shareTrackItem(context, track);
                }
                unit = Unit.INSTANCE;
            }
            if (unit == null && (album = libraryItemFactory.getAlbum(metadata.getUri())) != null) {
                ShareDeeplinkActionUtil.INSTANCE.shareAlbumItem(context, album);
            }
        } else {
            if (string != null) {
                albumPlaybackHelper.play((AlbumHint) null, metadata.getAsin(), PrimeCollectionTask.Task.PLAYBACK_FULLSCREEN_DEEPLINK, string);
                unit = Unit.INSTANCE;
            }
            if (unit == null) {
                albumPlaybackHelper.play(null, metadata.getAsin(), true, ControlSource.EXTERNAL, false);
            }
        }
        clearDeeplinkExtras(bundle);
    }

    public final void handlePlaylistDeeplinkAction(PlaylistMetadata metadata, Bundle bundle, PlaylistPlaybackAction playlistPlaybackAction, Integer startPosition, String trackAsin, boolean isCustomerSelectedEntity, Context context) {
        Unit unit;
        CatalogPlaylist catalogPlaylist;
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        Intrinsics.checkNotNullParameter(playlistPlaybackAction, "playlistPlaybackAction");
        Intrinsics.checkNotNullParameter(context, "context");
        Serializable serializable = bundle.getSerializable("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_ACTION");
        if (serializable == null) {
            return;
        }
        if (serializable == Action.PLAY) {
            playlistPlaybackAction.startPlayback(metadata, false, (voOSType.VOOSMP_PID_ANALYTICS_AGENT & 4) != 0 ? null : startPosition, (voOSType.VOOSMP_PID_ANALYTICS_AGENT & 8) != 0 ? null : null, (voOSType.VOOSMP_PID_ANALYTICS_AGENT & 16) != 0 ? false : isCustomerSelectedEntity, false, (voOSType.VOOSMP_PID_ANALYTICS_AGENT & 64) != 0 ? null : null, (voOSType.VOOSMP_PID_ANALYTICS_AGENT & 128) != 0 ? null : trackAsin, (voOSType.VOOSMP_PID_ANALYTICS_AGENT & 256) != 0 ? null : null);
        } else if (serializable == Action.SHARE) {
            LibraryItemFactory libraryItemFactory = AmazonApplication.getLibraryItemFactory();
            if (trackAsin == null) {
                unit = null;
            } else {
                MusicTrack track = libraryItemFactory.getTrack(new QuerySongByAsin(context).findCollectionUriByAsin(trackAsin));
                if (track != null) {
                    ShareDeeplinkActionUtil.INSTANCE.shareTrackItem(context, track);
                }
                unit = Unit.INSTANCE;
            }
            if (unit == null && (catalogPlaylist = libraryItemFactory.getCatalogPlaylist(metadata.getUri())) != null) {
                ShareDeeplinkActionUtil.INSTANCE.sharePlaylistItem(context, catalogPlaylist);
            }
        } else if (serializable == Action.PLAYFULL) {
            playlistPlaybackAction.startPlayback(metadata, false, (voOSType.VOOSMP_PID_ANALYTICS_AGENT & 4) != 0 ? null : startPosition, (voOSType.VOOSMP_PID_ANALYTICS_AGENT & 8) != 0 ? null : null, (voOSType.VOOSMP_PID_ANALYTICS_AGENT & 16) != 0 ? false : isCustomerSelectedEntity, true, (voOSType.VOOSMP_PID_ANALYTICS_AGENT & 64) != 0 ? null : null, (voOSType.VOOSMP_PID_ANALYTICS_AGENT & 128) != 0 ? null : trackAsin, (voOSType.VOOSMP_PID_ANALYTICS_AGENT & 256) != 0 ? null : null);
        } else {
            playlistPlaybackAction.startPlayback(metadata, false, (voOSType.VOOSMP_PID_ANALYTICS_AGENT & 4) != 0 ? null : startPosition, (voOSType.VOOSMP_PID_ANALYTICS_AGENT & 8) != 0 ? null : null, (voOSType.VOOSMP_PID_ANALYTICS_AGENT & 16) != 0 ? false : isCustomerSelectedEntity, true, (voOSType.VOOSMP_PID_ANALYTICS_AGENT & 64) != 0 ? null : null, (voOSType.VOOSMP_PID_ANALYTICS_AGENT & 128) != 0 ? null : null, (voOSType.VOOSMP_PID_ANALYTICS_AGENT & 256) != 0 ? null : null);
        }
        clearDeeplinkExtras(bundle);
    }

    private final void clearDeeplinkExtras(Bundle bundle) {
        bundle.remove("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_ACTION");
        bundle.remove("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_TRACK_ASIN");
    }
}
