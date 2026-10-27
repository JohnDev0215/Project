package com.amazon.music.destination;

import com.amazon.music.destination.core.Destination;

/* JADX INFO: loaded from: classes4.dex */
public class IamDestination extends Destination {
    private final String campaignId;
    private final String category;
    private final boolean throttle;

    public IamDestination(String str, String str2, boolean z, String str3, String str4) {
        super(str, str2);
        this.throttle = z;
        this.campaignId = str3;
        this.category = str4;
    }

    public boolean getThrottle() {
        return this.throttle;
    }

    public String getCampaignId() {
        return this.campaignId;
    }

    public String getCategory() {
        return this.category;
    }
}
