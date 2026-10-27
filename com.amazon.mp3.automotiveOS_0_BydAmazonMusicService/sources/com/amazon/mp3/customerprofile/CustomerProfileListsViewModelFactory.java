package com.amazon.mp3.customerprofile;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import com.amazon.mp3.catalog.fragment.datasource.repository.PageDataRepository;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelFactoryProvider;
import com.amazon.mp3.customerprofile.viewModels.CustomerProfileViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomerProfileListsViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ%\u0010\t\u001a\u0002H\n\"\b\b\u0000\u0010\n*\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\n0\rH\u0016¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/customerprofile/CustomerProfileListsViewModelFactory;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelFactoryProvider$PageViewModelFactory;", "application", "Landroid/app/Application;", "pageDataRepository", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "usePagination", "", "(Landroid/app/Application;Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;Z)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "p0", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "getViewModelClass", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CustomerProfileListsViewModelFactory implements PageViewModelFactoryProvider.PageViewModelFactory {
    private Application application;
    private PageDataRepository pageDataRepository;
    private boolean usePagination;

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelFactoryProvider.PageViewModelFactory
    public Class<ViewModel> getViewModelClass() {
        return CustomerProfileViewModel.class;
    }

    public CustomerProfileListsViewModelFactory(Application application, PageDataRepository pageDataRepository, boolean z) {
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(pageDataRepository, "pageDataRepository");
        this.application = application;
        this.pageDataRepository = pageDataRepository;
        this.usePagination = z;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return new CustomerProfileListsViewModel(this.application, this.pageDataRepository, this.usePagination);
    }
}
