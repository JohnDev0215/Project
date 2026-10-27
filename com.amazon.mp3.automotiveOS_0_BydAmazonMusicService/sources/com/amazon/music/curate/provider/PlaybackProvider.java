package com.amazon.music.curate.provider;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.environment.Environment;
import com.amazon.music.curate.model.Playlist;
import com.amazon.music.curate.model.Track;
import com.amazon.music.curate.model.station;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;

/* JADX INFO: compiled from: PlaybackProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H'J\u0010\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0003H&J!\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&¢\u0006\u0002\u0010\fJ0\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u0017H&JD\u0010\u0018\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u001d2\u0018\u0010\u001e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a0\u001f0\u0014H&JD\u0010 \u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u001d2\u0018\u0010\u001e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a0\u001f0\u0014H&JD\u0010!\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u001d2\u0018\u0010\u001e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a0\u001f0\u0014H&J<\u0010\"\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001d2\u0018\u0010\u001e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a0\u001f0\u0014H&J<\u0010#\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001d2\u0018\u0010\u001e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a0\u001f0\u0014H&JD\u0010$\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010%\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001d2\u0018\u0010\u001e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a0\u001f0\u0014H&J\u001a\u0010$\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020'2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H&JD\u0010(\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u001d2\u0018\u0010\u001e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a0\u001f0\u0014H&J(\u0010)\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H&¨\u0006*"}, d2 = {"Lcom/amazon/music/curate/provider/PlaybackProvider;", "", "getPlaybackItem", "Landroidx/lifecycle/LiveData;", "Lcom/amazon/music/curate/provider/PlaybackItem;", "isPlayStateChanged", "", "isPlaying", "context", "Landroid/content/Context;", "musicItem", "Lcom/amazon/music/curate/provider/MusicItem;", "(Landroid/content/Context;Lcom/amazon/music/curate/provider/MusicItem;)Ljava/lang/Boolean;", PageUriUtils.DO_PLAY_VALUE, "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", Environment.PLAYLIST_PATH, "Lcom/amazon/music/curate/model/Playlist;", "tracks", "", "Lcom/amazon/music/curate/model/Track;", "index", "", "playAlbum", "id", "", "isShuffleOn", "playbackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", ContextMappingConstants.CONTENT_INFO, "", "playArtist", "playCatalogPlaylist", "playPlaylistOnDemand", "playSFA", "playStation", "idType", "station", "Lcom/amazon/music/curate/model/Station;", "playUserPlaylist", "shuffle", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface PlaybackProvider {
    @Deprecated(message = "No longer used", replaceWith = @ReplaceWith(expression = "getPlaybackItem(activity", imports = {}))
    LiveData<PlaybackItem> getPlaybackItem();

    LiveData<Boolean> isPlayStateChanged();

    Boolean isPlaying(Context context, MusicItem musicItem);

    void play(FragmentActivity activity, Playlist playlist, List<Track> tracks, int index);

    void playAlbum(FragmentActivity activity, String id, boolean isShuffleOn, PlaybackPageType playbackPageType, List<? extends Map<String, String>> contentInfo);

    void playArtist(FragmentActivity activity, String id, boolean isShuffleOn, PlaybackPageType playbackPageType, List<? extends Map<String, String>> contentInfo);

    void playCatalogPlaylist(FragmentActivity activity, String id, boolean isShuffleOn, PlaybackPageType playbackPageType, List<? extends Map<String, String>> contentInfo);

    void playPlaylistOnDemand(FragmentActivity activity, String id, PlaybackPageType playbackPageType, List<? extends Map<String, String>> contentInfo);

    void playSFA(FragmentActivity activity, String id, PlaybackPageType playbackPageType, List<? extends Map<String, String>> contentInfo);

    void playStation(FragmentActivity activity, String idType, String id, PlaybackPageType playbackPageType, List<? extends Map<String, String>> contentInfo);

    void playStation(station station, FragmentActivity activity);

    void playUserPlaylist(FragmentActivity activity, String id, boolean isShuffleOn, PlaybackPageType playbackPageType, List<? extends Map<String, String>> contentInfo);

    void shuffle(FragmentActivity activity, Playlist playlist, List<Track> tracks);
}
