package com.amazon.mp3.catalog.fragment.datasource.repository;

import com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao;
import com.amazon.mp3.catalog.fragment.viewmodel.FetchType;
import com.amazon.music.views.library.models.PageGridViewModel;
import io.reactivex.Single;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PageDataRepository.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u0013\u0010\u0012J2\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0016R\"\u0010\r\u001a\u00020\f8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "", "", "pageIndex", "pageSize", "Lcom/amazon/mp3/catalog/fragment/viewmodel/FetchType;", "fetchType", "", "nextPageToken", "Lio/reactivex/Single;", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "fetchPageViewModels", "Lcom/amazon/mp3/catalog/fragment/datasource/dao/PageViewModelDao;", "modelDao", "Lcom/amazon/mp3/catalog/fragment/datasource/dao/PageViewModelDao;", "getModelDao", "()Lcom/amazon/mp3/catalog/fragment/datasource/dao/PageViewModelDao;", "setModelDao", "(Lcom/amazon/mp3/catalog/fragment/datasource/dao/PageViewModelDao;)V", "<init>", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class PageDataRepository {
    private PageViewModelDao modelDao;

    public PageDataRepository(PageViewModelDao modelDao) {
        Intrinsics.checkNotNullParameter(modelDao, "modelDao");
        this.modelDao = modelDao;
    }

    protected final PageViewModelDao getModelDao() {
        return this.modelDao;
    }

    public static /* synthetic */ Single fetchPageViewModels$default(PageDataRepository pageDataRepository, int i, int i2, FetchType fetchType, String str, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fetchPageViewModels");
        }
        if ((i3 & 8) != 0) {
            str = null;
        }
        return pageDataRepository.fetchPageViewModels(i, i2, fetchType, str);
    }

    public Single<PageGridViewModel> fetchPageViewModels(int pageIndex, int pageSize, FetchType fetchType, String nextPageToken) {
        Intrinsics.checkNotNullParameter(fetchType, "fetchType");
        return this.modelDao.fetchViewModelItems(pageIndex, pageSize, fetchType, nextPageToken);
    }
}
