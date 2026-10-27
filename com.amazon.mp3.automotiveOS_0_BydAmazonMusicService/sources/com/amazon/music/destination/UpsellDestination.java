package com.amazon.music.destination;

import com.amazon.music.destination.core.Destination;

/* JADX INFO: loaded from: classes4.dex */
public class UpsellDestination extends Destination {
    private final boolean isBlocking;
    private final String upsellReason;
    private final String upsellSource;

    public UpsellDestination(String str, String str2, String str3, String str4, boolean z) {
        super(str, str2);
        this.isBlocking = z;
        this.upsellReason = str3;
        this.upsellSource = str4;
    }

    public boolean getIsBlocking() {
        return this.isBlocking;
    }

    public String getUpsellReason() {
        return this.upsellReason;
    }

    public String getUpsellSource() {
        return this.upsellSource;
    }
}
