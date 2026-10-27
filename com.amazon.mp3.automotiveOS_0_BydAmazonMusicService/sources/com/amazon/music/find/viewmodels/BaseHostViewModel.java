package com.amazon.music.find.viewmodels;

import com.amazon.music.find.service.ExecutionContext;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: compiled from: BaseHostViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\b"}, d2 = {"Lcom/amazon/music/find/viewmodels/BaseHostViewModel;", "Lcom/amazon/music/find/viewmodels/BaseViewModel;", "Lcom/amazon/music/find/viewmodels/SearchHostViewData;", "()V", "copyDataWithContext", "executionContext", "Lcom/amazon/music/find/service/ExecutionContext;", "pageData", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class BaseHostViewModel extends BaseViewModel<SearchHostViewData> {
    @Override // com.amazon.music.find.viewmodels.BaseViewModel
    public SearchHostViewData copyDataWithContext(ExecutionContext executionContext, SearchHostViewData pageData) {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
        if (pageData == null) {
            return null;
        }
        return pageData.copy((WorkQueueKt.MASK & 1) != 0 ? pageData.fragmentType : null, (WorkQueueKt.MASK & 2) != 0 ? pageData.skillPath : null, (WorkQueueKt.MASK & 4) != 0 ? pageData.fragmentName : null, (WorkQueueKt.MASK & 8) != 0 ? pageData.queryParams : null, (WorkQueueKt.MASK & 16) != 0 ? pageData.extras : null, (WorkQueueKt.MASK & 32) != 0 ? pageData.title : null, (WorkQueueKt.MASK & 64) != 0 ? pageData.subTitle : null, (WorkQueueKt.MASK & 128) != 0 ? pageData.context : new FindPageGridViewModelContext(executionContext));
    }
}
