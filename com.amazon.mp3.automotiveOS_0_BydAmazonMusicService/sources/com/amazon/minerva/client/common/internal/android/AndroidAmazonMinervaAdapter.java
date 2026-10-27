package com.amazon.minerva.client.common.internal.android;

import android.content.Context;
import com.amazon.minerva.client.common.api.MetricEvent;
import com.amazon.minerva.client.common.compliance.ChildProfileVerifier;
import com.amazon.minerva.client.common.compliance.NonAnonymousCustomerIdProvider;
import com.amazon.minerva.client.common.compliance.UserControlVerifier;
import com.amazon.minerva.client.common.internal.AmazonMinervaAdapter;
import com.amazon.minerva.client.common.internal.compliance.ChildProfileVerifierAdapter;
import com.amazon.minerva.client.common.internal.compliance.NonAnonymousCustomerIdProviderAdapter;
import com.amazon.minerva.client.common.internal.compliance.UserControlVerifierAdapter;
import com.amazon.minerva.client.common.internal.transport.OAuthProviderAdapter;
import com.amazon.minerva.client.common.transport.OAuthProvider;
import com.amazon.minerva.client.thirdparty.api.AmazonMinerva;
import com.amazon.minerva.client.thirdparty.api.AmazonMinervaAndroidClientBuilder;

/* JADX INFO: loaded from: classes3.dex */
public class AndroidAmazonMinervaAdapter implements AmazonMinervaAdapter {
    AmazonMinerva mMinervaAndroidClient;

    public AndroidAmazonMinervaAdapter(Context context, String str, String str2, OAuthProvider oAuthProvider, ChildProfileVerifier childProfileVerifier, UserControlVerifier userControlVerifier, NonAnonymousCustomerIdProvider nonAnonymousCustomerIdProvider, String str3, String str4, boolean z, boolean z2) {
        this.mMinervaAndroidClient = createMinervaBuilder(context, str, str2, oAuthProvider, childProfileVerifier, userControlVerifier, nonAnonymousCustomerIdProvider, str3, str4, z, z2).build();
    }

    protected AndroidAmazonMinervaAdapter() {
    }

    protected AmazonMinervaAndroidClientBuilder createMinervaBuilder(Context context, String str, String str2, OAuthProvider oAuthProvider, ChildProfileVerifier childProfileVerifier, UserControlVerifier userControlVerifier, NonAnonymousCustomerIdProvider nonAnonymousCustomerIdProvider, String str3, String str4, boolean z, boolean z2) {
        AmazonMinervaAndroidClientBuilder amazonMinervaAndroidClientBuilderStandard;
        OAuthProviderAdapter oAuthProviderAdapter = new OAuthProviderAdapter(oAuthProvider);
        ChildProfileVerifierAdapter childProfileVerifierAdapter = new ChildProfileVerifierAdapter(childProfileVerifier);
        if (str4 != null && !str4.isEmpty()) {
            amazonMinervaAndroidClientBuilderStandard = AmazonMinervaAndroidClientBuilder.standard(context, str4);
        } else {
            amazonMinervaAndroidClientBuilderStandard = AmazonMinervaAndroidClientBuilder.standard(context);
        }
        amazonMinervaAndroidClientBuilderStandard.withRegion(str).withDeviceType(str2).withOAuthProvider(oAuthProviderAdapter).withChildProfileVerifier(childProfileVerifierAdapter).withUseDES(z).withRunInitialUpload(z2);
        if (userControlVerifier != null) {
            amazonMinervaAndroidClientBuilderStandard.withUserControlVerifier(new UserControlVerifierAdapter(userControlVerifier));
        }
        if (nonAnonymousCustomerIdProvider != null) {
            amazonMinervaAndroidClientBuilderStandard.withNonAnonymousCustomerIdProvider(new NonAnonymousCustomerIdProviderAdapter(nonAnonymousCustomerIdProvider));
        }
        if (str3 != null && !str3.isEmpty()) {
            amazonMinervaAndroidClientBuilderStandard.withNonAnonymousDeviceId(str3);
        }
        return amazonMinervaAndroidClientBuilderStandard;
    }

    @Override // com.amazon.minerva.client.common.internal.AmazonMinervaAdapter
    public void record(MetricEvent metricEvent) {
        this.mMinervaAndroidClient.record(((AndroidMetricEventAdapter) metricEvent.getMetricEventDelegate()).getAndroidMetricEvent());
    }
}
