package com.amazon.mp3.detailpages.album;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory;
import com.amazon.mp3.catalog.fragment.datasource.repository.AlbumDetailDataRepository;
import com.amazon.mp3.catalog.fragment.datasource.repository.PageDataRepository;
import com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel;
import com.amazon.mp3.catalog.fragment.viewmodel.BaseViewsRefinementManager;
import com.amazon.mp3.catalog.fragment.viewmodel.ContainerRefinementDataProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.FetchType;
import com.amazon.mp3.fragment.viewmodel.OnFilterChangedListener;
import com.amazon.mp3.util.Log;
import com.amazon.music.views.library.models.ExposedRefinementListModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.PageHeaderModel;
import com.amazon.music.views.library.models.TableRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryAlbumDetailViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u001f\u001a\u00020\u001e\u0012\u0006\u0010!\u001a\u00020 ¢\u0006\u0004\b\"\u0010#J\b\u0010\u0004\u001a\u00020\u0003H\u0002J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0014J$\u0010\u000e\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0014J\u0012\u0010\u000f\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0014J\u000e\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0010J\u0006\u0010\u0013\u001a\u00020\u0003J\u000e\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0014R\u0016\u0010\u0018\u001a\u0004\u0018\u00010\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001d¨\u0006$"}, d2 = {"Lcom/amazon/mp3/detailpages/album/LibraryAlbumDetailViewModel;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseAndroidViewModel;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseViewsRefinementManager$Provider;", "", "initRefinementManager", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseViewsRefinementManager;", "getRefinementManager", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "pageModel", "handleInitPageResponse", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsDiffUtilFactory;", "diffUtilFactory", "Lcom/amazon/mp3/catalog/fragment/viewmodel/FetchType;", "fetchType", "handleUpdatedPageResponse", "postUpdatedModels", "Lcom/amazon/mp3/fragment/viewmodel/OnFilterChangedListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setDownloadChangedListener", "fetchIsOwnedState", "Landroidx/lifecycle/MutableLiveData;", "", "getOwnedStateData", "Landroid/content/Context;", "context", "Landroid/content/Context;", "refinementManager", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseViewsRefinementManager;", "isOwnedState", "Landroidx/lifecycle/MutableLiveData;", "Landroid/app/Application;", "application", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "pageDataRepo", "<init>", "(Landroid/app/Application;Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LibraryAlbumDetailViewModel extends BaseAndroidViewModel implements BaseViewsRefinementManager.Provider {
    private final Context context;
    private final MutableLiveData<Boolean> isOwnedState;
    private BaseViewsRefinementManager refinementManager;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LibraryAlbumDetailViewModel(Application application, PageDataRepository pageDataRepo) {
        super(application, pageDataRepo, false, false, 12, null);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(pageDataRepo, "pageDataRepo");
        this.context = application.getApplicationContext();
        this.isOwnedState = new MutableLiveData<>(null);
        initRefinementManager();
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseViewsRefinementManager.Provider
    public BaseViewsRefinementManager getRefinementManager() {
        return this.refinementManager;
    }

    private final void initRefinementManager() {
        BaseViewsRefinementManager baseViewsRefinementManager = new BaseViewsRefinementManager(new ContainerRefinementDataProvider());
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
    protected synchronized void handleUpdatedPageResponse(PageGridViewModel pageModel, BaseViewsDiffUtilFactory diffUtilFactory, FetchType fetchType) {
        List<BaseViewModel> models;
        PageHeaderModel pageHeaderModel;
        List<BaseViewModel> models2;
        ExposedRefinementListModel exposedRefinementListModel;
        BaseViewsRefinementManager baseViewsRefinementManager;
        Intrinsics.checkNotNullParameter(diffUtilFactory, "diffUtilFactory");
        BaseViewsRefinementManager baseViewsRefinementManager2 = this.refinementManager;
        if (baseViewsRefinementManager2 != null) {
            baseViewsRefinementManager2.updateUnfilteredData(pageModel);
        }
        ArrayList arrayList = new ArrayList();
        List<BaseViewModel> filteredDataList = null;
        if (pageModel == null || (models = pageModel.getModels()) == null) {
            pageHeaderModel = null;
        } else {
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : models) {
                if (obj instanceof PageHeaderModel) {
                    arrayList2.add(obj);
                }
            }
            pageHeaderModel = (PageHeaderModel) CollectionsKt___CollectionsKt.getOrNull(arrayList2, 0);
        }
        if (pageModel == null || (models2 = pageModel.getModels()) == null) {
            exposedRefinementListModel = null;
        } else {
            ArrayList arrayList3 = new ArrayList();
            for (Object obj2 : models2) {
                if (obj2 instanceof ExposedRefinementListModel) {
                    arrayList3.add(obj2);
                }
            }
            exposedRefinementListModel = (ExposedRefinementListModel) CollectionsKt___CollectionsKt.getOrNull(arrayList3, 0);
        }
        if (pageHeaderModel != null) {
            arrayList.add(pageHeaderModel);
        }
        if (exposedRefinementListModel != null) {
            arrayList.add(exposedRefinementListModel);
        }
        Context context = this.context;
        if (context != null && (baseViewsRefinementManager = this.refinementManager) != null) {
            filteredDataList = baseViewsRefinementManager.getFilteredDataList(context);
        }
        if (filteredDataList == null) {
            filteredDataList = CollectionsKt__CollectionsKt.emptyList();
        }
        arrayList.addAll(filteredDataList);
        compareModelsForUpdates(pageModel, arrayList, diffUtilFactory);
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel
    protected void postUpdatedModels(PageGridViewModel pageModel) {
        BaseViewsRefinementManager baseViewsRefinementManager;
        List<BaseViewModel> models;
        boolean z = false;
        if (pageModel != null && (models = pageModel.getModels()) != null) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : models) {
                if (obj instanceof TableRowItemModel) {
                    arrayList.add(obj);
                }
            }
            if (!arrayList.isEmpty()) {
                z = true;
            }
        }
        if (!z) {
            super.postUpdatedModels(pageModel);
            return;
        }
        Context context = this.context;
        if (context == null || (baseViewsRefinementManager = this.refinementManager) == null) {
            return;
        }
        BaseViewsRefinementManager.applyRefinementFilters$default(baseViewsRefinementManager, context, null, null, 6, null);
    }

    public final void setDownloadChangedListener(OnFilterChangedListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        BaseViewsRefinementManager baseViewsRefinementManager = this.refinementManager;
        if (baseViewsRefinementManager == null) {
            return;
        }
        baseViewsRefinementManager.setChangedListener(listener);
    }

    public final void fetchIsOwnedState() {
        if (getPageDataRepository() instanceof AlbumDetailDataRepository) {
            Disposable disposableSubscribe = ((AlbumDetailDataRepository) getPageDataRepository()).isItemOwned().subscribeOn(Schedulers.from(getExecutorService())).observeOn(Schedulers.io()).subscribe(new BiConsumer() { // from class: com.amazon.mp3.detailpages.album.LibraryAlbumDetailViewModel$$ExternalSyntheticLambda0
                @Override // io.reactivex.functions.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    LibraryAlbumDetailViewModel.m823fetchIsOwnedState$lambda6(this.f$0, (Boolean) obj, (Throwable) obj2);
                }
            });
            Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "pageDataRepository.isIte…  }\n                    }");
            getCompositeDisposable().add(disposableSubscribe);
            return;
        }
        this.isOwnedState.postValue(Boolean.FALSE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchIsOwnedState$lambda-6, reason: not valid java name */
    public static final void m823fetchIsOwnedState$lambda6(LibraryAlbumDetailViewModel this$0, Boolean bool, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (th == null) {
            this$0.isOwnedState.postValue(bool);
        } else {
            Log.error(AlbumDetailFragment.class.getSimpleName(), Intrinsics.stringPlus("Error determining Album ownership ", th.getMessage()));
            this$0.isOwnedState.postValue(Boolean.FALSE);
        }
    }

    public final MutableLiveData<Boolean> getOwnedStateData() {
        return this.isOwnedState;
    }
}
