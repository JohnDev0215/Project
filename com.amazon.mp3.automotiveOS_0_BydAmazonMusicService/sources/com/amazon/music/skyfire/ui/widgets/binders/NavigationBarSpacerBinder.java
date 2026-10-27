package com.amazon.music.skyfire.ui.widgets.binders;

import Touch.WidgetsInterface.v1_0.NavigationBarSpacerElement;
import android.view.ViewGroup;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.widgets.views.NavigationBarSpacerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NavigationBarSpacerBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/NavigationBarSpacerBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/NavigationBarSpacerElement;", "Lcom/amazon/music/skyfire/ui/widgets/views/NavigationBarSpacerView;", "()V", "bind", "", "element", "view", "createView", "parent", "Landroid/view/ViewGroup;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class NavigationBarSpacerBinder extends ElementBinder<NavigationBarSpacerElement, NavigationBarSpacerView> {
    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public NavigationBarSpacerView createView(ViewGroup parent) {
        return new NavigationBarSpacerView(getContext());
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(NavigationBarSpacerElement element, NavigationBarSpacerView view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        view.setPadding(0, (int) getContext().getResources().getDimension(R.dimen.action_bar_height), 0, 0);
    }
}
