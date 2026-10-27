package com.amazon.mp3.playlist.support;

import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playlist.operation.PlaylistPrivacyMetadata;
import com.visualon.OSMPUtils.voOSType;
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
@DebugMetadata(c = "com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager$updatePlaylistVisibilityWithConfirmationDialogIfNeeded$2$1", f = "PlaylistPrivacyOperationSupportManager.kt", i = {}, l = {voOSType.VOOSMP_PID_ANALYTICS_EXPORT_GET_PACKET}, m = "invokeSuspend", n = {}, s = {})
final class PlaylistPrivacyOperationSupportManager$updatePlaylistVisibilityWithConfirmationDialogIfNeeded$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FragmentActivity $activity;
    final /* synthetic */ PlaylistPrivacyMetadata $playlistMetadata;
    final /* synthetic */ MediaProvider.Playlists.VisibilityState $state;
    int label;
    final /* synthetic */ PlaylistPrivacyOperationSupportManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PlaylistPrivacyOperationSupportManager$updatePlaylistVisibilityWithConfirmationDialogIfNeeded$2$1(MediaProvider.Playlists.VisibilityState visibilityState, PlaylistPrivacyOperationSupportManager playlistPrivacyOperationSupportManager, FragmentActivity fragmentActivity, PlaylistPrivacyMetadata playlistPrivacyMetadata, Continuation<? super PlaylistPrivacyOperationSupportManager$updatePlaylistVisibilityWithConfirmationDialogIfNeeded$2$1> continuation) {
        super(2, continuation);
        this.$state = visibilityState;
        this.this$0 = playlistPrivacyOperationSupportManager;
        this.$activity = fragmentActivity;
        this.$playlistMetadata = playlistPrivacyMetadata;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaylistPrivacyOperationSupportManager$updatePlaylistVisibilityWithConfirmationDialogIfNeeded$2$1(this.$state, this.this$0, this.$activity, this.$playlistMetadata, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlaylistPrivacyOperationSupportManager$updatePlaylistVisibilityWithConfirmationDialogIfNeeded$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.$state == MediaProvider.Playlists.VisibilityState.PRIVATE && Feature.playlists_privacy_enforcement.isEnabled()) {
                PlaylistPrivacyOperationSupportManager playlistPrivacyOperationSupportManager = this.this$0;
                FragmentActivity activity = this.$activity;
                Intrinsics.checkNotNullExpressionValue(activity, "activity");
                Long id = this.$playlistMetadata.getId();
                this.label = 1;
                obj = playlistPrivacyOperationSupportManager.hasUdoPlaylistWithMinFollowerCountToShowMakePrivateDialog(activity, id, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            this.this$0.updatePlaylistVisibilityWithProgressDialog(this.$playlistMetadata, this.$state);
            return Unit.INSTANCE;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        if (((Boolean) obj).booleanValue()) {
            this.this$0.confirmPlaylistPrivateActionDialog(this.$playlistMetadata);
        } else {
            this.this$0.updatePlaylistVisibilityWithProgressDialog(this.$playlistMetadata, this.$state);
        }
        return Unit.INSTANCE;
    }
}
