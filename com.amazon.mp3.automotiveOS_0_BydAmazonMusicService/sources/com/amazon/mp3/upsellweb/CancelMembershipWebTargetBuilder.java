package com.amazon.mp3.upsellweb;

import android.content.Context;
import com.amazon.mp3.account.AccountInfo;
import com.amazon.mp3.account.DeviceAttributes;
import com.amazon.mp3.web.webtargetbuilder.BaseWebTargetBuilder;
import com.amazon.mp3.web.webtargetbuilder.StandardWebTargetEndpoints;
import kotlin.Metadata;

/* JADX INFO: compiled from: CancelMembershipWebTargetBuilder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0011\b\u0010\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B%\b\u0000\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\rH\u0002¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/upsellweb/CancelMembershipWebTargetBuilder;", "Lcom/amazon/mp3/web/webtargetbuilder/BaseWebTargetBuilder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "accountInfo", "Lcom/amazon/mp3/account/AccountInfo;", "endpoints", "Lcom/amazon/mp3/web/webtargetbuilder/StandardWebTargetEndpoints;", "deviceAttributes", "Lcom/amazon/mp3/account/DeviceAttributes;", "(Lcom/amazon/mp3/account/AccountInfo;Lcom/amazon/mp3/web/webtargetbuilder/StandardWebTargetEndpoints;Lcom/amazon/mp3/account/DeviceAttributes;)V", "addPath", "", "addRedirectParameters", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CancelMembershipWebTargetBuilder extends BaseWebTargetBuilder {
    public CancelMembershipWebTargetBuilder(AccountInfo accountInfo, StandardWebTargetEndpoints standardWebTargetEndpoints, DeviceAttributes deviceAttributes) {
        super(accountInfo, standardWebTargetEndpoints, deviceAttributes);
        addPath();
        addRedirectParameters();
    }

    public CancelMembershipWebTargetBuilder(Context context) {
        this(new AccountInfo(context), new StandardWebTargetEndpoints(), new DeviceAttributes(context));
    }

    private final void addPath() {
        this.uriBuilder.path("mm/two-click/express-cancel");
    }

    private final void addRedirectParameters() {
        this.uriBuilder.appendQueryParameter("notNowRedirectURL", OfferPageRedirectPath.NOT_NOW.getBase64EncodedType());
    }
}
