package com.amazon.mp3.curate.provider;

import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.library.activity.NumberOfPlaylistsLimitReachedActivity;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.playlist.CreatePlaylistDialogFragment;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: compiled from: CurateLibraryNavigationProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.curate.provider.CurateLibraryNavigationProvider$onNavigationButtonClicked$1$1", f = "CurateLibraryNavigationProvider.kt", i = {}, l = {159}, m = "invokeSuspend", n = {}, s = {})
final class CurateLibraryNavigationProvider$onNavigationButtonClicked$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<Map<String, String>> $contentInfo;
    final /* synthetic */ FragmentActivity $it;
    int label;
    final /* synthetic */ CurateLibraryNavigationProvider this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    CurateLibraryNavigationProvider$onNavigationButtonClicked$1$1(FragmentActivity fragmentActivity, CurateLibraryNavigationProvider curateLibraryNavigationProvider, List<? extends Map<String, String>> list, Continuation<? super CurateLibraryNavigationProvider$onNavigationButtonClicked$1$1> continuation) {
        super(2, continuation);
        this.$it = fragmentActivity;
        this.this$0 = curateLibraryNavigationProvider;
        this.$contentInfo = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CurateLibraryNavigationProvider$onNavigationButtonClicked$1$1(this.$it, this.this$0, this.$contentInfo, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CurateLibraryNavigationProvider$onNavigationButtonClicked$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            boolean zMaxNumberOfPlaylistsReached = PlaylistUtil.maxNumberOfPlaylistsReached(this.$it);
            MainCoroutineDispatcher main2 = Dispatchers.getMain();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(zMaxNumberOfPlaylistsReached, this.$it, this.this$0, this.$contentInfo, null);
            this.label = 1;
            if (BuildersKt.withContext(main2, anonymousClass1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.curate.provider.CurateLibraryNavigationProvider$onNavigationButtonClicked$1$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: CurateLibraryNavigationProvider.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.curate.provider.CurateLibraryNavigationProvider$onNavigationButtonClicked$1$1$1", f = "CurateLibraryNavigationProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<Map<String, String>> $contentInfo;
        final /* synthetic */ boolean $isMaxNumberOfPlaylistsReached;
        final /* synthetic */ FragmentActivity $it;
        int label;
        final /* synthetic */ CurateLibraryNavigationProvider this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(boolean z, FragmentActivity fragmentActivity, CurateLibraryNavigationProvider curateLibraryNavigationProvider, List<? extends Map<String, String>> list, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$isMaxNumberOfPlaylistsReached = z;
            this.$it = fragmentActivity;
            this.this$0 = curateLibraryNavigationProvider;
            this.$contentInfo = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$isMaxNumberOfPlaylistsReached, this.$it, this.this$0, this.$contentInfo, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if (this.$isMaxNumberOfPlaylistsReached) {
                this.$it.startActivity(NumberOfPlaylistsLimitReachedActivity.getStartIntent());
            } else {
                CreatePlaylistDialogFragment.create(PageType.LIBRARY_LANDING_PAGE).build().show(this.$it.getSupportFragmentManager(), CreatePlaylistDialogFragment.class.getSimpleName());
                this.this$0.sendUiClickMetrics(ActionType.CREATE_PLAYLIST_ATTEMPT, this.$contentInfo);
            }
            return Unit.INSTANCE;
        }
    }
}
