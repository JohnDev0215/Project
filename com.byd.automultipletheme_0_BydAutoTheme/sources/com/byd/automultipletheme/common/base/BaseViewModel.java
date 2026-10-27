package com.byd.automultipletheme.common.base;

import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;

/* JADX INFO: compiled from: BaseViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/byd/automultipletheme/common/base/BaseViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "appVersion", "Landroidx/lifecycle/LiveData;", "", "getAppVersion", "()Landroidx/lifecycle/LiveData;", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class BaseViewModel extends ViewModel {
    private final LiveData<String> appVersion = CoroutineLiveDataKt.liveData$default((CoroutineContext) null, 0, new BaseViewModel$appVersion$1(null), 3, (Object) null);

    public final LiveData<String> getAppVersion() {
        return this.appVersion;
    }
}
