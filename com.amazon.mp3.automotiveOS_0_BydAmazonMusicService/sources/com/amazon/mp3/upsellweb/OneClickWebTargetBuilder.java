package com.amazon.mp3.upsellweb;

import android.content.Context;
import com.amazon.mp3.account.AccountInfo;
import com.amazon.mp3.account.DeviceAttributes;
import com.amazon.mp3.hawkfire.upsell.ActionType;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.mp3.web.webtargetbuilder.AbstractOneClickWebTargetBuilder;
import com.amazon.mp3.web.webtargetbuilder.MusicWebTargetEndpoints;
import com.amazon.mp3.web.webtargetbuilder.WebTargetEndpoints;

/* JADX INFO: loaded from: classes4.dex */
public class OneClickWebTargetBuilder extends AbstractOneClickWebTargetBuilder {
    OneClickWebTargetBuilder(final Context context) {
        this(new AccountInfo(context), new MusicWebTargetEndpoints(), new DeviceAttributes(context));
    }

    OneClickWebTargetBuilder(final AccountInfo accountInfo, final WebTargetEndpoints endpoints, final DeviceAttributes deviceAttributes) {
        super(accountInfo, endpoints, deviceAttributes);
        addPath();
        addRedirectParameters();
    }

    private void addPath() {
        this.uriBuilder.path("/unlimited/signup");
    }

    private void addRedirectParameters() {
        this.uriBuilder.appendQueryParameter("redirectURL", OfferPageRedirectPath.SUCCESS.getBase64EncodedType());
        this.uriBuilder.appendQueryParameter("notNowRedirectURL", OfferPageRedirectPath.NOT_NOW.getBase64EncodedType());
    }

    /* JADX INFO: renamed from: com.amazon.mp3.upsellweb.OneClickWebTargetBuilder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$hawkfire$upsell$ActionType;

        static {
            int[] iArr = new int[ActionType.values().length];
            $SwitchMap$com$amazon$mp3$hawkfire$upsell$ActionType = iArr;
            try {
                iArr[ActionType.SIGNUP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$hawkfire$upsell$ActionType[ActionType.UPGRADE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public OneClickWebTargetBuilder applyActionType(final ActionType action) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$hawkfire$upsell$ActionType[action.ordinal()];
        if (i == 1) {
            this.uriBuilder.path("/unlimited/oneclicksignup");
        } else if (i == 2) {
            this.uriBuilder.path("/unlimited/oneclickupgrade");
        }
        return this;
    }

    public OneClickWebTargetBuilder applyPlanName(final String planName) {
        this.uriBuilder.appendQueryParameter("subscriptionOverride", planName);
        return this;
    }

    @Override // com.amazon.mp3.web.webtargetbuilder.AbstractOneClickWebTargetBuilder, com.amazon.mp3.web.webtargetbuilder.BaseWebTargetBuilder, com.amazon.mp3.web.webtargetbuilder.WebTargetBuilder
    public WebTarget build() {
        return super.build();
    }
}
