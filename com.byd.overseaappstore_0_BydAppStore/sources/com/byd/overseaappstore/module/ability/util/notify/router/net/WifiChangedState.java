package com.byd.overseaappstore.module.ability.util.notify.router.net;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0007J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0007J&\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\t¨\u0006\u0017"}, d2 = {"Lcom/byd/overseaappstore/module/ability/util/notify/router/net/WifiChangedState;", "", "preState", "", "nowState", "(Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "getNowState", "()Ljava/lang/Boolean;", "setNowState", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getPreState", "setPreState", "component1", "component2", "copy", "(Ljava/lang/Boolean;Ljava/lang/Boolean;)Lcom/byd/overseaappstore/module/ability/util/notify/router/net/WifiChangedState;", "equals", "other", "hashCode", "", "toString", "", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class WifiChangedState {

    @Nullable
    private Boolean nowState;

    @Nullable
    private Boolean preState;

    public WifiChangedState() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public WifiChangedState(@Nullable Boolean bool, @Nullable Boolean bool2) {
        this.preState = bool;
        this.nowState = bool2;
    }

    public /* synthetic */ WifiChangedState(Boolean bool, Boolean bool2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : bool, (i & 2) != 0 ? null : bool2);
    }

    public static /* synthetic */ WifiChangedState copy$default(WifiChangedState wifiChangedState, Boolean bool, Boolean bool2, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = wifiChangedState.preState;
        }
        if ((i & 2) != 0) {
            bool2 = wifiChangedState.nowState;
        }
        return wifiChangedState.copy(bool, bool2);
    }

    @Nullable
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Boolean getPreState() {
        return this.preState;
    }

    @Nullable
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Boolean getNowState() {
        return this.nowState;
    }

    @NotNull
    public final WifiChangedState copy(@Nullable Boolean preState, @Nullable Boolean nowState) {
        return new WifiChangedState(preState, nowState);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WifiChangedState)) {
            return false;
        }
        WifiChangedState wifiChangedState = (WifiChangedState) other;
        return Intrinsics.areEqual(this.preState, wifiChangedState.preState) && Intrinsics.areEqual(this.nowState, wifiChangedState.nowState);
    }

    @Nullable
    public final Boolean getNowState() {
        return this.nowState;
    }

    @Nullable
    public final Boolean getPreState() {
        return this.preState;
    }

    public int hashCode() {
        Boolean bool = this.preState;
        int iHashCode = (bool == null ? 0 : bool.hashCode()) * 31;
        Boolean bool2 = this.nowState;
        return iHashCode + (bool2 != null ? bool2.hashCode() : 0);
    }

    public final void setNowState(@Nullable Boolean bool) {
        this.nowState = bool;
    }

    public final void setPreState(@Nullable Boolean bool) {
        this.preState = bool;
    }

    @NotNull
    public String toString() {
        return "WifiChangedState(preState=" + this.preState + ", nowState=" + this.nowState + ")";
    }
}
