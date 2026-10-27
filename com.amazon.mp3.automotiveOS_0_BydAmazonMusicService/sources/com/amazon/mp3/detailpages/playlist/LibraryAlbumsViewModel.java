package com.amazon.mp3.detailpages.playlist;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository;
import com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel;
import com.amazon.mp3.detailpages.refinement.RefinementsMapping;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.refinement.datasource.DatabaseSource;
import com.amazon.mp3.library.refinement.filter.Filter;
import com.amazon.mp3.library.refinement.sort.Sort;
import com.amazon.mp3.playback.state.PageStateManager;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.amazon.music.views.library.models.PageGridViewModel;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryAlbumsViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000[\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\r\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u0001B9\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u0007\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\u0002\u0010\u000bJ\b\u0010\u0019\u001a\u00020\u001aH\u0014J\u0010\u0010\u001b\u001a\u00020\u001a2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dJ\u0006\u0010\u001e\u001a\u00020\u001aR\u0010\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR6\u0010\u000f\u001a$\u0012\u0004\u0012\u00020\n\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u0002\u0012\u0002\b\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u00110\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R2\u0010\u0016\u001a \u0012\u0004\u0012\u00020\n\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u00170\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0013¨\u0006\u001f"}, d2 = {"Lcom/amazon/mp3/detailpages/playlist/LibraryAlbumsViewModel;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinableViewModel;", "Lcom/amazon/mp3/library/item/Album;", "Lcom/amazon/mp3/library/refinement/datasource/DatabaseSource;", "application", "Landroid/app/Application;", "pageDataRepo", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/RefinablePageDataRepository;", "defaultFilters", "", "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "(Landroid/app/Application;Lcom/amazon/mp3/catalog/fragment/datasource/repository/RefinablePageDataRepository;Ljava/util/Set;)V", "downloadStateModelProviderListener", "com/amazon/mp3/detailpages/playlist/LibraryAlbumsViewModel$downloadStateModelProviderListener$1", "Lcom/amazon/mp3/detailpages/playlist/LibraryAlbumsViewModel$downloadStateModelProviderListener$1;", "filterMapping", "", "Lcom/amazon/mp3/library/refinement/filter/Filter;", "getFilterMapping", "()Ljava/util/Map;", "groupDownloadStateModelProvider", "Lcom/amazon/mp3/catalog/fragment/viewmodel/GroupDownloadStateModelProvider;", "sortMapping", "Lcom/amazon/mp3/library/refinement/sort/Sort;", "getSortMapping", "onCleared", "", "registerDownloadStateListener", "uri", "Landroid/net/Uri;", "unregisterDownloadStateListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LibraryAlbumsViewModel extends RefinableViewModel<Album, DatabaseSource<Album>> {
    private final LibraryAlbumsViewModel$downloadStateModelProviderListener$1 downloadStateModelProviderListener;
    private final GroupDownloadStateModelProvider groupDownloadStateModelProvider;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazon.mp3.detailpages.playlist.LibraryAlbumsViewModel$downloadStateModelProviderListener$1] */
    public LibraryAlbumsViewModel(Application application, RefinablePageDataRepository<Album, DatabaseSource<Album>> pageDataRepo, Set<? extends RefinementFilterType> set) {
        super(application, pageDataRepo, false, 4, null);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(pageDataRepo, "pageDataRepo");
        enableFilters(set);
        Context applicationContext = application.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "application.applicationContext");
        this.groupDownloadStateModelProvider = new GroupDownloadStateModelProvider(applicationContext);
        this.downloadStateModelProviderListener = new GroupDownloadStateModelProvider.BauhausDefaultListener() { // from class: com.amazon.mp3.detailpages.playlist.LibraryAlbumsViewModel$downloadStateModelProviderListener$1
            {
                super(false, 1, null);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider.BauhausDefaultListener
            /* JADX INFO: renamed from: getPageModel */
            public PageGridViewModel get$pageGridViewModel() {
                return (PageGridViewModel) this.this$0.getPageData().getValue();
            }

            @Override // com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider.BauhausDefaultListener
            public PageStateManager getPageStateManager() {
                return this.this$0.getPageStateManager();
            }
        };
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel
    public Map<RefinementFilterType, Filter<Album, ?, DatabaseSource<Album>>> getFilterMapping() {
        return RefinementsMapping.INSTANCE.getAlbumsFilterMapping();
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel
    public Map<RefinementFilterType, Sort<Album, DatabaseSource<Album>>> getSortMapping() {
        if (AmazonApplication.getCapabilities().isTrueLikesAndFollowsEnabled()) {
            return RefinementsMapping.INSTANCE.getLikedAlbumsSortMapping();
        }
        return RefinementsMapping.INSTANCE.getAlbumsSortMapping();
    }

    public final void registerDownloadStateListener(Uri uri) {
        this.groupDownloadStateModelProvider.registerDownloadStateModelListener(this.downloadStateModelProviderListener, uri);
    }

    public final void unregisterDownloadStateListener() {
        this.groupDownloadStateModelProvider.unregisterDownloadStateModelListener();
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel, androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        this.groupDownloadStateModelProvider.unregisterDownloadStateModelListener();
    }
}
