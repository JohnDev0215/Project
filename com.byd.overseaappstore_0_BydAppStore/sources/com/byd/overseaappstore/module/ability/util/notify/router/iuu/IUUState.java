package com.byd.overseaappstore.module.ability.util.notify.router.iuu;

import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.notify.router.BaseDialogData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/byd/overseaappstore/module/ability/util/notify/router/iuu/IUUState;", "Lcom/byd/overseaappstore/module/ability/util/notify/router/BaseDialogData;", "softwareOptParam", "Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareOptParam;", "state", "", "(Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareOptParam;I)V", "getSoftwareOptParam", "()Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareOptParam;", "getState", "()I", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class IUUState extends BaseDialogData {

    @NotNull
    private final SoftwareOptParam softwareOptParam;
    private final int state;

    public IUUState(@NotNull SoftwareOptParam softwareOptParam, int i) {
        Intrinsics.checkNotNullParameter(softwareOptParam, "softwareOptParam");
        this.softwareOptParam = softwareOptParam;
        this.state = i;
    }

    public static /* synthetic */ IUUState copy$default(IUUState iUUState, SoftwareOptParam softwareOptParam, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            softwareOptParam = iUUState.softwareOptParam;
        }
        if ((i2 & 2) != 0) {
            i = iUUState.state;
        }
        return iUUState.copy(softwareOptParam, i);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final SoftwareOptParam getSoftwareOptParam() {
        return this.softwareOptParam;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getState() {
        return this.state;
    }

    @NotNull
    public final IUUState copy(@NotNull SoftwareOptParam softwareOptParam, int state) {
        Intrinsics.checkNotNullParameter(softwareOptParam, "softwareOptParam");
        return new IUUState(softwareOptParam, state);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IUUState)) {
            return false;
        }
        IUUState iUUState = (IUUState) other;
        return Intrinsics.areEqual(this.softwareOptParam, iUUState.softwareOptParam) && this.state == iUUState.state;
    }

    @NotNull
    public final SoftwareOptParam getSoftwareOptParam() {
        return this.softwareOptParam;
    }

    public final int getState() {
        return this.state;
    }

    public int hashCode() {
        return Integer.hashCode(this.state) + (this.softwareOptParam.hashCode() * 31);
    }

    @NotNull
    public String toString() {
        return "IUUState(softwareOptParam=" + this.softwareOptParam + ", state=" + this.state + ")";
    }
}
