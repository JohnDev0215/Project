package com.amazon.mp3.detailpages.actions;

import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: BaseViewModelFetcher.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/detailpages/actions/BaseViewModelFetcher;", "", "fetchModels", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "pageGridViewModel", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface BaseViewModelFetcher {
    List<BaseViewModel> fetchModels(PageGridViewModel pageGridViewModel);
}
