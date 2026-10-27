package com.amazon.mp3.detailpages.playlist.actions;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.activity.NetworkErrorDialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.playback.PlayStateMutationReason;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.UniqueCodeUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: DeleteUserPlaylistAction.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0018\u001a\u00020\u0017\u0012\u0006\u0010\u001a\u001a\u00020\u0011\u0012\u0006\u0010\u001c\u001a\u00020\t¢\u0006\u0004\b\u001e\u0010\u001fJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0002J\u0018\u0010\b\u001a\u00020\u00042\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0006H\u0002J0\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t2\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0006H\u0002J\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0002J\u0018\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J\u001a\u0010\u0016\u001a\u00020\u00042\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0006H\u0007R\u0014\u0010\u0018\u001a\u00020\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001d¨\u0006 "}, d2 = {"Lcom/amazon/mp3/detailpages/playlist/actions/DeleteUserPlaylistAction;", "", "", "shouldShowNetworkErrorDialog", "", "showNetworkErrorDialog", "Lkotlin/Function0;", "onDeletePlaylistSucceeded", "showDeletePlaylistDialog", "", "successMsg", "failureMessage", "toastSuccessMessage", "Landroid/content/DialogInterface$OnClickListener;", "positiveButtonClickListener", "Landroid/content/Context;", "context", "Landroid/net/Uri;", "playlistUri", "Lrx/Observable;", "deletePlaylistObservable", "deletePlaylist", "perform", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "uri", "Landroid/net/Uri;", "playlistName", "Ljava/lang/String;", "<init>", "(Landroidx/fragment/app/FragmentActivity;Landroid/net/Uri;Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class DeleteUserPlaylistAction {
    private final FragmentActivity activity;
    private final String playlistName;
    private final Uri uri;

    @JvmOverloads
    public final void perform() {
        perform$default(this, null, 1, null);
    }

    public DeleteUserPlaylistAction(FragmentActivity activity, Uri uri, String playlistName) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(playlistName, "playlistName");
        this.activity = activity;
        this.uri = uri;
        this.playlistName = playlistName;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void perform$default(DeleteUserPlaylistAction deleteUserPlaylistAction, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = null;
        }
        deleteUserPlaylistAction.perform(function0);
    }

    @JvmOverloads
    public final void perform(Function0<Unit> onDeletePlaylistSucceeded) {
        if (shouldShowNetworkErrorDialog()) {
            showNetworkErrorDialog();
        } else {
            showDeletePlaylistDialog(onDeletePlaylistSucceeded);
        }
    }

    private final boolean shouldShowNetworkErrorDialog() {
        return !ConnectivityUtil.hasAnyInternetConnection() && Intrinsics.areEqual("cirrus", MediaProvider.getSource(this.uri));
    }

    private final void showNetworkErrorDialog() {
        FragmentActivity fragmentActivity = this.activity;
        fragmentActivity.startActivity(NetworkErrorDialogActivity.getStartIntent(fragmentActivity));
    }

    private final void showDeletePlaylistDialog(Function0<Unit> onDeletePlaylistSucceeded) {
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(this.activity);
        boolean zMatchCloud = CirrusMediaSource.matchCloud(this.uri);
        String string = this.activity.getString(zMatchCloud ? R.string.dmusic_playlist_toast_deleting_playlist_cloud : R.string.dmusic_playlist_toast_deleting_playlist_device);
        Intrinsics.checkNotNullExpressionValue(string, "if (isRemote) activity.g…deleting_playlist_device)");
        String string2 = this.activity.getString(zMatchCloud ? R.string.playlist_removed_content_description : R.string.playlist_removed_from_device_content_description);
        Intrinsics.checkNotNullExpressionValue(string2, "activity.getString(if (i…vice_content_description)");
        String string3 = this.activity.getString(R.string.removal_fail_content_description);
        Intrinsics.checkNotNullExpressionValue(string3, "activity.getString(R.str…fail_content_description)");
        bauhausDialogBuilder.setTitle(zMatchCloud ? R.string.dmusic_playlist_confirm_delete_title : R.string.dmusic_playlist_context_remove_download);
        bauhausDialogBuilder.setPositiveButton(zMatchCloud ? R.string.dmusic_button_delete : R.string.dmusic_button_remove, positiveButtonClickListener(string2, string3, string, onDeletePlaylistSucceeded));
        bauhausDialogBuilder.setNegativeButton(R.string.dmusic_button_cancel, (DialogInterface.OnClickListener) null);
        bauhausDialogBuilder.setMessage(this.activity.getString(zMatchCloud ? R.string.dmusic_prime_playlist_delete_msg_cloud : R.string.dmusic_offline_prime_playlist_delete_msg_device, this.playlistName));
        Dialog dialogCreate = bauhausDialogBuilder.create();
        FragmentActivity fragmentActivity = this.activity;
        BaseActivity baseActivity = fragmentActivity instanceof BaseActivity ? (BaseActivity) fragmentActivity : null;
        if (baseActivity == null) {
            return;
        }
        baseActivity.showFragmentDialog(UniqueCodeUtil.nextUniqueCode(), dialogCreate);
    }

    private final DialogInterface.OnClickListener positiveButtonClickListener(String successMsg, final String failureMessage, final String toastSuccessMessage, final Function0<Unit> onDeletePlaylistSucceeded) {
        return new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.detailpages.playlist.actions.DeleteUserPlaylistAction$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DeleteUserPlaylistAction.m866positiveButtonClickListener$lambda1(this.f$0, toastSuccessMessage, onDeletePlaylistSucceeded, failureMessage, dialogInterface, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: positiveButtonClickListener$lambda-1, reason: not valid java name */
    public static final void m866positiveButtonClickListener$lambda1(final DeleteUserPlaylistAction this$0, final String toastSuccessMessage, final Function0 function0, final String failureMessage, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(toastSuccessMessage, "$toastSuccessMessage");
        Intrinsics.checkNotNullParameter(failureMessage, "$failureMessage");
        Context baseContext = this$0.activity.getBaseContext();
        Intrinsics.checkNotNullExpressionValue(baseContext, "activity.baseContext");
        this$0.deletePlaylistObservable(baseContext, this$0.uri).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.detailpages.playlist.actions.DeleteUserPlaylistAction$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DeleteUserPlaylistAction.m867positiveButtonClickListener$lambda1$lambda0(this.f$0, toastSuccessMessage, function0, failureMessage, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: positiveButtonClickListener$lambda-1$lambda-0, reason: not valid java name */
    public static final void m867positiveButtonClickListener$lambda1$lambda0(DeleteUserPlaylistAction this$0, String toastSuccessMessage, Function0 function0, String failureMessage, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(toastSuccessMessage, "$toastSuccessMessage");
        Intrinsics.checkNotNullParameter(failureMessage, "$failureMessage");
        if (Intrinsics.areEqual(bool, Boolean.TRUE)) {
            BauhausToastUtils.showTextToast(this$0.activity.getBaseContext(), toastSuccessMessage, 0);
            if (function0 == null) {
                return;
            }
            function0.invoke();
            return;
        }
        BauhausToastUtils.showTextToast(this$0.activity.getBaseContext(), failureMessage, 0);
    }

    private final Observable<Boolean> deletePlaylistObservable(final Context context, final Uri playlistUri) {
        Observable<Boolean> observableOnErrorReturn = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.detailpages.playlist.actions.DeleteUserPlaylistAction$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DeleteUserPlaylistAction.m864deletePlaylistObservable$lambda2(this.f$0, playlistUri, context, (Subscriber) obj);
            }
        }).onErrorReturn(new Func1() { // from class: com.amazon.mp3.detailpages.playlist.actions.DeleteUserPlaylistAction$$ExternalSyntheticLambda3
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return DeleteUserPlaylistAction.m865deletePlaylistObservable$lambda3((Throwable) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableOnErrorReturn, "create<Boolean> {\n      …rrorReturn { _ -> false }");
        return observableOnErrorReturn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: deletePlaylistObservable$lambda-2, reason: not valid java name */
    public static final void m864deletePlaylistObservable$lambda2(DeleteUserPlaylistAction this$0, Uri playlistUri, Context context, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(playlistUri, "$playlistUri");
        Intrinsics.checkNotNullParameter(context, "$context");
        subscriber.onNext(Boolean.valueOf(this$0.deletePlaylist(playlistUri, context)));
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: deletePlaylistObservable$lambda-3, reason: not valid java name */
    public static final Boolean m865deletePlaylistObservable$lambda3(Throwable th) {
        return Boolean.FALSE;
    }

    private final boolean deletePlaylist(Uri playlistUri, Context context) {
        Uri uriAppendTracksIfNeeded = ContentPlaybackUtils.INSTANCE.appendTracksIfNeeded(playlistUri);
        NowPlayingUtil.clearCollection(context, uriAppendTracksIfNeeded, PlayStateMutationReason.DELETING_TRACKS);
        MusicDownloader.getInstance(context).cancel(String.valueOf(uriAppendTracksIfNeeded));
        return context.getContentResolver().delete(playlistUri, null, null) != 0;
    }
}
