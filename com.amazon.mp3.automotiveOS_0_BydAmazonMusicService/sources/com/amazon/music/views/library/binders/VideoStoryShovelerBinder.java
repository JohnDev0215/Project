package com.amazon.music.views.library.binders;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.uicontentview.ContentViewedListener;
import com.amazon.music.views.library.metadata.VideoStoryMetadata;
import com.amazon.music.views.library.models.VideoStoryShovelerModel;
import com.amazon.music.views.library.models.VideoStoryTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.providers.SeeMoreProvider;
import com.amazon.music.views.library.providers.VideoStoryAutoPlayViewProvider;
import com.amazon.music.views.library.providers.VideoStoryPlaybackDataProvider;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.SectionWidget;
import com.amazon.ui.foundations.styles.GridSize;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: VideoStoryShovelerBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00020\u0004B}\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u00101\u001a\u000200\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\u0006\u0010\u001c\u001a\u00020\u001b\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 \u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#\u00122\u00105\u001a\u001a\u0012\u0016\b\u0001\u0012\u0012\u0012\u0006\b\u0001\u0012\u000204\u0012\u0006\b\u0001\u0012\u00020\f0302\"\u0012\u0012\u0006\b\u0001\u0012\u000204\u0012\u0006\b\u0001\u0012\u00020\f03¢\u0006\u0004\b6\u00107J\u0018\u0010\b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0002H\u0002J\u0018\u0010\t\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0002H\u0002J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0003H\u0002J\u001e\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00032\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002J\u0016\u0010\u000f\u001a\u00020\u00072\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0018\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0002H\u0016R\u0016\u0010\u0011\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010\u0014R\u0016\u0010\u0016\u001a\u0004\u0018\u00010\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001c\u001a\u00020\u001b8\u0014X\u0094\u0004¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0016\u0010!\u001a\u0004\u0018\u00010 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\u0016\u0010$\u001a\u0004\u0018\u00010#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R\u0014\u0010'\u001a\u00020&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010*\u001a\u00020)8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+R\u001a\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00020,8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b-\u0010.¨\u00068"}, d2 = {"Lcom/amazon/music/views/library/binders/VideoStoryShovelerBinder;", "Lcom/amazon/music/views/library/binders/BaseViewContainerBinder;", "Lcom/amazon/music/views/library/models/VideoStoryShovelerModel;", "Lcom/amazon/music/views/library/views/SectionWidget;", "Lcom/amazon/music/views/library/binders/BaseStateChangeBinder;", "view", "model", "", "showView", "bindStyle", "cleanWidget", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "models", "bindModels", "ifApplicablePrepareStoriesForPlayback", "Landroid/content/Context;", "context", "createView", "bind", "Landroid/content/Context;", "Lcom/amazon/music/views/library/providers/SeeMoreProvider;", "seeMoreProvider", "Lcom/amazon/music/views/library/providers/SeeMoreProvider;", "Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;", "adapterProvider", "Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "contentViewedListener", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "getContentViewedListener", "()Lcom/amazon/music/uicontentview/ContentViewedListener;", "Lcom/amazon/music/views/library/providers/VideoStoryPlaybackDataProvider;", "videoStoryPlaybackDataProvider", "Lcom/amazon/music/views/library/providers/VideoStoryPlaybackDataProvider;", "Lcom/amazon/music/views/library/providers/VideoStoryAutoPlayViewProvider;", "videoStoryAutoPlayViewProvider", "Lcom/amazon/music/views/library/providers/VideoStoryAutoPlayViewProvider;", "Lcom/amazon/music/views/library/binders/GridLayoutUtils;", "gridLayoutUtils", "Lcom/amazon/music/views/library/binders/GridLayoutUtils;", "Lcom/amazon/music/views/library/binders/PlaybackContainerBinder;", "playbackContainerBinder", "Lcom/amazon/music/views/library/binders/PlaybackContainerBinder;", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "modelClass", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Landroid/view/View;", "binders", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/providers/SeeMoreProvider;Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;Lcom/amazon/music/uicontentview/ContentViewedListener;Lcom/amazon/music/views/library/providers/VideoStoryPlaybackDataProvider;Lcom/amazon/music/views/library/providers/VideoStoryAutoPlayViewProvider;[Lcom/amazon/music/views/library/binders/UniversalBinder;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class VideoStoryShovelerBinder extends BaseViewContainerBinder<VideoStoryShovelerModel, SectionWidget> {
    private final BaseViewModelAdapterProvider adapterProvider;
    private final ContentViewedListener contentViewedListener;
    private Context context;
    private final GridLayoutUtils gridLayoutUtils;
    private final PlaybackContainerBinder playbackContainerBinder;
    private final SeeMoreProvider seeMoreProvider;
    private final VideoStoryAutoPlayViewProvider videoStoryAutoPlayViewProvider;
    private final VideoStoryPlaybackDataProvider videoStoryPlaybackDataProvider;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<VideoStoryShovelerModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(VideoStoryShovelerModel.class);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoStoryShovelerBinder(Context context, StyleSheet styleSheet, SeeMoreProvider seeMoreProvider, BaseViewModelAdapterProvider adapterProvider, ContentViewedListener contentViewedListener, VideoStoryPlaybackDataProvider videoStoryPlaybackDataProvider, VideoStoryAutoPlayViewProvider videoStoryAutoPlayViewProvider, UniversalBinder<? extends View, ? extends BaseViewModel>... binders) {
        super(styleSheet, contentViewedListener);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(adapterProvider, "adapterProvider");
        Intrinsics.checkNotNullParameter(contentViewedListener, "contentViewedListener");
        Intrinsics.checkNotNullParameter(binders, "binders");
        this.context = context;
        this.seeMoreProvider = seeMoreProvider;
        this.adapterProvider = adapterProvider;
        this.contentViewedListener = contentViewedListener;
        this.videoStoryPlaybackDataProvider = videoStoryPlaybackDataProvider;
        this.videoStoryAutoPlayViewProvider = videoStoryAutoPlayViewProvider;
        this.gridLayoutUtils = new GridLayoutUtils(context, styleSheet);
        this.playbackContainerBinder = new PlaybackContainerBinder();
        buildBinders((UniversalBinder<? extends View, ? extends BaseViewModel>[]) Arrays.copyOf(binders, binders.length));
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public SectionWidget createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        SectionWidget sectionWidget = new SectionWidget(context, getStyleSheet());
        sectionWidget.setContentView(new RecyclerView(context));
        return sectionWidget;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(SectionWidget view, final VideoStoryShovelerModel model) {
        VideoStoryAutoPlayViewProvider videoStoryAutoPlayViewProvider;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        cleanWidget(view);
        if (model.getModels() != null) {
            if (!model.getModels().isEmpty()) {
                List<BaseViewModel> models = model.getModels();
                boolean z = model.getMoreText() != null;
                bindModels(view, models);
                RecyclerView recyclerView = view.getRecyclerView();
                if (recyclerView != null && (videoStoryAutoPlayViewProvider = this.videoStoryAutoPlayViewProvider) != null) {
                    videoStoryAutoPlayViewProvider.setRecyclerView(recyclerView);
                }
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new AnonymousClass2(model, null), 2, null);
                if (model.getTileType() != 20) {
                    String title = model.getTitle();
                    if (title != null) {
                        view.setTitle(title);
                    }
                    view.highlightTitle(model.getStringsToHighlight());
                    String subtitle = model.getSubtitle();
                    if (subtitle != null) {
                        view.setSubtitle(subtitle);
                    }
                }
                if (z && !TextUtils.isEmpty(model.getMoreTarget()) && this.seeMoreProvider != null) {
                    String moreText = model.getMoreText();
                    Intrinsics.checkNotNull(moreText);
                    SectionWidget.setMoreButtonText$default(view, moreText, false, 2, null);
                    view.setMoreButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.VideoStoryShovelerBinder$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view2) {
                            VideoStoryShovelerBinder.m2373bind$lambda3(this.f$0, model, view2);
                        }
                    });
                }
                showView(view, model);
                return;
            }
        }
        hideView(view);
    }

    /* JADX INFO: renamed from: com.amazon.music.views.library.binders.VideoStoryShovelerBinder$bind$2, reason: invalid class name */
    /* JADX INFO: compiled from: VideoStoryShovelerBinder.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.views.library.binders.VideoStoryShovelerBinder$bind$2", f = "VideoStoryShovelerBinder.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ VideoStoryShovelerModel $model;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(VideoStoryShovelerModel videoStoryShovelerModel, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$model = videoStoryShovelerModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return VideoStoryShovelerBinder.this.new AnonymousClass2(this.$model, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            VideoStoryShovelerBinder.this.ifApplicablePrepareStoriesForPlayback(this.$model.getModels());
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-3, reason: not valid java name */
    public static final void m2373bind$lambda3(VideoStoryShovelerBinder this$0, VideoStoryShovelerModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        this$0.seeMoreProvider.onSeeMoreClick(model.getMoreTarget(), model.getViewReferenceId(), model.getNextPageToken());
    }

    private final void showView(SectionWidget view, VideoStoryShovelerModel model) {
        RecyclerView recyclerView = view.getRecyclerView();
        if (recyclerView != null) {
            GridLayoutUtils.setLayoutManager$default(this.gridLayoutUtils, recyclerView, model.getTileType(), Integer.valueOf(model.getModels().size()), false, false, null, null, 120, null);
        }
        bindStyle(view, model);
        if (this.gridLayoutUtils.shouldDisplayPeek(Integer.valueOf(model.getModels().size()), model.getTileType()) || model.getTileType() == 17) {
            int shovelerItemWidth = this.gridLayoutUtils.getShovelerItemWidth(model.getModels().size(), model.getTileType());
            UniversalAdapter adapter = view.getAdapter();
            if (!(adapter instanceof UniversalAdapter)) {
                adapter = null;
            }
            if (adapter != null) {
                adapter.setTileViewWidth(shovelerItemWidth);
            }
        }
        view.setVisibility(0);
    }

    private final void bindStyle(SectionWidget view, VideoStoryShovelerModel model) {
        if (this.gridLayoutUtils.shouldDisplayPeek(Integer.valueOf(model.getModels().size()), model.getTileType())) {
            SectionWidget.setMargins$default(view, 0, null, 0, null, null, 26, null);
            SectionWidget.setContentMargins$default(view, 0, null, 0, null, 10, null);
            GridSize gridSize = getStyleSheet().getGridSize(this.context);
            if (gridSize == null) {
                return;
            }
            SectionWidget.setTextMargins$default(view, Integer.valueOf(gridSize.getMarginSpace() + gridSize.getColumnSpace()), null, null, null, 14, null);
            RecyclerView recyclerView = view.getRecyclerView();
            if (recyclerView != null) {
                recyclerView.setClipToPadding(false);
            }
            RecyclerView recyclerView2 = view.getRecyclerView();
            if (recyclerView2 == null) {
                return;
            }
            recyclerView2.setPadding(gridSize.getMarginSpace() + (gridSize.getColumnSpace() / 2), 0, 0, 0);
        }
    }

    private final void cleanWidget(SectionWidget view) {
        view.setMoreButtonOnClickListener(null);
        view.setTitle("");
        view.setSubtitle("");
        SectionWidget.setMoreButtonText$default(view, "", false, 2, null);
        view.setMoreButtonOnClickListener(null);
        UniversalAdapter adapter = view.getAdapter();
        if (adapter != null) {
            adapter.clear();
        }
        view.setInitialMargins();
        RecyclerView recyclerView = view.getRecyclerView();
        if (recyclerView != null) {
            recyclerView.setPadding(0, 0, 0, 0);
        }
        view.showBackground(false);
        view.showImage(false);
        SectionWidget.setPlaybackContainerBodyMargins$default(view, null, null, null, null, 15, null);
        SectionWidget.setPlaybackContainerHeaderMargins$default(view, null, null, null, null, 15, null);
        view.highlightTitle(null);
        this.playbackContainerBinder.reset(view);
    }

    private final void bindModels(SectionWidget view, List<? extends BaseViewModel> models) {
        UniversalAdapter adapter = view.getAdapter();
        if (adapter != null) {
            this.adapterProvider.updateItems(adapter, models);
            return;
        }
        List<UniversalBinder<? extends View, ? extends BaseViewModel>> binders = getBinders();
        if (binders == null) {
            return;
        }
        BaseViewModelAdapterProvider baseViewModelAdapterProvider = this.adapterProvider;
        Object[] array = binders.toArray(new UniversalBinder[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        UniversalBinder[] universalBinderArr = (UniversalBinder[]) array;
        view.setAdapter(baseViewModelAdapterProvider.getAdapter(models, (UniversalBinder[]) Arrays.copyOf(universalBinderArr, universalBinderArr.length)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void ifApplicablePrepareStoriesForPlayback(List<? extends BaseViewModel> models) {
        ArrayList arrayList;
        VideoStoryPlaybackDataProvider videoStoryPlaybackDataProvider;
        if (CollectionsKt___CollectionsKt.getOrNull(models, 0) instanceof VideoStoryTileModel) {
            List<VideoStoryMetadata> storiesMetadata = ((VideoStoryMetadata) ((VideoStoryTileModel) CollectionsKt___CollectionsKt.first((List) models)).getMetadata()).getStoriesMetadata();
            if (storiesMetadata == null) {
                arrayList = null;
            } else {
                ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(storiesMetadata, 10));
                Iterator<T> it = storiesMetadata.iterator();
                while (it.hasNext()) {
                    String asin = ((VideoStoryMetadata) it.next()).getAsin();
                    if (asin == null) {
                        asin = "";
                    }
                    arrayList2.add(asin);
                }
                arrayList = arrayList2;
            }
            if (arrayList == null || (videoStoryPlaybackDataProvider = this.videoStoryPlaybackDataProvider) == null) {
                return;
            }
            videoStoryPlaybackDataProvider.fetchAndStoreVideoStoriesTemplate(arrayList);
        }
    }
}
