package com.amazon.appmanager.lib.metrics;

import com.amazon.minerva.client.common.compliance.UserControlVerifier;

/* JADX INFO: loaded from: classes2.dex */
public class MaftDefaultUserControlVerifier implements UserControlVerifier {
    @Override // com.amazon.minerva.client.common.compliance.UserControlVerifier
    public boolean isUsageCollectionEnabled() throws Exception {
        return true;
    }
}
