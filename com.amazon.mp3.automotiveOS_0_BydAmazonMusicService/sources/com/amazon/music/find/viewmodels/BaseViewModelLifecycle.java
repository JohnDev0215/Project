package com.amazon.music.find.viewmodels;

import androidx.lifecycle.LiveData;
import com.amazon.music.find.service.ExecutionContext;
import kotlin.Metadata;

/* JADX INFO: compiled from: BaseViewModelLifecycle.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH&J\b\u0010\t\u001a\u00020\u0004H&J\b\u0010\n\u001a\u00020\u0004H&¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/find/viewmodels/BaseViewModelLifecycle;", "T", "", "completeOperation", "", "executionContext", "Lcom/amazon/music/find/service/ExecutionContext;", "getPageData", "Landroidx/lifecycle/LiveData;", "onRefresh", "onResume", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface BaseViewModelLifecycle<T> {
    void completeOperation(ExecutionContext executionContext);

    LiveData<T> getPageData();

    void onRefresh();

    void onResume();
}
