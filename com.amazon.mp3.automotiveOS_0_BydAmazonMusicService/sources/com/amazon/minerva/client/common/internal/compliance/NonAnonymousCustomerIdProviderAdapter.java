package com.amazon.minerva.client.common.internal.compliance;

import com.amazon.minerva.client.thirdparty.compliance.NonAnonymousCustomerIdProvider;

/* JADX INFO: loaded from: classes3.dex */
public class NonAnonymousCustomerIdProviderAdapter implements NonAnonymousCustomerIdProvider {
    private com.amazon.minerva.client.common.compliance.NonAnonymousCustomerIdProvider mNonAnonymousCustomerIdProvider;

    public NonAnonymousCustomerIdProviderAdapter(com.amazon.minerva.client.common.compliance.NonAnonymousCustomerIdProvider nonAnonymousCustomerIdProvider) {
        this.mNonAnonymousCustomerIdProvider = nonAnonymousCustomerIdProvider;
    }

    @Override // com.amazon.minerva.client.thirdparty.compliance.NonAnonymousCustomerIdProvider
    public String getNonAnonymousCustomerId() throws Exception {
        return this.mNonAnonymousCustomerIdProvider.getNonAnonymousCustomerId();
    }
}
