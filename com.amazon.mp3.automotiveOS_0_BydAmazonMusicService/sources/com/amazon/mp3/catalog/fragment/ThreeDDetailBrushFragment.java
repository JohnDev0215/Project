package com.amazon.mp3.catalog.fragment;

import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.models.BaseDetailHeaderModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.Iterator;
import kotlin.Metadata;

/* JADX INFO: compiled from: ThreeDDetailBrushFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0014J\b\u0010\u0005\u001a\u00020\u0006H\u0014¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/ThreeDDetailBrushFragment;", "Lcom/amazon/mp3/catalog/fragment/DetailBrushFragment;", "()V", "getBackgroundImageUrl", "", "getPageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ThreeDDetailBrushFragment extends DetailBrushFragment {
    @Override // com.amazon.mp3.catalog.fragment.DetailBrushFragment
    protected String getBackgroundImageUrl() {
        Object next;
        Iterator<T> it = this.mPageModel.getModels().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((BaseViewModel) next) instanceof BaseDetailHeaderModel) {
                break;
            }
        }
        BaseDetailHeaderModel baseDetailHeaderModel = (BaseDetailHeaderModel) next;
        if (baseDetailHeaderModel == null) {
            return null;
        }
        return baseDetailHeaderModel.getImageUrl();
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected PageType getPageType() {
        return PageType.BROWSE_3D;
    }
}
