package com.amazon.music.explore.widgets.binders;

import Touch.WidgetsInterface.v1_0.SeeMoreItemElement;
import Touch.WidgetsInterface.v1_0.VisualShovelerElement;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.explore.widgets.models.VisualShovelerModel;
import com.amazon.music.views.library.binders.GridLayoutUtils;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.binders.VerticalTileBinder;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.SectionWidget;
import com.amazon.ui.foundations.styles.GridSize;
import java.util.List;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: loaded from: classes4.dex */
public class ExploreVisualShovelerBinder implements UniversalBinder<SectionWidget, VisualShovelerModel> {
    private final BaseViewModelAdapterProvider adapterProvider;
    private final Context context;
    private final GridLayoutUtils gridLayoutUtils;
    private StyleSheet styleSheet;
    private final VerticalTileBinder verticalTileBinder;

    public ExploreVisualShovelerBinder(Context context, StyleSheet styleSheet, BaseViewModelAdapterProvider baseViewModelAdapterProvider, VerticalTileBinder verticalTileBinder) {
        this.context = context;
        this.styleSheet = styleSheet;
        this.adapterProvider = baseViewModelAdapterProvider;
        this.verticalTileBinder = verticalTileBinder;
        this.gridLayoutUtils = new GridLayoutUtils(context, styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<VisualShovelerModel> getModelClass() {
        return Reflection.createKotlinClass(VisualShovelerModel.class);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public SectionWidget createView(Context context) {
        SectionWidget sectionWidget = new SectionWidget(context, this.styleSheet);
        sectionWidget.setContentView(new RecyclerView(context));
        return sectionWidget;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(SectionWidget sectionWidget, final VisualShovelerModel visualShovelerModel) {
        VisualShovelerElement widget = visualShovelerModel.getWidget();
        List<BaseViewModel> verticalTileModels = visualShovelerModel.getVerticalTileModels();
        int i = widget.isCompact() ? 17 : 5;
        int shovelerItemWidth = this.gridLayoutUtils.getShovelerItemWidth(widget.items().size(), i);
        BaseViewModelAdapterProvider baseViewModelAdapterProvider = this.adapterProvider;
        if (baseViewModelAdapterProvider != null) {
            UniversalAdapter adapter = baseViewModelAdapterProvider.getAdapter(verticalTileModels, this.verticalTileBinder);
            adapter.setTileViewWidth(shovelerItemWidth);
            sectionWidget.setAdapter(adapter);
        }
        String strPrimaryHeader = widget.primaryHeader() != null ? widget.primaryHeader() : "";
        String strSecondaryHeader = widget.secondaryHeader() != null ? widget.secondaryHeader() : "";
        sectionWidget.setTitle(strPrimaryHeader);
        sectionWidget.setSubtitle(strSecondaryHeader);
        final SeeMoreItemElement seeMoreItemElementSeeMore = widget.seeMore();
        if (seeMoreItemElementSeeMore != null) {
            sectionWidget.setMoreButtonText(seeMoreItemElementSeeMore.text());
            sectionWidget.setMoreButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.explore.widgets.binders.ExploreVisualShovelerBinder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ExploreVisualShovelerBinder.lambda$bind$0(visualShovelerModel, seeMoreItemElementSeeMore, view);
                }
            });
        }
        this.gridLayoutUtils.setLayoutManager(sectionWidget.getRecyclerView(), i, Integer.valueOf(widget.items().size()));
        bindStyle(sectionWidget, widget);
        ViewTriggerUtility.getInstance().triggerElementView(visualShovelerModel, widget.onViewed());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$bind$0(VisualShovelerModel visualShovelerModel, SeeMoreItemElement seeMoreItemElement, View view) {
        visualShovelerModel.getMethodsDispatcher().dispatchMethods(visualShovelerModel.getOwnerId(), seeMoreItemElement.onItemSelected());
    }

    private void bindStyle(SectionWidget sectionWidget, VisualShovelerElement visualShovelerElement) {
        StyleSheet styleSheet = this.styleSheet;
        SpacerKey spacerKey = SpacerKey.LARGE;
        int iIntValue = styleSheet.getSpacerInPixels(spacerKey) != null ? this.styleSheet.getSpacerInPixels(spacerKey).intValue() : 0;
        sectionWidget.setMargins(0, null, 0, null, sectionWidget);
        if (!hasHeaderContent(visualShovelerElement)) {
            iIntValue = 0;
        }
        sectionWidget.setContentMargins(0, Integer.valueOf(iIntValue), 0, null);
        GridSize gridSize = this.styleSheet.getGridSize();
        RecyclerView recyclerView = sectionWidget.getRecyclerView();
        if (gridSize == null || recyclerView == null) {
            return;
        }
        recyclerView.setClipToPadding(false);
        recyclerView.setPadding(gridSize.getMarginSpace() + (gridSize.getColumnSpace() / 2), 0, 0, 0);
        sectionWidget.setTextMargins(Integer.valueOf(gridSize.getMarginSpace() + gridSize.getColumnSpace()), null, null, null);
    }

    private boolean hasHeaderContent(VisualShovelerElement visualShovelerElement) {
        return (TextUtils.isEmpty(visualShovelerElement.primaryHeader()) && TextUtils.isEmpty(visualShovelerElement.secondaryHeader())) ? false : true;
    }
}
