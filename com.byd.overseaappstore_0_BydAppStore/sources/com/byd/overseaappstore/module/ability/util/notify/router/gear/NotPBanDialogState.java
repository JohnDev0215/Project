package com.byd.overseaappstore.module.ability.util.notify.router.gear;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/byd/overseaappstore/module/ability/util/notify/router/gear/NotPBanDialogState;", "", "()V", "isShowing", "", "()Z", "setShowing", "(Z)V", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class NotPBanDialogState {

    @NotNull
    public static final NotPBanDialogState INSTANCE = new NotPBanDialogState();
    private static boolean isShowing;

    private NotPBanDialogState() {
    }

    public final boolean isShowing() {
        return isShowing;
    }

    public final void setShowing(boolean z) {
        isShowing = z;
    }
}
