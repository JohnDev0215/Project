package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.RefinementCollectionElement;
import Touch.WidgetsInterface.v1_0.RefinementElement;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.utils.OnViewedTrigger;
import com.amazon.music.skyfire.ui.widgets.adapters.RefinementElementAdapter;
import com.amazon.music.views.library.views.ExposedRefinementListView;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RefinementCollectionBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/RefinementCollectionBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/RefinementCollectionElement;", "Lcom/amazon/music/views/library/views/ExposedRefinementListView;", "()V", "bind", "", "element", "view", "createView", "parent", "Landroid/view/ViewGroup;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RefinementCollectionBinder extends ElementBinder<RefinementCollectionElement, ExposedRefinementListView> {
    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public ExposedRefinementListView createView(ViewGroup parent) {
        ExposedRefinementListView exposedRefinementListView = new ExposedRefinementListView(getContext());
        exposedRefinementListView.init(getStyleSheet());
        return exposedRefinementListView;
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(RefinementCollectionElement element, ExposedRefinementListView view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        RecyclerView recyclerView = view.getRecyclerView();
        RefinementElementAdapter refinementElementAdapter = new RefinementElementAdapter(getBindingProvider());
        List<RefinementElement> listRefinementOptions = element.refinementOptions();
        Intrinsics.checkNotNullExpressionValue(listRefinementOptions, "element.refinementOptions()");
        refinementElementAdapter.addItems(listRefinementOptions);
        recyclerView.setAdapter(refinementElementAdapter);
        view.setTag(element.widgetId());
        OnViewedTrigger.Companion companion = OnViewedTrigger.INSTANCE;
        DispatcherContext dispatcherContext = getDispatcherContext();
        List<Method> listOnViewed = element.onViewed();
        Intrinsics.checkNotNullExpressionValue(listOnViewed, "element.onViewed()");
        companion.emitOnViewed(dispatcherContext, listOnViewed);
    }
}
