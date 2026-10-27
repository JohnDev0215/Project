package com.byd.overseaappstore.module.ability.data.uistate;

import com.byd.overseaappstore.module.ability.data.RetUiState;
import com.byd.overseaappstore.module.ability.data.upper.FeatureThemTopicGroup;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B+\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bR\"\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/uistate/FeatureThemGroupUiState;", "Lcom/byd/overseaappstore/module/ability/data/RetUiState;", "", "Lcom/byd/overseaappstore/module/ability/data/upper/FeatureThemTopicGroup;", "ret", "", "data", "visible", "(ZLjava/util/List;Z)V", "getData", "()Ljava/util/List;", "setData", "(Ljava/util/List;)V", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FeatureThemGroupUiState extends RetUiState<List<? extends FeatureThemTopicGroup>> {

    @Nullable
    private List<FeatureThemTopicGroup> data;

    public FeatureThemGroupUiState() {
        this(false, null, false, 7, null);
    }

    public FeatureThemGroupUiState(boolean z, @Nullable List<FeatureThemTopicGroup> list, boolean z2) {
        super(list, Boolean.valueOf(z), z2);
        this.data = list;
    }

    public /* synthetic */ FeatureThemGroupUiState(boolean z, List list, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? null : list, (i & 4) != 0 ? false : z2);
    }

    @Override // com.byd.overseaappstore.module.ability.data.RetUiState, com.byd.overseaappstore.module.ability.data.DataUiState
    @Nullable
    public List<FeatureThemTopicGroup> getData() {
        return this.data;
    }

    public void setData(@Nullable List<FeatureThemTopicGroup> list) {
        this.data = list;
    }
}
