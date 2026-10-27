package com.amazon.music.views.library.providers;

import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.FilterModel;
import kotlin.Metadata;

/* JADX INFO: compiled from: RefinementCallback.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lcom/amazon/music/views/library/providers/RefinementCallback;", "Lcom/amazon/music/views/library/providers/RefinementClickListenerProvider;", "onChildFilterClick", "", "filterModel", "Lcom/amazon/music/views/library/models/FilterModel;", "onFiltersReset", "model", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface RefinementCallback extends RefinementClickListenerProvider {
    void onChildFilterClick(FilterModel filterModel);

    void onFiltersReset();

    void onFiltersReset(FilterCollectionModel model);
}
