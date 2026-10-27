package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.MessageElement;
import Touch.WidgetsInterface.v1_0.TextButtonElement;
import android.content.Context;
import android.view.ViewGroup;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.ui.ktx.ViewKt;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.skyfire.ui.widgets.views.MessageView;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.styles.GridSize;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MessageBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/MessageBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/MessageElement;", "Lcom/amazon/music/skyfire/ui/widgets/views/MessageView;", "()V", "bind", "", "element", "view", "createView", "parent", "Landroid/view/ViewGroup;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MessageBinder extends ElementBinder<MessageElement, MessageView> {
    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public MessageView createView(ViewGroup parent) {
        return new MessageView(getContext());
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(MessageElement element, MessageView view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        StyleSheet styleSheet = getStyleSheet();
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        GridSize gridSize = styleSheet.getGridSize(context);
        if (gridSize != null) {
            int marginSpace = gridSize.getMarginSpace() + gridSize.getColumnSpace();
            view.setPadding(marginSpace, view.getPaddingTop(), marginSpace, view.getPaddingBottom());
        }
        ViewKt.applyFontStyle(view.getMessage(), getStyleSheet(), FontStyleKey.HEADLINE_4);
        view.getMessage().setText(element.message());
        ViewKt.applyFontStyle(view.getSubMessage(), getStyleSheet(), FontStyleKey.PRIMARY);
        view.getSubMessage().setText(element.subMessage());
        view.getButtons().removeAllViews();
        List<TextButtonElement> listButtons = element.buttons();
        Intrinsics.checkNotNullExpressionValue(listButtons, "element.buttons()");
        for (TextButtonElement b : listButtons) {
            ElementBindingProvider bindingProvider = getBindingProvider();
            Intrinsics.checkNotNullExpressionValue(b, "b");
            bindingProvider.attachView(b, view.getButtons());
        }
        MethodsDispatcher<Method> methodsDispatcher = getMethodsDispatcher();
        String ownerId = getOwnerId();
        List<Method> listOnViewed = element.onViewed();
        Intrinsics.checkNotNullExpressionValue(listOnViewed, "element.onViewed()");
        methodsDispatcher.dispatchMethods(ownerId, listOnViewed);
    }
}
