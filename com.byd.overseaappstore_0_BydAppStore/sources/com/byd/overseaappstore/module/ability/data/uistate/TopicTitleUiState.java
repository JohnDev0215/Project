package com.byd.overseaappstore.module.ability.data.uistate;

import com.byd.overseaappstore.module.ability.data.UiState;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/uistate/TopicTitleUiState;", "Lcom/byd/overseaappstore/module/ability/data/UiState;", "", "data", "visible", "", "(Ljava/lang/String;Z)V", "getData", "()Ljava/lang/String;", "setData", "(Ljava/lang/String;)V", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TopicTitleUiState extends UiState<String> {

    @Nullable
    private String data;

    public TopicTitleUiState() {
        this(null, false, 3, 0 == true ? 1 : 0);
    }

    public TopicTitleUiState(@Nullable String str, boolean z) {
        super(str, z);
        this.data = str;
    }

    public /* synthetic */ TopicTitleUiState(String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? false : z);
    }

    @Override // com.byd.overseaappstore.module.ability.data.UiState, com.byd.overseaappstore.module.ability.data.DataUiState
    @Nullable
    public String getData() {
        return this.data;
    }

    public void setData(@Nullable String str) {
        this.data = str;
    }
}
