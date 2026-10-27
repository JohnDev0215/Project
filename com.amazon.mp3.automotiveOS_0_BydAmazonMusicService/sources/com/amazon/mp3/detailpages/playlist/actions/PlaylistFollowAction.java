package com.amazon.mp3.detailpages.playlist.actions;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.dialog.NetworkErrorDialog;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.playlist.api.MC2PlaylistApi;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.curate.data.PlaylistIntentReceiverService;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: PlaylistFollowAction.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u001f2\u00020\u0001:\u0002 \u001fB\u001b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\b\u0010\t\u001a\u00020\u0004H\u0002J\u0012\u0010\n\u001a\u00020\u00042\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002J\u0006\u0010\f\u001a\u00020\u000bR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R$\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u001a8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u001c¨\u0006!"}, d2 = {"Lcom/amazon/mp3/detailpages/playlist/actions/PlaylistFollowAction;", "", "", "asin", "", "followPrimePlaylist", "Lcom/amazon/mp3/library/dialog/NetworkErrorDialog$INetworkErrorDialogHandler;", "handler", "showNetworkErrorDialog", "showErrorToast", "followPlaylist", "", "canFollowPlaylist", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/amazon/music/views/library/metadata/PlaylistMetadata;", "metadata", "Lcom/amazon/music/views/library/metadata/PlaylistMetadata;", "Lcom/amazon/mp3/detailpages/playlist/actions/PlaylistFollowAction$Callback;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/amazon/mp3/detailpages/playlist/actions/PlaylistFollowAction$Callback;", "getListener", "()Lcom/amazon/mp3/detailpages/playlist/actions/PlaylistFollowAction$Callback;", "setListener", "(Lcom/amazon/mp3/detailpages/playlist/actions/PlaylistFollowAction$Callback;)V", "Lio/reactivex/disposables/Disposable;", "disposable", "Lio/reactivex/disposables/Disposable;", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/metadata/PlaylistMetadata;)V", "Companion", "Callback", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PlaylistFollowAction {
    private static final String TAG = Reflection.getOrCreateKotlinClass(PlaylistFollowAction.class).getSimpleName();
    private final Context context;
    private Disposable disposable;
    private Callback listener;
    private final PlaylistMetadata metadata;

    /* JADX INFO: compiled from: PlaylistFollowAction.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005H&¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/detailpages/playlist/actions/PlaylistFollowAction$Callback;", "", "onFollowPlaylistFailed", "", "message", "", "onFollowPlaylistFinished", "luid", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface Callback {
        void onFollowPlaylistFailed(String message);

        void onFollowPlaylistFinished(String luid);
    }

    public PlaylistFollowAction(Context context, PlaylistMetadata playlistMetadata) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.metadata = playlistMetadata;
    }

    public final void setListener(Callback callback) {
        this.listener = callback;
    }

    public static /* synthetic */ void followPlaylist$default(PlaylistFollowAction playlistFollowAction, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            PlaylistMetadata playlistMetadata = playlistFollowAction.metadata;
            str = playlistMetadata == null ? null : playlistMetadata.getAsin();
        }
        playlistFollowAction.followPlaylist(str);
    }

    public final void followPlaylist(final String asin) {
        if (!ConnectivityUtil.hasAnyInternetConnection(this.context)) {
            showNetworkErrorDialog(new NetworkErrorDialog.INetworkErrorDialogHandler() { // from class: com.amazon.mp3.detailpages.playlist.actions.PlaylistFollowAction$followPlaylist$handler$1
                @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
                public void onConnectionErrorDialogCancel() {
                }

                @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
                public void onDialogDismiss() {
                }

                @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
                public void onRetryConnection() {
                    if (!ConnectivityUtil.hasAnyInternetConnection(this.this$0.context)) {
                        NetworkErrorDialog.create(this.this$0.context, this).show();
                    } else {
                        this.this$0.followPlaylist(asin);
                    }
                }
            });
            return;
        }
        String string = this.context.getString(R.string.dmusic_start_following_toast_message);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…_following_toast_message)");
        BauhausToastUtils.showTextToast(this.context, string, 0);
        if (asin == null) {
            return;
        }
        followPrimePlaylist(asin);
        PlaylistIntentReceiverService.INSTANCE.processUpdateCatalogPlaylist(this.context, asin);
    }

    public final boolean canFollowPlaylist() {
        if (this.disposable != null) {
            return false;
        }
        PlaylistMetadata playlistMetadata = this.metadata;
        return !(playlistMetadata == null ? false : Intrinsics.areEqual(playlistMetadata.getIsFollowed(), Boolean.TRUE));
    }

    private final void followPrimePlaylist(final String asin) {
        this.disposable = Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.mp3.detailpages.playlist.actions.PlaylistFollowAction$$ExternalSyntheticLambda0
            @Override // io.reactivex.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
                PlaylistFollowAction.m875followPrimePlaylist$lambda1(asin, this, observableEmitter);
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.mp3.detailpages.playlist.actions.PlaylistFollowAction$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                PlaylistFollowAction.m876followPrimePlaylist$lambda2(this.f$0, (String) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.detailpages.playlist.actions.PlaylistFollowAction$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                PlaylistFollowAction.m877followPrimePlaylist$lambda3(this.f$0, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: followPrimePlaylist$lambda-1, reason: not valid java name */
    public static final void m875followPrimePlaylist$lambda1(String asin, PlaylistFollowAction this$0, ObservableEmitter it) throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        Intrinsics.checkNotNullParameter(asin, "$asin");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        if (TextUtils.isEmpty(asin)) {
            it.onError(new IllegalStateException("prime playlist asin not available!"));
            return;
        }
        String strFollowPlaylistByAsin = new MC2PlaylistApi().followPlaylistByAsin(this$0.context, asin);
        if (strFollowPlaylistByAsin == null) {
            it.onError(new IllegalStateException("prime playlist luid is null"));
        } else {
            new PrimePlaylistDatabaseManager(this$0.context).follow(asin, strFollowPlaylistByAsin);
            SettingsUtil.setHasFollowedPrimePlaylists(this$0.context, true);
            this$0.context.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
            it.onNext(strFollowPlaylistByAsin);
        }
        it.onComplete();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: followPrimePlaylist$lambda-2, reason: not valid java name */
    public static final void m876followPrimePlaylist$lambda2(PlaylistFollowAction this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PlaylistMetadata playlistMetadata = this$0.metadata;
        if (playlistMetadata != null) {
            playlistMetadata.setLuid(str);
        }
        PlaylistMetadata playlistMetadata2 = this$0.metadata;
        if (playlistMetadata2 != null) {
            playlistMetadata2.setFollowed(Boolean.TRUE);
        }
        Callback callback = this$0.listener;
        if (callback != null) {
            callback.onFollowPlaylistFinished(str);
        }
        this$0.disposable = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: followPrimePlaylist$lambda-3, reason: not valid java name */
    public static final void m877followPrimePlaylist$lambda3(PlaylistFollowAction this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(TAG, Intrinsics.stringPlus("Error following a prime playlist, error message = ", th.getMessage()));
        Callback callback = this$0.listener;
        if (callback != null) {
            callback.onFollowPlaylistFailed(th.getMessage());
        }
        this$0.showErrorToast();
        this$0.disposable = null;
    }

    private final void showNetworkErrorDialog(NetworkErrorDialog.INetworkErrorDialogHandler handler) {
        NetworkErrorDialog.create(this.context, handler).show();
    }

    private final void showErrorToast() {
        final String string = this.context.getString(R.string.dmusic_unable_to_add_prime_playlist);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…le_to_add_prime_playlist)");
        AmazonApplication.getDefaultHandler().post(new Runnable() { // from class: com.amazon.mp3.detailpages.playlist.actions.PlaylistFollowAction$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                PlaylistFollowAction.m878showErrorToast$lambda4(this.f$0, string);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showErrorToast$lambda-4, reason: not valid java name */
    public static final void m878showErrorToast$lambda4(PlaylistFollowAction this$0, String addPrimePlaylist) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(addPrimePlaylist, "$addPrimePlaylist");
        BauhausToastUtils.showDefaultToast(this$0.context, addPrimePlaylist, 0);
    }
}
