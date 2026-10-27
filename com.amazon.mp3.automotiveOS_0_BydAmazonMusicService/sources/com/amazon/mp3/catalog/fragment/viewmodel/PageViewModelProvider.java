package com.amazon.mp3.catalog.fragment.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory;
import com.amazon.mp3.playback.state.PageStateManager;
import com.amazon.music.views.library.models.ErrorModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import kotlin.Metadata;

/* JADX INFO: compiled from: PageViewModelProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u00012\u00020\u0002J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H&J\b\u0010\t\u001a\u00020\bH&J\b\u0010\u000b\u001a\u00020\nH&J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH&J\u0018\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH&J$\u0010\u0015\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00112\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013H&J\b\u0010\u0017\u001a\u00020\u0016H&¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelProvider;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/NetworkStateProvider;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/LibraryStateObserver;", "Landroidx/lifecycle/MutableLiveData;", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "getPageModelLiveData", "Lcom/amazon/music/views/library/models/ErrorModel;", "getErrorLiveData", "Lcom/amazon/mp3/playback/state/PageStateManager;", "getPageStateManager", "", "refresh", "", "pageSize", "fetchInitPage", "pageIndex", "fetchMorePage", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsDiffUtilFactory;", "diffUtilFactory", "Lcom/amazon/mp3/catalog/fragment/viewmodel/FetchType;", "fetchType", "fetchUpdates", "", "hasPageData", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public interface PageViewModelProvider extends NetworkStateProvider, LibraryStateObserver {
    void fetchInitPage(int pageSize);

    void fetchMorePage(int pageIndex, int pageSize);

    void fetchUpdates(int pageSize, BaseViewsDiffUtilFactory diffUtilFactory, FetchType fetchType);

    MutableLiveData<ErrorModel> getErrorLiveData();

    MutableLiveData<PageGridViewModel> getPageModelLiveData();

    PageStateManager getPageStateManager();

    boolean hasPageData();

    void refresh();

    /* JADX INFO: compiled from: PageViewModelProvider.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void fetchUpdates$default(PageViewModelProvider pageViewModelProvider, int i, BaseViewsDiffUtilFactory baseViewsDiffUtilFactory, FetchType fetchType, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fetchUpdates");
            }
            if ((i2 & 4) != 0) {
                fetchType = null;
            }
            pageViewModelProvider.fetchUpdates(i, baseViewsDiffUtilFactory, fetchType);
        }
    }
}
