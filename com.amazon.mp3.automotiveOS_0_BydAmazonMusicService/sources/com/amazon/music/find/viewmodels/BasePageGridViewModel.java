package com.amazon.music.find.viewmodels;

import com.amazon.music.find.service.ExecutionContext;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.models.PageGridViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BasePageGridViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u0016J\n\u0010\b\u001a\u0004\u0018\u00010\tH&¨\u0006\n"}, d2 = {"Lcom/amazon/music/find/viewmodels/BasePageGridViewModel;", "Lcom/amazon/music/find/viewmodels/BaseViewModel;", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "()V", "copyDataWithContext", "executionContext", "Lcom/amazon/music/find/service/ExecutionContext;", "pageData", "getUiPageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class BasePageGridViewModel extends BaseViewModel<PageGridViewModel> {
    public abstract PageType getUiPageType();

    @Override // com.amazon.music.find.viewmodels.BaseViewModel
    public PageGridViewModel copyDataWithContext(ExecutionContext executionContext, PageGridViewModel pageData) {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
        if (pageData == null) {
            return null;
        }
        return pageData.copy((1839 & 1) != 0 ? pageData.viewId : null, (1839 & 2) != 0 ? pageData.nestedModelViewId : null, (1839 & 4) != 0 ? pageData.title : null, (1839 & 8) != 0 ? pageData.subTitle : null, (1839 & 16) != 0 ? pageData.getModels() : null, (1839 & 32) != 0 ? pageData.getTileType() : 0, (1839 & 64) != 0 ? pageData.pageIndex : 0, (1839 & 128) != 0 ? pageData.nextPageToken : null, (1839 & 256) != 0 ? pageData.filters : null, (1839 & 512) != 0 ? pageData.context : new FindPageGridViewModelContext(executionContext), (1839 & 1024) != 0 ? pageData.header : null);
    }
}
