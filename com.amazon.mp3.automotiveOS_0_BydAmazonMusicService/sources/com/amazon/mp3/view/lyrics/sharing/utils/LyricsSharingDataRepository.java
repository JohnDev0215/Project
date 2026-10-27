package com.amazon.mp3.view.lyrics.sharing.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.amazon.mp3.AmazonApplication;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: compiled from: LyricsSharingDataRepository.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\rJ\u0012\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u0010J\b\u0010\u0011\u001a\u00020\u0012H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/view/lyrics/sharing/utils/LyricsSharingDataRepository;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "job", "Lkotlinx/coroutines/Job;", "lyricsShareCardsBackgrounds", "Landroidx/lifecycle/MutableLiveData;", "", "Landroid/graphics/Bitmap;", "cancelBackgroundFetch", "", "fetchLyricsShareCardsBackgroundBitmaps", "getBackgroundBitmapsLiveData", "Landroidx/lifecycle/LiveData;", "shouldFetchLyricsShareCardBackgrounds", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LyricsSharingDataRepository {
    private static Job job;
    public static final LyricsSharingDataRepository INSTANCE = new LyricsSharingDataRepository();
    private static final MutableLiveData<List<Bitmap>> lyricsShareCardsBackgrounds = new MutableLiveData<>();
    private static final String TAG = LyricsSharingDataRepository.class.getSimpleName();

    private LyricsSharingDataRepository() {
    }

    /* JADX INFO: renamed from: com.amazon.mp3.view.lyrics.sharing.utils.LyricsSharingDataRepository$fetchLyricsShareCardsBackgroundBitmaps$1, reason: invalid class name */
    /* JADX INFO: compiled from: LyricsSharingDataRepository.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.view.lyrics.sharing.utils.LyricsSharingDataRepository$fetchLyricsShareCardsBackgroundBitmaps$1", f = "LyricsSharingDataRepository.kt", i = {}, l = {35}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    LyricsShareCacheUtil lyricsShareCacheUtil = LyricsShareCacheUtil.INSTANCE;
                    Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
                    Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplication().applicationContext");
                    List<Bitmap> listLoadImagesFromFile = lyricsShareCacheUtil.loadImagesFromFile(applicationContext, "lyrics_share");
                    MainCoroutineDispatcher main2 = Dispatchers.getMain();
                    C01031 c01031 = new C01031(listLoadImagesFromFile, null);
                    this.label = 1;
                    if (BuildersKt.withContext(main2, c01031, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
            } catch (Exception e) {
                Log.e(LyricsSharingDataRepository.TAG, Intrinsics.stringPlus("Failed fetching Lyrics share cards background  ", e));
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.amazon.mp3.view.lyrics.sharing.utils.LyricsSharingDataRepository$fetchLyricsShareCardsBackgroundBitmaps$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: LyricsSharingDataRepository.kt */
        @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
        @DebugMetadata(c = "com.amazon.mp3.view.lyrics.sharing.utils.LyricsSharingDataRepository$fetchLyricsShareCardsBackgroundBitmaps$1$1", f = "LyricsSharingDataRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01031 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ List<Bitmap> $backgroundBitmaps;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01031(List<Bitmap> list, Continuation<? super C01031> continuation) {
                super(2, continuation);
                this.$backgroundBitmaps = list;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01031(this.$backgroundBitmaps, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01031) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                LyricsSharingDataRepository.lyricsShareCardsBackgrounds.setValue(this.$backgroundBitmaps);
                return Unit.INSTANCE;
            }
        }
    }

    public final void fetchLyricsShareCardsBackgroundBitmaps() {
        if (shouldFetchLyricsShareCardBackgrounds()) {
            job = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AnonymousClass1(null), 3, null);
        }
    }

    private final boolean shouldFetchLyricsShareCardBackgrounds() {
        boolean z = lyricsShareCardsBackgrounds.getValue() != null;
        Job job2 = job;
        return (z || (job2 != null && job2.isActive())) ? false : true;
    }

    public final LiveData<List<Bitmap>> getBackgroundBitmapsLiveData() {
        return lyricsShareCardsBackgrounds;
    }

    public final void cancelBackgroundFetch() {
        Job job2 = job;
        if (job2 == null) {
            return;
        }
        Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
    }
}
