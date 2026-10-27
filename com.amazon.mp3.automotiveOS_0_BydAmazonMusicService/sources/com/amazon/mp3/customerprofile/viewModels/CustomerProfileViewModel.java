package com.amazon.mp3.customerprofile.viewModels;

import android.app.Application;
import com.amazon.mp3.catalog.fragment.datasource.repository.PageDataRepository;
import com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomerProfileViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/customerprofile/viewModels/CustomerProfileViewModel;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseAndroidViewModel;", "application", "Landroid/app/Application;", "pageDataRepo", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "(Landroid/app/Application;Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CustomerProfileViewModel extends BaseAndroidViewModel {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomerProfileViewModel(Application application, PageDataRepository pageDataRepo) {
        super(application, pageDataRepo, false, false, 8, null);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(pageDataRepo, "pageDataRepo");
    }
}
