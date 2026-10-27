package com.amazon.mp3.detailpages.artist;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory;
import com.amazon.mp3.catalog.fragment.datasource.repository.PageDataRepository;
import com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel;
import com.amazon.mp3.catalog.fragment.viewmodel.BaseViewsRefinementManager;
import com.amazon.mp3.catalog.fragment.viewmodel.FetchType;
import com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.VisualRowRefinementDataProvider;
import com.amazon.mp3.detailpages.actions.DefaultTrackInformationFetcher;
import com.amazon.mp3.fragment.viewmodel.OnFilterChangedListener;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.mp3.playback.state.PageStateManager;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.SupervisorKt;

/* JADX INFO: compiled from: LibraryArtistDetailViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000{\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\r\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\n\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u0016J\u0012\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J*\u0010\u001a\u001a\u00020\u00172\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020 J$\u0010!\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0014J\b\u0010&\u001a\u00020\u0017H\u0002J\b\u0010'\u001a\u00020\u0017H\u0014J\u0012\u0010(\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\u0010\u0010)\u001a\u00020\u00172\b\u0010*\u001a\u0004\u0018\u00010\u001eJ\u000e\u0010+\u001a\u00020\u00172\u0006\u0010,\u001a\u00020-J\u0006\u0010.\u001a\u00020\u0017R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/amazon/mp3/detailpages/artist/LibraryArtistDetailViewModel;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseAndroidViewModel;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseViewsRefinementManager$Provider;", "application", "Landroid/app/Application;", "pageDataRepo", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "(Landroid/app/Application;Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;)V", "context", "Landroid/content/Context;", "downloadStateModelProvider", "Lcom/amazon/mp3/catalog/fragment/viewmodel/GroupDownloadStateModelProvider;", "downloadStateModelProviderListener", "com/amazon/mp3/detailpages/artist/LibraryArtistDetailViewModel$downloadStateModelProviderListener$1", "Lcom/amazon/mp3/detailpages/artist/LibraryArtistDetailViewModel$downloadStateModelProviderListener$1;", "playbackJob", "Lkotlinx/coroutines/CompletableJob;", "playbackScope", "Lkotlinx/coroutines/CoroutineScope;", "refinementManager", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseViewsRefinementManager;", "getRefinementManager", "handleInitPageResponse", "", "pageModel", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "handleTrackItemClickPlayback", "contentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "contentUri", "Landroid/net/Uri;", "trackInformationFetcher", "Lcom/amazon/mp3/detailpages/actions/DefaultTrackInformationFetcher;", "handleUpdatedPageResponse", "diffUtilFactory", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsDiffUtilFactory;", "fetchType", "Lcom/amazon/mp3/catalog/fragment/viewmodel/FetchType;", "initRefinementManager", "onCleared", "postUpdatedModels", "registerDownloadStateListener", "uri", "setDownloadChangedListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/amazon/mp3/fragment/viewmodel/OnFilterChangedListener;", "unregisterDownloadStateListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LibraryArtistDetailViewModel extends BaseAndroidViewModel implements BaseViewsRefinementManager.Provider {
    private final Context context;
    private final GroupDownloadStateModelProvider downloadStateModelProvider;
    private final LibraryArtistDetailViewModel$downloadStateModelProviderListener$1 downloadStateModelProviderListener;
    private final CompletableJob playbackJob;
    private final CoroutineScope playbackScope;
    private BaseViewsRefinementManager refinementManager;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r10v6, types: [com.amazon.mp3.detailpages.artist.LibraryArtistDetailViewModel$downloadStateModelProviderListener$1] */
    public LibraryArtistDetailViewModel(Application application, PageDataRepository pageDataRepo) {
        super(application, pageDataRepo, false, false, 12, null);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(pageDataRepo, "pageDataRepo");
        this.context = application.getApplicationContext();
        CompletableJob completableJobSupervisorJob$default = SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null);
        this.playbackJob = completableJobSupervisorJob$default;
        this.playbackScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault().plus(completableJobSupervisorJob$default));
        this.downloadStateModelProviderListener = new GroupDownloadStateModelProvider.BauhausDefaultListener() { // from class: com.amazon.mp3.detailpages.artist.LibraryArtistDetailViewModel$downloadStateModelProviderListener$1
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
        initRefinementManager();
        this.downloadStateModelProvider = new GroupDownloadStateModelProvider(application);
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseViewsRefinementManager.Provider
    /* JADX INFO: renamed from: getRefinementManager, reason: from getter */
    public BaseViewsRefinementManager getRefinementsManager() {
        return this.refinementManager;
    }

    private final void initRefinementManager() {
        BaseViewsRefinementManager baseViewsRefinementManager = new BaseViewsRefinementManager(new VisualRowRefinementDataProvider());
        this.refinementManager = baseViewsRefinementManager;
        baseViewsRefinementManager.observePageData(getPageData());
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel
    protected void handleInitPageResponse(PageGridViewModel pageModel) {
        super.handleInitPageResponse(pageModel);
        BaseViewsRefinementManager baseViewsRefinementManager = this.refinementManager;
        if (baseViewsRefinementManager == null) {
            return;
        }
        baseViewsRefinementManager.updateUnfilteredData(pageModel);
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel
    protected void handleUpdatedPageResponse(PageGridViewModel pageModel, BaseViewsDiffUtilFactory diffUtilFactory, FetchType fetchType) {
        Intrinsics.checkNotNullParameter(diffUtilFactory, "diffUtilFactory");
        BaseViewsRefinementManager baseViewsRefinementManager = this.refinementManager;
        if (baseViewsRefinementManager != null) {
            baseViewsRefinementManager.updateUnfilteredData(pageModel);
        }
        super.handleUpdatedPageResponse(pageModel, diffUtilFactory, fetchType);
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel
    protected void postUpdatedModels(PageGridViewModel pageModel) {
        BaseViewsRefinementManager baseViewsRefinementManager;
        Context context = this.context;
        if (context == null || (baseViewsRefinementManager = this.refinementManager) == null) {
            return;
        }
        BaseViewsRefinementManager.applyRefinementFilters$default(baseViewsRefinementManager, context, null, null, 6, null);
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel, androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        unregisterDownloadStateListener();
    }

    public final void setDownloadChangedListener(OnFilterChangedListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        BaseViewsRefinementManager baseViewsRefinementManager = this.refinementManager;
        if (baseViewsRefinementManager == null) {
            return;
        }
        baseViewsRefinementManager.setChangedListener(listener);
    }

    public final void registerDownloadStateListener(Uri uri) {
        this.downloadStateModelProvider.registerDownloadStateModelListener(this.downloadStateModelProviderListener, uri);
    }

    public final void unregisterDownloadStateListener() {
        this.downloadStateModelProvider.unregisterDownloadStateModelListener();
    }

    public final void handleTrackItemClickPlayback(Context context, ContentMetadata contentMetadata, Uri contentUri, DefaultTrackInformationFetcher trackInformationFetcher) {
        Uri uriAppendTracksIfNeeded;
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        Intrinsics.checkNotNullParameter(trackInformationFetcher, "trackInformationFetcher");
        if (context == null || (uriAppendTracksIfNeeded = ContentPlaybackUtils.INSTANCE.appendTracksIfNeeded(contentUri)) == null) {
            return;
        }
        JobKt__JobKt.cancelChildren$default(this.playbackScope.getCoroutineContext(), (CancellationException) null, 1, (Object) null);
        BuildersKt__Builders_commonKt.launch$default(this.playbackScope, null, null, new LibraryArtistDetailViewModel$handleTrackItemClickPlayback$1$1$1(trackInformationFetcher, context, contentMetadata, uriAppendTracksIfNeeded, null), 3, null);
    }
}
