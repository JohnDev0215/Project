package com.amazon.mp3.detailpages.playlist.actions;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.dialog.DeletePlaylistUtil;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.playback.PlayStateMutationReason;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playlist.api.MC2PlaylistApi;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.util.AccessibilityUtil;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.Log;
import com.amazon.music.curate.data.PlaylistIntentReceiverService;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: PlaylistUnfollowAction.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005H\u0002J\b\u0010\u000f\u001a\u00020\u000bH\u0002J\u0006\u0010\u0010\u001a\u00020\bJ\b\u0010\u0011\u001a\u00020\bH\u0002J \u0010\u0012\u001a\u00020\u000b2\u0016\b\u0002\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0014H\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/detailpages/playlist/actions/PlaylistUnfollowAction;", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "playlistAsin", "", "playlistLuid", "isRemote", "", "(Landroidx/fragment/app/FragmentActivity;Ljava/lang/String;Ljava/lang/String;Z)V", "announceDeleteJobFinished", "", "isSucceed", "successMsg", "failMsg", "removeCatalogPlaylist", "unfollowOfflinePlaylist", "unfollowOnlinePlaylist", "unfollowPlaylist", "onFinished", "Lkotlin/Function1;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistUnfollowAction {
    private static final String TAG = Reflection.getOrCreateKotlinClass(PlaylistUnfollowAction.class).getSimpleName();
    private final FragmentActivity activity;
    private final boolean isRemote;
    private final String playlistAsin;
    private final String playlistLuid;

    @JvmOverloads
    public final void unfollowPlaylist() {
        unfollowPlaylist$default(this, null, 1, null);
    }

    public PlaylistUnfollowAction(FragmentActivity activity, String str, String str2, boolean z) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
        this.playlistAsin = str;
        this.playlistLuid = str2;
        this.isRemote = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void unfollowPlaylist$default(PlaylistUnfollowAction playlistUnfollowAction, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = null;
        }
        playlistUnfollowAction.unfollowPlaylist(function1);
    }

    @JvmOverloads
    public final void unfollowPlaylist(final Function1<? super Boolean, Unit> onFinished) {
        Resources resources = this.activity.getResources();
        final String string = resources.getString(this.isRemote ? R.string.playlist_removed_content_description : R.string.playlist_removed_from_device_content_description);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(if (…vice_content_description)");
        final String string2 = resources.getString(R.string.removal_fail_content_description);
        Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.st…fail_content_description)");
        DeletePlaylistUtil.createBauhausPrimeDialog(this.activity, this.isRemote, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.detailpages.playlist.actions.PlaylistUnfollowAction$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                PlaylistUnfollowAction.m892unfollowPlaylist$lambda3(this.f$0, string, string2, onFinished, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: unfollowPlaylist$lambda-3, reason: not valid java name */
    public static final void m892unfollowPlaylist$lambda3(final PlaylistUnfollowAction this$0, final String a11ySuccessMsg, final String a11yFailMsg, final Function1 function1, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(a11ySuccessMsg, "$a11ySuccessMsg");
        Intrinsics.checkNotNullParameter(a11yFailMsg, "$a11yFailMsg");
        BauhausToastUtils.showTextToast(this$0.activity, R.string.dmusic_stop_following_toast_message, 1);
        Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.detailpages.playlist.actions.PlaylistUnfollowAction$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlaylistUnfollowAction.m893unfollowPlaylist$lambda3$lambda0(this.f$0, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.detailpages.playlist.actions.PlaylistUnfollowAction$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlaylistUnfollowAction.m894unfollowPlaylist$lambda3$lambda1(this.f$0, a11ySuccessMsg, a11yFailMsg, function1, (Boolean) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.detailpages.playlist.actions.PlaylistUnfollowAction$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlaylistUnfollowAction.m895unfollowPlaylist$lambda3$lambda2(function1, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: unfollowPlaylist$lambda-3$lambda-0, reason: not valid java name */
    public static final void m893unfollowPlaylist$lambda3$lambda0(PlaylistUnfollowAction this$0, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        subscriber.onNext(Boolean.valueOf(this$0.isRemote ? this$0.unfollowOnlinePlaylist() : this$0.unfollowOfflinePlaylist()));
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: unfollowPlaylist$lambda-3$lambda-1, reason: not valid java name */
    public static final void m894unfollowPlaylist$lambda3$lambda1(PlaylistUnfollowAction this$0, String a11ySuccessMsg, String a11yFailMsg, Function1 function1, Boolean result) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(a11ySuccessMsg, "$a11ySuccessMsg");
        Intrinsics.checkNotNullParameter(a11yFailMsg, "$a11yFailMsg");
        Intrinsics.checkNotNullExpressionValue(result, "result");
        if (result.booleanValue()) {
            if (this$0.isRemote) {
                this$0.removeCatalogPlaylist();
            }
            this$0.announceDeleteJobFinished(result.booleanValue(), a11ySuccessMsg, a11yFailMsg);
            if (function1 == null) {
                return;
            }
            function1.invoke(Boolean.TRUE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: unfollowPlaylist$lambda-3$lambda-2, reason: not valid java name */
    public static final void m895unfollowPlaylist$lambda3$lambda2(Function1 function1, Throwable th) {
        Log.error(TAG, Intrinsics.stringPlus("An error happened when unfollowing a playlist, exception message: ", th.getMessage()));
        if (function1 == null) {
            return;
        }
        function1.invoke(Boolean.FALSE);
    }

    private final boolean unfollowOnlinePlaylist() throws ServiceException, AbstractHttpClient.HttpClientException {
        ContentResolver contentResolver;
        String str = this.playlistLuid;
        String str2 = this.playlistAsin;
        Context applicationContext = this.activity.getApplicationContext();
        if (!TextUtils.isEmpty(str2)) {
            if (!TextUtils.isEmpty(str) ? new MC2PlaylistApi().deletePlaylist(applicationContext, str) : true) {
                new PrimePlaylistDatabaseManager(applicationContext).unfollow(str2);
                PrimePlaylistUtil.cancelPrimePlaylistDownload(applicationContext, str2);
                PrimePlaylistUtil.deleteOfflinePrimePlaylist(applicationContext, Intrinsics.stringPlus("localPrimePlaylist-", str));
                SettingsUtil.setHasFollowedPrimePlaylists(applicationContext, new PrimePlaylistDatabaseManager().hasPrimePlaylists("cirrus"));
                if (applicationContext != null && (contentResolver = applicationContext.getContentResolver()) != null) {
                    contentResolver.notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
                }
                return true;
            }
            Log.error(TAG, "Error unfollowing playlist (API call)");
            return false;
        }
        throw new IllegalStateException("prime playlist asin is null or empty!");
    }

    public final boolean unfollowOfflinePlaylist() throws IllegalStateException {
        NowPlayingUtil.clearCollection(AmazonApplication.getContext(), MediaProvider.PrimePlaylists.getContentUri("cirrus-local", this.playlistAsin), PlayStateMutationReason.DELETING_TRACKS);
        PrimePlaylistUtil.deleteOfflinePrimePlaylist(this.activity.getApplicationContext(), this.playlistLuid);
        return true;
    }

    private final void announceDeleteJobFinished(boolean isSucceed, String successMsg, String failMsg) {
        if (isSucceed) {
            AccessibilityUtil.announceForAccessibility(this.activity, successMsg);
        } else {
            AccessibilityUtil.announceForAccessibility(this.activity, failMsg);
        }
    }

    private final void removeCatalogPlaylist() {
        final Context applicationContext = this.activity.getApplicationContext();
        if (applicationContext == null) {
            return;
        }
        Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.detailpages.playlist.actions.PlaylistUnfollowAction$$ExternalSyntheticLambda4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlaylistUnfollowAction.m890removeCatalogPlaylist$lambda4(this.f$0, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.detailpages.playlist.actions.PlaylistUnfollowAction$$ExternalSyntheticLambda5
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlaylistUnfollowAction.m891removeCatalogPlaylist$lambda6(this.f$0, applicationContext, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: removeCatalogPlaylist$lambda-4, reason: not valid java name */
    public static final void m890removeCatalogPlaylist$lambda4(PlaylistUnfollowAction this$0, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(subscriber, "subscriber");
        subscriber.onNext(this$0.playlistAsin);
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: removeCatalogPlaylist$lambda-6, reason: not valid java name */
    public static final void m891removeCatalogPlaylist$lambda6(PlaylistUnfollowAction this$0, Context context, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        String str2 = this$0.playlistAsin;
        if (str2 == null) {
            return;
        }
        PlaylistIntentReceiverService.INSTANCE.processRemoveCatalogPlaylist(context, str2);
    }
}
