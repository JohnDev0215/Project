package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.ButtonElement;
import Touch.WidgetsInterface.v1_0.GenericHeaderElement;
import Touch.WidgetsInterface.v1_0.IconButtonElement;
import Touch.WidgetsInterface.v1_0.ImageResources;
import Touch.WidgetsInterface.v1_0.PlainIconButtonElement;
import Touch.WidgetsInterface.v1_0.TextButtonElement;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.ktx.ViewKt;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.utils.OnViewedTrigger;
import com.amazon.music.skyfire.ui.widgets.views.GenericHeaderView;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.ktx.TextViewKt;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.styles.StatefulStyle;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GenericHeaderBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/GenericHeaderBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/GenericHeaderElement;", "Lcom/amazon/music/skyfire/ui/widgets/views/GenericHeaderView;", "()V", "bind", "", "element", "view", "createView", "parent", "Landroid/view/ViewGroup;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class GenericHeaderBinder extends ElementBinder<GenericHeaderElement, GenericHeaderView> {
    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public GenericHeaderView createView(ViewGroup parent) {
        return new GenericHeaderView(getContext());
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(GenericHeaderElement element, GenericHeaderView view) {
        View viewAttachView;
        BaseButton.StyleBuilder buttonBuilder;
        BaseButton.StyleBuilder iconBuilder;
        ButtonStyle buttonStyle;
        BaseButton.StyleBuilder buttonBuilder2;
        BaseButton.StyleBuilder styleBuilderWithStyle;
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        ViewKt.applyFontStyle(view.getPrimaryText(), getStyleSheet(), FontStyleKey.HEADLINE_1);
        view.getPrimaryText().setText(element.primaryText());
        TextView secondaryText = view.getSecondaryText();
        StyleSheet styleSheet = getStyleSheet();
        FontStyleKey fontStyleKey = FontStyleKey.SECONDARY;
        ViewKt.applyFontStyle(secondaryText, styleSheet, fontStyleKey);
        view.getSecondaryText().setText(element.secondaryText());
        ViewKt.applyFontStyle(view.getTertiaryText(), getStyleSheet(), fontStyleKey);
        view.getTertiaryText().setText(element.tertiaryText());
        view.getActionButtons().removeAllViews();
        List<ButtonElement> listButtons = element.buttons();
        Intrinsics.checkNotNullExpressionValue(listButtons, "element.buttons()");
        Iterator<T> it = listButtons.iterator();
        while (true) {
            boolean z = false;
            if (!it.hasNext()) {
                break;
            }
            ButtonElement b = (ButtonElement) it.next();
            ElementBindingProvider bindingProvider = getBindingProvider();
            Intrinsics.checkNotNullExpressionValue(b, "b");
            View viewAttachView2 = bindingProvider.attachView(b, view.getActionButtons());
            if (viewAttachView2 != null) {
                if (b instanceof IconButtonElement) {
                    if (viewAttachView2 instanceof BaseButton) {
                        if (b instanceof PlainIconButtonElement) {
                            ImageResources imageResourcesIconResources = ((IconButtonElement) b).iconResources();
                            ImageResources.Image image = imageResourcesIconResources == null ? null : imageResourcesIconResources.image();
                            if (image != ImageResources.Image.FAVORITE_ICON && image != ImageResources.Image.FAVORITE_ON_ICON) {
                                z = false;
                            }
                            z = z;
                        }
                        if (z) {
                            BaseButton.StyleBuilder iconBuilder2 = getStyleSheet().getIconBuilder(IconSizeKey.MEDIUM, IconStyleKey.PRIMARY);
                            ButtonStyle iconStyle = getStyleSheet().getIconStyle(IconStyleKey.ACCENT);
                            if (iconStyle != null && iconBuilder2 != null) {
                                iconBuilder2.withStyle(ButtonStyle.copy$default(iconStyle, new StatefulStyle(Integer.valueOf(ContextCompat.getColor(view.getContext(), R.color.cyan_accent)), null, null, null, null, null, null, null, 254, null), null, null, null, 14, null));
                            }
                            if (iconBuilder2 != null) {
                                iconBuilder2.applyStyle((BaseButton) viewAttachView2);
                            }
                        } else {
                            BaseButton.StyleBuilder iconBuilder3 = getStyleSheet().getIconBuilder(IconSizeKey.MEDIUM, IconStyleKey.PRIMARY);
                            if (iconBuilder3 != null) {
                                iconBuilder3.applyStyle((BaseButton) viewAttachView2);
                            }
                        }
                    }
                } else if ((b instanceof TextButtonElement) && (viewAttachView2 instanceof BaseButton) && (buttonStyle = getStyleSheet().getButtonStyle(ButtonStyleKey.FOLLOW_OUTLINE)) != null && (buttonBuilder2 = getStyleSheet().getButtonBuilder(ButtonSizeKey.LARGE_WITH_ICON, ButtonStyleKey.OUTLINE)) != null && (styleBuilderWithStyle = buttonBuilder2.withStyle(ButtonStyle.copy$default(buttonStyle, new StatefulStyle(Integer.valueOf(ContextCompat.getColor(view.getContext(), R.color.cyan_accent)), null, null, null, null, null, null, null, 254, null), null, null, null, 14, null))) != null) {
                    styleBuilderWithStyle.applyStyle((BaseButton) viewAttachView2);
                }
                viewAttachView2.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getResources().getDimensionPixelSize(R.dimen.spacer_small), view.getPaddingBottom());
            }
        }
        View view2 = new View(view.getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.weight = 1.0f;
        view2.setLayoutParams(layoutParams);
        view.getActionButtons().addView(view2);
        ButtonElement buttonElementCallToActionButton = element.callToActionButton();
        if (buttonElementCallToActionButton != null && (viewAttachView = getBindingProvider().attachView(buttonElementCallToActionButton, view.getActionButtons())) != null) {
            if (buttonElementCallToActionButton instanceof IconButtonElement) {
                if ((viewAttachView instanceof BaseButton) && (iconBuilder = getStyleSheet().getIconBuilder(IconSizeKey.MEDIUM, IconStyleKey.SOLID)) != null) {
                    iconBuilder.applyStyle((BaseButton) viewAttachView);
                }
            } else if ((buttonElementCallToActionButton instanceof TextButtonElement) && (viewAttachView instanceof BaseButton) && (buttonBuilder = getStyleSheet().getButtonBuilder(ButtonSizeKey.LARGE_WITH_ICON, ButtonStyleKey.SOLID)) != null) {
                buttonBuilder.applyStyle((BaseButton) viewAttachView);
            }
            viewAttachView.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
        }
        ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
        if (layoutParams2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView.LayoutParams");
        }
        RecyclerView.LayoutParams layoutParams3 = (RecyclerView.LayoutParams) layoutParams2;
        ((ViewGroup.MarginLayoutParams) layoutParams3).topMargin = view.getResources().getDimensionPixelSize(R.dimen.action_bar_height);
        view.setLayoutParams(layoutParams3);
        if (element.image() != null) {
            ConstraintLayout layout = view.getLayout();
            layout.setPadding(layout.getPaddingLeft(), layout.getResources().getDimensionPixelSize(R.dimen.generic_header_top_padding), layout.getPaddingRight(), layout.getPaddingBottom());
        }
        TextViewKt.collapseIfEmpty(view.getPrimaryText());
        TextViewKt.collapseIfEmpty(view.getSecondaryText());
        TextViewKt.collapseIfEmpty(view.getTertiaryText());
        com.amazon.ui.foundations.ktx.ViewKt.collapseIf(view.getActionButtons(), view.getActionButtons().getChildCount() == 0);
        OnViewedTrigger.Companion companion = OnViewedTrigger.INSTANCE;
        DispatcherContext dispatcherContext = getDispatcherContext();
        List<Method> listOnViewed = element.onViewed();
        Intrinsics.checkNotNullExpressionValue(listOnViewed, "element.onViewed()");
        companion.emitOnViewed(dispatcherContext, listOnViewed);
    }
}
