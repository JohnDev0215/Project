package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.ButtonElement;
import Touch.WidgetsInterface.v1_0.StaticCarouselHeaderElement;
import Touch.WidgetsInterface.v1_0.TextButtonElement;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import com.amazon.music.picassoimageloader.PicassoImageLoader;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.ktx.ViewGroupKt;
import com.amazon.music.skyfire.ui.ktx.ViewKt;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.utils.OnViewedTrigger;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.views.CollectionDetailHeaderView;
import com.amazon.ui.foundations.ktx.TextViewKt;
import com.amazon.ui.foundations.views.BaseButton;
import com.squareup.picasso.Picasso;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StaticCarouselHeaderBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0003H\u0002J\u0018\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0003H\u0016J\u0018\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0003H\u0002J\u0018\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0003H\u0002J\u0012\u0010\u0013\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0003H\u0002R#\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\u0017"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/StaticCarouselHeaderBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/StaticCarouselHeaderElement;", "Lcom/amazon/music/views/library/views/CollectionDetailHeaderView;", "()V", "picassoImageLoader", "Lcom/amazon/music/picassoimageloader/PicassoImageLoader;", "kotlin.jvm.PlatformType", "getPicassoImageLoader", "()Lcom/amazon/music/picassoimageloader/PicassoImageLoader;", "picassoImageLoader$delegate", "Lkotlin/Lazy;", "applyStyles", "", "element", "view", "bind", "bindArtwork", "bindText", "createView", "parent", "Landroid/view/ViewGroup;", "styleAndBindActionButtons", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StaticCarouselHeaderBinder extends ElementBinder<StaticCarouselHeaderElement, CollectionDetailHeaderView> {

    /* JADX INFO: renamed from: picassoImageLoader$delegate, reason: from kotlin metadata */
    private final Lazy picassoImageLoader = LazyKt__LazyJVMKt.lazy(new Function0<PicassoImageLoader>() { // from class: com.amazon.music.skyfire.ui.widgets.binders.StaticCarouselHeaderBinder$picassoImageLoader$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final PicassoImageLoader invoke() {
            return PicassoImageLoader.builder(this.this$0.getContext(), Picasso.get()).withTag(this.this$0.getContext()).build();
        }
    });

    private final PicassoImageLoader getPicassoImageLoader() {
        return (PicassoImageLoader) this.picassoImageLoader.getValue();
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public CollectionDetailHeaderView createView(ViewGroup parent) {
        return new CollectionDetailHeaderView(getContext(), getStyleSheet(), true, false, 8, null);
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(StaticCarouselHeaderElement element, CollectionDetailHeaderView view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        applyStyles(element, view);
        bindText(element, view);
        bindArtwork(element, view);
        styleAndBindActionButtons(element, view);
        OnViewedTrigger.Companion companion = OnViewedTrigger.INSTANCE;
        DispatcherContext dispatcherContext = getDispatcherContext();
        List<Method> listOnViewed = element.onViewed();
        Intrinsics.checkNotNullExpressionValue(listOnViewed, "element.onViewed()");
        companion.emitOnViewed(dispatcherContext, listOnViewed);
    }

    private final void applyStyles(StaticCarouselHeaderElement element, CollectionDetailHeaderView view) {
        Resources resources = view.getResources();
        int i = R.dimen.spacer_large;
        view.setPadding(resources.getDimensionPixelSize(i), view.getResources().getDimensionPixelSize(R.dimen.generic_header_top_padding), view.getResources().getDimensionPixelSize(i), view.getPaddingBottom());
        ViewKt.applyFontStyle(view.getHeadlineTextView(), view.getStyleSheet(), FontStyleKey.HEADLINE_3);
        ViewKt.applyFontStyle(view.getPrimaryTextView(), view.getStyleSheet(), FontStyleKey.PRIMARY);
        ViewKt.applyFontStyle(view.getSecondaryTextView(), view.getStyleSheet(), FontStyleKey.SECONDARY);
    }

    private final void bindText(StaticCarouselHeaderElement element, CollectionDetailHeaderView view) {
        view.getHeadlineTextView().setText(element.primaryText());
        view.getPrimaryTextView().setText(element.secondaryText());
        view.getSecondaryTextView().setText(element.tertiaryText());
        TextViewKt.collapseIfEmpty(view.getLabelTextView());
        TextViewKt.collapseIfEmpty(view.getHeadlineTextView());
        TextViewKt.collapseIfEmpty(view.getPrimaryTextView());
        TextViewKt.collapseIfEmpty(view.getSecondaryTextView());
        TextViewKt.collapseIfEmpty(view.getTertiaryTextView());
    }

    private final void bindArtwork(StaticCarouselHeaderElement element, CollectionDetailHeaderView view) {
        getPicassoImageLoader().loadImage(element.image(), view.getImageLoaderCallback());
    }

    private final void styleAndBindActionButtons(StaticCarouselHeaderElement element, CollectionDetailHeaderView view) {
        View viewCreateView;
        TextButtonBinder textButtonBinder = new TextButtonBinder();
        textButtonBinder.setButtonSizeKey(ButtonSizeKey.MEDIUM_WITH_ICON);
        textButtonBinder.setButtonStyleKey(ButtonStyleKey.SOLID);
        textButtonBinder.setBindingProvider(getBindingProvider());
        view.getActionIconContainer().removeAllViews();
        view.getRightActionIconContainer().removeAllViews();
        List<ButtonElement> buttons = element.buttons();
        Intrinsics.checkNotNullExpressionValue(buttons, "buttons");
        for (ButtonElement e : buttons) {
            ElementBindingProvider bindingProvider = getBindingProvider();
            Intrinsics.checkNotNullExpressionValue(e, "e");
            View viewCreateView2 = bindingProvider.createView(e, view.getActionIconContainer());
            if (viewCreateView2 != null) {
                BaseButton baseButton = (BaseButton) viewCreateView2;
                if (e instanceof TextButtonElement) {
                    textButtonBinder.bind((TextButtonElement) e, baseButton);
                } else {
                    getBindingProvider().bind(e, viewCreateView2);
                }
                BaseButton.StyleBuilder iconBuilder = view.getStyleSheet().getIconBuilder(IconSizeKey.MEDIUM, IconStyleKey.PRIMARY);
                if (iconBuilder != null) {
                    iconBuilder.applyStyle(baseButton);
                }
                ViewGroupKt.plusAssign(view.getActionIconContainer(), viewCreateView2);
            }
        }
        ButtonElement buttonElementCallToActionButton = element.callToActionButton();
        if (buttonElementCallToActionButton != null && (viewCreateView = getBindingProvider().createView(buttonElementCallToActionButton, view.getRightActionIconContainer())) != null) {
            BaseButton baseButton2 = (BaseButton) viewCreateView;
            TextButtonElement textButtonElement = (TextButtonElement) buttonElementCallToActionButton;
            textButtonBinder.bind(textButtonElement, baseButton2);
            String strText = textButtonElement.text();
            Intrinsics.checkNotNullExpressionValue(strText, "e.text()");
            baseButton2.setText(strText);
            ViewGroupKt.plusAssign(view.getRightActionIconContainer(), viewCreateView);
        }
        view.getRightActionIconContainer().setVisibility(0);
        com.amazon.ui.foundations.ktx.ViewKt.collapseIf(view.getRightActionIconContainer(), view.getRightActionIconContainer().getChildCount() == 0);
        com.amazon.ui.foundations.ktx.ViewKt.collapseIf(view.getActionIconContainer(), view.getActionIconContainer().getChildCount() == 0);
    }
}
