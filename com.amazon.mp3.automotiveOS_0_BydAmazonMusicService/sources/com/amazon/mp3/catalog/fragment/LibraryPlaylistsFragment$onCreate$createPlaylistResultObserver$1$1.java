package com.amazon.mp3.catalog.fragment;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import com.amazon.mp3.catalog.fragment.helper.CreatePlaylistHelper;
import com.amazon.mp3.catalog.fragment.viewmodel.CreatePlaylistResultViewModel;
import com.visualon.OSMPUtils.voOSType;
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
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LibraryPlaylistsFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.catalog.fragment.LibraryPlaylistsFragment$onCreate$createPlaylistResultObserver$1$1", f = "LibraryPlaylistsFragment.kt", i = {}, l = {voOSType.VOOSMP_PID_AUDIO_EFFECT_ENDPOINT_TYPE}, m = "invokeSuspend", n = {}, s = {})
final class LibraryPlaylistsFragment$onCreate$createPlaylistResultObserver$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ CreatePlaylistHelper.PlaylistInfo $playlistInfo;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ LibraryPlaylistsFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LibraryPlaylistsFragment$onCreate$createPlaylistResultObserver$1$1(CreatePlaylistHelper.PlaylistInfo playlistInfo, LibraryPlaylistsFragment libraryPlaylistsFragment, Continuation<? super LibraryPlaylistsFragment$onCreate$createPlaylistResultObserver$1$1> continuation) {
        super(2, continuation);
        this.$playlistInfo = playlistInfo;
        this.this$0 = libraryPlaylistsFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LibraryPlaylistsFragment$onCreate$createPlaylistResultObserver$1$1(this.$playlistInfo, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LibraryPlaylistsFragment$onCreate$createPlaylistResultObserver$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String mPlaylistName;
        LibraryPlaylistsFragment libraryPlaylistsFragment;
        CreatePlaylistHelper.PlaylistInfo playlistInfo;
        MutableLiveData<Boolean> didCreatePlaylistEntryInDb;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CreatePlaylistHelper.PlaylistInfo playlistInfo2 = this.$playlistInfo;
            if (playlistInfo2 != null && (mPlaylistName = playlistInfo2.getMPlaylistName()) != null) {
                libraryPlaylistsFragment = this.this$0;
                CreatePlaylistHelper.PlaylistInfo playlistInfo3 = this.$playlistInfo;
                FragmentActivity fragmentActivityRequireActivity = libraryPlaylistsFragment.requireActivity();
                Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
                CreatePlaylistHelper createPlaylistHelper = new CreatePlaylistHelper(fragmentActivityRequireActivity);
                String sourceIdString = libraryPlaylistsFragment.getSourceIdString();
                if (sourceIdString == null) {
                    sourceIdString = libraryPlaylistsFragment.getSourceId();
                }
                this.L$0 = libraryPlaylistsFragment;
                this.L$1 = playlistInfo3;
                this.label = 1;
                obj = createPlaylistHelper.savePlaylist(mPlaylistName, sourceIdString, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                playlistInfo = playlistInfo3;
            }
            return Unit.INSTANCE;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        playlistInfo = (CreatePlaylistHelper.PlaylistInfo) this.L$1;
        libraryPlaylistsFragment = (LibraryPlaylistsFragment) this.L$0;
        ResultKt.throwOnFailure(obj);
        CreatePlaylistHelper.PlaylistCreationResult playlistCreationResult = (CreatePlaylistHelper.PlaylistCreationResult) obj;
        if ((playlistCreationResult == null ? null : playlistCreationResult.getMPlaylistUri()) != null) {
            CreatePlaylistResultViewModel createPlaylistResultViewModel = libraryPlaylistsFragment.createPlaylistResultViewModel;
            if (createPlaylistResultViewModel != null && (didCreatePlaylistEntryInDb = createPlaylistResultViewModel.getDidCreatePlaylistEntryInDb()) != null) {
                didCreatePlaylistEntryInDb.postValue(Boxing.boxBoolean(true));
            }
            libraryPlaylistsFragment.handleAddTracksToPlaylist(playlistCreationResult, playlistInfo);
        }
        return Unit.INSTANCE;
    }
}
