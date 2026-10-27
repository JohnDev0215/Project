package com.amazon.mp3.catalog.fragment.datasource.dao;

import com.amazon.mp3.catalog.fragment.viewmodel.FetchType;
import com.amazon.music.views.library.models.PageGridViewModel;
import io.reactivex.Single;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PageViewModelDao.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J2\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH&J\b\u0010\f\u001a\u00020\u0006H\u0016¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/dao/PageViewModelDao;", "", "fetchViewModelItems", "Lio/reactivex/Single;", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "pageIndex", "", "pageSize", "fetchType", "Lcom/amazon/mp3/catalog/fragment/viewmodel/FetchType;", "nextPageToken", "", "getDefaultPageSize", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface PageViewModelDao {
    Single<PageGridViewModel> fetchViewModelItems(int pageIndex, int pageSize, FetchType fetchType, String nextPageToken);

    int getDefaultPageSize();

    /* JADX INFO: compiled from: PageViewModelDao.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static int getDefaultPageSize(PageViewModelDao pageViewModelDao) {
            Intrinsics.checkNotNullParameter(pageViewModelDao, "this");
            return 20;
        }

        public static /* synthetic */ Single fetchViewModelItems$default(PageViewModelDao pageViewModelDao, int i, int i2, FetchType fetchType, String str, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fetchViewModelItems");
            }
            if ((i3 & 8) != 0) {
                str = null;
            }
            return pageViewModelDao.fetchViewModelItems(i, i2, fetchType, str);
        }
    }
}
