package com.amazon.music.skyfire.ui.providers;

import CoreInterface.v1_0.Element;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.widgets.binders.ElementBinder;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Metadata;

/* JADX INFO: compiled from: ElementBindingProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H&J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u0013H&J \u0010\u001b\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001dH&J\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H&J\u0018\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u001d2\u0006\u0010\u0016\u001a\u00020\u0017H&J\u001e\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0013\u0018\u00010!2\u0006\u0010\u0014\u001a\u00020\u0015H&J\u0018\u0010\"\u001a\u00020\u001d2\u000e\u0010#\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150$H&R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u0004\u0018\u00010\tX¦\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006%"}, d2 = {"Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;", "", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "dispatcherContext", "Lcom/amazon/music/skyfire/ui/skyfire/DispatcherContext;", "getDispatcherContext", "()Lcom/amazon/music/skyfire/ui/skyfire/DispatcherContext;", "setDispatcherContext", "(Lcom/amazon/music/skyfire/ui/skyfire/DispatcherContext;)V", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "attachView", "Landroid/view/View;", "element", "LCoreInterface/v1_0/Element;", "parent", "Landroid/view/ViewGroup;", "bind", "", "view", "bindItem", "width", "", "createView", "viewType", "getBinder", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "getViewType", "elementType", "Ljava/lang/Class;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface ElementBindingProvider {
    View attachView(Element element, ViewGroup parent);

    void bind(Element element, View view);

    void bindItem(Element element, View view, int width);

    View createView(int viewType, ViewGroup parent);

    View createView(Element element, ViewGroup parent);

    ElementBinder<Element, View> getBinder(Element element);

    Context getContext();

    DispatcherContext getDispatcherContext();

    StyleSheet getStyleSheet();

    int getViewType(Class<? extends Element> elementType);

    void setContext(Context context);

    void setDispatcherContext(DispatcherContext dispatcherContext);
}
