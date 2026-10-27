package com.amazon.music.destination;

import com.amazon.music.destination.core.Destination;

/* JADX INFO: loaded from: classes4.dex */
public class CustomerProfileDestination extends Destination {
    private final String profileKey;

    public CustomerProfileDestination(String str, String str2, String str3) {
        super(str2, str3);
        this.profileKey = str;
    }

    public String getProfileKey() {
        return this.profileKey;
    }
}
