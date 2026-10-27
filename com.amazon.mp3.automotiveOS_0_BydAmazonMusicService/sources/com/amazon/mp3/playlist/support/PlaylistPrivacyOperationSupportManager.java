package com.amazon.mp3.playlist.support;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwnerKt;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.customerprofile.CustomerProfileDialogFragment;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.LocalRepositoryEvents;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.PlaylistUpdateEvent;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.PlaylistUpdateEventType;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.net.playlist.MusicPlaylistExceptions;
import com.amazon.mp3.playlist.api.PlaylistFactory;
import com.amazon.mp3.playlist.operation.CatalogPlaylistShareMetadata;
import com.amazon.mp3.playlist.operation.PlaylistPrivacyMetadata;
import com.amazon.mp3.playlist.operation.PlaylistPrivacyOperation;
import com.amazon.mp3.playlist.operation.PlaylistShareMetadata;
import com.amazon.mp3.playlist.operation.PlaylistTrackLimitValidationException;
import com.amazon.mp3.playlist.operation.TitleLengthValidationException;
import com.amazon.mp3.playlist.operation.UserPlaylistShareMetadata;
import com.amazon.mp3.sharing.ShareProviderActionHandler;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.music.share.PlaylistShareProvider;
import com.amazon.music.share.ShareProvider;
import com.amazon.music.share.UserPlaylistShareProvider;
import com.amazon.music.views.library.views.Orientation;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: PlaylistPrivacyOperationSupportManager.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 (2\u00020\u0001:\u0001(B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J#\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J#\u0010\u0014\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J!\u0010\u0018\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ\u000e\u0010\u001c\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u001dJ\u0010\u0010\u001e\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J \u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010\"\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0018\u0010#\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020$H\u0002J\u0010\u0010%\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u001dH\u0002J\u0018\u0010&\u001a\u00020\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0019\u001a\u00020\u001aJ\u0016\u0010&\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001aJ\u0018\u0010'\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006)"}, d2 = {"Lcom/amazon/mp3/playlist/support/PlaylistPrivacyOperationSupportManager;", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "weakActivity", "Ljava/lang/ref/WeakReference;", "checkConnectivityAndShowDialog", "", "confirmPlaylistPrivateActionDialog", "", "playlistMetadata", "Lcom/amazon/mp3/playlist/operation/PlaylistPrivacyMetadata;", "getPlaylistVersion", "", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "(Landroid/content/Context;Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hasUdoPlaylistWithMinFollowerCountToShowMakePrivateDialog", "playlistId", "", "(Landroid/content/Context;Ljava/lang/Long;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setPlaylistVisibility", "state", "Lcom/amazon/mp3/library/provider/MediaProvider$Playlists$VisibilityState;", "(Lcom/amazon/mp3/playlist/operation/PlaylistPrivacyMetadata;Lcom/amazon/mp3/library/provider/MediaProvider$Playlists$VisibilityState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sharePlaylist", "Lcom/amazon/mp3/playlist/operation/PlaylistShareMetadata;", "showGenericErrorDialog", "titleId", "", "messageId", "showHiddenProfileExceptionDialog", "showPlaylistIsPrivateDialog", "Lcom/amazon/mp3/playlist/operation/UserPlaylistShareMetadata;", "startPlaylistShare", "updatePlaylistVisibilityWithConfirmationDialogIfNeeded", "updatePlaylistVisibilityWithProgressDialog", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistPrivacyOperationSupportManager {
    private final WeakReference<FragmentActivity> weakActivity;

    /* JADX INFO: renamed from: com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager$getPlaylistVersion$1, reason: invalid class name */
    /* JADX INFO: compiled from: PlaylistPrivacyOperationSupportManager.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager", f = "PlaylistPrivacyOperationSupportManager.kt", i = {}, l = {324}, m = "getPlaylistVersion", n = {}, s = {})
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PlaylistPrivacyOperationSupportManager.this.getPlaylistVersion(null, null, this);
        }
    }

    public PlaylistPrivacyOperationSupportManager(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.weakActivity = new WeakReference<>(activity);
    }

    public final void sharePlaylist(PlaylistShareMetadata playlistMetadata) {
        Intrinsics.checkNotNullParameter(playlistMetadata, "playlistMetadata");
        if (checkConnectivityAndShowDialog()) {
            if (playlistMetadata instanceof CatalogPlaylistShareMetadata) {
                startPlaylistShare(playlistMetadata);
                return;
            }
            if (playlistMetadata instanceof UserPlaylistShareMetadata) {
                UserPlaylistShareMetadata userPlaylistShareMetadata = (UserPlaylistShareMetadata) playlistMetadata;
                if (userPlaylistShareMetadata.getVisibility() == MediaProvider.Playlists.VisibilityState.PRIVATE && Feature.playlists_privacy_enforcement.isEnabled()) {
                    FragmentActivity fragmentActivity = this.weakActivity.get();
                    if (fragmentActivity == null) {
                        return;
                    }
                    showPlaylistIsPrivateDialog(fragmentActivity, userPlaylistShareMetadata);
                    return;
                }
                startPlaylistShare(playlistMetadata);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startPlaylistShare(PlaylistShareMetadata playlistMetadata) {
        FragmentActivity fragmentActivity;
        ShareProvider playlistShareProvider;
        if (checkConnectivityAndShowDialog() && (fragmentActivity = this.weakActivity.get()) != null) {
            String musicDomain = EndpointsProvider.get().getMusicDomain();
            if (playlistMetadata instanceof UserPlaylistShareMetadata) {
                playlistShareProvider = new UserPlaylistShareProvider(fragmentActivity, musicDomain, ((UserPlaylistShareMetadata) playlistMetadata).getLuid(), playlistMetadata.getTitle(), ((UserPlaylistShareMetadata) playlistMetadata).getVersion(), PlaylistFactory.createPlaylistLibrary(fragmentActivity), ShareProviderActionHandler.newInstance(), playlistMetadata.getArtworkUrl());
            } else {
                playlistShareProvider = new PlaylistShareProvider(fragmentActivity, musicDomain, playlistMetadata.getAsin(), playlistMetadata.getTitle(), playlistMetadata.getArtworkUrl());
            }
            playlistShareProvider.startShare();
        }
    }

    public final void updatePlaylistVisibilityWithConfirmationDialogIfNeeded(Uri uri, MediaProvider.Playlists.VisibilityState state) {
        FragmentActivity fragmentActivity;
        Intrinsics.checkNotNullParameter(state, "state");
        if (checkConnectivityAndShowDialog() && (fragmentActivity = this.weakActivity.get()) != null) {
            LifecycleOwnerKt.getLifecycleScope(fragmentActivity).launchWhenStarted(new PlaylistPrivacyOperationSupportManager$updatePlaylistVisibilityWithConfirmationDialogIfNeeded$1$1(this, uri, state, fragmentActivity, null));
        }
    }

    public final void updatePlaylistVisibilityWithConfirmationDialogIfNeeded(PlaylistPrivacyMetadata playlistMetadata, MediaProvider.Playlists.VisibilityState state) {
        FragmentActivity fragmentActivity;
        Intrinsics.checkNotNullParameter(playlistMetadata, "playlistMetadata");
        Intrinsics.checkNotNullParameter(state, "state");
        if (checkConnectivityAndShowDialog() && (fragmentActivity = this.weakActivity.get()) != null) {
            LifecycleOwnerKt.getLifecycleScope(fragmentActivity).launchWhenStarted(new PlaylistPrivacyOperationSupportManager$updatePlaylistVisibilityWithConfirmationDialogIfNeeded$2$1(state, this, fragmentActivity, playlistMetadata, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updatePlaylistVisibilityWithProgressDialog(PlaylistPrivacyMetadata playlistMetadata, MediaProvider.Playlists.VisibilityState state) {
        FragmentActivity fragmentActivity = this.weakActivity.get();
        if (fragmentActivity == null) {
            return;
        }
        LifecycleOwnerKt.getLifecycleScope(fragmentActivity).launchWhenStarted(new PlaylistPrivacyOperationSupportManager$updatePlaylistVisibilityWithProgressDialog$1$1(fragmentActivity, this, playlistMetadata, state, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkConnectivityAndShowDialog() {
        FragmentActivity fragmentActivity = this.weakActivity.get();
        if (fragmentActivity == null) {
            return false;
        }
        return ConnectivityUtil.checkConnectivityAndShowDialog(fragmentActivity);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager$setPlaylistVisibility$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlaylistPrivacyOperationSupportManager.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager$setPlaylistVisibility$2", f = "PlaylistPrivacyOperationSupportManager.kt", i = {0}, l = {186}, m = "invokeSuspend", n = {"it"}, s = {"L$1"})
    static final class C04042 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        final /* synthetic */ PlaylistPrivacyMetadata $playlistMetadata;
        final /* synthetic */ MediaProvider.Playlists.VisibilityState $state;
        Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04042(PlaylistPrivacyMetadata playlistPrivacyMetadata, MediaProvider.Playlists.VisibilityState visibilityState, Continuation<? super C04042> continuation) {
            super(2, continuation);
            this.$playlistMetadata = playlistPrivacyMetadata;
            this.$state = visibilityState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PlaylistPrivacyOperationSupportManager.this.new C04042(this.$playlistMetadata, this.$state, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
            return ((C04042) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            FragmentActivity it;
            PlaylistPrivacyOperationSupportManager playlistPrivacyOperationSupportManager;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            boolean z = true;
            try {
            } catch (Exception e) {
                FragmentActivity fragmentActivity = (FragmentActivity) PlaylistPrivacyOperationSupportManager.this.weakActivity.get();
                if (fragmentActivity == null || fragmentActivity.isFinishing() || fragmentActivity.isDestroyed()) {
                    return Boxing.boxBoolean(false);
                }
                if (e instanceof TitleLengthValidationException) {
                    PlaylistPrivacyOperationSupportManager.this.showGenericErrorDialog(R.string.public_playlist_community_guidelines_dialog_title, R.string.public_playlist_community_guidelines_dialog_body, fragmentActivity);
                } else if (e instanceof PlaylistTrackLimitValidationException) {
                    PlaylistPrivacyOperationSupportManager.this.showGenericErrorDialog(R.string.public_playlist_track_limit_dialog_title, R.string.public_playlist_track_limit_dialog_body, fragmentActivity);
                } else if (e instanceof MusicPlaylistExceptions.HiddenProfileException) {
                    PlaylistPrivacyOperationSupportManager.this.showHiddenProfileExceptionDialog(fragmentActivity);
                } else if (e instanceof MusicPlaylistExceptions.TitleModerationDenialException) {
                    PlaylistPrivacyOperationSupportManager.this.showGenericErrorDialog(R.string.public_playlist_community_guidelines_dialog_title, R.string.public_playlist_community_guidelines_dialog_body, fragmentActivity);
                } else {
                    PlaylistPrivacyOperationSupportManager.this.showGenericErrorDialog(fragmentActivity);
                }
            }
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (!PlaylistPrivacyOperationSupportManager.this.checkConnectivityAndShowDialog()) {
                    return Boxing.boxBoolean(false);
                }
                FragmentActivity fragmentActivity2 = (FragmentActivity) PlaylistPrivacyOperationSupportManager.this.weakActivity.get();
                if (fragmentActivity2 != null) {
                    PlaylistPrivacyMetadata playlistPrivacyMetadata = this.$playlistMetadata;
                    MediaProvider.Playlists.VisibilityState visibilityState = this.$state;
                    PlaylistPrivacyOperationSupportManager playlistPrivacyOperationSupportManager2 = PlaylistPrivacyOperationSupportManager.this;
                    Context applicationContext = fragmentActivity2.getApplicationContext();
                    Intrinsics.checkNotNullExpressionValue(applicationContext, "it.applicationContext");
                    PlaylistPrivacyOperation playlistPrivacyOperation = new PlaylistPrivacyOperation(applicationContext, playlistPrivacyMetadata);
                    this.L$0 = playlistPrivacyOperationSupportManager2;
                    this.L$1 = fragmentActivity2;
                    this.label = 1;
                    Object objUpdatePlaylistVisibility = playlistPrivacyOperation.updatePlaylistVisibility(visibilityState, this);
                    if (objUpdatePlaylistVisibility == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    it = fragmentActivity2;
                    obj = objUpdatePlaylistVisibility;
                    playlistPrivacyOperationSupportManager = playlistPrivacyOperationSupportManager2;
                } else {
                    return Boxing.boxBoolean(false);
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                it = (FragmentActivity) this.L$1;
                playlistPrivacyOperationSupportManager = (PlaylistPrivacyOperationSupportManager) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            boolean zBooleanValue = ((Boolean) obj).booleanValue();
            if (zBooleanValue) {
                LocalRepositoryEvents.INSTANCE.sendEvent(new PlaylistUpdateEvent(PlaylistUpdateEventType.PRIVACY_STATUS_CHANGED));
            } else {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                playlistPrivacyOperationSupportManager.showGenericErrorDialog(it);
            }
            if (!zBooleanValue) {
                z = false;
            }
            return Boxing.boxBoolean(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object setPlaylistVisibility(PlaylistPrivacyMetadata playlistPrivacyMetadata, MediaProvider.Playlists.VisibilityState visibilityState, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getMain(), new C04042(playlistPrivacyMetadata, visibilityState, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showHiddenProfileExceptionDialog(FragmentActivity activity) {
        CustomerProfileDialogFragment customerProfileDialogFragmentCreateDialog = CustomerProfileDialogFragment.INSTANCE.createDialog(R.string.hidden_profile_make_playlist_public_dialog, R.string.hidden_profile_make_profile_public_button, "PUBLIC");
        FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "activity.supportFragmentManager");
        customerProfileDialogFragmentCreateDialog.show(supportFragmentManager, CustomerProfileDialogFragment.class.getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showGenericErrorDialog(FragmentActivity activity) {
        new BauhausDialogBuilder(activity).setTitle(R.string.dmusic_car_mode_time_out_error_title).setNeutralButton(R.string.public_playlist_track_limit_dialog_dismiss, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                PlaylistPrivacyOperationSupportManager.m1573showGenericErrorDialog$lambda6(dialogInterface, i);
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showGenericErrorDialog$lambda-6, reason: not valid java name */
    public static final void m1573showGenericErrorDialog$lambda6(DialogInterface dialog, int i) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showGenericErrorDialog(int titleId, int messageId, FragmentActivity activity) {
        new BauhausDialogBuilder(activity).setTitle(titleId).setMessage(messageId).setNeutralButton(R.string.public_playlist_track_limit_dialog_dismiss, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                PlaylistPrivacyOperationSupportManager.m1574showGenericErrorDialog$lambda7(dialogInterface, i);
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showGenericErrorDialog$lambda-7, reason: not valid java name */
    public static final void m1574showGenericErrorDialog$lambda7(DialogInterface dialog, int i) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }

    private final void showPlaylistIsPrivateDialog(final FragmentActivity activity, final UserPlaylistShareMetadata playlistMetadata) {
        new BauhausDialogBuilder(activity).setMessage(R.string.dmusic_playlist_privacy_share_description).setTitle(R.string.dmusic_playlist_privacy_share_title).setButtonOrientation(Orientation.VERTICAL).setTitleTextAlignment(4).setPositiveButton(R.string.dmusic_playlist_privacy_make_public, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager$$ExternalSyntheticLambda4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                PlaylistPrivacyOperationSupportManager.m1575showPlaylistIsPrivateDialog$lambda8(activity, this, playlistMetadata, dialogInterface, i);
            }
        }).setNegativeButton(R.string.dmusic_playlist_privacy_keep_private_text, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager$$ExternalSyntheticLambda5
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                PlaylistPrivacyOperationSupportManager.m1576showPlaylistIsPrivateDialog$lambda9(dialogInterface, i);
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showPlaylistIsPrivateDialog$lambda-8, reason: not valid java name */
    public static final void m1575showPlaylistIsPrivateDialog$lambda8(FragmentActivity activity, PlaylistPrivacyOperationSupportManager this$0, UserPlaylistShareMetadata playlistMetadata, DialogInterface dialog, int i) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(playlistMetadata, "$playlistMetadata");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
        LifecycleOwnerKt.getLifecycleScope(activity).launchWhenStarted(new PlaylistPrivacyOperationSupportManager$showPlaylistIsPrivateDialog$1$1(activity, this$0, playlistMetadata, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showPlaylistIsPrivateDialog$lambda-9, reason: not valid java name */
    public static final void m1576showPlaylistIsPrivateDialog$lambda9(DialogInterface dialog, int i) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
        PlaylistsPrivacyMetricsLogger.INSTANCE.sendShareDialogMetricEvent(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void confirmPlaylistPrivateActionDialog(final PlaylistPrivacyMetadata playlistMetadata) {
        FragmentActivity fragmentActivity = this.weakActivity.get();
        if (fragmentActivity == null) {
            return;
        }
        new BauhausDialogBuilder(fragmentActivity).setMessage(R.string.dmusic_playlist_privacy_make_playlist_private_dialog_description).setTitle(R.string.dmusic_playlist_privacy_make_playlist_private_dialog_title).setButtonOrientation(Orientation.VERTICAL).setTitleTextAlignment(4).setPositiveButton(R.string.dmusic_playlist_privacy_make_private_text, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                PlaylistPrivacyOperationSupportManager.m1571confirmPlaylistPrivateActionDialog$lambda12$lambda10(this.f$0, playlistMetadata, dialogInterface, i);
            }
        }).setNegativeButton(R.string.dmusic_playlist_privacy_keep_public_text, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                PlaylistPrivacyOperationSupportManager.m1572confirmPlaylistPrivateActionDialog$lambda12$lambda11(dialogInterface, i);
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: confirmPlaylistPrivateActionDialog$lambda-12$lambda-10, reason: not valid java name */
    public static final void m1571confirmPlaylistPrivateActionDialog$lambda12$lambda10(PlaylistPrivacyOperationSupportManager this$0, PlaylistPrivacyMetadata playlistMetadata, DialogInterface dialog, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(playlistMetadata, "$playlistMetadata");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
        this$0.updatePlaylistVisibilityWithProgressDialog(playlistMetadata, MediaProvider.Playlists.VisibilityState.PRIVATE);
        PlaylistsPrivacyMetricsLogger.INSTANCE.sendMakePrivateDialogMetric(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: confirmPlaylistPrivateActionDialog$lambda-12$lambda-11, reason: not valid java name */
    public static final void m1572confirmPlaylistPrivateActionDialog$lambda12$lambda11(DialogInterface dialog, int i) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
        PlaylistsPrivacyMetricsLogger.INSTANCE.sendMakePrivateDialogMetric(false);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager$getPlaylistVersion$2, reason: invalid class name */
    /* JADX INFO: compiled from: PlaylistPrivacyOperationSupportManager.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0003\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "kotlin.jvm.PlatformType", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager$getPlaylistVersion$2", f = "PlaylistPrivacyOperationSupportManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ Uri $uri;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Context context, Uri uri, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$context = context;
            this.$uri = uri;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$context, this.$uri, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return PlaylistUtil.getPlaylistVersionFromUri(this.$context, this.$uri);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getPlaylistVersion(android.content.Context r6, android.net.Uri r7, kotlin.coroutines.Continuation<? super java.lang.String> r8) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r8 instanceof com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager$getPlaylistVersion$1 r0 = (com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager$getPlaylistVersion$1 r0 = new com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager$getPlaylistVersion$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r8)
            goto L47
        L29:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L31:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.CoroutineDispatcher r8 = kotlinx.coroutines.Dispatchers.getIO()
            com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager$getPlaylistVersion$2 r2 = new com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager$getPlaylistVersion$2
            r4 = 0
            r2.<init>(r6, r7, r4)
            r0.label = r3
            java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r8, r2, r0)
            if (r8 != r1) goto L47
            return r1
        L47:
            java.lang.String r6 = "context: Context, uri: U…omUri(context, uri)\n    }"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r6)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager.getPlaylistVersion(android.content.Context, android.net.Uri, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager$hasUdoPlaylistWithMinFollowerCountToShowMakePrivateDialog$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlaylistPrivacyOperationSupportManager.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager$hasUdoPlaylistWithMinFollowerCountToShowMakePrivateDialog$2", f = "PlaylistPrivacyOperationSupportManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04032 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ Long $playlistId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04032(Long l, Context context, Continuation<? super C04032> continuation) {
            super(2, continuation);
            this.$playlistId = l;
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04032(this.$playlistId, this.$context, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
            return ((C04032) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Long l = this.$playlistId;
                if (l != null) {
                    return Boxing.boxBoolean(PlaylistUtil.hasUdoPlaylistWithMinFollowerCount(this.$context, String.valueOf(l.longValue()), 1, "*"));
                }
                return Boxing.boxBoolean(false);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object hasUdoPlaylistWithMinFollowerCountToShowMakePrivateDialog(Context context, Long l, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C04032(l, context, null), continuation);
    }
}
