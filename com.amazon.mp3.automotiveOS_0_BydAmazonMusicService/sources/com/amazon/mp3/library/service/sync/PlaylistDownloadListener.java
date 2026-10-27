package com.amazon.mp3.library.service.sync;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.downloads.DownloadState;
import com.amazon.music.downloads.Group;
import com.amazon.music.downloads.Item;
import com.amazon.music.downloads.notification.DownloadListener;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: PlaylistDownloadListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ$\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J$\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J$\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u000eH\u0016J&\u0010\u0012\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00142\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0011H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/library/service/sync/PlaylistDownloadListener;", "Lcom/amazon/music/downloads/notification/DownloadListener;", "context", "Landroid/content/Context;", "shouldNotifyAll", "", Environment.PLAYLIST_PATH, "Lcom/amazon/mp3/library/item/Playlist;", "(Landroid/content/Context;ZLcom/amazon/mp3/library/item/Playlist;)V", "onProgressUpdate", "", "p0", "", "p1", "Lcom/amazon/music/downloads/Group;", "p2", "", "Lcom/amazon/music/downloads/Item;", "onStatusUpdate", "downloadState", "Lcom/amazon/music/downloads/DownloadState;", TetheredMessageKey.requestId, "group", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class PlaylistDownloadListener extends DownloadListener {
    private static volatile PlaylistDownloadListener INSTANCE;
    private final Context context;
    private final Playlist playlist;
    private final boolean shouldNotifyAll;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = PlaylistDownloadListener.class.getSimpleName();

    @Override // com.amazon.music.downloads.notification.DownloadListener
    public void onProgressUpdate(String p0, Group p1, float p2) {
    }

    @Override // com.amazon.music.downloads.notification.DownloadListener
    public void onProgressUpdate(String p0, Item p1, float p2) {
    }

    @Override // com.amazon.music.downloads.notification.DownloadListener
    public void onStatusUpdate(DownloadState p0, String p1, Item p2) {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlaylistDownloadListener(Context context, boolean z, Playlist playlist) {
        super(z);
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.shouldNotifyAll = z;
        this.playlist = playlist;
    }

    /* JADX INFO: compiled from: PlaylistDownloadListener.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/library/service/sync/PlaylistDownloadListener$Companion;", "", "()V", "INSTANCE", "Lcom/amazon/mp3/library/service/sync/PlaylistDownloadListener;", "TAG", "", "kotlin.jvm.PlatformType", "getInstance", "context", "Landroid/content/Context;", Environment.PLAYLIST_PATH, "Lcom/amazon/mp3/library/item/Playlist;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final PlaylistDownloadListener getInstance(Context context, Playlist playlist) {
            PlaylistDownloadListener playlistDownloadListener;
            Intrinsics.checkNotNullParameter(context, "context");
            PlaylistDownloadListener playlistDownloadListener2 = PlaylistDownloadListener.INSTANCE;
            if (playlistDownloadListener2 != null) {
                return playlistDownloadListener2;
            }
            synchronized (this) {
                playlistDownloadListener = new PlaylistDownloadListener(context, false, playlist);
                PlaylistDownloadListener.INSTANCE = playlistDownloadListener;
            }
            return playlistDownloadListener;
        }
    }

    @Override // com.amazon.music.downloads.notification.DownloadListener
    public void onStatusUpdate(DownloadState downloadState, String requestId, Group group) {
        Intrinsics.checkNotNullParameter(downloadState, "downloadState");
        if (downloadState != DownloadState.DOWNLOADED || StringUtils.isBlank(requestId)) {
            return;
        }
        String string = Long.toString(MediaProvider.Playlists.getPlaylistId(Uri.parse(requestId)));
        Playlist playlist = this.playlist;
        if (Intrinsics.areEqual(string, playlist == null ? null : playlist.getId())) {
            PlaylistUpdater.autoDownload(this.playlist);
            MusicDownloader.getInstance(this.context).unregisterDownloadListener(this);
        }
        Log.debug(TAG, " onStatusUpdate, downloadState = %s, requestId = %s", downloadState.name(), requestId);
    }
}
