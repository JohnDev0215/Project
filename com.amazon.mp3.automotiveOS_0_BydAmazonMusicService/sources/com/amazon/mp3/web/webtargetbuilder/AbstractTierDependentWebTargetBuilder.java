package com.amazon.mp3.web.webtargetbuilder;

import com.amazon.mp3.account.AccountInfo;
import com.amazon.mp3.account.DeviceAttributes;
import com.amazon.mp3.account.SubscriptionTier;
import com.amazon.mp3.web.webtarget.WebTarget;

/* JADX INFO: loaded from: classes4.dex */
public abstract class AbstractTierDependentWebTargetBuilder extends BaseWebTargetBuilder {
    public abstract AbstractTierDependentWebTargetBuilder applyTier(SubscriptionTier tier);

    protected AbstractTierDependentWebTargetBuilder(final AccountInfo accountInfo, final WebTargetEndpoints endpoints, final DeviceAttributes deviceAttributes) {
        super(accountInfo, endpoints, deviceAttributes);
    }

    @Override // com.amazon.mp3.web.webtargetbuilder.BaseWebTargetBuilder, com.amazon.mp3.web.webtargetbuilder.WebTargetBuilder
    public WebTarget build() {
        return super.build();
    }
}
