package com.amazon.minerva.client.common.internal.compliance;

import com.amazon.minerva.client.thirdparty.compliance.UserControlVerifier;

/* JADX INFO: loaded from: classes3.dex */
public class UserControlVerifierAdapter implements UserControlVerifier {
    private com.amazon.minerva.client.common.compliance.UserControlVerifier mUserControlVerifier;

    public UserControlVerifierAdapter(com.amazon.minerva.client.common.compliance.UserControlVerifier userControlVerifier) {
        this.mUserControlVerifier = userControlVerifier;
    }

    @Override // com.amazon.minerva.client.thirdparty.compliance.UserControlVerifier
    public boolean isUsageCollectionEnabled() throws Exception {
        return this.mUserControlVerifier.isUsageCollectionEnabled();
    }
}
