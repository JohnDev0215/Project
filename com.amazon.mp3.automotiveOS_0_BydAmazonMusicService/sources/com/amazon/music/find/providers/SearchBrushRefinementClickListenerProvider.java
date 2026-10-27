package com.amazon.music.find.providers;

import android.view.View;
import com.amazon.music.find.viewmodels.SearchViewModel;
import com.amazon.music.views.library.models.FilterItemModel;
import com.amazon.music.views.library.models.FilterModel;
import com.amazon.music.views.library.models.RefinementPillModel;
import com.amazon.music.views.library.providers.RefinementClickListenerProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchBrushRefinementClickListenerProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/amazon/music/find/providers/SearchBrushRefinementClickListenerProvider;", "Lcom/amazon/music/views/library/providers/RefinementClickListenerProvider;", "searchViewModel", "Lcom/amazon/music/find/viewmodels/SearchViewModel;", "(Lcom/amazon/music/find/viewmodels/SearchViewModel;)V", "getSearchViewModel", "()Lcom/amazon/music/find/viewmodels/SearchViewModel;", "onClick", "", "model", "Lcom/amazon/music/views/library/models/RefinementPillModel;", "view", "Landroid/view/View;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchBrushRefinementClickListenerProvider implements RefinementClickListenerProvider {
    private final SearchViewModel searchViewModel;

    public SearchBrushRefinementClickListenerProvider(SearchViewModel searchViewModel) {
        Intrinsics.checkNotNullParameter(searchViewModel, "searchViewModel");
        this.searchViewModel = searchViewModel;
    }

    public final SearchViewModel getSearchViewModel() {
        return this.searchViewModel;
    }

    @Override // com.amazon.music.views.library.providers.RefinementClickListenerProvider
    public void onClick(RefinementPillModel model, View view) {
        FilterItemModel itemModel;
        String browseId;
        Intrinsics.checkNotNullParameter(model, "model");
        FilterModel content = model.getContent();
        if (content == null || content.isCollection() || (itemModel = content.getItemModel()) == null || (browseId = itemModel.getBrowseId()) == null) {
            return;
        }
        getSearchViewModel().toggleFilterSelection(browseId);
    }
}
