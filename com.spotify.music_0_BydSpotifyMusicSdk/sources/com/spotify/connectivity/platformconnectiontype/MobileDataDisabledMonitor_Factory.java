package com.spotify.connectivity.platformconnectiontype;

import android.content.Context;
import p.ul0;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class MobileDataDisabledMonitor_Factory implements ul0 {
    private final xy1 contextProvider;

    public MobileDataDisabledMonitor_Factory(xy1 xy1Var) {
        this.contextProvider = xy1Var;
    }

    public static MobileDataDisabledMonitor_Factory create(xy1 xy1Var) {
        return new MobileDataDisabledMonitor_Factory(xy1Var);
    }

    public static MobileDataDisabledMonitor newInstance(Context context) {
        return new MobileDataDisabledMonitor(context);
    }

    @Override // p.xy1
    public MobileDataDisabledMonitor get() {
        return newInstance((Context) this.contextProvider.get());
    }
}
