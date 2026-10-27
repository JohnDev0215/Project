package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.ButtonElement;
import Touch.WidgetsInterface.v1_0.SecondaryGenericHeaderElement;
import Touch.WidgetsInterface.v1_0.TextButtonElement;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.ktx.ViewKt;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.utils.OnViewedTrigger;
import com.amazon.music.skyfire.ui.widgets.views.SecondaryGenericHeaderView;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.ktx.TextViewKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SecondaryGenericHeaderBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/SecondaryGenericHeaderBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/SecondaryGenericHeaderElement;", "Lcom/amazon/music/skyfire/ui/widgets/views/SecondaryGenericHeaderView;", "()V", "bind", "", "element", "view", "createView", "parent", "Landroid/view/ViewGroup;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SecondaryGenericHeaderBinder extends ElementBinder<SecondaryGenericHeaderElement, SecondaryGenericHeaderView> {
    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public SecondaryGenericHeaderView createView(ViewGroup parent) {
        return new SecondaryGenericHeaderView(getContext());
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(SecondaryGenericHeaderElement element, SecondaryGenericHeaderView view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        ViewKt.applyFontStyle(view.getLabel(), getStyleSheet(), FontStyleKey.LABEL);
        view.getLabel().setText(element.label());
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
        for (ButtonElement b : listButtons) {
            ElementBindingProvider bindingProvider = getBindingProvider();
            Intrinsics.checkNotNullExpressionValue(b, "b");
            View viewAttachView = bindingProvider.attachView(b, view.getActionButtons());
            if (viewAttachView != null) {
                viewAttachView.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getResources().getDimensionPixelSize(R.dimen.spacer_small), view.getPaddingBottom());
            }
        }
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
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView.LayoutParams");
        }
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
        ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin = view.getResources().getDimensionPixelSize(R.dimen.action_bar_height);
        view.setLayoutParams(layoutParams2);
        if (element.image() != null) {
            ConstraintLayout layout = view.getLayout();
            layout.setPadding(layout.getPaddingLeft(), layout.getResources().getDimensionPixelSize(R.dimen.generic_header_top_padding), layout.getPaddingRight(), layout.getPaddingBottom());
        }
        TextViewKt.collapseIfEmpty(view.getLabel());
        TextViewKt.collapseIfEmpty(view.getPrimaryText());
        TextViewKt.collapseIfEmpty(view.getSecondaryText());
        TextViewKt.collapseIfEmpty(view.getTertiaryText());
        com.amazon.ui.foundations.ktx.ViewKt.collapseIf(view.getActionButtons(), view.getActionButtons().getChildCount() == 0);
        com.amazon.ui.foundations.ktx.ViewKt.collapseIf(view.getCallToActionButton(), element.callToActionButton() == null);
        OnViewedTrigger.Companion companion = OnViewedTrigger.INSTANCE;
        DispatcherContext dispatcherContext = getDispatcherContext();
        List<Method> listOnViewed = element.onViewed();
        Intrinsics.checkNotNullExpressionValue(listOnViewed, "element.onViewed()");
        companion.emitOnViewed(dispatcherContext, listOnViewed);
    }
}
