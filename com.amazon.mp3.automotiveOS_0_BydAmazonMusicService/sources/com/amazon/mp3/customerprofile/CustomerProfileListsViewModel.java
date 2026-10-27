package com.amazon.mp3.customerprofile;

import android.app.Application;
import com.amazon.mp3.catalog.fragment.datasource.repository.PageDataRepository;
import com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: CustomerProfileListsViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\u0017\u0010\u000e\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002¢\u0006\u0002\u0010\u000f¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/customerprofile/CustomerProfileListsViewModel;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseAndroidViewModel;", "application", "Landroid/app/Application;", "pageDataRepo", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "usePagination", "", "(Landroid/app/Application;Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;Z)V", "fetchMorePage", "", "pageIndex", "", "pageSize", "isFirstPage", "(Ljava/lang/Integer;)Z", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CustomerProfileListsViewModel extends BaseAndroidViewModel {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomerProfileListsViewModel(Application application, PageDataRepository pageDataRepo, boolean z) {
        super(application, pageDataRepo, z, false, 8, null);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(pageDataRepo, "pageDataRepo");
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel, com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider
    public void fetchMorePage(int pageIndex, int pageSize) {
        PageGridViewModel value = getPageData().getValue();
        if (value != null) {
            value.getPageIndex();
        }
        if (!StringUtils.isEmpty(value == null ? null : value.getNextPageToken()) || isFirstPage(Integer.valueOf(pageIndex))) {
            super.fetchMorePage(pageIndex, pageSize);
        }
    }

    private final boolean isFirstPage(Integer pageIndex) {
        return pageIndex == null || pageIndex.intValue() == 0;
    }
}
