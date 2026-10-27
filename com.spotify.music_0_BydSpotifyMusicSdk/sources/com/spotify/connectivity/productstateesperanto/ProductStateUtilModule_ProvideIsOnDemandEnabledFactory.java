package com.spotify.connectivity.productstateesperanto;

import com.spotify.connectivity.flags.Flags;
import p.ul0;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class ProductStateUtilModule_ProvideIsOnDemandEnabledFactory implements ul0 {
    private final xy1 flagsProvider;

    public ProductStateUtilModule_ProvideIsOnDemandEnabledFactory(xy1 xy1Var) {
        this.flagsProvider = xy1Var;
    }

    public static ProductStateUtilModule_ProvideIsOnDemandEnabledFactory create(xy1 xy1Var) {
        return new ProductStateUtilModule_ProvideIsOnDemandEnabledFactory(xy1Var);
    }

    public static boolean provideIsOnDemandEnabled(Flags flags) {
        return ProductStateUtilModule.provideIsOnDemandEnabled(flags);
    }

    @Override // p.xy1
    public Boolean get() {
        return Boolean.valueOf(provideIsOnDemandEnabled((Flags) this.flagsProvider.get()));
    }
}
