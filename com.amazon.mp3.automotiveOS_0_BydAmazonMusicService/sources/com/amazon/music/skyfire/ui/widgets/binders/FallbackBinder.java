package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Element;
import android.view.View;
import android.view.ViewGroup;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: FallbackBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0003H\u0016J\u0012\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/FallbackBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LCoreInterface/v1_0/Element;", "Landroid/view/View;", "()V", "logger", "Lorg/slf4j/Logger;", "bind", "", "element", "view", "createView", "parent", "Landroid/view/ViewGroup;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FallbackBinder extends ElementBinder<Element, View> {
    private final Logger logger;

    public FallbackBinder() {
        Logger logger = LoggerFactory.getLogger(FallbackBinder.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(javaClass.simpleName)");
        this.logger = logger;
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public View createView(ViewGroup parent) {
        return new View(getContext());
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(Element element, View view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        this.logger.warn("No binding found for '" + element.getClass() + "'; using a fallback binder");
    }
}
