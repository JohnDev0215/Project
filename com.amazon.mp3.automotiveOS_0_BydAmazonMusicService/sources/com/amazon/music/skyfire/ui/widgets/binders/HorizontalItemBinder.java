package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.SkyFireObject;
import ExternalActionInterface.v1_0.ImmutableTriggerExternalActionMethod;
import ExternalActionInterface.v1_0.PlayableContent;
import ExternalActionInterface.v1_0.Track;
import Touch.WidgetsInterface.v1_0.BadgeItemElement;
import Touch.WidgetsInterface.v1_0.ButtonElement;
import Touch.WidgetsInterface.v1_0.HorizontalItemElement;
import Touch.WidgetsInterface.v1_0.ImageResources;
import Touch.WidgetsInterface.v1_0.InvertedRectangleHorizontalItemElement;
import Touch.WidgetsInterface.v1_0.PlainIconButtonElement;
import Touch.WidgetsInterface.v1_0.RectangleHorizontalItemElement;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.skyfire.ui.Constants;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.ktx.PicassoKt;
import com.amazon.music.skyfire.ui.ktx.SkyFireKt;
import com.amazon.music.skyfire.ui.ktx.ViewKt;
import com.amazon.music.skyfire.ui.providers.DownloadProvider;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.skyfire.Methods;
import com.amazon.music.views.library.binders.ImageBinder;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.ImageStyleKey;
import com.amazon.music.views.library.styles.keys.IndicatorSizeKey;
import com.amazon.music.views.library.views.ArtworkFrameView;
import com.amazon.music.views.library.views.HorizontalTileView;
import com.amazon.music.views.library.views.IconPresenter;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.ImageSize;
import com.amazon.ui.foundations.styles.ImageStyle;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HorizontalItemBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0003H\u0016J \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u0002H\u0002J \u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0010H\u0016J\u0012\u0010\u001a\u001a\u00020\u00032\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u0012\u0010\u001d\u001a\u00020\u00102\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0002J\u0010\u0010 \u001a\u00020\f2\u0006\u0010!\u001a\u00020\u0013H\u0002R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\""}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/HorizontalItemBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ItemElementBinder;", "LTouch/WidgetsInterface/v1_0/HorizontalItemElement;", "Lcom/amazon/music/views/library/views/HorizontalTileView;", "()V", "imageBinder", "Lcom/amazon/music/views/library/binders/ImageBinder;", "getImageBinder", "()Lcom/amazon/music/views/library/binders/ImageBinder;", "imageBinder$delegate", "Lkotlin/Lazy;", "bind", "", "element", "view", "width", "", "bindArtwork", "artworkFrameLayout", "Lcom/amazon/music/views/library/views/ArtworkFrameView;", "bindLikes", "secondaryButton", "LTouch/WidgetsInterface/v1_0/PlainIconButtonElement;", "initialBind", "", "contentType", "createView", "parent", "Landroid/view/ViewGroup;", "getPlaceholderId", ContextMappingConstants.IMAGE, "LTouch/WidgetsInterface/v1_0/ImageResources$Image;", "setPlayStateIconStyle", "artworkFrameView", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class HorizontalItemBinder extends ItemElementBinder<HorizontalItemElement, HorizontalTileView> {

    /* JADX INFO: renamed from: imageBinder$delegate, reason: from kotlin metadata */
    private final Lazy imageBinder = LazyKt__LazyJVMKt.lazy(new Function0<ImageBinder>() { // from class: com.amazon.music.skyfire.ui.widgets.binders.HorizontalItemBinder$imageBinder$2
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

    public int contentType() {
        return 0;
    }

    private final ImageBinder getImageBinder() {
        return (ImageBinder) this.imageBinder.getValue();
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public HorizontalTileView createView(ViewGroup parent) {
        return new HorizontalTileView(getContext(), getStyleSheet());
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(HorizontalItemElement element, HorizontalTileView view) throws IllegalAccessException, NoSuchFieldException {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        bind(element, view, -1);
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ItemElementBinder
    public void bind(final HorizontalItemElement element, final HorizontalTileView view, int width) throws IllegalAccessException, NoSuchFieldException {
        float fraction;
        float dimension;
        ImageResources imageResourcesIconResources;
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        ArtworkFrameView artworkFrameLayout = view.getArtworkFrameLayout();
        if ((element instanceof RectangleHorizontalItemElement) || (element instanceof InvertedRectangleHorizontalItemElement)) {
            fraction = artworkFrameLayout.getContext().getResources().getFraction(R.fraction.aspect_ratio_16_9, 1, 1);
        } else {
            fraction = artworkFrameLayout.getContext().getResources().getFraction(R.fraction.aspect_ratio_1_1, 1, 1);
        }
        view.updateViewWidth(width);
        if (element.isFeatured() || (element instanceof InvertedRectangleHorizontalItemElement)) {
            dimension = view.getContext().getResources().getDimension(R.dimen.artwork_dimen_featured);
        } else {
            dimension = view.getContext().getResources().getDimension(R.dimen.artwork_default_dimen);
        }
        int i = (int) dimension;
        boolean z = element instanceof InvertedRectangleHorizontalItemElement;
        if (z) {
            fraction = 1 / fraction;
        }
        float f = fraction;
        int i2 = (int) (i * f);
        LayoutParamUtils.INSTANCE.setLayoutMargins(artworkFrameLayout, i2, i, (240 & 8) != 0 ? Boolean.FALSE : Boolean.FALSE, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
        bindArtwork(artworkFrameLayout, element);
        artworkFrameLayout.getArtworkImageView().setScalingFactor(f);
        setPlayStateIconStyle(artworkFrameLayout);
        artworkFrameLayout.setIndicatorSize(getStyleSheet(), IndicatorSizeKey.SMALL);
        ImageStyle imageStyle = getStyleSheet().getImageStyle(ImageStyleKey.DEFAULT);
        if (imageStyle != null) {
            artworkFrameLayout.getArtworkImageView().setImageStyle(imageStyle);
        }
        ImageSize imageSize = getStyleSheet().getImageSize(i2);
        if (imageSize != null) {
            artworkFrameLayout.getArtworkImageView().setImageSize(imageSize);
            artworkFrameLayout.setArtworkSize(-2, -2);
        }
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
        if (strTertiaryText == null) {
            strTertiaryText = "";
        }
        view.setDetailsText(strTertiaryText);
        String strLabel = element.label();
        view.setLabel(strLabel != null ? strLabel : "");
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.skyfire.ui.widgets.binders.HorizontalItemBinder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                HorizontalItemBinder.m2221bind$lambda15$lambda2(this.f$0, element, view2);
            }
        });
        if (z) {
            Intrinsics.checkNotNullExpressionValue(element.onItemContextSelected(), "element.onItemContextSelected()");
            if (!r4.isEmpty()) {
                view.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.amazon.music.skyfire.ui.widgets.binders.HorizontalItemBinder$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnLongClickListener
                    public final boolean onLongClick(View view2) {
                        return HorizontalItemBinder.m2222bind$lambda15$lambda3(this.f$0, element, view2);
                    }
                });
                return;
            }
            return;
        }
        List<BadgeItemElement> listBadges = element.badges();
        if (listBadges != null) {
            BadgeItemElement badgeItemElement = (BadgeItemElement) CollectionsKt___CollectionsKt.getOrNull(listBadges, 0);
            view.setAudioQualityText(badgeItemElement == null ? null : badgeItemElement.description());
            BadgeItemElement badgeItemElement2 = (BadgeItemElement) CollectionsKt___CollectionsKt.getOrNull(listBadges, 1);
            view.setImmersiveBadgeText(badgeItemElement2 == null ? null : badgeItemElement2.description());
        }
        Intrinsics.checkNotNullExpressionValue(element.onItemContextSelected(), "element.onItemContextSelected()");
        if (!r4.isEmpty()) {
            view.showOverflowMenu(true);
            view.setOverflowOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.skyfire.ui.widgets.binders.HorizontalItemBinder$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    HorizontalItemBinder.m2223bind$lambda15$lambda5(this.f$0, element, view2);
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.amazon.music.skyfire.ui.widgets.binders.HorizontalItemBinder$$ExternalSyntheticLambda3
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view2) {
                    return HorizontalItemBinder.m2224bind$lambda15$lambda6(this.f$0, element, view2);
                }
            });
        }
        Intrinsics.checkNotNullExpressionValue(element.onAddSelected(), "element.onAddSelected()");
        if (!r3.isEmpty()) {
            view.showRowButton(true);
            view.setRowButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.skyfire.ui.widgets.binders.HorizontalItemBinder$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) throws IllegalAccessException, NoSuchFieldException {
                    HorizontalItemBinder.m2225bind$lambda15$lambda7(this.f$0, element, view, view2);
                }
            });
        }
        if (element.secondaryButton() == null) {
            return;
        }
        ButtonElement buttonElementSecondaryButton = element.secondaryButton();
        final PlainIconButtonElement plainIconButtonElement = buttonElementSecondaryButton instanceof PlainIconButtonElement ? (PlainIconButtonElement) buttonElementSecondaryButton : null;
        ImageResources.Image image = (plainIconButtonElement == null || (imageResourcesIconResources = plainIconButtonElement.iconResources()) == null) ? null : imageResourcesIconResources.image();
        if (image == ImageResources.Image.DOWNLOAD_ICON) {
            PlayableContent playableContent = element.playableContent();
            Track track = playableContent instanceof Track ? (Track) playableContent : null;
            if (track != null) {
                DownloadProvider downloadProvider = getDownloadProvider();
                Context context = view.getContext();
                Intrinsics.checkNotNullExpressionValue(context, "context");
                if (downloadProvider.shouldShowDownloadButton(context, track.asin())) {
                    view.setLeftIcon(IconPresenter.IconType.DOWNLOAD_ICON, new View.OnClickListener() { // from class: com.amazon.music.skyfire.ui.widgets.binders.HorizontalItemBinder$$ExternalSyntheticLambda5
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view2) throws IllegalAccessException, NoSuchFieldException {
                            HorizontalItemBinder.m2220bind$lambda15$lambda14$lambda9(element, this, view, view2);
                        }
                    });
                    return;
                }
                return;
            }
            return;
        }
        if (image == ImageResources.Image.FAVORITE_ICON || image == ImageResources.Image.FAVORITE_ON_ICON) {
            view.showRowButton(true);
            bindLikes(plainIconButtonElement, view, true);
            view.setRowButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.skyfire.ui.widgets.binders.HorizontalItemBinder$$ExternalSyntheticLambda6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) throws IllegalAccessException, NoSuchFieldException {
                    HorizontalItemBinder.m2218bind$lambda15$lambda14$lambda11(plainIconButtonElement, this, view, view2);
                }
            });
        } else if (image == ImageResources.Image.PLAY_ICON) {
            view.showRowButton(true);
            view.restylePlayIcon();
            view.setRowButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.skyfire.ui.widgets.binders.HorizontalItemBinder$$ExternalSyntheticLambda7
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) throws IllegalAccessException, NoSuchFieldException {
                    HorizontalItemBinder.m2219bind$lambda15$lambda14$lambda13(element, this, view, view2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-15$lambda-2, reason: not valid java name */
    public static final void m2221bind$lambda15$lambda2(HorizontalItemBinder this$0, HorizontalItemElement element, View view) {
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
    /* JADX INFO: renamed from: bind$lambda-15$lambda-3, reason: not valid java name */
    public static final boolean m2222bind$lambda15$lambda3(HorizontalItemBinder this$0, HorizontalItemElement element, View view) {
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
    /* JADX INFO: renamed from: bind$lambda-15$lambda-5, reason: not valid java name */
    public static final void m2223bind$lambda15$lambda5(HorizontalItemBinder this$0, HorizontalItemElement element, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(element, "$element");
        DispatcherContext dispatcherContext = this$0.getDispatcherContext();
        if (dispatcherContext == null) {
            return;
        }
        List<Method> listOnItemContextSelected = element.onItemContextSelected();
        Intrinsics.checkNotNullExpressionValue(listOnItemContextSelected, "element.onItemContextSelected()");
        SkyFireKt.dispatch$default(dispatcherContext, listOnItemContextSelected, (String) null, 2, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-15$lambda-6, reason: not valid java name */
    public static final boolean m2224bind$lambda15$lambda6(HorizontalItemBinder this$0, HorizontalItemElement element, View view) {
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
    /* JADX INFO: renamed from: bind$lambda-15$lambda-7, reason: not valid java name */
    public static final void m2225bind$lambda15$lambda7(HorizontalItemBinder this$0, HorizontalItemElement element, HorizontalTileView this_with, View view) throws IllegalAccessException, NoSuchFieldException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(element, "$element");
        Intrinsics.checkNotNullParameter(this_with, "$this_with");
        DispatcherContext dispatcherContext = this$0.getDispatcherContext();
        if (dispatcherContext != null) {
            List<Method> listOnAddSelected = element.onAddSelected();
            Intrinsics.checkNotNullExpressionValue(listOnAddSelected, "element.onAddSelected()");
            SkyFireKt.dispatch(dispatcherContext, listOnAddSelected, ViewKt.getOrCreateUniqueTag(this_with));
        }
        SkyFireKt.setValue(element, "onAddSelected", CollectionsKt__CollectionsKt.emptyList());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-15$lambda-14$lambda-9, reason: not valid java name */
    public static final void m2220bind$lambda15$lambda14$lambda9(HorizontalItemElement element, HorizontalItemBinder this$0, HorizontalTileView this_with, View view) throws IllegalAccessException, NoSuchFieldException {
        List<Method> listOnItemSelected;
        DispatcherContext dispatcherContext;
        Intrinsics.checkNotNullParameter(element, "$element");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_with, "$this_with");
        ButtonElement buttonElementSecondaryButton = element.secondaryButton();
        if (buttonElementSecondaryButton != null && (listOnItemSelected = buttonElementSecondaryButton.onItemSelected()) != null && (dispatcherContext = this$0.getDispatcherContext()) != null) {
            SkyFireKt.dispatch(dispatcherContext, listOnItemSelected, ViewKt.getOrCreateUniqueTag(this_with));
        }
        ButtonElement buttonElementSecondaryButton2 = element.secondaryButton();
        if (buttonElementSecondaryButton2 == null) {
            return;
        }
        SkyFireKt.setValue(buttonElementSecondaryButton2, "onItemSelected", null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-15$lambda-14$lambda-11, reason: not valid java name */
    public static final void m2218bind$lambda15$lambda14$lambda11(PlainIconButtonElement plainIconButtonElement, HorizontalItemBinder this$0, HorizontalTileView this_with, View view) throws IllegalAccessException, NoSuchFieldException {
        DispatcherContext dispatcherContext;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_with, "$this_with");
        List<Method> listOnItemSelected = plainIconButtonElement.onItemSelected();
        if (listOnItemSelected != null && (dispatcherContext = this$0.getDispatcherContext()) != null) {
            SkyFireKt.dispatch(dispatcherContext, listOnItemSelected, ViewKt.getOrCreateUniqueTag(this_with));
        }
        this$0.bindLikes(plainIconButtonElement, this_with, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-15$lambda-14$lambda-13, reason: not valid java name */
    public static final void m2219bind$lambda15$lambda14$lambda13(HorizontalItemElement element, HorizontalItemBinder this$0, HorizontalTileView this_with, View view) throws IllegalAccessException, NoSuchFieldException {
        List<Method> listOnItemSelected;
        DispatcherContext dispatcherContext;
        Intrinsics.checkNotNullParameter(element, "$element");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_with, "$this_with");
        ButtonElement buttonElementSecondaryButton = element.secondaryButton();
        if (buttonElementSecondaryButton != null && (listOnItemSelected = buttonElementSecondaryButton.onItemSelected()) != null && (dispatcherContext = this$0.getDispatcherContext()) != null) {
            SkyFireKt.dispatch(dispatcherContext, listOnItemSelected, ViewKt.getOrCreateUniqueTag(this_with));
        }
        ButtonElement buttonElementSecondaryButton2 = element.secondaryButton();
        if (buttonElementSecondaryButton2 == null) {
            return;
        }
        SkyFireKt.setValue(buttonElementSecondaryButton2, "onItemSelected", null);
    }

    private final void bindLikes(PlainIconButtonElement secondaryButton, HorizontalTileView view, boolean initialBind) throws IllegalAccessException, NoSuchFieldException {
        List<Method> onItemSelected = secondaryButton.onItemSelected();
        List listListOf = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{Methods.setLikeTrack, Methods.setNeutralTrack});
        ImageResources.Image image = ImageResources.Image.FAVORITE_ON_ICON;
        ImageResources imageResourcesIconResources = secondaryButton.iconResources();
        boolean z = image == (imageResourcesIconResources == null ? null : imageResourcesIconResources.image());
        if (initialBind) {
            view.restyleLikeIcons(z);
            return;
        }
        Intrinsics.checkNotNullExpressionValue(onItemSelected, "onItemSelected");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(onItemSelected, 10));
        for (SkyFireObject skyFireObjectWithName : onItemSelected) {
            if (skyFireObjectWithName instanceof ImmutableTriggerExternalActionMethod) {
                ImmutableTriggerExternalActionMethod immutableTriggerExternalActionMethod = (ImmutableTriggerExternalActionMethod) skyFireObjectWithName;
                if (listListOf.contains(immutableTriggerExternalActionMethod.name())) {
                    skyFireObjectWithName = immutableTriggerExternalActionMethod.withName(z ? Methods.setLikeTrack : Methods.setNeutralTrack);
                }
            }
            arrayList.add(skyFireObjectWithName);
        }
        ArrayList arrayList2 = (ArrayList) CollectionsKt___CollectionsKt.toCollection(arrayList, new ArrayList());
        ImageResources.Image image2 = z ? ImageResources.Image.FAVORITE_ICON : ImageResources.Image.FAVORITE_ON_ICON;
        SkyFireKt.setValue(secondaryButton, "onItemSelected", arrayList2);
        ImageResources imageResourcesIconResources2 = secondaryButton.iconResources();
        if (imageResourcesIconResources2 == null) {
            return;
        }
        SkyFireKt.setValue(imageResourcesIconResources2, ContextMappingConstants.IMAGE, image2);
    }

    private final void bindArtwork(ArtworkFrameView artworkFrameLayout, HorizontalItemElement element) {
        ArtworkFrameView.initStyle$default(artworkFrameLayout, getStyleSheet(), null, 2, null);
        if (element instanceof InvertedRectangleHorizontalItemElement) {
            artworkFrameLayout.setPlayIcon((Integer) null);
            ImageBinder imageBinder = getImageBinder();
            ImageView image = artworkFrameLayout.getArtworkImageView().getImage();
            String strImage = element.image();
            ImageResources imageResourcesPlaceholder = element.placeholder();
            imageBinder.bind(image, strImage, 14, getPlaceholderId(imageResourcesPlaceholder != null ? imageResourcesPlaceholder.image() : null));
            return;
        }
        ImageResources imageResourcesIconResources = element.iconResources();
        if ((imageResourcesIconResources != null ? imageResourcesIconResources.image() : null) == ImageResources.Image.ARTWORK_STATION_BADGE) {
            artworkFrameLayout.enableStationBadge(true);
        }
        ImageBinder.bind$default(getImageBinder(), artworkFrameLayout.getArtworkImageView().getImage(), element.image(), Integer.valueOf(contentType()), 0, 8, null);
    }

    private final void setPlayStateIconStyle(ArtworkFrameView artworkFrameView) {
        ButtonSize buttonSize = getStyleSheet().getButtonSize(ButtonSizeKey.SMALL);
        Integer height = buttonSize == null ? null : buttonSize.getHeight();
        Integer color = getStyleSheet().getColor(ColorKey.COLOR_ACCENT);
        if (height != null && color != null) {
            artworkFrameView.setPlayNotificationIconStyle(height.intValue(), color.intValue());
        }
        artworkFrameView.setPlayIconGravity(8388691, true);
        artworkFrameView.setPlayState(null);
    }

    private final int getPlaceholderId(ImageResources.Image image) {
        Integer num = Constants.INSTANCE.getResMap().get(image);
        return num == null ? R.drawable.album_placeholder : num.intValue();
    }
}
