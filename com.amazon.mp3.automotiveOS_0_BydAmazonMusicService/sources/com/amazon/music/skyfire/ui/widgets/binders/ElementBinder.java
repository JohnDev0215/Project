package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Element;
import CoreInterface.v1_0.Method;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.amazon.music.skyfire.SkyFireApplication;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.ui.providers.DownloadProvider;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ElementBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u00020\u0005B\u0005¢\u0006\u0002\u0010\u0006J\u001d\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00028\u00002\u0006\u0010+\u001a\u00028\u0001H&¢\u0006\u0002\u0010,J\u001d\u0010-\u001a\u00028\u00012\u0006\u0010*\u001a\u00028\u00002\b\u0010.\u001a\u0004\u0018\u00010/¢\u0006\u0002\u00100J\u0017\u00101\u001a\u00028\u00012\b\u0010.\u001a\u0004\u0018\u00010/H&¢\u0006\u0002\u00102J\u001d\u00103\u001a\u00020)2\u0006\u0010*\u001a\u00028\u00002\u0006\u0010+\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010,J\u001d\u00104\u001a\u00020)2\u0006\u0010*\u001a\u00028\u00002\u0006\u0010+\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010,R\u001a\u0010\u0007\u001a\u00020\bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010 \u001a\u00020!8F¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0011\u0010$\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\b&\u0010'¨\u00065"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "E", "LCoreInterface/v1_0/Element;", "V", "Landroid/view/View;", "", "()V", "bindingProvider", "Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;", "getBindingProvider", "()Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;", "setBindingProvider", "(Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;)V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "dispatcherContext", "Lcom/amazon/music/skyfire/ui/skyfire/DispatcherContext;", "getDispatcherContext", "()Lcom/amazon/music/skyfire/ui/skyfire/DispatcherContext;", "downloadProvider", "Lcom/amazon/music/skyfire/ui/providers/DownloadProvider;", "getDownloadProvider", "()Lcom/amazon/music/skyfire/ui/providers/DownloadProvider;", "setDownloadProvider", "(Lcom/amazon/music/skyfire/ui/providers/DownloadProvider;)V", "methodsDispatcher", "Lcom/amazon/music/skyfire/platform/MethodsDispatcher;", "LCoreInterface/v1_0/Method;", "getMethodsDispatcher", "()Lcom/amazon/music/skyfire/platform/MethodsDispatcher;", "ownerId", "", "getOwnerId", "()Ljava/lang/String;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "bind", "", "element", "view", "(LCoreInterface/v1_0/Element;Landroid/view/View;)V", "createAndBind", "parent", "Landroid/view/ViewGroup;", "(LCoreInterface/v1_0/Element;Landroid/view/ViewGroup;)Landroid/view/View;", "createView", "(Landroid/view/ViewGroup;)Landroid/view/View;", "onAttached", "onDetached", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class ElementBinder<E extends Element, V extends View> {
    public ElementBindingProvider bindingProvider;
    public DownloadProvider downloadProvider;

    public abstract void bind(E element, V view);

    public abstract V createView(ViewGroup parent);

    public void onAttached(E element, V view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
    }

    public void onDetached(E element, V view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
    }

    public final ElementBindingProvider getBindingProvider() {
        ElementBindingProvider elementBindingProvider = this.bindingProvider;
        if (elementBindingProvider != null) {
            return elementBindingProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bindingProvider");
        return null;
    }

    public final void setBindingProvider(ElementBindingProvider elementBindingProvider) {
        Intrinsics.checkNotNullParameter(elementBindingProvider, "<set-?>");
        this.bindingProvider = elementBindingProvider;
    }

    public final DownloadProvider getDownloadProvider() {
        DownloadProvider downloadProvider = this.downloadProvider;
        if (downloadProvider != null) {
            return downloadProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("downloadProvider");
        return null;
    }

    public final void setDownloadProvider(DownloadProvider downloadProvider) {
        Intrinsics.checkNotNullParameter(downloadProvider, "<set-?>");
        this.downloadProvider = downloadProvider;
    }

    public final Context getContext() {
        Context context = getBindingProvider().getContext();
        Intrinsics.checkNotNull(context);
        return context;
    }

    public final DispatcherContext getDispatcherContext() {
        return getBindingProvider().getDispatcherContext();
    }

    public final MethodsDispatcher<Method> getMethodsDispatcher() {
        DispatcherContext dispatcherContext = getDispatcherContext();
        SkyFireApplication skyFireApplication = dispatcherContext == null ? null : dispatcherContext.getSkyFireApplication();
        Intrinsics.checkNotNull(skyFireApplication);
        return skyFireApplication;
    }

    public final String getOwnerId() {
        DispatcherContext dispatcherContext = getDispatcherContext();
        String listenerId = dispatcherContext == null ? null : dispatcherContext.getListenerId();
        Intrinsics.checkNotNull(listenerId);
        return listenerId;
    }

    public final StyleSheet getStyleSheet() {
        return getBindingProvider().getStyleSheet();
    }

    public final V createAndBind(E element, ViewGroup parent) {
        Intrinsics.checkNotNullParameter(element, "element");
        V v = (V) createView(parent);
        bind(element, v);
        return v;
    }
}
