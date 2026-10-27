package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Element;
import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.ButtonElement;
import Touch.WidgetsInterface.v1_0.FullScreenButtonWidgetElement;
import Touch.WidgetsInterface.v1_0.TextButtonElement;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.ktx.SkyFireKt;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.utils.OnViewedTrigger;
import com.amazon.music.skyfire.ui.widgets.views.FullScreenButtonWidgetView;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: FullScreenButtonWidgetBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/FullScreenButtonWidgetBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/FullScreenButtonWidgetElement;", "Lcom/amazon/music/skyfire/ui/widgets/views/FullScreenButtonWidgetView;", "()V", "bind", "", "element", "view", "createView", "parent", "Landroid/view/ViewGroup;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FullScreenButtonWidgetBinder extends ElementBinder<FullScreenButtonWidgetElement, FullScreenButtonWidgetView> {
    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public FullScreenButtonWidgetView createView(ViewGroup parent) {
        return new FullScreenButtonWidgetView(getContext());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [T, Touch.WidgetsInterface.v1_0.ButtonElement] */
    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(FullScreenButtonWidgetElement element, FullScreenButtonWidgetView view) {
        BaseButton.StyleBuilder buttonBuilder;
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        view.removeAllViews();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = element.button();
        TextButtonBinder textButtonBinder = new TextButtonBinder();
        textButtonBinder.setButtonSizeKey(ButtonSizeKey.MEDIUM_WITH_ICON);
        textButtonBinder.setButtonStyleKey(ButtonStyleKey.SOLID);
        textButtonBinder.setBindingProvider(getBindingProvider());
        ElementBindingProvider bindingProvider = getBindingProvider();
        T button = objectRef.element;
        Intrinsics.checkNotNullExpressionValue(button, "button");
        View viewCreateView = bindingProvider.createView((Element) button, view);
        if (viewCreateView != null) {
            BaseButton baseButton = (BaseButton) viewCreateView;
            T button2 = objectRef.element;
            if (((ButtonElement) button2) instanceof TextButtonElement) {
                Intrinsics.checkNotNullExpressionValue(button2, "button");
                textButtonBinder.bind((TextButtonElement) button2, baseButton);
            } else {
                ElementBindingProvider bindingProvider2 = getBindingProvider();
                T button3 = objectRef.element;
                Intrinsics.checkNotNullExpressionValue(button3, "button");
                bindingProvider2.bind((Element) button3, viewCreateView);
            }
            StyleSheet styleSheet = getStyleSheet();
            ButtonStyleKey buttonStyleKey = ButtonStyleKey.GLASS_OUTLINE;
            if (styleSheet.getButtonStyle(buttonStyleKey) != null && (buttonBuilder = getStyleSheet().getButtonBuilder(ButtonSizeKey.MEDIUM, buttonStyleKey)) != null) {
                buttonBuilder.applyStyle(baseButton);
            }
            Resources resources = baseButton.getResources();
            int i = R.dimen.spacer_large;
            viewCreateView.setPadding(resources.getDimensionPixelSize(i), baseButton.getResources().getDimensionPixelSize(R.dimen.spacer_giant), baseButton.getResources().getDimensionPixelSize(i), baseButton.getPaddingBottom());
            viewCreateView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.skyfire.ui.widgets.binders.FullScreenButtonWidgetBinder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    FullScreenButtonWidgetBinder.m2217bind$lambda4$lambda3(this.f$0, objectRef, view2);
                }
            });
            view.addView(viewCreateView);
        }
        OnViewedTrigger.Companion companion = OnViewedTrigger.INSTANCE;
        DispatcherContext dispatcherContext = getDispatcherContext();
        List<Method> listOnViewed = element.onViewed();
        Intrinsics.checkNotNullExpressionValue(listOnViewed, "element.onViewed()");
        companion.emitOnViewed(dispatcherContext, listOnViewed);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: bind$lambda-4$lambda-3, reason: not valid java name */
    public static final void m2217bind$lambda4$lambda3(FullScreenButtonWidgetBinder this$0, Ref.ObjectRef button, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(button, "$button");
        DispatcherContext dispatcherContext = this$0.getDispatcherContext();
        if (dispatcherContext == null) {
            return;
        }
        List<Method> listOnItemSelected = ((ButtonElement) button.element).onItemSelected();
        Intrinsics.checkNotNullExpressionValue(listOnItemSelected, "button.onItemSelected()");
        SkyFireKt.dispatch$default(dispatcherContext, listOnItemSelected, (String) null, 2, (Object) null);
    }
}
