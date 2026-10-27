package com.amazon.music.destination;

import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class SignupDestination extends AbstractWebDestination {
    private final DeeplinkTier tier;
    private boolean userConfirmationRequired;

    public SignupDestination(DeeplinkTier deeplinkTier, Map<String, String> map, String str, String str2) {
        super(map, str, str2);
        this.tier = deeplinkTier;
    }

    public void setUserConfirmationRequired(boolean z) {
        this.userConfirmationRequired = z;
    }

    public DeeplinkTier getTier() {
        return this.tier;
    }

    public boolean isUserConfirmationRequired() {
        return this.userConfirmationRequired;
    }
}
