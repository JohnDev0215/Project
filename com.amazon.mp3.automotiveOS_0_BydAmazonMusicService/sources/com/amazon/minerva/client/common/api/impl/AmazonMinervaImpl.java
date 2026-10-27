package com.amazon.minerva.client.common.api.impl;

import android.content.Context;
import com.amazon.minerva.client.common.api.AmazonMinerva;
import com.amazon.minerva.client.common.api.MetricEvent;
import com.amazon.minerva.client.common.compliance.ChildProfileVerifier;
import com.amazon.minerva.client.common.compliance.NonAnonymousCustomerIdProvider;
import com.amazon.minerva.client.common.compliance.UserControlVerifier;
import com.amazon.minerva.client.common.internal.AmazonMinervaAdapter;
import com.amazon.minerva.client.common.internal.android.AndroidAmazonMinervaAdapter;
import com.amazon.minerva.client.common.internal.fireos.FireOSAmazonMinervaAdapter;
import com.amazon.minerva.client.common.internal.util.DevicePlatformIdentifierUtil;
import com.amazon.minerva.client.common.transport.OAuthProvider;

/* JADX INFO: loaded from: classes3.dex */
public class AmazonMinervaImpl implements AmazonMinerva {
    private AmazonMinervaAdapter mMinervaClientDelegate;
    private DevicePlatformIdentifierUtil mPlatformIdentifierUtil;

    public AmazonMinervaImpl(Context context, String str, String str2, OAuthProvider oAuthProvider, ChildProfileVerifier childProfileVerifier, UserControlVerifier userControlVerifier, NonAnonymousCustomerIdProvider nonAnonymousCustomerIdProvider, String str3, String str4, boolean z, boolean z2) {
        DevicePlatformIdentifierUtil devicePlatformIdentifierUtil = DevicePlatformIdentifierUtil.getInstance();
        this.mPlatformIdentifierUtil = devicePlatformIdentifierUtil;
        if (devicePlatformIdentifierUtil.isDevicePlatformFireOS()) {
            this.mMinervaClientDelegate = new FireOSAmazonMinervaAdapter(context, str);
        } else {
            this.mMinervaClientDelegate = new AndroidAmazonMinervaAdapter(context, str, str2, oAuthProvider, childProfileVerifier, userControlVerifier, nonAnonymousCustomerIdProvider, str3, str4, z, z2);
        }
    }

    protected AmazonMinervaImpl() {
        this.mPlatformIdentifierUtil = DevicePlatformIdentifierUtil.getInstance();
    }

    @Override // com.amazon.minerva.client.common.api.AmazonMinerva
    public void record(MetricEvent metricEvent) {
        this.mMinervaClientDelegate.record(metricEvent);
    }
}
