package com.amazon.mp3.playlist.support;

import android.app.ProgressDialog;
import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playlist.operation.PlaylistPrivacyMetadata;
import com.amazon.mp3.playlist.operation.UserPlaylistShareMetadata;
import com.amazon.music.views.library.views.ProgressDialogFactory;
import com.visualon.OSMPUtils.voOSType;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: PlaylistPrivacyOperationSupportManager.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager$showPlaylistIsPrivateDialog$1$1", f = "PlaylistPrivacyOperationSupportManager.kt", i = {0, 1}, l = {voOSType.VOOSMP_PID_DOLBY_CERTIFICATION_ENABLE_JOC, 287}, m = "invokeSuspend", n = {"progressDialog", "progressDialog"}, s = {"L$0", "L$0"})
final class PlaylistPrivacyOperationSupportManager$showPlaylistIsPrivateDialog$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FragmentActivity $activity;
    final /* synthetic */ UserPlaylistShareMetadata $playlistMetadata;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ PlaylistPrivacyOperationSupportManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PlaylistPrivacyOperationSupportManager$showPlaylistIsPrivateDialog$1$1(FragmentActivity fragmentActivity, PlaylistPrivacyOperationSupportManager playlistPrivacyOperationSupportManager, UserPlaylistShareMetadata userPlaylistShareMetadata, Continuation<? super PlaylistPrivacyOperationSupportManager$showPlaylistIsPrivateDialog$1$1> continuation) {
        super(2, continuation);
        this.$activity = fragmentActivity;
        this.this$0 = playlistPrivacyOperationSupportManager;
        this.$playlistMetadata = userPlaylistShareMetadata;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaylistPrivacyOperationSupportManager$showPlaylistIsPrivateDialog$1$1(this.$activity, this.this$0, this.$playlistMetadata, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlaylistPrivacyOperationSupportManager$showPlaylistIsPrivateDialog$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        ProgressDialog progressDialog;
        UserPlaylistShareMetadata userPlaylistShareMetadata;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PlaylistsPrivacyMetricsLogger.INSTANCE.sendShareDialogMetricEvent(true);
            ProgressDialog progressDialogCreateProgressDialog = ProgressDialogFactory.INSTANCE.createProgressDialog(this.$activity);
            PlaylistPrivacyOperationSupportManager playlistPrivacyOperationSupportManager = this.this$0;
            PlaylistPrivacyMetadata playlistPrivacyMetadata = new PlaylistPrivacyMetadata(this.$playlistMetadata.getTitle(), this.$playlistMetadata.getTrackCount(), this.$playlistMetadata.getUri(), this.$playlistMetadata.getId());
            MediaProvider.Playlists.VisibilityState visibilityState = MediaProvider.Playlists.VisibilityState.PUBLIC;
            this.L$0 = progressDialogCreateProgressDialog;
            this.label = 1;
            Object playlistVisibility = playlistPrivacyOperationSupportManager.setPlaylistVisibility(playlistPrivacyMetadata, visibilityState, this);
            if (playlistVisibility == coroutine_suspended) {
                return coroutine_suspended;
            }
            progressDialog = progressDialogCreateProgressDialog;
            obj = playlistVisibility;
        } else {
            if (i != 1) {
                if (i == 2) {
                    userPlaylistShareMetadata = (UserPlaylistShareMetadata) this.L$1;
                    progressDialog = (ProgressDialog) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    userPlaylistShareMetadata.setVersion((String) obj);
                    this.this$0.startPlaylistShare(this.$playlistMetadata);
                    progressDialog.dismiss();
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            progressDialog = (ProgressDialog) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        if (((Boolean) obj).booleanValue()) {
            UserPlaylistShareMetadata userPlaylistShareMetadata2 = this.$playlistMetadata;
            PlaylistPrivacyOperationSupportManager playlistPrivacyOperationSupportManager2 = this.this$0;
            FragmentActivity fragmentActivity = this.$activity;
            Uri uri = userPlaylistShareMetadata2.getUri();
            this.L$0 = progressDialog;
            this.L$1 = userPlaylistShareMetadata2;
            this.label = 2;
            Object playlistVersion = playlistPrivacyOperationSupportManager2.getPlaylistVersion(fragmentActivity, uri, this);
            if (playlistVersion == coroutine_suspended) {
                return coroutine_suspended;
            }
            userPlaylistShareMetadata = userPlaylistShareMetadata2;
            obj = playlistVersion;
            userPlaylistShareMetadata.setVersion((String) obj);
            this.this$0.startPlaylistShare(this.$playlistMetadata);
        }
        progressDialog.dismiss();
        return Unit.INSTANCE;
    }
}
