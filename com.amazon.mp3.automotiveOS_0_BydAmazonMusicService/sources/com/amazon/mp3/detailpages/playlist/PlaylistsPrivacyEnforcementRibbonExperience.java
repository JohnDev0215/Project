package com.amazon.mp3.detailpages.playlist;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.util.OneTimeExperience;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: LibraryPlaylistsViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0011\u0010\u0007\u001a\u00020\bH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u0011\u0010\n\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\tR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/detailpages/playlist/PlaylistsPrivacyEnforcementRibbonExperience;", "Lcom/amazon/mp3/util/OneTimeExperience;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "minFollowerCount", "", "hasUdoPlaylistWithMinFollowerCount", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shouldPresentRibbon", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistsPrivacyEnforcementRibbonExperience extends OneTimeExperience {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int minFollowerCount;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlaylistsPrivacyEnforcementRibbonExperience(Context context) {
        super(INSTANCE.experimentName(context), context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.minFollowerCount = AmazonApplication.getConfiguration(context).getInteger("playlists_privacy_enforcement_followed_playlist_count", 5);
    }

    /* JADX INFO: compiled from: LibraryPlaylistsViewModel.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/detailpages/playlist/PlaylistsPrivacyEnforcementRibbonExperience$Companion;", "", "()V", "defaultExperimentName", "", "defaultFollowedPlaylistCount", "", "experimentName", "context", "Landroid/content/Context;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String experimentName(Context context) {
            String string = AmazonApplication.getConfiguration(context).getString("playlists_privacy_enforcement_followed_ribbon_experiment_name", "playlists_privacy_ribbon_experiment");
            Intrinsics.checkNotNullExpressionValue(string, "getConfiguration(context…\", defaultExperimentName)");
            return string;
        }
    }

    public final Object shouldPresentRibbon(Continuation<? super Boolean> continuation) {
        return (canPresentExperience() && Feature.playlists_privacy_enforcement_followed_ribbon_feature.isEnabled()) ? hasUdoPlaylistWithMinFollowerCount(continuation) : Boxing.boxBoolean(false);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.detailpages.playlist.PlaylistsPrivacyEnforcementRibbonExperience$hasUdoPlaylistWithMinFollowerCount$2, reason: invalid class name */
    /* JADX INFO: compiled from: LibraryPlaylistsViewModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.detailpages.playlist.PlaylistsPrivacyEnforcementRibbonExperience$hasUdoPlaylistWithMinFollowerCount$2", f = "LibraryPlaylistsViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PlaylistsPrivacyEnforcementRibbonExperience.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boxing.boxBoolean(PlaylistUtil.hasAnyUdoPrivatePlaylistWithMinFollowerCount(PlaylistsPrivacyEnforcementRibbonExperience.this.getContext(), PlaylistsPrivacyEnforcementRibbonExperience.this.minFollowerCount));
        }
    }

    private final Object hasUdoPlaylistWithMinFollowerCount(Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(null), continuation);
    }
}
