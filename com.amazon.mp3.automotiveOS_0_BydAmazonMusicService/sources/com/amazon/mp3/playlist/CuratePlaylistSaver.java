package com.amazon.mp3.playlist;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.playlist.service.PlaylistException;
import com.amazon.mp3.playlist.service.Playlists;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.Log;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class CuratePlaylistSaver {
    private static final String TAG = "CuratePlaylistSaver";

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$saveTracksToPlaylist$2(Uri uri) {
    }

    public void saveTracksToPlaylist(final Context context, final String playlistId, final List<String> trackAsins) {
        Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.playlist.CuratePlaylistSaver$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CuratePlaylistSaver.lambda$saveTracksToPlaylist$0(context, playlistId, trackAsins, (Subscriber) obj);
            }
        }).delay(2L, TimeUnit.SECONDS).doOnNext(new Action1() { // from class: com.amazon.mp3.playlist.CuratePlaylistSaver$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CuratePlaylistSaver.lambda$saveTracksToPlaylist$1(context, trackAsins, (Uri) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.playlist.CuratePlaylistSaver$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CuratePlaylistSaver.lambda$saveTracksToPlaylist$2((Uri) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.playlist.CuratePlaylistSaver$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                BauhausToastUtils.showDefaultToast(context, R.string.dmusic_playlist_edit_error_adding_song_toast_msg, 1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$saveTracksToPlaylist$0(Context context, String str, List list, Subscriber subscriber) {
        try {
            Uri contentUriFromLuid = MediaProvider.UdoPlaylists.getContentUriFromLuid(context, "cirrus", str);
            Playlists.append(context).withId(str).withTrackAsins(list).submit();
            subscriber.onNext(contentUriFromLuid);
            subscriber.onCompleted();
        } catch (PlaylistException e) {
            Log.warning(TAG, String.format("Failed to add tracks [%s] to playlist %s", StringUtils.join((Iterable<?>) list, ','), str), e);
            subscriber.onError(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$saveTracksToPlaylist$1(Context context, List list, Uri uri) {
        for (int i = 0; i < 2; i++) {
            SyncService.waitForSync(context, 19);
            if (PlaylistUtil.createPlaylistInfo(context, uri, CirrusMediaSource.match(uri)).mTrackCount == list.size()) {
                break;
            }
        }
        PlaylistUtil.refreshPlaylistArt(uri);
    }
}
