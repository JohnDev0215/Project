package com.amazon.music.explore.widgets.binders;

import Touch.WidgetsInterface.v1_0.FeaturedHorizontalShovelerElement;
import android.content.Context;
import android.text.TextUtils;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.explore.widgets.models.FeaturedShovelerModel;
import com.amazon.music.views.library.binders.GridLayoutUtils;
import com.amazon.music.views.library.binders.HorizontalTileBinder;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.SectionWidget;
import com.amazon.ui.foundations.styles.GridSize;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.List;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: loaded from: classes4.dex */
public class ExploreFeaturedShovelerBinder implements UniversalBinder<SectionWidget, FeaturedShovelerModel> {
    private static int TILE_LAYOUT_TYPE = 9;
    private final BaseViewModelAdapterProvider adapterProvider;
    private final Context context;
    private final GridLayoutUtils gridLayoutUtils;
    private final ExploreHorizontalItemBinder horizontalItemBinder;
    private final HorizontalTileBinder horizontalTileBinder;
    private final StyleSheet styleSheet;

    public ExploreFeaturedShovelerBinder(Context context, StyleSheet styleSheet, BaseViewModelAdapterProvider baseViewModelAdapterProvider, HorizontalTileBinder horizontalTileBinder, ExploreHorizontalItemBinder exploreHorizontalItemBinder) {
        this.context = context;
        this.styleSheet = styleSheet;
        this.adapterProvider = baseViewModelAdapterProvider;
        this.horizontalTileBinder = horizontalTileBinder;
        this.gridLayoutUtils = new GridLayoutUtils(context, styleSheet);
        this.horizontalItemBinder = exploreHorizontalItemBinder;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<FeaturedShovelerModel> getModelClass() {
        return Reflection.createKotlinClass(FeaturedShovelerModel.class);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public SectionWidget createView(Context context) {
        SectionWidget sectionWidget = new SectionWidget(context, this.styleSheet);
        sectionWidget.setContentView(new RecyclerView(context));
        return sectionWidget;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(SectionWidget sectionWidget, FeaturedShovelerModel featuredShovelerModel) {
        FeaturedHorizontalShovelerElement widget = featuredShovelerModel.getWidget();
        List<BaseViewModel> childrenModels = featuredShovelerModel.getChildrenModels();
        if (CollectionUtils.isEmpty(childrenModels)) {
            return;
        }
        bindModels(sectionWidget, childrenModels);
        String strPrimaryHeader = widget.primaryHeader() != null ? widget.primaryHeader() : "";
        String strSecondaryHeader = widget.secondaryHeader() != null ? widget.secondaryHeader() : "";
        sectionWidget.setTitle(strPrimaryHeader);
        sectionWidget.setSubtitle(strSecondaryHeader);
        showView(sectionWidget, featuredShovelerModel);
        ViewTriggerUtility.getInstance().triggerElementView(featuredShovelerModel, featuredShovelerModel.getWidget().onViewed());
    }

    private void showView(SectionWidget sectionWidget, FeaturedShovelerModel featuredShovelerModel) {
        if (sectionWidget.getRecyclerView() != null) {
            this.gridLayoutUtils.setLayoutManager(sectionWidget.getRecyclerView(), TILE_LAYOUT_TYPE, Integer.valueOf(featuredShovelerModel.getChildrenModels().size()));
        }
        bindStyle(sectionWidget, featuredShovelerModel);
        if (this.gridLayoutUtils.shouldDisplayPeek(Integer.valueOf(featuredShovelerModel.getChildrenModels().size()), TILE_LAYOUT_TYPE)) {
            int shovelerItemWidth = this.gridLayoutUtils.getShovelerItemWidth(featuredShovelerModel.getChildrenModels().size(), TILE_LAYOUT_TYPE);
            if (sectionWidget.getAdapter() != null) {
                sectionWidget.getAdapter().setTileViewWidth(shovelerItemWidth);
            }
        }
    }

    private void bindModels(SectionWidget sectionWidget, List<BaseViewModel> list) {
        sectionWidget.setAdapter(this.adapterProvider.getAdapter(list, this.horizontalTileBinder, this.horizontalItemBinder));
    }

    private void bindStyle(SectionWidget sectionWidget, FeaturedShovelerModel featuredShovelerModel) {
        if (this.gridLayoutUtils.shouldDisplayPeek(Integer.valueOf(featuredShovelerModel.getChildrenModels().size()), TILE_LAYOUT_TYPE)) {
            sectionWidget.setMargins(0, null, 0, null, sectionWidget);
            sectionWidget.setContentMargins(0, null, 0, null);
            GridSize gridSize = this.styleSheet.getGridSize();
            if (gridSize != null) {
                RecyclerView recyclerView = sectionWidget.getRecyclerView();
                if (recyclerView != null) {
                    recyclerView.setClipToPadding(false);
                    recyclerView.setPadding(gridSize.getMarginSpace() + (gridSize.getColumnSpace() / 2), 0, 0, 0);
                }
                StyleSheet styleSheet = this.styleSheet;
                SpacerKey spacerKey = SpacerKey.SMALL;
                sectionWidget.setTextMargins(null, null, null, Integer.valueOf(displayingHeaderText(featuredShovelerModel.getWidget()) ? styleSheet.getSpacerInPixels(spacerKey) != null ? this.styleSheet.getSpacerInPixels(spacerKey).intValue() : 0 : 0));
            }
        }
    }

    private boolean displayingHeaderText(FeaturedHorizontalShovelerElement featuredHorizontalShovelerElement) {
        return (TextUtils.isEmpty(featuredHorizontalShovelerElement.primaryHeader()) && TextUtils.isEmpty(featuredHorizontalShovelerElement.secondaryHeader())) ? false : true;
    }
}
