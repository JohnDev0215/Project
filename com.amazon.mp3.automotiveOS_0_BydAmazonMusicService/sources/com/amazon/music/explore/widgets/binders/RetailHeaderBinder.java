package com.amazon.music.explore.widgets.binders;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.ButtonElement;
import Touch.WidgetsInterface.v1_0.ImageResources;
import Touch.WidgetsInterface.v1_0.RetailHeaderElement;
import Touch.WidgetsInterface.v1_0.TextButtonElement;
import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.explore.widgets.adapters.ImageCarouselAdapter;
import com.amazon.music.explore.widgets.decorators.PageDotsDecorator;
import com.amazon.music.explore.widgets.views.RetailHeaderView;
import com.amazon.music.picassoimageloader.PicassoImageLoader;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.ui.Constants;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.ktx.ViewKt;
import com.amazon.music.skyfire.ui.widgets.binders.ElementBinder;
import com.amazon.music.skyfire.ui.widgets.binders.TextButtonBinder;
import com.amazon.music.skyfire.ui.widgets.decorators.VerticalGradientDecorator;
import com.amazon.music.views.library.binders.ImageBinder;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.ktx.TextViewKt;
import com.squareup.picasso.Picasso;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt__RangesKt;

/* JADX INFO: compiled from: RetailHeaderBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001a2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0003H\u0016J\u0018\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0016H\u0002J\u0012\u0010\u0017\u001a\u00020\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR#\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\n\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/explore/widgets/binders/RetailHeaderBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/RetailHeaderElement;", "Lcom/amazon/music/explore/widgets/views/RetailHeaderView;", "()V", "imageBinder", "Lcom/amazon/music/views/library/binders/ImageBinder;", "getImageBinder", "()Lcom/amazon/music/views/library/binders/ImageBinder;", "imageBinder$delegate", "Lkotlin/Lazy;", "picassoImageLoader", "Lcom/amazon/music/picassoimageloader/PicassoImageLoader;", "kotlin.jvm.PlatformType", "getPicassoImageLoader", "()Lcom/amazon/music/picassoimageloader/PicassoImageLoader;", "picassoImageLoader$delegate", "bind", "", "element", "view", "bindCarouselImages", "Landroidx/recyclerview/widget/RecyclerView;", "createView", "parent", "Landroid/view/ViewGroup;", "Companion", "DMMExplore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RetailHeaderBinder extends ElementBinder<RetailHeaderElement, RetailHeaderView> {
    private static final int GRADIENT_OVERLAY_ALPHA = 229;
    private static final ClosedFloatingPointRange<Float> GRADIENT_RANGE = RangesKt__RangesKt.rangeTo(0.8f, 1.0f);

    /* JADX INFO: renamed from: picassoImageLoader$delegate, reason: from kotlin metadata */
    private final Lazy picassoImageLoader = LazyKt__LazyJVMKt.lazy(new Function0<PicassoImageLoader>() { // from class: com.amazon.music.explore.widgets.binders.RetailHeaderBinder$picassoImageLoader$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final PicassoImageLoader invoke() {
            return PicassoImageLoader.builder(this.this$0.getContext(), Picasso.get()).withTag(this.this$0.getContext()).build();
        }
    });

    /* JADX INFO: renamed from: imageBinder$delegate, reason: from kotlin metadata */
    private final Lazy imageBinder = LazyKt__LazyJVMKt.lazy(new Function0<ImageBinder>() { // from class: com.amazon.music.explore.widgets.binders.RetailHeaderBinder$imageBinder$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ImageBinder invoke() {
            Context context = this.this$0.getContext();
            StyleSheet styleSheet = this.this$0.getStyleSheet();
            PicassoImageLoader picassoImageLoader = this.this$0.getPicassoImageLoader();
            Intrinsics.checkNotNullExpressionValue(picassoImageLoader, "picassoImageLoader");
            return new ImageBinder(context, styleSheet, picassoImageLoader);
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public final PicassoImageLoader getPicassoImageLoader() {
        return (PicassoImageLoader) this.picassoImageLoader.getValue();
    }

    private final ImageBinder getImageBinder() {
        return (ImageBinder) this.imageBinder.getValue();
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public RetailHeaderView createView(ViewGroup parent) {
        RetailHeaderView retailHeaderView = new RetailHeaderView(getContext());
        new PagerSnapHelper().attachToRecyclerView(retailHeaderView.getCarouselImages());
        return retailHeaderView;
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(RetailHeaderElement element, RetailHeaderView view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        bindCarouselImages(element, view.getCarouselImages());
        ImageBinder.bind$default(getImageBinder(), view.getImage(), element.image(), 4, 0, 8, null);
        if (element.primaryBadge() != null) {
            ViewKt.setImageUrl$default(view.getPrimaryBadge(), element.primaryBadge(), null, 2, null);
            view.getPrimaryBadge().setContentDescription(element.primaryBadgeDescription());
        }
        ViewKt.applyFontStyle(view.getLabel(), getStyleSheet(), FontStyleKey.LABEL);
        view.getLabel().setText(element.label());
        ViewKt.applyFontStyle(view.getPrimaryText(), getStyleSheet(), FontStyleKey.HEADLINE_4);
        view.getPrimaryText().setText(element.primaryText());
        ViewKt.applyFontStyle(view.getSecondaryText(), getStyleSheet(), FontStyleKey.TERTIARY);
        view.getSecondaryText().setText(element.secondaryText());
        if (element.callToActionButton() instanceof TextButtonElement) {
            TextButtonBinder textButtonBinder = new TextButtonBinder();
            textButtonBinder.setButtonSizeKey(ButtonSizeKey.LARGE);
            textButtonBinder.setButtonStyleKey(ButtonStyleKey.GLASS);
            textButtonBinder.setBindingProvider(getBindingProvider());
            ButtonElement buttonElementCallToActionButton = element.callToActionButton();
            if (buttonElementCallToActionButton != null) {
                textButtonBinder.bind((TextButtonElement) buttonElementCallToActionButton, view.getCallToActionButton());
            } else {
                throw new NullPointerException("null cannot be cast to non-null type Touch.WidgetsInterface.v1_0.TextButtonElement");
            }
        }
        ImageView image = view.getImage();
        String strImage = element.image();
        com.amazon.ui.foundations.ktx.ViewKt.collapseIf(image, strImage == null || strImage.length() == 0);
        TextViewKt.collapseIfEmpty(view.getLabel());
        ImageView primaryBadge = view.getPrimaryBadge();
        String strPrimaryBadge = element.primaryBadge();
        com.amazon.ui.foundations.ktx.ViewKt.collapseIf(primaryBadge, strPrimaryBadge == null || strPrimaryBadge.length() == 0);
        TextViewKt.collapseIfEmpty(view.getPrimaryText());
        TextViewKt.collapseIfEmpty(view.getSecondaryText());
        com.amazon.ui.foundations.ktx.ViewKt.collapseIf(view.getCallToActionButton(), element.callToActionButton() == null);
        MethodsDispatcher<Method> methodsDispatcher = getMethodsDispatcher();
        String ownerId = getOwnerId();
        List<Method> listOnViewed = element.onViewed();
        Intrinsics.checkNotNullExpressionValue(listOnViewed, "element.onViewed()");
        methodsDispatcher.dispatchMethods(ownerId, listOnViewed);
    }

    private final void bindCarouselImages(RetailHeaderElement element, RecyclerView view) {
        int iIntValue;
        ImageResources.Image image;
        List<String> images = element.carouselImages();
        ImageCarouselAdapter imageCarouselAdapter = new ImageCarouselAdapter();
        ImageResources imageResourcesPlaceholder = element.placeholder();
        Integer num = null;
        if (imageResourcesPlaceholder != null && (image = imageResourcesPlaceholder.image()) != null) {
            num = Constants.INSTANCE.getResMap().get(image);
        }
        if (num != null) {
            iIntValue = num.intValue();
        } else {
            iIntValue = R.drawable.merch_placeholder;
        }
        imageCarouselAdapter.setPlaceholderResId(iIntValue);
        Intrinsics.checkNotNullExpressionValue(images, "images");
        imageCarouselAdapter.addItems(images);
        VerticalGradientDecorator verticalGradientDecorator = new VerticalGradientDecorator();
        verticalGradientDecorator.setRange(GRADIENT_RANGE);
        verticalGradientDecorator.setStartColor(0);
        Integer color = getStyleSheet().getColor(ColorKey.COLOR_SECONDARY);
        Intrinsics.checkNotNull(color);
        int iIntValue2 = color.intValue();
        verticalGradientDecorator.setEndColor(Color.argb(GRADIENT_OVERLAY_ALPHA, Color.red(iIntValue2), Color.green(iIntValue2), Color.blue(iIntValue2)));
        view.addItemDecoration(verticalGradientDecorator);
        view.addItemDecoration(new PageDotsDecorator(getContext(), images.size()));
        view.setAdapter(imageCarouselAdapter);
        view.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
    }
}
