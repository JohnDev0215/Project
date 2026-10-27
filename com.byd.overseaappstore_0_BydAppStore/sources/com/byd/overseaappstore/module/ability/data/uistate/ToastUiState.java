package com.byd.overseaappstore.module.ability.data.uistate;

import com.byd.overseaappstore.module.ability.R$string;
import com.byd.overseaappstore.module.ability.data.UiState;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0005\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/uistate/ToastUiState;", "Lcom/byd/overseaappstore/module/ability/data/UiState;", "", "visible", "", "tipId", "(ZI)V", "getTipId", "()I", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ToastUiState extends UiState<Integer> {
    private final int tipId;

    public ToastUiState() {
        this(false, 0 == true ? 1 : 0, 3, null);
    }

    public ToastUiState(boolean z, int i) {
        super(null, z);
        this.tipId = i;
    }

    public /* synthetic */ ToastUiState(boolean z, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? false : z, (i2 & 2) != 0 ? R$string.net_no_connect_toast : i);
    }

    public final int getTipId() {
        return this.tipId;
    }
}
