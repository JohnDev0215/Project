package com.amazon.mp3.view.refinements;

import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.FilterItemModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SingleSelectRefinementOverflowSession.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0003H\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\u0007H\u0016J\u0012\u0010\f\u001a\u00020\u00072\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/view/refinements/SingleSelectRefinementOverflowSession;", "Lcom/amazon/mp3/view/refinements/RefinementOverflowSession;", "originalCollection", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "(Lcom/amazon/music/views/library/models/FilterCollectionModel;)V", "tempCollection", "commit", "", "getTempCollection", "isDataChanged", "", "reset", "updateFilterCollectionModel", "clickedFilterItemModel", "Lcom/amazon/music/views/library/models/FilterItemModel;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SingleSelectRefinementOverflowSession implements RefinementOverflowSession {
    private final FilterCollectionModel originalCollection;
    private FilterCollectionModel tempCollection;

    public SingleSelectRefinementOverflowSession(FilterCollectionModel originalCollection) {
        Intrinsics.checkNotNullParameter(originalCollection, "originalCollection");
        this.originalCollection = originalCollection;
        this.tempCollection = originalCollection.deepCopy();
    }

    @Override // com.amazon.mp3.view.refinements.RefinementOverflowSession
    public void updateFilterCollectionModel(FilterItemModel clickedFilterItemModel) {
        FilterCollectionModel parentCollection;
        if (clickedFilterItemModel == null || (parentCollection = clickedFilterItemModel.getParentCollection()) == null) {
            return;
        }
        for (FilterItemModel filterItemModel : parentCollection.getFilters()) {
            if (!Intrinsics.areEqual(filterItemModel, clickedFilterItemModel) && filterItemModel != null) {
                filterItemModel.setSelected(false);
            }
        }
        if (clickedFilterItemModel.getSelected()) {
            parentCollection.setSelectedSortingFilterModel(clickedFilterItemModel);
        } else {
            parentCollection.setSelectedSortingFilterModel(null);
        }
    }

    @Override // com.amazon.mp3.view.refinements.RefinementOverflowSession
    public boolean isDataChanged() {
        FilterItemModel selectedSortingFilterModel = this.originalCollection.getSelectedSortingFilterModel();
        String title = selectedSortingFilterModel == null ? null : selectedSortingFilterModel.getTitle();
        return !Intrinsics.areEqual(title, this.tempCollection.getSelectedSortingFilterModel() != null ? r2.getTitle() : null);
    }

    @Override // com.amazon.mp3.view.refinements.RefinementOverflowSession
    public FilterCollectionModel getTempCollection() {
        return this.tempCollection;
    }

    @Override // com.amazon.mp3.view.refinements.RefinementOverflowSession
    public void commit() {
        List<FilterItemModel> filters = this.originalCollection.getFilters();
        List<FilterItemModel> filters2 = this.tempCollection.getFilters();
        if (filters.size() == filters2.size()) {
            int i = 0;
            int size = filters.size();
            while (i < size) {
                int i2 = i + 1;
                FilterItemModel filterItemModel = filters2.get(i);
                if (filterItemModel != null) {
                    boolean selected = filterItemModel.getSelected();
                    FilterItemModel filterItemModel2 = filters.get(i);
                    if (filterItemModel2 != null) {
                        filterItemModel2.setSelected(selected);
                    }
                }
                i = i2;
            }
            this.originalCollection.setSelectedSortingFilterModel(this.tempCollection.getSelectedSortingFilterModel());
        }
    }

    @Override // com.amazon.mp3.view.refinements.RefinementOverflowSession
    public void reset() {
        List<FilterItemModel> filters = this.tempCollection.getFilters();
        if (filters != null) {
            for (FilterItemModel filterItemModel : filters) {
                if (filterItemModel != null) {
                    filterItemModel.setSelected(filterItemModel.getIsDefaultFilter());
                }
            }
        }
        this.tempCollection.setSelectedSortingFilterModel(this.originalCollection.getDefaultSelectedFilter());
    }
}
