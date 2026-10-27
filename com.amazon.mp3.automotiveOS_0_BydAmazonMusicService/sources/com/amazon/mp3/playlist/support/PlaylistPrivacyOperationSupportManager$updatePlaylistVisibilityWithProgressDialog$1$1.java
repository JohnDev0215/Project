package com.amazon.mp3.playlist.support;

import android.app.ProgressDialog;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playlist.operation.PlaylistPrivacyMetadata;
import com.amazon.music.views.library.views.ProgressDialogFactory;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: PlaylistPrivacyOperationSupportManager.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager$updatePlaylistVisibilityWithProgressDialog$1$1", f = "PlaylistPrivacyOperationSupportManager.kt", i = {0}, l = {156}, m = "invokeSuspend", n = {"progressDialog"}, s = {"L$0"})
final class PlaylistPrivacyOperationSupportManager$updatePlaylistVisibilityWithProgressDialog$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FragmentActivity $activity;
    final /* synthetic */ PlaylistPrivacyMetadata $playlistMetadata;
    final /* synthetic */ MediaProvider.Playlists.VisibilityState $state;
    Object L$0;
    int label;
    final /* synthetic */ PlaylistPrivacyOperationSupportManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PlaylistPrivacyOperationSupportManager$updatePlaylistVisibilityWithProgressDialog$1$1(FragmentActivity fragmentActivity, PlaylistPrivacyOperationSupportManager playlistPrivacyOperationSupportManager, PlaylistPrivacyMetadata playlistPrivacyMetadata, MediaProvider.Playlists.VisibilityState visibilityState, Continuation<? super PlaylistPrivacyOperationSupportManager$updatePlaylistVisibilityWithProgressDialog$1$1> continuation) {
        super(2, continuation);
        this.$activity = fragmentActivity;
        this.this$0 = playlistPrivacyOperationSupportManager;
        this.$playlistMetadata = playlistPrivacyMetadata;
        this.$state = visibilityState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaylistPrivacyOperationSupportManager$updatePlaylistVisibilityWithProgressDialog$1$1(this.$activity, this.this$0, this.$playlistMetadata, this.$state, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlaylistPrivacyOperationSupportManager$updatePlaylistVisibilityWithProgressDialog$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ProgressDialog progressDialog;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ProgressDialogFactory progressDialogFactory = ProgressDialogFactory.INSTANCE;
            FragmentActivity activity = this.$activity;
            Intrinsics.checkNotNullExpressionValue(activity, "activity");
            ProgressDialog progressDialogCreateProgressDialog = progressDialogFactory.createProgressDialog(activity);
            PlaylistPrivacyOperationSupportManager playlistPrivacyOperationSupportManager = this.this$0;
            PlaylistPrivacyMetadata playlistPrivacyMetadata = this.$playlistMetadata;
            MediaProvider.Playlists.VisibilityState visibilityState = this.$state;
            this.L$0 = progressDialogCreateProgressDialog;
            this.label = 1;
            if (playlistPrivacyOperationSupportManager.setPlaylistVisibility(playlistPrivacyMetadata, visibilityState, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            progressDialog = progressDialogCreateProgressDialog;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            progressDialog = (ProgressDialog) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        progressDialog.dismiss();
        return Unit.INSTANCE;
    }
}
