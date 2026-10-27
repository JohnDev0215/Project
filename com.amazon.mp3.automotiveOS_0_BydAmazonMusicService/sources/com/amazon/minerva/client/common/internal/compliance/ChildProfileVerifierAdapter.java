package com.amazon.minerva.client.common.internal.compliance;

import com.amazon.minerva.client.thirdparty.compliance.ChildProfileVerifier;

/* JADX INFO: loaded from: classes3.dex */
public class ChildProfileVerifierAdapter implements ChildProfileVerifier {
    private com.amazon.minerva.client.common.compliance.ChildProfileVerifier mChildProfileVerifier;

    public ChildProfileVerifierAdapter(com.amazon.minerva.client.common.compliance.ChildProfileVerifier childProfileVerifier) {
        this.mChildProfileVerifier = childProfileVerifier;
    }

    @Override // com.amazon.minerva.client.thirdparty.compliance.ChildProfileVerifier
    public boolean isChildProfile() throws Exception {
        return this.mChildProfileVerifier.isChildProfile();
    }
}
