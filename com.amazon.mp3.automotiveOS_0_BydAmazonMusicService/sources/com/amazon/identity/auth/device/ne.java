package com.amazon.identity.auth.device;

import com.amazon.minerva.client.thirdparty.compliance.UserControlVerifier;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class ne implements UserControlVerifier {
    @Override // com.amazon.minerva.client.thirdparty.compliance.UserControlVerifier
    public boolean isUsageCollectionEnabled() throws Exception {
        return true;
    }
}
