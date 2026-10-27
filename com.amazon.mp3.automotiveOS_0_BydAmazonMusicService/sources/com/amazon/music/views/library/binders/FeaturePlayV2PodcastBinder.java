package com.amazon.music.views.library.binders;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import com.amazon.music.uicontentview.ContentViewedListener;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.models.PodcastFeaturePlayModel;
import com.amazon.music.views.library.providers.FeaturedPlayMusicProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.FeaturePlayV2BaseView;
import com.amazon.music.views.library.views.FeaturePlayV2PodcastView;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.math.MathKt__MathJVMKt;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: FeaturePlayV2PodcastBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B1\u0012\u0006\u0010\u001d\u001a\u00020\u001c\u0012\u0006\u0010\"\u001a\u00020!\u0012\u0006\u0010'\u001a\u00020&\u0012\u0006\u0010*\u001a\u00020)\u0012\b\b\u0002\u0010,\u001a\u00020\u0012¢\u0006\u0004\bD\u0010EJ\u0018\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0002H\u0002J \u0010\u000f\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006H\u0002J\u0018\u0010\u0010\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0002H\u0002J\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0003H\u0002J\b\u0010\u0013\u001a\u00020\u0012H\u0002J\u0010\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0014H\u0016J \u0010\u0019\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0017H\u0016J\u0018\u0010\u001a\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0003H\u0016J\u001e\u0010\u001b\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006R\u001a\u0010\u001d\u001a\u00020\u001c8\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001a\u0010\"\u001a\u00020!8\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%R\u0014\u0010'\u001a\u00020&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010*\u001a\u00020)8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+R\u001a\u0010,\u001a\u00020\u00128\u0004X\u0084\u0004¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b,\u0010.R\u0014\u0010/\u001a\u00020\u00068\u0002X\u0082D¢\u0006\u0006\n\u0004\b/\u00100R\u0014\u00101\u001a\u00020\u00068\u0002X\u0082D¢\u0006\u0006\n\u0004\b1\u00100R\u0014\u00102\u001a\u00020\u00048\u0002X\u0082D¢\u0006\u0006\n\u0004\b2\u00103R\u0014\u00104\u001a\u00020\u00048\u0002X\u0082D¢\u0006\u0006\n\u0004\b4\u00103R\"\u0010\u0015\u001a\u00020\u00148\u0004@\u0004X\u0084.¢\u0006\u0012\n\u0004\b\u0015\u00105\u001a\u0004\b6\u00107\"\u0004\b8\u00109R$\u0010\u000b\u001a\u0004\u0018\u00010:8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010;\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u001a\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00030@8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bA\u0010B¨\u0006F"}, d2 = {"Lcom/amazon/music/views/library/binders/FeaturePlayV2PodcastBinder;", "Lcom/amazon/music/views/library/binders/BaseStateChangeBinder;", "Lcom/amazon/music/views/library/views/FeaturePlayV2PodcastView;", "Lcom/amazon/music/views/library/models/PodcastFeaturePlayModel;", "", "identifier", "", "numItemsAccessible", "", "reportContentView", "model", "view", "bindText", "backgroundImageViewHeight", "backgroundImageViewWidth", "bindImages", "bindBackGroundClickListener", "bindPlayback", "", "isScreenSizeGreaterThanMD", "Landroid/content/Context;", "context", "createView", "", "payload", "handleStateChange", "bind", "animateViews", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/music/views/library/providers/FeaturedPlayMusicProvider;", "playIconOnClickMusicProvider", "Lcom/amazon/music/views/library/providers/FeaturedPlayMusicProvider;", "getPlayIconOnClickMusicProvider", "()Lcom/amazon/music/views/library/providers/FeaturedPlayMusicProvider;", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "clickListenerFactory", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "contentViewedListener", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "isSonicRushEnabled", "Z", "()Z", "MD_WIDTH", "I", "ITEMS_IN_WIDGET", "ZERO_INDEX", "Ljava/lang/String;", "DEFAULT_IMAGE_URL", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "Lcom/amazon/music/views/library/views/FeaturePlayV2BaseView;", "Lcom/amazon/music/views/library/views/FeaturePlayV2BaseView;", "getView", "()Lcom/amazon/music/views/library/views/FeaturePlayV2BaseView;", "setView", "(Lcom/amazon/music/views/library/views/FeaturePlayV2BaseView;)V", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "modelClass", "<init>", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/providers/FeaturedPlayMusicProvider;Lcom/amazon/music/views/library/click/ClickListenerFactory;Lcom/amazon/music/uicontentview/ContentViewedListener;Z)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public class FeaturePlayV2PodcastBinder implements BaseStateChangeBinder<FeaturePlayV2PodcastView, PodcastFeaturePlayModel> {
    private final String DEFAULT_IMAGE_URL;
    private final int ITEMS_IN_WIDGET;
    private final int MD_WIDTH;
    private final String ZERO_INDEX;
    private final ClickListenerFactory clickListenerFactory;
    private final ContentViewedListener contentViewedListener;
    protected Context context;
    private final boolean isSonicRushEnabled;
    private final FeaturedPlayMusicProvider playIconOnClickMusicProvider;
    private final StyleSheet styleSheet;
    private FeaturePlayV2BaseView view;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<PodcastFeaturePlayModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(PodcastFeaturePlayModel.class);
    }

    public FeaturePlayV2PodcastBinder(StyleSheet styleSheet, FeaturedPlayMusicProvider playIconOnClickMusicProvider, ClickListenerFactory clickListenerFactory, ContentViewedListener contentViewedListener, boolean z) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(playIconOnClickMusicProvider, "playIconOnClickMusicProvider");
        Intrinsics.checkNotNullParameter(clickListenerFactory, "clickListenerFactory");
        Intrinsics.checkNotNullParameter(contentViewedListener, "contentViewedListener");
        this.styleSheet = styleSheet;
        this.playIconOnClickMusicProvider = playIconOnClickMusicProvider;
        this.clickListenerFactory = clickListenerFactory;
        this.contentViewedListener = contentViewedListener;
        this.isSonicRushEnabled = z;
        this.MD_WIDTH = 480;
        this.ITEMS_IN_WIDGET = 1;
        this.ZERO_INDEX = "0";
        this.DEFAULT_IMAGE_URL = "https://m.media-amazon.com/images/G/01/dmpexperience/emptyStateImage3x.png";
    }

    protected final Context getContext() {
        Context context = this.context;
        if (context != null) {
            return context;
        }
        Intrinsics.throwUninitializedPropertyAccessException("context");
        return null;
    }

    protected final void setContext(Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.context = context;
    }

    public FeaturePlayV2BaseView getView() {
        return this.view;
    }

    public void setView(FeaturePlayV2BaseView featurePlayV2BaseView) {
        this.view = featurePlayV2BaseView;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public FeaturePlayV2PodcastView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        setContext(context);
        FeaturePlayV2PodcastView featurePlayV2PodcastView = new FeaturePlayV2PodcastView(context, this.styleSheet, null, isScreenSizeGreaterThanMD(), this.playIconOnClickMusicProvider, 0, 0, this.isSonicRushEnabled, 96, null);
        featurePlayV2PodcastView.init();
        return featurePlayV2PodcastView;
    }

    @Override // com.amazon.music.views.library.binders.BaseStateChangeBinder
    public void handleStateChange(FeaturePlayV2PodcastView view, PodcastFeaturePlayModel model, Object payload) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (!Intrinsics.areEqual(payload, (Object) 1) || model.getPlayState() == null) {
            return;
        }
        view.setPlayState(model);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(FeaturePlayV2PodcastView view, PodcastFeaturePlayModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        setView(view);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (marginLayoutParams == null) {
            marginLayoutParams = new ViewGroup.MarginLayoutParams(-1, -2);
        } else {
            marginLayoutParams.width = -1;
            marginLayoutParams.height = -2;
        }
        if (!isScreenSizeGreaterThanMD()) {
            int iRoundToInt = MathKt__MathJVMKt.roundToInt(getContext().getResources().getDimension(R$dimen.spacer_medium));
            int iRoundToInt2 = MathKt__MathJVMKt.roundToInt(getContext().getResources().getDimension(R$dimen.spacer_epic));
            marginLayoutParams.setMargins(iRoundToInt, iRoundToInt2 + MathKt__MathJVMKt.roundToInt(getContext().getResources().getDimension(R$dimen.spacer_huge)), iRoundToInt, MathKt__MathJVMKt.roundToInt(getContext().getResources().getDimension(R$dimen.spacer_large)));
        }
        view.setLayoutParams(marginLayoutParams);
        bindText(model, view);
        bindBackGroundClickListener(model, view);
        bindPlayback(model);
        String viewReferenceId = model.getViewReferenceId();
        if (viewReferenceId == null) {
            return;
        }
        reportContentView(viewReferenceId, this.ITEMS_IN_WIDGET);
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

    private final void bindText(PodcastFeaturePlayModel model, FeaturePlayV2PodcastView view) {
        view.setLabelText(model.getLabel());
        view.setTitleText(model.getTitle());
        view.setPrimaryText(model.getPrimaryText());
        if (isScreenSizeGreaterThanMD()) {
            view.setSecondaryText(model.getSecondaryText());
            view.setTertiaryText(model.getTertiaryText());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void bindImages(PodcastFeaturePlayModel model, int backgroundImageViewHeight, int backgroundImageViewWidth) {
        Object imageUrl;
        String imageUrl2 = model.getImageUrl();
        if (imageUrl2 == null || imageUrl2.length() == 0) {
            imageUrl = this.DEFAULT_IMAGE_URL;
        } else {
            imageUrl = model.getImageUrl();
        }
        List<String> imageUrls = model.getImageUrls();
        if (imageUrls != null && (true ^ imageUrls.isEmpty())) {
            imageUrl = CollectionsKt___CollectionsKt.first((List<? extends Object>) imageUrls);
        }
        if (isScreenSizeGreaterThanMD()) {
            FeaturePlayV2BaseView view = getView();
            if (view == null) {
                return;
            }
            view.bindImagesForWideScreen((String) imageUrl, backgroundImageViewHeight);
            return;
        }
        FeaturePlayV2BaseView view2 = getView();
        if (view2 == null) {
            return;
        }
        view2.bindImagesForNormalScreen(view2.getBackgroundImageView(), view2.getForegroundImageView(), (String) imageUrl, backgroundImageViewHeight, backgroundImageViewWidth);
    }

    private final void bindBackGroundClickListener(final PodcastFeaturePlayModel model, final FeaturePlayV2PodcastView view) {
        view.getBackgroundImageView().setClickable(true);
        view.getBackgroundImageView().setOnClickListener(this.clickListenerFactory.create(model.getMetadata()));
        ViewTreeObserver viewTreeObserver = view.getBackgroundImageView().getViewTreeObserver();
        if (viewTreeObserver == null) {
            return;
        }
        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.amazon.music.views.library.binders.FeaturePlayV2PodcastBinder.bindBackGroundClickListener.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                int measuredHeight = view.getBackgroundImageView().getMeasuredHeight();
                int measuredWidth = view.getBackgroundImageView().getMeasuredWidth();
                ViewTreeObserver viewTreeObserver2 = view.getBackgroundImageView().getViewTreeObserver();
                if (viewTreeObserver2 != null) {
                    viewTreeObserver2.removeOnPreDrawListener(this);
                }
                this.bindImages(model, measuredHeight, measuredWidth);
                this.animateViews(model, measuredHeight, measuredWidth);
                return true;
            }
        });
    }

    public final void animateViews(PodcastFeaturePlayModel model, int backgroundImageViewHeight, int backgroundImageViewWidth) {
        FeaturePlayV2BaseView view;
        Intrinsics.checkNotNullParameter(model, "model");
        if (model.getImageUrls() == null || model.getImageUrls().isEmpty() || (view = getView()) == null) {
            return;
        }
        view.executeAnimationSequence(0, model.getImageUrls(), backgroundImageViewHeight, backgroundImageViewWidth);
    }

    private final void bindPlayback(PodcastFeaturePlayModel model) {
        FeaturePlayV2BaseView view = getView();
        if (view == null) {
            return;
        }
        view.setPlayState(model);
    }

    private final boolean isScreenSizeGreaterThanMD() {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        Intrinsics.checkNotNullExpressionValue(displayMetrics, "context.resources.displayMetrics");
        return ((float) displayMetrics.widthPixels) / displayMetrics.density >= ((float) this.MD_WIDTH);
    }
}
