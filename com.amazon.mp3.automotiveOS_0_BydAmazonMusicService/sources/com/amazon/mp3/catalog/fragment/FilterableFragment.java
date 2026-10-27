package com.amazon.mp3.catalog.fragment;

import androidx.lifecycle.MutableLiveData;
import com.amazon.music.views.library.models.FilterItemModel;
import kotlin.Metadata;

/* JADX INFO: compiled from: FilterableFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001R\u001c\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/FilterableFragment;", "", "Landroidx/lifecycle/MutableLiveData;", "Lcom/amazon/music/views/library/models/FilterItemModel;", "getActiveFilter", "()Landroidx/lifecycle/MutableLiveData;", "activeFilter", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public interface FilterableFragment {
    MutableLiveData<FilterItemModel> getActiveFilter();
}
