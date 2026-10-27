package com.amazon.device.crashmanager;

import android.content.pm.ApplicationInfo;

/* JADX INFO: loaded from: classes2.dex */
class ThirdPartyDomainChooser implements DomainChooser {
    private final boolean mAppIsDebuggable;

    ThirdPartyDomainChooser(ApplicationInfo applicationInfo) {
        this.mAppIsDebuggable = (applicationInfo.flags & 2) != 0;
    }

    @Override // com.amazon.device.crashmanager.DomainChooser
    public Domain chooseDomain() {
        if (!this.mAppIsDebuggable) {
            return Domain.PROD;
        }
        return Domain.BETA;
    }
}
