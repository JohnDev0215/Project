package com.amazon.mp3.catalog.fragment.provider;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.ExposedRefinementListModel;
import com.amazon.music.views.library.models.FilterModel;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: InfoProviderLibraryImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0013\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018¢\u0006\u0004\b)\u0010*J\u0011\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0002H\u0016J\n\u0010\t\u001a\u0004\u0018\u00010\bH\u0016J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u0011\u0010\r\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0004\b\r\u0010\u0004J\n\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0016J\n\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u0012\u001a\u00020\u0006H\u0016J\b\u0010\u0013\u001a\u00020\u0006H\u0016J\n\u0010\u0014\u001a\u0004\u0018\u00010\bH\u0016R\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00188\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR$\u0010\u001d\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\"\u0010#\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(¨\u0006+"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/provider/InfoProviderLibraryImpl;", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager$InfoProvider;", "", "findPositionOfRefinementList", "()Ljava/lang/Integer;", "actionBarHeight", "", "shouldDisplayPageComponentsInTopAppBar", "", "getArtworkUrl", "", "Lcom/amazon/music/views/library/models/FilterModel;", "getRefinementFilterModels", "getFilterButtonClickDestination", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getContentMetadata", "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "getRefinementListModel", "shouldDisplayFilterAndContextButtons", "shouldDisplayTitle", "getTitle", "I", "getActionBarHeight", "()I", "Landroidx/recyclerview/widget/RecyclerView;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "getRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "titleText", "Ljava/lang/String;", "getTitleText", "()Ljava/lang/String;", "setTitleText", "(Ljava/lang/String;)V", "displayTitle", "Z", "getDisplayTitle", "()Z", "setDisplayTitle", "(Z)V", "<init>", "(ILandroidx/recyclerview/widget/RecyclerView;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class InfoProviderLibraryImpl implements BaseViewsFilterActionBarManager.InfoProvider {
    private final int actionBarHeight;
    private boolean displayTitle;
    private final RecyclerView recyclerView;
    private String titleText;

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public String getArtworkUrl() {
        return null;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public ContentMetadata getContentMetadata() {
        return null;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldDisplayFilterAndContextButtons() {
        return false;
    }

    public InfoProviderLibraryImpl(int i, RecyclerView recyclerView) {
        this.actionBarHeight = i;
        this.recyclerView = recyclerView;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean doesHaveRefinement() {
        return BaseViewsFilterActionBarManager.InfoProvider.DefaultImpls.doesHaveRefinement(this);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldDisplayContextMenuOverflowButton() {
        return BaseViewsFilterActionBarManager.InfoProvider.DefaultImpls.shouldDisplayContextMenuOverflowButton(this);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldDisplayFilterButton(RecyclerView recyclerView, int i) {
        return BaseViewsFilterActionBarManager.InfoProvider.DefaultImpls.shouldDisplayFilterButton(this, recyclerView, i);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldHideRefinementsOnLoad() {
        return BaseViewsFilterActionBarManager.InfoProvider.DefaultImpls.shouldHideRefinementsOnLoad(this);
    }

    public final int getActionBarHeight() {
        return this.actionBarHeight;
    }

    public final void setTitleText(String str) {
        this.titleText = str;
    }

    public final void setDisplayTitle(boolean z) {
        this.displayTitle = z;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldDisplayPageComponentsInTopAppBar(int actionBarHeight) {
        Integer numFindPositionOfRefinementList;
        RecyclerView recyclerView = this.recyclerView;
        RecyclerView.LayoutManager layoutManager = recyclerView == null ? null : recyclerView.getLayoutManager();
        LinearLayoutManager linearLayoutManager = layoutManager instanceof LinearLayoutManager ? (LinearLayoutManager) layoutManager : null;
        if (linearLayoutManager == null || (numFindPositionOfRefinementList = findPositionOfRefinementList()) == null) {
            return false;
        }
        return BaseViewsFilterActionBarManager.INSTANCE.isTargetViewUnderActionBarView(numFindPositionOfRefinementList.intValue(), actionBarHeight, linearLayoutManager);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public List<FilterModel> getRefinementFilterModels() {
        ExposedRefinementListModel refinementListModel = getRefinementListModel();
        List<FilterModel> filterModels = refinementListModel == null ? null : refinementListModel.getFilterModels();
        return filterModels == null ? CollectionsKt__CollectionsKt.emptyList() : filterModels;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public Integer getFilterButtonClickDestination() {
        List<Object> items;
        RecyclerView recyclerView = this.recyclerView;
        RecyclerView.Adapter adapter = recyclerView == null ? null : recyclerView.getAdapter();
        UniversalAdapter universalAdapter = adapter instanceof UniversalAdapter ? (UniversalAdapter) adapter : null;
        if (universalAdapter == null || (items = universalAdapter.getItems()) == null) {
            return null;
        }
        int i = 0;
        Iterator<Object> it = items.iterator();
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            if (it.next() instanceof ExposedRefinementListModel) {
                break;
            }
            i++;
        }
        return Integer.valueOf(i);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public ExposedRefinementListModel getRefinementListModel() {
        List<Object> items;
        Object next;
        RecyclerView recyclerView = this.recyclerView;
        RecyclerView.Adapter adapter = recyclerView == null ? null : recyclerView.getAdapter();
        UniversalAdapter universalAdapter = adapter instanceof UniversalAdapter ? (UniversalAdapter) adapter : null;
        if (universalAdapter == null || (items = universalAdapter.getItems()) == null) {
            next = null;
        } else {
            Iterator<T> it = items.iterator();
            while (it.hasNext()) {
                next = it.next();
                if (next instanceof ExposedRefinementListModel) {
                    break;
                }
            }
            next = null;
        }
        if (next instanceof ExposedRefinementListModel) {
            return (ExposedRefinementListModel) next;
        }
        return null;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    /* JADX INFO: renamed from: shouldDisplayTitle, reason: from getter */
    public boolean getDisplayTitle() {
        return this.displayTitle;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    /* JADX INFO: renamed from: getTitle, reason: from getter */
    public String getTitleText() {
        return this.titleText;
    }

    private final Integer findPositionOfRefinementList() {
        UniversalAdapter universalAdapter;
        List<Object> items;
        RecyclerView recyclerView = this.recyclerView;
        if ((recyclerView == null ? null : recyclerView.getAdapter()) instanceof UniversalAdapter) {
            RecyclerView recyclerView2 = this.recyclerView;
            RecyclerView.Adapter adapter = recyclerView2 == null ? null : recyclerView2.getAdapter();
            if (adapter == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter");
            }
            universalAdapter = (UniversalAdapter) adapter;
        } else {
            universalAdapter = null;
        }
        if (universalAdapter == null || (items = universalAdapter.getItems()) == null) {
            return null;
        }
        int i = 0;
        Iterator<Object> it = items.iterator();
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            if (it.next() instanceof ExposedRefinementListModel) {
                break;
            }
            i++;
        }
        return Integer.valueOf(i);
    }
}
