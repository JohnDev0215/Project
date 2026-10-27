package com.amazon.mp3.playlist.support;

import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.playlist.operation.PlaylistPrivacyMetadata;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: PlaylistPrivacyOperationSupportManager.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager$updatePlaylistVisibilityWithConfirmationDialogIfNeeded$1$1", f = "PlaylistPrivacyOperationSupportManager.kt", i = {0, 0, 0}, l = {112}, m = "invokeSuspend", n = {"playlistName", "playlistTrackCount", "playlistId"}, s = {"L$0", "L$1", "L$2"})
final class PlaylistPrivacyOperationSupportManager$updatePlaylistVisibilityWithConfirmationDialogIfNeeded$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FragmentActivity $activity;
    final /* synthetic */ MediaProvider.Playlists.VisibilityState $state;
    final /* synthetic */ Uri $uri;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ PlaylistPrivacyOperationSupportManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PlaylistPrivacyOperationSupportManager$updatePlaylistVisibilityWithConfirmationDialogIfNeeded$1$1(PlaylistPrivacyOperationSupportManager playlistPrivacyOperationSupportManager, Uri uri, MediaProvider.Playlists.VisibilityState visibilityState, FragmentActivity fragmentActivity, Continuation<? super PlaylistPrivacyOperationSupportManager$updatePlaylistVisibilityWithConfirmationDialogIfNeeded$1$1> continuation) {
        super(2, continuation);
        this.this$0 = playlistPrivacyOperationSupportManager;
        this.$uri = uri;
        this.$state = visibilityState;
        this.$activity = fragmentActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaylistPrivacyOperationSupportManager$updatePlaylistVisibilityWithConfirmationDialogIfNeeded$1$1(this.this$0, this.$uri, this.$state, this.$activity, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlaylistPrivacyOperationSupportManager$updatePlaylistVisibilityWithConfirmationDialogIfNeeded$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Ref.IntRef intRef;
        Ref.ObjectRef objectRef;
        Ref.ObjectRef objectRef2;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
            intRef = new Ref.IntRef();
            Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
            CoroutineDispatcher io2 = Dispatchers.getIO();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(objectRef3, this.$activity, this.$uri, intRef, objectRef4, null);
            this.L$0 = objectRef3;
            this.L$1 = intRef;
            this.L$2 = objectRef4;
            this.label = 1;
            if (BuildersKt.withContext(io2, anonymousClass1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            objectRef = objectRef3;
            objectRef2 = objectRef4;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            objectRef2 = (Ref.ObjectRef) this.L$2;
            intRef = (Ref.IntRef) this.L$1;
            objectRef = (Ref.ObjectRef) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        this.this$0.updatePlaylistVisibilityWithConfirmationDialogIfNeeded(new PlaylistPrivacyMetadata((String) objectRef.element, intRef.element, this.$uri, (Long) objectRef2.element), this.$state);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager$updatePlaylistVisibilityWithConfirmationDialogIfNeeded$1$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: PlaylistPrivacyOperationSupportManager.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager$updatePlaylistVisibilityWithConfirmationDialogIfNeeded$1$1$1", f = "PlaylistPrivacyOperationSupportManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ FragmentActivity $activity;
        final /* synthetic */ Ref.ObjectRef<Long> $playlistId;
        final /* synthetic */ Ref.ObjectRef<String> $playlistName;
        final /* synthetic */ Ref.IntRef $playlistTrackCount;
        final /* synthetic */ Uri $uri;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Ref.ObjectRef<String> objectRef, FragmentActivity fragmentActivity, Uri uri, Ref.IntRef intRef, Ref.ObjectRef<Long> objectRef2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$playlistName = objectRef;
            this.$activity = fragmentActivity;
            this.$uri = uri;
            this.$playlistTrackCount = intRef;
            this.$playlistId = objectRef2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$playlistName, this.$activity, this.$uri, this.$playlistTrackCount, this.$playlistId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference failed for: r0v3, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r0v8, types: [T, java.lang.Long] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Ref.ObjectRef<String> objectRef = this.$playlistName;
            ?? playlistName = PlaylistUtil.getPlaylistName(this.$activity, this.$uri);
            Intrinsics.checkNotNullExpressionValue(playlistName, "getPlaylistName(activity, uri)");
            objectRef.element = playlistName;
            this.$playlistTrackCount.element = PlaylistUtil.getPlaylistCount(this.$activity, this.$uri);
            this.$playlistId.element = Boxing.boxLong(PlaylistUtil.getPlaylistId(this.$uri));
            return Unit.INSTANCE;
        }
    }
}
