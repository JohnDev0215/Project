package com.amazon.mp3.upsellweb;

import android.content.Context;
import com.amazon.mp3.account.AccountInfo;
import com.amazon.mp3.account.DeviceAttributes;
import com.amazon.mp3.account.SubscriptionTier;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.mp3.web.webtargetbuilder.BaseWebTargetBuilder;
import com.amazon.mp3.web.webtargetbuilder.MusicWebTargetEndpoints;
import com.amazon.mp3.web.webtargetbuilder.WebTargetEndpoints;

/* JADX INFO: loaded from: classes4.dex */
public class LandingPageWebTargetBuilder extends BaseWebTargetBuilder {
    LandingPageWebTargetBuilder(final Context context) {
        this(new AccountInfo(context), new MusicWebTargetEndpoints(), new DeviceAttributes(context));
    }

    LandingPageWebTargetBuilder(final AccountInfo accountInfo, final WebTargetEndpoints endpoints, final DeviceAttributes deviceAttributes) {
        super(accountInfo, endpoints, deviceAttributes);
        addPath();
    }

    private void addPath() {
        this.uriBuilder.path("unlimited");
    }

    /* JADX INFO: renamed from: com.amazon.mp3.upsellweb.LandingPageWebTargetBuilder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$account$SubscriptionTier;

        static {
            int[] iArr = new int[SubscriptionTier.values().length];
            $SwitchMap$com$amazon$mp3$account$SubscriptionTier = iArr;
            try {
                iArr[SubscriptionTier.UNLIMITED_FAMILY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$account$SubscriptionTier[SubscriptionTier.ECHO_HOME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$account$SubscriptionTier[SubscriptionTier.PRIME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public LandingPageWebTargetBuilder applyTier(final SubscriptionTier tier) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$account$SubscriptionTier[tier.ordinal()];
        if (i == 1) {
            this.uriBuilder.path("unlimited/family");
        } else if (i == 2) {
            this.uriBuilder.path("unlimited/echo");
        } else if (i == 3) {
            this.uriBuilder.path("prime");
        }
        return this;
    }

    @Override // com.amazon.mp3.web.webtargetbuilder.BaseWebTargetBuilder, com.amazon.mp3.web.webtargetbuilder.WebTargetBuilder
    public WebTarget build() {
        return super.build();
    }
}
