package com.amazon.mp3.catalog.fragment.adapter;

import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: BaseViewsDiffUtilFactory.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsDiffUtilFactory;", "", "getBaseViewsDiffUtil", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsDiffUtil;", "oldList", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "newList", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface BaseViewsDiffUtilFactory {
    BaseViewsDiffUtil getBaseViewsDiffUtil(List<? extends BaseViewModel> oldList, List<? extends BaseViewModel> newList);
}
