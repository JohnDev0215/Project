package com.amazon.device.crashmanager;

import android.os.Build;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultDomainChooser implements DomainChooser {
    @Override // com.amazon.device.crashmanager.DomainChooser
    public Domain chooseDomain() {
        String str = Build.TYPE;
        if (str.equals("user") || str.equals("userdebug")) {
            return Domain.PROD;
        }
        return Domain.BETA;
    }
}
