package com.amazon.music.views.library.binders;

import android.content.Context;
import com.amazon.music.uicontentview.ContentViewedListener;
import com.amazon.music.views.library.models.ExposedRefinementListModel;
import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.RefinementPillModel;
import com.amazon.music.views.library.providers.RefinementClickListenerProvider;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.ExposedRefinementListView;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: ExposedRefinementListBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B)\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0003H\u0016J\u0010\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J \u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0018\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/amazon/music/views/library/binders/ExposedRefinementListBinder;", "Lcom/amazon/music/views/library/binders/BaseStateChangeBinder;", "Lcom/amazon/music/views/library/views/ExposedRefinementListView;", "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "refinementClickListenerProvider", "Lcom/amazon/music/views/library/providers/RefinementClickListenerProvider;", "adapterProvider", "Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;", "contentViewedListener", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/providers/RefinementClickListenerProvider;Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;Lcom/amazon/music/uicontentview/ContentViewedListener;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "createView", "context", "Landroid/content/Context;", "handleStateChange", "payload", "", "reportContentView", "identifier", "", "numItemsAccessible", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ExposedRefinementListBinder implements BaseStateChangeBinder<ExposedRefinementListView, ExposedRefinementListModel> {
    private final BaseViewModelAdapterProvider adapterProvider;
    private final ContentViewedListener contentViewedListener;
    private final RefinementClickListenerProvider refinementClickListenerProvider;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<ExposedRefinementListModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(ExposedRefinementListModel.class);
    }

    public ExposedRefinementListBinder(StyleSheet styleSheet, RefinementClickListenerProvider refinementClickListenerProvider, BaseViewModelAdapterProvider adapterProvider, ContentViewedListener contentViewedListener) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(refinementClickListenerProvider, "refinementClickListenerProvider");
        Intrinsics.checkNotNullParameter(adapterProvider, "adapterProvider");
        this.styleSheet = styleSheet;
        this.refinementClickListenerProvider = refinementClickListenerProvider;
        this.adapterProvider = adapterProvider;
        this.contentViewedListener = contentViewedListener;
    }

    public /* synthetic */ ExposedRefinementListBinder(StyleSheet styleSheet, RefinementClickListenerProvider refinementClickListenerProvider, BaseViewModelAdapterProvider baseViewModelAdapterProvider, ContentViewedListener contentViewedListener, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(styleSheet, refinementClickListenerProvider, baseViewModelAdapterProvider, (i & 8) != 0 ? null : contentViewedListener);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public ExposedRefinementListView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ExposedRefinementListView exposedRefinementListView = new ExposedRefinementListView(context);
        exposedRefinementListView.init(this.styleSheet);
        return exposedRefinementListView;
    }

    @Override // com.amazon.music.views.library.binders.BaseStateChangeBinder
    public void handleStateChange(ExposedRefinementListView view, ExposedRefinementListModel model, Object payload) {
        UniversalAdapter adapter;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (Intrinsics.areEqual(payload, (Object) 13)) {
            if (model.getVisibility() == 0) {
                view.setScrollState(model.getScrollOffset());
                view.setScrollStateListener(model);
            } else {
                view.setScrollStateListener(null);
            }
            if (model.getVisibility() == 0 && (adapter = view.getAdapter()) != null) {
                adapter.notifyDataSetChanged();
            }
            view.setVisibility(model.getVisibility());
        }
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(ExposedRefinementListView view, ExposedRefinementListModel model) {
        Unit unit;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        view.updateStyle(this.styleSheet, model.getRefinementListType());
        UniversalAdapter adapter = view.getAdapter();
        if (adapter != null) {
            BaseViewModelAdapterProvider baseViewModelAdapterProvider = this.adapterProvider;
            List<RefinementPillModel> refinementList = model.getRefinementList();
            if (refinementList == null) {
                refinementList = CollectionsKt__CollectionsKt.emptyList();
            }
            baseViewModelAdapterProvider.updateItems(adapter, refinementList);
        } else {
            List<RefinementPillModel> refinementList2 = model.getRefinementList();
            if (refinementList2 != null) {
                view.setAdapter(this.adapterProvider.getAdapter(refinementList2, new RefinementPillBinder(this.styleSheet, this.refinementClickListenerProvider)));
            }
        }
        FilterCollectionModel focusedModel = model.getFocusedModel();
        if (focusedModel == null) {
            unit = null;
        } else {
            view.setFocusedState(focusedModel);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            view.clearFocusedState();
        }
        view.setScrollState(model.getScrollOffset());
        view.setScrollStateListener(model);
        view.setVisibility(model.getVisibility());
        String viewReferenceId = model.getViewReferenceId();
        if (viewReferenceId == null) {
            return;
        }
        reportContentView(viewReferenceId, model.getFilterModels().size());
    }

    private final void reportContentView(String identifier, int numItemsAccessible) {
        if (this.contentViewedListener == null) {
            return;
        }
        HashMap map = new HashMap();
        map.put("blockRef", identifier);
        String string = Integer.toString(0);
        Intrinsics.checkNotNullExpressionValue(string, "toString(0)");
        map.put("firstViewableIndex", string);
        String string2 = Integer.toString(numItemsAccessible - 1);
        Intrinsics.checkNotNullExpressionValue(string2, "toString(numItemsAccessible - 1)");
        map.put("lastViewableIndex", string2);
        String string3 = Integer.toString(numItemsAccessible);
        Intrinsics.checkNotNullExpressionValue(string3, "toString(numItemsAccessible)");
        map.put("numItemsAccessible", string3);
        this.contentViewedListener.onItemRendered(identifier, map);
    }
}
