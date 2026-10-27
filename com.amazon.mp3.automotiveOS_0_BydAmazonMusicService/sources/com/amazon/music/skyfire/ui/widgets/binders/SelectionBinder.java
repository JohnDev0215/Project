package com.amazon.music.skyfire.ui.widgets.binders;

import Touch.WidgetsInterface.v1_0.SelectionElement;
import android.view.ViewGroup;
import com.amazon.music.skyfire.ui.ktx.ViewKt;
import com.amazon.music.skyfire.ui.widgets.views.SelectionView;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SelectionBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/SelectionBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/SelectionElement;", "Lcom/amazon/music/skyfire/ui/widgets/views/SelectionView;", "()V", "bind", "", "element", "view", "createView", "parent", "Landroid/view/ViewGroup;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SelectionBinder extends ElementBinder<SelectionElement, SelectionView> {
    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public SelectionView createView(ViewGroup parent) {
        return new SelectionView(getContext());
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(SelectionElement element, SelectionView view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        ViewKt.applyFontStyle(view.getPrimaryText(), getStyleSheet(), FontStyleKey.PRIMARY);
        view.getPrimaryText().setText(element.primaryText());
        ViewKt.applyFontStyle(view.getSecondaryText(), getStyleSheet(), FontStyleKey.SECONDARY);
        view.getSecondaryText().setText(element.secondaryText());
    }
}
