package com.amazon.mp3.detailpages.playlist.actions;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusPlaylistScratch;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.music.curate.data.PlaylistIntentReceiverService;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: PlaylistReorderingAction.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J$\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/detailpages/playlist/actions/PlaylistReorderingAction;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "isFirstTimeReordering", "", "reorderPlaylist", "Lio/reactivex/Observable;", "", "playlistUri", "Landroid/net/Uri;", "from", "", "to", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistReorderingAction {
    private static final String TAG = Reflection.getOrCreateKotlinClass(PlaylistReorderingAction.class).getSimpleName();
    private final Context context;
    private boolean isFirstTimeReordering;

    public PlaylistReorderingAction(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.isFirstTimeReordering = true;
    }

    public final Observable<String> reorderPlaylist(final Uri playlistUri, final int from, final int to) {
        Intrinsics.checkNotNullParameter(playlistUri, "playlistUri");
        if (this.isFirstTimeReordering) {
            CirrusPlaylistScratch.create(this.context, PlaylistUtil.getPlaylistId(playlistUri));
            this.isFirstTimeReordering = false;
        }
        final String source = MediaProvider.getSource(playlistUri);
        final long playlistId = PlaylistUtil.getPlaylistId(playlistUri);
        Observable<String> observableSubscribeOn = Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.mp3.detailpages.playlist.actions.PlaylistReorderingAction$$ExternalSyntheticLambda0
            @Override // io.reactivex.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) throws ServiceException, AbstractHttpClient.HttpClientException {
                PlaylistReorderingAction.m882reorderPlaylist$lambda0(this.f$0, playlistUri, from, to, source, playlistId, observableEmitter);
            }
        }).observeOn(AndroidSchedulers.mainThread()).doOnNext(new Consumer() { // from class: com.amazon.mp3.detailpages.playlist.actions.PlaylistReorderingAction$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                PlaylistReorderingAction.m883reorderPlaylist$lambda1(this.f$0, (String) obj);
            }
        }).doOnComplete(new Action() { // from class: com.amazon.mp3.detailpages.playlist.actions.PlaylistReorderingAction$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Action
            public final void run() {
                PlaylistReorderingAction.m884reorderPlaylist$lambda2(this.f$0);
            }
        }).subscribeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(observableSubscribeOn, "create<String> {\n       …scribeOn(Schedulers.io())");
        return observableSubscribeOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: reorderPlaylist$lambda-0, reason: not valid java name */
    public static final void m882reorderPlaylist$lambda0(PlaylistReorderingAction this$0, Uri playlistUri, int i, int i2, String str, long j, ObservableEmitter it) throws ServiceException, AbstractHttpClient.HttpClientException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(playlistUri, "$playlistUri");
        Intrinsics.checkNotNullParameter(it, "it");
        if (!PlaylistUtil.moveUdoPlaylistTrack(this$0.context, playlistUri, i, i2)) {
            it.onError(new IllegalStateException("move Udo playlist track failed for the playlist: " + playlistUri + " from " + i + " to " + i2));
        } else {
            PlaylistUtil.setMutatedFlag(this$0.context);
            CirrusPlaylistScratch.post(this$0.context, str, j);
            String playlistLuid = PlaylistUtil.getPlaylistLuid(this$0.context, playlistUri);
            if (playlistLuid != null) {
                it.onNext(playlistLuid);
            } else {
                it.onError(new IllegalStateException(Intrinsics.stringPlus("The luid is null for the playlist: ", playlistUri)));
            }
        }
        it.onComplete();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: reorderPlaylist$lambda-1, reason: not valid java name */
    public static final void m883reorderPlaylist$lambda1(PlaylistReorderingAction this$0, String it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PlaylistIntentReceiverService.Companion companion = PlaylistIntentReceiverService.INSTANCE;
        Context context = this$0.context;
        Intrinsics.checkNotNullExpressionValue(it, "it");
        companion.processUpdatePlaylist(context, it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: reorderPlaylist$lambda-2, reason: not valid java name */
    public static final void m884reorderPlaylist$lambda2(PlaylistReorderingAction this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PlaylistUtil.clearMutatedFlag(this$0.context);
    }
}
