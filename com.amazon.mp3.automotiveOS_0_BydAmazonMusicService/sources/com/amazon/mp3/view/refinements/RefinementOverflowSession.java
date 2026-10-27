package com.amazon.mp3.view.refinements;

import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.FilterItemModel;
import kotlin.Metadata;

/* JADX INFO: compiled from: RefinementOverflowSession.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0003H&J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/view/refinements/RefinementOverflowSession;", "", "commit", "", "getTempCollection", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "isDataChanged", "", "reset", "updateFilterCollectionModel", "clickedFilterItemModel", "Lcom/amazon/music/views/library/models/FilterItemModel;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface RefinementOverflowSession {
    void commit();

    FilterCollectionModel getTempCollection();

    boolean isDataChanged();

    void reset();

    void updateFilterCollectionModel(FilterItemModel clickedFilterItemModel);
}
