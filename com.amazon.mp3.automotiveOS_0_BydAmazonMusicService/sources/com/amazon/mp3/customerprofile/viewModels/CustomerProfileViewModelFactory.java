package com.amazon.mp3.customerprofile.viewModels;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import com.amazon.mp3.catalog.fragment.datasource.repository.PageDataRepository;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelFactoryProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomerProfileViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J%\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u000bH\u0016¢\u0006\u0002\u0010\fJ\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/customerprofile/viewModels/CustomerProfileViewModelFactory;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelFactoryProvider$PageViewModelFactory;", "application", "Landroid/app/Application;", "pageDataRepository", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "(Landroid/app/Application;Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "p0", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "getViewModelClass", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CustomerProfileViewModelFactory implements PageViewModelFactoryProvider.PageViewModelFactory {
    private Application application;
    private PageDataRepository pageDataRepository;

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelFactoryProvider.PageViewModelFactory
    public Class<ViewModel> getViewModelClass() {
        return CustomerProfileViewModel.class;
    }

    public CustomerProfileViewModelFactory(Application application, PageDataRepository pageDataRepository) {
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(pageDataRepository, "pageDataRepository");
        this.application = application;
        this.pageDataRepository = pageDataRepository;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return new CustomerProfileViewModel(this.application, this.pageDataRepository);
    }
}
