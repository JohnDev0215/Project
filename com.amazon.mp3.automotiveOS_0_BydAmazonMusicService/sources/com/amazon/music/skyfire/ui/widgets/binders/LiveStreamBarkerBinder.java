package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.LiveStreamBarkerElement;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.picassoimageloader.PicassoImageLoader;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.utils.OnViewedTrigger;
import com.amazon.music.skyfire.ui.utils.ScreenUtils;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.LivestreamBarkerView;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.styles.ImageSize;
import com.google.android.gms.common.internal.ImagesContract;
import com.squareup.picasso.Picasso;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LiveStreamBarkerBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0003H\u0016J\u0012\u0010\u0010\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J0\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0005\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0002H\u0002J(\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0005\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0010\u0010\u001e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0003H\u0002R#\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\u001f"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/LiveStreamBarkerBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/LiveStreamBarkerElement;", "Lcom/amazon/music/views/library/views/LivestreamBarkerView;", "()V", "imageLoader", "Lcom/amazon/music/picassoimageloader/PicassoImageLoader;", "kotlin.jvm.PlatformType", "getImageLoader", "()Lcom/amazon/music/picassoimageloader/PicassoImageLoader;", "imageLoader$delegate", "Lkotlin/Lazy;", "bind", "", "element", "view", "createView", "parent", "Landroid/view/ViewGroup;", "loadBackgroundImage", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/music/imageloader/ImageLoader;", "loadSourceLogoImage", "sourceLogo", "Landroid/widget/ImageView;", ImagesContract.URL, "", "setMargins", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LiveStreamBarkerBinder extends ElementBinder<LiveStreamBarkerElement, LivestreamBarkerView> {

    /* JADX INFO: renamed from: imageLoader$delegate, reason: from kotlin metadata */
    private final Lazy imageLoader = LazyKt__LazyJVMKt.lazy(new Function0<PicassoImageLoader>() { // from class: com.amazon.music.skyfire.ui.widgets.binders.LiveStreamBarkerBinder$imageLoader$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final PicassoImageLoader invoke() {
            return PicassoImageLoader.builder(this.this$0.getContext(), Picasso.get()).withTag(this.this$0.getContext()).build();
        }
    });

    private final PicassoImageLoader getImageLoader() {
        return (PicassoImageLoader) this.imageLoader.getValue();
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public LivestreamBarkerView createView(ViewGroup parent) {
        return new LivestreamBarkerView(getContext(), getStyleSheet(), null, 0, 0, 28, null);
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(final LiveStreamBarkerElement element, LivestreamBarkerView view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (marginLayoutParams == null) {
            marginLayoutParams = new ViewGroup.MarginLayoutParams(-1, -2);
        } else {
            marginLayoutParams.width = -1;
            marginLayoutParams.height = -2;
        }
        Object systemService = getContext().getSystemService("window");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.WindowManager");
        }
        ((WindowManager) systemService).getDefaultDisplay().getMetrics(new DisplayMetrics());
        GridSize gridSize = getStyleSheet().getGridSize(getContext());
        int marginSpace = gridSize == null ? 0 : gridSize.getMarginSpace();
        int columnSpace = (int) ((r2.widthPixels - (marginSpace * 2)) - ((getStyleSheet().getGridSize(getContext()) == null ? 0 : r4.getColumnSpace()) * 2));
        float fraction = getContext().getResources().getFraction(R.fraction.feature_barker_aspect_ratio, 1, 1);
        marginLayoutParams.width = columnSpace;
        view.getBackgroundImageView().getLayoutParams().height = (int) (columnSpace / fraction);
        view.setLayoutParams(marginLayoutParams);
        Context context = getContext();
        StyleSheet styleSheet = getStyleSheet();
        PicassoImageLoader imageLoader = getImageLoader();
        Intrinsics.checkNotNullExpressionValue(imageLoader, "imageLoader");
        loadBackgroundImage(context, styleSheet, imageLoader, view, element);
        view.getArtistArtworkImageView().setVisibility(8);
        ImageView sourceLogoImageView = view.getSourceLogoImageView();
        sourceLogoImageView.setVisibility(0);
        StyleSheet styleSheet2 = getStyleSheet();
        PicassoImageLoader imageLoader2 = getImageLoader();
        Intrinsics.checkNotNullExpressionValue(imageLoader2, "imageLoader");
        String strWordmark = element.wordmark();
        Intrinsics.checkNotNullExpressionValue(strWordmark, "element.wordmark()");
        loadSourceLogoImage(styleSheet2, imageLoader2, sourceLogoImageView, strWordmark);
        view.setSourceLogoMargins();
        String strViewers = element.viewers();
        Intrinsics.checkNotNullExpressionValue(strViewers, "element.viewers()");
        view.setViewerCount(strViewers);
        view.setPrimaryTitle(element.primaryText());
        view.setSecondaryTitle(element.secondaryText());
        CharSequence charSequenceTertiaryText = element.tertiaryText();
        if (charSequenceTertiaryText == null) {
            charSequenceTertiaryText = "";
        }
        view.setTertiaryTitle(charSequenceTertiaryText);
        view.setLiveContentGradient(columnSpace);
        setMargins(view);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.skyfire.ui.widgets.binders.LiveStreamBarkerBinder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                LiveStreamBarkerBinder.m2230bind$lambda1$lambda0(this.f$0, element, view2);
            }
        });
        OnViewedTrigger.Companion companion = OnViewedTrigger.INSTANCE;
        DispatcherContext dispatcherContext = getDispatcherContext();
        List<Method> listOnViewed = element.onViewed();
        Intrinsics.checkNotNullExpressionValue(listOnViewed, "element.onViewed()");
        companion.emitOnViewed(dispatcherContext, listOnViewed);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-1$lambda-0, reason: not valid java name */
    public static final void m2230bind$lambda1$lambda0(LiveStreamBarkerBinder this$0, LiveStreamBarkerElement element, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(element, "$element");
        MethodsDispatcher<Method> methodsDispatcher = this$0.getMethodsDispatcher();
        String ownerId = this$0.getOwnerId();
        List<Method> listOnItemSelected = element.onItemSelected();
        Intrinsics.checkNotNullExpressionValue(listOnItemSelected, "element.onItemSelected()");
        methodsDispatcher.dispatchMethods(ownerId, listOnItemSelected);
    }

    private final void setMargins(LivestreamBarkerView view) {
        int dimensionPixelSize;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        ScreenUtils.Companion companion = ScreenUtils.INSTANCE;
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        if (companion.isLandscape(context)) {
            dimensionPixelSize = view.getResources().getDimensionPixelSize(R.dimen.spacer_giant);
        } else {
            dimensionPixelSize = view.getResources().getDimensionPixelSize(R.dimen.spacer_medium);
        }
        marginLayoutParams.leftMargin = dimensionPixelSize;
        marginLayoutParams.rightMargin = dimensionPixelSize;
        marginLayoutParams.topMargin = view.getResources().getDimensionPixelSize(R.dimen.spacer_large);
        view.setLayoutParams(marginLayoutParams);
    }

    private final void loadSourceLogoImage(StyleSheet styleSheet, ImageLoader imageLoader, ImageView sourceLogo, String url) {
        imageLoader.loadImage(url, new LiveStreamBarkerBinder$loadSourceLogoImage$callback$1(sourceLogo, styleSheet.getSpacerInPixels(SpacerKey.SMALL) == null ? 0.0f : r2.intValue()));
    }

    private final void loadBackgroundImage(Context context, StyleSheet styleSheet, ImageLoader imageLoader, LivestreamBarkerView view, LiveStreamBarkerElement element) {
        int i = view.getLayoutParams().width;
        String strThumbnailImage = element.thumbnailImage();
        Picasso.get().invalidate(strThumbnailImage);
        ImageSize imageSize = styleSheet.getImageSize(i);
        if (imageSize != null) {
            view.getBackgroundImageView().setImageSize(imageSize);
        }
        view.getBackgroundImageView().setScalingFactor(context.getResources().getFraction(R.fraction.feature_barker_aspect_ratio, 1, 1));
        imageLoader.loadImage(strThumbnailImage, new LiveStreamBarkerBinder$loadBackgroundImage$callback$1(view, context, styleSheet));
    }
}
