package com.amazon.music.views.library.binders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.uicontentview.ContentViewedListener;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.models.FeaturePlayModel;
import com.amazon.music.views.library.providers.FeaturedPlayMusicProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.utils.MediaUtil;
import com.amazon.music.views.library.views.FeaturePlayView;
import com.amazon.ui.foundations.utils.BitmapEffectUtils;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: FeaturePlayBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B=\u0012\u0006\u0010\u001e\u001a\u00020\u001d\u0012\u0006\u0010!\u001a\u00020 \u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#\u0012\u0006\u0010'\u001a\u00020&\u0012\u0006\u0010*\u001a\u00020)\u0012\b\b\u0002\u0010-\u001a\u00020,¢\u0006\u0004\b<\u0010=J\u0018\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0003H\u0002J\u0018\u0010\r\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0003H\u0002J\u0018\u0010\u000e\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0003H\u0002J\u0018\u0010\u000f\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0003H\u0002J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000b\u001a\u00020\u0003H\u0002J\u0010\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J \u0010\u0016\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0014H\u0016J\u0018\u0010\u0017\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0003H\u0016J\u000e\u0010\u0018\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0002J\u0016\u0010\u001b\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0019J\u000e\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019R\u0014\u0010\u001e\u001a\u00020\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0014\u0010!\u001a\u00020 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\u0016\u0010$\u001a\u0004\u0018\u00010#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R\u0014\u0010'\u001a\u00020&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010*\u001a\u00020)8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+R\u0014\u0010-\u001a\u00020,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010.R\u0014\u0010/\u001a\u00020\u00068\u0002X\u0082D¢\u0006\u0006\n\u0004\b/\u00100R\u0014\u00101\u001a\u00020\u00048\u0002X\u0082D¢\u0006\u0006\n\u0004\b1\u00102R\u0016\u0010\u0012\u001a\u00020\u00118\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u0012\u00103R\u001c\u00106\u001a\n 5*\u0004\u0018\u000104048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b6\u00107R\u001a\u0010;\u001a\b\u0012\u0004\u0012\u00020\u0003088VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b9\u0010:¨\u0006>"}, d2 = {"Lcom/amazon/music/views/library/binders/FeaturePlayBinder;", "Lcom/amazon/music/views/library/binders/BaseStateChangeBinder;", "Lcom/amazon/music/views/library/views/FeaturePlayView;", "Lcom/amazon/music/views/library/models/FeaturePlayModel;", "", "identifier", "", "numItemsAccessible", "", "reportContentView", "view", "model", "loadHeroImageForSmallAndMidScreenLeftAligned", "loadHeroBackgroundImageForSmallAndMidScreenLeftAligned", "loadHeroImageForWideScreen", "loadBackgroundImageForWideScreen", "screenSizeAwarePrimaryText", "Landroid/content/Context;", "context", "createView", "", "payload", "handleStateChange", "bind", "resetView", "Landroid/graphics/Bitmap;", "bitmap", "applyGreyScaleAndSetBitmap", "applyGreyScaleFilter", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/music/imageloader/ImageLoader;", "imageLoader", "Lcom/amazon/music/imageloader/ImageLoader;", "Lcom/amazon/music/views/library/providers/FeaturedPlayMusicProvider;", "playIconOnClickMusicProvider", "Lcom/amazon/music/views/library/providers/FeaturedPlayMusicProvider;", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "clickListenerFactory", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "contentViewedListener", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "", "isSonicRushEnabled", "Z", "ITEMS_IN_WIDGET", "I", "ZERO_INDEX", "Ljava/lang/String;", "Landroid/content/Context;", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "LOG", "Lorg/slf4j/Logger;", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "modelClass", "<init>", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/imageloader/ImageLoader;Lcom/amazon/music/views/library/providers/FeaturedPlayMusicProvider;Lcom/amazon/music/views/library/click/ClickListenerFactory;Lcom/amazon/music/uicontentview/ContentViewedListener;Z)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class FeaturePlayBinder implements BaseStateChangeBinder<FeaturePlayView, FeaturePlayModel> {
    private final int ITEMS_IN_WIDGET;
    private final Logger LOG;
    private final String ZERO_INDEX;
    private final ClickListenerFactory clickListenerFactory;
    private final ContentViewedListener contentViewedListener;
    private Context context;
    private final ImageLoader imageLoader;
    private final boolean isSonicRushEnabled;
    private final FeaturedPlayMusicProvider playIconOnClickMusicProvider;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<FeaturePlayModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(FeaturePlayModel.class);
    }

    public FeaturePlayBinder(StyleSheet styleSheet, ImageLoader imageLoader, FeaturedPlayMusicProvider featuredPlayMusicProvider, ClickListenerFactory clickListenerFactory, ContentViewedListener contentViewedListener, boolean z) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(imageLoader, "imageLoader");
        Intrinsics.checkNotNullParameter(clickListenerFactory, "clickListenerFactory");
        Intrinsics.checkNotNullParameter(contentViewedListener, "contentViewedListener");
        this.styleSheet = styleSheet;
        this.imageLoader = imageLoader;
        this.playIconOnClickMusicProvider = featuredPlayMusicProvider;
        this.clickListenerFactory = clickListenerFactory;
        this.contentViewedListener = contentViewedListener;
        this.isSonicRushEnabled = z;
        this.ITEMS_IN_WIDGET = 1;
        this.ZERO_INDEX = "0";
        this.LOG = LoggerFactory.getLogger((Class<?>) FeaturePlayBinder.class);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public FeaturePlayView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        return new FeaturePlayView(context, this.styleSheet, null, 0, 0, this.isSonicRushEnabled, 28, null);
    }

    @Override // com.amazon.music.views.library.binders.BaseStateChangeBinder
    public void handleStateChange(FeaturePlayView view, FeaturePlayModel model, Object payload) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (!Intrinsics.areEqual(payload, (Object) 1) || model.getPlayState() == null) {
            return;
        }
        if (MediaUtil.INSTANCE.isPodcastEntity(model.getContentType())) {
            view.setPodcastPlayState(model.getPlayState());
        } else {
            view.setPlayState(model.getPlayState());
        }
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(FeaturePlayView view, final FeaturePlayModel model) {
        FeaturePlayView.ViewType viewType;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        if (model.getIsNewUX()) {
            viewType = FeaturePlayView.ViewType.VIDEO_LEFT_ALIGNED;
        } else {
            viewType = model.getIsFacetedNavUX() ? FeaturePlayView.ViewType.MUSIC_FACETED_NAVIGATION : FeaturePlayView.ViewType.MUSIC_LEFT_ALIGNED;
        }
        view.initView(viewType);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (marginLayoutParams == null) {
            marginLayoutParams = new ViewGroup.MarginLayoutParams(-1, -2);
        } else {
            marginLayoutParams.width = -1;
            marginLayoutParams.height = -2;
        }
        resetView(view);
        view.setLayoutParams(marginLayoutParams);
        if (model.getMetadata() != null) {
            MediaUtil mediaUtil = MediaUtil.INSTANCE;
            if (mediaUtil.isPodcastEntity(model.getContentType())) {
                view.setEnabled(true);
                view.setClickable(true);
            }
            if (model.getMetadataList() != null) {
                view.setPlayMusicClickListener(ClickListenerFactory.DefaultImpls.create$default(this.clickListenerFactory, model.getMetadataList(), 0, null, 4, null));
            } else {
                view.setPlayMusicClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.FeaturePlayBinder$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        FeaturePlayBinder.m2301bind$lambda2$lambda0(this.f$0, model, view2);
                    }
                });
            }
            if (!mediaUtil.isPodcastEntity(model.getContentType())) {
                view.setBackgroundPlayMusicClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.FeaturePlayBinder$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        FeaturePlayBinder.m2302bind$lambda2$lambda1(this.f$0, model, view2);
                    }
                });
            }
            view.setOnClickListener(this.clickListenerFactory.create(model.getMetadata()));
        }
        if (!view.isWideScreen()) {
            if (model.getIsFacetedNavUX()) {
                loadHeroImageForSmallAndMidScreenLeftAligned(view, model);
            } else {
                loadHeroImageForSmallAndMidScreenLeftAligned(view, model);
                loadHeroBackgroundImageForSmallAndMidScreenLeftAligned(view, model);
            }
        } else {
            loadHeroImageForWideScreen(view, model);
            if (!model.getIsNewUX()) {
                loadBackgroundImageForWideScreen(view, model);
            }
        }
        view.setPlayButton();
        FeaturePlayView.setEqualizer$default(view, null, 1, null);
        view.setLabelText(model.getLabel());
        view.setTitle(model.getTitle());
        view.setPrimaryText(screenSizeAwarePrimaryText(model));
        view.setSecondaryText(model.getSecondaryText());
        view.setTertiaryText(model.getTertiaryText());
        if (MediaUtil.INSTANCE.isPodcastEntity(model.getContentType())) {
            view.setPodcastPlayState(model.getPlayState());
        } else {
            view.setPlayState(model.getPlayState());
        }
        String viewReferenceId = model.getViewReferenceId();
        if (viewReferenceId == null) {
            return;
        }
        reportContentView(viewReferenceId, this.ITEMS_IN_WIDGET);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-2$lambda-0, reason: not valid java name */
    public static final void m2301bind$lambda2$lambda0(FeaturePlayBinder this$0, FeaturePlayModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        FeaturedPlayMusicProvider featuredPlayMusicProvider = this$0.playIconOnClickMusicProvider;
        if (featuredPlayMusicProvider == null) {
            return;
        }
        featuredPlayMusicProvider.startPlayback(model.getMetadata());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-2$lambda-1, reason: not valid java name */
    public static final void m2302bind$lambda2$lambda1(FeaturePlayBinder this$0, FeaturePlayModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        FeaturedPlayMusicProvider featuredPlayMusicProvider = this$0.playIconOnClickMusicProvider;
        if (featuredPlayMusicProvider == null) {
            return;
        }
        featuredPlayMusicProvider.startPlayback(model.getMetadata());
    }

    public final void resetView(FeaturePlayView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setBackground(null);
        view.setClickable(false);
        view.setEnabled(false);
        view.setOnClickListener(null);
    }

    private final void reportContentView(String identifier, int numItemsAccessible) {
        HashMap map = new HashMap();
        map.put("blockRef", identifier);
        map.put("firstViewableIndex", this.ZERO_INDEX);
        map.put("lastViewableIndex", this.ZERO_INDEX);
        String string = Integer.toString(numItemsAccessible);
        Intrinsics.checkNotNullExpressionValue(string, "toString(numItemsAccessible)");
        map.put("numItemsAccessible", string);
        this.contentViewedListener.onItemRendered(identifier, map);
    }

    private final void loadHeroImageForSmallAndMidScreenLeftAligned(FeaturePlayView view, FeaturePlayModel model) {
        String imageUrl = model.getImageUrl();
        if (imageUrl == null) {
            return;
        }
        this.imageLoader.loadImage(imageUrl, new FeaturePlayBinder$loadHeroImageForSmallAndMidScreenLeftAligned$callback$1(view, model, this));
    }

    private final void loadHeroBackgroundImageForSmallAndMidScreenLeftAligned(FeaturePlayView view, FeaturePlayModel model) {
        String imageUrl = model.getImageUrl();
        if (imageUrl == null) {
            return;
        }
        this.imageLoader.loadImage(imageUrl, new FeaturePlayBinder$loadHeroBackgroundImageForSmallAndMidScreenLeftAligned$callback$1(view, this), ImageLoader.TransformationType.Blur2);
    }

    private final void loadHeroImageForWideScreen(FeaturePlayView view, FeaturePlayModel model) {
        String imageUrl = model.getImageUrl();
        if (imageUrl == null) {
            return;
        }
        this.imageLoader.loadImage(imageUrl, new FeaturePlayBinder$loadHeroImageForWideScreen$callback$1(view, model, this));
    }

    private final void loadBackgroundImageForWideScreen(FeaturePlayView view, FeaturePlayModel model) {
        String imageUrl = model.getImageUrl();
        if (imageUrl == null) {
            return;
        }
        this.imageLoader.loadImage(imageUrl, new FeaturePlayBinder$loadBackgroundImageForWideScreen$callback$1(view, this), ImageLoader.TransformationType.Blur2);
    }

    /* JADX INFO: renamed from: com.amazon.music.views.library.binders.FeaturePlayBinder$applyGreyScaleAndSetBitmap$1, reason: invalid class name */
    /* JADX INFO: compiled from: FeaturePlayBinder.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.views.library.binders.FeaturePlayBinder$applyGreyScaleAndSetBitmap$1", f = "FeaturePlayBinder.kt", i = {}, l = {353}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Bitmap $bitmap;
        final /* synthetic */ FeaturePlayView $view;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Bitmap bitmap, FeaturePlayView featurePlayView, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$bitmap = bitmap;
            this.$view = featurePlayView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FeaturePlayBinder.this.new AnonymousClass1(this.$bitmap, this.$view, continuation);
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
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Bitmap bitmapApplyGreyScaleFilter = FeaturePlayBinder.this.applyGreyScaleFilter(this.$bitmap);
                MainCoroutineDispatcher main2 = Dispatchers.getMain();
                C01191 c01191 = new C01191(this.$view, bitmapApplyGreyScaleFilter, null);
                this.label = 1;
                if (BuildersKt.withContext(main2, c01191, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: com.amazon.music.views.library.binders.FeaturePlayBinder$applyGreyScaleAndSetBitmap$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: FeaturePlayBinder.kt */
        @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
        @DebugMetadata(c = "com.amazon.music.views.library.binders.FeaturePlayBinder$applyGreyScaleAndSetBitmap$1$1", f = "FeaturePlayBinder.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01191 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Bitmap $greyScaleBitmap;
            final /* synthetic */ FeaturePlayView $view;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01191(FeaturePlayView featurePlayView, Bitmap bitmap, Continuation<? super C01191> continuation) {
                super(2, continuation);
                this.$view = featurePlayView;
                this.$greyScaleBitmap = bitmap;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01191(this.$view, this.$greyScaleBitmap, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01191) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.$view.getHeroImageView().setImageBitmap(this.$greyScaleBitmap);
                return Unit.INSTANCE;
            }
        }
    }

    public final void applyGreyScaleAndSetBitmap(FeaturePlayView view, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getDefault(), null, new AnonymousClass1(bitmap, view, null), 2, null);
    }

    public final Bitmap applyGreyScaleFilter(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        float width = bitmap.getWidth();
        float height = bitmap.getHeight();
        Bitmap scaledBitmap = BitmapEffectUtils.INSTANCE.getScaledBitmap(bitmap, width, height);
        int i = (int) width;
        int i2 = (int) height;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, i, i2);
        Bitmap bmpGrayscale = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmapCreateBitmap, 0.0f, 0.0f, paint);
        Intrinsics.checkNotNullExpressionValue(bmpGrayscale, "bmpGrayscale");
        return bmpGrayscale;
    }

    private final String screenSizeAwarePrimaryText(FeaturePlayModel model) {
        String primaryText = model.getPrimaryText();
        if (primaryText == null) {
            return null;
        }
        return StringsKt__StringsJVMKt.replace$default(primaryText, "\n", " ", false, 4, (Object) null);
    }
}
