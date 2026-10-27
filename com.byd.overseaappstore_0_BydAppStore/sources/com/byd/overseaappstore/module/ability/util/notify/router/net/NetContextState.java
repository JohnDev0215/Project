package com.byd.overseaappstore.module.ability.util.notify.router.net;

import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.notify.router.BaseDialogData;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.a2;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001BI\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0002\u0012\b\b\u0002\u0010\t\u001a\u00020\u0002\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0002¢\u0006\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\u0006R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\u0004\u001a\u0004\b\n\u0010\u0006R\u001f\u0010\r\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b8\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0004\u001a\u0004\b\u0012\u0010\u0006R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001a"}, d2 = {"Lcom/byd/overseaappstore/module/ability/util/notify/router/net/NetContextState;", "Lcom/byd/overseaappstore/module/ability/util/notify/router/BaseDialogData;", "", "wifiConnected", "Z", "getWifiConnected", "()Z", "cellularConnected", "getCellularConnected", "workWifiOnly", "getWorkWifiOnly", "", "Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareOptParam;", "optParamList", "Ljava/util/List;", "getOptParamList", "()Ljava/util/List;", "clickedDialogContinueOrCancel", "getClickedDialogContinueOrCancel", "Lspi/a2;", "bizSrc", "Lspi/a2;", "getBizSrc", "()Lspi/a2;", "<init>", "(ZZZLjava/util/List;Lspi/a2;Z)V", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class NetContextState extends BaseDialogData {

    @NotNull
    private final a2 bizSrc;
    private final boolean cellularConnected;
    private final boolean clickedDialogContinueOrCancel;

    @Nullable
    private final List<SoftwareOptParam> optParamList;
    private final boolean wifiConnected;
    private final boolean workWifiOnly;

    public NetContextState(boolean z, boolean z2, boolean z3, @Nullable List<SoftwareOptParam> list, @NotNull a2 bizSrc, boolean z4) {
        Intrinsics.checkNotNullParameter(bizSrc, "bizSrc");
        this.wifiConnected = z;
        this.cellularConnected = z2;
        this.workWifiOnly = z3;
        this.optParamList = list;
        this.bizSrc = bizSrc;
        this.clickedDialogContinueOrCancel = z4;
    }

    public /* synthetic */ NetContextState(boolean z, boolean z2, boolean z3, List list, a2 a2Var, boolean z4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? true : z2, (i & 4) != 0 ? true : z3, (i & 8) != 0 ? null : list, a2Var, (i & 32) != 0 ? false : z4);
    }

    @NotNull
    public final a2 getBizSrc() {
        return this.bizSrc;
    }

    public final boolean getCellularConnected() {
        return this.cellularConnected;
    }

    public final boolean getClickedDialogContinueOrCancel() {
        return this.clickedDialogContinueOrCancel;
    }

    @Nullable
    public final List<SoftwareOptParam> getOptParamList() {
        return this.optParamList;
    }

    public final boolean getWifiConnected() {
        return this.wifiConnected;
    }

    public final boolean getWorkWifiOnly() {
        return this.workWifiOnly;
    }
}
