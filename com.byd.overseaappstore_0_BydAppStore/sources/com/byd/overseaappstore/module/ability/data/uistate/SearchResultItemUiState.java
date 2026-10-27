package com.byd.overseaappstore.module.ability.data.uistate;

import com.byd.overseaappstore.module.ability.data.UiState;
import com.byd.overseaappstore.module.ability.data.upper.SearchResult;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/uistate/SearchResultItemUiState;", "Lcom/byd/overseaappstore/module/ability/data/UiState;", "Lcom/byd/overseaappstore/module/ability/data/upper/SearchResult;", "visible", "", "data", "(ZLcom/byd/overseaappstore/module/ability/data/upper/SearchResult;)V", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchResultItemUiState extends UiState<SearchResult> {
    public SearchResultItemUiState() {
        this(false, null, 3, 0 == true ? 1 : 0);
    }

    public SearchResultItemUiState(boolean z, @Nullable SearchResult searchResult) {
        super(searchResult, z);
    }

    public /* synthetic */ SearchResultItemUiState(boolean z, SearchResult searchResult, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? null : searchResult);
    }
}
