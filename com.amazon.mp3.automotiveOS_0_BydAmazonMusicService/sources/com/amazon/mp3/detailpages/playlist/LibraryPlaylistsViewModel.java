package com.amazon.mp3.detailpages.playlist;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository;
import com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel;
import com.amazon.mp3.detailpages.refinement.RefinementsMapping;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.PlaylistRepositoryEventsKt;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.RepositoryEventType;
import com.amazon.mp3.library.refinement.datasource.DatabaseSource;
import com.amazon.mp3.library.refinement.filter.Filter;
import com.amazon.mp3.library.refinement.sort.Sort;
import com.amazon.mp3.playback.state.PageStateManager;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.amazon.music.views.library.models.PageGridViewModel;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryPlaylistsViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000g\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\r\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u0001B9\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u0007\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\u0002\u0010\u000bJ\b\u0010\u001e\u001a\u00020\u001fH\u0014J\u0010\u0010 \u001a\u00020\u001f2\b\u0010!\u001a\u0004\u0018\u00010\"J\u0006\u0010#\u001a\u00020\u001fR\u0010\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR6\u0010\u000f\u001a$\u0012\u0004\u0012\u00020\n\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u0002\u0012\u0002\b\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u00110\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR2\u0010\u001b\u001a \u0012\u0004\u0012\u00020\n\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u001c0\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0013¨\u0006$"}, d2 = {"Lcom/amazon/mp3/detailpages/playlist/LibraryPlaylistsViewModel;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinableViewModel;", "Lcom/amazon/mp3/library/item/Playlist;", "Lcom/amazon/mp3/library/refinement/datasource/DatabaseSource;", "application", "Landroid/app/Application;", "pageDataRepo", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/RefinablePageDataRepository;", "defaultFilters", "", "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "(Landroid/app/Application;Lcom/amazon/mp3/catalog/fragment/datasource/repository/RefinablePageDataRepository;Ljava/util/Set;)V", "downloadStateModelProviderListener", "com/amazon/mp3/detailpages/playlist/LibraryPlaylistsViewModel$downloadStateModelProviderListener$1", "Lcom/amazon/mp3/detailpages/playlist/LibraryPlaylistsViewModel$downloadStateModelProviderListener$1;", "filterMapping", "", "Lcom/amazon/mp3/library/refinement/filter/Filter;", "getFilterMapping", "()Ljava/util/Map;", "groupDownloadStateModelProvider", "Lcom/amazon/mp3/catalog/fragment/viewmodel/GroupDownloadStateModelProvider;", "repositoryUpdatesEventTypeAllowList", "", "Lcom/amazon/mp3/library/provider/source/cirrus/dbutils/RepositoryEventType;", "getRepositoryUpdatesEventTypeAllowList$DigitalMusicAndroid3P_automotiveProdRelease", "()Ljava/util/List;", "sortMapping", "Lcom/amazon/mp3/library/refinement/sort/Sort;", "getSortMapping", "onCleared", "", "registerDownloadStateListener", "uri", "Landroid/net/Uri;", "unregisterDownloadStateListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LibraryPlaylistsViewModel extends RefinableViewModel<Playlist, DatabaseSource<Playlist>> {
    private final LibraryPlaylistsViewModel$downloadStateModelProviderListener$1 downloadStateModelProviderListener;
    private final GroupDownloadStateModelProvider groupDownloadStateModelProvider;
    private final List<RepositoryEventType> repositoryUpdatesEventTypeAllowList;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazon.mp3.detailpages.playlist.LibraryPlaylistsViewModel$downloadStateModelProviderListener$1] */
    public LibraryPlaylistsViewModel(Application application, RefinablePageDataRepository<Playlist, DatabaseSource<Playlist>> pageDataRepo, Set<? extends RefinementFilterType> set) {
        super(application, pageDataRepo, false, 4, null);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(pageDataRepo, "pageDataRepo");
        enableFilters(set);
        this.repositoryUpdatesEventTypeAllowList = PlaylistRepositoryEventsKt.getLibraryPlaylistUpdateEventTypes();
        Context applicationContext = application.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "application.applicationContext");
        this.groupDownloadStateModelProvider = new GroupDownloadStateModelProvider(applicationContext);
        this.downloadStateModelProviderListener = new GroupDownloadStateModelProvider.BauhausDefaultListener() { // from class: com.amazon.mp3.detailpages.playlist.LibraryPlaylistsViewModel$downloadStateModelProviderListener$1
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
    public Map<RefinementFilterType, Filter<Playlist, ?, DatabaseSource<Playlist>>> getFilterMapping() {
        return RefinementsMapping.INSTANCE.getPlaylistsFilterMapping();
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel
    public Map<RefinementFilterType, Sort<Playlist, DatabaseSource<Playlist>>> getSortMapping() {
        return RefinementsMapping.INSTANCE.getPlaylistsSortMapping();
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel
    public List<RepositoryEventType> getRepositoryUpdatesEventTypeAllowList$DigitalMusicAndroid3P_automotiveProdRelease() {
        return this.repositoryUpdatesEventTypeAllowList;
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
