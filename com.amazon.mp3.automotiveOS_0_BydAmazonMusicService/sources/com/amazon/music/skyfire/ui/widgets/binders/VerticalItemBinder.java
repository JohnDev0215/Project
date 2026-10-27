package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.BadgeItemElement;
import Touch.WidgetsInterface.v1_0.ButtonElement;
import Touch.WidgetsInterface.v1_0.CircleVerticalItemElement;
import Touch.WidgetsInterface.v1_0.ImageResources;
import Touch.WidgetsInterface.v1_0.RectangleVerticalItemElement;
import Touch.WidgetsInterface.v1_0.TextButtonElement;
import Touch.WidgetsInterface.v1_0.VerticalItemElement;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.skyfire.ui.Constants;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.ktx.PicassoKt;
import com.amazon.music.skyfire.ui.ktx.SkyFireKt;
import com.amazon.music.skyfire.ui.ktx.ViewKt;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.views.library.binders.ImageBinder;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.ImageStyleKey;
import com.amazon.music.views.library.views.ArtworkFrameView;
import com.amazon.music.views.library.views.VerticalTileView;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.ImageSize;
import com.amazon.ui.foundations.styles.ImageStyle;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import com.squareup.picasso.Picasso;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VerticalItemBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0003H\u0016J \u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0006H\u0016J\u0018\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u0002H\u0002J\u0018\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0012\u0010\u0019\u001a\u00020\u00032\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0012\u0010\u001c\u001a\u00020\u00062\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0002J\u0018\u0010\u001f\u001a\u00020 2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010!\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0003H\u0002J\u0010\u0010\"\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J \u0010#\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u0006H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006%"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/VerticalItemBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ItemElementBinder;", "LTouch/WidgetsInterface/v1_0/VerticalItemElement;", "Lcom/amazon/music/views/library/views/VerticalTileView;", "()V", "contentType", "", "imageBinder", "Lcom/amazon/music/views/library/binders/ImageBinder;", "getImageBinder", "()Lcom/amazon/music/views/library/binders/ImageBinder;", "imageBinder$delegate", "Lkotlin/Lazy;", "bind", "", "element", "view", "width", "bindArtwork", "artworkFrameLayout", "Lcom/amazon/music/views/library/views/ArtworkFrameView;", "bindButton", "LTouch/WidgetsInterface/v1_0/ButtonElement;", "button", "Lcom/amazon/ui/foundations/views/BaseButton;", "createView", "parent", "Landroid/view/ViewGroup;", "getPlaceholderId", ContextMappingConstants.IMAGE, "LTouch/WidgetsInterface/v1_0/ImageResources$Image;", "getScalingFactor", "", "reset", "setPlayStateIconStyle", "updateSize", "height", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VerticalItemBinder extends ItemElementBinder<VerticalItemElement, VerticalTileView> {
    private int contentType;

    /* JADX INFO: renamed from: imageBinder$delegate, reason: from kotlin metadata */
    private final Lazy imageBinder = LazyKt__LazyJVMKt.lazy(new Function0<ImageBinder>() { // from class: com.amazon.music.skyfire.ui.widgets.binders.VerticalItemBinder$imageBinder$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ImageBinder invoke() {
            Picasso picasso = Picasso.get();
            Intrinsics.checkNotNullExpressionValue(picasso, "get()");
            return PicassoKt.createImageBinder(picasso, this.this$0.getContext(), this.this$0.getStyleSheet());
        }
    });

    private final ImageBinder getImageBinder() {
        return (ImageBinder) this.imageBinder.getValue();
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public VerticalTileView createView(ViewGroup parent) {
        return new VerticalTileView(getContext(), getStyleSheet());
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(VerticalItemElement element, VerticalTileView view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        bind(element, view, -1);
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ItemElementBinder
    public void bind(final VerticalItemElement element, final VerticalTileView view, int width) {
        Unit unit;
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        reset(view);
        if (element instanceof CircleVerticalItemElement) {
            this.contentType = 4;
            view.centerTitle(true);
        }
        ArtworkFrameView artworkFrameView = view.getArtworkFrameView();
        float scalingFactor = getScalingFactor(element, artworkFrameView);
        float f = width;
        final int i = (int) (f / scalingFactor);
        final int i2 = (int) (f * scalingFactor);
        if (width == -1 || width == -2) {
            view.post(new Runnable() { // from class: com.amazon.music.skyfire.ui.widgets.binders.VerticalItemBinder$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    VerticalItemBinder.m2247bind$lambda0(view, element, this, i2, i);
                }
            });
        } else if (element instanceof RectangleVerticalItemElement) {
            updateSize(view, i2, width);
        } else {
            updateSize(view, width, i);
        }
        ImageStyle imageStyle = getStyleSheet().getImageStyle(ImageStyleKey.DEFAULT);
        if (imageStyle != null) {
            artworkFrameView.getArtworkImageView().setImageStyle(imageStyle);
        }
        artworkFrameView.getArtworkImageView().setScalingFactor(scalingFactor);
        bindArtwork(artworkFrameView, element);
        setPlayStateIconStyle(artworkFrameView);
        view.setEnabled(!element.isDisabled());
        String strPrimaryText = element.primaryText();
        Intrinsics.checkNotNullExpressionValue(strPrimaryText, "element.primaryText()");
        view.setTitle(strPrimaryText);
        String strSecondaryText = element.secondaryText();
        if (strSecondaryText == null) {
            strSecondaryText = "";
        }
        view.setSubtitle(strSecondaryText);
        String strTertiaryText = element.tertiaryText();
        view.setDetailsText(strTertiaryText != null ? strTertiaryText : "");
        Double dRating = element.rating();
        view.setStarRating(dRating == null ? null : Float.valueOf((float) dRating.doubleValue()), element.reviewCount());
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.skyfire.ui.widgets.binders.VerticalItemBinder$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                VerticalItemBinder.m2248bind$lambda6$lambda2(this.f$0, element, view2);
            }
        });
        List<BadgeItemElement> listBottomBadges = element.bottomBadges();
        if (listBottomBadges == null) {
            unit = null;
        } else {
            BadgeItemElement badgeItemElement = (BadgeItemElement) CollectionsKt___CollectionsKt.getOrNull(listBottomBadges, 0);
            view.setAudioQualityText(badgeItemElement == null ? null : badgeItemElement.description());
            BadgeItemElement badgeItemElement2 = (BadgeItemElement) CollectionsKt___CollectionsKt.getOrNull(listBottomBadges, 1);
            view.setImmersiveBadgeText(badgeItemElement2 == null ? null : badgeItemElement2.description());
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            view.setAudioQualityText(null);
            view.setImmersiveBadgeText(null);
        }
        Intrinsics.checkNotNullExpressionValue(element.onItemContextSelected(), "element.onItemContextSelected()");
        if (!r14.isEmpty()) {
            view.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.amazon.music.skyfire.ui.widgets.binders.VerticalItemBinder$$ExternalSyntheticLambda2
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view2) {
                    return VerticalItemBinder.m2249bind$lambda6$lambda5(this.f$0, element, view2);
                }
            });
        }
        List<ButtonElement> listButtons = element.buttons();
        if (listButtons == null) {
            return;
        }
        Object objFirst = CollectionsKt___CollectionsKt.first((List<? extends Object>) listButtons);
        Intrinsics.checkNotNullExpressionValue(objFirst, "it.first()");
        View viewFindViewById = view.findViewById(R.id.follow_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "view.findViewById(R.id.follow_button)");
        bindButton((ButtonElement) objFirst, (BaseButton) viewFindViewById);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-0, reason: not valid java name */
    public static final void m2247bind$lambda0(final VerticalTileView view, final VerticalItemElement element, final VerticalItemBinder this$0, final int i, final int i2) {
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(element, "$element");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.music.skyfire.ui.widgets.binders.VerticalItemBinder$bind$1$1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (element instanceof RectangleVerticalItemElement) {
                    VerticalItemBinder verticalItemBinder = this$0;
                    VerticalTileView verticalTileView = view;
                    verticalItemBinder.updateSize(verticalTileView, i, verticalTileView.getMeasuredWidth());
                } else {
                    VerticalItemBinder verticalItemBinder2 = this$0;
                    VerticalTileView verticalTileView2 = view;
                    verticalItemBinder2.updateSize(verticalTileView2, verticalTileView2.getMeasuredWidth(), i2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-6$lambda-2, reason: not valid java name */
    public static final void m2248bind$lambda6$lambda2(VerticalItemBinder this$0, VerticalItemElement element, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(element, "$element");
        DispatcherContext dispatcherContext = this$0.getDispatcherContext();
        if (dispatcherContext == null) {
            return;
        }
        List<Method> listOnItemSelected = element.onItemSelected();
        Intrinsics.checkNotNullExpressionValue(listOnItemSelected, "element.onItemSelected()");
        SkyFireKt.dispatch$default(dispatcherContext, listOnItemSelected, (String) null, 2, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-6$lambda-5, reason: not valid java name */
    public static final boolean m2249bind$lambda6$lambda5(VerticalItemBinder this$0, VerticalItemElement element, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(element, "$element");
        DispatcherContext dispatcherContext = this$0.getDispatcherContext();
        if (dispatcherContext == null) {
            return true;
        }
        List<Method> listOnItemContextSelected = element.onItemContextSelected();
        Intrinsics.checkNotNullExpressionValue(listOnItemContextSelected, "element.onItemContextSelected()");
        SkyFireKt.dispatch$default(dispatcherContext, listOnItemContextSelected, (String) null, 2, (Object) null);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSize(VerticalTileView view, int width, int height) {
        ArtworkFrameView artworkFrameView = view.getArtworkFrameView();
        if (this.contentType == 4) {
            artworkFrameView.getArtworkImageView().setImageSize(ImageSize.INSTANCE.getRoundImageSize(width));
        } else {
            artworkFrameView.getArtworkImageView().setImageSize(new ImageSize(width, Integer.valueOf(getStyleSheet().getImageCornerSize(height))));
        }
        artworkFrameView.setLayoutParams(new ConstraintLayout.LayoutParams(width, -2));
    }

    private final void reset(VerticalTileView view) {
        view.setLabel("");
        view.setTitle("");
        view.setSubtitle("");
        view.setDetailsText("");
        view.centerTitle(false);
        view.getArtworkFrameView().setPlayIcon((Integer) null);
        VerticalTileView.setStarRating$default(view, null, null, 2, null);
    }

    private final void bindArtwork(ArtworkFrameView artworkFrameLayout, VerticalItemElement element) {
        ArtworkFrameView.initStyle$default(artworkFrameLayout, getStyleSheet(), null, 2, null);
        ImageResources imageResourcesIconResources = element.iconResources();
        ViewKt.setPlayIcon(artworkFrameLayout, imageResourcesIconResources == null ? null : imageResourcesIconResources.image());
        artworkFrameLayout.getArtworkImageView().setContentDescription(element.imageDescription());
        ImageBinder imageBinder = getImageBinder();
        ImageView image = artworkFrameLayout.getArtworkImageView().getImage();
        String strImage = element.image();
        Integer numValueOf = Integer.valueOf(this.contentType);
        ImageResources imageResourcesPlaceholder = element.placeholder();
        imageBinder.bind(image, strImage, numValueOf, getPlaceholderId(imageResourcesPlaceholder != null ? imageResourcesPlaceholder.image() : null));
    }

    private final void setPlayStateIconStyle(ArtworkFrameView artworkFrameLayout) {
        ButtonSize buttonSize = getStyleSheet().getButtonSize(ButtonSizeKey.LARGE);
        Integer height = buttonSize == null ? null : buttonSize.getHeight();
        Integer color = getStyleSheet().getColor(ColorKey.COLOR_ACCENT);
        if (height != null && color != null) {
            artworkFrameLayout.setPlayNotificationIconStyle(height.intValue(), color.intValue());
        }
        ArtworkFrameView.setPlayIconGravity$default(artworkFrameLayout, 8388691, false, 2, null);
    }

    private final float getScalingFactor(VerticalItemElement element, ArtworkFrameView artworkFrameLayout) {
        if (element instanceof RectangleVerticalItemElement) {
            return artworkFrameLayout.getContext().getResources().getFraction(R.fraction.aspect_ratio_16_9, 1, 1);
        }
        return artworkFrameLayout.getContext().getResources().getFraction(R.fraction.aspect_ratio_1_1, 1, 1);
    }

    private final int getPlaceholderId(ImageResources.Image image) {
        Integer num = Constants.INSTANCE.getResMap().get(image);
        return num == null ? R.drawable.album_placeholder : num.intValue();
    }

    private final void bindButton(ButtonElement element, BaseButton button) {
        TextButtonBinder textButtonBinder = new TextButtonBinder();
        textButtonBinder.setButtonSizeKey(ButtonSizeKey.SMALL);
        textButtonBinder.setButtonStyleKey(ButtonStyleKey.FOLLOW_OUTLINE);
        textButtonBinder.setBindingProvider(getBindingProvider());
        textButtonBinder.bind((TextButtonElement) element, button);
        LayoutParamUtils.INSTANCE.setLayoutMargins(button, -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : Integer.valueOf(button.getResources().getDimensionPixelSize(R.dimen.spacer_small)), (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
    }
}
