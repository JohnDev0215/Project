package com.byd.overseaappstore.module.ability.data.uistate;

import com.byd.overseaappstore.module.ability.R$drawable;
import com.byd.overseaappstore.module.ability.R$string;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/uistate/ErrorEmptyUiState;", "", "visible", "", "picId", "", "tipId", "(ZII)V", "getPicId", "()I", "getTipId", "getVisible", "()Z", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ErrorEmptyUiState {
    private final int picId;
    private final int tipId;
    private final boolean visible;

    public ErrorEmptyUiState() {
        this(false, 0, 0, 7, null);
    }

    public ErrorEmptyUiState(boolean z, int i, int i2) {
        this.visible = z;
        this.picId = i;
        this.tipId = i2;
    }

    public /* synthetic */ ErrorEmptyUiState(boolean z, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? false : z, (i3 & 2) != 0 ? R$drawable.ic_no_data : i, (i3 & 4) != 0 ? R$string.no_data : i2);
    }

    public final int getPicId() {
        return this.picId;
    }

    public final int getTipId() {
        return this.tipId;
    }

    public final boolean getVisible() {
        return this.visible;
    }
}
