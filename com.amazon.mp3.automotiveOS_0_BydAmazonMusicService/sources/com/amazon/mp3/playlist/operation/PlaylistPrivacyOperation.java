package com.amazon.mp3.playlist.operation;

import android.content.Context;
import com.amazon.mp3.customerprofile.CustomerProfileDetailManager;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.net.playlist.MusicPlaylistExceptions;
import com.amazon.mp3.playlist.PlaylistConfigurationStorage;
import com.amazon.mp3.playlist.api.MC2PlaylistApi;
import com.amazon.mp3.util.Log;
import com.amazon.music.destination.parser.ParserUtil;
import java.lang.ref.WeakReference;
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
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: PlaylistPrivacyOperation.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0019\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n \u000e*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/playlist/operation/PlaylistPrivacyOperation;", "", "context", "Landroid/content/Context;", "metadata", "Lcom/amazon/mp3/playlist/operation/PlaylistPrivacyMetadata;", "(Landroid/content/Context;Lcom/amazon/mp3/playlist/operation/PlaylistPrivacyMetadata;)V", "maxTracksInPlaylist", "", "getMetadata", "()Lcom/amazon/mp3/playlist/operation/PlaylistPrivacyMetadata;", "profileVisibility", "", ParserUtil.TAG_QUERY_PARAM_NAME, "kotlin.jvm.PlatformType", "titleLengthLimit", "weakContext", "Ljava/lang/ref/WeakReference;", "updatePlaylistVisibility", "", "state", "Lcom/amazon/mp3/library/provider/MediaProvider$Playlists$VisibilityState;", "(Lcom/amazon/mp3/library/provider/MediaProvider$Playlists$VisibilityState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "validatePlaylistMetadataBeforeRemoteUpdate", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistPrivacyOperation {
    private final int maxTracksInPlaylist;
    private final PlaylistPrivacyMetadata metadata;
    private final String profileVisibility;
    private final String tag;
    private final int titleLengthLimit;
    private final WeakReference<Context> weakContext;

    public PlaylistPrivacyOperation(Context context, PlaylistPrivacyMetadata metadata) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        this.metadata = metadata;
        this.tag = PlaylistPrivacyOperation.class.getSimpleName();
        PlaylistConfigurationStorage playlistConfigurationStorage = PlaylistConfigurationStorage.get();
        this.maxTracksInPlaylist = playlistConfigurationStorage.getMaximumNumberOfTracksInPlaylist();
        this.titleLengthLimit = playlistConfigurationStorage.getMaximumPlaylistTitleLength();
        this.profileVisibility = CustomerProfileDetailManager.getInstance().getProfileStatus();
        this.weakContext = new WeakReference<>(context);
    }

    public final PlaylistPrivacyMetadata getMetadata() {
        return this.metadata;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playlist.operation.PlaylistPrivacyOperation$updatePlaylistVisibility$2, reason: invalid class name */
    /* JADX INFO: compiled from: PlaylistPrivacyOperation.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.playlist.operation.PlaylistPrivacyOperation$updatePlaylistVisibility$2", f = "PlaylistPrivacyOperation.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        final /* synthetic */ MediaProvider.Playlists.VisibilityState $state;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(MediaProvider.Playlists.VisibilityState visibilityState, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$state = visibilityState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PlaylistPrivacyOperation.this.new AnonymousClass2(this.$state, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws TitleLengthValidationException, PlaylistTrackLimitValidationException, MusicPlaylistExceptions.HiddenProfileException {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            PlaylistPrivacyOperation.this.validatePlaylistMetadataBeforeRemoteUpdate();
            return Boxing.boxBoolean(new MC2PlaylistApi().postPlaylistVisibilityUpdate((Context) PlaylistPrivacyOperation.this.weakContext.get(), PlaylistPrivacyOperation.this.getMetadata().getUri(), this.$state));
        }
    }

    public final Object updatePlaylistVisibility(MediaProvider.Playlists.VisibilityState visibilityState, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(visibilityState, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void validatePlaylistMetadataBeforeRemoteUpdate() throws TitleLengthValidationException, PlaylistTrackLimitValidationException, MusicPlaylistExceptions.HiddenProfileException {
        if (this.metadata.getTitle().length() > this.titleLengthLimit) {
            Log.debug(this.tag, "cant update playlist visibility, Playlist title " + this.metadata.getTitle().length() + " length exceeds maximum title length");
            throw new TitleLengthValidationException();
        }
        if (this.metadata.getTrackCount() > this.maxTracksInPlaylist) {
            Log.debug(this.tag, "Cant update playlist visibility, Playlist track count " + this.metadata.getTrackCount() + " exceeds maximum track limit");
            throw new PlaylistTrackLimitValidationException();
        }
        if (Intrinsics.areEqual("HIDDEN", this.profileVisibility)) {
            Log.debug(this.tag, "Cant update playlist visibility, profile status is hidden");
            throw new MusicPlaylistExceptions.HiddenProfileException();
        }
    }
}
