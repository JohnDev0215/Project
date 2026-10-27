package com.byd.overseaappstore.module.ability.util.notify.router.storage;

import com.byd.overseaappstore.module.ability.util.notify.router.BaseDialogData;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/byd/overseaappstore/module/ability/util/notify/router/storage/StorageState;", "Lcom/byd/overseaappstore/module/ability/util/notify/router/BaseDialogData;", "state", "", "(B)V", "getState", "()B", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StorageState extends BaseDialogData {
    private final byte state;

    public StorageState() {
        this((byte) 0, 1, null);
    }

    public StorageState(byte b) {
        this.state = b;
    }

    public /* synthetic */ StorageState(byte b, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (byte) 0 : b);
    }

    public final byte getState() {
        return this.state;
    }
}
