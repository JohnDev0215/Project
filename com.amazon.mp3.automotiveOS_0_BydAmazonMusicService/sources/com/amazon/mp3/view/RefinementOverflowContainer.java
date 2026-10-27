package com.amazon.mp3.view;

import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.FilterItemModel;
import com.amazon.music.views.library.models.FilterModel;
import com.amazon.music.views.library.providers.RefinementCallback;
import com.amazon.music.views.library.providers.SimpleClickListenerProvider;
import com.amazon.music.views.library.providers.StateChangeProvider;
import com.amazon.music.views.library.views.RefinementOverflowView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RefinementOverflowContainer.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\bf\u0018\u00002\u00020\u00012\u00020\u0002J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002R\u0014\u0010\n\u001a\u00020\u00078&X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\u000e\u001a\u00020\u000b8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001e\u0010\u0013\u001a\u0004\u0018\u00010\u00038&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0016\u001a\u0004\u0018\u00010\u00038&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012¨\u0006\u0017"}, d2 = {"Lcom/amazon/mp3/view/RefinementOverflowContainer;", "Lcom/amazon/music/views/library/providers/SimpleClickListenerProvider;", "Lcom/amazon/music/views/library/providers/StateChangeProvider;", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "model", "", "showFilterDetail", "Lcom/amazon/music/views/library/providers/RefinementCallback;", "getRefinementCallback", "()Lcom/amazon/music/views/library/providers/RefinementCallback;", "refinementCallback", "Lcom/amazon/music/views/library/views/RefinementOverflowView;", "getRefinementOverflowView", "()Lcom/amazon/music/views/library/views/RefinementOverflowView;", "refinementOverflowView", "getCurrentFilterCollection", "()Lcom/amazon/music/views/library/models/FilterCollectionModel;", "setCurrentFilterCollection", "(Lcom/amazon/music/views/library/models/FilterCollectionModel;)V", "currentFilterCollection", "getSingleCollectionModel", "setSingleCollectionModel", "singleCollectionModel", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public interface RefinementOverflowContainer extends SimpleClickListenerProvider, StateChangeProvider {
    FilterCollectionModel getCurrentFilterCollection();

    RefinementCallback getRefinementCallback();

    RefinementOverflowView getRefinementOverflowView();

    FilterCollectionModel getSingleCollectionModel();

    void setCurrentFilterCollection(FilterCollectionModel filterCollectionModel);

    void setSingleCollectionModel(FilterCollectionModel filterCollectionModel);

    /* JADX INFO: compiled from: RefinementOverflowContainer.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static void onClick(RefinementOverflowContainer refinementOverflowContainer, Object obj) {
            Intrinsics.checkNotNullParameter(refinementOverflowContainer, "this");
            if (obj instanceof FilterCollectionModel) {
                showFilterDetail(refinementOverflowContainer, (FilterCollectionModel) obj);
            } else if (obj instanceof FilterItemModel) {
                refinementOverflowContainer.getRefinementCallback().onChildFilterClick((FilterModel) obj);
            }
        }

        public static void onResetState(RefinementOverflowContainer refinementOverflowContainer, String str) {
            Unit unit;
            Intrinsics.checkNotNullParameter(refinementOverflowContainer, "this");
            FilterCollectionModel currentFilterCollection = refinementOverflowContainer.getCurrentFilterCollection();
            if (currentFilterCollection == null) {
                currentFilterCollection = refinementOverflowContainer.getSingleCollectionModel();
            }
            if (currentFilterCollection == null) {
                unit = null;
            } else {
                refinementOverflowContainer.getRefinementCallback().onFiltersReset(currentFilterCollection);
                unit = Unit.INSTANCE;
            }
            if (unit == null) {
                refinementOverflowContainer.getRefinementCallback().onFiltersReset();
            }
        }

        private static void showFilterDetail(RefinementOverflowContainer refinementOverflowContainer, FilterCollectionModel filterCollectionModel) {
            refinementOverflowContainer.getRefinementOverflowView().display(filterCollectionModel.getFilters(), filterCollectionModel.getTitle());
            refinementOverflowContainer.setCurrentFilterCollection(filterCollectionModel);
        }

        public static void setCatalogModel(RefinementOverflowContainer refinementOverflowContainer, FilterCollectionModel model) {
            Intrinsics.checkNotNullParameter(refinementOverflowContainer, "this");
            Intrinsics.checkNotNullParameter(model, "model");
            refinementOverflowContainer.setSingleCollectionModel(model);
        }
    }
}
