package com.amazon.mp3.view.refinements;

import com.amazon.music.views.library.models.ExposedRefinementListModel;
import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.FilterItemModel;
import com.amazon.music.views.library.models.RefinementPillModel;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseViewsRefinementModelBuilder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002JU\u0010\u0006\u001a\u00020\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\u0002\u0010\u0013J>\u0010\u0014\u001a\u00020\u00002\b\u0010\u0015\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u0016\u001a\u00020\u000e2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0019\u001a\u00020\u001aR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/amazon/mp3/view/refinements/BaseViewsRefinementModelBuilder;", "", "()V", "refinementFilterList", "", "Lcom/amazon/music/views/library/models/RefinementPillModel;", "addFilterCollectionModel", "blockRef", "", "title", "filters", "", "Lcom/amazon/music/views/library/models/FilterItemModel;", "canHaveMultipleSelections", "", "defaultSelectedFilter", "isSortCollection", "viewId", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ZLcom/amazon/music/views/library/models/FilterItemModel;ZLjava/lang/Integer;)Lcom/amazon/mp3/view/refinements/BaseViewsRefinementModelBuilder;", "addFilterModel", "browseId", "selected", "parentCollection", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BaseViewsRefinementModelBuilder {
    private final List<RefinementPillModel> refinementFilterList = new ArrayList();

    public final BaseViewsRefinementModelBuilder addFilterModel(String browseId, String blockRef, String title, boolean selected, FilterCollectionModel parentCollection, int viewId) {
        Intrinsics.checkNotNullParameter(title, "title");
        this.refinementFilterList.add(new RefinementPillModel(blockRef, new FilterItemModel(browseId, title, null, selected, blockRef, Integer.valueOf(viewId), false, parentCollection, null, 68, null), false, Integer.valueOf(viewId), null, 16, null));
        return this;
    }

    public final BaseViewsRefinementModelBuilder addFilterCollectionModel(String blockRef, String title, List<FilterItemModel> filters, boolean canHaveMultipleSelections, FilterItemModel defaultSelectedFilter, boolean isSortCollection, Integer viewId) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(filters, "filters");
        FilterCollectionModel filterCollectionModel = new FilterCollectionModel(title, filters, blockRef, viewId, canHaveMultipleSelections, defaultSelectedFilter, isSortCollection);
        for (FilterItemModel filterItemModel : filters) {
            filterItemModel.setParentCollection(filterCollectionModel);
            if (filterItemModel.getSelected() && isSortCollection) {
                filterCollectionModel.setSelectedSortingFilterModel(filterItemModel);
            }
        }
        this.refinementFilterList.add(new RefinementPillModel(blockRef, filterCollectionModel, false, viewId, null, 16, null));
        return this;
    }

    public final ExposedRefinementListModel build() {
        return new ExposedRefinementListModel(null, this.refinementFilterList, null, null, 12, null);
    }
}
