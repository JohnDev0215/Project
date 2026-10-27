package com.amazon.mp3.detailpages.actions;

import com.amazon.music.views.library.ktx.BaseViewContainerKt;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: BaseViewModelFetcher.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/detailpages/actions/TrackListContainerBaseViewModelFetcher;", "Lcom/amazon/mp3/detailpages/actions/BaseViewModelFetcher;", "()V", "fetchModels", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "pageGridViewModel", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TrackListContainerBaseViewModelFetcher implements BaseViewModelFetcher {
    @Override // com.amazon.mp3.detailpages.actions.BaseViewModelFetcher
    public List<BaseViewModel> fetchModels(PageGridViewModel pageGridViewModel) {
        BaseViewContainerModel<?> baseViewContainerModelFindContainerByTag;
        List<BaseViewModel> models;
        List<BaseViewModel> models2 = (pageGridViewModel == null || (baseViewContainerModelFindContainerByTag = BaseViewContainerKt.findContainerByTag(pageGridViewModel, "TRACK_LIST_TAG")) == null) ? null : baseViewContainerModelFindContainerByTag.getModels();
        if (models2 != null) {
            return models2;
        }
        if (pageGridViewModel == null || (models = pageGridViewModel.getModels()) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : models) {
            List<String> tags = ((BaseViewModel) obj).getTags();
            boolean z = false;
            if (tags != null && tags.contains("TRACK_TAG")) {
                z = true;
            }
            if (z) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}
