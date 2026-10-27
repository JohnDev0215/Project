package com.amazon.music.share.menu.viewmodel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.amazon.music.share.menu.ShareableContent;
import com.amazon.music.share.menu.data.LyricsShareCard;
import com.amazon.music.share.menu.data.LyricsShareCardBackground;
import com.amazon.music.share.menu.data.LyricsShareData;
import com.amazon.music.share.menu.util.LyricsShareCacheUtil;
import com.amazon.ui.foundations.styles.GradientStyle;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: compiled from: LyricsShareViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u0000 !2\u00020\u0001:\u0002 !B\u001f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\u001e\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0016\u0010\u0011\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002J\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\r0\u00132\u0006\u0010\u0014\u001a\u00020\u0015J*\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\r0\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002J*\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00100\rH\u0002J\"\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\r0\u00132\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002J\u0016\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u000bJ\u0006\u0010\u001d\u001a\u00020\u001aJ\u000e\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\b\u001a\u00020\tJ\f\u0010\u001f\u001a\u00020\u0006*\u00020\u000eH\u0002R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/amazon/music/share/menu/viewmodel/LyricsShareViewModel;", "Landroidx/lifecycle/ViewModel;", "sharableContent", "Lcom/amazon/music/share/menu/ShareableContent;", "openLyricsCardFragmentObservable", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "", "(Lcom/amazon/music/share/menu/ShareableContent;Lio/reactivex/rxjava3/subjects/PublishSubject;)V", "closeCallback", "Lcom/amazon/music/share/menu/viewmodel/LyricsShareViewModel$CloseCallback;", "getBlurAlbumArtCard", "Lcom/amazon/music/share/menu/data/LyricsShareCard;", "lyrics", "", "", "blurAlbumArt", "Landroid/graphics/Bitmap;", "getDefaultLyricsShareCard", "getLyricsShareCards", "Landroidx/lifecycle/MutableLiveData;", "context", "Landroid/content/Context;", "getLyricsShareCardsFromBitmaps", "backgroundBitmaps", "getLyricsShareCardsStyles", "onLyricsShareCardClicked", "", "cardPosition", "lyricsShareCard", "removeCloseCallback", "setCloseCallback", "parseColor", "CloseCallback", "Companion", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LyricsShareViewModel extends ViewModel {
    private CloseCallback closeCallback;
    private final PublishSubject<Integer> openLyricsCardFragmentObservable;
    private final ShareableContent sharableContent;

    /* JADX INFO: compiled from: LyricsShareViewModel.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/share/menu/viewmodel/LyricsShareViewModel$CloseCallback;", "", "close", "", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface CloseCallback {
        void close();
    }

    public LyricsShareViewModel(ShareableContent shareableContent, PublishSubject<Integer> publishSubject) {
        this.sharableContent = shareableContent;
        this.openLyricsCardFragmentObservable = publishSubject;
    }

    public final MutableLiveData<List<LyricsShareCard>> getLyricsShareCards(Context context) {
        List<String> lyrics;
        List<Bitmap> shareCardsBackgroundBitmaps;
        Intrinsics.checkNotNullParameter(context, "context");
        ShareableContent shareableContent = this.sharableContent;
        if (shareableContent != null && (lyrics = shareableContent.getLyrics()) != null && (shareCardsBackgroundBitmaps = this.sharableContent.getShareCardsBackgroundBitmaps()) != null) {
            if (shareCardsBackgroundBitmaps.size() > 1) {
                return getLyricsShareCardsStyles(lyrics);
            }
            return getLyricsShareCards(context, lyrics);
        }
        return new MutableLiveData<>();
    }

    private final LyricsShareCard getBlurAlbumArtCard(List<String> lyrics, Bitmap blurAlbumArt) {
        return new LyricsShareCard(lyrics, new LyricsShareCardBackground(null, blurAlbumArt, true, 1, null), -1);
    }

    private final MutableLiveData<List<LyricsShareCard>> getLyricsShareCards(Context context, List<String> lyrics) {
        List<Bitmap> shareCardsBackgroundBitmaps;
        Bitmap bitmap;
        MutableLiveData<List<LyricsShareCard>> mutableLiveData = new MutableLiveData<>();
        ArrayList arrayList = new ArrayList();
        ShareableContent shareableContent = this.sharableContent;
        if (shareableContent != null && (shareCardsBackgroundBitmaps = shareableContent.getShareCardsBackgroundBitmaps()) != null && (bitmap = (Bitmap) CollectionsKt___CollectionsKt.firstOrNull((List) shareCardsBackgroundBitmaps)) != null) {
            arrayList.add(getBlurAlbumArtCard(lyrics, bitmap));
        }
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new AnonymousClass3(context, arrayList, this, lyrics, mutableLiveData, null), 2, null);
        return mutableLiveData;
    }

    /* JADX INFO: renamed from: com.amazon.music.share.menu.viewmodel.LyricsShareViewModel$getLyricsShareCards$3, reason: invalid class name */
    /* JADX INFO: compiled from: LyricsShareViewModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.share.menu.viewmodel.LyricsShareViewModel$getLyricsShareCards$3", f = "LyricsShareViewModel.kt", i = {}, l = {66}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ MutableLiveData<List<LyricsShareCard>> $liveData;
        final /* synthetic */ List<String> $lyrics;
        final /* synthetic */ List<LyricsShareCard> $lyricsShareCards;
        int label;
        final /* synthetic */ LyricsShareViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(Context context, List<LyricsShareCard> list, LyricsShareViewModel lyricsShareViewModel, List<String> list2, MutableLiveData<List<LyricsShareCard>> mutableLiveData, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$context = context;
            this.$lyricsShareCards = list;
            this.this$0 = lyricsShareViewModel;
            this.$lyrics = list2;
            this.$liveData = mutableLiveData;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass3(this.$context, this.$lyricsShareCards, this.this$0, this.$lyrics, this.$liveData, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.$lyricsShareCards.addAll(this.this$0.getLyricsShareCardsFromBitmaps(this.$lyrics, LyricsShareCacheUtil.INSTANCE.loadImagesFromCache(this.$context, "lyrics_share")));
                this.$lyricsShareCards.add(this.this$0.getDefaultLyricsShareCard(this.$lyrics));
                MainCoroutineDispatcher main2 = Dispatchers.getMain();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$liveData, this.$lyricsShareCards, null);
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

        /* JADX INFO: renamed from: com.amazon.music.share.menu.viewmodel.LyricsShareViewModel$getLyricsShareCards$3$1, reason: invalid class name */
        /* JADX INFO: compiled from: LyricsShareViewModel.kt */
        @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
        @DebugMetadata(c = "com.amazon.music.share.menu.viewmodel.LyricsShareViewModel$getLyricsShareCards$3$1", f = "LyricsShareViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ MutableLiveData<List<LyricsShareCard>> $liveData;
            final /* synthetic */ List<LyricsShareCard> $lyricsShareCards;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(MutableLiveData<List<LyricsShareCard>> mutableLiveData, List<LyricsShareCard> list, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$liveData = mutableLiveData;
                this.$lyricsShareCards = list;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.$liveData, this.$lyricsShareCards, continuation);
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
                this.$liveData.setValue(this.$lyricsShareCards);
                return Unit.INSTANCE;
            }
        }
    }

    private final MutableLiveData<List<LyricsShareCard>> getLyricsShareCardsStyles(List<String> lyrics) {
        List<Bitmap> shareCardsBackgroundBitmaps;
        MutableLiveData<List<LyricsShareCard>> mutableLiveData = new MutableLiveData<>();
        ArrayList arrayList = new ArrayList();
        ShareableContent shareableContent = this.sharableContent;
        if (shareableContent != null && (shareCardsBackgroundBitmaps = shareableContent.getShareCardsBackgroundBitmaps()) != null) {
            Bitmap bitmap = (Bitmap) CollectionsKt___CollectionsKt.firstOrNull((List) shareCardsBackgroundBitmaps);
            if (bitmap != null) {
                arrayList.add(getBlurAlbumArtCard(lyrics, bitmap));
            }
            ArrayList arrayList2 = new ArrayList();
            int i = 0;
            for (Object obj : shareCardsBackgroundBitmaps) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                if (i != 0) {
                    arrayList2.add(obj);
                }
                i = i2;
            }
            arrayList.addAll(getLyricsShareCardsFromBitmaps(lyrics, arrayList2));
        }
        arrayList.add(getDefaultLyricsShareCard(lyrics));
        mutableLiveData.setValue(arrayList);
        return mutableLiveData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<LyricsShareCard> getLyricsShareCardsFromBitmaps(List<String> lyrics, List<Bitmap> backgroundBitmaps) {
        if (backgroundBitmaps.size() < 6) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        return CollectionsKt__CollectionsKt.listOf((Object[]) new LyricsShareCard[]{new LyricsShareCard(lyrics, new LyricsShareCardBackground(null, backgroundBitmaps.get(0), false, 5, null), Integer.valueOf(ViewCompat.MEASURED_STATE_MASK)), new LyricsShareCard(lyrics, new LyricsShareCardBackground(null, backgroundBitmaps.get(1), false, 5, null), Integer.valueOf(ViewCompat.MEASURED_STATE_MASK)), new LyricsShareCard(lyrics, new LyricsShareCardBackground(null, backgroundBitmaps.get(2), false, 5, null), -1), new LyricsShareCard(lyrics, new LyricsShareCardBackground(null, backgroundBitmaps.get(3), false, 5, null), -1), new LyricsShareCard(lyrics, new LyricsShareCardBackground(null, backgroundBitmaps.get(4), false, 5, null), -1), new LyricsShareCard(lyrics, new LyricsShareCardBackground(null, backgroundBitmaps.get(5), false, 5, null), Integer.valueOf(ViewCompat.MEASURED_STATE_MASK))});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LyricsShareCard getDefaultLyricsShareCard(List<String> lyrics) {
        return new LyricsShareCard(lyrics, new LyricsShareCardBackground(new GradientStyle(CollectionsKt__CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(parseColor("#000000")), Integer.valueOf(parseColor("#000000"))}), CollectionsKt__CollectionsKt.emptyList(), false), null, false, 6, null), -16711681);
    }

    private final int parseColor(String str) {
        return Color.parseColor(str);
    }

    public final void onLyricsShareCardClicked(int cardPosition, LyricsShareCard lyricsShareCard) {
        Intrinsics.checkNotNullParameter(lyricsShareCard, "lyricsShareCard");
        ShareableContent shareableContent = this.sharableContent;
        if (shareableContent != null && shareableContent.getEntityId() != null) {
            new LyricsShareData(cardPosition, lyricsShareCard.getLyrics());
            PublishSubject<Integer> publishSubject = this.openLyricsCardFragmentObservable;
            if (publishSubject != null) {
                publishSubject.onNext(Integer.valueOf(cardPosition));
            }
        }
        CloseCallback closeCallback = this.closeCallback;
        if (closeCallback == null) {
            return;
        }
        closeCallback.close();
    }

    public final void setCloseCallback(CloseCallback closeCallback) {
        Intrinsics.checkNotNullParameter(closeCallback, "closeCallback");
        this.closeCallback = closeCallback;
    }

    public final void removeCloseCallback() {
        this.closeCallback = null;
    }
}
