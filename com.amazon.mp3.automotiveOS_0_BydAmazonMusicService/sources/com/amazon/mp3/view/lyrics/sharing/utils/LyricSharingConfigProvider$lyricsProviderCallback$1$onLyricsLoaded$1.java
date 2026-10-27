package com.amazon.mp3.view.lyrics.sharing.utils;

import com.amazon.mp3.lyrics.item.Lyrics;
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

/* JADX INFO: compiled from: LyricSharingConfigProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.view.lyrics.sharing.utils.LyricSharingConfigProvider$lyricsProviderCallback$1$onLyricsLoaded$1", f = "LyricSharingConfigProvider.kt", i = {}, l = {57}, m = "invokeSuspend", n = {}, s = {})
final class LyricSharingConfigProvider$lyricsProviderCallback$1$onLyricsLoaded$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $trackAsin;
    int label;
    final /* synthetic */ LyricSharingConfigProvider this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LyricSharingConfigProvider$lyricsProviderCallback$1$onLyricsLoaded$1(LyricSharingConfigProvider lyricSharingConfigProvider, String str, Continuation<? super LyricSharingConfigProvider$lyricsProviderCallback$1$onLyricsLoaded$1> continuation) {
        super(2, continuation);
        this.this$0 = lyricSharingConfigProvider;
        this.$trackAsin = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LyricSharingConfigProvider$lyricsProviderCallback$1$onLyricsLoaded$1(this.this$0, this.$trackAsin, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LyricSharingConfigProvider$lyricsProviderCallback$1$onLyricsLoaded$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Lyrics lyricsDownloadLyrics = this.this$0.getLyricsManager().downloadLyrics(this.this$0.getLyricsTrackInfo(this.$trackAsin));
            MainCoroutineDispatcher main2 = Dispatchers.getMain();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, lyricsDownloadLyrics, null);
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
        this.this$0.getLyricsManager().refreshLyrics();
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.view.lyrics.sharing.utils.LyricSharingConfigProvider$lyricsProviderCallback$1$onLyricsLoaded$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: LyricSharingConfigProvider.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.view.lyrics.sharing.utils.LyricSharingConfigProvider$lyricsProviderCallback$1$onLyricsLoaded$1$1", f = "LyricSharingConfigProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Lyrics $lyrics;
        int label;
        final /* synthetic */ LyricSharingConfigProvider this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(LyricSharingConfigProvider lyricSharingConfigProvider, Lyrics lyrics, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = lyricSharingConfigProvider;
            this.$lyrics = lyrics;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, this.$lyrics, continuation);
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
            this.this$0.updateLyricsSharingConfig(this.$lyrics);
            return Unit.INSTANCE;
        }
    }
}
