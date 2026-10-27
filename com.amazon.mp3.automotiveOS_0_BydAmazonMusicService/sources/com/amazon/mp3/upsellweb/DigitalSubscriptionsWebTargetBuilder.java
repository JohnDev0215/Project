package com.amazon.mp3.upsellweb;

import android.content.Context;
import com.amazon.mp3.account.AccountInfo;
import com.amazon.mp3.account.DeviceAttributes;
import com.amazon.mp3.account.MarketplaceCategory;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.mp3.web.webtargetbuilder.BaseWebTargetBuilder;
import com.amazon.mp3.web.webtargetbuilder.StandardWebTargetEndpoints;
import com.amazon.mp3.web.webtargetbuilder.WebTargetEndpoints;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class DigitalSubscriptionsWebTargetBuilder extends BaseWebTargetBuilder {
    public DigitalSubscriptionsWebTargetBuilder(final Context context) {
        this(new AccountInfo(context), new StandardWebTargetEndpoints(), new DeviceAttributes(context));
    }

    DigitalSubscriptionsWebTargetBuilder(final AccountInfo accountInfo, final WebTargetEndpoints endpoints, final DeviceAttributes deviceAttributes) {
        super(accountInfo, endpoints, deviceAttributes);
        if (accountInfo.getMarketplaceCategory() == MarketplaceCategory.ROW) {
            this.uriBuilder.path("/unlimited/digital-subscriptions/signup");
        } else {
            this.uriBuilder.path("/music/unlimited/digital-subscriptions/signup");
        }
    }

    public DigitalSubscriptionsWebTargetBuilder withSegments(final List<String> segments) {
        Iterator<String> it = segments.iterator();
        while (it.hasNext()) {
            this.uriBuilder.appendPath(it.next());
        }
        return this;
    }

    public DigitalSubscriptionsWebTargetBuilder withNotNowRedirectURL() {
        this.uriBuilder.appendQueryParameter("notNowRedirectURL", OfferPageRedirectPath.NOT_NOW.getBase64EncodedType());
        return this;
    }

    public DigitalSubscriptionsWebTargetBuilder withRedirectURL() {
        this.uriBuilder.appendQueryParameter("redirectURL", OfferPageRedirectPath.SUCCESS.getBase64EncodedType());
        return this;
    }

    @Override // com.amazon.mp3.web.webtargetbuilder.BaseWebTargetBuilder, com.amazon.mp3.web.webtargetbuilder.WebTargetBuilder
    public WebTarget build() {
        return super.build();
    }
}
