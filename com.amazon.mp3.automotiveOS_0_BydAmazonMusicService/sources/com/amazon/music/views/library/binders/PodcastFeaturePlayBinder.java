package com.amazon.music.views.library.binders;

import android.content.Context;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import com.amazon.music.uicontentview.ContentViewedListener;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.models.PodcastFeaturePlayModel;
import com.amazon.music.views.library.providers.FeaturedPlayMusicProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.PodcastFeaturePlayView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: PodcastFeaturePlayBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B/\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0018\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u0003H\u0016J\u0018\u0010\u001f\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0002H\u0002J\u0018\u0010 \u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u0010H\u0002J\u0010\u0010\"\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0003H\u0002J\u0018\u0010#\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0002H\u0002J\u0010\u0010$\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J \u0010%\u001a\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010&\u001a\u00020'H\u0016J\b\u0010(\u001a\u00020\rH\u0002J\u0012\u0010)\u001a\u00020\r2\b\u0010*\u001a\u0004\u0018\u00010+H\u0002J\u0018\u0010,\u001a\u00020\u001d2\u0006\u0010-\u001a\u00020\u00132\u0006\u0010.\u001a\u00020\u0010H\u0002J\u000e\u0010/\u001a\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\u0002R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0013X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0002X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/amazon/music/views/library/binders/PodcastFeaturePlayBinder;", "Lcom/amazon/music/views/library/binders/BaseStateChangeBinder;", "Lcom/amazon/music/views/library/views/PodcastFeaturePlayView;", "Lcom/amazon/music/views/library/models/PodcastFeaturePlayModel;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "playIconOnClickMusicProvider", "Lcom/amazon/music/views/library/providers/FeaturedPlayMusicProvider;", "clickListenerFactory", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "contentViewedListener", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "isSonicRushEnabled", "", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/providers/FeaturedPlayMusicProvider;Lcom/amazon/music/views/library/click/ClickListenerFactory;Lcom/amazon/music/uicontentview/ContentViewedListener;Z)V", "ITEMS_IN_WIDGET", "", "MD_WIDTH", "ZERO_INDEX", "", "context", "Landroid/content/Context;", "defaultImageUrl", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "view", "bind", "", "model", "bindBackGroundClickListener", "bindImages", "backgroundImageViewHeight", "bindPlayback", "bindText", "createView", "handleStateChange", "payload", "", "isScreenSizeGreaterThanMD", "isTextViewEllipsized", "textViewPodcast", "Landroid/widget/TextView;", "reportContentView", "identifier", "numItemsAccessible", "resetView", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PodcastFeaturePlayBinder implements BaseStateChangeBinder<PodcastFeaturePlayView, PodcastFeaturePlayModel> {
    private final int ITEMS_IN_WIDGET;
    private final int MD_WIDTH;
    private final String ZERO_INDEX;
    private final ClickListenerFactory clickListenerFactory;
    private final ContentViewedListener contentViewedListener;
    private Context context;
    private final String defaultImageUrl;
    private final boolean isSonicRushEnabled;
    private final FeaturedPlayMusicProvider playIconOnClickMusicProvider;
    private final StyleSheet styleSheet;
    private PodcastFeaturePlayView view;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<PodcastFeaturePlayModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(PodcastFeaturePlayModel.class);
    }

    public PodcastFeaturePlayBinder(StyleSheet styleSheet, FeaturedPlayMusicProvider playIconOnClickMusicProvider, ClickListenerFactory clickListenerFactory, ContentViewedListener contentViewedListener, boolean z) {
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
        this.defaultImageUrl = "https://m.media-amazon.com/images/G/01/dmpexperience/emptyStateImage3x.png";
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public PodcastFeaturePlayView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        PodcastFeaturePlayView podcastFeaturePlayView = new PodcastFeaturePlayView(context, this.styleSheet, null, isScreenSizeGreaterThanMD(), this.playIconOnClickMusicProvider, 0, 0, this.isSonicRushEnabled, 96, null);
        podcastFeaturePlayView.init();
        return podcastFeaturePlayView;
    }

    @Override // com.amazon.music.views.library.binders.BaseStateChangeBinder
    public void handleStateChange(PodcastFeaturePlayView view, PodcastFeaturePlayModel model, Object payload) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (!Intrinsics.areEqual(payload, (Object) 1) || model.getPlayState() == null) {
            return;
        }
        view.setPlayState(model);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(PodcastFeaturePlayView view, PodcastFeaturePlayModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        this.view = view;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (marginLayoutParams == null) {
            marginLayoutParams = new ViewGroup.MarginLayoutParams(-1, -2);
        } else {
            marginLayoutParams.width = -1;
            marginLayoutParams.height = -2;
        }
        resetView(view);
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

    public final void resetView(PodcastFeaturePlayView view) {
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

    private final void bindText(PodcastFeaturePlayModel model, final PodcastFeaturePlayView view) {
        view.setLabelText(model.getLabel());
        view.setTitleText(model.getTitle());
        view.setPrimaryText(model.getPrimaryText());
        view.setSecondaryText(model.getSecondaryText());
        view.setTertiaryText(model.getTertiaryText());
        ViewTreeObserver viewTreeObserver = view.getTitleTextViewPodcast().getViewTreeObserver();
        Intrinsics.checkNotNullExpressionValue(viewTreeObserver, "view.titleTextViewPodcast.getViewTreeObserver()");
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.music.views.library.binders.PodcastFeaturePlayBinder.bindText.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (PodcastFeaturePlayBinder.this.isTextViewEllipsized(view.getTitleTextViewPodcast())) {
                    TextView titleTextViewPodcast = view.getTitleTextViewPodcast();
                    Context context = PodcastFeaturePlayBinder.this.context;
                    if (context == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context = null;
                    }
                    titleTextViewPodcast.setTextSize(0, context.getResources().getDimension(R$dimen.truncated_text_size));
                }
                view.getTitleTextViewPodcast().getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void bindImages(com.amazon.music.views.library.models.PodcastFeaturePlayModel r2, int r3) {
        /*
            r1 = this;
            java.lang.String r0 = r2.getImageUrl()
            if (r0 == 0) goto L1b
            java.lang.String r0 = r2.getImageUrl()
            int r0 = r0.length()
            if (r0 != 0) goto L12
            r0 = 1
            goto L13
        L12:
            r0 = 0
        L13:
            if (r0 == 0) goto L16
            goto L1b
        L16:
            java.lang.String r2 = r2.getImageUrl()
            goto L1d
        L1b:
            java.lang.String r2 = r1.defaultImageUrl
        L1d:
            boolean r0 = r1.isScreenSizeGreaterThanMD()
            if (r0 == 0) goto L2c
            com.amazon.music.views.library.views.PodcastFeaturePlayView r0 = r1.view
            if (r0 != 0) goto L28
            goto L34
        L28:
            r0.bindImagesForWideScreen(r2, r3)
            goto L34
        L2c:
            com.amazon.music.views.library.views.PodcastFeaturePlayView r0 = r1.view
            if (r0 != 0) goto L31
            goto L34
        L31:
            r0.bindImagesForNormalScreen(r2, r3)
        L34:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.views.library.binders.PodcastFeaturePlayBinder.bindImages(com.amazon.music.views.library.models.PodcastFeaturePlayModel, int):void");
    }

    private final void bindBackGroundClickListener(final PodcastFeaturePlayModel model, final PodcastFeaturePlayView view) {
        ImageView backgroundImageView = view.getBackgroundImageView();
        if (backgroundImageView != null) {
            backgroundImageView.setClickable(true);
        }
        ImageView backgroundImageView2 = view.getBackgroundImageView();
        if (backgroundImageView2 != null) {
            backgroundImageView2.setOnClickListener(this.clickListenerFactory.create(model.getMetadata()));
        }
        ImageView backgroundImageView3 = view.getBackgroundImageView();
        ViewTreeObserver viewTreeObserver = backgroundImageView3 == null ? null : backgroundImageView3.getViewTreeObserver();
        if (viewTreeObserver == null) {
            return;
        }
        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.amazon.music.views.library.binders.PodcastFeaturePlayBinder.bindBackGroundClickListener.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                ViewTreeObserver viewTreeObserver2;
                ImageView backgroundImageView4 = view.getBackgroundImageView();
                if (backgroundImageView4 != null && (viewTreeObserver2 = backgroundImageView4.getViewTreeObserver()) != null) {
                    viewTreeObserver2.removeOnPreDrawListener(this);
                }
                ImageView backgroundImageView5 = view.getBackgroundImageView();
                this.bindImages(model, (backgroundImageView5 == null ? null : Integer.valueOf(backgroundImageView5.getMeasuredHeight())).intValue());
                return true;
            }
        });
    }

    private final void bindPlayback(PodcastFeaturePlayModel model) {
        PodcastFeaturePlayView podcastFeaturePlayView;
        if (model == null || (podcastFeaturePlayView = this.view) == null) {
            return;
        }
        podcastFeaturePlayView.setPlayState(model);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isTextViewEllipsized(TextView textViewPodcast) {
        Layout layout;
        return textViewPodcast != null && (layout = textViewPodcast.getLayout()) != null && layout.getLineCount() > 1 && layout.getEllipsisCount(1) > 0;
    }

    private final boolean isScreenSizeGreaterThanMD() {
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Intrinsics.checkNotNullExpressionValue(displayMetrics, "context.resources.displayMetrics");
        return ((float) displayMetrics.widthPixels) / displayMetrics.density >= ((float) this.MD_WIDTH);
    }
}
