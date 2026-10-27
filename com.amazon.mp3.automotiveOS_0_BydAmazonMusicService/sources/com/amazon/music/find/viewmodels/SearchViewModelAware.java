package com.amazon.music.find.viewmodels;

import com.amazon.music.find.viewmodels.BasePageGridViewModel;
import kotlin.Metadata;

/* JADX INFO: compiled from: SearchViewModelAware.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003R\u0012\u0010\u0004\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/find/viewmodels/SearchViewModelAware;", "T", "Lcom/amazon/music/find/viewmodels/BasePageGridViewModel;", "", "searchViewModel", "getSearchViewModel", "()Lcom/amazon/music/find/viewmodels/BasePageGridViewModel;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface SearchViewModelAware<T extends BasePageGridViewModel> {
    T getSearchViewModel();
}
