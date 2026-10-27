package com.amazon.music.curate.data;

import android.content.Context;
import android.view.View;
import androidx.paging.LivePagedListBuilder;
import com.amazon.mp3.environment.Environment;
import com.amazon.music.curate.data.database.PlaylistDB;
import com.amazon.music.curate.model.Playlist;
import com.amazon.music.curate.model.Track;
import com.amazon.music.curate.provider.Configuration;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

/* JADX INFO: compiled from: UserPlaylistRepository.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 (2\u00020\u0001:\u0001(B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0091\u0003\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2!\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\n¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\f2!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00100\f2Q\u0010\u0014\u001aM\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0017\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u00100\u00152<\u0010\u001d\u001a8\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0017\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00100\u001e2!\u0010\u001f\u001a\u001d\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00100\f2!\u0010 \u001a\u001d\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00100\f2'\u0010!\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00100\f2!\u0010\"\u001a\u001d\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00100\f2\u000e\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0$2\b\u0010%\u001a\u0004\u0018\u00010\nJ\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00100'2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/amazon/music/curate/data/UserPlaylistRepository;", "", "configuration", "Lcom/amazon/music/curate/provider/Configuration;", "context", "Landroid/content/Context;", "(Lcom/amazon/music/curate/provider/Configuration;Landroid/content/Context;)V", "getUserPlaylist", "Lcom/amazon/music/curate/data/PlaylistLiveData;", "userPlaylistId", "", "onSeeMoreClickAction", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "playlistId", "", "onMoreClickAction", "Landroid/view/View;", "view", "onPlayButtonClick", "Lkotlin/Function3;", "Lcom/amazon/music/curate/model/Playlist;", Environment.PLAYLIST_PATH, "", "Lcom/amazon/music/curate/model/Track;", "tracks", "", "index", "onShuffleButtonClick", "Lkotlin/Function2;", "onDownloadButtonClick", "onShareButtonClick", "onStationButtonClick", "onEditButtonClick", "onGetFragmentSessionId", "Lkotlin/Function0;", "fragmentSessionId", "removeUserPlaylist", "Lrx/Observable;", "Companion", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UserPlaylistRepository {
    private static final Logger LOG;
    private static final int PAGE_SIZE = 20;
    private final Configuration configuration;
    private final Context context;

    public UserPlaylistRepository(Configuration configuration, Context context) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(context, "context");
        this.configuration = configuration;
        this.context = context;
    }

    static {
        Logger logger = LoggerFactory.getLogger(UserPlaylistRepository.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(UserPlaylistRe…y::class.java.simpleName)");
        LOG = logger;
    }

    public final Observable<Unit> removeUserPlaylist(String userPlaylistId) {
        Intrinsics.checkNotNullParameter(userPlaylistId, "userPlaylistId");
        PlaylistDB cache = this.configuration.getCache();
        Intrinsics.checkNotNull(cache);
        return cache.removePlaylist(userPlaylistId);
    }

    public final PlaylistLiveData getUserPlaylist(String userPlaylistId, Function1<? super String, Unit> onSeeMoreClickAction, Function1<? super View, Unit> onMoreClickAction, Function3<? super Playlist, ? super List<Track>, ? super Integer, Unit> onPlayButtonClick, Function2<? super Playlist, ? super List<Track>, Unit> onShuffleButtonClick, Function1<? super Playlist, Unit> onDownloadButtonClick, Function1<? super Playlist, Unit> onShareButtonClick, Function1<? super List<Track>, Unit> onStationButtonClick, Function1<? super Playlist, Unit> onEditButtonClick, Function0<String> onGetFragmentSessionId, String fragmentSessionId) {
        Intrinsics.checkNotNullParameter(userPlaylistId, "userPlaylistId");
        Intrinsics.checkNotNullParameter(onSeeMoreClickAction, "onSeeMoreClickAction");
        Intrinsics.checkNotNullParameter(onMoreClickAction, "onMoreClickAction");
        Intrinsics.checkNotNullParameter(onPlayButtonClick, "onPlayButtonClick");
        Intrinsics.checkNotNullParameter(onShuffleButtonClick, "onShuffleButtonClick");
        Intrinsics.checkNotNullParameter(onDownloadButtonClick, "onDownloadButtonClick");
        Intrinsics.checkNotNullParameter(onShareButtonClick, "onShareButtonClick");
        Intrinsics.checkNotNullParameter(onStationButtonClick, "onStationButtonClick");
        Intrinsics.checkNotNullParameter(onEditButtonClick, "onEditButtonClick");
        Intrinsics.checkNotNullParameter(onGetFragmentSessionId, "onGetFragmentSessionId");
        LOG.debug("getUserPlaylist(" + userPlaylistId + ')');
        PlaylistDetailDataSourceFactory playlistDetailDataSourceFactory = new PlaylistDetailDataSourceFactory(this.configuration, this.context, userPlaylistId, fragmentSessionId, onGetFragmentSessionId, onSeeMoreClickAction, onMoreClickAction, onPlayButtonClick, onShuffleButtonClick, onDownloadButtonClick, onShareButtonClick, onStationButtonClick, onEditButtonClick);
        return new PlaylistLiveData(new LivePagedListBuilder(playlistDetailDataSourceFactory, 20).build(), playlistDetailDataSourceFactory.getDataSourceLiveData(), playlistDetailDataSourceFactory.getRecsLoadedLiveData());
    }
}
