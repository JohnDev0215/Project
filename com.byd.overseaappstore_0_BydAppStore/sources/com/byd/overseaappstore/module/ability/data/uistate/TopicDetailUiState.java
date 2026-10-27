package com.byd.overseaappstore.module.ability.data.uistate;

import com.byd.overseaappstore.module.ability.data.UiState;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B!\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\"\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/uistate/TopicDetailUiState;", "Lcom/byd/overseaappstore/module/ability/data/UiState;", "", "Lcom/byd/overseaappstore/module/ability/data/upper/Software;", "data", "visible", "", "(Ljava/util/List;Z)V", "getData", "()Ljava/util/List;", "setData", "(Ljava/util/List;)V", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TopicDetailUiState extends UiState<List<? extends Software>> {

    @Nullable
    private List<? extends Software> data;

    public TopicDetailUiState() {
        this(null, false, 3, 0 == true ? 1 : 0);
    }

    public TopicDetailUiState(@Nullable List<? extends Software> list, boolean z) {
        super(list, z);
        this.data = list;
    }

    public /* synthetic */ TopicDetailUiState(List list, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list, (i & 2) != 0 ? false : z);
    }

    @Override // com.byd.overseaappstore.module.ability.data.UiState, com.byd.overseaappstore.module.ability.data.DataUiState
    @Nullable
    public List<Software> getData() {
        return this.data;
    }

    public void setData(@Nullable List<? extends Software> list) {
        this.data = list;
    }
}
