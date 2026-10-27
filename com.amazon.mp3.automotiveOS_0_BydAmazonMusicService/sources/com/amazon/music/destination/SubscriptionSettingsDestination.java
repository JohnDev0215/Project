package com.amazon.music.destination;

import com.amazon.music.destination.parser.Action;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class SubscriptionSettingsDestination extends AbstractWebDestination {
    private final boolean isUserConfirmationRequired;
    private final SubscriptionSetting subscriptionSetting;

    public enum SubscriptionSetting {
        NONE(""),
        PAYMENT("payment"),
        RENEWAL("renewal");

        private final String value;

        SubscriptionSetting(String str) {
            this.value = str;
        }

        public String getValue() {
            return this.value;
        }

        public static SubscriptionSetting fromString(String str) {
            for (SubscriptionSetting subscriptionSetting : values()) {
                if (subscriptionSetting.value.equals(str)) {
                    return subscriptionSetting;
                }
            }
            return null;
        }
    }

    public SubscriptionSettingsDestination(SubscriptionSetting subscriptionSetting, Action action, Map<String, String> map, String str, String str2, boolean z) {
        super(map, str, str2);
        this.isUserConfirmationRequired = z;
        if (subscriptionSetting == SubscriptionSetting.NONE && action != null && action == Action.RENEW) {
            this.subscriptionSetting = SubscriptionSetting.RENEWAL;
        } else {
            this.subscriptionSetting = subscriptionSetting;
        }
    }

    public SubscriptionSetting getSubscriptionSetting() {
        return this.subscriptionSetting;
    }

    public boolean getIsUserConfirmationRequired() {
        return this.isUserConfirmationRequired;
    }
}
