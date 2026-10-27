package com.amazon.mp3.view.lyrics.sharing.viewModel;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.view.lyrics.sharing.data.LyricsShareCardBackground;
import com.amazon.mp3.view.lyrics.sharing.data.LyricsShareCardStyle;
import com.amazon.mp3.view.lyrics.sharing.data.LyricsSharingDataModel;
import com.amazon.mp3.view.lyrics.sharing.utils.LyricsShareCacheUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
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

/* JADX INFO: compiled from: LyricsCardSelectionViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u000f\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u0016\u0010\u0017J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002J\b\u0010\t\u001a\u00020\u0007H\u0002J\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004R\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000eR.\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00040\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/view/lyrics/sharing/viewModel/LyricsCardSelectionViewModel;", "Landroidx/lifecycle/ViewModel;", "", "initLyricsShareCardsStyles", "", "Landroid/graphics/Bitmap;", "backgroundBitmaps", "Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsShareCardStyle;", "getLyricsShareCardsFromBitmaps", "getDefaultLyricsShareCard", "bitmaps", "getBackgroundStyles", "Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsSharingDataModel;", "lyricsSharingDataModel", "Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsSharingDataModel;", "Landroidx/lifecycle/MutableLiveData;", "lyricsShareCardsStyles", "Landroidx/lifecycle/MutableLiveData;", "getLyricsShareCardsStyles", "()Landroidx/lifecycle/MutableLiveData;", "setLyricsShareCardsStyles", "(Landroidx/lifecycle/MutableLiveData;)V", "<init>", "(Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsSharingDataModel;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LyricsCardSelectionViewModel extends ViewModel {
    private MutableLiveData<List<LyricsShareCardStyle>> lyricsShareCardsStyles;
    private final LyricsSharingDataModel lyricsSharingDataModel;

    public LyricsCardSelectionViewModel(LyricsSharingDataModel lyricsSharingDataModel) {
        Intrinsics.checkNotNullParameter(lyricsSharingDataModel, "lyricsSharingDataModel");
        this.lyricsSharingDataModel = lyricsSharingDataModel;
        this.lyricsShareCardsStyles = new MutableLiveData<>();
        initLyricsShareCardsStyles();
    }

    public final MutableLiveData<List<LyricsShareCardStyle>> getLyricsShareCardsStyles() {
        return this.lyricsShareCardsStyles;
    }

    private final void initLyricsShareCardsStyles() {
        ArrayList arrayList = new ArrayList();
        Bitmap blurAlbumArt = this.lyricsSharingDataModel.getBlurAlbumArt();
        if (blurAlbumArt != null) {
            arrayList.add(new LyricsShareCardStyle(new LyricsShareCardBackground(null, blurAlbumArt, false, 5, null), -1));
        }
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new AnonymousClass2(arrayList, this, null), 2, null);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.view.lyrics.sharing.viewModel.LyricsCardSelectionViewModel$initLyricsShareCardsStyles$2, reason: invalid class name */
    /* JADX INFO: compiled from: LyricsCardSelectionViewModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.view.lyrics.sharing.viewModel.LyricsCardSelectionViewModel$initLyricsShareCardsStyles$2", f = "LyricsCardSelectionViewModel.kt", i = {}, l = {46}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<LyricsShareCardStyle> $lyricsShareCardsStylesList;
        int label;
        final /* synthetic */ LyricsCardSelectionViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(List<LyricsShareCardStyle> list, LyricsCardSelectionViewModel lyricsCardSelectionViewModel, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$lyricsShareCardsStylesList = list;
            this.this$0 = lyricsCardSelectionViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$lyricsShareCardsStylesList, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                LyricsShareCacheUtil lyricsShareCacheUtil = LyricsShareCacheUtil.INSTANCE;
                Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplication().applicationContext");
                this.$lyricsShareCardsStylesList.addAll(this.this$0.getLyricsShareCardsFromBitmaps(lyricsShareCacheUtil.loadImagesFromFile(applicationContext, "lyrics_share")));
                this.$lyricsShareCardsStylesList.add(this.this$0.getDefaultLyricsShareCard());
                MainCoroutineDispatcher main2 = Dispatchers.getMain();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$lyricsShareCardsStylesList, null);
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

        /* JADX INFO: renamed from: com.amazon.mp3.view.lyrics.sharing.viewModel.LyricsCardSelectionViewModel$initLyricsShareCardsStyles$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: LyricsCardSelectionViewModel.kt */
        @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
        @DebugMetadata(c = "com.amazon.mp3.view.lyrics.sharing.viewModel.LyricsCardSelectionViewModel$initLyricsShareCardsStyles$2$1", f = "LyricsCardSelectionViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ List<LyricsShareCardStyle> $lyricsShareCardsStylesList;
            int label;
            final /* synthetic */ LyricsCardSelectionViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(LyricsCardSelectionViewModel lyricsCardSelectionViewModel, List<LyricsShareCardStyle> list, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = lyricsCardSelectionViewModel;
                this.$lyricsShareCardsStylesList = list;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, this.$lyricsShareCardsStylesList, continuation);
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
                this.this$0.getLyricsShareCardsStyles().setValue(this.$lyricsShareCardsStylesList);
                return Unit.INSTANCE;
            }
        }
    }

    public final List<LyricsShareCardStyle> getBackgroundStyles(List<Bitmap> bitmaps) {
        Intrinsics.checkNotNullParameter(bitmaps, "bitmaps");
        ArrayList arrayList = new ArrayList();
        Bitmap blurAlbumArt = this.lyricsSharingDataModel.getBlurAlbumArt();
        if (blurAlbumArt != null) {
            arrayList.add(new LyricsShareCardStyle(new LyricsShareCardBackground(null, blurAlbumArt, true, 1, null), -1));
        }
        arrayList.addAll(getLyricsShareCardsFromBitmaps(bitmaps));
        arrayList.add(getDefaultLyricsShareCard());
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<LyricsShareCardStyle> getLyricsShareCardsFromBitmaps(List<Bitmap> backgroundBitmaps) {
        if (backgroundBitmaps.size() < 6) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        return CollectionsKt__CollectionsKt.listOf((Object[]) new LyricsShareCardStyle[]{new LyricsShareCardStyle(new LyricsShareCardBackground(null, backgroundBitmaps.get(0), false, 5, null), ViewCompat.MEASURED_STATE_MASK), new LyricsShareCardStyle(new LyricsShareCardBackground(null, backgroundBitmaps.get(1), false, 5, null), ViewCompat.MEASURED_STATE_MASK), new LyricsShareCardStyle(new LyricsShareCardBackground(null, backgroundBitmaps.get(2), false, 5, null), -1), new LyricsShareCardStyle(new LyricsShareCardBackground(null, backgroundBitmaps.get(3), false, 5, null), -1), new LyricsShareCardStyle(new LyricsShareCardBackground(null, backgroundBitmaps.get(4), false, 5, null), -1), new LyricsShareCardStyle(new LyricsShareCardBackground(null, backgroundBitmaps.get(5), false, 5, null), ViewCompat.MEASURED_STATE_MASK)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LyricsShareCardStyle getDefaultLyricsShareCard() {
        return new LyricsShareCardStyle(new LyricsShareCardBackground(Integer.valueOf(R.drawable.black_background), null, false, 6, null), AmazonApplication.getApplication().getApplicationContext().getResources().getColor(R.color.cyan_accent));
    }
}
