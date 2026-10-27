package com.amazon.mp3.catalog.fragment;

import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtil;
import com.amazon.music.views.library.models.TextRowItemModel;
import com.amazon.music.views.library.models.VisualRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayQueueFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/PlayQueueDiffUtilCallback;", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsDiffUtil;", "oldList", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "newList", "(Ljava/util/List;Ljava/util/List;)V", "areItemsTheSame", "", "oldListIndex", "", "newListIndex", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlayQueueDiffUtilCallback extends BaseViewsDiffUtil {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayQueueDiffUtilCallback(List<? extends BaseViewModel> oldList, List<? extends BaseViewModel> newList) {
        super(oldList, newList);
        Intrinsics.checkNotNullParameter(oldList, "oldList");
        Intrinsics.checkNotNullParameter(newList, "newList");
    }

    @Override // com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtil, androidx.recyclerview.widget.DiffUtil.Callback
    public boolean areItemsTheSame(int oldListIndex, int newListIndex) {
        if (oldListIndex >= 0 && oldListIndex < getOldList().size()) {
            if (newListIndex >= 0 && newListIndex < getNewList().size()) {
                BaseViewModel baseViewModel = getOldList().get(oldListIndex);
                BaseViewModel baseViewModel2 = getNewList().get(newListIndex);
                if ((baseViewModel instanceof TextRowItemModel) && (baseViewModel2 instanceof TextRowItemModel)) {
                    return Intrinsics.areEqual(((TextRowItemModel) baseViewModel).getText(), ((TextRowItemModel) baseViewModel2).getText());
                }
                if ((baseViewModel instanceof VisualRowItemModel) && (baseViewModel2 instanceof VisualRowItemModel)) {
                    VisualRowItemModel visualRowItemModel = (VisualRowItemModel) baseViewModel;
                    VisualRowItemModel visualRowItemModel2 = (VisualRowItemModel) baseViewModel2;
                    if (Intrinsics.areEqual(visualRowItemModel.getMetadata().getUri(), visualRowItemModel2.getMetadata().getUri()) && Intrinsics.areEqual(visualRowItemModel.getTarget(), visualRowItemModel2.getTarget())) {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }
}
