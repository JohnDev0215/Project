package com.amazon.mp3.curate.provider;

import android.content.Context;
import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.download.helper.PrimePlaylistDownloadHelper;
import com.amazon.mp3.download.helper.UdoPlaylistDownloadHelper;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.fragment.DownloadSettingDialogFragment;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.music.curate.model.Playlist;
import com.amazon.music.curate.model.Track;
import com.amazon.music.curate.provider.DownloadProvider;
import com.amazon.music.curate.provider.DownloadStatus;
import com.amazon.music.downloads.notification.NotificationInfo;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CurateDownloadProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u001e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u001e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u000e\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001c¨\u0006\u001e"}, d2 = {"Lcom/amazon/mp3/curate/provider/CurateDownloadProvider;", "Lcom/amazon/music/curate/provider/DownloadProvider;", "()V", "download", "", "contentUri", "Landroid/net/Uri;", "notificationInfo", "Lcom/amazon/music/downloads/notification/NotificationInfo;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", Environment.PLAYLIST_PATH, "Lcom/amazon/music/curate/model/Playlist;", "track", "Lcom/amazon/music/curate/model/Track;", "getCatalogPlaylistDownloadStatus", "Landroidx/lifecycle/LiveData;", "Lcom/amazon/music/curate/provider/DownloadStatus;", "asin", "", "context", "Landroid/content/Context;", "getPlaylistTracksUri", "getTrackDownloadStatus", "getUserPlaylistDownloadStatus", "luid", "toDownloadStatus", "downloadState", "", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CurateDownloadProvider implements DownloadProvider {
    private static final String TAG = CurateDownloadProvider.class.getSimpleName();

    @Override // com.amazon.music.curate.provider.DownloadProvider
    public void download(Playlist playlist, FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        Intrinsics.checkNotNullParameter(activity, "activity");
        Uri playlistTracksUri = getPlaylistTracksUri(playlist, activity);
        download(playlistTracksUri, new NotificationInfo(playlist.getTitle(), null, ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.PLAYLIST_UDO.intValue(), MediaProvider.Playlists.getPlaylistId(playlistTracksUri)).toString()), activity);
    }

    private final Uri getPlaylistTracksUri(Playlist playlist, Context context) {
        Uri uri = MediaProvider.UdoPlaylists.Tracks.appendTracksToUri(MediaProvider.UdoPlaylists.getContentUriFromLuid(context, "cirrus", playlist.getId()));
        Intrinsics.checkNotNullExpressionValue(uri, "uri");
        return uri;
    }

    private final void download(Uri contentUri, NotificationInfo notificationInfo, FragmentActivity activity) {
        if (DownloadSettingDialogFragment.shouldShowDownloadSettingsDialogFragment(activity)) {
            DownloadSettingDialogFragment.showSettingsDialog(activity, contentUri.toString(), contentUri, notificationInfo, false);
        } else {
            MusicDownloader.getInstance(activity.getApplication()).download(contentUri.toString(), contentUri, notificationInfo, false, true);
        }
    }

    @Override // com.amazon.music.curate.provider.DownloadProvider
    public LiveData<DownloadStatus> getUserPlaylistDownloadStatus(String luid, Context context) {
        Intrinsics.checkNotNullParameter(luid, "luid");
        Intrinsics.checkNotNullParameter(context, "context");
        MutableLiveData mutableLiveData = new MutableLiveData();
        mutableLiveData.postValue(toDownloadStatus(new UdoPlaylistDownloadHelper(context).getDownloadState(luid)));
        return mutableLiveData;
    }

    @Override // com.amazon.music.curate.provider.DownloadProvider
    public LiveData<DownloadStatus> getCatalogPlaylistDownloadStatus(String asin, Context context) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        Intrinsics.checkNotNullParameter(context, "context");
        MutableLiveData mutableLiveData = new MutableLiveData();
        mutableLiveData.postValue(toDownloadStatus(new PrimePlaylistDownloadHelper(context).getDownloadState(asin)));
        return mutableLiveData;
    }

    public final DownloadStatus toDownloadStatus(int downloadState) {
        if (downloadState == 0) {
            return DownloadStatus.DOWNLOADED;
        }
        if (downloadState == 1) {
            return DownloadStatus.QUEUED;
        }
        if (downloadState == 2) {
            return DownloadStatus.ERROR_DOWNLOADING;
        }
        if (downloadState == 4) {
            return DownloadStatus.DOWNLOADING;
        }
        if (downloadState == 7) {
            return DownloadStatus.NOT_DOWNLOADED;
        }
        return DownloadStatus.NOT_DOWNLOADED;
    }

    @Override // com.amazon.music.curate.provider.DownloadProvider
    public void download(Track track) {
        Intrinsics.checkNotNullParameter(track, "track");
        throw new NotImplementedError(Intrinsics.stringPlus("An operation is not implemented: ", "not implemented"));
    }

    @Override // com.amazon.music.curate.provider.DownloadProvider
    public LiveData<DownloadStatus> getTrackDownloadStatus(String asin, Context context) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        Intrinsics.checkNotNullParameter(context, "context");
        MutableLiveData mutableLiveData = new MutableLiveData();
        MusicTrack trackForAsin = new CirrusSourceLibraryItemFactory(context).getTrackForAsin(asin);
        if (trackForAsin != null) {
            mutableLiveData.postValue(toDownloadStatus(trackForAsin.getDownloadState()));
        } else {
            mutableLiveData.postValue(toDownloadStatus(5));
        }
        return mutableLiveData;
    }
}
